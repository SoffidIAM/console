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

import com.soffid.iam.api.AsyncList;
import com.soffid.iam.api.ForbiddenWord;
import com.soffid.iam.api.PagedResult;
import com.soffid.iam.api.PasswordDomain;
import com.soffid.iam.api.PasswordPolicy;
import com.soffid.iam.api.PasswordPolicyForbbidenWord;
import com.soffid.iam.api.System;
import com.soffid.iam.api.UserDomain;
import com.soffid.iam.api.UserType;
import com.soffid.iam.bpm.service.scim.ScimHelper;
import com.soffid.iam.model.ForbiddenWordEntity;
import com.soffid.iam.model.PasswordDomainEntity;
import com.soffid.iam.model.PasswordPolicyEntity;
import com.soffid.iam.model.PolicyForbiddenWordEntity;
import com.soffid.iam.model.UserDomainEntity;
import com.soffid.iam.model.UserTypeEntity;
import com.soffid.iam.model.UserTypeEntityDao;
import com.soffid.iam.model.criteria.CriteriaSearchConfiguration;
import com.soffid.iam.service.account.AccountNameGenerator;
import es.caib.seycon.ng.exception.InternalErrorException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @see es.caib.seycon.ng.servei.DominiUsuariService
 */
public class UserDomainServiceImpl extends com.soffid.iam.service.UserDomainServiceBase  implements ApplicationContextAware {

	private ApplicationContext applicationContext;

	/**
	 * @see es.caib.seycon.ng.servei.DominiUsuariService#create(es.caib.seycon.ng.comu.DominiUsuari)
	 */
	protected com.soffid.iam.api.UserDomain handleCreate(com.soffid.iam.api.UserDomain dominiUsuari) throws java.lang.Exception {

		if (dominiUsuari.getCode() == null || "".equals(dominiUsuari.getCode())) { //$NON-NLS-1$
			throw new Exception(Messages.getString("UserDomainServiceImpl.0")); //$NON-NLS-1$
		}

		if (dominiUsuari.getType() == null || "".equals(dominiUsuari.getType())) { //$NON-NLS-1$
			throw new Exception(Messages.getString("UserDomainServiceImpl.1")); //$NON-NLS-1$
		}

		UserDomainEntity entity = getUserDomainEntityDao().userDomainToEntity(dominiUsuari);
		getUserDomainEntityDao().create(entity);
		return getUserDomainEntityDao().toUserDomain(entity);
	}

	/**
	 * @see es.caib.seycon.ng.servei.DominiUsuariService#update(es.caib.seycon.ng.comu.DominiUsuari)
	 */
	protected com.soffid.iam.api.UserDomain handleUpdate(com.soffid.iam.api.UserDomain dominiUsuari) throws java.lang.Exception {

		if (dominiUsuari.getCode() == null || "".equals(dominiUsuari.getCode())) { //$NON-NLS-1$
			throw new Exception(Messages.getString("UserDomainServiceImpl.2")); //$NON-NLS-1$
		}

		if (dominiUsuari.getType() == null || "".equals(dominiUsuari.getType())) { //$NON-NLS-1$
			throw new Exception(Messages.getString("UserDomainServiceImpl.3")); //$NON-NLS-1$
		}

		UserDomainEntity entity = getUserDomainEntityDao().userDomainToEntity(dominiUsuari);
		getUserDomainEntityDao().update(entity);
		return getUserDomainEntityDao().toUserDomain(entity);
	}

	/**
	 * @see es.caib.seycon.ng.servei.DominiUsuariService#delete(es.caib.seycon.ng.comu.DominiUsuari)
	 */
	protected void handleDelete(com.soffid.iam.api.UserDomain dominiUsuari) throws java.lang.Exception {
		UserDomainEntity entity = getUserDomainEntityDao().userDomainToEntity(dominiUsuari);
		getUserDomainEntityDao().remove(entity);
	}

