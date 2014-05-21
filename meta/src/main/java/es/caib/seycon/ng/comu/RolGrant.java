//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import com.soffid.mda.annotation.*;

@ValueObject ( translatedName="RoleGrant",
	 translatedPackage="com.soffid.iam.api")
public abstract class RolGrant {

	public java.lang.Long id;

	@Attribute(translated = "roleId" )
	public java.lang.Long idRol;

	@Attribute(translated = "roleName" )
	public java.lang.String rolName;

	@Attribute(translated = "system" )
	public java.lang.String dispatcher;

	@Nullable
	public java.lang.String domainValue;

	@Nullable
	public java.lang.String ownerAccountName;

	@Nullable
	public java.lang.String ownerDispatcher;

	@Nullable
	public java.lang.String ownerGroup;

	@Nullable
	@Attribute(translated = "ownerRole" )
	public java.lang.Long ownerRol;

	public boolean hasDomain;

	@Nullable
	@Attribute(translated = "ownerRoleName" )
	public java.lang.String ownerRolName;

	@Nullable
	public java.lang.String user;

}
