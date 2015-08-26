//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import com.soffid.mda.annotation.*;

@ValueObject ( translatedName="Configuration",
	 translatedPackage="com.soffid.iam.api")
public class Configuracio {

	@Attribute(translated = "code" )
	public java.lang.String codi;

	@Attribute(translated = "value" )
	public java.lang.String valor;

	@Nullable
	@Attribute(translated = "networkCode" )
	public java.lang.String codiXarxa;

	@Nullable
	@Attribute(translated = "description" )
	public java.lang.String descripcio;

	@Nullable
	public java.lang.Long id;

}
