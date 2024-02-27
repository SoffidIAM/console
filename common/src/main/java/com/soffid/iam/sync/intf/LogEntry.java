/*
 * LogEntry.java
 *
 * Created on May 8, 2000, 11:17 AM
 */
 
package com.soffid.iam.sync.intf;
import java.text.*;
/** 
 * Entrada de registro de acceso
 *
 * @author  $Author: u07286 $
 * @version  $Revision: 1.1.2.3 $
 */

public class LogEntry extends Object implements java.io.Serializable {
	
  public String getSessionId() {
        return SessionId;
    }
    public void setSessionId(String sessionId) {
        SessionId = sessionId;
    }
    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public java.util.Date getDate() {
        return date;
    }
    public void setDate(java.util.Date date) {
        this.date = date;
    }
    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public String getProtocol() {
        return protocol;
    }
    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }
    public String getHost() {
        return host;
    }
    public void setHost(String host) {
        this.host = host;
    }
    public String getClient() {
        return client;
    }
    public void setClient(String client) {
        this.client = client;
    }
    public String getInfo() {
        return info;
    }
    public void setInfo(String info) {
        this.info = info;
    }

private static final long serialVersionUID = 1L;	

  /** Constructor */
  public LogEntry () {
  }
  
  /** entrada de tipo logon */
  public final static int LOGON = 0;
  /** entrada de tipo logoff */
  public final static int LOGOFF = 1;
  /** entrada de tipo acceso denegado (control de acceso) */
  public final static int LOGON_DENIED = 2;
  
  
  /** Identificador de la sesión */
  public String SessionId;
  /** tipo de entrada. @see LogEntry#LOGON @see LogEntry#LOGOFF */
  public int type; // 0 -> Logon, 1 -> Logoff, 2 -> Not_allowed_login
  /** Fecha y hora del registro */
  public java.util.Date date;
  /** Código de usuario */
  public String user;
  /** Servicio utilizado */
  public String protocol;
  /** Máquina que registra la entrada */
  public String host;
  /** Máquina desde donde se entra */
  public String client;
  public String clientName;
  /** Información adicional dependiente del sistema */
  public String info;
  /** Volcado de datos
   * @param stream corriente de salida
   */
  public void dump (java.io.PrintStream stream) {
    if (type == LOGON)
       stream.println ("LOGON"); //$NON-NLS-1$
    else if (type == LOGOFF)
       stream.println ("LOGOFF"); //$NON-NLS-1$
    else if (type == LOGON_DENIED)
        stream.println ("LOGON DENIED");     //$NON-NLS-1$
    else
       stream.println (Messages.getString("LogEntry.Unknown") + //$NON-NLS-1$
        NumberFormat.getInstance().format(type));
    stream.println (Messages.getString("LogEntry.date")+DateFormat.getDateTimeInstance().format(date)); //$NON-NLS-1$
    stream.println (Messages.getString("LogEntry.user")+user); //$NON-NLS-1$
    stream.println (Messages.getString("LogEntry.host")+host); //$NON-NLS-1$
    stream.println (Messages.getString("LogEntry.service")+protocol); //$NON-NLS-1$
    stream.println (Messages.getString("LogEntry.host")+host); //$NON-NLS-1$
    stream.println (Messages.getString("LogEntry.client")+client); //$NON-NLS-1$
    stream.println (Messages.getString("LogEntry.info")+info); //$NON-NLS-1$
  }
public String getClientName() {
	return clientName;
}
public void setClientName(String clientName) {
	this.clientName = clientName;
}
}