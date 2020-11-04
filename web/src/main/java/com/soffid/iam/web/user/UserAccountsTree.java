package com.soffid.iam.web.user;

import java.util.HashMap;
import java.util.LinkedList;

import org.json.JSONWriter;

import com.soffid.iam.api.Account;

import es.caib.zkib.binder.tree.FullTreeModelProxy;
import es.caib.zkib.binder.tree.TreeModelProxyNode;
import es.caib.zkib.component.DataTree2;
import es.caib.zkib.datamodel.DataNode;


public class UserAccountsTree extends DataTree2 {
	HashMap<String, Boolean> status = new HashMap<String, Boolean>();
	
	
	protected String getRowClass(TreeModelProxyNode node) {
		DataNode dataNode = (DataNode) node.getValue();
		if (dataNode != null && dataNode.getInstance() instanceof Account) {
			Account acc = (Account) dataNode.getInstance();
			if (acc.isDisabled())
				return "dashed";
		}
		return "not-dashed";
	}
	
}
