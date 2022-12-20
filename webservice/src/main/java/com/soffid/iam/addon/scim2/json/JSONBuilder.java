package com.soffid.iam.addon.scim2.json;

import java.io.Closeable;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONPropertyIgnore;
import org.json.JSONPropertyName;
import org.json.JSONString;

import com.soffid.iam.utils.ConfigurationCache;

import es.caib.seycon.util.Base64;

public class JSONBuilder {
	private String server;
	private String[] attributes;
	private String[] excludedAttributes;

	public JSONBuilder(HttpServletRequest request) {
		if (request == null)
			server = "http://localhost:8080/soffid/webservice/scim2/v1/";
		else {
			server = ConfigurationCache.getProperty("soffid.externalURL");
			if (server == null)
				server = request.getScheme()+"://"+request.getHeader("Host")+"/";
			if (!server.endsWith("/"))
				server += "/";
			server += "soffid/webservice/scim2/v1/";
		}
	}

	public JSONObject build (Object bean) {
		JSONObject o = new JSONObject();
		populateMap(o, bean, null);
		return o;
	}
	
	
    private void populateMap(JSONObject o, Object bean, String path) {
        Class<?> klass = bean.getClass();
        Object id = null;
        // If klass is a System class then set includeSuperClass to false.

        boolean includeSuperClass = klass.getClassLoader() != null;

        while (klass != null) {
	        Field[] fields = klass.getDeclaredFields();
	        for (final Field field: fields) {
	        	if ( (field.getModifiers() & Modifier.STATIC) == 0) {
		        	String key = field.getName();
		        	Method method;
		        	try {
		        		method = klass.getMethod( (field.getType() == boolean.class ? "is" : "get" ) + key.substring(0,1).toUpperCase()+ key.substring(1) );
		        	} catch (NoSuchMethodException e) {
		        		continue;
		        	}
		            try {
		                final Object result = method.invoke(bean);
		                if (result != null && ! (result instanceof Class) && ! (result instanceof ClassLoader)) {
		                	String p = concatPath(path, key);
		                	if (key.equals("id")) id = result;
		                	if (isIncluded(p) && ! isExcluded(p))
		                	{
			                    o.put(key, wrap(result, p));
			                    if (result instanceof Closeable) {
			                        try {
			                            ((Closeable) result).close();
			                        } catch (IOException ignore) {
			                        }
			                    }
		                	}
		                }
		            } catch (IllegalAccessException ignore) {
		            } catch (IllegalArgumentException ignore) {
		            } catch (InvocationTargetException ignore) {
		            }
	        	}
	        }
	        klass = klass.getSuperclass();
        }
        if (id != null) {
        	JSONArray array = new JSONArray();
        	array.put("urn:soffid:"+bean.getClass().getName());
        	o.put("schemas", array);
        	JSONObject meta = new JSONObject();
        	o.put("meta", meta);
        	meta.put("resourceType", bean.getClass().getSimpleName());
        	meta.put("location", server+bean.getClass().getSimpleName()+"/"+id);
        }
    }

	private boolean isExcluded(String path) {
		if (excludedAttributes == null)
			return false;
		for (String att: excludedAttributes) {
			if (path.equals(att) || att.startsWith(path+"."))
				return true;
		}
		return false;
	}

	public boolean isIncluded(String path) {
		if (attributes == null)
			return true;
		for (String att: attributes) {
			if (path.equals(att) || 
					att.startsWith(path+".") ||
					path.startsWith(att+"."))
				return true;
		}
		return false;
	}

	public String concatPath(String path, String key) {
		return path == null ? key: path+"."+key;
	}

    public Object wrap(Object object, String path) {
        try {
            if (object == null) {
                return JSONObject.NULL;
            }
            if (object instanceof JSONObject || object instanceof JSONArray
                    || JSONObject.NULL.equals(object) || object instanceof JSONString
                    || object instanceof Byte || object instanceof Character
                    || object instanceof Short || object instanceof Integer
                    || object instanceof Long || object instanceof Boolean
                    || object instanceof Float || object instanceof Double
                    || object instanceof String || object instanceof BigInteger
                    || object instanceof BigDecimal || object instanceof Enum) {
                return object;
            }

            if (object instanceof Date) {
                String s = JSONParser.dateFormat2.format((Date) object);
                if (s.endsWith(".000"))
                	s = s.substring(0, s.length()-4);
                return s;
            }
            if (object instanceof Calendar) {
                String s = JSONParser.dateFormat2.format(((Calendar) object).getTime());
                if (s.endsWith(".000"))
                	s = s.substring(0, s.length()-4);
                return s;
            }
            if (object instanceof Collection) {
                Collection<?> coll = (Collection<?>) object;
            	JSONArray a = new JSONArray();
            	for (Object member: coll )
            		a.put(wrap( member, path));
                return a;
            }
            if (object instanceof byte[]) {
            	return Base64.encodeBytes((byte[])object, Base64.DONT_BREAK_LINES);
            }
            if (object.getClass().isArray()) {
            	JSONArray a = new JSONArray();
            	for (int i = 0; i < Array.getLength(object); i++ )
            		a.put(wrap( Array.get(object, i), path ));
                return a;
            }
            if (object instanceof Map) {
                Map<?, ?> map = (Map<?, ?>) object;
                JSONObject o = new JSONObject();
                for (Object key: map.keySet()) {
                	if (isIncluded(path+"."+key.toString()))
                		o.put(key.toString(), wrap(map.get(key), concatPath(path, key.toString())));
                }
                return o;
            }
            try {
            	Method m = object.getClass().getMethod("fromString", String.class);
            	if ((m.getModifiers() & Modifier.STATIC) != 0)
            		return object.toString();
            } catch (NoSuchMethodException e) {}
            Package objectPackage = object.getClass().getPackage();
            String objectPackageName = objectPackage != null ? objectPackage
                    .getName() : "";
            if (objectPackageName.startsWith("java.")
                    || objectPackageName.startsWith("javax.")
                    || object.getClass().getClassLoader() == null) {
                return object.toString();
            }
            JSONObject o = new JSONObject();
            populateMap(o, object, path);
        	JSONArray array = new JSONArray();
        	array.put("urn:soffid:"+object.getClass().getName());
        	o.put("schemas", array);
            return o;
        } catch (Exception exception) {
            return null;
        }
    }

