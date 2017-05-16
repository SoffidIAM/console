package com.soffid.iam.web.tenant;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.Tenant;
import com.soffid.iam.service.ejb.DispatcherService;
import com.soffid.iam.service.ejb.TenantService;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.datamodel.DataContext;
import es.caib.zkib.datamodel.xml.handler.FinderHandler;
import es.caib.zkib.datamodel.xml.handler.PersistenceHandler;

public class ServersHandler implements PersistenceHandler , FinderHandler {

	private TenantService ejb;

	public ServersHandler() throws NamingException, CreateException {
		super();
		ejb = EJBLocator.getTenantService();
	}

	public static StringInfo newServer () throws InternalErrorException, NamingException, CreateException
	{
		StringInfo d = new StringInfo();
		d.setNewValue("");
		d.setOldValue(null);
		return d;
	}

	public Collection find(DataContext arg0) throws Exception {
		Tenant tenant = (Tenant) arg0.getData();
		List<StringInfo> result = new LinkedList<StringInfo>();
		for (String s: ejb.getTenantServers(tenant))
		{
			StringInfo d = new StringInfo();
			d.setNewValue(s);
			d.setOldValue(s);
			result.add(d);
		}
		return result;
	}

	public void doDelete(DataContext ctx) throws Exception {
		Tenant tenant = (Tenant) ctx.getParent().getDataContext().getData();
		StringInfo info = (StringInfo) ctx.getData();
		if (info.oldValue != null && ! info.oldValue.isEmpty())
			ejb.removeTenantServer(tenant, info.oldValue);
	}

	public void doInsert(DataContext ctx) throws Exception {
		Tenant tenant = (Tenant) ctx.getParent().getDataContext().getData();
		StringInfo info = (StringInfo) ctx.getData();
		if (info.newValue != null && ! info.newValue.isEmpty())
			ejb.addTenantServer(tenant, info.newValue);
	}

	public void doUpdate(DataContext ctx) throws Exception {
		StringInfo info = (StringInfo) ctx.getData();
		if (! info.oldValue.equals(info.newValue))
		{
			doDelete(ctx);
			doInsert(ctx);
		}
	}

	public boolean isSuitable(DataContext arg0) {
		return true;
	}
}
