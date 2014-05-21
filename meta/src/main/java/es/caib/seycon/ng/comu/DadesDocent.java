//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import com.soffid.mda.annotation.*;

@ValueObject ( translatedName="TeachingData",
	 translatedPackage="com.soffid.iam.api")
public abstract class DadesDocent {

	@Attribute(translated = "name" )
	public java.lang.String nom;

	@Attribute(translated = "lastName1" )
	public java.lang.String llinatge1;

	@Nullable
	@Attribute(translated = "lastName2" )
	public java.lang.String llinatge2;

	@Attribute(translated = "main" )
	public java.lang.String centrePrincipal;

	@Nullable
	@Attribute(translated = "secondary" )
	public java.util.Collection<java.lang.String> centresSecundaris;

}
