/**
 * 
 */
/**
 * 
 */
/**
 * 
 */
package com.soffid.iam.authoritative.service;

import com.soffid.iam.api.CustomObject;
import com.soffid.iam.api.DataType;
import com.soffid.iam.api.Group;
import com.soffid.iam.api.GroupUser;
import com.soffid.iam.api.MetadataScope;
import com.soffid.iam.api.SoffidObjectType;
import com.soffid.iam.api.User;
import com.soffid.iam.api.UserData;
import com.soffid.iam.authoritative.model.AuthoritativeChangeEntity;
import com.soffid.iam.model.AuditEntity;
import com.soffid.iam.model.GroupEntity;
import com.soffid.iam.model.SystemEntity;
import com.soffid.iam.model.UserEntity;
import com.soffid.iam.sync.intf.AuthoritativeChange;
import com.soffid.iam.sync.intf.AuthoritativeChangeIdentifier;
import com.soffid.iam.utils.ConfigurationCache;
import com.soffid.iam.utils.Security;

import es.caib.seycon.ng.comu.TypeEnumeration;
import es.caib.seycon.ng.exception.InternalErrorException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.LogFactory;
import org.jbpm.JbpmContext;
import org.jbpm.graph.def.ProcessDefinition;
import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.taskmgmt.exe.TaskInstance;

/**
 * @author gbuades
 *
 */
public class AuthoritativeChangeServiceImpl extends AuthoritativeChangeServiceBase
{
	org.apache.commons.logging.Log log = LogFactory.getLog(getClass());
	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.AuthoritativeChangeServiceBase#handleFinishAuthoritativeChange(es.caib.seycon.ng.sync.intf.AuthoritativeChange)
	 */
	@Override
	protected void handleFinishAuthoritativeChange (AuthoritativeChange change)
					throws Exception
	{
		AuthoritativeChangeIdentifier changeId = change.getId();
		AuthoritativeChangeEntity ch = getAuthoritativeChangeEntityDao().load(changeId.getInternalId());
		if (ch != null)
		{
			applyChange(change);
			getAuthoritativeChangeEntityDao().remove(ch);
			ch.setId(null);
		}
	}

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.AuthoritativeChangeServiceBase#handleCancelAuthoritativeChange(es.caib.seycon.ng.sync.intf.AuthoritativeChange)
	 */
	@Override
	protected void handleCancelAuthoritativeChange (AuthoritativeChange change)
					throws Exception
	{
		AuthoritativeChangeIdentifier changeId = change.getId();
		AuthoritativeChangeEntity ch = getAuthoritativeChangeEntityDao().load(changeId.getInternalId());
		if (ch != null)
		{
			getAuthoritativeChangeEntityDao().remove(ch);
			ch.setId(null);
		}
	}

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.AuthoritativeChangeServiceBase#handleStartAuthoritativeChange(es.caib.seycon.ng.sync.intf.AuthoritativeChange)
	 */
	@Override
	protected boolean handleStartAuthoritativeChange (AuthoritativeChange change)
					throws Exception
	{
		SystemEntity dispatcher = getSystemEntityDao().findByName(change.getSourceSystem());
		if (dispatcher == null)
			throw new InternalErrorException(String.format("Invalid source change %s", change.getSourceSystem()));
		
		if (change.getGroups() != null && change.getUser().getPrimaryGroup() != null)
			change.getGroups().remove(change.getUser().getPrimaryGroup());
		
		if (dispatcher.getAuthoritativeProcess() == null || dispatcher.getAuthoritativeProcess().isEmpty())
		{
			applyChange(change);
			return true;
		}
		else if (change.getUser() == null)
		{
			applyChange(change);
			return true;
		}
		else
		{
			AuthoritativeChangeIdentifier changeId = change.getId();
			UserEntity ue = null;
			// Cancels any pending workflow
			if (change.getUser() != null && change.getUser().getUserName() != null)
			{
				ue = getUserEntityDao().findByUserName(change.getUser().getUserName());
				if (ue != null)
				{
					for ( AuthoritativeChangeEntity ch: ue.getPendingAuthoritativeChanges())
					{
						if (ch.getChangeDate() != null || ch.getChangeId() != null)
						{
							if (ch.getChangeDate() == null && changeId.getDate() == null ||
								ch.getChangeDate() != null && ch.getChangeDate().equals(changeId.getDate()))
							{
								if (ch.getChangeId() == null && changeId.getChangeId() == null ||
									ch.getChangeId() != null && ch.getChangeId().toString().equals(changeId.getChangeId()))
								{
									// This change is being managed => Ignore it
									return true;
								}
								
							}
						}
						cancelProcess (ch.getProcessId());
						getAuthoritativeChangeEntityDao().remove(ch);
					}
				}
			}
			
			if (! detectChange (change))
			{
				return true;
			}
			// Creates record on data base
			AuthoritativeChangeEntity ch = getAuthoritativeChangeEntityDao().newAuthoritativeChangeEntity();
			ch.setChangeDate(change.getId().getDate());
			if (changeId.getChangeId() != null)
				ch.setChangeId(changeId.getChangeId().toString());
			if (changeId.getEmployeeId() != null)
				ch.setEmployeeId(changeId.getEmployeeId().toString());
			ch.setUser(ue);
			ch.setDispatcher(dispatcher);
			getAuthoritativeChangeEntityDao().create(ch);

			// Starts the workflow
			changeId.setInternalId(ch.getId());
			Long processId = createProcessInstance(change, dispatcher) ;
			
			// Now updates the data base record
			// id will be null if the whole workflow has been executed and the change is already processed
			if (ch.getId() != null)
			{
				ch.setProcessId(processId);
				getAuthoritativeChangeEntityDao().update(ch);
			}

			return false;
		}
		
	}

