package com.soffid.iam.service;

import java.util.List;

import com.soffid.iam.api.Challenge;
import com.soffid.iam.service.impl.OTPHandler;

public class OTPValidationServiceImpl extends OTPValidationServiceBase {
	List <OTPHandler> handlers;
	
	@Override
	protected boolean handleValidatePin(Challenge challenge, String pin) throws Exception {
		if (challenge.getOtpHandler () == null)
			return true;
		for ( OTPHandler handler: handlers)
		{
			if (handler.getClass().getName().equals(challenge.getOtpHandler()))
			{
				return handler.validatePin(challenge, pin);
			}
		}
		return false;
	}

	@Override
	protected Challenge handleSelectToken(Challenge challenge) throws Exception {
		for ( OTPHandler handler: handlers)
		{
			Challenge ch = handler.selectToken(challenge);
			if (ch.getCardNumber() != null)
			{
				ch.setOtpHandler(handler.getClass().getName());
				return ch;
			}
		}
		return challenge;
	}

	@Override
	protected void handleRegisterOTPHandler(OTPHandler handler) throws Exception {
		handlers.add(handler);
	}

}
