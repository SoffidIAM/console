package com.soffid.iam.service;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.JSONException;

import com.soffid.iam.api.AsyncList;
import com.soffid.iam.api.Audit;
import com.soffid.iam.api.CustomObject;
import com.soffid.iam.api.MetadataScope;
import com.soffid.iam.api.PagedResult;
import com.soffid.iam.bpm.service.scim.ScimHelper;
import com.soffid.iam.model.AuditEntity;
import com.soffid.iam.model.CustomObjectAttributeEntity;
import com.soffid.iam.model.CustomObjectEntity;
import com.soffid.iam.model.CustomObjectEntityDao;
import com.soffid.iam.model.CustomObjectRoleEntity;
import com.soffid.iam.model.CustomObjectTypeEntity;
import com.soffid.iam.model.MetaDataEntity;
import com.soffid.iam.model.TaskEntity;
import com.soffid.iam.model.criteria.CriteriaSearchConfiguration;
import com.soffid.iam.sync.engine.TaskHandler;
import com.soffid.iam.utils.Security;
import com.soffid.scimquery.EvalException;
import com.soffid.scimquery.parser.ParseException;
import com.soffid.scimquery.parser.TokenMgrError;

import es.caib.seycon.ng.comu.AccountAccessLevelEnum;
import es.caib.seycon.ng.exception.InternalErrorException;

public class CustomObjectServiceImpl extends CustomObjectServiceBase {
	AccountAccessLevelEnum OWNERLEVEL[] = new AccountAccessLevelEnum[] { AccountAccessLevelEnum.ACCESS_OWNER };
	AccountAccessLevelEnum MANAGER_LEVEL[] = new AccountAccessLevelEnum[] { AccountAccessLevelEnum.ACCESS_OWNER, AccountAccessLevelEnum.ACCESS_MANAGER };
	AccountAccessLevelEnum USER_LEVEL[] = new AccountAccessLevelEnum[] { AccountAccessLevelEnum.ACCESS_OWNER, AccountAccessLevelEnum.ACCESS_MANAGER, AccountAccessLevelEnum.ACCESS_USER };
	
	@Override
	protected CustomObject handleCreateCustomObject(CustomObject obj) throws Exception {
		CustomObjectEntity entity = getCustomObjectEntityDao().newCustomObjectEntity();
		getCustomObjectEntityDao().customObjectToEntity(obj, entity, true);
		if (! hasAccessLevel(entity.getType(), MANAGER_LEVEL))
			throw new SecurityException("Access denied. Not authorized to create a custom object of class "+obj.getType());
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
		if (! hasAccessLevel(entity.getType(), MANAGER_LEVEL))
			throw new SecurityException("Access denied. Not authorized to remove a remove object of class "+obj.getType());
		getCustomObjectEntityDao().remove(entity);
		generateAudit(entity, "D");
		generateTask(entity);
	}

	@Override
	protected Collection<CustomObject> handleFindCustomObjectByJsonQuery(String objectType, String query) throws Exception {
		List<CustomObject> result = new LinkedList<CustomObject>();
		internalFindCustomObjectByJsonQuery(result, objectType, null, query, null, null);
		return result;
	}
	
	@Override
	protected AsyncList<CustomObject> handleFindCustomObjectByJsonQueryAsync(final String objectType, final String query) throws Exception {
		final AsyncList<CustomObject> result = new AsyncList<CustomObject>();
		getAsyncRunnerService().run(new Runnable() {
			public void run() {
				try {
					internalFindCustomObjectByJsonQuery(result, objectType, null, query, null, null);
				} catch (Exception e) {
					result.cancel(e);
				}
			}
		}, result);
		return result;
	}

	protected PagedResult<CustomObject> internalFindCustomObjectByJsonQuery(List<CustomObject> result, String objectType, 
			String text, String query, Integer start, Integer pageSize) throws Exception {
		if (objectType != null) {
			CustomObjectTypeEntity entity = getCustomObjectTypeEntityDao().findByName(objectType);
			if (! hasAccessLevel(entity, USER_LEVEL))
				throw new SecurityException("Access denied. Not authorized to query a custom object of class "+objectType);
		}
		// Register virtual attributes for additional data
		AdditionalDataJSONConfiguration.registerVirtualAttributes();

		ScimHelper h = new ScimHelper(objectType);
		
		h.setPrimaryAttributes(new String[] { "name", "description"});
		CriteriaSearchConfiguration config = new CriteriaSearchConfiguration();
		config.setFirstResult(start);
		config.setMaximumResultSize(pageSize);
		h.setConfig(config);
		h.setTenantFilter("tenant.id");
		if (objectType != null) {
			if (query == null || query.trim().isEmpty())
				query = "o.type.name eq '"+encode(objectType)+"'";
			else
				query = "(" + query + ") and o.type.name eq '"+encode(objectType)+"'";
		}
		CustomObjectEntityDao dao = getCustomObjectEntityDao();
		h.setGenerator((e) -> {
			return dao.toCustomObject((CustomObjectEntity) e);
		}); 
		h.search(text, query, (Collection) result); 
		PagedResult<CustomObject> pr = new PagedResult<>();
		pr.setStartIndex(start);
		pr.setItemsPerPage(pageSize);
		pr.setTotalResults(h.count());
		pr.setResources(result);
		return pr;
	}

