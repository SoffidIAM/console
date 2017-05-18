//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import java.util.Map;

import com.soffid.mda.annotation.*;

@ValueObject ( translatedName="Group",
	 translatedPackage="com.soffid.iam.api")
public class Grup {

	@Attribute(translated = "name" )
	public java.lang.String codi;

	@Nullable
	public java.lang.String quota;

	@Nullable
	@Attribute(translated = "driveLetter" )
	public java.lang.String unitatOfimatica;

	@Attribute(translated = "description" )
	public java.lang.String descripcio;

	@Nullable
	@Attribute(translated = "parentGroup" )
	public java.lang.String codiPare;

	@Nullable
	@Attribute(translated = "type" )
	public java.lang.String tipus;

	@Nullable
	@Attribute(translated = "driveServerName" )
	public java.lang.String nomServidorOfimatic;

	@Nullable
	public java.lang.Long id;

	@Nullable
	@Attribute(defaultValue = "false", translated = "obsolete" )
	public java.lang.Boolean obsolet;

	@Nullable
	@Attribute(translated = "organizational" )
	public java.lang.Boolean organitzatiu;

	@Nullable
	@Attribute(translated = "section" )
	public java.lang.String seccioPressupostaria;

	@Description ("Group custom attributes")
	@Nullable
	public Map<String,Object> attributes; 
}
