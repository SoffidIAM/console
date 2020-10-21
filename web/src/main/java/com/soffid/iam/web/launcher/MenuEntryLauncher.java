package com.soffid.iam.web.launcher;

import java.util.Collection;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.AccessTree;
import com.soffid.iam.api.AccessTreeExecution;
import com.soffid.iam.service.ejb.SelfService;
import com.soffid.iam.utils.Security;
import com.soffid.iam.web.menu.DynamicLauncher;
import com.soffid.iam.web.menu.MenuOption;
import com.soffid.iam.web.vault.LaunchHelper;

import es.caib.seycon.ng.comu.ExecucioPuntEntrada;


public class MenuEntryLauncher implements DynamicLauncher {

	@Override
	public void launch(MenuOption option) throws Exception {
		AccessTree accessTree = option.getAccessTree();
		
		String scope = com.soffid.iam.ServiceLocator.instance()
				.getEntryPointService()
				.getScopeForAddress(Security.getClientIp());
		AccessTreeExecution selected = null;
		if ("L".equals(scope))
			selected = findExecution (accessTree.getExecutions(), "L");
		if ("W".equals(scope) || "L".equals(scope) && selected == null)
			selected = findExecution (accessTree.getExecutions(), "W");
		if (selected == null)
			selected = findExecution (accessTree.getExecutions(), "I");

		if (selected != null)
			new LaunchHelper().launchAccessTree(accessTree, selected);
	}

	private AccessTreeExecution findExecution(Collection<AccessTreeExecution> executions, String scope) {
		for (AccessTreeExecution exe: executions)
			if (scope.equals(exe.getScope()))
				return exe;
		return null;
	}

}
