package com.soffid.iam.webservice;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.soffid.iam.api.Account;
import com.soffid.iam.api.Role;
import com.soffid.iam.api.RoleAccount;
import com.soffid.iam.service.ejb.AccountService;
import com.soffid.iam.service.ejb.ApplicationService;
import com.soffid.iam.service.ejb.DispatcherService;
import com.soffid.iam.service.ejb.UserService;
import com.soffid.iam.webservice.account.AccountQuery;
import com.soffid.iam.webservice.account.ExtendedAccount;
import com.soffid.iam.webservice.account.RoleDomain;

import es.caib.seycon.ng.exception.AccountAlreadyExistsException;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.exception.NeedsAccountNameException;

@Path("/scim/account")
@Produces({"text/xml", "application/json"})
public class AccountSvc {
	@EJB UserService userSvc;
	
	@EJB AccountService svc;
	
	@EJB ApplicationService appSvc;
	
	@EJB DispatcherService dispatcherSvc;

	@Path("")
    @GET
    public AccountQuery list(@QueryParam("query") @DefaultValue("") String query,
    		@QueryParam("attributes") String atts) throws InternalErrorException {
        AccountQuery uq = new AccountQuery();
        uq.setResources (toExtendedAccountList (svc.findAccountByJsonQuery(query)));
        uq.setTotalResults( uq.getResources().size() );
        return uq;
    }

    private Collection<ExtendedAccount> toExtendedAccountList(
			Collection<Account> list) throws InternalErrorException {
    	LinkedList<ExtendedAccount> r = new LinkedList<ExtendedAccount>();
		for ( Account u : list)
    	{
    		r.add(toExtendedAccount(u));
    	}
		return r;
	}

	private ExtendedAccount toExtendedAccount(Account acc) throws InternalErrorException {
		ExtendedAccount eacc = new ExtendedAccount(acc);
		List<RoleDomain> perms = new LinkedList<RoleDomain>();
		for (RoleAccount data: appSvc.findRoleAccountByAccount(acc.getId()))
		{
			RoleDomain perm = new RoleDomain();
			perm.setDomainValue(data.getDomainValue().getValue());
			Role r = appSvc.findRoleByNameAndSystem(data.getRoleName(), data.getSystem());
			perm.setRole(r.getId());
			perms.add(perm);
		}
		eacc.setRoles(perms);
		return eacc;
	}

	@Path("")
    @POST
    public Response create(ExtendedAccount account, @Context HttpServletRequest request) throws URISyntaxException  {
		try {
			Account newAccount = svc.createAccount(account);
	    	if (newAccount != null)
	    	{
	    		ExtendedAccount a = toExtendedAccount(newAccount);
	    		return Response
	    				.created( new URI(a.getMeta().getLocation()))
	    				.entity( a )
	    				.build();
	    		
	    	}
	    	else
	    		return Response.status(Status.NOT_FOUND).build();
		} catch (Exception e) {
    		return Response.serverError().entity(e.toString()).build();
		}
    }

    @Path("/{id}")
    @GET
    public Response show(
    		@PathParam("id") long id
    		)  {
        Account user;
		try {
			user = svc.findAccountById(id);
	    	if (user != null)
	    		return Response.ok( toExtendedAccount(user)).build();
	    	else
	    		return Response.status(Status.NOT_FOUND).build();
		} catch (InternalErrorException e) {
    		return Response.serverError().entity(e.toString()).build();
		}
    }

    @Path("/{id}")
    @DELETE
    public Response delete(@PathParam("id") long id
    		)  {
        Account user;
		try {
			user = svc.findAccountById(id);
	    	if (user != null)
	    	{
	    		svc.removeAccount(user);
	    		return Response.status(Status.NO_CONTENT).build();
	    	}
	    	else
	    		return Response.status(Status.NOT_FOUND).build();
		} catch (InternalErrorException e) {
    		return Response.serverError().entity(e.toString()).build();
		}
    }

