package com.soffid.iam.web.application;

import java.io.IOException;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.zk.ui.event.Event;

import com.soffid.iam.api.Role;
import com.soffid.iam.web.component.BulkAction;
import com.soffid.iam.web.component.FrameHandler;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.component.DataTable;
import es.caib.zkib.datasource.CommitException;

public class RoleHandler extends FrameHandler {

	public RoleHandler() throws NamingException, CreateException, InternalErrorException {
		super();
	}

	public void bulkAction(Event event) throws IOException, CommitException, InternalErrorException, NamingException, CreateException {
		DataTable listbox = (DataTable) getListbox();
		if (listbox.getSelectedIndexes() != null && listbox.getSelectedIndexes().length > 0) {
			BulkAction ba = new BulkAction( Role.class.getName() ); 
			ba.start(listbox ) ;
		}
	}
	
	public void importCsv () throws IOException, CommitException, InternalErrorException, NamingException, CreateException {
		new RoleImporter().importCsv(this);
	}
	

}
