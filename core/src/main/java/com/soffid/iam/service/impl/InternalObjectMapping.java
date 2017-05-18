/**
 * 
 */
package com.soffid.iam.service.impl;

import java.util.Collection;
import java.util.LinkedList;

import com.soffid.iam.api.AttributeMapping;
import com.soffid.iam.api.ObjectMapping;
import com.soffid.iam.api.ObjectMappingProperty;
import com.soffid.iam.api.ObjectMappingTrigger;

/**
 * @author bubu
 *
 */
public class InternalObjectMapping extends ObjectMapping
{
	Collection<ObjectMappingProperty> properties = new LinkedList<ObjectMappingProperty>();

	public Collection<ObjectMappingProperty> getProperties ()
	{
		return properties;
	}

	public void setProperties (Collection<ObjectMappingProperty> properties)
	{
		this.properties = properties;
	}
	
	
	Collection<AttributeMapping> attributes = new LinkedList<AttributeMapping>();

	public Collection<AttributeMapping> getAttributes ()
	{
		return attributes;
	}

	public void setAttributes (Collection<AttributeMapping> attributes)
	{
		this.attributes = attributes;
	}

	
	Collection<ObjectMappingTrigger> triggers = new LinkedList<ObjectMappingTrigger>();

	public Collection<ObjectMappingTrigger> getTriggers ()
	{
		return triggers;
	}

	public void setTriggers (Collection<ObjectMappingTrigger> triggers)
	{
		this.triggers = triggers;
	}
}
