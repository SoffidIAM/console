package com.soffid.iam.bpm.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.jbpm.JbpmException;
import org.jbpm.db.JbpmSession;
import org.jbpm.taskmgmt.exe.TaskInstance;

import com.soffid.iam.utils.Security;

public class TaskMgmtSession extends org.jbpm.db.TaskMgmtSession {
	Session session;
	JbpmSession jbpmSession = null;
	private static final Log log = LogFactory.getLog(GraphSession.class);

	public TaskMgmtSession(Session session) {
		super(session);
		this.session = session;
	    this.jbpmSession = new JbpmSession(session);
	}

	@Override
	public List findTaskInstances(String actorId) {
	    List result = null;
	    try {
	      Query query = session.getNamedQuery("TaskMgmtSession.findTaskInstancesByActorId");
	      query.setString("actorId", actorId);
	      query.setLong("tenant", Security.getCurrentTenantId());
	      result = query.list();
	    } catch (Exception e) {
	      log.error(e);
//	      jbpmSession.handleException();
	      throw new JbpmException("couldn't get task instances list for actor '"+actorId+"'", e);
	    } 
	    return result;
	}

	@Override
	public List findTaskInstances(String[] actorIds) {
	    List result = null;
	    try {
	      Query query = session.getNamedQuery("TaskMgmtSession.findTaskInstancesByActorIds");
	      query.setParameterList("actorIds", actorIds);
	      query.setLong("tenant", Security.getCurrentTenantId());
	      result = query.list();
	    } catch (Exception e) {
	      log.error(e);
//	      jbpmSession.handleException();
	      throw new JbpmException("couldn't get task instances list for actors '"+actorIds+"'", e);
	    } 
	    return result;
	}

	@Override
	public List findPooledTaskInstances(String actorId) {
	    List result = null;
	    try {
	      Query query = session.getNamedQuery("TaskMgmtSession.findPooledTaskInstancesByActorId");
	      query.setString("swimlaneActorId", actorId);
	      query.setLong("tenant", Security.getCurrentTenantId());
	      List identifiers = query.list();
	      if (!identifiers.isEmpty()) {
	        result = new ArrayList(identifiers.size());
	        for (Iterator i = identifiers.iterator(); i.hasNext();) {
	          Long taskInstanceId = (Long) i.next();
	          result.add(session.load(TaskInstance.class, taskInstanceId));
	        }
	      }
	      else {
	        result = Collections.EMPTY_LIST;
	      }
	    } catch (Exception e) {
	      log.error(e);
//	      jbpmSession.handleException();
	      throw new JbpmException("couldn't get pooled task instances list for actor '"+actorId+"'", e);
	    } 
	    return result;
	}

	@Override
	public List findPooledTaskInstances(List actorIds) {
	    List result = null;
	    try {
	      Query query = session.getNamedQuery("TaskMgmtSession.findPooledTaskInstancesByActorIds");
	      query.setParameterList("actorIds", actorIds);
	      query.setLong("tenant", Security.getCurrentTenantId());
	      List identifiers = query.list();
	      if (!identifiers.isEmpty()) {
	        result = new ArrayList(identifiers.size());
	        for (Iterator i = identifiers.iterator(); i.hasNext();) {
	          Long taskInstanceId = (Long) i.next();
	          result.add(session.load(TaskInstance.class, taskInstanceId));
	        }
	      }
	      else {
	        result = Collections.EMPTY_LIST;
	      }
	    } catch (Exception e) {
	      log.error(e);
//	      jbpmSession.handleException();
	      throw new JbpmException("couldn't get pooled task instances list for actors '"+actorIds+"'", e);
	    } 
	    return result;
	}

}
