package com.soffid.iam.web.config;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Event;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.AttributeTranslation;
import com.soffid.iam.api.AuthorizationRole;
import com.soffid.iam.api.Role;
import com.soffid.iam.service.ejb.ApplicationService;
import com.soffid.iam.service.ejb.AttributeTranslationService;
import com.soffid.iam.service.ejb.AuthorizationService;
import com.soffid.iam.utils.AutoritzacionsUsuari;
import com.soffid.iam.utils.Security;
import com.soffid.iam.web.component.FrameHandler;
import com.soffid.iam.web.component.Identity;
import com.soffid.iam.web.component.SearchBox;
import com.soffid.iam.web.popup.CsvParser;
import com.soffid.iam.web.popup.IdentityHandler;
import com.soffid.iam.web.popup.ImportCsvHandler;

import es.caib.zkib.component.DataModel;
import es.caib.zkib.component.DataTable;
import es.caib.zkib.datamodel.DataNode;
import es.caib.zkib.datasource.CommitException;
import es.caib.zkib.datasource.DataSource;
import es.caib.zkib.datasource.XPathUtils;
import es.caib.zkib.zkiblaf.Missatgebox;

public class AttributeTranslationHandler extends FrameHandler {
	private boolean canQueryAuthorization;
	private boolean canCreateAttributeTranslation;
	private boolean canDeleteAttributeTranslation;
	private boolean canUpdateAttributeTranslation;

	public AttributeTranslationHandler() throws Exception {
		canQueryAuthorization = Security.isUserInRole("attributeTranslation:query");
		canCreateAttributeTranslation = Security.isUserInRole("attributeTranslation:create");
		canDeleteAttributeTranslation = Security.isUserInRole("attributeTranslation:delete");
		canUpdateAttributeTranslation = Security.isUserInRole("attributeTranslation:update");
	}

	@Override
	public void setPage(Page page) {
		super.setPage(page);

		setVariable("canQueryAuthorization", canQueryAuthorization, true);
		setVariable("canCreateAttributeTranslation", canCreateAttributeTranslation, true);
		setVariable("canDeleteAttributeTranslation", canDeleteAttributeTranslation, true);
		setVariable("canUpdateAttributeTranslation", canUpdateAttributeTranslation, true);
	}

	public void onEditValue(Event event) {
		String[] data = (String[]) event.getData();
		String column = data[0];
		String value = data[1];
		XPathUtils.setValue(getListbox(), "/"+column, value);
		return;
	}
	
	public void importCsv () throws IOException, CommitException {
		getModel().commit();
		
		String[][] data = { 
				{"domain", Labels.getLabel("com.soffid.iam.api.AttributeTranslation.domain")},
				{"column1", Labels.getLabel("com.soffid.iam.api.AttributeTranslation.column1")},
				{"column2", Labels.getLabel("com.soffid.iam.api.AttributeTranslation.column2")},
				{"column3", Labels.getLabel("com.soffid.iam.api.AttributeTranslation.column3")},
				{"column4", Labels.getLabel("com.soffid.iam.api.AttributeTranslation.column4")},
				{"column5", Labels.getLabel("com.soffid.iam.api.AttributeTranslation.column5")}
		};
		
		String title = Labels.getLabel("attributeTranslation.load");
		ImportCsvHandler.startWizard(title, data, this, 
				parser -> importCsv(parser));
	}

	private void importCsv(CsvParser parser) {
		int updates = 0;
		int inserts = 0;
		int unchanged = 0;
		int removed = 0;
		Map<String,String> m = null;
		try {
			AttributeTranslationService svc = EJBLocator.getAttributeTranslationService();
			for ( Iterator<Map<String, String>> iterator = parser.iterator(); iterator.hasNext(); )
			{
				m = iterator.next();
				String domain = m.get("domain");
				String c1 = m.get("column1");
				String c2 = m.get("column2");
				String c3 = m.get("column3");
				String c4 = m.get("column4");
				String c5 = m.get("column5");
				if (domain != null && !domain.isEmpty() &&
						c1 != null && !c1.isEmpty() &&
						c2 != null && !c2.isEmpty()) {
					Collection<AttributeTranslation> atts = svc.findByColumn1(domain, c1);
					if (atts != null && atts.size() == 1) {
						for (AttributeTranslation att: atts) {
							att.setColumn2(c2);
							att.setColumn3(c3);
							att.setColumn4(c4);
							att.setColumn5(c5);
							svc.update(att);
							updates ++;
						}
					} else {
						AttributeTranslation att = new AttributeTranslation();
						att.setDomain(domain);
						att.setColumn1(c1);
						att.setColumn2(c2);
						att.setColumn3(c3);
						att.setColumn4(c4);
						att.setColumn5(c5);
						svc.create(att);
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
				throw new UiException("Error loading permissions for "+m.get("name"), e);
		}
		
		getModel().refresh();
		SearchBox sb = (SearchBox) getFellowIfAny("searchBox");
		if (sb != null)
			sb.updateProgress();
		Missatgebox.avis(Labels.getLabel("parametres.zul.import", new Object[] { updates, inserts, removed, unchanged }));
	}
	
	public void delete() {
		DataTable lb = (DataTable) getListbox();
		lb.delete();
	}
	
}
