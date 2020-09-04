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
import com.soffid.iam.api.OUType;
import com.soffid.iam.service.ejb.OrganizationalUnitTypeService;
import com.soffid.iam.web.component.FrameHandler;
import com.soffid.iam.web.popup.CsvParser;
import com.soffid.iam.web.popup.ImportCsvHandler;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.datasource.CommitException;
import es.caib.zkib.zkiblaf.Missatgebox;

public class GroupTypeHandler extends FrameHandler {

	public GroupTypeHandler() throws InternalErrorException {
	}

	@Override
	public void setPage(Page p) {
		super.setPage(p);
	}
		

	public void onChangeDades() {
	}
	
	public void importCsv () throws IOException, CommitException {
		getModel().commit();
		
		String[][] data = { 
				{"name", Labels.getLabel("com.soffid.iam.api.OUType.name")},
				{"description", Labels.getLabel("com.soffid.iam.api.OUType.description")},
				{"roleHolder", Labels.getLabel("com.soffid.iam.api.OUType.roleHolder")}
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
			OrganizationalUnitTypeService svc = EJBLocator.getOrganizationalUnitTypeService();
			Map<String, OUType> current = new HashMap<String, OUType>();
			for (OUType ut: svc.findOrganizationalUnitByJsonQuery(""))
				current.put(ut.getName(), ut);
			
			for ( Iterator<Map<String, String>> iterator = parser.iterator(); iterator.hasNext(); )
			{
				m = iterator.next();
				String name = m.get("name");
				String description = m.get("description");
				String roleHolder = m.get("roleHolder");

				if (name != null && !name.trim().isEmpty())
				{
					OUType ut = current.get(name);
					if (ut != null)
					{
						if (m.containsKey("description"))
							ut.setDescription(description);
						if (m.containsKey("roleHolder"))
							ut.setRoleHolder("true".equalsIgnoreCase(roleHolder) ||
									yes.equalsIgnoreCase(roleHolder));
						svc.update(ut);
						updates ++;
					} else {
						inserts ++;
						ut = new OUType();
						ut.setName(name);
						ut.setDescription(description);
						ut.setRoleHolder("true".equalsIgnoreCase(roleHolder));
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
