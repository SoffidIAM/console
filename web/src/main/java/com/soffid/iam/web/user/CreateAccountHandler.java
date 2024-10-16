package com.soffid.iam.web.user;

import java.util.Collection;
import java.util.Iterator;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.apache.openejb.jee.jba.cmp.Datasource;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Window;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.Account;
import com.soffid.iam.api.AccountStatus;
import com.soffid.iam.api.PasswordPolicy;
import com.soffid.iam.api.System;
import com.soffid.iam.api.User;
import com.soffid.iam.web.component.CustomField3;

import es.caib.seycon.ng.comu.AccountType;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.component.DataModel;
import es.caib.zkib.component.DataTable;
import es.caib.zkib.component.DataTree2;
import es.caib.zkib.component.Wizard;
import es.caib.zkib.datamodel.DataModelCollection;
import es.caib.zkib.datamodel.DataModelNode;
import es.caib.zkib.datamodel.DataNode;
import es.caib.zkib.datamodel.DataNodeCollection;
import es.caib.zkib.datasource.CommitException;
import es.caib.zkib.datasource.DataSource;
import es.caib.zkib.datasource.XPathUtils;
import es.caib.zkib.jxpath.Pointer;
import es.caib.zkib.zkiblaf.Missatgebox;


public class CreateAccountHandler extends Window implements AfterCompose {
	private String systemName;
	private Wizard wizard;
	private String userDataSource;
	private String dataSource;
	private String newUserPath;
	
	public void onStart(Event event) {
		wizard.setSelected(0);
		doHighlighted();
	}

	@Override
	public void afterCompose() {
		DataModel model = (DataModel) getFellow("model");
		model.getVariables().declareVariable("className", System.class.getName());
		wizard = (Wizard) getFellow("wizard");
	}

	public void undo() {
		setVisible(false);
	}
	
	public void onSelect(Event event) throws InternalErrorException, NamingException, CreateException {
		selectName(event);
	}
	
	public void selectName(Event ev) throws InternalErrorException, NamingException, CreateException {
		DataTable dt = (DataTable) getFellow("listbox");
		if (dt.getSelectedIndex() < 0) {
			Missatgebox.avis(Labels.getLabel("user_createaccount.selectOne"));
		} else {
			systemName = (String) XPathUtils.getValue((DataSource)dt, "@name");
			String userName = (String) XPathUtils.getValue( Path.getComponent(userDataSource), "@userName");
			String name = EJBLocator.getAccountService().guessAccountName(userName, systemName);
			getAccountNameField().setValue(name);
			((CustomField3) getFellow("systemName")).setValue(systemName);
			wizard.next();
			checkAccountName();
		}
	}
	
	public boolean checkAccountName() {
		boolean valid = true;
		CustomField3 accountNameField = getAccountNameField();
		String accountName = (String) accountNameField.getValue();
		if (accountName == null || accountName.trim().isEmpty() ) {
			valid = false;
		} else {
			try {
				Account acc = EJBLocator.getAccountService().findAccount(accountName, systemName);
				if (acc != null && acc.getType() == AccountType.IGNORED)
					accountNameField.setWarning(null, "Account already exists in target system");
				else if (acc != null) {
					valid = false;
					accountNameField.setWarning(null, "Account name already in use");
				}
				else
					accountNameField.setWarning(null, null);
			} catch (Exception e) {
				valid = false;
				accountNameField.setWarning(null, e.toString());
			}
			
		}
		return valid;
	}

	public CustomField3 getAccountNameField() {
		return (CustomField3) getFellow("accountName");
	}

	public void back(Event ev) {
		wizard.previous();
	}

	
	public String getUserDataSource() {
		return userDataSource;
	}

	
	public void setUserDataSource(String userDataSource) {
		this.userDataSource = userDataSource;
	}

	
	public String getDataSource() {
		return dataSource;
	}

	
	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}
	
	public void setAccountProperties(Event ev) throws Exception {
		if (checkAccountName()) {
			System system = EJBLocator.getDispatcherService().findDispatcherByName(systemName);
			String passwordPolicy = system.getPasswordsDomain();
			DataSource usersDataSource = (DataSource) Path.getComponent(userDataSource);
			
			Wizard interator;
			
			CustomField3 accountNameField = getAccountNameField();
			String accountName = (String) accountNameField.getValue();
			
			DataTree2 accountsTree = (DataTree2) Path.getComponent(dataSource);

			User user = (User) XPathUtils.eval(usersDataSource, "instance");
			
			PasswordPolicy policy = EJBLocator.getUserDomainService().findPolicyByTypeAndPasswordDomain(user.getUserType(), system.getPasswordsDomain());
			if (policy == null) {
				Missatgebox.avis(Labels.getLabel("user_createaccount.invalidDispatcher"));
				return;
			
			
			}				
			
			Account account = EJBLocator.getAccountService().createAccount(user, system, accountName);

			DataNodeCollection passwordDomains = (DataNodeCollection) XPathUtils.eval(usersDataSource, "domini");
			passwordDomains.refresh();
			newUserPath = null;
			for (int i = 0; i < passwordDomains.size(); i++) {
				DataModelNode passwordDomain = passwordDomains.getDataModel(i);
				DataModelCollection policies = passwordDomain.getListModel("policy");
				for (int j = 0; j < policies.getSize(); j++) {
					DataModelNode policyNode = policies.getDataModel(j);
					DataModelCollection accounts = policyNode.getListModel("account");
					for (int k = 0; k < accounts.getSize(); k++) {
						DataNode accountNode = (DataNode) accounts.getDataModel(k);
						Long id = (Long) accountNode.get("id");
						if (account.getId().equals(id)) {
							accountsTree.setSelectedIndex(new int[]{i, k});
							newUserPath = accountsTree.getSelectedItemXPath();
						}
					}
				}
			}
			wizard.next();
			return;
		}
	}
	
	public void apply(Event event) throws CommitException {
		DataSource usersDataSource = (DataSource) Path.getComponent(userDataSource);
		usersDataSource.commit();
		setVisible(false);
	}

	public void backAndRollback(Event ev) throws CommitException {
		DataTree2 accountsTree = (DataTree2) Path.getComponent(dataSource);
		DataSource usersDataSource = (DataSource) Path.getComponent(userDataSource);
		if (newUserPath != null) {
			XPathUtils.removePath(usersDataSource, newUserPath);
			usersDataSource.commit();
		}
		wizard.previous();
	}
	
}
