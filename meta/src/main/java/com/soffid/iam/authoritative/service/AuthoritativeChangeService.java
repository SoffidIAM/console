package com.soffid.iam.authoritative.service;

import com.soffid.iam.authoritative.model.AuthoritativeChangeEntity;
import com.soffid.iam.service.CustomObjectService;
import com.soffid.mda.annotation.Depends;
import com.soffid.mda.annotation.Description;
import com.soffid.mda.annotation.Nullable;
import com.soffid.mda.annotation.Operation;
import com.soffid.mda.annotation.Service;

import es.caib.bpm.servei.BpmConfigService;
import es.caib.bpm.servei.BpmEngine;
import es.caib.seycon.ng.model.AuditoriaEntity;
import es.caib.seycon.ng.model.DispatcherEntity;
import es.caib.seycon.ng.model.GrupEntity;
import es.caib.seycon.ng.model.UsuariEntity;
import es.caib.seycon.ng.servei.DadesAddicionalsService;
import es.caib.seycon.ng.servei.GrupService;
import es.caib.seycon.ng.servei.UsuariService;
import es.caib.seycon.ng.sync.intf.AuthoritativeChange;
@Depends ( {
	BpmEngine.class,
	BpmConfigService.class,
	DispatcherEntity.class,
	AuthoritativeChangeEntity.class,
	UsuariEntity.class,
	GrupService.class,
	AuditoriaEntity.class,
	UsuariService.class,
	DadesAddicionalsService.class,
	CustomObjectService.class,
	GrupEntity.class
})
@Service(internal=true) 
public class AuthoritativeChangeService {
	@Operation
	@Description ("Performs authoritative change")
	public void finishAuthoritativeChange (AuthoritativeChange change) {
		
	}
	
	@Operation
	@Description ("Cancels an authoritative change")
	public void cancelAuthoritativeChange (AuthoritativeChange change) {
		
	}
	@Operation
	@Description ("Notifies a new authoritative change has just arrived.\nResturns true if the authoritative change has been performed")
	public boolean startAuthoritativeChange (AuthoritativeChange change)
	{
		return true;
	}

}
