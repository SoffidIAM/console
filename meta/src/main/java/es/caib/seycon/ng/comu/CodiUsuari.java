//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import com.soffid.mda.annotation.*;

@ValueObject ( translatedName="UserCode",
	 translatedPackage="com.soffid.iam.api")
public abstract class CodiUsuari {

	@Nullable
	public java.lang.Long id;

	@Nullable
	@Attribute(translated = "userCode" )
	public java.lang.String codiUsuari;

	@Nullable
	@Attribute(translated = "userId" )
	public java.lang.Long idUsuari;

	@Nullable
	@Attribute(translated = "userDomainId" )
	public java.lang.Long idDominiUsuari;

	@Nullable
	@Attribute(translated = "domainCode" )
	public java.lang.String codiDomini;

	@Nullable
	@Attribute(translated = "domainDescription" )
	public java.lang.String descripcioDomini;

	@Nullable
	@Attribute(translated = "domainType" )
	public es.caib.seycon.ng.comu.TipusDominiUsuariEnumeration tipusDomini;

}
