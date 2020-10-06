package com.soffid.iam.web.component.inputField;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.zk.ui.event.EventListener;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.MailList;
import com.soffid.iam.web.component.InputField3;
import com.soffid.iam.web.popup.FinderHandler;
import com.soffid.iam.api.AsyncList;
import com.soffid.iam.api.CrudHandler;
import com.soffid.iam.api.DataType;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.component.Databox;

public class MailListDataHandler extends InputFieldDataHandler<MailList> {

	private CrudHandler<MailList> handler;

	public MailListDataHandler(DataType dataType) throws InternalErrorException, NamingException, CreateException {
		super (dataType);
		handler = EJBLocator.getCrudRegistryService().getHandler(MailList.class);
	}

	@Override
	public String getDescription(String name, String filter) throws Exception {
		int i = name.lastIndexOf('@');
		String listName = i < 0 ? name: name.substring(0,  i);
		String domainName = i < 0 ? "soffid": name.substring(i+1);
		String q = "name eq \"" + quote(listName)+ "\" and domainName eq \""+quote(domainName)+"\"";
		if (filter != null && ! filter.trim().isEmpty())
			q = "("+filter+") and ("+q+")";
		List<MailList> r = handler.read(null, q, null, null);
		if (! r.isEmpty())
			return r.iterator().next().getDescription();
		else
			return null;
	}

	@Override
	public AsyncList<MailList> search(String text, String filter) throws Exception {
		return handler.readAsync(text, filter);
	}

	@Override
	public void openFinder(String filter, boolean multiple, Databox databox, EventListener listener) throws Exception {
		FinderHandler.startWizard("Select mail domain", MailList.class.getName(),
				databox, multiple, 
				filter,
				listener);
		
	}

	@Override
	public String followLink(String value) throws UnsupportedEncodingException {
		return "/soffid/resource/maillist/mail-list.zul?name="+URLEncoder.encode(value,"UTF-8"); 
	}

	@Override
	public String[] toNameDescription(MailList o) {
		return new String[] {o.getName()+"@"+o.getDomainName(), o.getDescription()};
	}

	@Override
	public MailList getObject(String name, String filter) throws Exception {
		int i = name.lastIndexOf('@');
		String listName = i < 0 ? name: name.substring(0,  i);
		String domainName = i < 0 ? "": name.substring(i+1);
		String q = "name eq \"" + quote(listName)+ "\" and domainName eq \""+quote(domainName)+"\"";
		if (filter != null && ! filter.trim().isEmpty())
			q = "("+filter+") and ("+q+")";
		List<MailList> r = handler.read(null, q, null, null);
		if (! r.isEmpty())
			return r.iterator().next();
		else
			return null;
	}

}
