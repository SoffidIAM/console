package com.soffid.iam.web.popup;

import java.util.Collection;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.CrudHandler;
import com.soffid.iam.api.CustomObject;
import com.soffid.iam.web.component.SearchDictionaryBuilder;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.datamodel.DataContext;
import es.caib.zkib.datamodel.Finder;
import es.caib.zkib.datamodel.xml.handler.FinderHandler;
import es.caib.zkib.jxpath.Variables;


public class FinderHandlerModel implements FinderHandler {
	@Override
	public boolean isSuitable(DataContext node) {
		return true;
	}

	@Override
	public Collection find(DataContext ctx) throws Exception {
		Variables vars = ctx.getDataSource().getVariables();

		String query = (String) vars.getVariable("query");
		String textQuery = (String) vars.getVariable("textQuery");
		String className = (String) vars.getVariable("className");

		if (className == null)
			return null;
		
		Class clazz;
		if (className.startsWith(SearchDictionaryBuilder.COM_SOFFID_IAM_API_CUSTOM_OBJECT)) {
			clazz = CustomObject.class;
			String objectName = className.substring(SearchDictionaryBuilder.COM_SOFFID_IAM_API_CUSTOM_OBJECT.length());
			String q2 = "type.name eq \""+  objectName.replaceAll ("\"", "\\\\\"")+"\"";
			if (query == null || query.trim().isEmpty()) {
				query = q2;
			} else {
				query = q2 +" and ("+query+")";
			}
		} else {
			clazz = Class.forName(className);
		}

		CrudHandler crud = EJBLocator.getCrudRegistryService().getHandler(clazz);
		
		if (crud == null)
			throw new InternalErrorException("Unable to find "+clazz.getCanonicalName()+" finder");
		
		return crud.readAsync(textQuery, query);
	}

}
