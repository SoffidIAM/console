// (c) 2003 DGTIC
// $Log: Sessio.java,v $
// Revision 1.1.2.2  2012-09-25 08:57:19  u07286
// Multiples canvis
//
// Revision 1.1.2.1  2009-03-23 07:53:04  u89559
// *** empty log message ***
//
// Revision 1.1  2009-03-13 14:19:58  u07286
// Movidas las clases comunes de Seycon Legacy
//
// Revision 1.2  2008-10-02 08:51:06  u89559
// *** empty log message ***
//
// Revision 1.1  2008-05-22 11:52:13  u07286
// Version inicial
//
// Revision 1.1  2007-09-06 12:51:11  u89559
// [T252]
//
// Revision 1.3  2004-03-16 10:44:27  u07286
// Agregada informacion Javadoc y gestion de Puntos de Entrada
//

package es.caib.seycon.net;
import es.caib.seycon.net.HostInfo;
import es.caib.seycon.ng.sync.intf.UserInfo;


/**
 * Sesión Single-Sign-ON establecida desde un PC
 */
public class Sessio implements java.io.Serializable
{
  /** identificador de la sesión */
  long id;
  /** máquina que realiza el SSO */
  HostInfo maquina;
  /** máquina o dispositivo donde se encuentra el usuario */
  HostInfo maquinaCliente;
  /** datos del usuario */
  UserInfo usuari;

  /** construir una nueva sesión */
  public Sessio()
  {
  }

  /** obtener el identificador interno
   * @return identificador
   */
  public long getId()
  {
    return id;
  }

  /** asignar el identificador interno
   * @param newId identificador a asignar
   */
  public void setId(long newId)
  {
    id = newId;
  }

  /** obtener la máquina que se ha conectado
   * @return información de la máquina que ha identificado al usuario
   */
  public HostInfo getMaquina()
  {
    return maquina;
  }

  /** asignar la máquina que se ha conectado
   * @param newMaquina información de la maquina que ha identificado al usuario
   */
  public void setMaquina(HostInfo newMaquina)
  {
    maquina = newMaquina;
  }

  /** obtener la máquina donde se encuentra el usaurio
   * @return información de la máquina que ha utilizado el usuario
   */
  public HostInfo getMaquinaCliente()
  {
    return maquinaCliente;
  }

  /** asignar la máquina donde se encuentra el usaurio
   * @param newMaquinaCliente información de la máquina que ha utilizado el usuario
   */
  public void setMaquinaCliente(HostInfo newMaquinaCliente)
  {
    maquinaCliente = newMaquinaCliente;
  }

  /** obtener los datos del usuario
   * @return información del usuario
   */
  public UserInfo getUsuari()
  {
    return usuari;
  }

  /** asignar los datos del usuario
   * @param newUsuario datos del usuario
   */
  public void setUsuari(UserInfo newUsuari)
  {
    usuari = newUsuari;
  }
}