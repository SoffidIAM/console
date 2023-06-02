package com.soffid.iam.web.issue;

import java.io.UnsupportedEncodingException;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.EventListener;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.AsyncList;
import com.soffid.iam.api.DataType;
import com.soffid.iam.api.IssueUser;
import com.soffid.iam.api.User;
import com.soffid.iam.web.component.inputField.InputFieldDataHandler;
import com.soffid.iam.web.component.inputField.UserDataHandler;

import es.caib.seycon.ng.exception.InternalErrorException;


public class IssueUserDataHandler extends InputFieldDataHandler<IssueUser> {
	private UserDataHandler dataHandler;

	public IssueUserDataHandler(DataType dataType) throws InternalErrorException, NamingException, CreateException {
		super(dataType);
		dataHandler = new UserDataHandler(dataType);
	}

	@Override
	public IssueUser getObject(String name, String filter) throws Exception {
		IssueUser iu = new IssueUser();
		iu.setUserName(name);
		return iu;
	}

	@Override
	public String getDescription(String name, String filter) throws Exception {
		return dataHandler.getDescription(name, filter);
	}

	@Override
	public AsyncList<IssueUser> search(String text, String filter) throws Exception {
		throw new RuntimeException("Method not supported");
	}

	@Override
	public void openFinder(String filter, boolean multiple, Component databox, EventListener listener) throws Exception {
		// Nothing to do
	}

	@Override
	public String followLink(String value) throws UnsupportedEncodingException {
		return dataHandler.followLink(value);
	}

	@Override
	public String[] toNameDescription(IssueUser o) {
		if (o.getUserId() != null) {
			User u;
			try {
				u = EJBLocator.getUserService().findUserByUserId(o.getUserId());
				if (u != null) {
					return new String[] {u.getUserName(), u.getFullName()};
				}
			} catch (Exception e) {
			}
		}
		else if (o.getUserName() != null) {
			User u;
			try {
				u = EJBLocator.getUserService().findUserByUserName(o.getUserName());
				if (u != null) {
					return new String[] {u.getUserName(), u.getFullName()};
				}
			} catch (Exception e) {
			}
		}
		return new String[] { o.getUserName(), ""};
	}

}
