package es.caib.seycon.ng.exception;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.ejb.EJBException;

import com.soffid.tools.db.persistence.XmlReader;
import com.soffid.tools.db.schema.Database;
import com.soffid.tools.db.schema.ForeignKey;
import com.soffid.tools.db.schema.Table;

public class SoffidStackTrace
{
	
	private static Map<String, String> tableToEntity;
	private static Map<String, String> columnToAttribute;

	private static Database db;

	private static String dialect;

	public static String getStackTrace (Throwable t)
    {
    	StringWriter out = new StringWriter();
		printStackTrace(t, new PrintWriter(out));
		return out.toString();
    }
    
    public static void printStackTrace(Throwable e, PrintStream s) {
        synchronized (s) {
        	printStackTrace(e, new PrintWriter(s));
        }
    }

    private static boolean displayStackTrace (StackTraceElement stackElement)
    {
    	String cn = stackElement.getClassName();
    	if (cn.startsWith("org.hibernate.") || //$NON-NLS-1$
    			cn.startsWith("java.base/") || //$NON-NLS-1$
    			cn.startsWith("org.jboss.") || //$NON-NLS-1$
    			cn.startsWith("org.springframework.") || //$NON-NLS-1$
    			cn.startsWith("org.apache.catalina.") || //$NON-NLS-1$
    			cn.startsWith("org.apache.coyote.") || //$NON-NLS-1$
    			cn.startsWith("org.apache.tomee.") || //$NON-NLS-1$
    			cn.startsWith("org.apache.tomcat.") || //$NON-NLS-1$
    			cn.startsWith("org.apache.openejb.") || //$NON-NLS-1$
    			cn.startsWith("org.zkoss.") ||  //$NON-NLS-1$
    			cn.startsWith("com.sun.net.") ||  //$NON-NLS-1$
    			cn.startsWith("jdk.") ||  //$NON-NLS-1$
    			cn.startsWith("java.net.") ||  //$NON-NLS-1$
    			cn.startsWith("org.mortbay.") ||  //$NON-NLS-1$
    			cn.startsWith("sun.") ||  //$NON-NLS-1$
    			cn.startsWith("javax.servlet.") ||  //$NON-NLS-1$
    			cn.startsWith("java.lang.") ||  //$NON-NLS-1$
    			cn.startsWith("java.security.") ||  //$NON-NLS-1$
    			cn.startsWith("$") ||  //$NON-NLS-1$
    			cn.startsWith("bsh.") ) //$NON-NLS-1$
    		return false;
    	if (cn.endsWith("DaoBase") || cn.endsWith("ServiceBase") ||
    			cn.contains("DaoBase$") || cn.contains("ServiceBase$")) //$NON-NLS-1$ //$NON-NLS-2$
    		return false;
   		return true;
    }

    private static Throwable getCause( Throwable th)
    {
    	Throwable cause = null;
    	if (th instanceof EJBException)
    	{
    		cause = ((EJBException) th).getCausedByException();
    	}
    	if (cause == null)
    		cause = th.getCause();
    	if (cause == th)
    		return null;
    	else
    		return cause;
    }
    
	public static void printStackTrace(Throwable e, PrintWriter s) {
		printStackTrace(e, s, false);
	}
	
	public static boolean printStackTrace(Throwable e, PrintWriter s, boolean hideInternal) {
        synchronized (s) {
            StackTraceElement[] trace = e.getStackTrace();
            Throwable ourCause = getCause(e);
            boolean hasCause = false;
            if (e instanceof InternalErrorException)
            {
            	String c = ((InternalErrorException)e).getCauseFiltratString(); 
            	if (c != null) {
            		hasCause = true;
            		s.print(c);
            		hideInternal = true;
            	}
            }
            else if (ourCause != null && ourCause != e)
            {
            	hideInternal = printStackTraceAsCauseSeycon(ourCause, s, trace, hideInternal);
            	hasCause = true;
            }
            if (hasCause)
            {
            	s.println (); //$NON-NLS-1$
            	s.print (Messages.getString("Throws")); //$NON-NLS-1$
            }
            if (!hasCause || e.getClass() != InternalErrorException.class) {
            	s.print(e.getClass().getName());
            	s.print(": "); //$NON-NLS-1$
            }
            s.println (e.getMessage());
            for (int i = 0; i < trace.length; i++) {
                if (i == 0 || displayStackTrace(trace[i]))
                {
                	hideInternal = printStackTrace (trace[i], s, hideInternal);
                }
            }
            s.flush();
            return hideInternal;
        }
	}

