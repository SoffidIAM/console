//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import com.soffid.mda.annotation.*;

@ValueObject ( translatedName="AnonimousUser",
	 translatedPackage="com.soffid.iam.api")
public abstract class UsuariAnonim {

	@Attribute(translated = "name" )
	public java.lang.String nom;

	@Attribute(translated = "lastName" )
	public java.lang.String llinatge1;

	@Attribute(translated = "lasName2" )
	public java.lang.String llinatge2;

	@Attribute(translated = "email" )
	public java.lang.String correuElectronic;

	@Attribute(translated = "userCode" )
	public java.lang.String codiUsuari;

}
