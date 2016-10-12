package es.caib.bpm.vo;

import com.soffid.mda.annotation.Enumeration;

@Enumeration
public class PredefinedProcessType {
	public final String ROLE_GRANT_APPROVAL = "RoleApproval";
	public final String ROLE_DEFINITION_APPROVAL = "RoleDefApproval";
	public final String AUTHORITATIVE_CHANGE = "AuthoritativeChange";
	public final String PRIVILEGED_ACCOUNT = "PrivilegedAccount";
}
