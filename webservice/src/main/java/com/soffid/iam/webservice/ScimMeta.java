package com.soffid.iam.webservice;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

import javax.ws.rs.Path;

import com.soffid.iam.utils.ConfigurationCache;

public class ScimMeta implements Serializable
{
	private static final long serialVersionUID = -281653653884123556L;
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
		String base = ConfigurationCache.getProperty("soffid.webservice.url"); //$NON-NLS-1$
		if ( base == null)
		{
			try {
				base = "http://"+InetAddress.getLocalHost().getHostName()+":8080/webservice"; //$NON-NLS-1$ //$NON-NLS-2$
			} catch (UnknownHostException e) {
				throw new RuntimeException(e);
			}
		}
		Path p = (Path) cl.getAnnotation(Path.class);
		if (p == null)
			throw new RuntimeException(String.format(Messages.getString("ScimMeta.missingPathAnnotation"), cl.getName())); //$NON-NLS-1$
		location = concat ( concat ( base, p.value()), id );
		
	}

	private String concat(String base, String value) {
		if (! base.endsWith("/") && ! value.startsWith("/")) //$NON-NLS-1$ //$NON-NLS-2$
			return base + "/" + value; //$NON-NLS-1$
		else if (base.endsWith("/") && value.startsWith("/")) //$NON-NLS-1$ //$NON-NLS-2$
			return base + value.substring(1);
		else
			return base + value;
	}
}
