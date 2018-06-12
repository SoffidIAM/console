package com.soffid.iam.service.account;

import es.caib.seycon.ng.servei.account.*;

import com.soffid.iam.model.SystemEntity;
import com.soffid.iam.model.UserDomainEntity;
import com.soffid.iam.model.UserEntity;

public class AccountNameGeneratorImpl extends com.soffid.iam.service.account.AccountNameGeneratorBase
{

	@Override
	protected boolean handleNeedsAccount(UserEntity user, SystemEntity dispatcher) throws Exception {
		return false;
	}

	@Override
	protected String handleGetAccountName(UserEntity user, SystemEntity dispatcher, UserDomainEntity userDomain)
			throws Exception {
		// TODO Auto-generated method stub
		return user.getUserName();
	}

}
