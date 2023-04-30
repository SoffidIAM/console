package com.soffid.iam.web.component.inputField;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.EventListener;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.Host;
import com.soffid.iam.web.component.InputField3;
import com.soffid.iam.web.popup.FinderHandler;
import com.soffid.iam.api.Account;
import com.soffid.iam.api.AsyncList;
import com.soffid.iam.api.CrudHandler;
import com.soffid.iam.api.DataType;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.component.Databox;

public class HostDataHandler extends InputFieldDataHandler<Host> {

	private CrudHandler<Host> handler;

	public HostDataHandler(DataType dataType) throws InternalErrorException, NamingException, CreateException {
		super (dataType);
		handler = EJBLocator.getCrudRegistryService().getHandler(Host.class);
	}

	@Override
	public String getDescription(String name, String filter) throws Exception {
		String q = "name eq \"" + quote(name)+ "\" ";
		if (filter != null && ! filter.trim().isEmpty())
			q = "("+filter+") and ("+q+")";
		List<Host> r = handler.read(null, q, null, 2).getResources();
		
		if (! r.isEmpty())
			return r.iterator().next().getDescription();
		else
			return null;
	}

	@Override
	public AsyncList<Host> search(String text, String filter) throws Exception {
		return handler.readAsync(text, filter);
	}

	@Override
	public void openFinder(String filter, boolean multiple, Component databox, EventListener listener) throws Exception {
		FinderHandler.startWizard("Select host", Host.class.getName(),
				databox, multiple, 
				filter,
				listener);
		
	}

	@Override
	public String followLink(String value) throws UnsupportedEncodingException {
		return "/soffid/resource/host/host.zul?name="+URLEncoder.encode(value,"UTF-8"); 
	}


	@Override
	public String[] toNameDescription(Host o) {
		return new String[] {o.getName(), o.getDescription()};
	}

	@Override
	public Host getObject(String name, String filter) throws Exception {
		String q = "name eq \"" + quote(name)+ "\" ";
		if (filter != null && ! filter.trim().isEmpty())
			q = "("+filter+") and ("+q+")";
		List<Host> r = handler.read(null, q, null, 2).getResources();
		
		if (! r.isEmpty())
			return r.iterator().next();
		else
			return null;
	}

	@Override
	protected String getClassName() {
		return Host.class.getName();
	}
}
