//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.mda.annotation.*;

@Entity (table="SC_TASQUE" , translatedName="TaskEntity", translatedPackage="com.soffid.iam.model")
@Depends ({es.caib.seycon.ng.comu.Tasca.class,
	es.caib.seycon.ng.model.TaskLogEntity.class})
public abstract class TasqueEntity {

	@Column (name="TAS_ID")
	@Identifier
	public java.lang.Long id;

	@Column (name="TAS_USUARI", length=150, translated="user")
	@Nullable
	public java.lang.String usuari;

	@Column (name="TAS_CONTRA", length=128, translated="password")
	@Nullable
	public java.lang.String contra;

	@Column (name="TAS_CANCON", length=1, translated="changePassword")
	@Nullable
	public java.lang.String cancon;

	@Column (name="TAS_CARPET", length=150, translated="folder")
	@Nullable
	public java.lang.String carpet;

	@Column (name="TAS_TIPCAR", length=1, translated="folderType")
	@Nullable
	public java.lang.String tipcar;

	@Column (name="TAS_IMPRES", length=12, translated="printer")
	@Nullable
	public java.lang.String impres;

	@Column (name="TAS_MAQUIN", length=50, translated="host")
	@Nullable
	public java.lang.String maquin;

	@Column (name="TAS_SUBXAR", length=10, translated="subnet")
	@Nullable
	public java.lang.String subxar;

	@Column (name="TAS_MISSAT", length=1024, translated="message")
	@Nullable
	public java.lang.String missat;

	@Column (name="TAS_STATUS",
		defaultValue="\"P\"", length=1)
	@Nullable
	public java.lang.String status;

	@Column (name="TAS_DATA",
		defaultValue="new java.sql.Timestamp(System.currentTimeMillis())",
		translated="date")
	public java.sql.Timestamp data;

	@Column (name="TAS_TRANSA", length=50, translated="transaction")
	public java.lang.String transa;

	@Column (name="TAS_GRUP", length=20, translated="group")
	@Nullable
	public java.lang.String grup;

	@Column (name="TAS_ALIES", length=50, translated="alias")
	@Nullable
	public java.lang.String alies;

	@Column (name="TAS_DOMCOR", length=50, translated="mailDomain")
	@Nullable
	public java.lang.String domcor;

	@Column (name="TAS_ROLE", length=150)
	@Nullable
	public java.lang.String role;

	@Column (name="TAS_BD", length=50, translated="db")
	@Nullable
	public java.lang.String bd;

	@Column (name="TAS_CODDIS", length=50, translated="systemCode")
	@Nullable
	public java.lang.String coddis;

	@Column (name="TAS_SERVER", length=1024)
	@Nullable
	public java.lang.String server;

	@Column (name="TAS_PRIORI", translated="priority")
	@Nullable
	public java.lang.Long prioritat;

	@Column (name="TAS_DOMUSU", length=50, translated="usersDomain")
	@Nullable
	public java.lang.String dominiUsuaris;

	@Column (name="TAS_DOMCON", length=50, translated="passwordsDomain")
	@Nullable
	public java.lang.String dominiContrasenyes;

	@Column (name="TAS_HASH", length=200)
	@Nullable
	public java.lang.String hash;

	@ForeignKey (foreignColumn="TLO_IDTASQUE")
	public java.util.Collection<es.caib.seycon.ng.model.TaskLogEntity> logs;

	@Column (name="TAS_EXPDAT")
	@Nullable
	public java.util.Date expirationDate;

	@Column (name="TAS_ENTITY", length=100)
	@Nullable
	public java.lang.String entity;

	@Column (name="TAS_PKVAL", length=50)
	@Nullable
	public java.lang.Long primaryKeyValue;

	@DaoFinder
	public java.util.List<es.caib.seycon.ng.model.TasqueEntity> findByServer(
		java.lang.String server) {
	 return null;
	}
	@Operation(translated="findByTaskAndServer")
	@DaoFinder("select tasques from \nes.caib.seycon.ng.model.TasqueEntity tasques\nwhere tasques.server=:server and tasques.transa=:tasca order by tasques.data, tasques.id")
	public java.util.List<es.caib.seycon.ng.model.TasqueEntity> findByTascaAndServer(
		java.lang.String tasca, 
		java.lang.String server) {
	 return null;
	}
	@Operation(translated="findDataUnplannedTasks")
	@DaoFinder("select count(*),max(tasqueEntity.data) from es.caib.seycon.ng.model.TasqueEntity as tasqueEntity where tasqueEntity.server is null")
	public java.util.List<es.caib.seycon.ng.model.TasqueEntity> findDadesTasquesSensePlanificar() {
	 return null;
	}
	@Operation(translated="findDataPendingTasks")
	@DaoFinder("select count(*) from es.caib.seycon.ng.model.TasqueEntity as tasqueEntity where (:server is null and tasqueEntity.server is null) or (:server is not null and  tasqueEntity.server=:server)")
	public java.util.List<es.caib.seycon.ng.model.TasqueEntity> findDadesTasquesPendentsServer(
		java.lang.String server) {
	 return null;
	}
	@DaoFinder
	public java.util.List<es.caib.seycon.ng.model.TasqueEntity> findByHash(
		java.lang.String hash) {
	 return null;
	}
	@DaoFinder
	public java.util.List<es.caib.seycon.ng.model.TasqueEntity> findByAccount(
		java.lang.String usuari, 
		java.lang.String coddis) {
	 return null;
	}
	@DaoFinder
	public java.util.List<es.caib.seycon.ng.model.TasqueEntity> findByUser(
		java.lang.String usuari) {
	 return null;
	}
	@DaoOperation
	public void createNoFlush(
		es.caib.seycon.ng.model.TasqueEntity tasque)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
}
