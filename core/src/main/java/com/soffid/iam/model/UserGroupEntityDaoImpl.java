// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package com.soffid.iam.model;

import com.soffid.iam.api.Audit;
import com.soffid.iam.api.GroupUser;
import com.soffid.iam.api.Task;
import com.soffid.iam.model.AuditEntity;
import com.soffid.iam.model.GroupEntity;
import com.soffid.iam.model.MailListGroupMemberEntity;
import com.soffid.iam.model.RoleDependencyEntity;
import com.soffid.iam.model.RoleEntity;
import com.soffid.iam.model.RoleGroupEntity;
import com.soffid.iam.model.TaskEntity;
import com.soffid.iam.model.UserEntity;
import com.soffid.iam.model.UserGroupEntity;
import com.soffid.iam.sync.engine.TaskHandler;
import com.soffid.iam.utils.ExceptionTranslator;
import com.soffid.iam.utils.Security;

import es.caib.seycon.ng.PrincipalStore;
import es.caib.seycon.ng.comu.AccountType;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.ng.model.*;

import java.security.Principal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;

/**
 * @see es.caib.seycon.ng.model.UsuariGrupEntity
 */
public class UserGroupEntityDaoImpl extends com.soffid.iam.model.UserGroupEntityDaoBase {

    private void auditarUsuariGrup(String accio, String codiUsuariAuditat, String codiGrup) {
        String codiUsuari = Security.getCurrentAccount();
        Audit auditoria = new Audit();
        auditoria.setAction(accio);
        auditoria.setUser(codiUsuariAuditat);
        auditoria.setGroup(codiGrup);
        auditoria.setAuthor(codiUsuari);
        auditoria.setObject("SC_USUGRU"); //$NON-NLS-1$

        AuditEntity auditoriaEntity = getAuditEntityDao().auditToEntity(auditoria);
        getAuditEntityDao().create(auditoriaEntity);
    }

