package com.soffid.iam.service.crud;

import java.util.List;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.Account;
import com.soffid.iam.api.AsyncList;
import com.soffid.iam.api.CrudHandler;
import com.soffid.iam.api.PagedResult;
import com.soffid.iam.service.ejb.AccountService;

import es.caib.seycon.ng.exception.AccountAlreadyExistsException;
import es.caib.seycon.ng.exception.InternalErrorException;

public class CrudAccountHandler implements CrudHandler<Account> {
	private AccountService ejb;

	public AccountService getService() throws NamingException, CreateException {
		if (ejb == null)
			ejb = EJBLocator.getAccountService();
		return ejb;
	}
	
	@Override
	public Account create(Account object) throws InternalErrorException, NamingException, CreateException, AccountAlreadyExistsException {
		return getService().createAccount2(object);
	}

	@Override
	public PagedResult<Account> read(String text, String filter, Integer start, Integer end) throws InternalErrorException, NamingException, CreateException {
		return getService().findAccountByTextAndJsonQuery(text, filter, start, end);
	}

	@Override
	public AsyncList<Account> readAsync(String text, String filter) throws InternalErrorException, NamingException, CreateException {
		return getService().findAccountByTextAndJsonQueryAsync(text, filter);
	}

	@Override
	public Account update(Account object) throws InternalErrorException, NamingException, CreateException, AccountAlreadyExistsException {
		getService().updateAccount2(object);
		return object;
	}

	@Override
	public void delete(Account object) throws InternalErrorException, NamingException, CreateException {
		getService().removeAccount(object);
	}

}
