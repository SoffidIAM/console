package com.soffid.iam.service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.soffid.iam.api.Account;
import com.soffid.iam.api.AsyncList;
import com.soffid.iam.api.Audit;
import com.soffid.iam.api.CustomObject;
import com.soffid.iam.api.MetadataScope;
import com.soffid.iam.api.Role;
import com.soffid.iam.model.AccountEntity;
import com.soffid.iam.model.AuditEntity;
import com.soffid.iam.model.CustomObjectAttributeEntity;
import com.soffid.iam.model.CustomObjectEntity;
import com.soffid.iam.model.MetaDataEntity;
import com.soffid.iam.model.Parameter;
import com.soffid.iam.model.RoleAttributeEntity;
import com.soffid.iam.model.RoleEntity;
import com.soffid.iam.model.TaskEntity;
import com.soffid.iam.sync.engine.TaskHandler;
import com.soffid.iam.utils.Security;
import com.soffid.iam.utils.TimeOutUtils;
import com.soffid.scimquery.HQLQuery;
import com.soffid.scimquery.expr.AbstractExpression;
import com.soffid.scimquery.parser.ExpressionParser;

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
		AdditionalDataJSONConfiguration.registerVirtualAttribute(CustomObjectAttributeEntity.class, "metadata.name", "value");

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
		Collection<CustomObjectEntity> list = getCustomObjectEntityDao().findByText(objectType, query);
		return getCustomObjectEntityDao().toCustomObjectList(list);
	}

	@Override
	protected AsyncList<CustomObject> handleFindCustomObjectByTextAsync(final String objectType, final String query) throws Exception {
		final AsyncList<CustomObject> result = new AsyncList<CustomObject>();
		getAsyncRunnerService().run(
				new Runnable() {
					public void run () {
						for (CustomObjectEntity e : getCustomObjectEntityDao().findByText(objectType, query)) {
							if (result.isCancelled())
								return;
							CustomObject v = getCustomObjectEntityDao().toCustomObject(e);
							result.add(v);
						}
					}
				}, result);
		return result;
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
						List<String> l = o instanceof List? (List) o: Collections.singletonList(o);
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
}
