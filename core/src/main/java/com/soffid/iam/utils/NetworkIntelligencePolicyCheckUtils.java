package com.soffid.iam.utils;

import com.soffid.iam.ServiceLocator;
import com.soffid.iam.api.Account;
import com.soffid.iam.api.PasswordPolicy;
import com.soffid.iam.api.User;

public class NetworkIntelligencePolicyCheckUtils {

	final static String DEFAULT_AGENT = "soffid";

	public static boolean isCheckPasswordBreached(String userName) {
		try {
			User user = ServiceLocator.instance().getUserService().findUserByUserName(userName);
			String userType = user.getUserType();
			com.soffid.iam.api.System agent = ServiceLocator.instance().getDispatcherService().findDispatcherByName(DEFAULT_AGENT);
			String passwordDomain = agent.getPasswordsDomain();
			PasswordPolicy passordPolicy = ServiceLocator.instance().getUserDomainService().findPolicyByTypeAndPasswordDomain(userType, passwordDomain);
			return passordPolicy.isCheckPasswordBreached();
		} catch(Exception e) {
			return false;
		}
	}

	public static boolean isCheckPasswordBreached(String userName, String passwordDomain) {
		try {
			User user = ServiceLocator.instance().getUserService().findUserByUserName(userName);
			String userType = user.getUserType();
			PasswordPolicy passordPolicy = ServiceLocator.instance().getUserDomainService().findPolicyByTypeAndPasswordDomain(userType, passwordDomain);
			return passordPolicy.isCheckPasswordBreached();
		} catch(Exception e) {
			return false;
		}
	}

	public static boolean isCheckPasswordBreached(Account account) {
		try {
			String userType = account.getPasswordPolicy();
			String agentName = account.getSystem();
			com.soffid.iam.api.System agent = ServiceLocator.instance().getDispatcherService().findDispatcherByName(agentName);
			String passwordDomain = agent.getPasswordsDomain();
			PasswordPolicy passordPolicy = ServiceLocator.instance().getUserDomainService().findPolicyByTypeAndPasswordDomain(userType, passwordDomain);
			return passordPolicy.isCheckPasswordBreached();
		} catch(Exception e) {
			return false;
		}
	}
}
