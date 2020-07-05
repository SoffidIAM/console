//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.servei;
import org.springframework.transaction.annotation.Transactional;

import com.soffid.iam.api.Tenant;
import com.soffid.iam.service.TenantService;
import com.soffid.iam.service.CrudRegistryService;
import com.soffid.mda.annotation.Depends;
import com.soffid.mda.annotation.Operation;
import com.soffid.mda.annotation.Service;

import es.caib.seycon.ng.model.TipusDadaEntity;

@Service (translatedName="ApplicationBootService",
	translatedPackage="com.soffid.iam.service")
@Depends ({es.caib.seycon.ng.servei.AccountService.class,
	es.caib.seycon.ng.servei.PuntEntradaService.class,
	es.caib.seycon.ng.servei.DadesAddicionalsService.class,
	es.caib.seycon.ng.servei.XarxaService.class,
	es.caib.seycon.ng.servei.UsuariService.class,
	es.caib.seycon.ng.servei.AutoritzacioService.class,
	es.caib.seycon.ng.servei.GrupService.class,
	es.caib.seycon.ng.servei.DominiUsuariService.class,
	es.caib.seycon.ng.servei.InternalPasswordService.class,
	es.caib.seycon.ng.servei.DispatcherService.class,
	es.caib.seycon.ng.servei.AplicacioService.class,
	es.caib.seycon.ng.servei.ConfiguracioService.class,
	es.caib.bpm.servei.BpmConfigService.class,
	es.caib.bpm.servei.BpmEngine.class,
	es.caib.seycon.ng.servei.SoDRuleService.class,
	ServerPluginService.class,
	TenantService.class,
	TipusDadaEntity.class,
	com.soffid.iam.service.ScheduledTaskService.class,
	CrudRegistryService.class})
public abstract class ApplicationBootService {

	@Operation(translated="syncServerBoot")
	@Transactional(propagation=org.springframework.transaction.annotation.Propagation.REQUIRED ,
		rollbackForClassName={"java.lang.Exception"})
	public void syncServerBoot()
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	
	@Transactional(propagation=org.springframework.transaction.annotation.Propagation.REQUIRED ,
			rollbackForClassName={"java.lang.Exception"})
	public void tenantBoot(Tenant tenant)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	

	@Operation(translated="consoleBoot")
	@Transactional(propagation=org.springframework.transaction.annotation.Propagation.REQUIRED ,
		rollbackForClassName={"java.lang.Exception"})
	public void consoleBoot()
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
}
