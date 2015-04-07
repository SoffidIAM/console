// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package com.soffid.iam.model;

import es.caib.seycon.ng.model.*;

import com.soffid.iam.model.ApplicationDomainEntity;
import com.soffid.iam.model.GroupEntity;
import com.soffid.iam.model.InformationSystemEntity;
import com.soffid.iam.model.RoleEntity;
import com.soffid.iam.model.RoleGroupEntity;
import com.soffid.iam.model.TaskEntity;
import es.caib.seycon.ng.comu.ContenidorRol;
import es.caib.seycon.ng.comu.Domini;
import es.caib.seycon.ng.comu.RolGrant;
import es.caib.seycon.ng.comu.RolsGrup;
import es.caib.seycon.ng.comu.TipusDomini;
import es.caib.seycon.ng.comu.ValorDomini;
import es.caib.seycon.ng.sync.engine.TaskHandler;
import es.caib.seycon.ng.utils.TipusContenidorRol;
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

    public RoleGroupEntity rolsGrupToEntity(RolsGrup rolsGrup) {
        return null;
    }

    public void rolsGrupToEntity(RolsGrup source, RoleGroupEntity target, boolean copyIfNull) {
        super.rolsGrupToEntity(source, target, copyIfNull);
    }

    @Override
    public void create(RoleGroupEntity rolsGrupEntity) {
        super.create(rolsGrupEntity);
        TaskEntity tasque = getTaskEntityDao().newTaskEntity();
        tasque.setDate(new Timestamp(System.currentTimeMillis()));
        tasque.setTransaction(TaskHandler.UPDATE_ROLE);
        tasque.setRole(rolsGrupEntity.getAssignedRole().getName());
        tasque.setDb(rolsGrupEntity.getAssignedRole().getDatabases().getCode());
        getTaskEntityDao().create(tasque);
        getSession().flush();
    }

    @Override
    public void update(RoleGroupEntity rolsGrupEntity) {
        RoleGroupEntity old = load(rolsGrupEntity.getId());
        TaskEntity tasque = getTaskEntityDao().newTaskEntity();
        tasque.setDate(new Timestamp(System.currentTimeMillis()));
        tasque.setTransaction(TaskHandler.UPDATE_ROLE);
        tasque.setRole(old.getAssignedRole().getName());
        tasque.setDb(old.getAssignedRole().getDatabases().getCode());
        getTaskEntityDao().create(tasque);
        super.update(rolsGrupEntity);
        tasque = getTaskEntityDao().newTaskEntity();
        tasque.setDate(new Timestamp(System.currentTimeMillis()));
        tasque.setTransaction(TaskHandler.UPDATE_ROLE);
        tasque.setRole(rolsGrupEntity.getAssignedRole().getName());
        tasque.setDb(rolsGrupEntity.getAssignedRole().getDatabases().getCode());
        getTaskEntityDao().create(tasque);
        getSession().flush();
    }

    @Override
    public void remove(RoleGroupEntity rolsGrupEntity) {
        super.remove(rolsGrupEntity);
        TaskEntity tasque = getTaskEntityDao().newTaskEntity();
        tasque.setDate(new Timestamp(System.currentTimeMillis()));
        tasque.setTransaction(TaskHandler.UPDATE_ROLE);
        tasque.setRole(rolsGrupEntity.getAssignedRole().getName());
        tasque.setDb(rolsGrupEntity.getAssignedRole().getDatabases().getCode());
        getTaskEntityDao().create(tasque);
        getSession().flush();
    }

    public void toRolsGrup(RoleGroupEntity source, RolsGrup target) {
        super.toRolsGrup(source, target);
        toRolsGrupCustom(source, target);
    }

    public RolsGrup toRolsGrup(RoleGroupEntity entity) {
        RolsGrup rolGrup = super.toRolsGrup(entity);
        toRolsGrupCustom(entity, rolGrup);
        return rolGrup;
    }

    private void toRolsGrupCustom(RoleGroupEntity sourceEntity, RolsGrup targetVO) {
        // TODO: Revisar transformaciones: se utilizan en roles de grupos de
        // seyconweb
        String tipusDomini = sourceEntity.getAssignedRole().getDomainType();
        if (tipusDomini == null || tipusDomini.trim().compareTo("") == 0) { //$NON-NLS-1$
            tipusDomini = TipusDomini.SENSE_DOMINI;
        }
        if (tipusDomini.compareTo(TipusDomini.DOMINI_APLICACIO) == 0) {
        	ValorDomini valorDomini;
            if (sourceEntity.getGrantedDomainValue() != null)
            {
            	valorDomini = getDomainValueEntityDao().toValorDomini(sourceEntity.getGrantedDomainValue());
            }
            else
            {
                ApplicationDomainEntity dominiAplicacio = sourceEntity.getAssignedRole().getApplicationDomain();
                valorDomini = new ValorDomini();
                // Le asignamos como nombre la descripción del dominio de aplicación
                valorDomini.setNomDomini(sourceEntity.getAssignedRole().getApplicationDomain().getName());
            }
        	targetVO.setValorDomini(valorDomini);
        } else if (tipusDomini.compareTo(TipusDomini.GRUPS) == 0
                || tipusDomini.compareTo(TipusDomini.GRUPS_USUARI) == 0) {
            ValorDomini valorDomini = new ValorDomini();
            valorDomini.setNomDomini(tipusDomini);
            if (sourceEntity.getGrantedGroupDomain() != null)
            {
            	valorDomini.setDescripcio(sourceEntity.getGrantedGroupDomain().getDescription());
            	valorDomini.setValor(sourceEntity.getGrantedGroupDomain().getCode());
            }
            valorDomini.setCodiExternDomini(null);
            targetVO.setValorDomini(valorDomini);
        } else if (tipusDomini.compareTo(TipusDomini.APLICACIONS) == 0) {
            ValorDomini valorDomini = new ValorDomini();
            valorDomini.setNomDomini(tipusDomini);
            if (sourceEntity.getGrantedGroupDomain() != null)
            {
            	valorDomini.setDescripcio(sourceEntity.getGrantedApplicationDomain().getName());
            	valorDomini.setValor(sourceEntity.getGrantedApplicationDomain().getCode());
            }
            valorDomini.setCodiExternDomini(null);
            targetVO.setValorDomini(valorDomini);
        } else if (tipusDomini.compareTo(TipusDomini.SENSE_DOMINI) == 0) {
            ValorDomini valorDomini = new ValorDomini();
            valorDomini.setCodiExternDomini(null);
            valorDomini.setDescripcio(TipusDomini.Descripcio.SENSE_DOMINI);
            valorDomini.setNomDomini(TipusDomini.SENSE_DOMINI);
            valorDomini.setValor(""); //$NON-NLS-1$
            // targetVO.setValorDomini(valorDomini); // No se muestra
        }

        targetVO.setNomRol(sourceEntity.getAssignedRole().getName());
        targetVO.setDescripcioRol(sourceEntity.getAssignedRole().getDescription());
        targetVO.setBaseDeDadesRol(sourceEntity.getAssignedRole().getDatabases().getCode());
        InformationSystemEntity aplicacio = sourceEntity.getAssignedRole().getApplication();
        if (aplicacio != null) {
            targetVO.setCodiAplicacio(aplicacio.getCode());
        }
        targetVO.setCodiGrup(sourceEntity.getOwnerGroup().getCode());
        targetVO.setDescripcioGrup(sourceEntity.getOwnerGroup().getDescription());

    }

    public RoleGroupEntity contenidorRolToEntity(ContenidorRol contenidorRol) {
        // TODO Auto-generated method stub
        return null;
    }

    public ContenidorRol toContenidorRol(RoleGroupEntity entity) {
        ContenidorRol contenidorRol = super.toContenidorRol(entity); // Pasamos
                                                                     // el id
        contenidorRol.setTipus(TipusContenidorRol.ROL_GRUP);
        // Información específica:
        RoleEntity rol = entity.getAssignedRole();
        GroupEntity grup = entity.getOwnerGroup();
        contenidorRol.setInfoContenidor(String.format(Messages.getString("RoleGroupEntityDaoImpl.0"), rol.getName(), grup.getCode()));
        return contenidorRol;
    }

    public RoleGroupEntity rolGrantToEntity(RolGrant rolGrant) {
        return load(rolGrant.getId());
    }

    @Override
    public void toRolGrant(RoleGroupEntity source, RolGrant target) {
        target.setDispatcher(source.getAssignedRole().getDatabases().getCode());
        String tipus = source.getAssignedRole().getDomainType();
        if (TipusDomini.SENSE_DOMINI.equals(tipus)) {
            target.setHasDomain(false);
            target.setDomainValue(null);
        } else if (TipusDomini.APLICACIONS.equals(tipus) && 
        		source.getGrantedApplicationDomain() != null) {
        	target.setHasDomain(true);
        	target.setDomainValue(source.getGrantedApplicationDomain().getCode());
        } else if ((TipusDomini.GRUPS.equals(tipus) || TipusDomini.GRUPS_USUARI.equals(tipus)) &&
        		source.getGrantedGroupDomain() != null) {
        	target.setHasDomain(true);
        	target.setDomainValue(source.getGrantedGroupDomain().getCode());
        } else if (TipusDomini.DOMINI_APLICACIO.equals(tipus) && source.getGrantedDomainValue() != null) {
        	target.setHasDomain(true);
        	target.setDomainValue(source.getGrantedDomainValue().getValue());
        } else {
            target.setHasDomain(false);
            target.setDomainValue(null);
        }
        target.setOwnerRol(null);
        target.setOwnerRolName(null);
        target.setOwnerGroup(source.getOwnerGroup().getCode());
        target.setOwnerDispatcher(null);
        target.setOwnerAccountName(null);
        target.setId(source.getId());
        target.setIdRol(source.getAssignedRole().getId());
        target.setRolName(source.getAssignedRole().getName());
        
    }

}
