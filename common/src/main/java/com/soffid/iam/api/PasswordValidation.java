package com.soffid.iam.api;

public enum PasswordValidation {
    PASSWORD_GOOD , 
    PASSWORD_WRONG,
    PASSWORD_GOOD_EXPIRED;
	
    public static PasswordValidation toPasswordValidation(es.caib.seycon.ng.comu.PasswordValidation src)
    {
		return src == null ?
				null:
				PasswordValidation.valueOf(src.toString());
    }
};

