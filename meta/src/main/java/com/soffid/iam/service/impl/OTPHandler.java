package com.soffid.iam.service.impl;

import es.caib.seycon.ng.comu.Challenge;

public interface OTPHandler {
	Challenge selectToken (Challenge challenge);
	
	boolean validatePin (Challenge challenge, String pin) ;
}
