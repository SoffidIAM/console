package com.soffid.iam.addon.scim2.json;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.net.URL;
import java.sql.Date;
import java.util.Calendar;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.ServiceLocator;
import com.soffid.iam.api.DataType;

import es.caib.seycon.ng.comu.TypeEnumeration;
import es.caib.seycon.ng.comu.TypeEnumerationEnum;
import es.caib.seycon.ng.exception.InternalErrorException;

public class ResourceTypeGenerator {
	public JSONArray generate(String baseUrl) throws IOException {
		JSONArray r = new JSONArray();
		for (Enumeration<URL> e = getClass().getClassLoader().getResources("com/soffid/iam/addon/scim2/schemas.json"); e.hasMoreElements();) {
			URL url = e.nextElement();
			JSONObject o = parse(url);
			for (String key: o.keySet()) {
				JSONObject d = generateResourceType(baseUrl, key, o);
				r.put(d);
			}
		}
		return r;
	}
	
	public JSONObject generate(String baseUrl, String searchKey) throws IOException {
		JSONArray r = new JSONArray();
		for (Enumeration<URL> e = getClass().getClassLoader().getResources("com/soffid/iam/addon/scim2/schemas.json"); e.hasMoreElements();) {
			URL url = e.nextElement();
			JSONObject o = parse(url);
			for (String key: o.keySet()) {
				if (key.equals(searchKey)) {
					JSONObject d = generateResourceType(baseUrl, key, o);
					return d;
				}
			}
		}
		return null;
	}

	public JSONObject generateResourceType(String baseUrl, String key, JSONObject o) {
		JSONObject d = new JSONObject(template);
		JSONObject jsonObject = o.getJSONObject(key);
		if (jsonObject.has("description"))
			d.put("description", o.getString("description"));
		else
			d.put("description", key+" object");
		d.put("name", key);
		d.put("id", key);
		d.put("schema", "urn:soffid:"+jsonObject.get("class"));
		d.put("endpoint", baseUrl+key);
		d.getJSONObject("meta").put("location", baseUrl+"ResourceTypes/"+key);
		d.getJSONObject("meta").put("resourceType", "ResourceType");
		return d;
	}

	public static String template = "{\n"
			+ "     \"schemas\": [\"urn:ietf:params:scim:schemas:core:2.0:ResourceType\"],\n"
			+ "     \"id\": \"User\",\n"
			+ "     \"name\": \"User\",\n"
			+ "     \"endpoint\": \"/Users\",\n"
			+ "     \"schema\": \"urn:ietf:params:scim:schemas:core:2.0:User\",\n"
			+ "     \"meta\": {\n"
			+ "       \"location\": \"https://example.com/v2/ResourceTypes/User\",\n"
			+ "       \"resourceType\": \"ResourceType\"\n"
			+ "     }\n"
			+ "    }";

	public static String templateSchema = "{\n"
			+ "     \"schemas\": [\"urn:ietf:params:scim:schemas:core:2.0:Schema\"],\n"
			+ "     \"meta\": {\n"
			+ "       \"location\": \"https://example.com/v2/ResourceTypes/User\",\n"
			+ "       \"resourceType\": \"Schema\"\n"
			+ "     }\n"
			+ "    }";

	private JSONObject parse(URL url) throws IOException {
		StringBuffer sb = new StringBuffer();
		InputStream in = url.openStream();
		InputStreamReader reader = new InputStreamReader(in);
		for (int ch = reader.read(); ch != -1; ch = reader.read()) {
			sb.append((char)ch);
		}
		reader.close();
		in.close();
		return new JSONObject(sb.toString());
	}
	
	private JSONArray loadArray(String resource) throws IOException {
		StringBuffer sb = loadResource(resource);
		if (sb == null)
			return null;
		else
			return new JSONArray(sb.toString());
	}

	private JSONObject loadObject(String resource) throws IOException {
		StringBuffer sb = loadResource(resource);
		if (sb == null)
			return null;
		else
			return new JSONObject(sb.toString());
	}

	public StringBuffer loadResource(String resource) throws IOException {
		StringBuffer sb = new StringBuffer();
		InputStream in = getClass().getClassLoader().getResourceAsStream(resource);
		if (in == null)
			return null;
		InputStreamReader reader = new InputStreamReader(in);
		for (int ch = reader.read(); ch != -1; ch = reader.read()) {
			sb.append((char)ch);
		}
		reader.close();
		in.close();
		return sb;
	}

	public JSONArray generateSchema(String baseUrl) throws IOException, JSONException, InternalErrorException, ClassNotFoundException, NamingException, CreateException {
		JSONArray r = new JSONArray();
		for (Enumeration<URL> e = getClass().getClassLoader().getResources("com/soffid/iam/addon/scim2/schemas.json"); e.hasMoreElements();) {
			URL url = e.nextElement();
			JSONObject o = parse(url);
			for (String key: o.keySet()) {
				JSONObject d = generateSchema(baseUrl, key, o.getJSONObject(key));
				r.put(d);
			}
		}
		return r;
	}
	
