package com.soffid.iam.web.main;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.metainfo.EventHandler;
import org.zkoss.zk.ui.metainfo.ZScript;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.Application;
import com.soffid.iam.api.User;

public class SearchApplicationHandler extends SearchHandler<Application> {

	public void startSearch(String term) throws Exception {
		super.startSearch(term);
		try {
			list = EJBLocator.getApplicationService().findApplicationByTextAsync(term);
		} catch (Exception e) {}
	}
	
		
	@Override
	protected void addElement(Application next) {
		if (parentDiv.getChildren().isEmpty())
		{
			Label l = new Label( Labels.getLabel("auditoria.zul.Aplicacia"));
			l.setSclass("search-header");
			parentDiv.appendChild(l);
		}
		String s = next.getRelativeName()+" - "+next.getDescription();
		Div d = new Div();
		d.appendChild(new Label(s));
		d.setSclass("search-option");
		d.setAttribute("url", "/resource/application/application.zul?name="+next.getName());
		d.addEventHandler("onClick",
			new EventHandler(ZScript.parseContent("ref:searchBox.onNavigate"), null));
		parentDiv.appendChild(d);
	}


	@Override
	protected void addMore() {
		Div d = new Div();
		d.setSclass("search-more");
		d.appendChild(new Label("..."));
		parentDiv.appendChild(d);
	}

}
