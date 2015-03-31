package com.soffid.scimquery;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import com.soffid.scimquery.conf.ClassConfig;

public class HQLQuery {
	ClassConfig classConfig;
	
	
	public HQLQuery(ClassConfig classConfig) {
		super();
		this.classConfig = classConfig;
	}

	public ClassConfig getClassConfig() {
		return classConfig;
	}

	public void setClassConfig(ClassConfig classInfo) {
		this.classConfig = classConfig;
	}

	StringBuffer queryString = new StringBuffer();
	
	StringBuffer joinString = new StringBuffer();
	
	StringBuffer whereString = new StringBuffer();
	
	int nextParameter = 1;
	
	boolean nonHQLAttributeUsed = false;
	
	Map<String,Object> parameters = new HashMap<String, Object>();

	public int getNextParameter() {
		return nextParameter ++;
	}

	public Map<String, Object> getParameters() {
		return parameters;
	}


	int nextObject = 1;
	
	/**
	 * Maps paths to object names
	 */
	Map<String,String> objects = new HashMap<String, String>();

	public int getNextObject() {
		return nextObject ++;
	}

	public Map<String, String> getObjects() {
		return objects;
	}

	public StringBuffer getQueryString() {
		return queryString;
	}

	public StringBuffer getJoinString() {
		return joinString;
	}

	public StringBuffer getWhereString() {
		return whereString;
	}
	

	public String toString ()
	{
		StringBuffer sb = new StringBuffer();
		sb.append (getQueryString())
			.append(getJoinString())
			.append("\nwhere ")
			.append(getWhereString());
		return sb.toString();
	}

	public void dump (PrintStream out)
	{
		out.println(toString());
		for (String param: parameters.keySet())
		{
			out.print ("   ");
			out.print(param);
			out.print(" = ");
			out.println (parameters.get(param));
		}
	}

	public boolean isNonHQLAttributeUsed() {
		return nonHQLAttributeUsed;
	}

	public void setNonHQLAttributeUsed(boolean nonHQLAttributeUsed) {
		this.nonHQLAttributeUsed = nonHQLAttributeUsed;
	}
}
