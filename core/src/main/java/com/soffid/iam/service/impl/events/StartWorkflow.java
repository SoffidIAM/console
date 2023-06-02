package com.soffid.iam.service.impl.events;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.soffid.iam.ServiceLocator;
import com.soffid.iam.api.Issue;
import com.soffid.iam.api.IssueStatus;
import com.soffid.iam.bpm.api.ProcessDefinition;
import com.soffid.iam.bpm.api.ProcessInstance;
import com.soffid.iam.bpm.service.BpmEngine;
import com.soffid.iam.model.IssueEntity;
import com.soffid.iam.model.IssueHostEntity;
import com.soffid.iam.model.IssuePolicyActionEntity;
import com.soffid.iam.model.IssueUserEntity;
import com.soffid.iam.service.impl.issues.AutomaticActionHandler;

import es.caib.bpm.exception.BPMException;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.exception.SoffidStackTrace;

public class StartWorkflow implements AutomaticActionHandler {

	@Override
	public void process(Issue event, IssueEntity entity, IssuePolicyActionEntity actionEntity) throws InternalErrorException {
		BpmEngine svc = ServiceLocator.instance().getBpmEngine();
		final List<ProcessDefinition> procs = svc.findProcessDefinitions(actionEntity.getProcessDefinition(), true);
		if (procs.isEmpty())
			throw new InternalErrorException("Cannot find process definition "+actionEntity.getProcessDefinition());
		for (ProcessDefinition def: procs) {
			try {
				ProcessInstance proc = svc.newProcess(def);
				final Map<String, Object> atts = proc.getAttributes();
				if (event.getAccount() != null) {
					atts.put("account", event.getAccount().getName()+"@"+event.getAccount().getSystem());
				}
				atts.put("country", event.getCountry());
				atts.put("description", event.getDescription());
				if (event.getException() != null)
					atts.put("exception", new StringBuffer(event.getException()));
				atts.put("failedLoginPct", event.getFailedLoginPct());
				List<String> hosts = new LinkedList<>();
				for (IssueHostEntity ihe: entity.getHosts()) {
					if (ihe.getHost() != null)
						hosts.add(ihe.getHost().getName());
					else if (ihe.getHostName() != null)
						hosts.add(ihe.getHostName());
				}
				atts.put("hosts", hosts);
				List<String> users = new LinkedList<>();
				for (IssueUserEntity ihe: entity.getUsers()) {
					if (ihe.getUser() != null)
						hosts.add(ihe.getUser().getUserName());
					else if (ihe.getUserName() != null)
						hosts.add(ihe.getUserName());
				}
				atts.put("users", users);
				atts.put("humanConfidence", entity.getHumanConfidence());
				atts.put("id", entity.getId());
				atts.put("otpDevice", entity.getOtpDevice());
				atts.put("performedActions", entity.getPerformedActions());
				atts.put("risk", entity.getRisk());
				atts.put("roleAccount", event.getRoleAccount());
				atts.put("rule", event.getRule() == null ? null: event.getRule().getName());
				atts.put("status", event.getStatus());
				atts.put("system", entity.getSystem() == null ? null: entity.getSystem().getName());
				atts.put("type", entity.getType());
				svc.update(proc);
				svc.signal(proc);
			} catch (BPMException e) {
				throw new InternalErrorException("Error starting process "+actionEntity.getProcessDefinition(), e);
			}
		}
	}
	
	

}
