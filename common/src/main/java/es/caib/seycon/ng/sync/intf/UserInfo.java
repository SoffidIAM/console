/*
 * UserInfo.java
 *
 * Created on May 8, 2000, 11:17 AM
 */

// $Log: UserInfo.java,v $
// Revision 1.1.2.1  2012-09-25 08:57:19  u07286
// Multiples canvis
//
// Revision 1.1.2.1  2012-05-16 10:33:38  u07286
// Reestructuració de paquets seycon antics
//
// Revision 1.1  2007-09-06 12:51:10  u89559
// [T252]
//
// Revision 1.3  2004-03-15 12:08:09  u07286
// Conversion UTF-8
//
// Revision 1.2  2004/03/15 11:57:53  u07286
// Agregada documentacion JavaDoc
//
 
package es.caib.seycon.ng.sync.intf;
import java.text.*;

/** 
 * Estructura de información relativa a un usuario
 * @author  $Author: u07286 $
 * @version $Revision: 1.1.2.1 $
 */
 
public class UserInfo extends Object implements java.io.Serializable {

  /** Creates new UserInfo */
  public UserInfo() {
  }
  /** codigo de usuario */
  public String User;
  /** nombre de pila */
  public String Name;
  /** primer apellido */
  public String FirstFamilyName;
  /** segundo apellido */
  public String SecondFamilyName;
  /** grupo principal */
  public String PrimaryGroup;
  /** nombre corto (alias de correo */
  public String ShortName;
  /** subdominio de correo */
  public String MailDomain;
  /** servidor ofimmático (H:) */
  public String HomeServer;
  /** espacio asignado en el servidor ofimático @deprecated */
  public long UserQuota;
  /** servidor de correo */
  public String MailServer;
  /** servidor de perfiles */
  public String ProfileServer;
  /** alias de correo @deprecated */
  public String OldAliases;
  /** unidades ofimáticas especiales @deprecated */
  public String AdditionalDrives;
  /** usuario activo */
  public boolean active;
  /** tipo de usuario I = interno / E = externo */
  public String userType;
  /** duración máxima de las contraseñas */
  public int passwordMaxAge;
  /** tiempo desde la expiración de la contraseña, durante el cual se permite
   * que el usuario la cambie */
  public int passwordGrace;
  
  /** volcar el contenido @param stream corriente de salida */
  public void dump (java.io.PrintStream stream) {
    stream.println ("User:             "+User); //$NON-NLS-1$
    stream.println ("Name:             "+Name); //$NON-NLS-1$
    stream.println ("FirstFamilyName:  "+FirstFamilyName); //$NON-NLS-1$
    stream.println ("SecondFamilyName: "+SecondFamilyName); //$NON-NLS-1$
    stream.println ("ShortName:        "+ShortName); //$NON-NLS-1$
    stream.println ("MailDomain:       "+MailDomain); //$NON-NLS-1$
    stream.println ("HomeServer:       "+HomeServer); //$NON-NLS-1$
    stream.println ("UserQuota:        "+ NumberFormat.getInstance().format(UserQuota)); //$NON-NLS-1$
    stream.println ("MailServer:       "+MailServer); //$NON-NLS-1$
    stream.println ("ProfileServer:    "+ProfileServer); //$NON-NLS-1$
    stream.println ("OldAliases:       "+OldAliases); //$NON-NLS-1$
  }
}