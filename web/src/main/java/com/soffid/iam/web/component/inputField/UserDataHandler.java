package com.soffid.iam.web.component.inputField;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.EventListener;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.Account;
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
	public String getDescription(String name, String filter) throws Exception {
		String q = "userName eq \"" + quote(name)+ "\" ";
		if (filter != null && ! filter.trim().isEmpty())
			q = "("+filter+") and ("+q+")";
		List<User> r = handler.read(null, q, null, 2).getResources();
		if (! r.isEmpty())
			return r.iterator().next().getFullName();
		else
			return null;
	}

	@Override
	public AsyncList<User> search(String text, String filter) throws Exception {
		return handler.readAsync(text, filter);
	}

	@Override
	public void openFinder(String filter, boolean multiple, Component databox, EventListener listener) throws Exception {
		FinderHandler.startWizard("Select user", User.class.getName(),
				databox, multiple, 
				filter,
				listener);
		
	}

	@Override
	public String followLink(String value) throws UnsupportedEncodingException {
		return "/soffid/resource/user/user.zul?userName="+URLEncoder.encode(value,"UTF-8"); 
	}


	@Override
	public String[] toNameDescription(User o) {
		return new String[] {o.getUserName(), o.getFullName()};
	}

	@Override
	public User getObject(String name, String filter) throws Exception {
		String q = "userName eq \"" + quote(name)+ "\" ";
		if (filter != null && ! filter.trim().isEmpty())
			q = "("+filter+") and ("+q+")";
		List<User> r = handler.read(null, q, null, 2).getResources();
		if (! r.isEmpty())
			return r.iterator().next();
		else
			return null;
	}
	@Override
	protected String getClassName() {
		return User.class.getName();
	}

}
