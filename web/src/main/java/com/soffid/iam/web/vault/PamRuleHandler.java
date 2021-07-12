package com.soffid.iam.web.vault;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

import org.zkoss.util.resource.Labels;
import org.zkoss.xel.fn.CommonFns;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Event;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.Network;
import com.soffid.iam.api.PagedResult;
import com.soffid.iam.api.PamPolicy;
import com.soffid.iam.api.PamRule;
import com.soffid.iam.api.PamRuleType;
import com.soffid.iam.service.ejb.NetworkService;
import com.soffid.iam.service.ejb.PamPolicyService;
import com.soffid.iam.web.component.CustomField3;
import com.soffid.iam.web.component.DynamicColumnsDatatable;
import com.soffid.iam.web.component.FrameHandler;
import com.soffid.iam.web.component.SearchBox;
import com.soffid.iam.web.popup.CsvParser;
import com.soffid.iam.web.popup.ImportCsvHandler;
import com.soffid.iam.web.popup.SelectColumnsHandler;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.datasource.CommitException;
import es.caib.zkib.datasource.XPathUtils;
import es.caib.zkib.jxpath.JXPathNotFoundException;
import es.caib.zkib.zkiblaf.Missatgebox;

public class PamRuleHandler extends FrameHandler {
	public PamRuleHandler() throws InternalErrorException {
	}

	@Override
	public void setPage(Page p) {
		super.setPage(p);
	}
		

	public void onChangeDades() throws InternalErrorException, NamingException, CreateException {
	}
	
	public void importCsv () throws IOException, CommitException {
		getModel().commit();
		
		String[][] data = { 
				{"name", Labels.getLabel("com.soffid.iam.api.PamRule.name")},
				{"description", Labels.getLabel("com.soffid.iam.api.PamRule.description")},
				{"type", Labels.getLabel("com.soffid.iam.api.PamRule.type")},
				{"content", Labels.getLabel("com.soffid.iam.api.PamRule.content")}
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
		try {
			PamPolicyService svc = EJBLocator.getPamPolicyService();
			
			for ( Iterator<Map<String, String>> iterator = parser.iterator(); iterator.hasNext(); )
			{
				m = iterator.next();
				String name = m.get("name");

				if (name != null && !name.trim().isEmpty())
				{
					PagedResult<PamRule> l = svc.findRuleByJsonQuery(null, "name eq \""+quote(name)+"\"", null, null);
					PamRule current = l.getResources().isEmpty() ? null: l.getResources().get(0);
					boolean add = current == null;
					if (add)
						current = new PamRule();
					if (m.containsKey("description"))
						current.setDescription(m.get("description"));
					if (m.containsKey("type"))
						current.setType( PamRuleType.fromString(m.get("type")));
					if (m.containsKey("content"))
						current.setContent(m.get("content"));
					if (current == null)
					{
						current.setName(name);
						svc.createRule(current);
						inserts ++;
					}
					else
					{
						svc.updateRule(current);
						updates ++;
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
	
	private String quote(String name) {
		return name.replace("\\", "\\\\").replace("'", "\\'").replace("\"", "\\\"");
	}

	public void changeColumns(Event event) throws IOException {
		SelectColumnsHandler.startWizard((DynamicColumnsDatatable) getListbox());
	}
	
	public void addNew() throws Exception {
		super.addNew();
	}

	public boolean applyNoClose(Event event) throws CommitException {
		return super.applyNoClose(event);
	}

	@Override
	public void afterCompose() {
		super.afterCompose();
		HttpServletRequest req = (HttpServletRequest) Executions.getCurrent().getNativeRequest();
		String user = req.getParameter("name");
		if (user != null) {
			SearchBox sb = (SearchBox) getFellow("searchBox");
			sb.addAttribute("name").setSearchFilter(user);
			sb.search();
		}
	}
}
