//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import com.soffid.mda.annotation.*;

@ValueObject ( translatedName="Student",
	 translatedPackage="com.soffid.iam.api")
public class UsuariAlumne {

	@Nullable
	@Attribute(translated = "userCode" )
	public java.lang.String codiUsuari;

	@Nullable
	public java.lang.String contrasenya;

	public java.lang.String codiXestib;

	public java.lang.String nom;

	public java.lang.String llinatge1;

	@Nullable
	public java.lang.String llinatge2;

	@Attribute(translated = "school" )
	public java.lang.String codiCentre;

	public java.lang.String grupAlumne;

	@Nullable
	public java.lang.String correuElectronic;

}
