package es.caib.seycon.db;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * Fem una classe connection que empre els LogInfoPreparedStatement
 * 
 *  - Es marquen els Statements com a @Deprecated perquè s'empre la
 *  classe LogInfoPreparedStatement
 *  
 *  Alejandro Usero Ruiz - 31 d'agost de 2011
 *  
 * @author u88683
 *
 */
public class LogInfoConnection implements Connection {
    
	Connection logInfoConnection;
    Thread threadBound;
	/**
	 * @param executor
	 * @param milliseconds
	 * @throws SQLException
	 * @see java.sql.Connection#setNetworkTimeout(java.util.concurrent.Executor, int)
	 */
	public void setNetworkTimeout(Executor executor, int milliseconds)
			throws SQLException
	{
		try
		{
			Method m = logInfoConnection.getClass().getMethod("setNetworkTimeout", new Class[] {Executor.class, Integer.class});
			m.invoke(logInfoConnection, executor, milliseconds);
		}
		catch (Exception e)
		{
			throw new SQLException(e);
		}
	}

    public Thread getThread() {
        return threadBound;
    }

    /**
	 * @param schema
	 * @throws SQLException
	 * @see java.sql.Connection#setSchema(java.lang.String)
	 */
	public void setSchema(String schema) throws SQLException
	{
		Method m;
		try
		{
			m = logInfoConnection.getClass().getMethod("setSchema", new Class[] {String.class});
			m.invoke(logInfoConnection, schema);
		}
		catch (SecurityException e)
		{
			throw new SQLException ("Not implemented");
		}
		catch (NoSuchMethodException e)
		{
			throw new SQLException ("Not implemented");
		}
		catch (IllegalArgumentException e)
		{
			throw new SQLException ("Not implemented");
		}
		catch (IllegalAccessException e)
		{
			throw new SQLException ("Not implemented");
		}
		catch (InvocationTargetException e)
		{
			throw new SQLException ("Not implemented");
		}
		
	}

	/**
	 * @return
	 * @throws SQLException
	 * @see java.sql.Connection#getSchema()
	 */
	public String getSchema() throws SQLException
	{
		Method m;
		try
		{
			m = logInfoConnection.getClass().getMethod("getSchema");
			return (String) m.invoke(logInfoConnection);
		}
		catch (SecurityException e)
		{
			throw new SQLException ("Not implemented");
		}
		catch (NoSuchMethodException e)
		{
			throw new SQLException ("Not implemented");
		}
		catch (IllegalArgumentException e)
		{
			throw new SQLException ("Not implemented");
		}
		catch (IllegalAccessException e)
		{
			throw new SQLException ("Not implemented");
		}
		catch (InvocationTargetException e)
		{
			throw new SQLException ("Not implemented");
		}
	}

	/**
	 * @param executor
	 * @throws SQLException
	 * @see java.sql.Connection#abort(java.util.concurrent.Executor)
	 */
	public void abort(Executor executor) throws SQLException
	{
		Method m;
		try
		{
			m = logInfoConnection.getClass().getMethod("abort", new Class[] {Executor.class});
			m.invoke(logInfoConnection, executor);
		}
		catch (SecurityException e)
		{
			throw new SQLException ("Not implemented");
		}
		catch (NoSuchMethodException e)
		{
			throw new SQLException ("Not implemented");
		}
		catch (IllegalArgumentException e)
		{
			throw new SQLException ("Not implemented");
		}
		catch (IllegalAccessException e)
		{
			throw new SQLException ("Not implemented");
		}
		catch (InvocationTargetException e)
		{
			throw new SQLException ("Not implemented");
		}
	}

	public void unsetThread() throws SQLException {
        checkThread();
        this.threadBound = null;
    }

    /**
	 * @return
	 * @throws SQLException
	 * @see java.sql.Connection#getNetworkTimeout()
	 */
	public int getNetworkTimeout() throws SQLException
	{
		try
		{
			return (Integer) logInfoConnection.getClass().getMethod("getNetworkTimeout").invoke(logInfoConnection);
		}
		catch (IllegalArgumentException e)
		{
			throw new SQLException ("Not implemented");
		}
		catch (SecurityException e)
		{
			throw new SQLException ("Not implemented");
		}
		catch (IllegalAccessException e)
		{
			throw new SQLException ("Not implemented");
		}
		catch (InvocationTargetException e)
		{
			throw new SQLException ("Not implemented");
		}
		catch (NoSuchMethodException e)
		{
			throw new SQLException ("Not implemented");
		}
	}

