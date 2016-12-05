/**
 * 
 */
package es.caib.seycon.ng.servei.impl;

import java.util.Collection;
import java.util.LinkedList;

import es.caib.seycon.ng.comu.AttributeMapping;
import es.caib.seycon.ng.comu.ObjectMapping;
import es.caib.seycon.ng.comu.ObjectMappingProperty;
import es.caib.seycon.ng.comu.ObjectMappingTrigger;

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
