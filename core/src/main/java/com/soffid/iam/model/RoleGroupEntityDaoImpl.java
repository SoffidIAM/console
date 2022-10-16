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
import com.soffid.iam.api.DomainValue;
import com.soffid.iam.api.GroupRoles;
import com.soffid.iam.api.RoleGrant;
import com.soffid.iam.model.ApplicationDomainEntity;
import com.soffid.iam.model.GroupEntity;
import com.soffid.iam.model.InformationSystemEntity;
import com.soffid.iam.model.RoleEntity;
import com.soffid.iam.model.RoleGroupEntity;
import com.soffid.iam.model.TaskEntity;
import com.soffid.iam.sync.engine.TaskHandler;
import com.soffid.iam.utils.TipusContenidorRol;

import es.caib.seycon.ng.comu.TipusDomini;
import es.caib.seycon.ng.model.*;

import java.sql.Timestamp;

/**
 * @see es.caib.seycon.ng.model.RolsGrupEntity
 */
/**
 * @author u88683
 * 
 */
public class RoleGroupEntityDaoImpl extends
        com.soffid.iam.model.RoleGroupEntityDaoBase {

    public RoleGroupEntity groupRolesToEntity(GroupRoles rolsGrup) {
        return null;
    }

    public void groupRolesToEntity(GroupRoles source, RoleGroupEntity target, boolean copyIfNull) {
        super.groupRolesToEntity(source, target, copyIfNull);
    }

    @Override
    public void create(RoleGroupEntity rolsGrupEntity) {
        super.create(rolsGrupEntity);
        TaskEntity tasque = getTaskEntityDao().newTaskEntity();
        tasque.setDate(new Timestamp(System.currentTimeMillis()));
        tasque.setTransaction(TaskHandler.UPDATE_ROLE);
        tasque.setRole(rolsGrupEntity.getGrantedRole().getName());
        tasque.setDb(rolsGrupEntity.getGrantedRole().getSystem().getName());
        getTaskEntityDao().create(tasque);
        getSession().flush();
    }

    @Override
    public void update(RoleGroupEntity rolsGrupEntity) {
        RoleGroupEntity old = load(rolsGrupEntity.getId());
        TaskEntity tasque = getTaskEntityDao().newTaskEntity();
        tasque.setDate(new Timestamp(System.currentTimeMillis()));
        tasque.setTransaction(TaskHandler.UPDATE_ROLE);
        tasque.setRole(old.getGrantedRole().getName());
        tasque.setDb(old.getGrantedRole().getSystem().getName());
        getTaskEntityDao().create(tasque);
        super.update(rolsGrupEntity);
        tasque = getTaskEntityDao().newTaskEntity();
        tasque.setDate(new Timestamp(System.currentTimeMillis()));
        tasque.setTransaction(TaskHandler.UPDATE_ROLE);
        tasque.setRole(rolsGrupEntity.getGrantedRole().getName());
        tasque.setDb(rolsGrupEntity.getGrantedRole().getSystem().getName());
        getTaskEntityDao().create(tasque);
        getSession().flush();
    }

    @Override
    public void remove(RoleGroupEntity rolsGrupEntity) {
        super.remove(rolsGrupEntity);
        TaskEntity tasque = getTaskEntityDao().newTaskEntity();
        tasque.setDate(new Timestamp(System.currentTimeMillis()));
        tasque.setTransaction(TaskHandler.UPDATE_ROLE);
        tasque.setRole(rolsGrupEntity.getGrantedRole().getName());
        tasque.setDb(rolsGrupEntity.getGrantedRole().getSystem().getName());
        getTaskEntityDao().create(tasque);
        getSession().flush();
    }

    public void toGroupRoles(RoleGroupEntity source, GroupRoles target) {
        super.toGroupRoles(source, target);
        toRolsGrupCustom(source, target);
    }

    public GroupRoles toGroupRoles(RoleGroupEntity entity) {
        GroupRoles rolGrup = super.toGroupRoles(entity);
        toRolsGrupCustom(entity, rolGrup);
        return rolGrup;
    }

    private void toRolsGrupCustom(RoleGroupEntity sourceEntity, GroupRoles targetVO) {
        // TODO: Revisar transformaciones: se utilizan en roles de grupos de
        // seyconweb
        String tipusDomini = sourceEntity.getGrantedRole().getDomainType();
        if (tipusDomini == null || tipusDomini.trim().compareTo("") == 0) { //$NON-NLS-1$
            tipusDomini = TipusDomini.SENSE_DOMINI;
        }
        if (tipusDomini.compareTo(TipusDomini.DOMINI_APLICACIO) == 0 ||
        		TipusDomini.CUSTOM.equals(tipusDomini)) {
        	DomainValue valorDomini;
            if (sourceEntity.getGrantedDomainValue() != null)
            {
            	valorDomini = getDomainValueEntityDao().toDomainValue(sourceEntity.getGrantedDomainValue());
            }
            else
            {
                ApplicationDomainEntity dominiAplicacio = sourceEntity.getGrantedRole().getApplicationDomain();
                valorDomini = new DomainValue();
                // Le asignamos como nombre la descripción del dominio de aplicación
                valorDomini.setDomainName(sourceEntity.getGrantedRole().getApplicationDomain().getName());
            }
        	targetVO.setDomainValue(valorDomini);
        } else if (tipusDomini.compareTo(TipusDomini.GRUPS) == 0
                || tipusDomini.compareTo(TipusDomini.GRUPS_USUARI) == 0 ||
                		TipusDomini.GROUPS.equals(tipusDomini) ||
                		TipusDomini.MEMBERSHIPS.equals(tipusDomini)) {
            DomainValue valorDomini = new DomainValue();
            valorDomini.setDomainName(tipusDomini.compareTo(TipusDomini.GRUPS) == 0
                    || tipusDomini.compareTo(TipusDomini.GROUPS) == 0 ?
                    		TipusDomini.GROUPS : TipusDomini.MEMBERSHIPS);
            if (sourceEntity.getGrantedGroupDomain() != null)
            {
            	valorDomini.setDescription(sourceEntity.getGrantedGroupDomain().getDescription());
            	valorDomini.setValue(sourceEntity.getGrantedGroupDomain().getName());
            }
            valorDomini.setExternalCodeDomain(null);
            targetVO.setDomainValue(valorDomini);
        } else if (tipusDomini.compareTo(TipusDomini.APLICACIONS) == 0 ||
        		TipusDomini.APPLICATIONS.equals(tipusDomini)) {
            DomainValue valorDomini = new DomainValue();
            valorDomini.setDomainName(TipusDomini.APPLICATIONS);
            if (sourceEntity.getGrantedGroupDomain() != null)
            {
            	valorDomini.setDescription(sourceEntity.getGrantedApplicationDomain().getDescription());
            	valorDomini.setValue(sourceEntity.getGrantedApplicationDomain().getName());
            }
            valorDomini.setExternalCodeDomain(null);
            targetVO.setDomainValue(valorDomini);
        } else if (tipusDomini.compareTo(TipusDomini.SENSE_DOMINI) == 0) {
            DomainValue valorDomini = new DomainValue();
            valorDomini.setExternalCodeDomain(null);
            valorDomini.setDescription("");
            valorDomini.setDomainName(TipusDomini.SENSE_DOMINI);
            valorDomini.setValue(""); //$NON-NLS-1$
            // targetVO.setValorDomini(valorDomini); // No se muestra
        }

        targetVO.setRoleName(sourceEntity.getGrantedRole().getName());
        targetVO.setRoleDescription(sourceEntity.getGrantedRole().getDescription());
        targetVO.setRoleDatabases(sourceEntity.getGrantedRole().getSystem().getName());
        InformationSystemEntity aplicacio = sourceEntity.getGrantedRole().getInformationSystem();
        if (aplicacio != null) {
            targetVO.setApplicationCode(aplicacio.getName());
        }
        targetVO.setGroupCode(sourceEntity.getGroup().getName());
        targetVO.setGroupDescription(sourceEntity.getGroup().getDescription());

    }

    public RoleGroupEntity containerRoleToEntity(ContainerRole contenidorRol) {
        // TODO Auto-generated method stub
        return null;
    }

    public ContainerRole toContainerRole(RoleGroupEntity entity) {
        ContainerRole contenidorRol = super.toContainerRole(entity); // Pasamos
                                                                     // el id
        contenidorRol.setType(TipusContenidorRol.ROL_GRUP);
        // Información específica:
        RoleEntity rol = entity.getGrantedRole();
        GroupEntity grup = entity.getGroup();
        contenidorRol.setContainerInfo(String.format(Messages.getString("RoleGroupEntityDaoImpl.0"), rol.getName(), grup.getName()));
        return contenidorRol;
    }

    public RoleGroupEntity roleGrantToEntity(RoleGrant rolGrant) {
        return load(rolGrant.getId());
    }

    @Override
    public void toRoleGrant(RoleGroupEntity source, RoleGrant target) {
        target.setSystem(source.getGrantedRole().getSystem().getName());
        String tipus = source.getGrantedRole().getDomainType();
        if (tipus == null || tipus.trim().isEmpty() ||
        		TipusDomini.SENSE_DOMINI.equals(tipus)) {
            target.setHasDomain(false);
            target.setDomainValue(null);
        } else if ((TipusDomini.APLICACIONS.equals(tipus) ||
        		TipusDomini.APPLICATIONS.equals(tipus)) && 
        		source.getGrantedApplicationDomain() != null) {
        	target.setHasDomain(true);
        	target.setDomainValue(source.getGrantedApplicationDomain().getName());
        	target.setDomainDescription(source.getGrantedApplicationDomain().getDescription());
        } else if ((TipusDomini.GRUPS.equals(tipus) || TipusDomini.GRUPS_USUARI.equals(tipus)
        		|| TipusDomini.GROUPS.equals(tipus) || TipusDomini.MEMBERSHIPS.equals(tipus)) &&
        		source.getGrantedGroupDomain() != null) {
        	target.setHasDomain(true);
        	target.setDomainValue(source.getGrantedGroupDomain().getName());
        	target.setDomainDescription(source.getGrantedGroupDomain().getDescription());
        } else if ((TipusDomini.DOMINI_APLICACIO.equals(tipus) ||
        		TipusDomini.CUSTOM.equals(tipus)) && source.getGrantedDomainValue() != null) {
        	target.setHasDomain(true);
        	target.setDomainValue(source.getGrantedDomainValue().getValue());
        	target.setDomainDescription(source.getGrantedDomainValue().getDescription());
        } else {
            target.setHasDomain(false);
            target.setDomainValue(null);
        }
        target.setOwnerRole(null);
        target.setOwnerRoleName(null);
        target.setOwnerGroup(source.getGroup().getName());
        target.setOwnerSystem(null);
        target.setOwnerAccountName(null);
        target.setId(source.getId());
        target.setRoleId(source.getGrantedRole().getId());
        target.setRoleName(source.getGrantedRole().getName());
        target.setRoleDescription(source.getGrantedRole().getDescription());
        target.setInformationSystem(source.getGrantedRole().getInformationSystem().getName());
    }

}
