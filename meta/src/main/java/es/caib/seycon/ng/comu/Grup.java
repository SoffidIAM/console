//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import java.util.Map;

import com.soffid.mda.annotation.Attribute;
import com.soffid.mda.annotation.Description;
import com.soffid.mda.annotation.JsonAttribute;
import com.soffid.mda.annotation.JsonObject;
import com.soffid.mda.annotation.Nullable;
import com.soffid.mda.annotation.ValueObject;

import es.caib.seycon.ng.model.GrupEntity;

@JsonObject (hibernateClass=GrupEntity.class)
@ValueObject ( translatedName="Group",
	cache=300,
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
	@JsonAttribute(hibernateAttribute="-")
	public java.lang.Boolean obsolet;

	@Nullable
	@Attribute(translated = "organizational" )
	@JsonAttribute(hibernateAttribute="-")
	public java.lang.Boolean organitzatiu;

	@Nullable
	@Attribute(translated = "section" )
	public java.lang.String seccioPressupostaria;

	@Description ("Group custom attributes")
	@Nullable
	@JsonAttribute(hibernateJoin="attributes")
	@Attribute(defaultValue="new java.util.HashMap<String,Object>()")
	public Map<String,Object> attributes; 
}