	private static boolean printStackTrace(StackTraceElement stackTraceElement, PrintWriter s,
			boolean hideInternal) {
		boolean internal = true;
		final String cn = stackTraceElement.getClassName();
		if (cn.startsWith("com.soffid.iam.spring.") ||
				cn.startsWith("es.caib.zkib."))
			internal = true;
		else if (cn.startsWith("com.soffid") ||
				cn.startsWith("es.caib")) {
			internal = false;
		}
	
		if (!internal || !hideInternal) {
	    	s.println("\t"); //$NON-NLS-1$
	        s.printf(Messages.getString("InternalErrorException.at"), stackTraceElement.toString()); //$NON-NLS-1$    	
		}
		return !internal || hideInternal;
	}

	private static boolean printStackTraceAsCauseSeycon(Throwable th, PrintWriter s,
            StackTraceElement[] causedTrace, boolean hideInternal) {
        if (th == null)
            return hideInternal;

        StackTraceElement[] trace = th.getStackTrace();

        // Recurse if we have a cause
        Throwable ourCause = getCause(th);
        if (ourCause != null && ourCause != th) {
            hideInternal = printStackTraceAsCauseSeycon(ourCause, s, trace, hideInternal);
        	s.println (); //$NON-NLS-1$
        	s.print (Messages.getString("Throws")); //$NON-NLS-1$
        }

        s.print(th.getClass().getName());
        s.print(": "); //$NON-NLS-1$
        if (th.getMessage() != null)
        	s.println (th.getMessage());
        if (th instanceof InternalErrorException)
        {
        	final InternalErrorException internalErrorException = (InternalErrorException)th;
        	if (internalErrorException.getCause() == null || 
        			internalErrorException.getCause() == internalErrorException) {
				String c = internalErrorException.getCauseFiltratString(); 
	        	if (c != null) {
	                s.print(Messages.getString("InternalErrorException.CausedBy")); //$NON-NLS-1$
	        		s.print(c);
	        		return hideInternal;
	        	}
        	}
        }
        // Compute number of frames in common between this and caused
        int m = trace.length - 1, n = causedTrace.length - 1;
        while (m >= 0 && n >= 0 && trace[m].equals(causedTrace[n])) {
            m--;
            n--;
        }
        int framesInCommon = trace.length - 1 - m;

        for (int i = 0; i <= m; i++) {
            if (i == 0 || displayStackTrace(trace[i]))
            {
            	hideInternal = printStackTrace (trace[i], s, hideInternal);
            }
        }
        if (framesInCommon != 0) {
            s.print("\t"); //$NON-NLS-1$
            s.printf(Messages.getString("InternalErrorException.more"), framesInCommon); //$NON-NLS-1$
            s.println();
        }
        s.flush();
        return hideInternal;
    }

	public static String generateShortDescription(Exception e) {
		StringBuffer sb = new StringBuffer();
	   	LinkedList<String> significantExceptions = getSignificantExceptions(e, false);
	   	for ( String s: significantExceptions) {
   			if ( sb.length() > 0)
   				sb.append("\ncaused by ");
   			sb.append(s);
	   	}
	   	return sb.toString();
	}

	public static String generateEndUserDescription(Exception e) {
		StringBuffer sb = new StringBuffer();
	   	LinkedList<String> significantExceptions = getSignificantExceptions(e, true);
	   	for ( String s: significantExceptions) {
   			if ( sb.length() > 0)
   				sb.append("\ncaused by ");
   			sb.append(s);
	   	}
	   	return sb.toString();
	}

	private static LinkedList<String> getSignificantExceptions(Exception e, boolean endUser) {
		Throwable root = e;
	   	String last = null;
	   	String lastMessage = null;
	   	boolean end = false;
	   	LinkedList<String> significantExceptions = new LinkedList<>();
	   	do
	   	{
	   		if (endUser) {
	   			if (root instanceof RuntimeException ||
	   					root instanceof InternalErrorException ) {
	   				significantExceptions.clear();
	   				lastMessage = null;
	   			}
	   		}
	   		String next ;
	   		if ( root instanceof InternalErrorException || 
	   			 root instanceof SeyconException)
	   			next = root.getMessage();
	   		else if ( isDataAccessException( root.getClass())) {
	   			if (endUser) {
	   				significantExceptions.clear();
	   				lastMessage = null;
	   			}
	   			next = parseDataViolation(root);
	   			if (next != null)
	   				end = true;
	   			else
	   				next = root.toString();
	   		}
	   		else 
	   			next = root.toString();
	   		if (next != null)
	   		{
	   				
	   			if ( root instanceof EJBException) {
	   				// Ignore
	   			}
	   			else if (last == null || ! last.contains(next))
		   		{
		   			significantExceptions.add(next);
		   			last = next;
		   			lastMessage = root.getMessage();
		   		}
		   		else if (lastMessage != null && lastMessage.equals(root.toString()))
		   		{
		   			significantExceptions.removeLast();
		   			significantExceptions.add(next);
		   			last = next;
		   			lastMessage = root.getMessage();
		   		}
	   		}
	   		if (end || root.getCause() == null || root.getCause() == root)
	   			break;
	   		else
	   			root = root.getCause ();
	   	} while (true);
		return significantExceptions;
	}


