// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package com.soffid.iam.service;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.soffid.iam.api.Account;
import com.soffid.iam.api.AsyncList;
import com.soffid.iam.api.OUType;
import com.soffid.iam.api.PagedResult;
import com.soffid.iam.api.System;
import com.soffid.iam.bpm.service.scim.ScimHelper;
import com.soffid.iam.model.AccountEntity;
import com.soffid.iam.model.AccountEntityDao;
import com.soffid.iam.model.CustomDialect;
import com.soffid.iam.model.GroupTypeEntity;
import com.soffid.iam.model.GroupTypeEntityDao;
import com.soffid.iam.model.Parameter;
import com.soffid.iam.model.criteria.CriteriaSearchConfiguration;
import com.soffid.iam.utils.ConfigurationCache;
import com.soffid.iam.utils.Security;
import com.soffid.iam.utils.TimeOutUtils;
import com.soffid.scimquery.EvalException;
import com.soffid.scimquery.HQLQuery;
import com.soffid.scimquery.expr.AbstractExpression;
import com.soffid.scimquery.parser.ExpressionParser;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.exception.SeyconException;

/**
 * Servei per a accedir a TipusUnitatOrganitzativa Created on 01/06/2009
 * 
 * @author u91940
 * @see es.caib.seycon.ng.servei.TipusUnitatOrganitzativaService
 */
public class OrganizationalUnitTypeServiceImpl extends com.soffid.iam.service.OrganizationalUnitTypeServiceBase {

	/**
	 * @see es.caib.seycon.ng.servei.TipusUnitatOrganitzativaService#create(es.caib.seycon.ng.comu.TipusUnitatOrganitzativa)
	 */
	protected com.soffid.iam.api.OUType handleCreate(com.soffid.iam.api.OUType tipus) throws java.lang.Exception {
		GroupTypeEntity tipusSameCode = getGroupTypeEntityDao().findByName(tipus.getCode());
		if(tipusSameCode != null)
			throw new SeyconException(String.format(Messages.getString("OrganizationalUnitTypeServiceImpl.CodeTypeExists"), tipus.getCode())); 
		GroupTypeEntity entity = getGroupTypeEntityDao().oUTypeToEntity(tipus);
		getGroupTypeEntityDao().create(entity);
		tipus.setId(entity.getId());
		tipus = getGroupTypeEntityDao().toOUType(entity);
		return tipus;
	}

	/**
	 * @see es.caib.seycon.ng.servei.TipusUnitatOrganitzativaService#delete(es.caib.seycon.ng.comu.TipusUnitatOrganitzativa)
	 */
	protected void handleDelete(com.soffid.iam.api.OUType tipus) throws java.lang.Exception {
		GroupTypeEntity entity = getGroupTypeEntityDao().oUTypeToEntity(tipus);
		getGroupTypeEntityDao().remove(entity);
	}

	/**
	 * @see es.caib.seycon.ng.servei.TipusUnitatOrganitzativaService#update(es.caib.seycon.ng.comu.TipusUnitatOrganitzativa)
	 */
	protected com.soffid.iam.api.OUType handleUpdate(com.soffid.iam.api.OUType tipus) throws java.lang.Exception {
		GroupTypeEntity entity = getGroupTypeEntityDao().oUTypeToEntity(tipus);
		getGroupTypeEntityDao().update(entity);
		tipus = getGroupTypeEntityDao().toOUType(entity);
		return tipus;
	}

	/**
	 * @see es.caib.seycon.ng.servei.TipusUnitatOrganitzativaService#findTipusUnitatOrganitzativaByCodi(java.lang.String)
	 */
	protected OUType handleFindOUTypeByName(String CodiTipusUnitatOrganitzativa) throws Exception {
		GroupTypeEntity entity = getGroupTypeEntityDao().findByName(CodiTipusUnitatOrganitzativa);
		if (entity == null)
			return null;
		else
			return getGroupTypeEntityDao().toOUType(entity);
	}

	/**
	 * @see es.caib.seycon.ng.servei.TipusUnitatOrganitzativaService#findTipusUnitatOrganitzativaByFiltre(java.lang.String,
	 *      java.lang.String)
	 */
	protected Collection<OUType> handleFindOUTypeByFilter(String codi, String descripcio) throws Exception {
		Collection<GroupTypeEntity> entities = getGroupTypeEntityDao().findByFilter(codi, descripcio);
		int limitResults = Integer.parseInt(ConfigurationCache.getProperty("soffid.ui.maxrows")); //$NON-NLS-1$
		
		// Check maximum number of results
		if (entities.size() > limitResults)
		{
			return getGroupTypeEntityDao().toOUTypeList(entities).subList(0, limitResults);
		}
		
		return getGroupTypeEntityDao().toOUTypeList(entities);
	}

