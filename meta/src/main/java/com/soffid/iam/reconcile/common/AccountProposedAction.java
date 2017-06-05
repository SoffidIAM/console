//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package com.soffid.iam.reconcile.common;
import com.soffid.mda.annotation.*;

@Enumeration 
public class AccountProposedAction {

	public java.lang.String IGNORE="I";

	public java.lang.String CREATE_NEW_USER="N";

	public java.lang.String BIND_TO_EXISTING_USER="B";

	public java.lang.String SHARED="S";

	public java.lang.String UPDATE_ACCOUNT="U";

	public java.lang.String DELETE_ACCOUNT="D";

}
