//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.mda.annotation.*;

@Entity (table="SC_REPLDB", translatedName="ReplicaDatabaseEntity", translatedPackage="com.soffid.iam.model")
@Depends ({es.caib.seycon.ng.comu.ReplicaDatabase.class,
	es.caib.seycon.ng.model.ServerEntity.class,
	es.caib.seycon.ng.model.DispatcherEntity.class})
public abstract class ReplicaDatabaseEntity {

	@Column (name="RPL_ID")
	@Identifier
	public java.lang.Long id;

	@Column (name="RPL_NAME")
	public java.lang.String name;

	@Column (name="RPL_USER", length=128)
	@Nullable
	public java.lang.String userName;

	@Column (name="RPL_PASSWD", length=128)
	@Nullable
	public java.lang.String password;

	@ForeignKey (foreignColumn="SRV_DBA_ID")
	public java.util.Collection<es.caib.seycon.ng.model.ServerEntity> servers;

	@Column (name="RPL_DIS_ID", translated="system")
	@Nullable
	public es.caib.seycon.ng.model.DispatcherEntity dispatcher;

	@Column (name="RPL_URL", length=128)
	public java.lang.String url;

	@Column (name="RPL_IDSEED")
	public java.lang.Long idSeed;

}
