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
import com.soffid.iam.utils.ConfigurationCache;

import java.security.SecureRandom;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;

/**
 * @see es.caib.seycon.ng.model.TasqueEntity
 */
public class TaskEntityDaoImpl extends com.soffid.iam.model.TaskEntityDaoBase {
	org.apache.commons.logging.Log log = LogFactory.getLog(getClass());
	
    @Override
    public void create(TaskEntity tasqueEntity) {
		String status = ConfigurationCache.getProperty("soffid.task.mode");

		tasqueEntity.setTenant( getTenantEntityDao().load(Security.getCurrentTenantId()));

		if ("readonly".equals( status ) || "manual".equals( status ))
			return;

    	if (checkDuplicate(tasqueEntity))
    		return;

		tasqueEntity.setStatus("P"); //$NON-NLS-1$
		tasqueEntity.setDate(new Timestamp(System.currentTimeMillis()));

    	TransactionStatus c = currentTransactionStatus();
    	if (c.readonly)
    		return;
    	
		tooMuchTasks(tasqueEntity);
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
        tasqueEntity.setServer(c.server);
        super.create(tasqueEntity);
    }

    private boolean checkDuplicate(TaskEntity tasqueEntity) {
    	if (tasqueEntity.getTransaction().equals( TaskHandler.UPDATE_USER))
    	{
    		Query q = getSession().createQuery("select distinct 1 from com.soffid.iam.model.TaskEntity as t "
    				+ "where t.server is null and t.transaction=? and t.systemName is null and t.user=? and t.tenant.id=?" );
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

    static ThreadLocal<TransactionStatus> transactionStatus = new ThreadLocal<TransactionStatus>();
    static String transactionSeed = Long.toString(System.currentTimeMillis())+"/";
    static int virtualCounter = 0;
    
    private TransactionStatus currentTransactionStatus () {
    	TransactionStatus current = transactionStatus.get();
    	if (current == null)
    	{
    		current = new TransactionStatus();
    		current.transactionHash = "";
    		transactionStatus.set(current);
    	}
    	
    	if (current.virtualId == null)
    	{
    		String hash = transactionSeed + getSession().getTransaction().hashCode();
    		if (! hash.equals(current.transactionHash)) // New standard transition 
    		{
    			current.transactionHash = hash;
    			current.count = 0;
    			current.exceeded = false;
    		}
    	}
    	return current;
    }
    
    private boolean tooMuchTasks (TaskEntity entity)
    {
    	TransactionStatus c = currentTransactionStatus();
    	if (c.readonly)
    		return true;
    	
    	if (c.exceeded)
    	{
    		entity.setStatus("X");
    		return true;
    	}
    	c.count ++ ;
    	entity.setSourceTransaction(c.virtualId == null ? c.transactionHash: c.virtualId);
    	String limit = ConfigurationCache.getProperty("soffid.task.limit");
    	if (limit == null)
    		return false;
    	if ( c.count > Integer.parseInt(limit))
    	{
    		c.exceeded = true;
    		getSession().flush();
    		Query q = getSession().createQuery(
    				"update com.soffid.iam.model.TaskEntity "
    				+ "set status='X' "
    				+ "where sourceTransaction=:sourceTransaction and tenant.id=:tenantId");
    		q.setParameter("sourceTransaction", c.virtualId == null ? c.transactionHash: c.virtualId);
    		q.setParameter("tenantId", Security.getCurrentTenantId());
    		q.executeUpdate();
    		entity.setStatus("X");
    	}
    	return c.exceeded;
    }
    
	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.model.TasqueEntityDaoBase#handleCreateNoFlush(es.caib.seycon.ng.model.TasqueEntity)
	 */
	@Override
    protected void handleCreateNoFlush(TaskEntity tasque) throws Exception {
		String status = ConfigurationCache.getProperty("soffid.task.mode");
		if ("readonly".equals( status ) || "manual".equals( status ))
			return;
    	TransactionStatus c = currentTransactionStatus();
    	if (c.readonly)
    		return;
		
        tasque.setServer(c.server);
		tasque.setTenant  ( getTenantEntityDao().load (com.soffid.iam.utils.Security.getCurrentTenantId()) );
    	if (checkDuplicate(tasque))
    		return;
		tasque.setStatus("P");

    	
		tooMuchTasks(tasque);
		this.getHibernateTemplate().save(tasque);
	}

    protected void handleCreateForce(TaskEntity tasque) throws Exception {
		String status = ConfigurationCache.getProperty("soffid.task.mode");
		if ("readonly".equals( status ))
			return;

		TransactionStatus c = currentTransactionStatus();
    	if (c.readonly)
    		return;
        tasque.setServer(c.server);
    	
		tooMuchTasks(tasque);
		tasque.setTenant  ( getTenantEntityDao().load (com.soffid.iam.utils.Security.getCurrentTenantId()) );
    	if (checkDuplicate(tasque))
    		return;
		tasque.setStatus("P");
		this.getHibernateTemplate().save(tasque);
		this.getHibernateTemplate().flush();
	}


	@Override
	protected void handleFinishVirtualSourceTransaction(String virtualTransactionId) throws Exception {
		TransactionStatus current = currentTransactionStatus();
		TransactionStatus c = current.previous;
		transactionStatus.set(c);
	}

	@Override
	protected String handleStartVirtualSourceTransaction() throws Exception {
		return handleStartVirtualSourceTransaction(false);
	}

	@Override
	protected void handleReleaseAll() throws Exception {
		Query q = getSession().createQuery(
				"update com.soffid.iam.model.TaskEntityImpl "
				+ "set status='P' "
				+ "where status='X' and tenant.id=:tenantId");
		q.setLong("tenantId", Security.getCurrentTenantId());
		q.executeUpdate();
	}

	@Override
	protected void handleCancelUnscheduled() throws Exception {
		Query q = getSession().createQuery(
				"delete from com.soffid.iam.model.TaskEntityImpl "
				+ "where server is null and tenant.id=:tenantId");
		q.setLong("tenantId", Security.getCurrentTenantId());
		q.executeUpdate();
	}

	@Override
	protected String handleStartVirtualSourceTransaction(boolean readonly) throws Exception {
		TransactionStatus current = currentTransactionStatus();
		TransactionStatus c = new TransactionStatus();
		c.previous = current;
		synchronized (transactionSeed)
		{
			c.virtualId = transactionSeed + "#" + virtualCounter;
			virtualCounter ++;
		}
		c.transactionHash = null;
		c.count = 0;
		if (readonly )
			c.readonly = true;
		transactionStatus.set(c);
		return c.virtualId;
	}

	@Override
	protected String handleStartVirtualSourceTransaction(boolean readonly, String server) throws Exception {
		TransactionStatus current = currentTransactionStatus();
		TransactionStatus c = new TransactionStatus();
		c.previous = current;
		synchronized (transactionSeed)
		{
			c.virtualId = transactionSeed + "#" + virtualCounter;
			virtualCounter ++;
		}
		c.transactionHash = null;
		c.count = 0;
		if (readonly )
			c.readonly = true;
		c.server = server;
		transactionStatus.set(c);
		return c.virtualId;
	}

	@Override
	protected void handleCancelUnscheduledCopies(TaskEntity entity) throws Exception {
		if (entity.getTransaction().equals("UpdateUser") && entity.getSystemName() == null)
		{
			Query q = getSession().createQuery("delete from com.soffid.iam.model.TaskEntityImpl "
					+ "where transaction=? and user=? and "
					+ "tenant.id=? and hash is null");
			q.setString(0, entity.getTransaction());
			q.setString(1, entity.getUser());
			q.setLong(2, entity.getTenant().getId());
			q.executeUpdate();
		}
		if (entity.getTransaction().equals("UpdateAccount"))
		{
			Query q = getSession().createQuery("delete from com.soffid.iam.model.TaskEntityImpl "
					+ "where transaction=? and user=? and systemName=? and "
					+ "tenant.id=? and hash is null");
			q.setString(0, entity.getTransaction());
			q.setString(1, entity.getUser());
			q.setString(2, entity.getSystemName());
			q.setLong(3, entity.getTenant().getId());
			q.executeUpdate();
		}
		if (entity.getTransaction().equals("UpdateRole") && entity.getDb() == null)
		{
			Query q = getSession().createQuery("delete from com.soffid.iam.model.TaskEntityImpl "
					+ "where transaction=? and role=? and db=? and "
					+ "tenant.id=? and hash is null");
			q.setString(0, entity.getTransaction());
			q.setString(1, entity.getRole());
			q.setString(2, entity.getDb());
			q.setLong(3, entity.getTenant().getId());
			q.executeUpdate();
		}
	}
}

class TransactionStatus {
	String virtualId;
	String server;
	String transactionHash;
	int count;
	boolean exceeded;
	boolean readonly;
	TransactionStatus previous;
}
