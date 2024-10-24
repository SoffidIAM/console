//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import java.util.Date;
import java.util.Map;

import com.soffid.mda.annotation.*;

import es.caib.seycon.ng.model.UsuariGrupEntity;

@JsonObject (hibernateClass=UsuariGrupEntity.class)
@ValueObject ( translatedName="GroupUser",
	 translatedPackage="com.soffid.iam.api")
public class UsuariGrup {

	@JsonAttribute(hibernateAttribute = "user.userName")
	@Attribute(translated = "user", type = "USER", searchCriteria = true )
	public java.lang.String codiUsuari;

	@JsonAttribute(hibernateAttribute = "group.name")
	@Attribute(translated = "group", type = "GROUP", searchCriteria = true )
	public java.lang.String codiGrup;

	@Nullable
	@JsonAttribute(hibernateAttribute = "group.description")
	@Attribute(translated = "groupDescription", hidden = true )
	public java.lang.String descripcioGrup;

	@Nullable
	@JsonAttribute(hibernateAttribute = "group.id")
	@Attribute(hidden = true )
	public java.lang.Long groupId;

	@Nullable
	@Attribute(hidden=true)
	public java.lang.Long id;

	@Nullable
	@JsonAttribute(hibernateAttribute = "user.id")
	@Attribute(hidden = true )
	public java.lang.Long userId;


	@Attribute(translated = "fullName", hidden = true )
	@Nullable
	public java.lang.String nomComplet;

	@Nullable
	@Attribute(hidden = true)
	public java.lang.String info;

	@Nullable
	@Attribute(hidden=true)
	public Date start;

	@Nullable
	@Attribute(hidden=true)
	public Date end;

	@Nullable
	@Attribute(defaultValue="Boolean.FALSE", hidden = true)
	public Boolean disabled;

	@Description("This column indicates that this membership is an historic snapshot of a primary group membership")
	@Attribute(defaultValue="Boolean.FALSE", hidden = true)
	@Nullable
	public Boolean primaryGroup;

	@Description ("User group custom attributes")
	@JsonAttribute(hibernateJoin="attributes")
	@Attribute(defaultValue="new java.util.HashMap<String,Object>()", hidden = true)
	@Nullable
	public Map<String,Object> attributes; 
}
