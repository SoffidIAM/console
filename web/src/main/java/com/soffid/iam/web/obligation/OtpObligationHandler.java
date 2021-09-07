package com.soffid.iam.web.obligation;

import com.soffid.iam.common.security.Obligation;
import com.soffid.iam.web.component.OtpPageHandler;
import com.soffid.iam.web.obligation.ObligationManager;

public class OtpObligationHandler extends OtpPageHandler {

	@Override
	public void enable() {
		ObligationManager obligationManager = new ObligationManager();
		Obligation obligation = obligationManager.getNextObligation();
		if (obligation.getObligation().equals("urn:soffid:obligation:otp"))
		{
			obligationManager.meetObligation(obligation);
			obligationManager.handleNextObligation();
		}
	}

}
