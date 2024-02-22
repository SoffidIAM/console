//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import com.soffid.mda.annotation.*;

import es.caib.seycon.ng.model.RegistreAccesEntity;


@JsonObject(hibernateClass = RegistreAccesEntity.class)
@ValueObject ( translatedName="AccessLog",
	 translatedPackage="com.soffid.iam.api")
public class RegistreAcces {

	public java.lang.Long id;

	@Nullable
	@Attribute(translated = "sessionId" )
	public java.lang.String idSessio;

	@Nullable
	@Attribute(translated = "startDate" , type = "DATE_TIME")
	public java.util.Calendar dataInici;

	@Nullable
	@Attribute(translated = "endDate", type = "DATE_TIME")
	public java.util.Calendar dataFi;

	@Nullable
	public java.lang.String codeAge;

	@Nullable
	@Attribute(translated = "information" )
	public java.lang.String informacio;

	@Nullable
	@JsonAttribute(hibernateAttribute = "user.userName")
	@Attribute(translated = "userName", synonyms = {"userCode"} )
	public java.lang.String codiUsuari;

	@Nullable
	@JsonAttribute(hibernateAttribute = "server.name")
	@Attribute(translated = "serverName" )
	public java.lang.String nomServidor;

	@Nullable
	@JsonAttribute(hibernateAttribute = "client.name")
	@Attribute(translated = "clientName" )
	public java.lang.String nomClinet;

	@Nullable
	@JsonAttribute(hibernateAttribute = "clientAddress")
	public java.lang.String clientAddress;

	@Nullable
	@Attribute(translated = "accessType" )
	public java.lang.String tipusAcces;

	@Nullable
	@JsonAttribute(hibernateAttribute = "protocol.name")
	@Attribute(translated = "accessProtocol" )
	public java.lang.String protocolAcces;

	@Nullable
	@Attribute(translated = "userFullName" )
	public java.lang.String nomCompletUsuari;

	@Description("Jump server for PAM Sessions")
	@Nullable String jumpServerGroup;
	
	@Description("Account name for PAM Sessions")
	@Nullable String accountName;
}
