package com.soffid.iam.web.menu;

import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.event.Event;

import com.soffid.iam.api.AccessTree;
import com.soffid.iam.web.component.CustomField3;
import com.soffid.iam.web.component.FrameHandler;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.component.DataTree2;
import es.caib.zkib.datasource.XPathUtils;
import es.caib.zkib.jxpath.JXPathNotFoundException;

public class MenuEditorHandler extends FrameHandler {
	public MenuEditorHandler() throws InternalErrorException {
		com.soffid.iam.api.Tenant masterTenant = com.soffid.iam.ServiceLocator.instance().getTenantService().getMasterTenant();

	}

	@Override
	public void setPage(Page p) {
		super.setPage(p);
	}
		

	public void onChangeForm(Event ev) throws Exception {
		super.onChangeForm(ev);
		try {
			Long id = (Long) XPathUtils.getValue(getForm(), "id");
			CustomField3 cf = (CustomField3) getFellow("menu");
			cf.setReadonly(id != null);
			
			Boolean menu = (Boolean) XPathUtils.getValue(getForm(), "menu");
			getFellow("p_execucions").setVisible( ! Boolean.TRUE.equals(menu)); 
			getFellow("p_xml").setVisible( ! Boolean.TRUE.equals(menu)); 
			getFellow("tp_executions").setVisible( ! Boolean.TRUE.equals(menu)); 
			getFellow("tp_xml").setVisible( ! Boolean.TRUE.equals(menu)); 
			onChangeMenu(ev);
		} catch (JXPathNotFoundException e) {
			
		}
	}
	
	public void onChangeMenu(Event ev) {
		Boolean menu = (Boolean) XPathUtils.getValue(getForm(), "menu");
		((CustomField3) getFellow("menuType")).setVisible(Boolean.TRUE.equals(menu));
		((CustomField3) getFellow("system")).setVisible(! Boolean.TRUE.equals(menu));
	}

	public void addNew(Event ev) {
		DataTree2 dt = (DataTree2) getListbox();
		dt.commit();
		Long id = (Long) XPathUtils.getValue(getForm(), "id");
		AccessTree at = new AccessTree();
		at.setParentId(id);
		dt.addNew("/app", at);
	}
}
