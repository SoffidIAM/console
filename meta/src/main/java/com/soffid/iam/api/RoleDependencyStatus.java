package com.soffid.iam.api;

import com.soffid.mda.annotation.Enumeration;
import com.soffid.mda.annotation.ValueObject;

@Enumeration
public class RoleDependencyStatus {
	public final static String STATUS_ACTIVE = "A";
	public final static String STATUS_TOAPPROVE = "P";
	public final static String STATUS_TOREMOVE = "R";
	
}
