package com.soffid.iam.web.user;

import java.util.Map;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.GroupUser;
import com.soffid.iam.api.User;
import com.soffid.iam.api.UserAccount;
import com.soffid.iam.service.ejb.AccountService;
import com.soffid.iam.service.ejb.GroupService;
import com.soffid.iam.service.ejb.UserService;
import com.soffid.iam.web.component.MergeAction;

import es.caib.seycon.ng.comu.AccountType;
import es.caib.zkib.component.DataTable;
import es.caib.zkib.datamodel.DataModelNode;
import es.caib.zkib.datamodel.DataNode;
import es.caib.zkib.datasource.DataSource;


public class UserMergeAction extends MergeAction {
	DataTable table;
	
	public UserMergeAction() {
		super(User.class.getName());
	}

	@Override
	public void apply(Map<String, String> actions) throws Exception {
		String targetAction = actions.get("userName");
		apply(actions,  "1".equals(targetAction) ? positions[0]: positions[1]);
		String xpath1 = dataTable.getItemXPath(positions[0]);
		String xpath2 = dataTable.getItemXPath(positions[1]);
		DataSource ds = dataTable.getDataSource();
		DataNode srcNode;
		DataNode targetNode;
		if ("1".equals(targetAction)) {
			srcNode = (DataNode) ds.getJXPathContext().getValue(xpath2);
			targetNode = (DataNode) ds.getJXPathContext().getValue(xpath1);
		} else {
			srcNode = (DataNode) ds.getJXPathContext().getValue(xpath1);
			targetNode = (DataNode) ds.getJXPathContext().getValue(xpath2);			
		}
		User src = (User) srcNode.getInstance();
		User target = (User) targetNode.getInstance();
		
		// Move accounts
		AccountService accountService = EJBLocator.getAccountService();
		for ( UserAccount account: accountService.getUserAccounts(src)) {
			account.getOwnerUsers().clear();
			account.setType(AccountType.IGNORED);
			accountService.updateAccount2(account);
			account.setType(AccountType.USER);
			account.getOwnerUsers().clear();
			account.getOwnerUsers().add(target.getUserName());
			accountService.updateAccount2(account);
		}
		
		// Move group memberships
		GroupService groupService = EJBLocator.getGroupService();
		for ( GroupUser membership: groupService.findUsersGroupByUserName(src.getUserName()) ) {
			membership.setUser(target.getUserName());
			groupService.update(membership);
		}
		// Remove src
		UserService userService = EJBLocator.getUserService();
		userService.delete(src);
		userService.update(target);
		srcNode.delete();
		srcNode.setTransient(true);
		targetNode.setDirty(false);
	}

	
	public DataTable getTable() {
		return table;
	}

	
	public void setTable(DataTable table) {
		this.table = table;
	}

}