	private static boolean isDataAccessException(Class<?> class1) {
		if (class1.getName().endsWith(".DataAccessException"))
			return true;
		else if (class1.getSuperclass() == null)
			return false;
		else
			return isDataAccessException(class1.getSuperclass());
	}

	private static String parseDataViolation(Throwable root) {
		String table = null;
		String message = root.getMessage();
		int i = message.toLowerCase().indexOf("insert into ");
		if (i >= 0) {
			i += 12;
			int j = message.toLowerCase().indexOf(" ",i);
			if (j >= 0)
				table = message.substring(i,j);
		} else {
			i = message.toLowerCase().indexOf("delete from ");
			if (i >= 0) {
				i += 12;
				int j = message.toLowerCase().indexOf(" ",i);
				if (j >= 0)
					table = message.substring(i,j);
			}
			else {
				i = message.toLowerCase().indexOf("update ");
				if (i >= 0) {
					i += 7;
					int j = message.toLowerCase().indexOf(" ",i);
					if (j >= 0)
						table = message.substring(i,j);
				}
			}
		}
		
		String name = tableToEntity.get(table);
		String className = name;
		if (name == null) 
			className = name = table;
		else {
			name = simplifyEntity(name);
		}
		
		String msg = null;
		try {
			if (root.getCause() instanceof SQLException) {
				if ("mysql".equals(dialect)) 
					msg = parseMysqlException (table, name, className, (SQLException) root.getCause());
				if ("oracle".equals(dialect)) 
					msg = parseOracleException (table, name, className, (SQLException) root.getCause());
				if ("sqlserver".equals(dialect)) 
					msg = parseSqlserverException (table, name, className, (SQLException) root.getCause());
			}
		} catch (Exception e) {}
		if (msg != null)
			return msg;
		else
			return null;
	}

	private static String parseMysqlException(String table, String objectName, String className, SQLException cause) throws IOException, Exception {
		if (cause.getErrorCode() == 1406) { // Value too large
			String msg = cause.getMessage();
			String column = msg.substring(msg.indexOf("'")+1, msg.lastIndexOf("'"));
			String attributeName  = columnToAttribute.get(table+"."+column);
			if (attributeName != null && objectName != null)
				return String.format(Messages.getString("exception.valueTooLarge"), objectName, attributeName);
		}
		if (cause.getErrorCode() == 1048) { // Null value
			String msg = cause.getMessage();
			String column = msg.substring(msg.indexOf("'")+1, msg.lastIndexOf("'"));
			String attributeName  = columnToAttribute.get(table+"."+column);
			if (attributeName != null && objectName != null)
				return String.format(Messages.getString("exception.notNull"), objectName, attributeName);
		}
		if (cause.getErrorCode() == 1451) { // Cannot delete a parent row
			String msg = cause.getMessage();
			int i = msg.indexOf("FOREIGN KEY (`");
			String column = msg.substring(i+14, msg.indexOf("`", i+14));
			
			i = msg.indexOf("`.`");
			String referencedTable = msg.substring(i+3, msg.indexOf("`", i+3));
			
			String attributeName = columnToAttribute.get(referencedTable+"."+column);
			String referencedEntity = tableToEntity.get(referencedTable);
			referencedEntity = simplifyEntity(referencedEntity);
			
			if (attributeName != null && objectName != null && referencedEntity != null)
				return String.format(Messages.getString("exception.reference"),
						objectName, referencedEntity, attributeName);
		}
		if (cause.getErrorCode() == 1062) { // Unique key
			return String.format(Messages.getString("exception.duplicate"),
						objectName);
		}
		return null;
	}