	public void setThread() throws SQLException {
        checkThread();
        this.threadBound = Thread.currentThread();
    }
    
    private void checkThread () throws SQLException {
        if (threadBound != null && ! threadBound.equals(Thread.currentThread()))
            throw new SQLException("SQL Connection shared between threads !!!"); //$NON-NLS-1$
    }
    

    public LogInfoConnection(Connection logInfoConnection) {
        super();
        this.logInfoConnection = logInfoConnection;
    }

    public <T> T unwrap(Class<T> iface) throws SQLException {// JSE6
        return logInfoConnection.unwrap(iface);
    }

    public boolean isWrapperFor(Class<?> iface) throws SQLException {// JSE6
        return logInfoConnection.isWrapperFor(iface);
    }

    @Deprecated
    public Statement createStatement() throws SQLException {
        checkThread();
        return logInfoConnection.createStatement(); 
    }

    public PreparedStatement prepareStatement(String sql) throws SQLException {
	        checkThread();
	        LogInfoPreparedStatement wstmt = new LogInfoPreparedStatement(
	                logInfoConnection.prepareStatement(sql), sql);
	        return wstmt;
    }

    public CallableStatement prepareCall(String sql) throws SQLException {
    	try {
	        checkThread();
    	    return logInfoConnection.prepareCall(sql);
    	} catch (SQLException e) {
			throw new SQLException("SQL "+sql, e.getSQLState(), e); //$NON-NLS-1$
		}
    }

    public String nativeSQL(String sql) throws SQLException {
    	try {
	        checkThread();
	        return logInfoConnection.nativeSQL(sql);
    	} catch (SQLException e) {
			throw new SQLException("SQL "+sql, e.getSQLState(), e); //$NON-NLS-1$
		}
    }

    public void setAutoCommit(boolean autoCommit) throws SQLException {
        logInfoConnection.setAutoCommit(autoCommit);
    }

    public boolean getAutoCommit() throws SQLException {
        return logInfoConnection.getAutoCommit();
    }

    public void commit() throws SQLException {
        checkThread();
        logInfoConnection.commit();
    }

    public void rollback() throws SQLException {
        checkThread();
        logInfoConnection.rollback();
    }

    public void close() throws SQLException {
        checkThread();
        logInfoConnection.close();
    }

    public boolean isClosed() throws SQLException {
        return logInfoConnection.isClosed();
    }

    public DatabaseMetaData getMetaData() throws SQLException {
        return logInfoConnection.getMetaData();
    }

    public void setReadOnly(boolean readOnly) throws SQLException {
        logInfoConnection.setReadOnly(readOnly);
    }

    public boolean isReadOnly() throws SQLException {
        return logInfoConnection.isReadOnly();
    }

    public void setCatalog(String catalog) throws SQLException {
        logInfoConnection.setCatalog(catalog);
    }

    public String getCatalog() throws SQLException {
        return logInfoConnection.getCatalog();
    }

    public void setTransactionIsolation(int level) throws SQLException {
        logInfoConnection.setTransactionIsolation(level);
    }

    public int getTransactionIsolation() throws SQLException {
        return logInfoConnection.getTransactionIsolation();
    }

    public SQLWarning getWarnings() throws SQLException {
        return logInfoConnection.getWarnings();
    }

    public void clearWarnings() throws SQLException {
        logInfoConnection.clearWarnings();
    }

    @Deprecated
    public Statement createStatement(int resultSetType, int resultSetConcurrency)
            throws SQLException {
        checkThread();
        return logInfoConnection.createStatement(resultSetType,
                resultSetConcurrency);
    }

    public PreparedStatement prepareStatement(String sql, int resultSetType,
            int resultSetConcurrency) throws SQLException {
        checkThread();
        LogInfoPreparedStatement wstmt = new LogInfoPreparedStatement(
                logInfoConnection.prepareStatement(sql, resultSetType,
                        resultSetConcurrency), sql);
        return wstmt;
    }