	public JSONObject generateSchema(String baseUrl, String searchKey) throws IOException, JSONException, InternalErrorException, ClassNotFoundException, NamingException, CreateException {
		JSONArray r = new JSONArray();
		for (Enumeration<URL> e = getClass().getClassLoader().getResources("com/soffid/iam/addon/scim2/schemas.json"); e.hasMoreElements();) {
			URL url = e.nextElement();
			JSONObject o = parse(url);
			for (String key: o.keySet()) {
				JSONObject obj = o.getJSONObject(key);
				String name = "urn:soffid:"+obj.getString("class");
				if (name.equals(searchKey)) {
					JSONObject d = generateSchema(baseUrl, key, obj);
					return d;
				}
			}
		}
		return null;
	}

	public JSONObject generateSchema(String baseUrl, String key, JSONObject jsonObject) throws JSONException, IOException, InternalErrorException, ClassNotFoundException, NamingException, CreateException {
		JSONObject d = new JSONObject(templateSchema);
		d.put("name", key);
		String schemaName = "urn:soffid:"+jsonObject.getString("class");
		d.put("id", schemaName);
		if (jsonObject.has("description"))
			d.put("description", jsonObject.getString("description"));
		else
			d.put("description", key+" object");
		JSONArray atts = new JSONArray();
		d.put("attributes", atts);
		d.getJSONObject("meta").put("location", baseUrl+"Schemas/"+schemaName);
		d.getJSONObject("meta").put("resourceType", "Schema");
		if (!buildFromDescriptor(key, jsonObject, atts) &&
				!buildFromDictionary( key, jsonObject, atts) &&
				!buildFromJson(key, jsonObject, atts))
			buildFromClass(key, jsonObject, atts);
		return d;
	}

