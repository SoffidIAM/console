package com.soffid.iam.web.wheel;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.UiException;

import es.caib.zkib.zkiblaf.Missatgebox;

public class Irc03Sector extends Sector {

	private Object actualSector;

	public Irc03Sector(String tag) {
		super(tag);
		try {
			Class<?> actualClass = Class.forName("com.soffid.iam.web.wheel.ActualIrc03Sector");
			actualSector = actualClass.newInstance();
		} catch (Throwable e) {
			actualSector = null;
		}
	}

	@Override
	public boolean isDone() {
		try {
			if (actualSector == null) {
				dim(getHandler());
				return false;
			}
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
			Missatgebox.avis(Labels.getLabel("wheel.missingRecertificationAddon"), (ev) -> {
				Executions.getCurrent().sendRedirect("https://download.soffid.com/download/", "_blank");
			});
		else {
			try {
				actualSector.getClass().getMethod("activate").invoke(actualSector);
			} catch (Exception e) {
				throw new UiException(e);
			}
		}
	}

}
