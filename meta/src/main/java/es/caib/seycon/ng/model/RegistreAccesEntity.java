//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;

import com.soffid.iam.model.TenantEntity;
import com.soffid.mda.annotation.*;

@Entity(table = "SC_REGACC", translatedName = "AccessLogEntity", translatedPackage = "com.soffid.iam.model")
@Depends({ es.caib.seycon.ng.model.ServeiEntity.class,
		es.caib.seycon.ng.comu.RegistreAcces.class,
		es.caib.seycon.ng.model.MaquinaEntity.class,
		es.caib.seycon.ng.model.UsuariEntity.class })
public abstract class RegistreAccesEntity {

	@Column(name = "RAC_ID")
	@Identifier
	public java.lang.Long id;

	@Column(name = "RAC_IDSES", length = 200, translated = "sessionId")
	@Nullable
	public java.lang.String idSessio;

	@Column(name = "RAC_DATINI", translated = "startDate")
	@Nullable
	public java.util.Date dataInici;

	@Column(name = "RAC_DATFI", translated = "endDate")
	@Nullable
	public java.util.Date dataFi;

	@Column(name = "RAC_CODAGE", length = 50, translated = "system")
	@Nullable
	public java.lang.String codeAge;

	@Column(name = "RAC_INFO", length = 1024, translated = "information")
	@Nullable
	public java.lang.String informacio;

	@Column(name = "RAC_IDMAQ", translated = "server")
	@Nullable
	public es.caib.seycon.ng.model.MaquinaEntity servidor;

	@Column(name = "RAC_IDMAOR")
	@Nullable
	public es.caib.seycon.ng.model.MaquinaEntity client;

	@Column(name = "RAC_IDSER")
	@Nullable
	public es.caib.seycon.ng.model.ServeiEntity protocol;

	@Column(name = "RAC_IDUSU", translated = "user")
	@Nullable
	public es.caib.seycon.ng.model.UsuariEntity usuari;

	@Column(name = "RAC_TIPUSACC", length = 1, translated = "accessType")
	@Nullable
	public java.lang.String tipusAcces;

	@Column(name = "RAC_CLIADR", length = 128)
	@Nullable
	public java.lang.String clientAddress;

	@Column(name = "RAC_CLINAM", length = 128)
	@Nullable
	public java.lang.String clientHostName;

	@Column(name = "RAC_HOSADR", length = 128)
	@Nullable
	public java.lang.String hostAddress;

	@Column(name = "RAC_HOSNAM", length = 128)
	@Nullable
	public java.lang.String hostName;

	@Column(name="RAC_TEN_ID")
	TenantEntity tenant;
	
	@Nullable
	@Description("Jump server group used in PAM sessions")
	@Column(name = "RAC_JUSEGR", length=128)
	String jumpServerGroup;
	
	@Nullable
	@Description("Account name used in PAM sessions")
	@Column(name = "RAC_ACCNAM", length=128)
	String accountName;
	

	@Operation(translated = "findAccessLogByCriteria")
	@DaoFinder("select registreAcces\n"
			+ "from com.soffid.iam.model.AccessLogEntity registreAcces\n"
			+ "left join registreAcces.user usuari\n"
			+ "where (:clientHostName is null or registreAcces.clientHostName like :clientHostName) and\n"
			+ "(:server is null or registreAcces.hostName like :server) and\n"
			+ "(:userName is null or usuari.userName like :userName) and\n"
			+ "(:maxDate = :nullDate or registreAcces.startDate < :maxDate ) and\n"
			+ "(:minDate = :nullDate or registreAcces.endDate > :minDate ) and "
			+ "registreAcces.tenant.id = :tenantId "
			+ "order by registreAcces.startDate")
	public java.util.List<es.caib.seycon.ng.model.RegistreAccesEntity> findRegistreByFiltre(
			java.util.Date nullDate, java.util.Date maxDate,
			java.util.Date minDate, java.lang.String clientHostName,
			java.lang.String server, java.lang.String userName) {
		return null;
	}

