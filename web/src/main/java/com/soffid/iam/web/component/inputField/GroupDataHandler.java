package com.soffid.iam.web.component.inputField;

import java.util.List;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.zk.ui.event.EventListener;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.Group;
import com.soffid.iam.web.component.InputField3;
import com.soffid.iam.web.popup.FinderHandler;
import com.soffid.iam.api.AsyncList;
import com.soffid.iam.api.CrudHandler;
import com.soffid.iam.api.DataType;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.component.Databox;

public class GroupDataHandler extends InputFieldDataHandler<Group> {

	private CrudHandler<Group> handler;

	public GroupDataHandler(DataType dataType) throws InternalErrorException, NamingException, CreateException {
		super (dataType);
		handler = EJBLocator.getCrudRegistryService().getHandler(Group.class);
	}

	@Override
	public String getDescription(String name, String filter) throws InternalErrorException, NamingException, CreateException {
		String q = "name eq \"" + quote(name)+ "\" ";
		if (filter != null && ! filter.trim().isEmpty())
			q = "("+filter+") and ("+q+")";
		List<Group> r = handler.read(null, q, null, null);
		if (! r.isEmpty())
			return r.iterator().next().getDescription();
		else
			return null;
	}

	@Override
	public AsyncList<Group> search(String text, String filter) throws InternalErrorException, NamingException, CreateException {
		return handler.readAsync(text, filter);
	}

	@Override
	public void openFinder(String filter, boolean multiple, Databox databox, EventListener listener) throws Exception {
		FinderHandler.startWizard("Select group", Group.class.getName(),
				databox, multiple, 
				filter,
				listener);
		
	}

	@Override
	public void followLink(String value) {
		// TODO Auto-generated method stub
	}

	@Override
	public String[] toNameDescription(Group o) {
		return new String[] {o.getName(), o.getDescription()};
	}

}
