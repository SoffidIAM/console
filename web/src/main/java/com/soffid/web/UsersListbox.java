package com.soffid.web;

import javax.naming.InitialContext;

import org.apache.commons.logging.LogFactory;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;

import com.soffid.iam.api.AttributeVisibilityEnum;

import es.caib.seycon.ng.comu.TipusDada;
import es.caib.seycon.ng.comu.TypeEnumeration;
import es.caib.seycon.ng.servei.ejb.DadesAddicionalsService;
import es.caib.seycon.ng.servei.ejb.DadesAddicionalsServiceHome;
import es.caib.zkib.component.DataDatebox;
import es.caib.zkib.component.DataListbox;
import es.caib.zkib.component.DataListcell;

public class UsersListbox extends DataListbox implements AfterCompose {

	public void afterCompose() {
		try {
			DadesAddicionalsServiceHome home = (DadesAddicionalsServiceHome) new InitialContext().lookup(DadesAddicionalsServiceHome.JNDI_NAME);
			DadesAddicionalsService bean = home.create();
			for (TipusDada tda: bean.findTipusDadesByCodi("%") )
			{
				if (( AttributeVisibilityEnum.EDITABLE.equals(tda.getOperatorVisibility()) ||
						AttributeVisibilityEnum.READONLY.equals(tda.getOperatorVisibility()) ))
				{
					if ( TypeEnumeration.STRING_TYPE.equals(tda.getType()) || 
							TypeEnumeration.EMAIL_TYPE.equals(tda.getType()) ||
							TypeEnumeration.USER_TYPE.equals(tda.getType()))
					{
						Listheader h = new Listheader(tda.getLabel());
						h.setWidth("12%");
						h.setParent(getListhead());
						h.setSort("auto");
						DataListcell lc = new DataListcell();
						lc.setParent(getMasterListItem());
						lc.setBind("dada[@codiDada='" + tda.getCodi() + "']/valorDada");
					}
					else if (TypeEnumeration.DATE_TYPE.equals(tda.getType()))
					{
						Listheader h = new Listheader(tda.getLabel());
						h.setWidth("12%");
						h.setParent(getListhead());
						h.setSort("auto");
						DataListcell lc = new DataListcell();
						lc.setParent(getMasterListItem());
						DataDatebox db = new DataDatebox();
						db.setButtonVisible(false);
						db.setReadonly(true);
						db.setSclass("dateboxreadnoborder");
						db.setParent(lc);;
						db.setBind("dada[@codiDada='" + tda.getCodi() + "']/valorDadaDate");
						db.setFormat(Labels.getLabel("usuaris.zul.dateFormat2"));
					}
				}
			}
		} catch (Throwable t) {
			LogFactory.getLog(getClass()).warn("Error generating listbox", t);
		}
				
	}

}
