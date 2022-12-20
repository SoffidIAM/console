package com.soffid.iam.addon.scim2.json;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

public class JSONParser {
	public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

	public<E> E load(JSONObject data, Class<E> clazz, String ignore[]) throws Exception {
		E r = clazz.newInstance();
		populate (r, data, ignore);
		return r;
	}

	private void populate(Object r, JSONObject o, String ignore[]) throws Exception {
		Class<? extends Object> clazz = r.getClass();
		for (String key: o.keySet()) {
			if (!key.equals("meta") && !key.equals("schemas")) {
				boolean skip = false;
				for (String i: ignore) {
					if (i.equals(key)) {skip = true; break;}; // Ignore
				}
				if (!skip) {
					Field field;
					field = findField (key, clazz);
					if (field == null)
						throw new Exception("Invalid property "+key);
					Object v = o.get(key);
					v = cast(field.getName(), field.getGenericType(), v);
					if (v != null) {
						String methodName = "set"+ key.substring(0,1).toUpperCase()+ key.substring(1);
						boolean found = false;
						for (Method m: clazz.getMethods()) {
							if (m.getName().equals(methodName) &&
									m.getParameterCount() == 1) {
								if (m.getParameters()[0].getType().isPrimitive() ||
										m.getParameters()[0].getType().isAssignableFrom(v.getClass())) {
									m.invoke(r, v);
									found = true;
									break;
								} else {
									try {
										Class<?> cl2 = m.getParameters()[0].getType();
										Method m2 = cl2.getMethod("fromString", String.class);
						            	if ((m2.getModifiers() & Modifier.STATIC) != 0) {
											Object o2 = m2.invoke(null, v.toString());
											m.invoke(r, o2);	
											found = true;
											break;
						            	}
									} catch (NoSuchMethodException e) {
										// Ignore
									}
								}
							}
						}
						if (!found)
							throw new Exception("Invalid property "+key);
					}
				}
			} 
		}
	}

	private Field findField(String key, Class<? extends Object> clazz) {
		while (clazz != null) {
			try {
				return clazz.getDeclaredField(key);
			} catch (NoSuchFieldException e) {
			}
			clazz = clazz.getSuperclass();
		}
		return null;
	}

