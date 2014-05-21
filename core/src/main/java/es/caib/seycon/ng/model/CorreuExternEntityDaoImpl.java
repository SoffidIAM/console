// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.caib.seycon.ng.model;

import java.sql.Timestamp;

import es.caib.seycon.ng.comu.CorreuExtern;
import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.ng.sync.engine.TaskHandler;
import es.caib.seycon.ng.utils.ExceptionTranslator;

/**
 * @see es.caib.seycon.ng.model.CorreuExternEntity
 */
public class CorreuExternEntityDaoImpl extends es.caib.seycon.ng.model.CorreuExternEntityDaoBase {
    public void create(es.caib.seycon.ng.model.CorreuExternEntity correuExtern)
            throws RuntimeException {
        try {
            DominiCorreuEntity dominiCorreu = correuExtern.getLlistaCorreu().getDomini();
            if (dominiCorreu != null) {
                if (dominiCorreu.getObsolet().compareTo("S") == 0) { //$NON-NLS-1$
                    throw new SeyconException(
                            String.format(
                                    Messages.getString("CorreuExternEntityDaoImpl.0"), //$NON-NLS-1$
                                    correuExtern.getAdreca()));
                }
            }
            super.create(correuExtern);
            TasqueEntity tasque = getTasqueEntityDao().newTasqueEntity();
            tasque.setData(new Timestamp(System.currentTimeMillis()));
            tasque.setTransa(TaskHandler.UPDATE_LIST_ALIAS);
            tasque.setAlies(correuExtern.getLlistaCorreu().getNom());
            if (correuExtern.getLlistaCorreu().getDomini() != null)
                tasque.setDomcor(correuExtern.getLlistaCorreu().getDomini().getCodi());
            getTasqueEntityDao().create(tasque);
            getSession(false).flush();
        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);

            throw new SeyconException(String.format(Messages.getString("CorreuExternEntityDaoImpl.1"), correuExtern.getAdreca(), message)); //$NON-NLS-1$
        }
    }

    public void remove(es.caib.seycon.ng.model.CorreuExternEntity correuExtern)
            throws RuntimeException {
        try {
            super.remove(correuExtern);
            TasqueEntity tasque = getTasqueEntityDao().newTasqueEntity();
            tasque.setData(new Timestamp(System.currentTimeMillis()));
            tasque.setTransa(TaskHandler.UPDATE_LIST_ALIAS);
            tasque.setAlies(correuExtern.getLlistaCorreu().getNom());
            if (correuExtern.getLlistaCorreu().getDomini() != null)
                tasque.setDomcor(correuExtern.getLlistaCorreu().getDomini().getCodi());
            getTasqueEntityDao().create(tasque);
            getSession(false).flush();
        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);
            throw new SeyconException(String.format(
                    Messages.getString("CorreuExternEntityDaoImpl.2"), //$NON-NLS-1$
                    correuExtern.getAdreca(), message));
        }
    }

    public void toCorreuExtern(es.caib.seycon.ng.model.CorreuExternEntity sourceEntity,
            es.caib.seycon.ng.comu.CorreuExtern targetVO) {
        super.toCorreuExtern(sourceEntity, targetVO);
        toCorreuExternCustom(sourceEntity, targetVO);

    }

    public void toCorreuExternCustom(es.caib.seycon.ng.model.CorreuExternEntity sourceEntity,
            es.caib.seycon.ng.comu.CorreuExtern targetVO) {
        LlistaCorreuEntity llistaCorreu = sourceEntity.getLlistaCorreu();
        if (llistaCorreu != null) {
            targetVO.setLlistaCorreuNom(llistaCorreu.getNom());
            DominiCorreuEntity dominiCorreu = llistaCorreu.getDomini();
            if (dominiCorreu != null) {
                targetVO.setCodiDomini(dominiCorreu.getCodi());
            }
        }
    }

    /**
     * @see es.caib.seycon.ng.model.CorreuExternEntityDao#toCorreuExtern(es.caib.seycon.ng.model.CorreuExternEntity)
     */
    public es.caib.seycon.ng.comu.CorreuExtern toCorreuExtern(
            final es.caib.seycon.ng.model.CorreuExternEntity entity) {
        CorreuExtern correuExtern = super.toCorreuExtern(entity);
        toCorreuExternCustom(entity, correuExtern);
        return correuExtern;
    }

    /**
     * Retrieves the entity object that is associated with the specified value
     * object from the object store. If no such entity object exists in the
     * object store, a new, blank entity is created
     */
    private es.caib.seycon.ng.model.CorreuExternEntity loadCorreuExternEntityFromCorreuExtern(
            es.caib.seycon.ng.comu.CorreuExtern correuExtern) {
        es.caib.seycon.ng.model.CorreuExternEntity correuExternEntity = null;
        if (correuExtern.getId() != null) {
            correuExternEntity = load(correuExtern.getId());
        }
        if (correuExternEntity == null) {
            correuExternEntity = newCorreuExternEntity();
        }
        return correuExternEntity;
    }

    /**
     * @see es.caib.seycon.ng.model.CorreuExternEntityDao#correuExternToEntity(es.caib.seycon.ng.CorreuExtern)
     */
    public es.caib.seycon.ng.model.CorreuExternEntity correuExternToEntity(
            es.caib.seycon.ng.comu.CorreuExtern correuExtern) {
        es.caib.seycon.ng.model.CorreuExternEntity entity = this
                .loadCorreuExternEntityFromCorreuExtern(correuExtern);
        correuExternToEntity(correuExtern, entity, true);
        return entity;
    }

    public void correuExternToEntityCustom(es.caib.seycon.ng.comu.CorreuExtern sourceVO,
            es.caib.seycon.ng.model.CorreuExternEntity targetEntity) {
        String nomLlistaCorreu = sourceVO.getLlistaCorreuNom();
        String codiDomini = sourceVO.getCodiDomini();
        if (nomLlistaCorreu != null && nomLlistaCorreu.trim().compareTo("") != 0) { //$NON-NLS-1$
            LlistaCorreuEntity llistaCorreuEntity = getLlistaCorreuEntityDao()
                    .findByNomAndCodiDomini(nomLlistaCorreu, codiDomini);
            if (llistaCorreuEntity != null) {
                targetEntity.setLlistaCorreu(llistaCorreuEntity);
            } else {
                throw new SeyconException(String.format(Messages.getString("CorreuExternEntityDaoImpl.3"), nomLlistaCorreu, codiDomini)); //$NON-NLS-1$
            }
        } else {
            targetEntity.setLlistaCorreu(null);
        }
    }

    /**
     * @see es.caib.seycon.ng.model.CorreuExternEntityDao#correuExternToEntity(es.caib.seycon.ng.CorreuExtern,
     *      es.caib.seycon.ng.model.CorreuExternEntity)
     */
    public void correuExternToEntity(es.caib.seycon.ng.comu.CorreuExtern sourceVO,
            es.caib.seycon.ng.model.CorreuExternEntity targetEntity, boolean copyIfNull) {
        super.correuExternToEntity(sourceVO, targetEntity, copyIfNull);
        correuExternToEntityCustom(sourceVO, targetEntity);
    }

}
