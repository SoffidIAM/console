//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import com.soffid.mda.annotation.*;

@ValueObject ( translatedName="AgentStatusInfo",
	 translatedPackage="com.soffid.iam.api")
public abstract class AgentStatusInfo {

	@Attribute(translated = "agentName" )
	public java.lang.String nomAgent;

	@Attribute(translated = "className" )
	public java.lang.String nomClasse;

	@Attribute(translated = "pendingTasks" )
	public java.lang.Integer tasquesPendents;

	@Attribute(translated = "status" )
	public java.lang.String estat;

	public java.lang.String url;

	@Nullable
	@Attribute(translated = "statusMessage" )
	public java.lang.String msgEstat;

	@Nullable
	public java.lang.String stackTrace;

	@Nullable
	public java.lang.String version;

}
