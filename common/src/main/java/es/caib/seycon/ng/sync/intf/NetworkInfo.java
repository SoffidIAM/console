/*
 * NetworkInfo.java
 *
 * Created on May 8, 2000, 11:17 AM
 */
 
package es.caib.seycon.ng.sync.intf;

/** 
 * Información acerca de subredes
 *
 * @author  $Author: u07286 $
 * @version  $Revision: 1.1.2.1 $
 * @see NetworkMgr
 * @see Server
 */

// $Log: NetworkInfo.java,v $
// Revision 1.1.2.1  2012-09-25 08:57:19  u07286
// Multiples canvis
//
// Revision 1.1.2.1  2012-05-16 10:33:38  u07286
// Reestructuració de paquets seycon antics
//
// Revision 1.1  2007-09-06 12:51:10  u89559
// [T252]
//
// Revision 1.3  2004-03-15 12:08:04  u07286
// Conversion UTF-8
//
// Revision 1.2  2004/03/15 11:57:47  u07286
// Agregada documentacion JavaDoc
//

public class NetworkInfo extends Object implements java.io.Serializable {

  /** Crear NetworkInfo */
  public NetworkInfo () {
  }
  /** Nombre de la red - XAR_NOM */
  public String Name;
  /** Descripción - XAR_DESCRI */
  public String Description;
  /** Parámetros DHCP - XAR_PARDHC */
  public String DHCPParam;
  /** Dirección IP - XAR_ADRIP */
  public String IPAdress;
  /** Máscara de subred - XAR_MASIP */
  public String IPMask;
  /** Subred normalizada (ATM) - XAR_NORMAL */
  public boolean highBandWidth;
  /** Volar los datos
   * @param stream corriente de salida
   */
  public void dump (java.io.PrintStream stream) {
    stream.println (Messages.getString("NetworkInfo.Name")+Name); //$NON-NLS-1$
    stream.println (Messages.getString("NetworkInfo.Description")+Description); //$NON-NLS-1$
    stream.println (Messages.getString("NetworkInfo.dhcp")+ DHCPParam); //$NON-NLS-1$
    stream.println (Messages.getString("NetworkInfo.Adress")+ IPAdress ); //$NON-NLS-1$
    stream.println (Messages.getString("NetworkInfo.Mask")+ IPMask ); //$NON-NLS-1$
  }
}