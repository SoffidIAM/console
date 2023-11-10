//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import java.util.Collection;

import com.soffid.iam.authoritative.model.AuthoritativeChangeEntity;
import com.soffid.iam.model.TenantEntity;
import com.soffid.mda.annotation.*;

@Entity (table="SC_DISPAT",
	translatedName="SystemEntity", translatedPackage="com.soffid.iam.model")
@Depends ({es.caib.seycon.ng.model.DominiContrasenyaEntity.class,
	es.caib.seycon.ng.model.PoliticaContrasenyaEntity.class,
	es.caib.seycon.ng.model.TipusUsuariEntity.class,
	es.caib.seycon.ng.comu.Dispatcher.class,
	es.caib.seycon.ng.model.AuditoriaEntity.class,
	es.caib.seycon.ng.model.DominiCorreuEntity.class,
	es.caib.seycon.ng.model.GrupDispatcherEntity.class,
	es.caib.seycon.ng.model.TipusUsuariDispatcherEntity.class,
	es.caib.seycon.ng.model.GrupEntity.class,
	es.caib.seycon.ng.model.RolEntity.class,
	es.caib.seycon.ng.model.ControlAccessEntity.class,
	es.caib.seycon.ng.model.DominiUsuariEntity.class,
	es.caib.seycon.ng.model.AccountEntity.class,
	es.caib.seycon.ng.model.ObjectMappingEntity.class})
public abstract class DispatcherEntity {

	@Column (name="DIS_ID")
	@Identifier
	public java.lang.Long id;

	@Column (name="DIS_CODI", length=150, translated="name")
	@Nullable
	public java.lang.String codi;

	@Column (name="DIS_NOMCLA", length=100, translated="className")
	@Nullable
	public java.lang.String nomCla;

	@Column (name="DIS_URL", length=500)
	@Nullable
	public java.lang.String url;

	@Column (name="DIS_URL2", length=500)
	@Nullable
	public java.lang.String url2;

	@Column (name="DIS_PARAM0", length=500)
	@Nullable
	public java.lang.String param0;

	@Column (name="DIS_PARAM1", length=500)
	@Nullable
	public java.lang.String param1;

	@Column (name="DIS_PARAM2", length=500)
	@Nullable
	public java.lang.String param2;

	@Column (name="DIS_PARAM3", length=500)
	@Nullable
	public java.lang.String param3;

	@Column (name="DIS_PARAM4", length=500)
	@Nullable
	public java.lang.String param4;

	@Column (name="DIS_PARAM5", length=500)
	@Nullable
	public java.lang.String param5;

	@Column (name="DIS_PARAM6", length=500)
	@Nullable
	public java.lang.String param6;

	@Column (name="DIS_PARAM7", length=500)
	@Nullable
	public java.lang.String param7;

	@Column (name="DIS_PARAM8", length=500)
	@Nullable
	public java.lang.String param8;

	@Column (name="DIS_PARAM9", length=500)
	@Nullable
	public java.lang.String param9;

	@Column (name="DIS_BASROL", length=1, translated="roleBased")
	@Nullable
	public java.lang.String basRol;

	@Column (name="DIS_SEGUR", length=1, translated="trusted")
	@Nullable
	public java.lang.String segur;
	
	@Description("Manual account creation")
	@Column (name="DIS_MAACCR", length=1)
	@Nullable
	public Boolean manualAccountCreation;

	@Description("Full reconciliation")
	@Column (name="DIS_FULREC", length=1)
	@Nullable
	public Boolean fullReconciliation;

	@Description("Forrward changes to each agent after load")
	@Column (name="DIS_GENTAS", length=1)
	@Nullable
	public Boolean generateTasksOnLoad;

	@Column (name="DIS_TEN_ID")
	public TenantEntity tenant;
	
	@ForeignKey (foreignColumn="ROL_IDDISPAT", translated="role")
	public java.util.Collection<es.caib.seycon.ng.model.RolEntity> rol;

	@ForeignKey (foreignColumn="TPD_IDDIS", translated="userType")
	public java.util.Collection<es.caib.seycon.ng.model.TipusUsuariDispatcherEntity> tipusUsuari;

	@ForeignKey (foreignColumn="GRD_IDDIS", translated="systemGroup")
	public java.util.Collection<es.caib.seycon.ng.model.GrupDispatcherEntity> grupDispatcher;

	@Column (name="DIS_CONAC", length=1, translated="enableAccessControl")
	@Nullable
	public java.lang.String controlAcces;

	@ForeignKey (foreignColumn="CAC_DIS_ID", translated="accessControls")
	public java.util.Collection<es.caib.seycon.ng.model.ControlAccessEntity> controlAccess;

	@Column (name="DIS_DCN_ID", translated="passwordDomain")
	public es.caib.seycon.ng.model.DominiContrasenyaEntity domini;

	@Column (name="DIS_DOU_ID", translated="userDomain")
	@Nullable
	public es.caib.seycon.ng.model.DominiUsuariEntity dominiUsuari;

	@ForeignKey (foreignColumn="ACC_DIS_ID")
	public java.util.Collection<es.caib.seycon.ng.model.AccountEntity> accounts;

	@Column (name="DIS_MAIN", defaultValue="false", translated="mainSystem")
	public boolean mainDispatcher;

