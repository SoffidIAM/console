package com.soffid.iam.web.printer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
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

import com.google.common.collect.Lists;
import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.Network;
import com.soffid.iam.api.Printer;
import com.soffid.iam.service.ejb.NetworkService;
import com.soffid.iam.service.ejb.PrinterService;
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

public class PrinterHandler extends FrameHandler {
	public PrinterHandler() throws InternalErrorException {
	}

	@Override
	public void setPage(Page p) {
		super.setPage(p);
	}
		

	public void onChangeForm(Event ev) throws Exception  {
		super.onChangeForm(ev);
	}
	
	public void importCsv () throws IOException, CommitException {
		getModel().commit();
		
		  String[][] data = { 
				{"name", Labels.getLabel("com.soffid.iam.api.Printer.name")},
				{"description", Labels.getLabel("com.soffid.iam.api.Printer.description")},
				{"model", Labels.getLabel("com.soffid.iam.api.Printer.model")},
				{"hostName", Labels.getLabel("com.soffid.iam.api.Printer.hostName")},
				{"local", Labels.getLabel("com.soffid.iam.api.Printer.local")},
				{"users", Labels.getLabel("impressores.zul.Usuaris")},
				{"groups", Labels.getLabel("com.soffid.iam.api.System.groups")}
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
			PrinterService svc = EJBLocator.getPrinterService();
			Map<String, Printer> current = new HashMap<String, Printer>();
			for (Printer ut: svc.getPrinters())
				current.put(ut.getName(), ut);
			
			for ( Iterator<Map<String, String>> iterator = parser.iterator(); iterator.hasNext(); )
			{
				m = iterator.next();
				String name = m.get("name");
				if (name != null && !name.trim().isEmpty())
				{
					Printer ut = current.get(name);
					if (ut != null)
					{
						if (m.containsKey("description"))
							ut.setDescription(m.get("description"));
						if (m.containsKey("model"))
							ut.setModel(m.get("model"));
						if (m.containsKey("hostName"))
							ut.setHostName(m.get("hostName"));
						if (m.containsKey("local"))
							ut.setLocal(yes.equals(m.get("local")));
						if (m.containsKey("users")) {
							ut.setUsers(new LinkedList<String>());
							if (m.get("users") != null)
								for (String u: m.get("users").split("[ ,]+") )
									ut.getUsers().add(u);
						}
						if (m.containsKey("groups")) {
							ut.setGroups(new LinkedList<String>());
							if (m.get("groups") != null)
								for (String u: m.get("groups").split("[ ,]+") )
									ut.getGroups().add(u);
						}
						svc.update(ut);
						updates ++;
					} else {
						inserts ++;
						ut = new Printer();
						ut.setName(name);
						if (m.containsKey("description"))
							ut.setDescription(m.get("description"));
						if (m.containsKey("model"))
							ut.setModel(m.get("model"));
						if (m.containsKey("local"))
							ut.setLocal(yes.equals(m.get("local")));
						if (m.containsKey("hostName"))
							ut.setHostName(m.get("hostName"));
						if (m.containsKey("users")) {
							ut.setUsers(new LinkedList<String>());
							if (m.get("users") != null)
								for (String u: m.get("users").split("[ ,]+") )
									ut.getUsers().add(u);
						}
						if (m.containsKey("groups")) {
							ut.setGroups(new LinkedList<String>());
							if (m.get("groups") != null)
								for (String u: m.get("groups").split("[ ,]+") )
									ut.getGroups().add(u);
						}
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
		String user = req.getParameter("userName");
		if (user != null) {
			SearchBox sb = (SearchBox) getFellow("searchBox");
			sb.addAttribute("userName").setSearchFilter(user);
			sb.search();
		}
	}
}
