//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import java.util.Map;

import com.soffid.mda.annotation.*;

import es.caib.seycon.ng.model.LlistaCorreuEntity;
import es.caib.seycon.ng.model.UsuariEntity;

@JsonObject (hibernateClass=LlistaCorreuEntity.class)
@ValueObject ( translatedName="MailList",
	 translatedPackage="com.soffid.iam.api")
public class LlistaCorreu {

	@Description ("Mail list name")
	@Attribute(translated = "name" )
	public java.lang.String nom;

	@Description ("Mail description")
	@Nullable
	@Attribute(translated = "description" )
	public java.lang.String descripcio;

	@Description ("Mail domain")
	@Nullable
	@Attribute(translated = "domainCode" )
	public java.lang.String codiDomini;

	@Nullable
	public java.lang.Long id;

	@Nullable
	@Description ("Contains the exploded users list, resolving any group or role membership.")
	public java.lang.String explodedUsersList;

	@Nullable
	@Description ("Embeded mail lists")
	@Attribute(translated = "lists" )
	public java.lang.String llistaLlistes;

	@Nullable
	@Description ("External (unmanaged) mail lists that are subscribed to this one")
	@Attribute(translated = "externalList" )
	public java.lang.String llistaExterns;

	@Description ("Role whose gramtee should be subscribed to this list")
	@Nullable
	public java.lang.String roleMembers;

	@Description ("Business units whose membes should be subscribed to this list")
	@Nullable
	public java.lang.String groupMembers;

	@Description ("Contains the users that are directly subscribed to this mail list")
	@Nullable
	@Attribute(translated = "usersList" )
	public java.lang.String llistaUsuaris;


	@Description ("Mail lists that this one is subscribed to")
	@Nullable
	@Attribute(translated = "listsBelong" )
	public java.lang.String llistaLlistesOnPertany;

	@Description ("Mail list custom attributes")
	@JsonAttribute(hibernateJoin="attributes")
	@Attribute(defaultValue="new java.util.HashMap<String,Object>()")
	@Nullable
	public Map<String,Object> attributes; 
}
