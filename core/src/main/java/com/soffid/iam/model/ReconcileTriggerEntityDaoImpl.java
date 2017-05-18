//
// (C) 2013 Soffid
//
//

package com.soffid.iam.model;

import com.soffid.iam.api.ReconcileTrigger;

/**
 * DAO ReconcileTriggerEntity implementation
 */
public class ReconcileTriggerEntityDaoImpl extends ReconcileTriggerEntityDaoBase
{

	@Override
	public void toReconcileTrigger(ReconcileTriggerEntity source,
			ReconcileTrigger target) {
		super.toReconcileTrigger(source, target);
		target.setSystem(source.getSystem().getName());
	}

	@Override
	public void reconcileTriggerToEntity(ReconcileTrigger source,
			ReconcileTriggerEntity target, boolean copyIfNull) {
		super.reconcileTriggerToEntity(source, target, copyIfNull);
		target.setSystem( getSystemEntityDao().findByName(source.getSystem()));
	}
}
