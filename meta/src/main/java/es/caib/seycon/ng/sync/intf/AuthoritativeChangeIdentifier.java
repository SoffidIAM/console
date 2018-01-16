package es.caib.seycon.ng.sync.intf;

import java.util.Date;

import com.soffid.mda.annotation.Nullable;
import com.soffid.mda.annotation.ValueObject;

@ValueObject(translatedName="AuthoritativeChangeIdentifier", translatedPackage="com.soffid.iam.sync.intf",
		serialVersion="-605755430186793407L")
public class AuthoritativeChangeIdentifier {
	@Nullable
	Object employeeId;
	
	@Nullable
	Date date;
	
	@Nullable
	Object changeId;
	
	@Nullable
	Long internalId;
	

}
