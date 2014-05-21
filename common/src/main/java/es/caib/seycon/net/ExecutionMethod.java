package es.caib.seycon.net;

/** Métodos de ejecución.
 * Hace referencia a la ejecucion de un punto de entrada.
 * Está compuesto de un tipo de ejecución (ICA, URL, WPI u otros) y un 
 * contenido.
 *
 * @author $Author: u89559 $
 * @version $Version$
 * */
 
// @log
// $Log: ExecutionMethod.java,v $
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
// Revision 1.1.2.2  2004/02/20 12:13:33  u07286
// Comentarios javadoc

public class ExecutionMethod 
{
  int location;
  String type;
  String content;
  /** Constructor por defecto. No usar
   * @param newType Codigo interno del ExecutionType
   * @param newContent Contenido a retornar al cliente
   * @see es.caib.seycon.net.ejb.PuntEntradaEJB
   * @see es.caib.seycon.net.ExecutionType
   */
  public ExecutionMethod (String newType, String newContent)
  {
    type = newType;
    content = newContent;
  }

  /** Obtener el tipo de ejecución
   * @return Código del tipo de ejecucion
   * @see es.caib.seycon.net.ExecutionType
   */
  public String getType ()  { return type; }
  /** Obtener el contenido a ejecutar. En caso del tipo URL, se trata de la URL
   * a mostrar. 
   * En caso ICA o WPI contiene el contenido del archivo ICA o WPI a generar
   * @return Contenido de la ejecución
   * @see es.caib.seycon.net.ExecutionType
   */
  public String getContent () { return content; }
}