// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package com.soffid.iam.service;

import es.caib.seycon.ng.servei.*;

import com.soffid.iam.api.PasswordStatus;
import com.soffid.iam.api.Task;
import com.soffid.iam.common.security.SoffidPrincipal;
import com.soffid.iam.config.Config;
import com.soffid.iam.model.AccountEntity;
import com.soffid.iam.model.AccountEntityDao;
import com.soffid.iam.model.AccountPasswordEntity;
import com.soffid.iam.model.Parameter;
import com.soffid.iam.model.PasswordDomainEntity;
import com.soffid.iam.model.PasswordEntity;
import com.soffid.iam.model.PasswordPolicyEntity;
import com.soffid.iam.model.PolicyForbiddenWordEntity;
import com.soffid.iam.model.SystemEntity;
import com.soffid.iam.model.SystemEntityDao;
import com.soffid.iam.model.TaskEntity;
import com.soffid.iam.model.UserAccountEntity;
import com.soffid.iam.model.UserEntity;
import com.soffid.iam.model.UserTypeEntity;
import com.soffid.iam.sync.engine.DispatcherHandler;
import com.soffid.iam.sync.engine.TaskHandler;
import com.soffid.iam.sync.intf.UserMgr;
import com.soffid.iam.sync.service.ConsoleLogonService;
import com.soffid.iam.utils.ConfigurationCache;
import com.soffid.iam.utils.Security;

import es.caib.seycon.ng.comu.AccountType;

import com.soffid.iam.ServiceLocator;
import com.soffid.iam.api.AccountStatus;
import com.soffid.iam.api.Audit;
import com.soffid.iam.api.Issue;
import com.soffid.iam.api.IssueStatus;
import com.soffid.iam.api.IssueUser;
import com.soffid.iam.api.Password;
import com.soffid.iam.api.PasswordValidation;
import com.soffid.iam.api.PolicyCheckResult;

import es.caib.seycon.ng.comu.TipusDominiUsuariEnumeration;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.remote.RemoteServiceLocator;
import es.caib.seycon.util.Base64;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @see es.caib.seycon.ng.servei.InternalPasswordService
 */
