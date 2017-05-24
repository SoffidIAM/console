//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.servei;

import java.util.List;

import com.soffid.iam.api.MetadataScope;
import com.soffid.iam.model.AccountMetadataEntity;
import com.soffid.mda.annotation.*;

import es.caib.seycon.ng.comu.TipusDada;
import es.caib.seycon.ng.model.DispatcherEntity;
import net.sourceforge.plantuml.png.MetadataTag;

import org.springframework.transaction.annotation.Transactional;

import roles.ALL;
import roles.Tothom;

@Service(serverPath = "/seycon/DadesAddicionalsService", serverRole = "agent", translatedName = "AdditionalDataService", translatedPackage = "com.soffid.iam.service")
@Depends({ es.caib.seycon.ng.model.TipusDadaEntity.class,
		es.caib.seycon.ng.model.DadaUsuariEntity.class,
		es.caib.seycon.ng.model.UsuariEntity.class,
		AccountMetadataEntity.class, DispatcherEntity.class,
		com.soffid.iam.service.RuleEvaluatorService.class,
		AuditoriaService.class})
public abstract class DadesAddicionalsService {

	@Operation(translated = "getDataTypes", grantees={Tothom.class})
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public java.util.Collection<es.caib.seycon.ng.comu.TipusDada> getTipusDades()
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}


	@Operation(grantees={Tothom.class})
	@Transactional(rollbackFor = { java.lang.Exception.class })
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
			java.lang.String codi)
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
	public List<TipusDada> findSystemDataTypes(String system)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

}
