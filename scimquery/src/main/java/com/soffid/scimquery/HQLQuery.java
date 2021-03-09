package com.soffid.scimquery;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
	
	StringBuffer countQueryString = new StringBuffer();
	
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

	Map<String,String> joins = new HashMap<String,String>();
	public Map<String, String> getJoins() {
		return joins;
	}

	public StringBuffer getQueryString() {
		return queryString;
	}

	public void setQueryString(StringBuffer queryString) {
		this.queryString = queryString;
	}

	public void setJoinString(StringBuffer joinString) {
		this.joinString = joinString;
	}

	public void setWhereString(StringBuffer whereString) {
		this.whereString = whereString;
	}

	public StringBuffer getJoinString() {
		return joinString;
	}

	public StringBuffer getWhereString() {
		return whereString;
	}
	
	public StringBuffer getOrderByString() {
		StringBuffer sb = new StringBuffer();
		
		if (orderBy == null || orderBy.isEmpty()) return sb;
		for (String s: orderBy) {
			if (sb.length() > 0 ) sb.append(", ");
			sb.append(s);
		}
		return sb;
	}

	public String toCountString ()
	{
		StringBuffer sb = new StringBuffer();
		sb.append (getCountQueryString())
			.append(getJoinString());
		if (getWhereString().length() > 0)
			sb.append("\nwhere ")
			  .append(getWhereString());
		return sb.toString();
	}

	public String toString ()
	{
		StringBuffer sb = new StringBuffer();
		sb.append (getQueryString())
			.append(getJoinString());
		if (getWhereString().length() > 0)
			sb.append("\nwhere ")
			  .append(getWhereString());
		if (getOrderByString().length() > 0)
			sb.append("\norder by ")
				.append(getOrderByString());
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

	String rootObject = null;

	private List<String> orderBy;
	public String getRootObject() {
		return rootObject;
	}

	public void setRootObject(String rootObject) {
		this.rootObject = rootObject;
	}

	public StringBuffer getCountQueryString() {
		return countQueryString;
	}

	public void setCountQueryString(StringBuffer countQueryString) {
		this.countQueryString = countQueryString;
	}

	public void setOrderBy(List<String> orderBy) {
		this.orderBy = orderBy;
	}

	public List<String> getOrderBy() {
		return orderBy;
	}
}
