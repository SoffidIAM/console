/*
 * HostInfo.java
 *
 * Created on May 8, 2000, 11:17 AM
 */
 
package es.caib.seycon.ng.sync.intf;


/** 
 * Información acerca de una máquina. Corresponde a la tabla SC_MAQUIN
 *
 * @author  $Author: u07286 $
 * @version  $Revision: 1.1.2.1 $
 * @see HostMgr
 * @see Server
 */

// $Log: HostInfo.java,v $
// Revision 1.1.2.1  2012-09-25 08:57:19  u07286
// Multiples canvis
//
// Revision 1.1.2.1  2012-05-16 10:33:38  u07286
// Reestructuració de paquets seycon antics
//
// Revision 1.3  2010-03-15 10:23:31  u07286
// Movido a tag HEAD
//
// Revision 1.2.2.1  2009-10-22 06:37:55  u88683
// Corregimos comentario del campo Network
//
// Revision 1.2  2008-06-13 08:29:09  u89559
// *** empty log message ***
//
// Revision 1.1  2007-09-06 12:51:10  u89559
// [T252]
//
// Revision 1.3  2004-03-15 12:08:03  u07286
// Conversion UTF-8
//
// Revision 1.2  2004/03/15 11:57:46  u07286
// Agregada documentacion JavaDoc
//
public class HostInfo extends Object implements java.io.Serializable {

  /** Creates new HostInfo */
  public HostInfo () {
  }
  /** Nombre de la máquina - MAQ_NOM */
  public String Name;
  /** Descripción de la maquina - MAQ_DESCRI */
  public String Description;
  /** Parámetros DHCP - MAQ_PARDHC + información de la red */
  public String DHCPParam;
  /** Dirección IP - MAQ_ADRIP */
  public String IPAdress;
  /** Nombre de la red - XAR_CODI */
  public String Network;
  /** Sistema operativo - MAQ_SISOPE */
  public String OperatingSystem;
  /** Alias de la máquina MAQ_ALIES */
  public String alias [];
  /** Xarxa normalitzada */
  public boolean Normalitzada;
  /** Volcar el contenido.
   * @param stream corriente de salida
   */
  public void dump (java.io.PrintStream stream) {
    stream.println (Messages.getString("HostInfo.Name")+Name); //$NON-NLS-1$
    stream.println (Messages.getString("HostInfo.Description")+Description); //$NON-NLS-1$
    stream.println (Messages.getString("HostInfo.Dhcp")+ DHCPParam); //$NON-NLS-1$
    stream.println (Messages.getString("HostInfo.Adress")+ IPAdress ); //$NON-NLS-1$
    stream.println (Messages.getString("HostInfo.Network")+ Network ); //$NON-NLS-1$
    stream.println (Messages.getString("HostInfo.Os")+ OperatingSystem); //$NON-NLS-1$
    stream.println (Messages.getString("HostInfo.Normalized")+ Normalitzada); //$NON-NLS-1$
  }
}