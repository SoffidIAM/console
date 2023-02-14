package com.soffid.iam.web.wheel;

import java.util.Collection;
import java.util.HashMap;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Window;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.Network;
import com.soffid.iam.api.PagedResult;
import com.soffid.iam.api.Server;
import com.soffid.iam.api.System;
import com.soffid.iam.api.User;

import es.caib.zkib.zkiblaf.Missatgebox;

public class Pam01Sector extends Sector {

	public Pam01Sector(String tag) {
		super(tag);
	}

	@Override
	public boolean isDone() {
		try {
			PagedResult<Network> list = EJBLocator.getNetworkService().findNetworkByTextAndJsonQuery(null, null, null, null);
			int num = 0;
			for (Network s: list.getResources()) {
				if (Boolean.TRUE.equals(s.getDiscovery()))
					return true;
			}
			return false;
		}
		catch (Exception e) {
			return false;
		}
	}

	@Override
	protected void activate() {
		Executions.getCurrent().sendRedirect("/resource/network/discovery.zul?wizard=new", "_blank");
	}


}
