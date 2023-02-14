package es.caib.seycon.ng.exception;

import org.apache.commons.logging.LogFactory;

public class SeyconException extends RuntimeException {
	public SeyconException(String message, Throwable e){
		super(message, e);
	}

	public SeyconException(String message){
		super(message);
	}

}
