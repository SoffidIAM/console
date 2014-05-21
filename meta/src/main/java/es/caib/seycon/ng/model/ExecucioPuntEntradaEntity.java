//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.mda.annotation.*;

@Entity (table="SC_EXEPUE" )
@Depends ({es.caib.seycon.ng.comu.ExecucioPuntEntrada.class,
	es.caib.seycon.ng.model.TipusExecucioPuntEntradaEntity.class,
	es.caib.seycon.ng.model.PuntEntradaEntity.class})
public abstract class ExecucioPuntEntradaEntity {

	@Column (name="EPE_ID")
	@Nullable
	@Identifier
	public java.lang.Long id;

	@Column (name="EPE_AMBIT", length=1)
	public java.lang.String ambit;

	@Column (name="EPE_CONTIN", length=4000)
	@Nullable
	public java.lang.String contingut;

	@Column (name="EPE_IDPUE")
	public es.caib.seycon.ng.model.PuntEntradaEntity puntEntrada;

	@Column (name="EPE_CODEXE", length=10)
	@Nullable
	public java.lang.String codiExecucio;

	@DaoFinder
	public java.util.List<es.caib.seycon.ng.model.ExecucioPuntEntradaEntity> findByPuntEntrada(
		es.caib.seycon.ng.model.PuntEntradaEntity puntEntrada) {
	 return null;
	}
}
