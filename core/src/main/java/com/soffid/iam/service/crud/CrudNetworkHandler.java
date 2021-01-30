package com.soffid.iam.service.crud;

import java.util.List;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.AsyncList;
import com.soffid.iam.api.CrudHandler;
import com.soffid.iam.api.Network;
import com.soffid.iam.api.PagedResult;
import com.soffid.iam.service.ejb.NetworkService;

import es.caib.seycon.ng.exception.InternalErrorException;

public class CrudNetworkHandler implements CrudHandler<Network> {
	private NetworkService ejb;

	public NetworkService getService() throws NamingException, CreateException {
		if (ejb == null)
			ejb = EJBLocator.getNetworkService();
		return ejb;
	}
	
	@Override
	public Network create(Network object) throws InternalErrorException, NamingException, CreateException {
		return getService().create(object);
	}

	@Override
	public PagedResult<Network> read(String text, String filter, Integer start, Integer end) throws InternalErrorException, NamingException, CreateException {
		return getService().findNetworkByTextAndJsonQuery(text, filter, start, end);
	}

	@Override
	public AsyncList<Network> readAsync(String text, String filter) throws InternalErrorException, NamingException, CreateException {
		return getService().findNetworkByTextAndJsonQueryAsync(text, filter);
	}

	@Override
	public Network update(Network object) throws InternalErrorException, NamingException, CreateException {
		getService().update(object);
		return object;
	}

	@Override
	public void delete(Network object) throws InternalErrorException, NamingException, CreateException {
		getService().delete(object);
	}

}
