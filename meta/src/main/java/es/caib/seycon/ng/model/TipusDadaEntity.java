//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.mda.annotation.*;

@Entity (table="SC_TIPDAD" )
@Depends ({es.caib.seycon.ng.model.AuditoriaEntity.class,
	es.caib.seycon.ng.comu.TipusDada.class,
	es.caib.seycon.ng.model.DadaUsuariEntity.class})
public abstract class TipusDadaEntity {

	@Column (name="TDA_CODI", length=25)
	public java.lang.String codi;

	@Column (name="TDA_ORDRE")
	public java.lang.Long ordre;

	@ForeignKey (foreignColumn="DUS_TDAID")
	public java.util.Collection<es.caib.seycon.ng.model.DadaUsuariEntity> datos;

	@Column (name="TDA_ID")
	@Identifier
	public java.lang.Long id;

	@Column (name="TDA_TYPE", length=50)
	@Nullable
	public es.caib.seycon.ng.comu.TypeEnumeration type;

	@Column (name="TDA_SIZE")
	@Nullable
	public java.lang.Integer size;
	
	@Description ("blank separated list of url-encoded values")
	@Column (name="TDA_VALUE", length=64000)
	@Nullable
	public String values;

	@Description("Label to display")
	@Column (name="TDA_LABEL", length=64)
	@Nullable
	public String label;
	
	/********************** DAOS ************************/
	@DaoFinder("from es.caib.seycon.ng.model.TipusDadaEntity where codi = :codi")
	public es.caib.seycon.ng.model.TipusDadaEntity findTipusDadaByCodi(
		java.lang.String codi) {
	 return null;
	}
	@DaoFinder("from es.caib.seycon.ng.model.TipusDadaEntity tipusDada where (:codi is null or tipusDada.codi like :codi)")
	public java.util.List<es.caib.seycon.ng.model.TipusDadaEntity> findTipusDadesByCodi(
		java.lang.String codi) {
	 return null;
	}
	@DaoFinder
	public java.util.List<es.caib.seycon.ng.model.TipusDadaEntity> find(
		@Nullable java.util.Collection<es.caib.seycon.ng.model.Parameter> parameters) {
	 return null;
	}
}
