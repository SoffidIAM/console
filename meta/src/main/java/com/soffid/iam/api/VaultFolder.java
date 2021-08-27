package com.soffid.iam.api;

import com.soffid.iam.model.VaultFolderEntity;
import com.soffid.mda.annotation.Attribute;
import com.soffid.mda.annotation.Description;
import com.soffid.mda.annotation.JsonObject;
import com.soffid.mda.annotation.Nullable;
import com.soffid.mda.annotation.ValueObject;

import es.caib.seycon.ng.comu.AccountAccessLevelEnum;

@JsonObject(hibernateClass = VaultFolderEntity.class)
@ValueObject(cache=100, cacheTimeout=30000)
public class VaultFolder 
{
	@Nullable
	@Attribute(hidden = true)
	Long id;
	
	String name;
	
	@Attribute(hidden = true)
	boolean personal;
	
	String description;
	
	@Nullable
	@Attribute(hidden = true)
	Long parentId;
	
	@Nullable
	String parentFolder;

	@Description("Groups that can use the account using SSO")
	@Nullable
	@Attribute(defaultValue = "new java.util.LinkedList<String>()")
	public java.util.Collection<String> grantedGroups;

	@Description("Users that can use the account using SSO")
	@Nullable
	@Attribute(defaultValue = "new java.util.LinkedList<String>()")
	public java.util.Collection<String> grantedUsers;

	@Description("Roles that can use the account using SSO")
	@Nullable
	@Attribute(defaultValue = "new java.util.LinkedList<String>()")
	public java.util.Collection<String> grantedRoles;

	@Description("Groups that can use the account using SSO & Self Service")
	@Nullable
	@Attribute(defaultValue = "new java.util.LinkedList<String>()")
	public java.util.Collection<String> managerGroups;

	@Description("Users that can use the account using SSO & Self Service")
	@Nullable
	@Attribute(defaultValue = "new java.util.LinkedList<String>()")
	public java.util.Collection<String> managerUsers;

	@Description("Roles that can use the account using SSO & Self Service")
	@Nullable
	@Attribute(defaultValue = "new java.util.LinkedList<String>()")
	public java.util.Collection<String> managerRoles;

	@Description("Groups that can manage the account using SSO & Self Service & Console")
	@Nullable
	@Attribute(defaultValue = "new java.util.LinkedList<String>()")
	public java.util.Collection<String> ownerGroups;

	@Description("Users that can use the account using SSO & Self Service & Console")
	@Nullable
	@Attribute(defaultValue = "new java.util.LinkedList<String>()")
	public java.util.Collection<String> ownerUsers;

	@Description("Roles that can use the account using SSO & Self Serva	ice & Console")
	@Nullable
	@Attribute(defaultValue = "new java.util.LinkedList<String>()")
	public java.util.Collection<String> ownerRoles;

	@Description("Groups that can navigate")
	@Nullable
	@Attribute(defaultValue = "new java.util.LinkedList<String>()")
	public java.util.Collection<String> navigateGroups;

	@Description("Users that can navigate")
	@Nullable
	@Attribute(defaultValue = "new java.util.LinkedList<String>()")
	public java.util.Collection<String> navigateUsers;

	@Description("Roles that can use the account using SSO & Self Service & Console")
	@Nullable
	@Attribute(defaultValue = "new java.util.LinkedList<String>()")
	public java.util.Collection<String> navigateRoles;

	@Description("Effective access level")
	@Nullable
	public AccountAccessLevelEnum accessLevel;
	
	
	@Description("PAM Policy")
	@Nullable
	@Attribute(customUiHandler = "com.soffid.iam.web.vault.PamPolicyUiHandler")
	public String pamPolicy;

}
