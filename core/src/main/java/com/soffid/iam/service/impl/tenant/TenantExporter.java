package com.soffid.iam.service.impl.tenant;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import com.soffid.tools.db.persistence.XmlReader;
import com.soffid.tools.db.schema.Column;
import com.soffid.tools.db.schema.Database;
import com.soffid.tools.db.schema.ForeignKey;
import com.soffid.tools.db.schema.Table;
import com.soffid.tools.db.updater.MySqlUpdater;

import es.caib.seycon.ng.ServiceLocator;
import es.caib.seycon.ng.exception.InternalErrorException;

public class TenantExporter {
	private Long id;
	private ObjectOutputStream out;
	private Database db;
	private List<Action> firstStep = new LinkedList<Action>();
	private List<Action> secondStep = new LinkedList<Action>();
	private LinkedList<Table> tables;
	private HashMap<String,Long> ids = new HashMap<String,Long>();
	private long nextId;
	private Connection conn;
	Log log = LogFactory.getLog(getClass());
	

	
	boolean ignoreFailures;
	
	public void export(Long id, OutputStream out) throws Exception {
		this.id = id;
		this.out = new ObjectOutputStream( out );
    	PathMatchingResourcePatternResolver rpr = new PathMatchingResourcePatternResolver(getClass().getClassLoader());

    	DataSource ds = (DataSource) ServiceLocator.instance().getService("dataSource"); 
    	conn = ds.getConnection();

    	db = new Database();
    	XmlReader reader = new XmlReader();
    	parseResources(rpr, db, reader, "console-ddl.xml");
    	parseResources(rpr, db, reader, "core-ddl.xml");
    	parseResources(rpr, db, reader, "plugin-ddl.xml");

        try {
        	createExportPlan();
        	export ();
        } finally {
        	conn.close();
        }
        	
	}

	private void createExportPlan() {
		tables = new LinkedList<Table>( db.tables );
		
		// Export remove tenant		
		for ( Iterator<Table> it = tables.iterator(); it.hasNext(); )
		{
			Table t = it.next();
			if (t.name.equals("SC_TENANT"))
				it.remove();
		}
		
		
		
		while ( !tables.isEmpty()) {
			boolean anyChange;
			do {
				anyChange = false;
				for ( Iterator<Table> it = tables.iterator(); it.hasNext(); )
				{
					Table t = it.next();
					if ( ! pendingForeignKeys (t) )
					{
						firstStep.add( new Action(Action.Operation.EXPORT_FULL, t));
						it.remove();
						anyChange = true;
					}
				}
			} while (anyChange);

			for ( Iterator<Table> it = tables.iterator(); it.hasNext(); )
			{
				Table t = it.next();
				if ( ! pendingMandatoryForeignKeys (t) )
				{
					firstStep.add( new Action(Action.Operation.EXPORT_NOFK, t));
					secondStep.add( new Action(Action.Operation.EXPORT_FK, t));
					it.remove();
					anyChange = true;
					break;
				}
			}
		} 
	}

	private boolean pendingMandatoryForeignKeys(Table t) {
		for ( ForeignKey fk: db.foreignKeys)
		{
			if (fk.tableName.equals(t.name) )
			{
				if ( isPending (fk.foreignTable))
				{
					for (String column: fk.columns)
					{
						Column col = t.findColumn(column, false);
						if (col != null && col.notNull)
							return true;
					}
				}
			}
		}
		return false;
	}

	private boolean pendingForeignKeys(Table t) {
		for ( ForeignKey fk: db.foreignKeys)
		{
			if (fk.tableName.equals(t.name) )
			{
				if ( isPending (fk.foreignTable))
					return true;
			}
		}
		return false;
	}

	private boolean isPending(String foreignTable) {
		for ( Table table: tables)
		{
			if (table.name.equals(foreignTable))
				return true;
		}
		return false;
	}

	private void export() throws IOException, SQLException, InternalErrorException {
		ids.put("SC_TENANT#"+id, 0L);
		nextId = 1L;
		for ( Action action: firstStep)
		{
			export(action);
		}
		for ( Action action: secondStep)
		{
			export(action);
		}
	}

	public void export(Action action) throws IOException, SQLException, InternalErrorException {
		if (action.operation == Action.Operation.EXPORT_FULL)
			exportFull ( action.table );
		if (action.operation == Action.Operation.EXPORT_NOFK)
			exportNoFK ( action.table );
		if (action.operation == Action.Operation.EXPORT_FK)
			exportFK ( action.table );
	}

