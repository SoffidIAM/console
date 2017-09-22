package com.soffid.iam.service.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import com.soffid.iam.api.RoleGrant;
import com.soffid.iam.model.AccountEntity;
import com.soffid.iam.model.AccountEntityDao;
import com.soffid.iam.model.UserEntity;
import com.soffid.iam.model.UserEntityDao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class AccountDiffReport {
	UserEntityDao userEntityDao;
	AccountEntityDao accountEntityDao;
	
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

	public void disableAccount(UserEntity u, String accountName) {
		out.print("<tr class='line'><td>");
		out.print(escape(u.getUserName()));
		out.print(" ");
		out.print(escape(u.getFullName()));
		out.print("</td><td class='revoke'>");
		out.print(accountName);
		out.print("</td><td></td></tr>");
	}

	public void createAccount(UserEntity u, String accountName) {
		out.print("<tr class='line'><td>");
		out.print(escape(u.getUserName()));
		out.print(" ");
		out.print(escape(u.getFullName()));
		out.print("</td><td></td><td class='grant'>");
		out.print(accountName);
		out.print("</td></tr>");
	}

}
