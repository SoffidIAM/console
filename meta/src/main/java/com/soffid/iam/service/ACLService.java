package com.soffid.iam.service;

import com.soffid.iam.api.AccessControlList;
import com.soffid.mda.annotation.Depends;
import com.soffid.mda.annotation.Service;

import es.caib.seycon.ng.model.AccountEntity;
import es.caib.seycon.ng.model.GrupEntity;
import es.caib.seycon.ng.model.RolEntity;
import es.caib.seycon.ng.model.UsuariEntity;
import es.caib.seycon.ng.servei.AplicacioService;

@Service(internal=true)
@Depends ( {
	UsuariEntity.class, GrupEntity.class, RolEntity.class,
	AccountEntity.class,
	
	AplicacioService.class
})
public class ACLService extends Object {
	boolean isUserIncluded (long userId, AccessControlList acl) { return false; }
	
	boolean isAccountIncluded (long userId, AccessControlList acl) { return false; }

	AccessControlList expandUser (long userId) { return null;}

	AccessControlList expandACL (AccessControlList acl) { return null;}
}
