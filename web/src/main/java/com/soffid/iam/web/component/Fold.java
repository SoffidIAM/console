package com.soffid.iam.web.component;

import org.zkoss.xml.HTMLs;
import org.zkoss.zk.au.AuRequest;
import org.zkoss.zk.au.Command;
import org.zkoss.zk.au.ComponentCommand;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Div;


public class Fold extends Div {
	String label;
	boolean folded = true; 
	
	public Fold() {
		setSclass("fold");
	}
	
	public String getLabel() {
		return label;
	}

	
	public void setLabel(String label) {
		this.label = label;
	}
	
	public Command getCommand(String cmdId) {
		if (cmdId.equals(_onFoldCommand.getId()))
			return _onFoldCommand;
		if (cmdId.equals(_onUnfoldCommand.getId()))
			return _onUnfoldCommand;
			
		return super.getCommand(cmdId);
	}

	private static Command _onFoldCommand  = new ComponentCommand ("onFold", 0) {
		protected void process(AuRequest request) {
			((Fold)request.getComponent()).folded = true;
			Events.postEvent("onFold", request.getComponent(), null);
		}
	};

	private static Command _onUnfoldCommand  = new ComponentCommand ("onUnfold", 0) {
		protected void process(AuRequest request) {
			((Fold)request.getComponent()).folded = false;
			Events.postEvent("onUnfold", request.getComponent(), null);
		}
	};

	
	public boolean isFolded() {
		return folded;
	}
	
	public void setFolded(boolean folded) {
		this.folded = folded;
		smartUpdate("folded", folded);
	}

	@Override
	public String getInnerAttrs() {
		StringBuffer sb = new StringBuffer( super.getInnerAttrs() );
		HTMLs.appendAttribute(sb, "_folded", folded);
		return sb.toString();
	}
}
