package com.soffid.iam.web.host;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

import org.zkoss.util.resource.Labels;
import org.zkoss.xel.fn.CommonFns;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Event;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.Network;
import com.soffid.iam.service.ejb.NetworkService;
import com.soffid.iam.web.component.CustomField3;
import com.soffid.iam.web.component.DynamicColumnsDatatable;
import com.soffid.iam.web.component.FrameHandler;
import com.soffid.iam.web.component.ObjectAttributesDiv;
import com.soffid.iam.web.component.SearchBox;
import com.soffid.iam.web.popup.CsvParser;
import com.soffid.iam.web.popup.ImportCsvHandler;
import com.soffid.iam.web.popup.SelectColumnsHandler;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.datasource.CommitException;
import es.caib.zkib.datasource.DataSource;
import es.caib.zkib.datasource.XPathUtils;
import es.caib.zkib.jxpath.JXPathNotFoundException;
import es.caib.zkib.zkiblaf.Missatgebox;

public class HostHandler extends FrameHandler {
	public HostHandler() throws InternalErrorException {
	}

	@Override
	public void setPage(Page p) {
		super.setPage(p);
	}
		

	public void onChangeForm(Event ev) throws Exception {
		super.onChangeForm(ev);
		Long level = null;
		Long id = null;
		try {
			id = (Long) XPathUtils.getValue(getForm(), "id");
			String hostName = (String) XPathUtils.getValue(getForm(), "name");
			String network = (String) XPathUtils.getValue(getForm(), "networkCode");
			level = EJBLocator.getNetworkService().findAccessLevelByHostNameAndNetworkName(hostName, network);	
		} catch (Exception e) {
			
		}
		boolean isAdmin = id == null || (level != null && level.intValue() == 2);
		boolean isSupport = id == null || (level != null && level.intValue() >= 1);
		getFellow("deleteHost").setVisible(isAdmin);
		getFellow("assignIp").setVisible(isAdmin);
		getFellow("getPassword").setVisible(isAdmin || isSupport);
		ObjectAttributesDiv attributes = (ObjectAttributesDiv) getFellow("attributes");
		attributes.setReadonly(!isAdmin);
		attributes.refresh();
	}
	
	public void importCsv () throws IOException, CommitException {
		getModel().commit();
		
		String[][] data = { 
				{"name", Labels.getLabel("com.soffid.iam.api.Network.name")},
				{"description", Labels.getLabel("com.soffid.iam.api.Network.description")},
				{"ip", Labels.getLabel("com.soffid.iam.api.Network.ip")},
				{"mask", Labels.getLabel("com.soffid.iam.api.Network.mask")},
				{"lanAccess", Labels.getLabel("com.soffid.iam.api.Network.lanAccess")},
				{"dhcp", Labels.getLabel("com.soffid.iam.api.Network.dhcp")},
				{"dhcpSupport", Labels.getLabel("com.soffid.iam.api.Network.dhcpSupport")}
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
			NetworkService svc = EJBLocator.getNetworkService();
			Map<String, Network> current = new HashMap<String, Network>();
			for (Network ut: svc.findNetworkByTextAndJsonQuery(null, null, null, null).getResources())
				current.put(ut.getName(), ut);
			
			for ( Iterator<Map<String, String>> iterator = parser.iterator(); iterator.hasNext(); )
			{
				m = iterator.next();
				String name = m.get("name");
				String description = m.get("description");
				String roleHolder = m.get("roleHolder");

				if (name != null && !name.trim().isEmpty())
				{
					Network ut = current.get(name);
					if (ut != null)
					{
						if (m.containsKey("description"))
							ut.setDescription(description);
						if (m.containsKey("ip"))
							ut.setIp(m.get("ip"));
						if (m.containsKey("mask"))
							ut.setMask(m.get("mask"));
						if (m.containsKey("dhcp"))
							ut.setDhcp(m.get("dhcp"));
						if (m.containsKey("dhcpSupport"))
							ut.setDhcpSupport("true".equalsIgnoreCase(m.get("dhcpSupport")) ||
									yes.equalsIgnoreCase(m.get("dhcpSupport")));
						if (m.containsKey("lanAccess"))
							ut.setLanAccess("true".equalsIgnoreCase(m.get("lanAccess")) ||
									yes.equalsIgnoreCase(m.get("lanAccess")));
						svc.update(ut);
						updates ++;
					} else {
						inserts ++;
						ut = new Network();
						ut.setName(name);
						ut.setDescription(description);
						if (m.containsKey("ip"))
							ut.setIp(m.get("ip"));
						if (m.containsKey("mask"))
							ut.setMask(m.get("mask"));
						if (m.containsKey("dhcp"))
							ut.setDhcp(m.get("dhcp"));
						if (m.containsKey("dhcpSupport"))
							ut.setDhcpSupport("true".equalsIgnoreCase(m.get("dhcpSupport")) ||
									yes.equalsIgnoreCase(m.get("dhcpSupport")));
						if (m.containsKey("lanAccess"))
							ut.setLanAccess("true".equalsIgnoreCase(m.get("lanAccess")) ||
									yes.equalsIgnoreCase(m.get("lanAccess")));
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

	
	public void getAdminPassword(Event event) throws InternalErrorException, NamingException, CreateException {
		NetworkService svc = EJBLocator.getNetworkService();
		
		String hostName = (String) XPathUtils.getValue((DataSource)getListbox(), "name");

		String[] dadesAdmin = svc.getHostAdminUserAndPassword( hostName );
		if (dadesAdmin!=null && dadesAdmin.length==3 && dadesAdmin[1] != null) {
			Missatgebox.info (String.format(org.zkoss.util.resource.Labels.getLabel("maquines.DadesUsuari"), 
					new Object [] {dadesAdmin[0], dadesAdmin[1],dadesAdmin[2]}),
				org.zkoss.util.resource.Labels.getLabel("maquines.InformacioAdministracio"));
		} else {
			Missatgebox.info(Labels.getLabel("hosts.passwordNotAvailable"));
		}
		
	}
}
