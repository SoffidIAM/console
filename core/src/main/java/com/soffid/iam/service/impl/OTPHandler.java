package com.soffid.iam.service.impl;

import com.soffid.iam.api.Challenge;

public interface OTPHandler {
	Challenge selectToken (Challenge challenge);
	
	boolean validatePin (Challenge challenge, String pin) ;

}
