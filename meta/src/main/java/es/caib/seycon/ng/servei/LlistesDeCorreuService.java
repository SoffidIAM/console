//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.servei;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.soffid.iam.api.AsyncList;
import com.soffid.iam.api.MailListRoleMember;
import com.soffid.iam.api.PagedResult;
import com.soffid.iam.model.MailListAttributeEntity;
import com.soffid.iam.model.MailListGroupMemberEntity;
import com.soffid.iam.model.MailListRoleMemberEntity;
import com.soffid.iam.service.AsyncRunnerService;
import com.soffid.iam.service.impl.AttributeValidationService;
import com.soffid.mda.annotation.Depends;
import com.soffid.mda.annotation.Nullable;
import com.soffid.mda.annotation.Operation;
import com.soffid.mda.annotation.Service;

import es.caib.seycon.ng.comu.DominiCorreu;
import es.caib.seycon.ng.comu.LlistaCorreu;
import es.caib.seycon.ng.model.AplicacioEntity;
import es.caib.seycon.ng.model.GrupEntity;
import es.caib.seycon.ng.model.RolEntity;
import es.caib.seycon.ng.model.TipusDadaEntity;
import es.caib.seycon.ng.model.ValorDominiAplicacioEntity;

@Service (translatedName="MailListsService",
	translatedPackage="com.soffid.iam.service")
@Depends ({es.caib.seycon.ng.model.LlistaCorreuEntity.class,
	es.caib.seycon.ng.model.CorreuExternEntity.class,
	es.caib.seycon.ng.model.DominiCorreuEntity.class,
	es.caib.seycon.ng.model.UsuariEntity.class,
	es.caib.seycon.ng.model.AuditoriaEntity.class,
	es.caib.seycon.ng.model.LlistaCorreuUsuariEntity.class,
	es.caib.seycon.ng.model.RelacioLlistaCorreuEntity.class,
	MailListRoleMemberEntity.class, 
	MailListGroupMemberEntity.class,
	AplicacioEntity.class,
	ValorDominiAplicacioEntity.class,
	TipusDadaEntity.class, 
	AttributeValidationService.class,
	MailListAttributeEntity.class,
	RolEntity.class,
	AsyncRunnerService.class,
	GrupEntity.class})
public abstract class LlistesDeCorreuService {