	@Operation(translated = "findAccessLogByCriteria")
	@DaoFinder("select registreAcces\n"
			+ "from com.soffid.iam.model.AccessLogEntity registreAcces\n"
			+ "left join registreAcces.user usuari\n"
			+ "where (:clientHostName is null or registreAcces.clientHostName like :clientHostName) and\n"
			+ "(:server is null or registreAcces.hostName  like :server) and\n"
			+ "(:userName is null or usuari.userName like :userName) and "
			+ "registreAcces.tenant.id = :tenantId "
			+ "order by registreAcces.startDate\n")
	public java.util.List<es.caib.seycon.ng.model.RegistreAccesEntity> findRegistreByFiltre(
			java.lang.String clientHostName, java.lang.String server,
			java.lang.String userName) {
		return null;
	}


	@Operation(translated = "findAccessLogByCriteria2Dates")
	@DaoFinder("select registreAcces\n"
			+ "from com.soffid.iam.model.AccessLogEntity registreAcces\n"
			+ "left join registreAcces.user usuari\n"
			+ "where (:clientHostName is null or registreAcces.clientHostName like :clientHostName) and\n"
			+ "(:server is null or registreAcces.hostName like :server) and\n"
			+ "(:userName is null or usuari.userName like :userName) and\n"
			+ "(:startDate = :nullDate or registreAcces.startDate >= :startDate ) and\n"
			+ "(:endDate = :nullDate or registreAcces.endDate <= :endDate ) and "
			+ "registreAcces.tenant.id = :tenantId "
			+ "order by registreAcces.startDate")
	public java.util.List<es.caib.seycon.ng.model.RegistreAccesEntity> findRegistreByFiltre2Datas(
			java.util.Date nullDate, java.util.Date startDate,
			java.util.Date endDate, java.lang.String clientHostName,
			java.lang.String server, java.lang.String userName) {
		return null;
	}

	@Operation(translated = "findAccessLogByHost")
	@DaoFinder("select registreAcces\n"
			+ "from com.soffid.iam.model.AccessLogEntity registreAcces\n"
			+ "where (:server is null or registreAcces.hostName like :server) and\n"
			+ "(:startDate = :nullDate or registreAcces.startDate >= :startDate ) and\n"
			+ "(:protocol is null or\nregistreAcces.protocol.name = :protocol) and \n "
			+ "registreAcces.tenant.id = :tenantId "
			+ "order by registreAcces.startDate")
	public java.util.List<es.caib.seycon.ng.model.RegistreAccesEntity> findRegistreByMaquina(
			java.util.Date nullDate, java.util.Date startDate,
			java.lang.String server, java.lang.String protocol) {
		return null;
	}

	@Operation(translated = "findAccessLogByHostAndStartDateAndProtocol")
	@DaoFinder("select registreAcces\n"
			+ "from com.soffid.iam.model.AccessLogEntity registreAcces\n"
			+ "where (:server is null or registreAcces.hostName like :server) and\n"
			+ "(:startDate = :nullDate or registreAcces.startDate >= :startDate ) and\n"
			+ "(:protocol is null or\nregistreAcces.protocol.name = :protocol) and\n"
			+ "registreAcces.tenant.id = :tenantId  "
			+ "order by registreAcces.startDate desc")
	public java.util.List<es.caib.seycon.ng.model.RegistreAccesEntity> findRegistreByMaquinaDataIniDesc(
			java.util.Date nullDate, java.util.Date startDate,
			java.lang.String server, java.lang.String protocol) {
		return null;
	}

	@Operation(translated = "findAccessLogByStartDateAndUserName")
	@DaoFinder("select registreAcces\n"
			+ "from com.soffid.iam.model.AccessLogEntity registreAcces\n"
			+ "left join registreAcces.user usuari\n"
			+ "where (:userName is null or usuari.userName like :userName) and\n"
			+ "(:startDate = :nullDate or registreAcces.startDate >= :startDate ) and\n"
			+ "registreAcces.tenant.id = :tenantId   "
			+ "order by registreAcces.startDate")
	public java.util.List<es.caib.seycon.ng.model.RegistreAccesEntity> findRegistreByDataIniAndCodiUsuari(
			java.util.Date nullDate, java.util.Date startDate,
			java.lang.String userName) {
		return null;
	}

