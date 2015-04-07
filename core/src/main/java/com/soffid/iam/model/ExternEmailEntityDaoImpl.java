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

import com.soffid.iam.model.EmailDomainEntity;
import com.soffid.iam.model.EmailListEntity;
import com.soffid.iam.model.TaskEntity;
import es.caib.seycon.ng.comu.CorreuExtern;
import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.ng.sync.engine.TaskHandler;
import es.caib.seycon.ng.utils.ExceptionTranslator;
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
            TaskEntity tasque = getTaskEntityDao().newTaskEntity();
            tasque.setDate(new Timestamp(System.currentTimeMillis()));
            tasque.setTransaction(TaskHandler.UPDATE_LIST_ALIAS);
            tasque.setAlias(correuExtern.getMailList().getName());
            if (correuExtern.getMailList().getDomain() != null)
                tasque.setMailDomain(correuExtern.getMailList().getDomain().getCode());
            getTaskEntityDao().create(tasque);
            getSession(false).flush();
        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);

            throw new SeyconException(String.format(Messages.getString("ExternEmailEntityDaoImpl.1"), correuExtern.getAddress(), message)); //$NON-NLS-1$
        }
    }

    public void remove(com.soffid.iam.model.ExternEmailEntity correuExtern) throws RuntimeException {
        try {
            super.remove(correuExtern);
            TaskEntity tasque = getTaskEntityDao().newTaskEntity();
            tasque.setDate(new Timestamp(System.currentTimeMillis()));
            tasque.setTransaction(TaskHandler.UPDATE_LIST_ALIAS);
            tasque.setAlias(correuExtern.getMailList().getName());
            if (correuExtern.getMailList().getDomain() != null)
                tasque.setMailDomain(correuExtern.getMailList().getDomain().getCode());
            getTaskEntityDao().create(tasque);
            getSession(false).flush();
        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);
            throw new SeyconException(String.format(Messages.getString("ExternEmailEntityDaoImpl.2"), correuExtern.getAddress(), message));
        }
    }

    public void toCorreuExtern(com.soffid.iam.model.ExternEmailEntity sourceEntity, es.caib.seycon.ng.comu.CorreuExtern targetVO) {
        super.toCorreuExtern(sourceEntity, targetVO);
        toCorreuExternCustom(sourceEntity, targetVO);

    }

    public void toCorreuExternCustom(com.soffid.iam.model.ExternEmailEntity sourceEntity, es.caib.seycon.ng.comu.CorreuExtern targetVO) {
        EmailListEntity llistaCorreu = sourceEntity.getMailList();
        if (llistaCorreu != null) {
            targetVO.setLlistaCorreuNom(llistaCorreu.getName());
            EmailDomainEntity dominiCorreu = llistaCorreu.getDomain();
            if (dominiCorreu != null) {
                targetVO.setCodiDomini(dominiCorreu.getCode());
            }
        }
    }

    /**
     * @see es.caib.seycon.ng.model.CorreuExternEntityDao#toCorreuExtern(es.caib.seycon.ng.model.CorreuExternEntity)
     */
    public es.caib.seycon.ng.comu.CorreuExtern toCorreuExtern(final com.soffid.iam.model.ExternEmailEntity entity) {
        CorreuExtern correuExtern = super.toCorreuExtern(entity);
        toCorreuExternCustom(entity, correuExtern);
        return correuExtern;
    }

    /**
     * Retrieves the entity object that is associated with the specified value
     * object from the object store. If no such entity object exists in the
     * object store, a new, blank entity is created
     */
    private com.soffid.iam.model.ExternEmailEntity loadCorreuExternEntityFromCorreuExtern(es.caib.seycon.ng.comu.CorreuExtern correuExtern) {
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
    public com.soffid.iam.model.ExternEmailEntity correuExternToEntity(es.caib.seycon.ng.comu.CorreuExtern correuExtern) {
        com.soffid.iam.model.ExternEmailEntity entity = this.loadCorreuExternEntityFromCorreuExtern(correuExtern);
        correuExternToEntity(correuExtern, entity, true);
        return entity;
    }

    public void correuExternToEntityCustom(es.caib.seycon.ng.comu.CorreuExtern sourceVO, com.soffid.iam.model.ExternEmailEntity targetEntity) {
        String nomLlistaCorreu = sourceVO.getLlistaCorreuNom();
        String codiDomini = sourceVO.getCodiDomini();
        if (nomLlistaCorreu != null && nomLlistaCorreu.trim().compareTo("") != 0) { //$NON-NLS-1$
            EmailListEntity llistaCorreuEntity = getEmailListEntityDao().findByNameAndDomainCode(nomLlistaCorreu, codiDomini);
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
    public void correuExternToEntity(es.caib.seycon.ng.comu.CorreuExtern sourceVO, com.soffid.iam.model.ExternEmailEntity targetEntity, boolean copyIfNull) {
        super.correuExternToEntity(sourceVO, targetEntity, copyIfNull);
        correuExternToEntityCustom(sourceVO, targetEntity);
    }

}
