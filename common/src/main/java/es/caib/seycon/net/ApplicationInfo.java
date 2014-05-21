package es.caib.seycon.net;
import java.io.Serializable;

/** Estructura de informacion acerca de las aplicaciones.
 * Hace referencia a la tabla SC_APLICA
 *
 * 
 * @author $Author: u89559 $
 * @version $Revision: 1.1.2.1 $
 * */

// TODO: Revisaro todo

 
 
public class ApplicationInfo extends Object implements Serializable
{
   /** Identificador interno de la aplicacion */
  long id; 
  /** Nombre */
  String name;
  String code;
  /** 
   * Construye un objeto ApplicationInfo. No suele ser usado nunca 
   * @param  id    Identificador interno
   * @param  name Nombre a utilizar
   * @see es.caib.seycon.net.ejb.AuxiliaryDataEJB
   */
  public ApplicationInfo(long id, String name, String code)
  {
    this.id=id;
    this.name=name;
    this.code = code;
  }
  /** Obtener el nombre 
   * @return Nombre descriptivo de la aplicacion 
   * */
  public String getName() {return name;}
  /** Obtener el id interno 
   * @return Identificador interno */
  public long getId() { return id;}
  
  public String getCode() {return code;}
}