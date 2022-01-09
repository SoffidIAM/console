package com.soffid.iam.web.agent;

import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.event.Event;

import com.soffid.iam.utils.AutoritzacionsUsuari;
import com.soffid.iam.web.component.FrameHandler;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.datasource.CommitException;
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
	public void onChangeForm(Event evt) throws Exception
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
