package com.soffid.iam.service.impl.tenant;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import com.soffid.iam.api.Tenant;
import com.soffid.iam.model.identity.IdentityGenerator;
import com.soffid.iam.model.identity.IdentityGeneratorBean;
import com.soffid.tools.db.schema.Column;
import com.soffid.tools.db.schema.Table;

import es.caib.seycon.ng.exception.InternalErrorException;

public class TenantImporter extends TenantDataManager {
	private long firstId;
	private long stepId;
	private ObjectInputStream in;
	private File tmpFile;
	
	long rows = 0;
	long inserts = 0;
	long done = 0;
	
	public Tenant importTenant (InputStream in) throws SQLException, IOException, InternalErrorException, Exception {
		loadDatabaseDefinition();
		
		int read;
		ZipInputStream zin = new ZipInputStream(in);
		ZipEntry zentry = zin.getNextEntry();
		if (!zentry.getName().endsWith(".soffid-dump"))
			throw new IOException ("Not a soffid dump file");
		
		tmpFile = File.createTempFile("import", "data");
		OutputStream out = new FileOutputStream(tmpFile);
		for ( read = zin.read(); read >= 0; read = zin.read())
			out.write(read);
		out.close();
		zin.close();
		
		long rows = countRows();
		IdentityGeneratorBean bean = IdentityGeneratorBean.instance();
		long [] ids = bean.reserve(rows);
		firstId = ids[0];
		stepId = ids[1];
		Tenant t = loadData();
		tmpFile.delete();
		return t;
	}

	private long countRows() throws FileNotFoundException, IOException, ClassNotFoundException {
		long rows = 0;
		in  = new SecureObjectInputStream(new FileInputStream(tmpFile));
		
		Integer version = (Integer) in.readObject();
		if (version == null  || version.intValue() != 1)
			throw new IOException("Unsupported file with version "+version);
		String name = (String) in.readObject();
		String description = (String) in.readObject();
		do {
			String action = (String) in.readObject();
		if (action == null)	break;
			String tableName = (String) in.readObject();
			if (action.equals("update"))
				in.readObject(); // Primary key
			String columns[] = (String[]) in.readObject();
			do {
				Object row = (Object[]) in.readObject();
			if (row == null) break;
				rows ++;
			} while (true);
		} while (true);
		in.close();
		return rows;
	}

	private Tenant loadData() throws FileNotFoundException, IOException, ClassNotFoundException, SQLException {
		long rows = 0;
		in  = new ObjectInputStream(new FileInputStream(tmpFile));
		
		Integer version = (Integer) in.readObject();
		if (version == null  || version.intValue() != 1)
			throw new IOException("Unsupported file with version "+version);
		String name = (String) in.readObject();
		String description = (String) in.readObject();

		Tenant t = registerTenant(name, description);
		
		do {
			String action = (String) in.readObject();
			if (action == null)	break;
			if (action.equals("insert"))
				insertTable(  );
			else if (action.equals("update"))
				updateTable ( );
			else
				throw new IOException("Unexpected action "+action+" in input stream");
		} while (true);
		in.close();
		return t;
	}

	public Tenant registerTenant(String name, String description) throws SQLException {
		Tenant t = new Tenant();
		String append = "";
		int attempts = 1;
		boolean success = false;
		do
		{
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM SC_TENANT WHERE TEN_NAME=?");
			stmt.setObject(1, name + append);
			ResultSet q = stmt.executeQuery();
			if (q.next())
			{
				attempts ++;
				append = "("+attempts+")";
			}
			else
				success = true;
			q.close();
			stmt.close();
		} while (!success);
		t.setId(firstId);
		t.setName(name + append);
		t.setDescription(description);
		t.setEnabled(false);
		PreparedStatement stmt = conn.prepareStatement("INSERT INTO SC_TENANT(TEN_ID, TEN_NAME, TEN_DESCRI, TEN_ENABLE) VALUES (?,?,?,?)");
		stmt.setLong(1, firstId);
		stmt.setString(2, name + append);
		stmt.setString(3, description);
		if (conn.getMetaData().getDatabaseProductName().toLowerCase().contains("postgres"))
			stmt.setBoolean(4, false);
		else
			stmt.setInt(4, 0);
		stmt.execute();
		return t;
	}

	private void insertTable() throws ClassNotFoundException, IOException, SQLException {
		String tableName = (String) in.readObject();
		String columns[] = (String[]) in.readObject();
		
		boolean ignore = false;
		Table t = db.findTable(tableName, true);
		if (t == null)
		{
			log.info("Ignoring table "+tableName);
			Object[]  row;
			do {
				row = (Object[]) in.readObject();
			} while (row != null);
		}
		else
		{
			
			Map<String, String> foreignKeys = findForeignColumns(t.name);
			do {
				Object[] row = (Object[]) in.readObject();
				if (row == null)
					break;
				StringBuffer sb = new StringBuffer();
				StringBuffer sb2 = new StringBuffer();
				List<Object> values = new LinkedList<Object>();
				for ( int i = 0; i < row.length; i++ )
				{
					Column col = t.findColumn(columns[i], true);
					Object value = row[i];
					if ( value != null )
					{
						if (col.primaryKey || foreignKeys.containsKey(col.name))
						{
							long l = ((Long) value).longValue();
							value = new Long ( l * stepId + firstId);
						}
						if (sb.length() == 0)
						{
							sb.append("INSERT INTO "+tableName+"("+columns[i]);
							sb2.append("?");
						}
						else
						{
							sb.append(","+columns[i]);
							sb2.append(",?");
						}
						values.add(value);
					}
				}
				if (sb.length() > 0)
				{
					String sql = sb.toString()+") values ("+sb2.toString()+")";
					PreparedStatement stmt = conn.prepareStatement(sql);
					int n = 1;
					for ( Object v: values)
						stmt.setObject(n++, v);
					stmt.execute();
					stmt.close();
				}
			} while (true);
		}
	}

	private void updateTable() throws ClassNotFoundException, IOException, SQLException {
		String tableName = (String) in.readObject();
		String primaryKey = (String) in.readObject();
		String columns[] = (String[]) in.readObject();
		
		Table t = db.findTable(tableName, true);
		if (t == null)
		{
			log.info("Ignoring table "+tableName);
			Object[] row;
			do {
				row = (Object[]) in.readObject();
			} while (row != null);
		}
		else
		{
			Map<String, String> foreignKeys = findForeignColumns(t.name);
			do {
				Object[] row = (Object[]) in.readObject();
				if (row == null)
					break;
				StringBuffer sb = new StringBuffer();
				List<Object> values = new LinkedList<Object>();
				for ( int i = 1; i < row.length; i++ )
				{
					Object value = row[i];
					if ( value != null )
					{
						String columnName = columns[i-1];
						if (foreignKeys.containsKey(columnName))
						{
							long l = ((Long) value).longValue();
							value = new Long ( l * stepId + firstId);
						}
						if (sb.length() == 0)
						{
							sb.append("UPDATE "+tableName+"SET ");
						}
						else
						{
							sb.append(",");
						}
						sb.append( columnName) .append("=?");
						values.add(value);
					}
				}
				if (sb.length() > 0)
				{
					String sql = " WHERE "+primaryKey+"=?";
					PreparedStatement stmt = conn.prepareStatement(sql);
					int n = 1;
					for ( Object v: values)
						stmt.setObject(n++, v);
					Long pk = (Long) row[0];
					stmt.setLong(n++, pk.longValue() * stepId + firstId );
					stmt.execute();
					stmt.close();
				}
			} while (true);
			
		}
	}

}
