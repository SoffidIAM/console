/**
 * 
 */
package com.soffid.iam.spring;

import java.io.Serializable;
import java.sql.Connection;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.hibernate.CacheMode;
import org.hibernate.Criteria;
import org.hibernate.EntityMode;
import org.hibernate.Filter;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.ReplicationMode;
import org.hibernate.SQLQuery;
import org.hibernate.classic.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.stat.SessionStatistics;
import org.hibernate.type.Type;

/**
 * @author bubu
 *
 */
public class CustomSession implements Session
{

	private Session proxy;
	public CustomSession (Session proxy) {
		this.proxy = proxy;
	}
	public Object saveOrUpdateCopy (Object object) throws HibernateException
	{
		return proxy.saveOrUpdateCopy(object);
	}
	public Object saveOrUpdateCopy (Object object, Serializable id)
					throws HibernateException
	{
		return proxy.saveOrUpdateCopy(object, id);
	}
	public Object saveOrUpdateCopy (String entityName, Object object)
					throws HibernateException
	{
		return proxy.saveOrUpdateCopy(entityName, object);
	}
	public Object saveOrUpdateCopy (String entityName, Object object, Serializable id)
					throws HibernateException
	{
		return proxy.saveOrUpdateCopy(entityName, object, id);
	}
	public List find (String query) throws HibernateException
	{
		return proxy.find(query);
	}
	public List find (String query, Object value, Type type) throws HibernateException
	{
		return proxy.find(query, value, type);
	}
	public List find (String query, Object[] values, Type[] types)
					throws HibernateException
	{
		return proxy.find(query, values, types);
	}
	public Iterator iterate (String query) throws HibernateException
	{
		return proxy.iterate(query);
	}
	public Iterator iterate (String query, Object value, Type type)
					throws HibernateException
	{
		return proxy.iterate(query, value, type);
	}
	public Iterator iterate (String query, Object[] values, Type[] types)
					throws HibernateException
	{
		return proxy.iterate(query, values, types);
	}
	public Collection filter (Object collection, String filter)
					throws HibernateException
	{
		return proxy.filter(collection, filter);
	}
	public Collection filter (Object collection, String filter, Object value, Type type)
					throws HibernateException
	{
		return proxy.filter(collection, filter, value, type);
	}
	public Collection filter (Object collection, String filter, Object[] values,
					Type[] types) throws HibernateException
	{
		return proxy.filter(collection, filter, values, types);
	}
	public int delete (String query) throws HibernateException
	{
		return proxy.delete(query);
	}
	public int delete (String query, Object value, Type type) throws HibernateException
	{
		return proxy.delete(query, value, type);
	}
	public int delete (String query, Object[] values, Type[] types)
					throws HibernateException
	{
		return proxy.delete(query, values, types);
	}
	public Query createSQLQuery (String sql, String returnAlias, Class returnClass)
	{
		return proxy.createSQLQuery(sql, returnAlias, returnClass);
	}
	public Query createSQLQuery (String sql, String[] returnAliases,
					Class[] returnClasses)
	{
		return proxy.createSQLQuery(sql, returnAliases, returnClasses);
	}
	public void save (Object object, Serializable id) throws HibernateException
	{
		proxy.save(object, id);
	}
	public void save (String entityName, Object object, Serializable id)
					throws HibernateException
	{
		proxy.save(entityName, object, id);
	}
	public void update (Object object, Serializable id) throws HibernateException
	{
		proxy.update(object, id);
	}
	public void update (String entityName, Object object, Serializable id)
					throws HibernateException
	{
		proxy.update(entityName, object, id);
	}
	public EntityMode getEntityMode ()
	{
		return proxy.getEntityMode();
	}
	public org.hibernate.Session getSession (EntityMode entityMode)
	{
		return proxy.getSession(entityMode);
	}
	public void flush () throws HibernateException
	{
		proxy.flush();
	}
	public void setFlushMode (FlushMode flushMode)
	{
		proxy.setFlushMode(flushMode);
	}
	public FlushMode getFlushMode ()
	{
		return proxy.getFlushMode();
	}
	public void setCacheMode (CacheMode cacheMode)
	{
		proxy.setCacheMode(cacheMode);
	}
	public CacheMode getCacheMode ()
	{
		return proxy.getCacheMode();
	}
	public SessionFactory getSessionFactory ()
	{
		return proxy.getSessionFactory();
	}
	public Connection connection () throws HibernateException
	{
		return proxy.connection();
	}
	public Connection close () throws HibernateException
	{
		return proxy.close();
	}
	public void cancelQuery () throws HibernateException
	{
		proxy.cancelQuery();
	}
	public boolean isOpen ()
	{
		return proxy.isOpen();
	}
	public boolean isConnected ()
	{
		return proxy.isConnected();
	}
	public boolean isDirty () throws HibernateException
	{
		return proxy.isDirty();
	}
	public Serializable getIdentifier (Object object) throws HibernateException
	{
		return proxy.getIdentifier(object);
	}
	public boolean contains (Object object)
	{
		return proxy.contains(object);
	}
	public void evict (Object object) throws HibernateException
	{
		proxy.evict(object);
	}
	public Object load (Class theClass, Serializable id, LockMode lockMode)
					throws HibernateException
	{
		return proxy.load(theClass, id, lockMode);
	}
	public Object load (String entityName, Serializable id, LockMode lockMode)
					throws HibernateException
	{
		return proxy.load(entityName, id, lockMode);
	}
	public Object load (Class theClass, Serializable id) throws HibernateException
	{
		return proxy.load(theClass, id);
	}
	public Object load (String entityName, Serializable id) throws HibernateException
	{
		return proxy.load(entityName, id);
	}
	public void load (Object object, Serializable id) throws HibernateException
	{
		proxy.load(object, id);
	}
	public void replicate (Object object, ReplicationMode replicationMode)
					throws HibernateException
	{
		proxy.replicate(object, replicationMode);
	}
	public void replicate (String entityName, Object object,
					ReplicationMode replicationMode) throws HibernateException
	{
		proxy.replicate(entityName, object, replicationMode);
	}
	public Serializable save (Object object) throws HibernateException
	{
		return proxy.save(object);
	}
	public Serializable save (String entityName, Object object)
					throws HibernateException
	{
		return proxy.save(entityName, object);
	}
	public void saveOrUpdate (Object object) throws HibernateException
	{
		proxy.saveOrUpdate(object);
	}
	public void saveOrUpdate (String entityName, Object object)
					throws HibernateException
	{
		proxy.saveOrUpdate(entityName, object);
	}
	public void update (Object object) throws HibernateException
	{
		proxy.update(object);
	}
	public void update (String entityName, Object object) throws HibernateException
	{
		proxy.update(entityName, object);
	}
	public Object merge (Object object) throws HibernateException
	{
		return proxy.merge(object);
	}
	public Object merge (String entityName, Object object) throws HibernateException
	{
		return proxy.merge(entityName, object);
	}
	public void persist (Object object) throws HibernateException
	{
		proxy.persist(object);
	}
	public void persist (String entityName, Object object) throws HibernateException
	{
		proxy.persist(entityName, object);
	}
	public void delete (Object object) throws HibernateException
	{
		proxy.delete(object);
	}
	public void delete (String entityName, Object object) throws HibernateException
	{
		proxy.delete(entityName, object);
	}
	public void lock (Object object, LockMode lockMode) throws HibernateException
	{
		proxy.lock(object, lockMode);
	}
	public void lock (String entityName, Object object, LockMode lockMode)
					throws HibernateException
	{
		proxy.lock(entityName, object, lockMode);
	}
	public void refresh (Object object) throws HibernateException
	{
		proxy.refresh(object);
	}
	public void refresh (Object object, LockMode lockMode) throws HibernateException
	{
		proxy.refresh(object, lockMode);
	}
	public LockMode getCurrentLockMode (Object object) throws HibernateException
	{
		return proxy.getCurrentLockMode(object);
	}
	public Transaction beginTransaction () throws HibernateException
	{
		return proxy.beginTransaction();
	}
	public Transaction getTransaction ()
	{
		return proxy.getTransaction();
	}
	public Criteria createCriteria (Class persistentClass)
	{
		return proxy.createCriteria(persistentClass);
	}
	public Criteria createCriteria (Class persistentClass, String alias)
	{
		return proxy.createCriteria(persistentClass, alias);
	}
	public Criteria createCriteria (String entityName)
	{
		return proxy.createCriteria(entityName);
	}
	public Criteria createCriteria (String entityName, String alias)
	{
		return proxy.createCriteria(entityName, alias);
	}
	public Query createQuery (String queryString) throws HibernateException
	{
		return proxy.createQuery(queryString);
	}
	public SQLQuery createSQLQuery (String queryString) throws HibernateException
	{
		return proxy.createSQLQuery(queryString);
	}
	public Query createFilter (Object collection, String queryString)
					throws HibernateException
	{
		return proxy.createFilter(collection, queryString);
	}
	public Query getNamedQuery (String queryName) throws HibernateException
	{
		return proxy.getNamedQuery(queryName);
	}
	public void clear ()
	{
		proxy.clear();
	}
	public Object get (Class clazz, Serializable id) throws HibernateException
	{
		return proxy.get(clazz, id);
	}
	public Object get (Class clazz, Serializable id, LockMode lockMode)
					throws HibernateException
	{
		return proxy.get(clazz, id, lockMode);
	}
	public Object get (String entityName, Serializable id) throws HibernateException
	{
		return proxy.get(entityName, id);
	}
	public Object get (String entityName, Serializable id, LockMode lockMode)
					throws HibernateException
	{
		return proxy.get(entityName, id, lockMode);
	}
	public String getEntityName (Object object) throws HibernateException
	{
		return proxy.getEntityName(object);
	}
	public Filter enableFilter (String filterName)
	{
		return proxy.enableFilter(filterName);
	}
	public Filter getEnabledFilter (String filterName)
	{
		return proxy.getEnabledFilter(filterName);
	}
	public void disableFilter (String filterName)
	{
		proxy.disableFilter(filterName);
	}
	public SessionStatistics getStatistics ()
	{
		return proxy.getStatistics();
	}
	public void setReadOnly (Object entity, boolean readOnly)
	{
		proxy.setReadOnly(entity, readOnly);
	}
	public Connection disconnect () throws HibernateException
	{
		return proxy.disconnect();
	}
	public void reconnect () throws HibernateException
	{
		try 
		{
			proxy.reconnect();
		} catch (Exception e) 
		{
			// Ignore this error
		}
	}
	public void reconnect (Connection connection) throws HibernateException
	{
		proxy.reconnect(connection);
	}
	
	
}
