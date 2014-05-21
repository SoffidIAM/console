/*
 * UnknownUserException.java
 *
 * Created on May 8, 2000, 11:51 AM
 */
// $Log: UnknownUserException.java,v $
// Revision 1.1.2.1  2012-09-18 06:12:52  u07286
// Canvi paquet excepcions
//
// Revision 1.1.2.1  2012-05-16 10:33:38  u07286
// Reestructuració de paquets seycon antics
//
// Revision 1.1  2007-09-06 12:51:10  u89559
// [T252]
//
// Revision 1.3  2004-03-15 12:08:08  u07286
// Conversion UTF-8
//
// Revision 1.2  2004/03/15 11:57:53  u07286
// Agregada documentacion JavaDoc
//

package es.caib.seycon.ng.exception;

/** 
 * Excepción usuario desconocido
 * 
 * @author  $Author: u07286 $
 * @version $Log: UnknownUserException.java,v $
 * @version Revision 1.1.2.1  2012-09-18 06:12:52  u07286
 * @version Canvi paquet excepcions
 * @version
 * @version Revision 1.1.2.1  2012-05-16 10:33:38  u07286
 * @version Reestructuració de paquets seycon antics
 * @version
 * @version Revision 1.1  2007-09-06 12:51:10  u89559
 * @version [T252]
 * @version
 * @version Revision 1.3  2004-03-15 12:08:08  u07286
 * @version Conversion UTF-8
 * @version
 * @version Revision 1.2  2004/03/15 11:57:53  u07286
 * @version Agregada documentacion JavaDoc
 * @version
 */
 
public class UnknownUserException extends Exception {
  /**
   * Creates new <code>UnknownUserException</code> without detail message.
   */
  public UnknownUserException() {
    super ();
  }
  
  /**
   * Constructs an <code>UnknownUserException</code> with the specified detail message.
   * @param msg the detail message.
   */
  public UnknownUserException(String msg) {
    super(msg); //$NON-NLS-1$
  }
}

