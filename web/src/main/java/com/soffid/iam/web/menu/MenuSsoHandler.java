package com.soffid.iam.web.menu;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Div;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.AccessTree;
import com.soffid.iam.web.component.CustomField3;

import es.caib.zkib.component.DataDiv;
import es.caib.zkib.component.DataTree2;
import es.caib.zkib.datamodel.DataNode;
import es.caib.zkib.datasource.XPathUtils;
import es.caib.zkib.jxpath.ri.compiler.Path;
import es.caib.zkib.zkiblaf.Missatgebox;


public class MenuSsoHandler extends Div implements AfterCompose {
	private static final long serialVersionUID = 1L;
	private String listboxPath;
	
	public MenuSsoHandler() throws NamingException, CreateException {
	}

	
	public String getListboxPath() {
		return listboxPath;
	}

	
	public void setListboxPath(String listboxPath) {
		this.listboxPath = listboxPath;
	}


	@Override
	public void afterCompose() {
		// TODO Auto-generated method stub
		
	}
	
	
	public void validaXML() {
		DataTree2 listbox = (DataTree2) org.zkoss.zk.ui.Path.getComponent(getSpaceOwner(), listboxPath);
		DataNode dn = (DataNode) XPathUtils.getValue( listbox , "/.");
		AccessTree data = (AccessTree) dn.getInstance();
		try {		
			String errorVal = EJBLocator.getEntryPointService().validateXMLApplicationAccess(data);
			Missatgebox.info (!"".equals(errorVal) ? errorVal : org.zkoss.util.resource.Labels.getLabel("aplicacionsIntranet.Correcte"));
		} catch (Throwable th) {
			Missatgebox.error (String.format(org.zkoss.util.resource.Labels.getLabel("aplicacionsIntranet.ErrorValidacio"), 
											new Object [] {th.getMessage()}));
		} 
		
	}
}
