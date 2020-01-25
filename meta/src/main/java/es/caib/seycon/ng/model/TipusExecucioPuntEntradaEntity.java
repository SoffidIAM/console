//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.mda.annotation.*;

@Entity (table="SC_TIPEXE", translatedName="EntryPointExecutionTypeEntity", translatedPackage="com.soffid.iam.model")
@Depends ({es.caib.seycon.ng.comu.TipusExecucioPuntEntrada.class})
public abstract class TipusExecucioPuntEntradaEntity {

	@Column (name="EXE_ID")
	@Identifier
	public java.lang.Long id;

	@Column (name="EXE_CODI", length=10, translated="name")
	public java.lang.String codi;

	@Column (name="EXE_MIME", length=50, translated="mimeType")
	public java.lang.String tipusMime;

	@Column (name="EXE_PLANTI", length=2000, translated="template")
	@Nullable
	public java.lang.String plantilla;

	@Column (name="EXE_JAVCLA", length=150)
	@Nullable
	public java.lang.String javaClass;

	@Operation(translated="findByName")
	@DaoFinder
	public es.caib.seycon.ng.model.TipusExecucioPuntEntradaEntity findByCodi(
		java.lang.String name) {
	 return null;
	}
}
