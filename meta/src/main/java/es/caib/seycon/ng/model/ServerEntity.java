//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.mda.annotation.*;

@Entity (table="SC_SERVER" )
@Depends ({es.caib.seycon.ng.comu.Server.class,
	es.caib.seycon.ng.model.ReplicaDatabaseEntity.class,
	es.caib.seycon.ng.model.SecretEntity.class,
	com.soffid.iam.model.ScheduledTaskEntity.class})
public abstract class ServerEntity {

	@Column (name="SRV_ID")
	@Identifier
	public java.lang.Long id;

	@Column (name="SRV_NOM", length=100)
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

	@Column (name="SRV_DBA_ID")
	@Nullable
	public es.caib.seycon.ng.model.ReplicaDatabaseEntity backupDatabase;

	@Column (name="SRV_URL")
	@Nullable
	public java.lang.String url;

	@ForeignKey (foreignColumn="SEC_IDSRV")
	public java.util.Collection<es.caib.seycon.ng.model.SecretEntity> secrets;

	@ForeignKey (foreignColumn="SCT_SERVER")
	public java.util.Collection<com.soffid.iam.model.ScheduledTaskEntity> scheduledTasks;

	@DaoFinder
	public es.caib.seycon.ng.model.ServerEntity findByNom(
		java.lang.String nom) {
	 return null;
	}
}
