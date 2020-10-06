package com.soffid.iam.web.agent;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.jfree.util.Log;
import org.zkoss.idom.Document;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Fileupload;
import org.zkoss.zul.Image;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.Row;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Timer;
import org.zkoss.zul.Window;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.AgentDescriptor;
import com.soffid.iam.api.ScheduledTask;
import com.soffid.iam.api.System;
import com.soffid.iam.api.UserType;
import com.soffid.iam.doc.exception.DocumentBeanException;
import com.soffid.iam.service.ejb.DispatcherService;
import com.soffid.iam.utils.AutoritzacionsUsuari;
import com.soffid.iam.web.component.FileDump;
import com.soffid.iam.web.component.FrameHandler;
import com.soffid.iam.web.component.SearchBox;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.component.DataDatebox;
import es.caib.zkib.component.DataListbox;
import es.caib.zkib.component.DataModel;
import es.caib.zkib.component.DataTable;
import es.caib.zkib.component.Select;
import es.caib.zkib.component.Switch;
import es.caib.zkib.datamodel.DataModelCollection;
import es.caib.zkib.datamodel.DataNode;
import es.caib.zkib.datamodel.DataNodeCollection;
import es.caib.zkib.datamodel.xml.XmlDataNode;
import es.caib.zkib.datasource.CommitException;
import es.caib.zkib.datasource.DataSource;
import es.caib.zkib.datasource.XPathUtils;
import es.caib.zkib.jxpath.JXPathContext;
import es.caib.zkib.zkiblaf.Missatgebox;

public class SyncServerHandler extends FrameHandler {

	private boolean canManageServers;

	public SyncServerHandler() throws InternalErrorException {
		super();
	}

	@Override
	public void setPage(Page p) {
		super.setPage(p);

		canManageServers = AutoritzacionsUsuari.canManageServers();

		getNamespace().setVariable("canManageServers", canManageServers, true);
	}


	public void afterCompose() {
		super.afterCompose();
		
//		((SearchBox) getFellow("searchBox")).search();
	}

	@Override
	public void onChangeForm(Event evt)
	{
		super.onChangeForm(evt);
	}

	@Override
	public void delete() throws CommitException {
		Missatgebox.confirmaOK_CANCEL(org.zkoss.util.resource.Labels.getLabel("agents.deleteServer"),
				org.zkoss.util.resource.Labels.getLabel("agents.Esborra"), (evt) -> {
					if ("onOK".equals(evt.getName())) {
						super.delete();
					}
				});
	}
	
}
