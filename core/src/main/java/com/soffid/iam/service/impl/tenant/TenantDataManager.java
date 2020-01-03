package com.soffid.iam.service.impl.tenant;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import com.soffid.tools.db.persistence.XmlReader;
import com.soffid.tools.db.schema.Database;
import com.soffid.tools.db.schema.ForeignKey;
import com.soffid.tools.db.schema.Table;

import es.caib.seycon.ng.ServiceLocator;
import es.caib.seycon.ng.exception.InternalErrorException;

public class TenantDataManager {

	protected Database db;
	protected Connection conn;
	protected boolean ignoreFailures;
	protected Log log = LogFactory.getLog(getClass());

	public TenantDataManager() {
		super();
	}

	protected void loadDatabaseDefinition() throws SQLException, Exception, IOException, InternalErrorException {
		PathMatchingResourcePatternResolver rpr = new PathMatchingResourcePatternResolver(getClass().getClassLoader());
	
		DataSource ds = (DataSource) ServiceLocator.instance().getService("dataSource"); 
		conn = ds.getConnection();
	
		db = new Database();
		XmlReader reader = new XmlReader();
		parseResources(rpr, db, reader, "console-ddl.xml");
		parseResources(rpr, db, reader, "core-ddl.xml");
		parseResources(rpr, db, reader, "plugin-ddl.xml");
	
	}

	private void parseResources(ResourcePatternResolver rpr, Database db, XmlReader reader, String path) throws Exception {
		Enumeration<URL> resources = Thread.currentThread().getContextClassLoader().getResources(path);
		while (resources.hasMoreElements())
		{
			reader.parse(db, resources.nextElement().openStream());
		}
	}

	public boolean isIgnoreFailures() {
		return ignoreFailures;
	}

	public void setIgnoreFailures(boolean ignoreFailures) {
		this.ignoreFailures = ignoreFailures;
	}

	protected Map<String,String> findForeignColumns(String tableName) {
		Map<String,String> result = new HashMap<String,String>();
		
		Table t = db.findTable(tableName, true);
		
		for ( ForeignKey fk: db.foreignKeys)
		{
			if (fk.tableName.equals(t.name) )
			{
				for (String col: fk.columns)
					result.put(col, fk.foreignTable);
			}
		}
		return result;
	}

}