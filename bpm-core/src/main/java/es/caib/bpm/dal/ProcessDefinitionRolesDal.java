package es.caib.bpm.dal;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.jbpm.JbpmContext;
import org.jbpm.graph.def.ProcessDefinition;

import es.caib.bpm.entity.ProcessDefinitionUserRole;

public class ProcessDefinitionRolesDal 
{
	
	public List findProcessDefinitionRoles(ProcessDefinition definition, String appRole)
	{
		Criteria criteria= null;
		
		criteria= this.context.getSession().createCriteria(ProcessDefinitionUserRole.class);
		
		criteria.add(Restrictions.eq("processDefinitionId", new Long(definition.getId())));
		criteria.add(Restrictions.eq("appRole", appRole));
		
		return criteria.list();
	}
	
	public JbpmContext getContext() 
	{
		return context;
	}
	
	public void setContext(JbpmContext context) 
	{
		this.context = context;
	}
	
	private JbpmContext context= null;
}
