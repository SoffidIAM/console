package com.soffid.iam.service;

import com.soffid.iam.service.impl.OTPHandler;
import com.soffid.mda.annotation.Service;

import es.caib.seycon.ng.comu.Challenge;

@Service(internal=true, serverPath="/seycon/otpValidationService")
public class OTPValidationService {
	public void registerOTPHandler (OTPHandler handler) { }

	public Challenge selectToken (Challenge challenge) {return null;}
	
	public boolean validatePin (Challenge challenge, String pin) {return false;}
}

