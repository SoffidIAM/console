package com.soffid.iam.service;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.JSONException;

import com.soffid.iam.api.Account;
import com.soffid.iam.api.AsyncList;
import com.soffid.iam.api.Audit;
import com.soffid.iam.api.CustomObject;
import com.soffid.iam.api.MetadataScope;
import com.soffid.iam.api.Role;
import com.soffid.iam.api.User;
import com.soffid.iam.model.AccountEntity;
import com.soffid.iam.model.AuditEntity;
import com.soffid.iam.model.CustomObjectAttributeEntity;
import com.soffid.iam.model.CustomObjectEntity;
import com.soffid.iam.model.MetaDataEntity;
import com.soffid.iam.model.Parameter;
import com.soffid.iam.model.RoleAttributeEntity;
import com.soffid.iam.model.RoleEntity;
import com.soffid.iam.model.TaskEntity;
import com.soffid.iam.model.UserEntity;
import com.soffid.iam.model.criteria.CriteriaSearchConfiguration;
import com.soffid.iam.sync.engine.TaskHandler;
import com.soffid.iam.utils.Security;
import com.soffid.iam.utils.TimeOutUtils;
import com.soffid.scimquery.EvalException;
import com.soffid.scimquery.HQLQuery;
import com.soffid.scimquery.expr.AbstractExpression;
import com.soffid.scimquery.parser.ExpressionParser;
import com.soffid.scimquery.parser.ParseException;
import com.soffid.scimquery.parser.TokenMgrError;

import es.caib.seycon.ng.exception.InternalErrorException;

public class CustomObjectServiceImpl extends CustomObjectServiceBase {

	@Override
	protected CustomObject handleCreateCustomObject(CustomObject obj) throws Exception {
		CustomObjectEntity entity = getCustomObjectEntityDao().newCustomObjectEntity();
		getCustomObjectEntityDao().customObjectToEntity(obj, entity, true);
		getCustomObjectEntityDao().create(entity);
		updateAttributes(obj, entity);
		generateAudit(entity, "C");
		generateTask(entity);
		return getCustomObjectEntityDao().toCustomObject(entity);
	}

	@Override
	protected void handleDeleteCustomObject(CustomObject obj) throws Exception {
		CustomObjectEntity entity = getCustomObjectEntityDao().load(obj.getId());
		if (entity == null)
			return;
		getCustomObjectEntityDao().remove(entity);
		generateAudit(entity, "D");
		generateTask(entity);
	}

	@Override
	protected Collection<CustomObject> handleFindCustomObjectByJsonQuery(String objectType, String query) throws Exception {
		AsyncList<CustomObject> result = new AsyncList<CustomObject>();
		result.setTimeout(TimeOutUtils.getGlobalTimeOut());
		findCustomObjectByJsonQuery(result, objectType, query);
		if (result.isCancelled())
			TimeOutUtils.generateException();
		result.done();
		return result.get();
	}
	
	@Override
	protected AsyncList<CustomObject> handleFindCustomObjectByJsonQueryAsync(final String objectType, final String query) throws Exception {
		final AsyncList<CustomObject> result = new AsyncList<CustomObject>();
		getAsyncRunnerService().run(new Runnable() {
			public void run() {
				try {
					findCustomObjectByJsonQuery(result, objectType, query);
				} catch (Exception e) {
					result.cancel(e);
				}
			}
		}, result);
		return result;
	}

