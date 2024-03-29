/**
 * 
 */
package com.soffid.iam.spring;

import java.io.Serializable;
import java.sql.Connection;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.WeakHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
import org.hibernate.event.FlushEvent;
import org.hibernate.event.FlushEventListener;
import org.hibernate.proxy.HibernateProxy;
import org.hibernate.proxy.HibernateProxyHelper;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.stat.SessionStatistics;
import org.hibernate.type.Type;

import com.soffid.iam.utils.Security;

/**
 * @author bubu
 *
 */
public class CustomSession implements Session
{
	Runtime runtime = Runtime.getRuntime();
	static int threshold2 = 15;
	static {
		try {
			threshold2 =  Integer.parseInt(System.getProperty("soffid.memory.limit2"));
		} catch (Exception e) {}
	}

	protected void checkMemoryUsage() {
		if (! Security.isSyncServer()) {
			long max = runtime.maxMemory();
			long used = runtime.totalMemory() - runtime.freeMemory();
			long free = max - used ;
			long pct = free * 100L / max;
			if (pct < 15) {
				runtime.gc();
				throw new RuntimeException(new OutOfMemoryError("System is running out of memory"));
			}
		}
	}

	Log log = LogFactory.getLog(getClass());
	
	private Session proxy;
	public CustomSession (Session proxy) {
		this.proxy = proxy;
	}
	public Object saveOrUpdateCopy (Object object) throws HibernateException
	{
		Object o = proxy.saveOrUpdateCopy(object);
		registerDirtyEntity(o);
		return o;
	}
	public Object saveOrUpdateCopy (Object object, Serializable id)
					throws HibernateException
	{
		Object o = proxy.saveOrUpdateCopy(object, id);
		registerDirtyEntity(o);
		return o;
	}
	public Object saveOrUpdateCopy (String entityName, Object object)
					throws HibernateException
	{
		Object o = proxy.saveOrUpdateCopy(entityName, object);;
		registerDirtyEntity(o);
		return o; 
	}
	public Object saveOrUpdateCopy (String entityName, Object object, Serializable id)
					throws HibernateException
	{
		Object o = proxy.saveOrUpdateCopy(entityName, object, id);
		registerDirtyEntity(object);
		return o;
	}
	public List find (String query) throws HibernateException
	{
		checkMemoryUsage();
		return proxy.find(query);
	}
	public List find (String query, Object value, Type type) throws HibernateException
	{
		checkMemoryUsage();
		return proxy.find(query, value, type);
	}
	public List find (String query, Object[] values, Type[] types)
					throws HibernateException
	{
		checkMemoryUsage();
		return proxy.find(query, values, types);
	}
	public Iterator iterate (String query) throws HibernateException
	{
		checkMemoryUsage();
		return proxy.iterate(query);
	}
	public Iterator iterate (String query, Object value, Type type)
					throws HibernateException
	{
		checkMemoryUsage();
		return proxy.iterate(query, value, type);
	}
	public Iterator iterate (String query, Object[] values, Type[] types)
					throws HibernateException
	{
		checkMemoryUsage();
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
		checkMemoryUsage();
		return proxy.createSQLQuery(sql, returnAlias, returnClass);
	}
	public Query createSQLQuery (String sql, String[] returnAliases,
					Class[] returnClasses)
	{
		checkMemoryUsage();
		return proxy.createSQLQuery(sql, returnAliases, returnClasses);
	}
	public void save (Object object, Serializable id) throws HibernateException
	{
		proxy.save(object, id);
		registerDirtyEntity(object);
	}
	public void save (String entityName, Object object, Serializable id)
					throws HibernateException
	{
		proxy.save(entityName, object, id);
		registerDirtyEntity(object);
	}
	public void update (Object object, Serializable id) throws HibernateException
	{
		proxy.update(object, id);
		registerDirtyEntity(object);
	}
	public void update (String entityName, Object object, Serializable id)
					throws HibernateException
	{
		proxy.update(entityName, object, id);
		registerDirtyEntity(object);
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
		entities.remove(proxy);
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
		checkMemoryUsage();
		return proxy.load(theClass, id, lockMode);
	}
	public Object load (String entityName, Serializable id, LockMode lockMode)
					throws HibernateException
	{
		checkMemoryUsage();
		return proxy.load(entityName, id, lockMode);
	}
	public Object load (Class theClass, Serializable id) throws HibernateException
	{
		checkMemoryUsage();
		return proxy.load(theClass, id);
	}
	public Object load (String entityName, Serializable id) throws HibernateException
	{
		checkMemoryUsage();
		return proxy.load(entityName, id);
	}
	public void load (Object object, Serializable id) throws HibernateException
	{
		checkMemoryUsage();
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
		Serializable o = proxy.save(object);
		registerDirtyEntity(object);
		return o;
	}
	public Serializable save (String entityName, Object object)
					throws HibernateException
	{
		Serializable o = proxy.save(entityName, object);
		getDirtyEntities().add(o);
		return o;
	}
	public void saveOrUpdate (Object object) throws HibernateException
	{
		registerDirtyEntity(object);
		proxy.saveOrUpdate(object);
	}
	public void saveOrUpdate (String entityName, Object object)
					throws HibernateException
	{
		proxy.saveOrUpdate(entityName, object);
		registerDirtyEntity(object);
	}
	public void update (Object object) throws HibernateException
	{
		proxy.update(object);
		registerDirtyEntity(object);
	}
	public void update (String entityName, Object object) throws HibernateException
	{
		proxy.update(entityName, object);
		registerDirtyEntity(object);
	}
	public Object merge (Object object) throws HibernateException
	{
		Object o = proxy.merge(object);
		registerDirtyEntity(o);
		return o;
	}
	public Object merge (String entityName, Object object) throws HibernateException
	{
		Object o = proxy.merge(entityName, object);
		registerDirtyEntity(o);
		return o;
	}
	public void persist (Object object) throws HibernateException
	{
		proxy.persist(object);
		registerDirtyEntity(object);
	}
	public void persist (String entityName, Object object) throws HibernateException
	{
		proxy.persist(entityName, object);
		registerDirtyEntity(object);
	}
	public void delete (Object object) throws HibernateException
	{
		proxy.delete(object);
		registerDirtyEntity(object);
	}
	public void delete (String entityName, Object object) throws HibernateException
	{
		proxy.delete(entityName, object);
		registerDirtyEntity(object);
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
		checkMemoryUsage();
		return proxy.createCriteria(persistentClass);
	}
	public Criteria createCriteria (Class persistentClass, String alias)
	{
		checkMemoryUsage();
		return proxy.createCriteria(persistentClass, alias);
	}
	public Criteria createCriteria (String entityName)
	{
		checkMemoryUsage();
		return proxy.createCriteria(entityName);
	}
	public Criteria createCriteria (String entityName, String alias)
	{
		checkMemoryUsage();
		return proxy.createCriteria(entityName, alias);
	}
	public Query createQuery (String queryString) throws HibernateException
	{
		checkMemoryUsage();
		return proxy.createQuery(queryString);
	}
	public SQLQuery createSQLQuery (String queryString) throws HibernateException
	{
		checkMemoryUsage();
		return proxy.createSQLQuery(queryString);
	}
	public Query createFilter (Object collection, String queryString)
					throws HibernateException
	{
		checkMemoryUsage();
		return proxy.createFilter(collection, queryString);
	}
	public Query getNamedQuery (String queryName) throws HibernateException
	{
		checkMemoryUsage();
		return proxy.getNamedQuery(queryName);
	}
	public void clear ()
	{
		proxy.clear();
	}
	public Object get (Class clazz, Serializable id) throws HibernateException
	{
		checkMemoryUsage();
		return proxy.get(clazz, id);
	}
	public Object get (Class clazz, Serializable id, LockMode lockMode)
					throws HibernateException
	{
		checkMemoryUsage();
		return proxy.get(clazz, id, lockMode);
	}
	public Object get (String entityName, Serializable id) throws HibernateException
	{
		checkMemoryUsage();
		return proxy.get(entityName, id);
	}
	public Object get (String entityName, Serializable id, LockMode lockMode)
					throws HibernateException
	{
		checkMemoryUsage();
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
	

	// Entity flush improvement
	static WeakHashMap<Session, Collection<Object>> entities = new WeakHashMap<Session, Collection<Object>>();
	Collection<Object> dirtyEntities = null;
	
	public Collection<Object> getDirtyEntities ()
	{
		if (dirtyEntities == null)
		{
			synchronized (entities)
			{
				dirtyEntities = entities.get(proxy);
				if (dirtyEntities == null)
				{
					dirtyEntities = new HashSet<Object>();
					 entities.put(proxy, dirtyEntities);
				}
			}
		}
		return dirtyEntities;
	}

	public static Collection<Object> getDirtyEntities (org.hibernate.Session session)
	{
		synchronized (entities)
		{
			return entities.get(session);
		}
	}

	private void registerDirtyEntity(Object object) {
		if (object instanceof HibernateProxy)
		{
			HibernateProxy p = (HibernateProxy) object;
			object = p.getHibernateLazyInitializer().getImplementation();
		}
		getDirtyEntities().add(object);
	}
}
