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

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.soffid.iam.api.Account;
import com.soffid.iam.api.Application;
import com.soffid.iam.api.AttributeVisibilityEnum;
import com.soffid.iam.api.Audit;
import com.soffid.iam.api.CustomObjectType;
import com.soffid.iam.api.DataType;
import com.soffid.iam.api.Group;
import com.soffid.iam.api.GroupUser;
import com.soffid.iam.api.Host;
import com.soffid.iam.api.LetterCaseEnum;
import com.soffid.iam.api.MailList;
import com.soffid.iam.api.MetadataScope;
import com.soffid.iam.api.Role;
import com.soffid.iam.api.User;
import com.soffid.iam.api.UserData;
import com.soffid.iam.model.AccountMetadataEntity;
import com.soffid.iam.model.CustomDialect;
import com.soffid.iam.model.CustomObjectRoleEntity;
import com.soffid.iam.model.CustomObjectTypeEntity;
import com.soffid.iam.model.MetaDataEntity;
import com.soffid.iam.model.Parameter;
import com.soffid.iam.model.RoleEntity;
import com.soffid.iam.model.SystemEntity;
import com.soffid.iam.model.UserDataEntity;
import com.soffid.iam.model.UserEntity;
import com.soffid.iam.utils.ConfigurationCache;
import com.soffid.iam.utils.Security;
import com.soffid.scimquery.HQLQuery;
import com.soffid.scimquery.conf.ClassConfig;
import com.soffid.scimquery.conf.Configuration;
import com.soffid.scimquery.expr.AbstractExpression;
import com.soffid.scimquery.parser.ExpressionParser;

import es.caib.seycon.ng.comu.AccountAccessLevelEnum;
import es.caib.seycon.ng.comu.TipusDada;
import es.caib.seycon.ng.comu.TypeEnumeration;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.exception.SeyconException;

/**
 * @see es.caib.seycon.ng.servei.DadesAddicionalsService
 */
