/**
 * 
 */
/**
 * 
 */
package com.soffid.iam.service.impl;

import com.soffid.iam.api.AgentDescriptor;
import es.caib.seycon.ng.comu.AttributeMapping;
import es.caib.seycon.ng.comu.ObjectMapping;
import java.util.Collection;
import java.util.LinkedList;

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
					boolean authoritativeSource, boolean enableAttributeMapping)
	{
		super(id, description, className, userInterface, enableAccessControl,
						authoritativeSource, enableAttributeMapping);
	}

	/**
	 * @param otherBean
	 */
	public InternalAgentDescriptor (AgentDescriptor otherBean)
	{
		super(otherBean);
	}

}
