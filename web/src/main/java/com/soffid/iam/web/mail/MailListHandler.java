package com.soffid.iam.web.mail;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.zkoss.util.resource.Labels;
import org.zkoss.xel.fn.CommonFns;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.UiException;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.Configuration;
import com.soffid.iam.api.MailDomain;
import com.soffid.iam.service.ejb.ApplicationService;
import com.soffid.iam.service.ejb.AuthorizationService;
import com.soffid.iam.service.ejb.ConfigurationService;
import com.soffid.iam.service.ejb.MailListsService;
import com.soffid.iam.utils.Security;
import com.soffid.iam.web.component.FrameHandler;
import com.soffid.iam.web.component.SearchBox;
import com.soffid.iam.web.popup.CsvParser;
import com.soffid.iam.web.popup.ImportCsvHandler;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.datasource.CommitException;
import es.caib.zkib.zkiblaf.Missatgebox;

public class MailListHandler extends FrameHandler {
	public MailListHandler() throws InternalErrorException {
		
	}

	public void onChangeForm() {
	}
	
	public void importCsv () throws IOException, CommitException {
		getModel().commit();
		
		String[][] data = { 
				{"name", Labels.getLabel("dominisCorreu.zul.Codi")},
				{"description", Labels.getLabel("dominisCorreu.zul.Descripcia")},
				{"obsolete", Labels.getLabel("dominisCorreu.zul.Obsolet-2")}
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
			MailListsService svc = EJBLocator.getMailListsService();
			for ( Iterator<Map<String, String>> iterator = parser.iterator(); iterator.hasNext(); )
			{
				m = iterator.next();
				String name = m.get("name");
				String description = m.get("description");
				String obsolete = m.get("obsolete");

				if (name != null && !name.trim().isEmpty())
				{
					MailDomain cfg = svc.findMailDomainByName(name);
					if (cfg != null)
					{
						cfg.setDescription(description);
						if (obsolete != null)
							cfg.setObsolete("true".equals(obsolete) || yes.equals(obsolete));
						svc.update(cfg);
						updates ++;
					} else {
						inserts ++;
						cfg = new MailDomain();
						cfg.setName(name);
						cfg.setDescription(description);
						if (obsolete != null)
							cfg.setObsolete("true".equals(obsolete) || yes.equals(obsolete));
						svc.create(cfg);
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
	
	@Override
	public void afterCompose() {
		super.afterCompose();
		SearchBox sb = (SearchBox) getFellow("searchBox");
		HttpServletRequest req = (HttpServletRequest) Executions.getCurrent().getNativeRequest();
		String name = req.getParameter("name");
		if (name != null) {
			String[] n = name.split("@");
			if (n.length == 2) {
				sb.setBasicMode();
				sb.addAttribute("name").setSearchFilter(n[0]);
				sb.addAttribute("domain").setSearchFilter(n[1]);
				sb.search();
				
			}
		}
	}


}
