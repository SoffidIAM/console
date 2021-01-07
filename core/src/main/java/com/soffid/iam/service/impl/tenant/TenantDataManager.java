package com.soffid.iam.service.impl.tenant;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import com.soffid.tools.db.persistence.XmlReader;
import com.soffid.tools.db.schema.Column;
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
	protected List<Action> firstStep = new LinkedList<Action>();
	protected List<Action> secondStep = new LinkedList<Action>();
	private LinkedList<Table> tables;
	protected Long id;
	String[] hints = new String[] {
				"BPM_DATABASE_PROPERTY", "WHERE 1=0",
				"BPM_FILE_SYSTEM", "WHERE 1=0",
				"JBPM_ID_GROUP", "WHERE 1=0",
				"JBPM_ID_MEMBERSHIP", "WHERE 1=0",
				"JBPM_ID_USER", "WHERE 1=0",
				"JBPM_ID_PERMISSIONS", "WHERE 1=0",
				"JBPM_VARIABLEACCESS", "WHERE 1=0",
				"JBPM_PROCESSDEFINITION", "WHERE ID_ IN (SELECT PROCESSDEFINITION_ FROM JBPM_MODULEDEFINITION WHERE TENANT_=${tenantId})",
				"JBPM_DELEGATION", "WHERE PROCESSDEFINITION_ IN (SELECT PROCESSDEFINITION_ FROM JBPM_MODULEDEFINITION WHERE TENANT_=${tenantId})",
	//			"JBPM_BYTEARRAY", 
	//					"WHERE ID_ IN (SELECT BYTEARRAYVALUE_ "
	//					+ "FROM JBPM_VARIABLEINSTANCE, JBPM_MODULEINSTANCE "
	//					+ "WHERE JBPM_VARIABLEINSTANCE.PROCESSINSTANCE_ = JBPM_MODULE.ID_"
	//					+ "FROM JBPM_MODULEDEFINITION WHERE TENANT_=${tenantId}",
				"JBPM_VARIABLEINSTANCE", 
						" WHERE PROCESSINSTANCE_ IN (SELECT JBPM_MODULEINSTANCE.PROCESSINSTANCE_ FROM JBPM_MODULEINSTANCE "
						+ "WHERE JBPM_MODULEINSTANCE.TENANT_=${tenantId})",
				"JBPM_PROCESSINSTANCE", 
					" WHERE ID_ IN (SELECT JBPM_MODULEINSTANCE.PROCESSINSTANCE_ FROM  JBPM_MODULEINSTANCE "
					+ "WHERE JBPM_MODULEINSTANCE.TENANT_=${tenantId})",
				"JBPM_TOKEN", 
					"WHERE PROCESSINSTANCE_ IN (SELECT JBPM_MODULEINSTANCE.PROCESSINSTANCE_ FROM JBPM_MODULEINSTANCE "
					+ "WHERE JBPM_MODULEINSTANCE.TENANT_=${tenantId})",
				"JBPM_JOB", 
					"WHERE JBPM_JOB.PROCESSINSTANCE_ IN (SELECT JBPM_MODULEINSTANCE.PROCESSINSTANCE_ FROM JBPM_TOKEN, JBPM_MODULEINSTANCE "
					+ "WHERE JBPM_MODULEINSTANCE.TENANT_=${tenantId})",
				"JBPM_BYTEBLOCK",
					" WHERE PROCESSFILE_ IN ( SELECT JBPM_LOG.NEWBYTEARRAY_ FROM JBPM_LOG, JBPM_TOKEN, JBPM_MODULEINSTANCE "
					+								 "WHERE JBPM_LOG.TOKEN_=JBPM_TOKEN.ID_ AND JBPM_TOKEN.PROCESSINSTANCE_=JBPM_MODULEINSTANCE.PROCESSINSTANCE_ AND JBPM_MODULEINSTANCE.TENANT_=${tenantId}) "
					+ "OR PROCESSFILE_ IN ( SELECT JBPM_LOG.OLDBYTEARRAY_ FROM JBPM_LOG, JBPM_TOKEN, JBPM_MODULEINSTANCE " 
					+                                "WHERE JBPM_LOG.TOKEN_=JBPM_TOKEN.ID_ AND JBPM_TOKEN.PROCESSINSTANCE_=JBPM_MODULEINSTANCE.PROCESSINSTANCE_ AND JBPM_MODULEINSTANCE.TENANT_=${tenantId})"
					+ "OR PROCESSFILE_ IN ( SELECT BYTEARRAYVALUE_ FROM JBPM_VARIABLEINSTANCE , JBPM_MODULEINSTANCE "
					+								 "WHERE JBPM_VARIABLEINSTANCE.PROCESSINSTANCE_ = JBPM_MODULEINSTANCE.PROCESSINSTANCE_ AND JBPM_MODULEINSTANCE.TENANT_=${tenantId}) " 
					+ "OR PROCESSFILE_ IN ( SELECT ID_ FROM JBPM_MODULEDEFINITION WHERE JBPM_MODULEDEFINITION.TENANT_=${tenantId})" ,
				"JBPM_BYTEARRAY",
					" WHERE JBPM_BYTEARRAY.ID_ IN ( SELECT JBPM_LOG.NEWBYTEARRAY_ FROM JBPM_LOG, JBPM_TOKEN, JBPM_MODULEINSTANCE "
					+								 "WHERE JBPM_LOG.TOKEN_=JBPM_TOKEN.ID_ AND JBPM_TOKEN.PROCESSINSTANCE_=JBPM_MODULEINSTANCE.PROCESSINSTANCE_ AND JBPM_MODULEINSTANCE.TENANT_=${tenantId}) "
					+ "OR JBPM_BYTEARRAY.ID_ IN ( SELECT JBPM_LOG.OLDBYTEARRAY_ FROM JBPM_LOG, JBPM_TOKEN, JBPM_MODULEINSTANCE " 
					+                                "WHERE JBPM_LOG.TOKEN_=JBPM_TOKEN.ID_ AND JBPM_TOKEN.PROCESSINSTANCE_=JBPM_MODULEINSTANCE.PROCESSINSTANCE_ AND JBPM_MODULEINSTANCE.TENANT_=${tenantId}) "
					+ "OR JBPM_BYTEARRAY.ID_ IN ( SELECT BYTEARRAYVALUE_ FROM JBPM_VARIABLEINSTANCE , JBPM_MODULEINSTANCE "
					+								 "WHERE JBPM_VARIABLEINSTANCE.PROCESSINSTANCE_ = JBPM_MODULEINSTANCE.PROCESSINSTANCE_ AND JBPM_MODULEINSTANCE.TENANT_=${tenantId}) " 
					+ "OR JBPM_BYTEARRAY.FILEDEFINITION_ IN ( SELECT ID_ FROM JBPM_MODULEDEFINITION WHERE JBPM_MODULEDEFINITION.TENANT_=${tenantId})" ,
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
					"WHERE JBPM_TASKINSTANCE.PROCINST_ IN (SELECT JBPM_MODULEINSTANCE.PROCESSINSTANCE_ "
					+ "FROM JBPM_MODULEINSTANCE WHERE JBPM_MODULEINSTANCE.TENANT_=${tenantId}) ",
				"JBPM_POOLEDACTOR",
					"WHERE ID_ IN (SELECT JBPM_TASKACTORPOOL.POOLEDACTOR_ FROM JBPM_TASKACTORPOOL,JBPM_TASKINSTANCE,JBPM_MODULEINSTANCE "
					+ "WHERE JBPM_TASKINSTANCE.ID_=JBPM_TASKACTORPOOL.TASKINSTANCE_ AND "
					+ "JBPM_TASKINSTANCE.PROCINST_=JBPM_MODULEINSTANCE.PROCESSINSTANCE_ AND "
					+ "JBPM_MODULEINSTANCE.TENANT_=${tenantId}) ",
				"JBPM_TASKACTORPOOL",
					"WHERE TASKINSTANCE_ IN (SELECT JBPM_TASKACTORPOOL.TASKINSTANCE_ FROM JBPM_TASKINSTANCE,JBPM_MODULEINSTANCE "
					+ "WHERE JBPM_TASKINSTANCE.PROCINST_=JBPM_MODULEINSTANCE.PROCESSINSTANCE_ AND JBPM_MODULEINSTANCE.TENANT_=${tenantId}) ",
				"JBPM_NODE",
					"WHERE PROCESSDEFINITION_ IN (SELECT JBPM_MODULEDEFINITION.PROCESSDEFINITION_ "
					+ "FROM JBPM_MODULEDEFINITION WHERE JBPM_MODULEDEFINITION.TENANT_=${tenantId}) ",
				"JBPM_DECISIONCONDITION",
					"WHERE DECISION_ IN (SELECT JBPM_NODE.ID_ FROM JBPM_NODE,JBPM_MODULEDEFINITION "
					+ "WHERE JBPM_NODE.PROCESSDEFINITION_=JBPM_MODULEDEFINITION.PROCESSDEFINITION_ AND "
					+ "JBPM_MODULEDEFINITION.TENANT_=${tenantId}) ",
				"SC_ATTRIB",
					" WHERE ATT_TEN_ID=${tenantId}",
				"SC_ENTGRP",
					" WHERE ENG_TEN_ID=${tenantId}",
				"SC_USEBEH",
					"WHERE UBE_USU_ID IN (SELECT USU_ID FROM SC_USUARI WHERE USU_TEN_ID=${tenantId})",
				"SC_RPANSW",
					"WHERE ANS_USER IN (SELECT USU_ID FROM SC_USUARI WHERE USU_TEN_ID=${tenantId})",
				"SC_RPQUES",
					" WHERE DQU_TEN_ID=${tenantId}",
				"SCV_RECROL",
					" WHERE RRO_TEN_ID=${tenantId}",
				"SCV_RECROD",
					" WHERE RRD_TEN_ID=${tenantId}",
				"SCV_RECUSR",
					" WHERE RUS_TEN_ID=${tenantId}",
				"SCV_RECIS",
					" WHERE RIS_TEN_ID=${tenantId}",
				"SCV_RECGRO",
					" WHERE RGR_TEN_ID=${tenantId}",
				"SC_SCTAHA",
					" WHERE 1=0",
				"SC_USUPRO",
					"WHERE UPR_IDPROC IN (SELECT JBPM_MODULEINSTANCE.PROCESSINSTANCE_ FROM  JBPM_MODULEINSTANCE "
						+ "WHERE JBPM_MODULEINSTANCE.TENANT_=${tenantId})"
					
		};

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

	protected void createExportPlan() {
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

	protected String generateQuery(Table table, boolean forDelete) throws InternalErrorException {
		return generateQuery(table, forDelete, new HashSet<String>());
	}

	private String generateHint(Table table) {
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

	private String generateQuery(Table table, boolean forDelete, Set<String> forbiddenTables) throws InternalErrorException {
		String hint = generateHint(table);
		if (hint != null)
			return hint;
		
		LinkedList<SearchPath> list = new LinkedList<SearchPath>();
		SearchPath sp = new SearchPath();
		sp.from = "";
		sp.where = "";
		sp.from2 = "";
		sp.where2 = "";
		sp.tail2 = "";
		sp.lastTable = table.name;
		sp.path =  new HashSet<String>(forbiddenTables);
		sp.path.add(table.name);
		list.add(sp);
		String s = generateDirectQuery(forDelete, list);
		if (!s.isEmpty())
			return s;
	
		s = generateIndirectQuery ( table, forDelete, sp.path);
		if (s.isEmpty())
		{
			throw new InternalErrorException("There is no filter for table "+table.name);
		}
		return s;
	}

	private String generateIndirectQuery(Table table, boolean forDelete, Set<String> forbiddenTables) throws InternalErrorException {
		StringBuffer query = new StringBuffer();
		for ( ForeignKey fk: references (table.name))
		{
			if (! forbiddenTables.contains(fk.tableName))
			{
				Table master = db.findTable(fk.tableName, false);
				String s = null;
				try {
					s = generateQuery ( master, forDelete, forbiddenTables );
				} catch (InternalErrorException e) {}
				if (s != null && ! s.isEmpty())
				{
					if (query.length() > 0) query.append(" OR ");
					else query.append(" WHERE ");
					query.append(table.name +"."+table.getPrimaryKey())
						.append(" IN ( SELECT ");
					for (String columnName: fk.columns)
					{
						query.append( fk.tableName+"."+columnName);
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

	public String generateDirectQuery(boolean forDelete, LinkedList<SearchPath> list) {
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
						
						if (! sp.where2.isEmpty())
							sp.where2 = sp.where2+ " AND ";
						sp.where2 = sp.where2 + col + "="+id;
					}
					if (forDelete)
						return (sp.from2.isEmpty()? "" : " WHERE "+sp.from2)+
								(sp.where2.isEmpty()? "" : " WHERE "+sp.where2)+
								sp.tail2;
					else
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
						if (sp.from2.isEmpty())
						{
							newsp.where2 = sp.where2;
							newsp.from2 = " (";
							boolean first = true;
							for ( int i = 0; i < fk.columns.size(); i++)
							{
								if (first)
									first = false;
								else
									newsp.from2 = newsp.from2 + ",";
								newsp.from2 = newsp.from2 + sp.lastTable+"."+ fk.columns.get(i);
							}
							newsp.from2 = newsp.from2 + ") IN ( SELECT ";
							newsp.tail2 = sp.tail2 + ")";
							first = true;
							for ( int i = 0; i < fk.columns.size(); i++)
							{
								if (first) first = false;
								else newsp.from2 = newsp.from2 + ", " ;
								newsp.from2 = newsp.from2 + fk.foreignTable+"."+fk.foreignKeyColumns.get(i);
							}
							newsp.from2 = newsp.from2 + " FROM "+fk.foreignTable;
						} else {
							newsp.from2 = sp.from2+", "+fk.foreignTable;
							newsp.where2 = sp.where2;
							newsp.tail2 = sp.tail2;
							for ( int i = 0; i < fk.columns.size(); i++)
							{
								if (! newsp.where2.isEmpty())
									newsp.where2 = newsp.where2+ " AND ";
								newsp.where2 = newsp.where2 + sp.lastTable+"."+ fk.columns.get(i) + "="+
										fk.foreignTable+"."+fk.foreignKeyColumns.get(i);
							}
						}
						newsp.lastTable = fk.foreignTable;
						newsp.path = new HashSet<String>(sp.path);
						newsp.path.add(newsp.lastTable);
						list.add(newsp);
					} else {
						String where = forDelete ? sp.where2 : sp.where;
						for ( int i = 0; i < fk.columns.size(); i++)
						{
							if ( !where.isEmpty())
								where = where + " AND ";
							where = where +
									sp.lastTable+"."+ fk.columns.get(i) + " IN (SELECT "+
									table.name+"."+table.getPrimaryKey()+
									" FROM "+table.name+ hint+ ")";
						}
						if ( forDelete)
							return (sp.from2.isEmpty()? "" : " WHERE "+sp.from2)+
									" WHERE " + where + sp.tail2;
						else
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

	private boolean isPending(String foreignTable) {
		for ( Table table: tables)
		{
			if (table.name.equals(foreignTable))
				return true;
		}
		return false;
	}

}
