//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import com.soffid.mda.annotation.*;

import es.caib.seycon.ng.model.AuditoriaEntity;

@ValueObject ( translatedName="Audit",
	 translatedPackage="com.soffid.iam.api")
@JsonObject(hibernateClass=AuditoriaEntity.class)
public class Auditoria {

	@Attribute(translated = "additionalInfo" )
	@Nullable
	public java.lang.String data;

	@Nullable
	@Attribute(translated = "object" )
	public java.lang.String objecte;

	@Attribute(translated = "action" )
	public java.lang.String accio;

	@Description("Person who has made the action")
	@Nullable
	@Attribute(translated = "author" )
	@JsonAttribute(hibernateAttribute="accountAssoc.name")
	public java.lang.String autor;

	@Nullable
	@Attribute(translated = "role" )
	public java.lang.String rol;

	@Nullable
	@Attribute(translated = "database" )
	public java.lang.String bbdd;

	@Nullable
	public java.lang.Long id;

	@Nullable
	@Attribute(translated = "printer" )
	public java.lang.String impressora;

	@Nullable
	@Attribute(translated = "application" )
	public java.lang.String aplicacio;

	@Nullable
	@Attribute(translated = "mailList" )
	public java.lang.String llistaCorreu;

	@Nullable
	@Attribute(translated = "group" )
	public java.lang.String grup;

	@Nullable
	@Attribute(translated = "user" )
	public java.lang.String usuari;

	@Nullable
	@Attribute(translated = "mailDomain" )
	public java.lang.String dominiCorreu;

	@Nullable
	@Attribute(translated = "host" )
	public java.lang.String maquina;

	@Nullable
	@Attribute(translated = "network" )
	public java.lang.String xarxa;

	@Nullable
	@Attribute(translated = "file" )
	public java.lang.Long fitxer;

	@Nullable
	@Attribute(translated = "domainValue" )
	public java.lang.String valorDomini;

	@Nullable
	@Attribute(translated = "domain" )
	public java.lang.String domini;

	@Nullable
	@Attribute(translated = "configurationParameter" )
	public java.lang.String parametreConfiguracio;

	@Nullable
	@JsonAttribute(hibernateJoin="accountAssoc.users as users", hibernateAttribute="user.primaryGroup.name")
	@Attribute(translated = "primaryGroupAuthor" )
	public java.lang.String autorGrupPrimari;

	@Nullable
	@Attribute(translated = "authorFullName" )
	@JsonAttribute(hibernateAttribute="accountAssoc.description")
	public java.lang.String autorNomComplet;

	@Nullable
	@Attribute(translated = "authorization" )
	public java.lang.String autoritzacio;

	@Nullable
	@Attribute(translated = "fileName" )
	public java.lang.String nomFitxer;

	@Nullable
	@Attribute(translated = "identityFederation" )
	public java.lang.String federacioIdentitats;

	@Nullable
	@Attribute(translated = "mailListBelong" )
	public java.lang.String llistaCorreuPertany;

	@Nullable
	@Attribute(translated = "mailDomainBelogns" )
	public java.lang.String dominiCorreuPertany;

	@Nullable
	public java.lang.String account;

	@Nullable
	public java.lang.String message;

	@Nullable
	public java.lang.String passwordDomain;

	@Nullable
	public java.lang.String userDomain;

	@Nullable
	public java.lang.String userType;

	@Nullable
	public java.lang.String rule;

	@Nullable
	public java.lang.String scheduledTask;

	@Nullable
	@JsonAttribute(hibernateAttribute="date")
	public java.util.Calendar calendar;

	@Nullable
	public java.lang.String customObjectName;
	
	@Nullable
	public java.lang.String customObjectType;
}
