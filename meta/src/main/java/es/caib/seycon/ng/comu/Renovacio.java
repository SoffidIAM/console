//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import com.soffid.mda.annotation.*;

@ValueObject ( translatedName="Renewal",
	 translatedPackage="com.soffid.iam.api")
public abstract class Renovacio {

	@Attribute(translated = "date" )
	public java.util.Calendar data;

	@Attribute(translated = "active" )
	public java.lang.String actiu;

	@Attribute(translated = "renewalDate" )
	public java.util.Calendar dataRenovacio;

	@Attribute(translated = "userCode" )
	public java.lang.String codiUsuari;

	@Nullable
	public java.lang.Long id;

}