	/**
	 * @see es.caib.seycon.ng.servei.DominiUsuariService#create(es.caib.seycon.ng.comu.TipusUsuari)
	 */
	protected com.soffid.iam.api.UserType handleCreate(com.soffid.iam.api.UserType tipusUsuari) throws java.lang.Exception {
		UserTypeEntity entity = getUserTypeEntityDao().userTypeToEntity(tipusUsuari);
		getUserTypeEntityDao().create(entity);
		return getUserTypeEntityDao().toUserType(entity);

	}

	/**
	 * @see es.caib.seycon.ng.servei.DominiUsuariService#update(es.caib.seycon.ng.comu.TipusUsuari)
	 */
	protected com.soffid.iam.api.UserType handleUpdate(com.soffid.iam.api.UserType tipusUsuari) throws java.lang.Exception {
		UserTypeEntity entity = getUserTypeEntityDao().userTypeToEntity(tipusUsuari);
		getUserTypeEntityDao().update(entity);
		return getUserTypeEntityDao().toUserType(entity);
	}

	/**
	 * @see es.caib.seycon.ng.servei.DominiUsuariService#delete(es.caib.seycon.ng.comu.TipusUsuari)
	 */
	protected void handleDelete(com.soffid.iam.api.UserType tipusUsuari) throws java.lang.Exception {
		UserTypeEntity entity = getUserTypeEntityDao().userTypeToEntity(tipusUsuari);
		if(entity.getAccounts() != null && !entity.getAccounts().isEmpty())
			throw new InternalErrorException(Messages.getString("UserDomainServiceImpl.UserTypeWithAccounts")); //$NON-NLS-1$
		if(entity.getPolicies() != null && !entity.getPolicies().isEmpty())
			throw new InternalErrorException(Messages.getString("UserDomainServiceImpl.UserTypeWithPolicy")); //$NON-NLS-1$
		getUserTypeEntityDao().remove(entity);
	}

	/**
	 * @see es.caib.seycon.ng.servei.DominiUsuariService#findAllDominiUsuari()
	 */
	protected java.util.Collection<UserDomain> handleFindAllUserDomain() throws java.lang.Exception {
		Collection<UserDomainEntity> dominisUsuari = getUserDomainEntityDao().loadAll();
		return getUserDomainEntityDao().toUserDomainList(dominisUsuari);
	}
	
	/**
	 * @see es.caib.seycon.ng.servei.DominiUsuariService#findAllTipusUsuari()
	 */
	protected java.util.Collection<UserType> handleFindAllUserType() throws java.lang.Exception {
		Collection tipusUsuari = getUserTypeEntityDao().loadAll();
		return getUserTypeEntityDao().toUserTypeList(tipusUsuari);
	}

	@Override
    protected Collection<PasswordPolicy> handleFindAllPasswordPolicyDomain(String codiDomini) throws Exception {
		Collection politiques = getPasswordPolicyEntityDao().findByPasswordDomain(codiDomini);
		return getPasswordPolicyEntityDao().toPasswordPolicyList(politiques);
	}

	@Override
    protected Collection<ForbiddenWord> handleFindAllForbiddenWords() throws Exception {
		Collection forbidden = getForbiddenWordEntityDao().loadAll();
		return getForbiddenWordEntityDao().toForbiddenWordList(forbidden);
	}

	@Override
    protected ForbiddenWord handleCreate(ForbiddenWord paraulaProhibida) throws Exception {
		ForbiddenWordEntity entity = getForbiddenWordEntityDao().forbiddenWordToEntity(paraulaProhibida);
		getForbiddenWordEntityDao().create(entity);
		return getForbiddenWordEntityDao().toForbiddenWord(entity);
	}

	@Override
    protected ForbiddenWord handleUpdate(ForbiddenWord paraulaProhibida) throws Exception {
		ForbiddenWordEntity entity = getForbiddenWordEntityDao().forbiddenWordToEntity(paraulaProhibida);
		getForbiddenWordEntityDao().update(entity);
		return getForbiddenWordEntityDao().toForbiddenWord(entity);
	}

	@Override
    protected void handleDelete(ForbiddenWord paraulaProhibida) throws Exception {
		ForbiddenWordEntity entity = getForbiddenWordEntityDao().forbiddenWordToEntity(paraulaProhibida);
		getForbiddenWordEntityDao().remove(entity);

	}

