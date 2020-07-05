package com.soffid.iam.web.dsp;

import com.soffid.iam.utils.Security;

public class ZulFns {
	protected ZulFns() {}

	/** Returns the column attribute of a child of a row by specifying
	 * the index.
	 */
	public static final boolean isUserInRole(String role) {
		return Security.isUserInRole(role);
	}

}
