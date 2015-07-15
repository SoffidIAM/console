//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;

import com.soffid.mda.annotation.*;

@Entity(table = "SC_USUIMP", translatedName = "UserPrinterEntity", translatedPackage = "com.soffid.iam.model")
@Depends({ es.caib.seycon.ng.comu.UsuariImpressora.class,
		es.caib.seycon.ng.model.ImpressoraEntity.class,
		es.caib.seycon.ng.model.UsuariEntity.class,
		es.caib.seycon.ng.model.AuditoriaEntity.class,
		es.caib.seycon.ng.model.TasqueEntity.class })
public abstract class UsuariImpressoraEntity {

	@Column(name = "UIM_ORDRE", translated = "order")
	@Nullable
	public java.lang.Long uimOrdre;

	@Column(name = "UIM_IDIMP", translated = "printer")
	public es.caib.seycon.ng.model.ImpressoraEntity impressora;

	@Column(name = "UIM_IDUSU", translated = "user")
	public es.caib.seycon.ng.model.UsuariEntity usuari;

	@Column(name = "UIM_ID")
	@Identifier
	public java.lang.Long id;

	@Operation(translated = "findUserByUserAndPrinter")
	@DaoFinder("from com.soffid.iam.model.UserPrinterEntity as ui "
			+ "where ui.user.userName=:userName and ui.printer.name=:printer "
			+ "order by ui.user.userName, ui.printer.name")
	public es.caib.seycon.ng.model.UsuariImpressoraEntity findUsuariImpressoraByCodiUsuariAndCodiImpressora(
			java.lang.String userName, java.lang.String printer) {
		return null;
	}

	@Operation(translated = "findByPrinter")
	@DaoFinder("from com.soffid.iam.model.UserPrinterEntity as ui "
			+ "where ui.printer.name=:printer "
			+ "order by ui.user.userName, ui.printer.name")
	public java.util.List<es.caib.seycon.ng.model.UsuariImpressoraEntity> findUsuariImpressoresByCodiImpressora(
			java.lang.String printer) {
		return null;
	}

	@Operation(translated = "findByUser")
	@DaoFinder("from com.soffid.iam.model.UserPrinterEntity as ui "
			+ "where ui.user.userName=:userName  "
			+ "order by ui.user.userName, ui.printer.name")
	public java.util.List<es.caib.seycon.ng.model.UsuariImpressoraEntity> findUsuariImpressoresByCodiUsuari(
			java.lang.String userName) {
		return null;
	}

	@Description("Returns true if the permission on this object is granted")
	public boolean isAllowed(String permission) { return false; }
}
