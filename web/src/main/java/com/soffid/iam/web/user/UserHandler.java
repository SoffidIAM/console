package com.soffid.iam.web.user;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Map;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

import org.joda.time.format.ISODateTimeFormat;
import org.json.JSONArray;
import org.json.JSONObject;
import org.zkoss.util.TimeZones;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Button;
import org.zkoss.zul.Window;

import java.text.SimpleDateFormat;
import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.Configuration;
import com.soffid.iam.api.SyncAgentTaskLog;
import com.soffid.iam.service.ejb.ApplicationService;
import com.soffid.iam.service.ejb.AuthorizationService;
import com.soffid.iam.service.ejb.ConfigurationService;
import com.soffid.iam.utils.Security;
import com.soffid.iam.web.component.FrameHandler;
import com.soffid.iam.web.component.ObjectAttributesDiv;
import com.soffid.iam.web.component.SearchBox;
import com.soffid.iam.web.popup.CsvParser;
import com.soffid.iam.web.popup.ImportCsvHandler;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.component.DataTable;
import es.caib.zkib.datasource.CommitException;
import es.caib.zkib.datasource.XPathUtils;
import es.caib.zkib.zkiblaf.Missatgebox;

public class UserHandler extends FrameHandler {
	private boolean isMaster;
	private boolean canCreateUser;
	private boolean canUpdateUser;
	private boolean canDeleteUser;
	private boolean canQueryUser;
	private ConfigurationService configSvc;

	public UserHandler() throws InternalErrorException {
		com.soffid.iam.api.Tenant masterTenant = com.soffid.iam.ServiceLocator.instance().getTenantService().getMasterTenant();

		isMaster = com.soffid.iam.utils.Security.getCurrentTenantName().equals ( masterTenant.getName() );
		canCreateUser = Security.isUserInRole("user:create");
		canUpdateUser = Security.isUserInRole("user:update");
		canDeleteUser = Security.isUserInRole("user:delete");
		canQueryUser = Security.isUserInRole("user:query");;
	}

	@Override
	public void setPage(Page p) {
		super.setPage(p);
		getNamespace().setVariable("isMaster", isMaster, true);
		getNamespace().setVariable("canCreateUser", canCreateUser, true);
		getNamespace().setVariable("canUpdateUser", canUpdateUser, true);
		getNamespace().setVariable("canDeleteUser", canDeleteUser, true);
		getNamespace().setVariable("canQueryUser", canQueryUser, true);
		getNamespace().setVariable("canQueryUserRole", Security.isUserInRole("user:role:query"), true);
	}
		

	public void onChangeDades() {
		updateStatus();
	}
	
	public void importCsv () throws IOException, CommitException, InternalErrorException, NamingException, CreateException {
		new UserImporter().importCsv(this);
	}
	
