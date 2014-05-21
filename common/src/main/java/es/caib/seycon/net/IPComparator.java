// (c) 2004 DGTIC
// $Log: IPComparator.java,v $
// Revision 1.1.2.3  2012-09-25 08:57:19  u07286
// Multiples canvis
//
// Revision 1.1.2.2  2012-07-23 11:10:05  u88683
// Corregim refer�ncia a HostInfo perqu� referencie a es.caib.seycon.HostInfo (pare de l'altra inst�ncia)
//
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
// Revision 1.2  2004-03-16 10:44:26  u07286
// Agregada informacion Javadoc y gestion de Puntos de Entrada
//

package es.caib.seycon.net;
import java.util.Comparator;
import es.caib.seycon.*;
import es.caib.seycon.ng.sync.intf.NetworkInfo;

/** Comparador de IPs.
 * Se utiliza en los métodos de ordenación de listas y hashs con objetos
 * de tipo NetworkInfo, HostInfo o IPAddress.
 * 
 * @author $Author: u07286 $
 * @version $Revision: 1.1.2.3 $
 * 
 * @see IPAddress
 * @see HostInfo
 * @see NetworkInfo
 */

public class IPComparator implements Comparator 
{
  /** constructor */
  public IPComparator()
  {
  }

  /**
   * Obtener la dirección IP de un objeto, de tipo NetworkInfo, HostInfo o
   * IPAddress
   * @param o objeto cuya IP se desea extraer
   * @return objetop IPAddress con la dirección propia del objeto, o null si
   * no posee dirección
   */
  IPAddress extractIP (Object o)
  {
    try {
      if ( o instanceof NetworkInfo ) 
      {
        return new IPAddress ( ((NetworkInfo)o).IPAdress );
      }
      else if ( o instanceof es.caib.seycon.ng.sync.intf.HostInfo)
      {
        return new IPAddress ( ((es.caib.seycon.ng.sync.intf.HostInfo)o).IPAdress );
      }
      else if ( o instanceof IPAddress )
      {
        return (IPAddress) o;
      }
    } catch ( InvalidIPException e) 
    {
    }
    return null;
  }

  /**
   * Comparador de objetos.
   * Extrae las IPs de los objetos y las compara de forma numérica
   * @param o1 primer objeto a comparar
   * @param o2 segundo objeto a comparar
   * @return un número mayor que cero si o2 &gt; o1, un número menor que cero
   * si o1 &lt; o2 o cero si o1 = o2
   */
   
  public int compare(Object o1, Object o2)
  {
    IPAddress ip1 = extractIP (o1);
    IPAddress ip2 = extractIP (o2);
    if ( ip1 == null && ip2 == null) return 0;
    if ( ip1 == null ) return -1;
    if ( ip2 == null ) return 1;
    for ( int i = 0; i < 4; i++)
    {
      if ( ip1.ip[i] > ip2.ip[i]) return 1;
      if ( ip1.ip[i] < ip2.ip[i]) return -1;
    }
    return 0;
  }
}