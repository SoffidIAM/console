// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package com.soffid.iam.model;

import com.soffid.iam.api.AccessTreeExecutionType;
import com.soffid.iam.model.EntryPointExecutionTypeEntity;
import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.ng.model.*;

/**
 * @see es.caib.seycon.ng.model.TipusExecucioPuntEntradaEntity
 */
public class EntryPointExecutionTypeEntityDaoImpl
    extends com.soffid.iam.model.EntryPointExecutionTypeEntityDaoBase
{

	public EntryPointExecutionTypeEntity accessTreeExecutionTypeToEntity(AccessTreeExecutionType tipusExecucioPuntEntrada) {
		EntryPointExecutionTypeEntity tipusExe = null;
		if (tipusExecucioPuntEntrada.getCode() != null) {
			tipusExe = super.findByName(tipusExecucioPuntEntrada.getCode());
			super.accessTreeExecutionTypeToEntity(tipusExecucioPuntEntrada, tipusExe, true);
			return tipusExe;
		} else 
			throw new SeyconException(Messages.getString("EntryPointExecutionTypeEntityDaoImpl.invalidEntry")); //$NON-NLS-1$
	}

	public void toAccessTreeExecutionType(EntryPointExecutionTypeEntity source, AccessTreeExecutionType target) {
		super.toAccessTreeExecutionType(source, target);
	}
}