	/**
	 * @param processId
	 * @throws InternalErrorException 
	 */
	private void cancelProcess (Long processId) throws InternalErrorException
	{
		if (processId != null)
		{
    		JbpmContext ctx = getBpmEngine().getContext();
    		try {
    			ProcessInstance pi =  ctx.getProcessInstance(processId.longValue());
    			if (pi != null && !pi.hasEnded())
    			{
    				pi.getRootToken().addComment("Change has been cancelled");
    				pi.getRootToken().end();
    				pi.end();
    				for (TaskInstance ti: pi.getTaskMgmtInstance().getUnfinishedTasks(pi.getRootToken()))
    				{
    					if (!ti.hasEnded()) {
    						ti.cancel();
    					}
    				}            			
    				ctx.save(pi);
    			}
    		} finally {
    			ctx.close();
    		}
		}
	}

	private long createProcessInstance(AuthoritativeChange change, SystemEntity dispatcher) throws InternalErrorException {
		JbpmContext ctx = getBpmEngine().getContext();
		try {
			ProcessDefinition def = ctx.getGraphSession().findLatestProcessDefinition(dispatcher.getAuthoritativeProcess());
			if (def == null)
				throw new InternalErrorException (String.format("The configured process %s is no longer available", dispatcher.getAuthoritativeProcess()));
			
			ProcessInstance pi = def.createProcessInstance();
			
			User u = change.getUser();
			if (u.getUserName() != null)
			{
				u = getUserService().findUserByUserName(u.getUserName());
			}
			
			pi.getContextInstance().createVariable("change", change);
			if (u != null)
			{
				AuthoritativeChange current = getCurrentAttributes(u.getUserName());
				pi.getContextInstance().createVariable("current", current);
				pi.getContextInstance().createVariable("user", u);
			}
			
			ctx.save(pi);
			pi.signal();
			ctx.save(pi);
			return pi.getId();
		} finally {
			ctx.close();
		}
	}

	private boolean detectChange (AuthoritativeChange change) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InternalErrorException
	{
		boolean anyChange;
		anyChange = detectUserChange (change) ;
		anyChange = detectGroupChange (change) || anyChange;
		anyChange = detectGroup2Change (change) || anyChange;
		anyChange = detecteAttributeChange (change) || anyChange;
		return anyChange;
	}
	