	@Operation(translated = "findAccessLogByStartDateAndUserNameAndProtocol")
	@DaoFinder("select registreAcces\n"
			+ "from com.soffid.iam.model.AccessLogEntity registreAcces\n"
			+ "left join registreAcces.user usuari\n"
			+ "where "
			+ "(:userName is null or usuari.userName like :userName) and\n"
			+ "(:startDate = :nullDate or registreAcces.startDate >= :startDate ) and\n"
			+ "registreAcces.protocol.name= :protocol and \n"
			+ "registreAcces.tenant.id = :tenantId "
			+ "order by registreAcces.startDate desc")
	public java.util.List<es.caib.seycon.ng.model.RegistreAccesEntity> findRegistreByDataIniDescAndCodiUsuari(
			java.util.Date nullDate, java.util.Date startDate,
			java.lang.String userName, java.lang.String protocol) {
		return null;
	}

	@Operation(translated = "findLastAccessLogByUserName")
	@DaoFinder("select registreAcces\n"
			+ "from com.soffid.iam.model.AccessLogEntity registreAcces\n"
			+ "left join registreAcces.user usuari\n"
			+ "where "
			+ "(:userName is null or usuari.userName like :userName) and\n"
			+ "(:protocol is null or\nregistreAcces.protocol.name = :protocol) and\n"
			+ "registreAcces.tenant.id = :tenantId "
			+ "order by registreAcces.startDate")
	public java.util.List<es.caib.seycon.ng.model.RegistreAccesEntity> findDarrersRegistresByCodiUsuari(
			java.lang.String userName, java.lang.String protocol) {
		return null;
	}

	@Operation(translated = "findLastAccessLogByServerAndProtocol")
	@DaoFinder("select registreAcces\n"
			+ "from com.soffid.iam.model.AccessLogEntity registreAcces\n"
			+ "where (:server is null or registreAcces.hostName like :server) and\n"
			+ "registreAcces.tenant.id = :tenantId  and "
			+ "(:protocol is null or\nregistreAcces.protocol.name = :protocol)\n"
			+ "order by registreAcces.startDate desc")
	public java.util.List<es.caib.seycon.ng.model.RegistreAccesEntity> findDarrersRegistresAccesMaquinaProtocol(
			java.lang.String server, java.lang.String protocol) {
		return null;
	}

	@Operation(translated = "findAccessLogByCriteria2")
	@DaoFinder("select registreAcces\n"
			+ "from com.soffid.iam.model.AccessLogEntity registreAcces\n"
			+ "left join registreAcces.user usuari\n"
			+ "where (:clientHostName is null or registreAcces.clientHostName like :clientHostName) and\n"
			+ "(:server is null or registreAcces.hostName like :server) and\n"
			+ "(:userName is null or usuari.userName like :userName) and\n"
			+ "(:startDate = :nullDate or registreAcces.endDate >= :startDate ) and\n"
			+ "(:endDate = :nullDate or registreAcces.startDate <= :endDate ) and "
			+ "registreAcces.tenant.id = :tenantId "
			+ "order by registreAcces.startDate")
	public java.util.List<es.caib.seycon.ng.model.RegistreAccesEntity> findRegistreByFiltreNou(
			java.util.Date nullDate, java.util.Date startDate,
			java.util.Date endDate, java.lang.String clientHostName,
			java.lang.String server, java.lang.String userName) {
		return null;
	}

	@Operation(translated = "findLastDateBySystem")
	@DaoFinder("select max(rac.startDate) as startDate\n"
			+ "from com.soffid.iam.model.AccessLogEntity rac "
			+ "where rac.system=:system and \n"
			+ "rac.tenant.id = :tenantId")
	public java.util.Date findLastDateByDispatcher(java.lang.String system) {
		return null;
	}

