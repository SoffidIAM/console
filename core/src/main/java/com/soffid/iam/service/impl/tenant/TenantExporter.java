package com.soffid.iam.service.impl.tenant;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.naming.InitialContext;

import java.util.HashMap;

import com.soffid.iam.api.Tenant;
import com.soffid.tools.db.schema.Column;
import com.soffid.tools.db.schema.Table;
import com.soffid.tools.db.updater.MySqlUpdater;

import es.caib.seycon.ng.exception.InternalErrorException;

public class TenantExporter extends TenantDataManager {
	private ObjectOutputStream out;
	private HashMap<String,Long> ids = new HashMap<String,Long>();
	private long nextId;
	private Tenant tenant;
	
	public void export(Tenant t, OutputStream out) throws Exception {
		this.id = t.getId();
		this.tenant = t;
		ZipOutputStream zipOut = new ZipOutputStream(out);
		ZipEntry zentry = new ZipEntry(t.getName()+".soffid-dump");
		zipOut.putNextEntry(zentry);
		
		this.out = new ObjectOutputStream( zipOut );
    	loadDatabaseDefinition();
        	
    	try {
    		createExportPlan();
    		export ();
    		this.out.close();
    		zipOut.close();
    	} finally {
    		conn.close();
    	}
	}

	private void export() throws IOException, SQLException, InternalErrorException {
		ids.put("SC_TENANT#"+id, 0L);
		nextId = 1L;
		out.writeObject(new Integer(1)); // File version
		out.writeObject(tenant.getName());
		out.writeObject(tenant.getDescription());
		for ( Action action: firstStep)
		{
			export(action);
		}
		for ( Action action: secondStep)
		{
			export(action);
		}
		out.writeObject(null);
	}

	public void export(Action action) throws IOException, SQLException, InternalErrorException {
		log.info(action.table.name);
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
		sb.append("SELECT "+table.name+"."+table.getPrimaryKey());
		for (Column c: table.columns)
		{
			if ( foreignColumns.containsKey(c.name) && ! c.notNull)
			{
				columns.add( c.name );
				sb.append(",");
				sb.append(table.name+"."+c.name);
			}
		}
		sb.append(" FROM "+table.name);
		sb.append(generateQuery(table, false));


		try {
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery(sb.toString());
			out.writeObject ( "update" );
			out.writeObject ( table.name );
			out.writeObject ( table.getPrimaryKey() );
			out.writeObject( columns.toArray(new String[0]));
			int rows = 0;
			while (rset.next())
			{
				rows ++;
				writeRow ( rset, table, columns, foreignColumns, true);
			}
			log.info(table.name+" (step 2): "+rows);
			out.writeObject(null);
		} catch (SQLException e) {
			if ( ignoreFailures && e.getMessage().contains("Table not found"))
			{
				// Ignore
			}
			else
			{
				throw new InternalErrorException("Error dumping table "+table.name, e);
			}
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
		sb.append(generateQuery(table, false));


		try {
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery(sb.toString());
			out.writeObject ( "insert" );
			out.writeObject( table.name );
			out.writeObject( columns.toArray(new String[0]));
			int rows = 0;
			while (rset.next())
			{
				rows ++;
				writeRow ( rset, table, columns, foreignColumns, false);
			}
			out.writeObject(null);
			log.info(table.name+" (step 1) : "+rows);
		} catch (SQLException e) {
			if ( ignoreFailures && e.getMessage().contains("Table not found"))
			{
				// Ignore
			}
			else
			{
				log.info(sb.toString());
				throw e;
			}
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
		sb.append(generateQuery(table, false));


		int rows = 0;
		try {
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery(sb.toString());
			out.writeObject ( "insert" );
			out.writeObject( table.name );
			out.writeObject( columns.toArray(new String[0]));
			while (rset.next())
			{
				rows ++;
				writeRow ( rset, table, columns, foreignColumns, false);
			}
			out.writeObject(null);
			log.info(table.name+": "+rows);
		} catch (SQLException e) {
			if ( ignoreFailures && e.getMessage().contains("Table not found"))
			{
				// Ignore
			}
			else
			{
				log.info(sb.toString());
				throw e;
			}
		}
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
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				InputStream in = blob.getBinaryStream();
				for (int ch = in.read(); ch >= 0; ch = in.read())
					out.write(ch);
				out.close();
				value = out.toByteArray();
				blob.free();
			}
			if (value instanceof Clob)
			{
				Clob blob = (Clob) value;
				StringBuffer sb = new StringBuffer();
				Reader in = blob.getCharacterStream();
				for (int ch = in.read(); ch >= 0; ch = in.read())
					sb.append((char) ch);
				value = sb.toString();
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
					nextId++;
				}
			}
			row[i-1] = value;
			i ++;
		}
		out.writeObject(row);
	}
}


class SearchPath {
	String from;
	String where;
	
	String from2;
	String where2;
	String tail2;
	
	String lastTable;
	Set<String> path;
}

