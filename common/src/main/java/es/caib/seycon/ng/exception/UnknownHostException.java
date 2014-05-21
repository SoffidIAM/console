/*
 * UnknownHostException.java
 *
 * Created on May 8, 2000, 11:51 AM
 */
// $Log: UnknownHostException.java,v $
// Revision 1.1.2.1  2012-09-18 06:12:52  u07286
// Canvi paquet excepcions
//
// Revision 1.1.2.1  2012-05-16 10:33:38  u07286
// Reestructuració de paquets seycon antics
//
// Revision 1.1  2007-09-06 12:51:10  u89559
// [T252]
//
// Revision 1.3  2004-03-15 12:08:07  u07286
// Conversion UTF-8
//
// Revision 1.2  2004/03/15 11:57:51  u07286
// Agregada documentacion JavaDoc
//

package es.caib.seycon.ng.exception;

/** 
 * Excepción máquina desconcida (SC_MAQUIN)
 *
 * @author  u07286
 * @version 
 */
public class UnknownHostException extends Exception {
  /**
   * Creates new <code>UnknownHostException</code> without detail message.
   */
  public UnknownHostException() {
    super ();
  }
  

  /**
   * Constructs an <code>UnknownHostException</code> with the specified detail message.
   * @param msg the detail message.
   */
  public UnknownHostException(String msg) {
    super(Messages.getString("UnknownHostException.0")+msg); //$NON-NLS-1$
  }
}

