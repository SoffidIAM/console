package com.soffid.iam.web.config;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.zkoss.util.resource.Labels;
import org.zkoss.xel.fn.CommonFns;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Textbox;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.ForbiddenWord;
import com.soffid.iam.api.PasswordDomain;
import com.soffid.iam.api.PasswordPolicy;
import com.soffid.iam.api.PasswordPolicyForbbidenWord;
import com.soffid.iam.api.UserType;
import com.soffid.iam.service.ejb.UserDomainService;
import com.soffid.iam.utils.Security;
import com.soffid.iam.web.component.FrameHandler;
import com.soffid.iam.web.popup.CsvParser;
import com.soffid.iam.web.popup.ImportCsvHandler;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.component.DataModel;
import es.caib.zkib.component.DataTable;
import es.caib.zkib.component.DataTree2;
import es.caib.zkib.component.Select;
import es.caib.zkib.datamodel.DataModelNode;
import es.caib.zkib.datamodel.DataNode;
import es.caib.zkib.datamodel.DataNodeCollection;
import es.caib.zkib.datasource.CommitException;
import es.caib.zkib.datasource.DataSource;
import es.caib.zkib.datasource.XPathUtils;
import es.caib.zkib.zkiblaf.Missatgebox;

public class PasswordDomainHandler extends FrameHandler implements AfterCompose {
	private boolean canCreatePasswordDomain;
	private boolean canUpdatePasswordDomain;
	private boolean canDeletePasswordDomain;
	private boolean canQueryPasswordDomain;
	private Select lbtipusContrasenya;
	private DataTable listboxBadWords;

	public PasswordDomainHandler() throws InternalErrorException {
		com.soffid.iam.api.Tenant masterTenant = com.soffid.iam.ServiceLocator.instance().getTenantService().getMasterTenant();

		canCreatePasswordDomain = Security.isUserInRole("passwordDomain:create");
		canUpdatePasswordDomain = Security.isUserInRole("passwordDomain:update");
		canDeletePasswordDomain = Security.isUserInRole("passwordDomain:delete");
		canQueryPasswordDomain = Security.isUserInRole("passwordDomain:query");
		
	}

	@Override
	public void setPage(Page p) {
		super.setPage(p);
		getNamespace().setVariable("canCreatePasswordDomain", canCreatePasswordDomain, true);
		getNamespace().setVariable("canUpdatePasswordDomain", canUpdatePasswordDomain, true);
		getNamespace().setVariable("canDeletePasswordDomain", canDeletePasswordDomain, true);
		getNamespace().setVariable("canQueryPasswordDomain", canQueryPasswordDomain, true);
		getNamespace().setVariable("canModifyPasswordDomain", canUpdatePasswordDomain || canCreatePasswordDomain, true);
		getNamespace().setVariable("canCreatePasswordPolicy", canCreatePasswordDomain, true);
		getNamespace().setVariable("canUpdatePasswordPolicy", canUpdatePasswordDomain, true);
		getNamespace().setVariable("canDeletePasswordPolicy", canDeletePasswordDomain, true);
		getNamespace().setVariable("canQueryPasswordPolicy", canQueryPasswordDomain, true);
		getNamespace().setVariable("canModifyPasswordPolicy", canUpdatePasswordDomain || canCreatePasswordDomain, true);
	}
		

	public void onChangeForm(Event event) throws Exception {
		super.onChangeForm(event);
		DataNode dn;
		try {
			dn = (DataNode) XPathUtils.getValue( getListbox(), "/");
		} catch (Exception e) {
			return;
		}
		Object o = dn.getInstance();
		if ( o instanceof PasswordDomain) {
			getFellow("passwordPolicy").setVisible(false);
			getFellow("passwordDomain").setVisible(true);
		} else {
			getFellow("passwordPolicy").setVisible(true);
			getFellow("passwordDomain").setVisible(false);
			onSetPasswordType(event);
			fillBadWords();
		}
	}
	
