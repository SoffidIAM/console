//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.mda.annotation.*;

@Entity (table="SC_SECRET", translatedName="SecretEntity", translatedPackage="com.soffid.iam.model" )
@Depends ({es.caib.seycon.ng.model.ServerEntity.class,
	es.caib.seycon.ng.model.UsuariEntity.class})
public abstract class SecretEntity {

	@Column (name="SEC_IDSRV")
	public es.caib.seycon.ng.model.ServerEntity server;

	@Column (name="SEC_IDUSU", translated="user")
	public es.caib.seycon.ng.model.UsuariEntity usuari;

	@Column (name="SEC_ID")
	@Identifier
	public java.lang.Long id;

	@Column (name="SEC_VALOR", length=64000)
	public byte[] secrets;

	@DaoFinder("select secret\nfrom es.caib.seycon.ng.model.SecretEntity as secret\nwhere secret.server.id=:serverId\nand     secret.usuari.id=:userId")
	public es.caib.seycon.ng.model.SecretEntity findByUserAndServer(
		long userId, 
		long serverId) {
	 return null;
	}
	@DaoFinder
	public java.util.List<es.caib.seycon.ng.model.SecretEntity> findByServer(
		es.caib.seycon.ng.model.ServerEntity server) {
	 return null;
	}
}
