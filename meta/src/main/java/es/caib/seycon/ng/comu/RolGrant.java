//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import java.util.Date;

import com.soffid.iam.api.RoleDependencyStatus;
import com.soffid.mda.annotation.*;

@ValueObject ( translatedName="RoleGrant",
	 translatedPackage="com.soffid.iam.api")
public class RolGrant {
	@Nullable
	public java.lang.Long id;

	@Attribute(translated = "roleId" )
	public java.lang.Long idRol;

	@Attribute(translated = "roleName" )
	public java.lang.String rolName;

	@Nullable
	String roleDescription;
	
	@Attribute(translated = "system" )
	public java.lang.String dispatcher;
	
	@Nullable
	public java.lang.String informationSystem;

	@Description("true if the grant has domain or scope")
	public boolean hasDomain;

	@Description("The grant domain or scope")
	@Nullable
	public java.lang.String domainValue;

	@Description("The grant domain or scope description")
	@Nullable
	public java.lang.String domainDescription;

	@Nullable
	public java.lang.String ownerAccountName;

	@Nullable
	public java.lang.String ownerInformationSystem;

	@Nullable
	@Attribute(translated="ownerSystem")
	public java.lang.String ownerDispatcher;

	@Description ("Owner group, if any. Applies to roles granted to groups")
	@Nullable
	public java.lang.String ownerGroup;

	@Description ("Owner role id, if any. Applies to roles granted to roles")
	@Nullable
	@Attribute(translated = "ownerRole" )
	public java.lang.Long ownerRol;

	@Description ("Scope to be applied to owner role grant")
	@Nullable
	public java.lang.String ownerRolDomainValue;

	@Nullable
	@Attribute(translated = "ownerRoleName" )
	public java.lang.String ownerRolName;

	@Nullable
	@Attribute(translated = "ownerRoleDescription" )
	public java.lang.String ownerRolDescription;

	@Nullable
	public java.lang.String user;

	@Description ("Rol assignment start date. Null means since now")
	@Nullable
	public Date startDate;
	
	@Description ("Rol assignment end date. Null means forever")
	@Nullable
	public Date endDate;

	@Attribute(defaultValue="true")
	public boolean enabled;
	
	@Description("This attribute holds the group name that is bound to this the role assignment. Not applicable for shared accounts")
	@Nullable
	public String holderGroup;
	
	@Nullable
	@Attribute(defaultValue = "com.soffid.iam.api.RoleDependencyStatus.STATUS_TOAPPROVE")
	public RoleDependencyStatus status;
	
	@Description("True if the role is always granted. False if role grant is optional, and thus can be removed from user entitlements form")
	@Column (name="RRL_MANDAT", defaultValue="true")
	@Nullable
	public Boolean mandatory;
}
