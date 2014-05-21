package es.caib.seycon.net;

/** Métodos de ejecución.
 * Hace referencia a los distintos métodos de ejecuciones que se pueden poner
 * en la intranet. Básicamente son: WPI, URLs de internet o cliente ICA
 * @author $Author: u89559 $
 * @version $Version$
 * */

// $Log: ExecutionType.java,v $
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
//
 
public class ExecutionType 
{
  String type;
  String mimeType;
  String template;
  /** Constructor por defecto. No usar
   * @param newType Codigo interno
   * @param newMimeType Tipo mime a declara al navegador
   * @param newTemplate Plantilla base del contenido que tendrá la repuesta.
   * Sólamente sirve de ayuda al usuario
   * @see es.caib.seycon.net.ejb.PuntEntradaEJB
   * @see es.caib.seycon.net.ExecutionMethod
   */
  public ExecutionType(String newType, String newMimeType, String newTemplate)
  {
    type = newType;
    mimeType = newMimeType;
    template = newTemplate;
  }

  /** Obtener el tipo de ejecucion
   * @return Código del tipo de ejecucion
   */
  public String getType () { return type; }
  /** Obtener el tipo mime
   * @return Código del tipo mime
   */
  public String getMimeType () { return mimeType; }
  /** Obtener la plantilla
   * @return Contenido del valor por defecto
   */
  public String getTemplate () { return template; }
}