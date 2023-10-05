//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import java.util.Map;

import com.soffid.mda.annotation.*;

import es.caib.seycon.ng.model.UsuariEntity;

@JsonObject (hibernateClass=UsuariEntity.class)
@ValueObject ( translatedName="User",
	 cache=300,
	 translatedPackage="com.soffid.iam.api")
public abstract class Usuari {
	@Nullable
	@Attribute(hidden=true)
	public java.lang.Long id;

	@Attribute(translated = "userName", separator = "_commonAttributes", searchCriteria = true )
	public java.lang.String codi;

	@Attribute(translated = "firstName", searchCriteria = true )
	public java.lang.String nom;

	@Attribute(translated = "lastName", searchCriteria = true )
	public java.lang.String primerLlinatge;

	@Nullable
	@Attribute(translated = "middleName", synonyms = {"lastName2"}, searchCriteria = true )
	public java.lang.String segonLlinatge;
	
	@Nullable
	@Attribute(readonly = true)
	public java.lang.String fullName;
	
//	@Nullable
	@JsonAttribute(hibernateAttribute="userType.name")
	@Attribute(translated = "userType", type = "USER_TYPE", separator="_organization" )
	public java.lang.String tipusUsuari;
	
	@Attribute(translated = "primaryGroup", type = "GROUP" )
	@JsonAttribute(hibernateAttribute="primaryGroup.name")
	public java.lang.String codiGrupPrimari;
	
	@Nullable
	@Attribute(translated = "primaryGroupDescription", hidden = true )
	@JsonAttribute(hibernateAttribute="primaryGroup.description")
	public java.lang.String descripcioGrupPrimari;
	
	@JsonAttribute(hibernateAttribute="homeServer.name")
	@Attribute(translated = "homeServer", type = "HOST", filterExpression = "folders eq \"S\"" )
	@Nullable
	public java.lang.String servidorHome;
	
	@JsonAttribute(hibernateAttribute="profileServer.name")
	@Attribute(translated = "profileServer", type = "HOST", filterExpression = "folders eq \"S\"" )
	@Nullable
	public java.lang.String servidorPerfil;
	
	@Nullable
	@Attribute(type="EMAIL", separator="_emails", customUiHandler = "com.soffid.iam.web.user.EmailHandler")
	public String emailAddress;
	
	@Nullable
	@Attribute(translated = "mailAlias" )
	public java.lang.String aliesCorreu;
	
	@JsonAttribute(hibernateAttribute="mailServer.name")
	@Nullable
	@Attribute(translated = "mailServer", filterExpression = "mail eq \"S\"" , type="HOST")
	public java.lang.String servidorCorreu;
	
	@Nullable
	@Attribute(translated = "shortName", hidden = true )
	public java.lang.String nomCurt;
	
	@Nullable
	@JsonAttribute(hibernateAttribute="mailDomain.name")
	@Attribute(translated = "mailDomain", hidden = true )
	public java.lang.String dominiCorreu;
	
	@Nullable
	@Attribute(translated = "active", separator="_status")
	@JsonAttribute(hibernateAttribute="-")
	public java.lang.Boolean actiu;

	@Nullable
	@Attribute(translated = "multiSession" )
	public java.lang.Boolean multiSessio;

	@Nullable
	@Attribute(translated = "comments", multiline=true )
	public java.lang.String comentari;
	
	@Nullable
	@Attribute(translated = "createdByUser", synonyms = {"createdBy"}, readonly = true, type = "USER" , separator="_audit")
	public java.lang.String usuariCreacio;
	
	@Nullable
	@Attribute(translated = "createdDate", readonly = true,
	synonyms = {"createdOn"},
	type = "DATE_TIME")
	public java.util.Calendar dataCreacioUsuari;

	@Nullable
	@Attribute(translated = "modifiedByUser", synonyms= {"modifiedBy"}, type="USER", readonly=true )
	public java.lang.String usuariDarreraModificacio;

	@Nullable
	@Attribute(translated = "modifiedDate", entityAttribute = "lastModificationDate", synonyms= {"modifiedOn"}, type="DATE_TIME", readonly = true )
	public java.util.Calendar dataDarreraModificacioUsuari;


	@Description ("User attributes")
	@Nullable
	@Attribute(hidden = true)
	@JsonAttribute(hibernateAttribute = "attributes")
	Map<String, Object> attributes;
}
