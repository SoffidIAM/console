//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import com.soffid.mda.annotation.*;

@ValueObject ( translatedName="AccessLog",
	 translatedPackage="com.soffid.iam.api")
public class RegistreAcces {

	public java.lang.Long id;

	@Nullable
	@Attribute(translated = "sessionId" )
	public java.lang.String idSessio;

	@Nullable
	@Attribute(translated = "startDate" )
	public java.util.Calendar dataInici;

	@Nullable
	@Attribute(translated = "endDate" )
	public java.util.Calendar dataFi;

	@Nullable
	public java.lang.String codeAge;

	@Nullable
	@Attribute(translated = "information" )
	public java.lang.String informacio;

	@Nullable
	@Attribute(translated = "userCode" )
	public java.lang.String codiUsuari;

	@Nullable
	@Attribute(translated = "serverName" )
	public java.lang.String nomServidor;

	@Nullable
	@Attribute(translated = "clientName" )
	public java.lang.String nomClinet;

	@Nullable
	@Attribute(translated = "accessType" )
	public java.lang.String tipusAcces;

	@Nullable
	@Attribute(translated = "accessProtocol" )
	public java.lang.String protocolAcces;

	@Nullable
	@Attribute(translated = "userFullName" )
	public java.lang.String nomCompletUsuari;

}
