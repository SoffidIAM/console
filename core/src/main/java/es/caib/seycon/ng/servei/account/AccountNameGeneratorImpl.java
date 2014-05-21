package es.caib.seycon.ng.servei.account;

import es.caib.seycon.ng.model.DispatcherEntity;
import es.caib.seycon.ng.model.UsuariEntity;

public class AccountNameGeneratorImpl extends AccountNameGeneratorBase
{

	@Override
	protected String handleGetAccountName(UsuariEntity user,
			DispatcherEntity dispatcher) throws Exception
	{
		return user.getCodi();
	}

}
