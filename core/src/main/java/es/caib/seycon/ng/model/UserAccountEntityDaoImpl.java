package es.caib.seycon.ng.model;

import java.sql.Timestamp;
import java.util.GregorianCalendar;

import es.caib.seycon.ng.comu.AccountType;
import es.caib.seycon.ng.comu.UserAccount;
import es.caib.seycon.ng.sync.engine.TaskHandler;
import es.caib.seycon.ng.utils.Security;

public class UserAccountEntityDaoImpl extends UserAccountEntityDaoBase
{

	public UserAccountEntity userAccountToEntity(UserAccount instance)
	{
		UserAccountEntity entity = findByAccountDispatcherAndName(instance.getName(), instance.getDispatcher(), instance.getUser());
		if (entity == null)
			entity = newUserAccountEntity();
		
		userAccountToEntity(instance, entity, true);
		return entity;
	}

	@Override
	public void toUserAccount(UserAccountEntity source, UserAccount target)
	{
		getAccountEntityDao().toAccount(source.getAccount(), target);
		target.setUser(source.getUser().getCodi());
	}

	

	@Override
	protected void handlePropagateChanges(UserAccountEntity account) throws Exception 
	{
    	if (account.getAccount().getType().equals(AccountType.USER))
    	{
    		UsuariEntity usuariEntity = account.getUser();
            usuariEntity.setUsuariDarreraModificacio(Security.getCurrentUser());
            usuariEntity.setDataDarreraModificacio(GregorianCalendar.getInstance()
                    .getTime());
            getUsuariEntityDao().update(usuariEntity);

    		TasqueEntity tasque = getTasqueEntityDao().newTasqueEntity();
    		tasque.setData(new Timestamp(System.currentTimeMillis()));
			tasque.setTransa(TaskHandler.UPDATE_USER);
			tasque.setUsuari(account.getUser().getCodi());
			getTasqueEntityDao().create(tasque);
    	} else if (account.getAccount() != null) {
    		getAccountEntityDao().propagateChanges(account.getAccount());
    	}
    }
}
