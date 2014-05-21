package es.caib.seycon.ng.utils;

import java.security.Principal;

public class RunAsPrincipal implements Principal {
    String name;
    
    public RunAsPrincipal(String name) {
        super();
        this.name = name;
    }


    public String getName() {
        return name;
    }

}
