package com.soffid.iam.model;

import com.soffid.iam.api.UserAccount;
import com.soffid.iam.model.TaskEntity;
import com.soffid.iam.model.UserEntity;
import com.soffid.iam.sync.engine.TaskHandler;

import es.caib.seycon.ng.comu.AccountType;
import es.caib.seycon.ng.model.*;
import es.caib.seycon.ng.utils.Security;

import java.sql.Timestamp;
import java.util.GregorianCalendar;

public class UserAccountEntityDaoImpl extends com.soffid.iam.model.UserAccountEntityDaoBase
{

	public com.soffid.iam.model.UserAccountEntity userAccountToEntity(UserAccount instance) {
		com.soffid.iam.model.UserAccountEntity entity = findByAccountSystemAndName(instance.getName(), instance.getSystem(), instance.getUser());
		if (entity == null)
			entity = newUserAccountEntity();
		
		userAccountToEntity(instance, entity, true);
		return entity;
	}

	@Override
    public void toUserAccount(com.soffid.iam.model.UserAccountEntity source, UserAccount target) {
		getAccountEntityDao().toAccount(source.getAccount(), target);
		target.setUser(source.getUser().getUserName());
	}

	

	@Override
    protected void handlePropagateChanges(com.soffid.iam.model.UserAccountEntity account) throws Exception {
    	if (account.getAccount().getType().equals(AccountType.USER))
    	{
    		UserEntity usuariEntity = account.getUser();
            usuariEntity.setLastUserModification(Security.getCurrentUser());
            usuariEntity.setLastModificationDate(GregorianCalendar.getInstance().getTime());
            getUserEntityDao().update(usuariEntity);

    		TaskEntity tasque = getTaskEntityDao().newTaskEntity();
    		tasque.setDate(new Timestamp(System.currentTimeMillis()));
			tasque.setTransaction(TaskHandler.UPDATE_USER);
			tasque.setUser(account.getUser().getUserName());
			getTaskEntityDao().create(tasque);
    	} else if (account.getAccount() != null) {
    		getAccountEntityDao().propagateChanges(account.getAccount());
    	}
    }
}
