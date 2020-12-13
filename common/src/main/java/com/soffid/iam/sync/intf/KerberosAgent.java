package com.soffid.iam.sync.intf;

import java.rmi.Remote;

import es.caib.seycon.ng.exception.InternalErrorException;

public interface KerberosAgent extends Remote {
    public KerberosPrincipalInfo createServerPrincipal (String server) throws InternalErrorException;

    public String getRealmName () throws InternalErrorException;

    public String[] getRealmServers () throws InternalErrorException;
    
    public String parseKerberosToken ( String domain,  byte[] keytab, byte token[] ) throws InternalErrorException ;
    
    public String findPrincipalAccount (String principal) throws InternalErrorException ;
}
