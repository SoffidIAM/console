package com.soffid.iam.web.user;

import com.soffid.iam.api.Account;

import es.caib.zkib.binder.tree.TreeModelProxyNode;
import es.caib.zkib.component.DataTree2;
import es.caib.zkib.datamodel.DataNode;


public class UserColumnsTree extends DataTree2 {
	protected String getRowClass(TreeModelProxyNode node) {
		DataNode dataNode = (DataNode) node.getValue();
		if (dataNode.getInstance() instanceof Account) {
			Account acc = (Account) dataNode.getInstance();
			if (acc.isDisabled())
				return "dashed";
		}
		return "not-dashed";
	}
}
