package com.soffid.iam.webservice;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
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

import com.soffid.iam.api.Group;
import com.soffid.iam.service.ejb.GroupService;
import com.soffid.iam.webservice.group.ExtendedGroup;
import com.soffid.iam.webservice.group.GroupQuery;

import es.caib.seycon.ng.exception.InternalErrorException;

@Path("/scim/Group")
@Produces({ "application/scim+json", "application/json" })
@Consumes({ "application/scim+json", "application/json" })
public class GroupSvc {

	static final String RESOURCE = "Group";

	@EJB
	GroupService groupSvc;

	@Path("")
	@GET
	public GroupQuery list(@QueryParam("filter") @DefaultValue("") String query, @QueryParam("attributes") String atts)
			throws InternalErrorException {
		GroupQuery gq = new GroupQuery();
		gq.setResources(toExtendedGroupList(groupSvc.findGroupByJsonQuery(query)));
		gq.setTotalResults(gq.getResources().size());
		return gq;
	}

	@Path("/{id}")
	@GET
	public Response show(@PathParam("id") long id) {
		try {
			Group group = groupSvc.findGroupById(id);
			if (group != null)
				return SCIMResponseBuilder.responseOk(toExtendedGroup(group));
			else
				return SCIMResponseBuilder.responseOnlyHTTP(Status.NOT_FOUND);
		} catch (Exception e) {
			return SCIMResponseBuilder.errorGeneric(e);
		}
	}

	@Path("")
	@POST
	public Response create(ExtendedGroup extendedGroup, @Context HttpServletRequest request) throws URISyntaxException {
		try {
			Group group = groupSvc.create(extendedGroup);
			if (group != null) {
				ExtendedGroup newExtendedGroup = toExtendedGroup(group);
				return SCIMResponseBuilder.responseOk(newExtendedGroup,
						new URI(newExtendedGroup.getMeta().getLocation()));
			} else
				return SCIMResponseBuilder.responseOnlyHTTP(Status.NOT_FOUND);
		} catch (EJBException e) {
			return SCIMResponseBuilder.errorCustom(Status.CONFLICT, e.getMessage());
		} catch (Exception e) {
			return SCIMResponseBuilder.errorGeneric(e);
		}
	}

	@Path("/{id}")
	@DELETE
	public Response delete(@PathParam("id") long id) {
		try {
			Group group = groupSvc.findGroupById(id);
			if (group != null) {
				String message = Messages.getString("GroupSvc.deleteNotAllowed");
				return SCIMResponseBuilder.errorCustom(Status.INTERNAL_SERVER_ERROR, message); // $NON-NLS-1$
			} else {
				String message = String.format(Messages.getString("GroupSvc.groupNotFound"), id); //$NON-NLS-1$
				return SCIMResponseBuilder.errorCustom(Status.NOT_FOUND, message);
			}
		} catch (Exception e) {
			return SCIMResponseBuilder.errorGeneric(e);
		}
	}

	@Path("/{id}")
	@PATCH
	public Response patch(@PathParam("id") long id, ExtendedGroup extendedGroup) {
		try {
			// Validations
			Group group = groupSvc.findGroupById(id);
			if (group == null)
				return SCIMResponseBuilder.responseOnlyHTTP(Status.NOT_FOUND);
			if (null != extendedGroup.getId() && id != extendedGroup.getId())
				return SCIMResponseBuilder.errorCustom(Status.NOT_FOUND,
						String.format(Messages.getString("GroupSvc.groupNotEquals"), id, extendedGroup.getId())); //$NON-NLS-1$

			// Update only the attributes requested
			if (extendedGroup.getObsolete() != null)
				group.setObsolete(extendedGroup.getObsolete());
			if (extendedGroup.getOrganizational() != null)
				group.setOrganizational(extendedGroup.getOrganizational());
			if (extendedGroup.getAttributes() != null)
				group.setAttributes(extendedGroup.getAttributes());
			if (extendedGroup.getDescription() != null)
				group.setDescription(extendedGroup.getDescription());
			if (extendedGroup.getDriveLetter() != null)
				group.setDriveLetter(extendedGroup.getDriveLetter());
			if (extendedGroup.getDriveServerName() != null)
				group.setDriveServerName(extendedGroup.getDriveServerName());
			if (extendedGroup.getName() != null)
				group.setName(extendedGroup.getName());
			if (extendedGroup.getParentGroup() != null)
				group.setParentGroup(extendedGroup.getParentGroup());
			if (extendedGroup.getQuota() != null)
				group.setQuota(extendedGroup.getQuota());
			if (extendedGroup.getSection() != null)
				group.setSection(extendedGroup.getSection());
			if (extendedGroup.getType() != null)
				group.setType(extendedGroup.getType());

			// Update the group and return the result
			groupSvc.update(group);
			return SCIMResponseBuilder.responseOk(toExtendedGroup(group));
		} catch (Exception e) {
			return SCIMResponseBuilder.errorGeneric(e);
		}
	}

	@Path("/{id}")
	@PUT
	public Response update(@PathParam("id") long id, ExtendedGroup extendedGroup) {
		try {
			// Validations
			Group group = groupSvc.findGroupById(id);
			if (group == null)
				return SCIMResponseBuilder.responseOnlyHTTP(Status.NOT_FOUND);
			if (null != extendedGroup.getId() && id != extendedGroup.getId())
				return SCIMResponseBuilder.errorCustom(Status.NOT_FOUND,
						String.format(Messages.getString("GroupSvc.groupNotEquals"), id, extendedGroup.getId())); // $NON-NLS-1$

			// Update only the attributes requested
			group.setObsolete(extendedGroup.getObsolete());
			group.setOrganizational(extendedGroup.getOrganizational());
			group.setAttributes(extendedGroup.getAttributes());
			group.setDescription(extendedGroup.getDescription());
			group.setDriveLetter(extendedGroup.getDriveLetter());
			group.setDriveServerName(extendedGroup.getDriveServerName());
			group.setId(extendedGroup.getId());
			group.setName(extendedGroup.getName());
			group.setParentGroup(extendedGroup.getParentGroup());
			group.setQuota(extendedGroup.getQuota());
			group.setSection(extendedGroup.getSection());
			group.setType(extendedGroup.getType());

			// Update the group and return the result
			groupSvc.update(group);
			return SCIMResponseBuilder.responseOk(toExtendedGroup(group));
		} catch (Exception e) {
			return SCIMResponseBuilder.errorGeneric(e);
		}
	}

	private Collection<ExtendedGroup> toExtendedGroupList(Collection<Group> listGroup) throws InternalErrorException {
		LinkedList<ExtendedGroup> listExtendedGroup = new LinkedList<ExtendedGroup>();
		if (null != listGroup && !listGroup.isEmpty()) {
			for (Group group : listGroup) {
				listExtendedGroup.add(toExtendedGroup(group));
			}
		}
		return listExtendedGroup;
	}

	private ExtendedGroup toExtendedGroup(Group group) throws InternalErrorException {
		ExtendedGroup extendedGroup = new ExtendedGroup(group);

		// Include scim meta attributes
		ScimMeta meta = extendedGroup.getMeta();
		meta.setLocation(getClass(), group.getId().toString());
		meta.setResourceType(RESOURCE);
		return extendedGroup;
	}
}
