package es.caib.seycon.ng.sync.intf;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.soffid.iam.api.Account;
import com.soffid.iam.api.User;

import es.caib.seycon.ng.comu.SoffidObjectType;

public class ExtensibleObject implements Serializable, Map<String, Object>
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Map<String,Object> attributes = new HashMap<String, Object>();
	public int size()
	{
		return attributes.size();
	}

	public boolean isEmpty()
	{
		return attributes.isEmpty();
	}

	public boolean containsKey(Object key)
	{
		return attributes.containsKey(key);
	}

	public boolean containsValue(Object value)
	{
		return attributes.containsValue(value);
	}

	public Object get(Object key)
	{
		if (key instanceof String)
			return getAttribute((String)key);
		else
			return null;
	}

	public Object put(String key, Object value)
	{
		return setAttribute(key, value);
	}

	public Object remove(Object key)
	{
		return attributes.remove(key);
	}

	public void putAll(Map<? extends String, ? extends Object> m)
	{
		attributes.putAll(m);
	}

	public void clear()
	{
		attributes.clear();
	}

	public Set<String> keySet()
	{
		return attributes.keySet();
	}

	public Collection<Object> values()
	{
		return attributes.values();
	}

	public Set<java.util.Map.Entry<String, Object>> entrySet()
	{
		return attributes.entrySet();
	}

	public boolean equals(Object o)
	{
		return attributes.equals(o);
	}

	String objectType;

	public void setObjectType(String objectType)
	{
		this.objectType = objectType;
	}

	public String getObjectType()
	{
		return objectType;
	}

	public ExtensibleObject()
	{
	}

	public Object getAttribute (String attribute)
	{
		return attributes.get(attribute);
				
	}

	public Object setAttribute (String attribute, Object value)
	{
		return attributes.put(attribute, value);
	}

	public void removeAttribte(String attribute)
	{
		attributes.remove(attribute);
	}
	
	public Set<String> getAttributes ()
	{
		return attributes.keySet();
	}
	
	public String toString ()
	{
		return attributes.toString();
	}
}


