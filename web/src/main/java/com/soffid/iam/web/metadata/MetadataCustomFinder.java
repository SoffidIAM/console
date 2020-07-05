package com.soffid.iam.web.metadata;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.CustomObject;
import com.soffid.iam.api.CustomObjectType;
import com.soffid.iam.api.DataType;
import com.soffid.iam.api.MetadataScope;
import com.soffid.iam.web.WebDataType;

import es.caib.zkib.datamodel.DataContext;
import es.caib.zkib.datamodel.xml.handler.FinderHandler;

public class MetadataCustomFinder implements FinderHandler {

	@Override
	public Collection find(DataContext ctx) throws Exception {
		CustomObjectType co = (CustomObjectType) ctx.getData();
		List<DataType> l;
		l = new Vector<DataType>(
					EJBLocator.getAdditionalDataService().findDataTypesByObjectTypeAndName2(co.getName(), null));
		Collections.sort(l, new Comparator<DataType>() {
			public int compare(DataType o1, DataType o2) {
				return o1.getOrder().compareTo(o2.getOrder());
			}
		});
		
		List<WebDataType> l2 = new LinkedList<WebDataType>();
		for ( DataType dt: l)
			l2.add(new WebDataType(dt));
		return l2;
	}

	@Override
	public boolean isSuitable(DataContext arg0) {
		return true;
	}

}
