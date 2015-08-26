package es.caib.seycon.ng.comu;

public enum PasswordValidation {
    PASSWORD_GOOD , 
    PASSWORD_WRONG,
    PASSWORD_GOOD_EXPIRED;

	public static PasswordValidation toPasswordValidation(
			com.soffid.iam.api.PasswordValidation checkAccountPassword)
	{
		return PasswordValidation.valueOf(checkAccountPassword.toString());
				
	}
};

