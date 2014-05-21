//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import com.soffid.mda.annotation.*;

@ValueObject ( translatedName="NetworkAuthorization",
	 translatedPackage="com.soffid.iam.api")
public abstract class NetworkAuthorization {

	@Attribute(translated = "identity" )
	public es.caib.seycon.ng.comu.Identitat identitat;

	@Attribute(translated = "level" )
	public java.lang.Integer nivell;

	@Nullable
	@Attribute(translated = "mask" )
	public java.lang.String mascara;

	@Attribute(translated = "networkCode" )
	public java.lang.String codiXarxa;

	@Nullable
	public java.lang.Long id;

}
