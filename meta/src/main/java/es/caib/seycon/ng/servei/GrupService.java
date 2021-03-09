//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.servei;
import java.util.Collection;

import org.springframework.transaction.annotation.Transactional;

import com.soffid.iam.api.AsyncList;
import com.soffid.iam.api.PagedResult;
import com.soffid.iam.model.GroupAttributeEntity;
import com.soffid.iam.model.UserGroupAttributeEntity;
import com.soffid.iam.service.AsyncRunnerService;
import com.soffid.iam.service.impl.AttributeValidationService;
import com.soffid.mda.annotation.Depends;
import com.soffid.mda.annotation.Nullable;
import com.soffid.mda.annotation.Operation;
import com.soffid.mda.annotation.Service;

import es.caib.seycon.ng.comu.Grup;
import es.caib.seycon.ng.model.TipusDadaEntity;

@Service (translatedName="GroupService",
	translatedPackage="com.soffid.iam.service",
	serverPath="/seycon/GrupService",
	serverRole="agent")
@Depends ({
	/********** Entities *************/
	es.caib.seycon.ng.model.RolsGrupEntity.class,
	es.caib.seycon.ng.model.UsuariEntity.class,
	es.caib.seycon.ng.model.RolEntity.class,
	es.caib.seycon.ng.model.MaquinaEntity.class,
	es.caib.seycon.ng.model.UsuariGrupEntity.class,
	es.caib.seycon.ng.model.RolAccountEntity.class,
	es.caib.seycon.ng.model.TasqueEntity.class,
	es.caib.seycon.ng.model.ConfiguracioEntity.class,
	es.caib.seycon.ng.model.GrupEntity.class,
	UserGroupAttributeEntity.class,
	/************** Services ***************/
	AutoritzacioService.class,
	com.soffid.iam.service.RuleEvaluatorService.class,
	es.caib.seycon.ng.servei.AplicacioService.class,
	AsyncRunnerService.class,
	GroupAttributeEntity.class,
	AttributeValidationService.class,
	TipusDadaEntity.class,
	UsuariService.class
})
public abstract class GrupService {

