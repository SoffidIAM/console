/*
 * AgentMgr.java
 *
 * Created on May 8, 2000, 10:44 AM
 */


package com.soffid.iam.sync.intf;
import java.util.Date;

import es.caib.seycon.ng.exception.InternalErrorException;

public interface AgentMgr  {
  public String getInstance (String agentName, String agentClass, boolean roleBased, String grups[], String tipusUsuaris[], String params[])
    throws java.rmi.RemoteException, 
            InternalErrorException;
  
  public void reset() throws java.rmi.RemoteException;
  
  public Date getCertificateNotValidAfter() throws java.rmi.RemoteException;
}

