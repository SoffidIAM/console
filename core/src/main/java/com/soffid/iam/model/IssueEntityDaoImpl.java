package com.soffid.iam.model;

import java.util.LinkedList;

import org.apache.commons.beanutils.BeanUtils;

import com.soffid.iam.api.Issue;
import com.soffid.iam.lang.MessageFactory;
import com.soffid.iam.service.impl.events.IssueTextFormatter;
import com.soffid.iam.utils.Security;

import es.caib.seycon.ng.comu.Auditoria;

public class IssueEntityDaoImpl extends IssueEntityDaoBase {

	@Override
	public void issueToEntity(Issue source, IssueEntity target, boolean copyIfNull) {
		super.issueToEntity(source, target, copyIfNull);
		target.setSystem(source.getSystem() == null ? null: getSystemEntityDao().findByName(source.getSystem()));
		target.setRoleAccount(source.getRoleAccount() == null ? null: getRoleAccountEntityDao().load(source.getRoleAccount().getId()));
		target.setRule(source.getRule() == null ? null: getPamRuleEntityDao().load(source.getRule().getId()));
		target.setAccount(findAccount(source.getAccount()));
		SystemEntity mainDispatcher = getSystemEntityDao().findSoffidSystem();
		target.setRequester(getAccountEntityDao().findByNameAndSystem(Security.getCurrentAccount(),
				mainDispatcher.getName()));
	}

	private AccountEntity findAccount(String account) {
		if (account == null || account.trim().isEmpty())
			return null;
		int i = account.lastIndexOf("@");
		if (i < 0) return null;
		return getAccountEntityDao().findByNameAndSystem(account.substring(0,i), account.substring(i+1));
	}

	@Override
	public void toIssue(IssueEntity source, Issue target) {
		super.toIssue(source, target);
		target.setSystem( source.getSystem() == null ? null: source.getSystem().getName());
		target.setRoleAccount(source.getRoleAccount() == null? null: getRoleAccountEntityDao().toRoleAccount(source.getRoleAccount()));
		target.setRule(source.getRule() == null ? null: getPamRuleEntityDao().toPamRule(source.getRule()));
		target.setAccount(source.getAccount() == null ? null: 
			source.getAccount().getName()+"@"+source.getAccount().getSystem().getName());
		target.setUsers(getIssueUserEntityDao().toIssueUserList(source.getUsers()));
		target.setHosts(getIssueHostEntityDao().toIssueHostList(source.getHosts()));
		target.setRequester(source.getRequester() == null ? null:  source.getRequester().getName()+"@"+source.getRequester().getSystem().getName());
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