	public void importCsv () throws IOException, CommitException {
		getModel().commit();
		
		String[][] data = { 
				{"name", Labels.getLabel("user-type.shortName")},
				{"description", Labels.getLabel("accounts.description")},
				{"unmanaged", Labels.getLabel("dominiUsuaris.zul.Unmanaged")}
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
			UserDomainService svc = EJBLocator.getUserDomainService();
			Map<String, UserType> current = new HashMap<String, UserType>();
			for (UserType ut: svc.findAllUserType())
				current.put(ut.getName(), ut);
			
			for ( Iterator<Map<String, String>> iterator = parser.iterator(); iterator.hasNext(); )
			{
				m = iterator.next();
				String name = m.get("name");
				String description = m.get("description");
				String unmanaged = m.get("unmanaged");

				if (name != null && !name.trim().isEmpty())
				{
					UserType ut = current.get(name);
					if (ut != null)
					{
						if (m.containsKey("description"))
							ut.setDescription(description);
						if (m.containsKey("unmanaged"))
							ut.setUnmanaged("true".equalsIgnoreCase(unmanaged) ||
									yes.equalsIgnoreCase(unmanaged));
						svc.update(ut);
						updates ++;
					} else {
						inserts ++;
						ut = new UserType();
						ut.setName(name);
						ut.setDescription(description);
						ut.setUnmanaged("true".equalsIgnoreCase(unmanaged));
						svc.create(ut);
					}
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
		
		getModel().refresh();
		Missatgebox.avis(Labels.getLabel("parametres.zul.import", new Object[] { updates, inserts, removed, unchanged }));
	}
	
	public void afterCompose() {
		super.afterCompose();
		lbtipusContrasenya = (Select) getFellow("lbtipusContrasenya");
		listboxBadWords = (DataTable) getFellow("listboxBadWords");
	}

	public void onSetPasswordType(Event event) {
		DataNode dn = (DataNode) XPathUtils.getValue(getForm(), "/");
		if (dn.getInstance() instanceof PasswordPolicy) {
			PasswordPolicy pp = (PasswordPolicy) dn.getInstance();
			if ("A".equals(pp.getType())) {
				getFellow("rtempsRenovacio").setVisible(true);
				getFellow("rduradaMaxima").setVisible(false);
				getFellow("rduradaMinima").setVisible(false);
				getFellow("rduradaMaximaCaducada").setVisible(false);
			} else {
				getFellow("rtempsRenovacio").setVisible(false);
				getFellow("rduradaMaxima").setVisible(true);
				getFellow("rduradaMinima").setVisible(false);
				getFellow("rduradaMaximaCaducada").setVisible(true);
			}
		}
	}
	
	public void addNew (Event event) throws Exception {
		DataTree2 tree = (DataTree2) getListbox();
		tree.addNew("/passwordDomain");
		showDetails();
	}

	public void addPolicy (Event event) throws Exception {
		DataTree2 tree = (DataTree2) getListbox();
		tree.addNew("/policy");
		showDetails();
	}

	public void addWord( Event event) throws Exception {
		Textbox tb = (Textbox) event.getTarget();
		String word = tb.getText();
		
		DataNodeCollection coll = (DataNodeCollection) XPathUtils.getValue((DataSource) getModel(), "/badWords");
		for ( int i = 0; i < coll.getSize(); i++) {
			DataNode dn = (DataNode) coll.get(i);
			if (dn != null) {
				ForbiddenWord fw = (ForbiddenWord) dn.getInstance();
				if (fw.getForbiddenWord().equals(word))
				{
					int[] selected = listboxBadWords.getSelectedIndexes();
					for ( int s: selected)
						if (s == i) 
							return;
					selected = Arrays.copyOf(selected, selected.length+1);
					selected[selected.length-1] = i;
					listboxBadWords.setSelectedIndex ( selected );
					changeWords();
					return;
				}
			}
		}
		
		ForbiddenWord fw = new ForbiddenWord();
		fw.setForbiddenWord(word);
		coll.add(fw);

		int[] selected = listboxBadWords.getSelectedIndexes();
		selected = Arrays.copyOf(selected, selected.length+1);
		selected[selected.length-1] = coll.getSize()-1;
		listboxBadWords.setSelectedIndex ( selected );
		changeWords();
	}

	public void removeWord( Event event) throws Exception {
		listboxBadWords.delete();
		fillBadWords();
	}
	
	
	private int findBadWord(DataNodeCollection coll, ForbiddenWord fw) {
		for (int i = 0; i < coll.size(); i++) {
			DataNode dn = (DataNode) coll.get(i);
			ForbiddenWord fw2 = (ForbiddenWord) dn.getInstance();
			if (fw2 != null && fw2.getForbiddenWord().equals (fw.getForbiddenWord()))
				return i;
		}
		return -1;
	}
	
	public void fillBadWords () {
		DataNodeCollection collBadWords = (DataNodeCollection) XPathUtils.getValue((DataSource) getModel(), "/badWords");
		List<Integer> l = new LinkedList<Integer>();

		DataNodeCollection coll;
		try {
			coll = (DataNodeCollection) XPathUtils.getValue(getForm(), "/forbiddenWords");
		} catch (Exception e) {
			return;
		}
		
		for ( int i = 0; i < coll.getSize(); i++) {
			DataNode dn = (DataNode) coll.get(i);
			if (dn != null) {
				PasswordPolicyForbbidenWord fw = (PasswordPolicyForbbidenWord) dn.getInstance();
				if (! dn.isDeleted()) {
					int pos = findBadWord(collBadWords, fw.getForbiddenWord());
					if ( pos >= 0) l.add(pos);
				}
			}
		}
		int[] selected = new int[l.size()];
		for ( int i = 0; i < l.size(); i++)
			selected [i] = l.get(i).intValue();
		listboxBadWords.setSelectedIndex(selected);
	}
	
	public void changeWords() {
		int[] selected = listboxBadWords.getSelectedIndexes();
		Arrays.sort(selected);
		
		DataNodeCollection coll = (DataNodeCollection) XPathUtils.getValue((DataSource) getModel(), "/badWords");
		DataNodeCollection enabledColl = (DataNodeCollection) XPathUtils.getValue(getForm(), "/forbiddenWords");
		for ( int i = 0; i < coll.getSize(); i++) {
			DataNode dn = (DataNode) coll.get(i);
			if (dn != null) {
				ForbiddenWord fw = (ForbiddenWord) dn.getInstance();
				if ( Arrays.binarySearch(selected, i) >= 0)
					enableWord(fw, enabledColl);
				else
					disableWord (fw, enabledColl);
			}
		}
	}

	private void enableWord(ForbiddenWord fw, DataNodeCollection enabledColl) {
		for ( int i = 0; i < enabledColl.getSize(); i++) {
			DataNode dn = (DataNode) enabledColl.get(i);
			if (dn != null) {
				PasswordPolicyForbbidenWord pfw = (PasswordPolicyForbbidenWord) dn.getInstance();
				if (pfw.getForbiddenWord().getForbiddenWord().equals(fw.getForbiddenWord())) {
					if ( dn.isDeleted()) 
						dn.undelete();
					return;
				}
			}
		}
		PasswordPolicy pp = (PasswordPolicy) ((DataNode) XPathUtils.getValue(getForm(), "/")).getInstance();
		PasswordPolicyForbbidenWord pfw = new PasswordPolicyForbbidenWord();
		pfw.setForbiddenWord(fw);
		pfw.setPasswordDomainPolicy(pp);
		enabledColl.add(pfw);
	}

	private void disableWord(ForbiddenWord fw, DataNodeCollection enabledColl) {
		for ( int i = 0; i < enabledColl.getSize(); i++) {
			DataNode dn = (DataNode) enabledColl.get(i);
			if (dn != null) {
				PasswordPolicyForbbidenWord pfw = (PasswordPolicyForbbidenWord) dn.getInstance();
				if (pfw.getForbiddenWord().getForbiddenWord().equals(fw.getForbiddenWord())) {
					if (!dn.isDeleted()) 
						dn.delete();
					return;
				}
			}
		}
	}
	
	public void updateUserType(Event event) {
		XPathUtils.setValue(getForm(), "@userTypeDescription", "");
		String userType = (String) XPathUtils.eval(getForm(), "@userType");
		DataNodeCollection coll = (DataNodeCollection) getModel().getJXPathContext().getValue( "/userTypeAndSelect");
		for ( int i = 0; i < coll.size(); i++ ) {
			DataNode dn = (DataNode) coll.getDataModel(i);
			UserType ut = (UserType) dn.getInstance();
			if (ut.getName() == null ? userType == null : ut.getName().equals(userType)) {
				XPathUtils.setValue(getForm(), "@userTypeDescription", ut.getDescription());
				
			}
		}
	}
}
