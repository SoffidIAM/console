//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.mda.annotation.*;

@Entity (table="SC_ROLPUE" )
@Depends ({es.caib.seycon.ng.comu.AutoritzacioPuntEntrada.class,
	es.caib.seycon.ng.model.RolEntity.class,
	es.caib.seycon.ng.model.AuditoriaEntity.class,
	es.caib.seycon.ng.model.PuntEntradaEntity.class})
public abstract class AutoritzacioPUERolEntity {

	@Column (name="RPE_IDPUE")
	public es.caib.seycon.ng.model.PuntEntradaEntity puntEntrada;

	@Column (name="RPE_NIVAUT", length=1)
	public java.lang.String nivellAutoritzacio;

	@Column (name="RPE_ID")
	@Identifier
	public java.lang.Long id;

	@Column (name="RPE_IDROL", composition=true)
	public RolEntity role;

	@DaoFinder("from es.caib.seycon.ng.model.AutoritzacioPUERolEntity")
	public java.util.List<es.caib.seycon.ng.model.AutoritzacioPUERolEntity> findAll() {
	 return null;
	}
	@DaoFinder("select autor.puntEntrada "
			+ "from es.caib.seycon.ng.model.AutoritzacioPUERolEntity autor "
			+ "where autor.role.id=:idRol")
	public java.util.List<es.caib.seycon.ng.model.AutoritzacioPUERolEntity> findByIdRol(
		java.lang.Long idRol) {
	 return null;
	}
}
