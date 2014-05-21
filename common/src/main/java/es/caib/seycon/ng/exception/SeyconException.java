package es.caib.seycon.ng.exception;

public class SeyconException extends RuntimeException {
	String message;
	public SeyconException(String message){
		this.message = message;
	}
	public String getMessage(){
		return this.message;
	}
}
