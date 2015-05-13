// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.caib.seycon.ng.servei;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import es.caib.seycon.ng.comu.AccountType;
import es.caib.seycon.ng.comu.EstatContrasenya;
import es.caib.seycon.ng.comu.Password;
import es.caib.seycon.ng.comu.PasswordValidation;
import es.caib.seycon.ng.comu.PolicyCheckResult;
import es.caib.seycon.ng.comu.Tasca;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.model.AccountEntity;
import es.caib.seycon.ng.model.AccountEntityDao;
import es.caib.seycon.ng.model.AccountPasswordEntity;
import es.caib.seycon.ng.model.ContrasenyaEntity;
import es.caib.seycon.ng.model.DispatcherEntity;
import es.caib.seycon.ng.model.DispatcherEntityDao;
import es.caib.seycon.ng.model.DominiContrasenyaEntity;
import es.caib.seycon.ng.model.Parameter;
import es.caib.seycon.ng.model.ParaulaProhibidaPoliticaContrasenyaEntity;
import es.caib.seycon.ng.model.PoliticaContrasenyaEntity;
import es.caib.seycon.ng.model.TasqueEntity;
import es.caib.seycon.ng.model.TipusUsuariEntity;
import es.caib.seycon.ng.model.UserAccountEntity;
import es.caib.seycon.ng.model.UsuariEntity;
import es.caib.seycon.ng.sync.engine.TaskHandler;
import es.caib.seycon.ng.sync.servei.ConsoleLogonService;
import es.caib.seycon.util.Base64;

/**
 * @see es.caib.seycon.ng.servei.InternalPasswordService
 */
