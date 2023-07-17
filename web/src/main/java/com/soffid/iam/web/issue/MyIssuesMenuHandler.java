package com.soffid.iam.web.issue;

import java.util.List;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.web.menu.DynamicMenuHandler;
import com.soffid.iam.web.menu.MenuOption;


public class MyIssuesMenuHandler implements DynamicMenuHandler {
	@Override
	public List<MenuOption> getOptions(MenuOption option) {
		return null;
	}

	@Override
	public String getTip(MenuOption option) {
		try {
			int count = EJBLocator.getIssueService().countMyIssues();
			if (count > 0) return "" + count;
			else return null;
		} catch (Exception e) {
			return null;
		}
		
	}

	@Override
	public boolean isVisible(MenuOption option) {
		try {
			int count = EJBLocator.getIssueService().countMyIssues();
			return count > 0;
		} catch (Exception e) {
			return true;
		}
	}

	@Override
	public boolean isLeaf() {
		return true;
	}

}
