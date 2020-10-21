package com.soffid.iam.web.audit;

import com.soffid.iam.web.component.FrameHandler;
import com.soffid.iam.web.component.SearchBox;

import es.caib.seycon.ng.exception.InternalErrorException;


public class SessionHandler extends FrameHandler {

	public SessionHandler() throws InternalErrorException {
		super();
	}

	
	public void afterCompose() {
		super.afterCompose();
	}
	
	public void refresh() {
		getModel().refresh();
	}
}
