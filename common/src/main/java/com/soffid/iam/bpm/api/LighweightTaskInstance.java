package com.soffid.iam.bpm.api;

import java.util.Map;

/**
 * Clase ligera que optimiza el rendimiento evitando trabajar con las variables y las transiciones
 * La generación de esta clase en vez de TaskInstance puede suponer un 1000% de mejora en el rendimiento.
 * @author u91940
 *
 */
public class LighweightTaskInstance extends TaskInstance {
	private static final long serialVersionUID = 1L;
	
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}


	public LighweightTaskInstance() {
	}


	public Map getVariables() {
		throw new UnsupportedOperationException();
	}



    public void setVariables(Map variables) {
    	throw new UnsupportedOperationException();
    }



}
