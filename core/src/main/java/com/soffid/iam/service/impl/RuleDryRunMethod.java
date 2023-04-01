package com.soffid.iam.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.LinkedList;

import org.hibernate.Hibernate;

import com.soffid.iam.model.AccountEntity;
import com.soffid.iam.model.RoleAccountEntity;
import com.soffid.iam.model.RoleEntity;
import com.soffid.iam.model.RuleEntity;
import com.soffid.iam.model.UserEntity;

import es.caib.seycon.ng.exception.AccountAlreadyExistsException;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.exception.NeedsAccountNameException;

public class RuleDryRunMethod implements RuleEvaluatorGrantRevokeMethod {
	File outFile;
	private PrintStream out;
	private LinkedList<String> revokes;
	private LinkedList<String> grants;
	private String currentUser;
	
	
	public RuleDryRunMethod() throws IOException {
		outFile = File.createTempFile("ruleEngine", ".html");
		out = new PrintStream(outFile, "UTF-8");
		
		out.print("<table class='preview-table'><thead><tr class='head'><td>");
		out.print("User");
		out.print("</td><td>");
		out.print("Role to grant");
		out.print("</td><td>");
		out.print("Role to revoke");
		out.println("</tr></thead>");
		out.println("<tbody>");
		
		currentUser = null;
		grants = new LinkedList<String>();
		revokes = new LinkedList<String>();
	}
	
	
	private void dumpLine () {
		if (currentUser != null)
		{
			out.print("<tr class='line'><td>");
			out.print(escape(currentUser));
			out.print("</td><td>");
			for (String grant: grants)
			{
				out.print("<div class='grant'>");
				out.print(escape(grant));
				out.print("</div>");
			}
			out.print("</td><td>");
			for (String revoke: revokes)
			{
				out.print("<div class='revoke'>");
				out.print(escape(revoke));
				out.print("</div>");
			}
			out.println("</td></tr>");
			currentUser = null;
			revokes.clear();
			grants.clear();
		}
	}
	
	private String escape(String grant) {
		return grant
				.replaceAll("&", "&amp;")
				.replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;");
	}


	@Override
	public void grant(RuleEntity rule, UserEntity user, RoleEntity role, String domainValue, AccountEntity account)
			throws InternalErrorException, NeedsAccountNameException, AccountAlreadyExistsException {
		if (!user.getId().equals( currentUser) )
		{
			dumpLine();
			currentUser = user.getUserName()+" "+user.getFullName();
		}
		
		if (!Hibernate.isInitialized(role))
			Hibernate.initialize(role);
		if (!Hibernate.isInitialized(role.getSystem()))
			Hibernate.initialize(role.getSystem());
		String msg = role.getName()+" @ "+role.getSystem().getName();
		if ( domainValue != null)
			msg = msg + " / "+domainValue;
		grants.add(msg);
	}

	@Override
	public void revoke(UserEntity user, RoleAccountEntity grant) throws InternalErrorException {
		if (!user.getId().equals( currentUser) )
		{
			dumpLine();
			currentUser = user.getUserName()+" "+user.getFullName();
		}
		RoleEntity role = grant.getRole();
		String msg = role.getName()+" @ "+role.getSystem().getName();
		if ( grant.getDomainValue() != null)
			msg = msg + " / "+grant.getDomainValue().getValue();
		if ( grant.getGroup() != null)
			msg = msg + " / "+grant.getGroup().getName();
		if ( grant.getInformationSystem() != null)
			msg = msg + " / "+grant.getInformationSystem().getName();
		revokes.add(msg);
	}

	public void close() {
		dumpLine();
		out.println("</tbody></table></body></html>");
		out.close();
	}

	public File getFile() {
		return outFile;
	}

}
