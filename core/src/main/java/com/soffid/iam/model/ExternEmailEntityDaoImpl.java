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

import com.soffid.iam.api.ExternalName;
import com.soffid.iam.model.EmailDomainEntity;
import com.soffid.iam.model.EmailListEntity;
import com.soffid.iam.model.TaskEntity;
import com.soffid.iam.sync.engine.TaskHandler;
import com.soffid.iam.utils.ExceptionTranslator;

import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.ng.model.*;

import java.sql.Timestamp;

/**
 * @see es.caib.seycon.ng.model.CorreuExternEntity
 */
public class ExternEmailEntityDaoImpl extends com.soffid.iam.model.ExternEmailEntityDaoBase {
    public void create(com.soffid.iam.model.ExternEmailEntity correuExtern) throws RuntimeException {
        try {
            EmailDomainEntity dominiCorreu = correuExtern.getMailList().getDomain();
            if (dominiCorreu != null) {
                if (dominiCorreu.getObsolete().compareTo("S") == 0) { //$NON-NLS-1$
                    throw new SeyconException(String.format(Messages.getString("ExternEmailEntityDaoImpl.0"), correuExtern.getAddress()));
                }
            }
            super.create(correuExtern);
            getEmailListEntityDao().generateUpdateTasks(correuExtern.getMailList());
            getSession(false).flush();
        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);

            throw new SeyconException(String.format(Messages.getString("ExternEmailEntityDaoImpl.1"), correuExtern.getAddress(), message), e); //$NON-NLS-1$
        }
    }

    public void remove(com.soffid.iam.model.ExternEmailEntity correuExtern) throws RuntimeException {
        try {
            getEmailListEntityDao().generateUpdateTasks(correuExtern.getMailList());
            super.remove(correuExtern);
            getSession(false).flush();
        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);
            throw new SeyconException(String.format(Messages.getString("ExternEmailEntityDaoImpl.2"), correuExtern.getAddress(), message), e);
        }
    }

    public void toExternalName(com.soffid.iam.model.ExternEmailEntity sourceEntity, com.soffid.iam.api.ExternalName targetVO) {
        super.toExternalName(sourceEntity, targetVO);
        toCorreuExternCustom(sourceEntity, targetVO);

    }

    public void toCorreuExternCustom(com.soffid.iam.model.ExternEmailEntity sourceEntity, com.soffid.iam.api.ExternalName targetVO) {
        EmailListEntity llistaCorreu = sourceEntity.getMailList();
        if (llistaCorreu != null) {
            targetVO.setMailListName(llistaCorreu.getName());
            EmailDomainEntity dominiCorreu = llistaCorreu.getDomain();
            if (dominiCorreu != null) {
                targetVO.setDomainCode(dominiCorreu.getName());
            }
        }
    }

    /**
     * @see es.caib.seycon.ng.model.CorreuExternEntityDao#toCorreuExtern(es.caib.seycon.ng.model.CorreuExternEntity)
     */
    public com.soffid.iam.api.ExternalName toExternalName(final com.soffid.iam.model.ExternEmailEntity entity) {
        ExternalName correuExtern = super.toExternalName(entity);
        toCorreuExternCustom(entity, correuExtern);
        return correuExtern;
    }

    /**
     * Retrieves the entity object that is associated with the specified value
     * object from the object store. If no such entity object exists in the
     * object store, a new, blank entity is created
     */
    private com.soffid.iam.model.ExternEmailEntity loadCorreuExternEntityFromCorreuExtern(com.soffid.iam.api.ExternalName correuExtern) {
        com.soffid.iam.model.ExternEmailEntity correuExternEntity = null;
        if (correuExtern.getId() != null) {
            correuExternEntity = load(correuExtern.getId());
        }
        if (correuExternEntity == null) {
            correuExternEntity = newExternEmailEntity();
        }
        return correuExternEntity;
    }

    /**
     * @see es.caib.seycon.ng.model.CorreuExternEntityDao#correuExternToEntity(es.caib.seycon.ng.CorreuExtern)
     */
    public com.soffid.iam.model.ExternEmailEntity externalNameToEntity(com.soffid.iam.api.ExternalName correuExtern) {
        com.soffid.iam.model.ExternEmailEntity entity = this.loadCorreuExternEntityFromCorreuExtern(correuExtern);
        externalNameToEntity(correuExtern, entity, true);
        return entity;
    }

    public void correuExternToEntityCustom(com.soffid.iam.api.ExternalName sourceVO, com.soffid.iam.model.ExternEmailEntity targetEntity) {
        String nomLlistaCorreu = sourceVO.getMailListName();
        String codiDomini = sourceVO.getDomainCode();
        if (nomLlistaCorreu != null && nomLlistaCorreu.trim().compareTo("") != 0) { //$NON-NLS-1$
            EmailListEntity llistaCorreuEntity = getEmailListEntityDao().findByNameAndDomain(nomLlistaCorreu, codiDomini);
            if (llistaCorreuEntity != null) {
                targetEntity.setMailList(llistaCorreuEntity);
            } else {
                throw new SeyconException(String.format(Messages.getString("ExternEmailEntityDaoImpl.3"), nomLlistaCorreu, codiDomini)); //$NON-NLS-1$
            }
        } else {
            targetEntity.setMailList(null);
        }
    }

    /**
     * @see es.caib.seycon.ng.model.CorreuExternEntityDao#correuExternToEntity(es.caib.seycon.ng.CorreuExtern,
     *      es.caib.seycon.ng.model.CorreuExternEntity)
     */
    public void externalNameToEntity(com.soffid.iam.api.ExternalName sourceVO, com.soffid.iam.model.ExternEmailEntity targetEntity, boolean copyIfNull) {
        super.externalNameToEntity(sourceVO, targetEntity, copyIfNull);
        correuExternToEntityCustom(sourceVO, targetEntity);
    }

}
