package com.soffid.iam.web.component.inputField;

import java.util.List;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.zk.ui.event.EventListener;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.Role;
import com.soffid.iam.api.AsyncList;
import com.soffid.iam.api.CrudHandler;
import com.soffid.iam.api.DataType;
import com.soffid.iam.web.component.InputField3;
import com.soffid.iam.web.popup.FinderHandler;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.component.Databox;

public class RoleDataHandler extends InputFieldDataHandler<Role> {

	private CrudHandler<Role> handler;

	public RoleDataHandler(DataType dataType) throws InternalErrorException, NamingException, CreateException {
		super (dataType);
		handler = EJBLocator.getCrudRegistryService().getHandler(Role.class);
	}

	@Override
	public String getDescription(String name, String filter) throws Exception {
		int i = name.lastIndexOf('@');
		String roleName = i < 0 ? name: name.substring(0,  i);
		String systemName = i < 0 ? "soffid": name.substring(i+1);
		String q = "name eq \"" + quote(roleName)+ "\" and system eq \""+quote(systemName)+"\"";
		if (filter != null && ! filter.trim().isEmpty())
			q = "("+filter+") and ("+q+")";
		List<Role> r = handler.read(null, q, null, null);
		if (! r.isEmpty())
			return r.iterator().next().getDescription();
		else
			return null;
	}

	@Override
	public AsyncList<Role> search(String text, String filter) throws Exception {
		return handler.readAsync(text, filter);
	}

	@Override
	public void openFinder(String filter, boolean multiple, Databox databox, EventListener listener) throws Exception {
		FinderHandler.startWizard("Select role", Role.class.getName(),
				databox, multiple, 
				filter,
				listener);
		
	}
	@Override
	public void followLink(String value) {
		// TODO Auto-generated method stub
	}

	@Override
	public String[] toNameDescription(Role o) {
		return new String[] {o.getName()+"@"+o.getSystem(), o.getDescription()};
	}

	@Override
	public Role getObject(String name, String filter) throws Exception {
		int i = name.lastIndexOf('@');
		String roleName = i < 0 ? name: name.substring(0,  i);
		String systemName = i < 0 ? "soffid": name.substring(i+1);
		String q = "name eq \"" + quote(roleName)+ "\" and system eq \""+quote(systemName)+"\"";
		if (filter != null && ! filter.trim().isEmpty())
			q = "("+filter+") and ("+q+")";
		List<Role> r = handler.read(null, q, null, null);
		if (! r.isEmpty())
			return r.iterator().next();
		else
			return null;
	}

}
