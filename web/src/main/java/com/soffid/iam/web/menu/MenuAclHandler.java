package com.soffid.iam.web.menu;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.util.resource.Labels;
import org.zkoss.xel.fn.CommonFns;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.HtmlBasedComponent;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Div;
import org.zkoss.zul.Window;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.AccessTree;
import com.soffid.iam.api.AccessTreeAuthorization;
import com.soffid.iam.api.Account;
import com.soffid.iam.api.Role;
import com.soffid.iam.api.User;
import com.soffid.iam.service.ejb.ApplicationService;
import com.soffid.iam.service.ejb.EntryPointService;
import com.soffid.iam.service.ejb.GroupService;
import com.soffid.iam.service.ejb.UserService;
import com.soffid.iam.service.ejb.AccountService;
import com.soffid.iam.web.component.CustomField3;
import com.soffid.iam.web.component.ObjectAttributesDiv;
import com.soffid.iam.web.popup.CsvParser;
import com.soffid.iam.web.popup.ImportCsvHandler;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.component.DataTable;
import es.caib.zkib.component.DataTree2;
import es.caib.zkib.component.Wizard;
import es.caib.zkib.datamodel.DataNodeCollection;
import es.caib.zkib.datasource.CommitException;
import es.caib.zkib.datasource.DataSource;
import es.caib.zkib.datasource.XPathUtils;
import es.caib.zkib.zkiblaf.Missatgebox;


public class MenuAclHandler extends Div implements AfterCompose {
	private static final long serialVersionUID = 1L;
	private String listboxPath;
	
	private UserService userService;
	private Role currentRole;
	private CustomField3 account;
	private CustomField3 user;
	private CustomField3 role;
	private CustomField3 group;
	private CustomField3 account2;
	private CustomField3 user2;
	private CustomField3 role2;
	private CustomField3 group2;
	private CustomField3 typeField;
	private CustomField3 levelField;
	private CustomField3 account3;
	private CustomField3 user3;
	private CustomField3 role3;
	private CustomField3 group3;
	
	public MenuAclHandler() throws NamingException, CreateException, InternalErrorException {
		userService = EJBLocator.getUserService();
	}
	
	public void afterCompose() {
		listboxPath = (String) Executions.getCurrent().getArg().get("listbox");
		Window w = getWindowAdd();
		user = (CustomField3) w.getFellow("user");
		account = (CustomField3) w.getFellow("account");
		role = (CustomField3) w.getFellow("role");
		group = (CustomField3) w.getFellow("group");
		user2 = (CustomField3) w.getFellow("user2");
		role2 = (CustomField3) w.getFellow("role2");
		account2 = (CustomField3) w.getFellow("account2");
		group2 = (CustomField3) w.getFellow("group2");
		typeField = (CustomField3) w.getFellow("type");
		levelField = (CustomField3) w.getFellow("level");
		w = getWindowModify();
		user3 = (CustomField3) w.getFellow("user3");
		account3 = (CustomField3) w.getFellow("account3");
		role3 = (CustomField3) w.getFellow("role3");
		group3 = (CustomField3) w.getFellow("group3");
	}
	
	
	public void addNew (Event event) {
		Window w = (Window) getFellow("add-window");
		getWizard().setSelected(0);
		w.doHighlighted();
	}
	
	public void showDetails(Event event) {
		String o = (String) XPathUtils.getValue((DataSource)getListbox(), "authorizedEntityCode");
		String t = (String) XPathUtils.getValue((DataSource)getListbox(), "authorizationEntityType");
		getWizard().next();
		user3.setVisible( "user".equals(t) );
		role3.setVisible( "role".equals(t) );
		group3.setVisible( "group".equals(t) );
		account3.setVisible( "account".equals(t) );

		Window w = getWindowModify();
		w.doHighlighted();
		displayRemoveButton(getListbox(), false);
	}
	
	public void closeDetails(Event event) {
		Window w = getWindowModify();
		w.setVisible(false);
		if (event != null)
			event.stopPropagation();
	}
	
