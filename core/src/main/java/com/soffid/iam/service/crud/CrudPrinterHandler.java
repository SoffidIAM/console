package com.soffid.iam.service.crud;

import java.util.List;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.AsyncList;
import com.soffid.iam.api.CrudHandler;
import com.soffid.iam.api.Printer;
import com.soffid.iam.api.PagedResult;
import com.soffid.iam.service.ejb.PrinterService;
import com.soffid.iam.service.ejb.PrinterService;

import es.caib.seycon.ng.exception.InternalErrorException;

public class CrudPrinterHandler implements CrudHandler<Printer> {
	private PrinterService ejb;

	public PrinterService getService() throws NamingException, CreateException {
		if (ejb == null)
			ejb = EJBLocator.getPrinterService();
		return ejb;
	}
	
	@Override
	public Printer create(Printer object) throws InternalErrorException, NamingException, CreateException {
		return getService().create(object);
	}

	@Override
	public PagedResult<Printer> read(String text, String filter, Integer start, Integer end) throws InternalErrorException, NamingException, CreateException {
		PagedResult<Printer> p = new PagedResult<>();
		p.setResources ( getService().findPrinterByTextAndJsonQuery(text, filter, start, end));
		p.setStartIndex(start);
		p.setItemsPerPage(end);
		p.setTotalResults(p.getResources().size());
		return p;
	}

	@Override
	public AsyncList<Printer> readAsync(String text, String filter) throws InternalErrorException, NamingException, CreateException {
		return getService().findPrinterByTextAndJsonQueryAsync(text, filter);
	}

	@Override
	public Printer update(Printer object) throws InternalErrorException, NamingException, CreateException {
		return getService().update(object);
	}

	@Override
	public void delete(Printer object) throws InternalErrorException, NamingException, CreateException {
		getService().delete(object);
	}

}
