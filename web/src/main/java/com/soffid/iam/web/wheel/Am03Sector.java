package com.soffid.iam.web.wheel;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Executions;

import es.caib.zkib.zkiblaf.Missatgebox;

public class Am03Sector extends Sector {

	private Object actualSector;

	public Am03Sector(String tag) {
		super(tag);
		try {
			Class<?> actualClass = Class.forName("com.soffid.iam.web.wheel.ActualAm03Sector");
			actualSector = actualClass.newInstance();
		} catch (Throwable e) {
			actualSector = null;
		}
	}

	@Override
	public boolean isDone() {
		try {
			if (actualSector == null)
				return true;
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
			Missatgebox.avis(Labels.getLabel("wheel.missingFederationOtpAddon"), (ev) -> {
				Executions.getCurrent().sendRedirect("https://download.soffid.com/download/", "_blank");
			});
		else
			super.activate();
	}

}
