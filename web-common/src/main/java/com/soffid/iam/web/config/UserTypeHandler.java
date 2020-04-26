package com.soffid.iam.web.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.zkoss.util.resource.Labels;
import org.zkoss.xel.fn.CommonFns;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.UiException;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.Configuration;
import com.soffid.iam.api.UserType;
import com.soffid.iam.service.ejb.ApplicationService;
import com.soffid.iam.service.ejb.AuthorizationService;
import com.soffid.iam.service.ejb.ConfigurationService;
import com.soffid.iam.service.ejb.UserDomainService;
import com.soffid.iam.utils.Security;
import com.soffid.iam.web.component.FrameHandler;
import com.soffid.iam.web.popup.CsvParser;
import com.soffid.iam.web.popup.ImportCsvHandler;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.datasource.CommitException;
import es.caib.zkib.zkiblaf.Missatgebox;

public class UserTypeHandler extends FrameHandler {
	private boolean isMaster;
	private boolean canCreateUserType;
	private boolean canUpdateuserType;
	private boolean canDeleteuserType;
	private boolean canQueryUserType;

	public UserTypeHandler() throws InternalErrorException {
		com.soffid.iam.api.Tenant masterTenant = com.soffid.iam.ServiceLocator.instance().getTenantService().getMasterTenant();

		canCreateUserType = Security.isUserInRole("userType:create");
		canUpdateuserType = Security.isUserInRole("userType:update");
		canDeleteuserType = Security.isUserInRole("userType:delete");
		canQueryUserType = Security.isUserInRole("userType:query");;
		
	}

	@Override
	public void setPage(Page p) {
		super.setPage(p);
		getNamespace().setVariable("canCreateUserType", canCreateUserType, true);
		getNamespace().setVariable("canUpdateUserType", canUpdateuserType, true);
		getNamespace().setVariable("canDeleteUserType", canDeleteuserType, true);
		getNamespace().setVariable("canQueryUserType", canQueryUserType, true);
		getNamespace().setVariable("canModifyUserType", canUpdateuserType || canCreateUserType, true);
	}
		

	public void onChangeDades() {
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
}
