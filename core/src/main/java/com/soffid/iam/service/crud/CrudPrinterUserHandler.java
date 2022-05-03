package com.soffid.iam.service.crud;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.AsyncList;
import com.soffid.iam.api.CrudHandler;
import com.soffid.iam.api.PagedResult;
import com.soffid.iam.api.PrinterUser;
import com.soffid.iam.service.ejb.PrinterService;

import es.caib.seycon.ng.exception.InternalErrorException;

public class CrudPrinterUserHandler implements CrudHandler<PrinterUser> {
	private PrinterService ejb;

	public PrinterService getService() throws NamingException, CreateException {
		if (ejb == null)
			ejb = EJBLocator.getPrinterService();
		return ejb;
	}
	
	@Override
	public PrinterUser create(PrinterUser object) throws InternalErrorException, NamingException, CreateException {
		return getService().create(object);
	}

	@Override
	public PagedResult<PrinterUser> read(String text, String filter, Integer start, Integer end) throws InternalErrorException, NamingException, CreateException {
		return getService().findPrinterUserByTextAndJsonQuery(text, filter, start, end);
	}

	@Override
	public AsyncList<PrinterUser> readAsync(String text, String filter) throws InternalErrorException, NamingException, CreateException {
		return getService().findPrinterUserByTextAndJsonQueryAsync(text, filter);
	}

	@Override
	public PrinterUser update(PrinterUser object) throws InternalErrorException, NamingException, CreateException {
		return getService().update(object);
	}

	@Override
	public void delete(PrinterUser object) throws InternalErrorException, NamingException, CreateException {
		getService().delete(object);
	}

}
