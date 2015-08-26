//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import com.soffid.mda.annotation.*;

@ValueObject ( translatedName="AuthorizationRole",
	 translatedPackage="com.soffid.iam.api")
public class AutoritzacioRol {

	@Nullable
	public java.lang.Long id;

	@Attribute(translated = "authorization" )
	public java.lang.String autoritzacio;

	@Attribute(translated = "role" )
	public es.caib.seycon.ng.comu.Rol rol;

	@Nullable
	@Attribute(translated = "userRoleValueDomain" )
	public java.util.Collection<es.caib.seycon.ng.comu.ValorDomini> valorDominiRolUsuari;

	@Nullable
	@Attribute(translated = "description" )
	public java.lang.String descripcio;

	@Nullable
	@Attribute(translated = "domainType" )
	public java.lang.String tipusDomini;

	@Nullable
	@Attribute(translated = "businessGroupScope" )
	public java.lang.String scope;

	@Nullable
	@Attribute(translated = "scope" )
	public java.lang.String ambit;

	@Nullable
	@Attribute(translated = "inherit" )
	public java.lang.String hereta;

}
