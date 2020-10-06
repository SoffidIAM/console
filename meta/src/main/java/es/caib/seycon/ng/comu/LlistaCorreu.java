//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import java.util.List;
import java.util.Map;

import com.soffid.mda.annotation.*;

import es.caib.seycon.ng.model.LlistaCorreuEntity;
import es.caib.seycon.ng.model.UsuariEntity;

@JsonObject (hibernateClass=LlistaCorreuEntity.class)
@ValueObject ( translatedName="MailList",
	 translatedPackage="com.soffid.iam.api")
public class LlistaCorreu {

	@Description ("Mail list name")
	@Attribute(translated = "name", type="STRING", searchCriteria = true )
	public java.lang.String nom;

	@Description ("Mail domain")
	@Attribute(translated = "domainName", synonyms= {"domainCode"}, type="MAIL_DOMAIN", searchCriteria = true, filterExpression = "obsolete eq false" )
	@JsonAttribute(hibernateAttribute = "domain.name")
	public java.lang.String codiDomini;
	
	@Description ("Mail description")
	@Nullable
	@Attribute(translated = "description", type="STRING", searchCriteria = true )
	public java.lang.String descripcio;

	@Nullable
	@Attribute(hidden=true)
	public java.lang.Long id;

	@Nullable
	@Description ("Embeded mail lists")
	@Attribute(translated = "lists", type="MAIL_LIST", multivalue = true )
	public List<java.lang.String> llistaLlistes;

	@Nullable
	@Description ("External (unmanaged) mail lists that are subscribed to this one")
	@Attribute(translated = "externalList", type="EMAIL", multivalue = true )
	public List<java.lang.String> llistaExterns;

	@Description ("Role whose gramtee should be subscribed to this list")
	@Nullable
	@Attribute(type="ROLE", multivalue = true)
	public List<java.lang.String> roleMembers;

	@Description ("Business units whose membes should be subscribed to this list")
	@Nullable
	@Attribute(type="GROUP", multivalue = true, filterExpression = "obsolete eq false" )
	public List<java.lang.String> groupMembers;

	@Description ("Contains the users that are directly subscribed to this mail list")
	@Nullable
	@Attribute(translated = "usersList", type="USER", multivalue = true, filterExpression = "active eq true" )
	public List<java.lang.String> llistaUsuaris;

	@Description ("Mail list custom attributes")
	@JsonAttribute(hibernateJoin="attributes")
	@Attribute(defaultValue="new java.util.HashMap<String,Object>()", hidden = true)
	@Nullable
	public Map<String,Object> attributes; 

	@Description ("Mail lists that this one is subscribed to")
	@Nullable
	@Attribute(translated = "listsBelong", type="MAIL_LIST", readonly = true )
	public java.lang.String llistaLlistesOnPertany;
	
	@Nullable
	@Description ("Contains the exploded users list, resolving any group or role membership.")
	@Attribute(type="USER", multivalue = true, readonly = true)
	public List<java.lang.String> explodedUsersList;
	
}