	private void exportFK(Table table) throws IOException, SQLException, InternalErrorException {
		List<String> columns = new LinkedList<String>();

		StringBuffer sb = new StringBuffer();
		Map<String,String> foreignColumns = findForeignColumns ( table.name );
		for (Column c: table.columns)
		{
			if ( foreignColumns.containsKey(c.name) && ! c.notNull)
			{
				columns.add( c.name );
				if (sb.length() == 0)
					sb.append("SELECT ");
				else
					sb.append(",");
				sb.append(table.name+"."+c.name);
			}
		}
		sb.append(" FROM "+table.name);
		sb.append(generateQuery(table));

		log.info(sb.toString());

		try {
			Statement stmt = conn.createStatement();
			out.writeObject ( "update" );
			out.writeObject ( table.name );
			out.writeObject ( table.getPrimaryKey() );
			ResultSet rset = stmt.executeQuery(sb.toString());
			out.writeObject( columns.toArray(new String[0]));
			while (rset.next())
			{
				writeRow ( rset, table, columns, foreignColumns, true);
			}
		} catch (SQLException e) {
			if ( ignoreFailures && e.getMessage().contains("Table not found"))
			{
				// Ignore
			}
			else
				throw e;
		}
		out.writeObject(null);
	}

	private void exportNoFK(Table table) throws IOException, SQLException, InternalErrorException {
		List<String> columns = new LinkedList<String>();

		StringBuffer sb = new StringBuffer();
		Map<String,String> foreignColumns = findForeignColumns ( table.name );
		for (Column c: table.columns)
		{
			if ( c.primaryKey ||
				! foreignColumns.containsKey(c.name) ||
				c.notNull )
			{
				columns.add( c.name );
				if (sb.length() == 0)
					sb.append("SELECT ");
				else
					sb.append(",");
				sb.append(table.name+"."+c.name);
			}
		}
		sb.append(" FROM "+table.name);
		sb.append(generateQuery(table));

		log.info(sb.toString());

		try {
			Statement stmt = conn.createStatement();
			out.writeObject ( "insert" );
			out.writeObject( table.name );
			ResultSet rset = stmt.executeQuery(sb.toString());
			out.writeObject( columns.toArray(new String[0]));
			while (rset.next())
			{
				writeRow ( rset, table, columns, foreignColumns, false);
			}
			out.writeObject(null);
		} catch (SQLException e) {
			if ( ignoreFailures && e.getMessage().contains("Table not found"))
			{
				// Ignore
			}
			else
				throw e;
		}
	}

	private void exportFull(Table table) throws IOException, SQLException, InternalErrorException {
		List<String> columns = new LinkedList<String>();
		
		StringBuffer sb = new StringBuffer();
		Map<String,String> foreignColumns = findForeignColumns ( table.name );
		for (Column c: table.columns)
		{
			columns.add( c.name );
			if (sb.length() == 0)
				sb.append("SELECT ");
			else
				sb.append(",");
			sb.append(table.name+"."+c.name);
		}
		sb.append(" FROM "+table.name);
		sb.append(generateQuery(table));

		log.info(sb.toString());

		try {
			Statement stmt = conn.createStatement();
			out.writeObject ( "insert" );
			out.writeObject( table.name );
			ResultSet rset = stmt.executeQuery(sb.toString());
			out.writeObject( columns.toArray(new String[0]));
			while (rset.next())
			{
				writeRow ( rset, table, columns, foreignColumns, false);
			}
			out.writeObject(null);
		} catch (SQLException e) {
			if ( ignoreFailures && e.getMessage().contains("Table not found"))
			{
				// Ignore
			}
			else
				throw e;
		}
	}

