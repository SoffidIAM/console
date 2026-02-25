package com.soffid.iam.sync.intf;

import java.util.Collection;
import java.util.Map;

import com.soffid.iam.iga.api.AttributeMapping;
import com.soffid.iam.iga.api.ObjectMapping;
import com.soffid.iam.iga.api.ObjectMappingTrigger;
import com.soffid.iam.iga.api.SoffidObjectType;

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
	
	public Collection<ObjectMappingTrigger> getTriggers()
	{
		return triggers;
	}

	public void setTriggers(Collection<ObjectMappingTrigger> triggers)
	{
		this.triggers = triggers;
	}

	public boolean appliesToSoffidObject (ExtensibleObject soffidObject)
	{
		if (getSoffidObject().getValue().equals(soffidObject.getObjectType()))
			return ( ! getSoffidObject().equals(SoffidObjectType.OBJECT_CUSTOM) ||
				getSoffidCustomObject().equals(soffidObject.get("type"))) ;
		else if (getSoffidObject() == SoffidObjectType.OBJECT_CUSTOM && 
				getSoffidCustomObject().equals(soffidObject.getObjectType()))
			return true ;
		else
			return false;
	}

	public boolean appliesToSystemObject (ExtensibleObject soffidObject)
	{
		if (getSoffidObject().getValue().equals(soffidObject.getObjectType()))
			return ( ! getSoffidObject().equals(SoffidObjectType.OBJECT_CUSTOM) ||
				getSoffidCustomObject().equals(soffidObject.get("type")));
		else
			return false;
	}

}
