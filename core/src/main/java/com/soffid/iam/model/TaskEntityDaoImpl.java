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

import com.soffid.iam.model.TaskEntity;
import es.caib.seycon.ng.sync.engine.TaskHandler;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @see es.caib.seycon.ng.model.TasqueEntity
 */
public class TaskEntityDaoImpl extends com.soffid.iam.model.TaskEntityDaoBase {
    @Override
    public void create(TaskEntity tasqueEntity) {
        if (tasqueEntity.getStatus() == null)
            tasqueEntity.setStatus("P"); //$NON-NLS-1$
        if (tasqueEntity.getPriority() == null)
        {
    		String transactionCode = tasqueEntity.getTransaction();

    		if (transactionCode.equals(TaskHandler.VALIDATE_PASSWORD)
    				|| transactionCode.equals(TaskHandler.UPDATE_USER_PASSWORD)
    				|| transactionCode.equals(TaskHandler.UPDATE_PROPAGATED_PASSWORD)
    				|| transactionCode.equals(TaskHandler.PROPAGATE_PASSWORD)
    				|| transactionCode.equals(TaskHandler.UPDATE_PROPAGATED_PASSWORD_SINCRONO)
    				|| transactionCode.equals(TaskHandler.UPDATE_ACESS_CONTROL))
    		{
    			tasqueEntity.setPriority(0L);
    		}
    		else if (transactionCode.equals(TaskHandler.EXPIRE_USER_PASSWORD)
    				|| transactionCode.equals(TaskHandler.EXPIRE_USER_UNTRUSTED_PASSWORD)
    				|| transactionCode.equals(TaskHandler.RECONCILE_ROLE)
    				|| transactionCode.equals(TaskHandler.RECONCILE_ROLES)
    				|| transactionCode.equals(TaskHandler.RECONCILE_USER)
    				|| transactionCode.equals(TaskHandler.END_RECONCILE)
    				|| transactionCode.equals(TaskHandler.UPDATE_OBJECT))
    		{
    			tasqueEntity.setPriority(2L);
    		}

    		else
    		{
    			tasqueEntity.setPriority(1L);
    		}
        }
        super.create(tasqueEntity);
    }

    /**
     * @see es.caib.seycon.ng.model.TasqueEntityDao#toTasca(es.caib.seycon.ng.model.TasqueEntity,
     *      es.caib.seycon.ng.comu.Tasca)
     */
    public void toTasca(com.soffid.iam.model.TaskEntity source, es.caib.seycon.ng.comu.Tasca target) {
        // @todo verify behavior of toTasca
        super.toTasca(source, target);

        Calendar cal = GregorianCalendar.getInstance();
        cal.setTimeInMillis(source.getDate().getTime());
        target.setDataTasca(cal);
    }

    /**
     * @see es.caib.seycon.ng.model.TasqueEntityDao#toTasca(es.caib.seycon.ng.model.TasqueEntity)
     */
    public es.caib.seycon.ng.comu.Tasca toTasca(final com.soffid.iam.model.TaskEntity entity) {
        // @todo verify behavior of toTasca
        return super.toTasca(entity);
    }

    /**
     * Retrieves the entity object that is associated with the specified value
     * object from the object store. If no such entity object exists in the
     * object store, a new, blank entity is created
     */
    private com.soffid.iam.model.TaskEntity loadTasqueEntityFromTasca(es.caib.seycon.ng.comu.Tasca tasca) {
        TaskEntity tasqueEntity = null;

        if (tasca.getId() != null) {
            tasqueEntity = this.load(tasca.getId());
        }
        if (tasqueEntity == null) {
            tasqueEntity = newTaskEntity();
        }
        return tasqueEntity;
    }

    /**
     * @see es.caib.seycon.ng.model.TasqueEntityDao#tascaToEntity(es.caib.seycon.ng.comu.Tasca)
     */
    public com.soffid.iam.model.TaskEntity tascaToEntity(es.caib.seycon.ng.comu.Tasca tasca) {
        // @todo verify behavior of tascaToEntity
        com.soffid.iam.model.TaskEntity entity = this.loadTasqueEntityFromTasca(tasca);
        this.tascaToEntity(tasca, entity, true);
        return entity;
    }

    /**
     * @see es.caib.seycon.ng.model.TasqueEntityDao#tascaToEntity(es.caib.seycon.ng.comu.Tasca,
     *      es.caib.seycon.ng.model.TasqueEntity)
     */
    public void tascaToEntity(es.caib.seycon.ng.comu.Tasca source, com.soffid.iam.model.TaskEntity target, boolean copyIfNull) {
        // @todo verify behavior of tascaToEntity
        super.tascaToEntity(source, target, copyIfNull);

        // control de nulls
        if (source.getDataTasca() == null)
            throw new es.caib.seycon.ng.exception.SeyconException(
                    Messages.getString("TaskEntityDaoImpl.1")); //$NON-NLS-1$

        // mapeig adicional
        target.setDate(new Timestamp(source.getDataTasca().getTimeInMillis()));

    }

    /**
     * @see es.caib.seycon.ng.model.TasqueEntityDao#remove(es.caib.seycon.ng.model.TasqueEntity)
     */
    public void remove(com.soffid.iam.model.TaskEntity tasqueEntity) {
        super.remove(tasqueEntity);
        getSession().flush();
    }

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.model.TasqueEntityDaoBase#handleCreateNoFlush(es.caib.seycon.ng.model.TasqueEntity)
	 */
	@Override
    protected void handleCreateNoFlush(TaskEntity tasque) throws Exception {
		this.getHibernateTemplate().save(tasque);
	}

}
