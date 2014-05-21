//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import com.soffid.mda.annotation.*;

@ValueObject ( translatedName="RoleAssociation",
	 translatedPackage="com.soffid.iam.api")
public abstract class RolAssociacioRol {

	@Nullable
	@Attribute(translated = "roleParentContainer" )
	public es.caib.seycon.ng.comu.Rol rolContenidorPare;

	@Nullable
	@Attribute(translated = "roleContentGrant" )
	public es.caib.seycon.ng.comu.Rol rolContingutAtorgat;

	@Nullable
	@Attribute(translated = "domainTypeRoleGrant" )
	public java.lang.String tipusDominiRolAtorgat;

	@Nullable
	@Attribute(translated = "applicationCodeDomainRoleGrant" )
	public java.lang.String codiAplicacioDominiRolAtorgat;

	@Nullable
	@Attribute(translated = "groupCodeDomainRoleGrant" )
	public java.lang.String codiGrupDominiRolAtorgat;

	@Nullable
	@Attribute(translated = "applicationDomainValueRoleGrant" )
	public es.caib.seycon.ng.comu.ValorDomini valorDominiAplicacioRolAtorgat;

}
