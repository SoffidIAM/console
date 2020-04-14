package com.soffid.iam.bpm.service.scim;

import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.json.JSONException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.soffid.iam.api.AsyncList;
import com.soffid.iam.api.Network;
import com.soffid.iam.model.Parameter;
import com.soffid.iam.model.QueryBuilder;
import com.soffid.iam.model.criteria.CriteriaSearchConfiguration;
import com.soffid.iam.utils.Security;
import com.soffid.iam.utils.TimeOutUtils;
import com.soffid.scimquery.EvalException;
import com.soffid.scimquery.HQLQuery;
import com.soffid.scimquery.expr.AbstractExpression;
import com.soffid.scimquery.parser.ExpressionParser;
import com.soffid.scimquery.parser.ParseException;
import com.soffid.scimquery.parser.TokenMgrError;

import es.caib.seycon.ng.ServiceLocator;
import es.caib.seycon.ng.exception.InternalErrorException;

public class ScimHelper {
	String[] primaryAttributes = null;
	CriteriaSearchConfiguration config = null;
	String order = null;
	String tenantFilter;
	private Class objectClass;
	private ValueObjectGenerator generator;
	
	public ScimHelper (Class objectClass) {
		this.objectClass = objectClass;
	}
	
	public void search (String textFilter, String jsonQuery, Collection<Object> result) throws InternalErrorException, UnsupportedEncodingException, ClassNotFoundException, EvalException, JSONException, ParseException, TokenMgrError {
		String qs = "";

		String q2 = generateQuickSearchQuery(textFilter);
		
		if (jsonQuery != null && ! jsonQuery.trim().isEmpty())
			q2 = "( " +q2 + " ) and "+jsonQuery;
		
		AbstractExpression expr = ExpressionParser.parse(q2);
		HQLQuery hql = expr.generateHSQLString( objectClass );
		qs = hql.getWhereString().toString();
		if (tenantFilter != null) {
			if (qs.isEmpty())
				qs = "o." + tenantFilter + " = :tenantId";
			else
				qs = "("+qs+") and o." + tenantFilter + " = :tenantId";
			hql.setWhereString(new StringBuffer(qs));
		}
		

		SessionFactory sf = (SessionFactory) ServiceLocator.instance().getService("sessionFactory");
		org.hibernate.Query queryObject = sf.getCurrentSession()
				.createQuery( hql.toString() );
		int i = 0;
		Map<String, Object> params = hql.getParameters();
		for (String s : params.keySet())
		{
			Object v = params.get(s);
			if (v == null)
				queryObject.setParameter(s, v, 
						org.hibernate.Hibernate.STRING);
			else
				queryObject.setParameter(s, v);
		}
		queryObject.setParameter("tenantId", Security.getCurrentTenantId());
		TimeOutUtils tou = new TimeOutUtils();

		if (config != null)
		{
			if (config.getMaximumResultSize() != null)
				queryObject.setMaxResults(config.getMaximumResultSize());
			if (config.getFirstResult() != null)
				queryObject.setFirstResult(config.getFirstResult());
			if (config.getFetchSize() != null)
				queryObject.setFetchSize(config.getFetchSize());
		}
		
        java.util.List l = queryObject.list();

		for (Object e : l) {
			if (result instanceof AsyncList)
			{
				if (((AsyncList) result).isCancelled())
					return;
			}
			else
			{
				tou.checkTimeOut();
			}
			
			Object vo = generator.toValueObject(e);
			if (vo != null)
				result.add(vo);
		}
	}

	String generateQuickSearchQuery (String text) {
		if (text == null )
			return  "";
		String[] split = text.trim().split(" +");
		
		StringBuffer sb = new StringBuffer("");
		for (int i = 0; i < split.length; i++)
		{
			String t = split[i].replaceAll("\\\\","\\\\\\\\").replaceAll("\"", "\\\\\"");
			if (sb.length() > 0)
				sb.append(" and ");
			sb.append("(");
			boolean first = true;
			for (String primaryAttribute: primaryAttributes)
			{
				if (first) first = false;
				else sb.append(" or ");
				sb.append(primaryAttribute + " co \""+t+"\"");
			}
			sb.append(")");
		}
		return sb.toString();
	}

	public String[] getPrimaryAttributes() {
		return primaryAttributes;
	}

	public void setPrimaryAttributes(String[] primaryAttributes) {
		this.primaryAttributes = primaryAttributes;
	}

	public CriteriaSearchConfiguration getConfig() {
		return config;
	}

	public void setConfig(CriteriaSearchConfiguration config) {
		this.config = config;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getTenantFilter() {
		return tenantFilter;
	}

	public void setTenantFilter(String tenantFilter) {
		this.tenantFilter = tenantFilter;
	}

	public ValueObjectGenerator getGenerator() {
		return generator;
	}

	public void setGenerator(ValueObjectGenerator generator) {
		this.generator = generator;
	}
}
