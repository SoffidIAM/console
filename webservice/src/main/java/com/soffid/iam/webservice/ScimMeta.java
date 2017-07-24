package com.soffid.iam.webservice;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

import javax.ws.rs.Path;

import com.soffid.iam.utils.ConfigurationCache;

public class ScimMeta implements Serializable {
	String resourceType;
	Date created;
	Date lastModified;
	String location;
	
	
	public String getResourceType() {
		return resourceType;
	}
	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getLastModified() {
		return lastModified;
	}
	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	public void setLocation(Class<?> cl, String id)
	{
		String base = ConfigurationCache.getProperty("soffid.webservice.url");
		if ( base == null)
		{
			try {
				base = "http://"+InetAddress.getLocalHost().getHostName()+":8080/webservice";
			} catch (UnknownHostException e) {
				throw new RuntimeException(e);
			}
		}
		Path p = (Path) cl.getAnnotation(Path.class);
		if (p == null)
			throw new RuntimeException("Class "+cl.getName()+" does not have a Path annotation");
		location = concat ( concat ( base, p.value()), id );
		
	}

	private String concat(String base, String value) {
		if (! base.endsWith("/") && ! value.startsWith("/"))
			return base + "/" + value;
		else if (base.endsWith("/") && value.startsWith("/"))
			return base + value.substring(1);
		else
			return base + value;
	}
}
