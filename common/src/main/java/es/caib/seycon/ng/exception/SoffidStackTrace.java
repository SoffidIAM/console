package es.caib.seycon.ng.exception;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import javax.ejb.EJBException;

public class SoffidStackTrace
{
    public static String getStackTrace (Throwable t)
    {
    	ByteArrayOutputStream out = new ByteArrayOutputStream();
    	try
		{
			printStackTrace(t, new PrintStream(out, true, "UTF-8"));
			return out.toString("UTF-8");
		}
		catch (UnsupportedEncodingException e)
		{
			return e.toString();
		}
    }
    
    public static void printStackTrace(Throwable e, PrintStream s) {
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
            s.print(e.getClass().getName());
            s.print(": "); //$NON-NLS-1$
            s.println (e.getMessage());
            for (int i = 0; i < trace.length; i++) {
                if (i == 0 || displayStackTrace(trace[i]))
                {
                	printStackTrace (trace[i], s);
                }
            }
        }
    }

    private static boolean displayStackTrace (StackTraceElement stackElement)
    {
    	String cn = stackElement.getClassName();
    	if (cn.startsWith("org.hibernate.") || //$NON-NLS-1$
    			cn.startsWith("org.jboss.") || //$NON-NLS-1$
    			cn.startsWith("org.springframework.") || //$NON-NLS-1$
    			cn.startsWith("org.apache.catalina.") || //$NON-NLS-1$
    			cn.startsWith("org.zkoss.") ||  //$NON-NLS-1$
    			cn.startsWith("com.sun.net.") ||  //$NON-NLS-1$
    			cn.startsWith("java.net.") ||  //$NON-NLS-1$
    			cn.startsWith("org.mortbay.") ||  //$NON-NLS-1$
    			cn.startsWith("sun.") ||  //$NON-NLS-1$
    			cn.startsWith("javas.servlet.") ||  //$NON-NLS-1$
    			cn.startsWith("java.lang.") ||  //$NON-NLS-1$
    			cn.startsWith("$") ||  //$NON-NLS-1$
    			cn.startsWith("bsh.") ) //$NON-NLS-1$
    		return false;
    	if (cn.endsWith("DaoBase") || cn.endsWith("ServiceBase")) //$NON-NLS-1$ //$NON-NLS-2$
    		return false;
   		return true;
    }

    private static void printStackTrace(StackTraceElement stackTraceElement,
			PrintStream s)
	{
    	s.println("\t"); //$NON-NLS-1$
        s.printf(Messages.getString("InternalErrorException.at"), stackTraceElement.toString()); //$NON-NLS-1$    	
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
    
	private static void printStackTraceAsCauseSeycon(Throwable th, PrintStream s,
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
        s.println (th.getMessage());
        if (th instanceof InternalErrorException)
        {

        	String c = ((InternalErrorException)th).getCauseFiltratString(); 
        	if (c != null) {
                s.print(Messages.getString("InternalErrorException.CausedBy")); //$NON-NLS-1$
        		s.print(c);
        	}

        } else {
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
	
        }
    }


}
