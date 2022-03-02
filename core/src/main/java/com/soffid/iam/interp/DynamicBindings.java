package com.soffid.iam.interp;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import javax.script.Bindings;

public class DynamicBindings implements Bindings {
	static SecureServiceLocator serviceLocator = new SecureServiceLocator();
	private Map<String, Object> vars;

	public DynamicBindings(Map<String, Object> vars) {
		this.vars = vars;
	}

	@Override
	public int size() {
		return vars.size() + 1;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public boolean containsValue(Object value) {
		return vars.containsValue(value);
	}

	@Override
	public void clear() {
		vars.clear();
	}

	@Override
	public Set<String> keySet() {
		HashSet<String> set = new HashSet<String>(vars.keySet());
		set.add("serviceLocator");
		return set;
	}

	@Override
	public Collection<Object> values() {
		LinkedList<Object> l = new LinkedList<Object>(vars.values());
		l.add(serviceLocator);
		return l;
	}

	@Override
	public Set<Entry<String, Object>> entrySet() {
		HashSet<Entry<String, Object>> r = new HashSet<>();
		r.add(new Entry<String,Object>() {
			public String getKey() { return "serviceLocator";}
			public Object getValue() {return serviceLocator;}
			public Object setValue(Object value) {return serviceLocator;}
		});
		return r;
	}

	@Override
	public Object put(String name, Object value) {
		return vars.put(name, value);
	}

	@Override
	public void putAll(Map<? extends String, ? extends Object> toMerge) {
		vars.putAll(toMerge);
	}

	@Override
	public boolean containsKey(Object key) {
		return "serviceLocator".equals(key) || vars.containsKey(key);
	}

	@Override
	public Object get(Object key) {
		if ("serviceLocator".equals(key))
			return serviceLocator;
		else
			return vars.get(key);
	}

	@Override
	public Object remove(Object key) {
		return vars.remove(key);
	}

}
