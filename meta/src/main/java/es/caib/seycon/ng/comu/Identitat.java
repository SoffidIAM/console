//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import com.soffid.mda.annotation.*;

@ValueObject ( translatedName="Identity",
	 translatedPackage="com.soffid.iam.api")
public class Identitat {

	@Nullable
	@Attribute(translated = "userCode" )
	public java.lang.String codiUsuari;

	@Nullable
	@Attribute(translated = "groupCode" )
	public java.lang.String codiGrup;

	@Nullable
	@Attribute(translated = "roleName" )
	public java.lang.String nomRol;

	@Attribute(translated = "description" )
	public java.lang.String descripcio;

	@Attribute(translated = "identityCode" )
	public java.lang.String codiIdentitat;

}
