package com.soffid.iam.web.component;

import java.io.IOException;

import org.zkoss.xml.HTMLs;
import org.zkoss.zk.au.AuRequest;
import org.zkoss.zk.au.Command;
import org.zkoss.zk.au.out.AuInvoke;
import org.zkoss.zk.ui.AbstractComponent;
import org.zkoss.zk.ui.HtmlBasedComponent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.impl.XulElement;

import es.caib.zkdb.yaml.Yaml2Json;


public class Graphjs extends XulElement implements AfterCompose{
	int refresh = -1;
	String data;
	public Graphjs() {
		setSclass("graphjs");
	}
	private static Command _onRefreshCommand = new Command("onRefresh", 0) {
		@Override
		protected void process(AuRequest request) {
			((Graphjs)request.getComponent()).updateData();
		}
	};
	
	@Override
	public String getInnerAttrs() {
		final StringBuffer sb =
				new StringBuffer(64).append(super.getInnerAttrs());
		if (refresh > 0)
			HTMLs.appendAttribute(sb, "refresh", refresh);
		if (data != null)
			HTMLs.appendAttribute(sb, "data", data);
		return sb.toString();
	}

	@Override
	public void afterCompose() {
		updateData();
	}

	
	public void updateData() {
		Events.postEvent( new Event("onUpdate", this) );
	}

	public int getRefresh() {
		return refresh;
	}

	
	public void setRefresh(int refresh) {
		this.refresh = refresh;
		smartUpdate("refresh", refresh);
	}

	
	public String getData() {
		return data;
	}

	
	public void setData(String data) throws IOException {
		this.data = new Yaml2Json().transform(data);
		smartUpdate("data", data);
		response("refresh", new AuInvoke(this, "startTimer"));
	}

	public Command getCommand(String cmdId) {
		if ("onRefresh".equals(cmdId))
			return _onRefreshCommand  ;

		return super.getCommand(cmdId);
	}
	
}