	private Object cast(String att, Type rt, Object v) throws Exception {
		if (v == JSONObject.NULL)
			return null;
		
		ParameterizedType parameterizedType = null;
		Class clazz = null;
		if (rt instanceof ParameterizedType)
			parameterizedType = (ParameterizedType) rt;
		if (rt instanceof Class)
			clazz = (Class) rt;
		if (rt == String.class) {
			if (v instanceof String) return v;
			else if (v instanceof Boolean || v instanceof Long || v instanceof Integer || v instanceof Double || v instanceof Float)
				return v.toString();
		}
		else if (rt == Boolean.class || rt == boolean.class) {
			if (v instanceof Boolean) return v;
			else if (v instanceof String) return "true".equals(v);
			else if (v instanceof Long || v instanceof Integer) return ! "0".equals(v.toString());
		}
		else if (rt == Long.class || rt == long.class) {
			if (v instanceof Long) return v;
			else if (v instanceof String || v instanceof Integer) return Long.valueOf(v.toString());
		}
		else if (rt == Integer.class || rt == int.class) {
			if (v instanceof Integer) return v;
			else if (v instanceof String || v instanceof Long) return Integer.valueOf(v.toString());
		}
		else if (rt == Double.class || rt == double.class) {
			if (v instanceof Double) return v;
			else if (v instanceof String || v instanceof Float) return Double.valueOf(v.toString());
		}
		else if (rt == Float.class || rt == float.class) {
			if (v instanceof Float) return v;
			else if (v instanceof String || v instanceof Double) return Float.valueOf(v.toString());
		} else if (rt == Date.class) {
			if (v instanceof String) {
				try {
					return dateFormat2.parse((String)v);
				} catch (ParseException e) {
					try {
						return dateFormat.parse((String)v);
					} catch (ParseException e2) {
						
					}
				}
			}
			else if (v instanceof Integer || v instanceof Long) {
				return new Date( Long.parseLong(v.toString()) );
			}
		}
		else if (rt == Calendar.class) {
			if (v instanceof String) {
				try {
					Calendar c = Calendar.getInstance();
					c.setTime( dateFormat2.parse((String)v) );
					return c;
				} catch (ParseException e) {
					try {
						Calendar c = Calendar.getInstance();
						c.setTime( dateFormat.parse((String)v) );
						return c;
					} catch (ParseException e2) {
					}
				}
			}
			else if (v instanceof Integer || v instanceof Long) {
				Calendar c = Calendar.getInstance();
				c.setTimeInMillis(Long.parseLong(v.toString()) );
				return c;
			}
		}
		else if (rt == Collection.class || rt == List.class || rt == Set.class)
		{
			if (v instanceof JSONArray) {
				JSONArray array = (JSONArray) v;
				Collection l =  rt == Set.class? new HashSet(): new LinkedList();
				for (int i = 0; i < array.length(); i++) {
					Object o2 = cast (att, String.class, array.get(i));
					l.add(o2);
				}
				return l;
			} else {
				Collection l =  rt == Set.class? new HashSet(): new LinkedList();
				Object o2 = cast (att, String.class, v);
				l.add(o2);
				return l;
			}
		} else if (parameterizedType != null && (
					parameterizedType.getRawType() == Collection.class ||
					parameterizedType.getRawType() == List.class ||
					parameterizedType.getRawType() == Set.class ) )
		{
			
			if (v instanceof JSONArray) {
				JSONArray array = (JSONArray) v;
				Type t = parameterizedType.getActualTypeArguments()[0];
				Collection l = parameterizedType.getRawType() == Set.class ? new HashSet():  new LinkedList();
				for (int i = 0; i < array.length(); i++) {
					Object o2 = cast (att, t, array.get(i));
					l.add(o2);
				}
				return l;
			} else {
				JSONArray array = (JSONArray) v;
				Collection l = parameterizedType.getRawType() == Set.class ? new HashSet():  new LinkedList();
				Type t = parameterizedType.getActualTypeArguments()[0];
				Object o2 = cast (att, t, v);
				l.add(o2);
				return l;
			}
		}
		else if (clazz != null && clazz.isArray()) {
			if (v instanceof JSONArray) {
				JSONArray array = (JSONArray) v;
				Object l = Array.newInstance(clazz.getComponentType(), array.length());
				for (int i = 0; i < array.length(); i++) {
					Object o2 = cast (att, clazz.getComponentType(), array.get(i));
					Array.set(l, i, o2);
				}
				return l;
			}
		}
		else if (parameterizedType != null && parameterizedType.getRawType() == Map.class ||
				clazz != null && Map.class.isAssignableFrom(clazz)) {
			if (v instanceof JSONObject) {
				JSONObject jsonObject = (JSONObject) v;
				if (jsonObject.has("schemas") && jsonObject.get("schemas") instanceof JSONArray) {
					String schema = jsonObject.getJSONArray("schemas").getString(0);
					if (schema.toLowerCase().startsWith("urn:soffid:")) {
						try {
							Class s = Class.forName(schema.substring(11));
							Object target = s.newInstance();
							populate (target, jsonObject, new String[0]);
						} catch (ClassNotFoundException e) {
							throw new Exception("Unknown schema "+schema);
						}
					} else {
						Map m = new HashMap();
						for ( String key: jsonObject.keySet())
							m.put(key, cast(key, Object.class, jsonObject.get(key)));
						return m;
					}
				} else {
					Map m = new HashMap();
					for ( String key: jsonObject.keySet())
						m.put(key, cast(key, Object.class, jsonObject.get(key)));
					return m;
				}
			}
		}
		else if (v instanceof String)
		{
			return v;
		}
		else if (clazz != null) 
		{
			if (v instanceof JSONObject) {
				JSONObject jsonObject = (JSONObject) v;
				if (jsonObject.has("schemas") && jsonObject.get("schemas") instanceof JSONArray) {
					String schema = jsonObject.getJSONArray("schemas").getString(0);
					if (schema.toLowerCase().startsWith("urn:soffid:")) {
						try {
							Class s = Class.forName(schema.substring(11));
							Object target = s.newInstance();
							populate (target, jsonObject, new String[0]);
							return target;
						} catch (ClassNotFoundException e) {
							throw new Exception("Unknown schema "+schema);
						}
					} else {
						Map m = new HashMap();
						for ( String key: jsonObject.keySet())
							m.put(key, cast(key, Object.class, jsonObject.get(key)));
						return m;
					}
				} else {
					Map m = new HashMap();
					for ( String key: jsonObject.keySet())
						m.put(key, cast(key, Object.class, jsonObject.get(key)));
					return m;
				}
			}
			else if (v instanceof JSONArray) {
				JSONArray array = (JSONArray) v;
				List<Object> l = new LinkedList<Object>();
				for (int i = 0; i < array.length(); i++)
					l.add(cast(att+"["+i+"]", Object.class, array.get(i)));
				return l;
			}
			else if (clazz.isAssignableFrom(v.getClass()))
				return v;
		}

		throw new Exception("Invalid value for attribute "+att);
	}

}
