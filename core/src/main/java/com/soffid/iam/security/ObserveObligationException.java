package com.soffid.iam.security;

import java.util.List;

import es.caib.seycon.ng.exception.InternalErrorException;

public class ObserveObligationException extends InternalErrorException {
	List<Obligation> obligations;
	
	public ObserveObligationException(List<Obligation> obligations) {
		super();
		this.obligations = obligations;
	}

	public ObserveObligationException(String message, Throwable cause, List<Obligation> obligations) {
		super(message, cause);
		this.obligations = obligations;
	}

	public ObserveObligationException(String msg, List<Obligation> obligations) {
		super(msg);
		this.obligations = obligations;
	}

	public List<Obligation> getObligations() {
		return obligations;
	}

}
