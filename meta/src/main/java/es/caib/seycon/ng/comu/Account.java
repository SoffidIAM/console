//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import com.soffid.mda.annotation.*;

@ValueObject ( translatedName="Account",
	 translatedPackage="com.soffid.iam.api")
public abstract class Account {

	@Nullable
	public java.lang.Long id;

	public java.lang.String name;

	@Nullable
	public java.lang.String description;

	public es.caib.seycon.ng.comu.AccountType type;

	@Nullable
	public java.util.Collection<es.caib.seycon.ng.comu.Grup> grantedGroups;

	@Nullable
	public java.util.Collection<es.caib.seycon.ng.comu.Usuari> grantedUsers;

	@Nullable
	public java.util.Collection<es.caib.seycon.ng.comu.Rol> grantedRoles;

	@Attribute(translated = "system" )
	public java.lang.String dispatcher;

	@Nullable
	public java.util.Calendar lastUpdated;

	@Nullable
	public java.util.Calendar lastPasswordSet;

	@Nullable
	public java.util.Calendar passwordExpiration;

	@Attribute(defaultValue = "false")
	public boolean disabled;

	public java.lang.String passwordPolicy;

}
