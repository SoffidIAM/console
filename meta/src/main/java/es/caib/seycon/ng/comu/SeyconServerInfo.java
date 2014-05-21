//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import com.soffid.mda.annotation.*;

@ValueObject ( translatedName="SyncServerInfo",
	 translatedPackage="com.soffid.iam.api")
public abstract class SeyconServerInfo {

	public java.lang.String url;

	@Attribute(translated = "description" )
	public java.lang.String descripcio;

	@Nullable
	@Attribute(translated = "version" )
	public java.lang.String versio;

	@Nullable
	@Attribute(translated = "status" )
	public java.lang.String estat;

	@Nullable
	@Attribute(translated = "numberOfAgents" )
	public java.lang.String numAgents;

	@Nullable
	@Attribute(translated = "numberOfPendingTasks" )
	public java.lang.String numTasquesPendents;

	@Nullable
	public java.lang.String sso;

	@Nullable
	public java.lang.String jetty;

	@Nullable
	public java.lang.String ssoDaemon;

	@Nullable
	public java.lang.String taskGenerator;

	@Nullable
	@Attribute(translated = "expirationRootCertificate" )
	public java.util.Calendar caducitatRootCertificate;

	@Nullable
	@Attribute(translated = "expirationMainCertificate" )
	public java.util.Calendar caducitatMainCertificate;

	@Nullable
	@Attribute(translated = "currentServerDate" )
	public java.util.Calendar dataActualServer;

	@Nullable
	public java.lang.String databaseConnections;

}
