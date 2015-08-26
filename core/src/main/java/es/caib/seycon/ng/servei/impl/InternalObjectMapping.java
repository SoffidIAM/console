/**
 * 
 */
package es.caib.seycon.ng.servei.impl;

import java.util.Collection;
import java.util.LinkedList;

import com.soffid.iam.api.AttributeMapping;
import com.soffid.iam.api.ObjectMapping;
import com.soffid.iam.api.ObjectMappingProperty;

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
}
