//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import com.soffid.mda.annotation.*;

@ValueObject ( translatedName="DomainUsersMember",
	 translatedPackage="com.soffid.iam.api")
public abstract class DominiUsuarisMember {

	@Nullable
	@Attribute(translated = "code" )
	public java.lang.String codi;

	@Attribute(translated = "type" )
	public java.lang.String tipus;

	@Nullable
	@Attribute(translated = "description" )
	public java.lang.String descripcio;

	@Nullable
	@Attribute(translated = "descriptionType" )
	public java.lang.String descripcioTipus;

	@Nullable
	@Attribute(translated = "userDomain" )
	public es.caib.seycon.ng.comu.DominiUsuari dominiUsuari;

	@Nullable
	@Attribute(translated = "passwordDomain" )
	public es.caib.seycon.ng.comu.DominiContrasenya dominiContrasenya;

	@Nullable
	@Attribute(translated = "passwordPolicy" )
	public es.caib.seycon.ng.comu.PoliticaContrasenya politicaContrasenya;

}
