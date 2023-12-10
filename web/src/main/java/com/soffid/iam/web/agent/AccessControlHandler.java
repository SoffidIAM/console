package com.soffid.iam.web.agent;

import java.io.IOException;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.util.resource.Labels;
import org.zkoss.xel.fn.CommonFns;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.HtmlBasedComponent;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Window;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.AccessControl;
import com.soffid.iam.api.Role;
import com.soffid.iam.service.ejb.ApplicationService;
import com.soffid.iam.service.ejb.DispatcherService;
import com.soffid.iam.utils.Security;
import com.soffid.iam.web.component.CustomField3;
import com.soffid.iam.web.popup.CsvParser;
import com.soffid.iam.web.popup.ImportCsvHandler;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.component.DataModel;
import es.caib.zkib.component.DataTable;
import es.caib.zkib.component.Div;
import es.caib.zkib.datamodel.DataNode;
import es.caib.zkib.datamodel.DataNodeCollection;
import es.caib.zkib.datasource.CommitException;
import es.caib.zkib.datasource.DataSource;
import es.caib.zkib.datasource.XPathUtils;
import es.caib.zkib.zkiblaf.Missatgebox;

public class AccessControlHandler extends Div implements AfterCompose {
	private DataModel model;
	private DataTable gridControlAccess;
	private AccessControl currentAccessControl;

	public void afterCompose() {
		model = (DataModel) getFellow("model");
		gridControlAccess = (DataTable) getFellow("gridControlAccess");
	}
	
	int generaRandomNegatiu() {
	 	java.util.Random randomGenerator = new java.util.Random();
		    int randomInt = randomGenerator.nextInt(100000);
		    return -randomInt; //negatiu
	}

	public void addNew() {
		DataTable gridControlAccess = (DataTable) getFellow("gridControlAccess");
		final DataSource dataSource = gridControlAccess.getDataSource();
		String agent = (String) XPathUtils.eval(dataSource, "name");

		Window w = (Window) getFellow("accessControlWindow");
		((Radiogroup)w.getFellow("type")).setSelectedIndex(2);
		((CustomField3)w.getFellow("user")).setText("");
		((CustomField3)w.getFellow("role")).setText("");
		((CustomField3)w.getFellow("role")).setFilterExpression("system eq \""+agent.replace("\\", "\\\\").replace("\"", "\\\"")+"\"");
		((CustomField3)w.getFellow("host")).setText("%");
		((CustomField3)w.getFellow("user")).setVisible(true);
		((CustomField3)w.getFellow("role")).setVisible(false);
		((CustomField3)w.getFellow("app")).setText("%");
		((CustomField3)w.getFellow("comments")).setText("");
		
		currentAccessControl = null;
		
		w.doHighlighted();
	}
	
	public void activaRadio(Event ev) {
		Radio r = (Radio) ev.getTarget();
		final Radiogroup radiogroup = r.getRadiogroup();
		int selected = radiogroup.getSelectedIndex();
		((CustomField3)radiogroup.getFellow("user")).setVisible(selected == 0);
		((CustomField3)radiogroup.getFellow("role")).setVisible(selected == 1);
	}

	public void undoAdd(Event ev) {
		Window w = (Window) getFellow("accessControlWindow");
		w.setVisible(false);
	}
	
	public void applyAdd(Event ev) throws Exception {
		Window w = (Window) getFellow("accessControlWindow");
		int selected = ((Radiogroup)w.getFellow("type")).getSelectedIndex();
		com.soffid.iam.api.AccessControl acc;
		if (currentAccessControl == null)
			acc = new com.soffid.iam.api.AccessControl();
		else
			acc = currentAccessControl;
		
		CustomField3 f;
		if (selected == 0) {
			f = ((CustomField3)w.getFellow("user"));
			if (! f.attributeValidateAll()) return;	
			acc.setGenericUser((String) f.getValue());
			acc.setRoleId(null);
			acc.setRoleDescription(null);
		}
		else if (selected == 1) {
			f = ((CustomField3)w.getFellow("role"));
			if (! f.attributeValidateAll()) return;
			Role roleName = (Role) f.getValueObject();
			acc.setRoleId(roleName.getId());
			acc.setRoleDescription(roleName.getDescription());
			acc.setGenericUser(null);
		}
		else {
			acc.setGenericUser("%");
			acc.setRoleDescription(null);
			acc.setRoleId(null);
		}
		f = ((CustomField3)w.getFellow("host"));
		if (! f.attributeValidateAll()) return;	
		acc.setGenericHost((String) f.getValue());
		acc.setHostName((String)f.getValue());
		
		f = ((CustomField3)w.getFellow("app"));
		if (! f.attributeValidateAll()) return;	
		acc.setProgram((String) f.getValue());
		
		f = ((CustomField3)w.getFellow("comments"));
		acc.setComments((String) f.getValue());

		DataTable gridControlAccess = (DataTable) getFellow("gridControlAccess");
		final DataSource dataSource = gridControlAccess.getDataSource();
		Long id = (Long) XPathUtils.eval(dataSource, "id");
		acc.setAgentId(id);
		acc.setAgentName((String) XPathUtils.eval(dataSource, "name"));
		updateAgentTimestamp();
		if (currentAccessControl == null) {
			String path = XPathUtils.createPath(dataSource, "controlAcces", acc);
			try {
				gridControlAccess.commit();
			} catch (Exception e) {
				XPathUtils.removePath(dataSource, path);
				throw e;
			}
		} else {
			DataNode dn = (DataNode) gridControlAccess.getJXPathContext().getValue("/");
			dn.update();
			gridControlAccess.commit();
		}
		w.setVisible(false);
	}