	@Column (name="DIS_RDONLY",
		defaultValue="false")
	public boolean readOnly;

	@Nullable
	@Column (name="DIS_PAUSEY",
			defaultValue="false")
	public boolean pause;

	@Column (name="DIS_AUTHRT",
		defaultValue="false")
	@Nullable
	public java.lang.Boolean authoritative;

	@Column (name="DIS_BLOPAR")
	@Nullable
	public byte[] blobParam;

	@Column (name="DIS_TIMSTA")
	@Nullable
	public java.util.Date timeStamp;

	@Column (name="DIS_CREATED")
	@Nullable
	public java.util.Date created;

	@ForeignKey (foreignColumn="OBM_DIS_ID")
	public java.util.Collection<es.caib.seycon.ng.model.ObjectMappingEntity> objectMappings;

	@Column (name="DIS_DESCRI", length=250)
	@Nullable
	public java.lang.String description;

	@Column (name="DIS_AUTPRO", length=250)
	@Description ("Approval process to launch when an incoming authoritative change is received")
	@Nullable
	public String authoritativeProcess;
	
	@Column (name="DIS_SHARED")
	@Description ("false to use a dedicated server thread. true to use a shared server thread")
	@Nullable
	public Boolean sharedDispatcher;
	
	@Column (name="DIS_THREADS", defaultValue="1L")
	@Description ("Number of concurrent threads to process this agent tasks")
	@Nullable
	public Long threads;
	
	@ForeignKey(foreignColumn="PAU_DIS_ID")
	@Description ("Current authoritative changes pending to apply")
	public Collection<AuthoritativeChangeEntity> pendingChanges;
	
	@Column (name="DIS_TIMOUT")
	@Description("Time out for normal operations (milliseconds)")
	@Nullable
	Long timeout;
	
	@Column (name="DIS_LOTIOT")
	@Description("Time out for long operations (milliseconds)")
	@Nullable
	Long longTimeout;	
	
	@Column (name="DIS_TYPE")
	@Description("Usage of dispatcher: PAM, IAM or SSE")
	@Nullable
	String usage;	

	// ************************ DAOS ******************************
	@DaoFinder("from com.soffid.iam.model.SystemEntity se "
			+ "where (:name is null or se.name like :name) and "
			+ "(:className is null or se.className like :className) and "
			+ "(:url is null or upper(url) like upper(:url) or upper(url2) like upper(:url)) and\n"
			+ "(:roleBased is null or se.roleBased = :roleBased) and "
			+ "(:trusted is null or se.trusted = :trusted) and "
			+ "(:active is null or se.url is not null) and "
			+ "se.tenant.id = :tenantId")
	public java.util.List<es.caib.seycon.ng.model.DispatcherEntity> findByFilter(
		java.lang.String name, 
		java.lang.String className, 
		java.lang.String url, 
		java.lang.String roleBased, 
		java.lang.String trusted, 
		java.lang.String active) {
	 return null;
	}
	@Operation(translated="findByName")
	@DaoFinder("from com.soffid.iam.model.SystemEntity s "
			+ "where s.name = :name and s.tenant.id = :tenantId")
	public es.caib.seycon.ng.model.DispatcherEntity findByCodi(
		java.lang.String name) {
	 return null;
	}
	
	@Operation(translated="findActives")
	@DaoFinder("from com.soffid.iam.model.SystemEntity agent "
			+ "where agent.url is not null and agent.tenant.id = :tenantId "
			+ "order by agent.name")
	public java.util.List<es.caib.seycon.ng.model.DispatcherEntity> findActius() {
	 return null;
	}
	@Operation(translated="findSoffidSystem")
	@DaoFinder("select dis "
			+ "from com.soffid.iam.model.SystemEntity as dis "
			+ "where dis.mainSystem = true and dis.tenant.id = :tenantId")
	public es.caib.seycon.ng.model.DispatcherEntity findSoffidDispatcher() {
	 return null;
	}


	@DaoFinder("select dis "
			+ "from com.soffid.iam.model.SystemEntity as dis "
			+ "where dis.id in ("
				+ "select distinct dis2.id "
				+ "from com.soffid.iam.model.SystemEntity as dis2 "
				+ "join dis2.tenant as tenant "
				+ "join tenant.servers as server "
				+ "where server.tenantServer.name = :server)")
	public Collection<es.caib.seycon.ng.model.DispatcherEntity> findServerTenants(String server) {
		return null;
	}
	
	@DaoFinder("select dis "
				+ "from com.soffid.iam.model.SystemEntityImpl as dis, com.soffid.iam.model.AgentDescriptorEntity d "
				+ "where (dis.url = :url or dis.url2 = :url) and "
				+ "dis.className = d.className and "
				+ "d.service = :t")
	public Collection<es.caib.seycon.ng.model.DispatcherEntity> findServices(String url, boolean t) {
		return null;
	}

	public Collection<es.caib.seycon.ng.model.DispatcherEntity> findByUsage(String usage) {
		return null;
	}
}

@Index (name="DIS_UK_CODI",	unique=true,
entity=es.caib.seycon.ng.model.DispatcherEntity.class,
columns={"DIS_TEN_ID", "DIS_CODI"})
abstract class DispatcherIndex {
}
