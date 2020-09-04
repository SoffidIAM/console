package com.soffid.iam.web.menu;

import org.zkoss.util.resource.Labels;

import com.soffid.iam.api.AccessTreeExecution;

public class WebAccessTreeExecution extends Object {
	boolean enabled = false;
	AccessTreeExecution exec = new AccessTreeExecution();
	
	public WebAccessTreeExecution() {
		
	}
	
	public boolean isEnabled() {
		return enabled;
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public AccessTreeExecution getExec() {
		return exec;
	}
	
	public void setExec(AccessTreeExecution exec) {
		this.exec = exec;
	}
	
	public String getLabel() {
		if ("L".equals(exec.getScope()))
			return Labels.getLabel("aplicacionsIntranet.zul.ExecuciadesdIntrane");
		if ("W".equals(exec.getScope()))
			return Labels.getLabel("aplicacionsIntranet.zul.ExecuciadesdExtrane");
		if ("I".equals(exec.getScope()))
			return Labels.getLabel("aplicacionsIntranet.zul.ExecuciadesdInterne");
		return null;
	}

	public String getExplanation() {
		if ("L".equals(exec.getScope()))
			return Labels.getLabel("menu.internalExplanation");
		if ("W".equals(exec.getScope()))
			return Labels.getLabel("menu.externalExplanation");
		if ("I".equals(exec.getScope()))
			return Labels.getLabel("menu.internetExplanation");
		return null;
	}
}
