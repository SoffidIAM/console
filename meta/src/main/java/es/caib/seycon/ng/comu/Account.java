//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import java.util.Map;

import com.soffid.mda.annotation.*;

@ValueObject ( translatedName="Account",
	 translatedPackage="com.soffid.iam.api")
public abstract class Account {
	@Nullable
	public java.lang.Long id;

	@Description("Account name")
	public java.lang.String name;

	@Description("Account description")
	@Nullable
	public java.lang.String description;

	@Description("Account type")
	public es.caib.seycon.ng.comu.AccountType type;

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

	@Description("System where the acounts lives")
	@Attribute(translated = "system" )
	public java.lang.String dispatcher;

	@Description("Time stamp when the account was last updated on target system")
	@Nullable
	public java.util.Calendar lastUpdated;

	@Description("Time stamp when the password was last set on target system")
	@Nullable
	public java.util.Calendar lastPasswordSet;

	@Description("Time stamp when the password is expected to expire on target system")
	@Nullable
	public java.util.Calendar passwordExpiration;

	@Description("True if the account is disabled")
	@Attribute(defaultValue = "false")
	public boolean disabled;

	@Description("Account's user type. It is directly bound to a password policy depending on the system's password domain")
	public java.lang.String passwordPolicy;

	@Description ("Account attributes")
	@Nullable
	Map<String, Object> attributes;
	
	@Description("Read only attribute. Access level. See access level constants")
	@Nullable
	AccountAccessLevelEnum accessLevel;
}
