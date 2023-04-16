package com.soffid.iam.service.crud;

import java.util.List;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.AsyncList;
import com.soffid.iam.api.CrudHandler;
import com.soffid.iam.api.PagedResult;
import com.soffid.iam.api.Audit;
import com.soffid.iam.service.ejb.AuditService;

import es.caib.seycon.ng.exception.InternalErrorException;

public class CrudAuditHandler implements CrudHandler<Audit> {
	private AuditService ejb;

	public AuditService getService() throws NamingException, CreateException {
		if (ejb == null)
			ejb = EJBLocator.getAuditService();
		return ejb;
	}
	
	@Override
	public Audit create(Audit object) throws InternalErrorException, NamingException, CreateException {
		throw new InternalErrorException("Method not supported");
	}

	@Override
	public PagedResult<Audit> read(String text, String filter, Integer start, Integer end) throws InternalErrorException, NamingException, CreateException {
		return getService().findAuditByJsonQuery(filter, start, end);
	}

	@Override
	public AsyncList<Audit> readAsync(String text, String filter) throws InternalErrorException, NamingException, CreateException {
		return getService().findAuditByJsonQueryAsync(filter);
	}

	@Override
	public Audit update(Audit object) throws InternalErrorException, NamingException, CreateException {
		throw new InternalErrorException("Method not supported");
	}

	@Override
	public void delete(Audit object) throws InternalErrorException, NamingException, CreateException {
		throw new InternalErrorException("Method not supported");
	}

}
