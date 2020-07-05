package com.soffid.iam.web.group;

import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Event;

import com.soffid.iam.web.component.FrameHandler;
import com.soffid.iam.web.component.SearchBox;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.datamodel.DataNodeCollection;


public class GroupHandler extends FrameHandler {

	public GroupHandler() throws InternalErrorException {
		super();
	}

	
	@Override
	public void afterCompose() {
		super.afterCompose();
		
		SearchBox sb = (SearchBox) getFellow("searchBox");
		sb.search();
	}
	
	public void onChangeForm(Event event) {
		
	}
}
