//
// (C) 2013 Soffid
//
//

package com.soffid.iam.reconcile.model;

import java.util.HashMap;

import com.soffid.iam.model.AccountEntity;
import com.soffid.iam.model.UserAccountEntity;
import com.soffid.iam.reconcile.common.ReconcileAccount;
import com.soffid.iam.reconcile.model.ReconcileAccountEntityDaoBase;

import es.caib.seycon.ng.comu.AccountType;

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
		if ( source.getNewAccount() != null && !source.getNewAccount().booleanValue() &&
				source.getUserCode() == null && source.getPrimaryGroup() == null)
		{
			AccountEntity acc = getAccountEntityDao().findByNameAndSystem(source.getAccountName(), source.getDispatcher());
			if (acc != null && acc.getType().equals(AccountType.USER))
			{
				for (UserAccountEntity uac: acc.getUsers())
				{
					target.setUserCode(uac.getUser().getUserName());
					target.setUserFullName(uac.getUser().getFullName());
					target.setPrimaryGroup(uac.getUser().getPrimaryGroup().getName());
				}
			}
		}
	}
}
