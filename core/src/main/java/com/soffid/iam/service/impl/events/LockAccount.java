package com.soffid.iam.service.impl.events;

import java.util.HashMap;
import java.util.Map;

import com.soffid.iam.ServiceLocator;
import com.soffid.iam.api.Account;
import com.soffid.iam.api.AccountStatus;
import com.soffid.iam.api.Issue;
import com.soffid.iam.interp.Evaluator;
import com.soffid.iam.model.IssueEntity;
import com.soffid.iam.model.IssuePolicyActionEntity;
import com.soffid.iam.service.impl.issues.AutomaticActionHandler;

import es.caib.seycon.ng.exception.AccountAlreadyExistsException;
import es.caib.seycon.ng.exception.InternalErrorException;

public class LockAccount implements AutomaticActionHandler {

	@Override
	public void process(Issue event, IssueEntity entity, IssuePolicyActionEntity actionEntity) throws InternalErrorException {
		if ( entity.getAccount() != null && ! entity.getAccount().isDisabled() )
		{
			Account acc = ServiceLocator.instance().getAccountService().load(entity.getAccount().getId());
			acc.setStatus(AccountStatus.LOCKED);
			try {
				ServiceLocator.instance().getAccountService().updateAccount(acc);
			} catch (AccountAlreadyExistsException e) {
				// Cannot happen
			}
		}
	}
}
