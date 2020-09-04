package com.soffid.iam.web.application;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Div;
import org.zkoss.zul.Window;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.Domain;
import com.soffid.iam.api.Role;
import com.soffid.iam.service.ejb.UserService;
import com.soffid.iam.web.component.CustomField3;
import com.soffid.iam.web.component.ObjectAttributesDiv;

import es.caib.zkib.component.DataModel;
import es.caib.zkib.component.DataTree2;
import es.caib.zkib.datamodel.DataModelCollection;
import es.caib.zkib.datamodel.DataNode;
import es.caib.zkib.datasource.CommitException;
import es.caib.zkib.datasource.DataSource;
import es.caib.zkib.datasource.XPathUtils;
import es.caib.zkib.zkiblaf.Missatgebox;


public class ApplicationDomainHandler extends Div implements AfterCompose {
	private static final long serialVersionUID = 1L;
	private String listboxPath;
	
	private UserService userService;
	private Role currentRole;
	
	public ApplicationDomainHandler() throws NamingException, CreateException {
		userService = EJBLocator.getUserService();
	}
	
	public void afterCompose() {
		listboxPath = (String) Executions.getCurrent().getArg().get("listbox");
	}
	
	
	public void addNew (Event event) {
		DataTree2 dt = (DataTree2) Path.getComponent(listboxPath);
		DataSource dataSource = (DataSource) dt;
		DataSource rootDs = (DataSource) Path.getComponent(getSpaceOwner(), "/model");
		String name = (String) XPathUtils.getValue (dataSource, "/name");
		
		String path = getListbox().addNew("/domain");
		
		XPathUtils.setValue( rootDs, path+"/@externalCode", name) ;
		
		Window w = (Window) getFellow("add_domain_window");
		w.doHighlighted();
	}
	
	public void addNewValue (Event event) {
		DataTree2 dt = getListbox();

		String name = (String) dt.getJXPathContext().getValue("/name");
		String app = (String) dt.getJXPathContext().getValue("/externalCode");
		
		String path = dt.addNew("/domainValue");
		
		dt.getJXPathContext().setValue("/@externalCodeDomain", app) ;
		dt.getJXPathContext().setValue("/@domainName", name) ;
		
		Window w = getWindowAddValue();
		w.doHighlighted();
		getWindowModify().setVisible(false);
	}

	public void showDetails(Event event) {
		DataNode dn = (DataNode) getListbox().getJXPathContext().getValue("/");
		Object instance = dn.getInstance();
		if (instance instanceof Domain)
			getWindowModify().doHighlighted();
		else
			getWindowModifyValue().doHighlighted();
	}
	
	public void closeDomainDetails(Event event) throws Exception {
		Window w = getWindowModify();
		w.setVisible(false);
		if (event != null)
			event.stopPropagation();
		DataSource dt = (DataSource) Path.getComponent(listboxPath);
		DataModelCollection dn = (DataModelCollection) XPathUtils.getValue(dt, "/domain");
		dn.refresh();
	}
	
	public void closeValueDetails(Event event) throws Exception {
		Window w = getWindowModifyValue();
		w.setVisible(false);
		if (event != null)
			event.stopPropagation();
		DataNode dn = (DataNode) getListbox().getJXPathContext().getValue("/");
		DataModelCollection dnc = dn.getContainer();
		dnc.refresh();
	}

	public void delete(Event event) {
		Missatgebox.confirmaOK_CANCEL(Labels.getLabel("common.delete"), 
				(event2) -> {
					if (event2.getName().equals("onOK")) {
						DataTree2 dt = getListbox();
						dt.delete();
						dt.commit();
						getWindowModify().setVisible(false);
						getWindowModifyValue().setVisible(false);
					}
				});
	}
	
	public void onChangeDomain() {
		CustomField3 name = (CustomField3) getWindowModify().getFellow("name");
		CustomField3 desc = (CustomField3) getWindowModify().getFellow("description");
		if (name.validate() && desc.validate()) {
			getListbox().commit();
			getWindowModify().setVisible(false);
			getListbox().setSelectedIndex(new int[0]);
		}
	}

	public void onChangeValue() {
		CustomField3 name = (CustomField3) getWindowModifyValue().getFellow("name");
		CustomField3 desc = (CustomField3) getWindowModifyValue().getFellow("description");
		if (name.validate() && desc.validate()) {
			getListbox().commit();
			getWindowModifyValue().setVisible(false);
			getListbox().setSelectedIndex(new int[0]);
		}
	}

	public Window getWindowModify() {
		return (Window) getFellow("modify-window");
	}
	
	public Window getWindowModifyValue() {
		return (Window) getFellow("modify-value-window");
	}

	public DataTree2 getListbox() {
		return (DataTree2) getFellow("listbox");
	}

	public Window getWindowAdd() {
		return (Window) getFellow("add_domain_window");
	}

	public Window getWindowAddValue() {
		return (Window) getFellow("add_value_window");
	}

	public void applyAddDomain(Event event) throws CommitException {
		CustomField3 name = (CustomField3) getWindowAdd().getFellow("name");
		CustomField3 desc = (CustomField3) getWindowAdd().getFellow("description");
		if (name.validate() && desc.validate()) {
			getListbox().commit();
			getWindowAdd().setVisible(false);
			getListbox().setSelectedIndex(new int[0]);
		}
	}

	public void backAndRollbackDomain(Event ev) {
		DataTree2 dt = getListbox();
		dt.delete();
		dt.commit();
		getWindowAdd().setVisible(false);
	}

	public void applyAddValue(Event event) throws CommitException {
		CustomField3 name = (CustomField3) getWindowAddValue().getFellow("name");
		CustomField3 desc = (CustomField3) getWindowAddValue().getFellow("description");
		if (name.validate() && desc.validate()) {
			getListbox().commit();
			getListbox().setSelectedIndex(new int[0]);
			getWindowAddValue().setVisible(false);
			getListbox().setSelectedIndex(new int[0]);
		}
	}

	public void backAndRollbackValue(Event ev) {
		DataTree2 dt = getListbox();
		dt.delete();
		dt.commit();
		getListbox().setSelectedIndex(new int[0]);
		getWindowAddValue().setVisible(false);
	}

	
	public String getListboxPath() {
		return listboxPath;
	}

	
	public void setListboxPath(String listboxPath) {
		this.listboxPath = listboxPath;
	}
}
