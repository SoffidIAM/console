package com.soffid.iam.web.account;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.metainfo.ZScript;

import com.soffid.iam.api.Account;
import com.soffid.iam.api.LaunchType;
import com.soffid.iam.web.component.InputField3;
import com.soffid.iam.web.component.InputFieldUIHandler;

import es.caib.seycon.ng.comu.AccountType;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.datasource.CommitException;
import es.caib.zkib.datasource.XPathUtils;
import es.caib.zkib.jxpath.JXPathNotFoundException;

public class SshPublicKeyHandler extends InputFieldUIHandler {
	@Override
	public boolean isVisible(InputField3 field) throws Exception {
		try 
		{
			Object o = XPathUtils.eval(field, ".");
			if (o instanceof Account) {
				Account acc = (Account) o;
				if (acc.getType() == AccountType.USER) return false;
				String ssoSystem = com.soffid.iam.utils.ConfigurationCache.getProperty("AutoSSOSystem"); //$NON-NLS-1$
				if (acc.getSystem().equals(ssoSystem) && "Linux".equals(acc.getServerType()) ||
					acc.getLaunchType() == LaunchType.LAUNCH_TYPE_PAM && 
						acc.getLoginUrl() != null && 
						acc.getLoginUrl().startsWith("ssh:")){
					return true;
				}
			}
			return false;
		} catch (JXPathNotFoundException e) {
			return false;
		}
	}

	@Override
	public void beforeCreate(InputField3 field) throws Exception {
		Component frame = field.getFellowIfAny("frame");
		if (frame instanceof VaultHandler) {
			final VaultHandler h = (VaultHandler) frame;
			field.setForceSelectIcon(true);
			field.setSelectIcon("/img/restart.svg");
			field.setSelectIcon2("/img/restart-white.svg");
		}
	}
	
	@Override
	public boolean openSelectWindow(InputField3 field) throws Exception {
		Component frame = field.getFellowIfAny("frame");
		if (frame instanceof VaultHandler) {
			final VaultHandler h = (VaultHandler) frame;
			h.setSshKey(null);
		}
		return true;
	}

	@Override
	public boolean validate(InputField3 field) throws Exception {
		return true;
	}

}
