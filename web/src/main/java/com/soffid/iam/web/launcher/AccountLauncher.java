package com.soffid.iam.web.launcher;

import java.io.UnsupportedEncodingException;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import com.soffid.iam.web.menu.DynamicLauncher;
import com.soffid.iam.web.menu.MenuOption;
import com.soffid.iam.web.vault.LaunchHelper;

import es.caib.seycon.ng.exception.InternalErrorException;

public class AccountLauncher implements DynamicLauncher {

	@Override
	public void launch(MenuOption option) throws UnsupportedEncodingException, InternalErrorException, NamingException, CreateException {
		new LaunchHelper().launchAccount(option.getAccount());
	}

}
