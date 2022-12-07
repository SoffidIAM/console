package com.soffid.iam.web.wheel;

import java.util.Collection;
import java.util.HashMap;

import javax.naming.InitialContext;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Window;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.PagedResult;
import com.soffid.iam.api.Server;
import com.soffid.iam.api.System;
import com.soffid.iam.api.User;

import es.caib.zkib.zkiblaf.Missatgebox;

public class Am02Sector extends Sector {

	private Object actualSector;

	public Am02Sector(String tag) {
		super(tag);
		try {
			Class<?> actualClass = Class.forName("com.soffid.iam.web.wheel.ActualAm02Sector");
			actualSector = actualClass.newInstance();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			actualSector = null;
		}
	}

	@Override
	public boolean isDone() {
		try {
			if (actualSector == null)
				return false;
			else
				return ((Boolean)actualSector.getClass().getMethod("isDone").invoke(actualSector)).booleanValue();
		}
		catch (Exception e) {
			return true;
		}
	}

	@Override
	protected void activate() {
		if (actualSector == null)
			Missatgebox.avis(Labels.getLabel("wheel.missingFederationAddon"), (ev) -> {
				Executions.getCurrent().sendRedirect("https://download.soffid.com/download/", "_blank");
			});
		else
			super.activate();
	}


}
