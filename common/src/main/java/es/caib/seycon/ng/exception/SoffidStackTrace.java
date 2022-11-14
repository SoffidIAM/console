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

	public static String generateShortDescription(Throwable e) {
		Throwable cause = null;
		String lastMessage = e.getMessage();
		int lastPos = 0;
		StringBuffer msgBuffer = new StringBuffer();
		msgBuffer.append(e.getMessage());
		do {
			if ( e instanceof javax.ejb.EJBException ) 
				cause = ((EJBException)e).getCausedByException ();
			else if (e instanceof SecurityException || e instanceof javax.ejb.AccessLocalException) {
				cause = e.getCause ();
			}
			else
				cause = e.getCause ();
			if (cause == null || cause == e)
				break;
			if (lastMessage.equals(cause.toString()))
				msgBuffer.delete(lastPos, msgBuffer.length());
			String m = cause.getMessage();
			if ( m == null ) m = cause.getClass().getSimpleName();
			if (! (cause instanceof EJBException)) {
				// Remove previous message
				if (! msgBuffer.toString().contains(m))
				{
					lastMessage = m;
					lastPos = msgBuffer.length();
					if (msgBuffer.length() > 0)
						msgBuffer.append("\ncaused by: ");
					msgBuffer.append(m);
				}
			}
			e = cause;
		} while (true);
		return msgBuffer.toString();
	}


}
