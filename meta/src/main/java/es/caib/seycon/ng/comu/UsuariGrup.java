//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import java.util.Date;
import java.util.Map;

import com.soffid.mda.annotation.*;

@ValueObject ( translatedName="GroupUser",
	 translatedPackage="com.soffid.iam.api")
public class UsuariGrup {

	@Attribute(translated = "user" )
	public java.lang.String codiUsuari;

	@Attribute(translated = "group" )
	public java.lang.String codiGrup;

	@Nullable
	@Attribute(translated = "groupDescription" )
	public java.lang.String descripcioGrup;

	@Nullable
	public java.lang.Long id;

	@Attribute(translated = "fullName" )
	public java.lang.String nomComplet;

	@Nullable
	public java.lang.String info;

	@Nullable
	public Date start;

	@Nullable
	public Date end;

	@Nullable
	@Attribute(defaultValue="Boolean.FALSE")
	public Boolean disabled;

	@Description("This column indicates that this membership is an historic snapshot of a primary group membership")
	@Attribute(defaultValue="Boolean.FALSE")
	@Nullable
	public Boolean primaryGroup;

	@Description ("User group custom attributes")
	@JsonAttribute(hibernateJoin="attributes")
	@Attribute(defaultValue="new java.util.HashMap<String,Object>()")
	@Nullable
	public Map<String,Object> attributes; 
}
