/*
 * UnknownUserException.java
 *
 * Created on May 8, 2000, 11:51 AM
 */
package es.caib.seycon.ng.exception;

/** 
 * Excepción aplicación desconocido
 * 
 */
 
public class UnknownApplicationException extends Exception {
  /**
   * Creates new <code>UnknownUserException</code> without detail message.
   */
  public UnknownApplicationException() {
    super ();
  }
  
  /**
   * Constructs an <code>UnknownUserException</code> with the specified detail message.
   * @param msg the detail message.
   */
  public UnknownApplicationException(String msg) {
    super(msg); //$NON-NLS-1$
  }
}

