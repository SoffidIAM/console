// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.caib.seycon.ng.model;

import es.caib.seycon.ng.comu.TipusExecucioPuntEntrada;
import es.caib.seycon.ng.exception.SeyconException;

/**
 * @see es.caib.seycon.ng.model.TipusExecucioPuntEntradaEntity
 */
public class TipusExecucioPuntEntradaEntityDaoImpl
    extends es.caib.seycon.ng.model.TipusExecucioPuntEntradaEntityDaoBase
{

	public TipusExecucioPuntEntradaEntity tipusExecucioPuntEntradaToEntity(
			TipusExecucioPuntEntrada tipusExecucioPuntEntrada) {
		TipusExecucioPuntEntradaEntity tipusExe = null;
		if (tipusExecucioPuntEntrada.getCodi()!=null) {
			tipusExe = super.findByCodi(tipusExecucioPuntEntrada.getCodi());
			super.tipusExecucioPuntEntradaToEntity(tipusExecucioPuntEntrada, tipusExe, true);
			return tipusExe;
		} else 
			throw new SeyconException(Messages.getString("TipusExecucioPuntEntradaEntityDaoImpl.invalidEntry")); //$NON-NLS-1$
	}

	public void toTipusExecucioPuntEntrada(
			TipusExecucioPuntEntradaEntity source,
			TipusExecucioPuntEntrada target) {
		super.toTipusExecucioPuntEntrada(source, target);
	}
}