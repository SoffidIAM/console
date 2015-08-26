//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import com.soffid.mda.annotation.*;

@ValueObject ( translatedName="ExtranetCard",
	 translatedPackage="com.soffid.iam.api")
public class TargetaExtranet {

	@Nullable
	public java.lang.Long id;

	@Nullable
	@Attribute(translated = "userCode" )
	public java.lang.String codiUsuari;

	@Nullable
	@Attribute(translated = "code" )
	public java.lang.String codi;

	@Nullable
	@Attribute(translated = "outputDate" )
	public java.util.Calendar dataEmissio;

	@Nullable
	@Attribute(translated = "expirationDate" )
	public java.util.Calendar dataCaducitat;

	@Nullable
	@Attribute(translated = "active" )
	public java.lang.String actiu;

	@Nullable
	@Attribute(translated = "content" )
	public java.util.Collection<es.caib.seycon.ng.comu.ContingutTargetaExtranet> contingut;

}
