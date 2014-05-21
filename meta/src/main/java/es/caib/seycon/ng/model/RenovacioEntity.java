//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.mda.annotation.*;

@Entity (table="SC_FARREN" )
@Depends ({es.caib.seycon.ng.comu.Renovacio.class,
	es.caib.seycon.ng.model.UsuariEntity.class})
public abstract class RenovacioEntity {

	@Column (name="FAR_ID")
	@Identifier
	public java.lang.Long id;

	@Column (name="FAR_DATA")
	@Nullable
	public java.util.Date data;

	@Column (name="FAR_REN")
	@Nullable
	public java.util.Date dataRenovacio;

	@Column (name="FAR_ACTIU", length=3)
	@Nullable
	public java.lang.String actiu;

	@Column (name="FAR_IDUSU")
	public es.caib.seycon.ng.model.UsuariEntity usuari;

	@DaoFinder
	public java.lang.String desarRenovacio(
		java.lang.Long idUsuari, 
		java.util.Date dataRenovacio) {
	 return null;
	}
	@DaoFinder
	public java.util.List<es.caib.seycon.ng.model.RenovacioEntity> findByUsuari(
		es.caib.seycon.ng.model.UsuariEntity usuari) {
	 return null;
	}
	@DaoFinder
	public java.util.List<es.caib.seycon.ng.model.RenovacioEntity> findByFiltre(
		es.caib.seycon.ng.comu.RenovacioSearchCriteria criteriDeCerca) {
	 return null;
	}
	@DaoFinder
	public java.util.List<es.caib.seycon.ng.model.RenovacioEntity> find(
		@Nullable java.util.Collection<es.caib.seycon.ng.model.Parameter> parameters) {
	 return null;
	}
}
