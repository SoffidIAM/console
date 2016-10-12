package es.caib.seycon.ng.comu;

import com.soffid.mda.annotation.Description;
import com.soffid.mda.annotation.Enumeration;

@Enumeration()
@Description("How the user can use the accounts")
public class AccountAccessLevelEnum {
	@Description("Navegate through vault folders")
	public static String ACCESS_NAVIGATE = "N";

	@Description("Only usable on single sign on engine")
	public static String ACCESS_USER = "U";
	
	@Description("User can change the password using self service portal")
	public static String ACCESS_MANAGER = "M";

	@Description("User can managed account's access control list")
	public static String ACCESS_OWNER = "O";

	@Description("User canot use the account")
	public static String ACCESS_NONE = "-";
}
