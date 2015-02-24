//
// (C) 2013 Soffid
//
//

package com.soffid.iam.model;

import java.sql.Timestamp;

import es.caib.seycon.ng.model.TasqueEntity;
import es.caib.seycon.ng.sync.engine.TaskHandler;

/**
 * DAO MailListGroupMemberEntity implementation
 */
public class MailListGroupMemberEntityDaoImpl extends MailListGroupMemberEntityDaoBase
{
	private void createUpdateTask(MailListGroupMemberEntity entity) {
		if (entity.getMailList() != null &&
				entity.getMailList().getDomini() != null &&
				entity.getMailList().getNom() != null)
		{
	        TasqueEntity tasque = getTasqueEntityDao().newTasqueEntity();
	        tasque.setData(new Timestamp(System.currentTimeMillis()));
	        tasque.setTransa(TaskHandler.UPDATE_LIST_ALIAS);
	        tasque.setAlies(entity.getMailList().getNom());
            tasque.setDomcor(entity.getMailList().getDomini().getCodi());
	        getTasqueEntityDao().create(tasque);
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
