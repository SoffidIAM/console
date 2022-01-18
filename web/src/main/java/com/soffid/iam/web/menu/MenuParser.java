package com.soffid.iam.web.menu;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.metainfo.ComponentInfo;
import org.zkoss.zk.ui.metainfo.PageDefinition;
import org.zkoss.zk.ui.metainfo.Property;

import com.soffid.iam.utils.Security;

public class MenuParser {
	static Map<String,List<MenuOption>> maps = new HashMap<String,List<MenuOption>>();
	
	public List<MenuOption> getMenus(String cfg) throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException, JSONException {
		List<MenuOption> options = maps.get(cfg);
		if (options == null)
		{
			options = parse(cfg);
			maps.put(cfg, options);
		}
		
		options = applyPermissions(options);
		return options;
	}

	private List<MenuOption> applyPermissions(List<MenuOption> options) {
		List<MenuOption> r = new LinkedList<>();
		for (Iterator<MenuOption> iterator = options.iterator();
				iterator.hasNext();) {
			MenuOption src = iterator.next();
			MenuOption option = (MenuOption) src.clone();
			List<MenuOption> children = option.getOptions();
			if (children != null ) {
				option.setOptions( applyPermissions(children) );
				if (option.getOptions().isEmpty())
				{
					continue;
				}
				if (option.getOptions().size() == 1)
					option = option.getOptions().get(0);
			}  else {
				option.setOptions(null);
			}
			if (option.getPermissions() != null && option.getPermissions().length > 0)
			{
				boolean found = false;
				for (String p: option.getPermissions())
				{
					if (Security.isUserInRole(p)) {
						found = true;
						break;
					}
				}
				if (!found)
					continue;
			}
			r.add(option);
		}
		return r;
	}

	public List<MenuOption> parse(String cfg) throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException, JSONException {
		List<MenuOption> options = new LinkedList<MenuOption>();
		String rsrcName = "com/soffid/iam/menu/"+cfg;
		List<String> names = new LinkedList<>();
		names.add(rsrcName);
		if (rsrcName.endsWith(".yaml"))
			names.add(rsrcName.substring(0, rsrcName.length()-5)+"-addon.yaml");
		for (String name: names) {
			Enumeration<URL> r = MenuParser.class.getClassLoader().getResources(name);
			while (r.hasMoreElements())
			{
				URL next = r.nextElement();
				InputStream in = next.openStream();
				StringBuffer sb = new StringBuffer();
				InputStreamReader reader = new InputStreamReader(in, "UTF-8");
				int read;
				while ( (read=reader.read()) >= 0)
					sb.append((char) read);
				in.close();
				JSONArray array = (JSONArray) new YamlParser().parse(sb.toString());
				parseMenus (options, array );
			}
		}
		return options;
	}

	private void parseMenus(List<MenuOption> options, JSONArray array) throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException, JSONException {
		for ( int i = 0; i < array.length(); i++)
		{
			JSONObject obj = array.getJSONObject(i);
			String name = obj.optString("name");
			if (name == null)
			{
				throw new IOException("Error parsing menu file. Option with missing name");
			}
			MenuOption o = null;
			for (MenuOption o2: options) {
				if (o2.getLabel().equals(name))
				{
					o = o2; break;
				}
			}
			if (o == null)
			{
				o = new MenuOption();
				if ( obj.has("position")) {
					int pos = obj.getInt("position");
					if (pos >= options.size())
						options.add(o);
					else
						options.add(pos, o);
				}
				else
					options.add(o);
				o.setLabel(name);
			}
			String pagePermissions[] = null;
			if (obj.has("url"))
			{
				o.url = obj.getString("url");
				if (o.url != null && !o.url.trim().isEmpty() && !o.url.startsWith("/"))
					o.url = "/"+o.url;
				try {
					PageDefinition def = Executions.getCurrent().getPageDefinition(o.url);
					if (def != null)
					{
						for ( Object child: def.getChildren())
						{
							ComponentInfo cd = (ComponentInfo) child;
							if ("frame".equals(cd.getTag()))
							{
								for (Property prop: (List<Property>) cd.getProperties())
								{
									if (prop.getName().equals("permissions"))
									{
										String perms = prop.getRawValue();
										if (perms != null)
											pagePermissions = perms.split(" +");
									}
								}
							}
						}
					}
				} catch (Exception e) {
					// Page not found
				}
			}
			if (obj.has("small"))
				o.setSmall(obj.getBoolean("small"));
			if ( obj.has("img")) 
				o.setImg( obj.getString("img"));
			if (obj.has("permissions"))
			{
				List<String> p = o.permissions == null ? 
					new LinkedList<String>():
					new LinkedList<String>(	Arrays.asList(o.getPermissions()));
				JSONArray a = obj.getJSONArray("permissions");
				for (int j = 0; j < a.length(); j++)
					p.add(a.getString(j));
				o.permissions = p.toArray(new String[p.size()]);
			}
			if (obj.has("options"))
			{
				JSONArray options2 = obj.getJSONArray("options");
				if (o.options == null)
					o.options = new LinkedList<MenuOption>();
				parseMenus(o.options, options2);
			}
			if (obj.has("handler")) {
				o.handler =  (DynamicMenuHandler) Class.forName(obj.getString("handler")).newInstance();
			}
		}
	}
	
	
	public MenuOption findMenuOption (List<MenuOption> option, Page page) {
		if (page == null)
			return null;
		String path = page.getRequestPath();
		return findMenu (option, path);
	}
	
	public MenuOption findMenu(List<MenuOption> options, String path) {
		if (options == null || path == null)
			return null;
		for ( MenuOption option: options)
		{
			if (sameUrl(path, option.getUrl())) 
			{
				return option;
			}
			else
			{
				MenuOption o = findMenu (option.getOptions(), path);
				if (o != null)
					return o;
			}
		}
		return null;
	}

	public boolean sameUrl(String path, String path2) {
		if (path == null || path2 == null)
			return false;
		
		if (path.startsWith("/"))
			path = path.substring(1);
		if (path2.startsWith("/"))
			path2 = path2.substring(1);
		
		return path.equals(path2);
	}


}