	@Override
    protected PasswordPolicy handleCreate(PasswordPolicy politicaContrasenyaDomini) throws Exception {
		if (politicaContrasenyaDomini.getUserType() == null || "".equals(politicaContrasenyaDomini.getUserType())) { //$NON-NLS-1$
			throw new Exception(Messages.getString("UserDomainServiceImpl.4")); //$NON-NLS-1$
		}

		if (politicaContrasenyaDomini.getType() == null || "".equals(politicaContrasenyaDomini.getType())) { //$NON-NLS-1$
			throw new Exception(Messages.getString("UserDomainServiceImpl.5")); //$NON-NLS-1$
		}

		// Mirem que no existisca una altra amb el mateix tipus d'usuari i
		// domini de contrasenyes
		Collection altres = getPasswordPolicyEntityDao().findByPasswordDomain(politicaContrasenyaDomini.getPasswordDomainCode());
		if (altres != null)
			for (Iterator<PasswordPolicyEntity> it = altres.iterator(); it.hasNext(); ) {
            PasswordPolicyEntity pcd = it.next();
            if (pcd.getUserType() != null && pcd.getUserType().getName().equals(politicaContrasenyaDomini.getUserType())) {
                UserTypeEntity tipusu = getUserTypeEntityDao().findByName(politicaContrasenyaDomini.getUserType());
                throw new Exception(String.format(Messages.getString("UserDomainServiceImpl.6"), tipusu.getDescription(), politicaContrasenyaDomini.getPasswordDomainCode()));
            }
        }
		
		checkpoliticaContrasenyaDominiValues(politicaContrasenyaDomini);

		PasswordPolicyEntity entity = getPasswordPolicyEntityDao().passwordPolicyToEntity(politicaContrasenyaDomini);
		getPasswordPolicyEntityDao().create(entity);
		return getPasswordPolicyEntityDao().toPasswordPolicy(entity);

	}

	/** Method that implements the functionality to check the password policy values.
	 * @param pcd Password policy to check.
	 */
	private void checkpoliticaContrasenyaDominiValues(PasswordPolicy pcd) throws Exception {
		checkPasswordLength(pcd);
		checkUppercaseLettersLength(pcd);
		checkLowercaseLettersLength(pcd);
		checkNumberLength(pcd);
		checkSymbolsLength(pcd);
		
		checkGlobalMinValues(pcd);
		checkGlobalMaxValues(pcd);
	}

	/** Method that implements the functionality to check the password policy minimum values.
	 * @param pcd Password policy to check.
	 */
	private void checkGlobalMaxValues(PasswordPolicy pcd) throws Exception {
		long maxValues = 0;
		
		if (pcd.getMaximumLength() != null)
		{
			if (pcd.getMaximumUppercase() != null)
				maxValues += pcd.getMaximumUppercase();
			
			if (pcd.getMaximumLowercase() != null)
				maxValues += pcd.getMaximumLowercase();
			
			if (pcd.getMaximumNumbers() != null)
				maxValues += pcd.getMaximumNumbers();
			
			if (pcd.getMaximumSymbols() != null)
				maxValues += pcd.getMaximumSymbols();
			
			if (pcd.getMaximumLength() < maxValues)
			{
				throw new Exception(Messages.getString("UserDomainServiceImpl.MaxValuesLengthErrorMessage")); //$NON-NLS-1$
			}
		}
	}

	/** Method that implements the functionality to check the password policy maximum values.
	 * @param pcd Password policy to check.
	 */
	private void checkGlobalMinValues(PasswordPolicy pcd) throws Exception {
		long minValues = 0;
		
		if (pcd.getMinimumLength() != null)
		{
    		if (pcd.getMinimumUppercase() != null)
    			minValues += pcd.getMinimumUppercase();
    		
    		if (pcd.getMinimumLowercase() != null)
    			minValues += pcd.getMinimumLowercase();
    		
    		if (pcd.getMinimumNumbers() != null)
    			minValues += pcd.getMinimumNumbers();
    		
    		if (pcd.getMinimumSymbols() != null)
    			minValues += pcd.getMinimumSymbols();
    		
    		if (pcd.getMinimumLength() < minValues)
    		{
    			throw new Exception(Messages.getString("UserDomainServiceImpl.MinValuesLengthErrorMessage")); //$NON-NLS-1$
    		}
		}
	}

