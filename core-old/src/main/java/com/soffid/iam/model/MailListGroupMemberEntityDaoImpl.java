//
// (C) 2013 Soffid
//
//

package com.soffid.iam.model;

import com.soffid.iam.model.TaskEntity;
import es.caib.seycon.ng.sync.engine.TaskHandler;
import java.sql.Timestamp;

/**
 * DAO MailListGroupMemberEntity implementation
 */
public class MailListGroupMemberEntityDaoImpl extends MailListGroupMemberEntityDaoBase
{
	private void createUpdateTask(MailListGroupMemberEntity entity) {
		if (entity.getMailList() != null && entity.getMailList().getDomain() != null && entity.getMailList().getName() != null)
		{
	        TaskEntity tasque = getTaskEntityDao().newTaskEntity();
	        tasque.setDate(new Timestamp(System.currentTimeMillis()));
	        tasque.setTransaction(TaskHandler.UPDATE_LIST_ALIAS);
	        tasque.setAlias(entity.getMailList().getName());
            tasque.setMailDomain(entity.getMailList().getDomain().getName());
	        getTaskEntityDao().create(tasque);
		}
	}

	@Override
	public void create(MailListGroupMemberEntity entity) {
		super.create(entity);
		createUpdateTask(entity);
	}

	@Override
	public void remove(MailListGroupMemberEntity entity) {
		createUpdateTask(entity);
		super.remove(entity);
	}
}
