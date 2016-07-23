package com.soffid.iam.bpm.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.jbpm.JbpmException;
import org.jbpm.db.JbpmSession;
import org.jbpm.graph.def.ProcessDefinition;

import com.soffid.iam.utils.Security;

public class GraphSession extends org.jbpm.db.GraphSession {

	Session session;
	JbpmSession jbpmSession = null;
	private static final Log log = LogFactory.getLog(GraphSession.class);

	public GraphSession(JbpmSession jbpmSession) {
		super(jbpmSession);
	}

	public GraphSession(Session session) {
		super(session);
		this.session = session;
	    this.jbpmSession = new JbpmSession(session);
	}

	@Override
	public ProcessDefinition findProcessDefinition(String name, int version) {
	    ProcessDefinition processDefinition = null;
	    try
	    {
	      Query query = session.getNamedQuery("GraphSession.findProcessDefinitionByNameAndVersion");
	      query.setString("name", name);
	      query.setInteger("version", version);
	      query.setLong("tenant", Security.getCurrentTenantId());
	      processDefinition = (ProcessDefinition)query.uniqueResult();
	    }
	    catch (Exception e)
	    {
	      log.error(e);
//	      jbpmSession.handleException();
	      throw new JbpmException("couldn't get process definition with name '" + name + "' and version '" + version + "'", e);
	    }
	    return processDefinition;
	}

	@Override
	public ProcessDefinition findLatestProcessDefinition(String name) {
	    ProcessDefinition processDefinition = null;
	    try
	    {
	      Query query = session.getNamedQuery("GraphSession.findLatestProcessDefinitionQuery");
	      query.setString("name", name);
	      query.setLong("tenant", Security.getCurrentTenantId());
	      query.setMaxResults(1);
	      processDefinition = (ProcessDefinition)query.uniqueResult();
	    }
	    catch (Exception e)
	    {
	      log.error(e);
//	      jbpmSession.handleException();
	      throw new JbpmException("couldn't find process definition '" + name + "'", e);
	    }
	    return processDefinition;
	}

	@Override
	public List findLatestProcessDefinitions() {
	    List processDefinitions = new ArrayList();
	    Map processDefinitionsByName = new HashMap();
	    try
	    {
	      Query query = session.getNamedQuery("GraphSession.findAllProcessDefinitions");
	      query.setLong("tenant", Security.getCurrentTenantId());
	      Iterator iter = query.list().iterator();
	      while (iter.hasNext())
	      {
	        ProcessDefinition processDefinition = (ProcessDefinition)iter.next();
	        String processDefinitionName = processDefinition.getName();
	        ProcessDefinition previous = (ProcessDefinition)processDefinitionsByName.get(processDefinitionName);
	        if ((previous == null) || (previous.getVersion() < processDefinition.getVersion()))
	        {
	          processDefinitionsByName.put(processDefinitionName, processDefinition);
	        }
	      }
	      processDefinitions = new ArrayList(processDefinitionsByName.values());
	    }
	    catch (Exception e)
	    {
	      log.error(e);
//	      jbpmSession.handleException();
	      throw new JbpmException("couldn't find latest versions of process definitions", e);
	    }
	    return processDefinitions;
	}

	@Override
	public List findAllProcessDefinitions() {
	    try
	    {
	      Query query = session.getNamedQuery("GraphSession.findAllProcessDefinitions");
	      query.setLong("tenant", Security.getCurrentTenantId());
	      return query.list();
	    }
	    catch (Exception e)
	    {
	      log.error(e);
//	      jbpmSession.handleException();
	      throw new JbpmException("couldn't find all process definitions", e);
	    }
	}

	@Override
	public List findAllProcessDefinitionVersions(String name) {
	    try
	    {
	      Query query = session.getNamedQuery("GraphSession.findAllProcessDefinitionVersions");
	      query.setLong("tenant", Security.getCurrentTenantId());
	      query.setString("name", name);
	      return query.list();
	    }
	    catch (HibernateException e)
	    {
	      log.error(e);
	      throw new JbpmException("couldn't find all versions of process definition '" + name + "'", e);
	    }
	}
	
	

}
