package com.soffid.iam.service.account;

import es.caib.seycon.ng.servei.account.*;

import com.soffid.iam.model.SystemEntity;
import com.soffid.iam.model.UserEntity;

public class AccountNameGeneratorImpl extends com.soffid.iam.service.account.AccountNameGeneratorBase
{

	@Override
    protected String handleGetAccountName(UserEntity user, SystemEntity dispatcher) throws Exception {
		return user.getUserName();
	}

	@Override
	protected boolean handleNeedsAccount(UserEntity user, SystemEntity dispatcher) throws Exception {
		return false;
	}

}
