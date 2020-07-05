package com.soffid.iam.web.component.inputField;

import java.io.IOException;
import java.util.List;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.zk.ui.event.EventListener;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.AsyncList;
import com.soffid.iam.api.CrudHandler;
import com.soffid.iam.api.DataType;
import com.soffid.iam.api.DomainValue;
import com.soffid.iam.web.component.InputField3;
import com.soffid.iam.web.popup.FinderHandler;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.component.Databox;

public class DomainValueDataHandler extends InputFieldDataHandler<DomainValue> {

	private CrudHandler<DomainValue> handler;
	private String app;
	private String domain;

	public DomainValueDataHandler(DataType dataType, String app, String domain) throws InternalErrorException, NamingException, CreateException {
		super (dataType);
		this.app = app;
		this.domain = domain;
		handler = EJBLocator.getCrudRegistryService().getHandler(DomainValue.class);
	}

	@Override
	public String getDescription(String name, String filter) throws InternalErrorException, NamingException, CreateException {
		String q = "name eq \"" + quote(name)+ "\" and domain.name eq \""+quote(domain)+"\" and domain.informationSystem.name eq \""+app+"\"";
		if (filter != null && ! filter.trim().isEmpty())
			q = "("+filter+") and ("+q+")";
		List<DomainValue> r = handler.read(null, q, null, null);
		if (! r.isEmpty())
			return r.iterator().next().getDescription();
		else
			return null;
	}

	@Override
	public AsyncList<DomainValue> search(String text, String filter) throws InternalErrorException, NamingException, CreateException {
		return handler.readAsync(text, filter);
	}

	@Override
	public void openFinder(String filter, boolean multiple, Databox databox, EventListener listener) throws Exception {
		String q = "domain.name eq \""+quote(domain)+"\" and domain.informationSystem.name eq \""+app+"\"";
		if (filter != null && ! filter.trim().isEmpty())
			q = "("+filter+") and ("+q+")";
		FinderHandler.startWizard("Select Domain value", DomainValue.class.getName(),
				databox, multiple, 
				q,
				listener);
		
	}

	@Override
	public void followLink(String value) {
		// TODO Auto-generated method stub
	}

	@Override
	public String[] toNameDescription(DomainValue o) {
		return new String[] {o.getValue(), o.getDescription()};
	}


}
