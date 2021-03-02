package es.caib.seycon.ng.comu;

import java.util.Date;

import com.soffid.mda.annotation.Column;
import com.soffid.mda.annotation.Nullable;
import com.soffid.mda.annotation.ValueObject;

@ValueObject ( translatedName="AccountHistory",
	translatedPackage="com.soffid.iam.api")
public class AccountHistory {
	@Nullable
	public Account account;

	@Nullable
	AccountAccessLevelEnum level;
	
	@Nullable
	public Date start;

	@Nullable
	public Date end;
}
