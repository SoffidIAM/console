// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.caib.seycon.ng.model;

import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;

import es.caib.seycon.ng.comu.Renovacio;
import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.ng.utils.ExceptionTranslator;

/**
 * @see es.caib.seycon.ng.model.RenovacioEntity
 */
public class RenovacioEntityDaoImpl extends
        es.caib.seycon.ng.model.RenovacioEntityDaoBase {

    public void create(es.caib.seycon.ng.model.RenovacioEntity renovacio)
            throws RuntimeException {
        try {
            super.create(renovacio);
            getSession(false).flush();
        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("RenovacioEntityDaoImpl.0"),  //$NON-NLS-1$
					renovacio.getUsuari().getCodi(), 
					message));
        }
    }

    public void remove(es.caib.seycon.ng.model.RenovacioEntity renovacio)
            throws RuntimeException {
        try {
            super.remove(renovacio);
            getSession(false).flush();
        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("RenovacioEntityDaoImpl.1"),  //$NON-NLS-1$
					renovacio.getUsuari().getCodi(), 
					message));
        }
    }

    public void toRenovacio(
            es.caib.seycon.ng.model.RenovacioEntity sourceEntity,
            es.caib.seycon.ng.comu.Renovacio targetVO) {
        super.toRenovacio(sourceEntity, targetVO);
        toRenovacioCustom(sourceEntity, targetVO);
    }

    public void toRenovacioCustom(
            es.caib.seycon.ng.model.RenovacioEntity sourceEntity,
            es.caib.seycon.ng.comu.Renovacio targetVO) {
        Date sourceDate = sourceEntity.getData();
        targetVO.setData(new GregorianCalendar(sourceDate.getYear(), sourceDate
                .getMonth(), sourceDate.getDate(), sourceDate.getHours(),
                sourceDate.getMinutes(), sourceDate.getSeconds()));
        sourceDate = sourceEntity.getDataRenovacio();
        targetVO.setDataRenovacio(new GregorianCalendar(sourceDate.getYear(),
                sourceDate.getMonth(), sourceDate.getDate(), sourceDate
                        .getHours(), sourceDate.getMinutes(), sourceDate
                        .getSeconds()));
        targetVO.setCodiUsuari(sourceEntity.getUsuari().getCodi());
    }

    /**
     * @see es.caib.seycon.ng.model.RenovacioEntityDao#toRenovacio(es.caib.seycon.ng.model.RenovacioEntity)
     */
    public es.caib.seycon.ng.comu.Renovacio toRenovacio(
            final es.caib.seycon.ng.model.RenovacioEntity entity) {
        // @todo verify behavior of toRenovacio
        Renovacio renovacio = super.toRenovacio(entity);
        toRenovacioCustom(entity, renovacio);
        return renovacio;
    }

    /**
     * Retrieves the entity object that is associated with the specified value
     * object from the object store. If no such entity object exists in the
     * object store, a new, blank entity is created
     */
    private es.caib.seycon.ng.model.RenovacioEntity loadRenovacioEntityFromRenovacio(
            es.caib.seycon.ng.comu.Renovacio renovacio) {
        es.caib.seycon.ng.model.RenovacioEntity renovacioEntity = null;
        /*
         * Las renovaciones solo se crean, no se actualizan
         */
        /*
         * TODROP Collection renovacions = this.findByFiltre(renovacio.getData()
         * .getTime(), renovacio.getActiu(), renovacio.getDataRenovacio()
         * .getTime(), renovacio.getCodiUsuari()); if (renovacions != null) {
         * Iterator iterator = renovacions.iterator(); if (iterator.hasNext()) {
         * renovacioEntity = (RenovacioEntity) iterator.next(); } }
         */
        if (renovacioEntity == null) {
            renovacioEntity = newRenovacioEntity();
        }
        return renovacioEntity;
    }

    /**
     * @see es.caib.seycon.ng.model.RenovacioEntityDao#renovacioToEntity(es.caib.seycon.ng.comu.Renovacio)
     */
    public es.caib.seycon.ng.model.RenovacioEntity renovacioToEntity(
            es.caib.seycon.ng.comu.Renovacio renovacio) {
        // @todo verify behavior of renovacioToEntity
        es.caib.seycon.ng.model.RenovacioEntity entity = this
                .loadRenovacioEntityFromRenovacio(renovacio);
        this.renovacioToEntity(renovacio, entity, true);
        return entity;
    }

    public void renovacioToEntityCustom(
            es.caib.seycon.ng.comu.Renovacio sourceVO,
            es.caib.seycon.ng.model.RenovacioEntity targetEntity) {
        UsuariEntity usuariEntity = getUsuariEntityDao().findByCodi(
                sourceVO.getCodiUsuari());
        targetEntity.setUsuari(usuariEntity);
        targetEntity.setData(sourceVO.getData() == null ? sourceVO.getData()
                .getTime() : null);
        targetEntity.setDataRenovacio(sourceVO.getData() == null ? sourceVO
                .getData().getTime() : null);
    }

    /**
     * @see es.caib.seycon.ng.model.RenovacioEntityDao#renovacioToEntity(es.caib.seycon.ng.comu.Renovacio,
     *      es.caib.seycon.ng.model.RenovacioEntity)
     */
    public void renovacioToEntity(es.caib.seycon.ng.comu.Renovacio sourceVO,
            es.caib.seycon.ng.model.RenovacioEntity targetEntity,
            boolean copyIfNull) {
        // @todo verify behavior of renovacioToEntity
        super.renovacioToEntity(sourceVO, targetEntity, copyIfNull);
        renovacioToEntityCustom(sourceVO, targetEntity);
    }

}
