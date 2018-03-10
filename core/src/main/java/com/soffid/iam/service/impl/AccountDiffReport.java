package com.soffid.iam.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import com.soffid.iam.api.Account;
import com.soffid.iam.api.AccountStatus;
import com.soffid.iam.api.System;
import com.soffid.iam.api.User;
import com.soffid.iam.model.AccountEntity;
import com.soffid.iam.model.AccountEntityDao;
import com.soffid.iam.model.UserEntity;
import com.soffid.iam.model.UserEntityDao;
import com.soffid.iam.service.AccountService;

import es.caib.seycon.ng.exception.AccountAlreadyExistsException;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.exception.NeedsAccountNameException;

public class AccountDiffReport {
	UserEntityDao userEntityDao;
	AccountEntityDao accountEntityDao;
	AccountService accountService;
	boolean apply = false;
	System system;
	
	private File outFile;
	private PrintStream out;

	private void generateFooter() {
		out.print("</tbody></table>");
	}

	public void generateHeader() throws IOException {
		outFile = File.createTempFile("ruleEngine", ".html");
		out = new PrintStream(outFile, "UTF-8");
		
		out.println("<style>");
		out.println(".preview-table {border-collapse: collapse;}");
		out.println(".preview-table tr {padding-left: 1em; padding-right: 1em;}");
		out.println(".preview-table tr td {padding-left: 1em; padding-right: 1em;}");
		out.println(".preview-table .head {background-color: rgba(23, 181, 200, 1); border: 1px solid rgba(23, 181, 200, 1);}");
		out.println(".preview-table .line {border: 1px solid black;}");
		out.println(".preview-table .grant {color: green;}");
		out.println(".preview-table .revoke {color: red;}");
		out.println("</style>");
		out.print("<table class='preview-table'><thead><tr class='head'><td>");
		out.print("User");
		out.print("</td><td>");
		out.print("Account to disable");
		out.print("</td><td>");
		out.print("Account to create");
		out.println("</tr></thead>");
		out.println("<tbody>");
	}

	private String escape(String grant) {
		return grant
				.replaceAll("&", "&amp;")
				.replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;");
	}

	public AccountEntityDao getAccountEntityDao() {
		return accountEntityDao;
	}

	public void setAccountEntityDao(AccountEntityDao accountEntityDao) {
		this.accountEntityDao = accountEntityDao;
	}

	public UserEntityDao getUserEntityDao() {
		return userEntityDao;
	}

	public void setUserEntityDao(UserEntityDao userEntityDao) {
		this.userEntityDao = userEntityDao;
	}

	public void close() {
		generateFooter();
		out.close();
	}

	public File getFile() {
		return outFile;
	}

	public void disableAccount(UserEntity u, AccountEntity account) throws InternalErrorException, AccountAlreadyExistsException {
		if (apply)
		{
			Account acc = accountService.findAccountById(account.getId());
			acc.setStatus(AccountStatus.DISABLED);
			accountService.updateAccount(acc);
		}
		out.print("<tr class='line'><td>");
		out.print(escape(u.getUserName()));
		out.print(" ");
		out.print(escape(u.getFullName()));
		out.print("</td><td class='revoke'>");
		out.print(account.getName());
		out.print("</td><td></td></tr>");
	}

	public void createAccount(UserEntity u, String accountName) throws InternalErrorException, NeedsAccountNameException, AccountAlreadyExistsException {
		if (apply)
		{
			Account acc = accountService.findAccount(accountName, system.getName());
			if (acc == null)
			{
				User user = userEntityDao.toUser(u);
				accountService.createAccount(user, system, accountName);
			}
			else
			{
				acc.setStatus(AccountStatus.ACTIVE);
				accountService.updateAccount(acc);
			}
		}
		out.print("<tr class='line'><td>");
		out.print(escape(u.getUserName()));
		out.print(" ");
		out.print(escape(u.getFullName()));
		out.print("</td><td></td><td class='grant'>");
		out.print(accountName);
		out.print("</td></tr>");
	}

	public boolean isApply() {
		return apply;
	}

	public void setApply(boolean apply) {
		this.apply = apply;
	}

	public System getSystem() {
		return system;
	}

	public void setSystem(System system) {
		this.system = system;
	}

	public AccountService getAccountService() {
		return accountService;
	}

	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

}
