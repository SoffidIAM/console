//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import java.util.Collection;

import com.soffid.iam.model.TenantEntity;
import com.soffid.mda.annotation.*;

@Entity (table="SC_SERVER", translatedName="ServerEntity", translatedPackage="com.soffid.iam.model" )
@Depends ({es.caib.seycon.ng.comu.Server.class,
	es.caib.seycon.ng.model.SecretEntity.class,
	com.soffid.iam.model.ScheduledTaskEntity.class})
public abstract class ServerEntity {

	@Column (name="SRV_ID")
	@Identifier
	public java.lang.Long id;

	@Column (name="SRV_NOM", length=100, translated="name")
	public java.lang.String nom;

	@Column (name="SRV_PK")
	@Nullable
	public byte[] pk;

	@Column (name="SRV_AUTH", length=50)
	@Nullable
	public java.lang.String auth;

	@Column (name="SRV_TYPE", length=10)
	@Nullable
	public es.caib.seycon.ng.comu.ServerType type;

	@Column (name="SRV_USEMDB")
	@Nullable
	public java.lang.Boolean useMasterDatabase;

	@Column (name="SRV_URL")
	@Nullable
	public java.lang.String url;

	@Column (name="SRV_JVMOPT", length=256)
	@Nullable
	public java.lang.String javaOptions;

	@ForeignKey (foreignColumn="SEC_IDSRV")
	public java.util.Collection<es.caib.seycon.ng.model.SecretEntity> secrets;

	@ForeignKey (foreignColumn="SCT_SERVER")
	public java.util.Collection<com.soffid.iam.model.ScheduledTaskEntity> scheduledTasks;

	@Operation(translated="findByName")
	@DaoFinder
	public es.caib.seycon.ng.model.ServerEntity findByNom(
		java.lang.String name) {
	 return null;
	}

	@DaoFinder("select server "
			+ "from com.soffid.iam.model.ServerEntity as server "
			+ "join server.tenants as t "
			+ "where t.serverTenant.name = :name")
	public Collection<es.caib.seycon.ng.model.ServerEntity> findByTenant(
		java.lang.String name) {
	 return null;
	}

	@DaoFinder("select server "
			+ "from com.soffid.iam.model.ServerEntity as server "
			+ "join server.tenants as t "
			+ "where t.serverTenant.name = :name and server.type='gateway'")
	public Collection<es.caib.seycon.ng.model.ServerEntity> findGatewayByTenant(
		java.lang.String name) {
	 return null;
	}

	@DaoFinder("select count(*) "
			+ "from com.soffid.iam.model.ServerEntity as server "
			+ "where server.name like :name")
	public Long countServersByName(java.lang.String name) {
		return null;
	}
	
	@DaoFinder("select server "
			+ "from com.soffid.iam.model.ServerEntity as server "
			+ "where server.url=:url and server.type='remote'")
	public ServerEntity findRemoteByUrl (String url) { return null;}
}

@Index (name="SC_SERVER_UK1",	unique=true,
entity=es.caib.seycon.ng.model.ServerEntity.class,
columns={"SRV_NOM"})
abstract class ServerIndex {
}