	public void delete(Event event) {
		Missatgebox.confirmaOK_CANCEL(Labels.getLabel("common.delete"), 
				(event2) -> {
					if (event2.getName().equals("onOK")) {
						DataTable dt = getListbox();
						dt.delete();
						closeDetails(null);
						
					}
				});
	}
	
	public void onChange() {
		Window w = getWindowModify();
		ObjectAttributesDiv d = (ObjectAttributesDiv) w.getFellow("attributes");
		if (d.validate()) {
			DataTable dt = getListbox();
			dt.commit();
			closeDetails(null);
		}
	}

	public Window getWindowModify() {
		return (Window) getFellow("modify-window");
	}
	
	public void undoAdd(Event ev) {
		Window w = getWindowAdd();
		w.setVisible(false);
	}
	
	public DataTable getListbox() {
		return (DataTable) getFellow("listbox");
	}

	public Window getWindowAdd() {
		return (Window) getFellow("add-window");
	}

	public Wizard getWizard() {
		return (Wizard) getWindowAdd().getFellow("wizard");
	}

	public void backAndRollback(Event ev) {
		DataTable dt = getListbox();
		dt.delete();
		getWizard().previous();
	}

	
	public String getListboxPath() {
		return listboxPath;
	}

	
	public void setListboxPath(String listboxPath) {
		this.listboxPath = listboxPath;
	}

	public void selectType(Event event) {
		Window w = getWindowAdd();
		CustomField3 cf = (CustomField3) w.getFellow("type");
		if (cf.validate()) {
			String type = (String) cf.getValue();
			getWizard().next();
			user.setVisible("user".equals(type));
			role.setVisible("role".equals(type));
			group.setVisible("group".equals(type));
			account.setVisible("account".equals(type));
			user2.setVisible("user".equals(type));
			role2.setVisible("role".equals(type));
			group2.setVisible("group".equals(type));
			account2.setVisible("account".equals(type));
		}
		
	}
	
	public void selectObject(Event event) {
		Window w = getWindowAdd();
		String type = (String) typeField.getValue();
		if ("user".equals(type) && user.validate() ) {
			user2.setValue(user.getValue());
			getWizard().next();
		}
		if ("group".equals(type) && group.validate() ) {
			group2.setValue(group.getValue());
			getWizard().next();
		}
		if ("role".equals(type) && role.validate() ) {
			role2.setValue(role.getValue());
			getWizard().next();
		}
		if ("account".equals(type) && account.validate() ) {
			account2.setValue(account.getValue());
			getWizard().next();
		}
	}
	
	public void applyAdd(Event event) throws Exception {
		if (levelField.validate()) {
			DataSource entryPointDataSource = (DataSource) Path.getComponent(listboxPath);
			AccessTreeAuthorization na = new AccessTreeAuthorization();
			na.setAccessTreeId((Long) XPathUtils.getValue(entryPointDataSource, "id"));
			na.setAuthorizationLevelDescription( levelField.getValue().toString() );
			String type = (String) typeField.getValue();
			na.setAuthorizationEntityType(type);
			if ("user".equals(type) && user.validate() ) {
				na.setAuthorizedEntityCode(user.getValue().toString());
				na.setAuthorizedEntityDescription(user.getDescription(user.getValue()));
			}
			if ("group".equals(type) && group.validate() ) {
				na.setAuthorizedEntityCode(group.getValue().toString());
				na.setAuthorizedEntityDescription(group.getDescription(group.getValue()));
			}
			if ("role".equals(type) && role.validate() ) {
				na.setAuthorizedEntityCode(role.getValue().toString());
				na.setAuthorizedEntityDescription(role.getDescription(role.getValue()));
			}
			if ("account".equals(type) && account.validate() ) {
				na.setAuthorizedEntityCode(account.getValue().toString());
				na.setAuthorizedEntityDescription(account.getDescription(account.getValue()));
			}
			
			XPathUtils.createPath(entryPointDataSource, "/auth", na);
			entryPointDataSource.commit();
			getWindowAdd().setVisible(false);
			getListbox().setSelectedIndex(-1);
		}
	}

