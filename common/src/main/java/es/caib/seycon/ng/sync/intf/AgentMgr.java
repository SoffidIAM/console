/*
 * AgentMgr.java
 *
 * Created on May 8, 2000, 10:44 AM
 */


package es.caib.seycon.ng.sync.intf;
import java.util.Date;

import es.caib.seycon.ng.exception.InternalErrorException;

@Deprecated
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

