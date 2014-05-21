// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.caib.seycon.ng.model;
/**
 * @see es.caib.seycon.ng.model.NotificacioEntity
 */
public class NotificacioEntityDaoImpl
    extends es.caib.seycon.ng.model.NotificacioEntityDaoBase
{
    /**
     * @see es.caib.seycon.ng.model.NotificacioEntityDao#toNotificacio(es.caib.seycon.ng.model.NotificacioEntity, es.caib.seycon.ng.comu.Notificacio)
     */
    public void toNotificacio(
        es.caib.seycon.ng.model.NotificacioEntity source,
        es.caib.seycon.ng.comu.Notificacio target)
    {
        // @todo verify behavior of toNotificacio
        super.toNotificacio(source, target);
    }


    /**
     * @see es.caib.seycon.ng.model.NotificacioEntityDao#toNotificacio(es.caib.seycon.ng.model.NotificacioEntity)
     */
    public es.caib.seycon.ng.comu.Notificacio toNotificacio(final es.caib.seycon.ng.model.NotificacioEntity entity)
    {
        // @todo verify behavior of toNotificacio
        return super.toNotificacio(entity);
    }


    /**
     * Retrieves the entity object that is associated with the specified value object
     * from the object store. If no such entity object exists in the object store,
     * a new, blank entity is created
     */
    private es.caib.seycon.ng.model.NotificacioEntity loadNotificacioEntityFromNotificacio(es.caib.seycon.ng.comu.Notificacio notificacio)
    {
        // @todo implement loadNotificacioEntityFromNotificacio
        throw new java.lang.UnsupportedOperationException("es.caib.seycon.ng.model.loadNotificacioEntityFromNotificacio(es.caib.seycon.ng.comu.Notificacio) not yet implemented."); //$NON-NLS-1$

        /* A typical implementation looks like this:
        es.caib.seycon.ng.model.NotificacioEntity notificacioEntity = this.load(notificacio.getId());
        if (notificacioEntity == null)
        {
            notificacioEntity = es.caib.seycon.ng.model.NotificacioEntity.Factory.newInstance();
        }
        return notificacioEntity;
        */
    }

    
    /**
     * @see es.caib.seycon.ng.model.NotificacioEntityDao#notificacioToEntity(es.caib.seycon.ng.comu.Notificacio)
     */
    public es.caib.seycon.ng.model.NotificacioEntity notificacioToEntity(es.caib.seycon.ng.comu.Notificacio notificacio)
    {
        // @todo verify behavior of notificacioToEntity
        es.caib.seycon.ng.model.NotificacioEntity entity = this.loadNotificacioEntityFromNotificacio(notificacio);
        this.notificacioToEntity(notificacio, entity, true);
        return entity;
    }


    /**
     * @see es.caib.seycon.ng.model.NotificacioEntityDao#notificacioToEntity(es.caib.seycon.ng.comu.Notificacio, es.caib.seycon.ng.model.NotificacioEntity)
     */
    public void notificacioToEntity(
        es.caib.seycon.ng.comu.Notificacio source,
        es.caib.seycon.ng.model.NotificacioEntity target,
        boolean copyIfNull)
    {
        // @todo verify behavior of notificacioToEntity
        super.notificacioToEntity(source, target, copyIfNull);
    }

}