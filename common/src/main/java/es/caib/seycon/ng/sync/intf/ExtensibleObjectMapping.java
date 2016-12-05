package es.caib.seycon.ng.sync.intf;

import java.util.Collection;
import java.util.Map;

import es.caib.seycon.ng.comu.AttributeMapping;
import es.caib.seycon.ng.comu.ObjectMapping;
import es.caib.seycon.ng.comu.ObjectMappingTrigger;
import es.caib.seycon.ng.comu.SoffidObjectTrigger;
import es.caib.seycon.ng.comu.SoffidObjectType;

/**
 * @author gbuades
 *
 */
public class ExtensibleObjectMapping extends ObjectMapping
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7332145350848632617L;
	
	Map<String,String> properties = null;
	Collection<AttributeMapping> attributes =  null;
	Collection<ObjectMappingTrigger> triggers =  null;
	
	public Collection<ObjectMappingTrigger> getTriggers()
	{
		return triggers;
	}

	public void setTriggers(Collection<ObjectMappingTrigger> triggers)
	{
		this.triggers = triggers;
	}

	public Collection<AttributeMapping> getAttributes()
	{
		return attributes;
	}

	public void setAttributes(Collection<AttributeMapping> attributes)
	{
		this.attributes = attributes;
	}

	public Map<String, String> getProperties()
	{
		return properties;
	}

	public void setProperties(Map<String, String> properties)
	{
		this.properties = properties;
	}

	public ExtensibleObjectMapping()
	{
		super();
	}

	public ExtensibleObjectMapping(Long id, String systemObject,
			SoffidObjectType soffidObject, String condition, Long dispatcherId)
	{
		super(id, systemObject, soffidObject, condition, dispatcherId);
	}

	public ExtensibleObjectMapping(ObjectMapping otherBean)
	{
		super(otherBean);
		if (otherBean instanceof ExtensibleObjectMapping)
		{
			setAttributes(((ExtensibleObjectMapping) otherBean).getAttributes());
			setProperties(((ExtensibleObjectMapping) otherBean).getProperties());
			setTriggers(((ExtensibleObjectMapping) otherBean).getTriggers());
		}
	}

	public ExtensibleObjectMapping(String systemObject,
			SoffidObjectType soffidObject, Long dispatcherId)
	{
		super(systemObject, soffidObject, dispatcherId);
	}
	
}
