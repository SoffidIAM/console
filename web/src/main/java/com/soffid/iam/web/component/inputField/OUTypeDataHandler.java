package com.soffid.iam.web.component.inputField;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.EventListener;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.Group;
import com.soffid.iam.api.OUType;
import com.soffid.iam.web.component.InputField3;
import com.soffid.iam.web.popup.FinderHandler;
import com.soffid.iam.api.AsyncList;
import com.soffid.iam.api.CrudHandler;
import com.soffid.iam.api.DataType;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.component.Databox;

public class OUTypeDataHandler extends InputFieldDataHandler<OUType> {

	private CrudHandler<OUType> handler;

	public OUTypeDataHandler(DataType dataType) throws InternalErrorException, NamingException, CreateException {
		super (dataType);
		handler = EJBLocator.getCrudRegistryService().getHandler(OUType.class);
	}

	@Override
	public String getDescription(String name, String filter) throws Exception {
		String q = "name eq \"" + quote(name)+ "\" ";
		if (filter != null && ! filter.trim().isEmpty())
			q = "("+filter+") and ("+q+")";
		List<OUType> r = handler.read(null, q, null, 2).getResources();
		if (! r.isEmpty())
			return r.iterator().next().getDescription();
		else
			return null;
	}

	@Override
	public AsyncList<OUType> search(String text, String filter) throws Exception {
		return handler.readAsync(text, filter);
	}

	@Override
	public void openFinder(String filter, boolean multiple, Component databox, EventListener listener) throws Exception {
		FinderHandler.startWizard("Select group type", OUType.class.getName(),
				databox, multiple, 
				filter,
				listener);
		
	}

	@Override
	public String followLink(String value) throws UnsupportedEncodingException {
		return "/soffid/config/group-types.zul?name="+URLEncoder.encode(value,"UTF-8"); 
	}


	@Override
	public String[] toNameDescription(OUType o) {
		return new String[] {o.getName(), o.getDescription()};
	}

	@Override
	public OUType getObject(String name, String filter) throws Exception {
		String q = "name eq \"" + quote(name)+ "\" ";
		if (filter != null && ! filter.trim().isEmpty())
			q = "("+filter+") and ("+q+")";
		List<OUType> r = handler.read(null, q, null, 2).getResources();
		if (! r.isEmpty())
			return r.iterator().next();
		else
			return null;
	}

}
