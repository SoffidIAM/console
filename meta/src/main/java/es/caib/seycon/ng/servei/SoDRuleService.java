//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.servei;
import org.springframework.transaction.annotation.Transactional;

import com.soffid.iam.api.AsyncList;
import com.soffid.iam.service.AsyncRunnerService;
import com.soffid.mda.annotation.Depends;
import com.soffid.mda.annotation.Nullable;
import com.soffid.mda.annotation.Operation;
import com.soffid.mda.annotation.Service;

import es.caib.seycon.ng.comu.SoDRule;

@Service ( translatedName="SoDRuleService",
	 translatedPackage="com.soffid.iam.service")
@Depends ({es.caib.seycon.ng.model.SoDRuleEntity.class,
	es.caib.seycon.ng.model.SoDRoleEntity.class,
	es.caib.seycon.ng.model.AplicacioEntity.class,
	es.caib.seycon.ng.model.RolEntity.class,
	es.caib.seycon.ng.servei.AplicacioService.class,
	es.caib.seycon.ng.model.UsuariEntity.class,
	es.caib.seycon.ng.servei.ConfiguracioService.class,
	AsyncRunnerService.class})
public abstract class SoDRuleService {

	@Operation ( grantees={roles.sod_query.class,
			roles.application_query.class},
			translated="findRuleByApplication")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.SoDRule> findRuleByApplication(
		java.lang.Long applicationId)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	
	
	@Operation ( grantees={roles.sod_query.class,
			roles.application_query.class},
			translated="findRolesByRule")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.SoDRole> findRolesByRule(
		java.lang.Long ruleId)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	
	
	@Operation ( grantees={roles.sod_create.class},
			translated="create")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.SoDRule create(
		es.caib.seycon.ng.comu.SoDRule rule)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	
	
	@Operation ( grantees={roles.sod_update.class},
			translated="update")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.SoDRule update(
		es.caib.seycon.ng.comu.SoDRule rule)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	
	
	@Operation ( grantees={roles.sod_delete.class},
			translated="remove")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void remove(
		es.caib.seycon.ng.comu.SoDRule rule)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	
	
	@Operation ( grantees={roles.sod_update.class},
			translated="remove")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void remove(
		es.caib.seycon.ng.comu.SoDRole role)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	
	
	@Operation ( grantees={roles.sod_update.class},
			translated="create")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.SoDRole create(
		es.caib.seycon.ng.comu.SoDRole role)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	
	
	@Operation ( grantees={roles.sod_query.class},
			translated="isAllowed")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.SoDRule isAllowed(
		es.caib.seycon.ng.comu.RolAccount ra)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	
	
	@Operation ( grantees={roles.user_role_query.class},
			translated="qualifyRolAccountList")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void qualifyRolAccountList(
		java.util.List<es.caib.seycon.ng.comu.RolAccount> ra)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	
	
	@Operation ( grantees={roles.user_role_query.class,
			roles.application_query.class},
			translated="findAffectingRulesByRolAccount")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.SoDRule> findAffectingRulesByRolAccount(
		es.caib.seycon.ng.comu.RolAccount ra)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	
	
	@Operation ( grantees={roles.user_role_query.class,
			roles.application_query.class},
			translated="getRuleById")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.SoDRule getRuleById(
		java.lang.Long ruleId)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	
	
	@Operation(translated="internalRemovingRole")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void internalRemovingRole(
		java.lang.Long roleId)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	
	@Operation(grantees = { roles.sod_query.class })
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public java.util.List<SoDRule> findSodRuleByJsonQuery(
			@Nullable String query,
			@Nullable Integer first,
			@Nullable Integer pageSize)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(grantees = { roles.sod_query.class })
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public AsyncList<SoDRule> findSodRuleByJsonQueryAsync(
			@Nullable String query)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}


}
