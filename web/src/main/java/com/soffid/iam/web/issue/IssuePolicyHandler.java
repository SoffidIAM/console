package com.soffid.iam.web.issue;

import org.zkoss.zk.ui.event.Event;

import com.soffid.iam.api.IssuePolicyAction;
import com.soffid.iam.web.component.FrameHandler;
import com.soffid.iam.web.component.SearchBox;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.datasource.DataSource;
import es.caib.zkib.datasource.XPathUtils;


public class IssuePolicyHandler extends FrameHandler {

	public IssuePolicyHandler() throws InternalErrorException {
		super();
	}

	@Override
	public void onChangeForm(Event ev) throws Exception {
		super.onChangeForm(ev);
	}

	public void addAction(Event ev) throws Exception {
		XPathUtils.createPath((DataSource) getListbox(), "/actions", new IssuePolicyAction());
	}

	@Override
	public void afterCompose() {
		SearchBox sb = (SearchBox) getFellow("searchBox");
		sb.search();
	}
	

}