	protected void findCustomObjectByJsonQuery(AsyncList<CustomObject> result, String objectType, String query) throws Exception {

		// Register virtual attributes for additional data
		AdditionalDataJSONConfiguration.registerVirtualAttributes();;

		AbstractExpression expr = ExpressionParser.parse(query);
		HQLQuery hql = expr.generateHSQLString(CustomObject.class);
		String qs = hql.getWhereString().toString();
		if (qs.isEmpty())
			qs = "o.type.tenant.id = :tenantId and o.type.name=:objectType";
		else
			qs = "("+qs+") and o.type.tenant.id = :tenantId and o.type.name=:objectType";
		
		hql.setWhereString(new StringBuffer(qs));
		Map<String, Object> params = hql.getParameters();
		Parameter paramArray[] = new Parameter[params.size()+2];
		int i = 0;
		for (String s : params.keySet())
			paramArray[i++] = new Parameter(s, params.get(s));
		paramArray[i++] = new Parameter("tenantId", Security.getCurrentTenantId());
		paramArray[i++] = new Parameter("objectType", objectType);
		for (CustomObjectEntity ue : getCustomObjectEntityDao().query(hql.toString(),
				paramArray)) 
		{
			if (result.isCancelled())
				return;
			CustomObject u = getCustomObjectEntityDao().toCustomObject(ue);
			if (!hql.isNonHQLAttributeUsed() || expr.evaluate(u)) {
				result.add(u);
			}
		}
	}

	@Override
	protected Collection<CustomObject> handleFindCustomObjectByText(String objectType, String query) throws Exception {
		return handleFindCustomObjectByTextAndFilter(objectType,  query, null);
	}

	@Override
	protected AsyncList<CustomObject> handleFindCustomObjectByTextAsync(final String objectType, final String query) throws Exception {
		return handleFindCustomObjectByTextAndFilterAsync(objectType,  query, null);
	}
	@Override
	protected CustomObject handleUpdateCustomObject(CustomObject obj) throws Exception {
		CustomObjectEntity entity = getCustomObjectEntityDao().load(obj.getId());
		if (entity == null)
			throw new InternalErrorException("Custom object "+obj.getId()+" not found");
		
		if (! entity.getName().equals(obj.getName()))
		{
			generateTask(entity);
		}
		getCustomObjectEntityDao().customObjectToEntity(obj, entity, true);
		getCustomObjectEntityDao().create(entity);
		updateAttributes(obj, entity);
		generateAudit(entity, "U");
		generateTask(entity);
		return getCustomObjectEntityDao().toCustomObject(entity);
	}

	private void updateAttributes (CustomObject obj, CustomObjectEntity entity) throws InternalErrorException
	{
		if (obj.getAttributes() == null)
			obj.setAttributes(new HashMap<String, Object>());
		
		if (entity != null)
		{
			Map<String, Object> attributes = obj.getAttributes();
			if (attributes == null)
				attributes = (new HashMap<String, Object>());
			
			LinkedList<CustomObjectAttributeEntity> entities = new LinkedList<CustomObjectAttributeEntity> (entity.getAttributes());
			HashSet<String> keys = new HashSet<String>();
			for (String key: attributes.keySet() )
			{
				List<MetaDataEntity> ml = getMetaDataEntityDao().findByObjectTypeAndName(obj.getType(), key);
				if (ml == null || ml.isEmpty())
					throw new InternalErrorException("Attribute definition not found for attribute "+key);
				MetaDataEntity metadata = ml.iterator().next();
				Object v = attributes.get(key);
				if (v == null)
				{
					// Do nothing
				}
				else if (v instanceof List)
				{
					List l = (List) v;
					for (Object o: (List) v)
					{
						if (o != null)
						{
							updateAttribute(entity, entities, key, metadata, o);
						}
					}
				}
				else
				{
					updateAttribute(entity, entities, key, metadata, v);
				}
			}
			
			entity.getAttributes().removeAll(entities);
			getCustomObjectEntityDao().update(entity);
			
			Collection<MetaDataEntity> md = getMetaDataEntityDao().findByScope(MetadataScope.ROLE);
			
			for ( MetaDataEntity m: md) if ( m.getBuiltin() == null || ! m.getBuiltin().booleanValue() )
			{
				Object o = attributes.get(m.getName());
				if ( o == null || "".equals(o))
				{
					if (m.getRequired() != null && m.getRequired().booleanValue())
						throw new InternalErrorException(String.format("Missing attribute %s", m.getLabel()));
				} else {
					if (m.getUnique() != null && m.getUnique().booleanValue())
					{
						Collection<String> l = (Collection<String>) ( o instanceof Collection? (Collection) o: Collections.singletonList(o) );
						for (String v: l)
						{
							List<CustomObjectAttributeEntity> p = getCustomObjectAttributeEntityDao().findByTypeNameAndValue(entity.getType().getName(), m.getName(), v);
							if (p.size() > 1)
								throw new InternalErrorException(String.format("Already exists a role with %s %s",
										m.getLabel(), v));
						}
					}
				}
			}
		}
	}

