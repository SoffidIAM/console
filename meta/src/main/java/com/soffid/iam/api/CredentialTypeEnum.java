package com.soffid.iam.api;

import com.soffid.mda.annotation.Enumeration;

@Enumeration
public class CredentialTypeEnum {
	public String CT_PASSWORD = "PASS";
//	public String CT_CERTIFICATE = "X509";
//	public String CT_SSHKEY = "SSH";
	public String CT_KUBERNETES = "KUBE";
}
