package com.soffid.iam.service.saml;

import java.util.List;

import javax.annotation.Nonnull;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.opensaml.core.criterion.EntityIdCriterion;
import org.opensaml.saml.common.assertion.AssertionValidationException;
import org.opensaml.saml.common.assertion.ValidationContext;
import org.opensaml.saml.common.assertion.ValidationResult;
import org.opensaml.saml.saml2.assertion.ConditionValidator;
import org.opensaml.saml.saml2.assertion.SAML2AssertionValidationParameters;
import org.opensaml.saml.saml2.assertion.StatementValidator;
import org.opensaml.saml.saml2.assertion.SubjectConfirmationValidator;
import org.opensaml.saml.saml2.core.Assertion;
import org.opensaml.saml.saml2.core.Response;
import org.opensaml.security.SecurityException;
import org.opensaml.security.credential.UsageType;
import org.opensaml.security.criteria.UsageCriterion;
import org.opensaml.xmlsec.signature.Signature;
import org.opensaml.xmlsec.signature.support.SignaturePrevalidator;
import org.opensaml.xmlsec.signature.support.SignatureTrustEngine;

import net.shibboleth.utilities.java.support.primitive.StringSupport;
import net.shibboleth.utilities.java.support.resolver.CriteriaSet;

public class SAML20ResponseValidator {
	Log log = LogFactory.getLog(getClass());
	
	private List<ConditionValidator> conditionValidators;
	private List<SubjectConfirmationValidator> subjectConfirmationValidators;
	private List<StatementValidator> statementValidators;
	private SignatureTrustEngine trustEngine;
	private SignaturePrevalidator signaturePrevalidator;

	public SAML20ResponseValidator(List<ConditionValidator> conditionValidators,
			List<SubjectConfirmationValidator> subjectConfirmationValidators,
			List<StatementValidator> statementValidators, SignatureTrustEngine signatureTrustEngine,
			SignaturePrevalidator signaturePrevalidator) {
		this.conditionValidators = conditionValidators;
		this.subjectConfirmationValidators = subjectConfirmationValidators;
		this.statementValidators = statementValidators;
		this.trustEngine = signatureTrustEngine;
		this.signaturePrevalidator = signaturePrevalidator;
	}

	public ValidationResult validate(Response token, ValidationContext ctx) throws AssertionValidationException {
		if (!token.isSigned()) {
			ctx.setValidationFailureMessage("Assertion was required to be signed, but was not");
			return ValidationResult.INVALID;
		} 
        if (trustEngine == null) {
            log.warn("Signature validation was necessary, but no signature trust engine was available");
            ctx.setValidationFailureMessage("Assertion signature could not be evaluated due to internal error");
            return ValidationResult.INDETERMINATE;
        }
        
        return performSignatureValidation(token, ctx);
	}

    @Nonnull protected ValidationResult performSignatureValidation(@Nonnull final Response token, 
            @Nonnull final ValidationContext context) throws AssertionValidationException {
        Signature signature = token.getSignature();
        
        String tokenIssuer = null;
        if (token.getIssuer() != null) {
            tokenIssuer = token.getIssuer().getValue();
        }
        
        log.debug(String.format("Attempting signature validation on Request '%s' from Issuer '%s'",
                token.getID(), tokenIssuer));
        
        try {
            signaturePrevalidator.validate(signature);
        } catch (org.opensaml.xmlsec.signature.support.SignatureException e) {
            String msg = String.format("Assertion Signature failed pre-validation: %s", e.getMessage());
            log.warn(msg);
            context.setValidationFailureMessage(msg);
            return ValidationResult.INVALID;
        }
        
        CriteriaSet criteriaSet = getSignatureValidationCriteriaSet(token, context);
        
        try {
            if (trustEngine.validate(signature, criteriaSet)) {
                log.debug(String.format("Validation of signature of Assertion '%s' from Issuer '%s' was successful",
                        token.getID(), tokenIssuer));
                return ValidationResult.VALID;
            } else {
                String msg = String.format(
                        "Signature of Assertion '%s' from Issuer '%s' was not valid", token.getID(), tokenIssuer);
                log.warn(msg);
                context.setValidationFailureMessage(msg);
                return ValidationResult.INVALID;
            }
        } catch (SecurityException e) {
            String msg = String.format(
                    "A problem was encountered evaluating the signature over Assertion with ID '%s': %s",
                    token.getID(), e.getMessage());
            log.warn(msg);
            context.setValidationFailureMessage(msg);
            return ValidationResult.INDETERMINATE;
        }
        
    }

    /**
     * Get the criteria set that will be used in evaluating the Assertion signature via the supplied trust engine.
     * 
     * @param token assertion whose signature will be validated
     * @param context current validation context
     * @return the criteria set to use
     */
    @Nonnull protected CriteriaSet getSignatureValidationCriteriaSet(@Nonnull final Response token, 
            @Nonnull final ValidationContext context) {
        
        CriteriaSet criteriaSet = (CriteriaSet) context.getStaticParameters().get(
                SAML2AssertionValidationParameters.SIGNATURE_VALIDATION_CRITERIA_SET);
        if (criteriaSet == null)  {
            criteriaSet = new CriteriaSet();
        }
        
        if (!criteriaSet.contains(EntityIdCriterion.class)) {
            String issuer =  null;
            if (token.getIssuer() != null) {
                issuer = StringSupport.trimOrNull(token.getIssuer().getValue());
            }
            if (issuer != null) {
                criteriaSet.add(new EntityIdCriterion(issuer));
            }
        }
        
        if (!criteriaSet.contains(UsageCriterion.class)) {
            criteriaSet.add(new UsageCriterion(UsageType.SIGNING));
        }
        
        return criteriaSet;
    }
}
