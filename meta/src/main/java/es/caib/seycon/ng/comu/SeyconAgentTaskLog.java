//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import com.soffid.mda.annotation.*;

@ValueObject ( translatedName="SyncAgentTaskLog",
	 translatedPackage="com.soffid.iam.api")
public class SeyconAgentTaskLog {

	@Attribute(translated = "taskId" )
	public java.lang.Long idTasca;

	@Attribute(translated = "taskDescription" )
	public java.lang.String descripcioTasca;

	@Attribute(translated = "agentCode" )
	public java.lang.String codiAgent;

	@Attribute(translated = "complete" )
	public java.lang.String complet;

	@Attribute(translated = "message" )
	public java.lang.String missatge;

	@Attribute(translated = "creationDate" )
	public java.util.Calendar dataCreacio;

	@Attribute(translated = "lastExecution" )
	public java.lang.Long darreraExecucio;

	@Attribute(translated = "lastExecutionDate" )
	public java.util.Calendar dataDarreraExecucio;

	@Attribute(translated = "nextExecution" )
	public java.lang.Long proximaExecucio;

	@Attribute(translated = "nextExecutionDate" )
	public java.util.Calendar dataProximaExecucio;

	@Attribute(translated = "executionsNumber" )
	public java.lang.Long numExecucions;

	@Attribute(translated = "priority" )
	public java.lang.Long prioritat;

	public java.lang.String stackTrace;

}
