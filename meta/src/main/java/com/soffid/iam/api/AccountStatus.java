package com.soffid.iam.api;

import com.soffid.mda.annotation.Enumeration;

@Enumeration
public class AccountStatus {
	public static String ACTIVE = "a";
	public static String FORCED_ACTIVE = "FA";
	public static String LOCKED = "l";
	public static String DISABLED = "d";
	public static String FORCED_DISABLED = "FD";
	public static String REMOVED = "r";
	public static String ARCHIVED = "x";
}
