package com.soffid.iam.web;

import org.apache.commons.logging.LogFactory;
import org.zkoss.util.resource.Labels;
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

public class UsersListbox extends DataListbox implements AfterCompose {

	public void afterCompose() {
		try {
			DadesAddicionalsService bean = EJBLocator.getDadesAddicionalsService();
			for (TipusDada tda: bean.findDataTypes(MetadataScope.USER) )
			{
				if ( ! ( AttributeVisibilityEnum.HIDDEN.equals(tda.getOperatorVisibility()) ))
				{
					if (TypeEnumeration.DATE_TYPE.equals(tda.getType()))
					{
						Listheader h = new Listheader(tda.getLabel());
						h.setWidth("12%");
						h.setParent(getListhead());
						h.setSort("auto");
						h.setVisible(false);
						DataListcell lc = new DataListcell();
						lc.setParent(getMasterListItem());
						DataDatebox db = new DataDatebox();
						db.setButtonVisible(false);
						db.setReadonly(true);
						db.setSclass("dateboxreadnoborder");
						db.setParent(lc);;
						db.setBind("/attributes[@name='" + tda.getCodi() + "']");
						db.setFormat(Labels.getLabel("usuaris.zul.dateFormat2"));
					}
					else if ( TypeEnumeration.HTML.equals(tda.getType()) ||
							TypeEnumeration.BINARY_TYPE.equals(tda.getType()) ||
							TypeEnumeration.ATTACHMENT_TYPE.equals(tda.getType()) ||
							TypeEnumeration.PHOTO_TYPE.equals(tda.getType()))
					{
						
					}
					else
					{
						Listheader h = new Listheader(tda.getLabel());
						h.setWidth("12%");
						h.setVisible(false);
						h.setParent(getListhead());
						h.setSort("auto");
						DataListcell lc = new DataListcell();
						lc.setParent(getMasterListItem());
						lc.setBind("/attributes[@name='" + tda.getCodi() + "']");
					}
				}
			}
		} catch (Throwable t) {
			LogFactory.getLog(getClass()).warn("Error generating listbox", t);
		}
				
	}

}
