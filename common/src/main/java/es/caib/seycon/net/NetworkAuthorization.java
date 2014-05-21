// (c) 2003 DGTIC
// $Log: NetworkAuthorization.java,v $
// Revision 1.1.2.3  2012-09-25 08:57:19  u07286
// Multiples canvis
//
// Revision 1.1.2.2  2012-07-23 11:10:33  u88683
// canviem refer�ncia a RoleInfo perqu� siga es.caib.seycon.RoleInfo (pare de l'altre inst�ncia)
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
import es.caib.seycon.*;
import es.caib.seycon.ng.sync.intf.GroupInfo;
import es.caib.seycon.ng.sync.intf.UserInfo;

import java.util.regex.*;

/**
 * Objeto autorización de acceso a la gestión de subredes
 * @author $Author: u07286 $
 * @version $Revision: 1.1.2.3 $
 */
 
public class NetworkAuthorization 
{
  /** usuario o rol autorizado */
  Object identitat;
  /** nivel de acceso */
  int nivell;
  /** máscara de nombre hosts de la red sobre los que tiene acceso */
  String mascara;
  /** expresión regular compilada a partir de la máscara */
  java.util.regex.Pattern pattern;

  /** sin acceso a la red*/
  public final static int SenseAcces = -1;
  /** acceso de consulta */
  public final static int Consulta = 0;
  /** acceso de consulta con posibilidad de ver sesiones abiertas y realizar
   * captura de pantallla y teclado 
   */
  public final static int Suport = 1;
  /** acceso de administración, con posibilidad de dar altas y bajas de máquinas
   */
  public final static int Administracio = 2;


  /** construir una nueva autorización */
  public NetworkAuthorization()
  {
  }

  /** recuperar la identidad (usuario,rol o grupo) autorizada 
   * @return identidad autorizada
   */
  public Object getIdentitat()
  {
    return identitat;
  }

  /** asignar la identidad (usuario,rol o grupo) autorizada 
   * @param newIdentitat objeto del tipo {@link UserInfo}, {@link GroupInfo} o
   * {@link RoleInfo}
   */
   
  public void setIdentitat(Object newIdentitat)
  {
    identitat = newIdentitat;
  }

  /** obtener el nivel de acceso 
   * @return {@link #SenseAcces} {@link #Consulta} {@link #Suport} o
   * {@link #Administracio}
   */
  public int getNivell()
  {
    return nivell;
  }

  /** asignar el nivel de acceso 
   * @param newLevel {@link #SenseAcces} {@link #Consulta} {@link #Suport} o
   * {@link #Administracio}
   */
  public void setNivell(int newLevel)
  {
    nivell = newLevel;
  }

  /** obtener la máscara de acceso
   * @return expresión regular que filtra las máquinas visibles por nombre
   */
  public String getMascara()
  {
    return mascara;
  }

  /** asignar la máscara de acceso
   * @param newMascara expresión regular que filtra las máquinas visibles por nombre
   */
  public void setMascara(String newMascara)
  {
    mascara = newMascara;
    pattern = Pattern.compile(newMascara);
  }

  /** determinar si un nombre host coincide con la máscara asignada
   * @return true si cumple la expresión regular de la mascara
   */
  public boolean matches (String host)
  {
    return pattern.matcher(host).matches();
  }

  /**
   * Generar cadena legible de la autorización
   * 
   * @return cadena con los datos de la identidad, máscara 
   *
   */

  public String toString() 
  {
    String s = "";   //$NON-NLS-1$

    if (identitat == null) s = ""; //$NON-NLS-1$
    else if (identitat instanceof UserInfo) 
      s = s + ((UserInfo)identitat).User;
    else if (identitat instanceof es.caib.seycon.ng.sync.intf.RoleInfo)
      s = s + ((es.caib.seycon.ng.sync.intf.RoleInfo)identitat).name+"@"+((es.caib.seycon.ng.sync.intf.RoleInfo)identitat).db; //$NON-NLS-1$
    else
      s = s + ((GroupInfo)identitat).Name;

    s = s + "/" + nivell + "/"; //$NON-NLS-1$ //$NON-NLS-2$

    s = s + mascara;
    return s;
  }

  /**
   * Obtener el nombre legible de la identidad asignada
   * @return nombre del rol, usuario o grupo
   */
  public String getNomIdentitat ()
  {
    if (identitat == null) return null;
    else if (identitat instanceof UserInfo)
    {
      return ((UserInfo)identitat).User;
    } 
    else if (identitat instanceof es.caib.seycon.ng.sync.intf.RoleInfo)
    {
    	es.caib.seycon.ng.sync.intf.RoleInfo r1 = (es.caib.seycon.ng.sync.intf.RoleInfo) identitat;
      return r1.name+(r1.db==null?"":"@"+r1.db); //$NON-NLS-1$ //$NON-NLS-2$
    }
    else if (identitat instanceof GroupInfo)
    {
      return ((GroupInfo)identitat).Name;
    } else 
    {
      return identitat.toString ();
    }
  }

  /**
   * Comparación entre autorización
   * @return true si son autorizaciones iguales
   */
  public boolean equals(Object obj)
  {
    if (obj instanceof NetworkAuthorization) 
    {
      NetworkAuthorization obj2 = (NetworkAuthorization) obj;
      if (nivell != obj2.nivell ) return false;
      if (mascara != null && obj2.mascara == null ||
          mascara == null && obj2.mascara != null) return false;
      if (mascara != null && ! mascara.equals (obj2.mascara)) return false;
      if (identitat != null && obj2.identitat == null ||
          identitat == null && obj2.identitat != null ) return false;
      if (identitat == null && obj2.identitat == null) return true;
      if (identitat.getClass() != obj2.identitat.getClass()) return false;
      if (identitat instanceof UserInfo)
      {
        return ((UserInfo)identitat).Name.equals (((UserInfo)obj2.identitat).Name);
      } 
      else if (identitat instanceof es.caib.seycon.ng.sync.intf.RoleInfo)
      {
    	es.caib.seycon.ng.sync.intf.RoleInfo r1 = (es.caib.seycon.ng.sync.intf.RoleInfo) identitat;
    	es.caib.seycon.ng.sync.intf.RoleInfo r2 = (es.caib.seycon.ng.sync.intf.RoleInfo) obj2.identitat;
        return r1.name.equals (r2.name) &&
               (r1.db == null && r2.db == null ||
                r1.db != null && r1.db.equals (r2.db));
      }
      else if (identitat instanceof GroupInfo)
      {
        return ((GroupInfo)identitat).Name.equals (((GroupInfo)obj2.identitat).Name);
      }
      else
        return false;

    } else
      return false;
  }

}

