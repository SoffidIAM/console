/**
 * 
 */
package es.caib.seycon.ng.spring;

import java.io.Serializable;
import java.sql.Connection;
import java.util.Map;
import java.util.Set;

import javax.naming.NamingException;
import javax.naming.Reference;

import org.hibernate.HibernateException;
import org.hibernate.Interceptor;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.classic.Session;
import org.hibernate.engine.FilterDefinition;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.metadata.CollectionMetadata;
import org.hibernate.stat.Statistics;

/**
 * @author bubu
 *
 */
public class CustomSessionFactory implements SessionFactory
{
	private SessionFactory proxy;

	public  CustomSessionFactory ( SessionFactory proxy)
	{
		this.proxy = proxy;
	}

	public Reference getReference () throws NamingException
	{
		return proxy.getReference();
	}

	public Session openSession (Connection connection)
	{
		return new CustomSession(proxy.openSession(connection));
	}

	public Session openSession (Interceptor interceptor) throws HibernateException
	{
		return new CustomSession(proxy.openSession(interceptor));
	}

	public Session openSession (Connection connection, Interceptor interceptor)
	{
		return new CustomSession(proxy.openSession(connection, interceptor));
	}

	public Session openSession () throws HibernateException
	{
		return new CustomSession(proxy.openSession());
	}

	public Session getCurrentSession () throws HibernateException
	{
		return new CustomSession(proxy.getCurrentSession());
	}

	public ClassMetadata getClassMetadata (Class persistentClass)
					throws HibernateException
	{
		return proxy.getClassMetadata(persistentClass);
	}

	public ClassMetadata getClassMetadata (String entityName) throws HibernateException
	{
		return proxy.getClassMetadata(entityName);
	}

	public CollectionMetadata getCollectionMetadata (String roleName)
					throws HibernateException
	{
		return proxy.getCollectionMetadata(roleName);
	}

	public Map getAllClassMetadata () throws HibernateException
	{
		return proxy.getAllClassMetadata();
	}

	public Map getAllCollectionMetadata () throws HibernateException
	{
		return proxy.getAllCollectionMetadata();
	}

	public Statistics getStatistics ()
	{
		return proxy.getStatistics();
	}

	public void close () throws HibernateException
	{
		proxy.close();
	}

	public boolean isClosed ()
	{
		return proxy.isClosed();
	}

	public void evict (Class persistentClass) throws HibernateException
	{
		proxy.evict(persistentClass);
	}

	public void evict (Class persistentClass, Serializable id) throws HibernateException
	{
		proxy.evict(persistentClass, id);
	}

	public void evictEntity (String entityName) throws HibernateException
	{
		proxy.evictEntity(entityName);
	}

	public void evictEntity (String entityName, Serializable id)
					throws HibernateException
	{
		proxy.evictEntity(entityName, id);
	}

	public void evictCollection (String roleName) throws HibernateException
	{
		proxy.evictCollection(roleName);
	}

	public void evictCollection (String roleName, Serializable id)
					throws HibernateException
	{
		proxy.evictCollection(roleName, id);
	}

	public void evictQueries () throws HibernateException
	{
		proxy.evictQueries();
	}

	public void evictQueries (String cacheRegion) throws HibernateException
	{
		proxy.evictQueries(cacheRegion);
	}

	public StatelessSession openStatelessSession ()
	{
		return proxy.openStatelessSession();
	}

	public StatelessSession openStatelessSession (Connection connection)
	{
		return proxy.openStatelessSession(connection);
	}

	public Set getDefinedFilterNames ()
	{
		return proxy.getDefinedFilterNames();
	}

	public FilterDefinition getFilterDefinition (String filterName)
					throws HibernateException
	{
		return proxy.getFilterDefinition(filterName);
	}

}
