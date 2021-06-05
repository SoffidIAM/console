package com.soffid.iam.web.popup;

import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Row;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.Password;
import com.soffid.iam.api.SoffidObjectType;
import com.soffid.iam.service.ejb.AccountService;
import com.soffid.iam.service.ejb.DispatcherService;
import com.soffid.iam.service.ejb.UserService;
import com.soffid.iam.sync.engine.intf.GetObjectResults;
import com.soffid.iam.utils.AutoritzacionsUsuari;

import es.caib.seycon.ng.exception.BadPasswordException;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.component.DataModel;
import es.caib.zkib.component.DataTree2;
import es.caib.zkib.datasource.CommitException;
import es.caib.zkib.datasource.DataSource;
import es.caib.zkib.zkiblaf.Missatgebox;


public class ObjectAttributesHandler extends Window implements AfterCompose {
	private String[] account;

	public ObjectAttributesHandler() throws NamingException, CreateException {
	}
	
	public void setPage(Page p) {
		super.setPage(p);
	}
	
	public void cleanWindow() {
		Grid grid = (Grid) getFellow("attributeGrid");
		grid.getRows().getChildren().clear();
		setVisible(false);
	}

	
	public void afterCompose() {
	}
	
	public void onWindowClose(Event event) {
		cleanWindow();
		event.stopPropagation();
	}

	public void onStart(Event event) 
	{
		Object[] data = (Object[])event.getData();
		String objectName = (String) data[0];
		com.soffid.iam.sync.engine.intf.GetObjectResults o = (GetObjectResults) data[1];
		account = (String[]) ( data.length > 2 ? data[2] : null );
		
		
		boolean displayReconcile = account != null;
		
		getFellow("loadButton").setVisible(displayReconcile);

		doHighlighted();
		
		((Label)getFellow("detall_nom")).setValue(objectName);
		Grid grid = (Grid) getFellow("attributeGrid");
		grid.getRows().getChildren().clear();
		boolean blue=true;
		Map attributes = o.getObject();
		List<String> keys = new LinkedList(attributes.keySet());
		Collections.sort(keys);
		for (String key: keys)
		{
			Row row = new Row();
			if (blue) row.setStyle("background-color: #d0d0ff;");
			blue = ! blue;
			row.appendChild(new Label(key));
			String v = (String) attributes.get(key);
			Label l = new Label(v);
			l.setMultiline(true);
			row.appendChild(l);
			grid.getRows().appendChild(row);			
		}
		((Textbox)getFellow("log")).setValue( o.getLog() );
		((Label)getFellow("status")).setValue(o.getStatus());
		
	}
	
	public void doLoad() throws NamingException, CreateException, InternalErrorException 
	{
		com.soffid.iam.service.ejb.DispatcherService svc = com.soffid.iam.EJBLocator.getDispatcherService();
		com.soffid.iam.sync.engine.intf.GetObjectResults o = svc.reconcile(
				account[0], account[1]);
		getFellow("loadButton").setVisible(false);
		((Textbox)getFellow("log")).setValue( o.getLog() );
		((Label)getFellow("status")).setValue(o.getStatus());
	}
}
