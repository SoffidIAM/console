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

import com.soffid.iam.api.Audit;
import com.soffid.iam.model.AccountEntity;
import com.soffid.iam.model.AuditEntity;
import com.soffid.iam.model.PasswordPolicyEntity;
import com.soffid.iam.model.UserAccountEntity;
import com.soffid.iam.model.UserEntity;
import com.soffid.iam.utils.ConfigurationCache;
import com.soffid.iam.utils.Security;

import es.caib.seycon.ng.comu.AccountType;

import com.soffid.iam.api.Password;
import com.soffid.iam.api.PasswordValidation;
import com.soffid.iam.api.PolicyCheckResult;

import es.caib.seycon.ng.exception.BadPasswordException;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.exception.InvalidPasswordException;
import es.caib.seycon.ng.exception.UnknownUserException;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;

import org.springframework.dao.InvalidDataAccessResourceUsageException;

/**
 * @see es.caib.seycon.ng.servei.PasswordService
 */
public class PasswordServiceImpl
    extends com.soffid.iam.service.PasswordServiceBase
{

    /**
     * @see es.caib.seycon.ng.servei.PasswordService#checkPolicy(java.lang.String, java.lang.String, es.caib.seycon.ng.comu.Password)
     */
    @SuppressWarnings("rawtypes")
    protected PolicyCheckResult handleCheckPolicy(java.lang.String user, java.lang.String passwordDomain, Password password)
        throws java.lang.Exception
    {
       	AccountEntity acc = getAccountEntityDao().findByNameAndSystem(user, passwordDomain);
       	if (acc == null)
       		return null;
       	else
       		return getInternalPasswordService().checkAccountPolicy(acc, password);
    }

    /**
     * @see es.caib.seycon.ng.servei.PasswordService#checkPassword(java.lang.String, java.lang.String, Password, boolean, boolean)
     */
    protected boolean handleCheckPassword(java.lang.String user, java.lang.String system, 
            Password password, boolean checkTrusted, boolean checkExpired)
        throws java.lang.Exception
    {
       	AccountEntity acc = getAccountEntityDao().findByNameAndSystem(user, system);
       	if (acc == null)
       	{
   			throw new InternalErrorException("Account not found "+user+" at "+system);
       	}	

       	return getInternalPasswordService().checkAccountPassword(acc, password, checkTrusted, checkExpired) != PasswordValidation.PASSWORD_WRONG;
    }

    /**
     * @see es.caib.seycon.ng.servei.PasswordService#checkPin(java.lang.String, java.lang.String)
     */
    protected boolean handleCheckPin(java.lang.String user, java.lang.String pin)
        throws java.lang.Exception
    {
        UserEntity ue = getUserEntityDao().findByUserName(user);
        if (ue == null)
            throw new InternalErrorException(String.format(Messages.getString("PasswordServiceImpl.5"), user)); //$NON-NLS-1$
        return getInternalPasswordService().checkPin(ue, pin);
    }

	private void auditaCanviPassword(String codiUsuariAuditat, String codiDominiContrasenyes) {
		String codiUsuari = Security.getCurrentAccount();
		Audit auditoria = new Audit();
		auditoria.setAction("p"); //$NON-NLS-1$
		auditoria.setUser(codiUsuariAuditat);
		auditoria.setAuthor(codiUsuari);
		auditoria.setObject("SC_USUARI"); //$NON-NLS-1$
		auditoria.setPasswordDomain(codiDominiContrasenyes);

		AuditEntity auditoriaEntity = getAuditEntityDao().auditToEntity(auditoria);
		getAuditEntityDao().create(auditoriaEntity);
	}


	@Override
    protected void handleChangePassword(String user, String passwordDomain,
            Password oldPassword, Password newPassword) throws Exception {
       	AccountEntity acc = getAccountEntityDao().findByNameAndSystem(user, passwordDomain);
 
       	com.soffid.iam.service.InternalPasswordService ips = getInternalPasswordService();
        
        if ( ips.checkAccountPassword(acc, oldPassword, true, true) == PasswordValidation.PASSWORD_WRONG) {
        	throw new InvalidPasswordException();
        } else {
            PolicyCheckResult check = ips.checkAccountPolicy(acc, newPassword);
            if (! check.isValid()) {
                throw new BadPasswordException(check.getReason());
            }
            ips.storeAndForwardAccountPassword(acc, newPassword, false, null);
            auditaCanviPassword(user, passwordDomain);
        }
    }

    @Override
    protected boolean handleCheckExpiredPassword(String account, String dispatcher) throws Exception {
       	AccountEntity acc = getAccountEntityDao().findByNameAndSystem(account, dispatcher);

       	return getInternalPasswordService().isAccountPasswordExpired(acc);
    }

	@Override
	protected String handleGetDefaultDispatcher() throws Exception
	{
		return getInternalPasswordService().getDefaultDispatcher();
	}

	@Override
	protected String handleGetPolicyDescription (String account, String dispatcher) throws InternalErrorException
	{
       	AccountEntity acc = getAccountEntityDao().findByNameAndSystem(account, dispatcher);

       	if (acc == null)
       		return ""; //$NON-NLS-1$
       	
       	String type = acc.getPasswordPolicy().getName();
       	if (acc.getType().equals(AccountType.USER))
       	{
       		for (UserAccountEntity ua : acc.getUsers()) {
                type = ua.getUser().getUserType().getName();
            }
       	} 
       	
       	PasswordPolicyEntity politica = getPasswordPolicyEntityDao().findByPasswordDomainAndUserType(acc.getSystem().getPasswordDomain().getName(), type);
       	if (politica != null)
       	{
       		return getInternalPasswordService().getPolicyDescription(politica);
       	}
       	else
       		return ""; //$NON-NLS-1$
	}

	@Override
    protected Calendar handleGetPasswordExpiredDate(java.lang.String user, java.lang.String system) throws java.lang.Exception {
       	AccountEntity acc = getAccountEntityDao().findByNameAndSystem(user, system);
       	if (acc == null)
   			throw new InternalErrorException("Account not found "+user+" at "+system);
       	long userId = 0;
   		for (UserAccountEntity ua : acc.getUsers()) {
            userId = ua.getUser().getId();
            break;
        }
   		if (userId==0)
   			throw new InternalErrorException("Account user not found, account "+user+" at "+system);
   		return getInternalPasswordService().getPasswordExpiredDate(userId, acc.getSystem().getPasswordDomain().getId());
    }

}