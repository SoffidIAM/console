/*
 * AgentMgr.java
 *
 * Created on May 8, 2000, 10:44 AM
 */


package es.caib.seycon.ng.sync.intf;
import java.util.Date;

import es.caib.seycon.ng.exception.InternalErrorException;

/** Interfaz remoto para gestionar el arranque de los agentes SEYCON.
 * Se llama desde el SeyconServer cada vez que se teiene que instanciar un
 * agente SEYCON. Sigue el siguiente protocolo:<BR>
 * <LI>El agente Seycon (actua como servidor RMI) crea el registro RMI según
 * el parámetro seycon.agent.url</LI>
 * <LI>El agente Seycon registra una clase que implementa AgentMgr en la URL
 * especificada en el parámetro seycon.agent.url</LI>
 * <LI>El {@link es.caib.seycon.impl.TaskDispatcher} correspondiente se ejecuta en el servidor seycon 
 * (actua como cliente RMI) y contacta con el agente seycon a través de la URL
 * especificada en la tabla SC_DISPAT, obteniendo el interfaz {@link es.caib.seycon.ng.sync.intf.AgentMgr}.
 * Esta URL coincide con la URL especificada
 * en la propiedad seycon.agent.url</LI>
 * <LI>{@link es.caib.seycon.impl.TaskDispatcher} ejecuta el método GetID</LI>
 * <LI>{@link es.caib.seycon.impl.TaskDispatcher} firma con la clave privada seycon (propiedad 
 * seycon.privatekey) la cadena aleatoria de bytes generada por el agente</LI>
 * <LI>{@link es.caib.seycon.impl.TaskDispatcher} invoca getInstance con el array firmado, el nombre de clase
 * a instanciar y los parámetros pertinentes</LI>
 * <LI>{@link es.caib.seycon.impl.TaskDispatcher} hace uso del objeto remoto recibido (derivado de {@link es.caib.seycon.ng.sync.agent.Agent})
 * para propagar los cambios habidos en el SEYCON</LI>
 * 
 * 
 * @author $Author: u07286 $
 * @version $Revision: 1.1.2.2 $
 * @see es.caib.seycon.impl.TaskDispatcher
 * @see ClientApplication
 * @see AgentMgrImpl
 * @see Agent
 */

 // $Log: AgentMgr.java,v $
 // Revision 1.1.2.2  2012-10-01 06:21:20  u07286
 // Reestructuració de paquets
 //
 // Revision 1.1.2.1  2012-09-25 08:57:19  u07286
 // Multiples canvis
 //
 // Revision 1.1.2.2  2012-09-18 06:11:08  u07286
 // Canvi paquet excepcions
 //
 // Revision 1.1.2.1  2012-05-16 10:33:38  u07286
 // Reestructuració de paquets seycon antics
 //
 // Revision 1.6  2010-07-15 12:48:33  u88683
 // Afegim informaci� de la caducitad dels certificats dels agents
 //
 // Revision 1.5  2010-03-15 10:23:31  u07286
 // Movido a tag HEAD
 //
 // Revision 1.4.2.2  2009-03-23 07:52:00  u89559
 // *** empty log message ***
 //
 // Revision 1.4.2.1  2009-01-09 12:39:11  u89559
 // *** empty log message ***
 //
 // Revision 1.4  2008-12-16 08:39:16  u89559
 // *** empty log message ***
 //
 // Revision 1.3  2008-10-16 11:43:42  u07286
 // Migrado de RMI a HTTP
 //
 // Revision 1.2  2008-10-03 13:29:04  u89559
 // *** empty log message ***
 //
 // Revision 1.1  2007-09-06 12:51:10  u89559
 // [T252]
 //
 // Revision 1.3  2004-03-15 12:08:02  u07286
 // Conversion UTF-8
 //
 // Revision 1.2  2004/03/15 11:57:45  u07286
 // Agregada documentacion JavaDoc
 //

public interface AgentMgr  {
  /** Instanciar un agente seycon.
   * @param firma Array firmado con la clave privada. Se verificará que el 
   * vector retornado en la llada a GetID está firmado con la clave pública
   * indicada por la propiedad (seycon.publickey)
   * @param agentClass Clase de objetos a instanciar
   * @param agentName Nombre del agente
   * @param roleBased Está basada en roles o no
   * @param params Parámetros del agente
   * @return Objecto derivado de {@link es.caib.seycon.ng.sync.agent.Agent}
   * @exception InternalErrorException Algún problema de configuración, o error
   * al firmar la ID proporcinado en la llamada anterior
   * @exception java.rmi.RemoteException Error de comunicaciones
   */
  public String getInstance (String agentName, String agentClass, boolean roleBased, String grups[], String tipusUsuaris[], String params[])
    throws java.rmi.RemoteException, 
            InternalErrorException;
  
  public void reset() throws java.rmi.RemoteException;
  
  public Date getCertificateNotValidAfter() throws java.rmi.RemoteException;
  

}

