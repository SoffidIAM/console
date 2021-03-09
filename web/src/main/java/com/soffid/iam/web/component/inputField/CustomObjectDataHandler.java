package com.soffid.iam.web.component.inputField;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.zk.ui.event.EventListener;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.CustomObject;
import com.soffid.iam.api.AsyncList;
import com.soffid.iam.api.CrudHandler;
import com.soffid.iam.api.DataType;
import com.soffid.iam.web.component.InputField3;
import com.soffid.iam.web.component.SearchDictionaryBuilder;
import com.soffid.iam.web.popup.FinderHandler;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.component.Databox;

public class CustomObjectDataHandler extends InputFieldDataHandler<CustomObject> {

	private CrudHandler<CustomObject> handler;

	public CustomObjectDataHandler(DataType dataType) throws InternalErrorException, NamingException, CreateException {
		super (dataType);
		handler = EJBLocator.getCrudRegistryService().getHandler(CustomObject.class);
	}

	@Override
	public String getDescription(String name, String filter) throws Exception {
		String q = "name eq \"" + quote(name)+ "\" and type.name eq\""+quote(dataType.getDataObjectType())+"\"";
		if (filter != null && ! filter.trim().isEmpty())
			q = "("+filter+") and ("+q+")";
		List<CustomObject> r = handler.read(null, q, null, 2).getResources();
		if (! r.isEmpty())
			return r.iterator().next().getDescription();
		else
			return null;
	}

	@Override
	public AsyncList<CustomObject> search(String text, String filter) throws Exception {
		String q = "type.name eq\""+quote(dataType.getDataObjectType())+"\"";
		if (filter != null && ! filter.trim().isEmpty())
			q = "("+filter+") and ("+q+")";
		return handler.readAsync(text, q);
	}

	@Override
	public void openFinder(String filter, boolean multiple, Databox databox, EventListener listener) throws Exception {
		String q = "type.name eq\""+quote(dataType.getDataObjectType())+"\"";
		if (filter != null && ! filter.trim().isEmpty())
			q = "("+filter+") and ("+q+")";
		FinderHandler.startWizard("Select "+dataType.getDataObjectType(), SearchDictionaryBuilder.COM_SOFFID_IAM_API_CUSTOM_OBJECT+dataType.getDataObjectType(),
				databox, multiple, 
				filter,
				listener);
		
	}

	@Override
	public String followLink(String value) throws UnsupportedEncodingException {
		return null;
	}


	@Override
	public String[] toNameDescription(CustomObject o) {
		return new String[] {o.getName(), o.getDescription()};
	}

	@Override
	public CustomObject getObject(String name, String filter) throws Exception {
		String q = "name eq \"" + quote(name)+ "\" and type.name eq\""+quote(dataType.getDataObjectType())+"\"";
		if (filter != null && ! filter.trim().isEmpty())
			q = "("+filter+") and ("+q+")";
		List<CustomObject> r = handler.read(null, q, null, 2).getResources();
		if (! r.isEmpty())
			return r.iterator().next();
		else
			return null;
	}

}
