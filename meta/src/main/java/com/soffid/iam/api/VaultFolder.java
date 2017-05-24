package com.soffid.iam.api;

import com.soffid.mda.annotation.Description;
import com.soffid.mda.annotation.Nullable;
import com.soffid.mda.annotation.ValueObject;

import es.caib.seycon.ng.comu.AccountAccessLevelEnum;

@ValueObject(cache=100, cacheTimeout=30000)
public class VaultFolder 
{
	@Nullable
	Long id;
	
	String name;
	
	boolean personal;
	
	String description;
	
	@Nullable
	Long parentId;
	
	@Nullable
	String parentFolder;

	@Description("Groups that can use the account using SSO")
	@Nullable
	public java.util.Collection<es.caib.seycon.ng.comu.Grup> grantedGroups;

	@Description("Users that can use the account using SSO")
	@Nullable
	public java.util.Collection<es.caib.seycon.ng.comu.Usuari> grantedUsers;

	@Description("Roles that can use the account using SSO")
	@Nullable
	public java.util.Collection<es.caib.seycon.ng.comu.Rol> grantedRoles;

	@Description("Groups that can use the account using SSO & Self Service")
	@Nullable
	public java.util.Collection<es.caib.seycon.ng.comu.Grup> managerGroups;

	@Description("Users that can use the account using SSO & Self Service")
	@Nullable
	public java.util.Collection<es.caib.seycon.ng.comu.Usuari> managerUsers;

	@Description("Roles that can use the account using SSO & Self Service")
	@Nullable
	public java.util.Collection<es.caib.seycon.ng.comu.Rol> managerRoles;

	@Description("Groups that can manage the account using SSO & Self Service & Console")
	@Nullable
	public java.util.Collection<es.caib.seycon.ng.comu.Grup> ownerGroups;

	@Description("Users that can use the account using SSO & Self Service & Console")
	@Nullable
	public java.util.Collection<es.caib.seycon.ng.comu.Usuari> ownerUsers;

	@Description("Roles that can use the account using SSO & Self Service & Console")
	@Nullable
	public java.util.Collection<es.caib.seycon.ng.comu.Rol> ownerRoles;

	@Description("Groups that can navigate")
	@Nullable
	public java.util.Collection<Long> navigateGroups;

	@Description("Users that can navigate")
	@Nullable
	public java.util.Collection<Long> navigateUsers;

	@Description("Roles that can use the account using SSO & Self Service & Console")
	@Nullable
	public java.util.Collection<Long> navigateRoles;

	@Description("Effective access level")
	@Nullable
	public AccountAccessLevelEnum accessLevel;
}
