/**
 * 
 */
package es.caib.seycon.ng.servei.impl;

import java.util.Collection;
import java.util.LinkedList;

import es.caib.seycon.ng.comu.AgentDescriptor;
import es.caib.seycon.ng.comu.AttributeMapping;
import es.caib.seycon.ng.comu.ObjectMapping;

/**
 * @author bubu
 *
 */
public class InternalAgentDescriptor extends AgentDescriptor
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Collection<InternalObjectMapping> objects = new LinkedList<InternalObjectMapping>();
	public Collection<InternalObjectMapping> getObjects ()
	{
		return objects;
	}

	public void setObjects (Collection<InternalObjectMapping> objects)
	{
		this.objects = objects;
	}

	/**
	 * 
	 */
	public InternalAgentDescriptor ()
	{
	}

	/**
	 * @param id
	 * @param description
	 * @param className
	 * @param userInterface
	 * @param enableAccessControl
	 * @param authoritativeSource
	 * @param enableAttributeMapping
	 */
	public InternalAgentDescriptor (Long id, String description, String className,
					byte[] userInterface, boolean enableAccessControl,
					boolean authoritativeSource, boolean enableAttributeMapping, boolean enableObjectTriggers)
	{
		super(id, description, className, userInterface, enableAccessControl,
						authoritativeSource, enableAttributeMapping, enableObjectTriggers);
	}

	/**
	 * @param otherBean
	 */
	public InternalAgentDescriptor (AgentDescriptor otherBean)
	{
		super(otherBean);
	}

}