	public void previous(Event event) {
		getWizard().previous();
	}
	
	public void displayRemoveButton(Component lb, boolean display) {
		HtmlBasedComponent d = (HtmlBasedComponent) lb.getNextSibling();
		if (d != null && d instanceof Div) {
			d =  (HtmlBasedComponent) d.getFirstChild();
			if (d != null && "deleteButton".equals(d.getSclass())) {
				d.setVisible(display);
			}
		}
	}
	
	public void multiSelect(Event event) {
		DataTable lb = (DataTable) event.getTarget();
		displayRemoveButton( lb, lb.getSelectedIndexes() != null && lb.getSelectedIndexes().length > 0);
	}

	public void deleteSelected(Event event0) {
		Component b = event0.getTarget();
		final Component lb = b.getParent().getPreviousSibling();
		if (lb instanceof DataTable) {
			final DataTable dt = (DataTable) lb;
			if (dt.getSelectedIndexes() == null || dt.getSelectedIndexes().length == 0) return;
			String msg = dt.getSelectedIndexes().length == 1 ? 
					Labels.getLabel("common.delete") :
					String.format(Labels.getLabel("common.deleteMulti"), dt.getSelectedIndexes().length);
				
			Missatgebox.confirmaOK_CANCEL(msg, 
					(event) -> {
						if (event.getName().equals("onOK")) {
							dt.delete();
							displayRemoveButton(lb, false);
						}
					});
		}
	}

	public void downloadCsv(Event event) {
		Component lb = getListbox();
		if (lb instanceof DataTable)
			((DataTable) lb).download();
		if (lb instanceof DataTree2)
			((DataTree2) lb).download();
	}

	public void importCsv (Event ev) throws IOException, CommitException, InternalErrorException, NamingException, CreateException {
		String[][] data = {
				{Labels.getLabel("aplicacionsIntranet.zul.Nivell"), Labels.getLabel("aplicacionsIntranet.zul.Nivell")},
				{Labels.getLabel("accounts.scope"), Labels.getLabel("accounts.scope")},
				{Labels.getLabel("aplicacionsIntranet.zul.Propietari"), Labels.getLabel("aplicacionsIntranet.zul.Propietari")},
				{Labels.getLabel("com.soffid.iam.api.Account.description"), Labels.getLabel("com.soffid.iam.api.Account.description")},
		};
		String title = Labels.getLabel("tenant.zul.import");
		ImportCsvHandler.startWizard(title, data, this,
				parser -> importCsv(parser));
	}