	/** Method that implements the functionality to check the password policy symbols length.
	 * @param pcd Password policy to check.
	 */
	private void checkSymbolsLength(PasswordPolicy pcd) throws Exception {
		if ((pcd.getMinimumSymbols() != null) && (pcd.getMaximumSymbols() != null))
		{
			if (pcd.getMinimumSymbols() > pcd.getMaximumSymbols())
			{
				throw new Exception(Messages.getString("UserDomainServiceImpl.MaxSymbolsLengthErrorMessage")); //$NON-NLS-1$
			}
		}
	}

	/** Method that implements the functionality to check the password policy numbers length.
	 * @param pcd Password policy to check.
	 */
	private void checkNumberLength(PasswordPolicy pcd) throws Exception {
		if ((pcd.getMinimumNumbers() != null) && (pcd.getMaximumNumbers() != null))
		{
			if (pcd.getMinimumNumbers() > pcd.getMaximumNumbers())
			{
				throw new Exception(Messages.getString("UserDomainServiceImpl.MaxNumbersLengthErrorMessage")); //$NON-NLS-1$
			}
		}
	}

	/** Method that implements the functionality to check the password policy lower case length.
	 * @param pcd Password policy to check.
	 */
	private void checkLowercaseLettersLength(PasswordPolicy pcd) throws Exception {
		if ((pcd.getMinimumLowercase() != null) && (pcd.getMaximumLowercase() != null))
		{
			if (pcd.getMinimumLowercase() > pcd.getMaximumLowercase())
			{
				throw new Exception(Messages.getString("UserDomainServiceImpl.MaxLowerLengthErrorMessage")); //$NON-NLS-1$
			}
		}
	}

	/** Method that implements the functionality to check the password policy upper case length.
	 * @param pcd Password policy to check.
	 */
	private void checkUppercaseLettersLength(PasswordPolicy pcd) throws Exception {
		if ((pcd.getMinimumUppercase() != null) && (pcd.getMaximumUppercase() != null))
		{
			if (pcd.getMinimumUppercase() > pcd.getMaximumUppercase())
			{
				throw new Exception(Messages.getString("UserDomainServiceImpl.MaxUpperLengthErrorMessage")); //$NON-NLS-1$
			}
		}
	}

	/** Method that implements the functionality to check the password policy length.
	 * @param pcd Password policy to check.
	 */
	private void checkPasswordLength(PasswordPolicy pcd) throws Exception {
		// Check password length
		if ((pcd.getMinimumLength() != null) && (pcd.getMaximumLength() != null))
		{
			if (pcd.getMinimumLength() > pcd.getMaximumLength())
			{
				throw new Exception(
					Messages.getString(Messages.getString("UserDomainServiceImpl.MaxLengthErrorMessage"))); //$NON-NLS-1$
			}
		}
	}

	@Override
    protected PasswordPolicy handleUpdate(PasswordPolicy politicaContrasenyaDomini) throws Exception {
		if (politicaContrasenyaDomini.getUserType() == null || "".equals(politicaContrasenyaDomini.getUserType())) { //$NON-NLS-1$
			throw new Exception(Messages.getString("UserDomainServiceImpl.7")); //$NON-NLS-1$
		}

		if (politicaContrasenyaDomini.getType() == null || "".equals(politicaContrasenyaDomini.getType())) { //$NON-NLS-1$
			throw new Exception(Messages.getString("UserDomainServiceImpl.8")); //$NON-NLS-1$
		}

		PasswordPolicyEntity entity = getPasswordPolicyEntityDao().passwordPolicyToEntity(politicaContrasenyaDomini);

		getPasswordPolicyEntityDao().update(entity);
		return getPasswordPolicyEntityDao().toPasswordPolicy(entity);
	}

	@Override
    protected void handleDelete(PasswordPolicy politicaContrasenyaDomini) throws Exception {
		PasswordPolicyEntity entity = getPasswordPolicyEntityDao().passwordPolicyToEntity(politicaContrasenyaDomini);
		getPasswordPolicyEntityDao().remove(entity);
	}

