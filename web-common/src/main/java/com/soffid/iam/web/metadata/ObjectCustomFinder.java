package com.soffid.iam.web.metadata;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.CustomObjectType;
import com.soffid.iam.api.MetadataScope;
import com.soffid.iam.api.MetadataScopeEnum;

import es.caib.seycon.ng.comu.TipusDada;
import es.caib.zkib.datamodel.DataContext;
import es.caib.zkib.datamodel.xml.handler.FinderHandler;

public class ObjectCustomFinder implements FinderHandler {
	private static MetadataScope builtinObjects[] = new MetadataScope[]{
			MetadataScope.APPLICATION,
			MetadataScope.ROLE,
			MetadataScope.USER,
			MetadataScope.GROUP,
			MetadataScope.MAIL_LIST
	};
	
	@Override
	public Collection find(DataContext ctx) throws Exception {
		List<CustomObjectType> list = new LinkedList<CustomObjectType>(
				EJBLocator.getAdditionalDataService().findCustomObjectTypeByJsonQuery(""));
		for (MetadataScope m: builtinObjects )
		{
			CustomObjectType cob = new CustomObjectType(m.getValue(), "Builtin "+m.getValue()+" object", m, true);
			list.add(cob);
		}
		Collections.sort(list, new Comparator<CustomObjectType>() {
			public int compare(CustomObjectType o1, CustomObjectType o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
		return list;
	}

	@Override
	public boolean isSuitable(DataContext arg0) {
		return true;
	}

}
