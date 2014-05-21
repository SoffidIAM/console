//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import com.soffid.mda.annotation.*;

@ValueObject ( translatedName="ExternalName",
	 translatedPackage="com.soffid.iam.api")
public abstract class CorreuExtern {

	@Attribute(translated = "email" )
	public java.lang.String adreca;

	@Attribute(translated = "mailListName" )
	public java.lang.String llistaCorreuNom;

	@Nullable
	@Attribute(translated = "domainCode" )
	public java.lang.String codiDomini;

	@Nullable
	public java.lang.Long id;

}