	private static String parseOracleException(String table, String objectName, String className, SQLException cause) throws IOException, Exception {
		if (cause.getErrorCode() == 12899) { // Value too large
			String msg = cause.getMessage();
			int i = msg.lastIndexOf("\"");
			int j = msg.lastIndexOf("\"", i-1);
			String column = msg.substring(j+1, i);
			String attributeName  = columnToAttribute.get(table+"."+column);
			if (attributeName != null && objectName != null)
				return String.format(Messages.getString("exception.valueTooLarge"), objectName, attributeName);
		}
		if (cause.getErrorCode() == 1400) { // Null value
			String msg = cause.getMessage();
			int i = msg.lastIndexOf("\"");
			int j = msg.lastIndexOf("\"", i-1);
			String column = msg.substring(j+1, i);
			String attributeName  = columnToAttribute.get(table+"."+column);
			if (attributeName != null && objectName != null)
				return String.format(Messages.getString("exception.notNull"), objectName, attributeName);
		}
		if (cause.getErrorCode() == 2292) { // Cannot delete a parent row
			String msg = cause.getMessage();
			int i = msg.lastIndexOf(")");
			int j = msg.lastIndexOf(".",i-1);
			String fkName = msg.substring(j+1, i);
			
			for (ForeignKey fk: getDatabase().foreignKeys) {
				if (fk.name.equals(fkName)) {
					String referencedTable = fk.tableName;
					String column = fk.columns.firstElement();
					String attributeName = columnToAttribute.get(referencedTable+"."+column);
					String referencedEntity = tableToEntity.get(referencedTable);
					referencedEntity = simplifyEntity(referencedEntity);
					
					if (attributeName != null && objectName != null && referencedEntity != null)
						return String.format(Messages.getString("exception.reference"),
								objectName, referencedEntity, attributeName);
					
				}
			}
		}
		if (cause.getErrorCode() == 1062) { // Unique key
			return String.format(Messages.getString("exception.duplicate"),
						objectName);
		}
		return null;
	}

	private static String parseSqlserverException(String table, String objectName, String className, SQLException cause) throws IOException, Exception {
		if (cause.getErrorCode() == 2628) { // Value too large
			String msg = cause.getMessage();
			int i = msg.indexOf("column \'");
			int j = msg.indexOf("\'", i+8);
			String column = msg.substring(i+8, j);
			String attributeName  = columnToAttribute.get(table+"."+column);
			if (attributeName != null && objectName != null)
				return String.format(Messages.getString("exception.valueTooLarge"), objectName, attributeName);
		}
		if (cause.getErrorCode() == 515) { // Null value
			String msg = cause.getMessage();
			int i = msg.indexOf("\'");
			int j = msg.indexOf("\'", i+1);
			String column = msg.substring(i+1, j);
			String attributeName  = columnToAttribute.get(table+"."+column);
			if (attributeName != null && objectName != null)
				return String.format(Messages.getString("exception.notNull"), objectName, attributeName);
		}
		if (cause.getErrorCode() == 547) { // Cannot delete a parent row
			String msg = cause.getMessage();
			int i = msg.indexOf("\"");
			int j = msg.indexOf("\"",i+1);
			String fkName = msg.substring(i+1, j);
			
			for (ForeignKey fk: getDatabase().foreignKeys) {
				if (fk.name.equals(fkName)) {
					String referencedTable = fk.tableName;
					String column = fk.columns.firstElement();
					String attributeName = columnToAttribute.get(referencedTable+"."+column);
					String referencedEntity = tableToEntity.get(referencedTable);
					referencedEntity = simplifyEntity(referencedEntity);
					
					if (attributeName != null && objectName != null && referencedEntity != null)
						return String.format(Messages.getString("exception.reference"),
								objectName, referencedEntity, attributeName);
					
				}
			}
		}
		if (cause.getErrorCode() == 1062) { // Unique key
			return String.format(Messages.getString("exception.duplicate"),
						objectName);
		}
		return null;
	}

	private static String simplifyEntity(String foreignEntity) {
		if (foreignEntity.endsWith("Impl")) foreignEntity = foreignEntity.substring(0,foreignEntity.length()-4);
		if (foreignEntity.endsWith("Entity")) foreignEntity = foreignEntity.substring(0,foreignEntity.length()-6);
		if (foreignEntity.contains(".")) foreignEntity = foreignEntity.substring(foreignEntity.lastIndexOf(".")+1);
		return foreignEntity;
	}

	public static void setTableToEntityMap(Map<String, String> tableToEntity) {
		SoffidStackTrace.tableToEntity = tableToEntity;
	}
	
	public static Database getDatabase() throws IOException, Exception {
		if (db == null) {
	    	db = new Database();
	    	XmlReader reader = new XmlReader();
	    	parseResources(db, reader, "console-ddl.xml");
	    	parseResources(db, reader, "core-ddl.xml");
	    	parseResources(db, reader, "plugin-ddl.xml");
		}
	   	return db;
	}
	
	private static void parseResources(Database db,
			XmlReader reader, String path) throws IOException, Exception {
		Enumeration<URL> resources = Thread.currentThread().getContextClassLoader().getResources(path);
    	while (resources.hasMoreElements())
    	{
    		reader.parse(db, resources.nextElement().openStream());
    	}
	}

	public static void setDialect(String string) {
		SoffidStackTrace.dialect = (string);
	}

	public static void setColumnToAttributeMap(Map<String, String> columnToAttribute) {
		SoffidStackTrace.columnToAttribute = columnToAttribute;
	}
}
