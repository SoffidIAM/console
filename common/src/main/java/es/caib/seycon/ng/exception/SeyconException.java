package es.caib.seycon.ng.exception;

import org.apache.commons.logging.LogFactory;

public class SeyconException extends RuntimeException {
	String message;
	public SeyconException(String message){
		this.message = message;
	}
	public String getMessage(){
		return this.message;
	}
}
