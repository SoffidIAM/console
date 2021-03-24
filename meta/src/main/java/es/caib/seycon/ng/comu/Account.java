//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import java.util.Map;

import com.soffid.iam.api.AccountStatus;
import com.soffid.mda.annotation.*;

import es.caib.seycon.ng.model.AccountEntity;

@JsonObject (hibernateClass=AccountEntity.class)
@ValueObject ( translatedName="Account",
	 translatedPackage="com.soffid.iam.api")
public class Account {
	@Nullable
	@Attribute(hidden = true)
	public java.lang.Long id;

	@Description("System where the acounts lives")
	@Attribute(separator = "_commonAttributes", translated = "system", customUiHandler = "com.soffid.iam.web.account.SystemFieldHandler" )
	@JsonAttribute(hibernateAttribute="system.name")
	public java.lang.String dispatcher;
	
	@Description("Account name")
	@Attribute(searchCriteria = true, customUiHandler = "com.soffid.iam.web.account.AccountNameHandler")
	public java.lang.String name;
	
	@Description("Old account name")
	@Nullable
	@Attribute(hidden = true)
	public java.lang.String oldName;

	@Description("Account description")
	@Nullable
	public java.lang.String description;

	@Description("Account type")
	@JsonAttribute(hibernateAttribute="type")
	@Attribute(customUiHandler = "com.soffid.iam.web.account.AccountTypeHandler")
	public es.caib.seycon.ng.comu.AccountType type;

	@Description("True if the account is disabled. Better use status attribute")
	@Attribute(defaultValue = "false", hidden = true)
	public boolean disabled;
	
	@Description("Account status: active / disabled / removed")
	@JsonAttribute(hibernateAttribute="status")
	@Attribute(customUiHandler = "com.soffid.iam.web.account.AccountStatusHandler")
	@Nullable
	public AccountStatus status;
	
	@Description("Account's user type. It is directly bound to a password policy depending on the system's password domain")
	@JsonAttribute(hibernateAttribute="passwordPolicy.name")
	@Attribute(type="USER_TYPE")
	public java.lang.String passwordPolicy;
	
	@Description("Groups that can manage the account using SSO & Self Service & Console")
	@Nullable
	@Attribute(hidden=false, separator="_owners", type = "GROUP", multivalue = true, customUiHandler = "com.soffid.iam.web.account.AccountOwnerHandler")
	public java.util.Collection<String> ownerGroups;
	
	@Description("Users that can use the account using SSO & Self Service & Console")
	@Nullable
	@Attribute(hidden=false, type = "USER",  multivalue = true, customUiHandler = "com.soffid.iam.web.account.AccountOwnerUserHandler")
	public java.util.Collection<String> ownerUsers;
	
	@Description("Roles that can use the account using SSO & Self Service & Console")
	@Nullable
	@Attribute(hidden=false, type = "ROLE",  multivalue = true, customUiHandler = "com.soffid.iam.web.account.AccountOwnerHandler")
	public java.util.Collection<String> ownerRoles;
	
	@Description("Groups that can use the account using SSO & Self Service")
	@Nullable
	@Attribute(hidden=false, type = "GROUP", separator="_managers",  multivalue = true, customUiHandler = "com.soffid.iam.web.account.AccountOwnerHandler")
	public java.util.Collection<String> managerGroups;
	
	@Description("Users that can use the account using SSO & Self Service")
	@Nullable
	@Attribute(hidden=false, type = "USER",  multivalue = true, customUiHandler = "com.soffid.iam.web.account.AccountOwnerHandler")
	public java.util.Collection<String> managerUsers;
	
	@Description("Roles that can use the account using SSO & Self Service")
	@Nullable
	@Attribute(hidden=false, type = "ROLE",  multivalue = true, customUiHandler = "com.soffid.iam.web.account.AccountOwnerHandler")
	public java.util.Collection<String> managerRoles;
	
	@Description("Groups that can use the account using SSO")
	@Nullable
	@Attribute(hidden=false, type="GROUP", separator="_sso",  multivalue = true, customUiHandler = "com.soffid.iam.web.account.AccountOwnerHandler")
	public java.util.Collection<String> grantedGroups;

	@Description("Users that can use the account using SSO")
	@Nullable
	@Attribute(hidden=false, type="USER",  multivalue = true, customUiHandler = "com.soffid.iam.web.account.AccountOwnerHandler")
	public java.util.Collection<String> grantedUsers;

	@Description("Roles that can use the account using SSO")
	@Nullable
	@Attribute(hidden=false, type="ROLE",  multivalue = true, customUiHandler = "com.soffid.iam.web.account.AccountOwnerHandler")
	public java.util.Collection<String> grantedRoles;

	@Description("Read only attribute. Access level. See access level constants")
	@Nullable
	@Attribute(hidden=true)
	AccountAccessLevelEnum accessLevel;
	
	@Description("Server type for password synchronization")
	@Nullable
	@Attribute(separator = "_passwordProperties", customUiHandler = "com.soffid.iam.web.account.ServerTypeHandler", listOfValues = {"Windows", "Linux", "Database"})
	String serverType;
	
	@Description("Server name. Used for SSO accounts")
	@Nullable
	@Attribute(customUiHandler = "com.soffid.iam.web.account.ServerNameHandler")
	String serverName;
	
	@Nullable
	@Attribute(customUiHandler = "com.soffid.iam.web.account.VaultFieldHandler", separator = "_vault")
	Long vaultFolderId;
	
	@Nullable
	@Attribute(hidden=true)
	String vaultFolder;

	@Attribute(defaultValue="false")
	boolean inheritNewPermissions;

	@Description("Login URL")
	@Nullable
	@Attribute(separator = "_launchProperties")
	String loginUrl;

	@Description("Login name. Used for SSO accounts")
	@Attribute(customUiHandler="com.soffid.iam.web.account.LoginNameHandler")
	@Nullable
	String loginName;

	@Nullable
	com.soffid.iam.api.LaunchType launchType;
	
	@Nullable
	@Attribute(customUiHandler = "com.soffid.iam.web.account.JumpServerGroupFieldHandler" )
	String jumpServerGroup;

	@Description("Creation date")
	@Nullable
	@Attribute(type = "DATE_TIME", readonly = true, separator = "_audit")
	public java.util.Date created;

	@Description("Time stamp when the account was last used on target system")
	@Nullable
	@Attribute(type = "DATE_TIME", readonly = true)
	public java.util.Calendar lastLogin;

	@Description("Time stamp when the account was last updated on target system")
	@Nullable
	@Attribute(type = "DATE_TIME", readonly = true)
	public java.util.Calendar lastUpdated;

	@Description("Time stamp when the password was last set on target system")
	@Nullable
	@Attribute(type = "DATE_TIME", readonly = true)
	public java.util.Calendar lastPasswordSet;

	@Description("Time stamp when the password is expected to expire on target system")
	@Nullable
	@Attribute(type = "DATE_TIME", readonly = true)
	public java.util.Calendar passwordExpiration;

	@Nullable
	@Description ("Current user that is using this privileged account. Does not apply for other accounts")
	@Attribute(readonly = true, type = "USER")
	String lockedBy;
	
	@Nullable
	@Attribute(readonly = true)
	PasswordValidation passwordStatus;

	@Description ("Account attributes")
	@Nullable
	@JsonAttribute(hibernateJoin="attributes")
	@Attribute(hidden=true, defaultValue = "new java.util.HashMap<String,Object>()")
	Map<String, Object> attributes;
}


