//
// (C) 2013 Soffid
//
//

package com.soffid.iam.model;

import com.soffid.iam.model.TaskEntity;
import com.soffid.iam.sync.engine.TaskHandler;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.exception.SeyconException;

import java.sql.Timestamp;

/**
 * DAO MailListGroupMemberEntity implementation
 */
public class MailListGroupMemberEntityDaoImpl extends MailListGroupMemberEntityDaoBase
{
	private void createUpdateTask(MailListGroupMemberEntity entity) {
		try {
			getEmailListEntityDao().generateUpdateTasks(entity.getMailList());
		} catch (InternalErrorException e) {
			throw new SeyconException("Error generating task", e);
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