    @Path("/{id}")
    @PUT
    public Response update(@PathParam("id") long id,
    		ExtendedAccount user
    		)  {
        Account user2;
		try {
			user2 = svc.findAccountById(id);
	    	if (user == null)
	    		return Response.status(Status.NOT_FOUND).build();
	    	user2.setAccessLevel(user.getAccessLevel() );
	    	user2.setAttributes(user.getAttributes());
	    	user2.setDescription(user.getDescription());
	    	user2.setDisabled(user.isDisabled());
	    	user2.setGrantedGroups(user.getGrantedGroups());
	    	user2.setGrantedRoles(user.getGrantedRoles());
	    	user2.setGrantedUsers(user.getGrantedUsers());
	    	user2.setId(user.getId());
	    	user2.setInheritNewPermissions(user.isInheritNewPermissions());
	    	user2.setLoginUrl(user.getLoginUrl());
	    	user2.setManagerGroups(user.getManagerGroups());
	    	user2.setManagerRoles(user.getManagerRoles());
	    	user2.setManagerUsers(user.getManagerUsers());
	    	user2.setName(user.getName());
	    	user2.setOwnerGroups(user.getOwnerGroups());
	    	user2.setOwnerRoles(user.getOwnerRoles());
	    	user2.setOwnerUsers(user.getOwnerUsers());
	    	user2.setPasswordPolicy(user.getPasswordPolicy());
	    	user2.setSystem(user.getSystem());
	    	user2.setVaultFolder(user.getVaultFolder());
	    	user2.setVaultFolderId(user.getVaultFolderId());
	    	user2 = svc.updateAccount(user2);
	    	
	    	updateRoles(user, user2);
	    	
	    	return Response.ok().entity( toExtendedAccount(user2)).build();
		} catch (Exception e) {
    		return Response.serverError().entity(e.toString()).build();
		}
    }
    
    private void updateRoles (ExtendedAccount src, Account target) throws InternalErrorException, NeedsAccountNameException, AccountAlreadyExistsException
    {
    	Collection<RoleAccount> accounts = appSvc.findRoleAccountByAccount(target.getId());
    	for (RoleAccount ua: accounts)
    	{
    		if ( ua.getRuleId() == null)
    		{
				Role role = appSvc.findRoleByNameAndSystem(ua.getRoleName(), ua.getSystem());
	    		boolean found = false;
	    		for ( RoleDomain ua2: src.getRoles())
	    		{
	    			if (ua2.getRole() == role.getId().longValue())
	    			{
	    				if (ua2.getDomainValue() == null || ua2.getDomainValue().trim().isEmpty() ?
	    						ua.getDomainValue().getValue() == null :
	    						ua2.getDomainValue().equals(ua.getDomainValue().getValue()))
	    				{
		    				found = true;
		    				break;
	    				}
	    			}
	    		}
	    		if (!found)
	    		{
	    			appSvc.delete(ua);
	    		}
    		}
    	}
		for ( RoleDomain ua2: src.getRoles())
    	{
    		boolean found = false;
        	for (RoleAccount ua: accounts)
    		{
    			Role role = appSvc.findRoleByNameAndSystem(ua.getRoleName(), ua.getSystem());
    			if (ua2.getRole() == role.getId().longValue())
    			{
    				if (ua2.getDomainValue() == null || ua2.getDomainValue().trim().isEmpty() ?
    						ua.getDomainValue().getValue() == null :
    						ua2.getDomainValue().equals(ua.getDomainValue().getValue()))
    				{
	    				found = true;
	    				break;
    				}
    			}
    		}
    		if (!found)
    		{
    			Role role = appSvc.findRoleById(ua2.getRole());
    			RoleAccount ra = new RoleAccount();
    			ra.setAccountName(target.getName());
    			ra.setSystem(target.getSystem());
    			ra.setRoleName(role.getName());
    			ra.setInformationSystemName(role.getInformationSystemName());
    			ra.setAccountSystem(target.getSystem());
    		}
    	}
    }
}


