package com.soffid.iam.utils;

import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.soffid.iam.ServiceLocator;
import com.soffid.iam.api.Issue;
import com.soffid.iam.api.IssueStatus;
import com.soffid.iam.api.IssueUser;
import com.soffid.iam.api.User;
import com.soffid.iam.common.security.SoffidPrincipal;
import com.soffid.iam.service.IssueService;

import es.caib.seycon.ng.exception.InternalErrorException;

public class NetworkIntelligenceIssuesUtils {

	protected Log log = LogFactory.getLog(getClass());

	public Issue openIssueEmailBreached(String mailDomain, String breachedEmail, List<User> listUsers, String lastBreachDecription) {
		try {
			Issue i = new Issue();
			i.setCreated(new Date());
			i.setStatus(IssueStatus.NEW);
			i.setType("email-breached");
			List<IssueUser> liu = new LinkedList<IssueUser>();
			for (User u : listUsers) {
				IssueUser iu = new IssueUser();
				iu.setUserId(u.getId());
				iu.setUserName(u.getUserName());
				liu.add(iu);
			}
			i.setUsers(liu);
			String description = "The email "+breachedEmail+"@"+mailDomain+" has been found to be breached.";
			i.setDescription(description);
			i.setException(lastBreachDecription);
			IssueService is = ServiceLocator.instance().getIssueService();
			return is.createInternalIssue(i);
		} catch (InternalErrorException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Issue openIssuePasswordBreachedAsync(String userName) {
		SoffidPrincipal principal = Security.getSoffidPrincipal();
		new Thread (() -> {
			try {
				ServiceLocator.instance().getAsyncRunnerService().runNewTransaction( () -> {
					Issue i = null;
					try {
						Security.nestedLogin(principal);
						i = openIssuePasswordBreachedInternal(userName);
					} catch (Exception e) {
						log.warn("Error trying to register a password-breached e="+e);
					} finally {
						Security.nestedLogoff();
					}
					return i;
				});
			} catch (Throwable th) {
				log.warn("Error trying to register a password-breached th="+th);
			}
		}).start();
		return null;
	}

	private Issue openIssuePasswordBreachedInternal(String userName) {
		try {
			Issue i = new Issue();
			i.setCreated(new Date());
			i.setStatus(IssueStatus.NEW);
			i.setType("password-breached");
			if (Security.getSoffidPrincipal().getUserId() != null) {
				IssueUser iu = new IssueUser();
				iu.setUserId(Security.getSoffidPrincipal().getUserId());
				i.setUsers(Arrays.asList(iu));
			} else {
				IssueUser iu = new IssueUser();
				iu.setUserName(userName);
				i.setUsers(Arrays.asList(iu));
			}
			String description = "The user "+userName+" has tried to set a breached password, please check the correct action about it";
			i.setException(description);
			i.setDescription(description);
			IssueService is = ServiceLocator.instance().getIssueService();
			return is.createInternalIssue(i);
		} catch (InternalErrorException e) {
			e.printStackTrace();
		}
		return null;
	}
}
