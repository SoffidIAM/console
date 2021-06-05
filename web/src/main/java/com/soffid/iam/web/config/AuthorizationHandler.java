package com.soffid.iam.web.config;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.CreateException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.Status;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Listbox;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.AuthorizationRole;
import com.soffid.iam.api.Role;
import com.soffid.iam.service.ejb.ApplicationService;
import com.soffid.iam.service.ejb.AuthorizationService;
import com.soffid.iam.utils.AutoritzacionsUsuari;
import com.soffid.iam.web.component.FrameHandler;
import com.soffid.iam.web.component.Identity;
import com.soffid.iam.web.popup.CsvParser;
import com.soffid.iam.web.popup.IdentityHandler;
import com.soffid.iam.web.popup.ImportCsvHandler;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.binder.BindContext;
import es.caib.zkib.component.DataModel;
import es.caib.zkib.component.DataTable;
import es.caib.zkib.component.Form;
import es.caib.zkib.datamodel.DataModelCollection;
import es.caib.zkib.datamodel.DataNode;
import es.caib.zkib.datamodel.DataNodeCollection;
import es.caib.zkib.datasource.CommitException;
import es.caib.zkib.datasource.DataSource;
import es.caib.zkib.datasource.XPathUtils;
import es.caib.zkib.zkiblaf.Missatgebox;

public class AuthorizationHandler extends FrameHandler {
	private boolean canQueryAuthorization;
	private boolean canCreateRolAutoritzacion;
	private boolean canDeleteRolAutoritzacion;

	public AuthorizationHandler() throws Exception {

		// Autoritzacions
		canQueryAuthorization = AutoritzacionsUsuari.hasQueryAuthorization();
		canCreateRolAutoritzacion = AutoritzacionsUsuari.hasCreateAuthorizationRol();
		canDeleteRolAutoritzacion = AutoritzacionsUsuari.hasDeleteAuthorizationRol();

	}

	@Override
	public void setPage(Page page) {
		super.setPage(page);

		setVariable("canQueryAuthorization", canQueryAuthorization, true);
		setVariable("canCreateRolAutoritzacion", canCreateRolAutoritzacion, true);
		setVariable("canDeleteRolAutoritzacion", canDeleteRolAutoritzacion, true);

	}

	public void select() {
		DataTable t = (DataTable) getListbox();
		if (t.getSelectedIndex() >= 0) {
			DataModel model = getModel();
			String codiSeleccionatAbans = (String) getModel().getVariables().getVariable("descripcioSeleccionat");
			String message = String.format(org.zkoss.util.resource.Labels.getLabel("autoritzacions.VolConfirmar"),
							new Object[]{codiSeleccionatAbans});
					
			// Verifiquem si n'hi ha canvis pendents:
			if (model.isCommitPending()) {
				es.caib.zkib.zkiblaf.Missatgebox.confirmaYES_NO(message,
					org.zkoss.util.resource.Labels.getLabel("autoritzacions.ConfirmaCanvis"),
					(event) -> {
						if ("onOK".equals(event.getName())) {
							model.commit();
						} else {
							model.refresh();
						}
					});

				if( t.getSelectedIndex() >= 0)
				{
					String codiSeleccionat = (String) XPathUtils.getValue((DataSource) t, "name");
					String descripcioSeleccionat = (String) XPathUtils.getValue((DataSource) t, "description");
					String tipusDominiSeleccionat = (String) XPathUtils.getValue((DataSource) t, "typeDescription");  
					String tipusDomini = (String) XPathUtils.getValue((DataSource) t, "type");  
					String scopeSeleccionat = (String) XPathUtils.getValue((DataSource) t, "scope");
					String heretaSeleccionat = (String) XPathUtils.getValue((DataSource) t, "inherited");
					
					model.getVariables().declareVariable("queryEnabled", true);
					
					model.getVariables().declareVariable("codiAutoritzacio", codiSeleccionat);
					model.getVariables().declareVariable("descripcioSeleccionat", descripcioSeleccionat);
					model.getVariables().declareVariable("tipusDominiSeleccionat", tipusDominiSeleccionat);
					model.getVariables().declareVariable("tipusDomini", tipusDomini);
					model.getVariables().declareVariable("scopeSeleccionat", scopeSeleccionat);
					model.getVariables().declareVariable("heretaSeleccionat", heretaSeleccionat);
					
					
					// i el tipus de domini
					String s_tipusDominiSeleccionat = org.zkoss.util.resource.Labels.getLabel("autoritzacions.TipusDomini")+tipusDominiSeleccionat;
					model.getVariables().declareVariable("s_tipusDominiSeleccionat", s_tipusDominiSeleccionat);

				}
			}
		}
	}

