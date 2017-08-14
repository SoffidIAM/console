package com.soffid.iam.webservice;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
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

import com.soffid.iam.api.Password;
import com.soffid.iam.api.User;
import com.soffid.iam.api.UserAccount;
import com.soffid.iam.api.UserData;
import com.soffid.iam.service.ejb.AccountService;
import com.soffid.iam.service.ejb.AdditionalDataService;
import com.soffid.iam.service.ejb.ApplicationService;
import com.soffid.iam.service.ejb.DispatcherService;
import com.soffid.iam.service.ejb.UserService;
import com.soffid.iam.webservice.user.ExtendedUser;
import com.soffid.iam.webservice.user.JsonAccount;
import com.soffid.iam.webservice.user.UserQuery;

import es.caib.seycon.ng.exception.AccountAlreadyExistsException;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.exception.NeedsAccountNameException;
import es.caib.seycon.util.Base64;

@Path("/scim/User")
@Produces({"application/scim+json","application/json"})
@Consumes({"application/scim+json","application/json"})
public class UserSvc {
	@EJB UserService svc;
	
	@EJB AccountService accountSvc;
	
	@EJB ApplicationService appSvc;
	
	@EJB DispatcherService dispatcherSvc;

	@EJB AdditionalDataService dataSvc;

	@Path("")
    @GET
    public UserQuery list(@QueryParam("filter") @DefaultValue("") String query,
    		@QueryParam("attributes") String atts) throws InternalErrorException {
        UserQuery uq = new UserQuery();
        uq.setResources (toExtendedUserList (svc.findUserByJsonQuery(query)));
        uq.setTotalResults (uq.getResources().size());
        return uq;
    }

    private Collection<ExtendedUser> toExtendedUserList(
			Collection<User> list) throws InternalErrorException {
    	LinkedList<ExtendedUser> r = new LinkedList<ExtendedUser>();
		for ( User u : list)
    	{
    		r.add(toExtendedUser(u));
    	}
		return r;
	}

	private ExtendedUser toExtendedUser(User u) throws InternalErrorException {
		ExtendedUser eu = new ExtendedUser(u);

		for (UserData data: svc.findUserDataByUserName(u.getUserName()))
		{
			if (data.getDateValue() != null)
				eu.getAttributes().put(data.getAttribute(), data.getDateValue().getTime());
			else if (data.getBlobDataValue() != null)
				eu.getAttributes().put(data.getAttribute(), Base64.encodeBytes(data.getBlobDataValue(), Base64.DONT_BREAK_LINES));
			else
				eu.getAttributes().put(data.getAttribute(), data.getValue());
		}
		
		for (UserAccount acc: accountSvc.getUserAccounts(u))
		{
			JsonAccount js = new JsonAccount();
			js.setName(acc.getName());
			js.setId(acc.getId());
			js.setSystem(acc.getSystem());
			eu.getAccounts().add(js);
		}
		
		ScimMeta meta = eu.getMeta();
		meta.setLocation(getClass(), u.getId().toString());
		meta.setCreated(u.getCreatedDate().getTime());
		meta.setLastModified(u.getModifiedDate().getTime());
		meta.setResourceType("User"); //$NON-NLS-1$
		return eu;
	}

	@Path("")
    @POST
    public Response create(ExtendedUser user, @Context HttpServletRequest request) throws URISyntaxException  {
		try {
			User newUser = svc.create(user);
	    	if (newUser != null)
	    	{
				updateAccounts(user, newUser);
		    	updateAttributes(user, newUser, true);
				ExtendedUser eu = toExtendedUser(newUser);
	    		return SCIMResponseBuilder.responseOk(eu, new URI (eu.getMeta().getLocation()));
	    	}
	    	else
	    		return SCIMResponseBuilder.responseOnlyHTTP(Status.NOT_FOUND);
		} catch (EJBException e) {
			if (e.getMessage().contains("porque ya existe uno con este código.")) { //$NON-NLS-1$
				String message = e.getMessage().substring(e.getMessage().indexOf("No es posible")); //$NON-NLS-1$
	    		return SCIMResponseBuilder.errorCustom(Status.CONFLICT, message);
			}
			return SCIMResponseBuilder.errorGeneric(e);
		} catch (Exception e) {
    		return SCIMResponseBuilder.errorGeneric(e);
		}
    }

    @Path("/{id}")
    @GET
    public Response show(
    		@PathParam("id") long id
    		)  {
        User user;
		try {
			user = svc.findUserByUserId(id);
	    	if (user != null)
	    		return SCIMResponseBuilder.responseOk(toExtendedUser(user));
	    	else
	    		return SCIMResponseBuilder.responseOnlyHTTP(Status.NOT_FOUND);
		} catch (Exception e) {
    		return SCIMResponseBuilder.errorGeneric(e);
		}
    }

