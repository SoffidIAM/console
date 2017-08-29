package com.soffid.iam.web.metadata;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

import com.soffid.iam.api.CustomObject;
import com.soffid.iam.api.CustomObjectType;
import com.soffid.iam.api.MetadataScope;

import es.caib.seycon.ng.EJBLocator;
import es.caib.seycon.ng.comu.TipusDada;
import es.caib.zkib.datamodel.DataContext;
import es.caib.zkib.datamodel.xml.handler.FinderHandler;

public class MetadataCustomFinder implements FinderHandler {

	@Override
	public Collection find(DataContext ctx) throws Exception {
		CustomObjectType co = (CustomObjectType) ctx.getData();
		List<TipusDada> l;
		if (co.isBuiltin())
		{
			MetadataScope scope = MetadataScope.fromString(co.getName());
			l = new Vector<TipusDada>(
					EJBLocator.getDadesAddicionalsService().findDataTypes(scope));
		}
		else
		{
			l = new Vector<TipusDada>(
					EJBLocator.getDadesAddicionalsService().findDataTypesByObjectTypeAndName(co.getName(), null));
		}
		Collections.sort(l, new Comparator<TipusDada>() {
			public int compare(TipusDada o1, TipusDada o2) {
				return o1.getOrdre().compareTo(o2.getOrdre());
			}
		});
		return l;
	}

	@Override
	public boolean isSuitable(DataContext arg0) {
		return true;
	}

}
