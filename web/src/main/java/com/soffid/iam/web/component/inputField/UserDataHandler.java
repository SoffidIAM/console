package com.soffid.iam.web.component.inputField;

import java.util.List;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.zk.ui.event.EventListener;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.Application;
import com.soffid.iam.api.AsyncList;
import com.soffid.iam.api.CrudHandler;
import com.soffid.iam.api.DataType;
import com.soffid.iam.api.User;
import com.soffid.iam.web.component.InputField3;
import com.soffid.iam.web.popup.FinderHandler;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.component.Databox;

public class UserDataHandler extends InputFieldDataHandler<User> {

	private CrudHandler<User> handler;

	public UserDataHandler(DataType dataType) throws InternalErrorException, NamingException, CreateException {
		super (dataType);
		handler = EJBLocator.getCrudRegistryService().getHandler(User.class);
	}

	@Override
	public String getDescription(String name, String filter) throws InternalErrorException, NamingException, CreateException {
		String q = "userName eq \"" + quote(name)+ "\" ";
		if (filter != null && ! filter.trim().isEmpty())
			q = "("+filter+") and ("+q+")";
		List<User> r = handler.read(null, q, null, null);
		if (! r.isEmpty())
			return r.iterator().next().getFullName();
		else
			return null;
	}

	@Override
	public AsyncList<User> search(String text, String filter) throws InternalErrorException, NamingException, CreateException {
		return handler.readAsync(text, filter);
	}

	@Override
	public void openFinder(String filter, boolean multiple, Databox databox, EventListener listener) throws Exception {
		FinderHandler.startWizard("Select user", User.class.getName(),
				databox, multiple, 
				filter,
				listener);
		
	}

	@Override
	public void followLink(String value) {
		// TODO Auto-generated method stub
	}

	@Override
	public String[] toNameDescription(User o) {
		return new String[] {o.getUserName(), o.getFullName()};
	}

}
