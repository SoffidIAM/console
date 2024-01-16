package com.soffid.iam.api;

import java.io.Serializable;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import com.soffid.iam.lang.MessageFactory;

public class PolicyCheckResult implements Serializable {
    
    public static int NUM_ERRORS = 21; 

    public static PolicyCheckResult VALID = new PolicyCheckResult (0);
    public static PolicyCheckResult TOO_LONG = new PolicyCheckResult (1);
    public static PolicyCheckResult TOO_SHORT = new PolicyCheckResult (2);
    public static PolicyCheckResult INVALID_REGEXP = new PolicyCheckResult (3);
    public static PolicyCheckResult REGEXP_NOT_MATCH = new PolicyCheckResult (4);
    public static PolicyCheckResult TOO_MANY_CAPS = new PolicyCheckResult (5);
    public static PolicyCheckResult TOO_FEW_CAPS = new PolicyCheckResult (6);
    public static PolicyCheckResult TOO_MANY_SMALLS = new PolicyCheckResult (7);
    public static PolicyCheckResult TOO_FEW_SMALLS = new PolicyCheckResult (8);
    public static PolicyCheckResult TOO_MANY_NUMBERS = new PolicyCheckResult (9);
    public static PolicyCheckResult TOO_FEW_NUMBERS = new PolicyCheckResult (10);
    public static PolicyCheckResult TOO_MANY_SIGNS = new PolicyCheckResult (11);
    public static PolicyCheckResult TOO_FEW_SIGNS = new PolicyCheckResult (12);
    public static PolicyCheckResult OLD_PASSWORD = new PolicyCheckResult (13);
    public static PolicyCheckResult FORBIDDEN_WORD = new PolicyCheckResult (14);
    public static PolicyCheckResult NOPOLICY_DEFINED = new PolicyCheckResult (15);
    public static PolicyCheckResult CONTAINS_ACCOUNTNAME = new PolicyCheckResult (16);
    public static PolicyCheckResult CONTAINS_NAME = new PolicyCheckResult (17);
    public static PolicyCheckResult MORE_TYPES_OF_CHARS = new PolicyCheckResult (18);
    public static PolicyCheckResult NOT_YET = new PolicyCheckResult (19);
    public static PolicyCheckResult CUSTOM_CHECK = new PolicyCheckResult (20);

    public int reasonCode;
    public String extraInfo;
    
    public boolean isValid() {
        return reasonCode == VALID.reasonCode;
    }

    public String getReason(Locale l) {
        ResourceBundle rb = ResourceBundle.getBundle("com/soffid/iam/api/PolicyCheckResult", //$NON-NLS-1$
        		l,
				getClass().getClassLoader(),
				ResourceBundle.Control.getNoFallbackControl(
					ResourceBundle.Control.FORMAT_PROPERTIES)); 
        String s = Messages.getString("PolicyCheckResult.UnknownCode")+reasonCode; //$NON-NLS-1$
        try {
            s = rb.getString(Integer.toString(reasonCode));
        } catch (MissingResourceException e) {}
        if (extraInfo != null)
            s = s + ": "+extraInfo; //$NON-NLS-1$
        return s;
    }

    public String getReason() {
        return getReason (MessageFactory.getLocale());
    }

    public int getReasonCode() {
        return reasonCode;
    }

    public void setReasonCode(int reasonCode) {
        this.reasonCode = reasonCode;
    }
    
    public PolicyCheckResult(int value) {
        if (value < 0 || value >= NUM_ERRORS) {
            throw new RuntimeException (Messages.getString("PolicyCheckResult.Invalidtoken")+value); //$NON-NLS-1$
        }
        this.reasonCode = value;
    }

    public PolicyCheckResult(int value, String extraInfo) {
        if (value < 0 || value >= NUM_ERRORS) {
            throw new RuntimeException (Messages.getString("PolicyCheckResult.Invalidtoken")+value); //$NON-NLS-1$
        }
        this.reasonCode = value;
        this.extraInfo = extraInfo; 
    }
}
