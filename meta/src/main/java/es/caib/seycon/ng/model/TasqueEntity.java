//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;

import java.util.Collection;

import com.soffid.iam.model.TenantEntity;
import com.soffid.mda.annotation.*;

@Entity(table = "SC_TASQUE", translatedName = "TaskEntity", translatedPackage = "com.soffid.iam.model")
@Depends({ es.caib.seycon.ng.comu.Tasca.class,
		es.caib.seycon.ng.model.TaskLogEntity.class })
public abstract class TasqueEntity {

	@Column(name = "TAS_ID")
	@Identifier
	public java.lang.Long id;

	@Column(name = "TAS_USUARI", length = 150, translated = "user")
	@Nullable
	public java.lang.String usuari;

	@Column(name = "TAS_CONTRA", length = 128, translated = "password")
	@Nullable
	public java.lang.String contra;

	@Column(name = "TAS_CANCON", length = 1, translated = "changePassword")
	@Nullable
	public java.lang.String cancon;

	@Column(name = "TAS_CARPET", length = 150, translated = "folder")
	@Nullable
	public java.lang.String carpet;

	@Column(name = "TAS_TIPCAR", length = 1, translated = "folderType")
	@Nullable
	public java.lang.String tipcar;

	@Column(name = "TAS_IMPRES", length = 12, translated = "printer")
	@Nullable
	public java.lang.String impres;

	@Column(name = "TAS_MAQUIN", length = 50, translated = "host")
	@Nullable
	public java.lang.String maquin;

	@Column(name = "TAS_SUBXAR", length = 10, translated = "subnet")
	@Nullable
	public java.lang.String subxar;

	@Column(name = "TAS_MISSAT", length = 1024, translated = "message")
	@Nullable
	public java.lang.String missat;

	@Column(name = "TAS_STATUS", defaultValue = "\"P\"", length = 1)
	@Nullable
	public java.lang.String status;

	@Column(name = "TAS_DATA", defaultValue = "new java.sql.Timestamp(System.currentTimeMillis())", translated = "date")
	public java.sql.Timestamp data;

	@Column(name = "TAS_TRANSA", length = 50, translated = "transaction")
	public java.lang.String transa;

	@Column(name = "TAS_GRUP", length = 50, translated = "group")
	@Nullable
	public java.lang.String grup;

	@Column(name = "TAS_ALIES", length = 50, translated = "alias")
	@Nullable
	public java.lang.String alies;

	@Column(name = "TAS_DOMCOR", length = 50, translated = "mailDomain")
	@Nullable
	public java.lang.String domcor;

	@Column(name = "TAS_ROLE", length = 150)
	@Nullable
	public java.lang.String role;

	@Column(name = "TAS_BD", length = 50, translated = "db")
	@Nullable
	public java.lang.String bd;

	@Column(name = "TAS_CODDIS", length = 50, translated = "systemName")
	@Nullable
	public java.lang.String coddis;

	@Column(name = "TAS_SERVER", length = 1024)
	@Nullable
	public java.lang.String server;

	@Column(name = "TAS_PRIORI", translated = "priority")
	@Nullable
	public java.lang.Long prioritat;

	@Column(name = "TAS_DOMUSU", length = 50, translated = "usersDomain")
	@Nullable
	public java.lang.String dominiUsuaris;

	@Column(name = "TAS_DOMCON", length = 50, translated = "passwordsDomain")
	@Nullable
	public java.lang.String dominiContrasenyes;

	@Column(name = "TAS_HASH", length = 200)
	@Nullable
	public java.lang.String hash;

	@ForeignKey(foreignColumn = "TLO_IDTASQUE")
	public java.util.Collection<es.caib.seycon.ng.model.TaskLogEntity> logs;

	@Column(name = "TAS_EXPDAT")
	@Nullable
	public java.util.Date expirationDate;

	@Column(name = "TAS_ENTITY", length = 100)
	@Nullable
	public java.lang.String entity;

	@Column(name = "TAS_PKVAL", length = 50)
	@Nullable
	public java.lang.Long primaryKeyValue;
	
	@Column(name = "TAS_CUOBTY", length = 100)
	@Nullable
	public java.lang.String customObjectType;

	@Column(name = "TAS_CUOBNA", length = 50)
	@Nullable
	public String customObjectName;
	
	@Column(name = "TAS_SOURCE", length = 50)
	@Nullable
	public String sourceTransaction;

	@Column(name = "TAS_TEN_ID")
	public TenantEntity tenant;

	@DaoFinder("select tasques from \n"
			+ "com.soffid.iam.model.TaskEntity tasques\n"
			+ "where tasques.server=:server  and tasques.tenant.id = :tenantId "
			+ "order by tasques.date, tasques.id")
	public java.util.List<es.caib.seycon.ng.model.TasqueEntity> findByServer(
			java.lang.String server) {
		return null;
	}

	@DaoFinder("select count(*) \n"
			+ "from com.soffid.iam.model.TaskEntity tasques\n"
			+ "where tasques.server is not null and tasques.tenant.id = :tenantId " )
	public Long countTasks() {
		return null;
	}

