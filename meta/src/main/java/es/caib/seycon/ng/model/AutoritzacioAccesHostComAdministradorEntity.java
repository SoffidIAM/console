//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.mda.annotation.*;

@Entity (table="SC_ADMMAQ" )
@Depends ({es.caib.seycon.ng.comu.AutoritzacioAccesHostComAdministrador.class,
	es.caib.seycon.ng.model.MaquinaEntity.class,
	es.caib.seycon.ng.model.UsuariEntity.class,
	es.caib.seycon.ng.model.DadaUsuariEntity.class})
public abstract class AutoritzacioAccesHostComAdministradorEntity {

	@Column (name="ADM_ID")
	@Identifier
	public java.lang.Long id;

	@Column (name="ADM_DATCAD")
	public java.util.Date dataCaducitatAutoritzacioAcces;

	@Column (name="ADM_USUID")
	public es.caib.seycon.ng.model.UsuariEntity usuari;

	@Column (name="ADM_MAQID")
	public es.caib.seycon.ng.model.MaquinaEntity host;

	@Column (name="ADM_IDWF")
	public java.lang.Long idProcesWorkflow;

	@Column (name="ADM_DATINI")
	@Nullable
	public java.util.Date dataPeticio;

	@DaoFinder("select autoriza from es.caib.seycon.ng.model.AutoritzacioAccesHostComAdministradorEntity autoriza where autoriza.host.nom = :nomHost and (:dataPeticio = :nullDate or autoriza.dataPeticio >= :dataPeticio) and (:dataCaducitat = :nullDate or autoriza.dataCaducitatAutoritzacioAcces >= :dataCaducitat) order by autoriza.dataPeticio")
	public java.util.List<es.caib.seycon.ng.model.AutoritzacioAccesHostComAdministradorEntity> findByNomHostIDataPeticio(
		java.lang.String nomHost, 
		@Nullable java.util.Date dataPeticio, 
		@Nullable java.util.Date dataCaducitat, 
		java.util.Date nullDate) {
	 return null;
	}
}
