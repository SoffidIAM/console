package com.soffid.iam.web.user;

import java.util.Date;
import java.util.Map;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.EventUserAction;
import com.soffid.iam.api.IssueStatus;
import com.soffid.iam.api.IssueUser;
import com.soffid.iam.api.User;
import com.soffid.iam.service.ejb.UserService;
import com.soffid.iam.web.component.MergeAction;

import es.caib.zkib.component.DataTable;
import es.caib.zkib.datamodel.DataNode;
import es.caib.zkib.datasource.XPathUtils;


public class UserMergeAction extends MergeAction {
	DataTable table;
	
	public UserMergeAction() {
		super(User.class.getName());
	}

	@Override
	public void apply(Map<String, int[]> actions, int srcPosition[]) throws Exception {
		int[] options = actions.get("userName");
		int targetPosition = options[0];
		super.apply(actions, srcPosition, options[0]);
		
		String xpath = xPaths.get(targetPosition);
		Long targetId = (Long) XPathUtils.eval(dataSource, xpath+"/@id");
		String targetName = (String) XPathUtils.eval(dataSource, xpath+"/@userName");
		DataNode targetNode = (DataNode) XPathUtils.eval(dataSource, xpath);
		// Remove src
		UserService userService = EJBLocator.getUserService();
		for (int pos: srcPosition) {
			if (pos != targetPosition) {
				xpath = xPaths.get(pos);
				Long srcId = (Long) XPathUtils.eval(dataSource, xpath+"/@id");
				String name = (String) XPathUtils.eval(dataSource, xpath+"/@userName");
				userService.merge(srcId, targetId);
				if (currentIssue != null)
					EJBLocator.getIssueService().registerAction(currentIssue, "Merge user "+name+" into "+targetName);
			}
		}
		
		if (currentIssue != null) {
			for (int i = 0; i < names.size(); i++) {
				String name = names.get(i);
				for (IssueUser iu: currentIssue.getUsers()) {
					if (iu.getUserName().equals(name)) {
						if (targetPosition == i) {
							iu.setAction(EventUserAction.MASTER_USER);
						}
						else {
							iu.setAction(EventUserAction.DIFFERENT_USER);
							for (int pos: srcPosition) {
								if (pos == i)
									iu.setAction(EventUserAction.DUPLICATED);
							}
						}
					}
				}
			}
			currentIssue.setStatus(IssueStatus.SOLVED);
			EJBLocator.getIssueService().update(currentIssue);
		}

		for (int pos: srcPosition) {
			if (pos != targetPosition) {
				xpath = xPaths.get(pos);
				DataNode dn = (DataNode) XPathUtils.eval(dataSource, xpath);
				dn.delete();
				dn.setTransient(true);
			}
		}
		dataSource.commit();
		targetNode.setDirty(false);
	}

	
	public DataTable getTable() {
		return table;
	}

	
	public void setTable(DataTable table) {
		this.table = table;
	}


}
