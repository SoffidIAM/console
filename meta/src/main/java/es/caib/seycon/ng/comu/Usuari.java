//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import com.soffid.mda.annotation.*;

import es.caib.seycon.ng.model.UsuariEntity;

@JsonObject (hibernateClass=UsuariEntity.class)
@ValueObject ( translatedName="User",
	 translatedPackage="com.soffid.iam.api")
public class Usuari {

	@Attribute(translated = "userName" )
	public java.lang.String codi;

	@Attribute(translated = "firstName" )
	public java.lang.String nom;

	@Attribute(translated = "lastName" )
	public java.lang.String primerLlinatge;

	@Nullable
	@Attribute(translated = "shortName" )
	public java.lang.String nomCurt;

	@Nullable
	@Attribute(translated = "createdDate" )
	public java.util.Calendar dataCreacioUsuari;

	@Nullable
	@Attribute(translated = "createdByUser" )
	public java.lang.String usuariCreacio;

	@Nullable
	@Attribute(translated = "active" )
	public java.lang.Boolean actiu;

	@Nullable
	@Attribute(translated = "middleName" )
	public java.lang.String segonLlinatge;

	@Nullable
	@Attribute(translated = "multiSession" )
	public java.lang.Boolean multiSessio;

	@Nullable
	@Attribute(translated = "comments" )
	public java.lang.String comentari;

	@Nullable
	@JsonAttribute(hibernateAttribute="userType.name")
	@Attribute(translated = "userType" )
	public java.lang.String tipusUsuari;

	@JsonAttribute(hibernateAttribute="profileServer.name")
	@Attribute(translated = "profileServer" )
	public java.lang.String servidorPerfil;

	@JsonAttribute(hibernateAttribute="homeServer.name")
	@Attribute(translated = "homeServer" )
	public java.lang.String servidorHome;

	@JsonAttribute(hibernateAttribute="mailServer.name")
	@Attribute(translated = "mailServer" )
	public java.lang.String servidorCorreu;

	@Nullable
	public java.lang.Long passwordMaxAge;

	@Attribute(translated = "primaryGroup" )
	@JsonAttribute(hibernateAttribute="primaryGroup.name")
	public java.lang.String codiGrupPrimari;

	@Nullable
	@Attribute(translated = "modifiedDate" )
	public java.util.Calendar dataDarreraModificacioUsuari;

	@Nullable
	@Attribute(translated = "modifiedByUser" )
	public java.lang.String usuariDarreraModificacio;

	@Nullable
	@Attribute(translated = "nationalID" )
	public java.lang.String NIF;

	@Nullable
	@Attribute(translated = "phoneNumber" )
	public java.lang.String telefon;

	@Nullable
	public java.lang.Long id;

	@Nullable
	@Attribute(translated = "mailAlias" )
	public java.lang.String aliesCorreu;

	@Nullable
	@JsonAttribute(hibernateAttribute="mailDomain.name")
	@Attribute(translated = "mailDomain" )
	public java.lang.String dominiCorreu;

	@Nullable
	@Attribute(translated = "consoleProperties" )
	public es.caib.seycon.ng.comu.UsuariSEU usuariSEU;

	@Nullable
	@Attribute(translated = "primaryGroupDescription" )
	@JsonAttribute(hibernateAttribute="primaryGroup.description")
	public java.lang.String descripcioGrupPrimari;

	@Nullable
	public java.lang.String fullName;

}