    @Path("/{id}")
    @DELETE
    public Response delete(@PathParam("id") long id) {
        User user;
		try {
			user = svc.findUserByUserId(id);
	    	if (user != null) {
	    		svc.delete(user);
	    		return SCIMResponseBuilder.responseOnlyHTTP(Status.NO_CONTENT);
	    	} else {
	    		String message = String.format(Messages.getString("UserSvc.userNotFound"), id); //$NON-NLS-1$
	    		return SCIMResponseBuilder.errorCustom(Status.NOT_FOUND, message);
	    	}
		} catch (Exception e) {
    		return SCIMResponseBuilder.errorGeneric(e);
		}
    }

    @Path("/{id}")
    @PUT
    public Response update(@PathParam("id") long id,
    		ExtendedUser user
    		)  {
        User user2;
		try {
			user2 = svc.findUserByUserId(id);
	    	if (user2 == null) return SCIMResponseBuilder.responseOnlyHTTP(Status.NOT_FOUND);
	    	
	    	user2.setActive(user.getActive());
	    	user2.setComments(user.getComments());
	    	user2.setConsoleProperties(user.getConsoleProperties());
	    	user2.setCreatedByUser(user.getCreatedByUser());
	    	user2.setCreatedDate(user.getCreatedDate());
	    	user2.setFirstName(user.getFirstName());
	    	user2.setFullName(user.getFullName());
	    	user2.setHomeServer(user.getHomeServer());
	    	user2.setId( user.getId() );
	    	user2.setLastName(user.getLastName());
	    	user2.setMailAlias(user.getMailAlias());
	    	user2.setMailDomain(user.getMailDomain());
	    	user2.setMailServer(user.getMailServer());
	    	user2.setMailDomain(user.getMailDomain());
	    	user2.setMailServer(user.getMailServer());
	    	user2.setMiddleName(user.getMiddleName());
	    	user2.setModifiedByUser(user.getModifiedByUser());
	    	user2.setModifiedDate(user.getModifiedDate());
	    	user2.setMultiSession(user.getMultiSession());
	    	user2.setNationalID(user.getNationalID());
	    	user2.setPasswordMaxAge(user.getPasswordMaxAge());
	    	user2.setPhoneNumber(user.getPhoneNumber());
	    	user2.setPrimaryGroup(user.getPrimaryGroup());
	    	user2.setPrimaryGroupDescription(user.getPrimaryGroupDescription());
	    	user2.setProfileServer(user.getProfileServer());
	    	user2.setShortName(user.getShortName());
	    	user2.setUserName(user.getUserName());
	    	user2.setUserType(user.getUserType());
	    	svc.update(user2);
	    	if (user.getPassword() != null)
	    		svc.changePassword(user.getUserName(), "DEFAULT", new Password (user.getPassword())); //$NON-NLS-1$
	    	updateAttributes(user, user2, true);
			updateAccounts(user, user2);
	    	return SCIMResponseBuilder.responseOk(toExtendedUser(user2));
		} catch (Exception e) {
    		return SCIMResponseBuilder.errorGeneric(e);
		}
    }
    
