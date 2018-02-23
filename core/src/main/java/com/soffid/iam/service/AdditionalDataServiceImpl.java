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

import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.soffid.iam.api.AttributeVisibilityEnum;
import com.soffid.iam.api.Audit;
import com.soffid.iam.api.CustomObjectType;
import com.soffid.iam.api.DataType;
import com.soffid.iam.api.MetadataScope;
import com.soffid.iam.api.UserData;
import com.soffid.iam.model.AccountMetadataEntity;
import com.soffid.iam.model.CustomObjectTypeEntity;
import com.soffid.iam.model.MetaDataEntity;
import com.soffid.iam.model.Parameter;
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

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.exception.SeyconException;

/**
 * @see es.caib.seycon.ng.servei.DadesAddicionalsService
 */
public class AdditionalDataServiceImpl extends
		com.soffid.iam.service.AdditionalDataServiceBase {

	/**
	 * @see es.caib.seycon.ng.servei.DadesAddicionalsService#getTipusDades()
	 */
	protected java.util.Collection<DataType> handleGetDataTypes() throws java.lang.Exception {
		List<MetaDataEntity> col = this.getMetaDataEntityDao().loadAll();
		for ( Iterator<MetaDataEntity> it = col.iterator(); it.hasNext(); )
		{
			MetaDataEntity td = it.next();
			if (td.getScope() != null && ! td.getScope().equals(MetadataScope.USER))
				it.remove();
		}
		return getMetaDataEntityDao().toDataTypeList(col);
	}

	/**
	 * @see es.caib.seycon.ng.servei.DadesAddicionalsService#create(es.caib.seycon.ng.comu.TipusDada)
	 */
	protected com.soffid.iam.api.DataType handleCreate(com.soffid.iam.api.DataType tipusDada) throws java.lang.Exception {
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
			if(tipusDadaMateixCodi != null)
				throw new SeyconException(String.format(Messages.getString("AdditionalDataServiceImpl.IntegrityViolationCode"), new Object[]{tipusDada.getCode()}));
			AccountMetadataEntity tipusDadaEntity = getAccountMetadataEntityDao().dataTypeToEntity(tipusDada);
			if (tipusDadaEntity != null) {
				getAccountMetadataEntityDao().create(tipusDadaEntity);
				tipusDada.setId(tipusDadaEntity.getId());
				return getAccountMetadataEntityDao().toDataType(tipusDadaEntity);
			}
		}
		else
		{
			validateUniqueOrderForMetaData(tipusDada);
			List<MetaDataEntity> tipusDadaMateixCodi = getMetaDataEntityDao().findDataTypesByScopeAndName(tipusDada.getScope(), tipusDada.getCode());
			if(tipusDadaMateixCodi != null && !tipusDadaMateixCodi.isEmpty())
				throw new SeyconException(String.format(Messages.getString("AdditionalDataServiceImpl.IntegrityViolationCode"), new Object[]{tipusDada.getCode()}));
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
			validateUniqueOrderForMetaData(tipusDada);
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
			validateUniqueOrderForAccountMetadata(tipusDada);
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
		if (dataTypeVO.getOrder() == 0) {
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
		List<MetaDataEntity> dataTypeEntityList = null;
		if (dataTypeVO.getScope().equals(MetadataScope.CUSTOM)) {
			dataTypeEntityList = getMetaDataEntityDao().findByObjectTypeAndName(dataTypeVO.getCustomObjectType(), null);
		} else {
			dataTypeEntityList = getMetaDataEntityDao().findByScope(dataTypeVO.getScope());
		}
		for (MetaDataEntity dataTypeEntity : dataTypeEntityList) {
			if ((dataTypeVO.getId() == null || ! dataTypeVO.getId().equals(dataTypeEntity.getId())) && 
					dataTypeVO.getOrder().equals(dataTypeEntity.getOrder())) {
				throw new SeyconException(String.format(Messages.getString("AdditionalDataServiceImpl.IntegrityViolationOrder"),
						dataTypeVO.getOrder(), dataTypeVO.getCode(), dataTypeEntity.getName()));
			}
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
		
		UserDataEntity dadaUsuariEntity = getUserDataEntityDao().userDataToEntity(dadaUsuari);

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
		return getAccountMetadataEntityDao().toDataTypeList(de.getMetaData());
	}

	@Override
	protected Collection<DataType> handleFindDataTypes(MetadataScope scope)
			throws Exception {
		if ( scope == MetadataScope.USER)
			return handleGetDataTypes();
		
		
		List<MetaDataEntity> col = this.getMetaDataEntityDao().findByScope(scope);
		return getMetaDataEntityDao().toDataTypeList(col);
	}

	@Override
	protected Collection<DataType> handleFindDataTypesByScopeAndName(
			MetadataScope scope, String name) throws Exception { 
		List<MetaDataEntity> col = this.getMetaDataEntityDao().findDataTypesByScopeAndName(scope, name);
		for (MetaDataEntity td: col)
		{
			if (td.getScope() == null)
			{
				td.setScope(MetadataScope.USER);
				getMetaDataEntityDao().update(td);
			}
		}
		List<DataType> tipusDadaList = getMetaDataEntityDao().toDataTypeList(col);
		Collections.sort(tipusDadaList, new Comparator<DataType>() {

			public int compare(DataType o1, DataType o2) {
				if (o1.getScope() == o2.getScope())
					return o1.getOrder().compareTo(o2.getOrder());
				else
					return o1.getScope().compareTo(o2.getScope());
			}
		});
		return tipusDadaList;
	}

	@Override
	protected CustomObjectType handleCreateCustomObjectType(CustomObjectType obj) throws Exception {
		CustomObjectTypeEntity entity = getCustomObjectTypeEntityDao().newCustomObjectTypeEntity();
		getCustomObjectTypeEntityDao().customObjectTypeToEntity(obj, entity, true);
		getCustomObjectTypeEntityDao().create(entity);
		return getCustomObjectTypeEntityDao().toCustomObjectType(entity);
	}

	@Override
	protected void handleDelete(CustomObjectType obj) throws Exception {
		getCustomObjectTypeEntityDao().remove(obj.getId());
	}

	@Override
	protected Collection<CustomObjectType> handleFindCustomObjectTypeByJsonQuery(String query) throws Exception {
		ClassConfig config = Configuration.getClassConfig(CustomObjectType.class);

		AbstractExpression expr = ExpressionParser.parse(query);
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
		List<MetaDataEntity> r = getMetaDataEntityDao().findByObjectTypeAndName(objectType, code);
		return getMetaDataEntityDao().toDataTypeList(r);
	}

	@Override
	protected CustomObjectType handleUpdateCustomObjectType(CustomObjectType obj) throws Exception {
		CustomObjectTypeEntity entity = getCustomObjectTypeEntityDao().load(obj.getId());
		getCustomObjectTypeEntityDao().customObjectTypeToEntity(obj, entity, true);
		getCustomObjectTypeEntityDao().create(entity);
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
}
