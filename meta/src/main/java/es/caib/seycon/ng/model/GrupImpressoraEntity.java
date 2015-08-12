//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.mda.annotation.*;

@Entity (table="SC_GRUIMP" )
@Depends ({es.caib.seycon.ng.comu.GrupImpressora.class,
	es.caib.seycon.ng.model.ImpressoraEntity.class,
	es.caib.seycon.ng.model.GrupEntity.class,
	es.caib.seycon.ng.model.AuditoriaEntity.class,
	es.caib.seycon.ng.model.TasqueEntity.class})
public abstract class GrupImpressoraEntity {

	@Column (name="GIM_ORDRE")
	@Nullable
	public java.lang.Long ordre;

	@Column (name="GIM_IDGRU")
	public es.caib.seycon.ng.model.GrupEntity grup;

	@Column (name="GIM_IDIMP")
	public es.caib.seycon.ng.model.ImpressoraEntity impressora;

	@Column (name="GIM_ID")
	@Identifier
	public java.lang.Long id;

	@DaoFinder("select grupImpressora from es.caib.seycon.ng.model.GrupImpressoraEntity grupImpressora where grupImpressora.grup.codi = :codiGrup and grupImpressora.impressora.codi = :codiImpressora")
	public es.caib.seycon.ng.model.GrupImpressoraEntity findGrupImpressoraByCodiGrupAndCodiImpressora(
		java.lang.String codiGrup, 
		java.lang.String codiImpressora) {
	 return null;
	}
	@DaoFinder("select grupImpressora from es.caib.seycon.ng.model.GrupImpressoraEntity grupImpressora where grupImpressora.impressora.codi = :codiImpressora")
	public java.util.List<es.caib.seycon.ng.model.GrupImpressoraEntity> findGrupImpressoresByCodiImpressora(
		java.lang.String codiImpressora) {
	 return null;
	}
	@DaoFinder("select grupImpressora from es.caib.seycon.ng.model.GrupImpressoraEntity grupImpressora where grupImpressora.grup.codi = :codiGrup")
	public java.util.List<es.caib.seycon.ng.model.GrupImpressoraEntity> findGrupImpressoresByCodiGrup(
		java.lang.String codiGrup) {
	 return null;
	}

	@Description("Returns true if the permission on this object is granted")
	public boolean isAllowed(String permission) { return false; }
}