public class InternalPasswordServiceImpl extends
        es.caib.seycon.ng.servei.InternalPasswordServiceBase
        implements ApplicationContextAware {

    private ApplicationContext ctx;

	/**
     * @throws InternalErrorException
     * @see es.caib.seycon.ng.servei.InternalPasswordService#checkPolicy(es.caib.seycon.ng.model.UsuariEntity,
     *      es.caib.seycon.ng.model.PoliticaContrasenyaDominiEntity,
     *      es.caib.seycon.ng.comu.Password)
     */
    @SuppressWarnings("rawtypes")
    protected PolicyCheckResult handleCheckPolicy(es.caib.seycon.ng.model.UsuariEntity user,
            es.caib.seycon.ng.model.PoliticaContrasenyaEntity politica,
            es.caib.seycon.ng.comu.Password password) throws InternalErrorException {
    	
        PolicyCheckResult pcr = internalCheckBasicPolicy(politica, password, getUserAccounts (user, politica));
        
        if (pcr != PolicyCheckResult.VALID)
        	return pcr;

        if (user != null && isOldPassword(user, politica.getDominiContrasenya(), password))
            return PolicyCheckResult.OLD_PASSWORD;

        return PolicyCheckResult.VALID;
    }

    /**
     * Gets the list of accounts that belong to a user and a selected password polciy apply.
     * 
     * @param user user 
     * @param politica password policy
     * @return set of accounts
     */
    private Collection<AccountEntity> getUserAccounts(UsuariEntity user,
			PoliticaContrasenyaEntity politica) {
    	LinkedList<AccountEntity> accounts = new LinkedList<AccountEntity>();
    	for (UserAccountEntity uae: user.getAccounts())
    	{
    		AccountEntity acc = uae.getAccount();
    		if (acc.getType().equals(AccountType.USER))
    		{
    			if (acc.getDispatcher().getDomini() == politica.getDominiContrasenya())
    				accounts.add(acc);
    		}
    	}
    	return accounts;
	}

	/**
     * @throws InternalErrorException
     * @see es.caib.seycon.ng.servei.InternalPasswordService#checkPolicy(es.caib.seycon.ng.model.UsuariEntity,
     *      es.caib.seycon.ng.model.PoliticaContrasenyaDominiEntity,
     *      es.caib.seycon.ng.comu.Password)
     */
    @SuppressWarnings("rawtypes")
    protected PolicyCheckResult handleCheckPolicy(es.caib.seycon.ng.model.UsuariEntity user,
            es.caib.seycon.ng.model.DominiContrasenyaEntity passwordDomain,
            es.caib.seycon.ng.comu.Password password) throws InternalErrorException {
    	PoliticaContrasenyaEntity politica = getUserPolicy(user, passwordDomain);
    	
    	return handleCheckPolicy (user, politica, password);
    }

	private PolicyCheckResult internalCheckBasicPolicy(
			es.caib.seycon.ng.model.PoliticaContrasenyaEntity politica,
			es.caib.seycon.ng.comu.Password password,
			Collection<AccountEntity> accounts)
	{
		String uncrypted = password.getPassword();
        if (politica.getMaxLongitud() != null
                && uncrypted.length() > politica.getMaxLongitud().longValue()) {
            return PolicyCheckResult.TOO_LONG;
        }
        if (politica.getMinLongitud() != null
                && uncrypted.length() < politica.getMinLongitud().longValue()) {
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

        if (politica.getMinMinuscules() != null && mins < politica.getMinMinuscules().longValue()) {
            return PolicyCheckResult.TOO_FEW_SMALLS;
        }

        if (politica.getMaxMinuscules() != null && mins > politica.getMaxMinuscules().longValue()) {
            return PolicyCheckResult.TOO_MANY_SMALLS;
        }

        if (politica.getMinMajuscules() != null && majs < politica.getMinMajuscules().longValue()) {
            return PolicyCheckResult.TOO_FEW_CAPS;
        }

        if (politica.getMaxMajuscules() != null && majs > politica.getMaxMajuscules().longValue()) {
            return PolicyCheckResult.TOO_MANY_CAPS;
        }

        if (politica.getMinNumeros() != null && numb < politica.getMinNumeros().longValue()) {
            return PolicyCheckResult.TOO_FEW_NUMBERS;
        }

        if (politica.getMaxNumeros() != null && numb > politica.getMaxNumeros().longValue()) {
            return PolicyCheckResult.TOO_MANY_NUMBERS;
        }

        if (politica.getMinSignesPuntuacio() != null
                && others < politica.getMinSignesPuntuacio().longValue()) {
            return PolicyCheckResult.TOO_FEW_SIGNS;
        }

        if (politica.getMaxSignesPuntuacio() != null
                && others > politica.getMaxSignesPuntuacio().longValue()) {
            return PolicyCheckResult.TOO_MANY_SIGNS;
        }

        if (politica.getExpressioRegular() != null && politica.getExpressioRegular().length() > 0) {
            try {
                if (!Pattern.matches(politica.getExpressioRegular(), uncrypted)) {
                    return PolicyCheckResult.REGEXP_NOT_MATCH;
                }
            } catch (PatternSyntaxException e) {
                return PolicyCheckResult.INVALID_REGEXP;
            }

        }

        if (politica.getParaulaProhibidaContrasenya() != null) {
            for (Iterator it = politica.getParaulaProhibidaContrasenya().iterator(); it.hasNext();) {
                ParaulaProhibidaPoliticaContrasenyaEntity ppce = (ParaulaProhibidaPoliticaContrasenyaEntity) it
                        .next();
                if (uncrypted.contains(ppce.getParaulaProhibida().getParaulaProhibida())) {
                    return new PolicyCheckResult(PolicyCheckResult.FORBIDDEN_WORD.getReasonCode(),
                            ppce.getParaulaProhibida().getParaulaProhibida());
                }
            }
        }
        
        if (politica.getComplexPasswords() != null && politica.getComplexPasswords().booleanValue())
        {
        	return checkComplexRequirements (accounts, password);
        }
        return PolicyCheckResult.VALID;
	}

    private PolicyCheckResult checkComplexRequirements(Collection<AccountEntity> accounts,
			Password password) {
    	// Check account inclussion
    	for (AccountEntity acc: accounts)
    	{
    		if (password.getPassword().toLowerCase().contains(acc.getName().toLowerCase()))
    			return PolicyCheckResult.CONTAINS_ACCOUNTNAME;
    		
    		if (password.getPassword().toLowerCase().contains(acc.getDescription().toLowerCase()))
    			return PolicyCheckResult.CONTAINS_NAME;
    	}
    	
    	// Check for display name inclussion
    	for (AccountEntity acc: accounts)
    	{
    		for (String part: acc.getDescription().split("[,.#-_ &\t]+"))
    		{
    			if (part.length() >= 3 && password.getPassword().toLowerCase().contains(part.toLowerCase()))
    	    			return PolicyCheckResult.CONTAINS_NAME;
    		}
    	}
    	
    	int mays = 0, mins = 0, numbers = 0, signs = 0, others = 0;
    	
    	for (char ch: password.getPassword().toCharArray())
    	{
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
     *      java.lang.String, es.caib.seycon.ng.comu.Password, boolean)
     */
    protected void handleStorePassword(es.caib.seycon.ng.model.UsuariEntity user,
            DominiContrasenyaEntity dce, es.caib.seycon.ng.comu.Password password,
            boolean mustChange) throws java.lang.Exception {
        PoliticaContrasenyaEntity pcd = null;
        pcd = getUserPolicy(user, dce);
        if (pcd == null)
            throw new InternalErrorException(String.format(
                    "Policy not found for password domain %s", dce.getCodi())); //$NON-NLS-1$

        doStorePassword(user, dce, pcd, password, mustChange? "E": "N", mustChange); //$NON-NLS-1$ //$NON-NLS-2$
    }

    /**
     * @see es.caib.seycon.ng.servei.InternalPasswordService#storePassword(es.caib.seycon.ng.model.UsuariEntity,
     *      java.lang.String, es.caib.seycon.ng.comu.Password, boolean)
     */
    protected void handleStoreAndForwardPassword(es.caib.seycon.ng.model.UsuariEntity user,
            DominiContrasenyaEntity dce, es.caib.seycon.ng.comu.Password password,
            boolean mustChange) throws java.lang.Exception {

        handleStorePassword(user, dce, password, mustChange);

        createTask(TaskHandler.UPDATE_USER_PASSWORD, 
                dce.getCodi(), user.getCodi(), password, mustChange);
    }

    private void doStorePassword(UsuariEntity usuari, DominiContrasenyaEntity dce,
            PoliticaContrasenyaEntity pcd, es.caib.seycon.ng.comu.Password password, String estat,
            boolean mustChange) throws InternalErrorException {
        reorderOldPasswords(usuari, dce, pcd);

        ContrasenyaEntity ce = getContrasenyaEntityDao().newContrasenyaEntity();
        ce.setDomini(dce);
        ce.setOrdre(new Long(0));
        ce.setUsuari(usuari);
        Date d = new Date();
        ce.setData(d);
        Calendar c = new GregorianCalendar();
        c.setTime(d);
        if (!mustChange) {
            c.set(Calendar.HOUR_OF_DAY, 0);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);
            c.set(Calendar.MILLISECOND, 0);
            if (pcd.getDuradaMaxima() != null && pcd.getTipus().equals("M")) //$NON-NLS-1$
                c.add(Calendar.DAY_OF_MONTH, pcd.getDuradaMaxima().intValue());
            else if (pcd.getTempsRenovacio() != null && pcd.getTipus().equals("A")) //$NON-NLS-1$
                c.add(Calendar.DAY_OF_MONTH, pcd.getTempsRenovacio().intValue());
            else
                c.add(Calendar.DAY_OF_MONTH, 3650);
        }
        ce.setDataCaducitat(c.getTime());
        ce.setContrasenya(getDigest(password));
        ce.setActiu(estat);
        getContrasenyaEntityDao().create(ce);
        for (UserAccountEntity ua: usuari.getAccounts())
        {
        	AccountEntity acc = ua.getAccount();
        	if (acc.getType().equals(AccountType.USER) && 
        		(acc.getDispatcher().getUrl() == null || acc.getDispatcher().getUrl().isEmpty()))
            {
            	acc.setLastPasswordSet(new Date());
            	acc.setPasswordExpiration(c.getTime());
            	getAccountEntityDao().update(acc);
            }
        }
    }

    /**
     * Generar el digest SHA-1 de la contrase�a
     * 
     * @param password
     *            contrase�a a cifrar
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

    private static MessageDigest digest;

    private void reorderOldPasswords(UsuariEntity user, DominiContrasenyaEntity dce,
            PoliticaContrasenyaEntity pcd) {
    	
    	LinkedList<ContrasenyaEntity> passwords = new LinkedList<ContrasenyaEntity>(getContrasenyaEntityDao().findByUsuariDomini(user, dce));
    	Collections.sort(passwords, new Comparator<ContrasenyaEntity>()
		{

			public int compare (ContrasenyaEntity o1, ContrasenyaEntity o2)
			{
				if (o1.getOrdre().longValue() == o2.getOrdre().longValue())
					return 0;
				if (o1.getOrdre().longValue() > o2.getOrdre().longValue())
					return -1;
				else
					return +1;
			}
		});

    	for (ContrasenyaEntity contrasenya : passwords){

            if (pcd == null || pcd.getMaxHistoric() == null
                    || contrasenya.getOrdre() + 1 >= pcd.getMaxHistoric().longValue()) {
                getContrasenyaEntityDao().remove(contrasenya);

            } else {
                contrasenya.setOrdre(contrasenya.getOrdre() + 1);
                getContrasenyaEntityDao().update(contrasenya);
            }
        }
    }


    /**
     * @see es.caib.seycon.ng.servei.InternalPasswordService#confirmPassword(es.caib.seycon.ng.model.UsuariEntity,
     *      java.lang.String, es.caib.seycon.ng.comu.Password)
     */
    protected void handleConfirmPassword(es.caib.seycon.ng.model.UsuariEntity user,
            DominiContrasenyaEntity dce, es.caib.seycon.ng.comu.Password password)
            throws java.lang.Exception {

        PoliticaContrasenyaEntity pcd = null;
        pcd = getUserPolicy(user, dce);
        if (pcd == null)
            throw new InternalErrorException(String.format(
                    "Policy not found for password domain %s", dce.getCodi())); //$NON-NLS-1$

        String digest = getDigest(password);
        ContrasenyaEntity contra = getContrasenyaEntityDao().findLastByUsuariDomini(user, dce);
        if (contra != null && contra.getContrasenya().equals(digest)
                && contra.getActiu().equals("N")) { //$NON-NLS-1$
            contra.setActiu("S"); //$NON-NLS-1$
            getContrasenyaEntityDao().update(contra);
        }
    }

    private PoliticaContrasenyaEntity getUserPolicy(es.caib.seycon.ng.model.UsuariEntity user,
            DominiContrasenyaEntity dce) {
    	return getPoliticaContrasenyaEntityDao().
    					findByDominiContrasenyaTipusUsuari(dce.getCodi(), user.getTipusUsuari().getCodi());
    }

    /**
     * @see es.caib.seycon.ng.servei.InternalPasswordService#disableUntrustedPasswords(es.caib.seycon.ng.model.UsuariEntity)
     */
    @SuppressWarnings("unchecked")
    protected void handleDisableUntrustedPasswords() throws java.lang.Exception {

        for (Iterator<DominiContrasenyaEntity> dcIterator = getDominiContrasenyaEntityDao()
                .loadAll().iterator(); dcIterator.hasNext();) {
            DominiContrasenyaEntity dc = dcIterator.next();
            for (Iterator<PoliticaContrasenyaEntity> tuIterator = dc.getPoliticaContrasenyes()
                    .iterator(); tuIterator.hasNext();) {
                PoliticaContrasenyaEntity pc = tuIterator.next();
                if (pc.getTipus().equals("M")) { //$NON-NLS-1$
                    disableUntrustedManualPasswords(dc, pc);
                } else {
                    renewAutomaticPasswords(dc, pc);
                }
            }
        }
    }

    private void renewAutomaticPasswords(DominiContrasenyaEntity dc, PoliticaContrasenyaEntity pc)
            throws InternalErrorException {
        Calendar c = new GregorianCalendar();
//        c.set(Calendar.HOUR_OF_DAY, 0);
//        c.set(Calendar.MINUTE, 0);
//        c.set(Calendar.SECOND, 0);
//        c.set(Calendar.MILLISECOND, 0);
//        c.add(Calendar.DAY_OF_MONTH, -1);

        Collection expired = getContrasenyaEntityDao().query(
                "from es.caib.seycon.ng.model.ContrasenyaEntity as contrasenyaEntity " //$NON-NLS-1$
                        + "where contrasenya.domini = :domini and " //$NON-NLS-1$
                        + "contrasenya.usuari.tipusUsuari = :tipusUsuari and " //$NON-NLS-1$
                        + "contrasenya.usuari.actiu='S' and " //$NON-NLS-1$
                        + "contrasenya.dataCaducitat <= :caducitat and " //$NON-NLS-1$
                        + "contrasenya.actiu in ('S', 'N') ", //$NON-NLS-1$
                new Parameter[] { new Parameter("domini", dc), //$NON-NLS-1$
                        new Parameter("tipusUsuari", pc.getTipusUsuariDomini()), //$NON-NLS-1$
                        new Parameter("caducitat", c.getTime()) }); //$NON-NLS-1$
        for (Iterator<ContrasenyaEntity> it = expired.iterator(); it.hasNext();) {
            ContrasenyaEntity contra = it.next();
            Password password = generateRandomPassword(contra.getUsuari(), dc, pc, false, true);
            doStorePassword(contra.getUsuari(), dc, pc, password, "N", false); //$NON-NLS-1$
            createTask(TaskHandler.UPDATE_USER_PASSWORD, 
                    dc.getCodi(), contra.getUsuari().getCodi(), password, false);
        }

        expired = getAccountPasswordEntityDao().query(
                        "select pass from es.caib.seycon.ng.model.AccountPasswordEntity as pass " //$NON-NLS-1$
        						+ "join pass.account as account " //$NON-NLS-1$
                                + "where account.dispatcher.domini = :domini and " //$NON-NLS-1$
                                + "account.passwordPolicy = :tipusUsuari and " //$NON-NLS-1$
                                + "account.disabled = false and " //$NON-NLS-1$
                                + "pass.expirationDate <= :caducitat and " //$NON-NLS-1$
                                + "pass.active in ('S', 'N') and pass.order = 0", //$NON-NLS-1$
         new Parameter[] { new Parameter("domini", dc), //$NON-NLS-1$
                                new Parameter("tipusUsuari", pc.getTipusUsuariDomini()), //$NON-NLS-1$
                                new Parameter("caducitat", c.getTime()) }); //$NON-NLS-1$
         for (Iterator<AccountPasswordEntity> it = expired.iterator(); it.hasNext();) {
        	 AccountPasswordEntity contra = it.next();
                    Password password = generateRandomPassword(contra.getAccount(), pc, false, true);
                    doStoreAccountPassword(contra.getAccount(), pc, password, "N", false, null); //$NON-NLS-1$
                    createAccountTask(TaskHandler.UPDATE_ACCOUNT_PASSWORD, 
                            contra.getAccount().getName(), contra.getAccount().getDispatcher().getCodi(), password, false, null);
         }
    }

    private void disableUntrustedManualPasswords(DominiContrasenyaEntity dc,
            PoliticaContrasenyaEntity pc) throws InternalErrorException {
        Calendar c = new GregorianCalendar();
//        c.set(Calendar.HOUR_OF_DAY, 0);
//        c.set(Calendar.MINUTE, 0);
//        c.set(Calendar.SECOND, 0);
//        c.set(Calendar.MILLISECOND, 0);

        
        Collection expired = getContrasenyaEntityDao().query(
                "from es.caib.seycon.ng.model.ContrasenyaEntity as contrasenyaEntity " //$NON-NLS-1$
                        + "where contrasenya.domini = :domini and " //$NON-NLS-1$
                        + "contrasenya.usuari.tipusUsuari = :tipusUsuari and " //$NON-NLS-1$
                        + "contrasenya.dataCaducitat <= :caducitat and " //$NON-NLS-1$
                        + "contrasenya.actiu in ('S', 'N') and contrasenya.ordre=0", //$NON-NLS-1$
                new Parameter[] { new Parameter("domini", dc), //$NON-NLS-1$
                        new Parameter("tipusUsuari", pc.getTipusUsuariDomini()), //$NON-NLS-1$
                        new Parameter("caducitat", c.getTime()) }); //$NON-NLS-1$
        for (Iterator<ContrasenyaEntity> it = expired.iterator(); it.hasNext();) {
            ContrasenyaEntity contra = it.next();
            Password password = generateRandomPassword(contra.getUsuari(), dc, pc, false, true);
            contra.setActiu("E"); //$NON-NLS-1$
            getContrasenyaEntityDao().update(contra);
            createTask(TaskHandler.EXPIRE_USER_UNTRUSTED_PASSWORD,
                    dc.getCodi(), contra.getUsuari().getCodi(), password, false);
        }

        expired = getAccountPasswordEntityDao().query(
                        "select pass from es.caib.seycon.ng.model.AccountPasswordEntity as pass " //$NON-NLS-1$
        						+ "join pass.account as account " //$NON-NLS-1$
                                + "where account.dispatcher.domini = :domini and " //$NON-NLS-1$
                                + "account.passwordPolicy = :tipusUsuari and " //$NON-NLS-1$
                                + "account.disabled = false and " //$NON-NLS-1$
                                + "pass.expirationDate <= :caducitat and " //$NON-NLS-1$
                                + "pass.active in ('S', 'N') and pass.order = 0", //$NON-NLS-1$
         new Parameter[] { new Parameter("domini", dc), //$NON-NLS-1$
                                new Parameter("tipusUsuari", pc.getTipusUsuariDomini()), //$NON-NLS-1$
                                new Parameter("caducitat", c.getTime()) }); //$NON-NLS-1$
         for (Iterator<AccountPasswordEntity> it = expired.iterator(); it.hasNext();) {
        	 AccountPasswordEntity contra = it.next();
        	 AccountEntity acc = contra.getAccount();
        	 // Reset password when
        	 // a - Password is High privileged
        	 // b - Domain is not trusted
        	 //
        	 // For normal passwords on trusted systems, do nothing. Mark for expiration when needed.
        	 //
        	 if (acc.getType().equals (AccountType.PRIVILEGED) ||
        					 acc.getDispatcher().getSegur().equals("N")) //$NON-NLS-1$
        	 {
                    Password password = generateRandomPassword(contra.getAccount(), pc, false, true);
                    doStoreAccountPassword(contra.getAccount(), pc, password, "N", false, null); //$NON-NLS-1$
                    createAccountTask(TaskHandler.UPDATE_ACCOUNT_PASSWORD, 
                            contra.getAccount().getName(), contra.getAccount().getDispatcher().getCodi(), password, false, null);
        	 }
        	 else
        	 {
        		 contra.setActive("E"); //$NON-NLS-1$
        		 getAccountPasswordEntityDao().update(contra);
        	 }
         }
    }

    private TaskHandler createTask(String transa, String dominiContrasenyes,
            String user, Password password, boolean mustChange) throws InternalErrorException {
        TasqueEntity tasque = getTasqueEntityDao().newTasqueEntity();
        tasque.setData(new Timestamp(System.currentTimeMillis()));
        tasque.setTransa(transa);
        tasque.setDominiContrasenyes(dominiContrasenyes);
        tasque.setUsuari(user);
        tasque.setContra(password.toString());
        tasque.setCancon(mustChange ? "S" : "N"); //$NON-NLS-1$ //$NON-NLS-2$
        tasque.setStatus("P"); //$NON-NLS-1$
        try {
       		return getTaskQueue().addTask(tasque);
        } 
        catch (NoSuchBeanDefinitionException e) 
        {
            getTasqueEntityDao().createNoFlush(tasque);
            return null;
        }
    }


    @SuppressWarnings ("unchecked")
	private Map<String, Exception> executeOB(String transa, String dominiContrasenyes,
    	            String user, Password password, boolean mustChange) throws InternalErrorException {
        Tasca tasca = new Tasca();
        tasca.setTransa(transa);
        tasca.setDominiContrasenyes(dominiContrasenyes);
        tasca.setUsuari(user);
        tasca.setContra(password.toString());
        tasca.setCancon(mustChange ? "S" : "N"); //$NON-NLS-1$ //$NON-NLS-2$
        tasca.setStatus("P"); //$NON-NLS-1$
        try {
    		TaskHandler th = new TaskHandler();
    		th.setTask(tasca);
    		return getTaskQueue().processOBTask(th);
        } 
        catch (NoSuchBeanDefinitionException e) 
        {
            createTask(transa, dominiContrasenyes, user, password, mustChange);
            return null;
        }
    }
    


    private Password generateRandomPassword(UsuariEntity usuariEntity,
            DominiContrasenyaEntity dc, PoliticaContrasenyaEntity pc, boolean minLength,
            boolean maxLength) throws InternalErrorException {
        Random r = new Random(System.currentTimeMillis() + hashCode());
        Password password;
        int retries = 0;
        do {
            retries++;
            if (retries > 100)
                throw new InternalErrorException(String.format(
                        "Cannot generate valid password for domain %s, user type %s", dc.getCodi(), //$NON-NLS-1$
                        pc.getTipusUsuariDomini().getCodi()));
            password = generatePasswordCandidate(pc, minLength, maxLength, r);

        } while (!handleCheckPolicy(usuariEntity, pc, password).isValid());
        return password;
    }

	private Password generatePasswordCandidate(PoliticaContrasenyaEntity pc,
			boolean minLength, boolean maxLength, Random r)
	{
		Password password;
		StringBuffer sb;
		sb = new StringBuffer();
		int length;
		if (pc.getMinLongitud() == null)
		    if (pc.getMaxLongitud() == null)
		        length = 6;
		    else if (minLength && pc.getMaxLongitud() > 6)
		        length = 6;
		    else
		        length = pc.getMaxLongitud().intValue();
		else if (pc.getMaxLongitud() == null || pc.getMaxLongitud().equals(pc.getMinLongitud()))
		    length = pc.getMinLongitud().intValue();
		else if (minLength)
		    length = pc.getMinLongitud().intValue();
		else if (maxLength)
		    length = pc.getMaxLongitud().intValue();
		else
		    length = pc.getMinLongitud().intValue()
		            + r.nextInt(pc.getMaxLongitud().intValue() - pc.getMinLongitud().intValue());

		int minMays = pc.getMinMajuscules() == null ? 0: pc.getMinMajuscules().intValue();
		int minMins = pc.getMinMinuscules() == null ? 0: pc.getMinMinuscules().intValue();
		int minNums = pc.getMinNumeros() == null ? 0: pc.getMinNumeros().intValue();
		int minSims = pc.getMinSignesPuntuacio() == null ? 0: pc.getMinSignesPuntuacio().intValue();
		int maxMays, maxMins, maxNums, maxSims;
		maxMins = pc.getMaxMinuscules() == null ? length - minMays - minNums - minSims: 
			pc.getMaxMinuscules().intValue();
		if (maxMins > length)
			maxMins = length;
		if (minLength && maxMins >= length - minMays - minNums - minSims) {
			maxSims = minSims;
			maxMays = minMays;
			maxNums = minNums;
			// _abcdefghijklmnopqrstuvwxyz
			//  12345678901234567890123456
			//           1         2     2
		    for (int i = 0; i < maxMins; i++) {
		        int ch = 'a' + r.nextInt(26);
		        sb.append((char) ch);
		    }
		    maxMins = 0;
		    
		} else {
		    maxMays = length - minNums - minMins - minSims;
		    if (pc.getMaxMajuscules() != null && pc.getMaxMajuscules().intValue() < maxMays)
		    	maxMays = pc.getMaxMajuscules().intValue();
		    maxNums = length - minMays - minMins - minSims;
		    if (pc.getMaxNumeros() != null && pc.getMaxNumeros().intValue() < maxNums)
		    	maxNums = pc.getMaxNumeros().intValue();
		    maxSims = length - minMays - minMins - minNums;
		    if (pc.getMaxSignesPuntuacio() != null && pc.getMaxSignesPuntuacio().intValue() < maxSims)
		    	maxSims = pc.getMaxSignesPuntuacio().intValue();
		}
		while (sb.length() < length) {
			int remaining = length-sb.length();
		    if (minMins > 0 && minMins + minMays + minNums + minSims == remaining) {
		    	sb.append ((char) ('a' + r.nextInt(26)));
		    	minMins --;
		    } else if (minMays > 0 && minMays + minNums + minSims == remaining) {
		    	sb.append ((char) ('A' + r.nextInt(26)));
		    	minMays --;
		    } else if (minNums > 0 && minNums + minSims == remaining) {
		    	sb.append ((char) ('0' + r.nextInt(10)));
		    	minNums --;
		    } else if (minSims > 0 && minSims == remaining) {
		    	sb.append ((char) ('!' + r.nextInt(14)));
		    	minSims --;
		    } else {
		        int ch = r.nextInt(26 + 26 + 10 + 15);
		        if (ch < 26 && maxMins > 0) {
		        	sb.append((char) ('a'+ch));
		        	maxMins --;
		        	if (minMins > 0) minMins --;
		        }
		        if (ch >= 26 && ch < 26 + 26 && maxMays > 0) {
		        	sb.append((char) ('A'+ch-26));
		        	maxMays --;
		        	if (minMays > 0) minMays --;
		        }
		        if (ch >= 26 + 26 && ch < 26 + 26 + 10 && maxNums > 0) {
		        	sb.append((char) ('0'+ch-26-26));
		        	maxNums --;
		        	if (minNums > 0) minNums --;
		        }
		        if (ch >= 26 + 26 + 10 && maxSims > 0) {
		        	sb.append((char) ('!'+ch-26-26-10));
		        	maxSims --;
		        	if (minSims > 0) minSims --;
		        }
		    }
		}
		
		// Now shuffle
		char b []= sb.toString().toCharArray();
		for (int i = 0 ; i < b.length; i++ )
		{
			int j  = r.nextInt(b.length);
			if (i != j)
			{
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
     *      es.caib.seycon.ng.comu.Password)
     */
    protected boolean handleIsLastPassword(es.caib.seycon.ng.model.UsuariEntity user,
            es.caib.seycon.ng.comu.Password password) throws java.lang.Exception {
        return false;
    }

    /**
     * @see es.caib.seycon.ng.servei.InternalPasswordService#checkPassword(es.caib.seycon.ng.model.UsuariEntity,
     *      es.caib.seycon.ng.model.DominiContrasenyaEntity,
     *      es.caib.seycon.ng.comu.Password, boolean, boolean)
     */
    protected PasswordValidation handleCheckPassword(es.caib.seycon.ng.model.UsuariEntity user,
            es.caib.seycon.ng.model.DominiContrasenyaEntity passwordDomain,
            es.caib.seycon.ng.comu.Password password, boolean checkTrusted, boolean checkExpired)
            throws java.lang.Exception {
        String digest = getDigest(password);

        if (user.getActiu().equals("N")) //$NON-NLS-1$
        	return PasswordValidation.PASSWORD_WRONG;
        ContrasenyaEntity contra = getContrasenyaEntityDao().findLastByUsuariDomini(user,
                passwordDomain);
        if (contra != null
                && (contra.getActiu().equals("S") || contra.getActiu().equals("N") || contra //$NON-NLS-1$ //$NON-NLS-2$
                        .getActiu().equals("E"))) { //$NON-NLS-1$
            if (digest.equals(contra.getContrasenya())) {
                if (new Date().before(contra.getDataCaducitat())) {
                    return PasswordValidation.PASSWORD_GOOD;
                } else if (checkExpired) {
                    return PasswordValidation.PASSWORD_GOOD_EXPIRED;
                } else {
                    return PasswordValidation.PASSWORD_WRONG;
                }
            }
        }

        boolean taskQueue = false;
    	try {
    		if (checkTrusted && getTaskQueue() != null)
    		{
    			taskQueue = true;
	            final long timeToWait = 60000; // 1 minute
	            TaskHandler th = createTask(TaskHandler.VALIDATE_PASSWORD, passwordDomain.getCodi(), user.getCodi(),
	                    password, false);
	
	            th.setTimeout(new Date(System.currentTimeMillis() + timeToWait));
	            synchronized (th) {
	                if (th.getTask().getStatus().equals("P")) { //$NON-NLS-1$
	                    th.wait(timeToWait);
	                }
	            }
	            return th.isValidated() ? PasswordValidation.PASSWORD_GOOD
                    : PasswordValidation.PASSWORD_WRONG;
        	}
    	}
        catch (NoSuchBeanDefinitionException e) 
        {
        
        }
		if (checkTrusted && ! taskQueue && "true".equals(System.getProperty("soffid.auth.trustedLogin")))
		{
			for (UserAccountEntity userAccount: user.getAccounts())
			{
				AccountEntity ae = userAccount.getAccount();
				if (!ae.isDisabled() && ae.getDispatcher().getDomini() == passwordDomain)
				{
					PasswordValidation status = validatePasswordOnServer(ae, password);
					if (status.equals (PasswordValidation.PASSWORD_GOOD))
						return status;
				}
			}
		}

        return PasswordValidation.PASSWORD_WRONG;
    }

    /**
     * @see es.caib.seycon.ng.servei.InternalPasswordService#checkPin(es.caib.seycon.ng.model.UsuariEntity,
     *      java.lang.String)
     */
    protected boolean handleCheckPin(es.caib.seycon.ng.model.UsuariEntity user, java.lang.String pin)
            throws java.lang.Exception {
        // @todo implement protected boolean
        // handleCheckPin(es.caib.seycon.ng.model.UsuariEntity user,
        // java.lang.String pin)
        return false;
    }

    /**
     * @see es.caib.seycon.ng.servei.InternalPasswordService#disableExpiredPasswords()
     */
    @SuppressWarnings("unchecked")
    protected void handleDisableExpiredPasswords() throws java.lang.Exception {
        for (Iterator<DominiContrasenyaEntity> dcIterator = getDominiContrasenyaEntityDao()
                .loadAll().iterator(); dcIterator.hasNext();) {
            DominiContrasenyaEntity dc = dcIterator.next();
            for (Iterator<PoliticaContrasenyaEntity> tuIterator = dc.getPoliticaContrasenyes()
                    .iterator(); tuIterator.hasNext();) 
            {
                PoliticaContrasenyaEntity pc = tuIterator.next();
                if (pc.getDuradaMaxima() != null && pc.getDuradaMaximaCaducada() != null)
                {
                    Calendar c = new GregorianCalendar();
                    c.set(Calendar.HOUR_OF_DAY, 0);
                    c.set(Calendar.MINUTE, 0);
                    c.set(Calendar.SECOND, 0);
                    c.set(Calendar.MILLISECOND, 0);
                    
                    c.add(Calendar.DAY_OF_YEAR, - pc.getDuradaMaximaCaducada().intValue());
    
                    Collection<ContrasenyaEntity> expired = getContrasenyaEntityDao().query(
                            "from es.caib.seycon.ng.model.ContrasenyaEntity as contrasenyaEntity " //$NON-NLS-1$
                                    + "where contrasenya.domini = :domini and " //$NON-NLS-1$
                                    + "contrasenya.usuari.tipusUsuari.codi = :tipusUsuari and " //$NON-NLS-1$
                                    + "contrasenya.dataCaducitat <= :caducitat and " //$NON-NLS-1$
                                    + "contrasenya.actiu = 'E' and contrasenya.ordre = 0", //$NON-NLS-1$
                            new Parameter[] { new Parameter("domini", dc), //$NON-NLS-1$
                                    new Parameter("tipusUsuari", pc.getTipusUsuariDomini().getCodi()), //$NON-NLS-1$
                                    new Parameter("caducitat", c.getTime()) }); //$NON-NLS-1$
                    for (Iterator<ContrasenyaEntity> it = expired.iterator(); it.hasNext();) {
                        ContrasenyaEntity contra = it.next();
                        Password password = generateRandomPassword(contra.getUsuari(), dc, pc, false,
                                true);
                        contra.setActiu("D"); //$NON-NLS-1$
                        getContrasenyaEntityDao().update(contra);
                        createTask(TaskHandler.EXPIRE_USER_PASSWORD,
                                dc.getCodi(), contra.getUsuari().getCodi(), password, false);
                    }
    
                    List<AccountPasswordEntity> expiredAccount = getAccountPasswordEntityDao().query(
                        "select pass from es.caib.seycon.ng.model.AccountPasswordEntity as pass " //$NON-NLS-1$
        						+ "join pass.account as account " //$NON-NLS-1$
                                + "where account.dispatcher.domini = :domini and " //$NON-NLS-1$
                                + "account.passwordPolicy = :tipusUsuari and " //$NON-NLS-1$
                                + "account.disabled = false and " //$NON-NLS-1$
                                + "pass.expirationDate <= :caducitat and " //$NON-NLS-1$
                                + "pass.active = 'E' and pass.order = 0", //$NON-NLS-1$
                     new Parameter[] { new Parameter("domini", dc), //$NON-NLS-1$
                                            new Parameter("tipusUsuari", pc.getTipusUsuariDomini()), //$NON-NLS-1$
                                            new Parameter("caducitat", c.getTime()) }); //$NON-NLS-1$
                     for (Iterator<AccountPasswordEntity> it = expiredAccount.iterator(); it.hasNext();) {
                    	 AccountPasswordEntity contra = it.next();
                    	 AccountEntity acc = contra.getAccount();
                    	 // Reset password when
                    	 // a - Password is High privileged
                    	 // b - Domain is not trusted
                    	 //
                    	 // For normal passwords on trusted systems, do nothing. Mark for expiration when needed.
                    	 //
                        Password password = generateRandomPassword(contra.getAccount(), pc, false, true);
                        doStoreAccountPassword(contra.getAccount(), pc, password, "N", false, null); //$NON-NLS-1$
                        createAccountTask(TaskHandler.UPDATE_ACCOUNT_PASSWORD, 
                                contra.getAccount().getName(), contra.getAccount().getDispatcher().getCodi(), password, false, null);
                    }
                }
            }
        }
    }

    /**
     * @see es.caib.seycon.ng.servei.InternalPasswordService#isOldPassword(es.caib.seycon.ng.model.UsuariEntity,
     *      java.lang.String, es.caib.seycon.ng.comu.Password)
     */
    protected boolean handleIsOldPassword(UsuariEntity user,
            DominiContrasenyaEntity passwordDomain, es.caib.seycon.ng.comu.Password password)
            throws java.lang.Exception {
        String digest = getDigest(password);
        for (Iterator it = getContrasenyaEntityDao().findByUsuariDomini(user, passwordDomain)
                .iterator(); it.hasNext();) {
            ContrasenyaEntity contrasenya = (ContrasenyaEntity) it.next();
            if (contrasenya.getContrasenya().equals(digest))
                return true;
        }
        return false;
    }

    /**
     * Generates a new password
     */

    protected Password randomPassword(UsuariEntity user,
            es.caib.seycon.ng.model.DominiContrasenyaEntity passDomain, boolean mustChange, boolean fake)
            throws Exception {
        Password password = null;
        boolean found = false;

        PoliticaContrasenyaEntity pcd = getUserPolicy(user, passDomain);
        if (pcd != null) {
            password = generateRandomPassword(user, passDomain, pcd, true, false);
            if (!fake) {
                doStorePassword(user, passDomain, pcd, password, mustChange ? "E": "N", mustChange); //$NON-NLS-1$ //$NON-NLS-2$
                createTask(TaskHandler.UPDATE_USER_PASSWORD,
                        passDomain.getCodi(),
                        user.getCodi(), password, mustChange);
            }
            return password;
        } else
            return null;
    }

    /**
     * @see es.caib.seycon.ng.servei.InternalPasswordService#genrateNewPassword(es.caib.seycon.ng.model.UsuariEntity)
     */
    @SuppressWarnings("unchecked")
    @Override
    protected Password handleGenerateNewPassword(UsuariEntity user,
            es.caib.seycon.ng.model.DominiContrasenyaEntity passDomain, boolean mustChange) throws Exception {
        return randomPassword(user, passDomain, mustChange, false);

    }

    @Override
    protected EstatContrasenya handleGetPasswordsStatus(UsuariEntity user,
            DominiContrasenyaEntity domini) throws Exception {
		ContrasenyaEntity last = getContrasenyaEntityDao().findLastByUsuariDomini(user, domini);
		if (last == null)
			return null;
		else
			return getContrasenyaEntityDao().toEstatContrasenya(last);
    }

    @Override
    protected Collection<EstatContrasenya> handleGetExpiredPasswords(Date desde, Date finsa,
            TipusUsuariEntity tipusUsuari) throws Exception {
        return null;
    }

    @Override
    protected Password handleGenerateFakePassword(UsuariEntity user,
            DominiContrasenyaEntity passDomain) throws Exception {
        if (user == null) {
            Password pass = null;
            int len = 0;
            for (PoliticaContrasenyaEntity politica : getPoliticaContrasenyaEntityDao()
                    .findByDominiContrasenya(passDomain.getCodi())) {
                Password newPass = generateRandomPassword(null, passDomain, politica, false, true);
                if (len < newPass.getPassword().length()) {
                    len = newPass.getPassword().length();
                    pass = newPass;
                }
            }
            return pass;
        } else
            return randomPassword(user, passDomain, true, true);
    }

    @Override
    protected boolean handleIsPasswordExpired(UsuariEntity user,
            DominiContrasenyaEntity passwordDomain) throws Exception {

        ContrasenyaEntity contra = getContrasenyaEntityDao().findLastByUsuariDomini(user,
                passwordDomain);

        if (contra.getDataCaducitat().before(new Date())) {
            return true;
        }

        return false;
    }

    @Override
    protected void handleStorePassword(String user, String passwordDomain, String password,
            boolean mustChange) throws Exception {
        UsuariEntity usuari = getUsuariEntityDao().findByCodi(user);
        DominiContrasenyaEntity dc = getDominiContrasenyaEntityDao().findByCodi(passwordDomain);
        storePassword(usuari, dc, new Password(password), mustChange);
    }


	@Override
	protected boolean handleExistsPassword(UsuariEntity user,
			DominiContrasenyaEntity passwordDomain) throws Exception
	{
		return getContrasenyaEntityDao().findByUsuariDomini(user, passwordDomain).size() == 0;
	}

	private PoliticaContrasenyaEntity getAccountPolicy (AccountEntity account)
	{
		DominiContrasenyaEntity domini = account.getDispatcher().getDomini();
		TipusUsuariEntity tipusUsuari = account.getPasswordPolicy();
		return getPoliticaContrasenyaEntityDao().
			findByDominiContrasenyaTipusUsuari(domini.getCodi(), tipusUsuari.getCodi());
		
	}
	@Override
	protected PolicyCheckResult handleCheckAccountPolicy(AccountEntity account,
			Password password) throws InternalErrorException
	{
		if (account.getType().equals(AccountType.USER))
		{
			UsuariEntity user = getUsuari (account);
			DominiContrasenyaEntity passwordDomain = getPasswordDomain(account);
			
			return handleCheckPolicy(user, getUserPolicy(user, passwordDomain), password);
		} else {
			PoliticaContrasenyaEntity politica = getAccountPolicy(account);
			
			if (politica == null)
			{
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
	
    private void reorderOldAccountPasswords(AccountEntity account,
            PoliticaContrasenyaEntity pcd) {

    	LinkedList<AccountPasswordEntity> passwords = new LinkedList<AccountPasswordEntity>(account.getPasswords());
    	Collections.sort(passwords, new Comparator<AccountPasswordEntity>()
		{

			public int compare (AccountPasswordEntity o1, AccountPasswordEntity o2)
			{
				if (o1.getOrder().longValue() == o2.getOrder().longValue())
					return 0;
				if (o1.getOrder().longValue() > o2.getOrder().longValue())
					return -1;
				else
					return +1;
			}
		});
    	
    	for (AccountPasswordEntity ap: passwords)
    	{
            if (pcd == null || pcd.getMaxHistoric() == null
                    || ap.getOrder() + 1 >= pcd.getMaxHistoric().longValue()) {
                getAccountPasswordEntityDao().remove(ap);

            } else {
                ap.setOrder(ap.getOrder() + 1);
                getAccountPasswordEntityDao().update(ap);
            }
        }
    }


	
    private void doStoreAccountPassword(AccountEntity account,
    		PoliticaContrasenyaEntity pcd,
            es.caib.seycon.ng.comu.Password password, String estat,
            boolean mustChange, Date untilDate) throws InternalErrorException {
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
            if (pcd.getDuradaMaxima() != null && pcd.getTipus().equals("M")) //$NON-NLS-1$
                c.add(Calendar.DAY_OF_MONTH, pcd.getDuradaMaxima().intValue());
            else if (pcd.getTempsRenovacio() != null && pcd.getTipus().equals("A")) //$NON-NLS-1$
                c.add(Calendar.DAY_OF_MONTH, pcd.getTempsRenovacio().intValue());
            else
                c.add(Calendar.DAY_OF_MONTH, 3650);
        }
        if (untilDate != null && c.getTime().after(untilDate))
        	c.setTime(untilDate);
        ce.setExpirationDate(c.getTime());
        ce.setPassword(getDigest(password));
        ce.setActive(estat);
        getAccountPasswordEntityDao().create(ce);

        if (account.getDispatcher().getUrl() == null || account.getDispatcher().getUrl().isEmpty())
        {
        	account.setLastPasswordSet(new Date());
        	account.setPasswordExpiration(c.getTime());
        	getAccountEntityDao().update(account);
        }
    }



	@Override
	protected void handleStoreAccountPassword(String account, String dispatcher,
			String password, boolean mustChange, Date untilDate) throws Exception
	{
		AccountEntityDao accDao = getAccountEntityDao ();
		AccountEntity acc = accDao.findByNameAndDispatcher(account, dispatcher);
		if (acc.getType().equals(AccountType.USER))
		{
			UsuariEntity user = getUsuari (acc);
			DominiContrasenyaEntity passwordDomain = getPasswordDomain(acc);
			handleStorePassword(user, passwordDomain, new Password(password), mustChange);
		} else {
			if (acc == null)
	            throw new IllegalArgumentException(String.format(
	                    Messages.getString("InternalPasswordServiceImpl.PolicyNotFound"), account));  //$NON-NLS-1$
	
	        PoliticaContrasenyaEntity pcd = null;
	        pcd = getAccountPolicy(acc);
	        if (pcd == null)
	            throw new InternalErrorException(String.format(
	                    Messages.getString("InternalPasswordServiceImpl.PolicyNotFound"), account));  //$NON-NLS-1$
	
	        doStoreAccountPassword(acc, pcd, new Password(password), mustChange?"E": "N", mustChange, untilDate); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	@Override
	protected void handleStoreAccountPassword(AccountEntity account,
			Password password, boolean mustChange, Date untilDate) throws Exception
	{
		if (account.getType().equals(AccountType.USER))
		{
			UsuariEntity user = getUsuari (account);
			DominiContrasenyaEntity passwordDomain = getPasswordDomain(account);
			handleStorePassword(user, passwordDomain, password, mustChange);
		} else {
	        PoliticaContrasenyaEntity pcd = null;
	        pcd = getAccountPolicy(account);
	        if (pcd == null)
	            throw new InternalErrorException(String.format(
	                    Messages.getString("InternalPasswordServiceImpl.PolicyNotFound"), account.getName()));  //$NON-NLS-1$
	
	        doStoreAccountPassword(account, pcd, password, mustChange?"E": "N", mustChange, untilDate); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	@Override
	protected PasswordValidation handleCheckAccountPassword(AccountEntity account,
			Password password, boolean checkTrusted, boolean checkExpired)
			throws Exception
	{

		if (account.isDisabled())
			return PasswordValidation.PASSWORD_WRONG;
		
		if (account.getType().equals(AccountType.USER))
		{
			UsuariEntity user = getUsuari (account);
			DominiContrasenyaEntity passwordDomain = getPasswordDomain(account);
			return handleCheckPassword(user, passwordDomain, password, checkTrusted, checkExpired);
		}
		else
		{
	        String digest = getDigest(password);
	
	        AccountPasswordEntity contra = getAccountPasswordEntityDao().findLastByAccount(account.getId());
	        if (contra != null
	                && (contra.getActive().equals("S") ||  //$NON-NLS-1$
	                		contra.getActive().equals("N") || contra //$NON-NLS-1$ //$NON-NLS-2$
	                        .getActive().equals("E"))) { //$NON-NLS-1$
	            if (digest.equals(contra.getPassword())) {
	                if (new Date().before(contra.getExpirationDate())) {
	                    return PasswordValidation.PASSWORD_GOOD;
	                } else if (checkExpired) {
	                    return PasswordValidation.PASSWORD_GOOD_EXPIRED;
	                } else {
	                    return PasswordValidation.PASSWORD_WRONG;
	                }
	            }
	        }
	
	    	try {
	    		if (checkTrusted && getTaskQueue() != null)
	    		{
		            final long timeToWait = 60000; // 1 minute
		            TaskHandler th = createAccountTask(TaskHandler.VALIDATE_ACCOUNT_PASSWORD, 
		            		account.getName(), account.getDispatcher().getCodi(),
		                    password, false, null);
		
		            th.setTimeout(new Date(System.currentTimeMillis() + timeToWait));
		            synchronized (th) {
		                if (th.getTask().getStatus().equals("P")) { //$NON-NLS-1$
		                    th.wait(timeToWait);
		                }
		            }
		            return th.isValidated() ? PasswordValidation.PASSWORD_GOOD
	                    : PasswordValidation.PASSWORD_WRONG;
	        	}
	    		else if (checkTrusted && "true".equals(System.getProperty("soffid.auth.trustedLogin"))) 
	    		{
	    			return validatePasswordOnServer (account, password);
	    		}
	    	}
	        catch (NoSuchBeanDefinitionException e) 
	        {
	        
	        }
	
	        return PasswordValidation.PASSWORD_WRONG;
		}
	}

    private PasswordValidation validatePasswordOnServer(AccountEntity account, Password password) throws InternalErrorException, IOException {
    	
    	if ( "S".equals(account.getDispatcher().getSegur()))
    	{
    		ConsoleLogonService ls = (ConsoleLogonService) getSeyconServerService().getServerService(ConsoleLogonService.REMOTE_PATH);
    		if (ls != null)
    			return ls.validatePassword(account.getName(), account.getDispatcher().getCodi(), password.getPassword());
    	}

    	return PasswordValidation.PASSWORD_WRONG;
	}

	private DominiContrasenyaEntity getPasswordDomain(AccountEntity account)
	{
		return account.getDispatcher().getDomini();
	}

	private UsuariEntity getUsuari(AccountEntity account) throws InternalErrorException
	{
		for (UserAccountEntity uae: account.getUsers())
			return uae.getUser();
		throw new InternalErrorException(String.format(Messages.getString("InternalPasswordServiceImpl.NoUserForAccount"), account.getName(), account.getDispatcher().getCodi())); //$NON-NLS-1$
	}

	private TaskHandler createAccountTask(String transa, String account,
            String dispatcher, Password password, boolean mustChange, Date untilDate) throws InternalErrorException {
        TasqueEntity tasque = getTasqueEntityDao().newTasqueEntity();
        tasque.setData(new Timestamp(System.currentTimeMillis()));
        tasque.setTransa(transa);
        tasque.setCoddis(dispatcher);
        tasque.setUsuari(account);
        tasque.setContra(password.toString());
        tasque.setCancon(mustChange ? "S" : "N"); //$NON-NLS-1$ //$NON-NLS-2$
        tasque.setStatus("P"); //$NON-NLS-1$
        tasque.setExpirationDate(untilDate);
        getTasqueEntityDao().create(tasque);
        try {
        	return getTaskQueue().addTask(tasque);
        } 
        catch (NoSuchBeanDefinitionException e) 
        {
            return null;
        }
    }


	@Override
	protected void handleConfirmAccountPassword(AccountEntity account,
			Password password) throws Exception
	{
        String digest = getDigest(password);
        AccountPasswordEntity contra = getAccountPasswordEntityDao().findLastByAccount(account.getId());
        if (contra != null && contra.getPassword().equals(digest)
                && contra.getActive().equals("N")) { //$NON-NLS-1$
            contra.setActive("S"); //$NON-NLS-1$
            getAccountPasswordEntityDao().update(contra);
        }
	}

	@Override
	protected boolean handleIsOldAccountPassword(AccountEntity account,
			Password password) throws Exception
	{
		if (account.getType().equals(AccountType.USER))
		{
			UsuariEntity user = getUsuari (account);
			DominiContrasenyaEntity passwordDomain = getPasswordDomain(account);
			return handleIsOldPassword(user, passwordDomain, password);
		}
		else
		{
	        String digest = getDigest(password);
	        
	        for (AccountPasswordEntity contrasenya: account.getPasswords()) {
	            if (contrasenya.getPassword().equals(digest))
	                return true;
	        }
	        return false;
		}
	}


    private Password generateRandomPassword(AccountEntity account, PoliticaContrasenyaEntity pc, boolean minLength,
            boolean maxLength) throws InternalErrorException {
        Random r = new Random(System.currentTimeMillis() + hashCode());
        Password password;
        int retries = 0;
        do {
            retries++;
            if (retries > 100)
                throw new InternalErrorException(String.format(
                        Messages.getString("InternalPasswordServiceImpl.CannotGeneratePassword"),  //$NON-NLS-1$
                        account.getName(), account.getDispatcher().getCodi()));
            password = generatePasswordCandidate(pc, minLength, maxLength, r);

        } while (!handleCheckAccountPolicy(account, password).isValid());
        return password;
    }


    /**
     * Generates a new password
     */

    protected Password randomPassword(AccountEntity account, boolean mustChange, boolean fake)
            throws Exception {
        Password password = null;

        PoliticaContrasenyaEntity pcd = getAccountPolicy(account);
        if (pcd != null) {
            password = generateRandomPassword(account, pcd, true, false);
            if (!fake) {
                doStoreAccountPassword(account, pcd, password, "N", mustChange, null); //$NON-NLS-1$
                if (account.getDispatcher().getUrl() != null && !account.getDispatcher().getUrl().isEmpty())
                	createAccountTask(TaskHandler.UPDATE_ACCOUNT_PASSWORD,
                        account.getName(),
                        account.getDispatcher().getCodi(), 
                        password, mustChange, null);
            }
            return password;
        } else
            return null;
    }


    @Override
	protected Password handleGenerateNewAccountPassword(AccountEntity account, boolean mustChange)
			throws Exception
	{
        return randomPassword(account, mustChange, false);
	}

	@Override
	protected EstatContrasenya handleGetAccountPasswordsStatus(AccountEntity account)
			throws Exception
	{
		AccountPasswordEntity last = getAccountPasswordEntityDao().findLastByAccount(account.getId());
		if (last == null)
			return null;
		else
			return getAccountPasswordEntityDao().toEstatContrasenya(last);
	}

	@Override
	protected Password handleGenerateFakeAccountPassword(AccountEntity account)
			throws Exception
	{
        return randomPassword(account, true, true);
	}

	@Override
	protected void handleStoreAndForwardAccountPassword(AccountEntity account,
			Password password, boolean mustChange, Date untilDate) throws Exception
	{
		if (account.getType().equals(AccountType.USER))
		{
			UsuariEntity user = getUsuari (account);
			DominiContrasenyaEntity passwordDomain = getPasswordDomain(account);
			handleStoreAndForwardPassword(user, passwordDomain, password, mustChange);
		}
		else
		{
            handleStoreAccountPassword(account, password, mustChange, untilDate);
    
        	createAccountTask(TaskHandler.UPDATE_ACCOUNT_PASSWORD,
        		account.getName(), account.getDispatcher().getCodi(),
                password, mustChange, untilDate);
		}
	}

	@Override
	protected boolean handleIsAccountPasswordExpired(AccountEntity account)
			throws Exception
	{
		if (account.getType().equals(AccountType.USER))
		{
			for (UserAccountEntity uae:account.getUsers())
			{
				return handleIsPasswordExpired(uae.getUser(), account.getDispatcher().getDomini());
			}
		}
		else
		{
			AccountPasswordEntity contra = getAccountPasswordEntityDao().findLastByAccount(account.getId());
	
	        if (contra.getExpirationDate().before(new Date())) {
	            return true;
	        }
		}

        return false;
	}

	@Override
	protected boolean handleExistsAccountPassword(AccountEntity account)
			throws Exception
	{
		return account.getPasswords().isEmpty();
	}

	static String defaultDispatcher;
	@Override
	protected String handleGetDefaultDispatcher() throws Exception
	{
		if (defaultDispatcher == null)
		{
			DispatcherEntityDao dao = getDispatcherEntityDao();
			String defaultName = System.getProperty("soffid.auth.system");
			if (defaultName != null)
			{
				DispatcherEntity dispatcher = dao.findByCodi(defaultName); //$NON-NLS-1$
				if (dispatcher != null)
					return defaultName;
			}
			
			for (DispatcherEntity dispatcher: dao.loadAll())
			{
				if (dispatcher.isMainDispatcher())
				{
					defaultDispatcher = dispatcher.getCodi();
					return defaultDispatcher;
				}
			}
			DispatcherEntity dispatcher = dao.findByCodi("Soffid"); //$NON-NLS-1$
			if (dispatcher == null) 
				dispatcher = dao.findByCodi("soffid"); //$NON-NLS-1$
			if (dispatcher == null)
				dispatcher = dao.findByCodi("seu"); //$NON-NLS-1$
			if (dispatcher == null)
				defaultDispatcher = "soffid"; //$NON-NLS-1$
			else
			{
				dispatcher.setMainDispatcher(true);
				getDispatcherEntityDao().update(dispatcher);
				defaultDispatcher = dispatcher.getCodi();
			}
		}
		return defaultDispatcher;
		
	}

	private void addCondition (StringBuffer b, String message, Long value)
	{
   		if (value != null && value.longValue() > 0)
   			b.append (String.format(message, value))
   				.append('\n');
	}

	private void addCondition (StringBuffer b, String message, Boolean value)
	{
   		if (value != null && value.booleanValue())
   			b.append (String.format(message, value))
   				.append('\n');
	}

	private void addCondition (StringBuffer b, String message, String value)
	{
   		if (value != null && value.length() > 0)
   			b.append (String.format(message, value))
   				.append('\n');
	}


	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.InternalPasswordServiceBase#handleGetPolicyDescription(es.caib.seycon.ng.model.PoliticaContrasenyaEntity)
	 */
	@Override
	protected String handleGetPolicyDescription (PoliticaContrasenyaEntity politica)
					throws Exception
	{
		StringBuffer b = new StringBuffer ();
   		addCondition (b, Messages.getString("PasswordServiceImpl.MinLongCondition"), politica.getMinLongitud()); //$NON-NLS-1$
   		addCondition (b, Messages.getString("PasswordServiceImpl.MaxLongCondition"), politica.getMaxLongitud()); //$NON-NLS-1$
   		addCondition (b, Messages.getString("PasswordServiceImpl.MinMinusculesCondition"), politica.getMinMinuscules()); //$NON-NLS-1$
   		addCondition (b, Messages.getString("PasswordServiceImpl.MaxMinusculesCondition"), politica.getMaxMinuscules()); //$NON-NLS-1$
   		addCondition (b, Messages.getString("PasswordServiceImpl.MinCapitalsCondition"), politica.getMinMajuscules()); //$NON-NLS-1$
   		addCondition (b, Messages.getString("PasswordServiceImpl.MaxCapitalCondition"), politica.getMaxMajuscules()); //$NON-NLS-1$
   		addCondition (b, Messages.getString("PasswordServiceImpl.MinNumbersCondition"), politica.getMinNumeros()); //$NON-NLS-1$
   		addCondition (b, Messages.getString("PasswordServiceImpl.MaxNumbersCondition"), politica.getMaxNumeros()); //$NON-NLS-1$
   		addCondition (b, Messages.getString("PasswordServiceImpl.MinSignsCondition"), politica.getMinSignesPuntuacio()); //$NON-NLS-1$
   		addCondition (b, Messages.getString("PasswordServiceImpl.MaxSignsCondition"), politica.getMaxSignesPuntuacio()); //$NON-NLS-1$
   		addCondition (b, Messages.getString("PasswordServiceImpl.PatternCondition"), politica.getExpressioRegular()); //$NON-NLS-1$
   		addCondition (b, Messages.getString("PasswordServiceImpl.MaxHistoricCondition"), politica.getMaxHistoric()); //$NON-NLS-1$
   		addCondition (b, Messages.getString("PasswordServiceImpl.ComplexPasswordsCondition"), politica.getComplexPasswords()); //$NON-NLS-1$
   		if (politica.getParaulaProhibidaContrasenya() != null && ! politica.getParaulaProhibidaContrasenya().isEmpty())
   		{
   			b.append (Messages.getString("PasswordServiceImpl.WordNotAllowedCondition")); //$NON-NLS-1$
   			for (ParaulaProhibidaPoliticaContrasenyaEntity o: politica.getParaulaProhibidaContrasenya())
   			{
   				b.append ("- ") //$NON-NLS-1$
   					.append(o.getParaulaProhibida().getParaulaProhibida())
   					.append('\n');
   			}
   		}
   		addCondition (b, Messages.getString("PasswordServiceImpl.PasswordMaxDurationCondition"), politica.getDuradaMaxima()); //$NON-NLS-1$
   		addCondition (b, Messages.getString("PasswordServiceImpl.ExpiredPasswordMaxDurationCondition"), politica.getDuradaMaximaCaducada()); //$NON-NLS-1$
       	return b.toString();
	}

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.InternalPasswordServiceBase#handleCheckPolicy(es.caib.seycon.ng.model.PoliticaContrasenyaEntity, es.caib.seycon.ng.comu.Password)
	 */
	@Override
	protected PolicyCheckResult handleCheckPolicy (PoliticaContrasenyaEntity policy,
					Password password) throws Exception
	{
		Collection<AccountEntity> accounts = Collections.emptyList();
		return 	internalCheckBasicPolicy( policy, password, accounts);
	}

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.InternalPasswordServiceBase#handleUpdateExpiredPasswords(es.caib.seycon.ng.model.UsuariEntity, boolean)
	 */
	@Override
	protected boolean handleUpdateExpiredPasswords (final UsuariEntity usuari,
					boolean externalAuth) throws Exception
	{
		boolean anyChange = false;
		for (final DominiContrasenyaEntity dce: getDominiContrasenyaEntityDao().loadAll())
		{
			EstatContrasenya status = getPasswordsStatus(usuari, dce);
			if (status == null || status.getCaducada().booleanValue())
			{
				final PoliticaContrasenyaEntity pce = getUserPolicy(usuari, dce);
				if (pce != null)
				{
					if (pce.getTipus().equals("A") || externalAuth) //$NON-NLS-1$
					{
						final Password p = generateFakePassword(usuari, dce);

						PlatformTransactionManager txMgr = (PlatformTransactionManager) ctx.getBean("transactionManager"); //$NON-NLS-1$
						TransactionTemplate txTemplate = new TransactionTemplate(txMgr);
						txTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
						InternalErrorException e = (InternalErrorException) txTemplate.execute(new TransactionCallback()
						{
							
							public Object doInTransaction (TransactionStatus status)
							{
								try {	
									InternalPasswordService svc = (InternalPasswordService) ctx.getBean(InternalPasswordService.SERVICE_NAME);
									svc.storePassword(usuari.getCodi(), dce.getCodi(), p.getPassword(), false);
								} catch (InternalErrorException e) {
									return e;
								}
								return null;
							}
						});
						
						if (e != null)
							throw e;
						
						executeOB(TaskHandler.UPDATE_USER_PASSWORD, dce.getCodi(), usuari.getCodi(), p, false);
						anyChange = true;
					}
				}
			}
		}
		return anyChange;
	}

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.InternalPasswordServiceBase#handleEnumExpiredPasswords(es.caib.seycon.ng.model.UsuariEntity)
	 */
	@Override
	protected Collection<DominiContrasenyaEntity> handleEnumExpiredPasswords (UsuariEntity usuari)
					throws Exception
	{
		Collection<DominiContrasenyaEntity> list = new LinkedList<DominiContrasenyaEntity>();
		for (DominiContrasenyaEntity dce: getDominiContrasenyaEntityDao().loadAll())
		{
			EstatContrasenya status = getPasswordsStatus(usuari, dce);
			if (status == null || status.getCaducada().booleanValue())
			{
				PoliticaContrasenyaEntity pce = getUserPolicy(usuari, dce);
				if (pce != null)
				{
					if (pce.getTipus().equals("M")) //$NON-NLS-1$
					{
						list.add(dce);
					}
				}
			}
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.InternalPasswordServiceBase#handleStoreAndSynchronizePassword(es.caib.seycon.ng.model.UsuariEntity, es.caib.seycon.ng.model.DominiContrasenyaEntity, es.caib.seycon.ng.comu.Password, boolean)
	 */
	@Override
	protected void handleStoreAndSynchronizePassword (UsuariEntity user,
					DominiContrasenyaEntity passwordDomain, Password password,
					boolean mustChange) throws Exception
	{
        handleStorePassword(user, passwordDomain, password, mustChange);

        executeOB(TaskHandler.UPDATE_USER_PASSWORD, 
        				passwordDomain.getCodi(), user.getCodi(), password, mustChange);
	}

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.InternalPasswordServiceBase#handleStoreAndSynchronizeAccountPassword(es.caib.seycon.ng.model.AccountEntity, es.caib.seycon.ng.comu.Password, boolean, java.util.Date)
	 */
	@Override
	protected void handleStoreAndSynchronizeAccountPassword (AccountEntity account,
					Password password, boolean mustChange, Date expirationDate)
					throws Exception
	{
		if (account.getType().equals(AccountType.USER))
		{
			UsuariEntity user = getUsuari (account);
			DominiContrasenyaEntity passwordDomain = getPasswordDomain(account);
			handleStoreAndSynchronizePassword(user, passwordDomain, password, mustChange);
		}
		else
		{
            handleStoreAccountPassword(account, password, mustChange, expirationDate);
    
        	executeOB(TaskHandler.UPDATE_ACCOUNT_PASSWORD,
        		account.getName(), account.getDispatcher().getCodi(),
                password, mustChange);
		}
	}

	/* (non-Javadoc)
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 */
	public void setApplicationContext (ApplicationContext applicationContext)
					throws BeansException
	{
		this.ctx = applicationContext;
		
	}
	
	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.InternalPasswordServiceBase#handleStoreAndForwardPasswordById(long, long, es.caib.seycon.ng.comu.Password, boolean)
	 */
	@Override
	protected void handleStoreAndForwardPasswordById (long user, long passwordDomain,
					Password password, boolean mustChange) throws Exception
	{
		UsuariEntity usuari = getUsuariEntityDao().load(user);
		DominiContrasenyaEntity domini = getDominiContrasenyaEntityDao().load(passwordDomain);
		storeAndForwardPassword(usuari, domini, password, mustChange);
	}

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.InternalPasswordServiceBase#handleStoreAndForwardAccountPasswordById(long, es.caib.seycon.ng.comu.Password, boolean, java.util.Date)
	 */
	@Override
	protected void handleStoreAndForwardAccountPasswordById (long account,
					Password password, boolean mustChange, Date expirationDate)
					throws Exception
	{
		AccountEntity accountEntity = getAccountEntityDao().load(account);
		storeAndForwardAccountPassword(accountEntity, password, mustChange, expirationDate);
	}

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.InternalPasswordServiceBase#handleGetPasswordsStatusById(long, long)
	 */
	@Override
	protected EstatContrasenya handleGetPasswordsStatusById (long user, long domini)
					throws Exception
	{
		UsuariEntity usuari = getUsuariEntityDao().load(user);
		DominiContrasenyaEntity dominiEntity = getDominiContrasenyaEntityDao().load(domini);
		return getPasswordsStatus(usuari, dominiEntity);
	}
	
	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.InternalPasswordServiceBase#handleGetAccountPasswordsStatusById(long)
	 */
	@Override
	protected EstatContrasenya handleGetAccountPasswordsStatusById (long account)
					throws Exception
	{
		AccountEntity accountEntity = getAccountEntityDao().load(account);
		return getAccountPasswordsStatus(accountEntity);
	}

}
