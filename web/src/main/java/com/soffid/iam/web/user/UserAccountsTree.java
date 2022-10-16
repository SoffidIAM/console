package com.soffid.iam.web.user;

import java.util.HashMap;
import java.util.LinkedList;

import org.json.JSONWriter;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.Account;
import com.soffid.iam.api.System;

import es.caib.zkib.binder.tree.FullTreeModelProxy;
import es.caib.zkib.binder.tree.TreeModelProxyNode;
import es.caib.zkib.component.DataTree2;
import es.caib.zkib.datamodel.DataNode;


public class UserAccountsTree extends DataTree2 {
	HashMap<String, System> systems = new HashMap<>();
	
	protected String getRowClass(TreeModelProxyNode node) {
		DataNode dataNode = (DataNode) node.getValue();
		if (dataNode != null && dataNode.getInstance() instanceof Account) {
			Account acc = (Account) dataNode.getInstance();
			if (acc.isDisabled())
				return "dashed";
			System s = systems.get(acc.getSystem());
			if (s == null) {
				try {
					s = EJBLocator.getDispatcherService().findDispatcherByName(acc.getSystem());
					systems.put(acc.getSystem(), s);
				} catch (Exception e) {
					return "not-dashed";
				}
			}
			if (s == null || (s.isReadOnly() || s.getUrl() == null) && ! "- no class -".equals(s.getClassName()))
				return "grayed";
		}
		return "not-dashed";
	}
	
}
