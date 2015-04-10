// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package com.soffid.iam.model;

import es.caib.seycon.ng.model.*;

import com.soffid.iam.model.EntryPointExecutionTypeEntity;
import es.caib.seycon.ng.comu.TipusExecucioPuntEntrada;
import es.caib.seycon.ng.exception.SeyconException;

/**
 * @see es.caib.seycon.ng.model.TipusExecucioPuntEntradaEntity
 */
public class EntryPointExecutionTypeEntityDaoImpl
    extends com.soffid.iam.model.EntryPointExecutionTypeEntityDaoBase
{

	public EntryPointExecutionTypeEntity tipusExecucioPuntEntradaToEntity(TipusExecucioPuntEntrada tipusExecucioPuntEntrada) {
		EntryPointExecutionTypeEntity tipusExe = null;
		if (tipusExecucioPuntEntrada.getCodi()!=null) {
			tipusExe = super.findByName(tipusExecucioPuntEntrada.getCodi());
			super.tipusExecucioPuntEntradaToEntity(tipusExecucioPuntEntrada, tipusExe, true);
			return tipusExe;
		} else 
			throw new SeyconException(Messages.getString("EntryPointExecutionTypeEntityDaoImpl.invalidEntry")); //$NON-NLS-1$
	}

	public void toTipusExecucioPuntEntrada(EntryPointExecutionTypeEntity source, TipusExecucioPuntEntrada target) {
		super.toTipusExecucioPuntEntrada(source, target);
	}
}