    private boolean isValidMethodName(String name) {
        return !"getClass".equals(name) && !"getDeclaringClass".equals(name);
    }

    private String getKeyNameFromMethod(Method method) {
        final int ignoreDepth = getAnnotationDepth(method, JSONPropertyIgnore.class);
        if (ignoreDepth > 0) {
            final int forcedNameDepth = getAnnotationDepth(method, JSONPropertyName.class);
            if (forcedNameDepth < 0 || ignoreDepth <= forcedNameDepth) {
                // the hierarchy asked to ignore, and the nearest name override
                // was higher or non-existent
                return null;
            }
        }
        JSONPropertyName annotation = getAnnotation(method, JSONPropertyName.class);
        if (annotation != null && annotation.value() != null && !annotation.value().isEmpty()) {
            return annotation.value();
        }
        String key;
        final String name = method.getName();
        if (name.startsWith("get") && name.length() > 3) {
            key = name.substring(3);
        } else if (name.startsWith("is") && name.length() > 2) {
            key = name.substring(2);
        } else {
            return null;
        }
        // if the first letter in the key is not uppercase, then skip.
        // This is to maintain backwards compatibility before PR406
        // (https://github.com/stleary/JSON-java/pull/406/)
        if (Character.isLowerCase(key.charAt(0))) {
            return null;
        }
        if (key.length() == 1) {
            key = key.toLowerCase(Locale.ROOT);
        } else if (!Character.isUpperCase(key.charAt(1))) {
            key = key.substring(0, 1).toLowerCase(Locale.ROOT) + key.substring(1);
        }
        return key;
    }

    /**
     * Searches the class hierarchy to see if the method or it's super
     * implementations and interfaces has the annotation.
     *
     * @param <A>
     *            type of the annotation
     *
     * @param m
     *            method to check
     * @param annotationClass
     *            annotation to look for
     * @return the {@link Annotation} if the annotation exists on the current method
     *         or one of it's super class definitions
     */
    private static <A extends Annotation> A getAnnotation(final Method m, final Class<A> annotationClass) {
        // if we have invalid data the result is null
        if (m == null || annotationClass == null) {
            return null;
        }

        if (m.isAnnotationPresent(annotationClass)) {
            return m.getAnnotation(annotationClass);
        }

        // if we've already reached the Object class, return null;
        Class<?> c = m.getDeclaringClass();
        if (c.getSuperclass() == null) {
            return null;
        }

        // check directly implemented interfaces for the method being checked
        for (Class<?> i : c.getInterfaces()) {
            try {
                Method im = i.getMethod(m.getName(), m.getParameterTypes());
                return getAnnotation(im, annotationClass);
            } catch (final SecurityException ex) {
                continue;
            } catch (final NoSuchMethodException ex) {
                continue;
            }
        }

        try {
            return getAnnotation(
                    c.getSuperclass().getMethod(m.getName(), m.getParameterTypes()),
                    annotationClass);
        } catch (final SecurityException ex) {
            return null;
        } catch (final NoSuchMethodException ex) {
            return null;
        }
    }

    /**
     * Searches the class hierarchy to see if the method or it's super
     * implementations and interfaces has the annotation. Returns the depth of the
     * annotation in the hierarchy.
     *
     * @param <A>
     *            type of the annotation
     *
     * @param m
     *            method to check
     * @param annotationClass
     *            annotation to look for
     * @return Depth of the annotation or -1 if the annotation is not on the method.
     */
    private static int getAnnotationDepth(final Method m, final Class<? extends Annotation> annotationClass) {
        // if we have invalid data the result is -1
        if (m == null || annotationClass == null) {
            return -1;
        }

        if (m.isAnnotationPresent(annotationClass)) {
            return 1;
        }

        // if we've already reached the Object class, return -1;
        Class<?> c = m.getDeclaringClass();
        if (c.getSuperclass() == null) {
            return -1;
        }

        // check directly implemented interfaces for the method being checked
        for (Class<?> i : c.getInterfaces()) {
            try {
                Method im = i.getMethod(m.getName(), m.getParameterTypes());
                int d = getAnnotationDepth(im, annotationClass);
                if (d > 0) {
                    // since the annotation was on the interface, add 1
                    return d + 1;
                }
            } catch (final SecurityException ex) {
                continue;
            } catch (final NoSuchMethodException ex) {
                continue;
            }
        }

        try {
            int d = getAnnotationDepth(
                    c.getSuperclass().getMethod(m.getName(), m.getParameterTypes()),
                    annotationClass);
            if (d > 0) {
                // since the annotation was on the superclass, add 1
                return d + 1;
            }
            return -1;
        } catch (final SecurityException ex) {
            return -1;
        } catch (final NoSuchMethodException ex) {
            return -1;
        }
    }

	public void setAttributes(String[] split) {
		this.attributes = split;
		Arrays.sort(this.attributes);
	}

	public void setExcludedAttributes(String[] split) {
		this.excludedAttributes = split;
		Arrays.sort(this.excludedAttributes);
	}

	public String getServer() {
		return server;
	}
}
