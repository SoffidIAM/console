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

import com.soffid.iam.api.ContainerRole;
import com.soffid.iam.api.DomainType;
import com.soffid.iam.api.RoleDependencyStatus;
import com.soffid.iam.api.RoleGrant;
import com.soffid.iam.model.DomainValueEntity;
import com.soffid.iam.model.GroupEntity;
import com.soffid.iam.model.InformationSystemEntity;
import com.soffid.iam.model.RoleDependencyEntity;
import com.soffid.iam.model.RoleEntity;
import com.soffid.iam.model.TaskEntity;
import com.soffid.iam.sync.engine.TaskHandler;
import com.soffid.iam.utils.TipusContenidorRol;

import es.caib.bpm.exception.BPMException;
import es.caib.seycon.ng.comu.TipusDomini;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.ng.model.*;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Hibernate;

/**
 * @see es.caib.seycon.ng.model.RolAssociacioRolEntity
 */
public class RoleDependencyEntityDaoImpl extends
        com.soffid.iam.model.RoleDependencyEntityDaoBase {

    @Override
    public void create(RoleDependencyEntity rolAssociacioRolEntity) {
        super.create(rolAssociacioRolEntity);
        generateTask(rolAssociacioRolEntity.getContainer());
        generateTask(rolAssociacioRolEntity.getContained());
        getSession().flush ();
    }

    @Override
    public void update(RoleDependencyEntity rolAssociacioRolEntity) {
        RoleDependencyEntity old = load(rolAssociacioRolEntity.getId());
        super.update(rolAssociacioRolEntity);
        generateTask(rolAssociacioRolEntity.getContainer());
        generateTask(rolAssociacioRolEntity.getContained());
        if (!old.getContainer().getId().equals(rolAssociacioRolEntity.getContainer().getId())) {
            generateTask(old.getContainer());
        }
        if (!old.getContainer().getId().equals(rolAssociacioRolEntity.getContained().getId())) {
            generateTask(old.getContained());
        }
        getSession().flush ();
    }

    @Override
    public void remove(RoleDependencyEntity rolAssociacioRolEntity) {
        super.remove(rolAssociacioRolEntity);
        generateTask(rolAssociacioRolEntity.getContainer());
        generateTask(rolAssociacioRolEntity.getContained());
        getSession().flush ();
    }

    private void generateTask(RoleEntity rol) {
        TaskEntity tasque = getTaskEntityDao().newTaskEntity();
        tasque.setDate(new Timestamp(System.currentTimeMillis()));
        tasque.setTransaction(TaskHandler.UPDATE_ROLE);
        tasque.setRole(rol.getName());
        tasque.setDb(rol.getSystem().getName());
        getTaskEntityDao().create(tasque);
    }

    public RoleDependencyEntity containerRoleToEntity(ContainerRole contenidorRol) {
        return null;
    }

    public ContainerRole toContainerRole(RoleDependencyEntity rolAssocRolEntity) {
        ContainerRole contenidorRol = super.toContainerRole(rolAssocRolEntity); // Pasamos
                                                                                // el
                                                                                // id
        contenidorRol.setType(TipusContenidorRol.ROL_ROL);
        RoleEntity rcontenidor = rolAssocRolEntity.getContainer(); // rol
                                                                      // atorgat
                                                                      // (si lo
                                                                      // tienes
                                                                      // tienes
                                                                      // el rol
                                                                      // contingut)
        ContainerRole contenidor = getRoleEntityDao().toContainerRole(rcontenidor);
        // Afegim informació del domini:
        String infoDomini = ""; //$NON-NLS-1$
        // Si es nulo o valor SENSE_DOMINI no ponemos valor de dominio
        if (rolAssocRolEntity.getContained().getDomainType() != null &&
        		!TipusDomini.SENSE_DOMINI.equals(rolAssocRolEntity.getContained().getDomainType())) {
            String tipusDominiAsoc = rolAssocRolEntity.getContained().getDomainType(); // Tipo
                                                                         // de
                                                                         // dominio
                                                                         // de
                                                                         // la
                                                                         // asociación
            String tipusDominiRol = rcontenidor.getDomainType();
            // "{"+tipusDomini+":"+valorDomini+"["+descripcioValorDomini+"]}"
            if (TipusDomini.APLICACIONS.equals(tipusDominiAsoc) 
            		|| TipusDomini.APPLICATIONS.equals(tipusDominiAsoc) ) {
                InformationSystemEntity app = rolAssocRolEntity.getDomainApplication();
                if (app != null) {
                    infoDomini = "{" + tipusDominiAsoc + ":" + app.getName() + "[" + app.getDescription() + "]}"; //$NON-NLS-1$ //$NON-NLS-2$
                } else {
                    infoDomini = "{" + tipusDominiAsoc + ":" //$NON-NLS-1$ //$NON-NLS-2$
                            + TipusDomini.QUALQUE_VALOR_DOMINI + "}"; //$NON-NLS-1$
                }
            } else if (TipusDomini.GRUPS.equals(tipusDominiAsoc)
                    || TipusDomini.GRUPS_USUARI.equals(tipusDominiAsoc)
                    || TipusDomini.GROUPS.equals(tipusDominiAsoc) 
                    || TipusDomini.MEMBERSHIPS.equals(tipusDominiAsoc) ) {
                GroupEntity gr = rolAssocRolEntity.getDomainGroup();
                if (gr != null) {
                    infoDomini = "{" + tipusDominiAsoc + ":" + gr.getName() + "[" + gr.getDescription() + "]}"; //$NON-NLS-1$ //$NON-NLS-2$
                } else {
                    infoDomini = "{" + tipusDominiAsoc + ":" //$NON-NLS-1$ //$NON-NLS-2$
                            + TipusDomini.QUALQUE_VALOR_DOMINI + "}"; //$NON-NLS-1$
                }
            } else if (TipusDomini.DOMINI_APLICACIO.equals(tipusDominiAsoc)
            		|| TipusDomini.CUSTOM.equals(tipusDominiAsoc) ) {
                DomainValueEntity vd = rolAssocRolEntity.getDomainApplicationValue();
                if (vd != null) {
                    infoDomini = "{" + tipusDominiAsoc + ":" + vd.getValue() + "[" + vd.getDomain().getName() + "]}"; //$NON-NLS-1$ //$NON-NLS-2$
                } else {
                    infoDomini = "{" + tipusDominiAsoc + ":" //$NON-NLS-1$ //$NON-NLS-2$
                            + TipusDomini.QUALQUE_VALOR_DOMINI + "[}"; //$NON-NLS-1$
                }
            }
            // Casos de nous: pot tindre tipusDominiAsoc a null (és un camp nou)
            else if (TipusDomini.QUALQUE_VALOR_DOMINI.equals(tipusDominiAsoc)
                    || (tipusDominiAsoc == null && tipusDominiRol != null && !TipusDomini.SENSE_DOMINI
                            .equals(tipusDominiRol))) {
                // IMPORTANT: Aquí posam el tipus de domini del Rol original (no
                // de l'associació)
                infoDomini = "{" + rcontenidor.getDomainType() + ":" + TipusDomini.QUALQUE_VALOR_DOMINI + "}"; //$NON-NLS-1$
            }
        }
        // Retornem informació del contenidor-pare (rol que el té atorgat)
        contenidorRol.setContainerInfo(contenidor.getContainerInfo() + infoDomini);

        return contenidorRol;
    }

    public RoleDependencyEntity roleGrantToEntity(RoleGrant rolGrant) {
        return load(rolGrant.getId());
    }

    @Override
    public void toRoleGrant(RoleDependencyEntity source, RoleGrant target) {
    	// Translate granted domain
        String tipus = source.getContained().getDomainType();
        if (tipus == null || TipusDomini.SENSE_DOMINI.equals(tipus)) {
        	target.setHasDomain(false);
        	target.setDomainValue(null);
        } else if ((TipusDomini.APLICACIONS.equals(tipus) ||
        		TipusDomini.APPLICATIONS.equals(tipus) ) &&
        		source.getDomainApplication() != null) {
            target.setDomainValue(source.getDomainApplication().getName());
            target.setDomainDescription(source.getDomainApplication().getDescription());
            target.setHasDomain(true);
        } else if ((TipusDomini.GRUPS.equals(tipus) || 
        		TipusDomini.GRUPS_USUARI.equals(tipus) ||
        		TipusDomini.GROUPS.equals(tipus)  ||
        		TipusDomini.MEMBERSHIPS.equals(tipus) ) && 
        		source.getDomainGroup() != null) {
            target.setDomainValue(source.getDomainGroup().getName());
            target.setDomainDescription(source.getDomainGroup().getDescription());
            target.setHasDomain(true);
        } else if ((TipusDomini.DOMINI_APLICACIO.equals(tipus)  ||
        		TipusDomini.CUSTOM.equals(tipus) ) && 
        		source.getDomainApplicationValue() != null) {
            target.setDomainValue(source.getDomainApplicationValue().getValue());
            target.setDomainDescription(source.getDomainApplicationValue().getDescription());
            target.setHasDomain(true);
        } else if (TipusDomini.QUALQUE_VALOR_DOMINI.equals(tipus) ) {
            target.setHasDomain(true);
            target.setDomainValue(null);
        }
    	// Translate grantee domain
        tipus = source.getContainer().getDomainType();
        if (TipusDomini.APLICACIONS.equals(tipus) && 
                source.getGranteeApplicationDomain() != null) {
            target.setOwnerRolDomainValue(source.getGranteeApplicationDomain().getName());
        } else if (( TipusDomini.GRUPS.equals(tipus)
                || TipusDomini.GRUPS_USUARI.equals(tipus) ) &&
                source.getGranteeGroupDomain() != null) {
            target.setOwnerRolDomainValue(source.getGranteeGroupDomain().getName());
        } else if (TipusDomini.DOMINI_APLICACIO.equals(tipus) &&
                source.getGranteeDomainValue() != null) {
            target.setOwnerRolDomainValue(source.getGranteeDomainValue().getValue());
        } else if (TipusDomini.QUALQUE_VALOR_DOMINI.equals(tipus) ) {
            target.setOwnerRolDomainValue(null);
        } else {
            target.setOwnerRolDomainValue(null);
        }
        target.setOwnerRole(source.getContainer().getId());
        target.setOwnerRoleName(source.getContainer().getName());
        target.setOwnerGroup(null);
        target.setOwnerAccountName(null);
        target.setOwnerRoleDescription(source.getContainer().getDescription());
        target.setOwnerSystem(source.getContainer().getSystem().getName());
        target.setOwnerInformationSystem(source.getContainer().getInformationSystem().getName());
        target.setId(source.getId());
        target.setRoleId(source.getContained().getId());
        target.setRoleName(source.getContained().getName());
        target.setRoleDescription(source.getContained().getDescription());
        target.setSystem(source.getContained().getSystem().getName());
        target.setInformationSystem(source.getContained().getInformationSystem().getName());
        if (source.getStatus() == null)
        	target.setStatus (RoleDependencyStatus.STATUS_ACTIVE);
        else
        	target.setStatus(source.getStatus());
        target.setMandatory(source.getMandatory());
    }

	@Override
	public void roleGrantToEntity(RoleGrant grant, RoleDependencyEntity entity, boolean copyIfNull) {
        RoleEntity ownerRole = grant.getOwnerRole() == null ? null: getRoleEntityDao().load (grant.getOwnerRole()); 
        RoleEntity ownedRole = grant.getRoleId() == null ? null: getRoleEntityDao().load (grant.getRoleId()); 
	        
        entity.setContained(ownedRole);
        entity.setContainer(ownerRole);
 
        try {
			assignDomainValue(entity, grant, ownedRole,
					ownerRole);
 
			assignGranteeDomainValue(entity, grant, ownedRole,
					ownerRole);
		} catch (InternalErrorException e) {
			throw new RuntimeException (e);
		}
 
        if ( Hibernate.isInitialized(ownedRole.getContainerRoles()))
        	ownedRole.getContainerRoles().add(entity);
        
        if ( Hibernate.isInitialized(ownerRole.getContainedRoles()))
        	ownerRole.getContainedRoles().add(entity);
 
	}

	public void handleAssignDomainValue(RoleDependencyEntity rare,
			RoleGrant currentPare, com.soffid.iam.model.RoleEntity grantedRole,
			RoleEntity granteeRole) {
		// Añadimos la relación con el padre
		if (granteeRole != null) {
			// Podemos tener dos casos: que el Role no tenga Dominio
			// o que si tenga
			String tipusDominiAsoc = grantedRole.getDomainType();
			// Primer mirem que no siga sense valor domini (si té
			// valor de domini)
			if (currentPare.getDomainValue() == null
					|| currentPare.getDomainValue().trim().length() == 0
					|| tipusDominiAsoc == null
					|| TipusDomini.SENSE_DOMINI.equals(tipusDominiAsoc)) {
			} else if (TipusDomini.GRUPS.equals(tipusDominiAsoc)
					|| TipusDomini.GRUPS_USUARI.equals(tipusDominiAsoc) 
					|| TipusDomini.GROUPS.equals(tipusDominiAsoc) 
					|| TipusDomini.MEMBERSHIPS.equals(tipusDominiAsoc) ) {
				GroupEntity grupAsoc = getGroupEntityDao().findByName(
						currentPare.getDomainValue());
				if (grupAsoc == null) {
					throw new SeyconException(String.format(
							Messages.getString("RoleEntityDaoImpl.14"), //$NON-NLS-1$
							currentPare.getDomainValue()));
				}
				rare.setDomainGroup(grupAsoc);
			} else if (TipusDomini.APLICACIONS.equals(tipusDominiAsoc)
					|| TipusDomini.APPLICATIONS.equals(tipusDominiAsoc) ) {
				InformationSystemEntity appAsoc = getInformationSystemEntityDao()
						.findByCode(currentPare.getDomainValue());
				if (appAsoc == null) {
					throw new SeyconException(String.format(
							Messages.getString("RoleEntityDaoImpl.15"), //$NON-NLS-1$
							currentPare.getDomainValue()));
				}
				rare.setDomainApplication(appAsoc);
			} else if (TipusDomini.DOMINI_APLICACIO.equals(tipusDominiAsoc)
					|| TipusDomini.CUSTOM.equals(tipusDominiAsoc) ) {
				DomainValueEntity valdomAsoc = getDomainValueEntityDao()
						.findByRoleAndValue(grantedRole.getId(),
								currentPare.getDomainValue());
				if (valdomAsoc == null) {
					throw new SeyconException(String.format(
							Messages.getString("RoleEntityDaoImpl.16"),
							granteeRole.getApplicationDomain().getName(),
							currentPare.getDomainValue()));
				}
				rare.setDomainApplicationValue(valdomAsoc);
			}
		} else {
			throw new SeyconException(String.format(
					Messages.getString("RoleEntityDaoImpl.17"),
					currentPare.getOwnerRoleName(), currentPare.getOwnerRole(),
					currentPare.getOwnerSystem()));
		}
	}

	public void handleAssignGranteeDomainValue(RoleDependencyEntity rare,
			RoleGrant grant, com.soffid.iam.model.RoleEntity grantedRole,
			RoleEntity granteeRole) {
		// Añadimos la relación con el padre
		if (granteeRole != null) {
			// Podemos tener dos casos: que el Role no tenga Dominio
			// o que si tenga
			String tipusDominiAsoc = granteeRole.getDomainType();
			// Primer mirem que no siga sense valor domini (si té
			// valor de domini)
			if (grant.getOwnerRolDomainValue() == null
					|| grant.getOwnerRolDomainValue().trim().length() == 0
					|| tipusDominiAsoc == null
					|| TipusDomini.SENSE_DOMINI.equals(tipusDominiAsoc)) {
			} else if (TipusDomini.GRUPS.equals(tipusDominiAsoc)
					|| TipusDomini.GRUPS_USUARI.equals(tipusDominiAsoc)
					|| TipusDomini.GROUPS.equals(tipusDominiAsoc) 
					|| TipusDomini.MEMBERSHIPS.equals(tipusDominiAsoc) ) {
				GroupEntity grupAsoc = getGroupEntityDao().findByName(
						grant.getOwnerRolDomainValue());
				if (grupAsoc == null) {
					throw new SeyconException(String.format(
							Messages.getString("RoleEntityDaoImpl.14"), //$NON-NLS-1$
							grant.getDomainValue()));
				}
				rare.setGranteeGroupDomain(grupAsoc);
			} else if (TipusDomini.APLICACIONS.equals(tipusDominiAsoc)
					|| TipusDomini.APPLICATIONS.equals(tipusDominiAsoc) ) {
				InformationSystemEntity appAsoc = getInformationSystemEntityDao()
						.findByCode(grant.getOwnerRolDomainValue());
				if (appAsoc == null) {
					throw new SeyconException(String.format(
							Messages.getString("RoleEntityDaoImpl.15"), //$NON-NLS-1$
							grant.getDomainValue()));
				}
				rare.setGranteeApplicationDomain(appAsoc);
			} else if (TipusDomini.DOMINI_APLICACIO.equals(tipusDominiAsoc) 
					|| TipusDomini.CUSTOM.equals(tipusDominiAsoc) ) {
				DomainValueEntity valdomAsoc = getDomainValueEntityDao()
						.findByRoleAndValue(granteeRole.getId(),
								grant.getOwnerRolDomainValue());
				if (valdomAsoc == null) {
					throw new SeyconException(String.format(
							Messages.getString("RoleEntityDaoImpl.16"),
							grantedRole.getApplicationDomain().getName(),
							grant.getDomainValue()));
				}
				rare.setGranteeDomainValue(valdomAsoc);
			}
		} else {
			throw new SeyconException(String.format(
					Messages.getString("RoleEntityDaoImpl.17"),
					grant.getOwnerRoleName(), grant.getOwnerRole(),
					grant.getOwnerSystem()));
		}
	}

}
