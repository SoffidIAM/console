//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import com.soffid.mda.annotation.*;

@ValueObject ( translatedName="OUType",
	 translatedPackage="com.soffid.iam.api")
public class TipusUnitatOrganitzativa {

	@Attribute(translated = "code" )
	public java.lang.String codi;

	@Nullable
	@Attribute(translated = "description" )
	public java.lang.String descripcio;

	@Nullable
	public java.lang.Long id;

	@Description("True if business units of this type can hold user roles")
	@Nullable
	public boolean roleHolder;
}
