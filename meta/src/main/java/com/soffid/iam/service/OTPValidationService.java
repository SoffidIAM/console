package com.soffid.iam.service;

import com.soffid.iam.service.impl.OTPHandler;
import com.soffid.mda.annotation.Operation;
import com.soffid.mda.annotation.Service;

import es.caib.seycon.ng.comu.Challenge;
import roles.Tothom;

@Service(serverPath="/seycon/otpValidationService", serverRole="agent")
public class OTPValidationService {
	public void registerOTPHandler (OTPHandler handler) { }

	@Operation(grantees= {Tothom.class})
	public Challenge selectToken (Challenge challenge) {return null;}
	
	@Operation(grantees= {Tothom.class})
	public Challenge resendToken (Challenge challenge, boolean alternativeMethod) {return null;}
	
	@Operation(grantees= {Tothom.class})
	public boolean validatePin (Challenge challenge, String pin) {return false;}

	@Operation(grantees= {Tothom.class})
	public boolean resetFailCount (String account) {return false;}

	@Operation(grantees= {Tothom.class})
	public String generateTypeForAudit(Challenge challenge) {return null;}
}

