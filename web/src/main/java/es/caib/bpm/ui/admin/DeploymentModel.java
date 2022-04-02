package es.caib.bpm.ui.admin;

import org.zkoss.zk.ui.ext.AfterCompose;

import es.caib.zkib.component.DataModel;


public class DeploymentModel extends DataModel implements AfterCompose {
	public void afterCompose() {
		super.afterCompose();
		getJXPathContext().getVariables().declareVariable("all", true);
	}
}
