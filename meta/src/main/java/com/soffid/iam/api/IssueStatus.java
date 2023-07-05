package com.soffid.iam.api;

import com.soffid.mda.annotation.Enumeration;

@Enumeration
public class IssueStatus {
	public String NEW = "N";
	public String ACKNOWLEDGED = "A";
	public String SOLVED = "S";
	public String SOLVED_NOTADUPLICATE = "D";
}
