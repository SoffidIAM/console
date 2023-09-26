package com.soffid.iam.web.account;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.Account;
import com.soffid.iam.service.ejb.AccountService;
import com.soffid.iam.utils.Security;
import com.soffid.iam.web.component.InputField3;
import com.soffid.iam.web.component.InputFieldContainer;
import com.soffid.iam.web.component.InputFieldUIHandler;
import com.soffid.iam.web.component.ObjectAttributesDiv;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.datasource.XPathUtils;

public class LoginNameHandler extends InputFieldUIHandler {
	@Override
	public void onChange(InputField3 field) throws Exception {
		InputFieldContainer d = field.getObjectContainer();
		if (d != null) {
			String system = (String) XPathUtils.eval(field, "system");			
			String ssoSystem = com.soffid.iam.utils.ConfigurationCache.getProperty("AutoSSOSystem"); //$NON-NLS-1$
			if (ssoSystem != null && ssoSystem.equals(system)) {
				Long id = (Long) XPathUtils.eval(field, "id");
				if (id == null) {
					InputField3 desc = d.getInputFieldsMap().get("name");
					if (Security.isUserInRole("account:query")) {
						long l = findLastAccount(ssoSystem) + 1;
						desc.setValue(""+l);
					} else {
						desc.setValue("?");
					}
					desc.invalidate();
				}
			}
		}
	}

	@Override
	public boolean isVisible(InputField3 field) throws Exception {
		return true;
	}
	
	/**
	 * 
	 * @param system
	 * @return
	 * @throws InternalErrorException 
	 * @throws CreateException 
	 * @throws NamingException 
	 */
	private long findLastAccount (String system) throws InternalErrorException, NamingException, CreateException
	{
		long bits = 0;
		long top = 0;
		long attempt = 1;
		/**
		 * Find radix the first account with number = 2 ^ radix
		 */
		AccountService accountService = EJBLocator.getAccountService();
		do
		{
			Account acc = accountService.findAccount(""+attempt, system);
			if (acc == null) break;
			top = attempt;
			attempt = attempt + attempt;
			bits ++ ;
		} while (true);
		/**
		 * Now look for the other bits
		 * top exists
		 * attempt does not exist
		 */
		long step = top;
		while (bits > 1)
		{
			step = step / 2;
			attempt = top + step;
			Account acc = accountService.findAccount(""+attempt, system);
			if (acc != null) top = attempt;
			bits --;
		}
		return top;
	}


}