	public void bulkAction(Event event) throws IOException, CommitException, InternalErrorException, NamingException, CreateException {
		DataTable listbox = (DataTable) getListbox();
		if (listbox.getSelectedIndexes() != null && listbox.getSelectedIndexes().length > 0) {
			UserBulkAction ba = new UserBulkAction( ); 
			ba.start(listbox ) ;
		}
	}

	
	public void mergeAction(Event event) throws IOException, CommitException, InternalErrorException, NamingException, CreateException, WrongValueException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		DataTable listbox = (DataTable) getListbox();
		if (listbox.getSelectedIndexes() != null && listbox.getSelectedIndexes().length == 2) {
			new UserMergeAction().start((DataTable) getListbox());
		} else {
			Missatgebox.avis(Labels.getLabel("merge.warning"));
		}
	}

	private void importCsv(CsvParser parser) {
		Map<String,String> m = null;
		int updates = 0;
		int inserts = 0;
		int unchanged = 0;
		int removed = 0;
		try {
			configSvc = EJBLocator.getConfigurationService();
			for ( Iterator<Map<String, String>> iterator = parser.iterator(); iterator.hasNext(); )
			{
				m = iterator.next();
				String name = m.get("name");
				String network = m.get("networkName");
				String description = m.get("description");
				String value = m.get("value");
				if (network != null && network.isEmpty()) network = null;

				if (name != null && !name.trim().isEmpty() && m.containsKey("value"))
				{
					Configuration cfg = configSvc.findParameterByNameAndNetworkName(name, network);
					if (cfg != null)
					{
						if (value == null) {
							configSvc.delete(cfg);
							removed ++;
						}
						else if (cfg.getValue() != null && cfg.getValue().equals(value))
						{
							unchanged ++;
						} else {
							cfg.setValue(value);
							if (m.containsKey("description"))
								cfg.setDescription(description);
							configSvc.update(cfg);
							updates ++;
						}
					} else if (value != null) {
						inserts ++;
						cfg = new Configuration();
						cfg.setValue(value);
						cfg.setDescription(description);
						cfg.setName(name);
						cfg.setNetworkCode(network);
						configSvc.create(cfg);
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
	
	public void addNew() throws Exception {
		super.addNew();
	}

	@Override
	public void afterCompose() {
		super.afterCompose();
		HttpServletRequest req = (HttpServletRequest) Executions.getCurrent().getNativeRequest();
		String user = req.getParameter("userName");
		if (user != null) {
			SearchBox sb = (SearchBox) getFellow("searchBox");
			sb.setBasicMode();
			sb.addAttribute("userName").setSearchFilter(user);
			sb.search();
		}
	}
	
	public void updateStatus() {
		Button b = (Button) getFellow("pendingTasksButton");
		try {
			Long id = (Long) XPathUtils.eval(getForm(), "id");
			if (id == null)
				b.setVisible(false);
			else {
				String userName = (String) XPathUtils.getValue(getForm(), "userName");
				int i = EJBLocator.getUserService().isUpdatePendingExtended(userName);
				if ( i == 0 ) b.setVisible( false );
				else {
					b.setVisible(true);
					b.setImage(i == 1 ? "/img/held.svg" :
						i == 2 ? "/img/sync.svg" :
							"/img/warning.svg");
				}
			}
		} catch (Exception e) {
			b.setVisible(false);
		}
	}

	@Override
	public boolean applyNoClose(Event event) throws CommitException {
		boolean b = super.applyNoClose(event);
		updateStatus();
		return b;
	}
	
	public void viewTasks (Event event) throws InternalErrorException, NamingException, CreateException {
		String userName = (String) XPathUtils.getValue(getForm(), "userName");

		Window w = (Window) getFellow("tasksWindow");
		DataTable dt = (DataTable) w.getFellow("tasks");
		JSONArray array = new JSONArray();
		for (SyncAgentTaskLog tl: EJBLocator.getUserService().getActiveTasks(userName)) {
			JSONObject j = dt.wrap(tl);
			array.put(j);
		}
		dt.setData(array);
		w.doHighlighted();
	}

	public void closeTaskWindow(Event event) {
		Window w = (Window) getFellow("tasksWindow");
		w.setVisible(false);
	}
	
	public void audit (Event ev) {
		String user = (String) XPathUtils.eval(getForm(), "userName");
		Execution exec = Executions.getCurrent();
		Calendar c = Calendar.getInstance();
		c.setTimeZone(TimeZones.getCurrent());
		c.add(Calendar.DAY_OF_MONTH, -1);
		c.set(Calendar.HOUR, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		c.set(Calendar.AM_PM, Calendar.AM);
		String d = ISODateTimeFormat.dateTime().print(c.getTimeInMillis()); 
		exec.sendRedirect("/monitor/audit.zul?filter=user eq \""+ user.replace("\\", "\\\\").replace("\"","\\\"")+ "\" and calendar gt \""+d+"\"",
				"_blank");
	}

	public void accessLog (Event ev) {
		String user = (String) XPathUtils.eval(getForm(), "userName");
		Execution exec = Executions.getCurrent();
		Calendar c = Calendar.getInstance();
		c.setTimeZone(TimeZones.getCurrent());
		c.add(Calendar.DAY_OF_MONTH, -1);
		c.set(Calendar.HOUR, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		c.set(Calendar.AM_PM, Calendar.AM);
		String d = ISODateTimeFormat.dateTime().print(c.getTimeInMillis()); 
		exec.sendRedirect("/monitor/access.zul?filter=userName eq \""+ user.replace("\\", "\\\\").replace("\"","\\\"")+ "\" and startDate gt \""+d+"\"",
				"_blank");
	}
	
	public void refresh(Event ev) throws InternalErrorException, NamingException, CreateException {
		String user = (String) XPathUtils.eval(getForm(), "userName");
		EJBLocator.getUserService().refreshChanges(user);
		
	}
}
