package com.soffid.iam.service.impl.tenant;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.soffid.iam.api.Tenant;
import com.soffid.tools.db.schema.Column;
import com.soffid.tools.db.schema.Table;

import es.caib.seycon.ng.exception.InternalErrorException;

public class TenantRemover extends TenantDataManager {
	private Tenant tenant;
	private HashMap<String,List<Long>> deleteNoFKrows = new HashMap<String, List<Long>>();
	public void remove(Tenant t) throws Exception {
		this.id = t.getId();
		this.tenant = t;

		loadDatabaseDefinition();
        	
    	try {
    		createExportPlan();
    		remove ();
    	} finally {
    		conn.close();
    	}
	}

	private void remove() throws IOException, SQLException, InternalErrorException {
		for ( ListIterator<Action> it = secondStep.listIterator(secondStep.size());
				it.hasPrevious();)
		{
			Action action = it.previous();
			export(action);
		}
		for ( ListIterator<Action> it = firstStep.listIterator(firstStep.size());
				it.hasPrevious();)
		{
			Action action = it.previous();
			export(action);
		}
	}

	public void export(Action action) throws IOException, SQLException, InternalErrorException {
		if (action.operation == Action.Operation.EXPORT_FULL)
			deleteFull ( action.table );
		if (action.operation == Action.Operation.EXPORT_NOFK)
			deleteGuided ( action.table );
		if (action.operation == Action.Operation.EXPORT_FK)
			nullifyForeignKeys ( action.table );
	}

	private void nullifyForeignKeys(Table table) throws SQLException, InternalErrorException {
		List<String> columns = new LinkedList<String>();

		StringBuffer sb = new StringBuffer();
		Map<String,String> foreignColumns = findForeignColumns ( table.name );
		sb.append("SELECT " + table.name+"."+ table.getPrimaryKey()+ " FROM "+table.name);
		sb.append(generateQuery(table, true));
		try {
			List<Long> values = new LinkedList<Long>();
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery(sb.toString());
			while (rset.next())
			{
				values.add(rset.getLong(1));
			}
			rset.close();
			stmt.close();
			deleteNoFKrows.put(table.name, values);
			
			StringBuffer sb2 = new StringBuffer();
			sb2.append("UPDATE " + table.name+ " SET ");
			boolean first = true;
			for (Column c: table.columns)
			{
				if ( foreignColumns.containsKey(c.name) && ! c.notNull && ! c.name.endsWith("_TEN_ID"))
				{
					if (first)
						first = false;
					else
						sb2.append(", ");
					sb2.append(c.name+"= NULL ");
				}
			}
			sb2.append(" WHERE "+table.getPrimaryKey()+"=?");
			PreparedStatement stmt2 = conn.prepareStatement(sb2.toString());
			for ( Long value: values)
			{
				stmt2.setLong(1, value);
				stmt2.execute();
				stmt2.clearParameters();
			}
			stmt2.close();
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

	private void deleteGuided(Table table) throws SQLException, InternalErrorException {
		List<Long> values = deleteNoFKrows.get(table.name);

		StringBuffer sb2 = new StringBuffer();
		
		try {
			sb2.append("DELETE FROM " + table.name+ " WHERE "+table.getPrimaryKey()+"=?");
			PreparedStatement stmt2 = conn.prepareStatement(sb2.toString());
			for ( Long value: values)
			{
				stmt2.setLong(1, value);
				stmt2.execute();
				stmt2.clearParameters();
			}
			stmt2.close();
		} catch (SQLException e) {
			if ( ignoreFailures && e.getMessage().contains("Table not found"))
			{
				// Ignore
			}
			else
			{
				log.info(sb2.toString());
				throw e;
			}
		}
	}

	private void deleteFull(Table table) throws InternalErrorException, SQLException {
		List<String> columns = new LinkedList<String>();

		StringBuffer sb = new StringBuffer();
		Map<String,String> foreignColumns = findForeignColumns ( table.name );
		sb.append("DELETE FROM "+table.name+" ");
		sb.append(generateQuery(table, true));
		try {
			Statement stmt = conn.createStatement();
			stmt.execute(sb.toString());
			stmt.close();
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

}
