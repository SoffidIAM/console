//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import com.soffid.mda.annotation.*;

@ValueObject ( translatedName="ApplicationAdministration",
	 translatedPackage="com.soffid.iam.api")
public class AdministracioAplicacio {

	@Attribute(translated = "informationSystemName" )
	public java.lang.String codiAplicacio;

	@Attribute(translated = "roleName" )
	public java.lang.String nomRol;

	@Attribute(translated = "userName" )
	public java.lang.String codiUsuari;

	@Nullable
	public java.lang.Long id;

	@Nullable
	@Attribute(translated = "userFullName" )
	public java.lang.String nomComplertUsuari;

	@Attribute(translated = "roleInformationSystem" )
	public java.lang.String codiAplicacioRol;

	@Attribute(translated = "roleSystemName" )
	public java.lang.String codiBaseDeDadesRol;

}
