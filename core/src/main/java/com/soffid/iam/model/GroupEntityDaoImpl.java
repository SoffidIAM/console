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
import com.soffid.iam.api.DomainValue;
import com.soffid.iam.api.Group;
import com.soffid.iam.api.Identity;
import com.soffid.iam.api.Task;
import com.soffid.iam.model.AuditEntity;
import com.soffid.iam.model.GroupEntity;
import com.soffid.iam.model.GroupTypeEntity;
import com.soffid.iam.model.HostEntity;
import com.soffid.iam.model.MailListGroupMemberEntity;
import com.soffid.iam.model.MailListRoleMemberEntity;
import com.soffid.iam.model.RoleDependencyEntity;
import com.soffid.iam.model.RoleEntity;
import com.soffid.iam.model.RoleGroupEntity;
import com.soffid.iam.model.TaskEntity;
import com.soffid.iam.model.UserEntity;
import com.soffid.iam.model.UserGroupEntity;
import com.soffid.iam.model.criteria.CriteriaSearchConfiguration;
import com.soffid.iam.sync.engine.TaskHandler;
import com.soffid.iam.utils.ExceptionTranslator;
import com.soffid.iam.utils.Security;

import es.caib.seycon.ng.PrincipalStore;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.ng.model.*;

import java.security.Principal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @see es.caib.seycon.ng.model.GrupEntity
 */
