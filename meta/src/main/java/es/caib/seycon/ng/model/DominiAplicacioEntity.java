//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.mda.annotation.*;

@Entity (table="SC_DOMAPP" )
@Depends ({es.caib.seycon.ng.comu.Domini.class,
	es.caib.seycon.ng.model.GrupEntity.class,
	es.caib.seycon.ng.model.AplicacioEntity.class,
	es.caib.seycon.ng.model.UsuariEntity.class,
	es.caib.seycon.ng.model.RolEntity.class,
	es.caib.seycon.ng.model.AuditoriaEntity.class,
	es.caib.seycon.ng.model.ValorDominiAplicacioEntity.class})
public abstract class DominiAplicacioEntity {

	@Column (name="DOM_ID")
	@Identifier
	public java.lang.Long id;

	@ForeignKey (foreignColumn="VDO_DOM")
	public java.util.Collection<es.caib.seycon.ng.model.ValorDominiAplicacioEntity> valors;

	@ForeignKey (foreignColumn="ROL_DOMAPP")
	public java.util.Collection<es.caib.seycon.ng.model.RolEntity> rols;

	@Column (name="DOM_NOM", length=30)
	public java.lang.String nom;

	@Column (name="DOM_APP")
	@Nullable
	public es.caib.seycon.ng.model.AplicacioEntity aplicacio;

	@Column (name="DOM_DESC", length=50)
	@Nullable
	public java.lang.String descripcio;

	@DaoFinder("select dominiAplicacio from es.caib.seycon.ng.model.DominiAplicacioEntity as dominiAplicacio \nleft join dominiAplicacio.rols as rol \nwhere ((:nomRol is null and rol is null) or (:nomRol is not null and rol.nom = :nomRol)) and \ndominiAplicacio.nom = :nomDomini")
	public es.caib.seycon.ng.model.DominiAplicacioEntity findByNomDominiAndNomRol(
		java.lang.String nomDomini, 
		java.lang.String nomRol) {
	 return null;
	}
	@DaoFinder("select dominiAplicacio from es.caib.seycon.ng.model.DominiAplicacioEntity dominiAplicacio left join dominiAplicacio.rols rol where rol.nom = :nomRol")
	public java.util.List<es.caib.seycon.ng.model.DominiAplicacioEntity> findByNomRol(
		java.lang.String nomRol) {
	 return null;
	}
	@DaoFinder("select dominiAplicacio \nfrom es.caib.seycon.ng.model.DominiAplicacioEntity dominiAplicacio\n left join dominiAplicacio.aplicacio aplicacio \nwhere \naplicacio.codi = :codiAplicacio \norder by dominiAplicacio.nom")
	public java.util.List<es.caib.seycon.ng.model.DominiAplicacioEntity> findByCodiAplicacio(
		java.lang.String codiAplicacio) {
	 return null;
	}
	@DaoFinder("select dominiAplicacio from es.caib.seycon.ng.model.DominiAplicacioEntity dominiAplicacio left join dominiAplicacio.aplicacio aplicacio where aplicacio.codi like :codisAplicacions")
	public java.util.List<es.caib.seycon.ng.model.DominiAplicacioEntity> findByCodisAplicacions(
		java.lang.String codisAplicacions) {
	 return null;
	}
	@DaoFinder
	public java.util.List<es.caib.seycon.ng.model.DominiAplicacioEntity> find(
		@Nullable java.util.Collection<es.caib.seycon.ng.model.Parameter> parameters) {
	 return null;
	}
}