	@Override
    protected PasswordPolicyForbbidenWord handleCreate(PasswordPolicyForbbidenWord paraulaProhibidaContrasenyaDomini) throws Exception {
		PolicyForbiddenWordEntity entity = getPolicyForbiddenWordEntityDao().passwordPolicyForbbidenWordToEntity(paraulaProhibidaContrasenyaDomini);
		getPolicyForbiddenWordEntityDao().create(entity);
		return getPolicyForbiddenWordEntityDao().toPasswordPolicyForbbidenWord(entity);
	}

	@Override
    protected PasswordPolicyForbbidenWord handleUpdate(PasswordPolicyForbbidenWord paraulaProhibidaContrasenyaDomini) throws Exception {
		PolicyForbiddenWordEntity entity = getPolicyForbiddenWordEntityDao().passwordPolicyForbbidenWordToEntity(paraulaProhibidaContrasenyaDomini);
		getPolicyForbiddenWordEntityDao().update(entity);
		return getPolicyForbiddenWordEntityDao().toPasswordPolicyForbbidenWord(entity);
	}

	@Override
    protected void handleDelete(PasswordPolicyForbbidenWord paraulaProhibidaContrasenyaDomini) throws Exception {
		PolicyForbiddenWordEntity entity = getPolicyForbiddenWordEntityDao().passwordPolicyForbbidenWordToEntity(paraulaProhibidaContrasenyaDomini);
		getPolicyForbiddenWordEntityDao().remove(entity);
	}

	@Override
    protected PasswordDomain handleCreate(PasswordDomain dominiContrasenya) throws Exception {
		if (dominiContrasenya.getCode() == null || "".equals(dominiContrasenya.getCode().trim())) { //$NON-NLS-1$
			throw new Exception(Messages.getString("UserDomainServiceImpl.9")); //$NON-NLS-1$
		}
		PasswordDomainEntity contrasenyaDominiEntity = getPasswordDomainEntityDao().passwordDomainToEntity(dominiContrasenya);
		
		getPasswordDomainEntityDao().create(contrasenyaDominiEntity);
		return getPasswordDomainEntityDao().toPasswordDomain(contrasenyaDominiEntity);
	}

	@Override
    protected PasswordDomain handleUpdate(PasswordDomain dominiContrasenya) throws Exception {
		if (dominiContrasenya.getCode() == null || "".equals(dominiContrasenya.getCode().trim())) { //$NON-NLS-1$
			throw new Exception(Messages.getString("UserDomainServiceImpl.11")); //$NON-NLS-1$
		}
		PasswordDomainEntity contrasenyaDominiEntity = getPasswordDomainEntityDao().passwordDomainToEntity(dominiContrasenya);

		getPasswordDomainEntityDao().update(contrasenyaDominiEntity);
		return getPasswordDomainEntityDao().toPasswordDomain(contrasenyaDominiEntity);
	}

	@Override
    protected void handleDelete(PasswordDomain dominiContrasenya) throws Exception {
		PasswordDomainEntity contrasenyaDominiEntity = getPasswordDomainEntityDao().passwordDomainToEntity(dominiContrasenya);

		getPasswordDomainEntityDao().remove(contrasenyaDominiEntity);
	}

	final static String DU_DU = "DU"; // Domini d'usuaris //$NON-NLS-1$
	final static String DU_DU_S = "Domini d'usuaris"; //$NON-NLS-1$
	final static String DU_DC = "DC"; // Domini de contrasenyes //$NON-NLS-1$
	// Domini de contrasenyes:
	final static String DU_DC_S = "Domini de contrasenyes";  //$NON-NLS-1$
	final static String DU_PC = "PC"; // Politica de contrasenyes de domini //$NON-NLS-1$
	// Politica de contrasenyes de domini:
	final static String DU_PC_S = "Política de contrasenyes";  //$NON-NLS-1$

