package com.soffid.iam.web.component.inputField;

import java.io.IOException;
import java.util.List;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.zk.ui.event.EventListener;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.Application;
import com.soffid.iam.api.AsyncList;
import com.soffid.iam.api.CrudHandler;
import com.soffid.iam.api.DataType;
import com.soffid.iam.web.component.InputField3;
import com.soffid.iam.web.popup.FinderHandler;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.component.Databox;

public class ApplicationDataHandler extends InputFieldDataHandler<Application> {

	private CrudHandler<Application> handler;

	public ApplicationDataHandler(DataType dataType) throws InternalErrorException, NamingException, CreateException {
		super (dataType);
		handler = EJBLocator.getCrudRegistryService().getHandler(Application.class);
	}

	@Override
	public String getDescription(String name, String filter) throws InternalErrorException, NamingException, CreateException {
		String q = "name eq \"" + quote(name)+ "\" ";
		if (filter != null && ! filter.trim().isEmpty())
			q = "("+filter+") and ("+q+")";
		List<Application> r = handler.read(null, q, null, null);
		if (! r.isEmpty())
			return r.iterator().next().getDescription();
		else
			return null;
	}

	@Override
	public AsyncList<Application> search(String text, String filter) throws InternalErrorException, NamingException, CreateException {
		return handler.readAsync(text, filter);
	}

	@Override
	public void openFinder(String filter, boolean multiple, Databox databox, EventListener listener) throws Exception {
		FinderHandler.startWizard("Select application", Application.class.getName(),
				databox, multiple, 
				filter,
				listener);
		
	}

	@Override
	public void followLink(String value) {
		// TODO Auto-generated method stub
	}

	@Override
	public String[] toNameDescription(Application o) {
		return new String[] {o.getName(), o.getDescription()};
	}


}
