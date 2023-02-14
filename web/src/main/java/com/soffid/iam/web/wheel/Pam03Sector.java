package com.soffid.iam.web.wheel;

import java.util.Date;
import java.util.List;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.UiException;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.JumpServerGroup;
import com.soffid.iam.api.Network;
import com.soffid.iam.api.PagedResult;
import com.soffid.iam.api.PamAction;
import com.soffid.iam.api.PamPolicy;
import com.soffid.iam.api.PamRule;
import com.soffid.iam.api.PamRuleType;
import com.soffid.iam.api.VaultFolder;
import com.soffid.iam.model.PamActionType;
import com.soffid.iam.utils.Security;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.zkiblaf.Application;
import es.caib.zkib.zkiblaf.Missatgebox;

public class Pam03Sector extends Sector {

	public Pam03Sector(String tag) {
		super(tag);
	}

	
	@Override
	public boolean isDone() {
		try {
			List<JumpServerGroup> servers = EJBLocator.getPamSessionService().findJumpServerGroups();
			List<PamPolicy> list = EJBLocator.getPamPolicyService().findPolicyByJsonQuery(null, null,null,null).getResources();
			return !list.isEmpty() && ! servers.isEmpty();
		}
		catch (Exception e) {
			return false;
		}
	}



	@Override
	protected void activate() {
		List<JumpServerGroup> servers;
		try {
			servers = EJBLocator.getPamSessionService().findJumpServerGroups();
			if (servers.isEmpty())
				Missatgebox.avis(Labels.getLabel("wizard-pampolicy.noServer"));
			else {
				createRule("sudo", "sudo");
				createRule("passwd", "passwd");
				createRule("Massive delete", "rm.*-.*r");
				createRule("Drop table", "[dD][rR][oO][pP] [tT][aA][bB][lL][eE]");
				createPolicy("default", "Default Policy");
				Missatgebox.avis(Labels.getLabel("wizard-pampolicy.starting"), (e) -> {
					Application.jumpTo("/vault/pam_policies.zul");
				});			
			}
		} catch (Exception e1) {
			throw new UiException(e1);
		}
	}


	private void createPolicy(String name, String description) throws InternalErrorException, NamingException, CreateException {
		for (PamPolicy policy: EJBLocator.getPamPolicyService().findPolicyByJsonQuery(null, "name eq \""+name+"\"", null, null).getResources()) {
			return;
		}
		PamPolicy policy = new PamPolicy();
		policy.setName(name);
		policy.setDescription(description);
		policy = EJBLocator.getPamPolicyService().createPolicy(policy);
		for (PamAction action: EJBLocator.getPamPolicyService().findPolicyActions(policy)) {
			action.getActions().add(PamActionType.NOTIFY);
			EJBLocator.getPamPolicyService().updateAction(action);
		}
	}


	private void createRule(String name, String expression) throws InternalErrorException, NamingException, CreateException {
		if (EJBLocator.getPamPolicyService().findRuleByJsonQuery(null, "name eq \""+name+"\"", null, null).getResources().isEmpty()) {
			PamRule rule = new PamRule();
			rule.setAuthor(Security.getCurrentUser());
			rule.setContent(expression);
			rule.setDate(new Date());
			rule.setName(name);
			rule.setType(PamRuleType.KEYBOARD);
			rule.setDescription(name);
			EJBLocator.getPamPolicyService().createRule(rule);
		}
	}
}
