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
    public void toReminder(com.soffid.iam.model.NoticeEntity source, com.soffid.iam.api.Reminder target) {
        // @todo verify behavior of toNotificacio
        super.toReminder(source, target);
    }


    /**
     * @see es.caib.seycon.ng.model.NotificacioEntityDao#toNotificacio(es.caib.seycon.ng.model.NotificacioEntity)
     */
    public com.soffid.iam.api.Reminder toReminder(final com.soffid.iam.model.NoticeEntity entity) {
        // @todo verify behavior of toNotificacio
        return super.toReminder(entity);
    }


    /**
     * Retrieves the entity object that is associated with the specified value object
     * from the object store. If no such entity object exists in the object store,
     * a new, blank entity is created
     */
    private com.soffid.iam.model.NoticeEntity loadNotificacioEntityFromNotificacio(com.soffid.iam.api.Reminder notificacio) {
        throw new java.lang.UnsupportedOperationException("com.soffid.iam.model.NoticeEntityDao.loadNotificacioEntityFromNotificacio(es.caib.seycon.ng.comu.Notificacio) not yet implemented."); //$NON-NLS-1$

    }

    
    /**
     * @see es.caib.seycon.ng.model.NotificacioEntityDao#notificacioToEntity(es.caib.seycon.ng.comu.Notificacio)
     */
    public com.soffid.iam.model.NoticeEntity reminderToEntity(com.soffid.iam.api.Reminder notificacio) {
        // @todo verify behavior of notificacioToEntity
        com.soffid.iam.model.NoticeEntity entity = this.loadNotificacioEntityFromNotificacio(notificacio);
        this.reminderToEntity(notificacio, entity, true);
        return entity;
    }


    /**
     * @see es.caib.seycon.ng.model.NotificacioEntityDao#notificacioToEntity(es.caib.seycon.ng.comu.Notificacio, es.caib.seycon.ng.model.NotificacioEntity)
     */
    public void reminderToEntity(com.soffid.iam.api.Reminder source, com.soffid.iam.model.NoticeEntity target, boolean copyIfNull) {
        // @todo verify behavior of notificacioToEntity
        super.reminderToEntity(source, target, copyIfNull);
    }

}