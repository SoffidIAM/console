/*
 * UnknownPrinterException.java
 *
 * Created on May 8, 2000, 11:51 AM
 */
// $Log: UnknownPrinterException.java,v $
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
 * Excepción impresora desconocida
 * 
 * @author  $Author: u07286 $
 * @version $Revision: 1.1.2.1 $
 */
 
public class UnknownPrinterException extends Exception {
  /**
   * Creates new <code>UnknownPrinterException</code> without detail message.
   */
  public UnknownPrinterException() {
    super ();
  }
  

  /**
   * Constructs an <code>UnknownPrinterException</code> with the specified detail message.
   * @param msg the detail message.
   */
  public UnknownPrinterException(String msg) {
    super(Messages.getString("UnknownPrinterException.0")+msg); //$NON-NLS-1$
  }
}