public class InternalPasswordServiceImpl extends com.soffid.iam.service.InternalPasswordServiceBase
		implements ApplicationContextAware {
	Log log = LogFactory.getLog(getClass());
	
	private ApplicationContext ctx;

	/**
	 * @throws InternalErrorException
	 * @see es.caib.seycon.ng.servei.InternalPasswordService#checkPolicy(es.caib.seycon.ng.model.UsuariEntity,
	 *      es.caib.seycon.ng.model.PoliticaContrasenyaDominiEntity,
	 *      com.soffid.iam.api.Password)
	 */
	@SuppressWarnings(value = "rawtypes")
	protected PolicyCheckResult handleCheckPolicy(com.soffid.iam.model.UserEntity user,
			com.soffid.iam.model.PasswordPolicyEntity politica, com.soffid.iam.api.Password password, 
			boolean ignoreMinimumPeriod)
			throws InternalErrorException {
		PolicyCheckResult pcr = internalCheckBasicPolicy(politica, password, getUserAccounts(user, politica));

		if (pcr != PolicyCheckResult.VALID)
			return pcr;

		if (user != null && isOldPassword(user, politica.getPasswordDomain(), password))
			return PolicyCheckResult.OLD_PASSWORD;

		if (ignoreMinimumPeriod)
			return PolicyCheckResult.VALID;

		if (politica.getMinimumPeriod() == null || politica.getMinimumPeriod() == 0)
			return PolicyCheckResult.VALID;
		
		for (PasswordEntity pe : getPasswordEntityDao().findLastByUserDomain(user, politica.getPasswordDomain())) {
			if (pe==null || pe.getDate()==null )
				return PolicyCheckResult.NOT_YET;
			if (pe.getExpirationDate() != null && pe.getExpirationDate().getTime() <= System.currentTimeMillis())
				return PolicyCheckResult.VALID;
			if (System.currentTimeMillis() - pe.getDate().getTime() < politica.getMinimumPeriod().longValue() * 1000 * 60 * 60 * 24 )
				return PolicyCheckResult.NOT_YET; 
		}
		return PolicyCheckResult.VALID; // No previous password
	}

	protected PolicyCheckResult handleCheckPolicy(com.soffid.iam.model.UserEntity user,
			com.soffid.iam.model.PasswordPolicyEntity politica, com.soffid.iam.api.Password password)
			throws InternalErrorException {
		return handleCheckPolicy(user, politica, password, false);
	}
	
	/**
	 * Gets the list of accounts that belong to a user and a selected password
	 * polciy apply.
	 * 
	 * @param user     user
	 * @param politica password policy
	 * @return set of accounts
	 */
	private Collection<AccountEntity> getUserAccounts(UserEntity user, PasswordPolicyEntity politica) {
		LinkedList<AccountEntity> accounts = new LinkedList<AccountEntity>();
		if (user != null) {
			for (UserAccountEntity uae : user.getAccounts()) {
				AccountEntity acc = uae.getAccount();
				if (acc.getType().equals(AccountType.USER)) {
					if (acc.getSystem().getPasswordDomain() == politica.getPasswordDomain())
						accounts.add(acc);
				}
			}
		}
		return accounts;
	}

	/**
	 * @throws InternalErrorException
	 * @see es.caib.seycon.ng.servei.InternalPasswordService#checkPolicy(es.caib.seycon.ng.model.UsuariEntity,
	 *      es.caib.seycon.ng.model.PoliticaContrasenyaDominiEntity,
	 *      com.soffid.iam.api.Password)
	 */
	@SuppressWarnings(value = "rawtypes")
	protected PolicyCheckResult handleCheckPolicy(com.soffid.iam.model.UserEntity user,
			com.soffid.iam.model.PasswordDomainEntity passwordDomain, com.soffid.iam.api.Password password,
			boolean ignoreMinimumPeriod)
			throws InternalErrorException {
		PasswordPolicyEntity politica = getUserPolicy(user, passwordDomain);
		if (politica == null)
			throw new InternalError ("No password policy available for user "+user.getUserName()+" and password domain "+passwordDomain.getName());
		return handleCheckPolicy(user, politica, password, ignoreMinimumPeriod);
	}

	@SuppressWarnings(value = "rawtypes")
	protected PolicyCheckResult handleCheckPolicy(com.soffid.iam.model.UserEntity user,
			com.soffid.iam.model.PasswordDomainEntity passwordDomain, com.soffid.iam.api.Password password)
			throws InternalErrorException {
		return handleCheckPolicy(user, passwordDomain, password, false);
	}
	
	private PolicyCheckResult internalCheckBasicPolicy(com.soffid.iam.model.PasswordPolicyEntity politica,
			com.soffid.iam.api.Password password, Collection<AccountEntity> accounts) {
		String uncrypted = password.getPassword();
		if (politica.getMaxLength() != null && uncrypted.length() > politica.getMaxLength().longValue()) {
			return PolicyCheckResult.TOO_LONG;
		}
		if (politica.getMinLength() != null && uncrypted.length() < politica.getMinLength().longValue()) {
			return PolicyCheckResult.TOO_SHORT;
		}
		int majs = 0;
		int mins = 0;
		int numb = 0;
		int others = 0;
		for (int i = 0; i < uncrypted.length(); i++) {
			char ch = uncrypted.charAt(i);
			if (Character.isUpperCase(ch))
				majs++;
			else if (Character.isLetter(ch))
				mins++;
			else if (Character.isDigit(ch))
				numb++;
			else
				others++;
		}

		if (politica.getMinLowerCase() != null && mins < politica.getMinLowerCase().longValue()) {
			return PolicyCheckResult.TOO_FEW_SMALLS;
		}

		if (politica.getMaxLowerCase() != null && mins > politica.getMaxLowerCase().longValue()) {
			return PolicyCheckResult.TOO_MANY_SMALLS;
		}

		if (politica.getMinUpperCase() != null && majs < politica.getMinUpperCase().longValue()) {
			return PolicyCheckResult.TOO_FEW_CAPS;
		}

		if (politica.getMaxUpperCase() != null && majs > politica.getMaxUpperCase().longValue()) {
			return PolicyCheckResult.TOO_MANY_CAPS;
		}

		if (politica.getMinNumbers() != null && numb < politica.getMinNumbers().longValue()) {
			return PolicyCheckResult.TOO_FEW_NUMBERS;
		}

		if (politica.getMaxNumbers() != null && numb > politica.getMaxNumbers().longValue()) {
			return PolicyCheckResult.TOO_MANY_NUMBERS;
		}

		if (politica.getMinSymbols() != null && others < politica.getMinSymbols().longValue()) {
			return PolicyCheckResult.TOO_FEW_SIGNS;
		}

		if (politica.getMaxSymbols() != null && others > politica.getMaxSymbols().longValue()) {
			return PolicyCheckResult.TOO_MANY_SIGNS;
		}

		if (politica.getRegularExpression() != null && politica.getRegularExpression().length() > 0) {
			try {
				if (!Pattern.matches(politica.getRegularExpression(), uncrypted)) {
					return PolicyCheckResult.REGEXP_NOT_MATCH;
				}
			} catch (PatternSyntaxException e) {
				return PolicyCheckResult.INVALID_REGEXP;
			}

		}

		if (politica.getForbiddenWords() != null) {
			for (Iterator it = politica.getForbiddenWords().iterator(); it.hasNext();) {
				PolicyForbiddenWordEntity ppce = (PolicyForbiddenWordEntity) it.next();
				if (uncrypted.contains(ppce.getForbiddenWord().getForbiddenWord())) {
					return new PolicyCheckResult(PolicyCheckResult.FORBIDDEN_WORD.getReasonCode(),
							ppce.getForbiddenWord().getForbiddenWord());
				}
			}
		}

		if (politica.getComplexPasswords() != null && politica.getComplexPasswords().booleanValue()) {
			return checkComplexRequirements(accounts, password);
		}
		return PolicyCheckResult.VALID;
	}

	private PolicyCheckResult checkComplexRequirements(Collection<AccountEntity> accounts, Password password) {
		// Check account inclussion
		for (AccountEntity acc : accounts) {
			if (password.getPassword().toLowerCase().contains(acc.getName().toLowerCase()))
				return PolicyCheckResult.CONTAINS_ACCOUNTNAME;

			if (acc.getDescription() != null && password.getPassword().toLowerCase().contains(acc.getDescription().toLowerCase()))
				return PolicyCheckResult.CONTAINS_NAME;
		}

		// Check for display name inclussion
		for (AccountEntity acc : accounts) {
			if (acc.getDescription() != null) {
				for (String part : acc.getDescription().split("[,.#-_ &\t]+")) {
					if (part.length() >= 3 && password.getPassword().toLowerCase().contains(part.toLowerCase()))
						return PolicyCheckResult.CONTAINS_NAME;
				}
			}
		}

		int mays = 0, mins = 0, numbers = 0, signs = 0, others = 0;

		for (char ch : password.getPassword().toCharArray()) {
			if (Character.isUpperCase(ch))
				mays = 1;
			else if (Character.isLowerCase(ch))
				mins = 1;
			else if (Character.isDigit(ch))
				numbers = 1;
			else if (Character.isLetter(ch))
				others = 1;
			else
				signs = 1;
		}

		if (mays + mins + numbers + signs + others < 3)
			return PolicyCheckResult.MORE_TYPES_OF_CHARS;

		return PolicyCheckResult.VALID;
	}

	/**
	 * @param untilDate
	 * @see es.caib.seycon.ng.servei.InternalPasswordService#storePassword(es.caib.seycon.ng.model.UsuariEntity,
	 *      java.lang.String, com.soffid.iam.api.Password, boolean)
	 */
	protected void handleStorePassword(com.soffid.iam.model.UserEntity user, PasswordDomainEntity dce,
			com.soffid.iam.api.Password password, boolean mustChange) throws java.lang.Exception {
		PasswordPolicyEntity pcd = null;
		pcd = getUserPolicy(user, dce);
		if (pcd == null)
			throw new InternalErrorException(String.format("Policy not found for password domain %s", dce.getName())); //$NON-NLS-1$

		doStorePassword(user, dce, pcd, password, mustChange ? "E" : "N", mustChange); //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * @see es.caib.seycon.ng.servei.InternalPasswordService#storePassword(es.caib.seycon.ng.model.UsuariEntity,
	 *      java.lang.String, com.soffid.iam.api.Password, boolean)
	 */
	protected void handleStoreAndForwardPassword(com.soffid.iam.model.UserEntity user, PasswordDomainEntity dce,
			com.soffid.iam.api.Password password, boolean mustChange) throws java.lang.Exception {

		handleStorePassword(user, dce, password, mustChange);

		createTask(TaskHandler.UPDATE_USER_PASSWORD, dce.getName(), user.getUserName(), password, mustChange, true);
	}

	private void doStorePassword(UserEntity usuari, PasswordDomainEntity dce, PasswordPolicyEntity pcd,
			com.soffid.iam.api.Password password, String estat, boolean mustChange) throws InternalErrorException {
		reorderOldPasswords(usuari, dce, pcd);

		PasswordEntity ce = getPasswordEntityDao().newPasswordEntity();
		ce.setDomain(dce);
		ce.setOrder(new Long(0));
		ce.setUser(usuari);
		Date d = new Date();
		ce.setDate(d);
		Calendar c = new GregorianCalendar();
		c.setTime(d);
		if (!mustChange) {
			c.set(Calendar.HOUR_OF_DAY, 0);
			c.set(Calendar.MINUTE, 0);
			c.set(Calendar.SECOND, 0);
			c.set(Calendar.MILLISECOND, 0);
			if (pcd.getAvailableTime() != null && pcd.getType().equals("M")) //$NON-NLS-1$
				c.add(Calendar.DAY_OF_MONTH, pcd.getAvailableTime().intValue());
			else if (pcd.getRenewalTime() != null && pcd.getType().equals("A")) //$NON-NLS-1$
				c.add(Calendar.DAY_OF_MONTH, pcd.getRenewalTime().intValue());
			else
				c.add(Calendar.DAY_OF_MONTH, 3650);
		}
		ce.setExpirationDate(c.getTime());
		ce.setPassword2(getDigest2(usuari.getId(), password));
		ce.setActive(estat);
		getPasswordEntityDao().create(ce);
		for (UserAccountEntity ua : usuari.getAccounts()) {
			AccountEntity acc = ua.getAccount();
			if (acc.getType().equals(AccountType.USER)
					&& (acc.getSystem().getUrl() == null || acc.getSystem().getUrl().isEmpty())) {
				acc.setLastPasswordSet(new Date());
				acc.setPasswordExpiration(c.getTime());
				getAccountEntityDao().update(acc, null);
			}
		}
	}

	/**
	 * Generar el digest SHA-1 de la contrase�a
	 * 
	 * @param password contrase�a a cifrar
	 * @return digest SHA-1 del password
	 * @throws InternalErrorException
	 * @throws UnsupportedEncodingException
	 */
	public String getDigest(Password password) throws InternalErrorException {
		try {
			if (digest == null) {
				digest = MessageDigest.getInstance("SHA-1"); //$NON-NLS-1$
			}
			synchronized (digest) {
				byte bytes[] = digest.digest(password.getPassword().getBytes("UTF-8")); //$NON-NLS-1$
				return Base64.encodeBytes(bytes);
			}
		} catch (Exception e) {
			throw new InternalErrorException(e.getMessage());
		}
	}

	public String getDigest2(Long id, Password password) throws InternalErrorException {
		try {
			if (digest2 == null) {
				digest2 = MessageDigest.getInstance("SHA-256"); //$NON-NLS-1$
			}
			synchronized (digest2) {
				digest2.update( id.toString().getBytes("UTF-8") );
				byte bytes[] = digest2.digest(password.getPassword().getBytes("UTF-8")); //$NON-NLS-1$
				return Base64.encodeBytes(bytes);
			}
		} catch (Exception e) {
			throw new InternalErrorException(e.getMessage());
		}
	}

	public String getDigest2b(Long id, Password password) throws InternalErrorException {
		try {
			if (digest == null) {
				digest = MessageDigest.getInstance("SHA-1"); //$NON-NLS-1$
			}
			synchronized (digest) {
				digest.update( id.toString().getBytes("UTF-8") );
				byte bytes[] = digest.digest(password.getPassword().getBytes("UTF-8")); //$NON-NLS-1$
				return Base64.encodeBytes(bytes);
			}
		} catch (Exception e) {
			throw new InternalErrorException(e.getMessage());
		}
	}

	private static MessageDigest digest;
	private static MessageDigest digest2;

	private void reorderOldPasswords(UserEntity user, PasswordDomainEntity dce, PasswordPolicyEntity pcd) {

		LinkedList<PasswordEntity> passwords = new LinkedList<PasswordEntity>(
				getPasswordEntityDao().findByUserDomain(user, dce));
		Collections.sort(passwords, new Comparator<PasswordEntity>() {

			public int compare(PasswordEntity o1, PasswordEntity o2) {
				if (o1.getOrder().longValue() == o2.getOrder().longValue())
					return 0;
				if (o1.getOrder().longValue() > o2.getOrder().longValue())
					return -1;
				else
					return +1;
			}
		});

		for (PasswordEntity contrasenya : passwords) {
			if (pcd == null || pcd.getRememberedPasswords() == null
					|| contrasenya.getOrder() + 1 >= pcd.getRememberedPasswords().longValue()) {
				getPasswordEntityDao().remove(contrasenya);
			} else {
				contrasenya.setOrder(contrasenya.getOrder() + 1);
				getPasswordEntityDao().update(contrasenya);
			}
		}
	}

	/**
	 * @see es.caib.seycon.ng.servei.InternalPasswordService#confirmPassword(es.caib.seycon.ng.model.UsuariEntity,
	 *      java.lang.String, com.soffid.iam.api.Password)
	 */
	protected void handleConfirmPassword(com.soffid.iam.model.UserEntity user, PasswordDomainEntity dce,
			com.soffid.iam.api.Password password) throws java.lang.Exception {

		PasswordPolicyEntity pcd = null;
		pcd = getUserPolicy(user, dce);
		if (pcd == null)
			throw new InternalErrorException(String.format("Policy not found for password domain %s", dce.getName())); //$NON-NLS-1$

		for (PasswordEntity contra : getPasswordEntityDao().findLastByUserDomain(user, dce)) {
			contra.setActive("S"); //$NON-NLS-1$
			getPasswordEntityDao().update(contra);
		}
	}

	private PasswordPolicyEntity getUserPolicy(com.soffid.iam.model.UserEntity user, PasswordDomainEntity dce) {
		return getPasswordPolicyEntityDao().findByPasswordDomainAndUserType(dce.getName(),
				user.getUserType().getName());
	}

	/**
	 * @see es.caib.seycon.ng.servei.InternalPasswordService#disableUntrustedPasswords(es.caib.seycon.ng.model.UsuariEntity)
	 */
	@SuppressWarnings("unchecked")
	protected void handleDisableUntrustedPasswords() throws java.lang.Exception {

		for (Iterator<PasswordDomainEntity> dcIterator = getPasswordDomainEntityDao().loadAll().iterator(); dcIterator
				.hasNext();) {
			PasswordDomainEntity dc = dcIterator.next();
			for (Iterator<PasswordPolicyEntity> tuIterator = dc.getPasswordPolicies().iterator(); tuIterator
					.hasNext();) {
				PasswordPolicyEntity pc = tuIterator.next();
				if (pc.getType().equals("M")) {
					disableUntrustedManualPasswords(dc, pc);
				} else {
					renewAutomaticPasswords(dc, pc);
				}
			}
		}
	}

	private void renewAutomaticPasswords(PasswordDomainEntity dc, PasswordPolicyEntity pc)
			throws InternalErrorException {
		Calendar c = new GregorianCalendar();
//        c.set(Calendar.HOUR_OF_DAY, 0);
//        c.set(Calendar.MINUTE, 0);
//        c.set(Calendar.SECOND, 0);
//        c.set(Calendar.MILLISECOND, 0);
//        c.add(Calendar.DAY_OF_MONTH, -1);

		Collection expired = getPasswordEntityDao().query(
				"select contrasenya " + "from com.soffid.iam.model.PasswordEntity as contrasenya "
						+ "where contrasenya.domain = :domini and " + "contrasenya.user.userType = :tipusUsuari and "
						+ "contrasenya.user.active=\'S\' and " + "contrasenya.expirationDate <= :caducitat and "
						+ "contrasenya.active in (\'S\', \'N\') ",
				new Parameter[] { new Parameter("domini", dc), new Parameter("tipusUsuari", pc.getUserType()),
						new Parameter("caducitat", c.getTime()) }); //$NON-NLS-1$
		for (Iterator<PasswordEntity> it = expired.iterator(); it.hasNext();) {
			PasswordEntity contra = it.next();
			Password password = generateRandomPassword(contra.getUser(), dc, pc, false, true);
			doStorePassword(contra.getUser(), dc, pc, password, "N", false);
			createTask(TaskHandler.UPDATE_USER_PASSWORD, dc.getName(), contra.getUser().getUserName(), password, false);
		}

		expired = getAccountPasswordEntityDao().query(
				"select pass " + "from com.soffid.iam.model.AccountPasswordEntity as pass "
						+ "join pass.account as account " + "where account.system.passwordDomain = :domini and "
						+ "  account.system.url is not null and " + "  account.passwordPolicy = :tipusUsuari and "
						+ "  account.disabled = false and " + "  account.type != 'I' and "
						+ "  pass.expirationDate <= :caducitat and " + "  pass.active in (\'S\', \'N\') and "
						+ "  pass.order = 0",
				new Parameter[] { new Parameter("domini", dc), new Parameter("tipusUsuari", pc.getUserType()),
						new Parameter("caducitat", c.getTime()) }); //$NON-NLS-1$
		for (Iterator<AccountPasswordEntity> it = expired.iterator(); it.hasNext();) {
			AccountPasswordEntity contra = it.next();
			Password password = generateRandomPassword(contra.getAccount(), pc, false, true);
			doStoreAccountPassword(contra.getAccount(), pc, password, "N", false, null);
			createAccountTask(TaskHandler.UPDATE_ACCOUNT_PASSWORD, contra.getAccount().getName(),
					contra.getAccount().getSystem().getName(), password, false, null);
		}
	}

	private void disableUntrustedManualPasswords(PasswordDomainEntity dc, PasswordPolicyEntity pc)
			throws InternalErrorException {
		Calendar c = new GregorianCalendar();
//        c.set(Calendar.HOUR_OF_DAY, 0);
//        c.set(Calendar.MINUTE, 0);
//        c.set(Calendar.SECOND, 0);
//        c.set(Calendar.MILLISECOND, 0);

		Collection expired = getPasswordEntityDao().query(
				"select contrasenya " + "from com.soffid.iam.model.PasswordEntity as contrasenya "
						+ "where contrasenya.domain = :domini and " + "contrasenya.user.userType = :tipusUsuari and "
						+ "contrasenya.expirationDate <= :caducitat and " + "contrasenya.active in (\'S\', \'N\') and "
						+ "contrasenya.order=0",
				new Parameter[] { new Parameter("domini", dc), new Parameter("tipusUsuari", pc.getUserType()),
						new Parameter("caducitat", c.getTime()) }); //$NON-NLS-1$
		for (Iterator<PasswordEntity> it = expired.iterator(); it.hasNext();) {
			PasswordEntity contra = it.next();
			Password password = generateRandomPassword(contra.getUser(), dc, pc, false, true);
			contra.setActive("E");
			getPasswordEntityDao().update(contra);
			createTask(TaskHandler.EXPIRE_USER_UNTRUSTED_PASSWORD, dc.getName(), contra.getUser().getUserName(),
					password, false);
		}

		expired = getAccountPasswordEntityDao().query(
				"select pass " + "from com.soffid.iam.model.AccountPasswordEntity as pass "
						+ "join pass.account as account " + "where account.system.passwordDomain = :domini and "
						+ "  account.system.url is not null and " + "  account.passwordPolicy = :tipusUsuari and "
						+ "  account.disabled = false and " + "  account.type != 'I' and "
						+ "  pass.expirationDate <= :caducitat and " + "  pass.active in (\'S\', \'N\') and "
						+ "  pass.order = 0",
				new Parameter[] { new Parameter("domini", dc), new Parameter("tipusUsuari", pc.getUserType()),
						new Parameter("caducitat", c.getTime()) }); //$NON-NLS-1$
		for (Iterator<AccountPasswordEntity> it = expired.iterator(); it.hasNext();) {
			AccountPasswordEntity contra = it.next();
			AccountEntity acc = contra.getAccount();
			if (acc.getType().equals(AccountType.PRIVILEGED) || acc.getSystem().getTrusted().equals("N")) {
				Password password = generateRandomPassword(contra.getAccount(), pc, false, true);
				doStoreAccountPassword(contra.getAccount(), pc, password, "N", false, null);
				createAccountTask(TaskHandler.UPDATE_ACCOUNT_PASSWORD, contra.getAccount().getName(),
						contra.getAccount().getSystem().getName(), password, false, null);
			} else {
				contra.setActive("E");
				getAccountPasswordEntityDao().update(contra);
			}
		}
	}

	private TaskHandler createTask(String transa, String dominiContrasenyes, String user, Password password,
			boolean mustChange, boolean force) throws InternalErrorException {
		TaskEntity tasque = getTaskEntityDao().newTaskEntity();
		tasque.setDate(new Timestamp(System.currentTimeMillis()));
		tasque.setTransaction(transa);
		tasque.setPasswordsDomain(dominiContrasenyes);
		tasque.setUser(user);
		tasque.setPassword(password.toString());
		tasque.setChangePassword(mustChange ? "S" : "N"); //$NON-NLS-1$ //$NON-NLS-2$
		tasque.setStatus("P"); //$NON-NLS-1$
		try {
			tasque.setTenant( getTenantEntityDao().load( Security.getCurrentTenantId() ));
			tasque.setServer( Config.getConfig().getHostName() );
			log.info("Creating task for host "+ tasque.getServer());
			return getTaskQueue().addTask(tasque);
		} catch (NoSuchBeanDefinitionException e) {
			tasque.setServer( null );
			if (force)
				getTaskEntityDao().createForce(tasque);
			else
				getTaskEntityDao().createNoFlush(tasque);
			return null;
		} catch (IOException e) {
			tasque.setServer( null );
			if (force)
				getTaskEntityDao().createForce(tasque);
			else
				getTaskEntityDao().createNoFlush(tasque);
			return null;
		}
	}

	private TaskHandler createTask(String transa, String dominiContrasenyes, String user, Password password,
			boolean mustChange) throws InternalErrorException {
		return createTask(transa, dominiContrasenyes, user, password, mustChange, false);
	}

	@SuppressWarnings("unchecked")
	private Map<String, Exception> executeOB(String transa, String dominiContrasenyes, String user, Password password,
			boolean mustChange) throws InternalErrorException {
		Task tasca = new Task();
		tasca.setTransaction(transa);
		tasca.setPasswordDomain(dominiContrasenyes);
		tasca.setUser(user);
		tasca.setPassword(password.toString());
		tasca.setPasswordChange(mustChange ? "S" : "N"); //$NON-NLS-1$ //$NON-NLS-2$
		tasca.setStatus("P"); //$NON-NLS-1$
		try {
			TaskHandler th = new TaskHandler();
			th.setTenant(Security.getCurrentTenantName());
			th.setTenantId(Security.getCurrentTenantId());
			th.setTask(tasca);
			return getTaskQueue().processOBTask(th);
		} catch (NoSuchBeanDefinitionException e) {
			createTask(transa, dominiContrasenyes, user, password, mustChange);
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	private Map<String, Exception> executeAccountOB(String transa, String account, String system, Password password,
			boolean mustChange) throws InternalErrorException {
		Task tasca = new Task();
		tasca.setTransaction(transa);
		tasca.setUser(account);
		tasca.setSystemName(system);
		tasca.setDatabase(system);
		tasca.setPassword(password.toString());
		tasca.setPasswordChange(mustChange ? "S" : "N"); //$NON-NLS-1$ //$NON-NLS-2$
		tasca.setStatus("P"); //$NON-NLS-1$
		try {
			TaskHandler th = new TaskHandler();
			th.setTenant(Security.getCurrentTenantName());
			th.setTenantId(Security.getCurrentTenantId());
			th.setTask(tasca);
			return getTaskQueue().processOBTask(th);
		} catch (NoSuchBeanDefinitionException e) {
			createAccountTask(transa, account, system, password, mustChange, null);
			return null;
		}
	}

	private Password generateRandomPassword(UserEntity usuariEntity, PasswordDomainEntity dc, PasswordPolicyEntity pc,
			boolean minLength, boolean maxLength) throws InternalErrorException {
		Random r = new Random(System.currentTimeMillis() + hashCode());
		Password password;
		int retries = 0;
		do {
			retries++;
			if (retries > 100)
				throw new InternalErrorException(
						String.format("Cannot generate valid password for domain %s, user type %s", dc.getName(),
								pc.getUserType().getName()));
			password = generatePasswordCandidate(pc, minLength, maxLength, r);

		} while (!handleCheckPolicy(usuariEntity, pc, password, true).isValid());
		return password;
	}

	private Password generatePasswordCandidate(PasswordPolicyEntity pc, boolean minLength, boolean maxLength,
			Random r) {
		Password password;
		StringBuffer sb;
		sb = new StringBuffer();
		int length;
		if (pc.getMinLength() == null)
			if (pc.getMaxLength() == null)
				length = 6;
			else if (minLength && pc.getMaxLength() > 6)
				length = 6;
			else
				length = pc.getMaxLength().intValue();
		else if (pc.getMaxLength() == null || pc.getMaxLength().equals(pc.getMinLength()))
			length = pc.getMinLength().intValue();
		else if (minLength)
			length = pc.getMinLength().intValue();
		else if (maxLength)
			length = pc.getMaxLength().intValue();
		else
			length = pc.getMinLength().intValue()
					+ r.nextInt(pc.getMaxLength().intValue() - pc.getMinLength().intValue());

		int minMays = pc.getMinUpperCase() == null ? 0 : pc.getMinUpperCase().intValue();
		int minMins = pc.getMinLowerCase() == null ? 0 : pc.getMinLowerCase().intValue();
		int minNums = pc.getMinNumbers() == null ? 0 : pc.getMinNumbers().intValue();
		int minSims = pc.getMinSymbols() == null ? 0 : pc.getMinSymbols().intValue();
		int maxMays, maxMins, maxNums, maxSims;
		if (pc.getComplexPasswords() != null && pc.getComplexPasswords().booleanValue()) {
			if (minMays <= 0)
				minMays = 1;
			if (minNums <= 0)
				minNums = 1;
			if (minSims <= 0)
				minSims = 1;
			if (minMins <= 0)
				minMins = 1;
			if (length < 4)
				length = 4;
		}
		maxMins = pc.getMaxLowerCase() == null ? length - minMays - minNums - minSims : pc.getMaxLowerCase().intValue();
		if (maxMins > length)
			maxMins = length;
		if (minLength && maxMins >= length - minMays - minNums - minSims) {
			maxSims = minSims;
			maxMays = minMays;
			maxNums = minNums;
			// _abcdefghijklmnopqrstuvwxyz
			// 12345678901234567890123456
			// 1 2 2
			for (int i = 0; i < maxMins; i++) {
				int ch = 'a' + r.nextInt(26);
				sb.append((char) ch);
			}
			maxMins = 0;

		} else {
			maxMays = length - minNums - minMins - minSims;
			if (pc.getMaxUpperCase() != null && pc.getMaxUpperCase().intValue() < maxMays)
				maxMays = pc.getMaxUpperCase().intValue();
			maxNums = length - minMays - minMins - minSims;
			if (pc.getMaxNumbers() != null && pc.getMaxNumbers().intValue() < maxNums)
				maxNums = pc.getMaxNumbers().intValue();
			maxSims = length - minMays - minMins - minNums;
			if (pc.getMaxSymbols() != null && pc.getMaxSymbols().intValue() < maxSims)
				maxSims = pc.getMaxSymbols().intValue();
		}
		while (sb.length() < length) {
			int remaining = length - sb.length();
			if (minMins > 0 && minMins + minMays + minNums + minSims == remaining) {
				sb.append((char) ('a' + r.nextInt(26)));
				minMins--;
			} else if (minMays > 0 && minMays + minNums + minSims == remaining) {
				sb.append((char) ('A' + r.nextInt(26)));
				minMays--;
			} else if (minNums > 0 && minNums + minSims == remaining) {
				sb.append((char) ('0' + r.nextInt(10)));
				minNums--;
			} else if (minSims > 0 && minSims == remaining) {
				sb.append((char) ('!' + r.nextInt(14)));
				minSims--;
			} else {
				int ch = r.nextInt(26 + 26 + 10 + 15);
				if (ch < 26 && maxMins > 0) {
					sb.append((char) ('a' + ch));
					maxMins--;
					if (minMins > 0)
						minMins--;
				}
				if (ch >= 26 && ch < 26 + 26 && maxMays > 0) {
					sb.append((char) ('A' + ch - 26));
					maxMays--;
					if (minMays > 0)
						minMays--;
				}
				if (ch >= 26 + 26 && ch < 26 + 26 + 10 && maxNums > 0) {
					sb.append((char) ('0' + ch - 26 - 26));
					maxNums--;
					if (minNums > 0)
						minNums--;
				}
				if (ch >= 26 + 26 + 10 && maxSims > 0) {
					sb.append((char) ('!' + ch - 26 - 26 - 10));
					maxSims--;
					if (minSims > 0)
						minSims--;
				}
			}
		}

		// Now shuffle
		char b[] = sb.toString().toCharArray();
		for (int i = 0; i < b.length; i++) {
			int j = r.nextInt(b.length);
			if (i != j) {
				char ch = b[i];
				b[i] = b[j];
				b[j] = ch;
			}
		}
		password = new Password(new String(b));
		return password;
	}

	/**
	 * @see es.caib.seycon.ng.servei.InternalPasswordService#isLastPassword(es.caib.seycon.ng.model.UsuariEntity,
	 *      com.soffid.iam.api.Password)
	 */
	protected boolean handleIsLastPassword(com.soffid.iam.model.UserEntity user, com.soffid.iam.api.Password password)
			throws java.lang.Exception {
		return false;
	}
	
	private Set<String> currentValidationRequests = new java.util.HashSet<String>();

	/**
	 * @see es.caib.seycon.ng.servei.InternalPasswordService#checkPassword(es.caib.seycon.ng.model.UsuariEntity,
	 *      es.caib.seycon.ng.model.DominiContrasenyaEntity,
	 *      com.soffid.iam.api.Password, boolean, boolean)
	 */
	protected PasswordValidation handleCheckPassword(com.soffid.iam.model.UserEntity user,
			com.soffid.iam.model.PasswordDomainEntity passwordDomain, com.soffid.iam.api.Password password,
			boolean checkTrusted, boolean checkExpired) throws java.lang.Exception {
		return checkUserPassword(user, null, passwordDomain, password, checkTrusted, checkExpired);
	}
	
	protected PasswordValidation checkUserPassword(com.soffid.iam.model.UserEntity user,
			AccountEntity account,
			com.soffid.iam.model.PasswordDomainEntity passwordDomain, com.soffid.iam.api.Password password,
			boolean checkTrusted, boolean checkExpired) throws java.lang.Exception {
		log.info("Checking password for "+user.getUserName()+"/"+passwordDomain.getName());
		
		if (user.getActive().equals("N")) { //$NON-NLS-1$
			if (debugPasswords())
				log.info("CheckUserPassword " +user.getUserName() + " / " + passwordDomain.getName() + " : WRONG (User disabled)");
			return PasswordValidation.PASSWORD_WRONG;
		}
		
		PasswordPolicyEntity ppe = getPasswordPolicyEntityDao().findByPasswordDomainAndUserType(passwordDomain.getName(), user.getUserType().getName());
		if (ppe == null)
			return PasswordValidation.PASSWORD_WRONG;
		
		PasswordEntity lastContra = null;
		for (PasswordEntity contra : getPasswordEntityDao().findLastByUserDomain(user, passwordDomain)) {
			if (contra != null && (contra.getActive().equals("S") || contra.getActive().equals("N") //$NON-NLS-1$
					|| contra.getActive().equals("E"))) {
				lastContra = contra;
				if ( isLocked (contra, ppe)) {
					log.info("CheckUserPassword " +user.getUserName() + " / " + passwordDomain.getName() + " : Password is temporarily locked");
					if (checkTrusted) updateFailures (contra, account, ppe);
					return PasswordValidation.PASSWORD_WRONG;
				}
				if ( isRightPassword(password, contra) ) {
					if (debugPasswords()) {
		            	log.info("CheckUserPassword " +user.getUserName() + " / " + passwordDomain.getName() + " : Current password matches");
		            }				
					if (new Date().before(contra.getExpirationDate())) {
						if (debugPasswords()) 
			            	log.info("CheckUserPassword " +user.getUserName() + " / " + passwordDomain.getName() + " : GOOD");
						if (account == null)
							updateAccountLastLogin(user, passwordDomain);
						else { 
							account.setLastLogin(new Date());
							getAccountEntityDao().update(account);
						}
						resetFailures (contra, ppe);
						return PasswordValidation.PASSWORD_GOOD;
					} else if (checkExpired) {
						if (debugPasswords()) 
			            	log.info("CheckUserPassword " +user.getUserName() + " / " + passwordDomain.getName() + " : EXPIRED");
						resetFailures (contra, ppe);
						return PasswordValidation.PASSWORD_GOOD_EXPIRED;
					} else {
						if (debugPasswords()) 
			            	log.info("CheckUserPassword " +user.getUserName() + " / " + passwordDomain.getName()+ " : WRONG (Expired)");
						return PasswordValidation.PASSWORD_WRONG;
					}
				} else {
					if (debugPasswords()) {
		            	log.info("CheckUserPassword " +user.getUserName() + " / " + passwordDomain.getName() + " : Current password does not match "+hash(password.getPassword()));
		            }				
				}
			}
		}

		boolean taskQueue = false;
		try {
			if (checkTrusted && getTaskQueue() != null) {
				String hash = user.getUserName()+":"+passwordDomain.getName()+":"+password.getPassword();
				MessageDigest digest = MessageDigest.getInstance("SHA-256"); //$NON-NLS-1$
				byte r[] = digest.digest(hash.getBytes("UTF-8"));
				hash = Base64.encodeBytes(r);
				if ( currentValidationRequests.contains(hash))
				{
					log.info("Detected recursive password validation for "+user.getUserName()+"/"+passwordDomain.getName());
					return PasswordValidation.PASSWORD_WRONG;
				}
				else
				{
					currentValidationRequests.add(hash);
					try {
						log.info("Checking password for "+user.getUserName()+"/"+passwordDomain.getName()+" on trusted systems. Creating task");
						taskQueue = true;
						if (validateOnDispatchers(user, passwordDomain, password)) {
							updateAccountLastLogin(user, passwordDomain);
							log.info("Storing password validate on trusted system");
							handleStoreAndForwardPassword(user, passwordDomain, password, false);
							return PasswordValidation.PASSWORD_GOOD;
						}
						else {
							if (lastContra != null && checkTrusted) 
								updateFailures (lastContra, account, ppe);
							return PasswordValidation.PASSWORD_WRONG;
						}
					} finally {
						currentValidationRequests.remove(hash);
					}
				}
			}
		} catch (NoSuchBeanDefinitionException e) {

		}
		
		if (checkTrusted) {
			if (!taskQueue) {
				// Console
				if ("true".equals(ConfigurationCache.getProperty("soffid.auth.trustedLogin"))) {
					log.info("Checking password for "+user.getUserName()+"/"+passwordDomain.getName()+" on trusted systems. Invoking sync server");
					for (UserAccountEntity userAccount : user.getAccounts()) {
						AccountEntity ae = userAccount.getAccount();
						if (!ae.isDisabled() && ae.getSystem().getPasswordDomain().getId().equals(passwordDomain.getId()) &&
								"S".equals(ae.getSystem().getTrusted())) {
							PasswordValidation status = validatePasswordOnServer(ae, password);
							if (status.equals(PasswordValidation.PASSWORD_GOOD)) {
								return status;
							}
						}
					}
				}
				updateFailures (lastContra, account, ppe);
			} else {
				// Sync server
				SoffidPrincipal p = Security.getSoffidPrincipal();
				// Do not lock twice. The console will lock it
				if (p == null ||
					p.getName() ==null ||
					!p.getName().endsWith("\\$$INTERNAL$$"))
						updateFailures (lastContra, account, ppe);
			}
		}

		return PasswordValidation.PASSWORD_WRONG;
	}

	private void resetFailures(PasswordEntity contra, PasswordPolicyEntity ppe) {
		if (contra.getFails() != null) {
			contra.setFails(null);
			contra.setUnlockDate(null);
			getPasswordEntityDao().update(contra);
		}
	}

	private void updateFailures(PasswordEntity contra, AccountEntity account2, PasswordPolicyEntity ppe) throws Exception {
		if (contra == null)
			return;
		contra.setFails(contra.getFails() == null ? 1: contra.getFails().intValue() + 1);
		if (ppe.getMaxFailures() != null && contra.getFails() > ppe.getMaxFailures() ) {
			Issue issue = new Issue();
			if (account2 != null)
				issue.setAccount(account2.getName()+"@"+account2.getSystem().getName());
			IssueUser iu = new IssueUser();
			iu.setUserName(contra.getUser().getUserName());
			iu.setUserId(contra.getUser().getId());
			issue.setUsers(Arrays.asList(iu));
			issue.setCreated(new Date());
			issue.setStatus(IssueStatus.NEW);
			issue.setType("locked-account");
			issue.setHash(contra.getId().toString());
			getIssueService().createInternalIssue(issue);
			if (ppe.getUnlockAfterSeconds() != null) {
				contra.setUnlockDate(new Date(System.currentTimeMillis() + ppe.getUnlockAfterSeconds().longValue() * 1000));
				if (account2 != null) {
					auditLockAccount(contra.getUser().getUserName(), account2);
				}
				else {
					for (AccountEntity account: getAccountEntityDao().findByUserAndSystem(contra.getUser().getUserName(), 
							handleGetDefaultDispatcher())) {
						auditLockAccount(contra.getUser().getUserName(), account);
					}
				}
			} else {
				contra.setUnlockDate(null);
				if (account2 != null) {
					account2.setStatus(AccountStatus.LOCKED);
					getAccountEntityDao().update(account2);
					auditLockAccount(contra.getUser().getUserName(), account2);
				}
				else {
					for (AccountEntity account: getAccountEntityDao().findByUserAndSystem(contra.getUser().getUserName(), 
							handleGetDefaultDispatcher())) {
						account.setStatus(AccountStatus.LOCKED);
						getAccountEntityDao().update(account);
						auditLockAccount(contra.getUser().getUserName(), account);
					}
				}
			}
		}
		getPasswordEntityDao().update(contra);
	}

	private boolean isLocked(PasswordEntity contra, PasswordPolicyEntity ppe) {
		if (ppe.getMaxFailures() == null)
			return false;
		if (contra.getFails() == null ||
				contra.getFails().intValue() < ppe.getMaxFailures().intValue()) {
			return false;
		}
		else {
			if (contra.getUnlockDate() == null || contra.getUnlockDate().before(new Date()))
				return false;
			else
				return true;
			
		}
	}

	class ThreadInfo {
		boolean success;
		int pending;
	}
	private boolean validateOnDispatchers(UserEntity user, PasswordDomainEntity passwordDomain, Password password) throws InternalErrorException {
		final ThreadInfo t = new ThreadInfo();
		t.success = false;
		t.pending = 0;
		final long timeOut = System.currentTimeMillis() + 60000; // 1 minute
		for (UserAccountEntity acc: user.getAccounts()) {
			if (! acc.getAccount().isDisabled() && acc.getAccount().getSystem().getPasswordDomain() == passwordDomain) {
				final String accountName = acc.getAccount().getName();
				final DispatcherHandler h = getTaskGenerator().getDispatcher(acc.getAccount().getSystem().getName());
				if (h != null && h.isConnected() && Boolean.TRUE.equals( h.getSystem().getTrusted()) ) {
					synchronized (t) {
						t.pending ++ ;
					}
					new Thread() {
						public void run() {
							try {
								Object agent = h.connect(false, false);
								if ( agent instanceof es.caib.seycon.ng.sync.intf.UserMgr &&
									((es.caib.seycon.ng.sync.intf.UserMgr) agent).validateUserPassword(accountName, es.caib.seycon.ng.comu.Password.toPassword(password))) {
									if (debugPasswords())
										log.info("CheckUserPassword " +user.getUserName() + " / " + passwordDomain.getName() + " : Password accepted by any system: "+h.getSystem().getName()+": "+hash(password.getPassword()));
									t.success = true;
								}
								else if ( agent instanceof UserMgr && 
									((UserMgr) agent).validateUserPassword(accountName, password)) {
									if (debugPasswords())
										log.info("CheckUserPassword " +user.getUserName() + " / " + passwordDomain.getName() + " : Password accepted by any system: "+h.getSystem().getName()+": "+hash(password.getPassword()));
									t.success = true;
								}
								else {
									log.info("CheckUserPassword " +user.getUserName() + " / " + passwordDomain.getName() + " : WRONG Password not accepted by system "+h.getSystem().getName()+": "+hash(password.getPassword()));
								}
							} catch (Exception e) {
								log.info("Cannot validate account "+accountName+" on "+h.getSystem().getName(), e);
							} finally {
								synchronized(t) {
									t.pending --;
									if (t.pending == 0 || t.success)
									{
										t.notify();
									}
								}	
							}
						}
					}.start();
				}
			}
		}

		try {
			synchronized (t) {
				long delay = timeOut - System.currentTimeMillis();
				if (t.success || t.pending == 0 || delay <= 0)
					return t.success;
				t.wait(delay);
				if (debugPasswords()) {
					if (t.success)
						log.info("CheckUserPassword " +user.getUserName() + " / " + passwordDomain.getName() + " : WRONG Password not accepted by any system: "+hash(password.getPassword()));
					else
						log.info("CheckUserPassword " +user.getUserName() + " / " + passwordDomain.getName() + " : GOOD Password accepted by  system: "+hash(password.getPassword()));
				}
				return t.success;
			}
		} catch (InterruptedException e) {
			return t.success;
		}
	}

	private boolean validateOnDispatchers(AccountEntity acc, Password password) throws InternalErrorException {
		final ThreadInfo t = new ThreadInfo();
		t.success = false;
		t.pending = 0;
		final long timeOut = System.currentTimeMillis() + 60000; // 1 minute

		if (! acc.isDisabled() ) {
			final String accountName = acc.getName();
			final DispatcherHandler h = getTaskGenerator().getDispatcher(acc.getSystem().getName());
			if (h != null && h.isConnected() ) {
				synchronized (t) {
					t.pending ++ ;
				}
				new Thread() {
					public void run() {
						try {
							Object agent = h.connect(false, false);
							if ( agent instanceof es.caib.seycon.ng.sync.intf.UserMgr &&
								((es.caib.seycon.ng.sync.intf.UserMgr) agent).validateUserPassword(accountName, es.caib.seycon.ng.comu.Password.toPassword(password))) {
								if (debugPasswords())
									log.info("CheckAccountPassword " +acc.getName() + " / " + acc.getSystem().getName() + " : "+hash(password.getPassword()));
								t.success = true;
							}
							else if ( agent instanceof UserMgr && 
								((UserMgr) agent).validateUserPassword(accountName, password)) {
								if (debugPasswords())
									log.info("CheckAccountPassword " +acc.getName() + " / " + acc.getSystem().getName() + " :  "+hash(password.getPassword()));
								t.success = true;
							}
							else {
								log.info("CheckAccountPassword " +acc.getName() + " / " + acc.getSystem().getName() + " : WRONG Password not accepted by system "+h.getSystem().getName()+": "+hash(password.getPassword()));
							}
						} catch (Exception e) {
							log.info("Cannot validate account "+acc.getName()+" on "+h.getSystem().getName(), e);
						} finally {
							synchronized(t) {
								t.pending --;
								if (t.pending == 0 || t.success)
								{
									t.notify();
								}
							}	
						}
					}
				}.start();
			}
		}

		try {
			synchronized (t) {
				long delay = timeOut - System.currentTimeMillis();
				if (t.success || t.pending == 0 || delay <= 0)
					return t.success;
				t.wait(delay);
				if (debugPasswords()) {
					if (t.success)
						log.info("CheckAccountPassword " +acc.getName() + " / " + acc.getSystem().getName() + " : WRONG Password not accepted by any system: "+hash(password.getPassword()));
					else
						log.info("CheckAccountPassword " +acc.getName() + " / " + acc.getSystem().getName() + " : GOOD Password accepted by  system: "+hash(password.getPassword()));
				}
				return t.success;
			}
		} catch (InterruptedException e) {
			return t.success;
		}
	}


	public boolean isRightPassword(Password password, PasswordEntity contra) throws InternalErrorException {
		String digest = getDigest(password);
		String digest2 = getDigest2(contra.getUser().getId(), password);
		String digest2b = getDigest2b(contra.getUser().getId(), password);

		return contra.getPassword() != null && digest.equals(contra.getPassword()) ||
				contra.getPassword2() != null && (digest2.equals(contra.getPassword2()) || digest2b.equals(contra.getPassword2()));
	}

	private void updateAccountLastLogin(UserEntity user, PasswordDomainEntity passwordDomain) {
		for (UserAccountEntity uac : user.getAccounts()) {
			SystemEntity dispatcher = uac.getAccount().getSystem();
			if (dispatcher.isMainSystem() && dispatcher.getPasswordDomain() == passwordDomain) {
				uac.getAccount().setLastLogin(new Date());
				getAccountEntityDao().update(uac.getAccount());
			}
		}
	}

	/**
	 * @see es.caib.seycon.ng.servei.InternalPasswordService#checkPin(es.caib.seycon.ng.model.UsuariEntity,
	 *      java.lang.String)
	 */
	protected boolean handleCheckPin(com.soffid.iam.model.UserEntity user, java.lang.String pin)
			throws java.lang.Exception {
		// @todo implement protected boolean
		// handleCheckPin(es.caib.seycon.ng.model.UsuariEntity user,
		// java.lang.String pin)
		return false;
	}

	/**
	 * @see es.caib.seycon.ng.servei.InternalPasswordService#disableExpiredPasswords()
	 */
	@SuppressWarnings(value = "unchecked")
	protected void handleDisableExpiredPassword() throws java.lang.Exception {
		for (Iterator<PasswordDomainEntity> dcIterator = getPasswordDomainEntityDao().loadAll().iterator(); dcIterator
				.hasNext();) {
			PasswordDomainEntity dc = dcIterator.next();
			for (Iterator<PasswordPolicyEntity> tuIterator = dc.getPasswordPolicies().iterator(); tuIterator
					.hasNext();) {
				PasswordPolicyEntity pc = tuIterator.next();
				if (pc.getAvailableTime() != null && pc.getGracePeriodTime() != null) {
					Calendar c = new GregorianCalendar();
					c.set(Calendar.HOUR_OF_DAY, 0);
					c.set(Calendar.MINUTE, 0);
					c.set(Calendar.SECOND, 0);
					c.set(Calendar.MILLISECOND, 0);
					c.add(Calendar.DAY_OF_YEAR, -pc.getGracePeriodTime().intValue());
					Collection<PasswordEntity> expired = getPasswordEntityDao().query(
							"select contrasenya " + "from com.soffid.iam.model.PasswordEntity as contrasenya "
									+ "where contrasenya.domain = :domini and "
									+ "contrasenya.user.userType.name = :tipusUsuari and "
									+ "contrasenya.expirationDate <= :caducitat and "
									+ "contrasenya.active = \'E\' and " + "contrasenya.order = 0",
							new Parameter[] { new Parameter("domini", dc),
									new Parameter("tipusUsuari", pc.getUserType().getName()),
									new Parameter("caducitat", c.getTime()) });
					for (Iterator<PasswordEntity> it = expired.iterator(); it.hasNext();) {
						PasswordEntity contra = it.next();
						Password password = generateRandomPassword(contra.getUser(), dc, pc, false, true);
						contra.setActive("D");
						getPasswordEntityDao().update(contra);
						createTask(TaskHandler.EXPIRE_USER_PASSWORD, dc.getName(), contra.getUser().getUserName(),
								password, false);
					}
					List<AccountPasswordEntity> expiredAccount = getAccountPasswordEntityDao().query("select pass "
							+ "from com.soffid.iam.model.AccountPasswordEntity as pass "
							+ "join pass.account as account " + "where account.system.passwordDomain = :domini and "
							+ "account.passwordPolicy = :tipusUsuari and " + "account.disabled = false and "
							+ "pass.expirationDate <= :caducitat and " + "pass.active = \'E\' and " + "pass.order = 0",
							new Parameter[] { new Parameter("domini", dc),
									new Parameter("tipusUsuari", pc.getUserType()),
									new Parameter("caducitat", c.getTime()) });
					for (Iterator<AccountPasswordEntity> it = expiredAccount.iterator(); it.hasNext();) {
						AccountPasswordEntity contra = it.next();
						AccountEntity acc = contra.getAccount();
						Password password = generateRandomPassword(contra.getAccount(), pc, false, true);
						doStoreAccountPassword(contra.getAccount(), pc, password, "N", false, null);
						createAccountTask(TaskHandler.UPDATE_ACCOUNT_PASSWORD, contra.getAccount().getName(),
								contra.getAccount().getSystem().getName(), password, false, null);
					}
				}
			}
		}
	}

	/**
	 * @see es.caib.seycon.ng.servei.InternalPasswordService#isOldPassword(es.caib.seycon.ng.model.UsuariEntity,
	 *      java.lang.String, com.soffid.iam.api.Password)
	 */
	protected boolean handleIsOldPassword(UserEntity user, PasswordDomainEntity passwordDomain, Password password)
			throws java.lang.Exception {
		for (Iterator it = getPasswordEntityDao().findByUserDomain(user, passwordDomain).iterator(); it.hasNext();) {
			PasswordEntity contra = (PasswordEntity) it.next();
			if (isRightPassword(password, contra) ) {
				if (debugPasswords())
					log.info("IsOldPassword "+user.getUserName()+"/"+passwordDomain.getName()+": Detected at position "+contra.getOrder());
				return true;
			}
		}
		return false;
	}

	/**
	 * Generates a new password
	 * 
	 * @param b
	 */

	protected Password randomPassword(UserEntity user, com.soffid.iam.model.PasswordDomainEntity passDomain,
			boolean mustChange, boolean fake, boolean forcePropagation) throws Exception {
		Password password = null;
		boolean found = false;

		PasswordPolicyEntity pcd = getUserPolicy(user, passDomain);
		if (pcd != null) {
			password = generateRandomPassword(user, passDomain, pcd, true, false);
			if (!fake) {
				doStorePassword(user, passDomain, pcd, password, mustChange ? "E" : "N", mustChange); //$NON-NLS-1$ //$NON-NLS-2$
				createTask(TaskHandler.UPDATE_USER_PASSWORD, passDomain.getName(), user.getUserName(), password,
						mustChange, forcePropagation);
			}
			return password;
		} else
			return null;
	}

	/**
	 * @see es.caib.seycon.ng.servei.InternalPasswordService#genrateNewPassword(es.caib.seycon.ng.model.UsuariEntity)
	 */
	@SuppressWarnings(value = "unchecked")
	@Override
	protected Password handleGenerateNewPassword(UserEntity user, com.soffid.iam.model.PasswordDomainEntity passDomain,
			boolean mustChange) throws Exception {
		return randomPassword(user, passDomain, mustChange, false, true);

	}

	@Override
	protected PasswordStatus handleGetPasswordsStatus(UserEntity user, PasswordDomainEntity domini) throws Exception {
		for (PasswordEntity last : getPasswordEntityDao().findLastByUserDomain(user, domini))
			return getPasswordEntityDao().toPasswordStatus(last);
		return null;
	}

	@Override
	protected Collection<PasswordStatus> handleGetExpiredPasswords(Date desde, Date finsa, UserTypeEntity tipusUsuari)
			throws Exception {
		return null;
	}

	@Override
	protected Password handleGenerateFakePassword(UserEntity user, PasswordDomainEntity passDomain) throws Exception {
		if (user == null) {
			Password pass = null;
			int len = 0;
			for (PasswordPolicyEntity politica : getPasswordPolicyEntityDao()
					.findByPasswordDomain(passDomain.getName())) {
				Password newPass = generateRandomPassword(null, passDomain, politica, false, true);
				if (len < newPass.getPassword().length()) {
					len = newPass.getPassword().length();
					pass = newPass;
				}
			}
			return pass;
		} else
			return randomPassword(user, passDomain, true, true, false);
	}

	@Override
	protected boolean handleIsPasswordExpired(UserEntity user, PasswordDomainEntity passwordDomain) throws Exception {

		for (PasswordEntity contra : getPasswordEntityDao().findLastByUserDomain(user, passwordDomain))
			if (contra.getExpirationDate().before(new Date()))
				return true;

		return false;
	}

	@Override
	protected void handleStorePassword(String user, String passwordDomain, String password, boolean mustChange)
			throws Exception {
		UserEntity usuari = getUserEntityDao().findByUserName(user);
		PasswordDomainEntity dc = getPasswordDomainEntityDao().findByName(passwordDomain);
		storePassword(usuari, dc, new Password(password), mustChange);
	}

	@Override
	protected boolean handleExistsPassword(UserEntity user, PasswordDomainEntity passwordDomain) throws Exception {
		return getPasswordEntityDao().findByUserDomain(user, passwordDomain).size() == 0;
	}

	private PasswordPolicyEntity getAccountPolicy(AccountEntity account) {
		PasswordDomainEntity domini = account.getSystem().getPasswordDomain();
		UserTypeEntity tipusUsuari = account.getPasswordPolicy();
		return getPasswordPolicyEntityDao().findByPasswordDomainAndUserType(domini.getName(), tipusUsuari.getName());

	}

	@Override
	protected PolicyCheckResult handleCheckAccountPolicy(AccountEntity account, Password password)
			throws InternalErrorException {
		if (account.getType().equals(AccountType.USER)) {
			UserEntity user = getUsuari(account);
			PasswordDomainEntity passwordDomain = getPasswordDomain(account);

			return handleCheckPolicy(user, getUserPolicy(user, passwordDomain), password);
		} else {
			PasswordPolicyEntity politica = getAccountPolicy(account);

			if (politica == null) {
				return PolicyCheckResult.NOPOLICY_DEFINED;
			}

			PolicyCheckResult pcr = internalCheckBasicPolicy(politica, password, Collections.singleton(account));

			if (pcr != PolicyCheckResult.VALID)
				return pcr;

			if (account != null && isOldAccountPassword(account, password))
				return PolicyCheckResult.OLD_PASSWORD;

			return PolicyCheckResult.VALID;
		}
	}

	private void reorderOldAccountPasswords(AccountEntity account, PasswordPolicyEntity pcd) {

		LinkedList<AccountPasswordEntity> passwords = new LinkedList<AccountPasswordEntity>(account.getPasswords());
		Collections.sort(passwords, new Comparator<AccountPasswordEntity>() {

			public int compare(AccountPasswordEntity o1, AccountPasswordEntity o2) {
				if (o1.getOrder().longValue() == o2.getOrder().longValue())
					return 0;
				if (o1.getOrder().longValue() > o2.getOrder().longValue())
					return -1;
				else
					return +1;
			}
		});

		for (AccountPasswordEntity ap : passwords) {
			if (pcd == null || pcd.getRememberedPasswords() == null
					|| ap.getOrder() + 1 >= pcd.getRememberedPasswords().longValue()) {
				getAccountPasswordEntityDao().remove(ap);
				account.getPasswords().remove(ap);
			} else {
				ap.setOrder(ap.getOrder() + 1);
				getAccountPasswordEntityDao().update(ap);
			}
		}
	}

	private void doStoreAccountPassword(AccountEntity account, PasswordPolicyEntity pcd,
			com.soffid.iam.api.Password password, String estat, boolean mustChange, Date untilDate)
			throws InternalErrorException {
		reorderOldAccountPasswords(account, pcd);

		AccountPasswordEntity ce = getAccountPasswordEntityDao().newAccountPasswordEntity();
		ce.setOrder(new Long(0));
		ce.setAccount(account);
		Date d = new Date();
		ce.setDate(d);
		Calendar c = new GregorianCalendar();
		c.setTime(d);
		if (!mustChange) {
			c.set(Calendar.HOUR_OF_DAY, 0);
			c.set(Calendar.MINUTE, 0);
			c.set(Calendar.SECOND, 0);
			c.set(Calendar.MILLISECOND, 0);
			if (pcd.getAvailableTime() != null && pcd.getType().equals("M")) //$NON-NLS-1$
				c.add(Calendar.DAY_OF_MONTH, pcd.getAvailableTime().intValue());
			else if (pcd.getRenewalTime() != null && pcd.getType().equals("A")) //$NON-NLS-1$
				c.add(Calendar.DAY_OF_MONTH, pcd.getRenewalTime().intValue());
			else
				c.add(Calendar.DAY_OF_MONTH, 3650);
		}
		if (untilDate != null && c.getTime().after(untilDate))
			c.setTime(untilDate);
		ce.setExpirationDate(c.getTime());
		ce.setPassword2(getDigest2(account.getId(), password));
		ce.setActive(estat);
		getAccountPasswordEntityDao().create(ce);
		account.getPasswords().add(ce);
		if (account.getSystem().getUrl() == null || account.getSystem().getUrl().isEmpty()) {
			account.setLastPasswordSet(new Date());
			account.setPasswordExpiration(c.getTime());
			getAccountEntityDao().update(account, "p");
		}
	}

	@Override
	protected void handleStoreAccountPassword(String account, String dispatcher, String password, boolean mustChange,
			Date untilDate) throws Exception {
		AccountEntityDao accDao = getAccountEntityDao();
		AccountEntity acc = accDao.findByNameAndSystem(account, dispatcher);
		if (acc.getType().equals(AccountType.USER)) {
			UserEntity user = getUsuari(acc);
			PasswordDomainEntity passwordDomain = getPasswordDomain(acc);
			handleStorePassword(user, passwordDomain, new Password(password), mustChange);
		} else {
			if (acc == null)
				throw new IllegalArgumentException(
						String.format(Messages.getString("InternalPasswordServiceImpl.PolicyNotFound"), account)); //$NON-NLS-1$

			PasswordPolicyEntity pcd = null;
			pcd = getAccountPolicy(acc);
			if (pcd == null)
				throw new InternalErrorException(
						String.format(Messages.getString("InternalPasswordServiceImpl.PolicyNotFound"), account)); //$NON-NLS-1$

			doStoreAccountPassword(acc, pcd, new Password(password), mustChange ? "E" : "N", mustChange, untilDate); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	@Override
	protected void handleStoreAccountPassword(AccountEntity account, Password password, boolean mustChange,
			Date untilDate) throws Exception {
		if (account.getType().equals(AccountType.USER)) {
			UserEntity user = getUsuari(account);
			PasswordDomainEntity passwordDomain = getPasswordDomain(account);
			handleStorePassword(user, passwordDomain, password, mustChange);
		} else {
			PasswordPolicyEntity pcd = null;
			pcd = getAccountPolicy(account);
			if (pcd == null)
				throw new InternalErrorException(String
						.format(Messages.getString("InternalPasswordServiceImpl.PolicyNotFound"), account.getName())); //$NON-NLS-1$

			doStoreAccountPassword(account, pcd, password, mustChange ? "E" : "N", mustChange, untilDate); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

    public String hash(String s) {
    	MessageDigest d;
		try {
			d = MessageDigest.getInstance("SHA-256");
			return Base64.encodeBytes(d.digest(s.getBytes( StandardCharsets.UTF_8 ) ), Base64.DONT_BREAK_LINES);
		} catch (NoSuchAlgorithmException e) {
			return s;
		}
    }
    
	Set<String> currentAccountValidationRequests = new HashSet<String>();
	@Override
	protected PasswordValidation handleCheckAccountPassword(AccountEntity account, Password password,
			boolean checkTrusted, boolean checkExpired) throws Exception {

		if (account.isDisabled()) {
			if (debugPasswords()) {
            	log.info("CheckAccountPassword " +account.getName() +  " @ " + account.getSystem().getName() + " : FALSE (Account disabled)");
            }
			return PasswordValidation.PASSWORD_WRONG;
		}
		
		if (account.getType().equals(AccountType.USER)) {
			UserEntity user = getUsuari(account);
			PasswordDomainEntity passwordDomain = getPasswordDomain(account);
			return checkUserPassword(user, account, passwordDomain, password, checkTrusted, checkExpired);
		} else {
			PasswordPolicyEntity ppe = getPasswordPolicyEntityDao()
					.findByPasswordDomainAndUserType(account.getSystem().getPasswordDomain().getName(), 
							account.getPasswordPolicy().getName());
			if (ppe == null)
				return PasswordValidation.PASSWORD_WRONG;

			AccountPasswordEntity contra = getAccountPasswordEntityDao().findLastByAccount(account.getId());
			if (contra != null && (contra.getActive().equals("S") || //$NON-NLS-1$
					contra.getActive().equals("N") || contra //$NON-NLS-1$ //$NON-NLS-2$
							.getActive().equals("E"))) { //$NON-NLS-1$
				if (isLocked(contra, ppe) ) {
					log.info("CheckAccountPassword " +account.getName()+" @ "+account.getSystem().getName() + " : Password is temporarily locked");
					updateFailures (contra, ppe);
					return PasswordValidation.PASSWORD_WRONG;
				}
				if (isRightPassword(password, contra)) 
				{
					if (debugPasswords()) {
		            	log.info("CheckAccountPassword " +account.getName() + " @ " + account.getSystem().getName()+ " : Current password matches");
		            }				
					if (new Date().before(contra.getExpirationDate())) {
						resetFailures (contra, ppe);
						if (debugPasswords()) 
			            	log.info("CheckAccountPassword " +account.getName() + " @ " + account.getSystem().getName()+ " : GOOD");
						return PasswordValidation.PASSWORD_GOOD;
					} else if (checkExpired) {
						resetFailures (contra, ppe);
						if (debugPasswords()) 
			            	log.info("CheckAccountPassword " +account.getName() + " @ " + account.getSystem().getName()+ " : EXPIRED");
						return PasswordValidation.PASSWORD_GOOD_EXPIRED;
					} else {
						if (debugPasswords()) 
			            	log.info("CheckAccountPassword " +account.getName() + " @ " + account.getSystem().getName()+ " : WRONG (Expired)");
						return PasswordValidation.PASSWORD_WRONG;
					}
				} else {
					if (debugPasswords()) {
		            	log.info("CheckAccountPassword " +account.getName() + " @ " + account.getSystem().getName()+ " : Current password does not match "+hash(password.getPassword()));
		            }				
				}
			} else {
				if (debugPasswords()) {
	            	log.info("CheckAccountPassword " +account.getName() + " @ " + account.getSystem().getName()+ " : No current password found");
	            }				
			}

			try {
				if (checkTrusted && getTaskQueue() != null) {
					String hash = account.getName()+":"+account.getSystem().getName()+":"+password.getPassword();
					MessageDigest digest = MessageDigest.getInstance("SHA-256"); //$NON-NLS-1$
					byte r[] = digest.digest(hash.getBytes("UTF-8"));
					hash = Base64.encodeBytes(r);
					if ( currentAccountValidationRequests.contains(hash))
					{
						log.info("Detected recursive password validation for "+account.getName()+"/"+account.getSystem().getName());
						return PasswordValidation.PASSWORD_WRONG;
					}
					else
					{
						try {
							currentAccountValidationRequests.add(hash);
							log.info("Received password validation for "+account.getName()+"/"+account.getSystem().getName()+". Forwarding task to trusted dispatchers");
							if (validateOnDispatchers(account, password)) {
								log.info("Storing password validate on trusted system");
								handleStoreAccountPassword(account, password, false, null);
								return PasswordValidation.PASSWORD_GOOD;
							}
							else {
								updateFailures (contra, ppe);
								return PasswordValidation.PASSWORD_WRONG;
							}
						} finally {
							currentAccountValidationRequests.remove(hash);
						}
					}

				}
			} catch (NoSuchBeanDefinitionException e) {
				if (checkTrusted) {
					if (debugPasswords())
						log.info("CheckAccountPassword " +account.getName() + " @ " + account.getSystem().getName()+ " : Sending request to server");
					return validatePasswordOnServer(account, password);
				}

			}

			return PasswordValidation.PASSWORD_WRONG;
		}
	}

	private void resetFailures(AccountPasswordEntity contra, PasswordPolicyEntity ppe) {
		if (contra.getFails() != null) {
			contra.setFails(null);
			contra.setUnlockDate(null);
			getAccountPasswordEntityDao().update(contra);
		}
	}

	private void updateFailures(AccountPasswordEntity contra, PasswordPolicyEntity ppe) throws Exception {
		contra.setFails(contra.getFails() == null ? 1: contra.getFails().intValue() + 1);
		if (ppe.getMaxFailures() != null && contra.getFails() > ppe.getMaxFailures() ) {
			auditLockAccount(null, contra.getAccount());
			Issue issue = new Issue();
			issue.setAccount(contra.getAccount().getName()+"@"+contra.getAccount().getSystem().getName());
			issue.setCreated(new Date());
			issue.setStatus(IssueStatus.NEW);
			issue.setType("locked-account");
			issue.setHash(contra.getId().toString());
			getIssueService().createInternalIssue(issue);
			if (ppe.getMaxFailures().intValue() == contra.getFails().intValue()) {
				contra.setUnlockDate(new Date(System.currentTimeMillis() + ppe.getUnlockAfterSeconds().longValue() * 1000));
			} else {
				contra.setUnlockDate(null);
				contra.getAccount().setStatus(AccountStatus.LOCKED);
				getAccountEntityDao().update(contra.getAccount());
			}
		}
		getAccountPasswordEntityDao().update(contra);
	}

	private boolean isLocked(AccountPasswordEntity contra, PasswordPolicyEntity ppe) {
		if (ppe.getMaxFailures() == null)
			return false;
		if (contra.getFails() == null ||
				contra.getFails().intValue() < ppe.getMaxFailures().intValue()) {
			if (contra.getUnlockDate() == null || contra.getUnlockDate().before(new Date()))
				return false;
			else
				return false;
		}
		else
			return true;
	}

	public boolean debugPasswords() {
		return "true".equals(ConfigurationCache.getProperty("soffid.server.trace-passwords"));
	}

	private PasswordValidation validatePasswordOnServer(AccountEntity account, Password password)
			throws InternalErrorException, IOException {

		if (account.getSystem().getUrl() != null) {
			try {
				log.info("Checking account in sync server: "+account.getName()+" at "+account.getSystem().getName());
				ConsoleLogonService ls = (ConsoleLogonService) getSyncServerService()
						.getServerService(ConsoleLogonService.REMOTE_PATH);
				if (ls != null) {
					PasswordValidation r = ls.validatePassword(account.getName(), account.getSystem().getName(),
							password.getPassword());
					if (debugPasswords()) {
						if (r == PasswordValidation.PASSWORD_GOOD)
							log.info("CheckAccountPassword " +account.getName() + " @ " + account.getSystem().getName() + " : GOOD Password accepted by syncserver");
						if (r == PasswordValidation.PASSWORD_GOOD_EXPIRED)
							log.info("CheckAccountPassword " +account.getName() + " @ " + account.getSystem().getName() + " : GOOD_EXPIRED Password accepted by syncserver");
						if (r == PasswordValidation.PASSWORD_WRONG)
							log.info("CheckAccountPassword " +account.getName() + " @ " + account.getSystem().getName() + " : WRONG Password accepted by syncserver "+hash(password.getPassword()));
					}
					return r;
				}
			} catch (Exception e) {
				log.warn("Logon failed in sync server: "+account.getName()+" at "+account.getSystem().getName(), e);
			}
		} else {
			if (debugPasswords()) {
				log.info("CheckAccountPassword " +account.getName() + " @ " + account.getSystem().getName() + " : WRONG (System not trusted or offline)");
			}
		}

		return PasswordValidation.PASSWORD_WRONG;
	}

	private PasswordDomainEntity getPasswordDomain(AccountEntity account) {
		return account.getSystem().getPasswordDomain();
	}

	private UserEntity getUsuari(AccountEntity account) throws InternalErrorException {
		for (UserAccountEntity uae : account.getUsers())
			return uae.getUser();
		throw new InternalErrorException(
				String.format(Messages.getString("InternalPasswordServiceImpl.NoUserForAccount"), account.getName(),
						account.getSystem().getName())); // $NON-NLS-1$
	}

	private TaskHandler createAccountTask(String transa, String account, String dispatcher, Password password,
			boolean mustChange, Date untilDate) throws InternalErrorException {
		TaskEntity tasque = getTaskEntityDao().newTaskEntity();
		tasque.setDate(new Timestamp(System.currentTimeMillis()));
		tasque.setTransaction(transa);
		tasque.setSystemName(dispatcher);
		tasque.setDb(dispatcher);
		tasque.setUser(account);
		tasque.setPassword(password.toString());
		tasque.setChangePassword(mustChange ? "S" : "N"); //$NON-NLS-1$ //$NON-NLS-2$
		tasque.setStatus("P"); //$NON-NLS-1$
		tasque.setExpirationDate(untilDate);
		getTaskEntityDao().create(tasque);
		try {
			return getTaskQueue().addTask(tasque);
		} catch (NoSuchBeanDefinitionException e) {
			return null;
		}
	}

	@Override
	protected void handleConfirmAccountPassword(AccountEntity account, Password password) throws Exception {
		AccountPasswordEntity contra = getAccountPasswordEntityDao().findLastByAccount(account.getId());
		if (contra != null && 
				isRightPassword(password, contra) && 
				contra.getActive().equals("N")) { //$NON-NLS-1$
			contra.setActive("S"); //$NON-NLS-1$
			getAccountPasswordEntityDao().update(contra);
		}
	}

	@Override
	protected boolean handleIsOldAccountPassword(AccountEntity account, Password password) throws Exception {
		if (account.getType().equals(AccountType.USER)) {
			UserEntity user = getUsuari(account);
			PasswordDomainEntity passwordDomain = getPasswordDomain(account);
			return handleIsOldPassword(user, passwordDomain, password);
		} else {
			for (AccountPasswordEntity contra : account.getPasswords()) {
				if (isRightPassword(password, contra))  {
					if (debugPasswords()) {
						log.info("IsOldPassword "+account.getName()+"/"+account.getSystem().getName()+": Detected at position "+contra.getOrder());
						return true;
					}
				}
			}
			return false;
		}
	}

	public boolean isRightPassword(Password password, AccountPasswordEntity contra) throws InternalErrorException {
		String digest = getDigest(password);
		String digest2 = getDigest2(contra.getAccount().getId(), password);
		String digest2b = getDigest2b(contra.getAccount().getId(), password);

		return contra.getPassword() != null && digest.equals(contra.getPassword()) ||
				contra.getPassword2() != null && (digest2.equals(contra.getPassword2())  || 
						digest2b.equals(contra.getPassword2()));
	}

	private Password generateRandomPassword(AccountEntity account, PasswordPolicyEntity pc, boolean minLength,
			boolean maxLength) throws InternalErrorException {
		Random r = new Random(System.currentTimeMillis() + hashCode());
		Password password;
		int retries = 0;
		do {
			retries++;
			if (retries > 100)
				throw new InternalErrorException(
						String.format(Messages.getString("InternalPasswordServiceImpl.CannotGeneratePassword"),
								account.getName(), account.getSystem().getName()));
			password = generatePasswordCandidate(pc, minLength, maxLength, r);

		} while (!handleCheckAccountPolicy(account, password).isValid());
		return password;
	}

	/**
	 * Generates a new password
	 */

	protected Password randomPassword(AccountEntity account, boolean mustChange, boolean fake) throws Exception {
		Password password = null;

		PasswordPolicyEntity pcd = getAccountPolicy(account);
		if (pcd != null) {
			password = generateRandomPassword(account, pcd, true, false);
			if (!fake) {
				doStoreAccountPassword(account, pcd, password, "N", mustChange, null); //$NON-NLS-1$
				if (account.getSystem().getUrl() != null && !account.getSystem().getUrl().isEmpty())
					createAccountTask(TaskHandler.UPDATE_ACCOUNT_PASSWORD, account.getName(),
							account.getSystem().getName(), password, mustChange, null);
			}
			return password;
		} else
			return null;
	}

	@Override
	protected Password handleGenerateNewAccountPassword(AccountEntity account, boolean mustChange) throws Exception {
		return randomPassword(account, mustChange, false);
	}

	@Override
	protected PasswordStatus handleGetAccountPasswordsStatus(AccountEntity account) throws Exception {
		AccountPasswordEntity last = getAccountPasswordEntityDao().findLastByAccount(account.getId());
		if (last == null)
			return null;
		else
			return getAccountPasswordEntityDao().toPasswordStatus(last);
	}

	@Override
	protected Password handleGenerateFakeAccountPassword(AccountEntity account) throws Exception {
		return randomPassword(account, true, true);
	}

	@Override
	protected void handleStoreAndForwardAccountPassword(AccountEntity account, Password password, boolean mustChange,
			Date untilDate) throws Exception {
		if (account.getType().equals(AccountType.USER)) {
			UserEntity user = getUsuari(account);
			PasswordDomainEntity passwordDomain = getPasswordDomain(account);
			handleStoreAndForwardPassword(user, passwordDomain, password, mustChange);
		} else {
			handleStoreAccountPassword(account, password, mustChange, untilDate);

			createAccountTask(TaskHandler.UPDATE_ACCOUNT_PASSWORD, account.getName(), account.getSystem().getName(),
					password, mustChange, untilDate);
		}
	}

	@Override
	protected boolean handleIsAccountPasswordExpired(AccountEntity account) throws Exception {
		if (account.getType().equals(AccountType.USER)) {
			for (UserAccountEntity uae : account.getUsers()) {
				return handleIsPasswordExpired(uae.getUser(), account.getSystem().getPasswordDomain());
			}
		} else {
			AccountPasswordEntity contra = getAccountPasswordEntityDao().findLastByAccount(account.getId());

			if (contra.getExpirationDate().before(new Date())) {
				return true;
			}
		}

		return false;
	}

	@Override
	protected boolean handleExistsAccountPassword(AccountEntity account) throws Exception {
		return account.getPasswords().isEmpty();
	}

	HashMap<String, String> defaultDispatchers = new HashMap<String, String>();

	@Override
	protected String handleGetDefaultDispatcher() throws Exception {
		String defaultDispatcher = defaultDispatchers.get(Security.getCurrentTenantName());
		if (defaultDispatcher == null) {
			SystemEntityDao dao = getSystemEntityDao();
			String defaultName = ConfigurationCache.getProperty("soffid.auth.system");
			if (defaultName != null) {
				SystemEntity dispatcher = dao.findByName(defaultName); // $NON-NLS-1$
				if (dispatcher != null) {
					defaultDispatchers.put(Security.getCurrentTenantName(), defaultName);
					return defaultName;
				}
			}

			for (SystemEntity dispatcher : dao.loadAll()) {
				if (dispatcher.isMainSystem()) {
					defaultDispatcher = dispatcher.getName();
					defaultDispatchers.put(Security.getCurrentTenantName(), defaultDispatcher);
					return defaultDispatcher;
				}
			}
			SystemEntity dispatcher = dao.findByName("Soffid"); //$NON-NLS-1$
			if (dispatcher == null)
				dispatcher = dao.findByName("soffid"); //$NON-NLS-1$
			if (dispatcher == null)
				dispatcher = dao.findByName("seu"); //$NON-NLS-1$
			if (dispatcher == null)
				defaultDispatcher = "soffid"; //$NON-NLS-1$
			else {
				dispatcher.setMainSystem(true);
				getSystemEntityDao().update(dispatcher);
				defaultDispatcher = dispatcher.getName();
			}
			defaultDispatchers.put(Security.getCurrentTenantName(), defaultDispatcher);
		}
		return defaultDispatcher;

	}

	private void addCondition(StringBuffer b, String message, Long value) {
		if (value != null && value.longValue() > 0)
			b.append(String.format(message, value)).append('\n');
	}

	private void addCondition(StringBuffer b, String message, Boolean value) {
		if (value != null && value.booleanValue())
			b.append(String.format(message, value)).append('\n');
	}

	private void addCondition(StringBuffer b, String message, String value) {
		if (value != null && value.length() > 0)
			b.append(String.format(message, value)).append('\n');
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see es.caib.seycon.ng.servei.InternalPasswordServiceBase#
	 * handleGetPolicyDescription(es.caib.seycon.ng.model.PoliticaContrasenyaEntity)
	 */
	@Override
	protected String handleGetPolicyDescription(PasswordPolicyEntity politica) throws Exception {
		StringBuffer b = new StringBuffer();
		addCondition(b, Messages.getString("PasswordServiceImpl.MinLongCondition"), politica.getMinLength()); //$NON-NLS-1$
		addCondition(b, Messages.getString("PasswordServiceImpl.MaxLongCondition"), politica.getMaxLength()); //$NON-NLS-1$
		addCondition(b, Messages.getString("PasswordServiceImpl.MinMinusculesCondition"), politica.getMinLowerCase()); //$NON-NLS-1$
		addCondition(b, Messages.getString("PasswordServiceImpl.MaxMinusculesCondition"), politica.getMaxLowerCase()); //$NON-NLS-1$
		addCondition(b, Messages.getString("PasswordServiceImpl.MinCapitalsCondition"), politica.getMinUpperCase()); //$NON-NLS-1$
		addCondition(b, Messages.getString("PasswordServiceImpl.MaxCapitalCondition"), politica.getMaxUpperCase()); //$NON-NLS-1$
		addCondition(b, Messages.getString("PasswordServiceImpl.MinNumbersCondition"), politica.getMinNumbers()); //$NON-NLS-1$
		addCondition(b, Messages.getString("PasswordServiceImpl.MaxNumbersCondition"), politica.getMaxNumbers()); //$NON-NLS-1$
		addCondition(b, Messages.getString("PasswordServiceImpl.MinSignsCondition"), politica.getMinSymbols()); //$NON-NLS-1$
		addCondition(b, Messages.getString("PasswordServiceImpl.MaxSignsCondition"), politica.getMaxSymbols()); //$NON-NLS-1$
		addCondition(b, Messages.getString("PasswordServiceImpl.PatternCondition"), politica.getRegularExpression()); //$NON-NLS-1$
		addCondition(b, Messages.getString("PasswordServiceImpl.MaxHistoricCondition"), //$NON-NLS-1$
				politica.getRememberedPasswords());
		addCondition(b, Messages.getString("PasswordServiceImpl.ComplexPasswordsCondition"), //$NON-NLS-1$
				politica.getComplexPasswords());
		if (politica.getForbiddenWords() != null && !politica.getForbiddenWords().isEmpty()) {
			b.append(Messages.getString("PasswordServiceImpl.WordNotAllowedCondition")); //$NON-NLS-1$
			for (PolicyForbiddenWordEntity o : politica.getForbiddenWords()) {
				b.append("- ").append(o.getForbiddenWord().getForbiddenWord()).append('\n');
			}
		}
		addCondition(b, Messages.getString("PasswordServiceImpl.PasswordMaxDurationCondition"), //$NON-NLS-1$
				politica.getAvailableTime());
		addCondition(b, Messages.getString("PasswordServiceImpl.RenewalTimeCondition"), //$NON-NLS-1$
				politica.getRenewalTime());
		addCondition(b, Messages.getString("PasswordServiceImpl.MinimumDurationCondition"), //$NON-NLS-1$
				politica.getMinimumPeriod());
		return b.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * es.caib.seycon.ng.servei.InternalPasswordServiceBase#handleCheckPolicy(es.
	 * caib.seycon.ng.model.PoliticaContrasenyaEntity, com.soffid.iam.api.Password)
	 */
	@Override
	protected PolicyCheckResult handleCheckPolicy(PasswordPolicyEntity policy, Password password) throws Exception {
		Collection<AccountEntity> accounts = Collections.emptyList();
		return internalCheckBasicPolicy(policy, password, accounts);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see es.caib.seycon.ng.servei.InternalPasswordServiceBase#
	 * handleUpdateExpiredPasswords(es.caib.seycon.ng.model.UsuariEntity, boolean)
	 */
	@Override
	protected boolean handleUpdateExpiredPasswords(final UserEntity usuari, boolean externalAuth) throws Exception {
		boolean anyChange = false;
		for (final PasswordDomainEntity dce : getPasswordDomainEntityDao().loadAll()) {
			PasswordStatus status = getPasswordsStatus(usuari, dce);
			if (status == null || status.getExpired().booleanValue()) {
				final PasswordPolicyEntity pce = getUserPolicy(usuari, dce);
				if (pce != null) {
					if (pce.getType().equals("A") || externalAuth) {
						final Password p = generateFakePassword(usuari, dce);
						PlatformTransactionManager txMgr = (PlatformTransactionManager) ctx
								.getBean("transactionManager");
						TransactionTemplate txTemplate = new TransactionTemplate(txMgr);
						txTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
						InternalErrorException e = (InternalErrorException) txTemplate
								.execute(new TransactionCallback() {

									public Object doInTransaction(TransactionStatus status) {
										try {
											com.soffid.iam.service.InternalPasswordService svc = (com.soffid.iam.service.InternalPasswordService) ctx
													.getBean(
															com.soffid.iam.service.InternalPasswordService.SERVICE_NAME);
											svc.storePassword(usuari.getUserName(), dce.getName(), p.getPassword(),
													false);
										} catch (InternalErrorException e) {
											return e;
										}
										return null;
									}
								});
						if (e != null)
							throw e;
						executeOB(TaskHandler.UPDATE_USER_PASSWORD, dce.getName(), usuari.getUserName(), p, false);
						anyChange = true;
					}
				}
			}
		}
		return anyChange;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see es.caib.seycon.ng.servei.InternalPasswordServiceBase#
	 * handleEnumExpiredPasswords(es.caib.seycon.ng.model.UsuariEntity)
	 */
	@Override
	protected Collection<PasswordDomainEntity> handleEnumExpiredPasswords(UserEntity usuari) throws Exception {
		Collection<PasswordDomainEntity> list = new LinkedList<PasswordDomainEntity>();
		for (PasswordDomainEntity dce : getPasswordDomainEntityDao().loadAll()) {
			PasswordStatus status = getPasswordsStatus(usuari, dce);
			if (status == null || status.getExpired().booleanValue()) {
				PasswordPolicyEntity pce = getUserPolicy(usuari, dce);
				if (pce != null) {
					if (pce.getType().equals("M")) {
						list.add(dce);
					}
				}
			}
		}
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see es.caib.seycon.ng.servei.InternalPasswordServiceBase#
	 * handleStoreAndSynchronizePassword(es.caib.seycon.ng.model.UsuariEntity,
	 * es.caib.seycon.ng.model.DominiContrasenyaEntity, com.soffid.iam.api.Password,
	 * boolean)
	 */
	@Override
	protected void handleStoreAndSynchronizePassword(UserEntity user, PasswordDomainEntity passwordDomain,
			Password password, boolean mustChange) throws Exception {
		handleStorePassword(user, passwordDomain, password, mustChange);

		executeOB(TaskHandler.UPDATE_USER_PASSWORD, passwordDomain.getName(), user.getUserName(), password, mustChange);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see es.caib.seycon.ng.servei.InternalPasswordServiceBase#
	 * handleStoreAndSynchronizeAccountPassword(es.caib.seycon.ng.model.
	 * AccountEntity, com.soffid.iam.api.Password, boolean, java.util.Date)
	 */
	@Override
	protected void handleStoreAndSynchronizeAccountPassword(AccountEntity account, Password password,
			boolean mustChange, Date expirationDate) throws Exception {
		if (account.getType().equals(AccountType.USER)) {
			UserEntity user = getUsuari(account);
			PasswordDomainEntity passwordDomain = getPasswordDomain(account);
			handleStoreAndSynchronizePassword(user, passwordDomain, password, mustChange);
		} else {
			handleStoreAccountPassword(account, password, mustChange, expirationDate);

			Map<String, Exception> m = executeAccountOB(TaskHandler.UPDATE_ACCOUNT_PASSWORD, 
					account.getName(), account.getSystem().getName(), password,
					mustChange);
			Exception ex = m.get(account.getSystem().getName());
			if (ex != null)
				throw ex;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.context.ApplicationContextAware#setApplicationContext(org
	 * .springframework.context.ApplicationContext)
	 */
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.ctx = applicationContext;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see es.caib.seycon.ng.servei.InternalPasswordServiceBase#
	 * handleStoreAndForwardPasswordById(long, long, com.soffid.iam.api.Password,
	 * boolean)
	 */
	@Override
	protected void handleStoreAndForwardPasswordById(long user, long passwordDomain, Password password,
			boolean mustChange) throws Exception {
		UserEntity usuari = getUserEntityDao().load(user);
		PasswordDomainEntity domini = getPasswordDomainEntityDao().load(passwordDomain);
		storeAndForwardPassword(usuari, domini, password, mustChange);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see es.caib.seycon.ng.servei.InternalPasswordServiceBase#
	 * handleStoreAndForwardAccountPasswordById(long, com.soffid.iam.api.Password,
	 * boolean, java.util.Date)
	 */
	@Override
	protected void handleStoreAndForwardAccountPasswordById(long account, Password password, boolean mustChange,
			Date expirationDate) throws Exception {
		AccountEntity accountEntity = getAccountEntityDao().load(account);
		storeAndForwardAccountPassword(accountEntity, password, mustChange, expirationDate);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see es.caib.seycon.ng.servei.InternalPasswordServiceBase#
	 * handleGetPasswordsStatusById(long, long)
	 */
	@Override
	protected PasswordStatus handleGetPasswordsStatusById(long user, long domini) throws Exception {
		UserEntity usuari = getUserEntityDao().load(user);
		PasswordDomainEntity dominiEntity = getPasswordDomainEntityDao().load(domini);
		return getPasswordsStatus(usuari, dominiEntity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see es.caib.seycon.ng.servei.InternalPasswordServiceBase#
	 * handleGetAccountPasswordsStatusById(long)
	 */
	@Override
	protected PasswordStatus handleGetAccountPasswordsStatusById(long account) throws Exception {
		AccountEntity accountEntity = getAccountEntityDao().load(account);
		return getAccountPasswordsStatus(accountEntity);
	}

	@Override
	protected Calendar handleGetPasswordExpiredDate(long user, long passwordDomain) throws Exception {
		UserEntity ue = getUserEntityDao().load(user);
		PasswordDomainEntity pde = getPasswordDomainEntityDao().load(passwordDomain);
		for (PasswordEntity pe : getPasswordEntityDao().findLastByUserDomain(ue, pde)) {
			if (pe==null || pe.getExpirationDate()==null)
				return null;
			Calendar c = Calendar.getInstance();
			c.setTime(pe.getExpirationDate());
			return c;
		}
		return null;
	}

    private void auditLockAccount(String user, AccountEntity account) throws InternalErrorException {

    	Audit auditoria = new Audit();
        auditoria.setAction("Y"); //$NON-NLS-1$
        auditoria.setUser(user);
        auditoria.setAccount(account.getName());
        auditoria.setDatabase(account.getSystem().getName());
        auditoria.setAuthor(null);
        auditoria.setCalendar(Calendar.getInstance());
        auditoria.setObject("SC_ACCOUN"); //$NON-NLS-1$
        
        ServiceLocator.instance().getAuditService().create(auditoria);
    }
}
