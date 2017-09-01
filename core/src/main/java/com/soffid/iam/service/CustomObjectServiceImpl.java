package com.soffid.iam.service;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.JSONException;

import com.soffid.iam.api.Audit;
import com.soffid.iam.api.CustomObject;
import com.soffid.iam.model.AuditEntity;
import com.soffid.iam.model.CustomObjectAttributeEntity;
import com.soffid.iam.model.CustomObjectEntity;
import com.soffid.iam.model.MetaDataEntity;
import com.soffid.iam.model.Parameter;
import com.soffid.iam.model.TaskEntity;
import com.soffid.iam.sync.engine.TaskHandler;
import com.soffid.iam.utils.Security;
import com.soffid.scimquery.HQLQuery;
import com.soffid.scimquery.conf.AttributeConfig;
import com.soffid.scimquery.conf.ClassConfig;
import com.soffid.scimquery.conf.Configuration;
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
		getCustomObjectAttributeEntityDao().remove(entity.getAttributes());
		entity.getAttributes().clear();
		getCustomObjectEntityDao().remove(entity);
		generateAudit(entity, "D");
		generateTask(entity);
	}

	@Override
	protected Collection<CustomObject> handleFindCustomObjectByJsonQuery(String objectType, String query) throws Exception {
		ClassConfig config = getJsonConfiguration();

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
		Collection<CustomObject> result = new LinkedList<CustomObject>();
		for (CustomObjectEntity ue : getCustomObjectEntityDao().query(hql.toString(),
				paramArray)) 
		{
			CustomObject u = getCustomObjectEntityDao().toCustomObject(ue);
			if (!hql.isNonHQLAttributeUsed() || expr.evaluate(u)) {
				result.add(u);
			}
		}
		return result;
	}

	private ClassConfig getJsonConfiguration()
			throws  ClassNotFoundException, UnsupportedEncodingException, JSONException 
	{
		ClassConfig cc = Configuration
				.getClassConfig(CustomObject.class);
		
		if (cc == null)
		{
			cc = new ClassConfig();
			AttributeConfig attributeConfig = new AttributeConfig();
			attributeConfig.setVirtualAttribute(true);
			attributeConfig.setVirtualAttributeValue("value");
			attributeConfig.setVirtualAttributeName("attribute.name");
			cc.setDefaultVirtualAttribute(attributeConfig);
			Configuration.registerClass(cc);
		}

		return Configuration.getClassConfig(com.soffid.iam.api.CustomObject.class);
	}

	@Override
	protected Collection<CustomObject> handleFindCustomObjectByText(String objectType, String query) throws Exception {
		Collection<CustomObjectEntity> list = getCustomObjectEntityDao().findByText(objectType, query);
		return getCustomObjectEntityDao().toCustomObjectList(list);
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
		
		HashSet<String> keys = new HashSet<String>(obj.getAttributes().keySet());
		for ( CustomObjectAttributeEntity att: entity.getAttributes())
		{
			Object v = obj.getAttributes().get(att.getMetadata().getName());
			att.setObjectValue(v);
			keys.remove(att.getMetadata().getName());
		}
		List<MetaDataEntity> md = getMetaDataEntityDao().findByObjectTypeAndName(obj.getType(), null);
		for (String key: keys)
		{
			Object v = obj.getAttributes().get(key);
			if ( v != null)
			{
				boolean found = false;
				CustomObjectAttributeEntity aae = getCustomObjectAttributeEntityDao().newCustomObjectAttributeEntity();
				for ( MetaDataEntity d: md)
				{
					if (d.getName().equals(key))
					{
						aae.setMetadata(d);
						found = true;
						break;
					}
				}
				if (!found)
					throw new InternalErrorException(String.format("Unknown attribute %s", key));
				aae.setObjectValue(v);
				aae.setCustomObject(entity);
				getCustomObjectAttributeEntityDao().create(aae);
			}
		}
		
		for ( MetaDataEntity m: md)
		{
			Object o = obj.getAttributes().get(m.getName());
			if ( o == null || "".equals(o))
			{
				if (m.getRequired() != null && m.getRequired().booleanValue())
					throw new InternalErrorException(String.format("Missing attribute %s", m.getLabel()));
			} else {
				if (m.getUnique() != null && m.getUnique().booleanValue())
				{
					List<CustomObjectAttributeEntity> p = getCustomObjectAttributeEntityDao().findByTypeNameAndValue(obj.getType(), m.getName(), o.toString());
					if (p.size() > 1)
						throw new InternalErrorException(String.format("Already exists a role with %s %s",
								m.getLabel(), o.toString()));
				}
			}
		}
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
		return getCustomObjectEntityDao().toCustomObject(o);
	}
}
