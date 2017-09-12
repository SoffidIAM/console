package es.caib.seycon.ng.sync.intf;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class WrappedExtensibleObject extends ExtensibleObject
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	com.soffid.iam.sync.intf.ExtensibleObject source;
	
	public WrappedExtensibleObject ( com.soffid.iam.sync.intf.ExtensibleObject source)
	{
		this.source = source;
	}
	
	
	public String toString ()
	{
		return source.toString();
	}


	public int size() {
		return source.size();
	}


	public boolean isEmpty() {
		return source.isEmpty();
	}


	public boolean containsKey(Object key) {
		return source.containsKey(key);
	}


	public boolean containsValue(Object value) {
		return source.containsValue(value);
	}


	public Object get(Object key) {
		return source.get(key);
	}


	public Object put(String key, Object value) {
		return source.put(key, value);
	}


	public Object remove(Object key) {
		return source.remove(key);
	}


	public void putAll(Map<? extends String, ? extends Object> m) {
		source.putAll(m);
	}


	public void clear() {
		source.clear();
	}


	public Set<String> keySet() {
		return source.keySet();
	}


	public Collection<Object> values() {
		return source.values();
	}


	public Set<java.util.Map.Entry<String, Object>> entrySet() {
		return source.entrySet();
	}


	public boolean equals(Object o) {
		return source.equals(o);
	}


	public void setObjectType(String objectType) {
		source.setObjectType(objectType);
	}


	public String getObjectType() {
		return source.getObjectType();
	}


	public int hashCode() {
		return source.hashCode();
	}


	public Object getAttribute(String attribute) {
		return source.getAttribute(attribute);
	}


	public Object setAttribute(String attribute, Object value) {
		return source.setAttribute(attribute, value);
	}


	public void removeAttribte(String attribute) {
		source.removeAttribte(attribute);
	}


	public Set<String> getAttributes() {
		return source.getAttributes();
	}
}


