//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.mda.annotation.*;

@Entity (table="SC_IMPRES" )
@Depends ({es.caib.seycon.ng.model.AuditoriaEntity.class,
	es.caib.seycon.ng.model.TasqueEntity.class,
	es.caib.seycon.ng.comu.Impressora.class,
	es.caib.seycon.ng.model.MaquinaEntity.class,
	es.caib.seycon.ng.model.GrupImpressoraEntity.class,
	es.caib.seycon.ng.model.UsuariImpressoraEntity.class})
public abstract class ImpressoraEntity {

	@Column (name="IMP_ID")
	@Identifier
	public java.lang.Long id;

	@Column (name="IMP_MODEL", length=50)
	@Nullable
	public java.lang.String model;

	@Column (name="IMP_CODI", length=12)
	public java.lang.String codi;

	@Column (name="IMP_LOCAL", length=1)
	@Nullable
	public java.lang.String local;

	@Column (name="IMP_IDMAQ")
	public es.caib.seycon.ng.model.MaquinaEntity servidor;

	@ForeignKey (foreignColumn="GIM_IDIMP")
	public java.util.Collection<es.caib.seycon.ng.model.GrupImpressoraEntity> grups;

	@ForeignKey (foreignColumn="UIM_IDIMP")
	public java.util.Collection<es.caib.seycon.ng.model.UsuariImpressoraEntity> usuaris;

	@DaoFinder
	public es.caib.seycon.ng.model.ImpressoraEntity findByCodi(
		java.lang.String codi) {
	 return null;
	}
	@DaoFinder("select impressora \nfrom \nes.caib.seycon.ng.model.ImpressoraEntity impressora \nwhere \n(:codi is null or impressora.codi like :codi) and \n(:model is null or impressora.model like :model) and \n(:local is null or impressora.local = :local) and \n(:maquina is null or  impressora.servidor.nom like :maquina) \norder by impressora.codi")
	public java.util.List<es.caib.seycon.ng.model.ImpressoraEntity> findImpressoresByCriteri(
		java.lang.String model, 
		java.lang.String codi, 
		java.lang.String local, 
		java.lang.String maquina) {
	 return null;
	}
}
