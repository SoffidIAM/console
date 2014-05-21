// (c) 2003 DGTIC
// $Log: PrincipalInfo.java,v $
// Revision 1.1.2.2  2012-09-25 08:57:19  u07286
// Multiples canvis
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
// Revision 1.2  2004-03-16 10:44:27  u07286
// Agregada informacion Javadoc y gestion de Puntos de Entrada
//

package es.caib.seycon.net;
import es.caib.seycon.*;
import es.caib.seycon.ng.sync.intf.GroupInfo;
import es.caib.seycon.ng.sync.intf.UserInfo;

/**
 * Implementa un principal susceptible de ser autorizado.
 * Formalmente contendra un rol, usuario o grupo
 * @author $Author: u07286 $
 * @version $Revision: 1.1.2.2 $
 */
 
public class PrincipalInfo 
{
  /** nombre del principal */
  String name;
  /** descripción del principal */
  String description;
  /** construir a partir de un nombre y descripción arbitrarios 
   * @param name nombre del principal
   * @description descripción
   */
  public PrincipalInfo(String name, String description)
  {
    this.name = name;
    this.description = description;
  }

  /** construir a partir de los datos de un usuario
   * @param ui información del usuario
   */
  public PrincipalInfo(UserInfo ui)
  {
    this.name = ui.User;
    this.description = ui.Name+ " " + ui.FirstFamilyName+ " " +  //$NON-NLS-1$ //$NON-NLS-2$
      (ui.SecondFamilyName == null ? "": ui.SecondFamilyName); //$NON-NLS-1$
  }

  /** construir a partir de los datos de un grupo 
   * @param gi datos del grupo
   */
  public PrincipalInfo(GroupInfo gi)
  {
    this.name = gi.Name;
    this.description = gi.Description;
  }

  /** construir a partir de los datos de un rol
   * @param ri datos del rol
   */
  public PrincipalInfo(RoleInfo ri)
  {
    this.name = ri.name+(ri.db == null? "": "@"+ri.db); //$NON-NLS-1$ //$NON-NLS-2$
    this.description = ri.description;
  }

  /** obtener el nombre del principal */
  public String getName () { return name; }
  /** obtener su descripción */
  public String getDescription () { return description; }
}

