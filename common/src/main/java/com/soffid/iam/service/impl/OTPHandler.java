package com.soffid.iam.service.impl;

import com.soffid.iam.am.api.Challenge;

public interface OTPHandler {
	boolean hasToken (String user) throws Exception;

	Challenge selectToken (Challenge challenge) throws Exception;
	
	boolean validatePin (Challenge challenge, String pin) throws Exception;

	boolean resetFailCount (String account) throws Exception;

	Challenge resendToken(Challenge challenge, boolean alternativeMethod) throws Exception;

	String generateTypeForAudit(Challenge challenge) throws Exception;
}
