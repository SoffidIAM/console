package com.soffid.iam.service;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jbpm.JbpmContext;
import org.jbpm.logging.exe.LoggingInstance;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.ibm.icu.util.BytesTrie.Iterator;
import com.soffid.iam.api.PamSecurityCheck;
import com.soffid.iam.api.RequestedObligation;
import com.soffid.iam.api.RequestedObligationEnum;
import com.soffid.iam.api.RequestedObligationEnumEnum;
import com.soffid.iam.bpm.api.ProcessInstance;
import com.soffid.iam.bpm.model.AuthenticationLog;
import com.soffid.iam.common.security.Obligation;
import com.soffid.iam.common.security.ObserveObligationException;
import com.soffid.iam.model.AccountEntity;
import com.soffid.iam.model.UserAccountEntity;
import com.soffid.iam.utils.ConfigurationCache;
import com.soffid.iam.utils.Security;

import es.caib.bpm.exception.BPMException;
import es.caib.bpm.vo.PredefinedProcessType;
import es.caib.seycon.ng.exception.InternalErrorException;

public class PamSecurityHandlerServiceImpl extends PamSecurityHandlerServiceBase implements ApplicationContextAware {

	private ApplicationContext applicationContext;

	@Override
	protected void handleCheckPermission(AccountEntity account, String action) throws Exception {
		if (Security.isSyncServer())
			return;
		
		PamSecurityCheck psc = handleGetObligations(account, action);
		if ( ! psc.isAllowed())
			throw new SecurityException("Action not authorized");
		else
			checkObligations(account, action, psc.getObligations());
	}

	private void checkObligations(AccountEntity account, String action, List<RequestedObligation> obligations) throws ObserveObligationException {
		List<Obligation> unmeetObligations = new LinkedList<>();
		for (RequestedObligation req: obligations) {
			if (req.getObligation().equals(RequestedObligationEnum.WORKFLOW.getValue())) {
				boolean found = false;
				for (UserAccountEntity userAccount: new LinkedList<UserAccountEntity>(account.getUsers())) {
					if (userAccount.getUntilDate() != null && 
							userAccount.getUntilDate().before(new Date())) {
						getUserAccountEntityDao().remove(userAccount);
						account.getUsers().remove(userAccount);
					}
					else if (userAccount.getUser().getUserName().equals(Security.getSoffidPrincipal().getUserName()))
					{
						if (userAccount.getWorkflowId() == null ||
							Boolean.TRUE.equals(userAccount.getApproved()))
							found = true;
						else
						{
							Long wfId = userAccount.getWorkflowId();
							ProcessInstance process;
							try {
								process = getBpmEngine().getProcess(wfId);
								if (process.getEnd() == null ) {
									req.getAttributes().put("in-progress-process", wfId.toString());
								} else {
									getUserAccountEntityDao().remove(userAccount);
								}
							} catch (InternalErrorException | BPMException e) {
							}
						}
					}
				}
				if (!found) {
					addObligation(req, unmeetObligations, account, action);
				}
			} else {
				if ( !checkObligation(req)) {
					addObligation(req, unmeetObligations, account, action);
				}
			}
		}
		if (! unmeetObligations.isEmpty())
			throw new ObserveObligationException(unmeetObligations);
	}


	public void addObligation(RequestedObligation req, List<Obligation> unmeetObligations, AccountEntity account, String action) {
		Obligation o = new Obligation();
		o.setObligation(req.getObligation());
		o.setAttributes(req.getAttributes());
		String tokenTimeoutString = ConfigurationCache.getProperty("soffid.otp.timeout");
		try {
			o.setTimeout(System.currentTimeMillis() + 1000 * Integer.parseInt(tokenTimeoutString));
		} catch (Exception e) {
			o.setTimeout(System.currentTimeMillis() + 60000);
		}

		o.getAttributes().put("account", account.getName());
		o.getAttributes().put("systemName", account.getSystem().getName());
		o.getAttributes().put("loginName", account.getLoginName());
		o.getAttributes().put("server", account.getServerName());
		o.getAttributes().put("action",  action);
		
		unmeetObligations.add(o);
	}

	private boolean checkObligation(RequestedObligation req) {
		Map<String, String> attributes = Security.getSoffidPrincipal().getObligation(req.getObligation());
		if (attributes == null)
			return false;
		for ( String key: req.getAttributes().keySet()) {
			String value = req.getAttributes().get(key);
			if (value != null && ! value.equals(attributes.get(key)))
				return false;
		}
		return true;
	}

	@Override
	protected PamSecurityCheck handleCheckPermissionImpl(AccountEntity account, String action) throws Exception {
		return null;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Override
	protected PamSecurityCheck handleGetObligations(AccountEntity account, String action) throws Exception {
		PamSecurityCheck psc = new PamSecurityCheck();
		psc.setAllowed(true);
		psc.setObligations(new LinkedList<RequestedObligation>());
		Map<String, PamSecurityHandlerService> beans = applicationContext.getBeansOfType(PamSecurityHandlerService.class);
		for ( PamSecurityHandlerService bean: beans.values()) {
			PamSecurityCheck p = bean.checkPermissionImpl(account, action);
			if (p != null) {
				if (!p.isAllowed()) {
					psc.setAllowed(false);
				}
				if (p.getObligations() != null)
					psc.getObligations().addAll(p.getObligations());
			}
		}
		return psc;
	}

}
