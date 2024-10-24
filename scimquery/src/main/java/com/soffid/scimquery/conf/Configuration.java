package com.soffid.scimquery.conf;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class Configuration {
	
	static Map<String, ClassConfig> currentConfig = new HashMap<String, ClassConfig>();
	
	public static ClassConfig getClassConfig (Class<?> clazz) throws UnsupportedEncodingException, JSONException, ClassNotFoundException
	{
		ClassConfig cc = getClassConfig(clazz.getCanonicalName());
		if (cc != null)
			cc.setBeanClass(clazz);
		return cc;
	}

	public static ClassConfig getClassConfig (String className) throws UnsupportedEncodingException, JSONException, ClassNotFoundException
	{
 		if (className == null)
			return null;

 		ClassConfig cc = currentConfig.get(className);
 		if (cc != null)
 			return cc;
 		
		String resourceName = className.replace('.', '/') + ".query.json";

		InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(resourceName);
		
		if (in == null)
			return null;
		
		try {
			JSONObject obj = new JSONObject( new JSONTokener( new InputStreamReader(in, "UTF-8")));
			
			
			cc = new ClassConfig();
			
			String hibernateClass = obj.getString("hibernateClass");
			cc.setClazz(className);
			cc.setHibernateClass(hibernateClass);
			cc.setAttributes(new HashMap<String, AttributeConfig>());
			
			JSONArray array = obj.getJSONArray("attributes");
			for (int i = 0; i < array.length(); i++)
			{
				JSONObject att = array.getJSONObject(i);
				AttributeConfig attConfig = new AttributeConfig();
				String attName = att.getString("name");
				String hibernateName = att.optString("hibernateName", null);
				String scimType = att.optString("type", null);
				attConfig.setAttributeName(attName);
				attConfig.setHibernateColumn(hibernateName);
				if (scimType != null && scimType.length() > 0)
				{
					attConfig.setScimType(Class.forName(scimType));
				}
				cc.getAttributes().put(attName, attConfig);
			}
		
			registerClass (cc);
			
			return cc;
		} catch (JSONException e ) {
			throw new JSONException("Error parsing resource "+resourceName+": "+e.getMessage());
		}
	}
	
	public static void registerClass (ClassConfig classConfig)
	{
		currentConfig.put(classConfig.getClazz(), classConfig);
	}

}
