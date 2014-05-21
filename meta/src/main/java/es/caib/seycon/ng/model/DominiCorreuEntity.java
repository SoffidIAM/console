//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.mda.annotation.*;

@Entity (table="SC_DOMCOR" )
@Depends ({es.caib.seycon.ng.comu.DominiCorreu.class,
	es.caib.seycon.ng.model.AuditoriaEntity.class,
	es.caib.seycon.ng.model.UsuariEntity.class,
	es.caib.seycon.ng.model.LlistaCorreuEntity.class})
public abstract class DominiCorreuEntity {

	@Column (name="DCO_ID")
	@Identifier
	public java.lang.Long id;

	@Column (name="DCO_DESCRI", length=50)
	@Nullable
	public java.lang.String descripcio;

	@Column (name="DCO_CODI", length=50)
	public java.lang.String codi;

	@ForeignKey (foreignColumn="USU_IDDCO")
	public java.util.Collection<es.caib.seycon.ng.model.UsuariEntity> usuaris;

	@ForeignKey (foreignColumn="LCO_IDDCO")
	public java.util.Collection<es.caib.seycon.ng.model.LlistaCorreuEntity> llistesCorreu;

	@Column (name="DCO_OBSOLET", length=5)
	public java.lang.String obsolet;

	@DaoFinder
	public es.caib.seycon.ng.model.DominiCorreuEntity findByCodi(
		java.lang.String codi) {
	 return null;
	}
	@DaoFinder("from \nes.caib.seycon.ng.model.DominiCorreuEntity dominiCorreu \nwhere \n(:codi is null or dominiCorreu.codi like :codi) and \n(:descripcio is null or dominiCorreu.descripcio like :descripcio) and \n(:obsolet is null or dominiCorreu.obsolet = :obsolet) \norder by dominiCorreu.codi")
	public java.util.List<es.caib.seycon.ng.model.DominiCorreuEntity> findByFiltre(
		java.lang.String codi, 
		java.lang.String descripcio, 
		java.lang.String obsolet) {
	 return null;
	}
	@DaoFinder
	public java.util.List<es.caib.seycon.ng.model.DominiCorreuEntity> find(
		@Nullable java.util.Collection<es.caib.seycon.ng.model.Parameter> parameters) {
	 return null;
	}
}
