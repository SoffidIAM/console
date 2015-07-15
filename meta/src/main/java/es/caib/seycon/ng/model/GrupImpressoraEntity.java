//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;

import com.soffid.mda.annotation.*;

@Entity(table = "SC_GRUIMP", translatedName = "PrinterGroupEntity", translatedPackage = "com.soffid.iam.model")
@Depends({ es.caib.seycon.ng.comu.GrupImpressora.class,
		es.caib.seycon.ng.model.ImpressoraEntity.class,
		es.caib.seycon.ng.model.GrupEntity.class,
		es.caib.seycon.ng.model.AuditoriaEntity.class,
		es.caib.seycon.ng.model.TasqueEntity.class })
public abstract class GrupImpressoraEntity {

	@Column(name = "GIM_ORDRE", translated = "order")
	@Nullable
	public java.lang.Long ordre;

	@Column(name = "GIM_IDGRU", translated = "group")
	public es.caib.seycon.ng.model.GrupEntity grup;

	@Column(name = "GIM_IDIMP", translated = "printer")
	public es.caib.seycon.ng.model.ImpressoraEntity impressora;

	@Column(name = "GIM_ID")
	@Identifier
	public java.lang.Long id;

	@Operation(translated = "findByGroupAndPrinter")
	@DaoFinder("select grupImpressora "
			+ "from com.soffid.iam.model.PrinterGroupEntity grupImpressora "
			+ "where grupImpressora.group.name = :group and grupImpressora.printer.name = :printer")
	public es.caib.seycon.ng.model.GrupImpressoraEntity findGrupImpressoraByCodiGrupAndCodiImpressora(
			java.lang.String group, java.lang.String printer) {
		return null;
	}

	@Operation(translated = "findByPrinter")
	@DaoFinder("select grupImpressora from com.soffid.iam.model.PrinterGroupEntity grupImpressora "
			+ "where grupImpressora.printer.name = :printer")
	public java.util.List<es.caib.seycon.ng.model.GrupImpressoraEntity> findGrupImpressoresByCodiImpressora(
			java.lang.String printer) {
		return null;
	}

	@Operation(translated = "findByGroup")
	@DaoFinder("select grupImpressora from com.soffid.iam.model.PrinterGroupEntity grupImpressora "
			+ "where grupImpressora.group.name = :group")
	public java.util.List<es.caib.seycon.ng.model.GrupImpressoraEntity> findGrupImpressoresByCodiGrup(
			java.lang.String group) {
		return null;
	}

	@Description("Returns true if the permission on this object is granted")
	public boolean isAllowed(String permission) { return false; }
}
