package com.soffid.iam.web.config;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.UiException;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.Configuration;
import com.soffid.iam.service.ejb.ApplicationService;
import com.soffid.iam.service.ejb.AuthorizationService;
import com.soffid.iam.service.ejb.ConfigurationService;
import com.soffid.iam.utils.Security;
import com.soffid.iam.web.component.FrameHandler;
import com.soffid.iam.web.popup.CsvParser;
import com.soffid.iam.web.popup.ImportCsvHandler;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.datasource.CommitException;
import es.caib.zkib.zkiblaf.Missatgebox;

public class ParameterHandler extends FrameHandler {
	private boolean isMaster;
	private boolean canCreateParameter;
	private boolean canUpdateParameter;
	private boolean canDeleteParameter;
	private boolean canQueryParameter;
	private ConfigurationService configSvc;

	public ParameterHandler() throws InternalErrorException {
		com.soffid.iam.api.Tenant masterTenant = com.soffid.iam.ServiceLocator.instance().getTenantService().getMasterTenant();

		isMaster = com.soffid.iam.utils.Security.getCurrentTenantName().equals ( masterTenant.getName() );
		canCreateParameter = Security.isUserInRole("parameter:create");
		canUpdateParameter = Security.isUserInRole("parameter:update");
		canDeleteParameter = Security.isUserInRole("parameter:delete");
		canQueryParameter = Security.isUserInRole("parameter:query");;
		
	}

	@Override
	public void setPage(Page p) {
		super.setPage(p);
		getNamespace().setVariable("isMaster", isMaster, true);
		getNamespace().setVariable("canCreateParameter", canCreateParameter, true);
		getNamespace().setVariable("canUpdateParameter", canUpdateParameter, true);
		getNamespace().setVariable("canDeleteParameter", canDeleteParameter, true);
		getNamespace().setVariable("canQueryParameter", canQueryParameter, true);
	}
		

	public void onChangeDades() {
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
}
