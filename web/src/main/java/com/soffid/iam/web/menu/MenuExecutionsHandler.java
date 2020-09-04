package com.soffid.iam.web.menu;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;

import com.soffid.iam.api.AccessTree;
import com.soffid.iam.api.AccessTreeExecution;
import com.soffid.iam.web.component.CustomField3;
import com.soffid.iam.web.vault.LaunchHelper;

import es.caib.zkib.component.DataDiv;
import es.caib.zkib.datamodel.DataNode;
import es.caib.zkib.datasource.DataSource;
import es.caib.zkib.datasource.XPathUtils;


public class MenuExecutionsHandler extends Div implements AfterCompose {
	private static final long serialVersionUID = 1L;
	private String listboxPath;
	
	public MenuExecutionsHandler() throws NamingException, CreateException {
	}

	
	public String getListboxPath() {
		return listboxPath;
	}

	
	public void setListboxPath(String listboxPath) {
		this.listboxPath = listboxPath;
	}


	@Override
	public void afterCompose() {
	}
	
	public void enable(Event event) {
		CustomField3 cf = (CustomField3) event.getTarget();
		boolean enabled = Boolean.TRUE.equals( XPathUtils.getValue(cf, "enabled"));
		CustomField3 type = (CustomField3) cf.getNextSibling();
		CustomField3 content = (CustomField3) type.getNextSibling();
		CustomField3 explanation = (CustomField3) content.getNextSibling();
		type.setVisible( enabled );
		content.setVisible( enabled );
		explanation.setVisible( enabled );
		Button test = (Button) explanation.getNextSibling();
		test.setVisible( enabled );
	}
	
	public void initMethod(Event event) {
		Component dd = (Component) event.getData();
		CustomField3 cf = (CustomField3) dd.getChildren().get(1); 
		boolean enabled = Boolean.TRUE.equals( XPathUtils.getValue(cf, "enabled"));
		CustomField3 type = (CustomField3) cf.getNextSibling();
		CustomField3 content = (CustomField3) type.getNextSibling();
		CustomField3 explanation = (CustomField3) content.getNextSibling();
		type.setVisible( enabled );
		content.setVisible( enabled );
		explanation.setVisible( enabled );
		Button test = (Button) explanation.getNextSibling();
		test.setVisible( enabled );
	}
	
	public void test(Event event) throws Exception {
		DataSource tree = (DataSource) Path.getComponent(getPage(), listboxPath);
		tree.commit();
		
		AccessTree at = (AccessTree) ((DataNode)XPathUtils.getValue( tree, "/.")).getInstance();
		WebAccessTreeExecution exec = (WebAccessTreeExecution) ((DataNode)XPathUtils.getValue( event.getTarget(), "/.")).getInstance();
		
		new LaunchHelper().launchAccessTree(at, exec.getExec());
	}
}
