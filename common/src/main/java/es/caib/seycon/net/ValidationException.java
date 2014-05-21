// (c) 2003 DGTIC
// $Log: ValidationException.java,v $
// Revision 1.1.2.1  2009-03-23 07:53:05  u89559
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
// Revision 1.2  2004-03-16 10:44:27  u07286
// Agregada informacion Javadoc y gestion de Puntos de Entrada
//

package es.caib.seycon.net;

/** Error genérico de validación de datos.
 * Es usado de forma masiva en los EJBs para denotar errorse en la
 * introducción de datos
 * @author $Author: u89559 $
 * @version $Revision: 1.1.2.1 $
 */
public class ValidationException extends Exception 
{

  /** texto del mensaje */
  String msg ;
  /** constructor */
  public ValidationException()
  {
    msg = ""; //$NON-NLS-1$
  }

  /** constructor con informacion adicional */
  public ValidationException (String s) {
    super (s);
    msg = s;
  }

  /** obtener información adicional */
  public String getMessage () { return msg; }
}