	private boolean buildFromJson(String key, JSONObject jsonObject, JSONArray atts) {
		String className = jsonObject.getString("class");
		className = className.replace(".", "/")+".ui.json";
		try {
			JSONObject d = loadObject(className);
			if (d == null)
				return false;
			JSONArray atts2 = d.getJSONArray("attributes");
			for (int i = 0; i < atts2.length(); i++) {
				JSONObject att2 = atts2.getJSONObject(i);
				JSONObject a = new JSONObject();
				a.put("name", att2.getString("name"));
				a.put("type", translate (att2.getString("type")));
				a.put("required", att2.getBoolean("required"));
				a.put("caseExact", true);
				if (a.has("enumeration")) {
					List<String> literals = null;
					try {
						literals = (List<String>) Class.forName(a.getString("enumeration")).getMethod("literals").invoke(null);
					} catch (Exception e) {
					}
					if (literals != null) {
						JSONArray cv = new JSONArray();
						a.put("canonicalValues", cv);
						for (String value: literals) {
							cv.put(value);
						}
					}
				}
				a.put("mutability", att2.optBoolean("readonly") ? "readOnly": "readWrite");
				a.put("returned", "default");
				a.put("uniqueness", "none");
				a.put("multiValued", att2.optBoolean("multivalue"));
				atts.put(a);
			}
			// Add id
			JSONObject b = new JSONObject();
			b.put("name", "id");
			b.put("description","Internal identifier");
			b.put("type", "integer");
			b.put("mutability", "readOnly");
			b.put("returned", "default");
			b.put("uniqueness", "server");
			b.put("multiValued", false);
			
			String resource = jsonObject.optString("additionalResource");
			if (resource != null && ! resource.trim().isEmpty()) {
				JSONArray atts3 = loadArray(resource);
				for (int i = 0; i < atts3.length(); i++) 
				{
					atts.put(atts3.getJSONObject(i));
				}
			}
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	private String translate(String type) {
		if (type.equals("BOOLEAN"))
			return "boolean";
		if (type.equals("BINARY") || type.equals("HTML") ||
				type.equals("PHOTO")) 
			return "binary";
		if (type.equals("DATE") ||
				type.equals("DATE_TIME"))
			return "dateTime";
		if (type.equals("NUMBER") )
			return "integer";
		return "string";
	}

	private boolean buildFromDescriptor(String key, JSONObject jsonObject, JSONArray atts) throws IOException {
		String resource = jsonObject.optString("resource");
		if (resource == null || resource.trim().isEmpty())
			return false;
		JSONArray atts2 = loadArray(resource);
		if (atts2 == null)
			return false;
		for (int i = 0; i < atts2.length(); i++) 
			atts.put(atts2.getJSONObject(i));
		return true;
	}

	private boolean buildFromDictionary(String key, JSONObject jsonObject, JSONArray atts) throws IOException, JSONException, InternalErrorException, NamingException, CreateException {
		Collection<DataType> dt = EJBLocator.getAdditionalDataService().findDataTypesByObjectTypeAndName2(jsonObject.getString("class"), null);
		if (dt == null || dt.isEmpty()) {
			return false;
		}
		JSONArray customAttributes = null;
		for (DataType t: dt) {
			if (t.getType() == TypeEnumeration.SEPARATOR)
				continue;
			JSONObject a = new JSONObject();
			a.put("name", t.getName());
			a.put("description", t.getLabel());
			a.put("type", translate (t.getType()));
			a.put("required", t.isRequired());
			a.put("caseExact", true);
			if (t.getValues() != null && !t.getValues().isEmpty()) {
				JSONArray cv = new JSONArray();
				a.put("canonicalValues", cv);
				for (String value: t.getValues()) {
					if (value.contains(":")) value = value.substring(0, value.indexOf(':')).trim();
					cv.put(value);
				}
			} else if (t.getEnumeration() != null) {
				List<String> literals = null;
				try {
					literals = (List<String>) Class.forName(t.getEnumeration()).getMethod("literals").invoke(null);
				} catch (Exception e) {
				}
				if (literals != null) {
					JSONArray cv = new JSONArray();
					a.put("canonicalValues", cv);
					for (String value: literals) {
						cv.put(value);
					}
				}
			}
			a.put("mutability", t.isReadOnly() ? "readOnly": "readWrite");
			a.put("returned", "default");
			a.put("uniqueness", Boolean.TRUE.equals(t.getUnique()) ? "server": "none");
			a.put("multiValued", t.isMultiValued());
			if (Boolean.TRUE.equals(t.getBuiltin())) {
				atts.put(a);
			} else {
				if (customAttributes == null) {
					customAttributes = new JSONArray();
					JSONObject b = new JSONObject();
					b.put("name", "attributes");
					b.put("description","Custom attributes");
					b.put("type", "complex");
					b.put("mutability", "readWrite");
					b.put("returned", "default");
					b.put("uniqueness", "none");
					b.put("multiValued", false);
					b.put("subattributes", customAttributes);
					atts.put(b);
				}
				customAttributes.put(a);
			}
		}
		// Add id
		JSONObject b = new JSONObject();
		b.put("name", "id");
		b.put("description","Internal identifier");
		b.put("type", "integer");
		b.put("mutability", "readOnly");
		b.put("returned", "default");
		b.put("uniqueness", "server");
		b.put("multiValued", false);
		
		String resource = jsonObject.optString("additionalResource");
		if (resource != null && ! resource.trim().isEmpty()) {
			JSONArray atts2 = loadArray(resource);
			for (int i = 0; i < atts2.length(); i++) 
				atts.put(atts2.getJSONObject(i));
		}
		return true;
	}

	private String translate(TypeEnumeration type) {
		if (type == TypeEnumeration.BOOLEAN_TYPE)
			return "boolean";
		if (type == TypeEnumeration.BINARY_TYPE || type == TypeEnumeration.HTML ||
				type == TypeEnumeration.PHOTO_TYPE) 
			return "binary";
		if (type == TypeEnumeration.DATE_TIME_TYPE ||
				type == TypeEnumeration.DATE_TYPE)
			return "dateTime";
		if (type == TypeEnumeration.NUMBER_TYPE )
			return "integer";
		return "string";
	}

	private void buildFromClass(String key, JSONObject jsonObject, JSONArray atts) throws ClassNotFoundException {
		String className = jsonObject.getString("class");
		Class cl = Class.forName(className);
		while (cl != null)
		{
			for (Field f: cl.getDeclaredFields()) {
	        	if ( (f.getModifiers() & Modifier.STATIC) == 0 &&
	        			! ClassLoader.class.isAssignableFrom(f.getType())) {
					JSONObject a = new JSONObject();
					a.put("name", f.getName());
					a.put("type", translate (f.getGenericType()));
					a.put("required", false);
					a.put("caseExact", true);
					a.put("mutability", f.getName().equals("id") ? "readOnly": "readWrite");
					a.put("returned", "default");
					a.put("uniqueness", "none");
					a.put("multiValued", Collection.class.isAssignableFrom(f.getType()) || f.getType().isArray());
					atts.put(a);
	        	}
			}
			cl = cl.getSuperclass();
		}
	}

	private String translate(Type type) {
		Class cl = type instanceof Class ? (Class) type: null;
		ParameterizedType pt = type instanceof ParameterizedType ? (ParameterizedType) type: null;
		if (pt != null && pt.getRawType() instanceof Class)
			cl = (Class) pt.getRawType();
		if (cl != null) {
			if (cl == Boolean.class || cl == boolean.class)
				return "boolean";
			if ( byte[].class.isAssignableFrom(cl)) 
				return "binary";
			if ( Date.class.isAssignableFrom(cl) ||
					Calendar.class.isAssignableFrom(cl))
				return "dateTime";
			if ( Integer.class.isAssignableFrom(cl) ||
					Long.class.isAssignableFrom(cl) ||
					int.class.isAssignableFrom(cl) ||
					long.class.isAssignableFrom(cl))
				return "integer";
			if ( Integer.class.isAssignableFrom(cl) ||
					Long.class.isAssignableFrom(cl) ||
					int.class.isAssignableFrom(cl) ||
					long.class.isAssignableFrom(cl))
				return "decimal";
			if ( String.class.isAssignableFrom(cl))
				return "string";
			if (Collection.class.isAssignableFrom(cl)) 
			{
				if (pt != null && pt.getActualTypeArguments().length > 0) 
					return translate (pt.getActualTypeArguments()[0]);
				else
					return "string";
			}
		}
		return "complex";
	}

}