	private String encode(String objectType) {
		return objectType.replace("\\", "\\\\").replace("\"", "\\\"").replace("\'", "\\\'");
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
		
		if (! hasAccessLevel(entity.getType(), MANAGER_LEVEL))
			throw new SecurityException("Access denied. Not authorized to modify a custom object of class "+entity.getType().getName());

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
		CustomObjectTypeEntity type = getCustomObjectTypeEntityDao().findByName(objectType);
		if (! hasAccessLevel(type, USER_LEVEL))
			throw new SecurityException("Access denied. Not authorized to query a custom object of class "+type.getName());
		
		CustomObjectEntity o = getCustomObjectEntityDao().findByTypeAndName(objectType, name);
		if (o == null)
			return null;
		return getCustomObjectEntityDao().toCustomObject(o);
	}

	@Override
	protected Collection<String> handleFindCustomObjectNames(String objectType) throws Exception {
		return getCustomObjectEntityDao().findCustomObjectNames(objectType);
	}

	@Override
	protected AsyncList<CustomObject> handleFindCustomObjectByTextAndFilterAsync(String objectType, String text, String filter) throws Exception {
		final AsyncList<CustomObject> result = new AsyncList<CustomObject>();
		getAsyncRunnerService().run(new Runnable() {
			public void run() {
				try {
					internalFindCustomObjectByJsonQuery(result, objectType, text, filter, null, null);
				} catch (Exception e) {
					result.cancel(e);
				}
			}
		}, result);
		return result;
	}

	@Override
	protected Collection<CustomObject> handleFindCustomObjectByTextAndFilter(String objectType, String text, String filter) throws Exception {
		final List<CustomObject> result = new LinkedList<CustomObject>();
		internalFindCustomObjectByJsonQuery(result, objectType, text, filter, null, null);
		return result;
	}

	@Override
	protected AsyncList<CustomObject> handleFindCustomObjectByTextAndJsonQueryAsync(String text, String filter)
			throws Exception {
		final AsyncList<CustomObject> result = new AsyncList<CustomObject>();
		getAsyncRunnerService().run(new Runnable() {
			public void run() {
				try {
					internalFindCustomObjectByJsonQuery(result, null, text, filter, null, null);
				} catch (Exception e) {
					result.cancel(e);
				}
			}
		}, result);
		return result;
	}

	@Override
	protected PagedResult<CustomObject> handleFindCustomObjectByTextAndJsonQuery(String text, String filter,
			Integer start, Integer end) throws Exception {
		final List<CustomObject> result = new LinkedList<CustomObject>();
		return internalFindCustomObjectByJsonQuery(result, null, text, filter, start, end);
	}

	private PagedResult<CustomObject> internalSearchCustomObjectsByJson(String objectType, String query, List<CustomObject> result,
			Integer start, Integer end)
			throws UnsupportedEncodingException, ClassNotFoundException, JSONException, ParseException, TokenMgrError,
			EvalException, InternalErrorException {
		// Register virtual attributes for additional data
		AdditionalDataJSONConfiguration.registerVirtualAttributes();
		
		final CustomObjectEntityDao dao = getCustomObjectEntityDao();
		ScimHelper h = new ScimHelper(CustomObject.class);
		if (objectType != null)
			h.setCustomObjectType(objectType);
		h.setPrimaryAttributes(new String[] { "name", "description"});
		CriteriaSearchConfiguration config = new CriteriaSearchConfiguration();
		config.setFirstResult(start);
		config.setMaximumResultSize(end);
		h.setConfig(config);
		h.setTenantFilter("type.tenant.id");
//		h.setOrder("o.name");
		h.setGenerator((entity) -> {
			final CustomObjectEntity co = (CustomObjectEntity) entity;
			if (hasAccessLevel(co.getType(), USER_LEVEL))
				return dao.toCustomObject(co);
			else
				return null;
		}); 

		h.search("", query, (Collection) result); 

		PagedResult<CustomObject> pr = new PagedResult<>();
		pr.setStartIndex(start);
		pr.setItemsPerPage(end);
		pr.setTotalResults(h.count());
		pr.setResources(result);
		return pr;
	}

	boolean hasAccessLevel(CustomObjectTypeEntity entity, AccountAccessLevelEnum levels[]) {
		if (Security.isSyncServer())
			return true;

		if (entity == null)
			return false;
		if (entity.getPublicAccess() == null || entity.getPublicAccess().booleanValue())
			return true;
		
		String[] soffidRoles = Security.getSoffidPrincipal().getSoffidRoles();
		AccountAccessLevelEnum e = AccountAccessLevelEnum.ACCESS_NONE;
		for ( CustomObjectRoleEntity role: entity.getAccessRoles() ) {
			String roleName = role.getRole().getName()+ "@" + role.getRole().getSystem().getName();
			if (Arrays.binarySearch(soffidRoles, roleName) >= 0) 
				for ( AccountAccessLevelEnum level: levels ) {
					if (role.getLevel() == level)
						return true;
			}
		}
		return false;
	}

	@Override
	protected AsyncList<CustomObject> handleFindCustomObjectByTextAndJsonQueryAsync(String objectType, String text,
			String filter) throws Exception {
		final AsyncList<CustomObject> result = new AsyncList<CustomObject>();
		getAsyncRunnerService().run(new Runnable() {
			public void run() {
				try {
					internalFindCustomObjectByJsonQuery(result, objectType, text, filter, null, null);
				} catch (Exception e) {
					result.cancel(e);
				}
			}
		}, result);
		return result;
	}

	@Override
	protected PagedResult<CustomObject> handleFindCustomObjectByTextAndJsonQuery(String objectType, String text,
			String filter, Integer start, Integer end) throws Exception {
		final List<CustomObject> result = new LinkedList<CustomObject>();
		return internalFindCustomObjectByJsonQuery(result, objectType, text, filter, start, end);
	}
}
