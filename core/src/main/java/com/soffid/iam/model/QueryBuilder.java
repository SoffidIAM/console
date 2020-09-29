package com.soffid.iam.model;

import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

import com.soffid.iam.ServiceLocator;

public class QueryBuilder {
	static SessionFactory sessionFactory = (SessionFactory) ServiceLocator.instance().getService("sessionFactory");
	
    public List query(org.springframework.orm.hibernate3.support.HibernateDaoSupport dao,
            String queryString, Parameter[] parameters) throws HibernateException {
        org.hibernate.Query queryObject = dao.getSessionFactory().getCurrentSession()
                .createQuery(queryString);
        for (int i = 0; parameters != null && i < parameters.length; i++) {
        	if (parameters[i].getValue() == null)
        		queryObject.setParameter(parameters[i].getName(), parameters[i].getValue(), 
            		org.hibernate.Hibernate.STRING);
        	else
        		queryObject.setParameter(parameters[i].getName(), parameters[i].getValue());
        }
        java.util.List results = queryObject.list();
        return results;

    }

    public List query(org.springframework.orm.hibernate3.support.HibernateDaoSupport dao,
            String queryString, Parameter[] parameters, int maxResults) throws HibernateException {
        org.hibernate.Query queryObject = dao.getSessionFactory().getCurrentSession()
                .createQuery(queryString);
        for (int i = 0; parameters != null && i < parameters.length; i++) {
        	if (parameters[i].getValue() == null)
        		queryObject.setParameter(parameters[i].getName(), parameters[i].getValue(), 
            		org.hibernate.Hibernate.STRING);
        	else
        		queryObject.setParameter(parameters[i].getName(), parameters[i].getValue());
        }
        queryObject.setMaxResults(maxResults);
        java.util.List results = queryObject.list();
        return results;

    }

    public List query(String queryString, Parameter[] parameters, Integer start, Integer maxResults) throws HibernateException {
        org.hibernate.Query queryObject = sessionFactory.getCurrentSession().createQuery(queryString);
        for (int i = 0; parameters != null && i < parameters.length; i++) {
        	if (parameters[i].getValue() == null)
        		queryObject.setParameter(parameters[i].getName(), parameters[i].getValue(), 
            		org.hibernate.Hibernate.STRING);
        	else
        		queryObject.setParameter(parameters[i].getName(), parameters[i].getValue());
        }
        if (start != null  && maxResults.intValue() > 0)
        	queryObject.setFirstResult(start);
        if (maxResults != null && maxResults.intValue() > 0)
        	queryObject.setMaxResults(maxResults);
        java.util.List results = queryObject.list();
        return results;

    }
}
