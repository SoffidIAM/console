//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.servei;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.soffid.iam.api.AsyncList;
import com.soffid.iam.service.AsyncRunnerService;
import com.soffid.mda.annotation.Depends;
import com.soffid.mda.annotation.Nullable;
import com.soffid.mda.annotation.Operation;
import com.soffid.mda.annotation.Service;

import es.caib.seycon.ng.comu.ValorDomini;

@Service (translatedName="DomainService",
	translatedPackage="com.soffid.iam.service")
@Depends ({es.caib.seycon.ng.model.DominiAplicacioEntity.class,
	es.caib.seycon.ng.model.ValorDominiAplicacioEntity.class,
	es.caib.seycon.ng.servei.AplicacioService.class,
	es.caib.seycon.ng.servei.GrupService.class,
	es.caib.seycon.ng.servei.UsuariService.class,
	es.caib.seycon.ng.model.GrupEntity.class,
	es.caib.seycon.ng.model.AplicacioEntity.class,
	AutoritzacioService.class,
	AsyncRunnerService.class})
public abstract class DominiService {

	@Operation(grantees = { roles.application_create.class,
			roles.application_update.class }, translated = "create")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public es.caib.seycon.ng.comu.Domini create(
			es.caib.seycon.ng.comu.Domini domini)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(grantees = { roles.application_delete.class }, translated = "delete")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public void delete(es.caib.seycon.ng.comu.Domini domini)
			throws es.caib.seycon.ng.exception.InternalErrorException {
	}

	@Operation(grantees = { roles.application_create.class,
			roles.application_update.class }, translated = "create")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public es.caib.seycon.ng.comu.ValorDomini create(
			es.caib.seycon.ng.comu.ValorDomini valorDomini)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(grantees = { roles.application_create.class,
			roles.application_update.class })
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public es.caib.seycon.ng.comu.ValorDomini update(
			es.caib.seycon.ng.comu.ValorDomini valorDomini)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(grantees = { roles.application_create.class,
			roles.application_update.class, roles.application_delete.class }, translated = "delete")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public void delete(es.caib.seycon.ng.comu.ValorDomini valorDomini)
			throws es.caib.seycon.ng.exception.InternalErrorException {
	}

	@Operation(translated = "findUserDomainGroup")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public es.caib.seycon.ng.comu.Domini findDominiGrupsUsuari()
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(translated = "findGroupsDomain")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public es.caib.seycon.ng.comu.Domini findDominiGrups()
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(translated = "findApplicationDomainByDomianNameAndApplicationName")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public es.caib.seycon.ng.comu.Domini findDominiAplicacioByNomDominiAndCodiAplicacio(
			java.lang.String nomDomini, java.lang.String codiAplicacio)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(grantees = { roles.application_query.class,
			roles.user_role_query.class }, translated = "findDomainValuesByFilter")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public java.util.Collection<es.caib.seycon.ng.comu.ValorDomini> findValorsDominiByFiltre(
			es.caib.seycon.ng.comu.Domini domini,
			@Nullable java.lang.String codi,
			@Nullable java.lang.String descripcio,
			@Nullable java.lang.String codiUsuari)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(translated = "findApplicationDomainValueByDomainNameAndDomainApplicationNameAndValue")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public es.caib.seycon.ng.comu.ValorDomini findValorDominiAplicacioByNomDominiAndCodiAplicacioDominiAndValor(
			java.lang.String nomDomini, java.lang.String codiAplicacio,
			java.lang.String valor)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(grantees = { roles.application_query.class }, translated = "findApplicationDomainsByApplicationName")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public java.util.Collection<es.caib.seycon.ng.comu.Domini> findDominisAplicacioByCodiAplicacio(
			java.lang.String codiAplicacio)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(translated = "update")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public es.caib.seycon.ng.comu.Domini update(
			es.caib.seycon.ng.comu.Domini domini)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(grantees = { roles.application_query.class }, translated = "findDomainsByApplicationName")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public java.util.Collection<es.caib.seycon.ng.comu.Domini> findDominisByCodiAplicacio(
			java.lang.String codiAplicacio)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(grantees = { roles.application_query.class })
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public es.caib.seycon.ng.comu.Domini findDomainByApplicationAndName(
			java.lang.String codiAplicacio,
			String name)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}


	@Operation(grantees = { roles.application_query.class })
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public List<ValorDomini> findDomainValueByTextAndFilter(
			@Nullable String text, 
			@Nullable String query, 
			@Nullable Integer first,
			@Nullable Integer max)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(grantees = { roles.application_query.class })
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public AsyncList<ValorDomini> findDomainValueByTextAndFilterAsync(
			@Nullable String text,
			@Nullable String query)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}
}
