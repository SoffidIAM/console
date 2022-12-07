package com.soffid.iam.web.wheel;

import java.util.Collection;
import java.util.HashMap;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Window;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.PagedResult;
import com.soffid.iam.api.Server;
import com.soffid.iam.api.System;
import com.soffid.iam.api.User;

import es.caib.zkib.zkiblaf.Missatgebox;

public class Iga03Sector extends Sector {

	public Iga03Sector(String tag) {
		super(tag);
	}

	@Override
	public boolean isDone() {
		try {
			Collection<System> list = EJBLocator.getDispatcherService().findAllActiveDispatchers();
			int num = 0;
			for (System s: list) {
				if (!s.isAuthoritative())
					num ++;
				if (num > 1) return true;
			}
			return false;
		}
		catch (Exception e) {
			return true;
		}
	}


}
