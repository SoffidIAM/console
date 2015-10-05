//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.mda.annotation.*;

@Entity (table="SC_PUEPUE", translatedName="EntryPointTreeEntity", translatedPackage="com.soffid.iam.model" )
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

	@Column (name="PPE_IDPEFI", translated="child")
	public es.caib.seycon.ng.model.PuntEntradaEntity fill;

	@Operation(translated="findByChildren")
	@DaoFinder("select arbre from com.soffid.iam.model.EntryPointTreeEntity arbre where arbre.child.id=:childId order by arbre.order")
	public java.util.List<es.caib.seycon.ng.model.ArbrePuntEntradaEntity> findByFill(
		java.lang.Long childId) {
	 return null;
	}
	@Operation(translated="findByParent")
	@DaoFinder("select arbre from com.soffid.iam.model.EntryPointTreeEntity arbre where arbre.parent.id=:parentId order by arbre.order")
	public java.util.List<es.caib.seycon.ng.model.ArbrePuntEntradaEntity> findByPare(
		java.lang.Long parentId) {
	 return null;
	}
}
