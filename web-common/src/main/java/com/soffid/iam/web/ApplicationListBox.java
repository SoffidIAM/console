package com.soffid.iam.web;

import java.util.List;

import org.apache.commons.logging.LogFactory;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Listheader;

import com.soffid.iam.api.AttributeVisibilityEnum;
import com.soffid.iam.api.MetadataScope;

import es.caib.seycon.ng.EJBLocator;
import es.caib.seycon.ng.comu.TipusDada;
import es.caib.seycon.ng.comu.TypeEnumeration;
import es.caib.seycon.ng.servei.ejb.DadesAddicionalsService;
import es.caib.zkib.component.DataDatebox;
import es.caib.zkib.component.DataListbox;
import es.caib.zkib.component.DataListcell;
import es.caib.zkib.component.MasterListItem;

public class ApplicationListBox extends DataListbox implements AfterCompose {

	public void afterCompose() {
		try {
			MasterListItem mli = getMasterListItem();
			DadesAddicionalsService bean = EJBLocator.getDadesAddicionalsService();
			for (TipusDada tda: bean.findDataTypes(MetadataScope.APPLICATION) )
			{
				if ( TypeEnumeration.STRING_TYPE.equals(tda.getType()) || 
						TypeEnumeration.EMAIL_TYPE.equals(tda.getType()) ||
						TypeEnumeration.USER_TYPE.equals(tda.getType()) ||
						TypeEnumeration.CUSTOM_OBJECT_TYPE.equals(tda.getType()) ||
						TypeEnumeration.APPLICATION_TYPE.equals(tda.getType()) ||
						TypeEnumeration.GROUP_TYPE.equals(tda.getType()) )
				{
					List children = mli.getChildren();
					int size = children.size();

					Listheader h = new Listheader(tda.getLabel());
					h.setWidth("12%");
					h.setVisible(false);
					h.setParent(getListhead());
					h.setSort("auto");

					DataListcell lc = new DataListcell();
					lc.setParent(mli);
					lc.setBind("attributes[@name='" + tda.getCodi() + "']");
				}
				else if (TypeEnumeration.DATE_TYPE.equals(tda.getType()))
				{
					List children = mli.getChildren();
					int size = children.size();

					Listheader h = new Listheader(tda.getLabel());
					h.setWidth("12%");
					h.setParent(getListhead());
					h.setSort("auto");
					h.setVisible(false);
					DataListcell lc = new DataListcell();

					lc.setParent(mli);

					DataDatebox db = new DataDatebox();
					db.setButtonVisible(false);
					db.setReadonly(true);
					db.setSclass("dateboxreadnoborder");

					db.setParent(lc);;
					db.setBind("attributes[@name='" + tda.getCodi() + "']");
					db.setFormat(Labels.getLabel("usuaris.zul.dateFormat2"));
				}
			}
		} catch (Throwable t) {
			LogFactory.getLog(getClass()).warn("Error generating listbox", t);
		}
				
	}

}