	@Override
	protected Collection<OUType> handleFindOrganizationalUnitByJsonQuery(String query) throws InternalErrorException, Exception {

		// Prepare query HQL
		AbstractExpression expr = ExpressionParser.parse(query);
		expr.setOracleWorkaround( CustomDialect.isOracle());
		HQLQuery hql = expr.generateHSQLString(OUType.class);
		String qs = hql.getWhereString().toString();
		if (qs.isEmpty())
			qs = "o.tenant.id = :tenantId";
		else
			qs = "(" + qs + ") and o.tenant.id = :tenantId";
		hql.setWhereString(new StringBuffer(qs));

		// Include HQL parameters
		Map<String, Object> params = hql.getParameters();
		Parameter paramArray[] = new Parameter[params.size() + 1];
		int i = 0;
		for (String s : params.keySet())
			paramArray[i++] = new Parameter(s, params.get(s));
		paramArray[i++] = new Parameter("tenantId", Security.getCurrentTenantId());

		// Execute HQL and generate result
		TimeOutUtils tou = new TimeOutUtils();
		LinkedList<OUType> result = new LinkedList<OUType>();
		for (GroupTypeEntity e : getGroupTypeEntityDao().query(hql.toString(), paramArray)) {
			tou.checkTimeOut();
			OUType vo = getGroupTypeEntityDao().toOUType(e);
			if (!hql.isNonHQLAttributeUsed() || expr.evaluate(vo)) {
				//if (getAuthorizationService().hasPermission(Security.AUTO_USER_QUERY, e)) {
					result.add(vo);
				//}
			}
		}
		return result;
	}

	String generateQuickSearchQuery (String text) {
		if (text == null )
			return  "";
		String[] split = ScimHelper.split(text);
		
		StringBuffer sb = new StringBuffer("");
		for (int i = 0; i < split.length; i++)
		{
			String t = split[i].replaceAll("\\\\","\\\\\\\\").replaceAll("\"", "\\\\\"");
			if (sb.length() > 0)
				sb.append(" and ");
			sb.append("(");
			sb.append("name co \""+t+"\"");
			sb.append(" or description co \""+t+"\"");
			sb.append(")");
		}
		return sb.toString();
	}

	@Override
	protected AsyncList<OUType> handleFindOUTypeByTextAndFilterAsync(String text, String filter) throws Exception {
		String q = generateQuickSearchQuery(text);
		if (!q.isEmpty() && filter != null && ! filter.trim().isEmpty())
			q = "("+q+") and ("+filter+")";
		else if ( filter != null && ! filter.trim().isEmpty())
			q = filter;
		final String query = q;
		final AsyncList<OUType> result = new AsyncList<OUType>();
		getAsyncRunnerService().run(new Runnable() {
			public void run() {
				try {
					findByJsonQuery(result, query, null, null);
				} catch (Exception e) {
					result.cancel(e);
				}
			}
		}, result);
		return result;
	}

	@Override
	protected PagedResult<OUType> handleFindOUTypeByTextAndFilter(String text, String filter, Integer first, Integer pageSize)
			throws Exception {

		final GroupTypeEntityDao dao = getGroupTypeEntityDao();
		ScimHelper h = new ScimHelper(OUType.class);
		h.setPrimaryAttributes(new String[] { "name", "description"});
		CriteriaSearchConfiguration config = new CriteriaSearchConfiguration();
		config.setFirstResult(first);
		config.setMaximumResultSize(pageSize);
		h.setConfig(config);
		h.setTenantFilter("tenant.id");
		h.setGenerator((entity) -> {
			return dao.toOUType((GroupTypeEntity) entity);
		}); 
		LinkedList<OUType> result = new LinkedList<OUType>();
		h.search(text, filter, (Collection) result); 
		PagedResult<OUType> pr = new PagedResult<>();
		pr.setStartIndex(first);
		pr.setItemsPerPage(pageSize);
		pr.setTotalResults(h.count());
		pr.setResources(result);
		return pr;

	}
	
	protected void findByJsonQuery ( AsyncList<OUType> result, String query, Integer first, Integer pageSize) 
			throws Exception
	{
		// Register virtual attributes for additional data
		AdditionalDataJSONConfiguration.registerVirtualAttributes();

		// Prepare query HQL
		AbstractExpression expression = ExpressionParser.parse(query);
		expression.setOracleWorkaround( CustomDialect.isOracle());
		HQLQuery hql = expression.generateHSQLString(OUType.class);
		String qs = hql.getWhereString().toString();
		if (qs.isEmpty())
			qs = "o.tenant.id = :tenantId";
		else
			qs = "(" + qs + ") and o.tenant.id = :tenantId";
		hql.setWhereString(new StringBuffer(qs));

		// Include HQL parameters
		Map<String, Object> params = hql.getParameters();
		Parameter paramArray[] = new Parameter[params.size() + 1];
		int i = 0;
		for (String s : params.keySet())
			paramArray[i++] = new Parameter(s, params.get(s));
		paramArray[i++] = new Parameter("tenantId", Security.getCurrentTenantId());

		CriteriaSearchConfiguration cfg = new CriteriaSearchConfiguration();
		cfg.setFetchSize(first);
		cfg.setMaximumResultSize(pageSize);
		// Execute HQL and generate result
		for (GroupTypeEntity ge : getGroupTypeEntityDao().query(hql.toString(), paramArray, cfg )) {
			if (result.isCancelled())
				return;
			OUType g = getGroupTypeEntityDao().toOUType(ge);
			if (!hql.isNonHQLAttributeUsed() || expression.evaluate(g)) {
				result.add(g);
			}
		}
	}

}