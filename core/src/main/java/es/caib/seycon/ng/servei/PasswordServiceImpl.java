// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.caib.seycon.ng.servei;

import com.soffid.iam.model.AccountEntity;
import com.soffid.iam.model.AuditEntity;
import com.soffid.iam.model.PasswordPolicyEntity;
import com.soffid.iam.model.UserAccountEntity;
import com.soffid.iam.model.UserEntity;
import es.caib.seycon.ng.comu.AccountType;
import es.caib.seycon.ng.comu.Auditoria;
import es.caib.seycon.ng.comu.Password;
import es.caib.seycon.ng.comu.PasswordValidation;
import es.caib.seycon.ng.comu.PolicyCheckResult;
import es.caib.seycon.ng.exception.BadPasswordException;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.exception.InvalidPasswordException;
import es.caib.seycon.ng.exception.UnknownUserException;
import es.caib.seycon.ng.utils.Security;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Iterator;
import org.apache.webdav.lib.properties.GetContentLengthProperty;
import org.springframework.dao.InvalidDataAccessResourceUsageException;

/**
 * @see es.caib.seycon.ng.servei.PasswordService
 */
public class PasswordServiceImpl
    extends es.caib.seycon.ng.servei.PasswordServiceBase
{

    /**
     * @see es.caib.seycon.ng.servei.PasswordService#checkPolicy(java.lang.String, java.lang.String, es.caib.seycon.ng.comu.Password)
     */
    @SuppressWarnings("rawtypes")
    protected PolicyCheckResult handleCheckPolicy(java.lang.String user, java.lang.String passwordDomain, es.caib.seycon.ng.comu.Password password)
        throws java.lang.Exception
    {
       	AccountEntity acc = getAccountEntityDao().findByNameAndSystem(user, passwordDomain);
       	if (acc == null)
       		return null;
       	else
       		return getInternalPasswordService().checkAccountPolicy(acc, password);
    }

    /**
     * @see es.caib.seycon.ng.servei.PasswordService#checkPassword(java.lang.String, java.lang.String, es.caib.seycon.ng.comu.Password, boolean, boolean)
     */
    protected boolean handleCheckPassword(java.lang.String user, java.lang.String passwordDomain, 
            es.caib.seycon.ng.comu.Password password, boolean checkTrusted, boolean checkExpired)
        throws java.lang.Exception
    {
       	AccountEntity acc = getAccountEntityDao().findByNameAndSystem(user, passwordDomain);

       	return getInternalPasswordService().checkAccountPassword(acc, password, checkTrusted, checkExpired) != PasswordValidation.PASSWORD_WRONG;
    }

    /**
     * @see es.caib.seycon.ng.servei.PasswordService#checkPin(java.lang.String, java.lang.String)
     */
    protected boolean handleCheckPin(java.lang.String user, java.lang.String pin)
        throws java.lang.Exception
    {
        UserEntity ue = getUserEntityDao().findByCode(user);
        if (ue == null)
            throw new InternalErrorException(String.format(Messages.getString("PasswordServiceImpl.5"), user)); //$NON-NLS-1$
        return getInternalPasswordService().checkPin(ue, pin);
    }

	private void auditaCanviPassword(String codiUsuariAuditat, String codiDominiContrasenyes) {
		String codiUsuari = Security.getCurrentAccount();
		Auditoria auditoria = new Auditoria();
		auditoria.setAccio("p"); //$NON-NLS-1$
		auditoria.setUsuari(codiUsuariAuditat);
		auditoria.setAutor(codiUsuari);
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"dd/MM/yyyy kk:mm:ss"); //$NON-NLS-1$
		auditoria.setData(dateFormat.format(GregorianCalendar.getInstance()
				.getTime()));
		auditoria.setObjecte("SC_USUARI"); //$NON-NLS-1$
		auditoria.setPasswordDomain(codiDominiContrasenyes);

		AuditEntity auditoriaEntity = getAuditEntityDao().auditoriaToEntity(auditoria);
		getAuditEntityDao().create(auditoriaEntity);
	}


	@Override
    protected void handleChangePassword(String user, String passwordDomain,
            Password oldPassword, Password newPassword) throws Exception {
       	AccountEntity acc = getAccountEntityDao().findByNameAndSystem(user, passwordDomain);
 
       	InternalPasswordService ips = getInternalPasswordService();
        
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
    protected boolean handleCheckPasswordExpired(String account, String dispatcher)
            throws Exception {
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
       	
       	String type = acc.getPasswordPolicy().getCode();
       	if (acc.getType().equals(AccountType.USER))
       	{
       		for (UserAccountEntity ua : acc.getUsers()) {
                type = ua.getUser().getUserType().getCode();
            }
       	} 
       	
       	PasswordPolicyEntity politica = getPasswordPolicyEntityDao().findByPasswordDomainAndUserType(acc.getSystem().getDomain().getCode(), type);
       	if (politica != null)
       	{
       		return getInternalPasswordService().getPolicyDescription(politica);
       	}
       	else
       		return ""; //$NON-NLS-1$
	}
}