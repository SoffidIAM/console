package com.soffid.iam.web.application;

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
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Window;

import com.soffid.codemirror.Codemirror;
import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.MailDomain;
import com.soffid.iam.api.Role;
import com.soffid.iam.api.RoleAccount;
import com.soffid.iam.api.Rule;
import com.soffid.iam.api.RuleAssignedRole;
import com.soffid.iam.service.ejb.MailListsService;
import com.soffid.iam.web.component.CustomField3;
import com.soffid.iam.web.component.DomainValueField;
import com.soffid.iam.web.component.FileDump;
import com.soffid.iam.web.component.FrameHandler;
import com.soffid.iam.web.component.InputField3;
import com.soffid.iam.web.popup.CsvParser;
import com.soffid.iam.web.popup.ImportCsvHandler;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.component.DataTable;
import es.caib.zkib.component.Wizard;
import es.caib.zkib.datamodel.DataNode;
import es.caib.zkib.datamodel.DataNodeCollection;
import es.caib.zkib.datasource.CommitException;
import es.caib.zkib.datasource.DataSource;
import es.caib.zkib.datasource.XPathUtils;
import es.caib.zkib.zkiblaf.Missatgebox;

public class RuleHandler extends FrameHandler implements AfterCompose {
	private Role currentRole;
	private RuleAssignedRole currentRoleAccount;

	public RuleHandler() throws InternalErrorException {
		
	}

	public void onChangeForm() {
		Codemirror cm = (Codemirror) getFellow("ru_script");
//		cm.invalidate();
	}
	
