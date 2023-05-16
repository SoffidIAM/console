package com.soffid.iam.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;

import org.json.JSONArray;
import org.json.JSONObject;

public class Yaml2Json {
	String line;
	int lines = 0;
	BufferedReader reader;
	StringWriter writer;
	
	public String transform(String src) throws IOException
	{
		if (src == null)
			return null;
		return transform (new StringReader(src));
		
	}
	
	public String transform(Reader src) throws IOException
	{
		if (src == null)
			return null;
		reader = new BufferedReader( src );
		writer = new StringWriter();
		readLine();
		if (line.trim().startsWith("["))
		{
			return new JSONArray(src).toString();
		}
		if (line.trim().startsWith("{"))
		{
			return new JSONObject(src).toString();
		}
		String prefix = getIndent();
		readUnknown (prefix);
		if (line != null)
			throw new IOException("Unexpected line "+lines+": "+line);
		return writer.toString();
	}

	public String getIndent() {
		int i = 0;
		while (i < line.length() && (line.charAt(i) == ' ' || line.charAt(i) == '\t')) 
			i++;
		String prefix = line.substring(0, i);
		return prefix;
	}

	private void readUnknown(String prefix) throws IOException {
		if (line.startsWith(prefix) && line.startsWith(prefix + "-"))
		{
			int i  = prefix.length()+1; 
			while (i < line.length() && (line.charAt(i) == ' ' || line.charAt(i) == '\t'))
				i++; 
			readArray (line.substring(0,  i));
		}
		else if (line.startsWith(prefix + " ") ||
				line.startsWith(prefix+"\t"))
		{
			throw new IOException("Unexpected line "+lines+": "+line);
		}
		else
		{
			readObject (prefix);
		}
	}

	private void readArray( String prefix) throws IOException {
		boolean first = true;
		do
		{
			if (line.startsWith(prefix + " ") || line.startsWith(prefix+"\t"))
			{
				throw new IOException("Unexpected identation at line "+lines+": "+line);
			}
			if (first)
				writer.append("[");
			else
				writer.append(",");
			first = false;
			if (! line.contains(":"))
			{
				String value = line.substring(prefix.length()).trim();
				readLine();
				if (value.equals( ">")) {
					value = readMultiLine(prefix.replaceAll("-", " "), true, false);
				}
				else if (value.equals( "|")) {
					value = readMultiLine(prefix.replaceAll("-", " "), true, true);
				}
				else if (value.equals( ">-")) {
					value = readMultiLine(prefix.replaceAll("-", " "), false, false);
				}
				else if (value.equals( "|-")) {
					value = readMultiLine(prefix.replaceAll("-", " "), false, true);
				}
				writer.append(quoteValue( value ));
			}
			else
			{
				String prefix2 = prefix.replaceAll("-", " ");
				readUnknown(prefix2);
			}
		} while (line != null && line.startsWith(prefix));
		writer.append("]");
	}

	private void readObject( String prefix) throws IOException {
		boolean first = true;
		do
		{
			if (line.startsWith(prefix + " ") ||
					line.startsWith(prefix+"\t"))
			{
				throw new IOException("Unexpected identation at line "+lines+": "+line);
			}
			if (first)
				writer.append("{");
			else
				writer.append(",");
			first = false;
			String[] v = readTagValue (line.substring(prefix.length()), prefix);
			writer.append(v[0]).append(":");
			if (v.length > 1)
			{
				writer.append(v[1]);
			}
			else if (line.startsWith(prefix+ " ") ||
					line.startsWith(prefix+"\t") ||
					line.startsWith(prefix+ "-"))
			{
				String prefix3 = getIndent();
				readUnknown(prefix3);
			}
			else
				throw new IOException("Expecting more identation at line "+lines+": "+line);
		} while (line != null && line.startsWith(prefix));
		writer.append("}");
	}

	private String[] readTagValue(String substring, String prefix) throws IOException {
		int i = substring.indexOf(":");
		if ( i < 0)
			throw new IOException("Expecting ':' at line "+lines+": "+line);
		String tag = substring.substring(0, i).trim();
		if (tag.startsWith(" ") || tag.startsWith("\t"))
			throw new IOException("Expecting less identation at line "+lines+": "+line);
		String value = substring.substring(i+1).trim();
		readLine();
		if (value.isEmpty())
		{
			return new String[] { quote(tag) };
		}
		else if (value.equals( ">")) {
			value = readMultiLine(prefix, true, false);
			return new String[] { quote(tag), quoteValue(value) };
		}
		else if (value.equals( "|")) {
			value = readMultiLine(prefix, true, true);
			return new String[] { quote(tag), quoteValue(value) };
		}
		else if (value.equals( ">-")) {
			value = readMultiLine(prefix, false, false);
			return new String[] { quote(tag), quoteValue(value) };
		}
		else if (value.equals( "|-")) {
			value = readMultiLine(prefix, false, true);
			return new String[] { quote(tag), quoteValue(value) };
		}
		else
		{
			return new String[] { quote(tag), quoteValue(value) };
		}
	}

	private String readMultiLine(String prefix, boolean trailNl, boolean keepNl) throws IOException {
		StringBuffer sb = new StringBuffer();
		while (line != null && (
				line.startsWith(prefix + " ") ||
					line.startsWith(prefix+"\t")))
		{
			if (sb.length() > 0 && keepNl)
				sb.append('\n');
			else
				sb.append(' ');
			sb.append(line.substring(prefix.length()).trim());
			readLine();
		}
		if (trailNl)
			sb.append('\n');
		return sb.toString();
	}

	private String quote(String value) {
		if (value.startsWith("\"") && value.endsWith("\""))
			value = value.substring(1, value.length()-1);
		return "\""+value.replaceAll("\\\\", "\\\\\\\\").replaceAll("\"", "\\\\\"").replace("\n", "\\\n") + "\"";
	}

	private String quoteValue(String value) {
		if (value.startsWith("[") && value.endsWith("]"))
		{
			String s = "[";
			for (String value2: value.substring(1, value.length()-1).trim().split(" *, *"))
			{
				if (s.length()>1) s+=",";
				s += quoteValue(value2);
			}
			s += "]";
			return s;
		}
		else if (value.equals("true") || value.equals("false"))
			return value;
		else
			return quote(value);
	}

	public void readLine() throws IOException {
		do {
			lines++;
			line=reader.readLine();
		} while (line != null && 
				(line.trim().isEmpty() || line.startsWith("#")));
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
		System.out.println( new Yaml2Json().transform(v));
	}
}