	private void updateAttribute(CustomObjectEntity entity, LinkedList<CustomObjectAttributeEntity> attributes, String key,
			MetaDataEntity metadata, Object value) throws InternalErrorException {
		CustomObjectAttributeEntity aae = findAttributeEntity(attributes, key, value);
		if (aae == null)
		{
			getAttributeValidationService().validate(metadata.getType(), metadata.getDataObjectType(), value);
			aae = getCustomObjectAttributeEntityDao().newCustomObjectAttributeEntity();
			aae.setCustomObject(entity);
			aae.setMetadata(metadata);
			aae.setObjectValue(value);
			getCustomObjectAttributeEntityDao().create(aae);
			entity.getAttributes().add(aae);
		}
		else
			attributes.remove(aae);
	}

	private CustomObjectAttributeEntity findAttributeEntity(LinkedList<CustomObjectAttributeEntity> entities, String key,
			Object o) {
		for (CustomObjectAttributeEntity aae: entities)
		{
			if (aae.getMetadata().getName().equals(key))
			{
				if (aae.getObjectValue() != null && aae.getObjectValue().equals(o))
					return aae;
			}
		}
		return null;
	}

	void generateTask(CustomObjectEntity entity)
	{
		TaskEntity task = getTaskEntityDao().newTaskEntity();
		task.setTransaction(TaskHandler.UPDATE_OBJECT);
		task.setPrimaryKeyValue(entity.getId());
		task.setCustomObjectName(entity.getName());
		task.setCustomObjectType(entity.getType().getName());
		task.setStatus("P");
		task.setTenant(entity.getType().getTenant());
		task.setDate( new Timestamp( System.currentTimeMillis()));
		getTaskEntityDao().create(task);
	}

	void generateAudit(CustomObjectEntity entity, String action)
	{
		Audit auditoria = new Audit();
		auditoria.setAction(action); //$NON-NLS-1$
		auditoria.setAuthor(Security.getCurrentAccount());
		auditoria.setObject("SC_CUSOBJ"); //$NON-NLS-1$
		auditoria.setCustomObjectName(entity.getName());
		auditoria.setCustomObjectType(entity.getType().getName());
		auditoria.setCalendar(Calendar.getInstance());

		AuditEntity auditoriaEntity = getAuditEntityDao().auditToEntity(auditoria);
		getAuditEntityDao().create(auditoriaEntity);
	}

	@Override
	protected CustomObject handleFindCustomObjectByTypeAndName(String objectType, String name)
			throws Exception {
		CustomObjectEntity o = getCustomObjectEntityDao().findByTypeAndName(objectType, name);
		if (o == null)
			return null;
		return getCustomObjectEntityDao().toCustomObject(o);
	}

	@Override
	protected Collection<String> handleFindCustomObjectNames(String objectType) throws Exception {
		return getCustomObjectEntityDao().findCustomObjectNames(objectType);
	}

	String generateQuickSearchQuery (String text) {
		if (text == null )
			return  "";
		List<MetaDataEntity> atts = getMetaDataEntityDao().findByScope(MetadataScope.USER);
		String[] split = text.trim().split(" +");
		
		StringBuffer sb = new StringBuffer("");
		for (int i = 0; i < split.length; i++)
		{
			String t = split[i].replaceAll("\\\\","\\\\\\\\").replaceAll("\"", "\\\\\"");
			if (sb.length() > 0)
				sb.append(" and ");
			sb.append("(");
			sb.append("name co \""+t+"\"");
			sb.append(" or description co \""+t+"\"");
			for (MetaDataEntity att: atts)
			{
				if (att.getSearchCriteria() != null && att.getSearchCriteria().booleanValue())
				{
					sb.append(" or attributes."+att.getName()+" co \""+t+"\"");
				}
			}
			sb.append(")");
		}
		return sb.toString();
	}
	
