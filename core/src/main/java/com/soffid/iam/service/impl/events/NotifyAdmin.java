package com.soffid.iam.service.impl.events;

import org.apache.commons.beanutils.BeanUtils;

import com.soffid.iam.ServiceLocator;
import com.soffid.iam.api.Issue;
import com.soffid.iam.model.IssueEntity;
import com.soffid.iam.model.IssueHostEntity;
import com.soffid.iam.model.IssuePolicyActionEntity;
import com.soffid.iam.model.IssueUserEntity;
import com.soffid.iam.service.impl.issues.AutomaticActionHandler;

import es.caib.seycon.ng.exception.InternalErrorException;

public class NotifyAdmin implements AutomaticActionHandler {

	@Override
	public void process(Issue event, IssueEntity entity, IssuePolicyActionEntity actionEntity) throws InternalErrorException {
		ServiceLocator.instance().getMailService().sendHtmlMailToActors(
				new String[] {entity.getActor()},
				new IssueTextFormatter().format (actionEntity.getSubject(), event, entity),
				new IssueTextFormatter().format (actionEntity.getBody(), event, entity));
	}
	
	

}
