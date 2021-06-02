package com.soffid.iam.model;

import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

import com.soffid.iam.ServiceLocator;

import com.soffid.iam.model.criteria.CriteriaSearchConfiguration;

public class QueryBuilder {
	static SessionFactory sessionFactory = (SessionFactory) ServiceLocator.instance().getService("sessionFactory");
	
    public List query(org.springframework.orm.hibernate3.support.HibernateDaoSupport dao,
            String queryString, Parameter[] parameters) throws HibernateException {
    	return query(dao.getSessionFactory(), queryString, parameters);
    }

    public List query(org.springframework.orm.hibernate3.support.HibernateDaoSupport dao,
            String queryString, Parameter[] parameters, Integer maxResults) throws HibernateException {
    	return query(dao.getSessionFactory(), queryString, parameters, maxResults);
    }

    public List query(org.springframework.orm.hibernate3.support.HibernateDaoSupport dao,
            String queryString, Parameter[] parameters, CriteriaSearchConfiguration conf) throws HibernateException {
    	return query(dao.getSessionFactory(), queryString, parameters, conf);
    }
    
    public List query(String queryString, Parameter[] parameters) throws HibernateException {
    	return query(getSessionFactory(), queryString, parameters);
    }

    public List query(String queryString, Parameter[] parameters, Integer maxResults) throws HibernateException {
    	return query(getSessionFactory(), queryString, parameters, maxResults);
    }

    public List query(String queryString, Parameter[] parameters, CriteriaSearchConfiguration conf) throws HibernateException {
    	return query(getSessionFactory(), queryString, parameters, conf);
    }
    

    public List query(SessionFactory sessionFactory,
            String queryString, Parameter[] parameters) throws HibernateException {
        org.hibernate.Query queryObject = sessionFactory.getCurrentSession()
                .createQuery(queryString);
        for (int i = 0; parameters != null && i < parameters.length; i++) {
        	if (parameters[i].getValue() == null)
        		queryObject.setParameter(parameters[i].getName(), parameters[i].getValue(), 
            		org.hibernate.Hibernate.STRING);
        	else if (parameters[i].getValue() instanceof Collection) 
        		queryObject.setParameterList(parameters[i].getName(), (Collection) parameters[i].getValue());
        	else
        		queryObject.setParameter(parameters[i].getName(), parameters[i].getValue());
        }
        java.util.List results = queryObject.list();
        return results;

    }

    public List query(SessionFactory sessionFactory,
            String queryString, Parameter[] parameters, Integer maxResults) throws HibernateException {
        org.hibernate.Query queryObject = sessionFactory.getCurrentSession()
                .createQuery(queryString);
        for (int i = 0; parameters != null && i < parameters.length; i++) {
        	if (parameters[i].getValue() == null)
        		queryObject.setParameter(parameters[i].getName(), parameters[i].getValue(), 
            		org.hibernate.Hibernate.STRING);
        	else if (parameters[i].getValue() instanceof Collection) 
        		queryObject.setParameterList(parameters[i].getName(), (Collection) parameters[i].getValue());
        	else
        		queryObject.setParameter(parameters[i].getName(), parameters[i].getValue());
        }
        if (maxResults != null)
        	queryObject.setMaxResults(maxResults);
        java.util.List results = queryObject.list();
        return results;

    }

    public List query(SessionFactory sessionFactory,
            String queryString, Parameter[] parameters, CriteriaSearchConfiguration conf) throws HibernateException {
        org.hibernate.Query queryObject = sessionFactory.getCurrentSession()
                .createQuery(queryString);
        for (int i = 0; parameters != null && i < parameters.length; i++) {
        	if (parameters[i].getValue() == null)
        		queryObject.setParameter(parameters[i].getName(), parameters[i].getValue(), 
            		org.hibernate.Hibernate.STRING);
        	else if (parameters[i].getValue() instanceof Collection) 
        		queryObject.setParameterList(parameters[i].getName(), (Collection) parameters[i].getValue());
        	else
        		queryObject.setParameter(parameters[i].getName(), parameters[i].getValue());
        }
        if (conf.getMaximumResultSize() != null)
        	queryObject.setMaxResults(conf.getMaximumResultSize());
        if (conf.getFirstResult() != null)
            queryObject.setFirstResult(conf.getFirstResult());
        if (conf.getFetchSize() != null)
            queryObject.setFetchSize(conf.getFetchSize());
        java.util.List results = queryObject.list();
        return results;

    }

    protected SessionFactory getSessionFactory() { 
    	return (SessionFactory) ServiceLocator.instance().getService("sessionFactory");
    }
    		

}