    @Path("/{id}")
    @PATCH
    public Response patch(@PathParam("id") long id,
    		ExtendedUser user
    		)  {
        User user2;
		try {
			user2 = svc.findUserByUserId(id);
	    	if (user2 == null) return SCIMResponseBuilder.responseOnlyHTTP(Status.NOT_FOUND);

	    	if (user.getActive() != null) user2.setActive(user.getActive());
	    	if (user.getComments() !=null) user2.setComments(user.getComments());
	    	if (user.getConsoleProperties() != null) user2.setConsoleProperties(user.getConsoleProperties());
	    	if (user.getCreatedByUser() != null) user2.setCreatedByUser(user.getCreatedByUser());
	    	if (user.getCreatedDate() != null) user2.setCreatedDate(user.getCreatedDate());
	    	if (user.getFirstName () !=null) user2.setFirstName(user.getFirstName());
	    	if (user.getFullName () !=null) user2.setFullName(user.getFullName());
	    	if (user.getHomeServer () !=null) user2.setHomeServer(user.getHomeServer());
	    	if (user.getId () !=null) user2.setId( user.getId() );
	    	if (user.getLastName () !=null) user2.setLastName(user.getLastName());
	    	if (user.getMailAlias () !=null) user2.setMailAlias(user.getMailAlias());
	    	if (user.getMailDomain () !=null) user2.setMailDomain(user.getMailDomain());
	    	if (user.getMailServer () !=null) user2.setMailServer(user.getMailServer());
	    	if (user.getMailDomain () !=null) user2.setMailDomain(user.getMailDomain());
	    	if (user.getMailServer () !=null) user2.setMailServer(user.getMailServer());
	    	if (user.getMiddleName () !=null) user2.setMiddleName(user.getMiddleName());
	    	if (user.getModifiedByUser () !=null) user2.setModifiedByUser(user.getModifiedByUser());
	    	if (user.getModifiedDate () !=null) user2.setModifiedDate(user.getModifiedDate());
	    	if (user.getMultiSession () !=null) user2.setMultiSession(user.getMultiSession());
	    	if (user.getNationalID () !=null) user2.setNationalID(user.getNationalID());
	    	if (user.getPasswordMaxAge () !=null) user2.setPasswordMaxAge(user.getPasswordMaxAge());
	    	if (user.getPhoneNumber () !=null) user2.setPhoneNumber(user.getPhoneNumber());
	    	if (user.getPrimaryGroup () !=null) user2.setPrimaryGroup(user.getPrimaryGroup());
	    	if (user.getPrimaryGroupDescription () !=null) user2.setPrimaryGroupDescription(user.getPrimaryGroupDescription());
	    	if (user.getProfileServer () !=null) user2.setProfileServer(user.getProfileServer());
	    	if (user.getShortName () !=null) user2.setShortName(user.getShortName());
	    	if (user.getUserName () !=null) user2.setUserName(user.getUserName());
	    	if (user.getUserType () !=null) user2.setUserType(user.getUserType());
	    	svc.update(user2);
	    	if (user.getPassword() != null)
	    		svc.changePassword(user2.getUserName(), "DEFAULT", new Password (user.getPassword())); //$NON-NLS-1$
			updateAccounts(user, user2);
	    	updateAttributes(user, user2, false);
	    	return SCIMResponseBuilder.responseOk(toExtendedUser(user2));
		} catch (Exception e) {
    		return SCIMResponseBuilder.errorGeneric(e);
		}
    }
    

    private void updateAccounts (ExtendedUser src, User target) throws InternalErrorException, NeedsAccountNameException, AccountAlreadyExistsException
    {
    	Collection<com.soffid.iam.api.UserAccount> accounts = accountSvc.getUserAccounts(target);
    	for (UserAccount ua: accounts)
    	{
    		boolean found = false;
    		for ( JsonAccount ua2: src.getAccounts())
    		{
    			if (ua2.getId() == ua.getId().longValue())
    			{
    				found = true;
    				break;
    			}
    		}
    		if (!found)
    		{
    			accountSvc.removeAccount(ua);
    		}
    	}
		for ( JsonAccount ua2: src.getAccounts())
    	{
    		boolean found = false;
    		{
    	    	for (UserAccount ua: accounts)
    			if (ua2.getId() == ua.getId().longValue())
    			{
    				found = true;
    				break;
    			}
    		}
    		if (!found)
    		{
    			accountSvc.createAccount(target, dispatcherSvc.findDispatcherByName(ua2.getSystem()), ua2.getName());
    		}
    	}
    }

    private void updateAttributes (ExtendedUser src, User target, boolean delete) throws InternalErrorException, NeedsAccountNameException, AccountAlreadyExistsException
    {
    	Collection<UserData> atts = svc.findUserDataByUserName(target.getUserName());
    	for (UserData ua: atts)
    	{
    		Object value = src.getAttributes().get(ua.getAttribute());
    		if (value == null)
    		{
    			if (delete || src.getAttributes().containsKey(ua.getAttribute()))
    				dataSvc.delete(ua);
    		}
    		else 
    		{
    			if (value instanceof Date){
    				Calendar c = Calendar.getInstance();
    				c.setTime((Date) value);
    				ua.setDateValue(c);
    			}
    			else
    				ua.setValue(value.toString());
    			dataSvc.update(ua);
    		}
    	}
		for ( String key: src.getAttributes().keySet())
    	{
    		boolean found = false;
	    	for (UserData ua: atts)
	    	{
	    		if (ua.getAttribute().equals(key))
	    		{
	    			found = true;
	    			break;
	    		}
	    	}
	    	if ( ! found)
    		{
	    		UserData data = new UserData();
	    		data.setUser(target.getUserName());
	    		data.setAttribute(key);
	    		Object value = src.getAttributes().get(key);
    			if (value instanceof Date){
    				Calendar c = Calendar.getInstance();
    				c.setTime((Date) value);
    				data.setDateValue(c);
    			}
    			else
    				data.setValue(value.toString());
    			dataSvc.create(data);
    		}
    	}
    }
}


