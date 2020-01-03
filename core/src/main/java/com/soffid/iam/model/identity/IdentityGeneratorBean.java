/**
 * 
 */
package com.soffid.iam.model.identity;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.MappingException;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.exception.JDBCExceptionHelper;
import org.hibernate.type.Type;
import org.hibernate.util.PropertiesHelper;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author bubu
 * 
 */
public class IdentityGeneratorBean implements InitializingBean
{
	public static IdentityGeneratorBean theBean = null;
	private DataSource dataSource;
	private IdentityGeneratorThread thread = null;
	/**
	 * The sequence parameter
	 */
	private String tableName;
	private String sql;
	private String str1;
	private String str2;
	/**
	 * 
	 */
	private boolean createTable;

	public boolean isCreateTable ()
	{
		return createTable;
	}

	public void setCreateTable (boolean createTable)
	{
		this.createTable = createTable;
	}

	public void setDataSource (DataSource ds)
	{
		dataSource = ds;
	}

	public void setTableName (String tn)
	{
		tableName = tn;
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	public void afterPropertiesSet () throws Exception
	{
		if (theBean == null) theBean = this;
		if (getTableName() == null)
			setTableName("SC_SEQUENCE"); //$NON-NLS-1$

		setStr1("SELECT SEQ_NEXT, SEQ_CACHE, SEQ_INCREMENT FROM " + getTableName()); //$NON-NLS-1$
		setStr2("UPDATE " + getTableName() + " SET SEQ_NEXT=? WHERE SEQ_NEXT=?"); //$NON-NLS-1$ //$NON-NLS-2$

		initializeThread();
	}

	protected void initializeThread ()
	{
		if (thread == null)
		{
			thread = new IdentityGeneratorThread();
			thread.str1 = getStr1();
			thread.str2 = getStr2();
			thread.dataSource = getDataSource();
			thread.tableName = getTableName();
			thread.createTable = createTable;
			thread.setName("SoffidIdentityGenerator"); //$NON-NLS-1$
			thread.setDaemon(true);
			thread.start();
		}
	}

	public static IdentityGeneratorBean instance() 
	{
		return theBean;
	}
	
	public Long getNext (SessionImplementor session) throws SQLException, InterruptedException
	{
		if (thread == null)
			return null;
		else
			return thread.getNext(session);
	}

	
	public long[] reserve (long number) throws SQLException, InterruptedException
	{
		if (thread != null)
			return thread.reserve(number);
		else
			return null;
	}
	/**
	 * @param i	
	 * @param j
	 * @param k
	 * @throws SQLException 
	 */
	public void initialize (long next, long cache, long increment) throws SQLException
	{

		// Query current sequence status
		Connection connection = getDataSource().getConnection();
		try
		{
			// Initialize sequence
			PreparedStatement st2 = connection.prepareStatement("INSERT INTO "+getTableName()+" (SEQ_NEXT, SEQ_CACHE, SEQ_INCREMENT) VALUES (?, ?, ?)"); //$NON-NLS-1$ //$NON-NLS-2$
			try
			{
				st2.setLong(1,  next);
				st2.setLong(2, cache);
				st2.setLong(3, increment);
				st2.execute();
			}
			finally
			{
				st2.close();
			}
		}
		finally
		{
			connection.close();
		}
	}

	/**
	 * @return
	 * @throws SQLException
	 */
	public boolean isSequenceStarted () throws SQLException
	{
		// Query current sequence status
		Connection connection = getDataSource().getConnection();
		try
		{
			PreparedStatement st = connection.prepareStatement(getStr1());
			try
			{
				ResultSet rs = st.executeQuery();
				try
				{
					if (!rs.next())
						return false;
					else
						return true;
				}
				finally
				{
					rs.close();
				}
			}
			finally
			{
				st.close();
			}
		}
		finally
		{
			connection.close();
		}
	}

	/**
	 * @return the str1
	 */
	public String getStr1 ()
	{
		return str1;
	}

	/**
	 * @param str1 the str1 to set
	 */
	public void setStr1 (String str1)
	{
		this.str1 = str1;
	}

	/**
	 * @return the str2
	 */
	public String getStr2 ()
	{
		return str2;
	}

	/**
	 * @param str2 the str2 to set
	 */
	public void setStr2 (String str2)
	{
		this.str2 = str2;
	}

	/**
	 * @return the dataSource
	 */
	public DataSource getDataSource ()
	{
		return dataSource;
	}

	/**
	 * @return the tableName
	 */
	public String getTableName ()
	{
		return tableName;
	}

}