public class AdditionalDataServiceImpl extends
		com.soffid.iam.service.AdditionalDataServiceBase {
	Map<String, MetadataScope> scopeForType = new HashMap<>();
	Map<MetadataScope, String> typeForScope = new HashMap<>();

	private void registerMapping (String name, MetadataScope scope) {
		scopeForType.put(name, scope);
		typeForScope.put(scope, name);
	}
	public AdditionalDataServiceImpl () {
		registerMapping(User.class.getName(), MetadataScope.USER);
		registerMapping(Account.class.getName(), MetadataScope.ACCOUNT);
		registerMapping(Group.class.getName(), MetadataScope.GROUP);
		registerMapping(GroupUser.class.getName(), MetadataScope.GROUP_MEMBERSHIP);
		registerMapping(MailList.class.getName(), MetadataScope.MAIL_LIST);
		registerMapping(Role.class.getName(), MetadataScope.ROLE);
	}
	/**
	 * @see es.caib.seycon.ng.servei.DadesAddicionalsService#getTipusDades()
	 */
	protected java.util.Collection<DataType> handleGetDataTypes() throws java.lang.Exception {
		CustomObjectTypeEntity user = getCustomObjectTypeEntityDao().findByName(User.class.getName());
		Collection<MetaDataEntity> col = new LinkedList<>(user.getAttributes());
		for ( Iterator<MetaDataEntity> iterator = col.iterator(); iterator.hasNext(); )
		{
			MetaDataEntity md = iterator.next();
			if ( Boolean.TRUE.equals(md.getBuiltin()))
				iterator.remove();
		}
		LinkedList <DataType> list = new LinkedList<DataType>( getMetaDataEntityDao().toDataTypeList(col));
		Collections.sort(list, new Comparator<DataType>() {
			@Override
			public int compare(DataType o1, DataType o2) {
				return o1.getOrder().compareTo(o2.getOrder());
			}
			
		});
		return list;
	}

	/**
	 * @see es.caib.seycon.ng.servei.DadesAddicionalsService#create(es.caib.seycon.ng.comu.TipusDada)
	 */
	protected com.soffid.iam.api.DataType handleCreate(com.soffid.iam.api.DataType tipusDada) throws java.lang.Exception {
		// Compatibility with v2
		if (tipusDada.getObjectType() == null) {
			if (tipusDada.getScope() == MetadataScope.ACCOUNT)
				tipusDada.setObjectType(Account.class.getName());
			if (tipusDada.getScope() == MetadataScope.APPLICATION)
				tipusDada.setObjectType(Application.class.getName());
			if (tipusDada.getScope() == MetadataScope.GROUP)
				tipusDada.setObjectType(Group.class.getName());
			if (tipusDada.getScope() == MetadataScope.GROUP_MEMBERSHIP)
				tipusDada.setObjectType(GroupUser.class.getName());
			if (tipusDada.getScope() == MetadataScope.MAIL_LIST)
				tipusDada.setObjectType(MailList.class.getName());
			if (tipusDada.getScope() == MetadataScope.ROLE)
				tipusDada.setObjectType(Role.class.getName());
			if (tipusDada.getScope() == MetadataScope.USER || tipusDada.getScope() == null)
				tipusDada.setObjectType(User.class.getName());
		}
		if (tipusDada.getAdminVisibility() == null)
			tipusDada.setAdminVisibility(AttributeVisibilityEnum.EDITABLE);
		if (tipusDada.getAdminVisibility() == null)
			tipusDada.setOperatorVisibility(AttributeVisibilityEnum.EDITABLE);
		if (tipusDada.getAdminVisibility() == null)
			tipusDada.setUserVisibility(AttributeVisibilityEnum.READONLY);

		if (tipusDada.getSystemName() != null && tipusDada.getSystemName().trim().length() > 0)
		{
			validateUniqueOrderForAccountMetadata(tipusDada);
			AccountMetadataEntity tipusDadaMateixCodi = getAccountMetadataEntityDao().findByName(tipusDada.getSystemName(), tipusDada.getCode());
			if(tipusDadaMateixCodi != null) {
				throw new SeyconException(String.format(Messages.getString("AdditionalDataServiceImpl.IntegrityViolationCode"), new Object[]{tipusDada.getCode()}));
			}
			AccountMetadataEntity tipusDadaEntity = getAccountMetadataEntityDao().dataTypeToEntity(tipusDada);
			if (tipusDadaEntity != null) {
				getAccountMetadataEntityDao().create(tipusDadaEntity);
				tipusDada.setId(tipusDadaEntity.getId());
				return getAccountMetadataEntityDao().toDataType(tipusDadaEntity);
			}
		}
		else
		{
			if (tipusDada.getObjectType() == null)
				tipusDada.setObjectType(typeForScope.get(tipusDada.getScope()));
			validateUniqueOrderForMetaData(tipusDada);
			List<MetaDataEntity> tipusDadaMateixCodi = getMetaDataEntityDao().findByObjectTypeAndName(tipusDada.getCustomObjectType(), tipusDada.getCode());
			if(tipusDadaMateixCodi != null && !tipusDadaMateixCodi.isEmpty()) {
				MetaDataEntity d = tipusDadaMateixCodi.get(0);
				if (Boolean.TRUE.equals(d.getBuiltin())) {
					tipusDada.setBuiltin(d.getBuiltin());
					tipusDada.setId(d.getId());
					return handleUpdate(tipusDada);
				}
				else
					throw new SeyconException(String.format(Messages.getString("AdditionalDataServiceImpl.IntegrityViolationCode"), new Object[]{tipusDada.getCode()}));
			}
			MetaDataEntity tipusDadaEntity = getMetaDataEntityDao().dataTypeToEntity(tipusDada);
			if (tipusDadaEntity != null) {
				getMetaDataEntityDao().create(tipusDadaEntity);
				tipusDada.setId(tipusDadaEntity.getId());
				return getMetaDataEntityDao().toDataType(tipusDadaEntity);
			}
		}
		return null;
	}

	/**
	 * @see es.caib.seycon.ng.servei.DadesAddicionalsService#delete(es.caib.seycon.ng.comu.TipusDada)
	 */
	protected void handleDelete(com.soffid.iam.api.DataType tipusDada) throws java.lang.Exception {
		if (tipusDada.getSystemName() == null || tipusDada.getSystemName().length() == 0)
		{
			MetaDataEntity tipusDadaEntity = getMetaDataEntityDao().load(tipusDada.getId());
			getMetaDataEntityDao().remove(tipusDadaEntity);
		} else {
			AccountMetadataEntity tipusDadaEntity = getAccountMetadataEntityDao().load(tipusDada.getId());
			getAccountMetadataEntityDao().remove(tipusDadaEntity);
		}
	}

	/**
	 * @see es.caib.seycon.ng.servei.DadesAddicionalsService#update(es.caib.seycon.ng.comu.TipusDada)
	 */
	protected com.soffid.iam.api.DataType handleUpdate(com.soffid.iam.api.DataType tipusDada) throws java.lang.Exception {
		if (tipusDada.getSystemName() == null || tipusDada.getSystemName().trim().length() == 0)
		{
			MetaDataEntity tipusDadaEntity = getMetaDataEntityDao().dataTypeToEntity(tipusDada);
		
			if (tipusDadaEntity.getAdminVisibility() == null)
				tipusDadaEntity.setAdminVisibility(AttributeVisibilityEnum.EDITABLE);
			if (tipusDadaEntity.getAdminVisibility() == null)
				tipusDadaEntity.setOperatorVisibility(AttributeVisibilityEnum.EDITABLE);
			if (tipusDadaEntity.getAdminVisibility() == null)
				tipusDadaEntity.setUserVisibility(AttributeVisibilityEnum.READONLY);
	
			getMetaDataEntityDao().update(tipusDadaEntity);
			return getMetaDataEntityDao().toDataType(tipusDadaEntity);
		} else {
			AccountMetadataEntity tipusDadaEntity = getAccountMetadataEntityDao().dataTypeToEntity(tipusDada);
			
			if (tipusDadaEntity.getAdminVisibility() == null)
				tipusDadaEntity.setAdminVisibility(AttributeVisibilityEnum.EDITABLE);
			if (tipusDadaEntity.getAdminVisibility() == null)
				tipusDadaEntity.setOperatorVisibility(AttributeVisibilityEnum.EDITABLE);
			if (tipusDadaEntity.getAdminVisibility() == null)
				tipusDadaEntity.setUserVisibility(AttributeVisibilityEnum.READONLY);
	
			getAccountMetadataEntityDao().update(tipusDadaEntity);
			return getAccountMetadataEntityDao().toDataType(tipusDadaEntity);
		}
	}

	/**
	 * Validate unique order for different custom objects types
	 */
	private void validateUniqueOrderForAccountMetadata(DataType dataTypeVO) {
		List<AccountMetadataEntity> dataTypeEntityList = getAccountMetadataEntityDao().findBySystem(dataTypeVO.getSystemName());
		if (dataTypeVO.getOrder() == null || dataTypeVO.getOrder().equals( 0 ) ) {
			long next = 10;
			for (AccountMetadataEntity dataTypeEntity : dataTypeEntityList) {
				if (dataTypeEntity.getOrder().longValue() >= next) next = dataTypeEntity.getOrder().longValue() + 1;
			}
			dataTypeVO.setOrder(next);
		} else {
			for (AccountMetadataEntity dataTypeEntity : dataTypeEntityList) {
				if ((dataTypeVO.getId()==null || !dataTypeEntity.getId().equals(dataTypeVO.getId())) && 
						dataTypeVO.getOrder().equals(dataTypeEntity.getOrder())) {
					throw new SeyconException(String.format(Messages.getString("AdditionalDataServiceImpl.IntegrityViolationOrder"),
							dataTypeVO.getOrder(), dataTypeVO.getCode(), dataTypeEntity.getName()));
				}
			}
		}
	}

	/**
	 * Validate unique order for different custom objects types
	 */
	private void validateUniqueOrderForMetaData(DataType dataTypeVO) {
		if (dataTypeVO.getOrder() == null)
		{
			long max = 1;
			List<MetaDataEntity> dt = getMetaDataEntityDao().findByObjectTypeAndName(dataTypeVO.getCustomObjectType(), null);
			for ( MetaDataEntity d: dt)
				if (d.getOrder() != null && d.getOrder().longValue() >= max)
					max = d.getOrder().longValue()+1;
			dataTypeVO.setOrder(new Long(max));
		}
	}

	/**
	 * @see es.caib.seycon.ng.servei.DadesAddicionalsService#findTipusDadesByCodi(java.lang.String)
	 */
	protected java.util.Collection<DataType> handleFindDataTypesByName(java.lang.String codi) throws java.lang.Exception {
		int limitResults = Integer.parseInt(ConfigurationCache.getProperty("soffid.ui.maxrows")); //$NON-NLS-1$
		
		if (codi != null
				&& (codi.trim().compareTo("") == 0 || codi.trim() //$NON-NLS-1$
						.compareTo("%") == 0)) { //$NON-NLS-1$
			codi = null;
		}
		
		List<MetaDataEntity> dades = getMetaDataEntityDao().findDataTypesByName(codi);
		
		Collections.sort(dades, new Comparator<MetaDataEntity>(){
            
            
            public int compare(MetaDataEntity o1, MetaDataEntity o2) {
                return o1.getOrder().compareTo(o2.getOrder());
            }
        });
		
		if (dades != null)
		{
			// Check maximum number of results
			if (dades.size() > limitResults)
			{
				return getMetaDataEntityDao().toDataTypeList(dades).subList(0, limitResults);
			}
			
			return getMetaDataEntityDao().toDataTypeList(dades);
		}
		
		return new Vector();
	}

	protected DataType handleFindDataTypeByName(java.lang.String codi) throws java.lang.Exception {
		MetaDataEntity tipusDadaEntity = getMetaDataEntityDao().findDataTypeByName(codi);
		if (tipusDadaEntity != null) {
			DataType tipusDada = getMetaDataEntityDao().toDataType(tipusDadaEntity);
			return tipusDada;
		}
		return null;
	}

	public UserData handleCreate(UserData dadaUsuari) throws InternalErrorException {
		
		UserDataEntity dadaUsuariEntity = dadaUsuari.getId() == null ? null: getUserDataEntityDao().load ( dadaUsuari.getId());
		
		if (dadaUsuariEntity == null)
			dadaUsuariEntity = getUserDataEntityDao().userDataToEntity(dadaUsuari);
		else
			getUserDataEntityDao().userDataToEntity(dadaUsuari, dadaUsuariEntity, true);

		AttributeVisibilityEnum visibility = dadaUsuariEntity.getAttributeVisibility();

		if (!visibility.equals(AttributeVisibilityEnum.EDITABLE))
			throw new SecurityException(String.format("Not allowed to modify the attributes %s", dadaUsuari.getAttribute()));
		
		UserEntity usuari = dadaUsuariEntity.getUser();
		usuari.setLastModificationDate(GregorianCalendar.getInstance().getTime());
		usuari.setLastUserModification(Security.getCurrentAccount());
		getUserEntityDao().update(usuari);

		getUserDataEntityDao().create(dadaUsuariEntity);
		
		auditChange(dadaUsuari);

		getRuleEvaluatorService().applyRules(usuari);
		return getUserDataEntityDao().toUserData(dadaUsuariEntity);
	}

	public void handleDelete(UserData dadaUsuari) throws InternalErrorException {
		if (dadaUsuari.getId() != null)
		{
			UserDataEntity dadaUsuariEntity = getUserDataEntityDao().userDataToEntity(dadaUsuari);
	
			AttributeVisibilityEnum visibility = dadaUsuariEntity.getAttributeVisibility(); 
	
			if (!visibility.equals(AttributeVisibilityEnum.EDITABLE))
				throw new SecurityException(String.format("Not allowed to modify the attributes %s", dadaUsuari.getAttribute()));
	
			UserEntity usuari = dadaUsuariEntity.getUser();
			usuari.setLastModificationDate(GregorianCalendar.getInstance().getTime());
			usuari.setLastUserModification(Security.getCurrentAccount());
			getUserEntityDao().update(usuari);
	
			getUserDataEntityDao().remove(dadaUsuariEntity);
	
			auditChange(dadaUsuari);
	
			getRuleEvaluatorService().applyRules(usuari);
		}
	}

	public UserData handleUpdate(UserData dadaUsuari) throws InternalErrorException {
		if (dadaUsuari.getId() != null) {
			auditChange(dadaUsuari);
			if (dadaUsuari.getDateValue() == null && 
					dadaUsuari.getBlobDataValue() == null && 
					( dadaUsuari.getValue() == null || equals(dadaUsuari.getValue().trim().isEmpty())))
			{ 
				delete(dadaUsuari); // esborrar dada
				dadaUsuari.setId(null);
				return dadaUsuari;
			} else {
				// Aquí comprovem si pot actualitzar totes les dades addicionals
				// o només el telèfon
				UserDataEntity dadaUsuariEntity = getUserDataEntityDao().userDataToEntity(dadaUsuari);
				
				AttributeVisibilityEnum visibility = dadaUsuariEntity.getAttributeVisibility();

				if (!visibility.equals(AttributeVisibilityEnum.EDITABLE))
					throw new SecurityException(String.format("Not allowed to modify the attributes %s", dadaUsuari.getAttribute()));
				
				UserEntity usuari = dadaUsuariEntity.getUser();
				usuari.setLastModificationDate(GregorianCalendar.getInstance().getTime());
				usuari.setLastUserModification(Security.getCurrentAccount());
				getUserEntityDao().update(usuari);

				getUserDataEntityDao().update(dadaUsuariEntity);
				getRuleEvaluatorService().applyRules(usuari);
				return getUserDataEntityDao().toUserData(dadaUsuariEntity);
			}
		} else {
			return create(dadaUsuari);
		}
	}

	private void auditChange(UserData dadaUsuari) throws InternalErrorException {
		Audit audit = new Audit();
		audit.setObject("SC_DADUSU");
		audit.setAction("U");
		audit.setAuthor(Security.getCurrentAccount());
		audit.setCalendar(Calendar.getInstance());
		audit.setConfigurationParameter(dadaUsuari.getAttribute());
		audit.setUser(dadaUsuari.getUser());
		getAuditService().create(audit);
	}

	@Override
    protected DataType handleFindSystemDataType(String system, String name) throws Exception {
		AccountMetadataEntity am = getAccountMetadataEntityDao().findByName(system, name);
		if (am == null)
			return null;
		else
			return getAccountMetadataEntityDao().toDataType(am);
	}

	@Override
    protected List<DataType> handleFindSystemDataTypes(String system) throws Exception {
		SystemEntity de = getSystemEntityDao().findByName(system);
		if (de == null)
			return null;
		LinkedList <DataType>col = new LinkedList<DataType>( getAccountMetadataEntityDao().toDataTypeList(de.getMetaData()));
		for ( Iterator<DataType> iterator = col.iterator(); iterator.hasNext(); )
		{
			DataType md = iterator.next();
			if ( Boolean.TRUE.equals(md.getBuiltin()))
				iterator.remove();
		}
		Collections.sort(col, new Comparator<DataType>() {
			@Override
			public int compare(DataType o1, DataType o2) {
				return o1.getOrder().compareTo(o2.getOrder());
			}
			
		});
		return col;
	}

	@Override
	protected Collection<DataType> handleFindDataTypes(MetadataScope scope)
			throws Exception {
		
		String name = typeForScope.get(scope);
		if ( name == null)
			return new LinkedList<>();
		
		CustomObjectTypeEntity entity = getCustomObjectTypeEntityDao().findByName(name);
		if (entity == null)
			return new LinkedList<>();
		Collection<MetaDataEntity> entities = new LinkedList<MetaDataEntity>( entity.getAttributes() );
		for ( Iterator<MetaDataEntity> iterator = entities.iterator(); iterator.hasNext(); )
		{
			MetaDataEntity md = iterator.next();
			if ( Boolean.TRUE.equals(md.getBuiltin()))
				iterator.remove();
		}
		LinkedList <DataType> col = new LinkedList<DataType>( getMetaDataEntityDao().toDataTypeList(entities));
		Collections.sort(col, new Comparator<DataType>() {
			@Override
			public int compare(DataType o1, DataType o2) {
				return o1.getOrder().compareTo(o2.getOrder());
			}
			
		});
		return col;
	}

	@Override
	protected Collection<DataType> handleFindDataTypesByScopeAndName(
			MetadataScope scope, String name) throws Exception {
		return handleFindDataTypesByObjectTypeAndName(typeForScope.get(scope), name);
	}

	@Override
	protected CustomObjectType handleCreateCustomObjectType(CustomObjectType obj) throws Exception {
		CustomObjectTypeEntity entity = getCustomObjectTypeEntityDao().newCustomObjectTypeEntity();
		getCustomObjectTypeEntityDao().customObjectTypeToEntity(obj, entity, true);
		getCustomObjectTypeEntityDao().create(entity);
		updateRoles(entity, obj);
		
		if (! obj.isBuiltin()) {
			MetaDataEntity name = getMetaDataEntityDao().newMetaDataEntity();
			name.setBuiltin(true);
			name.setObjectType(entity);
			name.setName("name");
			name.setNlsLabel("com.soffid.iam.api.CustomObject.name");
			name.setOrder(1L);
			name.setRequired(true);
			name.setSize(100);
			name.setType(TypeEnumeration.STRING_TYPE);
			getMetaDataEntityDao().create(name);
			
			
			MetaDataEntity description = getMetaDataEntityDao().newMetaDataEntity();
			description.setBuiltin(true);
			description.setObjectType(entity);
			description.setName("description");
			description.setNlsLabel("com.soffid.iam.api.CustomObject.description");
			description.setOrder(2L);
			description.setRequired(true);
			description.setSize(100);
			description.setType(TypeEnumeration.STRING_TYPE);
			getMetaDataEntityDao().create(description);
		}
		
		return getCustomObjectTypeEntityDao().toCustomObjectType(entity);
	}

	private void updateRoles(CustomObjectTypeEntity entity, CustomObjectType obj) throws InternalErrorException {
		getCustomObjectRoleEntityDao().remove(entity.getAccessRoles());
		if (obj != null && !obj.isBuiltin() && Boolean.FALSE.equals( obj.getPublicAccess())) {
			for (String g: obj.getManagerRoles())
			{
				RoleEntity r = getRoleEntityDao().findByShortName(g);
				if (r == null)
					throw new InternalErrorException("Wrong role "+g);
				CustomObjectRoleEntity c = getCustomObjectRoleEntityDao().newCustomObjectRoleEntity();
				c.setCustomObjectType(entity);
				c.setLevel(AccountAccessLevelEnum.ACCESS_MANAGER);
				c.setRole(r);
				getCustomObjectRoleEntityDao().create(c);
			}
			for (String g: obj.getUserRoles())
			{
				RoleEntity r = getRoleEntityDao().findByShortName(g);
				if (r == null)
					throw new InternalErrorException("Wrong role "+g);
				CustomObjectRoleEntity c = getCustomObjectRoleEntityDao().newCustomObjectRoleEntity();
				c.setCustomObjectType(entity);
				c.setLevel(AccountAccessLevelEnum.ACCESS_USER);
				c.setRole(r);
				getCustomObjectRoleEntityDao().create(c);
			}
		}
	}
	@Override
	protected void handleDeleteCustomObjectType(CustomObjectType obj) throws Exception {
		CustomObjectTypeEntity t = getCustomObjectTypeEntityDao().load(obj.getId());
				
		getCustomObjectRoleEntityDao().remove(t.getAccessRoles());
		
		getCustomObjectTypeEntityDao().remove(t);
	}

	@Override
	protected Collection<CustomObjectType> handleFindCustomObjectTypeByJsonQuery(String query) throws Exception {
		ClassConfig config = Configuration.getClassConfig(CustomObjectType.class);

		AbstractExpression expr = ExpressionParser.parse(query);
		expr.setOracleWorkaround( CustomDialect.isOracle());
		HQLQuery hql = expr.generateHSQLString(CustomObjectType.class);
		String qs = hql.getWhereString().toString();
		if (qs.isEmpty())
			qs = "o.tenant.id = :tenantId";
		else
			qs = "("+qs+") and o.tenant.id = :tenantId";
		
		hql.setWhereString(new StringBuffer(qs));
		Map<String, Object> params = hql.getParameters();
		Parameter paramArray[] = new Parameter[params.size()+1];
		int i = 0;
		for (String s : params.keySet())
			paramArray[i++] = new Parameter(s, params.get(s));
		paramArray[i++] = new Parameter("tenantId", Security.getCurrentTenantId());
		Collection<CustomObjectType> result = new LinkedList<CustomObjectType>();
		for (CustomObjectTypeEntity ue : getCustomObjectTypeEntityDao().query(hql.toString(),
				paramArray)) 
		{
			CustomObjectType u = getCustomObjectTypeEntityDao().toCustomObjectType(ue);
			if (!hql.isNonHQLAttributeUsed() || expr.evaluate(u)) {
				result.add(u);
			}
		}
		return result;
	}

	@Override
	protected Collection<DataType> handleFindDataTypesByObjectTypeAndName(String objectType, String code)
			throws Exception {
		List<MetaDataEntity> col = new LinkedList<MetaDataEntity> (getMetaDataEntityDao().findByObjectTypeAndName(objectType, code));
		for ( Iterator<MetaDataEntity> it = col.iterator(); it.hasNext(); )
		{
			MetaDataEntity td = it.next();
			if (Boolean.TRUE.equals( td.getBuiltin() ) )
				it.remove();
		}
		return getMetaDataEntityDao().toDataTypeList(col);
	}

	@Override
	protected CustomObjectType handleUpdateCustomObjectType(CustomObjectType obj) throws Exception {
		CustomObjectTypeEntity entity = getCustomObjectTypeEntityDao().load(obj.getId());
		getCustomObjectTypeEntityDao().customObjectTypeToEntity(obj, entity, true);
		getCustomObjectTypeEntityDao().update(entity);
		updateRoles(entity, obj);
		return getCustomObjectTypeEntityDao().toCustomObjectType(entity);
	}

	@Override
	protected CustomObjectType handleFindCustomObjectTypeByName(String name) throws Exception {
		CustomObjectTypeEntity o = getCustomObjectTypeEntityDao().findByName(name);
		if ( o == null)
			return null;
		else
			return getCustomObjectTypeEntityDao().toCustomObjectType(o);
	}

	@Override
	protected Collection<DataType> handleFindDataTypes2(MetadataScope scope) throws Exception {
		return handleFindDataTypesByObjectTypeAndName2(typeForScope.get(scope), null);
	}

	@Override
	protected List<DataType> handleFindSystemDataTypes2(String system) throws Exception {
		SystemEntity de = getSystemEntityDao().findByName(system);
		if (de == null)
			return new LinkedList<>();
		LinkedList <DataType>col = new LinkedList<DataType>( getAccountMetadataEntityDao().toDataTypeList(de.getMetaData()));
		Collections.sort(col, new Comparator<DataType>() {
			@Override
			public int compare(DataType o1, DataType o2) {
				return o1.getOrder().compareTo(o2.getOrder());
			}
			
		});
		return col;
	}

	@Override
	protected Collection<DataType> handleFindDataTypesByObjectTypeAndName2(String objectType, String attribute)
			throws Exception {
		List<MetaDataEntity> r = getMetaDataEntityDao().findByObjectTypeAndName(objectType, attribute);
		if (! r.isEmpty())
			return getMetaDataEntityDao().toDataTypeList(r);
		if (objectType == null)
			return null;
		
		String fileName = objectType.replace(".", "/") + ".ui.json";
		List<DataType> d = getDescriptorMetadata(fileName);
		if (d == null) {
			return new LinkedList<DataType>();
		}
		for (Iterator<DataType> it = d.iterator(); it.hasNext(); ) {
			DataType dt = it.next();
			if (attribute != null && ! attribute.equals(dt.getName()))
				it.remove();
		}
		return d;
	}

	@Override
	protected void handleRegisterStandardObject(String resourceName, MetadataScope scope, boolean reset)
			throws Exception {
		InputStream in = getClass().getClassLoader().getResourceAsStream(resourceName);
		if (in == null)
			throw new IOException("Cannot find resource "+resourceName);
		JSONObject o = new JSONObject(new JSONTokener(in));
		in.close();
		
		String className = o.getString("class");

		JSONArray atts = o.getJSONArray("attributes");
		List<MetaDataEntity> current = getMetaDataEntityDao().findByObjectTypeAndName(className, null);
		long last = 0;
		HashMap<String, MetaDataEntity> map = new HashMap<>();
		for (Iterator<MetaDataEntity> it = current.iterator(); it.hasNext();) {
			MetaDataEntity md = it.next();
			if (reset && md.getBuiltin() != null && md.getBuiltin().booleanValue()) {
				getMetaDataEntityDao().remove(md);
				it.remove();
			} else {
				map.put(md.getName(), md);
				if (md.getOrder().longValue() > last)
					last = md.getOrder().longValue();
			}
		}
		
		CustomObjectTypeEntity cot = getCustomObjectTypeEntityDao().findByName(className);
		if (cot == null) {
			cot = getCustomObjectTypeEntityDao().newCustomObjectTypeEntity();
			cot.setBuiltin(true);
			if (scope != null)
				cot.setDescription("Builtin "+scope.getValue()+" object");
			else {
				String name = className;
				if (name.contains(".")) name = name.substring(name.lastIndexOf('.')+1);
				cot.setDescription("Builtin "+name+" object");
			}
			cot.setName(className);
			cot.setScope(scope);
			cot.setBuiltin(true);
			getCustomObjectTypeEntityDao().create(cot);
			if (scope != null)
				last = upgradeMetadata(cot, scope );
		} else {
			cot.setScope(scope);
			cot.setBuiltin(true);
			getCustomObjectTypeEntityDao().update(cot);
		}
		
		for (int i = 0; i < atts.length(); i++) {
			JSONObject att = atts.getJSONObject(i);
			String name = att.optString("name");
			String type = att.optString("type");
			String lettercase = att.optString("lettercase");
			boolean required = att.optBoolean("required", false);
			if ( resourceName.equals("com/soffid/iam/api/Host.ui.json") && 
					name.equals("networkCode"))
				required = true;
			boolean readonly = att.optBoolean("readonly", false);
			boolean hidden = att.optBoolean("hidden", false);
			boolean multiline = att.optBoolean("multiline", false);
			boolean searchCriteria = att.optBoolean("searchCriteria", false);
			boolean multivalue = att.optBoolean("multivalue", false);
			String customUiHandler = att.optString("custom_ui_handler");
			String separator = att.optString("separator");
			String validator = att.optString("validator");
			String length = att.optString("length");
			String filterExpression = att.optString("filter_expression");
			String enumeration = att.optString("enumeration");
			if (separator != null && 
					!separator.trim().isEmpty() && 
					! map.containsKey("_"+separator.toUpperCase()+"_")) {
				MetaDataEntity md = getMetaDataEntityDao().newMetaDataEntity();
				md.setBuiltin(true);
				md.setMultiValued(false);
				md.setMultiLine(multiline);
				md.setName("_"+separator.toUpperCase()+"_");
				md.setNlsLabel(className+"."+separator);
				md.setOrder(last++);
				md.setRequired(false);
				md.setScope(scope);
				md.setObjectType(cot);
				md.setType(TypeEnumeration.SEPARATOR);
				getMetaDataEntityDao().create(md);
				md.setReadOnly(true);
			}
			if (! map.containsKey(name) && ! hidden) {
				MetaDataEntity md = getMetaDataEntityDao().newMetaDataEntity();
				md.setAdminVisibility( hidden ? AttributeVisibilityEnum.HIDDEN :
					readonly ? AttributeVisibilityEnum.READONLY :
						AttributeVisibilityEnum.EDITABLE);
				md.setBuiltin(true);
				md.setEnumeration(enumeration);
				md.setFilterExpression(filterExpression);
				md.setLetterCase(lettercase != null && lettercase.toLowerCase().startsWith("u") ? LetterCaseEnum.UPPERCASE :
					lettercase != null && lettercase.toLowerCase().startsWith("l") ? LetterCaseEnum.LOWERCASE:
						LetterCaseEnum.MIXEDCASE);
				md.setMultiValued(multivalue);
				md.setName(name);
				md.setNlsLabel(className+"."+name);
				md.setOrder(last++);
				md.setRequired(required);
				md.setSearchCriteria(searchCriteria);
				md.setMultiLine(multiline);
				md.setScope(scope);
				md.setObjectType(cot);
				if (length != null && !length.trim().isEmpty())
					md.setSize(Integer.parseInt(length));
				md.setType(guessType (type));
				md.setValidator(validator);
				md.setReadOnly(readonly);
				md.setBuiltinHandler(customUiHandler);
				JSONArray values = att.optJSONArray("listOfValues");
				if (values != null) {
					StringBuffer sb = new StringBuffer();
					for (int j = 0; j < values.length(); j++) {
						if (sb.length() > 0) sb.append(" ");
						sb.append (URLEncoder.encode(values.optString(j) , "UTF-8"));
					}
					md.setValues(sb.toString());
				}
				getMetaDataEntityDao().create(md);
			}
		}
		
		if (reset) {
			current = getMetaDataEntityDao().findByObjectTypeAndName(className, null);
			for (Iterator<MetaDataEntity> it = current.iterator(); it.hasNext();) {
				MetaDataEntity md = it.next();
				if (md.getBuiltin() == null || ! md.getBuiltin().booleanValue()) {
					md.setOrder(last ++);
					getMetaDataEntityDao().update(md);
				}
			}
			
		}
		
	}

	protected List<DataType> getDescriptorMetadata(String resourceName)
			throws Exception {
		InputStream in = getClass().getClassLoader().getResourceAsStream(resourceName);
		if (in == null)
			return null;
		
		JSONObject o = new JSONObject(new JSONTokener(in));
		in.close();
		
		String className = o.getString("class");

		JSONArray atts = o.getJSONArray("attributes");
		
		long last =  0;
		List<DataType> result = new LinkedList<>();
		for (int i = 0; i < atts.length(); i++) {
			JSONObject att = atts.getJSONObject(i);
			String name = att.optString("name");
			String type = att.optString("type");
			String lettercase = att.optString("lettercase");
			boolean required = att.optBoolean("required", false);
			if ( resourceName.equals("com/soffid/iam/api/Host.ui.json") && 
					name.equals("networkCode"))
				required = true;
			boolean readonly = att.optBoolean("readonly", false);
			boolean hidden = att.optBoolean("hidden", false);
			boolean multiline = att.optBoolean("multiline", false);
			boolean searchCriteria = att.optBoolean("searchCriteria", false);
			boolean multivalue = att.optBoolean("multivalue", false);
			String customUiHandler = att.optString("custom_ui_handler");
			String separator = att.optString("separator");
			String validator = att.optString("validator");
			String length = att.optString("length");
			String filterExpression = att.optString("filter_expression");
			String enumeration = att.optString("enumeration");
			if (! hidden) {
				DataType md = new DataType();
				md.setAdminVisibility( hidden ? AttributeVisibilityEnum.HIDDEN :
					readonly ? AttributeVisibilityEnum.READONLY :
						AttributeVisibilityEnum.EDITABLE);
				md.setBuiltin(true);
				md.setEnumeration(enumeration);
				md.setFilterExpression(filterExpression);
				md.setLetterCase(lettercase != null && lettercase.toLowerCase().startsWith("u") ? LetterCaseEnum.UPPERCASE :
					lettercase != null && lettercase.toLowerCase().startsWith("l") ? LetterCaseEnum.LOWERCASE:
						LetterCaseEnum.MIXEDCASE);
				md.setMultiValued(multivalue);
				md.setName(name);
				md.setNlsLabel(className+"."+name);
				md.setOrder(last++);
				md.setRequired(required);
				md.setSearchCriteria(searchCriteria);
				md.setMultiLine(multiline);
				if (length != null && !length.trim().isEmpty())
					md.setSize(Integer.parseInt(length));
				md.setType(guessType (type));
				md.setValidator(validator);
				md.setReadOnly(readonly);
				md.setBuiltinHandler(customUiHandler);
				JSONArray values = att.optJSONArray("listOfValues");
				if (values != null) {
					List<String> v = new LinkedList<>();
					for (int j = 0; j < values.length(); j++) {
						v.add(values.optString(j));
					}
					md.setValues(v);
				}

				result.add(md);
			}
		}
		return result;
	}

	private long upgradeMetadata(CustomObjectTypeEntity cot, MetadataScope scope) {
		long last = 0;
		for (MetaDataEntity dt: getMetaDataEntityDao().findByScope(scope)) {
			dt.setObjectType(cot);
			getMetaDataEntityDao().update(dt);
			if (dt.getOrder() != null && dt.getOrder().longValue() > last)
				last = dt.getOrder().longValue();
		}
		return last;
	}
	
	private TypeEnumeration guessType(String type) throws InternalErrorException {
		for (String value: (List<String>)TypeEnumeration.literals()) {
			if ( value.equalsIgnoreCase(type))
				return TypeEnumeration.fromString(value);
		}

		int pos = 0;
		for (String value: (List<String>)TypeEnumeration.names()) {
			if ( value.equalsIgnoreCase(type+"_TYPE"))
				return TypeEnumeration.fromString( (String) TypeEnumeration.literals().get(pos));
			pos ++;
		}

		pos = 0;
		for (String value: (List<String>)TypeEnumeration.names()) {
			if ( value.equalsIgnoreCase(type))
				return TypeEnumeration.fromString( (String) TypeEnumeration.literals().get(pos));
			pos ++;
		}
		
		throw new InternalErrorException ("Unknown data type "+type);
	}
	@Override
	protected AccountAccessLevelEnum handleGetAccessLevel(CustomObjectType type) throws Exception {
		CustomObjectTypeEntity entity = getCustomObjectTypeEntityDao().load(type.getId());
		if (entity == null || entity.isBuiltin())
			return null;
		if (entity.getPublicAccess() == null || entity.getPublicAccess().booleanValue())
			return AccountAccessLevelEnum.ACCESS_MANAGER;
		
		String[] soffidRoles = Security.getSoffidPrincipal().getSoffidRoles();
		AccountAccessLevelEnum e = AccountAccessLevelEnum.ACCESS_NONE;
		for ( CustomObjectRoleEntity role: entity.getAccessRoles() ) {
			String roleName = role.getRole().getName()+ "@" + role.getRole().getSystem().getName();
			if (Arrays.binarySearch(soffidRoles, roleName) >= 0) {
				if (isBetter(role.getLevel(), e))
					e = role.getLevel();
			}
		}
		return e;
	}
	private boolean isBetter(AccountAccessLevelEnum level, AccountAccessLevelEnum e) {
		if (level == AccountAccessLevelEnum.ACCESS_OWNER)
			return true;
		else if (level == AccountAccessLevelEnum.ACCESS_MANAGER)
			return e != AccountAccessLevelEnum.ACCESS_OWNER;
		else if (level == AccountAccessLevelEnum.ACCESS_USER)
			return e != AccountAccessLevelEnum.ACCESS_OWNER && e != AccountAccessLevelEnum.ACCESS_MANAGER;
		else if (level == AccountAccessLevelEnum.ACCESS_NAVIGATE)
			return e != AccountAccessLevelEnum.ACCESS_OWNER && e != AccountAccessLevelEnum.ACCESS_MANAGER && e != AccountAccessLevelEnum.ACCESS_USER;
		else
			return false;
	}
}
