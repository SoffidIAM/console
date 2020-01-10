package com.soffid.iam.service;

import java.util.Date;

import com.soffid.iam.api.Stats;
import com.soffid.iam.model.JumpServerGroupEntity;
import com.soffid.iam.model.StatsEntity;
import com.soffid.mda.annotation.Depends;
import com.soffid.mda.annotation.Operation;
import com.soffid.mda.annotation.Service;

import es.caib.seycon.ng.model.AccountEntity;
import es.caib.seycon.ng.model.AutoritzacioRolEntity;
import es.caib.seycon.ng.servei.AplicacioService;
import es.caib.seycon.ng.servei.AutoritzacioService;
import roles.Tothom;

@Service()
@Depends({StatsEntity.class, AccountEntity.class, JumpServerGroupEntity.class,
	AutoritzacioRolEntity.class, AutoritzacioService.class,
	AplicacioService.class, PamSessionService.class})
public class StatsService
{
	@Operation(grantees = {Tothom.class})
	Stats findStats (String name, Date since, Date until, int step) { return null; }
	
	void updateStats() {};
	
	void purge() {};
}
