package com.soffid.iam.web.wheel;

import java.util.Collection;
import java.util.HashMap;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Window;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.PagedResult;
import com.soffid.iam.api.Server;
import com.soffid.iam.api.User;

import es.caib.zkib.zkiblaf.Missatgebox;

public class LoadUsersSector extends Sector {

	public LoadUsersSector(String tag) {
		super(tag);
	}

	@Override
	public boolean isDone() {
		try {
			PagedResult<User> list = EJBLocator.getUserService().findUserByJsonQuery(null, null, 2);
			return list.getResources().size() > 1;
		}
		catch (Exception e) {
			return true;
		}
	}


}