	@Override
	protected AsyncList<CustomObject> handleFindCustomObjectByTextAndFilterAsync(String objectType, String text, String filter) throws Exception {
		String q = generateQuickSearchQuery(text);
		if (!q.isEmpty() && filter != null && ! filter.trim().isEmpty())
			q = "("+q+") and ("+filter+")";
		else if ( filter != null && ! filter.trim().isEmpty())
			q = filter;
		return handleFindCustomObjectByJsonQueryAsync(objectType, q);
			
	}

	@Override
	protected Collection<CustomObject> handleFindCustomObjectByTextAndFilter(String objectType, String text, String filter) throws Exception {
		String q = generateQuickSearchQuery(text);
		if (!q.isEmpty() && filter != null && ! filter.trim().isEmpty())
			q = "("+q+") and ("+filter+")";
		else if ( filter != null && ! filter.trim().isEmpty())
			q = filter;
		return handleFindCustomObjectByJsonQuery(objectType, q);
	}

	@Override
	protected AsyncList<CustomObject> handleFindCustomObjectByTextAndJsonQueryAsync(String text, String filter)
			throws Exception {
		String q = generateQuickSearchQuery(text);
		if (!q.isEmpty() && filter != null && ! filter.trim().isEmpty())
			q = "("+q+") and ("+filter+")";
		else if ( filter != null && ! filter.trim().isEmpty())
			q = filter;

		final AsyncList<CustomObject> result = new AsyncList<CustomObject>();
		
		final String query = q;
		getAsyncRunnerService().run(new Runnable() {

			@Override
			public void run() {
				try {
					internalSearchCustomObjectsByJson(query, result, null, null);
				} catch (Throwable e) {
					throw new RuntimeException(e);
				}				
			}
			
		}, result);

		return result;
	}

	@Override
	protected List<CustomObject> handleFindCustomObjectByTextAndJsonQuery(String text, String filter,
			Integer start, Integer end) throws Exception {
		String q = generateQuickSearchQuery(text);
		if (!q.isEmpty() && filter != null && ! filter.trim().isEmpty())
			q = "("+q+") and ("+filter+")";
		else if ( filter != null && ! filter.trim().isEmpty())
			q = filter;
		LinkedList<CustomObject> result = new LinkedList<CustomObject>();

		internalSearchCustomObjectsByJson(q, result, start, end);
		
		return result;
	}

	private void internalSearchCustomObjectsByJson(String query, Collection<CustomObject> result,
			Integer start, Integer end)
			throws UnsupportedEncodingException, ClassNotFoundException, JSONException, ParseException, TokenMgrError,
			EvalException, InternalErrorException {
		// Register virtual attributes for additional data
		AdditionalDataJSONConfiguration.registerVirtualAttributes();

		AbstractExpression expr = ExpressionParser.parse(query);
		HQLQuery hql = expr.generateHSQLString(CustomObject.class);
		String qs = hql.getWhereString().toString();
		if (qs.isEmpty())
			qs = "o.tenant.id = :tenantId";
		else
			qs = "("+qs+") and o.tenant.id = :tenantId";
		
		qs = qs + " order by o.name";

		hql.setWhereString(new StringBuffer(qs));
		Map<String, Object> params = hql.getParameters();
		Parameter paramArray[] = new Parameter[params.size()+1];
		int i = 0;
		for (String s : params.keySet())
			paramArray[i++] = new Parameter(s, params.get(s));
		paramArray[i++] = new Parameter("tenantId", Security.getCurrentTenantId());
		TimeOutUtils tou = new TimeOutUtils();
		CriteriaSearchConfiguration cfg = new CriteriaSearchConfiguration();
		cfg.setFirstResult(start);
		cfg.setMaximumResultSize(end);
		for (CustomObjectEntity ue : getCustomObjectEntityDao().query(hql.toString(),
				paramArray, cfg)) {
			if (result instanceof AsyncList)
			{
				if (((AsyncList) result).isCancelled())
					return;
			}
			else
			{
				tou.checkTimeOut();
			}
			CustomObject c = getCustomObjectEntityDao().toCustomObject(ue);
			result.add(c);
		}
	}

}
