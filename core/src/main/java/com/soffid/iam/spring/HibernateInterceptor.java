/**
 * 
 */
package com.soffid.iam.spring;

import java.io.Serializable;
import java.util.Iterator;

import org.hibernate.CallbackException;
import org.hibernate.EntityMode;
import org.hibernate.Interceptor;
import org.hibernate.Transaction;
import org.hibernate.type.Type;

/**
 * @author bubu
 *
 */
public class HibernateInterceptor implements Interceptor
{

	/* (non-Javadoc)
	 * @see org.hibernate.Interceptor#onLoad(java.lang.Object, java.io.Serializable, java.lang.Object[], java.lang.String[], org.hibernate.type.Type[])
	 */
	public boolean onLoad (Object entity, Serializable id, Object[] state,
					String[] propertyNames, Type[] types) throws CallbackException
	{
		return false;
	}

	/* (non-Javadoc)
	 * @see org.hibernate.Interceptor#onFlushDirty(java.lang.Object, java.io.Serializable, java.lang.Object[], java.lang.Object[], java.lang.String[], org.hibernate.type.Type[])
	 */
	public boolean onFlushDirty (Object entity, Serializable id, Object[] currentState,
					Object[] previousState, String[] propertyNames, Type[] types)
					throws CallbackException
	{
		return false;
	}

	/* (non-Javadoc)
	 * @see org.hibernate.Interceptor#onSave(java.lang.Object, java.io.Serializable, java.lang.Object[], java.lang.String[], org.hibernate.type.Type[])
	 */
	public boolean onSave (Object entity, Serializable id, Object[] state,
					String[] propertyNames, Type[] types) throws CallbackException
	{
		return false;
	}

	/* (non-Javadoc)
	 * @see org.hibernate.Interceptor#onDelete(java.lang.Object, java.io.Serializable, java.lang.Object[], java.lang.String[], org.hibernate.type.Type[])
	 */
	public void onDelete (Object entity, Serializable id, Object[] state,
					String[] propertyNames, Type[] types) throws CallbackException
	{

	}

	/* (non-Javadoc)
	 * @see org.hibernate.Interceptor#onCollectionRecreate(java.lang.Object, java.io.Serializable)
	 */
	public void onCollectionRecreate (Object collection, Serializable key)
					throws CallbackException
	{

	}

	/* (non-Javadoc)
	 * @see org.hibernate.Interceptor#onCollectionRemove(java.lang.Object, java.io.Serializable)
	 */
	public void onCollectionRemove (Object collection, Serializable key)
					throws CallbackException
	{

	}

	/* (non-Javadoc)
	 * @see org.hibernate.Interceptor#onCollectionUpdate(java.lang.Object, java.io.Serializable)
	 */
	public void onCollectionUpdate (Object collection, Serializable key)
					throws CallbackException
	{

	}

	/* (non-Javadoc)
	 * @see org.hibernate.Interceptor#preFlush(java.util.Iterator)
	 */
	public void preFlush (Iterator entities) throws CallbackException
	{

	}

	/* (non-Javadoc)
	 * @see org.hibernate.Interceptor#postFlush(java.util.Iterator)
	 */
	public void postFlush (Iterator entities) throws CallbackException
	{

	}

	/* (non-Javadoc)
	 * @see org.hibernate.Interceptor#isTransient(java.lang.Object)
	 */
	public Boolean isTransient (Object entity)
	{
		return null;
	}

	/* (non-Javadoc)
	 * @see org.hibernate.Interceptor#findDirty(java.lang.Object, java.io.Serializable, java.lang.Object[], java.lang.Object[], java.lang.String[], org.hibernate.type.Type[])
	 */
	public int[] findDirty (Object entity, Serializable id, Object[] currentState,
					Object[] previousState, String[] propertyNames, Type[] types)
	{
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.hibernate.Interceptor#instantiate(java.lang.String, org.hibernate.EntityMode, java.io.Serializable)
	 */
	public Object instantiate (String entityName, EntityMode entityMode, Serializable id)
					throws CallbackException
	{
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.hibernate.Interceptor#getEntityName(java.lang.Object)
	 */
	public String getEntityName (Object object) throws CallbackException
	{
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.hibernate.Interceptor#getEntity(java.lang.String, java.io.Serializable)
	 */
	public Object getEntity (String entityName, Serializable id)
					throws CallbackException
	{
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.hibernate.Interceptor#afterTransactionBegin(org.hibernate.Transaction)
	 */
	public void afterTransactionBegin (Transaction tx)
	{
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.hibernate.Interceptor#beforeTransactionCompletion(org.hibernate.Transaction)
	 */
	public void beforeTransactionCompletion (Transaction tx)
	{
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.hibernate.Interceptor#afterTransactionCompletion(org.hibernate.Transaction)
	 */
	public void afterTransactionCompletion (Transaction tx)
	{
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.hibernate.Interceptor#onPrepareStatement(java.lang.String)
	 */
	public String onPrepareStatement (String sql)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
