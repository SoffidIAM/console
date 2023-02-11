package com.soffid.iam.model.impl;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Session;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import com.soffid.tools.db.persistence.XmlReader;
import com.soffid.tools.db.schema.Database;
import com.soffid.tools.db.schema.ForeignKey;

import es.caib.seycon.ng.exception.SeyconException;

public class SchemaTools {
	static Database database = null;
	static Hashtable<String, List<PreDeleteListener>> listeners = new Hashtable<>();
	
	private Database getDatabase() throws IOException, Exception {
		if (database == null) {
	    	Database db = new Database();
	    	XmlReader reader = new XmlReader();
	    	PathMatchingResourcePatternResolver rpr = new PathMatchingResourcePatternResolver(getClass().getClassLoader());
	    	parseResources(rpr, db, reader, "console-ddl.xml");
	    	parseResources(rpr, db, reader, "core-ddl.xml");
	    	parseResources(rpr, db, reader, "plugin-ddl.xml");
	    	database = db;
		}
		return database;
	}
	private void parseResources(ResourcePatternResolver rpr, Database db,
			XmlReader reader, String path) throws IOException, Exception {
		Enumeration<URL> resources = Thread.currentThread().getContextClassLoader().getResources(path);
    	while (resources.hasMoreElements())
    	{
    		reader.parse(db, resources.nextElement().openStream());
    	}
	}
	
	public void registerPreDeleteListener (String table, PreDeleteListener listener) {
		List<PreDeleteListener> l = listeners.get(table);
		if (l == null) {
			l = new LinkedList<>();
			listeners.put(table, l);
		}
		l.add(listener);
	}
	
	public void deleteDependencies (Session sesson, String table, Long id) {
		try {
			List<PreDeleteListener> l = listeners.get(table);
			if (l != null) {
				for (PreDeleteListener pdl: l) {
					pdl.onDelete(id);
				}
			}
			Database db = getDatabase();
			for ( ForeignKey fk: db.foreignKeys) {
				if (fk.foreignTable.equals(table) &&
						fk.columns.size() == 1) {
					sesson.createSQLQuery("DELETE FROM "+fk.tableName+" WHERE "+fk.columns.get(0)+"=:id")
						.setParameter("id", id)
						.executeUpdate();
				}
			}
		} catch (RuntimeException e) {
			throw (RuntimeException) e;
		} catch (Exception e) {
			throw new SeyconException(e.getMessage(), e);
		}
	}
}
