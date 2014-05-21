package es.caib.seycon.net;

/** Autorización a un individuo o rol a acceder a un punto de entrada.
 *
 * @author $Author: u89559 $
 * @version $Revision: 1.1.2.1 $
 * @see es.caib.seycon.net.ejb.PuntEntradaEJB
 * @see es.caib.seycon.net.PrincipalInfo
 */
 
 // $Log: Authorization.java,v $
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
 // Revision 1.3  2004-03-16 12:26:45  u07286
 // Actualizadas referencias JavaDoc
 //
 // Revision 1.2  2004/03/16 10:44:25  u07286
 // Agregada informacion Javadoc y gestion de Puntos de Entrada
 //
 // Revision 1.1  2004/03/15 12:13:28  u07286
 // Version inicial
 //
 // Revision 1.1.2.2  2004/02/20 12:13:33  u07286
 // Comentarios javadoc
 
 
public class Authorization 
{
  /** Acceso de consulta ( y ejecución ) */
  public static final int query = 1;
  /** Acceso de administrador */
  public static final int admin = 2;
  
  String principal;
  int level;
  /** Constructor por defecto. No se suele utilizar
   * @param principal Nombre del usuario, grupo o rol
   * @param level Nivel de acceso (query o admin)
   * @see es.caib.seycon.net.ejb.PuntEntradaEJB
   **/
  public Authorization(String principal, int level)
  {
     this.principal = principal;
     this.level = level;
  }

  /** Comparador
   * @param o Objecto Authorization a comparar
   * @return true si los objetos son idénticos
   */
  public boolean equals (Object o) {
    try {
      Authorization a = (Authorization) o;
//      System.out.println ("Comparando "+principal+"("+level+") con "+a.principal+"("+a.level+") = "+
//         (level == a.level && principal.equals (a.principal)) );
      return level == a.level && principal.equals (a.principal) ;
    } catch (ClassCastException e) {
      return false;
    }
  }
  /** Obtener el principal
   * @return Código del usuario, role o grupo
   */
  public String getPrincipal () { return principal; }
  /** Obtener el nivel de acceso
   * @return admin o query
   */
  public int getLevel () {return level; }
}
