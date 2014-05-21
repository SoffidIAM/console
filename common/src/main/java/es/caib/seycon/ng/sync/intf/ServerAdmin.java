
// Copyright (c) 2000 Govern  de les Illes Balears
package es.caib.seycon.ng.sync.intf;

/**
 * Intefaz remota de gestión del servidor SEYCON
 * <P>
 * @author $Author: u07286 $
 * @version $Revision: 1.1.2.1 $
 */

// $Log: ServerAdmin.java,v $
// Revision 1.1.2.1  2012-09-25 08:57:19  u07286
// Multiples canvis
//
// Revision 1.1.2.1  2012-05-16 10:33:38  u07286
// Reestructuració de paquets seycon antics
//
// Revision 1.1  2007-09-06 12:51:10  u89559
// [T252]
//
// Revision 1.3  2004-03-15 12:08:05  u07286
// Conversion UTF-8
//
// Revision 1.2  2004/03/15 11:57:49  u07286
// Agregada documentacion JavaDoc
//

public interface ServerAdmin extends java.rmi.Remote
{
  /** Obtener el estado del servidor @return estado del servidor */
  public String getStatus () throws java.rmi.RemoteException;
  /** Obtener el estado ampliado del servidor @return estado ampliado del servidor */
  public String getStatus (String type) throws java.rmi.RemoteException;
  /** Reiniciar la conexión con todos los agentes */
  public void restart () throws java.rmi.RemoteException;
  /** Detener ordenadamente el servidor */
  public void shutdown () throws java.rmi.RemoteException;
}

 