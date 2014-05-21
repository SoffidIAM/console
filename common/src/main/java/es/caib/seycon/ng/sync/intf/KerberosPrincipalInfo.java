package es.caib.seycon.ng.sync.intf;

import java.io.Serializable;

import es.caib.seycon.ng.comu.Password;

public class KerberosPrincipalInfo implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String userName;
    private Password password;
    private String principalName;
    private byte [] keytab;
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public Password getPassword() {
        return password;
    }
    public void setPassword(Password password) {
        this.password = password;
    }
    public String getPrincipalName() {
        return principalName;
    }
    public void setPrincipalName(String principalName) {
        this.principalName = principalName;
    }
    public byte[] getKeytab() {
        return keytab;
    }
    public void setKeytab(byte[] keytab) {
        this.keytab = keytab;
    }
    
}
