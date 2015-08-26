//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import com.soffid.mda.annotation.*;

@ValueObject ( translatedName="ParentData",
	 translatedPackage="com.soffid.iam.api")
public class DadesPare {

	@Attribute(translated = "name" )
	public java.lang.String nom;

	@Attribute(translated = "lastName1" )
	public java.lang.String llinatge1;

	@Nullable
	@Attribute(translated = "lastName2" )
	public java.lang.String llinatge2;

	@Nullable
	public java.lang.String email;

}
