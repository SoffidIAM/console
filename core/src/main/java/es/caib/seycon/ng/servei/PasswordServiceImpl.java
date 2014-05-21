// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.caib.seycon.ng.servei;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Iterator;

import org.apache.webdav.lib.properties.GetContentLengthProperty;
import org.springframework.dao.InvalidDataAccessResourceUsageException;

import es.caib.seycon.ng.comu.AccountType;
import es.caib.seycon.ng.comu.Auditoria;
import es.caib.seycon.ng.comu.Password;
import es.caib.seycon.ng.comu.PasswordValidation;
import es.caib.seycon.ng.comu.PolicyCheckResult;
import es.caib.seycon.ng.exception.BadPasswordException;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.exception.InvalidPasswordException;
import es.caib.seycon.ng.exception.UnknownUserException;
import es.caib.seycon.ng.model.AccountEntity;
import es.caib.seycon.ng.model.AuditoriaEntity;
import es.caib.seycon.ng.model.DispatcherEntity;
import es.caib.seycon.ng.model.DominiContrasenyaEntity;
import es.caib.seycon.ng.model.DominiUsuariEntityDao;
import es.caib.seycon.ng.model.ParaulaProhibidaPoliticaContrasenyaEntity;
import es.caib.seycon.ng.model.PoliticaContrasenyaEntity;
import es.caib.seycon.ng.model.PoliticaContrasenyaEntityDao;
import es.caib.seycon.ng.model.UserAccountEntity;
import es.caib.seycon.ng.model.UsuariEntity;
import es.caib.seycon.ng.utils.Security;

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
       	AccountEntity acc = getAccountEntityDao().findByNameAndDispatcher(user, passwordDomain);
        return getInternalPasswordService().checkAccountPolicy(acc, password);
    }

    /**
     * @see es.caib.seycon.ng.servei.PasswordService#checkPassword(java.lang.String, java.lang.String, es.caib.seycon.ng.comu.Password, boolean, boolean)
     */
    protected boolean handleCheckPassword(java.lang.String user, java.lang.String passwordDomain, 
            es.caib.seycon.ng.comu.Password password, boolean checkTrusted, boolean checkExpired)
        throws java.lang.Exception
    {
       	AccountEntity acc = getAccountEntityDao().findByNameAndDispatcher(user, passwordDomain);

       	return getInternalPasswordService().checkAccountPassword(acc, password, checkTrusted, checkExpired) != PasswordValidation.PASSWORD_WRONG;
    }

    /**
     * @see es.caib.seycon.ng.servei.PasswordService#checkPin(java.lang.String, java.lang.String)
     */
    protected boolean handleCheckPin(java.lang.String user, java.lang.String pin)
        throws java.lang.Exception
    {
        UsuariEntity ue = getUsuariEntityDao().findByCodi(user);
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

		AuditoriaEntity auditoriaEntity = getAuditoriaEntityDao()
				.auditoriaToEntity(auditoria);
		getAuditoriaEntityDao().create(auditoriaEntity);
	}


	@Override
    protected void handleChangePassword(String user, String passwordDomain,
            Password oldPassword, Password newPassword) throws Exception {
       	AccountEntity acc = getAccountEntityDao().findByNameAndDispatcher(user, passwordDomain);
 
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
       	AccountEntity acc = getAccountEntityDao().findByNameAndDispatcher(account, dispatcher);

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
       	AccountEntity acc = getAccountEntityDao().findByNameAndDispatcher(account, dispatcher);

       	if (acc == null)
       		return ""; //$NON-NLS-1$
       	
       	String type = acc.getPasswordPolicy().getCodi();
       	if (acc.getType().equals(AccountType.USER))
       	{
       		for (UserAccountEntity ua: acc.getUsers())
       		{
       			type = ua.getUser().getTipusUsuari().getCodi();
       		}
       	} 
       	
       	PoliticaContrasenyaEntity politica = getPoliticaContrasenyaEntityDao().findByDominiContrasenyaTipusUsuari(
       					acc.getDispatcher().getDomini().getCodi(), 
       					type);
       	if (politica != null)
       	{
       		return getInternalPasswordService().getPolicyDescription(politica);
       	}
       	else
       		return ""; //$NON-NLS-1$
	}
}