	@Operation ( grantees={roles.group_create.class},
			translated="create")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.Grup create(
		es.caib.seycon.ng.comu.Grup grup)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation (translated="getGroups")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.Grup> getGrups()
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.group_query.class},
			translated="findGroupByGroupName")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.Grup findGrupByCodiGrup(
		java.lang.String codi)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.group_query.class,roles.lopd_query.class},
			translated="findSubgroupsByGroupName")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.Grup> findSubGrupsByCodiGrup(
		java.lang.String codi)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation (translated="removeGroupFormUser", grantees={roles.user_group_delete.class})
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void removeGrupFromUsuari(
		java.lang.String codiUsuari, 
		java.lang.String codiGrup)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Operation (translated="findGroupsByGroupsType")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.Grup> findGrupsByTipusGrup(
		java.lang.String tipus)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation (translated="setSuperGroup")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void setSuperGrup(
		java.lang.String codiSubGrup, 
		java.lang.String codiSuperGrup)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Operation (translated="getOfficeServer")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.Maquina getServidorOfimatic(
		@Nullable es.caib.seycon.ng.comu.Grup grup)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation(translated="delete", grantees=roles.group_delete.class)
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void delete(
		es.caib.seycon.ng.comu.Grup grup)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Operation ( grantees={roles.group_update.class},
			translated="update")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.Grup update(
		es.caib.seycon.ng.comu.Grup grup)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation (translated="findGroupsByFilter")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.Grup> findGrupsByFiltre(
		@Nullable java.lang.String codi, 
		@Nullable java.lang.String pare, 
		@Nullable java.lang.String unitatOfimatica, 
		@Nullable java.lang.String descripcio, 
		@Nullable java.lang.String tipus, 
		@Nullable java.lang.String obsolet)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation (translated="findGroupsByUserName")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.Grup> findGrupsByCodiUsuari(
		java.lang.String codiUsuari)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation (translated="addGroupToUser", grantees={roles.user_group_create.class})
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void addGrupToUsuari(
		java.lang.String codiUsuari, 
		java.lang.String codiGrup)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Operation (translated="findPrimaryGroupByUserName")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.Grup findGrupPrimariByCodiUsuari(
		java.lang.String codiUsuari)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation (translated="findGroupsFromUsersByUserName")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.Grup> findGrupsFromUsuarisByCodiUsuari(
		java.lang.String codiUsuari)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation (translated="findGroupsFromRolesByUserName")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.Grup> findGrupsFromRolsByCodiUsuari(
		java.lang.String codiUsuari)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.user_group_create.class},
			translated="create")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.UsuariGrup create(
		es.caib.seycon.ng.comu.UsuariGrup usuariGrup)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.user_group_create.class},
			translated="update")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.UsuariGrup update(
		es.caib.seycon.ng.comu.UsuariGrup usuariGrup)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.user_group_delete.class},
			translated="delete")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void delete(
		es.caib.seycon.ng.comu.UsuariGrup usuariGrup)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Operation (translated="findUserGroupByUserNameAndGroupName")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.UsuariGrup findUsuariGrupByCodiUsuariAndCodiGrup(
		java.lang.String codiUsuari, 
		java.lang.String codiGrup)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.lopd_query.class},
			translated="getSuperGroup")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.Grup getSuperGrup(
		java.lang.String codiGrup)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.user_query.class},
			translated="findUsersGroupByUserName")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.UsuariGrup> findUsuariGrupsByCodiUsuari(
		java.lang.String codiUsuari)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.user_query.class})
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.UsuariGrup> findUserGroupHistoryByUserName(
		java.lang.String userName)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation (translated="findUsersRolesWithGroupByUserName")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.RolAccount> findRolsUsuarisAmbGrupByCodiUsuari(
		java.lang.String codiUsuari)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.lopd_query.class})
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.Grup> getConselleriesAmbDireccionsGenerals()
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.group_query.class},
			translated="getParentList")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.Grup> getLlistaDePares(
		java.lang.String codiGrup)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.group_query.class},
			translated="findGroupById")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.Grup findGrupById(
		java.lang.Long grupId)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.group_query.class},
			translated="getRolesFromGroup")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.Rol> getRolsFromGrup(
		es.caib.seycon.ng.comu.Grup grup)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.group_user_query.class},
			translated="findUsersBelongtoGroupByGroupName")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.UsuariGrup> findUsuarisPertanyenAlGrupByCodiGrup(
		java.lang.String codiGrup)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}

	@Operation ( grantees={roles.group_user_query.class},
			translated="findUsersBelongtoGroupByGroupNameAsync")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public AsyncList<es.caib.seycon.ng.comu.UsuariGrup> findUsuarisPertanyenAlGrupByCodiGrupAsync(
		java.lang.String codiGrup)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}

	@Operation (translated="getRolesFromGroup")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.RolsGrup> getRolsFromGrup(
		java.lang.String codiGrup)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.group_role_query.class},
			translated="findUsersRolesDomainTypeAndUserGroups")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.RolAccount> findRolsUsuarisTipusDominiGrupsAndGrupsUsuari(
		java.lang.String codiGrup)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.group_query.class},
			translated="findGroupsByFilter")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.Grup> findGrupsByFiltre(
		@Nullable java.lang.String codi, 
		@Nullable java.lang.String pare, 
		@Nullable java.lang.String unitatOfimatica, 
		@Nullable java.lang.String descripcio, 
		@Nullable java.lang.String tipus, 
		@Nullable java.lang.String obsolet, 
		@Nullable java.lang.String servidorOfimatic, 
		@Nullable java.lang.String seccioPressupostaria)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.group_role_query.class},
			translated="getRolesFromGroupAndParentGroup")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.RolsGrup> getRolsFromGrupYParesGrup(
		es.caib.seycon.ng.comu.Grup grup)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation(translated="propagateRolsChangesToDispatcher")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void propagateRolsChangesToDispatcher(
		java.lang.String codiGrup)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}

	@Operation(grantees = { roles.group_query.class })
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public java.util.List<es.caib.seycon.ng.comu.Grup> findGroupByText(
			@Nullable String text) {
		return null;
	}

	@Operation(grantees = { roles.group_query.class })
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public AsyncList<es.caib.seycon.ng.comu.Grup> findGroupByTextAsync(
			@Nullable String text) {
		return null;
	}

	@Operation(grantees = { roles.group_query.class })
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public java.util.List<es.caib.seycon.ng.comu.Grup> findGroupByTextAndFilter(
			@Nullable String text,
			@Nullable String filter) {
		return null;
	}

	@Operation(grantees = { roles.group_query.class })
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public PagedResult<es.caib.seycon.ng.comu.Grup> findGroupByTextAndFilter(
			@Nullable String text,
			@Nullable String filter,
			@Nullable Integer first,
			@Nullable Integer pageSize) {
		return null;
	}

	@Operation(grantees = { roles.group_query.class })
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public AsyncList<es.caib.seycon.ng.comu.Grup> findGroupByTextAndFilterAsync(
			@Nullable String text,
			@Nullable String filter) {
		return null;
	}

	@Operation(grantees = { roles.group_query.class })
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public java.util.List<es.caib.seycon.ng.comu.Grup> findGroupByJsonQuery(@Nullable String query)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(grantees = { roles.group_query.class })
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public PagedResult<Grup> findGroupByJsonQuery(@Nullable String query,
			@Nullable Integer startIndex,
			@Nullable Integer count)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(grantees = { roles.group_query.class })
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public AsyncList<es.caib.seycon.ng.comu.Grup> findGroupByJsonQueryAsync(@Nullable String query)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Transactional(readOnly=true)
	public Collection<String>findGroupNames () { return null; }

	@Operation(grantees = { roles.user_query.class })
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public PagedResult<es.caib.seycon.ng.comu.UsuariGrup> findGroupUserByJsonQuery(
			@Nullable String query,
			@Nullable Integer startIndex,
			@Nullable Integer count)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}
}
