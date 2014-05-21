/**
 * 
 */
package es.caib.seycon.ng.model.identity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.logging.LogFactory;
import org.hibernate.engine.SessionImplementor;

public class IdentityGeneratorThread extends Thread
{
	public String tableName;
	public DataSource dataSource;
	public String str2;
	public String str1;
	IdentityGeneratorBean theBean;
	long next = 0;
	long cache = 0;
	long increment;
	private SQLException lastException;
	Object sem1 = new Object();
	Object sem2 = new Object ();
	public boolean createTable;
	Boolean isOffline = null;
	
	public synchronized long getNext(SessionImplementor session) throws SQLException, InterruptedException
	{
		while (cache <= 0)
		{
			synchronized (sem1)
			{
				cache = -1;
				sem1.notify();
			}
			synchronized (sem2)
			{
				if (cache > 0)
					break;
				sem2.wait ();
			}
			if (lastException != null)
				throw lastException;
		}
		cache --;
		long result = next;
		next += increment;
		return result;
	}

	@Override
	public void run ()
	{
		try {
			while (true)
			{
				synchronized (sem1)
				{
					if (cache >= 0)
						sem1.wait ();
    				if (cache <= 0)
    				{
    					try {
    						lastException = null;
    						fillCache();
    					} catch (SQLException e) {
    						lastException = e;
    					}
    				}
				}
				synchronized (sem2)
				{
					sem2.notifyAll();
				}
			}
		} catch (Throwable e) {
			LogFactory.getLog(IdentityGeneratorBean.class).warn("IdentityGeneratorThread exited"); //$NON-NLS-1$
		}
	}
	
	private void fillCache() throws SQLException
	{
		do
		{
			// Query current sequence status
			Connection connection = dataSource.getConnection();
			try {
				if (createTable)
					performCreateTable(connection);
				long next1;
				long increment1;
				long cache1;
				PreparedStatement st = connection.prepareStatement(str1);
				try {
					ResultSet rs = st.executeQuery();
					try {
						if (!rs.next())
						{
							throw new SQLException(String.format(Messages.getString("IdentityGeneratorThread.SequenceTableNotInitialized"), tableName)); //$NON-NLS-1$
						}
						next1 = rs.getLong(1);
						increment1 = rs.getLong(3);
						cache1 = rs.getLong(2);
   					}
					finally {
						rs.close();
					}
   				}
				finally {
					st.close();
				}
				// Update sequence
				PreparedStatement st2 = connection.prepareStatement(str2);
				long next2 = next1 + cache1 * increment1;
				st2.setLong(1, next2);
				st2.setLong(2, next1);
				try {
					if (st2.executeUpdate() == 1)
					{
						next = next1;
						increment = increment1;
						cache = cache1;
						break;
					}
   				}
				finally {
					st2.close();
				}
			} finally {
				connection.close();
			}
		} while (true);
	}

	/**
	 * @param connection 
	 * @throws SQLException 
	 * 
	 */
	private void performCreateTable (Connection connection) throws SQLException
	{
		connection.createStatement().execute("CREATE TABLE "+tableName+" (SEQ_NEXT BIGINT, SEQ_CACHE BIGINT, SEQ_INCREMENT BIGINT)"); //$NON-NLS-1$ //$NON-NLS-2$
		connection.createStatement().execute("INSERT INTO "+tableName+" (SEQ_NEXT, SEQ_CACHE, SEQ_INCREMENT) VALUES (1, 10, 1)"); //$NON-NLS-1$ //$NON-NLS-2$
		createTable = false;
	}
	
	
}