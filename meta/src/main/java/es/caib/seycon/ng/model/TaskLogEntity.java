//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.mda.annotation.*;

@Entity (table="SC_TASKLOG" , translatedName="TaskLogEntity", translatedPackage="com.soffid.iam.model")
@Depends ({es.caib.seycon.ng.model.TasqueEntity.class,
	es.caib.seycon.ng.model.DispatcherEntity.class})
public abstract class TaskLogEntity {

	@Column (name="TLO_COMPLETE", length=1, translated="completed")
	@Nullable
	public java.lang.String complet;

	@Column (name="TLO_MISSAT", length=1024, translated="message")
	@Nullable
	public java.lang.String missatge;

	@Column (name="TLO_DATCREA", translated="creationDate")
	@Nullable
	public java.util.Date dataCreacio;

	@Column (name="TLO_DADAEX", translated="lastExecution")
	@Nullable
	public java.lang.Long darreraExecucio;

	@Column (name="TLO_PRDAEX", translated="nextExecution")
	@Nullable
	public java.lang.Long proximaExecucio;

	@Column (name="TLO_NUMEXE", translated="executionsNumber")
	@Nullable
	public java.lang.Long numExecucions;

	@Column (name="TLO_STACK", length=4000)
	@Nullable
	public java.lang.String stackTrace;

	@Column (name="TLO_IDTASQUE", translated="task")
	public es.caib.seycon.ng.model.TasqueEntity tasca;

	@Column (name="TLO_ID")
	@Identifier
	public java.lang.Long id;

	@Column (name="TLO_DIS_ID", translated="system")
	public es.caib.seycon.ng.model.DispatcherEntity dispatcher;

	@Operation(translated="findByAgentCode")
	@DaoFinder("from es.caib.seycon.ng.model.TaskLogEntity tlog left join tlog.dispatcher agent where agent.codi=:codiAgent order by tlog.tasca.id")
	public java.util.List<es.caib.seycon.ng.model.TaskLogEntity> findByCodiAgent() {
	 return null;
	}
	@Operation(translated="findByTaskID")
	@DaoFinder("from es.caib.seycon.ng.model.TaskLogEntity tlog\nwhere tlog.tasca.id=:idTasca")
	public java.util.List<es.caib.seycon.ng.model.TaskLogEntity> findByIdTasca(
		java.lang.Long idTasca) {
	 return null;
	}
	@DaoFinder("select tlog\nfrom es.caib.seycon.ng.model.TaskLogEntity tlog \nleft join tlog.tasca tasca\nwhere  \n  (:server is not null and tasca.server=:server) \n  or (:server is null and tasca.server is null)\norder by tlog.tasca.id, tlog.dispatcher.codi")
	public java.util.List<es.caib.seycon.ng.model.TaskLogEntity> findAllHavingTasqueByServer(
		java.lang.String server) {
	 return null;
	}
	@DaoFinder("select tlog from es.caib.seycon.ng.model.TaskLogEntity tlog\nleft join tlog.dispatcher agent\nleft join tlog.tasca tasca\nwhere agent.codi=:agent and \n(:complet is null or tlog.complet=:complet) \nand ( \n   (:server is not null and tasca.server=:server) \n  or (:server is null and tasca.server is null)\n) order by tasca.id, agent.codi")
	public java.util.List<es.caib.seycon.ng.model.TaskLogEntity> findAllHavingTasqueByAgentAndServer(
		java.lang.String server, 
		java.lang.String agent, 
		@Nullable java.lang.String complet) {
	 return null;
	}
}
