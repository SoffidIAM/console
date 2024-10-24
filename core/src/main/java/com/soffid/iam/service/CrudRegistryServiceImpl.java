package com.soffid.iam.service;

import java.util.HashMap;
import java.util.Map;

import com.soffid.iam.api.Account;
import com.soffid.iam.api.Application;
import com.soffid.iam.api.Audit;
import com.soffid.iam.api.CrudHandler;
import com.soffid.iam.api.CustomObject;
import com.soffid.iam.api.DomainValue;
import com.soffid.iam.api.Group;
import com.soffid.iam.api.GroupUser;
import com.soffid.iam.api.Host;
import com.soffid.iam.api.Issue;
import com.soffid.iam.api.MailDomain;
import com.soffid.iam.api.MailList;
import com.soffid.iam.api.Network;
import com.soffid.iam.api.OUType;
import com.soffid.iam.api.Printer;
import com.soffid.iam.api.PrinterUser;
import com.soffid.iam.api.Role;
import com.soffid.iam.api.RoleAccount;
import com.soffid.iam.api.System;
import com.soffid.iam.api.User;
import com.soffid.iam.api.UserType;
import com.soffid.iam.api.VaultFolder;
import com.soffid.iam.service.crud.CrudAccountHandler;
import com.soffid.iam.service.crud.CrudApplicationHandler;
import com.soffid.iam.service.crud.CrudAuditHandler;
import com.soffid.iam.service.crud.CrudCustomObjectHandler;
import com.soffid.iam.service.crud.CrudDomainValueHandler;
import com.soffid.iam.service.crud.CrudGroupHandler;
import com.soffid.iam.service.crud.CrudGroupUserHandler;
import com.soffid.iam.service.crud.CrudHostHandler;
import com.soffid.iam.service.crud.CrudIssueHandler;
import com.soffid.iam.service.crud.CrudMailDomainHandler;
import com.soffid.iam.service.crud.CrudMailListHandler;
import com.soffid.iam.service.crud.CrudNetworkHandler;
import com.soffid.iam.service.crud.CrudOUTypeHandler;
import com.soffid.iam.service.crud.CrudPrinterHandler;
import com.soffid.iam.service.crud.CrudPrinterUserHandler;
import com.soffid.iam.service.crud.CrudRoleAccountHandler;
import com.soffid.iam.service.crud.CrudRoleHandler;
import com.soffid.iam.service.crud.CrudSystemHandler;
import com.soffid.iam.service.crud.CrudUserHandler;
import com.soffid.iam.service.crud.CrudUserTypeHandler;
import com.soffid.iam.service.crud.CrudVaultFolderHandler;

public class CrudRegistryServiceImpl extends CrudRegistryServiceBase {
	Map<String, CrudHandler<?>> registry = new HashMap<String,CrudHandler<?>>();
	
	@Override
	protected <E> CrudHandler<E> handleGetHandler(Class<E> cl) throws Exception {
		return (CrudHandler<E>) registry.get(cl.getName());
	}

	@Override
	protected <E> CrudHandler<E> handleGetHandler(String cl) throws Exception {
		CrudHandler<E> h = (CrudHandler<E>) registry.get(cl);
		if (h == null) {
			h = (CrudHandler<E>) new CrudCustomObjectHandler(cl);
		}
		return h;
	}
	@Override
	protected void handleRegisterDefaultHandlers() throws Exception {
		registry.put(User.class.getName(), new CrudUserHandler());
		registry.put(Host.class.getName(), new CrudHostHandler());
		registry.put(Network.class.getName(), new CrudNetworkHandler());
		registry.put(Group.class.getName(), new CrudGroupHandler());
		registry.put(Role.class.getName(), new CrudRoleHandler());
		registry.put(Application.class.getName(), new CrudApplicationHandler());
		registry.put(UserType.class.getName(), new CrudUserTypeHandler());
		registry.put(MailDomain.class.getName(), new CrudMailDomainHandler());
		registry.put(System.class.getName(), new CrudSystemHandler());
		registry.put(DomainValue.class.getName(), new CrudDomainValueHandler());
		registry.put(OUType.class.getName(), new CrudOUTypeHandler());
		registry.put(VaultFolder.class.getName(), new CrudVaultFolderHandler());
		registry.put(Account.class.getName(), new CrudAccountHandler());
		registry.put(MailList.class.getName(), new CrudMailListHandler());
		registry.put(CustomObject.class.getName(), new CrudCustomObjectHandler(null));
		registry.put(GroupUser.class.getName(), new CrudGroupUserHandler());
		registry.put(RoleAccount.class.getName(), new CrudRoleAccountHandler());
		registry.put(Printer.class.getName(), new CrudPrinterHandler());
		registry.put(PrinterUser.class.getName(), new CrudPrinterUserHandler());
		registry.put(Audit.class.getName(), new CrudAuditHandler());
		registry.put(Issue.class.getName(), new CrudIssueHandler());
	}

	@Override
	protected <E> void handleRegisterHandler(Class<E> cl, CrudHandler<E> handler) throws Exception {
		if (registry.containsKey(cl.getName()))
			throw new InternalError("CrudHandler for class "+cl.getName()+" is already registered");

		registry.put(cl.getName(), handler);
	}

}
