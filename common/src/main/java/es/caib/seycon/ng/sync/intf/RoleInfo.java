// Copyright (c) 2000 Govern  de les Illes Balears
package es.caib.seycon.ng.sync.intf;

/**
 * Información relacionada con un rol (SC_ROLES)
 * <P>
 * 
 * @author $Author: u07286 $
 * @version $Revision: 1.1.2.1 $
 */

// $Log
public class RoleInfo extends Object implements java.io.Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -7108671664238946923L;
    /** código del rol */
    public String name;
    /** descripción */
    public String description;
    /** código de la aplicación */
    public String applicationName;
    /** descripción de la aplicación (NAME)*/
    public String applicationDescription;
    /** true si es un rol por defecto */
    public boolean defaultRole;
    /** true si se halla protegido por contraseña */
    public boolean passwordProtected;
    /** base de datos en la que se define (CODI) */
    public String db;
    /** tipus de domini */
    public String tipusDomini;
    /** valor de domini */
    public String valorDeDomini;

    /**
     * Constructor
     */
    public RoleInfo() {
    }

    /**
     * Constructor completo
     */
    public RoleInfo(String name, String description, String applicationName,
            String applicationDescription, boolean defaultRole,
            boolean passwordProtected, String db, String tipusDomini,
            String valorDeDomini) {
        super();
        this.name = name;
        this.description = description;
        this.applicationName = applicationName;
        this.applicationDescription = applicationDescription;
        this.defaultRole = defaultRole;
        this.passwordProtected = passwordProtected;
        this.db = db;
        this.tipusDomini = tipusDomini;
        this.valorDeDomini = valorDeDomini;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getApplicationDescription() {
        return applicationDescription;
    }

    public void setApplicationDescription(String applicationDescription) {
        this.applicationDescription = applicationDescription;
    }

    public boolean isDefaultRole() {
        return defaultRole;
    }

    public void setDefaultRole(boolean defaultRole) {
        this.defaultRole = defaultRole;
    }

    public boolean isPasswordProtected() {
        return passwordProtected;
    }

    public void setPasswordProtected(boolean passwordProtected) {
        this.passwordProtected = passwordProtected;
    }

    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }

    public String getTipusDomini() {
        return tipusDomini;
    }

    public void setTipusDomini(String tipusDomini) {
        this.tipusDomini = tipusDomini;
    }

    public String getValorDeDomini() {
        return valorDeDomini;
    }

    public void setValorDeDomini(String valorDeDomini) {
        this.valorDeDomini = valorDeDomini;
    }

    public boolean equals(Object obj) {
	    if (obj instanceof RoleInfo) {
	        RoleInfo g2 = (RoleInfo) obj;
	        return equalNullable(this.name, g2.name) &&
	             equalNullable(this.db, g2.db);
	    } else
                return false;
	}

    private boolean equalNullable(String n1, String n2) {
        if (n1 == null) {
            return n2 == null; 
        } else {
            return n1.equals(n2);
        }
    }

	public String toString() {//Per a la depuració només (Alejandro)
		return name+"@"+db+">"+applicationName+" domini '"+tipusDomini+"' valor '"+valorDeDomini+"'"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
	}
}