	private void importCsv(CsvParser parser) {
		Map<String,String> m = null;
		int updates = 0;
		int inserts = 0;
		int unchanged = 0;
		int removed = 0;
		String yes = CommonFns.getLabel("mesg:org.zkoss.zul.mesg.MZul:YES");
		try {
			List<AccessTreeAuthorization> authsInDatabase = new LinkedList<AccessTreeAuthorization>();
			List<AccessTreeAuthorization> authsInFile = new LinkedList<AccessTreeAuthorization>();

			// Retrieve current AccessTreeAuthorization from database
			DataTable dt = (DataTable) getFellow("listbox");
			String accessTreeName = (String) XPathUtils.eval(dt.getDataSource(), "name");
			EntryPointService srv = EJBLocator.getEntryPointService();
			List<AccessTree> accessTreeList = srv.findAccessTreeByTextAndJsonQuery("", "name eq \""+accessTreeName+"\"", new Integer(0), new Integer(500));
			AccessTree accessTree = null;
			if (accessTreeList!=null && !accessTreeList.isEmpty()) {
				accessTree = accessTreeList.get(0);
				Collection<AccessTreeAuthorization>	accessTreeColl = srv.getAuthorizationsApplicationAcessTree(accessTree);
				if (!accessTreeColl.isEmpty()) {
					Iterator<AccessTreeAuthorization> i = accessTreeColl.iterator();
					while (i.hasNext())
						authsInDatabase.add((AccessTreeAuthorization) i.next());
				}
			}

			// Go through the file
			for (Iterator<Map<String, String>> iterator = parser.iterator(); iterator.hasNext(); ) {
				m = iterator.next();
				String nivel = m.get(Labels.getLabel("aplicacionsIntranet.zul.Nivell"));
				String scope = m.get(Labels.getLabel("accounts.scope"));
				String propietario = m.get(Labels.getLabel("aplicacionsIntranet.zul.Propietari"));
				String descripcion = m.get(Labels.getLabel("com.soffid.iam.api.Account.description"));
				if (nivel!=null && !nivel.trim().isEmpty() &&
						scope!=null && !scope.trim().isEmpty() &&
						propietario!=null && !propietario.trim().isEmpty()) {

					// Instanciate the file object as AccessTreeAuthorization
					AccessTreeAuthorization ata = new AccessTreeAuthorization();
					if (Labels.getLabel("aplicacionsIntranet.zul.Administrador").equals(nivel)) {
						ata.setAuthorizationLevelDescription("admin");
					} else if (Labels.getLabel("aplicacionsIntranet.zul.Autoritzat").equals(nivel)) {
						ata.setAuthorizationLevelDescription("exec");
					}
					ata.setAuthorizationEntityType(scope);
					ata.setAuthorizedEntityCode(propietario);
					if (descripcion!=null) {
						ata.setAuthorizedEntityDescription(descripcion);
					} else {
						ata.setAuthorizedEntityDescription(propietario);
					}

					// Check for changes
					boolean foundInFile = false;
					for (AccessTreeAuthorization ata2: authsInDatabase) {
						if (nullCmp (ata.getAuthorizationLevelDescription(), ata2.getAuthorizationLevelDescription()) &&
							nullCmp (ata.getAuthorizationEntityType(), ata2.getAuthorizationEntityType()) &&
							nullCmp (ata.getAuthorizedEntityCode(), ata2.getAuthorizedEntityCode()) ) {
							unchanged ++;
							foundInFile = true;
							authsInFile.add(ata);
							break;
						}
					}
					if (!foundInFile) {
						srv.createAuthorization(accessTree, ata);
						inserts ++;
						authsInDatabase.add(ata);
						authsInFile.add(ata);
					}
				}
			}

			// We review the ones to be deleted
			for (AccessTreeAuthorization ata: authsInDatabase) {
				boolean foundInFile = false;
				for (AccessTreeAuthorization ata2: authsInFile) {
					if (nullCmp (ata.getAuthorizationLevelDescription(), ata2.getAuthorizationLevelDescription()) &&
						nullCmp (ata.getAuthorizationEntityType(), ata2.getAuthorizationEntityType()) &&
						nullCmp (ata.getAuthorizedEntityCode(), ata2.getAuthorizedEntityCode()) ) {
						foundInFile = true;
						break;
					}
				}
				if (!foundInFile) {
					srv.deleteAuthorization(accessTree, ata);
					removed++;
				}
			}
		} catch (UiException e) {
			throw e;
		} catch (Exception e) {
			if (m == null)
				throw new UiException(e);
			else
				throw new UiException("Error loading parameter "+m.get("name"), e);
		}

		Missatgebox.avis(Labels.getLabel("parametres.zul.import", new Object[] { updates, inserts, removed, unchanged }));
		DataTable dt = (DataTable) getFellow("listbox");
		DataNodeCollection coll = (DataNodeCollection) XPathUtils.eval(dt.getDataSource(), "auth");
		coll.commit();
		try {
			coll.refresh();
		} catch (Exception e) {
			throw new UiException(e);
		}
	}

	private boolean nullCmp(Object object1, Object object2) {
		return object1 == null ? object2 == null : object1.equals(object2);
	}
}
