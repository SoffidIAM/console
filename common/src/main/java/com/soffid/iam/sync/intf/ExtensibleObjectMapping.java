package com.soffid.iam.sync.intf;

import java.util.Collection;
import java.util.Map;

import com.soffid.iam.api.AttributeMapping;
import com.soffid.iam.api.ObjectMapping;
import com.soffid.iam.api.ObjectMappingTrigger;
import com.soffid.iam.api.SoffidObjectType;

public class ExtensibleObjectMapping extends ObjectMapping
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7332145350848632617L;
	
	Map<String,String> properties = null;
	Collection<AttributeMapping> attributes =  null;
	Collection<ObjectMappingTrigger> triggers =  null;

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
			SoffidObjectType soffidObject, String condition, Long dispatcherId, boolean authoritative)
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
	}

	public ExtensibleObjectMapping(String systemObject,
			SoffidObjectType soffidObject, Long dispatcherId)
	{
		super(systemObject, soffidObject, dispatcherId, false); 
	}
	
	public static ExtensibleObjectMapping toExtensibleObjectMapping (es.caib.seycon.ng.sync.intf.ExtensibleObjectMapping other)
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
	public static java.util.List<ExtensibleObjectMapping> toExtensibleObjectMappingList (java.util.Collection<es.caib.seycon.ng.sync.intf.ExtensibleObjectMapping> source)
	{
		if (source == null) return null;

		java.util.List<ExtensibleObjectMapping> target = new java.util.LinkedList<ExtensibleObjectMapping> ();
		for (es.caib.seycon.ng.sync.intf.ExtensibleObjectMapping obj: source) 
		{
				target.add ( toExtensibleObjectMapping(obj));
		}
		return target;
	}

	public Collection<ObjectMappingTrigger> getTriggers()
	{
		return triggers;
	}

	public void setTriggers(Collection<ObjectMappingTrigger> triggers)
	{
		this.triggers = triggers;
	}

}
