package com.soffid.iam.web.wheel;

import java.util.HashMap;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Window;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.bpm.api.ProcessDefinition;

import es.caib.zkib.zkiblaf.Missatgebox;

public class Iga04Sector extends Sector {

	public Iga04Sector(String tag) {
		super(tag);
	}

	@Override
	public boolean isDone() {
		try {
			List<ProcessDefinition> list = EJBLocator.getBpmEngine().findAllProcessDefinitions(false);
			return list.size() > 3;
		}
		catch (Exception e) {
			return true;
		}
	}

	protected void activate() {
		try {
			if (new InitialContext().lookup("openejb:/local/soffid.ejb.com.soffid.iam.addons.bpm.core.BpmEditorService") == null) {
				Missatgebox.avis(Labels.getLabel("wheel.missingBpmAddon"), (ev) -> {
					Executions.getCurrent().sendRedirect("https://download.soffid.com/download/", "_blank");
				});
			}
			else
				Executions.getCurrent().sendRedirect("/addon/bpm/editor-v3.zul?wizard=new", "_blank");
		} catch (NamingException e) {
		}
	}

}
