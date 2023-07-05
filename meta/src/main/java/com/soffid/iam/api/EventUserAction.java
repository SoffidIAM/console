package com.soffid.iam.api;

import com.soffid.mda.annotation.Enumeration;

@Enumeration
public class EventUserAction {
	public String MASTER_USER = "M";
	public String DUPLICATED = "D";
	public String DIFFERENT_USER = "U";
	public String LOCK = "L";
	public String UNLOCK = "O";
	public String DISABLE = "D";
	public String UNKNOWN = "K";
}
