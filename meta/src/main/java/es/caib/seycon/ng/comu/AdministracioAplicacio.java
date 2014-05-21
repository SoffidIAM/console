//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import com.soffid.mda.annotation.*;

@ValueObject ( translatedName="ApplicationAdministration",
	 translatedPackage="com.soffid.iam.api")
public abstract class AdministracioAplicacio {

	@Attribute(translated = "applicationCode" )
	public java.lang.String codiAplicacio;

	@Attribute(translated = "roleName" )
	public java.lang.String nomRol;

	@Attribute(translated = "userCode" )
	public java.lang.String codiUsuari;

	@Nullable
	public java.lang.Long id;

	@Nullable
	@Attribute(translated = "userFullName" )
	public java.lang.String nomComplertUsuari;

	@Attribute(translated = "applicationRoleCode" )
	public java.lang.String codiAplicacioRol;

	@Attribute(translated = "databaseRoleCode" )
	public java.lang.String codiBaseDeDadesRol;

}
