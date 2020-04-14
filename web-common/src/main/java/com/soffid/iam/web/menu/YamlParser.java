package com.soffid.iam.web.menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class YamlParser {
	String line;
	int lines = 0;
	BufferedReader reader;
	StringWriter writer;
	
	public Object parse(String src) throws IOException
	{
		if (src == null)
			return null;
		reader = new BufferedReader( new StringReader(src));
		readLine();
		if (line.trim().startsWith("["))
		{
			JSONArray s = new JSONArray(src);
			return s;
		}
		if (line.trim().startsWith("{"))
		{
			return new JSONObject(src);
		}
		String prefix = getIndent();
		Object o = readUnknown (prefix);
		if (line != null)
			throw new IOException("Unexpected line "+lines+": "+line);
		return o;
	}

	public String getIndent() {
		int i = 0;
		while (i < line.length() && (line.charAt(i) == ' ' || line.charAt(i) == '\t')) 
			i++;
		String prefix = line.substring(0, i);
		return prefix;
	}

	private Object readUnknown(String prefix) throws IOException {
		if (line.startsWith(prefix) && line.startsWith(prefix + "-"))
		{
			JSONArray list = new JSONArray();
			int i  = prefix.length()+1; 
			while (i < line.length() && (line.charAt(i) == ' ' || line.charAt(i) == '\t'))
				i++; 
			readArray (list, line.substring(0,  i));
			return list;
		}
		else if (line.startsWith(prefix + " ") ||
				line.startsWith(prefix+"\t"))
		{
			throw new IOException("Unexpected line "+lines+": "+line);
		}
		else
		{
			return readObject (prefix);
		}
	}

	private void readArray( JSONArray list, String prefix) throws IOException {
		boolean first = true;
		do
		{
			if (line.startsWith(prefix + " ") || line.startsWith(prefix+"\t"))
			{
				throw new IOException("Unexpected identation at line "+lines+": "+line);
			}
			first = false;
			if (! line.contains(":"))
			{
				Object value = parseValue( line.substring(prefix.length()).trim());
				list.put(value);
				readLine();
			}
			else
			{
				String prefix2 = prefix.replaceAll("-", " ");
				list.put( readUnknown(prefix2));
			}
		} while (line != null && line.startsWith(prefix));
	}

	private Object parseValue(String line) {
		line = line.trim();
		if (line.startsWith("\"") && line.endsWith("\""))
			return line.substring(1,line.length()-1);
		if (line.trim().startsWith("["))
		{
			JSONArray s = new JSONArray(line);
			return s;
		}
		if (line.trim().startsWith("{"))
		{
			return new JSONObject(line);
		}
		return line;
	}

	private JSONObject readObject( String prefix) throws IOException {
		JSONObject o = new JSONObject();
		boolean first = true;
		do
		{
			if (line.startsWith(prefix + " ") ||
					line.startsWith(prefix+"\t"))
			{
				throw new IOException("Unexpected identation at line "+lines+": "+line);
			}
			first = false;
			Object[] v = readTagValue (line.substring(prefix.length()));
			readLine();
			if (v.length > 1)
			{
				o.put((String)v[0], v[1]);
			}
			else if (line.startsWith(prefix+ " ") ||
					line.startsWith(prefix+"\t") ||
					line.startsWith(prefix+ "-"))
			{
				String prefix3 = getIndent();
				o.put( (String) v[0], readUnknown(prefix3));
			}
			else
				throw new IOException("Expecting more identation at line "+lines+": "+line);
		} while (line != null && line.startsWith(prefix));
		return o;
	}

	private Object[] readTagValue(String substring) throws IOException {
		int i = substring.indexOf(":");
		if ( i < 0)
			throw new IOException("Expecting ':' at line "+lines+": "+line);
		String tag = substring.substring(0, i).trim();
		if (tag.startsWith(" ") || tag.startsWith("\t"))
			throw new IOException("Expecting less identation at line "+lines+": "+line);
		String value = substring.substring(i+1).trim();
		if (value.isEmpty())
		{
			return new Object[] { tag };
		}
		else
		{
			return new Object[] { tag, parseValue(value) };
			
		}
	}


	public void readLine() throws IOException {
		do {
			lines++;
			line=reader.readLine();
		} while (line != null && (line.trim().isEmpty() || line.trim().startsWith("#")));
	}
	
	public static void main(String args[]) throws IOException
	{
		String v =
				  "- name: test\n"
				+ "  value: test2\n"
				+ "- name: test3\n"
				+ "  value: test3\n"
				+ "- name: test4\n"
				+ "  value:\n"
				+ "     t1: a1\n"
				+ "     t2: a2\n"
				+ "     t3: [ a , b,c, d]\n"
				+ "     t4:\n"
				+ "     - 1\n"
				+ "     - 2\n"
				+ "     - 3\n"
				+ "  sort: false\n";
		System.out.println(v);
		System.out.println( new YamlParser().parse(v));
	}
}