	/**
	 * @param change
	 * @return
	 * @throws InternalErrorException 
	 */
	private boolean detecteAttributeChange (AuthoritativeChange change) throws InternalErrorException
	{
		log.info("Testing attributes change");
		if (change.getAttributes() != null)
		{
			for (String attribute : change.getAttributes().keySet()) {
				log.info("Testing attributes change for  " + attribute );
                Object value = change.getAttributes().get(attribute);
                if (value != null && value instanceof Date) {
                    Calendar c = Calendar.getInstance();
                    c.setTime((Date) value);
                    value = c;
                }
                if (change.getUser().getUserName() == null)
                {
                	log.info("No user name. Assuming change on attribute "+attribute);
                	return true; 
                }
                UserData dada = getUserService().findDataByUserAndCode(change.getUser().getUserName(), attribute);
                if (dada == null && value != null) {
                	log.info("User "+change.getUser().getUserName()+". Detected change on attribute "+attribute);
                	return true; 
                }
                else if (value == null && dada != null) {
                	log.info("User "+change.getUser().getUserName()+". Detected change on attribute "+attribute);
                	return true; 
                }
                else if (value != null && value instanceof byte[]) {
                    if (((byte[]) value).equals(dada.getBlobDataValue())) {
                    	log.info("User "+change.getUser().getUserName()+". Detected change on attribute "+attribute);
                    	return true;
                    }
                } else if (value != null && value instanceof Calendar) {
                    if (!((Calendar) value).equals(dada.getDateValue())) {
                    	log.info("User "+change.getUser().getUserName()+". Detected change on attribute "+attribute);
                    	return true;
                    }
                } else if (value != null && !value.toString().equals(dada.getValue())) {
                	log.info("User "+change.getUser().getUserName()+". Detected change on attribute "+attribute);
                	return true;
                }
            }
		}
		return false;
	}
	

	/**
	 * @param change
	 * @return
	 * @throws InternalErrorException 
	 */
	private boolean detectGroupChange (AuthoritativeChange change) throws InternalErrorException
	{
		if (change.getGroups() == null)
			return false;
		
		Collection<GroupUser> grups = getGroupService().findUsersGroupByUserName(change.getUser().getUserName());
		
		Set<String> actualGroups = new HashSet<String>(change.getGroups());
		
		// First remove
		for (Iterator<GroupUser> it = grups.iterator(); it.hasNext(); ) {
            GroupUser ug = it.next();
            if (actualGroups.contains(ug.getGroup())) {
                log.info("Received user without group " + ug.getGroup());
                actualGroups.remove(ug.getGroup());
            } else {
                return true;
            }
        }
		
		return ! actualGroups.isEmpty();
	}
	
	
	private boolean detectGroup2Change (AuthoritativeChange change) throws InternalErrorException
	{
		if (change.getGroups2() == null)
			return false;
		
		Collection<GroupUser> grups = getGroupService().findUsersGroupByUserName(change.getUser().getUserName());
		
		LinkedList<GroupUser> actualGroups = new LinkedList<GroupUser>(change.getGroups2());
		
		// First remove
		for (Iterator<GroupUser> it = grups.iterator(); it.hasNext(); ) {
            GroupUser ug = it.next();
            GroupUser ug2 = findMembership (ug.getGroup(), change.getGroups2());
            if (ug2 == null) {
            	log.info("Received user without group " + ug.getGroup());
            	return true;
            }
            if (ug2.getAttributes() != null && ! ug2.getAttributes().equals(ug.getAttributes()))
            {
            	log.info("Received group membership with different attributes in " + ug.getGroup());
            	return true;
            }
            
            actualGroups.remove(ug2);
        }
		
		return ! actualGroups.isEmpty();
	}
	

	private GroupUser findMembership(String group, Collection<GroupUser> groups2) {
		for (GroupUser gu: groups2) {
			if (gu.getGroup().equals(groups2))
				return gu;
		}
		return null;
	}

	/**
	 * @param change
	 * @return
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws NoSuchMethodException 
	 * @throws InternalErrorException 
	 * 
	 */
	private boolean detectUserChange (AuthoritativeChange change) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InternalErrorException
	{
		if (change.getUser().getUserName() == null)
		{
			log.info("Received change without userName. New user is expected");
			return true;
		}
		User old = getUserService().findUserByUserName(change.getUser().getUserName());
		if (old == null)
		{
			log.info("Received new user");
			return true;
		}
		else
			return !compareUsers(change.getUser(), old);
	}

