//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package com.soffid.iam.service;
import com.soffid.iam.api.ApplyRuleProcess;
import com.soffid.iam.api.Rule;
import com.soffid.iam.model.RuleEntity;
import com.soffid.mda.annotation.*;

import es.caib.seycon.ng.model.RolEntity;
import es.caib.seycon.ng.servei.UsuariService;

import java.io.File;

import org.springframework.transaction.annotation.Transactional;

@Service ( internal=true,
	 translatedName="RuleEvaluatorService",
	 translatedPackage="com.soffid.iam.service")
@Depends ({es.caib.seycon.ng.servei.AplicacioService.class,
	es.caib.seycon.ng.model.RolAccountEntity.class,
	com.soffid.iam.model.RuleAssignedRoleEntity.class,
	com.soffid.iam.model.RuleEntity.class,
	es.caib.seycon.ng.model.UsuariEntity.class,
	es.caib.seycon.ng.model.GrupEntity.class,
	es.caib.seycon.ng.servei.AccountService.class,
	es.caib.seycon.ng.model.DispatcherEntity.class,
	com.soffid.iam.service.SoffidEventListener.class,
	es.caib.seycon.ng.model.TasqueEntity.class,
	UsuariService.class,
	AsyncRunnerService.class,
	RolEntity.class})
public abstract class RuleEvaluatorService {

	@Transactional(rollbackFor={java.lang.Exception.class})
	public void apply(
		com.soffid.iam.model.RuleEntity rule, 
		es.caib.seycon.ng.model.UsuariEntity user)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void applyRules(
		es.caib.seycon.ng.model.UsuariEntity user)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}

	@Transactional(rollbackFor={java.lang.Exception.class})
	public void apply(
		com.soffid.iam.model.RuleEntity rule)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}

	@Transactional(rollbackFor={java.lang.Exception.class})
	public ApplyRuleProcess applyAsync(
		com.soffid.iam.model.RuleEntity rule)
		throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Transactional(readOnly=true,noRollbackFor={java.lang.Exception.class})
	public File dryRun(
		RuleEntity rule)
		throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Transactional(readOnly=false,noRollbackFor={java.lang.Exception.class})
	@Description("Query the rule process status")
	public ApplyRuleProcess queryProcessStatus(
		ApplyRuleProcess process)
		throws es.caib.seycon.ng.exception.InternalErrorException { return null; }

}
