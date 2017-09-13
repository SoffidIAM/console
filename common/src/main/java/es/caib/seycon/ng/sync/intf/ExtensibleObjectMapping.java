package es.caib.seycon.ng.sync.intf;

import java.util.Collection;
import java.util.Map;

import com.soffid.iam.api.User;
import com.soffid.iam.sync.intf.ExtensibleObject;

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
		super(id, systemObject, soffidObject, null, condition, dispatcherId, false);
	}

	public ExtensibleObjectMapping(Long id, String systemObject,
			SoffidObjectType soffidObject, String customObjectType,
			String condition, Long dispatcherId)
	{
		super(id, systemObject, soffidObject, customObjectType, condition, dispatcherId, false);
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
		super(systemObject, soffidObject, dispatcherId, false);
	}
	
	public static ExtensibleObjectMapping toExtensibleObjectMapping (com.soffid.iam.sync.intf.ExtensibleObjectMapping other)
	{
		ExtensibleObjectMapping eom = new ExtensibleObjectMapping();
		eom.attributes = AttributeMapping.toAttributeMappingList(other.getAttributes());
		eom.properties = other.getProperties();
		ObjectMapping.toObjectMapping(other, eom);
		return eom;
	}

	/**
	 * Creates a User list on a Usuari collection.
	 */
	public static java.util.List<ExtensibleObjectMapping> toExtensibleObjectMappingList (java.util.Collection<com.soffid.iam.sync.intf.ExtensibleObjectMapping> source)
	{
		if (source == null) return null;

		java.util.List<ExtensibleObjectMapping> target = new java.util.LinkedList<ExtensibleObjectMapping> ();
		for (com.soffid.iam.sync.intf.ExtensibleObjectMapping obj: source) 
		{
				target.add ( toExtensibleObjectMapping(obj));
		}
		return target;
	}

}
