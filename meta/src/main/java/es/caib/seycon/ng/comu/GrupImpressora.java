//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import com.soffid.mda.annotation.*;

@ValueObject ( translatedName="PrinterGroup",
	 translatedPackage="com.soffid.iam.api")
public class GrupImpressora {

	@Attribute(translated = "enabledByDefault" )
	public java.lang.Boolean perDefecte;

	@Attribute(translated = "groupCode" )
	public java.lang.String codiGrup;

	@Attribute(translated = "printerCode" )
	public java.lang.String codiImpressora;

	@Nullable
	public java.lang.Long id;

	@Nullable
	@Attribute(translated = "printerServerName" )
	public java.lang.String nomServidorImpressora;

}
