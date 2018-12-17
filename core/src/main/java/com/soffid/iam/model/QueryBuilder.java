package com.soffid.iam.model;

import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;

public class QueryBuilder {
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
}
