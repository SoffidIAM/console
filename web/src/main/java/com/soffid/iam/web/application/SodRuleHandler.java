package com.soffid.iam.web.application;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.zkoss.util.resource.Labels;
import org.zkoss.xel.fn.CommonFns;
import org.zkoss.zk.au.out.AuInvoke;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Window;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.MailDomain;
import com.soffid.iam.api.Role;
import com.soffid.iam.api.RuleAssignedRole;
import com.soffid.iam.api.SoDRole;
import com.soffid.iam.api.SoDRuleMatrix;
import com.soffid.iam.api.SodRuleType;
import com.soffid.iam.service.ejb.ApplicationService;
import com.soffid.iam.service.ejb.MailListsService;
import com.soffid.iam.web.component.CustomField3;
import com.soffid.iam.web.component.DomainValueField;
import com.soffid.iam.web.component.FrameHandler;
import com.soffid.iam.web.component.InputField3;
import com.soffid.iam.web.popup.CsvParser;
import com.soffid.iam.web.popup.ImportCsvHandler;

import es.caib.seycon.ng.comu.SoDRisk;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.component.DataTable;
import es.caib.zkib.component.Wizard;
import es.caib.zkib.datamodel.DataNode;
import es.caib.zkib.datamodel.DataNodeCollection;
import es.caib.zkib.datasource.CommitException;
import es.caib.zkib.datasource.DataSource;
import es.caib.zkib.datasource.XPathUtils;
import es.caib.zkib.zkiblaf.Missatgebox;

public class SodRuleHandler extends FrameHandler implements AfterCompose {
	private Role currentRole;
	private RuleAssignedRole currentRoleAccount;

	public SodRuleHandler() throws InternalErrorException {
		
	}

	public void onChangeForm(Event event) {
		DataTable lb = (DataTable) getListbox();
		if (lb.getSelectedIndex() >= 0) {
			displayNumber();
			displayMatrix();
		}
	}

	public void onChangeType(Event event ) {
		displayNumber();
		displayMatrix();
	}
	
	public void displayNumber() {
		SodRuleType type = (SodRuleType) XPathUtils.eval(getForm(), "type");
		getFellow("number").setVisible(type == SodRuleType.MATCH_SOME);
	}

	public void displayMatrix() {
		SodRuleType type2 = (SodRuleType) XPathUtils.eval(getForm(), "type");
		if (type2 == SodRuleType.MATCH_MATRIX) {
			createMatrix();
		} else {
			DataTable matrix = (DataTable) getFellow("matrix");
			matrix.setVisible(false);
		}
	}
	
	public void onChangeLevel(Event event) throws Exception {
		String[] data = (String[]) event.getData();
		long rownum = Integer.parseInt( data[0] );
		long colnum = Integer.parseInt( data[1] );
		DataNodeCollection roles = (DataNodeCollection) XPathUtils.eval(getForm(), "role");

		DataNodeCollection cells = (DataNodeCollection) XPathUtils.eval(getForm(), "cell");
		DataNode cell = getCell(rownum, colnum);
		SoDRuleMatrix m;
		if (cell == null) {
			cell =  (DataNode) cells.newInstance();
			m = (SoDRuleMatrix) cell.getInstance();
			m.setColumn(colnum);
			m.setRow(rownum);
			m.setRisk(SoDRisk.SOD_NA);
			m.setRuleId((Long) XPathUtils.eval(getForm(), "@id"));
		} else {
			m = (SoDRuleMatrix) cell.getInstance();
		}
		
		if (m.getRisk() == SoDRisk.SOD_NA)
			m.setRisk(SoDRisk.SOD_LOW);
		else if (m.getRisk() == SoDRisk.SOD_LOW)
			m.setRisk(SoDRisk.SOD_HIGH);
		else if (m.getRisk() == SoDRisk.SOD_HIGH)
			m.setRisk(SoDRisk.SOD_FORBIDDEN);
		else
			m.setRisk(SoDRisk.SOD_NA);
		
		cell.update();
		
		int tablerow = 0;
		for (int i = 0; i < roles.size(); i++) {
			DataNode dn = (DataNode) roles.get(i);
			if ( !dn.isDeleted()) {
				SoDRole r = (SoDRole) dn.getInstance();
				if (r.getId().longValue() == rownum) {
					JSONObject jsonRow = gernerateRow(roles, dn);
					DataTable dt = (DataTable) getFellow("matrix");
		            dt.response("update_"+tablerow, new AuInvoke(dt, "updateRow", Integer.toString(tablerow), jsonRow.toString()));
				}
				tablerow ++;
			}
		}
		
	}
	
	private void createMatrix() {
		DataTable matrix = (DataTable) getFellow("matrix");
		DataNodeCollection roles = (DataNodeCollection) XPathUtils.eval(getForm(), "role");
		
		JSONArray cols = new JSONArray();
		JSONObject col0 = new JSONObject();
		col0.put("name", Labels.getLabel("accounts.role"));
		col0.put("value", "roleName");
		col0.put("sort", false);
		col0.put("vertical", false);
		cols.put(col0);
		for (int i = 0; i < roles.size(); i++) {
			DataNode dn = (DataNode) roles.get(i);
			if ( !dn.isDeleted()) {
				JSONObject col = new JSONObject();
				SoDRole r = (SoDRole) dn.getInstance();
				col.put("name", r.getRole().getName());
				col.put("sort", false);
				col.put("vertical", true);
				col.put("className", "minicolumn");
				col.put("template", "<img #{r"+i+"_hidden} class='imageclic' title='#{r"+i+"_title}' onClick='zkDatatable.sendClientAction(this, \"onChangeLevel\", [#{row},"+r.getId()+"])' src='"+ getDesktop().getExecution().getContextPath()+ "/img/risk.#{r"+i+"}.svg'/>");
				cols.put(col);
			}
		}
		matrix.setColumns(cols.toString());
		
		JSONArray rows = new JSONArray();
		for (int i = 0; i < roles.size(); i++) {
			DataNode dn = (DataNode) roles.get(i);
			if ( !dn.isDeleted()) {
				JSONObject row = gernerateRow(roles, dn);
				rows.put(row);
			}
		}
		
		matrix.invalidate();
		matrix.setData(rows);
		matrix.setVisible(true);
	}