	/**
	 * @param change
	 * @throws NoSuchMethodException 
	 * @throws InternalErrorException 
	 * @throws SecurityException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	private void applyChange (AuthoritativeChange change) throws SecurityException, InternalErrorException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException
	{
		Security.nestedLogin(change.getSourceSystem(), Security.ALL_PERMISSIONS);
		try {
			ProcessTracker tracker = new ProcessTracker();
			tracker.change = change;
			tracker.auditGenerated = false;
			if (change.getUser() != null)
			{
	    		User user = applyUserChange(tracker);
	    		if (change.getAttributes() != null)
	    			applyAttributesChange (user, tracker);
	    		if (change.getGroups() != null)
	    			applyGroupChange (user, tracker);
	    		if (change.getGroups2() != null)
	    			applyGroup2Change (user, tracker);
			}
			else if (change.getGroup() != null)
			{
				applyGroupChange (tracker);
			}
			else if (change.getObject() != null)
			{
				applyObjectChange (tracker);
			}
		} finally {
			Security.nestedLogoff();
		}
	}

	/**
	 * @param user
	 * @param change
	 * @throws InternalErrorException 
	 */
	private void applyGroup2Change(User user, ProcessTracker tracker) throws InternalErrorException {
		Collection<GroupUser> currentGroups = getGroupService().findUsersGroupByUserName(user.getUserName());
		
		AuthoritativeChange change = tracker.change;
		LinkedList<GroupUser> newGroups = new LinkedList<GroupUser>(change.getGroups2());
		
		// First remove
		for (Iterator<GroupUser> it = currentGroups.iterator(); it.hasNext(); ) {
            GroupUser currentGroup = it.next();
            GroupUser newGroup = findMembership (currentGroup.getGroup(), newGroups);
            if (newGroup == null) {
                auditAuthoritativeChange(tracker);
                getGroupService().delete(currentGroup);
            } 
            else 
            {
            	if (newGroup.getAttributes() != null && ! newGroup.getAttributes().equals(currentGroup.getAttributes()))
	            {
	                auditAuthoritativeChange(tracker);
	                newGroup.setId(currentGroup.getId());
	                newGroup.setUser(currentGroup.getGroup());
	                getGroupService().update(newGroup);
	            }
	            newGroups.remove(newGroup);
            }
        }

		// Now add
		for (GroupUser groupUser : newGroups) {
            auditAuthoritativeChange(tracker);
            groupUser.setUser(user.getUserName());
            getGroupService().create(groupUser);
        }
	}

	private void applyGroupChange(User user, ProcessTracker tracker) throws InternalErrorException {
		Collection<GroupUser> grups = getGroupService().findUsersGroupByUserName(user.getUserName());
		
		AuthoritativeChange change = tracker.change;
		Set<String> actualGroups = change.getGroups();
		
		// First remove
		for (Iterator<GroupUser> it = grups.iterator(); it.hasNext(); ) {
            GroupUser ug = it.next();
            if (actualGroups.contains(ug.getGroup())) {
                actualGroups.remove(ug.getGroup());
            } else {
                auditAuthoritativeChange(tracker);
                getGroupService().removeGroupFormUser(user.getUserName(), ug.getGroup());
            }
        }
		
		for (String group : actualGroups) {
            auditAuthoritativeChange(tracker);
            getGroupService().addGroupToUser(user.getUserName(), group);
        }
		
	}