	public void editRule(Event event) throws InternalErrorException, NamingException, CreateException {
		if ( Security.isUserInRole(Security.AUTO_AGENT_ACCESSCONTROL_UPDATE)) {
			DataSource ds = gridControlAccess;
			
			currentAccessControl = (AccessControl) ds.getJXPathContext().getValue("instance");
			
			String agent = (String) XPathUtils.eval(gridControlAccess.getDataSource(), "name");

			Window w = (Window) getFellow("accessControlWindow");
			final int option = "%".equals(currentAccessControl.getGenericUser()) ? 2:
				currentAccessControl.getRoleId() == null ? 0: 1;
			((Radiogroup)w.getFellow("type")).setSelectedIndex( option );
			((CustomField3)w.getFellow("user")).setText(option == 0 ? currentAccessControl.getGenericUser(): null);
			((CustomField3)w.getFellow("role")).setFilterExpression("system eq \""+agent.replace("\\", "\\\\").replace("\"", "\\\"")+"\"");
			if (currentAccessControl.getRoleId() == null) {
				((CustomField3)w.getFellow("role")).setText("");
			} else {
				Role role = EJBLocator.getApplicationService().findRoleById(currentAccessControl.getRoleId());
				((CustomField3)w.getFellow("role")).setText(role.getName()+"@"+role.getSystem());
			}
			((CustomField3)w.getFellow("host")).setText(currentAccessControl.getGenericHost());
			((CustomField3)w.getFellow("user")).setVisible(option == 0);
			((CustomField3)w.getFellow("role")).setVisible(option == 1);
			((CustomField3)w.getFellow("app")).setText(currentAccessControl.getProgram());
			((CustomField3)w.getFellow("comments")).setText(currentAccessControl.getComments());
			w.doHighlighted();
		}
		
		
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
	
	public void exportCsv(Event event) {
		gridControlAccess.download();
	}
	
	
	public void importCsv () throws IOException, CommitException {
		gridControlAccess.commit();
		
		String[][] data = { 
				{"host", Labels.getLabel("agents.zul.Maquina/IP")},
				{"user", Labels.getLabel("agents.zul.Usuari")},
				{"role", Labels.getLabel("agents.zul.Rol")},
				{"app", Labels.getLabel("agents.zul.Programa")},
				{"comments", Labels.getLabel("com.soffid.iam.api.User.comments")},
		};
		
		String title = Labels.getLabel("tenant.zul.import");
		ImportCsvHandler.startWizard(title, data, this, 
				parser -> importCsv(parser));
	}
	
	protected void updateAgentTimestamp() {
		DataTable gridControlAccess = (DataTable) getFellow("gridControlAccess");
		final DataSource dataSource = gridControlAccess.getDataSource();
		XPathUtils.setValue(dataSource, "timeStamp", Calendar.getInstance());
	}

	private void importCsv(CsvParser parser) {
		Map<String,String> m = null;
		int updates = 0;
		int inserts = 0;
		int unchanged = 0;
		int removed = 0;
		String yes = CommonFns.getLabel("mesg:org.zkoss.zul.mesg.MZul:YES");
		try {
			String agent = (String) XPathUtils.eval(gridControlAccess.getDataSource(), "name");

			DispatcherService svc = EJBLocator.getDispatcherService();
			ApplicationService appSvc = EJBLocator.getApplicationService();
			LinkedList<AccessControl> current = new LinkedList<AccessControl>();
			for (AccessControl ut: svc.findAccessControlByDispatcherName(agent))
				current.add(ut);
			
			for ( Iterator<Map<String, String>> iterator = parser.iterator(); iterator.hasNext(); )
			{
				m = iterator.next();
				String app = m.get("app");
				String role = m.get("role");
				String user = m.get("user");
				String host = m.get("host");
				String comments = m.get("comments");

				if (app != null && !app.trim().isEmpty())
				{
					AccessControl ac = new AccessControl();
					ac.setProgram(app);
					ac.setAgentName(agent);
					ac.setGenericHost(host);
					ac.setComments(comments);
					if (role != null && !role.trim().isEmpty()) {
						Role r = appSvc.findRoleByNameAndSystem(role, agent);
						if (r == null)
							throw new Exception(String.format("Cannot find role %s", role));
						ac.setRoleDescription(r.getName());
						ac.setRoleId(r.getId());
					}
					if (user != null && !user.trim().isEmpty()) {
						ac.setGenericUser(user);
					}
					boolean found = false;
					for (AccessControl ac2: current)
					{
						if (nullCmp (ac.getRoleDescription(), ac2.getRoleDescription()) &&
							nullCmp (ac.getGenericUser(), ac2.getGenericUser()) &&
							nullCmp (ac.getProgram(), ac2.getProgram()) &&
							nullCmp (ac.getRoleId(), ac2.getRoleId()) &&
							nullCmp (ac.getGenericHost(), ac2.getGenericHost()) ) {
							unchanged ++;
							found = true;
							break;
						}
					}
					if (!found) {
						svc.create(ac);
						current.add(ac);
						inserts ++;
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

		Missatgebox.avis(Labels.getLabel("parametres.zul.import", new Object[] { updates, inserts, removed, unchanged }));
		DataNodeCollection coll = (DataNodeCollection) XPathUtils.eval(gridControlAccess.getDataSource(), "controlAcces");
		updateAgentTimestamp();
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
