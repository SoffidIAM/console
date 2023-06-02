package com.soffid.iam.model;

import java.util.LinkedList;

import org.apache.commons.beanutils.BeanUtils;

import com.soffid.iam.api.Issue;
import com.soffid.iam.lang.MessageFactory;
import com.soffid.iam.service.impl.events.IssueTextFormatter;

import es.caib.seycon.ng.comu.Auditoria;

public class IssueEntityDaoImpl extends IssueEntityDaoBase {

	@Override
	public void issueToEntity(Issue source, IssueEntity target, boolean copyIfNull) {
		super.issueToEntity(source, target, copyIfNull);
		target.setSystem(source.getSystem() == null ? null: getSystemEntityDao().findByName(source.getSystem()));
		target.setRoleAccount(source.getRoleAccount() == null ? null: getRoleAccountEntityDao().load(source.getRoleAccount().getId()));
		target.setRule(source.getRule() == null ? null: getPamRuleEntityDao().load(source.getRule().getId()));
		target.setAccount(source.getAccount() == null? null: getAccountEntityDao().load(source.getAccount().getId()));
	}

	@Override
	public void toIssue(IssueEntity source, Issue target) {
		super.toIssue(source, target);
		target.setSystem( source.getSystem() == null ? null: source.getSystem().getName());
		target.setRoleAccount(source.getRoleAccount() == null? null: getRoleAccountEntityDao().toRoleAccount(source.getRoleAccount()));
		target.setRule(source.getRule() == null ? null: getPamRuleEntityDao().toPamRule(source.getRule()));
		target.setAccount(source.getAccount() == null ? null: getAccountEntityDao().toAccount(source.getAccount()));
		target.setUsers(getIssueUserEntityDao().toIssueUserList(source.getUsers()));
		target.setHosts(getIssueHostEntityDao().toIssueHostList(source.getHosts()));
		calculateDescription(source, target);

	}

	private void calculateDescription(IssueEntity source, Issue target) {
		String msg = MessageFactory.getString("com.soffid.iam.model.issues.messages", 
				"issue."+source.getType());
		if (msg != null) {
			String s = new IssueTextFormatter().format(msg, target, source);
			target.setDescription(s);
		}
	}

}
