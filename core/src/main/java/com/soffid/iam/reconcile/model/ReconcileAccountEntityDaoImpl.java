//
// (C) 2013 Soffid
//
//

package com.soffid.iam.reconcile.model;

import java.util.HashMap;

import com.soffid.iam.reconcile.common.ReconcileAccount;
import com.soffid.iam.reconcile.model.ReconcileAccountEntityDaoBase;

/**
 * DAO ReconcileAccountEntity implementation
 */
public class ReconcileAccountEntityDaoImpl extends ReconcileAccountEntityDaoBase
{

	@Override
	public void toReconcileAccount(ReconcileAccountEntity source,
			ReconcileAccount target) {
		super.toReconcileAccount(source, target);
		HashMap<String, Object> m = new HashMap<String, Object>();
		target.setAttributes(m);
		for ( ReconcileAccountAttributesEntity att: source.getAttributes())
		{
			if (att.getDateValue() != null)
				m.put(att.getAttribute(), att.getDateValue());
			if (att.getValue() != null)
				m.put(att.getAttribute(), att.getValue());
		}
	}
}
