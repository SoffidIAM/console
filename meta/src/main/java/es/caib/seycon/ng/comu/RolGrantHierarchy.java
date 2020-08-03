//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import java.util.Date;
import java.util.List;

import com.soffid.iam.api.RoleDependencyStatus;
import com.soffid.mda.annotation.*;

@ValueObject ( translatedName="RoleGrantHierarchy",
	 translatedPackage="com.soffid.iam.api")
public class RolGrantHierarchy extends RolGrant{
	@Nullable
	String ruleName;
	@Nullable
	String ruleDescription;
	
	@Nullable
	String groupName;
	@Nullable
	String groupDescription;
	
	@Nullable
	String accountName;
	@Nullable
	String accountDescription;
	
	@Nullable
	@Attribute(defaultValue = "new java.util.LinkedList<>()")
	public List<RolGrantHierarchy> nested;
	
	
}
