package com.soffid.iam.web.wheel;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.util.resource.Labels;

import com.soffid.iam.EJBLocator;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.zkiblaf.Application;
import es.caib.zkib.zkiblaf.Missatgebox;

public class Irc01Sector extends Sector {

	public Irc01Sector(String tag) {
		super(tag);
	}

	@Override
	public boolean isDone() {
		try {
			return ! EJBLocator.getSoDRuleService().findSodRuleByJsonQuery(null, null, null).isEmpty();
		} catch (InternalErrorException | NamingException | CreateException e) {
			return true;
		}
	}

	@Override
	protected void activate() {
		Missatgebox.avis(Labels.getLabel("wizard-sod.explanation"), e -> {
			Application.jumpTo("/resource/application/sod.zul?wizard=add");
		});
	}

}
