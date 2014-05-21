package es.caib.seycon.ng.comu;

import java.util.ArrayList;
import java.util.LinkedList;

public class DispatcherAccessControl implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codi;
	private Boolean controlAccessActiu;
	private LinkedList<ControlAcces> controlAcces;

	public DispatcherAccessControl(String codi) {
		this.codi = codi;
		controlAcces = new LinkedList<ControlAcces>();
	}

	public void setControlAccessActiu(Boolean controlAccessActiu) {
		this.controlAccessActiu = controlAccessActiu;
	}	

	public Boolean getControlAccessActiu() {
		return controlAccessActiu;
	}

	public String getCodi() {
		return codi;
	}

	public LinkedList<ControlAcces> getControlAcces() {
		return controlAcces;
	}



}