    public CallableStatement prepareCall(String sql, int resultSetType,
            int resultSetConcurrency) throws SQLException {
        checkThread();
        return logInfoConnection.prepareCall(sql, resultSetType,
                resultSetConcurrency);
    }

    public Map<String, Class<?>> getTypeMap() throws SQLException {
        return logInfoConnection.getTypeMap();
    }

    public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
        logInfoConnection.setTypeMap(map);
    }

    public void setHoldability(int holdability) throws SQLException {
        logInfoConnection.setHoldability(holdability);
    }

    public int getHoldability() throws SQLException {
        return logInfoConnection.getHoldability();
    }

    public Savepoint setSavepoint() throws SQLException {
        return logInfoConnection.setSavepoint();
    }

    public Savepoint setSavepoint(String name) throws SQLException {
        return logInfoConnection.setSavepoint(name);
    }

    public void rollback(Savepoint savepoint) throws SQLException {
        logInfoConnection.rollback(savepoint);
    }

    public void releaseSavepoint(Savepoint savepoint) throws SQLException {
        logInfoConnection.releaseSavepoint(savepoint);
    }

    @Deprecated
    public Statement createStatement(int resultSetType,
            int resultSetConcurrency, int resultSetHoldability)
            throws SQLException {
        return logInfoConnection.createStatement(resultSetType,
                resultSetConcurrency, resultSetHoldability);
    }

    public PreparedStatement prepareStatement(String sql, int resultSetType,
            int resultSetConcurrency, int resultSetHoldability)
            throws SQLException {
        checkThread();
        LogInfoPreparedStatement wstmt = new LogInfoPreparedStatement(
                logInfoConnection.prepareStatement(sql, resultSetType,
                        resultSetConcurrency, resultSetHoldability), sql);
        return wstmt;
    }

    public CallableStatement prepareCall(String sql, int resultSetType,
            int resultSetConcurrency, int resultSetHoldability)
            throws SQLException {
        checkThread();
        return logInfoConnection.prepareCall(sql, resultSetType,
                resultSetConcurrency, resultSetHoldability);
    }

    public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys)
            throws SQLException {
        checkThread();
        LogInfoPreparedStatement wstmt = new LogInfoPreparedStatement(
                logInfoConnection.prepareStatement(sql, autoGeneratedKeys), sql);
        return wstmt;
    }

    public PreparedStatement prepareStatement(String sql, int[] columnIndexes)
            throws SQLException {
        checkThread();
        LogInfoPreparedStatement wstmt = new LogInfoPreparedStatement(
                logInfoConnection.prepareStatement(sql, columnIndexes), sql);
        return wstmt;
    }

    public PreparedStatement prepareStatement(String sql, String[] columnNames)
            throws SQLException {

        checkThread();
        LogInfoPreparedStatement wstmt = new LogInfoPreparedStatement(
                logInfoConnection.prepareStatement(sql, columnNames), sql);
        return wstmt;
    }

    public Clob createClob() throws SQLException {
        checkThread();
        return logInfoConnection.createClob();
    }

    public Blob createBlob() throws SQLException {
        checkThread();
        return logInfoConnection.createBlob();
    }

    public NClob createNClob() throws SQLException {
        checkThread();
        return logInfoConnection.createNClob();
    }

    public SQLXML createSQLXML() throws SQLException {
        return logInfoConnection.createSQLXML();
    }

    public boolean isValid(int timeout) throws SQLException {
        return logInfoConnection.isValid(timeout);
    }

    public void setClientInfo(String name, String value)
            throws SQLClientInfoException {
        logInfoConnection.setClientInfo(name, value);
    }

    public void setClientInfo(Properties properties)
            throws SQLClientInfoException {
        logInfoConnection.setClientInfo(properties);
    }

    public String getClientInfo(String name) throws SQLException {
        return logInfoConnection.getClientInfo(name);
    }

    public Properties getClientInfo() throws SQLException {
        return logInfoConnection.getClientInfo();
    }

    public Array createArrayOf(String typeName, Object[] elements)
            throws SQLException {
        return logInfoConnection.createArrayOf(typeName, elements);
    }

    public Struct createStruct(String typeName, Object[] attributes)
            throws SQLException {
        return logInfoConnection.createStruct(typeName, attributes);
    }

}
