//
// (C) 2013 Soffid
//
//

package com.soffid.iam.model;

import java.sql.Timestamp;

import com.soffid.iam.api.MailListRoleMember;

import es.caib.seycon.ng.comu.TipusDomini;
import es.caib.seycon.ng.model.TasqueEntity;
import es.caib.seycon.ng.sync.engine.TaskHandler;

/**
 * DAO MailListRoleMemberEntity implementation
 */
public class MailListRoleMemberEntityDaoImpl extends MailListRoleMemberEntityDaoBase
{

	@Override
	public void toMailListRoleMember(MailListRoleMemberEntity source,
			MailListRoleMember target) {
		target.setDispatcherName(source.getRole().getBaseDeDades().getCodi());
		target.setRoleDescription(source.getRole().getDescripcio());
		target.setRoleName(source.getRole().getNom());
		target.setScope("");
		if (source.getDomainValueScope() != null)
			target.setScope(source.getDomainValueScope().getValor());
			
		if (source.getGroupScope() != null)
			target.setScope(source.getGroupScope().getCodi());
		
		if (source.getInformationSystemScope() != null)
			target.setScope(source.getInformationSystemScope().getCodi());

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
}
