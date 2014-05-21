
package es.caib.seycon.net;

/**
 * Excepción producida por direcciones IP mal formadas
 * @author $Author: u89559 $
 * @version $Revision: 1.1.2.1 $
 */

// $Log: InvalidIPException.java,v $
// Revision 1.1.2.1  2009-03-23 07:53:04  u89559
// *** empty log message ***
//
// Revision 1.1  2009-03-13 14:19:58  u07286
// Movidas las clases comunes de Seycon Legacy
//
// Revision 1.1  2008-05-22 11:52:13  u07286
// Version inicial
//
// Revision 1.1  2007-09-06 12:51:11  u89559
// [T252]
//
// Revision 1.2  2004-03-16 10:44:26  u07286
// Agregada informacion Javadoc y gestion de Puntos de Entrada
//

public class InvalidIPException extends Exception {
  /** Constructor
   * @param s causa detallada del error
   */
  InvalidIPException (String s) { super (s);}
}
