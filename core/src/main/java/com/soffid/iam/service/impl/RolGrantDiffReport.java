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

public class RolGrantDiffReport {
	UserEntityDao userEntityDao;
	AccountEntityDao accountEntityDao;
	
	private File outFile;
	private PrintStream out;

	public File generateReport(Collection<RoleGrant> list1, LinkedList<RoleGrant> list2) throws IOException {
		generateHeader();
		
		HashMap<String, RoleGrant> grantees = new HashMap<String, RoleGrant>();
		for (RoleGrant pr: list1)
		{
			grantees.put(pr.getOwnerAccountName()+" => "+
					pr.getRoleName()+" @ "+pr.getSystem()+" / "+
					pr.getDomainValue(), pr);
		}

		HashMap<String, RoleGrant> newGrantees = new HashMap<String, RoleGrant>();
		for (RoleGrant pr: list2)
		{
			String n = pr.getOwnerAccountName()+" => "+
					pr.getRoleName()+" @ "+pr.getSystem()+" / "+
					pr.getDomainValue();
			if (grantees.containsKey(n))
				grantees.remove(n);
			else
				newGrantees.put(n, pr);
		}

		generateList(grantees, "Role will be revoked from:", "revoke");
		generateList(newGrantees, "Role will be granted to:", "grant");
		
		generateFooter();
		
		out.close();
		return outFile;
	}

	private void generateList(HashMap<String, RoleGrant> grantees,
			String title, String style) {
		if (grantees.isEmpty())
			return;
		boolean first = true;
		for (RoleGrant rg: grantees.values())
		{
			out.print("<tr class='line'><td>");
			if (first)
			{
				out.print(title);
				first = false;
			}
			out.print("</td><td class=\""+style+"\">");
			if (rg.getUser() != null)
			{
				UserEntity u = userEntityDao.findByUserName(rg.getUser());
				if (u != null)
				{
					out.print(escape(u.getUserName()));
					out.print(" ");
					out.print(escape(u.getFullName()));
				}
			}
			else if (rg.getOwnerAccountName() != null)
			{
				AccountEntity account = accountEntityDao.findByNameAndSystem(rg.getOwnerAccountName(),  rg.getOwnerSystem());
				if (account != null)
				{
					out.print(escape(account.getName()));
					out.print(" ");
					out.print(escape(account.getDescription()));
				}
			}
			out.print("</td><td>");
			out.print(rg.getRoleName());
			out.print(" @ ");
			out.print(rg.getSystem());
			if (rg.getDomainValue() != null)
			{
				out.print(" / ");
				out.print(rg.getDomainValue());
			}
			out.print("</td></tr>");
		}		
	}

	private void generateFooter() {
		out.print("</tbody></table>");
	}

	private void generateHeader() throws IOException {
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
		out.print("Action");
		out.print("</td><td>");
		out.print("User or account");
		out.print("</td><td>");
		out.print("Role / domain");
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

}
