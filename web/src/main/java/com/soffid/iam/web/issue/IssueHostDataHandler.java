package com.soffid.iam.web.issue;

import java.io.UnsupportedEncodingException;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.EventListener;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.AsyncList;
import com.soffid.iam.api.DataType;
import com.soffid.iam.api.Host;
import com.soffid.iam.api.IssueHost;
import com.soffid.iam.api.User;
import com.soffid.iam.web.component.inputField.HostDataHandler;
import com.soffid.iam.web.component.inputField.InputFieldDataHandler;
import com.soffid.iam.web.component.inputField.UserDataHandler;

import es.caib.seycon.ng.exception.InternalErrorException;


public class IssueHostDataHandler extends InputFieldDataHandler<IssueHost> {
	private HostDataHandler dataHandler;

	public IssueHostDataHandler(DataType dataType) throws InternalErrorException, NamingException, CreateException {
		super(dataType);
		dataHandler = new HostDataHandler(dataType);
	}

	@Override
	public IssueHost getObject(String name, String filter) throws Exception {
		IssueHost iu = new IssueHost();
		iu.setHostName(name);
		return iu;
	}

	@Override
	public String getDescription(String name, String filter) throws Exception {
		return dataHandler.getDescription(name, filter);
	}

	@Override
	public AsyncList<IssueHost> search(String text, String filter) throws Exception {
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
	public String[] toNameDescription(IssueHost o) {
		if (o.getHostId() != null) {
			Host u;
			try {
				u = EJBLocator.getNetworkService().findHostById(o.getHostId());
				if (u != null) {
					return new String[] {u.getName(), u.getDescription()};
				}
			} catch (Exception e) {
			}
		}
		else if (o.getHostName() != null) {
			Host u;
			try {
				u = EJBLocator.getNetworkService().findHostByName(o.getHostName());
				if (u != null) {
					return new String[] {u.getName(), u.getDescription()};
				}
			} catch (Exception e) {
			}
		}
		return new String[] { o.getHostName(), ""};
	}

}
