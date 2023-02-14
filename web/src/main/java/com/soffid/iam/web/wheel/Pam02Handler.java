package com.soffid.iam.web.wheel;

import java.util.Arrays;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Label;
import org.zkoss.zul.Timer;
import org.zkoss.zul.Window;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.ServiceLocator;
import com.soffid.iam.api.Account;
import com.soffid.iam.api.AuthorizationRole;
import com.soffid.iam.api.Role;
import com.soffid.iam.api.VaultFolder;
import com.soffid.iam.common.security.SoffidPrincipal;
import com.soffid.iam.service.AuthorizationService;
import com.soffid.iam.utils.Security;

import es.caib.seycon.ng.exception.AccountAlreadyExistsException;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.component.DataModel;
import es.caib.zkib.component.DataTable;
import es.caib.zkib.component.Wizard;
import es.caib.zkib.datamodel.DataNode;
import es.caib.zkib.datamodel.DataNodeCollection;
import es.caib.zkib.zkiblaf.Missatgebox;


public class Pam02Handler extends Window {
	private Wizard wizard;
	private Timer timer;
	
	int substep;
	int currentServers;
	private DataTable listbox;
	private boolean finished;
	private Exception lastException;
	
	public void back(Event ev) {
		if (wizard.getSelected() == 0)
			setVisible(false);
		else
			wizard.previous();
		timer.stop();
	}
	
	public void onTimer(Event ev) throws Exception {
		if (finished) {
			((Label)getFellow("progress")).setValue(Labels.getLabel("wizard-vault.selectAccountsFinished"));
			getFellow("step3Button").setVisible(true);
			getFellow("step3Wait").setVisible(false);
			if (lastException != null)
				throw lastException;
		}
	}
	
	public void doHighlighted() {
		super.doHighlighted();
		wizard = (Wizard) getFellow("wizard");
		timer = (Timer) getFellow("timer");
		listbox = (DataTable) getFellow("listbox");
		wizard.setSelected(0);
	}

	public void step2(Event ev) {
		int[] s = listbox.getSelectedIndexes();
		if (s.length == 0) {
			Missatgebox.avis(Labels.getLabel("wizard-vault.selectAccountsWarning"));
		} else {
			wizard.next();
			final SoffidPrincipal p = Security.getSoffidPrincipal();
			new Thread(() -> {
				createVault(p);
			}).start();;
			timer.start();
		}
	}

	private void createVault(SoffidPrincipal principal) {
		int[] s = listbox.getSelectedIndexes();
		Arrays.sort(s);

		Security.nestedLogin(principal);
		try {
			Role owner = createRole("SOFFID_ADMIN", "Soffid administrators");
			Role manager = createRole("SOFFID_VAULT_MGR", "Password vault manager");
			Role user = createRole("SOFFID_VAULT_USER", "Password vault user");
			VaultFolder folder = createVaultFolder(owner,  manager, user);
			assignAccounts(folder, owner, manager, user);
		} catch (Exception e) {
			lastException = e;
		} finally {
			finished = true;
			Security.nestedLogoff();
		}
	}

	private void assignAccounts(VaultFolder folder, Role owner, Role manager, Role user) throws InternalErrorException, AccountAlreadyExistsException, NamingException, CreateException {
		int[] s = listbox.getSelectedIndexes();
		
		DataModel dm = (DataModel) getFellow("model");
		DataNodeCollection coll = (DataNodeCollection) dm.getJXPathContext().getValue("/account");
		for (int p: s) {
			DataNode accNode = (DataNode) coll.getDataModel(p);
			Account acc = (Account) accNode.getInstance();
			acc.setVaultFolderId(folder.getId());
			acc.setInheritNewPermissions(true);
			acc.getOwnerRoles().add(owner.getName()+"@"+owner.getSystem());
			acc.getManagerRoles().add(manager.getName()+"@"+manager.getSystem());
			acc.getGrantedRoles().add(user.getName()+"@"+user.getSystem());
			EJBLocator.getAccountService().updateAccount2(acc);
		}
	}

	private Role createRole(String name, String description) throws InternalErrorException, NamingException, CreateException {
		final String soffidSystem = EJBLocator.getDispatcherService().findSoffidDispatcher().getName();
		Role role = EJBLocator.getApplicationService().findRoleByNameAndSystem(name, soffidSystem);
		if (role == null) {
			role = new Role();
			role.setName(name);
			role.setDescription(description);
			role.setSystem(soffidSystem);
			role.setInformationSystemName("SOFFID");
			role = EJBLocator.getApplicationService().create(role);
		}
		
		grant("sso:manageAccounts", role);
		return role;
	}

	private void grant(String authName, Role role) throws InternalErrorException {
		AuthorizationService autService = ServiceLocator.instance().getAuthorizationService();
		for (AuthorizationRole auth0: autService.getAuthorizationRoles(authName)) {
			if (auth0.getRole().getId().equals(role.getId()))
				return; // Already granted
		}
		AuthorizationRole auth = new AuthorizationRole();
		auth.setAuthorization(authName);
		auth.setRole(role);
		autService.create(auth );
	}

	private VaultFolder createVaultFolder(Role owner, Role manager, Role user) throws InternalErrorException, NamingException, CreateException {
		for (VaultFolder f: EJBLocator.getVaultService().getRootFolders() ) {
			if (! f.isPersonal() && f.getName().equals("Shared accounts"))
				return f;
		}
		VaultFolder folder = new VaultFolder();
		folder.setName("Shared accounts");
		folder.setDescription("Shared accounts");
		folder.getOwnerRoles().add(owner.getName()+"@"+owner.getSystem());
		folder.getManagerRoles().add(manager.getName()+"@"+manager.getSystem());
		folder.getGrantedRoles().add(user.getName()+"@"+user.getSystem());
		return EJBLocator.getVaultService().create(folder);
	}
	
	public void end(Event ev) {
		Executions.getCurrent().sendRedirect("/resource/account/vault.zul", "_blank");
		detach();

	}
}
