package com.soffid.iam.deployer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.LinkedList;
import java.util.List;

public class QueryHelper {
	Connection conn;
	
	

    public void select (String sql, Object params[] , QueryAction query) throws SQLException, IOException
    {
    	PreparedStatement stmt = conn.prepareStatement(sql);
    	int num = 0;
    	for ( Object param: params) 
    	{
    		if (param == null)
    		{
    			stmt.setNull(num, Types.VARCHAR);
    		} 
    		else if (param instanceof Long)
    		{
    			stmt.setLong(num, (Long) param);
    		}
    		else if (param instanceof Integer)
    		{
    			stmt.setInt(num, (Integer) param);
    		}
    		else if (param instanceof Date)
    		{
    			stmt.setDate(num, (Date) param);
    		}
    		else 
    		{
    			stmt.setString(num, param.toString());
    		}
    	}
    	try {
    		ResultSet rset = stmt.executeQuery();
    		try {
	    		while (rset.next())
	    		{
	    			query.perform(rset);
	    		}
    		} finally {
    			rset.close ();
    		}
    	} finally {
    		stmt.close();
    	}
    	
    }


    public List<Object[]> select (String sql, Object... params) throws SQLException
    {
    	List<Object[]> result = new LinkedList<Object []>(); 
    	PreparedStatement stmt = conn.prepareStatement(sql);
    	int num = 0;
    	for ( Object param: params) 
    	{
    		if (param == null)
    		{
    			stmt.setNull(num, Types.VARCHAR);
    		} 
    		else if (param instanceof Long)
    		{
    			stmt.setLong(num, (Long) param);
    		}
    		else if (param instanceof Integer)
    		{
    			stmt.setInt(num, (Integer) param);
    		}
    		else if (param instanceof Date)
    		{
    			stmt.setDate(num, (Date) param);
    		}
    		else 
    		{
    			stmt.setString(num, param.toString());
    		}
    	}
    	try {
    		ResultSet rset = stmt.executeQuery();
    		try {
				int cols = rset.getMetaData().getColumnCount();
	    		while (rset.next())
	    		{
	    			Object [] row = new Object[cols];
	    			for (int i = 0; i < cols; i++) {
	    				row[i] = rset.getObject(i+1);
	    			}
	    			result.add(row);
	    		}
	    		return result;
    		} finally {
    			rset.close ();
    		}
    	} finally {
    		stmt.close();
    	}
    	
    }

    public void execute (String sql, Object... params) throws SQLException
    {
    	PreparedStatement stmt = conn.prepareStatement(sql);
    	int num = 0;
    	for ( Object param: params) 
    	{
    		if (param == null)
    		{
    			stmt.setNull(num, Types.VARCHAR);
    		} 
    		else if (param instanceof Long)
    		{
    			stmt.setLong(num, (Long) param);
    		}
    		else if (param instanceof Integer)
    		{
    			stmt.setInt(num, (Integer) param);
    		}
    		else if (param instanceof Date)
    		{
    			stmt.setDate(num, (Date) param);
    		}
    		else 
    		{
    			stmt.setString(num, param.toString());
    		}
    	}
    	try {
    		stmt.execute();
    	} finally {
    		stmt.close();
    	}
    	
    }

	public QueryHelper(Connection conn) {
		super();
		this.conn = conn;
	}   
	
}
