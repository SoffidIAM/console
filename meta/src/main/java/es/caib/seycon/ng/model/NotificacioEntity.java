//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.mda.annotation.*;

@Entity (table="SC_NOTIFICA", translatedName="NoticeEntity", translatedPackage="com.soffid.iam.model" )
@Depends ({es.caib.seycon.ng.model.RolEntity.class,
	es.caib.seycon.ng.model.UsuariEntity.class,
	es.caib.seycon.ng.model.AplicacioEntity.class,
	es.caib.seycon.ng.comu.Notificacio.class})
public abstract class NotificacioEntity {

	@Column (name="NTF_ID")
	@Identifier
	public java.lang.Long id;

	@Column (name="NTF_DATA", translated="modificationDate")
	public java.util.Date dataModificacio;

	@Column (name="NTF_INFO", length=512, translated="information")
	public java.lang.String informacio;

	@Column (name="NTF_USU", translated="user")
	public es.caib.seycon.ng.model.UsuariEntity usuari;

	@Column (name="NTF_ROL", translated="role")
	@Nullable
	public es.caib.seycon.ng.model.RolEntity rol;

	@Column (name="NTF_APL", translated="application")
	public es.caib.seycon.ng.model.AplicacioEntity aplicacio;

	@Operation(translated="findByApplicationCode")
	@DaoFinder("from com.soffid.iam.model.NoticeEntity notifica where "
			+ "notifica.application.name = :informationSystem and "
			+ "notifica.application.tenant.id = :tenantId "
			+ "order by notifica.modificationDate asc")
	public java.util.List<es.caib.seycon.ng.model.NotificacioEntity> findByCodiAplicacio(
		java.lang.String informationSystem) {
	 return null;
	}
	
	@DaoFinder("from com.soffid.iam.model.NoticeEntity notifica "
			+ "order by notifica.modificationDate asc")
	public java.util.List<es.caib.seycon.ng.model.NotificacioEntity> findAll() {
	 return null;
	}
}
