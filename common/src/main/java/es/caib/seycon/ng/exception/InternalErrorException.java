/*
 * InternalErrorException2.java
 *
 * Created on November 9, 2010, 09:25 AM
 */
package es.caib.seycon.ng.exception;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * Error interno. Encapsula numerosos errores que pueden ocurrir durante el
 * proceso de las tareas. Añade el throwable que nos indica la causa
 * 
 * @author $Author: u07286 $
 * @version $Revision: 1.1.2.1 $
 */
public class InternalErrorException extends Exception {

    private static final long serialVersionUID = 1L;
    String _cause;
    String _causeFiltrat;
    String shortMessage;
    transient Throwable realCause = null;

    public InternalErrorException() {
        super();
    }

    public InternalErrorException(String msg) {
        super(msg);
    }

    public InternalErrorException(String msg, Object... params) {
        super(String.format (msg, params));
    }

    public InternalErrorException(String message, Throwable cause) {
        super(message);
        realCause = cause;
        try {
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            PrintStream ps = new PrintStream(bout);
            cause.printStackTrace(ps);
            ps.close();
            bout.close();
            this._cause = new String(bout.toByteArray());
            // Obtenim la causa filtrada
            bout = new ByteArrayOutputStream();
            ps = new PrintStream(bout);
            SoffidStackTrace.printStackTrace(cause, ps); 
            this._causeFiltrat = new String(bout.toByteArray());
            ps.close();
            bout.close();
            shortMessage = SoffidStackTrace.generateShortDescription(this);
        } catch (IOException ex) {

        }
    }

    public void printStackTrace() {// Afegim el stacktrace del cause
        printStackTrace(System.err);
    }

    public void printStackTrace(PrintStream s) {
        // Afegim el stacktrace del cause
        super.printStackTrace(s);
        if (_cause != null && realCause == null) {
            s.print(Messages.getString("InternalErrorException.CausedBy")); //$NON-NLS-1$
            s.println(_cause);
        }
    }

    public void printStackTrace(PrintWriter s) {
        // Afegim el stacktrace del cause
        super.printStackTrace(s);
        if (_cause != null && realCause == null) {
            s.print(Messages.getString("InternalErrorException.CausedBy")); //$NON-NLS-1$
            s.println(_cause);
        }
    }

    public String getCauseFiltratString() {
        return _causeFiltrat;
    }

	@Override
	public Throwable getCause()
	{
		return realCause;
	}

	@Override
	public String getMessage() {
		if (realCause == null && shortMessage != null)
			return shortMessage;
		else
			return super.getMessage();
	}

}