	@Operation(translated = "findAccessLogByAgentAndSessionIDAndEndDate")
	@DaoFinder("select rac\n"
			+ "from com.soffid.iam.model.AccessLogEntity rac\n"
			+ "where rac.sessionId=:sessioId and "
			+ "rac.system = :system and (rac.endDate is null or rac.endDate=:date) and "
			+ "rac.server=:server and "
			+ "rac.tenant.id = :tenantId ")
	public java.util.List<es.caib.seycon.ng.model.RegistreAccesEntity> findByAgentSessioIdEndDate(
			java.lang.String system, java.lang.String sessioId,
			java.util.Date date, es.caib.seycon.ng.model.MaquinaEntity server) {
		return null;
	}

	@Operation(translated = "findAccessLogByAgentAndSessionIDAndEndDate2")
	@DaoFinder("select rac\n"
			+ "from com.soffid.iam.model.AccessLogEntity rac\n"
			+ "where rac.sessionId=:sessioId and "
			+ "rac.system = :system and (rac.endDate is null or rac.endDate=:date) and "
			+ "rac.hostName=:hostName and "
			+ "rac.tenant.id = :tenantId ")
	public java.util.List<es.caib.seycon.ng.model.RegistreAccesEntity> findByAgentSessioIdEndDate2(
			java.lang.String system, java.lang.String sessioId,
			java.util.Date date, String hostName) {
		return null;
	}

	@Operation(translated = "findAccessLogBySessionIDAndStartDate")
	@DaoFinder("select rac\n"
			+ "from com.soffid.iam.model.AccessLogEntity rac\n"
			+ "where rac.sessionId=:sessioId and "
			+ "rac.system = :system and (rac.startDate=:date) and "
			+ "rac.server=:server and "
			+ "rac.tenant.id = :tenantId ")
	public java.util.List<es.caib.seycon.ng.model.RegistreAccesEntity> findByAgentSessioIdStartDate(
			java.lang.String system, java.lang.String sessioId,
			java.util.Date date, es.caib.seycon.ng.model.MaquinaEntity server) {
		return null;
	}

	@Operation(translated = "findAccessLogBySessionIDAndStartDate2")
	@DaoFinder("select rac\n"
			+ "from com.soffid.iam.model.AccessLogEntity rac\n"
			+ "where rac.sessionId=:sessioId and "
			+ "rac.system = :system and (rac.startDate=:date) and "
			+ "rac.hostName=:hostName and "
			+ "rac.tenant.id = :tenantId ")
	public java.util.List<es.caib.seycon.ng.model.RegistreAccesEntity> findByAgentSessioIdStartDate2(
			java.lang.String system, java.lang.String sessioId,
			java.util.Date date, String hostName) {
		return null;
	}

	@DaoFinder("from com.soffid.iam.model.AccessLogEntity rac "
			+ "where rac.server.id=:id or rac.client.id=:id and "
			+ "rac.tenant.id = :tenantId "
			+ "order by rac.startDate")
	public java.util.List<es.caib.seycon.ng.model.RegistreAccesEntity> findByHostId(
			Long id) {
		return null;
	}

	@Description("Returns true if the permission on this object is granted")
	public boolean isAllowed(String permission) { return false; }
}


@Index (name="RAC_AGE_DAT_I",	unique=false,
	entity=es.caib.seycon.ng.model.RegistreAccesEntity.class,
	columns={"RAC_TEN_ID", "RAC_CODAGE", "RAC_DATINI"})
abstract class RegistreAccesDataIndex {
}


@Index (name="RAC_AGE_I",	unique=false,
entity=es.caib.seycon.ng.model.RegistreAccesEntity.class,
columns={"RAC_TEN_ID", "RAC_IDSES", "RAC_CODAGE"})
abstract class RegistreAccesIndex {
}

@Index (name="RAC_MAQ_ORI_P_I",	unique=false,
entity=es.caib.seycon.ng.model.RegistreAccesEntity.class,
columns={"RAC_TEN_ID", "RAC_DATINI", "RAC_IDMAOR"})
abstract class RegistreAccesMaquinaIndex {
}

@Index (name="RAC_DATAINI_P_I",	unique=false,
entity=es.caib.seycon.ng.model.RegistreAccesEntity.class,
columns={"RAC_TEN_ID", "RAC_DATINI"})
abstract class RegistreAccesNomesDataIndex {
}