	private Map<String,String> findForeignColumns(String tableName) {
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

	private void writeRow(ResultSet rset, Table table, List<String> columns, Map<String,String> foreignColumns, boolean update) throws SQLException, IOException {
		int i = 1;
		Object[]  row = new Object[ columns.size()];
		for ( String columnName: columns)
		{
			Column col = table.findColumn(columnName, true);
			Object value = rset.getObject(i);
			if (value instanceof Blob)
			{
				Blob blob = (Blob) value;
				value = blob.getBytes(0, (int) blob.length());
				blob.free();
			}
			if (foreignColumns.containsKey(columnName))
			{
				String foreignTable = foreignColumns.get(columnName);
				value = ids.get(foreignTable+"#"+value);
			}
			else if (col.primaryKey)
			{
				if (update)
					value = ids.get(table.name+"#"+value);
				else
				{
					ids.put(table.name+"#"+value, nextId);
					value = nextId;
				}
			}
			i ++;
		}
		out.writeObject(row);
	}

	private String generateQuery(Table table) throws InternalErrorException {
		return generateQuery(table, new HashSet<String>());
	}
	
	private String generateHint ( Table table)
	{
		for (int i=0; i < hints.length; i+=2)
			if (hints[i].equals(table.name))
			{
				String r = " " + hints[i+1];
				do {
					int m = r.indexOf("${tenantId}");
					if (m >= 0)
						r =  r.substring(0, m) + id + r.substring(m+11);
					else
						return r;
				} while (true);
			}
		return null;
		
	}
	private String generateQuery(Table table, Set<String> forbiddenTables) throws InternalErrorException {
		String hint = generateHint(table);
		if (hint != null)
			return hint;
		
		LinkedList<SearchPath> list = new LinkedList<SearchPath>();
		SearchPath sp = new SearchPath();
		sp.from = "";
		sp.where = "";
		sp.lastTable = table.name;
		sp.path =  new HashSet<String>(forbiddenTables);
		sp.path.add(table.name);
		list.add(sp);
		String s = generateDirectQuery(list);
		if (!s.isEmpty())
			return s;

		s = generateIndirectQuery ( table, sp.path);
		if (s.isEmpty())
		{
			throw new InternalErrorException("There is no filter for table "+table.name);
		}
		return s;
	}

	private String generateIndirectQuery(Table table, Set<String> forbiddenTables) throws InternalErrorException {
		StringBuffer query = new StringBuffer();
		for ( ForeignKey fk: references (table.name))
		{
			if (! forbiddenTables.contains(fk.tableName))
			{
				Table master = db.findTable(fk.tableName, false);
				String s = null;
				try {
					s = generateQuery ( master, forbiddenTables );
				} catch (InternalErrorException e) {}
				if (s != null && ! s.isEmpty())
				{
					if (query.length() > 0) query.append(" OR ");
					else query.append(" WHERE ");
					query.append(table.getPrimaryKey())
						.append(" IN ( SELECT ");
					for (String columnName: fk.columns)
					{
						query.append(columnName);
					}
					query.append(" FROM ")
						.append(fk.tableName)
						.append(s)
						.append(")");
				}
			}
		}
		return query.toString();
	}

	public String generateDirectQuery(LinkedList<SearchPath> list) {
		SearchPath sp;
		while ( ! list.isEmpty())
		{
			sp = list.poll();
			for ( ForeignKey fk: mandatoryForeignKeys(sp.lastTable))
			{
				if (fk.foreignTable.equals("SC_TENANT"))
				{
					for ( String col: fk.columns)
					{
						if (! sp.where.isEmpty())
							sp.where = sp.where+ " AND ";
						sp.where = sp.where + col + "="+id;
					}
					return sp.from+" WHERE "+sp.where;
				}
				else if ( !sp.path.contains(fk.foreignTable))
				{
					Table table = db.findTable(fk.foreignTable, false);
					String hint = generateHint( table );
					if (hint == null)
					{
						SearchPath newsp = new SearchPath();
						newsp.from = sp.from+", "+fk.foreignTable;
						newsp.where = sp.where;
						for ( int i = 0; i < fk.columns.size(); i++)
						{
							if (! newsp.where.isEmpty())
								newsp.where = newsp.where+ " AND ";
							newsp.where = newsp.where + sp.lastTable+"."+ fk.columns.get(i) + "="+
									fk.foreignTable+"."+fk.foreignKeyColumns.get(i);
						}
						newsp.lastTable = fk.foreignTable;
						newsp.path = new HashSet<String>(sp.path);
						newsp.path.add(newsp.lastTable);
						list.add(newsp);
					} else {
						String where = sp.where;
						for ( int i = 0; i < fk.columns.size(); i++)
						{
							if ( !where.isEmpty())
								where = where + " AND ";
							where = where +
									sp.lastTable+"."+ fk.columns.get(i) + " IN (SELECT "+
									table.getPrimaryKey()+
									" FROM "+table.name+ hint+ ")";
						}
						return sp.from + " WHERE " + where;
					}
						
				}
			}
		}
		return "";
	}

	private List<ForeignKey> mandatoryForeignKeys(String lastTable) {
		List<ForeignKey> result = new LinkedList<ForeignKey>();
		
		Table t = db.findTable(lastTable, true);
		
		for ( ForeignKey fk: db.foreignKeys)
		{
			if (fk.tableName.equals(t.name) )
			{
				boolean mandatory = true;
				for (String column: fk.columns)
				{
					Column col = t.findColumn(column, false);
					if (col != null && ! col.notNull &&
							!col.name.equals("TENANT_")) // Hack for JBPM modules
						mandatory = false;
				}
				if (mandatory)
					result.add(fk);
			}
		}
		return result;
	}

	private List<ForeignKey> references(String lastTable) {
		List<ForeignKey> result = new LinkedList<ForeignKey>();
		
		for ( ForeignKey fk: db.foreignKeys)
		{
			if (fk.foreignTable.equals(lastTable) )
			{
				result.add(fk);
			}
		}
		return result;
	}

	private void parseResources(ResourcePatternResolver rpr, Database db,
			XmlReader reader, String path) throws Exception {
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

	String[] hints = new String[] {
			"BPM_DATABASE_PROPERTY", "WHERE 1=0",
			"BPM_FILE_SYSTEM", "WHERE 1=0",
			"JBPM_ID_GROUP", "WHERE 1=0",
			"JBPM_ID_MEMBERSHIP", "WHERE 1=0",
			"JBPM_ID_USER", "WHERE 1=0",
			"JBPM_ID_PERMISSIONS", "WHERE 1=0",
			"JBPM_VARIABLEACCESS", "WHERE 1=0",
			"JBPM_PROCESSDEFINITION", "WHERE ID_ IN (SELECT PROCESSDEFINITION_ FROM JBPM_MODULEDEFINITION WHERE TENANT_=${tenantId}",
//			"JBPM_BYTEARRAY", 
//					"WHERE ID_ IN (SELECT BYTEARRAYVALUE_ "
//					+ "FROM JBPM_VARIABLEINSTANCE, JBPM_MODULEINSTANCE "
//					+ "WHERE JBPM_VARIABLEINSTANCE.PROCESSINSTANCE_ = JBPM_MODULE.ID_"
//					+ "FROM JBPM_MODULEDEFINITION WHERE TENANT_=${tenantId}",
			"JBPM_VARIABLEINSTANCE", 
					", JBPM_MODULEINSTANCE "
					+ "WHERE JBPM_VARIABLEINSTANCE.PROCESSINSTANCE_ = JBPM_MODULEINSTANCE.PROCESSINSTANCE_ "
					+ "AND JBPM_MODULEINSTANCE.TENANT_=${tenantId}",
			"JBPM_PROCESSINSTANCE", 
				", JBPM_MODULEINSTANCE "
				+ "WHERE JBPM_PROCESSINSTANCE.ID_ = JBPM_MODULEINSTANCE.PROCESSINSTANCE_ "
				+ "AND JBPM_MODULEINSTANCE.TENANT_=${tenantId}",
			"JBPM_BYTEBLOCK",
				" WHERE PROCESSFILE_ IN ( SELECT JBPM_LOG.NEWBYTEARRAY_ FROM JBPM_LOG, JBPM_TOKEN, JBPM_MODULEINSTANCE "
				+								 "WHERE JBPM_LOG.TOKEN_=JBPM_TOKEN.ID_ AND JBPM_TOKEN.PROCESSINSTANCE_=JBPM_MODULEINSTANCE.PROCESSINSTANCE_ AND JBPM_MODULEINSTANCE.TENANT_=${tenantId}) "
				+ "OR PROCESSFILE_ IN ( SELECT JBPM_LOG.OLDBYTEARRAY_ FROM JBPM_LOG, JBPM_TOKEN, JBPM_MODULEINSTANCE " 
				+                                "WHERE JBPM_LOG.TOKEN_=JBPM_TOKEN.ID_ AND JBPM_TOKEN.PROCESSINSTANCE_=JBPM_MODULEINSTANCE.PROCESSINSTANCE_ AND JBPM_MODULEINSTANCE.TENANT_=${tenantId})"
				+ "OR PROCESSFILE_ IN ( SELECT BYTEARRAYVALUE_ FROM JBPM_VARIABLEINSTANCE , JBPM_MODULEINSTANCE "
				+								 "WHERE JBPM_VARIABLEINSTANCE.PROCESSINSTANCE_ = JBPM_MODULEINSTANCE.PROCESSINSTANCE_AND JBPM_MODULEINSTANCE.TENANT_=${tenantId}) " 
				+ "OR PROCESSFILE_ IN ( SELECT ID_ FROM JBPM_MODULEDEFINITION WHERE JBPM_MODULEDEFINITION.TENANT_=${tenantId})" ,
			"JBPM_BYTEARRAY",
				" WHERE JBPM_BYTEARRAY.ID_ IN ( SELECT JBPM_LOG.NEWBYTEARRAY_ FROM JBPM_LOG, JBPM_TOKEN, JBPM_MODULEINSTANCE "
				+								 "WHERE JBPM_LOG.TOKEN_=JBPM_TOKEN.ID_ AND JBPM_TOKEN.PROCESSINSTANCE_=JBPM_MODULEINSTANCE.PROCESSINSTANCE_ AND JBPM_MODULEINSTANCE.TENANT_=${tenantId}) "
				+ "OR JBPM_BYTEARRAY.ID_ IN ( SELECT JBPM_LOG.OLDBYTEARRAY_ FROM JBPM_LOG, JBPM_TOKEN, JBPM_MODULEINSTANCE " 
				+                                "WHERE JBPM_LOG.TOKEN_=JBPM_TOKEN.ID_ AND JBPM_TOKEN.PROCESSINSTANCE_=JBPM_MODULEINSTANCE.PROCESSINSTANCE_ AND JBPM_MODULEINSTANCE.TENANT_=${tenantId})"
				+ "OR JBPM_BYTEARRAY.ID_ IN ( SELECT BYTEARRAYVALUE_ FROM JBPM_VARIABLEINSTANCE , JBPM_MODULEINSTANCE "
				+								 "WHERE JBPM_VARIABLEINSTANCE.PROCESSINSTANCE_ = JBPM_MODULEINSTANCE.PROCESSINSTANCE_AND JBPM_MODULEINSTANCE.TENANT_=${tenantId}) " 
				+ "OR JBPM_BYTEARRAY.FILEDEFINITON_ IN ( SELECT ID_ FROM JBPM_MODULEDEFINITION WHERE JBPM_MODULEDEFINITION.TENANT_=${tenantId})" ,
			"BPM_PROHIE",
				" WHERE BPM_PROHIE.PRH_PARPRO IN ( SELECT JBPM_MODULEINSTANCE.PROCESSINSTANCE_ FROM JBPM_MODULEINSTANCE "
				+								 "WHERE JBPM_MODULEINSTANCE.TENANT_=${tenantId}) ",
			"SC_ICONES",
				" WHERE SC_ICONES.ICO_ID IN (SELECT PUE_ICON FROM SC_PUNENT WHERE PUE_TEN_ID=${tenantId}) OR "
				+" SC_ICONES.ICO_ID IN (SELECT PUE_ICON2 FROM SC_PUNENT WHERE PUE_TEN_ID=${tenantId}) ",
			"SC_TIPEXE",
				" WHERE 1 = 0 ",
			"SC_SEQUENCE",
				" WHERE 1 = 0 ",
			"JBPM_TASKINSTANCE",
				",JBPM_MODULEINSTANCE WHERE JBPM_TASKINSTANCE.PROCINST_=JBPM_MODULEINSTANCE.PROCESSINSTANCE_ AND JBPM_MODULEINSTANCE.TENANT_=${tenantId}) ",
			"JBPM_POOLEDACTOR",
				",JBPM_TASKACTORPOOL,JBPM_TASKINSTANCE,JBPM_MODULEINSTANCE "
				+ "WHERE JBPM_POOLEDACTOR.POOLEDACTOR_=JBPM.TASKACTORPOOL.ID_ AND JBPM_TASKINSTANCE.ID_=JBPM_TASKACTORPOOL.TASKINSTANCE_ AND JBPM_TASKINSTANCE.PROCINST_=JBPM_MODULEINSTANCE.PROCESSINSTANCE_ AND JBPM_MODULEINSTANCE.TENANT_=${tenantId}) ",
			"JBPM_TASKACTORPOOL",
				",JBPM_TASKINSTANCE,JBPM_MODULEINSTANCE "
				+ "WHERE JBPM_TASKINSTANCE.ID_=JBPM_TASKACTORPOOL.TASKINSTANCE_ AND JBPM_TASKINSTANCE.PROCINST_=JBPM_MODULEINSTANCE.PROCESSINSTANCE_ AND JBPM_MODULEINSTANCE.TENANT_=${tenantId}) ",
			"JBPM_NODE",
				",JBPM_MODULEDEFINITION WHERE JBPM_NODE.PROCESSDEFINITION_=JBPM_MODULEDEFINITION.PROCESSDEFINITION_ AND JBPM_MODULEDEFINITION.TENANT_=${tenantId}) ",
			"JBPM_DECISIONCONDITION",
				",JBPM_NODE,JBPM_MODULEDEFINITION "
				+ "WHERE JBPM_DECISIONCONDITION.DECISION_ = JBPM_NODE.ID_ AND "
				+ "JBPM_NODE.PROCESSDEFINITION_=JBPM_MODULEDEFINITION.PROCESSDEFINITION_ AND "
				+ "JBPM_MODULEDEFINITION.TENANT_=${tenantId}) "
	};
}


class SearchPath {
	String from;
	String where;
	String lastTable;
	Set<String> path;
}

