//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.mda.annotation.*;

@Entity (table="SC_DOMCON" )
@Depends ({es.caib.seycon.ng.model.PoliticaContrasenyaEntity.class,
	es.caib.seycon.ng.comu.DominiContrasenya.class,
	es.caib.seycon.ng.model.DominiUsuariEntity.class,
	es.caib.seycon.ng.model.AuditoriaEntity.class,
	es.caib.seycon.ng.model.DispatcherEntity.class})
public abstract class DominiContrasenyaEntity {

	@Column (name="DCN_ID")
	@Identifier
	public java.lang.Long id;

	@Column (name="DCN_CODI", length=50)
	public java.lang.String codi;

	@Column (name="DCN_DESC", length=100)
	@Nullable
	public java.lang.String descripcio;

	@ForeignKey (foreignColumn="PCD_DCN_ID")
	public java.util.Collection<es.caib.seycon.ng.model.PoliticaContrasenyaEntity> politicaContrasenyes;

	@ForeignKey (foreignColumn="DIS_DCN_ID")
	public java.util.Collection<es.caib.seycon.ng.model.DispatcherEntity> dispatchers;

	@DaoFinder
	public es.caib.seycon.ng.model.DominiContrasenyaEntity findByCodi(
		java.lang.String codi) {
	 return null;
	}
	@DaoFinder("select dominiContrasenya \nfrom es.caib.seycon.ng.model.UsuariEntity as usuari \ninner join usuari.tipusUsuari as tipus \ninner join tipus.politiques as politica  with politica.tipus='M'  \ninner join politica.dominiContrasenya as dominiContrasenya\nwhere usuari.id= :userId")
	public es.caib.seycon.ng.model.DominiContrasenyaEntity findDefaultDomain(
		long userId) {
	 return null;
	}
	@DaoFinder("select de.domini\nfrom es.caib.seycon.ng.model.DispatcherEntity as de\nwhere de.codi=:dispatcher")
	public es.caib.seycon.ng.model.DominiContrasenyaEntity findByDispatcher(
		java.lang.String dispatcher) {
	 return null;
	}
}