	public JSONObject gernerateRow(DataNodeCollection roles, DataNode dn) {
		JSONObject row = new JSONObject();
		SoDRole r = (SoDRole) dn.getInstance();
		row.put("roleName", r.getRole().getName());
		row.put("row", r.getId().toString());
		boolean skip = true;
		for (int j = 0; j < roles.size(); j++) {
			DataNode dn2 = (DataNode) roles.get(j);
			if ( !dn2.isDeleted()) {
				SoDRole r2 = (SoDRole) dn2.getInstance();
				if (r2.getId().equals(r.getId())) skip = false;					
				if (skip) {
					row.put("r"+j, "" );
					row.put("r"+j+"_hidden", "style='display:none'");
				} else {
					JSONObject col = new JSONObject();
					String level = getLevel ( r, r2 );
					row.put("r"+j, level );
					row.put("r"+j+"_hidden", "");
					row.put("r"+j+"_title",
						r.getRole().getName()+ 
						(r2.getId().equals(r.getId()) ? "" : " + "+r2.getRole().getName())+
						(SoDRisk.SOD_FORBIDDEN.getValue().endsWith(level) ? ": "+Labels.getLabel("es.caib.seycon.ng.comu.SoDRisk.SOD_FORBIDDEN") :
						SoDRisk.SOD_HIGH.getValue().endsWith(level) ? ": "+Labels.getLabel("es.caib.seycon.ng.comu.SoDRisk.SOD_HIGH") :
						SoDRisk.SOD_LOW.getValue().endsWith(level) ? ": "+Labels.getLabel("es.caib.seycon.ng.comu.SoDRisk.SOD_LOW"):
						""));
				}
			}
		}
		return row;
	}

	private String getLevel(SoDRole r, SoDRole r2) {
		DataNode cell = getCell(r.getId(), r2.getId());
		if (cell == null)
			return SoDRisk.SOD_NA.getValue();
		else
			return ((SoDRuleMatrix) cell.getInstance()).getRisk().toString();
	}

	private DataNode getCell(Long r, Long r2) {
		DataNodeCollection roles = (DataNodeCollection) XPathUtils.eval(getForm(), "cell");
		for (int i = 0; i < roles.getSize(); i++) {
			DataNode dn = (DataNode) roles.get(i);
			if (!dn.isDeleted()) {
				SoDRuleMatrix cell = (SoDRuleMatrix) dn.getInstance();
				if (cell.getRow().equals(r) && cell.getColumn().equals(r2) ||
						cell.getRow().equals(r2) && cell.getColumn().equals(r))
					return dn;
			}
		}
		return null;
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
		HttpServletRequest req = (HttpServletRequest) Executions.getCurrent().getNativeRequest();
		if ("add".equals(req.getParameter("wizard"))) {
			try {
				addNew();
			} catch (Exception e) {
				throw new UiException(e);
			}
		}
	}
	
	public void addNewRole(Event event) throws CommitException {
		applyNoClose(event);
		Window w = (Window) getFellow("add-window");
		getWizard().setSelected(0);
		w.doHighlighted();
		CustomField3 role = (CustomField3) w.getFellow("role");
		role.setValue(null);
	}
	
	public Window getWindowModify() {
		return (Window) getFellow("modify-window");
	}
	
	public void undoAdd(Event ev) {
		Window w = getWindowAdd();
		w.setVisible(false);
	}
	
	public void selectDomain(Event ev) throws Exception {
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

	public void applyAdd(Event event) throws Exception {
		DataSource groupsDataSource = (DataSource) getListbox();
		
		ApplicationService appSvc = EJBLocator.getApplicationService();
		Long ruleId = (Long) groupsDataSource.getJXPathContext().getValue("id");
		List<String> roles = (List<String>) ((CustomField3) getWindowAdd().getFellow("role")).getValue();
		for ( String role: roles) {
			SoDRole sodRole = new SoDRole();
			sodRole.setRuleId(ruleId);
			Role r = appSvc.findRoleByShortName(role);
			if (r == null)
				throw new UiException("Cannot find role "+role);
			sodRole.setRole(r);
			XPathUtils.createPath(groupsDataSource, "/role", sodRole);
		}
		
//		groupsDataSource.commit();
		getWindowAdd().setVisible(false);
		applyNoClose(event);
		displayMatrix();
	}

	public void deleteRole(Event ev) {
		DataTable lb = (DataTable) getFellow("roles");
		lb.deleteSelectedItem();
		displayMatrix();
	}
	
	public void backAndRollback(Event ev) {
		DataTable dt = (DataTable) getListbox();
		dt.delete();
		getWizard().previous();
		if (currentRole.getDomain() == null) 
			getWizard().previous();
	}

	
	public void closePreview(Event e) {
		Window previewWindow = (Window) getFellow("previewWindow");
		previewWindow.setVisible(false);
	}
}
