//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import com.soffid.mda.annotation.*;

@ValueObject ( translatedName="AdministratorAuthorizationToAccessHost",
	 translatedPackage="com.soffid.iam.api")
public class AutoritzacioAccesHostComAdministrador {

	@Nullable
	public java.lang.Long id;

	@Nullable
	@Attribute(translated = "userCode" )
	public java.lang.String codiUsuari;

	@Attribute(translated = "hostName" )
	public java.lang.String nomHost;

	@Attribute(translated = "authorizationAccessExpirationDate" )
	public java.util.Calendar dataCaducitatAutoritzacioAcces;

	@Attribute(translated = "bpmProcessId" )
	@Nullable
	public java.lang.Long idProcesWorkflow;

	@Nullable
	@Attribute(translated = "userName" )
	public java.lang.String nomUsuari;

	@Nullable
	@Attribute(translated = "userEmail" )
	public java.lang.String correuUsuari;

	@Nullable
	@Attribute(translated = "hostIp" )
	public java.lang.String ipHost;

	@Nullable
	@Attribute(translated = "hostNetwork" )
	public java.lang.String xarxaHost;

	@Nullable
	@Attribute(translated = "hostDescription" )
	public java.lang.String descripcioHost;

	@Nullable
	@Attribute(translated = "requestDate" )
	public java.util.Calendar dataPeticio;

}
