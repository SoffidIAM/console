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

	@Description ("Domain value")
	@Attribute(translated = "value" )
	public java.lang.String valor;

	@Nullable
	@Description ("Unique identifier")
	public java.lang.Long id;

	@Nullable
	@Description ("Domain value description")
	@Attribute(translated = "description" )
	public java.lang.String descripcio;

	@Attribute(translated = "domainName" )
	@Description ("Domain name")
	public java.lang.String nomDomini;

	@Nullable
	@Attribute(translated = "externalCodeDomain" )
	@Description("Information system name")
	public java.lang.String codiExternDomini;

}
