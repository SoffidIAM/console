package com.soffid.iam.bpm.service.scim;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.JSONException;

import com.graphbuilder.struc.LinkedList;
import com.soffid.iam.api.AsyncList;
import com.soffid.iam.api.CustomObject;
import com.soffid.iam.api.CustomObjectType;
import com.soffid.iam.api.DataType;
import com.soffid.iam.model.CustomDialect;
import com.soffid.iam.model.criteria.CriteriaSearchConfiguration;
import com.soffid.iam.service.AdditionalDataJSONConfiguration;
import com.soffid.iam.service.AdditionalDataService;
import com.soffid.iam.service.LuceneIndexService;
import com.soffid.iam.utils.Security;
import com.soffid.iam.utils.TimeOutUtils;
import com.soffid.scimquery.EvalException;
import com.soffid.scimquery.HQLQuery;
import com.soffid.scimquery.conf.AttributeConfig;
import com.soffid.scimquery.conf.Configuration;
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
	Session session;
	String extraJoin = null;
	String extraWhere = null;
	private String returnValue;
	private int pageSize;
	HashMap <String,Object> extraParameters = null;
	private boolean textIndex = false;
	private String objectClassName;
	
	public ScimHelper (Class objectClass) throws InternalErrorException {
		this.objectClass = objectClass;
		objectClassName = objectClass.getName();
		CustomObjectType dt = svc.findCustomObjectTypeByName(objectClass.getName());
		if (dt != null && dt.isTextIndex())
			textIndex  = true;
	}
	
	public ScimHelper (String objectClass) throws InternalErrorException {
		this.objectClassName = objectClass;
		this.objectClass = CustomObject.class;
		CustomObjectType dt = svc.findCustomObjectTypeByName(objectClass);
		if (dt != null && dt.isTextIndex())
			textIndex  = true;
	}
	
	public void search (String textFilter, String jsonQuery, Collection<Object> result) throws InternalErrorException, UnsupportedEncodingException, ClassNotFoundException, EvalException, JSONException, ParseException, TokenMgrError {
		AdditionalDataJSONConfiguration.registerVirtualAttributes();

		AbstractExpression expr = evaluateQuery(textFilter, jsonQuery);

		if (session == null) {
			SessionFactory sf = (SessionFactory) ServiceLocator.instance().getService("sessionFactory");
			session = sf.getCurrentSession();
		}
					
		if (! textIndex || (textFilter == null || textFilter.trim().isEmpty()))
			searchJson(textFilter, jsonQuery, result);
		else
			searchMixed(textFilter, jsonQuery, result);
	}

	public void searchMixed (String textFilter, String jsonQuery, Collection<Object> result) throws InternalErrorException, UnsupportedEncodingException, ClassNotFoundException, EvalException, JSONException, ParseException, TokenMgrError {
		if (extraWhere == null)
			extraWhere = "o.id = :id";
		else
			extraWhere = "("+extraWhere+") and o.id=:id";
		AbstractExpression expr = evaluateQuery(null, jsonQuery);
					
		String hqlQuery = hql.toString();
		if (returnValue != null) {
			int i = hqlQuery.indexOf(" from ");
			hqlQuery = hqlQuery.substring(0, i) + ", "+returnValue+hqlQuery.substring(i);
		}
		if (order != null && hql.getOrderByString().length() == 0)
			hqlQuery = hqlQuery + " order by "+ order;
		
		LuceneIndexService luceneSvc = ServiceLocator.instance().getLuceneIndexService();
		
		final ScimCollector collector = new ScimCollector(result);
		collector.setConfig(config);
		collector.setSession(session);
		collector.setHqlQuery(hqlQuery);
		collector.setGenerator(generator);
		collector.setHql(hql);
		collector.setExpression(expr);
		HashMap<String,Object> params = new HashMap<>(hql.getParameters());
		if (tenantFilter != null)
			params.put("tenantId", Security.getCurrentTenantId());
		if (extraParameters != null) {
			for (Entry<String, Object> entry: extraParameters.entrySet()) {
				params.put(entry.getKey(), entry.getValue());
			}
			
		}
		collector.setParams(params);
		luceneSvc.search(objectClassName, textFilter, collector);
		count = new Integer(collector.size());
	}


	public void searchJson (String textFilter, String jsonQuery, Collection<Object> result) throws InternalErrorException, UnsupportedEncodingException, ClassNotFoundException, EvalException, JSONException, ParseException, TokenMgrError {
		AbstractExpression expr = evaluateQuery(textFilter, jsonQuery);

		String hqlQuery = hql.toString();
		if (returnValue != null) {
			int i = hqlQuery.indexOf(" from ");
			hqlQuery = hqlQuery.substring(0, i) + ", "+returnValue+hqlQuery.substring(i);
		}
		if (order != null && hql.getOrderByString().length() == 0)
			hqlQuery = hqlQuery + " order by "+ order;

		org.hibernate.Query queryObject = session.createQuery( hqlQuery );
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
		if (tenantFilter != null)
			queryObject.setParameter("tenantId", Security.getCurrentTenantId());
		if (extraParameters != null) {
			for (Entry<String, Object> entry: extraParameters.entrySet()) {
				final Object value = entry.getValue();
				if (value != null && value instanceof Collection)
					queryObject.setParameterList(entry.getKey(), (Collection) value);
				else
					queryObject.setParameter(entry.getKey(), value);
			}
			
		}
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

		AsyncList asyncList = null;
		if (result instanceof AsyncList)
			asyncList = (AsyncList) result;
		count = null;
		if (pageSize > 0 ) {
			int page = 0;
			do {
				queryObject.setFirstResult(page);
				queryObject.setMaxResults(pageSize);
				if (!fetchObjects(result, expr, queryObject, tou))
					break;
				page += pageSize;
				session.flush();
			} while(true);
			
		}
		else
		{
	        fetchObjects(result, expr, queryObject, tou);
			
		}
		if ((config == null || config.getMaximumResultSize() == null && config.getFetchSize() == null) &&
				(asyncList == null || ! asyncList.isCancelled()))
			count = new Integer(result.size());
	}


	private boolean fetchObjects(Collection<Object> result, AbstractExpression expr, org.hibernate.Query queryObject,
			TimeOutUtils tou) throws InternalErrorException, EvalException {
	    Iterator it = queryObject.iterate();
	    count = null;
        
	    boolean any = false;
		while (it.hasNext()) {
			Object e = it.next();
			any = true;
			if (result instanceof AsyncList)
			{
				if (((AsyncList) result).isCancelled())
					return false;
			}
			else
			{
				tou.checkTimeOut();
			}
			
			Object vo = generator.toValueObject(e);
			if (vo != null) {
				Object v = vo.getClass().isArray() ? Array.get(vo, 0): vo;
				if (! hql.isNonHQLAttributeUsed() || expr.evaluate(v))
					result.add(vo);
			}
		}
		return any;
	}


	private AbstractExpression evaluateQuery(String textFilter, String jsonQuery) throws InternalErrorException,
			ParseException, TokenMgrError, EvalException, UnsupportedEncodingException, ClassNotFoundException {
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
		expr.setOracleWorkaround( isOracle());
		hql = expr.generateHSQLString( objectClass );
		qs = hql.getWhereString().toString();
		if (tenantFilter != null) {
			if (qs.isEmpty())
				qs = "o." + tenantFilter + " = :tenantId";
			else
				qs = "("+qs+") and o." + tenantFilter + " = :tenantId";
			hql.setWhereString(new StringBuffer(qs));
		}
		
		if (extraWhere != null) {
			if (qs.isEmpty())
				qs = extraWhere;
			else
				qs = "("+qs+") and "+extraWhere;
			hql.setWhereString(new StringBuffer(qs));
		}
		
		if (extraJoin != null) {
			StringBuffer join = hql.getJoinString();
			join.append(" ").append(extraJoin);
			hql.setJoinString(join);
		}
		return expr;
	}

	static Boolean oracle = null;
	public boolean isOracle() {
		if (oracle == null) {
			oracle = CustomDialect.isOracle() || CustomDialect.isSqlServer();
		}
		return oracle.booleanValue();
	}

	String generateQuickSearchQuery (String text) throws InternalErrorException, UnsupportedEncodingException, JSONException, ClassNotFoundException {
		if (text == null || text.trim().isEmpty())
			return  "";
		String[] split = text.trim().split("[ ,./-]+");
		
		StringBuffer sb = new StringBuffer("");
		for (int i = 0; i < split.length; i++)
		{
			String t = split[i].replaceAll("\\\\","\\\\\\\\").replaceAll("\"", "\\\\\"");
			if (! t.trim().isEmpty()) {
				if (sb.length() > 0)
					sb.append(" and ");
				sb.append("(");
				boolean first = true;
				Collection<DataType> list = svc.findDataTypesByObjectTypeAndName2(objectClassName, null);
				for (DataType dt: list) {
					if (Boolean.TRUE.equals( dt.getSearchCriteria())) {
						if (first) first = false;
						else sb.append(" or ");
						if (Boolean.TRUE.equals(dt.getBuiltin()))
							sb.append(dt.getName() + " co \""+t+"\"");
						else
							sb.append( "attributes."+dt.getName() + " co \""+t+"\"");
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
		}
		return sb.toString();
	}

	public int count (String textFilter, String jsonQuery) throws InternalErrorException, UnsupportedEncodingException, ClassNotFoundException, EvalException, JSONException, ParseException, TokenMgrError {
		evaluateQuery(textFilter, jsonQuery);
		return count();
	}
	
	public int count() {
		if (count != null)
			return count.intValue();

		if (hql == null) {
			return 0;
		}
		
		if (session == null) {
			SessionFactory sf = (SessionFactory) ServiceLocator.instance().getService("sessionFactory");
			session = sf.getCurrentSession();
		}
		
		String qs = hql.getWhereString().toString();

		org.hibernate.Query queryObject = session.createQuery( hql.toCountString() );
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

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}


	public static String[] split(String text) {
		if (text.startsWith("\"") && text.endsWith("\"") && text.length() > 2)
			return new String[] {text.substring(1, text.length()-1)};
		else
			return text.trim().split("[ ,./-]+");
	}


	public String getExtraJoin() {
		return extraJoin;
	}


	public void setExtraJoin(String extraJoin) {
		this.extraJoin = extraJoin;
	}


	public String getExtraWhere() {
		return extraWhere;
	}


	public void setExtraWhere(String extraWhere) {
		this.extraWhere = extraWhere;
	}



	public String getReturnValue() {
		return returnValue;
	}


	public void setReturnValue(String returnValue) {
		this.returnValue = returnValue;
	}


	public void setPageSize(int i) {
		pageSize = i;
	}


	public HashMap<String, Object> getExtraParameters() {
		return extraParameters;
	}


	public void setExtraParameters(HashMap<String, Object> extraParameters) {
		this.extraParameters = extraParameters;
	}


	public void setCustomObjectType(String type) throws InternalErrorException {
		CustomObjectType dt = svc.findCustomObjectTypeByName(type);
		if (dt != null && dt.isTextIndex())
			textIndex  = true;
	}
}
