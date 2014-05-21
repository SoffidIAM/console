//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import com.soffid.mda.annotation.*;

@ValueObject ( translatedName="UserType",
	 translatedPackage="com.soffid.iam.api")
public abstract class TipusUsuari {

	@Nullable
	public java.lang.Long id;

	@Nullable
	@Attribute(translated = "code" )
	public java.lang.String codi;

	@Nullable
	@Attribute(translated = "description" )
	public java.lang.String descripcio;

}
