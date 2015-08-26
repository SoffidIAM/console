// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package com.soffid.iam.model;

import es.caib.seycon.ng.model.*;
/**
 * @see es.caib.seycon.ng.model.NotificacioEntity
 */
public class NoticeEntityDaoImpl
    extends com.soffid.iam.model.NoticeEntityDaoBase
{
    /**
     * @see es.caib.seycon.ng.model.NotificacioEntityDao#toNotificacio(es.caib.seycon.ng.model.NotificacioEntity, es.caib.seycon.ng.comu.Notificacio)
     */
    public void toNotificacio(com.soffid.iam.model.NoticeEntity source, es.caib.seycon.ng.comu.Notificacio target) {
        // @todo verify behavior of toNotificacio
        super.toNotificacio(source, target);
    }


    /**
     * @see es.caib.seycon.ng.model.NotificacioEntityDao#toNotificacio(es.caib.seycon.ng.model.NotificacioEntity)
     */
    public es.caib.seycon.ng.comu.Notificacio toNotificacio(final com.soffid.iam.model.NoticeEntity entity) {
        // @todo verify behavior of toNotificacio
        return super.toNotificacio(entity);
    }


    /**
     * Retrieves the entity object that is associated with the specified value object
     * from the object store. If no such entity object exists in the object store,
     * a new, blank entity is created
     */
    private com.soffid.iam.model.NoticeEntity loadNotificacioEntityFromNotificacio(es.caib.seycon.ng.comu.Notificacio notificacio) {
        throw new java.lang.UnsupportedOperationException("com.soffid.iam.model.NoticeEntityDao.loadNotificacioEntityFromNotificacio(es.caib.seycon.ng.comu.Notificacio) not yet implemented."); //$NON-NLS-1$

    }

    
    /**
     * @see es.caib.seycon.ng.model.NotificacioEntityDao#notificacioToEntity(es.caib.seycon.ng.comu.Notificacio)
     */
    public com.soffid.iam.model.NoticeEntity notificacioToEntity(es.caib.seycon.ng.comu.Notificacio notificacio) {
        // @todo verify behavior of notificacioToEntity
        com.soffid.iam.model.NoticeEntity entity = this.loadNotificacioEntityFromNotificacio(notificacio);
        this.notificacioToEntity(notificacio, entity, true);
        return entity;
    }


    /**
     * @see es.caib.seycon.ng.model.NotificacioEntityDao#notificacioToEntity(es.caib.seycon.ng.comu.Notificacio, es.caib.seycon.ng.model.NotificacioEntity)
     */
    public void notificacioToEntity(es.caib.seycon.ng.comu.Notificacio source, com.soffid.iam.model.NoticeEntity target, boolean copyIfNull) {
        // @todo verify behavior of notificacioToEntity
        super.notificacioToEntity(source, target, copyIfNull);
    }

}