	private void auditAuthoritativeChange (ProcessTracker tracker)
	{
		if (!tracker.auditGenerated)
		{
            AuditEntity auditoria = getAuditEntityDao().newAuditEntity();
            auditoria.setAction("U");
            auditoria.setDate(new Date());
            if (tracker.change.getUser() != null)
                auditoria.setUser(tracker.change.getUser().getUserName());
            if (tracker.change.getGroup() != null)
            {
            	GroupEntity g = getGroupEntityDao().findByName(tracker.change.getGroup().getName());
            	auditoria.setGroup(g);
            }
            if (tracker.change.getObject() != null)
            {
                auditoria.setCustomObjectName(tracker.change.getObject().getName());
                auditoria.setCustomObjectType(tracker.change.getObject().getType());
            }
            auditoria.setObject("AUTH_IDENT");
            auditoria.setDb(tracker.change.getSourceSystem());
            getAuditEntityDao().create(auditoria);
            tracker.auditGenerated = true;
		}
	}
	/**
	 * @param change
	 * @return 
	 * @throws InternalErrorException 
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	private User applyUserChange(ProcessTracker tracker) throws InternalErrorException, SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		AuthoritativeChange change = tracker.change;
		User user = change.getUser();
		User oldUser = getUserService().findUserByUserName(user.getUserName());
		if (oldUser == null)
		{
			if (user.getPrimaryGroup() == null) user.setPrimaryGroup("World");
			if (user.getFirstName() == null) user.setFirstName("?");
			if (user.getLastName() == null) user.setLastName("?");
			if (user.getActive() == null) user.setActive(Boolean.TRUE);
			if (user.getMultiSession() == null) user.setMultiSession(Boolean.FALSE);
			if (user.getMailServer() == null) user.setMailServer("null");
			if (user.getHomeServer() == null) user.setHomeServer("null");
			if (user.getProfileServer() == null) user.setProfileServer("null");
			if (user.getUserType() == null) user.setUserType("I");
			oldUser = getUserService().create(user);
		} else {
			boolean anyChange = !compareUsers(user, oldUser);
			if (anyChange)
			{
				auditAuthoritativeChange(tracker);
				getUserService().update(oldUser);
			}
		}
		return oldUser;
	}

	private boolean compareUsers(User user, User oldUser) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		boolean anyChange = false;
		for (String att : new String[]{"Active", "MailAlias", "PrimaryGroup", "Comments", "MailDomain", "MultiSession", 
				"LastName", "ShortName", "FirstName", "MiddleName", "MailServer", "ProfileServer", 
				"HomeServer", "UserType"}) {
            Method getter = User.class.getMethod("get" + att);
            Method setter = User.class.getMethod("set" + att, getter.getReturnType());
            Object value = getter.invoke(user);
            if ("".equals(value)) value = null;
            if (value != null) {
                Object oldValue = getter.invoke(oldUser);
                if ("".equals(oldValue)) oldValue = null;
                if (oldValue == null || !oldValue.equals(value)) {
                    log.info("Received change on attribute " + att);
                    setter.invoke(oldUser, value);
                    anyChange = true;
                }
            }
        }
		return ! anyChange;
	}

	/**
	 * @param change
	 * @throws InternalErrorException 
	 */
	private void applyAttributesChange(User user, ProcessTracker tracker) throws InternalErrorException {
		log.info("Applying attributes change");
		AuthoritativeChange change = tracker.change;
		
		Map<String, Object> attributes = getUserService().findUserAttributes(user.getUserName());
		
		
		for (String attribute : change.getAttributes().keySet()) {
            DataType tda = getAdditionalDataService().findDataTypeByName(attribute);
            auditAuthoritativeChange(tracker);
            if (tda == null) {
                long i = 100;
                tda = new DataType();
                for (DataType tda2 : getAdditionalDataService().getDataTypes()) {
                    if (tda2.getOrder().longValue() >= i) i = tda2.getOrder().longValue() + 1;
                }
                tda.setOrder(i);
                tda.setCode(attribute);
				tda.setScope(MetadataScope.USER);
				tda.setType(TypeEnumeration.STRING_TYPE);
				tda = getAdditionalDataService().create(tda);
			}
            Object value = change.getAttributes().get(attribute);
            attributes.put(attribute, value);
		}
		getUserService().updateUserAttributes(user.getUserName(), attributes);
	}
	
	private AuthoritativeChange getCurrentAttributes (String user) throws InternalErrorException
	{
		if (user == null)
			return null;
		
		AuthoritativeChange current = new AuthoritativeChange();
		current.setUser(getUserService().findUserByUserName(user));
		if (current.getUser() == null)
			return null;
		else
		{
    		current.setGroups(new HashSet<String>());
    		for (Group grup : getUserService().getUserGroups(current.getUser().getId())) {
                current.getGroups().add(grup.getName());
            }
    		
    		current.setAttributes( getUserService().findUserAttributes(user));
    		return current;
		}
	}