	@DaoFinder("select count(*) \n"
			+ "from com.soffid.iam.model.TaskEntity tasques\n"
			+ "where tasques.server = :server and tasques.tenant.id = :tenantId " )
	public Long countTasksByServer(String server) {
		return null;
	}

	@DaoFinder("select count(*) \n"
			+ "from com.soffid.iam.model.TaskEntity tasques\n"
			+ "where tasques.server is null and tasques.tenant.id = :tenantId " )
	public Long countUnscheduledTasks() {
		return null;
	}

	@DaoFinder("select systemName, count(*) \n"
			+ "from com.soffid.iam.model.TaskEntity tasques\n"
			+ "where tasques.server is not null and tasques.tenant.id = :tenantId \n"
			+ "group by systemName" )
	public Collection<Object[]> countTasksBySystem() {
		return null;
	}

	@DaoFinder("select systemName, count(*) \n"
			+ "from com.soffid.iam.model.TaskEntity tasques\n"
			+ "where tasques.server = :server and tasques.tenant.id = :tenantId \n"
			+ "group by systemName" )
	public Collection<Object[]> countTasksBySystem(String server) {
		return null;
	}

	@DaoFinder("select tasques from \n"
			+ "com.soffid.iam.model.TaskEntity tasques\n"
			+ "where tasques.server=:server and "
			+ "(tasques.systemName is null or tasques.systemName = :system) "
			+ "and tasques.tenant.id = :tenantId "
			+ "order by tasques.date, tasques.id")
	public java.util.List<es.caib.seycon.ng.model.TasqueEntity> findByServerAndSystem(
			java.lang.String server,
			String system) {
		return null;
	}

	@Operation(translated = "findByTaskAndServer")
	@DaoFinder("select tasques from \n"
			+ "com.soffid.iam.model.TaskEntity tasques\n"
			+ "where tasques.server=:server and tasques.transaction=:transaction  and tasques.tenant.id = :tenantId "
			+ "order by tasques.date, tasques.id")
	public java.util.List<es.caib.seycon.ng.model.TasqueEntity> findByTascaAndServer(
			java.lang.String transaction, java.lang.String server) {
		return null;
	}

	@Operation(translated = "findDataUnplannedTasks")
	@DaoFinder("select count(*),max(tasqueEntity.date) "
			+ "from com.soffid.iam.model.TaskEntity as tasqueEntity "
			+ "where tasqueEntity.server is null and tasqueEntity.tenant.id = :tenantId")
	public java.util.List<Object[]> findDadesTasquesSensePlanificar() {
		return null;
	}

	@Operation(translated = "findDataPendingTasks")
	@DaoFinder("select count(*) from com.soffid.iam.model.TaskEntity as tasqueEntity "
			+ "where ((:server is null and tasqueEntity.server is null) or "
			+ "       (:server is not null and  tasqueEntity.server=:server) ) "
			+ "and tasqueEntity.tenant.id = :tenantId ")
	public java.util.List<Long> findDadesTasquesPendentsServer(
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
			java.lang.String user, java.lang.String systemName) {
		return null;
	}

	@DaoFinder
	public java.util.List<es.caib.seycon.ng.model.TasqueEntity> findByUser(
			java.lang.String user) {
		return null;
	}

	@DaoFinder
	public java.util.List<es.caib.seycon.ng.model.TasqueEntity> findByHost(
		java.lang.String host) {
	 return null;
	}

	@DaoFinder("select t "
			+ "from com.soffid.iam.model.TaskEntity as t "
			+ "where t.server is null and t.tenant.id=:tenantId")
	public java.util.List<es.caib.seycon.ng.model.TasqueEntity> findUnscheduled() {
	 return null;
	}
	
	@DaoOperation
	public void createNoFlush(es.caib.seycon.ng.model.TasqueEntity tasque)
			throws es.caib.seycon.ng.exception.InternalErrorException {
	}

	@DaoOperation
	public void createForce(es.caib.seycon.ng.model.TasqueEntity tasque)
			throws es.caib.seycon.ng.exception.InternalErrorException {
	}

	@DaoOperation
	public String startVirtualSourceTransaction()
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@DaoOperation
	public String startVirtualSourceTransaction(boolean readonly)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@DaoOperation
	public void finishVirtualSourceTransaction(String virtualTransactionId)
			throws es.caib.seycon.ng.exception.InternalErrorException {
	}

	@DaoOperation
	public void releaseAll()
			throws es.caib.seycon.ng.exception.InternalErrorException {
	}

	@DaoOperation
	public void cancelUnscheduled()
			throws es.caib.seycon.ng.exception.InternalErrorException {
	}

	@DaoOperation
	public void cancelUnscheduledCopies(TasqueEntity entity)
			throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	
	public String toString() {return null;}
}


@Index (name="SC_TAS_HASH",	unique=false,
	entity=es.caib.seycon.ng.model.TasqueEntity.class,
	columns={"TAS_TEN_ID", "TAS_HASH"})
abstract class TasqueHashIndex {
}

