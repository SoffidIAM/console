package com.soffid.iam.api;

import com.soffid.mda.annotation.Enumeration;

@Enumeration
public class RequestedObligationEnum {
	public String OTP = "urn:soffid:obligation:otp";
	public String MESSAGE = "urn:soffid:obligation:message";
	public String WORKFLOW = "urn:soffid:obligation:bpm";
	public String SESSION_RECORDING = "urn:soffid:obligation:session-recording";
	public String NOTIFY_OWNER = "urn:soffid:obligation:notify-owner";
}