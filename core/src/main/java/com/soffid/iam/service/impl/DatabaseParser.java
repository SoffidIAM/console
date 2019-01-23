package com.soffid.iam.service.impl;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import com.soffid.tools.db.persistence.XmlReader;
import com.soffid.tools.db.schema.Database;

public class DatabaseParser {
	public Database parse () throws Exception
	{
    	PathMatchingResourcePatternResolver rpr = new PathMatchingResourcePatternResolver(getClass().getClassLoader());

    	Database db = new Database();
    	XmlReader reader = new XmlReader();
    	parseResources(rpr, db, reader, "console-ddl.xml");
    	parseResources(rpr, db, reader, "core-ddl.xml");
    	parseResources(rpr, db, reader, "plugin-ddl.xml");
    	return db;
	}

	private void parseResources(ResourcePatternResolver rpr, Database db,
			XmlReader reader, String path) throws IOException, Exception {
		Enumeration<URL> resources = Thread.currentThread().getContextClassLoader().getResources(path);
    	while (resources.hasMoreElements())
    	{
    		reader.parse(db, resources.nextElement().openStream());
    	}
	}
}
