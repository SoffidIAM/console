//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import java.util.Collection;

import com.soffid.iam.authoritative.model.AuthoritativeChangeEntity;
import com.soffid.mda.annotation.*;

@Entity (table="SC_DISPAT" )
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
	es.caib.seycon.ng.model.ReplicaDatabaseEntity.class,
	es.caib.seycon.ng.model.ObjectMappingEntity.class})
public abstract class DispatcherEntity {

	@Column (name="DIS_ID")
	@Identifier
	public java.lang.Long id;

	@Column (name="DIS_CODI", length=50)
	@Nullable
	public java.lang.String codi;

	@Column (name="DIS_NOMCLA", length=100)
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

	@Column (name="DIS_BASROL", length=1)
	@Nullable
	public java.lang.String basRol;

	@Column (name="DIS_SEGUR", length=1)
	@Nullable
	public java.lang.String segur;

	@Description("Manual account creation")
	@Column (name="DIS_MAACCR", length=1)
	@Nullable
	public Boolean manualAccountCreation;

	@ForeignKey (foreignColumn="ROL_IDDISPAT")
	public java.util.Collection<es.caib.seycon.ng.model.RolEntity> rol;

	@ForeignKey (foreignColumn="TPD_IDDIS")
	public java.util.Collection<es.caib.seycon.ng.model.TipusUsuariDispatcherEntity> tipusUsuari;

	@ForeignKey (foreignColumn="GRD_IDDIS")
	public java.util.Collection<es.caib.seycon.ng.model.GrupDispatcherEntity> grupDispatcher;

	@Column (name="DIS_CONAC", length=1)
	@Nullable
	public java.lang.String controlAcces;

	@ForeignKey (foreignColumn="CAC_DIS_ID")
	public java.util.Collection<es.caib.seycon.ng.model.ControlAccessEntity> controlAccess;

	@Column (name="DIS_DCN_ID")
	public es.caib.seycon.ng.model.DominiContrasenyaEntity domini;

	@Column (name="DIS_DOU_ID")
	@Nullable
	public es.caib.seycon.ng.model.DominiUsuariEntity dominiUsuari;

	@ForeignKey (foreignColumn="ACC_DIS_ID")
	public java.util.Collection<es.caib.seycon.ng.model.AccountEntity> accounts;

	@Column (name="DIS_MAIN",
		defaultValue="false")
	public boolean mainDispatcher;

	@Column (name="DIS_RDONLY",
		defaultValue="false")
	public boolean readOnly;

	@ForeignKey (foreignColumn="RPL_DIS_ID")
	public java.util.Collection<es.caib.seycon.ng.model.ReplicaDatabaseEntity> replicaDatabases;

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
	
	/************************ DAOS *******************************/
	@DaoFinder("from es.caib.seycon.ng.model.DispatcherEntity\n"
			+ "where\n"
			+ "(:codi is null or upper(codi) like upper(:codi)) and\n"
			+ "(:nomCla is null or upper(nomCla) like upper(:nomCla)) and\n"
			+ "(:url is null or upper(url) like upper(:url) or upper(url2) like upper(:url)) and\n"
			+ "(:basRol is null or upper(basRol) = upper(:basRol)) and\n"
			+ "(:segur is null or segur = :segur) and\n"
			+ "(:actiu is null or url is not null)")
	public java.util.List<es.caib.seycon.ng.model.DispatcherEntity> findDispatchersByFiltre(
		java.lang.String codi, 
		java.lang.String nomCla, 
		java.lang.String url, 
		java.lang.String basRol, 
		java.lang.String segur, 
		java.lang.String actiu) {
	 return null;
	}
	@DaoFinder
	public es.caib.seycon.ng.model.DispatcherEntity findByCodi(
		java.lang.String codi) {
	 return null;
	}
	@DaoFinder
	public java.util.List<es.caib.seycon.ng.model.DispatcherEntity> find(
		@Nullable java.util.Collection<es.caib.seycon.ng.model.Parameter> parameters) {
	 return null;
	}
	@DaoFinder("from es.caib.seycon.ng.model.DispatcherEntity agent\nwhere agent.url is not null order by agent.codi")
	public java.util.List<es.caib.seycon.ng.model.DispatcherEntity> findActius() {
	 return null;
	}
	@DaoFinder("select dis\nfrom es.caib.seycon.ng.model.DispatcherEntity as dis\nwhere dis.mainDispatcher = true")
	public es.caib.seycon.ng.model.DispatcherEntity findSoffidDispatcher() {
	 return null;
	}
}
