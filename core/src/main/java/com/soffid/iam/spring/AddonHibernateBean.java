package com.soffid.iam.spring;

import java.io.IOException;
import java.util.Properties;

import org.hibernate.MappingException;
import org.springframework.core.io.ClassPathResource;

public class AddonHibernateBean
{
	String [] mappingResources;
	Properties hibernateProperties;

	public Properties getHibernateProperties ()
	{
		return hibernateProperties;
	}

	public void setHibernateProperties (Properties hibernateProperties)
	{
		this.hibernateProperties = hibernateProperties;
	}

	

	public String[] getMappingResources()
	{
		return mappingResources;
	}

	public void setMappingResources(String[] files)
	{
		this.mappingResources = files;
	}
	
	
	public void reconfigure ( org.hibernate.cfg.Configuration config ) throws MappingException, IOException 
	{
		if (mappingResources != null)
		{
			for (int i = 0; i < mappingResources.length; i++)
			{
				config.addInputStream (new ClassPathResource(mappingResources[i].trim()).getInputStream());
			}
		}
		if (hibernateProperties != null)
		{
			for (Object key : hibernateProperties.keySet())
			{
				config.setProperty(key.toString(), hibernateProperties.getProperty(key.toString()));
			}
		}
	}
}
