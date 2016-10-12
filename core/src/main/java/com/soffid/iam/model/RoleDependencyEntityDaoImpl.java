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

import es.caib.seycon.ng.comu.TipusDomini;
import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.ng.model.*;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

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
        if (!TipusDomini.SENSE_DOMINI.equals(rolAssocRolEntity.getContained().getDomainType())) {
            String tipusDominiAsoc = rolAssocRolEntity.getContained().getDomainType(); // Tipo
                                                                         // de
                                                                         // dominio
                                                                         // de
                                                                         // la
                                                                         // asociación
            String tipusDominiRol = rcontenidor.getDomainType();
            // "{"+tipusDomini+":"+valorDomini+"["+descripcioValorDomini+"]}"
            if (TipusDomini.APLICACIONS.equals(tipusDominiAsoc)) {
                InformationSystemEntity app = rolAssocRolEntity.getDomainApplication();
                if (app != null) {
                    infoDomini = "{" + tipusDominiAsoc + ":" + app.getName() + "[" + app.getDescription() + "]}"; //$NON-NLS-1$ //$NON-NLS-2$
                } else {
                    infoDomini = "{" + tipusDominiAsoc + ":" //$NON-NLS-1$ //$NON-NLS-2$
                            + TipusDomini.QUALQUE_VALOR_DOMINI + "}"; //$NON-NLS-1$
                }
            } else if (TipusDomini.GRUPS.equals(tipusDominiAsoc)
                    || TipusDomini.GRUPS_USUARI.equals(tipusDominiAsoc)) {
                GroupEntity gr = rolAssocRolEntity.getDomainGroup();
                if (gr != null) {
                    infoDomini = "{" + tipusDominiAsoc + ":" + gr.getName() + "[" + gr.getDescription() + "]}"; //$NON-NLS-1$ //$NON-NLS-2$
                } else {
                    infoDomini = "{" + tipusDominiAsoc + ":" //$NON-NLS-1$ //$NON-NLS-2$
                            + TipusDomini.QUALQUE_VALOR_DOMINI + "}"; //$NON-NLS-1$
                }
            } else if (TipusDomini.DOMINI_APLICACIO.equals(tipusDominiAsoc)) {
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
        if (TipusDomini.APLICACIONS.equals(tipus) && source.getDomainApplication() != null) {
            target.setDomainValue(source.getDomainApplication().getName());
            target.setHasDomain(true);
        } else if ((TipusDomini.GRUPS.equals(tipus) || TipusDomini.GRUPS_USUARI.equals(tipus)) && source.getDomainGroup() != null) {
            target.setDomainValue(source.getDomainGroup().getName());
            target.setHasDomain(true);
        } else if (TipusDomini.DOMINI_APLICACIO.equals(tipus) && source.getDomainApplicationValue() != null) {
            target.setDomainValue(source.getDomainApplicationValue().getValue());
            target.setHasDomain(true);
        } else if (TipusDomini.QUALQUE_VALOR_DOMINI.equals(tipus) ) {
            target.setHasDomain(true);
            target.setDomainValue(null);
        } else {
            target.setHasDomain(false);
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
        target.setOwnerSystem(source.getContainer().getSystem().getName());
        target.setId(source.getId());
        target.setRoleId(source.getContained().getId());
        target.setRoleName(source.getContained().getName());
        target.setSystem(source.getContained().getSystem().getName());
        target.setInformationSystem(source.getContained().getInformationSystem().getName());
        if (source.getStatus() == null)
        	target.setStatus (RoleDependencyStatus.STATUS_ACTIVE);
        else
        	target.setStatus(source.getStatus());
    }

}
