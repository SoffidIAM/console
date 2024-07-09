package com.soffid.iam.utils;

import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.soffid.iam.ServiceLocator;
import com.soffid.iam.api.Account;
import com.soffid.iam.api.Issue;
import com.soffid.iam.api.IssueStatus;
import com.soffid.iam.api.IssueUser;
import com.soffid.iam.api.User;
import com.soffid.iam.common.security.SoffidPrincipal;
import com.soffid.iam.service.IssueService;

import es.caib.seycon.ng.exception.InternalErrorException;

public class NetworkIntelligenceIssuesUtils {

	protected Log log = LogFactory.getLog(getClass());

	public Issue openIssueEmailBreached(String mailDomain, String breachedEmail, List<User> listUsers, String breachName, String breachDecription) {
		try {
			Issue i = new Issue();
			i.setCreated(new Date());
			i.setStatus(IssueStatus.NEW);
			i.setType("breached-email");
			List<IssueUser> liu = new LinkedList<IssueUser>();
			for (User u : listUsers) {
				IssueUser iu = new IssueUser();
				iu.setUserId(u.getId());
				iu.setUserName(u.getUserName());
				liu.add(iu);
			}
			i.setUsers(liu);
			i.setBreachedEmail(breachedEmail+"@"+mailDomain);
			i.setDataBreach(breachName);
			i.setHtmlDescription(breachDecription);
			IssueService is = ServiceLocator.instance().getIssueService();
			return is.createInternalIssue(i);
		} catch (InternalErrorException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Issue openIssuePasswordBreachedAsync(User user) {
		SoffidPrincipal principal = Security.getSoffidPrincipal();
		new Thread (() -> {
			try {
				ServiceLocator.instance().getAsyncRunnerService().runNewTransaction( () -> {
					Issue i = null;
					try {
						Security.nestedLogin(principal);
						i = openIssuePasswordBreachedInternal(user);
					} catch (Exception e) {
						log.warn("Error trying to register a breached-password e="+e);
					} finally {
						Security.nestedLogoff();
					}
					return i;
				});
			} catch (Throwable th) {
				log.warn("Error trying to register a breached-password th="+th);
			}
		}).start();
		return null;
	}

	private Issue openIssuePasswordBreachedInternal(User userAffected) {
		try {
			Issue i = new Issue();
			i.setCreated(new Date());
			i.setStatus(IssueStatus.NEW);
			i.setType("breached-password");
			IssueUser iu = new IssueUser();
			iu.setUserId(userAffected.getId());
			iu.setUserName(userAffected.getUserName());
			i.setUsers(Arrays.asList(iu));
			String description = null;
			if (Security.getSoffidPrincipal().getUserName() != null) {
				description = "The user "+Security.getSoffidPrincipal().getUserName()+" has tried to set a breached password for the user "+userAffected.getUserName()+", please check the correct action about it";
			} else {
				description = "An attempt has been made to set a breached password for user "+userAffected.getUserName()+", please check the correct action about it";
			}
			i.setException(description);
			createIssue(i);
		} catch (InternalErrorException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Issue openIssuePasswordBreachedAsync(Account account) {
		SoffidPrincipal principal = Security.getSoffidPrincipal();
		new Thread (() -> {
			try {
				ServiceLocator.instance().getAsyncRunnerService().runNewTransaction( () -> {
					Issue i = null;
					try {
						Security.nestedLogin(principal);
						i = openIssuePasswordBreachedInternal(account);
					} catch (Exception e) {
						log.warn("Error trying to register a breached-password e="+e);
					} finally {
						Security.nestedLogoff();
					}
					return i;
				});
			} catch (Throwable th) {
				log.warn("Error trying to register a breached-password th="+th);
			}
		}).start();
		return null;
	}

	private Issue openIssuePasswordBreachedInternal(Account account) {
		try {
			Issue i = new Issue();
			i.setCreated(new Date());
			i.setStatus(IssueStatus.NEW);
			i.setType("breached-account-password");
			i.setAccount(account.getName()+"@"+account.getSystem());
			String description = null;
			if (Security.getSoffidPrincipal() != null) {
				description = "The user "+Security.getSoffidPrincipal().getUserName()+" has tried to set a breached password for the account "+account.getName()+"@"+account.getSystem()+", please check the correct action about it";
			} else {
				description = "The system has tried to set a breached password for the account "+account.getName()+"@"+account.getSystem()+", please check the correct action about it";
			}
			i.setException(description);
			i.setDescription(description);
			createIssue(i);
		} catch (InternalErrorException e) {
			e.printStackTrace();
		}
		return null;
	}

	private synchronized Issue createIssue(Issue issue) throws InternalErrorException {
		IssueService is = ServiceLocator.instance().getIssueService();
		return is.createInternalIssue(issue);
	}
}
