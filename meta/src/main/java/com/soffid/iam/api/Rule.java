//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package com.soffid.iam.api;
import com.soffid.mda.annotation.*;

@ValueObject 
public abstract class Rule {

	@Nullable
	public java.lang.Long id;

	public java.lang.String description;

	public java.lang.String bshExpression;

}
