package com.soffid.iam.service;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.soffid.iam.api.Challenge;
import com.soffid.iam.service.impl.OTPHandler;
import com.soffid.iam.service.impl.linotp.LinotpHandler;

import es.caib.seycon.ng.exception.InternalErrorException;

public class OTPValidationServiceImpl extends OTPValidationServiceBase {
	List <OTPHandler> handlers = new LinkedList<OTPHandler>();
	Log log = LogFactory.getLog(getClass());
	
	public OTPValidationServiceImpl() {
		handlers.add(new LinotpHandler());
	}
	
	@Override
	protected boolean handleValidatePin(Challenge challenge, String pin) throws Exception {
		if (challenge.getOtpHandler () == null)
			return false;
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
			try {
				Challenge ch = handler.selectToken(challenge);
				if (ch.getCardNumber() != null)
				{
					ch.setOtpHandler(handler.getClass().getName());
					return ch;
				}
			} catch (InternalErrorException th) {
				throw th;
			} catch (Throwable th) {
				log.warn(th);
			}
		}
		return challenge;
	}

	@Override
	protected void handleRegisterOTPHandler(OTPHandler handler) throws Exception {
		handlers.add(handler);
	}

	@Override
	protected boolean handleResetFailCount(String account) throws Exception {
		for (OTPHandler handler: handlers) {
			return handler.resetFailCount(account);
		}
		return false;
	}

	@Override
	protected Challenge handleResendToken(Challenge challenge, boolean alternativeMethod) throws Exception {
		if (challenge.getOtpHandler () == null || ! challenge.isResendAvailable())
			return challenge;
		for ( OTPHandler handler: handlers)
		{
			if (handler.getClass().getName().equals(challenge.getOtpHandler()))
			{
				return handler.resendToken(challenge, alternativeMethod);
			}
		}
		return challenge;
	}

	@Override
	protected String handleGenerateTypeForAudit(Challenge challenge) throws Exception {
		if (challenge.getOtpHandler() == null)
			return null;
		for (OTPHandler handler: handlers) {
			if (handler.getClass().getName().equals(challenge.getOtpHandler())) {
				return handler.generateTypeForAudit(challenge);
			}
		}
		return "?";
	}
}
