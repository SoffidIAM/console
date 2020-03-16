package com.soffid.iam.web.component;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.component.DataModel;
import es.caib.zkib.component.DataTable;
import es.caib.zkib.component.Form;
import es.caib.zkib.datamodel.DataNode;
import es.caib.zkib.datamodel.DataNodeCollection;
import es.caib.zkib.datasource.CommitException;
import es.caib.zkib.datasource.DataSource;
import es.caib.zkib.datasource.XPathUtils;
import es.caib.zkib.zkiblaf.Application;
import es.caib.zkib.zkiblaf.Missatgebox;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.apache.commons.beanutils.PropertyUtils;
import org.zkoss.util.resource.Labels;
import org.zkoss.zhtml.Filedownload;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.HtmlBasedComponent;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Fileupload;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import com.soffid.iam.utils.Security;

public class FrameHandler extends Frame {
	public FrameHandler() throws InternalErrorException {
	}

	@Override
	public void setPage(Page p) {
		super.setPage(p);
	}
		
	public void showDetails() {
		getCard().setSclass ( "card is-flipped" );
	}
	
	private HtmlBasedComponent getCard() {
		return (HtmlBasedComponent) getFellow("card");
	}

	public void hideDetails() throws CommitException {
		getModel().commit();
		getCard().setSclass ( "card" );
		getListbox().setSelectedIndex(-1);
	}
	
	protected DataTable getListbox() {
		return (DataTable) getFellow("listbox");
	}

	protected DataModel getModel() {
		return (DataModel) getFellow("model");
	}

	
	protected Form getForm() {
		return (Form) getFellow("form");
	}

	public void addNew() {
		getListbox().addNew();
		showDetails();
	}
	
	public void delete() throws CommitException {
		getListbox().delete();
		hideDetails();
	}

	public void onChangeDades() {
	}
	
	public void apply(Event ev) throws CommitException {
		getModel().commit();
		hideDetails();
	}
	
	public void undo(Event ev) throws CommitException {
		Long id = (Long) getForm().getJXPathContext().getValue("/@id");
		getModel().refresh();
		if (id != null)
		{
			try {
				ListModel model = getListbox().getModel();
				for (int i = 0; i < model.getSize(); i++)
				{
					Object o = ((DataNode)model.getElementAt(i)).getInstance();
					if (id.equals( PropertyUtils.getProperty(o, "id")))
					{
						getListbox().setSelectedIndex(i);
						return;
					}
				}
			} catch (Exception e) {
			}
		}
		hideDetails();
	}

	public void menu(Event ev) {
		String option = (String) ev.getTarget().getAttribute("target");
		Application.setPage("/main/menu.zul?option="+option);
	}
	
	public boolean canClose ()
	{
		boolean result = false;
		if (getModel() != null & getModel().isCommitPending())
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

}