	@Override
	public boolean insertBefore(Component newChild, Component refChild) {
		boolean x =  super.insertBefore(newChild, refChild);
		if (newChild instanceof DataModel) {
			DataModel model = (DataModel) newChild;
			model.getVariables().declareVariable("canQueryAuthorization", canQueryAuthorization);
			model.getVariables().declareVariable("canCreateRolAutoritzacion", canCreateRolAutoritzacion);
			model.getVariables().declareVariable("canDeleteRolAutoritzacion", canDeleteRolAutoritzacion);
		}
		return x;
	}
	
	public void rolesTableAction(Event event) {
		DataTable t = (DataTable) getFellow("rolesTable");
		String roleDescription = (String) XPathUtils.getValue((DataSource)t, "/role/name");
		Missatgebox.confirmaOK_CANCEL(
				Labels.getLabel("autoritzacions.zul.confirm-delete", new String[] {roleDescription}),
				Labels.getLabel("dbpropertyadmin.Alerta"),
				(ev) -> {
					if (ev.getName().equals("onOK")) {
						t.delete();
						DataNode dn = (DataNode) XPathUtils.getValue(getListbox(), "/");
						if (dn != null)
							dn.update();
					}
				});
	}

	@Override
	public void addNew() throws Exception {
		IdentityHandler.selectIdentity(Labels.getLabel("accounts.addRole"), 
				new Identity.Type[] {
						Identity.Type.ROLE
				},
				this, "onAddRole");
	}
	
	public void addRole(Event event) {
		List<Identity> identities = (List<Identity>) event.getData();
		DataTable dt = (DataTable) getFellow("rolesTable");
		
		String auth = (String) XPathUtils.getValue(getForm(), "name");

		for (Identity identity: identities)
		{
			Role role = (Role) identity.getObject();
			dt.addNew();
			XPathUtils.setValue((DataSource) dt, "role", role);
			XPathUtils.setValue((DataSource) dt, "authorization", auth);
		}
		
		DataNode dn = (DataNode) XPathUtils.getValue(getListbox(), "/");
		dn.update();
	}
	
	public void importCsv () throws IOException, CommitException {
		getModel().commit();
		
		String[][] data = { 
				{"name", Labels.getLabel("autoritzacions.zul.Codi")},
				{"roles", Labels.getLabel("aplicacions.zul.Rols")}
		};
		
		String title = Labels.getLabel("autoritzacions.zul");
		ImportCsvHandler.startWizard(title, data, this, 
				parser -> importCsv(parser));
	}

	private void importCsv(CsvParser parser) {
		Map<String,String> m = null;
		try {
			AuthorizationService authorizationService = EJBLocator.getAuthorizationService();
			ApplicationService appService = EJBLocator.getApplicationService();
			for ( Iterator<Map<String, String>> iterator = parser.iterator(); iterator.hasNext(); )
			{
				m = iterator.next();
				String name = m.get("name");
				String roles = m.get("roles");
				if (roles == null) roles = "";
				if (name != null && !name.isEmpty()) {
					Set<String> newRoles = new HashSet<String>();
					for (String s: roles.split(";"))
						if ( ! s.trim().isEmpty())
							newRoles.add(s.trim());
					
					Collection<AuthorizationRole> oldRoles = authorizationService.getAuthorizationRoles(name);
					
					// Remove old roles
					for ( Iterator<AuthorizationRole> it2 = oldRoles.iterator(); it2.hasNext();) {
						AuthorizationRole oldRole = it2.next();
						String roleName = oldRole.getRole().getName()+"@"+oldRole.getRole().getSystem();
						if ( newRoles.contains(roleName)) {
							newRoles.remove(roleName);
						} else {
							authorizationService.delete(oldRole);
						}
					}
					// Add new ones
					for (String newRole: newRoles) {
						String[] s = newRole.split("@");
						String roleName;
						String system;
						if (s.length == 2) {
							roleName = s[0];
							system = s[1];
						} else if (s.length == 1){
							roleName = newRole;
							system = "soffid";
						} else {
							throw new UiException("Role "+newRole+" contains more than one @ sign");
						}
						roleName = roleName.trim();
						system = system.trim();
						Role r = appService.findRoleByNameAndSystem(roleName, system);
						if (r == null)
							throw new UiException("Cannot find role "+roleName+" at "+system);
						AuthorizationRole ar = new AuthorizationRole();
						ar.setAuthorization(name);
						ar.setRole(r);
						authorizationService.create(ar);
					}
				}				
			}
		} catch (UiException e) {
			throw e;
		} catch (Exception e) {
			if (m == null)
				throw new UiException(e);
			else
				throw new UiException("Error loading permissions for "+m.get("name"), e);
		}
		
		getModel().refresh();
	}
}