    public void update(UserGroupEntity usuariGrup) {
        try {

            if (usuariGrup.getGroup().getObsolete() != null && usuariGrup.getGroup().getObsolete().compareTo("S") == 0) { //$NON-NLS-1$
                throw new SeyconException(String.format(Messages.getString("UserGroupEntityDaoImpl.0"), usuariGrup.getGroup().getName()));
            }
            if (usuariGrup.getUser().getUserName().equals(Security.getCurrentUser())) {
                throw new SeyconException(Messages.getString("UserGroupEntityDaoImpl.1")); //$NON-NLS-1$
            }

            createMailTask(usuariGrup);

            super.update(usuariGrup);
            TaskEntity tasque = getTaskEntityDao().newTaskEntity();
            tasque.setDate(new Timestamp(System.currentTimeMillis()));
            tasque.setTransaction(TaskHandler.UPDATE_USER);
            tasque.setUser(usuariGrup.getUser().getUserName());
            getTaskEntityDao().create(tasque);
            tasque = getTaskEntityDao().newTaskEntity();
            tasque.setDate(new Timestamp(System.currentTimeMillis()));
            tasque.setTransaction(TaskHandler.UPDATE_GROUP);
            tasque.setGroup(usuariGrup.getGroup().getName());
            getTaskEntityDao().create(tasque);
            getSession(false).flush();

            // NOTA: en teoría no se llama a este método
            // Herencia de Roles: propagamos los roles heredados por el grupo (y
            // sus subgrupos)
            HashSet rolsAPropagar = new HashSet();
            Collection rolsAtorgatsGrupISubgrups = getRolsAtorgatsGrupIParesGrup(usuariGrup.getGroup());
            if (rolsAtorgatsGrupISubgrups != null)
                rolsAPropagar.addAll(rolsAtorgatsGrupISubgrups);
            // Propagamos los roles: (creamos las tareas)
            propagarRolsAtorgatsGrups(rolsAPropagar);

            getSession(false).flush();
            auditarUsuariGrup("U", usuariGrup.getUser().getUserName(), usuariGrup.getGroup().getName()); //$NON-NLS-1$
        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);
            throw new SeyconException(String.format(Messages.getString("UserGroupEntityDaoImpl.2"), 
            		usuariGrup.getUser().getUserName(), usuariGrup.getGroup().getName(), message),
            		e);
        }
    }

    public void create(com.soffid.iam.model.UserGroupEntity usuariGrup) throws RuntimeException {
        try {

            if (usuariGrup.getGroup().getObsolete() != null && usuariGrup.getGroup().getObsolete().compareTo("S") == 0) { //$NON-NLS-1$
                throw new SeyconException(String.format(Messages.getString("UserGroupEntityDaoImpl.3"), usuariGrup.getGroup().getName())); //$NON-NLS-1$
            }

            if (usuariGrup.getUser().getUserName().equals(Security.getCurrentUser())) {
                throw new SeyconException(Messages.getString("UserGroupEntityDaoImpl.4")); //$NON-NLS-1$
            }

            super.create(usuariGrup);
            getSession(false).flush();

            createMailTask(usuariGrup);
            // Herencia de Roles: propagamos los roles heredados por el grupo (y
            // de sus padres)
            HashSet rolsAPropagar = new HashSet();
            Collection rolsAtorgatsGrupIPares = getRolsAtorgatsGrupIParesGrup(usuariGrup.getGroup());
            if (rolsAtorgatsGrupIPares != null)
                rolsAPropagar.addAll(rolsAtorgatsGrupIPares);
            // Propagamos los roles: (creamos las tareas)
            propagarRolsAtorgatsGrups(rolsAPropagar);

            TaskEntity tasque = getTaskEntityDao().newTaskEntity();
            tasque.setDate(new Timestamp(System.currentTimeMillis()));
            tasque.setTransaction(TaskHandler.UPDATE_USER);
            tasque.setUser(usuariGrup.getUser().getUserName());
            getTaskEntityDao().create(tasque);
            tasque = getTaskEntityDao().newTaskEntity();
            tasque.setDate(new Timestamp(System.currentTimeMillis()));
            tasque.setTransaction(TaskHandler.UPDATE_GROUP);
            tasque.setGroup(usuariGrup.getGroup().getName());
            getTaskEntityDao().create(tasque);
            getSession(false).flush();
            auditarUsuariGrup("C", usuariGrup.getUser().getUserName(), usuariGrup.getGroup().getName()); //$NON-NLS-1$
        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);
            throw new SeyconException(String.format(Messages.getString("UserGroupEntityDaoImpl.5"),
            		usuariGrup.getUser() == null ? "<Unknown>": usuariGrup.getUser().getUserName(), 
            		usuariGrup.getGroup() == null ? "<Unknown>": usuariGrup.getGroup().getName(), message),
            		e);
        }
    }

    public void remove(com.soffid.iam.model.UserGroupEntity usuariGrup) throws RuntimeException {
        try {
            if (usuariGrup.getUser().getUserName().equals(Security.getCurrentUser())) {
                throw new SeyconException(Messages.getString("UserGroupEntityDaoImpl.6")); //$NON-NLS-1$
            }
            
            createMailTask(usuariGrup);
            
            super.remove(usuariGrup);
            getSession(false).flush();


            if (Hibernate.isInitialized(usuariGrup.getUser()) && Hibernate.isInitialized(usuariGrup.getUser().getSecondaryGroups()))
            {
            	usuariGrup.getUser().getSecondaryGroups().remove(usuariGrup);
            }


            if (Hibernate.isInitialized(usuariGrup.getGroup()) && Hibernate.isInitialized(usuariGrup.getGroup().getSecondaryGroupUsers()))
            {
            	usuariGrup.getGroup().getSecondaryGroupUsers().remove(usuariGrup);
            }

            String codiUsuari = usuariGrup.getUser().getUserName();
            String codiGrup = usuariGrup.getGroup().getName();
            TaskEntity tasque = getTaskEntityDao().newTaskEntity();
            tasque.setDate(new Timestamp(System.currentTimeMillis()));
            tasque.setTransaction(TaskHandler.UPDATE_USER);
            tasque.setUser(usuariGrup.getUser().getUserName());
            getTaskEntityDao().create(tasque);
            tasque = getTaskEntityDao().newTaskEntity();
            tasque.setDate(new Timestamp(System.currentTimeMillis()));
            tasque.setTransaction(TaskHandler.UPDATE_GROUP);
            tasque.setGroup(usuariGrup.getGroup().getName());
            getTaskEntityDao().create(tasque);

            // Herencia de Roles: propagamos los roles heredados por el grupo (y
            // sus subgrupos)
            HashSet rolsAPropagar = new HashSet();
            Collection rolsAtorgatsGrupISubgrups = getRolsAtorgatsGrupIParesGrup(usuariGrup.getGroup());
            if (rolsAtorgatsGrupISubgrups != null)
                rolsAPropagar.addAll(rolsAtorgatsGrupISubgrups);
            // Propagamos los roles: (creamos las tareas)
            propagarRolsAtorgatsGrups(rolsAPropagar);

            getSession(false).flush();
            auditarUsuariGrup("D", codiUsuari, codiGrup); //$NON-NLS-1$
        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);
            throw new SeyconException(String.format(Messages.getString("UserGroupEntityDaoImpl.7"), 
            		usuariGrup.getUser().getUserName(), usuariGrup.getGroup().getName(), message),
            		e);
        }
    }

    public void toGroupUser(com.soffid.iam.model.UserGroupEntity sourceEntity, com.soffid.iam.api.GroupUser targetVO) {
        super.toGroupUser(sourceEntity, targetVO);
        toUsuariGrupCustom(sourceEntity, targetVO);
    }

    /**
     * Mètode que omple els valors del VO en la transformació. Per cada nou camp
     * que s'afegeixi al VO, s'ha d'implementar el codi corresponent aquí.
     * 
     * @param sourceEntity
     * @param targetVO
     */
    private void toUsuariGrupCustom(com.soffid.iam.model.UserGroupEntity sourceEntity, com.soffid.iam.api.GroupUser targetVO) {
    	targetVO.setUser(sourceEntity.getUser().getUserName());
    	targetVO.setUserId(sourceEntity.getUser().getId());
        targetVO.setGroup(sourceEntity.getGroup().getName());
        targetVO.setGroupDescription(sourceEntity.getGroup().getDescription());
        targetVO.setGroupId(sourceEntity.getGroup().getId());
        UserEntity user = sourceEntity.getUser();
        String nomComplet = user.getFullName();
        targetVO.setFullName(nomComplet);

        targetVO.setAttributes(new HashMap<String, Object>());
		Map<String, Object> attributes = targetVO.getAttributes();
		for (UserGroupAttributeEntity att : sourceEntity.getAttributes()) {
			if (att.getMetadata().getMultiValued() != null && att.getMetadata().getMultiValued().booleanValue())
			{
				LinkedList<Object> r = (LinkedList<Object>) attributes.get(att.getMetadata().getName());
				if (r == null)
				{
					r = new LinkedList<Object>();
					attributes.put(att.getMetadata().getName(), r);
				}
				r.add(att.getObjectValue());
			}
			else
			{
				attributes.put(att.getMetadata().getName(),att.getObjectValue());
			}
		}
		for (Object o: attributes.values())
		{
			if (o != null && o instanceof List) Collections.sort((List) o);
		}

    }

    /**
     * @see es.caib.seycon.ng.model.UsuariGrupEntityDao#toUsuariGrup(es.caib.seycon.ng.model.UsuariGrupEntity)
     */
    public com.soffid.iam.api.GroupUser toGroupUser(final com.soffid.iam.model.UserGroupEntity entity) {
        // @todo verify behavior of toUsuariGrup
        GroupUser usuariGrup = super.toGroupUser(entity);
        return usuariGrup;
    }

    /**
     * Retrieves the entity object that is associated with the specified value
     * object from the object store. If no such entity object exists in the
     * object store, a new, blank entity is created
     */
    private com.soffid.iam.model.UserGroupEntity loadUsuariGrupEntityFromUsuariGrup(com.soffid.iam.api.GroupUser usuariGrup) {
        com.soffid.iam.model.UserGroupEntity usuariGrupEntity = null;
        if (usuariGrup.getId() != null) {
            usuariGrupEntity = load(usuariGrup.getId());
        }
        if (usuariGrupEntity == null) {
            usuariGrupEntity = newUserGroupEntity();
        }
        return usuariGrupEntity;
    }

    /**
     * @see es.caib.seycon.ng.model.UsuariGrupEntityDao#usuariGrupToEntity(es.caib.seycon.ng.comu.UsuariGrup)
     */
    public com.soffid.iam.model.UserGroupEntity groupUserToEntity(com.soffid.iam.api.GroupUser usuariGrup) {
        // @todo verify behavior of usuariGrupToEntity
        com.soffid.iam.model.UserGroupEntity entity = this.loadUsuariGrupEntityFromUsuariGrup(usuariGrup);
        this.groupUserToEntity(usuariGrup, entity, true);
        return entity;
    }

    /**
     * Mètode que omple els valors del Entity en la transformació. Per cada nou
     * camp que s'afegeixi al Entity, s'ha d'implementar el codi corresponent
     * aquí.
     * 
     * @param sourceVO
     * @param targetEntity
     */
    private void usuariGrupToEntityCustom(com.soffid.iam.api.GroupUser sourceVO, com.soffid.iam.model.UserGroupEntity targetEntity) {
        UserEntity usuari = null;
        if (sourceVO.getUser() != null)
        	usuari = getUserEntityDao().findByUserName(sourceVO.getUser());
        if (usuari == null && sourceVO.getUserId() != null)
        	usuari = getUserEntityDao().load(sourceVO.getUserId());
        if (usuari == null) {
            throw new SeyconException(String.format(Messages.getString("UserGroupEntityDaoImpl.8"), sourceVO.getUser()));
        }
        targetEntity.setUser(usuari);
        GroupEntity grup = null;
        if (sourceVO.getGroup() != null)
        	grup = getGroupEntityDao().findByName(sourceVO.getGroup());
        if (grup != null && sourceVO.getGroupId() != null)
        	grup = getGroupEntityDao().load(sourceVO.getGroupId());
        if (grup == null) {
            throw new SeyconException(String.format(Messages.getString("UserGroupEntityDaoImpl.9"), sourceVO.getGroup()));
        } else {
            targetEntity.setGroup(grup);
        }
    }

    /**
     * @see es.caib.seycon.ng.model.UsuariGrupEntityDao#usuariGrupToEntity(es.caib.seycon.ng.comu.UsuariGrup,
     *      es.caib.seycon.ng.model.UsuariGrupEntity)
     */
    public void groupUserToEntity(com.soffid.iam.api.GroupUser sourceVO, com.soffid.iam.model.UserGroupEntity targetEntity, boolean copyIfNull) {
        super.groupUserToEntity(sourceVO, targetEntity, copyIfNull);
        usuariGrupToEntityCustom(sourceVO, targetEntity);
    }

    private Collection getParesGrup(GroupEntity grupAnalitzar) {

        Collection totsPares = new HashSet();
        GroupEntity pare = grupAnalitzar.getParent();
        while (pare != null) {
            totsPares.add(pare);
            pare = pare.getParent();
        }

        return totsPares;
    }

    private Collection getRolsContingutsPerPropagar(RoleEntity rol) {
        // Si rol té atorgats d'altres rols (és conetnidor dele rols)
        // s'han de propagar tots els rols que conté (per assignar-lo a
        // l'usuari)
        HashSet rolsPropagar = new HashSet();
        // Sólo hemos de propagar a los usuarios que tienen el rol contenedor
        // con valor de dominio correspondiente (o si es SENSE_DOMINI o a
        // qualque valor)
        // Montamos un FIFO De roles (puede haber cadena de
        // herencia A atorgat B[sense domini] atorgat C ... atorgat Z[amb
        // domini]
        LinkedList rolsAnalitzar = new LinkedList(); // FIFO
        rolsAnalitzar.add(rol);
        RoleEntity rolActual = null;
        while ((rolActual = (RoleEntity) rolsAnalitzar.poll()) != null) {
            Collection socContenidor = rolActual.getContainedRoles();

            if (socContenidor != null)
                for (Iterator it = socContenidor.iterator(); it.hasNext(); ) {
                RoleDependencyEntity associacio = (RoleDependencyEntity) it.next();
                RoleEntity rolContingut = associacio.getContained();
                rolsPropagar.add(rolContingut);
                rolsAnalitzar.add(rolContingut);
            }
        }
        return rolsPropagar;
    }

    /**
     * Obtiene dado un grupo, los roles otorgados al grupo (y los roles
     * otorgados a los padres del grupo indicado)
     * 
     * @param grup
     * @return
     */
    private Collection getRolsAtorgatsGrupIParesGrup(GroupEntity grup) {

        // 1) Obtenim els grups pares del grup
        HashSet totGrup = new HashSet();
        totGrup.add(grup);
        Collection paresGrup = getParesGrup(grup);
        totGrup.addAll(paresGrup);

        // 2) Obtenim els rols atorgats al grup i els grups pare
        HashSet totRolAtorgatGrup = new HashSet();
        for (Iterator it = totGrup.iterator(); it.hasNext(); ) {
            Object obj = it.next();
            if (obj != null) {
                GroupEntity g = (GroupEntity) obj;
                Collection rolsAtorgatsG = g.getGrantedRoles();
                if (rolsAtorgatsG != null) totRolAtorgatGrup.addAll(rolsAtorgatsG);
            }
        }

        // 3) Obtenim els rols atorgats als rols:
        HashSet rolsPropagar = new HashSet();
        for (Iterator it = totRolAtorgatGrup.iterator(); it.hasNext(); ) {
            Object obj = it.next();
            if (obj != null) {
                RoleGroupEntity rolgrup = (RoleGroupEntity) obj;
                rolsPropagar.add(rolgrup.getGrantedRole());
                Collection rolsAtorgatsRol = getRolsContingutsPerPropagar(rolgrup.getGrantedRole());
                if (rolsAtorgatsRol != null) rolsPropagar.addAll(rolsAtorgatsRol);
            }
        }

        return new ArrayList(rolsPropagar);
    }

    private void propagarRolsAtorgatsGrups(Collection rolsPropagar) {
        // Propaguem els rols
        if (rolsPropagar != null) {
            for (Iterator it = rolsPropagar.iterator(); it.hasNext(); ) {
                Object obj = it.next();
                if (obj != null) {
                    RoleEntity role = (RoleEntity) obj;
                    Task updateRole = new Task();
                    updateRole.setTransaction("UpdateRole");
                    updateRole.setTaskDate(Calendar.getInstance());
                    updateRole.setStatus("P");
                    updateRole.setRole(role.getName());
                    updateRole.setDatabase(role.getSystem().getName());
                    TaskEntity tasca = getTaskEntityDao().taskToEntity(updateRole);
                    getTaskEntityDao().create(tasca);
                }
            }
        }
    }

    public void create(Collection entities) {
        if (entities != null)
            for (Iterator it = entities.iterator(); it.hasNext(); ) {
            Object obj = it.next();
            if (obj instanceof UserGroupEntity) {
                UserGroupEntity entity = (UserGroupEntity) obj;
                this.create(entity);
            }
        }
    }

    public void update(Collection entities) {
        if (entities != null)
            for (Iterator it = entities.iterator(); it.hasNext(); ) {
            Object obj = it.next();
            if (obj instanceof UserGroupEntity) {
                UserGroupEntity entity = (UserGroupEntity) obj;
                this.update(entity);
            }
        }
    }

    public void remove(Collection entities) {
        if (entities != null) {
        	List<Object> uge = new LinkedList(entities);
            for (Iterator it = uge.iterator(); it.hasNext(); ) {
	            Object obj = it.next();
	            if (obj instanceof UserGroupEntity) {
	                UserGroupEntity entity = (UserGroupEntity) obj;
	                this.remove(entity);
	            }
	        }
        }
    }


    private void createMailTask(UserGroupEntity ug) throws InternalErrorException {
        //  Now, updates any mail lists the users belongs
        for (MailListGroupMemberEntity lce : ug.getGroup().getMailLists()) {
            getEmailListEntityDao().generateUpdateTasks(lce.getMailList());
        }
    }
    
}
