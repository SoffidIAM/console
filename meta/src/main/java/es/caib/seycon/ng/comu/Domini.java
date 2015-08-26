//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import com.soffid.mda.annotation.*;

@ValueObject ( translatedName="Domain",
	 translatedPackage="com.soffid.iam.api")
public class Domini {

	@Nullable
	@Description("Unique identifier")
	public java.lang.Long id;

	@Attribute(translated = "name" )
	@Description ("Domain name")
	public java.lang.String nom;

	@Nullable
	@Description ("Information system name")
	@Attribute(translated = "externalCode" )
	public java.lang.String codiExtern;

	@Nullable
	@Description ("Domain description")
	@Attribute(translated = "description" )
	public java.lang.String descripcio;

}
