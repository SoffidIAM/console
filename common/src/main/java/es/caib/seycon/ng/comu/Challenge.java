// Copyright (c) 2000 Govern  de les Illes Balears
package es.caib.seycon.ng.comu;

import java.io.Serializable;

import org.ietf.jgss.GSSContext;

/**
 * Desafio que el servidor lanza al cliente durante el proceso de Single
 * Sign-on. El desafío es generado por el servidor y completado por el cliente
 * <P>
 * 
 * @author $Author: u07286 $
 * @version Revision$
 */

// $Log: Challenge.java,v $
// Revision 1.1.2.2  2012-10-17 11:54:52  u07286
// Reestructuració de paquets
//
// Revision 1.1.2.1  2012-10-01 06:21:20  u07286
// Reestructuració de paquets
//
// Revision 1.1.2.2  2012-09-18 06:13:45  u07286
// Moure class Password
//
// Revision 1.1.2.1  2012-05-16 10:33:38  u07286
// Reestructuració de paquets seycon antics
//
// Revision 1.6  2011-06-01 08:58:53  u07286
// Corregit buts en password login
//
// Revision 1.5  2010-12-13 12:22:23  u07286
// Login per certificat
//
// Revision 1.4  2010-08-27 07:54:05  u07286
// Kerberos debugging
//
// Revision 1.3  2010-08-26 12:05:04  u07286
// Soporte a KojiKabuto
//
// Revision 1.2  2010-07-26 11:44:02  u07286
// Single sig-on kerberos
//
// Revision 1.1 2007-09-06 12:51:17 u89559
// [T252]
//
// Revision 1.2 2004-05-18 06:13:40 u07286
// Agregado javadoc
// 

public class Challenge extends Object implements Serializable {
    /** No puede iniciar si no dispone de tarjeta */
    public static final int CARD_REQUIRED = 1;
    /** Se debe solicitar la tarjeta si esta disponible */
    public static final int CARD_IFABLE = 2;
    /** Sólo se pedira la tarjeta si el cliente (IP origen) es desconocido */
    public static final int CARD_IFNEEDED = 3;
    /** Nunca se debe pedir la tarjeta */
    public static final int CARD_DISABLED = 4;

    public static final int TYPE_RMI = 0;
    public static final int TYPE_KERBEROS = 2;
    public static final int TYPE_CERT = 3;
    public static final int TYPE_PASSWORD = 3;
    /** Identificador único del desafío */

    /** Tipo de identifcación **/
    private int type;
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    /** Código de usuario */
    Usuari user;
    
    public Usuari getUser() {
        return user;
    }

    public void setUser(Usuari user) {
        this.user = user;
    }
    
    String userKey;
    

    
    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public Maquina getHost() {
        return host;
    }

    public void setHost(Maquina host) {
        this.host = host;
    }

    public Maquina getClientHost() {
        return clientHost;
    }

    public void setClientHost(Maquina clientHost) {
        this.clientHost = clientHost;
    }

    /** Contraseña a utilizar */
    private es.caib.seycon.ng.comu.Password password;
    /** Host de origen de la solicitud de logon */
    Maquina host;
    /**
     * Host donde está el usuario. Puede no ser el host de origen de la
     * solicitud de logon en caso de ser este un terminal server o un servidor
     * web
     */
    Maquina clientHost;
    /** Puerto en el que quedará escuchando el proceso centinela del SSO */
    private int centinelPort;
    /** Tarjeta a utilizar */
    private String cardNumber;
    /** Celda a solicitar al usuario */
    private String cell;
    /** Valor introducido por el usuario */
    private String value;
    /** Timestamp del desafio */
    private java.sql.Timestamp timeStamp;
    /** Versión del cliente */
    private int clientVersion;
    /** Domini kerberos */
    private String kerberosDomain;
    /** Identificador llarg */
    private String challengeId;
    /** Contexte kerberos */
    private GSSContext kerberosContext;

    public GSSContext getKerberosContext() {
        return kerberosContext;
    }

    public void setKerberosContext(GSSContext kerberosContext) {
        this.kerberosContext = kerberosContext;
    }

    public String getKerberosDomain() {
        return kerberosDomain;
    }

    public void setKerberosDomain(String kerberosDomain) {
        this.kerberosDomain = kerberosDomain;
    }

    public String getChallengeId() {
        return challengeId;
    }

    public void setChallengeId(String challengeId) {
        this.challengeId = challengeId;
    }

    /**
     * Constructor
     */
    public Challenge() {
    }

    public void setClientVersion(int clientVersion) {
        this.clientVersion = clientVersion;
    }

    public int getClientVersion() {
        return clientVersion;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public String getCell() {
        return cell;
    }

    public void setPassword(es.caib.seycon.ng.comu.Password password) {
        this.password = password;
    }

    public es.caib.seycon.ng.comu.Password getPassword() {
        return password;
    }

    public void setCentinelPort(int centinelPort) {
        this.centinelPort = centinelPort;
    }

    public int getCentinelPort() {
        return centinelPort;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setTimeStamp(java.sql.Timestamp timeStamp) {
        this.timeStamp = timeStamp;
    }

    public java.sql.Timestamp getTimeStamp() {
        return timeStamp;
    }
    

    private String domain;
    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
    
    /**
     * Close old sessions
     * 
     */
    boolean closeOldSessions;
    
    /**
     * Silent mode
     * 
     */
    boolean silent;
	public boolean isCloseOldSessions()
	{
		return closeOldSessions;
	}

	public void setCloseOldSessions(boolean closeOldSessions)
	{
		this.closeOldSessions = closeOldSessions;
	}

	public boolean isSilent()
	{
		return silent;
	}

	public void setSilent(boolean silent)
	{
		this.silent = silent;
	}
}
