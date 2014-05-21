/*
 * GroupInfo.java
 *
 * Created on May 8, 2000, 11:17 AM
 */

package es.caib.seycon.ng.sync.intf;

import java.text.*;

/**
 * Información acerca de un grupo. Corresponde con la tabla SC_GRUPS
 * 
 * @author $Author: u07286 $
 * @version $Revision: 1.1.2.1 $
 * @see GroupMgr
 * @see Server
 */

// $Log: GroupInfo.java,v $
// Revision 1.1.2.1  2012-09-25 08:57:19  u07286
// Multiples canvis
//
// Revision 1.1.2.1  2012-05-16 10:33:38  u07286
// Reestructuració de paquets seycon antics
//
// Revision 1.4  2010-06-08 13:01:25  u88683
// A�adimos atributo de grupo Secci�n Presupuestaria en la informaci�n del grupo
//
// Revision 1.3  2010-03-15 10:23:31  u07286
// Movido a tag HEAD
//
// Revision 1.2.2.3  2009-11-27 08:25:56  u91940
// PJR
//
// Revision 1.2.2.2  2009-11-24 07:46:00  u88683
// Añadimos un nuevo campo a GroupInfo : GroupUOrgTypeCode  que es el código del tipo de unidad organizativa, para algunos agentes
//
// Revision 1.2.2.1  2009-09-28 10:47:48  u07286
// Depurada gestión de roles heredados
//
// Revision 1.2 2008-03-13 08:38:34 u07286
// Creado tipo de agente "local"
// Creado agente JBPM
// Creado entorno de test
//
// Revision 1.1 2007-09-06 12:51:10 u89559
// [T252]
//
// Revision 1.3 2004-03-15 12:08:03 u07286
// Conversion UTF-8
//
// Revision 1.2 2004/03/15 11:57:46 u07286
// Agregada documentacion JavaDoc
//
public class GroupInfo extends Object implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -626255116218142261L;

    /** Crea un nuevo GroupInfo */
    public GroupInfo() {
    }

    /** Nombre del grupo (GRU_CODI) */
    public String Name;
    /** Descripción (GRU_DESCRI) */
    public String Description;
    /** Servidor ofimático (GRU_IDMAQ) */
    public String GroupServer;
    /** Unidad ofimática a asignar a los usuarios (GRU_UNIOFI) */
    public String GroupDrive;
    /** Código del tipo de unidad organizativa del grupo*/
    public String GroupUOrgTypeCode;
    /** Código de la Sección Presupuestaria */
    public String SeccionPresupuestaria;
    /**
     * Quota
     * 
     * @deprecated
     */
    public long GroupQuota;
    /**
     * Grupo padre
     * 
     */
    public String parent;

    /**
     * Volvar los datos
     * 
     * @param stream
     *                corriente de salida
     */
    public void dump(java.io.PrintStream stream) {
        stream.println(Messages.getString("GroupInfo.name") + Name); //$NON-NLS-1$
        stream.println(Messages.getString("GroupInfo.description") + Description); //$NON-NLS-1$
        stream.println(Messages.getString("GroupInfo.server") + GroupServer); //$NON-NLS-1$
        stream.println(Messages.getString("GroupInfo.drive") + GroupDrive); //$NON-NLS-1$
    }

    public boolean equals(Object obj) {
        if (obj instanceof GroupInfo)
        {
            GroupInfo g2 = (GroupInfo) obj;
            if (Name == null) return g2 == null;
            return Name.equals(g2);
        }
        else
            return false;
    }

	/**
	 * @return the name
	 */
	public synchronized String getName() {
		return Name;
	}

	/**
	 * @param name the name to set
	 */
	public synchronized void setName(String name) {
		Name = name;
	}

	/**
	 * @return the description
	 */
	public synchronized String getDescription() {
		return Description;
	}

	/**
	 * @param description the description to set
	 */
	public synchronized void setDescription(String description) {
		Description = description;
	}

	/**
	 * @return the groupServer
	 */
	public synchronized String getGroupServer() {
		return GroupServer;
	}

	/**
	 * @param groupServer the groupServer to set
	 */
	public synchronized void setGroupServer(String groupServer) {
		GroupServer = groupServer;
	}

	/**
	 * @return the groupDrive
	 */
	public synchronized String getGroupDrive() {
		return GroupDrive;
	}

	/**
	 * @param groupDrive the groupDrive to set
	 */
	public synchronized void setGroupDrive(String groupDrive) {
		GroupDrive = groupDrive;
	}

	/**
	 * @return the groupUOrgTypeCode
	 */
	public synchronized String getGroupUOrgTypeCode() {
		return GroupUOrgTypeCode;
	}

	/**
	 * @param groupUOrgTypeCode the groupUOrgTypeCode to set
	 */
	public synchronized void setGroupUOrgTypeCode(String groupUOrgTypeCode) {
		GroupUOrgTypeCode = groupUOrgTypeCode;
	}

	/**
	 * @return the groupQuota
	 */
	public synchronized long getGroupQuota() {
		return GroupQuota;
	}

	/**
	 * @param groupQuota the groupQuota to set
	 */
	public synchronized void setGroupQuota(long groupQuota) {
		GroupQuota = groupQuota;
	}

	/**
	 * @return the parent
	 */
	public synchronized String getParent() {
		return parent;
	}

	/**
	 * @param parent the parent to set
	 */
	public synchronized void setParent(String parent) {
		this.parent = parent;
	}

	public String getSeccionPresupuestaria() {
		return SeccionPresupuestaria;
	}

	public void setSeccionPresupuestaria(String seccionPresupuestaria) {
		SeccionPresupuestaria = seccionPresupuestaria;
	}
    
}
