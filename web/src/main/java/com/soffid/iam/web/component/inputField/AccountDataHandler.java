package com.soffid.iam.web.component.inputField;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.zk.ui.event.EventListener;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.Account;
import com.soffid.iam.api.AsyncList;
import com.soffid.iam.api.CrudHandler;
import com.soffid.iam.api.DataType;
import com.soffid.iam.web.component.InputField3;
import com.soffid.iam.web.popup.FinderHandler;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.component.Databox;

public class AccountDataHandler extends InputFieldDataHandler<Account> {

	private CrudHandler<Account> handler;

	public AccountDataHandler(DataType dataType) throws InternalErrorException, NamingException, CreateException {
		super (dataType);
		handler = EJBLocator.getCrudRegistryService().getHandler(Account.class);
	}

	@Override
	public String getDescription(String name, String filter) throws Exception {
		int i = name.lastIndexOf('@');
		String accountName = i < 0 ? name: name.substring(0,  i);
		String systemName = i < 0 ? "soffid": name.substring(i+1);
		String q = "name eq \"" + quote(accountName)+ "\" and system eq \""+quote(systemName)+"\"";
		if (filter != null && ! filter.trim().isEmpty())
			q = "("+filter+") and ("+q+")";
		List<Account> r = handler.read(null, q, null, 2).getResources();
		if (! r.isEmpty())
			return r.iterator().next().getDescription();
		else
			return null;
	}

	@Override
	public AsyncList<Account> search(String text, String filter) throws Exception {
		return handler.readAsync(text, filter);
	}

	@Override
	public void openFinder(String filter, boolean multiple, Databox databox, EventListener listener) throws Exception {
		FinderHandler.startWizard("Select account", Account.class.getName(),
				databox, multiple, 
				filter,
				listener);
		
	}
	@Override
	public String followLink(String name) throws UnsupportedEncodingException {
		int i = name.lastIndexOf('@');
		String accountName = i < 0 ? name: name.substring(0,  i);
		String systemName = i < 0 ? "soffid": name.substring(i+1);
		return "/soffid/resource/account/account.zul?name="+URLEncoder.encode(accountName,"UTF-8")+"&system="+URLEncoder.encode(systemName, "UTF-8"); 
	}

	@Override
	public String[] toNameDescription(Account o) {
		return new String[] {o.getName()+"@"+o.getSystem(), o.getDescription()};
	}

	@Override
	public Account getObject(String name, String filter) throws Exception {
		int i = name.lastIndexOf('@');
		String accountName = i < 0 ? name: name.substring(0,  i);
		String systemName = i < 0 ? "soffid": name.substring(i+1);
		String q = "name eq \"" + quote(accountName)+ "\" and system eq \""+quote(systemName)+"\"";
		if (filter != null && ! filter.trim().isEmpty())
			q = "("+filter+") and ("+q+")";
		List<Account> r = handler.read(null, q, null, 2).getResources();
		if (! r.isEmpty())
			return r.iterator().next();
		else
			return null;
	}

}