	@Operation ( grantees={roles.mail_query.class},
			translated="getMailLists")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.LlistaCorreu> getLlistesDeCorreu()
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	
	@Operation ( grantees={roles.mail_query.class,roles.user_delete.class},
			translated="findUsersByMailListNameAndDomainName")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.Usuari> findUsuarisByNomLlistaCorreuAndCodiDomini(
		java.lang.String nomLlistaCorreu, 
		@Nullable java.lang.String codiDomini)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}

	@Operation ( grantees={roles.mail_query.class,roles.user_update.class},
			translated="findExternalMailsByNameListAndDomainName")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.CorreuExtern> findCorreusExternsByNomLlistaCorreuAndCodiDomini(
		java.lang.String nomLlistaCorreu, 
		@Nullable java.lang.String codiDomini)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	
	@Operation ( grantees={roles.mail_query.class})
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.Grup> findGroupMembers(
		java.lang.String nomLlistaCorreu, 
		@Nullable java.lang.String codiDomini)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}

	@Operation ( grantees={roles.mail_query.class})
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<MailListRoleMember> findRoleMembers(
		java.lang.String nomLlistaCorreu, 
		@Nullable java.lang.String codiDomini)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}

	@Operation ( grantees={roles.mail_query.class},
			translated="findMailListsByData")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.LlistaCorreu> findLlistesDeCorreuByDades(
		@Nullable java.lang.String nom, 
		@Nullable java.lang.String domini, 
		@Nullable java.lang.String descripcio, 
		@Nullable java.lang.String membres)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.mail_create.class,roles.user_create.class,
			roles.user_update.class},
			translated="create")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.LlistaCorreu create(
		es.caib.seycon.ng.comu.LlistaCorreu llistaCorreu)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.mail_delete.class,roles.user_delete.class},
			translated="delete")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void delete(
		es.caib.seycon.ng.comu.LlistaCorreu llistaCorreu)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Operation ( grantees={roles.mail_update.class,roles.mail_delete.class},
			translated="update")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.LlistaCorreu update(
		es.caib.seycon.ng.comu.LlistaCorreu llistaCorreu)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.mail_query.class,roles.user_update.class},
	translated="findUserMailListByListNameAndDomainName")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.LlistaCorreuUsuari> findLlistaCorreuUsuariByNomLlistaCorreuAndCodiDomini(
		java.lang.String nomLlistaCorreu, 
		@Nullable java.lang.String codiDomini)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.mail_create.class,roles.user_create.class,
			roles.user_delete.class,roles.user_update.class,
			roles.mail_update.class},
			translated="create")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.LlistaCorreuUsuari create(
		es.caib.seycon.ng.comu.LlistaCorreuUsuari llistaCorreuUsuari)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.mail_delete.class,roles.user_delete.class,
			roles.mail_update.class},
			translated="deleteUserMailList")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void delete(
		es.caib.seycon.ng.comu.LlistaCorreuUsuari llistaCorreuUsuari)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Operation ( grantees={roles.mail_create.class,roles.mail_update.class},
			translated="create")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.CorreuExtern create(
		es.caib.seycon.ng.comu.CorreuExtern correuExtern)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.mail_delete.class,roles.mail_update.class},
			translated="delete")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void delete(
		es.caib.seycon.ng.comu.CorreuExtern correuExtern)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Operation ( grantees={roles.mail_query.class,roles.user_create.class,
			roles.user_delete.class,roles.user_update.class},
			translated="findUserMailListByListNameAndDomainNameAndUserName")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.LlistaCorreuUsuari findLlistaCorreuUsuariByNomLlistaCorreuAndCodiDominiAndCodiUsuari(
		java.lang.String nomLlistaCorreu, 
		@Nullable java.lang.String codiDomini, 
		java.lang.String codiUsuari)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}

	@Operation ( grantees={roles.mail_query.class,roles.user_delete.class},
			translated="findUserMailListByUserName")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.LlistaCorreuUsuari> findLlistaCorreuUsuariByCodiUsuari(
		java.lang.String codiUsuari)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}

	@Operation ( grantees={roles.mail_query.class,roles.user_delete.class},
			translated="findUserMailListHistoryByUserName")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.LlistaCorreuUsuari> findLlistaCorreuUsuariHistoricByCodiUsuari(
		java.lang.String codiUsuari)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}

	@Operation ( grantees={roles.mail_create.class},
			translated="create")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.DominiCorreu create(
		es.caib.seycon.ng.comu.DominiCorreu dominiCorreu)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.mail_update.class},
			translated="update")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.DominiCorreu update(
		es.caib.seycon.ng.comu.DominiCorreu dominiCorreu)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.mail_delete.class},
			translated="delete")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void delete(
		es.caib.seycon.ng.comu.DominiCorreu dominiCorreu)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Operation ( grantees={roles.mail_query.class},
			translated="getDomainMails")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.DominiCorreu> getDominiCorreus()
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.mail_query.class},
			translated="findMailDomainByName")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.DominiCorreu findDominiCorreuByCodi(
		java.lang.String codi)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.mail_query.class},
			translated="finExternalMailByEmail")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.CorreuExtern findCorreuExternByAdreca(
		java.lang.String adreca)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.mail_delete.class,roles.mail_update.class},
			translated="delete")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void delete(
		es.caib.seycon.ng.comu.RelacioLlistaCorreu relacioLlistaCorreu)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	
	@Operation ( grantees={roles.mail_create.class,roles.mail_update.class},
			translated="create")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.RelacioLlistaCorreu create(
		es.caib.seycon.ng.comu.RelacioLlistaCorreu relacioLlistaCorreu)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	
	@Operation ( grantees={roles.mail_create.class,roles.mail_update.class})
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void subscribeGroup ( String mailListName, String mailListDomain, String groupName)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}

	@Operation ( grantees={roles.mail_create.class,roles.mail_update.class})
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void unsubscribeGroup ( String mailListName, String mailListDomain, String groupName)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}

	@Operation ( grantees={roles.mail_create.class,roles.mail_update.class})
	@Transactional(rollbackFor={java.lang.Exception.class})
	public MailListRoleMember subscribeRole ( String mailListName, String mailListDomain, MailListRoleMember roleMember)
		throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}
	
	@Operation ( grantees={roles.mail_create.class,roles.mail_update.class})
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void unsubscribeRole ( String mailListName, String mailListDomain, MailListRoleMember roleMember)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	
	@Operation ( grantees={roles.mail_query.class},
			translated="findRelationsMailListByNameAndBelongsMailListNameAndNameAndContainsMailListName")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.RelacioLlistaCorreu findRelacioLlistaCorreuByNomAndCodiLlistaCorreuPertanyAndNomAndCodiLlistaCorreuConte(
		java.lang.String nomPertany, 
		@Nullable java.lang.String dominiCorreuPertany, 
		java.lang.String nomConte, 
		@Nullable java.lang.String dominiCorreuConte)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.mail_query.class,roles.user_update.class},
			translated="findRelationsMailListByNameBelongsMailListAndDomainName")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.RelacioLlistaCorreu> findRelacionsLlistaCorreuByNomLlistaCorreuPertanyAndCodiDomini(
		java.lang.String nomLlistaCorreuPertany, 
		@Nullable java.lang.String codiDomini)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.mail_query.class,roles.user_update.class},
			translated="findRelationsMailListByNameContainsMailListAndDomainName")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.RelacioLlistaCorreu> findRelacionsLlistaCorreuByNomLlistaCorreuConteAndCodiDomini(
		java.lang.String nomLlistaCorreuConte, 
		@Nullable java.lang.String codiDomini)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.mail_query.class,roles.user_create.class,roles.user_update.class},
			translated="findMailDomainsByFilter")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.DominiCorreu> findDominisCorreuByFiltre(
		@Nullable java.lang.String codi, 
		@Nullable java.lang.String descripcio, 
		@Nullable java.lang.String obsolet)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.mail_query.class,roles.user_create.class,roles.user_update.class},
			translated="findMailListByNameAndDomainName")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.LlistaCorreu findLlistaCorreuByNomAndCodiDomini(
		java.lang.String nomLlistaCorreu, 
		@Nullable java.lang.String codiDomini)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation (translated="checkEmptyMailList")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void netejaLlistaCorreuBuida(
		java.lang.String nomLlistaCorreu, 
		@Nullable java.lang.String codiDomini)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Operation ( grantees={roles.mail_delete.class},
			translated="deleteAtomic")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void deleteAtomic(
		es.caib.seycon.ng.comu.LlistaCorreuUsuari llistaCorreuUsuari)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Operation ( grantees={roles.mail_delete.class},
			translated="deleteAtomic")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void deleteAtomic(
		es.caib.seycon.ng.comu.CorreuExtern correuExtern)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Operation ( grantees={roles.mail_delete.class},
			translated="deleteAtomic")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void deleteAtomic(
		es.caib.seycon.ng.comu.RelacioLlistaCorreu relacioLlistaCorreu)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	
	
	@Operation(grantees = { roles.mail_query.class })
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public java.util.Collection<es.caib.seycon.ng.comu.LlistaCorreu> findMailListByJsonQuery(@Nullable String query)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(grantees = { roles.mail_query.class })
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public AsyncList<es.caib.seycon.ng.comu.LlistaCorreu> findMailListByJsonQueryAsync(@Nullable String query)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(grantees = { roles.mail_query.class })
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public PagedResult<DominiCorreu> findMailDomainsByJsonQuery(@Nullable String query, 
			@Nullable Integer first,
			@Nullable Integer max)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(grantees = { roles.mail_query.class })
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public AsyncList<es.caib.seycon.ng.comu.DominiCorreu> findMailDomainsByJsonQueryAsync(@Nullable String query)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(grantees = { roles.mail_query.class })
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public PagedResult<DominiCorreu> findMailDomainsByTextAndFilter(
			@Nullable String text, 
			@Nullable String query, 
			@Nullable Integer first,
			@Nullable Integer max)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(grantees = { roles.mail_query.class })
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public AsyncList<DominiCorreu> findMailDomainsByTextAndFilterAsync(
			@Nullable String text,
			@Nullable String query)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(grantees = { roles.mail_query.class })
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public PagedResult<LlistaCorreu> findMailListByTextAndFilter(
			@Nullable String text, 
			@Nullable String query, 
			@Nullable Integer first,
			@Nullable Integer max)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(grantees = { roles.mail_query.class })
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public AsyncList<LlistaCorreu> findMailListByTextAndFilterAsync(
			@Nullable String text,
			@Nullable String query)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}
}
