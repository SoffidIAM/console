package es.caib.seycon.ng.servei.account;

import com.soffid.iam.model.SystemEntity;
import com.soffid.iam.model.UserEntity;

public class AccountNameGeneratorImpl extends AccountNameGeneratorBase
{

	@Override
    protected String handleGetAccountName(UserEntity user, SystemEntity dispatcher) throws Exception {
		return user.getUserName();
	}

}
