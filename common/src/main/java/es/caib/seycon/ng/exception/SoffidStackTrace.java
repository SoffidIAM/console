package es.caib.seycon.ng.exception;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJBException;

public class SoffidStackTrace
{
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
            	}
            }
            else if (ourCause != null && ourCause != e)
            {
            	printStackTraceAsCauseSeycon(ourCause, s, trace);
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
                	printStackTrace (trace[i], s);
                }
            }
            s.flush();
        }
	}

	private static void printStackTrace(StackTraceElement stackTraceElement, PrintWriter s) {
    	s.println("\t"); //$NON-NLS-1$
        s.printf(Messages.getString("InternalErrorException.at"), stackTraceElement.toString()); //$NON-NLS-1$    	
	}

	private static void printStackTraceAsCauseSeycon(Throwable th, PrintWriter s,
            StackTraceElement[] causedTrace) {
        if (th == null)
            return;

        StackTraceElement[] trace = th.getStackTrace();

        // Recurse if we have a cause
        Throwable ourCause = getCause(th);
        if (ourCause != null && ourCause != th) {
            printStackTraceAsCauseSeycon(ourCause, s, trace);
        	s.println (); //$NON-NLS-1$
        	s.print (Messages.getString("Throws")); //$NON-NLS-1$
        }

        s.print(th.getClass().getName());
        s.print(": "); //$NON-NLS-1$
        if (th.getMessage() != null)
        	s.println (th.getMessage());
        if (th instanceof InternalErrorException)
        {

        	String c = ((InternalErrorException)th).getCauseFiltratString(); 
        	if (c != null) {
                s.print(Messages.getString("InternalErrorException.CausedBy")); //$NON-NLS-1$
        		s.print(c);
        		return;
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
            	printStackTrace (trace[i], s);
            }
        }
        if (framesInCommon != 0) {
            s.print("\t"); //$NON-NLS-1$
            s.printf(Messages.getString("InternalErrorException.more"), framesInCommon); //$NON-NLS-1$
            s.println();
        }
        s.flush();
    }

	public static String generateShortDescription(Exception e) {
		StringBuffer sb = new StringBuffer();
	   	Throwable root = e;
	   	String last = null;
	   	String lastMessage = null;
	   	LinkedList<String> significantExceptions = new LinkedList<>();
	   	do
	   	{
	   		String next ;
	   		if ( root instanceof InternalErrorException)
	   			next = root.getMessage();
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
	   		if (root.getCause() == null || root.getCause() == root)
	   			break;
	   		else
	   			root = root.getCause ();
	   	} while (true);
	   	for ( String s: significantExceptions) {
   			if ( sb.length() > 0)
   				sb.append("\ncaused by ");
   			sb.append(s);
	   	}
	   	return sb.toString();
	}


}
