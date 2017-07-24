package es.caib.loginModule;

import es.caib.seycon.ng.ServiceLocator;
import es.caib.seycon.ng.comu.Password;
import es.caib.seycon.ng.exception.BadPasswordException;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.exception.InvalidPasswordException;
import es.caib.seycon.ng.servei.PasswordService;

public class ChangePass {

	public void changePassword(String user, String oldPass, String newPass) 
			throws InternalErrorException, BadPasswordException, InvalidPasswordException 
	{

		PasswordService passwordService = ServiceLocator.instance().getPasswordService();
		String dominiPasswordsPerDefecte = passwordService.getDefaultDispatcher();
		passwordService.changePassword(user, dominiPasswordsPerDefecte, new Password(oldPass), new Password(newPass));

	}
}
