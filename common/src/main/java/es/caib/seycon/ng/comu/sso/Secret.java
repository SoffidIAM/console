package es.caib.seycon.ng.comu.sso;

import java.io.Serializable;

import es.caib.seycon.ng.comu.Password;


public class Secret implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
	private String name;
    private Password value;
    
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Password getValue() {
        return value;
    }
    public void setValue(Password value) {
        this.value = value;
    }
}