	@Override
    protected UserDomain handleFindUserDomainByName(String codiDominiUsuari) throws Exception {
		UserDomainEntity d = getUserDomainEntityDao().findByName(codiDominiUsuari);
		if (d == null)
			return null;
		else
			return getUserDomainEntityDao().toUserDomain(d);
	}

	@Override
    protected Collection<PasswordPolicyForbbidenWord> handleFindForbiddenWordsPasswordPolicy(PasswordPolicy politicaContrasenya) throws Exception {
		PasswordPolicyEntity entity = getPasswordPolicyEntityDao().passwordPolicyToEntity(politicaContrasenya);
		// relació entre ParaulesProhibidesEntity i PoliticaContrasenyaEntity
		Collection<PolicyForbiddenWordEntity> paraulesContrasenya = new LinkedList(entity.getForbiddenWords());
		// Tornem les relacions ParaulaProhibidaPoliticaContrasenya
		return getPolicyForbiddenWordEntityDao().toPasswordPolicyForbbidenWordList(paraulesContrasenya);
	}

	@Override
    protected PasswordDomain handleFindPasswordDomainByName(String codi) throws Exception {
		PasswordDomainEntity dce = getPasswordDomainEntityDao().findByName(codi);
		if (dce == null)
			return null;
		else
			return getPasswordDomainEntityDao().toPasswordDomain(dce);
	}

	@Override
    protected PasswordPolicy handleFindPolicyByTypeAndPasswordDomain(String tipus, String domini) throws Exception {
		PasswordPolicyEntity pce = getPasswordPolicyEntityDao().findByPasswordDomainAndUserType(domini, tipus);
		if (pce == null)
			return null;
		else
			return getPasswordPolicyEntityDao().toPasswordPolicy(pce);
	}

	@Override
    protected Collection<PasswordDomain> handleFindAllPasswordDomain() throws Exception {
		return getPasswordDomainEntityDao().toPasswordDomainList(getPasswordDomainEntityDao().loadAll());
	}

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException
	{
		this.applicationContext = applicationContext;
	}

	@Override
	protected Collection<String> handleFindNameGenerators() throws Exception
	{
		LinkedList<String> result =  new LinkedList<String>();
		for (String name: applicationContext.getBeanNamesForType(AccountNameGenerator.class))
		{
			result.add(name);
		}
		return result;
	}
	
	@Override
	protected AsyncList<UserType> handleFindUserTypeByTextAndFilterAsync(final String text, final String jsonQuery)
			throws Exception {
		final AsyncList<UserType> result = new AsyncList<UserType>();
		getAsyncRunnerService().run(new Runnable() {
			@Override
			public void run() {
				try {
					doFindUserTypeByTextAndFilter(text, jsonQuery, null, null, result);
				} catch (Throwable e) {
					throw new RuntimeException(e);
				}				
			}
		}, result);

		return result;
	}

	private PagedResult<UserType> doFindUserTypeByTextAndFilter(String text, String jsonQuery,
			Integer start, Integer pageSize,
			List<UserType> result) throws Exception {
		final UserTypeEntityDao dao = getUserTypeEntityDao();
		ScimHelper h = new ScimHelper(UserType.class);
		h.setPrimaryAttributes(new String[] { "code", "description"});
		CriteriaSearchConfiguration config = new CriteriaSearchConfiguration();
		config.setFirstResult(start);
		config.setMaximumResultSize(pageSize);
		h.setConfig(config);
		h.setTenantFilter("tenant.id");
		h.setGenerator((entity) -> {
			return dao.toUserType((UserTypeEntity) entity);
		}); 
		h.search(text, jsonQuery, (Collection) result); 
		PagedResult<UserType> pr = new PagedResult<>();
		pr.setStartIndex(start);
		pr.setItemsPerPage(pageSize);
		pr.setTotalResults(h.count());
		pr.setResources(result);
		return pr;
	}
	
	@Override
	protected PagedResult<UserType> handleFindUserTypeByTextAndFilter(String text, String jsonQuery,
			Integer start, Integer pageSize) throws Exception {
		final LinkedList<UserType> result = new LinkedList<UserType>();
		return doFindUserTypeByTextAndFilter(text, jsonQuery, start, pageSize, result);
	}

}