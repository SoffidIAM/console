package com.soffid.iam.web.component;

import java.io.IOException;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.au.out.AuScript;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.HtmlBasedComponent;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.MouseEvent;
import org.zkoss.zk.ui.metainfo.EventHandler;
import org.zkoss.zk.ui.metainfo.ZScript;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.Messagebox;

import com.soffid.iam.web.menu.MenuOption;
import com.soffid.iam.web.menu.MenuParser;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.binder.BindContext;
import es.caib.zkib.component.DataModel;
import es.caib.zkib.component.DataTable;
import es.caib.zkib.component.DataTree2;
import es.caib.zkib.component.Form;
import es.caib.zkib.datamodel.DataNode;
import es.caib.zkib.datasource.CommitException;
import es.caib.zkib.datasource.DataSource;
import es.caib.zkib.datasource.XPathUtils;
import es.caib.zkib.zkiblaf.Application;
import es.caib.zkib.zkiblaf.Missatgebox;

public class FrameHandler extends Frame {
	String menu = "console.yaml";

	public FrameHandler() throws InternalErrorException {
		setMold("div");
		setStyle("position:relative");
		setSaveContent(true);
		setId("frame");
	}

	@Override
	public void afterCompose() {
		if (!Executions.getCurrent().isExplorer())
		{
			response("setUrl", 
					new AuScript(this, 
							String.format("try {"
									+ "window.history.pushState(\"%s\", \"%s\", window.location.protocol+\"//\"+window.location.host+\"%s\");"
									+ "} catch (e) {console.log(e);}",
									getPage().getRequestPath(), 
									getPage().getTitle(), 
									getPage().getRequestPath())));
			
		}
		MenuParser menuParser = new MenuParser();
		List<MenuOption> options;
		try {
			options = menuParser.getMenus(menu);
			MenuOption option = menuParser.findMenuOption(options, getPage());
			if (option != null)
			{
				setTitle(Labels.getLabel(option.getLabel()));
			} else {
				setTitle ("");
			}
		} catch (IOException e) {
		}

		Application.registerPage(this);
		super.afterCompose();
	}


	@Override
	public void setPage(Page p) {
		super.setPage(p);
	}
		
	public void showDetails() {
		getCard().setSclass ( "card is-flipped" );
	}
	
	protected HtmlBasedComponent getCard() {
		return (HtmlBasedComponent) getFellow("card");
	}

	public void hideDetails() throws CommitException {
		getModel().commit();
		getCard().setSclass ( "card" );
		Component lb = getListbox();
		if (lb instanceof DataTable)
			((DataTable) lb).setSelectedIndex(-1);
		if (lb instanceof DataTree2)
			((DataTree2) lb).setSelectedIndex(new int[0]);
	}
	
	protected Component getListbox() {
		return getFellow("listbox");
	}

	protected DataModel getModel() {
		return (DataModel) getFellowIfAny("model");
	}

	protected BindContext getForm() {
		return (BindContext) getFellow("form");
	}

	public void addNew() throws Exception {
		Component lb = getListbox();
		if (lb instanceof DataTable)
		{
			((DataTable) lb).addNew();
			showDetails();
		}
	}
	
	public void delete() throws CommitException {
		Component lb = getListbox();
		if (lb instanceof DataTable)
			((DataTable) lb).delete();
		if (lb instanceof DataTree2)
			((DataTree2) lb).delete();
		hideDetails();
	}

	public void onChangeForm(Event ev) {
	}
	
	public void apply(Event ev) throws CommitException {
		getModel().commit();
		hideDetails();
	}
	
	public void undo(Event ev) throws CommitException {
		Component lb = getListbox();
		Long id = (Long) XPathUtils.getValue( getForm(), "/@id");
		int pos[] = null;
		if (lb instanceof DataTree2)
			pos = ((DataTree2) lb).getSelectedItem();
		getModel().refresh();
		if (id != null && lb instanceof DataTable)
		{
			try {
				ListModel model = ((DataTable) lb).getModel();
				for (int i = 0; i < model.getSize(); i++)
				{
					Object o = ((DataNode)model.getElementAt(i)).getInstance();
					if (id.equals( PropertyUtils.getProperty(o, "id")))
					{
						((DataTable) lb).setSelectedIndex(i);
						return;
					}
				}
			} catch (Exception e) {
			}
		}
		if (pos != null && lb instanceof DataTree2)
		{
			((DataTree2)lb).setSelectedIndex(pos);
		}
		hideDetails();
	}

	public void menu(Event ev) {
		String option = (String) ev.getTarget().getAttribute("target");
		if (ev instanceof MouseEvent &&
				((MouseEvent) ev).getKeys() == MouseEvent.CTRL_KEY )
			Executions.getCurrent().sendRedirect("/main/menu.zul?option="+option, "_blank");
		else
			Application.setPage("/main/menu.zul?option="+option);
	}
	
	public boolean canClose ()
	{
		boolean result = false;
		if (getModel() != null && getModel().isCommitPending())
		{
			try
			{
				result = Missatgebox.confirmaYES_NO(Labels.getLabel("task.msgDeseaSalir"), //$NON-NLS-1$
						Labels.getLabel("task.titleDeseaSalir"), //$NON-NLS-1$
						Messagebox.QUESTION);

				if (result)
				{
					Component c = getModel();
					if (c != null && c instanceof DataModel)
					{
						DataModel dm  =(DataModel) c;
						dm.refresh();
					}
				}
				return result;
			}
			catch (Exception ex)
			{
				return true;
			}
		}
		else
		{
			return true;
		}
	}

	
	public void closeFrame() throws IOException {
		Page p = getPage();
		String path = p.getRequestPath();
		
		List<MenuOption> options;
		MenuParser menuParser = new MenuParser();
		options = menuParser.parse(menu);
		MenuOption o = menuParser.findMenu(options, path);
		if (o != null)	
			Application.setPage(o.getUrl());

	}
	
	public void applyNoClose(Event event) throws CommitException {
		getModel().commit();
	}
}
