package es.caib.bpm.dal;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.jbpm.JbpmContext;

import es.caib.bpm.entity.ProcessDefinitionProperty;

public class ProcessDefinitionPropertyDal {

	public String getProcessDefinitionProperty(long definitionId, String name)
	{
		Session sesion = this.context.getSession();
		
		Criteria criteria = sesion.createCriteria(ProcessDefinitionProperty.class);

		criteria.add(Restrictions.eq("processDefinitionId", new Long(definitionId))); //$NON-NLS-1$
		criteria.add(Restrictions.eq("name", name)); //$NON-NLS-1$
		
		List result = criteria.list();
		if (result.size() == 0)
			return null;
		ProcessDefinitionProperty property = (ProcessDefinitionProperty) result.get(0);
		if (property == null)
			return null;
		else
			return property.getValue();
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
