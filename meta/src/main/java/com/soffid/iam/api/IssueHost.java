package com.soffid.iam.api;

import com.soffid.mda.annotation.ValueObject;

@ValueObject
public class IssueHost {
	HostEventAction action;
	Long hostId;
	String hostName;
}
