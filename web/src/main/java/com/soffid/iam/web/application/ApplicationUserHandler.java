package com.soffid.iam.web.application;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Div;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.Configuration;
import com.soffid.iam.api.DomainValue;
import com.soffid.iam.api.RoleAccount;
import com.soffid.iam.service.ejb.ApplicationService;
import com.soffid.iam.web.popup.CsvParser;
import com.soffid.iam.web.popup.ImportCsvHandler;

import es.caib.zkib.binder.SingletonBinder;
import es.caib.zkib.component.DataTable;
import es.caib.zkib.component.DateFormats;
import es.caib.zkib.datamodel.DataModelCollection;
import es.caib.zkib.datasource.CommitException;
import es.caib.zkib.datasource.XPathUtils;
import es.caib.zkib.zkiblaf.Missatgebox;


public class ApplicationUserHandler extends Div implements AfterCompose {
	private static final long serialVersionUID = 1L;
	private String listboxPath;
	
	public ApplicationUserHandler() throws NamingException, CreateException {
	}
	
	public void afterCompose() {
	}
	
	
	public void downloadCsv(Event event) {
		getListbox().download();
	}

	private DataTable getListbox() {
		return (DataTable) getFellow("listbox");
	}

	
	public String getListboxPath() {
		return listboxPath;
	}

	
	public void setListboxPath(String listboxPath) {
		this.listboxPath = listboxPath;
	}


	public void importCsv () throws IOException, CommitException {
		getModel().commit();
		
		String[][] data = { 
				{"name", Labels.getLabel("parametres.zul.Parametre-2")},
				{"networkName", Labels.getLabel("parametres.zul.Xarxa-2")},
				{"value", Labels.getLabel("parametres.zul.Valor-2")},
				{"description", Labels.getLabel("parametres.zul.Descripcia-2")}
		};
		
		String title = Labels.getLabel("tenant.zul.import");
		ImportCsvHandler.startWizard(title, data, this, 
				parser -> importCsv(parser));
	}

	private DataTable getModel() {
		return (DataTable) getFellow("listbox");
	}

	private void importCsv(CsvParser parser) {
		Map<String,String> m = null;
		int updates = 0;
		int inserts = 0;
		int unchanged = 0;
		int removed = 0;
		try {
			ApplicationService appSvc = EJBLocator.getApplicationService();
			for ( Iterator<Map<String, String>> iterator = parser.iterator(); iterator.hasNext(); )
			{
				m = iterator.next();
				String name = m.get("accountName");
				String accountId = m.get("accountId");
				String accountSystem = m.get("accountSystem");
				String domainValue = m.get("domainValue");
				String startDate = m.get("startDate");
				String endDate = m.get("endDate");
				String user = m.get("user");
				String roleName = m.get("roleName");
				String roleSystem = m.get("roleSystem");
				
				RoleAccount ra = new RoleAccount();
				ra.setAccountName(name == null || name.trim().isEmpty() ? null: name);
				ra.setAccountId(accountId == null || accountId.trim().isEmpty() ? null: Long.parseLong(accountId));
				ra.setAccountSystem(accountSystem == null || accountSystem.trim().isEmpty() ? null: accountSystem);
				if (domainValue != null && ! domainValue.trim().isEmpty()) {
					ra.setDomainValue(new DomainValue());
					ra.getDomainValue().setValue(domainValue);
				}
				ra.setStartDate(startDate == null || startDate.trim().isEmpty()? new Date(): DateFormats.getDateFormat().parse(startDate));
				ra.setEndDate(endDate == null || endDate.trim().isEmpty()? new Date(): DateFormats.getDateFormat().parse(endDate));
				
				appSvc.create(ra);
				inserts ++;
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

		SingletonBinder b = new SingletonBinder(this);
		b.setDataPath(listboxPath);
		DataModelCollection coll = (DataModelCollection) b.getValue();
		try {
			coll.refresh();
		} catch (Exception e) {
		}
		b.setParent(null);
	}
}