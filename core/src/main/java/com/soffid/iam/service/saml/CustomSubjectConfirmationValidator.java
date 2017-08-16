package com.soffid.iam.service.saml;

import org.opensaml.saml.common.assertion.AssertionValidationException;
import org.opensaml.saml.common.assertion.ValidationContext;
import org.opensaml.saml.common.assertion.ValidationResult;
import org.opensaml.saml.saml2.assertion.impl.BearerSubjectConfirmationValidator;
import org.opensaml.saml.saml2.core.Assertion;
import org.opensaml.saml.saml2.core.SubjectConfirmation;

public class CustomSubjectConfirmationValidator extends BearerSubjectConfirmationValidator {

	@Override
	protected ValidationResult validateRecipient(SubjectConfirmation confirmation, Assertion assertion,
			ValidationContext context) throws AssertionValidationException {
		return ValidationResult.VALID;
	}

	@Override
	protected ValidationResult validateAddress(SubjectConfirmation confirmation, Assertion assertion,
			ValidationContext context) throws AssertionValidationException {
		return ValidationResult.VALID;
	}

}
