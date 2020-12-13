//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.servei;

import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.soffid.iam.api.CustomObjectType;
import com.soffid.iam.api.MetadataScope;
import com.soffid.iam.model.AccountMetadataEntity;
import com.soffid.iam.model.CustomObjectEntity;
import com.soffid.iam.model.CustomObjectTypeEntity;
import com.soffid.mda.annotation.Depends;
import com.soffid.mda.annotation.Description;
import com.soffid.mda.annotation.Nullable;
import com.soffid.mda.annotation.Operation;
import com.soffid.mda.annotation.Service;

import es.caib.seycon.ng.comu.TipusDada;
import es.caib.seycon.ng.model.DispatcherEntity;
import roles.Tothom;
import roles.customObjectType_create;
import roles.customObjectType_delete;
import roles.customObjectType_query;
import roles.customObjectType_update;

@Service(serverPath = "/seycon/DadesAddicionalsService", serverRole = "agent", translatedName = "AdditionalDataService", translatedPackage = "com.soffid.iam.service")
@Depends({ es.caib.seycon.ng.model.TipusDadaEntity.class,
		es.caib.seycon.ng.model.DadaUsuariEntity.class,
		es.caib.seycon.ng.model.UsuariEntity.class,
		AccountMetadataEntity.class, DispatcherEntity.class,
		com.soffid.iam.service.RuleEvaluatorService.class,
		AuditoriaService.class,
		CustomObjectTypeEntity.class,
		CustomObjectEntity.class})
public abstract class DadesAddicionalsService {

	@Operation(translated = "getDataTypes", grantees={Tothom.class})
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public java.util.Collection<es.caib.seycon.ng.comu.TipusDada> getTipusDades()
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}



	@Operation(grantees={Tothom.class})
	@Transactional(rollbackFor = { java.lang.Exception.class })
	@Description("Retrieves the bultin and not builtin attributes")
	public java.util.Collection<es.caib.seycon.ng.comu.TipusDada> findDataTypes2( MetadataScope scope)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}


	@Operation(grantees={Tothom.class})
	@Transactional(rollbackFor = { java.lang.Exception.class })
	@Description("Retrieves the not builtin attributes")
	public java.util.Collection<es.caib.seycon.ng.comu.TipusDada> findDataTypes( MetadataScope scope)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(grantees = { roles.metadata_create.class }, translated = "create")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public es.caib.seycon.ng.comu.TipusDada create(
			es.caib.seycon.ng.comu.TipusDada tipusDada)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(grantees = { roles.metadata_delete.class }, translated = "delete")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public void delete(es.caib.seycon.ng.comu.TipusDada tipusDada)
			throws es.caib.seycon.ng.exception.InternalErrorException {
	}

	@Operation(grantees = { roles.metadata_update.class }, translated = "update")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public es.caib.seycon.ng.comu.TipusDada update(
			es.caib.seycon.ng.comu.TipusDada tipusDada)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(grantees = { roles.metadata_query.class, Tothom.class }, translated = "findDataTypesByName")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public java.util.Collection<es.caib.seycon.ng.comu.TipusDada> findTipusDadesByCodi(
			java.lang.String codi)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(grantees = { roles.metadata_query.class, Tothom.class }, translated = "findDataTypesByScopeAndName")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public java.util.Collection<es.caib.seycon.ng.comu.TipusDada> findTipusDadesByScopeAndName(
			@Nullable MetadataScope scope,
			@Nullable java.lang.String codi)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(grantees = { roles.metadata_query.class, Tothom.class }, translated = "findDataTypesByObjectTypeAndName2")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	@Description("Retrieves the bultin and not builtin attributes")
	public java.util.Collection<es.caib.seycon.ng.comu.TipusDada> findDataTypesByObjectTypeAndName2(
			String objectType,
			@Nullable java.lang.String codi)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(grantees = { roles.metadata_query.class, Tothom.class }, translated = "findDataTypesByObjectTypeAndName")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	@Description("Retrieves the not builtin attributes")
	public java.util.Collection<es.caib.seycon.ng.comu.TipusDada> findDataTypesByObjectTypeAndName(
			String objectType,
			@Nullable java.lang.String codi)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(grantees = { roles.metadata_query.class, Tothom.class }, translated = "findDataTypeByName")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public es.caib.seycon.ng.comu.TipusDada findTipusDadaByCodi(
			java.lang.String codi)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(grantees = { roles.user_metadata_update.class,
			roles.user_custom_update.class }, translated = "create")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public es.caib.seycon.ng.comu.DadaUsuari create(
			es.caib.seycon.ng.comu.DadaUsuari dadaUsuari)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(grantees = { roles.user_metadata_update.class,
			roles.user_custom_update.class }, translated = "delete")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public void delete(es.caib.seycon.ng.comu.DadaUsuari dadaUsuari)
			throws es.caib.seycon.ng.exception.InternalErrorException {
	}

	@Operation(grantees = { roles.user_metadata_update.class,
			roles.user_custom_update.class }, translated = "update")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public es.caib.seycon.ng.comu.DadaUsuari update(
			es.caib.seycon.ng.comu.DadaUsuari dadaUsuari)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}


	@Operation(grantees = { roles.metadata_query.class})
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public es.caib.seycon.ng.comu.TipusDada findSystemDataType(
			String system, String name)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(grantees = { roles.metadata_query.class})
	@Transactional(rollbackFor = { java.lang.Exception.class })
	@Description("Retrieves the bultin and not builtin attributes")
	public List<TipusDada> findSystemDataTypes2(String system)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(grantees = { roles.metadata_query.class})
	@Transactional(rollbackFor = { java.lang.Exception.class })
	@Description("Retrieves the not builtin attributes")
	public List<TipusDada> findSystemDataTypes(String system)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(grantees={customObjectType_query.class})
	public Collection<CustomObjectType> findCustomObjectTypeByJsonQuery (@Nullable String query) { return null;}
	
	@Operation(grantees={customObjectType_query.class})
	public CustomObjectType findCustomObjectTypeByName (String name) { return null;}

	@Operation(grantees={customObjectType_create.class})
	public CustomObjectType createCustomObjectType (CustomObjectType obj) { return null;}

	@Operation(grantees={customObjectType_update.class})
	public CustomObjectType updateCustomObjectType (CustomObjectType obj) { return null;}

	@Operation(grantees={customObjectType_delete.class})
	public void deleteCustomObjectType (CustomObjectType obj) { }
	
	@Operation(grantees = { roles.metadata_update.class })
	public void registerStandardObject ( String resourceName, @Nullable MetadataScope scope, boolean reset) { }
}
