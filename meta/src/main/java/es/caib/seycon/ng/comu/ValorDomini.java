//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import com.soffid.mda.annotation.*;

@ValueObject ( translatedName="DomainValue",
	 translatedPackage="com.soffid.iam.api")
public abstract class ValorDomini {

	@Attribute(translated = "value" )
	public java.lang.String valor;

	@Nullable
	public java.lang.Long id;

	@Nullable
	@Attribute(translated = "description" )
	public java.lang.String descripcio;

	@Attribute(translated = "domainName" )
	public java.lang.String nomDomini;

	@Nullable
	@Attribute(translated = "externalCodeDomain" )
	public java.lang.String codiExternDomini;

}
