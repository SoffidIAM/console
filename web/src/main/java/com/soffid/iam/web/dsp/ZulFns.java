package com.soffid.iam.web.dsp;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.xel.fn.CommonFns;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.Account;
import com.soffid.iam.api.Host;
import com.soffid.iam.utils.ConfigurationCache;
import com.soffid.iam.utils.Security;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.component.DateFormats;
import es.caib.zkib.datamodel.DataModelCollection;
import es.caib.zkib.datamodel.DataNode;
import es.caib.zkib.datamodel.DataNodeCollection;

public class ZulFns {
	protected ZulFns() {}

	/** Returns the column attribute of a child of a row by specifying
	 * the index.
	 */
	public static final boolean isUserInRole(String role) {
		return Security.isUserInRole(role);
	}

	public static final boolean isHistoryEnabled() {
		return ConfigurationCache.isHistoryEnabled();
	}

	public static final boolean isUpdatePending(Account account) {
		try {
			return EJBLocator.getAccountService().isUpdatePending(account);
		} catch (InternalErrorException | NamingException | CreateException e) {
			return false;
		}
	}

	public static final String getStatusIcon(Account account) {
		try {
			if (account.getId() == null)
				return "/img/sync.svg";
			int i = EJBLocator.getAccountService().isUpdatePendingExtended(account);
			return i == 1 ? "/img/held-green.svg" :
				i == 2 ? "/img/sync.svg":
				i == 3 ? "/img/warning.svg":
					"/img/account-green.svg";
		} catch (Exception e) {
			return "/img/account-green.svg";
		}
	}

	public static final String getDateFormat() {
		return DateFormats.getDateFormatString();
	}

	public static final String getDateTimeFormat() {
		return DateFormats.getDateTimeFormatString();
	}


	public static final String getHostIcon(DataNode node) {
		Host h = (Host) node.getInstance();
		if (h == null)
			return "/img/question.svg";
		try {
			if (! EJBLocator.getNetworkDiscoveryService().findHostSystems(h).isEmpty())
				return "NTS".equals(h.getOs()) ? "/img/windows.svg" : "LIN".equals(h.getOs()) ? "/img/linux.svg" : "/img/host.svg";
		} catch (Exception e) {
		}
		return "NTS".equals(h.getOs()) ? "/img/windows-black.svg" : "LIN".equals(h.getOs()) ? "/img/linux-black.svg" : "/img/host-black.svg";
	}
}
