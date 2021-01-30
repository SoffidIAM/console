package com.soffid.iam.bpm.service.scim;

import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.json.JSONException;

import com.soffid.iam.api.AsyncList;
import com.soffid.iam.api.DataType;
import com.soffid.iam.model.criteria.CriteriaSearchConfiguration;
import com.soffid.iam.service.AdditionalDataService;
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
	AdditionalDataService svc = com.soffid.iam.ServiceLocator.instance().getAdditionalDataService();
	private HQLQuery hql;
	Integer count = null;
	
	public ScimHelper (Class objectClass) {
		this.objectClass = objectClass;
	}
	
	public void search (String textFilter, String jsonQuery, Collection<Object> result) throws InternalErrorException, UnsupportedEncodingException, ClassNotFoundException, EvalException, JSONException, ParseException, TokenMgrError {
		String qs = "";

		String q2 = generateQuickSearchQuery(textFilter);
		
		if (jsonQuery != null && ! jsonQuery.trim().isEmpty())
		{
			if (q2 == null || q2.trim().isEmpty())
				q2 = jsonQuery;
			else
				q2 = "( " +q2 + " ) and "+jsonQuery;
		}
		
		AbstractExpression expr = ExpressionParser.parse(q2);
		hql = expr.generateHSQLString( objectClass );
		qs = hql.getWhereString().toString();
		if (tenantFilter != null) {
			if (qs.isEmpty())
				qs = "o." + tenantFilter + " = :tenantId";
			else
				qs = "("+qs+") and o." + tenantFilter + " = :tenantId";
			hql.setWhereString(new StringBuffer(qs));
		}
		
		SessionFactory sf = (SessionFactory) ServiceLocator.instance().getService("sessionFactory");
		String q = hql.toString();
		if (order != null)
			q = q + " order by "+ order;
		org.hibernate.Query queryObject = sf.getCurrentSession()
				.createQuery( q );
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
        count = null;
        
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
		
		count = new Integer(result.size());
	}

	String generateQuickSearchQuery (String text) throws InternalErrorException {
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
			Collection<DataType> list = svc.findDataTypesByObjectTypeAndName2(objectClass.getName(), null);
			for (DataType dt: list) {
				if (Boolean.TRUE.equals( dt.getSearchCriteria())) {
					if (first) first = false;
					else sb.append(" or ");
					sb.append(dt.getName() + " co \""+t+"\"");
				}
			}
			if (first) {
				for (String primaryAttribute: primaryAttributes)
				{
					if (first) first = false;
					else sb.append(" or ");
					sb.append(primaryAttribute + " co \""+t+"\"");
				}
			}
			sb.append(")");
		}
		return sb.toString();
	}
	
	public int count() {
		if (hql == null)
			return 0;

		if (count != null)
			return count.intValue();
		
		SessionFactory sf = (SessionFactory) ServiceLocator.instance().getService("sessionFactory");
		
		org.hibernate.Query queryObject = sf.getCurrentSession()
				.createQuery( hql.toCountString() );
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

		for (Object o: queryObject.list()) {
			if (o instanceof Long)
				return ((Long) o).intValue();
			else if (o instanceof Integer)
				return ((Integer) o).intValue();
		}
		return 0;
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
