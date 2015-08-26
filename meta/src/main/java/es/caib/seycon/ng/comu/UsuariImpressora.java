//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import com.soffid.mda.annotation.*;

@ValueObject ( translatedName="PrinterUser",
	 translatedPackage="com.soffid.iam.api")
public class UsuariImpressora {

	@Attribute(translated = "printer" )
	public java.lang.String codiImpressora;

	@Attribute(translated = "user" )
	public java.lang.String codiUsuari;

	@Nullable
	@Attribute(translated = "enabledByDefault" )
	public java.lang.Boolean perDefecte;

	@Nullable
	public java.lang.Long id;

	@Nullable
	@Attribute(translated = "fullName" )
	public java.lang.String nomComplert;

	@Nullable
	@Attribute(translated = "printerServerName" )
	public java.lang.String nomServidorImpressora;

}
