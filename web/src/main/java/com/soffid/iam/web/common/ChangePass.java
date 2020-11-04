package com.soffid.iam.web.common;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.service.ejb.PasswordService;

import es.caib.seycon.ng.ServiceLocator;
import es.caib.seycon.ng.comu.Password;
import es.caib.seycon.ng.exception.BadPasswordException;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.exception.InvalidPasswordException;

public class ChangePass {

	public void changePassword(String user, String oldPass, String newPass) 
			throws InternalErrorException, BadPasswordException, InvalidPasswordException, NamingException, CreateException 
	{
		PasswordService passwordService = EJBLocator.getPasswordService();
		String dominiPasswordsPerDefecte = passwordService.getDefaultDispatcher();
		passwordService.changePassword(user, dominiPasswordsPerDefecte, new Password(oldPass), new Password(newPass));

	}
}
