//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package com.soffid.iam.api;
import com.soffid.mda.annotation.*;

@ValueObject 
public class RuleAssignedRole {

	@Nullable
	public java.lang.Long id;

	@Nullable
	public java.lang.String bshDomainValueExpression;

	@Nullable
	public java.lang.String domainValue;

	public java.lang.Long roleId;

	public java.lang.Long ruleId;

}
