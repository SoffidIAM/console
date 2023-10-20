package com.soffid.iam.api;

import java.util.Date;

import com.soffid.mda.annotation.ValueObject;

@ValueObject
public class PasswordDomainStatus {
	String domainName;
	Date lockedUntil;
	Integer failures;
}
