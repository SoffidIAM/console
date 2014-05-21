//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.mda.annotation.*;

@Entity (table="SC_NOTIFICA" )
@Depends ({es.caib.seycon.ng.model.RolEntity.class,
	es.caib.seycon.ng.model.UsuariEntity.class,
	es.caib.seycon.ng.model.AplicacioEntity.class,
	es.caib.seycon.ng.comu.Notificacio.class})
public abstract class NotificacioEntity {

	@Column (name="NTF_ID")
	@Identifier
	public java.lang.Long id;

	@Column (name="NTF_DATA")
	public java.util.Date dataModificacio;

	@Column (name="NTF_INFO", length=512)
	public java.lang.String informacio;

	@Column (name="NTF_USU")
	public es.caib.seycon.ng.model.UsuariEntity usuari;

	@Column (name="NTF_ROL")
	@Nullable
	public es.caib.seycon.ng.model.RolEntity rol;

	@Column (name="NTF_APL")
	public es.caib.seycon.ng.model.AplicacioEntity aplicacio;

	@DaoFinder("from es.caib.seycon.ng.model.NotificacioEntity notifica where notifica.aplicacio.codi = :codiAplicacio order by notifica.dataModificacio asc")
	public java.util.List<es.caib.seycon.ng.model.NotificacioEntity> findByCodiAplicacio(
		java.lang.String codiAplicacio) {
	 return null;
	}
	@DaoFinder("from es.caib.seycon.ng.model.NotificacioEntity notifica order by notifica.dataModificacio asc")
	public java.util.List<es.caib.seycon.ng.model.NotificacioEntity> findAll() {
	 return null;
	}
}
