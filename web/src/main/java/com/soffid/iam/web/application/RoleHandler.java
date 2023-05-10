package com.soffid.iam.web.application;

import java.io.IOException;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.event.Event;

import com.soffid.iam.api.Role;
import com.soffid.iam.web.component.BulkAction;
import com.soffid.iam.web.component.FrameHandler;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.component.DataTable;
import es.caib.zkib.datasource.CommitException;
import es.caib.zkib.zkiblaf.Missatgebox;

public class RoleHandler extends FrameHandler {

	public RoleHandler() throws NamingException, CreateException, InternalErrorException {
		super();
	}

	public void bulkAction(Event event) throws IOException, CommitException, InternalErrorException, NamingException, CreateException {
		DataTable listbox = (DataTable) getListbox();
		if (listbox.getSelectedIndexes() != null && listbox.getSelectedIndexes().length > 0) {
			BulkAction ba = new BulkAction( Role.class.getName() ); 
			ba.start(listbox ) ;
		} else {
			Missatgebox.avis("bulk.selectOneObject");
		}
	}
	
	@Override
	public void onPageAttached(Page newpage, Page oldpage) {
		super.onPageAttached(newpage, oldpage);
		boolean readonly = "true".equals( Executions.getCurrent().getParameter("readonly") );
		getNamespace().setVariable("readonly", readonly, true);
		boolean embed = "true".equals( Executions.getCurrent().getParameter("embed") );
		getNamespace().setVariable("embed", embed, true);
	}
	
	public void importCsv (Event ev) throws IOException, CommitException, InternalErrorException, NamingException, CreateException {
		new RoleImporter().importCsv(this);
	}

}
