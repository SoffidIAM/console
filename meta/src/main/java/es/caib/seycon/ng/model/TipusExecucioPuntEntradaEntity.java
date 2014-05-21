//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.mda.annotation.*;

@Entity (table="SC_TIPEXE" )
@Depends ({es.caib.seycon.ng.comu.TipusExecucioPuntEntrada.class})
public abstract class TipusExecucioPuntEntradaEntity {

	@Column (name="EXE_ID")
	@Identifier
	public java.lang.Long id;

	@Column (name="EXE_CODI", length=10)
	public java.lang.String codi;

	@Column (name="EXE_MIME", length=50)
	public java.lang.String tipusMime;

	@Column (name="EXE_PLANTI", length=2000)
	@Nullable
	public java.lang.String plantilla;

	@DaoFinder
	public es.caib.seycon.ng.model.TipusExecucioPuntEntradaEntity findByCodi(
		java.lang.String codi) {
	 return null;
	}
}