	public void importCsv () throws IOException, CommitException {
		getModel().commit();
		
		String[][] data = { 
				{"name", Labels.getLabel("dominisCorreu.zul.Codi")},
				{"description", Labels.getLabel("dominisCorreu.zul.Descripcia")},
				{"obsolete", Labels.getLabel("dominisCorreu.zul.Obsolet-2")}
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
			MailListsService svc = EJBLocator.getMailListsService();
			for ( Iterator<Map<String, String>> iterator = parser.iterator(); iterator.hasNext(); )
			{
				m = iterator.next();
				String name = m.get("name");
				String description = m.get("description");
				String obsolete = m.get("obsolete");

				if (name != null && !name.trim().isEmpty())
				{
					MailDomain cfg = svc.findMailDomainByName(name);
					if (cfg != null)
					{
						cfg.setDescription(description);
						if (obsolete != null)
							cfg.setObsolete("true".equals(obsolete) || yes.equals(obsolete));
						svc.update(cfg);
						updates ++;
					} else {
						inserts ++;
						cfg = new MailDomain();
						cfg.setName(name);
						cfg.setDescription(description);
						if (obsolete != null)
							cfg.setObsolete("true".equals(obsolete) || yes.equals(obsolete));
						svc.create(cfg);
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

	@Override
	public void afterCompose() {
		super.afterCompose();
		Codemirror cm = (Codemirror) getFellow("ru_script");
		try {
			cm.setGlobalVars(new com.soffid.iam.web.agent.ScriptEnviroment().getRuleVars());
			cm.invalidate();
		} catch (InternalErrorException | NamingException | CreateException e) {
			throw new UiException(e);
		}
	}
	
	public void addNewRole(Event event) throws CommitException {
		applyNoClose(event);
		Window w = (Window) getFellow("add-window");
		getWizard().setSelected(0);
		w.doHighlighted();
		CustomField3 role = (CustomField3) w.getFellow("role");
	}
	
	public Window getWindowModify() {
		return (Window) getFellow("modify-window");
	}
	
	public void undoAdd(Event ev) {
		Window w = getWindowAdd();
		w.setVisible(false);
	}
	
	public void selectDomain(Event ev) throws InternalErrorException, NamingException, CreateException, IOException, CommitException {
		Window w = getWindowAdd();
		InputField3 input = (InputField3) w.getFellow("role");
		String v = (String) input.getValue();
		if (input.attributeValidateAll()) {
			int i = v.lastIndexOf("@");
			String roleName = i > 0 ? v.substring(0, i): v;
			String systemName = i > 0 ? v.substring(i+1) : EJBLocator.getDispatcherService().findSoffidDispatcher().getName();
			currentRole = EJBLocator.getApplicationService().findRoleByNameAndSystem(roleName, systemName);
			Component usersListbox = getListbox();
			Long ruleId = (Long) XPathUtils.getValue(usersListbox, "@id");

			if (currentRole != null) {
				currentRoleAccount = new RuleAssignedRole();
				currentRoleAccount.setRoleId(currentRole.getId());
				currentRoleAccount.setRuleId(ruleId);
				
				DataNodeCollection coll = (DataNodeCollection) XPathUtils.getValue(usersListbox, "/ruleAssignedRole");
				coll.add(currentRoleAccount);
				coll.setActiveNode(coll.getDataModel(coll.getSize()-1));
				getWizard().next();
				if (currentRole.getDomain() == null) {
					getWizard().next();
					applyAdd(ev);
				} else {
					DomainValueField dvf = (DomainValueField) getWindowAdd().getFellow("domainValues");
					dvf.setApplication(currentRole.getInformationSystemName());
					dvf.setDomain(currentRole.getDomain());
					dvf.setValue(new LinkedList<String>());
					dvf.createField();
				}
			}
		}
	}

	public Window getWindowAdd() {
		return (Window) getFellow("add-window");
	}

	public Wizard getWizard() {
		return (Wizard) getWindowAdd().getFellow("wizard");
	}

	public void applyAdd(Event event) throws CommitException {
		DataSource groupsDataSource = (DataSource) getListbox();

		DomainValueField input = (DomainValueField) getWindowAdd().getFellow("domainValues");
		List<String> domains = (List<String>) input.getValue();
		DataTable lb = (DataTable) getListbox();
		if ( currentRole.getDomain() !=  null && domains != null && ! domains.isEmpty()) {
			lb.delete();
			Long ruleId = (Long) XPathUtils.getValue(getListbox(), "@id");
			for (String domain: domains) {
				currentRoleAccount = new RuleAssignedRole();
				currentRoleAccount.setRoleId(currentRole.getId());
				currentRoleAccount.setRuleId(ruleId);
				currentRoleAccount.setDomainValue(domain);
				DataNodeCollection coll = (DataNodeCollection) XPathUtils.getValue(getListbox(), "/ruleAssignedRole");				
			}
		}
		groupsDataSource.commit();
		getWindowAdd().setVisible(false);
	}

	public void backAndRollback(Event ev) {
		DataTable dt = (DataTable) getListbox();
		dt.delete();
		getWizard().previous();
		if (currentRole.getDomain() == null) 
			getWizard().previous();
	}

	public void back(Event ev) {
		getWizard().previous();
	}

	@SuppressWarnings("unchecked")
	public void previewRules(Event e) throws InternalErrorException, NamingException, CreateException {
		com.soffid.iam.api.Rule r = (Rule) ((DataNode)XPathUtils.getValue(getForm(), "/.")).getInstance();
		List roles = new LinkedList();
		for (DataNode dn: (Collection<DataNode>) XPathUtils.getValue(getForm(),"/ruleAssignedRole"))
		{
			roles.add(dn.getInstance());
		}
		com.soffid.iam.service.ejb.RulesService svc = com.soffid.iam.EJBLocator.getRulesService();
		String file = svc.generateChangesReport(r, roles);
		Window previewWindow = (Window) getFellow("previewWindow");
		((FileDump)previewWindow.getFellow("previewDiv")).setSrc(file);
		previewWindow.doHighlighted();
	}
	
	public void applyRule(Event e) throws CommitException, InternalErrorException, NamingException, CreateException {
		closePreview(e);
		getModel().commit();
		com.soffid.iam.api.Rule r = (Rule) ((DataNode)XPathUtils.getValue(getForm(), "/.")).getInstance();
		EJBLocator.getRulesService().apply(r);
	}
	
	public void closePreview(Event e) {
		Window previewWindow = (Window) getFellow("previewWindow");
		previewWindow.setVisible(false);
	}
}