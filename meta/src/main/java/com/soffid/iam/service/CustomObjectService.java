package com.soffid.iam.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.soffid.iam.api.AsyncList;
import com.soffid.iam.api.CustomObject;
import com.soffid.iam.api.CustomObjectType;
import com.soffid.iam.api.PagedResult;
import com.soffid.iam.model.CustomObjectAttributeEntity;
import com.soffid.iam.model.CustomObjectEntity;
import com.soffid.iam.model.CustomObjectTypeEntity;
import com.soffid.iam.service.impl.AttributeValidationService;
import com.soffid.mda.annotation.Depends;
import com.soffid.mda.annotation.Nullable;
import com.soffid.mda.annotation.Operation;
import com.soffid.mda.annotation.Service;

import es.caib.seycon.ng.comu.Account;
import es.caib.seycon.ng.comu.AccountAccessLevelEnum;
import es.caib.seycon.ng.model.AuditoriaEntity;
import es.caib.seycon.ng.model.TasqueEntity;
import es.caib.seycon.ng.model.TipusDadaEntity;
import roles.customObject_create;
import roles.customObject_delete;
import roles.customObject_query;
import roles.customObject_update;

@Service (serverPath="/seycon/customObjectService", serverRole="agent")
@Depends({CustomObjectEntity.class, CustomObjectTypeEntity.class, TipusDadaEntity.class, CustomObjectAttributeEntity.class,
	AttributeValidationService.class, TasqueEntity.class, AuditoriaEntity.class, AsyncRunnerService.class})
public class CustomObjectService {
	@Operation(grantees={customObject_query.class})
	public Collection<CustomObject> findCustomObjectByJsonQuery (String objectType, @Nullable String query) { return null;}
	
	@Operation(grantees={customObject_query.class})
	public Collection<CustomObject> findCustomObjectByText (String objectType, @Nullable String query) { return null;}

	@Operation(grantees={customObject_query.class})
	public AsyncList<CustomObject> findCustomObjectByJsonQueryAsync (String objectType, @Nullable String query) { return null;}
	
	@Operation(grantees={customObject_query.class})
	public AsyncList<CustomObject> findCustomObjectByTextAsync (String objectType, @Nullable String query) { return null;}

	@Transactional(rollbackFor = { java.lang.Exception.class })
	@Operation(grantees={customObject_query.class})
	public AsyncList<CustomObject> findCustomObjectByTextAndFilterAsync(String objectType, @Nullable String text, @Nullable String filter) { return null; }

	@Transactional(rollbackFor = { java.lang.Exception.class })
	@Operation(grantees={customObject_query.class})
	public Collection<CustomObject> findCustomObjectByTextAndFilter(String objectType, @Nullable String text, @Nullable String filter) { return null; }

	@Operation(grantees={customObject_query.class})
	public CustomObject findCustomObjectByTypeAndName (String objectType, String name) { return null;}

	@Operation(grantees={customObject_create.class})
	public CustomObject createCustomObject (CustomObject obj) { return null;}

	@Operation(grantees={customObject_update.class})
	public CustomObject updateCustomObject (CustomObject obj) { return null;}

	@Operation(grantees={customObject_delete.class})
	public void deleteCustomObject (CustomObject obj) { }

	@Operation
	@Transactional(readOnly=true)
	public java.util.Collection<String> findCustomObjectNames(String objectType) { return null; }

	@Transactional(rollbackFor = { java.lang.Exception.class })
	@Operation(grantees={customObject_query.class})
	public PagedResult<CustomObject> findCustomObjectByTextAndJsonQuery(@Nullable String text, @Nullable String filter, @Nullable Integer start, @Nullable Integer end) { return null; }

	@Transactional(rollbackFor = { java.lang.Exception.class })
	@Operation(grantees={customObject_query.class})
	public AsyncList<CustomObject> findCustomObjectByTextAndJsonQueryAsync(@Nullable String text, @Nullable String filter) { return null; }

	@Transactional(rollbackFor = { java.lang.Exception.class })
	@Operation(grantees={customObject_query.class})
	public PagedResult<CustomObject> findCustomObjectByTextAndJsonQuery(String objectType, @Nullable String text, @Nullable String filter, @Nullable Integer start, @Nullable Integer end) { return null; }

	@Transactional(rollbackFor = { java.lang.Exception.class })
	@Operation(grantees={customObject_query.class})
	public AsyncList<CustomObject> findCustomObjectByTextAndJsonQueryAsync(String objectType, @Nullable String text, @Nullable String filter) { return null; }
}
