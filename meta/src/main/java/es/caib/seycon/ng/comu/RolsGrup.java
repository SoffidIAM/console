//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import com.soffid.mda.annotation.*;

@ValueObject ( translatedName="GroupRoles",
	 translatedPackage="com.soffid.iam.api")
public abstract class RolsGrup {

	@Nullable
	public java.lang.Long id;

	@Attribute(translated = "roleName" )
	public java.lang.String nomRol;

	@Attribute(translated = "roleDescription" )
	public java.lang.String descripcioRol;

	@Attribute(translated = "roleDatabases" )
	public java.lang.String baseDeDadesRol;

	@Attribute(translated = "applicationCode" )
	public java.lang.String codiAplicacio;

	@Attribute(translated = "groupCode" )
	public java.lang.String codiGrup;

	@Attribute(translated = "groupDescription" )
	public java.lang.String descripcioGrup;

	@Attribute(translated = "domainValue" )
	public es.caib.seycon.ng.comu.ValorDomini valorDomini;

}
