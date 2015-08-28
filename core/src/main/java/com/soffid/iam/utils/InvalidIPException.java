
package com.soffid.iam.utils;

/**
 * Excepción producida por direcciones IP mal formadas
 * @author $Author: u89559 $
 * @version $Revision: 1.1 $
 */

// $Log: InvalidIPException.java,v $
// Revision 1.1  2008-03-13 08:04:22  u89559
// [T390] version con bugs resueltos
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
