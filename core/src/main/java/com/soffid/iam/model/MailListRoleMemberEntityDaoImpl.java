//
// (C) 2013 Soffid
//
//

package com.soffid.iam.model;

import com.soffid.iam.api.MailListRoleMember;
import com.soffid.iam.model.TaskEntity;
import com.soffid.iam.sync.engine.TaskHandler;

import es.caib.seycon.ng.comu.TipusDomini;

import java.sql.Timestamp;

/**
 * DAO MailListRoleMemberEntity implementation
 */
public class MailListRoleMemberEntityDaoImpl extends MailListRoleMemberEntityDaoBase
{

	@Override
	public void toMailListRoleMember(MailListRoleMemberEntity source,
			MailListRoleMember target) {
		target.setDispatcherName(source.getRole().getSystem().getName());
		target.setRoleDescription(source.getRole().getDescription());
		target.setRoleName(source.getRole().getName());
		target.setScope("");
		if (source.getDomainValueScope() != null)
			target.setScope(source.getDomainValueScope().getValue());
			
		if (source.getGroupScope() != null)
			target.setScope(source.getGroupScope().getName());
		
		if (source.getInformationSystemScope() != null)
			target.setScope(source.getInformationSystemScope().getName());

	}

	public MailListRoleMemberEntity mailListRoleMemberToEntity(
			MailListRoleMember instance) {
		return null;
	}

	@Override
	public void create(MailListRoleMemberEntity entity) {
		super.create(entity);
		createUpdateTask(entity);
	}

	@Override
	public void remove(MailListRoleMemberEntity entity) {
		createUpdateTask(entity);
		super.remove(entity);
	}

	private void createUpdateTask(MailListRoleMemberEntity entity) {
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
}
