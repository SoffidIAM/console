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
import es.caib.seycon.ng.utils.Security;

import com.soffid.iam.model.TaskEntity;
import com.soffid.iam.sync.engine.TaskHandler;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.hibernate.Query;

/**
 * @see es.caib.seycon.ng.model.TasqueEntity
 */
public class TaskEntityDaoImpl extends com.soffid.iam.model.TaskEntityDaoBase {
    @Override
    public void create(TaskEntity tasqueEntity) {
    	tasqueEntity.setTenant( getTenantEntityDao().load(Security.getCurrentTenantId()));
    	if (checkDuplicate(tasqueEntity))
    		return;
    	
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

    private boolean checkDuplicate(TaskEntity tasqueEntity) {
    	if (tasqueEntity.getTransaction().equals( TaskHandler.UPDATE_USER))
    	{
    		Query q = getSession().createQuery("select distinct 1 from com.soffid.iam.model.TaskEntity as t "
    				+ "where t.server is null and t.systemName is null and t.transaction=? and t.user=? and t.tenant.id=?" );
    		q.setString(0, tasqueEntity.getTransaction());
    		q.setString(1, tasqueEntity.getUser());
    		q.setLong(2, tasqueEntity.getTenant().getId());
    		if (! q.list().isEmpty())
    			return true;
    	}
    	else if (tasqueEntity.getTransaction().equals( TaskHandler.UPDATE_GROUP))
    	{
    		Query q = getSession().createQuery("select distinct 1 from com.soffid.iam.model.TaskEntity as t "
    				+ "where t.server is null and t.systemName is null and t.transaction=? and t.group=?  and t.tenant.id=?" );
    		q.setString(0, tasqueEntity.getTransaction());
    		q.setString(1, tasqueEntity.getGroup());
//    		q.setString(2, tasqueEntity.getBd());
    		q.setLong(2, tasqueEntity.getTenant().getId());
    		if (! q.list().isEmpty())
    			return true;
    	}
    	else if (tasqueEntity.getTransaction().equals( TaskHandler.UPDATE_ROLE))
    	{
    		Query q = getSession().createQuery("select distinct 1 from com.soffid.iam.model.TaskEntity as t "
    				+ "where t.server is null and t.systemName is null and t.transaction=? and t.role=? and t.db=?  and t.tenant.id=?" );
    		q.setString(0, tasqueEntity.getTransaction());
    		q.setString(1, tasqueEntity.getRole());
    		q.setString(2, tasqueEntity.getDb());
    		q.setLong(3, tasqueEntity.getTenant().getId());
    		if (! q.list().isEmpty())
    			return true;
    	}
    	else if (tasqueEntity.getTransaction().equals( TaskHandler.UPDATE_ACCOUNT))
    	{
    		Query q = getSession().createQuery("select distinct 1 from com.soffid.iam.model.TaskEntity as t "
    				+ "where t.server is null and t.transaction=? and t.user=? and t.systemName=?  and t.tenant.id=?" );
    		q.setString(0, tasqueEntity.getTransaction());
    		q.setString(1, tasqueEntity.getUser());
    		q.setString(2, tasqueEntity.getSystemName());
    		q.setLong(3, tasqueEntity.getTenant().getId());
    		if (! q.list().isEmpty())
    			return true;
    	}
		return false;
	}


    /**
     * @see es.caib.seycon.ng.model.TasqueEntityDao#toTasca(es.caib.seycon.ng.model.TasqueEntity,
     *      es.caib.seycon.ng.comu.Tasca)
     */
    public void toTask(com.soffid.iam.model.TaskEntity source, com.soffid.iam.api.Task target) {
        // @todo verify behavior of toTasca
        super.toTask(source, target);

        Calendar cal = GregorianCalendar.getInstance();
        cal.setTimeInMillis(source.getDate().getTime());
        target.setTaskDate(cal);
    }

    /**
     * @see es.caib.seycon.ng.model.TasqueEntityDao#toTasca(es.caib.seycon.ng.model.TasqueEntity)
     */
    public com.soffid.iam.api.Task toTask(final com.soffid.iam.model.TaskEntity entity) {
        // @todo verify behavior of toTasca
        return super.toTask(entity);
    }

    /**
     * Retrieves the entity object that is associated with the specified value
     * object from the object store. If no such entity object exists in the
     * object store, a new, blank entity is created
     */
    private com.soffid.iam.model.TaskEntity loadTasqueEntityFromTasca(com.soffid.iam.api.Task tasca) {
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
    public com.soffid.iam.model.TaskEntity taskToEntity(com.soffid.iam.api.Task tasca) {
        // @todo verify behavior of tascaToEntity
        com.soffid.iam.model.TaskEntity entity = this.loadTasqueEntityFromTasca(tasca);
        this.taskToEntity(tasca, entity, true);
        return entity;
    }

    /**
     * @see es.caib.seycon.ng.model.TasqueEntityDao#tascaToEntity(es.caib.seycon.ng.comu.Tasca,
     *      es.caib.seycon.ng.model.TasqueEntity)
     */
    public void taskToEntity(com.soffid.iam.api.Task source, com.soffid.iam.model.TaskEntity target, boolean copyIfNull) {
        // @todo verify behavior of tascaToEntity
        super.taskToEntity(source, target, copyIfNull);

        // control de nulls
        if (source.getTaskDate() == null)
            throw new es.caib.seycon.ng.exception.SeyconException(
                    Messages.getString("TaskEntityDaoImpl.1")); //$NON-NLS-1$

        // mapeig adicional
        target.setDate(new Timestamp(source.getTaskDate().getTimeInMillis()));

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
		tasque.setTenant  ( getTenantEntityDao().load (com.soffid.iam.utils.Security.getCurrentTenantId()) );
    	if (checkDuplicate(tasque))
    		return;
		this.getHibernateTemplate().save(tasque);
	}

}
