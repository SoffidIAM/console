package com.soffid.iam.web.user;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.Password;
import com.soffid.iam.api.SoffidObjectType;
import com.soffid.iam.api.System;
import com.soffid.iam.service.ejb.AccountService;
import com.soffid.iam.service.ejb.DispatcherService;
import com.soffid.iam.service.ejb.UserService;
import com.soffid.iam.sync.engine.intf.GetObjectResults;
import com.soffid.iam.utils.AutoritzacionsUsuari;
import com.soffid.iam.utils.Security;

import es.caib.seycon.ng.exception.BadPasswordException;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.component.DataModel;
import es.caib.zkib.component.DataTree2;
import es.caib.zkib.datamodel.DataNode;
import es.caib.zkib.datasource.CommitException;
import es.caib.zkib.datasource.DataSource;
import es.caib.zkib.datasource.XPathUtils;
import es.caib.zkib.zkiblaf.Missatgebox;


public class AccountDetailsHandler extends Window implements AfterCompose {
	private static final long serialVersionUID = 1L;
	private String dataSourcePath;
	private DataSource dataSource;
	
	public AccountDetailsHandler() throws NamingException, CreateException {
		dataSourcePath = (String) Executions.getCurrent().getAttribute("dataSource");
	}
	
	public void setPage(Page p) {
		super.setPage(p);
		boolean canUpdateUserMetadata = AutoritzacionsUsuari.hasUpdateUserMetadata();
		boolean canUpdateUser = Security.isUserInRole("user:update");
		
		getNamespace().setVariable("canUpdateUser", canUpdateUser, true);
		p.setVariable("canUpdateUserMetadata", canUpdateUserMetadata);
	}
	
	void cleanWindow() {
		if (dataSource instanceof DataTree2)
			((DataTree2) dataSource).setSelectedIndex(new int[0]);
		setVisible(false);
	}

	
	public void afterCompose() {
		dataSource = (DataSource) XPathUtils.getPath(getPage(), dataSourcePath);
	}
	
	public void onWindowClose(Event event) {
		DataSource ds = (DataSource) Path.getComponent(dataSourcePath);
		DataNode dn = (DataNode) ds.getJXPathContext().getValue("/");
		dn.getParent().refresh();
		cleanWindow();
		event.stopPropagation();
	}

	public String stringify (Object obj, String indent)
	{
		if (obj == null) return "";
		
		if (obj instanceof java.util.Calendar)
		{
			return new java.text.SimpleDateFormat(org.zkoss.util.resource.Labels.getLabel("selfService.Format"))
					.format(((Calendar)obj).getTime());
		}
		if (obj instanceof java.util.Date)
		{
			return new java.text.SimpleDateFormat(org.zkoss.util.resource.Labels.getLabel("selfService.Format"))
					.format((Date)obj);
		}
		if (obj instanceof java.util.Collection)
		{
			String r = "[";
			for ( Object obj2 : (Collection)obj) {
				if (!r.isEmpty()) r = r + ",\n"+indent;
				r = r + stringify(obj2, indent+"  ");
			}
			return r + "]";
		}
		if (obj instanceof java.util.Map)
		{
			String r = "{";
			Map map = (Map)obj;
			for ( Object k : map.keySet()) {
				if (!r.isEmpty()) r = r + ",\n";
				r = r + stringify(k, indent+" ") + ": "+stringify (map.get(k), indent+"  ");
			}
			return r + "}";
		}
		if (obj.getClass().isArray())
		{
			String r = "";
			Object[] array = (Object[]) obj; 
			for ( Object obj2 : array) {
				if (r.isEmpty()) r = "[";
				else r = r + ",\n"+indent;
				r = r + stringify(obj2, indent+"  ");
			}
			return r + "]";
		}
		return obj.toString();
	}

	public void doQuery () throws NamingException, CreateException, InternalErrorException
	{
		DispatcherService svc = EJBLocator.getDispatcherService();
		
		String name = (String) dataSource.getJXPathContext().getValue("/@name");
		String system = (String) dataSource.getJXPathContext().getValue("/@system");

		GetObjectResults result = svc.getNativeObject(
				system,
				SoffidObjectType.OBJECT_ACCOUNT,
				name, system);
		Map map = result.getObject();
		if (map == null)
		{
			es.caib.zkib.zkiblaf.Missatgebox.avis(org.zkoss.util.resource.Labels.getLabel("agents.zul.notFound"));
			return;
		}
		Component c = Path.getComponent("//objectAttributes/window");
		for (Object key: map.keySet())
		{
			map.put(key, stringify(map.get(key), ""));
		}
		Events.postEvent("onStart", c, new Object[]{
				name,
				result
		});
	}
	
	public void onStart(Event event) {
		Component b = getFellow("detailsButton");
		
		String system = (String) dataSource.getJXPathContext().getValue("@system");
		boolean visible = false;
		try {
			System s = EJBLocator.getDispatcherService().findDispatcherByName(system);
			if (s != null && s.getUrl() != null)
				visible = true;
		} catch (Exception e ) {
			// Probably lack of permissions
		}
		b.setVisible( visible );
		doHighlighted();
		
	}

	public void onChange (Event event) throws CommitException {
		dataSource.commit();
		cleanWindow();
		
	}
	
	public void delete() throws CommitException {
		Missatgebox.confirmaOK_CANCEL(Labels.getLabel("common.delete"), 
				(event) -> {
					if (event.getName().equals("onOK")) {
						DataNode node = (DataNode) dataSource.getJXPathContext().getValue("/");
						node.delete();
						dataSource.commit();
						cleanWindow();
					}
				});
	}

}