	private Group applyGroupChange(ProcessTracker tracker) throws InternalErrorException, SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		AuthoritativeChange change = tracker.change;
		Group g = change.getGroup();
		Group oldGroup = null;
		if ( ConfigurationCache.isHistoryEnabled() &&  
				(g.getObsolete() != null && g.getObsolete().booleanValue()) && 
				g.getEndDate() != null && 
				g.getStartDate() != null) {
			oldGroup = getGroupService().findGroupByGroupNameAndDate(g.getName(), new Date(g.getEndDate().getTime() - 10));
			if (oldGroup != null && oldGroup.getObsolete().booleanValue())
				return oldGroup; // Already loaded

			auditAuthoritativeChange(tracker);
			if (g.getParentGroup() == null) g.setParentGroup("world");
			if (g.getDescription() == null) g.setDescription("?");
			oldGroup = getGroupService().createHistoric(g);
			return oldGroup;
		} else {
			oldGroup = getGroupService().findGroupByGroupName(g.getName());
		}
		if (oldGroup == null)
		{
			auditAuthoritativeChange(tracker);
			if (g.getParentGroup() == null) g.setParentGroup("world");
			if (g.getDescription() == null) g.setDescription("?");
			if (g.getObsolete() == null) g.setObsolete(false);
			oldGroup = getGroupService().create(g);
		} else {
			boolean anyChange = !compareGroups(g, oldGroup);
			if (anyChange)
			{
				auditAuthoritativeChange(tracker);
				getGroupService().update(oldGroup);
			}
		}
		return oldGroup;
	}

	private CustomObject applyObjectChange(ProcessTracker tracker) throws InternalErrorException, SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		AuthoritativeChange change = tracker.change;
		CustomObject g = change.getObject();
		CustomObject oldObject = getCustomObjectService().findCustomObjectByTypeAndName(g.getType(), g.getName()); 
		if (oldObject == null)
		{
			if (g.getDescription() == null) g.setDescription(g.getName());
			oldObject = getCustomObjectService().createCustomObject(g);
		} else {
			boolean anyChange = !compareCustomObjects(g, oldObject);
			if (anyChange)
			{
				auditAuthoritativeChange(tracker);
				getCustomObjectService().updateCustomObject(oldObject);
			}
		}
		return oldObject;
	}

	private boolean compareGroups(Group g, Group oldGroup) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		boolean anyChange = false;
		for (String att : new String[]{"Description", "DriveLetter", "DriveServerName", "Name", "Obsolete", 
				"Organizational", "ParentGroup", "Quota", "Section", "Type", "StartDate", "EndDate"}) {
            Method getter = Group.class.getMethod("get" + att);
            Method setter = Group.class.getMethod("set" + att, getter.getReturnType());
            Object value = getter.invoke(g);
            if ("".equals(value)) value = null;
            if (value != null) {
                Object oldValue = getter.invoke(oldGroup);
                if ("".equals(oldValue)) oldValue = null;
                if (oldValue == null || !oldValue.equals(value)) {
                    log.info("Received change on attribute " + att);
                    setter.invoke(oldGroup, value);
                    anyChange = true;
                }
            }
        }
		
		for (String attribute : g.getAttributes().keySet()) {
            Object value = g.getAttributes().get(attribute);
            if (value != null && value instanceof Date) {
                Calendar c = Calendar.getInstance();
                c.setTime((Date) value);
                value = c;
            }
            Object oldValue = oldGroup.getAttributes().get(attribute);
            if (oldValue != null && oldValue instanceof Date) {
                Calendar c = Calendar.getInstance();
                c.setTime((Date) oldValue);
                oldValue = c;
            }
            if (oldValue == null && value != null ||
            		oldValue != null && ! oldValue.equals(value))
            {
            	oldGroup.getAttributes().put(attribute, value);
            	anyChange = true;
            }
		}
		return ! anyChange;
	} 

	private boolean compareCustomObjects(CustomObject g, CustomObject oldGroup) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		boolean anyChange = false;
		for (String att : new String[]{"Name", "Type", "Description"}) {
            Method getter = CustomObject.class.getMethod("get" + att);
            Method setter = CustomObject.class.getMethod("set" + att, getter.getReturnType());
            Object value = getter.invoke(g);
            if ("".equals(value)) value = null;
            if (value != null) {
                Object oldValue = getter.invoke(oldGroup);
                if ("".equals(oldValue)) oldValue = null;
                if (oldValue == null || !oldValue.equals(value)) {
                    log.info("Received change on attribute " + att);
                    setter.invoke(oldGroup, value);
                    anyChange = true;
                }
            }
        }
		
		for (String attribute : g.getAttributes().keySet()) {
            Object value = g.getAttributes().get(attribute);
            if (value != null && value instanceof Date) {
                Calendar c = Calendar.getInstance();
                c.setTime((Date) value);
                value = c;
            }
            Object oldValue = oldGroup.getAttributes().get(attribute);
            if (oldValue != null && oldValue instanceof Date) {
                Calendar c = Calendar.getInstance();
                c.setTime((Date) oldValue);
                oldValue = c;
            }
            if (oldValue == null && value != null ||
            		oldValue != null && ! oldValue.equals(value))
            {
            	oldGroup.getAttributes().put(attribute, value);
            	anyChange = true;
            }
		}
		return ! anyChange;
	} 
}

class ProcessTracker {
	AuthoritativeChange change;
	boolean auditGenerated;
}
