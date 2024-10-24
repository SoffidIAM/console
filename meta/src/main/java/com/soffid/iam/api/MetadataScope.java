package com.soffid.iam.api;

import com.soffid.mda.annotation.Enumeration;

@Enumeration
public class MetadataScope {
	public static String ROLE = "role";
	public static String USER = "user";
	public static String APPLICATION = "application";
	public static String GROUP = "group";
	public static String ACCOUNT = "account";
	public static String CUSTOM = "custom";
	public static String MAIL_LIST = "mail_list";
	public static String GROUP_MEMBERSHIP = "group_membership";
}