public class GroupEntityDaoImpl extends
		com.soffid.iam.model.GroupEntityDaoBase {

	private void auditarGrup(String accio, String codiGrup) {
		String codiUsuari = Security.getCurrentAccount();
		Audit auditoria = new Audit();
		auditoria.setAction(accio);
		auditoria.setGroup(codiGrup);
		auditoria.setAuthor(codiUsuari);
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"dd/MM/yyyy kk:mm:ss"); //$NON-NLS-1$
		auditoria.setAdditionalInfo(dateFormat.format(GregorianCalendar.getInstance().getTime()));
		auditoria.setObject("SC_GRUP"); //$NON-NLS-1$
		AuditEntity auditoriaEntity = getAuditEntityDao().auditToEntity(auditoria);
		getAuditEntityDao().create(auditoriaEntity);
	}

	public void create(com.soffid.iam.model.GroupEntity grup) throws RuntimeException {
		try {
			super.create(grup);
			getSession(false).flush();
			
			// PROPAGAMOS LOS ROLES OTORGADOS A LOS GRUPOS PADRE 
			//  el grupo todavía no existe: no tiene roles otorgados
			if (grup.getParent() != null) {
				// Herencia de Roles: propagamos los roles heredados por el grupo (y de sus padres)
				HashSet rolsAPropagar =new HashSet();
				Collection rolsAtorgatsGrupIPares = getRolsAtorgatsGrupIParesGrup(grup);
				if (rolsAtorgatsGrupIPares!=null) rolsAPropagar.addAll(rolsAtorgatsGrupIPares);
				// Propagamos los roles: (creamos las tareas)
				propagarRolsAtorgatsGrups(rolsAPropagar);						
			}
			
			
                        TaskEntity tasque = getTaskEntityDao().newTaskEntity();
                        tasque.setDate(new Timestamp(System.currentTimeMillis()));
                        tasque.setTransaction(TaskHandler.UPDATE_GROUP);
                        tasque.setGroup(grup.getName());
                        getTaskEntityDao().create(tasque);
                        if (grup.getHomeServer() != null) {
                            tasque = getTaskEntityDao().newTaskEntity();
                            tasque.setDate(new Timestamp(System.currentTimeMillis()));
                            tasque.setTransaction(TaskHandler.CREATE_FOLDER);
                            tasque.setFolder(grup.getName());
                            tasque.setFolderType("G"); //$NON-NLS-1$
                            getTaskEntityDao().create(tasque);
                        }
			auditarGrup("C", grup.getName()); //$NON-NLS-1$
                        getSession().flush();
		} catch (Throwable e) {
			String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("GroupEntityDaoImpl.0"), grup.getName(), message));
		}
	}

	public void remove(com.soffid.iam.model.GroupEntity grup) throws RuntimeException { //En principi NO ES FA MAI
		try {
			String codiGrup = grup.getName();
			
			// Herencia de Roles: propagamos los roles heredados por el grupo (y de sus padres)
			HashSet rolsAPropagar =new HashSet();
			Collection rolsAtorgatsGrupIPares = getRolsAtorgatsGrupIParesGrup(grup);
			if (rolsAtorgatsGrupIPares!=null) rolsAPropagar.addAll(rolsAtorgatsGrupIPares);
			// Propagamos los roles: (creamos las tareas)
			propagarRolsAtorgatsGrups(rolsAPropagar);						

            TaskEntity tasque;
            tasque = getTaskEntityDao().newTaskEntity();
            tasque.setDate(new Timestamp(System.currentTimeMillis()));
            tasque.setTransaction(TaskHandler.UPDATE_GROUP);
            tasque.setGroup(grup.getName());
            getTaskEntityDao().create(tasque);
            if (grup.getHomeServer() != null) {
                tasque = getTaskEntityDao().newTaskEntity();
                tasque.setDate(new Timestamp(System.currentTimeMillis()));
                tasque.setTransaction(TaskHandler.CREATE_FOLDER);
                tasque.setFolder(grup.getName());
                tasque.setFolderType("G"); //$NON-NLS-1$
                getTaskEntityDao().create(tasque);
            }
			auditarGrup("D", codiGrup); //$NON-NLS-1$

			getUserGroupEntityDao().remove( grup.getSecondaryGroupUsers());
			getRoleGroupEntityDao().remove(grup.getGrantedRoles());
			getGroupAttributeEntityDao().remove(grup.getAttributes());
			getMailListGroupMemberEntityDao().remove(grup.getMailLists());
			getMailListRoleMemberEntityDao().remove(grup.getRoleScopeMailLists());
			getRoleAccountEntityDao().remove(grup.getUsersRoles());
			for (AuditEntity aud: grup.getAudit())
			{
				aud.setGroup(null);
			}
			super.remove(grup);
			getSession(false).flush();
		} catch (Throwable e) {
			String message = ExceptionTranslator.translate(e);

			throw new SeyconException(String.format(Messages.getString("GroupEntityDaoImpl.1"), grup.getName(), message));
		}
	}

	public void update(com.soffid.iam.model.GroupEntity grup) throws RuntimeException {
		try {
			// Buscamos el padre anterior (para ver si cambia)
			GroupEntity old = load(grup.getId());
			GroupEntity pareGrupAbansUpdate = old.getParent();
			Collection pares = new HashSet();
			pares.add(pareGrupAbansUpdate);
			pares.add(grup.getParent());
			
			// Actualizamos al grupo
			super.update(grup);
			getSession(false).flush();
			
			// PROPAGAMOS LOS ROLES OTORGADOS A LOS PADRES DEL GRUPO
			// Si n'hi ha més d'un pare: ha canviat
			// O si només hi ha un pare (i és nou o ha desaparegut)
			if (pares.size() > 1 || (pareGrupAbansUpdate == null && grup.getParent() != null) || (pareGrupAbansUpdate != null && grup.getParent() == null)) {

				// Herencia de Roles: propagamos los roles heredados por el grupo (y de sus padres)
				HashSet rolsAPropagar =new HashSet();
				for (Iterator it = pares.iterator(); it.hasNext(); ) {
                    Object obj = it.next();
                    if (obj != null) {
                        GroupEntity g = (GroupEntity) obj;
                        Collection rolsAtorgatsGrupIPares = getRolsAtorgatsGrupIParesGrup(g);
                        if (rolsAtorgatsGrupIPares != null) rolsAPropagar.addAll(rolsAtorgatsGrupIPares);
                    }
                }
				// Propagamos los ROLES (del grupo padre antiguo y nuevo): (creamos las tareas)
				propagarRolsAtorgatsGrups(rolsAPropagar);
				// Propagamos LOS USUARIOS: de este grupo
				Collection usuarisPropagar = getUsuarisPertanyenGrup(grup.getName());
				if (usuarisPropagar!=null) {
					propagarUsuarisGrup(usuarisPropagar);
				}
				
				
			}
                        TaskEntity tasque;
                        tasque = getTaskEntityDao().newTaskEntity();
                        tasque.setDate(new Timestamp(System.currentTimeMillis()));
                        tasque.setTransaction(TaskHandler.UPDATE_GROUP);
                        tasque.setGroup(grup.getName());
                        getTaskEntityDao().create(tasque);
                        if (grup.getHomeServer() != null ? old.getHomeServer() == null || !old.getHomeServer().getId().equals(grup.getHomeServer().getId()) : old.getHomeServer() != null) {
                            tasque = getTaskEntityDao().newTaskEntity();
                            tasque.setDate(new Timestamp(System.currentTimeMillis()));
                            tasque.setTransaction(TaskHandler.CREATE_FOLDER);
                            tasque.setFolder(grup.getName());
                            tasque.setFolderType("G"); //$NON-NLS-1$
                            getTaskEntityDao().create(tasque);
                        }
			auditarGrup("U", grup.getName()); //$NON-NLS-1$
			getSession().flush();
		} catch (Throwable e) {
			String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("GroupEntityDaoImpl.2"), grup.getName(), message));
		}
	}

	public void toGroup(com.soffid.iam.model.GroupEntity sourceEntity, com.soffid.iam.api.Group targetVO) {
		super.toGroup(sourceEntity, targetVO);
		toGrupCustom(targetVO, sourceEntity);
	}

	private void toGrupCustom(Group grup, final GroupEntity entity) {
		String organitzatiu = entity.getOrganizational();
		if (organitzatiu != null && organitzatiu.trim().compareTo("") != 0) { //$NON-NLS-1$
			grup.setOrganizational(new Boolean(organitzatiu.trim().compareToIgnoreCase("S") == 0 ? true : false)); //$NON-NLS-1$
		} else {
			grup.setOrganizational(new Boolean(false));
		}

		GroupEntity grupPare = entity.getParent();
		if (grupPare != null) {
			String codiPare = grupPare.getName();
			grup.setParentGroup(codiPare);
		}

		HostEntity servidorOfimatic = entity.getHomeServer();
		if (servidorOfimatic != null) {
			String nomServidorOfimatic = servidorOfimatic.getName();
			grup.setDriveServerName(nomServidorOfimatic);
		}
		

		GroupEntity grupEntity = entity; //findByCodi(entity.getCodi()); //¿para qué lo cargamos si lo tenemos?
		GroupTypeEntity tipusUnitatOrganitzativa = grupEntity.getUnitType();
		if (tipusUnitatOrganitzativa != null) {
			String codiTipus = tipusUnitatOrganitzativa.getName();
			grup.setType(codiTipus); // Unitat Organizativa
		} else {
			grup.setType(null); // Unitat Organizativa
		}

		String obsolet = entity.getObsolete();
		if (obsolet != null && obsolet.compareTo("") != 0) { //$NON-NLS-1$
			grup.setObsolete(obsolet.compareTo("S") == 0 ? new Boolean(true) : new Boolean(false));
		} else {
			grup.setObsolete(new Boolean(false));
		}

		if (entity.getQuotaGroup() != null) {
			grup.setQuota(String.valueOf(entity.getQuotaGroup().longValue()));
		} else {
			grup.setQuota("0"); //$NON-NLS-1$
		}
		grup.setAttributes(new HashMap<String, Object>());
		Map<String, Object> attributes = grup.getAttributes();
		for (GroupAttributeEntity att : grupEntity.getAttributes()) {
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

	}

	/**
	 * @see es.caib.seycon.ng.model.GrupEntityDao#toGrup(es.caib.seycon.ng.model.GrupEntity)
	 */
	public com.soffid.iam.api.Group toGroup(final com.soffid.iam.model.GroupEntity entity) {
		Group grup = super.toGroup(entity);
		toGrupCustom(grup, entity);
		return grup;
	}

	/**
	 * Retrieves the entity object that is associated with the specified value
	 * object from the object store. If no such entity object exists in the
	 * object store, a new, blank entity is created
	 */
	private com.soffid.iam.model.GroupEntity loadGrupEntityFromGrup(com.soffid.iam.api.Group grup) {
		com.soffid.iam.model.GroupEntity grupEntity = null;
		if (grup.getId() != null) {
			grupEntity = load(grup.getId());
		}
		if (grupEntity == null) {
			grupEntity = newGroupEntity();
		}
		return grupEntity;
	}

	/**
	 * @see es.caib.seycon.ng.model.GrupEntityDao#grupToEntity(es.caib.seycon.ng.comu.Grup)
	 */
	public com.soffid.iam.model.GroupEntity groupToEntity(com.soffid.iam.api.Group grup) {
		com.soffid.iam.model.GroupEntity entity = this.loadGrupEntityFromGrup(grup);
		this.groupToEntity(grup, entity, true);
		return entity;
	}

	private void grupToEntityCustom(com.soffid.iam.api.Group sourceVO, com.soffid.iam.model.GroupEntity targetEntity) {
		Boolean organitzatiu = sourceVO.getOrganizational();
		if (organitzatiu != null) {
			targetEntity.setOrganizational(organitzatiu.booleanValue() ? "S" : "N"); //$NON-NLS-1$
		}else{
			targetEntity.setOrganizational("N"); //$NON-NLS-1$
		}

		String codiPare = sourceVO.getParentGroup();
		if (codiPare != null && codiPare.trim().compareTo("") != 0) { //$NON-NLS-1$
			GroupEntity grupPare = findByName(sourceVO.getParentGroup());
			if (grupPare == null) {
				throw new SeyconException(String.format(Messages.getString("GroupEntityDaoImpl.3"), codiPare)); //$NON-NLS-1$
			} else {
				if (grupPare.getObsolete() != null && grupPare.getObsolete().compareTo("S") == 0) { //$NON-NLS-1$
					throw new SeyconException(
							Messages.getString("GroupEntityDaoImpl.4")); //$NON-NLS-1$
				} else {
					targetEntity.setParent(grupPare);
				}
			}
		} else {
			targetEntity.setParent(null);
		}

		String nomServidorOfimatic = sourceVO.getDriveServerName();
		if (nomServidorOfimatic != null
				&& nomServidorOfimatic.trim().compareTo("") != 0) { //$NON-NLS-1$
			HostEntity servidorOfimatic = getHostEntityDao().findByName(nomServidorOfimatic);
			if (servidorOfimatic != null) {
				targetEntity.setHomeServer(servidorOfimatic);
			} else {
				throw new SeyconException(String.format(Messages.getString("GroupEntityDaoImpl.5"),  //$NON-NLS-1$
						nomServidorOfimatic));
			}
		} else {
			targetEntity.setHomeServer(null);
		}

		String codiTipus = sourceVO.getType(); // Unitat Organizativa
		if (codiTipus != null && codiTipus.trim().compareTo("") != 0) { //$NON-NLS-1$
			GroupTypeEntity tipusEntity = getGroupTypeEntityDao().findByName(codiTipus);
			if (tipusEntity != null) {
				targetEntity.setUnitType(tipusEntity);
			} else {
				throw new SeyconException(String.format(Messages.getString("GroupEntityDaoImpl.6"),  //$NON-NLS-1$
						codiTipus));
			}
		} else {
			targetEntity.setUnitType(null);
		}

		if (sourceVO.getObsolete() != null) {
			targetEntity.setObsolete(sourceVO.getObsolete().booleanValue() ? "S" : "N"); //$NON-NLS-1$
		} else {
			targetEntity.setObsolete("N"); //$NON-NLS-1$
		}

		try {
			if (sourceVO.getQuota() != null
					&& sourceVO.getQuota().compareTo("") != 0) { //$NON-NLS-1$
				targetEntity.setQuotaGroup(Long.valueOf(sourceVO.getQuota()));
			} else {
				targetEntity.setQuotaGroup(new Long(0));
			}
		} catch (Exception e) {
			throw new SeyconException(String.format(Messages.getString("GroupEntityDaoImpl.7"),  //$NON-NLS-1$
					sourceVO.getQuota()));
		}
	}

	/**
	 * @see es.caib.seycon.ng.model.GrupEntityDao#grupToEntity(es.caib.seycon.ng.comu.Grup,
	 *      es.caib.seycon.ng.model.GrupEntity)
	 */
	public void groupToEntity(com.soffid.iam.api.Group sourceVO, com.soffid.iam.model.GroupEntity targetEntity, boolean copyIfNull) {
		super.groupToEntity(sourceVO, targetEntity, copyIfNull);
		grupToEntityCustom(sourceVO, targetEntity);
	}

	protected void handleSetParentGroup(String codiSubGrup, String codiSuperGrup) throws Exception {
		GroupEntity superGrup = findByName(codiSuperGrup);
		GroupEntity subGrup = findByName(codiSubGrup);
		/*
		 * No crea ciclo: desde el padre no se puede llegar al hijo
		 */
		GroupEntity pareGrup = superGrup; //comencem pel nou pare
		while (pareGrup != null) {
			if (pareGrup.getName().compareTo(codiSubGrup) == 0) {
				throw new Exception(String.format(Messages.getString("GroupEntityDaoImpl.8"),  //$NON-NLS-1$
						codiSuperGrup, 
						codiSubGrup));
			}
			pareGrup = pareGrup.getParent();
		}

		// Afegim com a pare del subgrup el supergrup
		subGrup.setParent(superGrup);
	}

	/**
	 * @see es.caib.seycon.ng.model.GrupEntityDao#toIdentitat(es.caib.seycon.ng.model.GrupEntity,
	 *      es.caib.seycon.ng.comu.Identitat)
	 */
	public void toIdentity(com.soffid.iam.model.GroupEntity source, com.soffid.iam.api.Identity target) {
		super.toIdentity(source, target);
		toIdentitatCustom(source, target);
	}

	public void toIdentitatCustom(com.soffid.iam.model.GroupEntity source, com.soffid.iam.api.Identity target) {
		String codiGrup = source.getName();
		target.setGroupCode(codiGrup);
		target.setIdentityCode(codiGrup);
		String descripcio = source.getDescription();
		target.setDescription(descripcio);
	}

	/**
	 * @see es.caib.seycon.ng.model.GrupEntityDao#toIdentitat(es.caib.seycon.ng.model.GrupEntity)
	 */
	public com.soffid.iam.api.Identity toIdentity(final com.soffid.iam.model.GroupEntity entity) {
		Identity identitat = super.toIdentity(entity);
		toIdentitatCustom(entity, identitat);
		return identitat;
	}

	/**
	 * Retrieves the entity object that is associated with the specified value
	 * object from the object store. If no such entity object exists in the
	 * object store, a new, blank entity is created
	 */
	private com.soffid.iam.model.GroupEntity loadGrupEntityFromIdentitat(com.soffid.iam.api.Identity identitat) {
		/*
		 * La identitat és read only
		 */
		String codiGrup = identitat.getGroupCode();
		if (codiGrup != null) {
			GroupEntity grupEntity = findByName(codiGrup);
			if (grupEntity != null) {
				return grupEntity;
			} else {
				throw new SeyconException(String.format(Messages.getString("GroupEntityDaoImpl.9"),  //$NON-NLS-1$
						codiGrup));
			}
		}
		throw new SeyconException(Messages.getString("GroupEntityDaoImpl.10")); //$NON-NLS-1$
	}

	/**
	 * @see es.caib.seycon.ng.model.GrupEntityDao#identitatToEntity(es.caib.seycon.ng.comu.Identitat)
	 */
	public com.soffid.iam.model.GroupEntity identityToEntity(com.soffid.iam.api.Identity identitat) {
		// @todo verify behavior of identitatToEntity
		com.soffid.iam.model.GroupEntity entity = this.loadGrupEntityFromIdentitat(identitat);
		this.identityToEntity(identitat, entity, true);
		return entity;
	}

	/**
	 * @see es.caib.seycon.ng.model.GrupEntityDao#identitatToEntity(es.caib.seycon.ng.comu.Identitat,
	 *      es.caib.seycon.ng.model.GrupEntity)
	 */
	public void identityToEntity(com.soffid.iam.api.Identity source, com.soffid.iam.model.GroupEntity target, boolean copyIfNull) {
		super.identityToEntity(source, target, copyIfNull);
	}

	public GroupEntity domainValueToEntity(DomainValue valorDomini) {
		// els tipus domini son read-only
		return null;
	}

	public DomainValue toDomainValue(GroupEntity entity) {
		DomainValue valorDomini = super.toDomainValue(entity);
		valorDomini.setDescription(entity.getDescription());
		// pot ser GRUPS o GRUPS_USUARI
		// domini service ho emplena
		valorDomini.setDomainName(null);
		valorDomini.setValue(entity.getName());
		// encara no es sap l'usuari del grup si aquest és GRUPS_USUARI
		valorDomini.setExternalCodeDomain(null);
		return valorDomini;
	}

	/* HERENCIA DE ROLES  */
	
	/**
	 * ATORGACIO DE ROLS
	 * @param grupAnalitzar
	 * @return
	 */
	private Collection getParesGrup(GroupEntity grupAnalitzar) {

		Collection totsPares = new HashSet();
		GroupEntity pare = grupAnalitzar.getParent();
		while (pare !=null) {
			totsPares.add(pare);
			pare = pare.getParent();
		}

		return totsPares;
	}
	
	/**
	 * ATORGACIO DE ROLS
	 * @param rol
	 * @return
	 */
	private Collection getRolsContingutsPerPropagar(RoleEntity rol) {
		// Si rol té atorgats d'altres rols (és contenidor del rols) 
		// s'han de propagar tots els rols que conté (per assignar-lo a l'usuari)
		HashSet rolsPropagar = new HashSet();
		// Sólo hemos de propagar a los usuarios que tienen el rol contenedor 
		// con valor de dominio correspondiente (o si es SENSE_DOMINI o a qualque valor)
		// Montamos un FIFO De roles (puede haber cadena de 
		// herencia A atorgat B[sense domini] atorgat C ... atorgat Z[amb domini]
		LinkedList rolsAnalitzar = new LinkedList(); // FIFO
		rolsAnalitzar.add(rol);
		RoleEntity rolActual = null;
		while ((rolActual = (RoleEntity) rolsAnalitzar.poll()) != null) {
			Collection socContenidor = rolActual.getContainedRoles();
				
			if (socContenidor!=null) for (Iterator it = socContenidor.iterator(); it.hasNext(); ) {
                RoleDependencyEntity associacio = (RoleDependencyEntity) it.next();
                RoleEntity rolContingut = associacio.getContained();
                rolsPropagar.add(rolContingut);
                rolsAnalitzar.add(rolContingut);
            }
		}
		return rolsPropagar;
	}		
	
	/**
	 * ATORGACIÓ DE ROLS
	 * Obtiene dado un grupo, los roles otorgados al grupo (y
	 * los roles otorgados a los padres del grupo indicado)
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
	
    private void updateMailLists(GroupEntity group) throws InternalErrorException {
    	while (group != null)
    	{
	    	for (MailListGroupMemberEntity lce : group.getMailLists()) {
                getEmailListEntityDao().generateUpdateTasks(lce.getMailList());
            }
	    	group = group.getParent();
    	}
    }
    
	/**
	 * Atorgació de rols: Propaga els rols indicats 
	 * @param rolsPropagar
	 */
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
	
	private Collection getUsuarisPertanyenGrup(String codiGrup) {
		HashSet totsUsuaris = new HashSet();
		// Obtenemos los grupos primarios primero
		Collection usuarisGrupComGrupPrimari = getUserEntityDao().findByPrimaryGroup(codiGrup);
		for (Iterator it = usuarisGrupComGrupPrimari.iterator(); it.hasNext(); ) {
            UserEntity user = (UserEntity) it.next();
            totsUsuaris.add(user.getUserName());
        }
		
		// Esto obtiene los usuarios que tienen el grupo como secundario
		Collection usuarisGrupComGrupSec = getUserGroupEntityDao().findByGroupName(codiGrup);
		for (Iterator it = usuarisGrupComGrupSec.iterator(); it.hasNext(); ) {
            UserGroupEntity usugru = (UserGroupEntity) it.next();
            totsUsuaris.add(usugru.getUser().getUserName());
        }
		
		return new ArrayList(totsUsuaris);
		
		
	}
	
	private void propagarUsuarisGrup (Collection usuarisPropagar) {
		if (usuarisPropagar != null)
			for (Iterator it = usuarisPropagar.iterator(); it.hasNext(); ) {
            Object obj = it.next();
            if (obj != null) {
                String codiUsuari = (String) obj;
                Task updateUser = new Task();
                updateUser.setTransaction("UpdateUser");
                updateUser.setTaskDate(Calendar.getInstance());
                updateUser.setUser(codiUsuari);
                updateUser.setStatus("P");
                TaskEntity tasca = getTaskEntityDao().taskToEntity(updateUser);
                getTaskEntityDao().create(tasca);
            }
        }		
	}

	public void create(Collection entities) {
		if (entities!=null) for (Iterator it = entities.iterator(); it.hasNext(); ) {
            Object obj = it.next();
            if (obj instanceof GroupEntity) {
                GroupEntity entity = (GroupEntity) obj;
                this.create(entity);
            }
        }
	}

	public void update(Collection entities) {
		if (entities!=null) for (Iterator it = entities.iterator(); it.hasNext(); ) {
            Object obj = it.next();
            if (obj instanceof GroupEntity) {
                GroupEntity entity = (GroupEntity) obj;
                this.update(entity);
            }
        }
	}

	public void remove(Collection entities) {
		if (entities!=null) for (Iterator it = entities.iterator(); it.hasNext(); ) {
            Object obj = it.next();
            if (obj instanceof GroupEntity) {
                GroupEntity entity = (GroupEntity) obj;
                this.remove(entity);
            }
        }
	}

	@Override
	public Collection<GroupEntity> findByText(CriteriaSearchConfiguration criteria, String text) {
		String[] split = text.trim().split(" +");
		Parameter[] params = new Parameter[split.length + 1];
		
		StringBuffer sb = new StringBuffer("select u "
				+ "from com.soffid.iam.model.GroupEntity as u "
				+ "where u.tenant.id = :tenantId and u.obsolete='N'");
		params[0] = new Parameter("tenantId", Security.getCurrentTenantId());
		for (int i = 0; i < split.length; i++)
		{
			sb.append(" and ");
			params[i+1] = new Parameter("param"+i, "%"+split[i].toUpperCase()+"%");
			sb.append("(upper(u.name) like :param")
				.append(i)
				.append(" or upper(u.description) like :param")
				.append(i)
				.append(")");
		}
		return query(sb.toString(), params);
	}

}
