package es.caib.seycon.ng.sync.intf;

import java.rmi.Remote;

import es.caib.seycon.ng.exception.InternalErrorException;

public interface KerberosAgent extends Remote {
    /**
     * Creates a kerberos principal
     * 
     * @param name Principal name
     * @param password Principal password
     * @return keytab
     * @throws InternalErrorException 
     */
    public KerberosPrincipalInfo createServerPrincipal (String server) throws InternalErrorException;

    public String getRealmName () throws InternalErrorException;

    public String[] getRealmServers () throws InternalErrorException;

    public String parseKerberosToken ( String serverPrincipal, byte[] keytab, byte token[] ) throws InternalErrorException ;

    public String findPrincipalAccount (String principal) throws InternalErrorException ;

	public String[] getDomainNames() throws InternalErrorException;
}
