package com.soffid.iam.model;

import com.soffid.mda.annotation.Enumeration;

@Enumeration
public class PamActionType {
	public static String CLOSE_SESSION = "C";
	public static String LOCK_ACCOUNT = "L";
	public static String NOTIFY = "N";
	public static String ISSUE = "I";
	public static String NONE = "-";
}
