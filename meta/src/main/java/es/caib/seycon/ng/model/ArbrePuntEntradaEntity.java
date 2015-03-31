//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.mda.annotation.*;

@Entity (table="SC_PUEPUE", translatedName="EntryPointEntity", translatedPackage="com.soffid.iam.model" )
@Depends ({es.caib.seycon.ng.comu.ArbrePuntEntrada.class,
	es.caib.seycon.ng.model.AuditoriaEntity.class,
	es.caib.seycon.ng.model.PuntEntradaEntity.class})
public abstract class ArbrePuntEntradaEntity {

	@Column (name="PPE_ID")
	@Identifier
	public java.lang.Long id;

	@Column (name="PPE_ORDRE", translated="order")
	public java.lang.Integer ordre;

	@Column (name="PPE_IDPEPA", translated="parent")
	public es.caib.seycon.ng.model.PuntEntradaEntity pare;

	@Column (name="PPE_IDPEFI", translated="children")
	public es.caib.seycon.ng.model.PuntEntradaEntity fill;

	@Operation(translated="findByChildren")
	@DaoFinder("select arbre from es.caib.seycon.ng.model.ArbrePuntEntradaEntity arbre where arbre.fill.id=:idFill order by arbre.ordre")
	public java.util.List<es.caib.seycon.ng.model.ArbrePuntEntradaEntity> findByFill(
		java.lang.Long idFill) {
	 return null;
	}
	@Operation(translated="findByParent")
	@DaoFinder("select arbre from es.caib.seycon.ng.model.ArbrePuntEntradaEntity arbre where arbre.pare.id=:idPare order by arbre.ordre")
	public java.util.List<es.caib.seycon.ng.model.ArbrePuntEntradaEntity> findByPare(
		java.lang.Long idPare) {
	 return null;
	}
}
