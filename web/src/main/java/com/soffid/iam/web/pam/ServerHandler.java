package com.soffid.iam.web.pam;

import org.zkoss.zk.ui.Page;

import com.soffid.iam.web.component.FrameHandler;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.datasource.CommitException;
import es.caib.zkib.zkiblaf.Missatgebox;


public class ServerHandler extends FrameHandler {
	private boolean canRemoveJumpServer;
	private boolean canUpdateJumpServer;
	private boolean canCreateJumpServer;

	public ServerHandler() throws InternalErrorException {
		super();
	}

	@Override
	public void setPage(Page p) {
		super.setPage(p);
		canCreateJumpServer = com.soffid.iam.utils.Security.isUserInRole("jumpServer:create");
		canUpdateJumpServer = com.soffid.iam.utils.Security.isUserInRole("jumpServer:update");
		canRemoveJumpServer = com.soffid.iam.utils.Security.isUserInRole("jumpServer:remove");
		setVariable("canCreateJumpServer", canCreateJumpServer, true);
		setVariable("canUpdateJumpServer", canUpdateJumpServer, true);
		setVariable("canRemoveJumpServer", canRemoveJumpServer, true);
	}

	@Override
	public void delete() throws CommitException {
		Missatgebox.confirmaOK_CANCEL(org.zkoss.util.resource.Labels.getLabel("pamserver.delete2"),
				org.zkoss.util.resource.Labels.getLabel("pamserver.delete"), (evt) -> {
					if ("onOK".equals(evt.getName())) {
						super.delete();
					}
				});
	}
	

}
