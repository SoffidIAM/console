//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.servei;
import org.springframework.transaction.annotation.Transactional;

import com.soffid.mda.annotation.Depends;
import com.soffid.mda.annotation.Operation;
import com.soffid.mda.annotation.Service;

@Service (translatedName="UserDomainService", 
	translatedPackage="com.soffid.iam.service")
@Depends ({es.caib.seycon.ng.model.DominiContrasenyaEntity.class,
	es.caib.seycon.ng.servei.AuditoriaService.class,
	es.caib.seycon.ng.model.ContrasenyaEntity.class,
	es.caib.seycon.ng.model.UsuariEntity.class,
	es.caib.seycon.ng.model.DominiUsuariEntity.class,
	es.caib.seycon.ng.model.TipusUsuariEntity.class,
	es.caib.seycon.ng.model.PoliticaContrasenyaEntity.class,
	es.caib.seycon.ng.model.ParaulesProhibidesEntity.class,
	es.caib.seycon.ng.model.ParaulaProhibidaPoliticaContrasenyaEntity.class})
public abstract class DominiUsuariService {

	@Operation ( grantees={roles.usersDomain_create.class},
			translated="create")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.DominiUsuari create(
		es.caib.seycon.ng.comu.DominiUsuari dominiUsuari)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.usersDomain_update.class},
			translated="update")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.DominiUsuari update(
		es.caib.seycon.ng.comu.DominiUsuari dominiUsuari)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.usersDomain_delete.class},
			translated="delete")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void delete(
		es.caib.seycon.ng.comu.DominiUsuari dominiUsuari)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Operation ( grantees={roles.userType_create.class},
			translated="create")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.TipusUsuari create(
		es.caib.seycon.ng.comu.TipusUsuari tipusUsuari)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.userType_update.class},
			translated="update")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.TipusUsuari update(
		es.caib.seycon.ng.comu.TipusUsuari tipusUsuari)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.userType_delete.class},
			translated="delete")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void delete(
		es.caib.seycon.ng.comu.TipusUsuari tipusUsuari)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Operation ( grantees={roles.usersDomain_query.class},
			translated="findAllUserDomain")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.DominiUsuari> findAllDominiUsuari()
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.userType_query.class, roles.sso_manage.class},
			translated="findAllUserType")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.TipusUsuari> findAllTipusUsuari()
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.passwordDomain_query.class},
			translated="findAllPasswordPolicyDomain")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.PoliticaContrasenya> findAllPolitiquesContrasenyaDomini(
		java.lang.String codiDominiContrasenya)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.passwordDomain_query.class},
			translated="findAllForbiddenWords")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.ParaulaProhibida> findAllParaulesProhibides()
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.passwordDomain_create.class},
			translated="create")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.PoliticaContrasenya create(
		es.caib.seycon.ng.comu.PoliticaContrasenya politicaContrasenyaDomini)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.passwordDomain_update.class},
			translated="update")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.PoliticaContrasenya update(
		es.caib.seycon.ng.comu.PoliticaContrasenya politicaContrasenyaDomini)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.passwordDomain_delete.class},
			translated="delete")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void delete(
		es.caib.seycon.ng.comu.PoliticaContrasenya politicaContrasenyaDomini)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Operation ( grantees={roles.passwordDomain_create.class},
			translated="create")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.ParaulaProhibida create(
		es.caib.seycon.ng.comu.ParaulaProhibida paraulaProhibida)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.passwordDomain_update.class},
			translated="update")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.ParaulaProhibida update(
		es.caib.seycon.ng.comu.ParaulaProhibida paraulaProhibida)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.passwordDomain_delete.class},
			translated="delete")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void delete(
		es.caib.seycon.ng.comu.ParaulaProhibida paraulaProhibida)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Operation ( grantees={roles.passwordDomain_create.class},
			translated="create")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.ParaulaProhibidaPoliticaContrasenya create(
		es.caib.seycon.ng.comu.ParaulaProhibidaPoliticaContrasenya paraulaProhibidaContrasenya)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.passwordDomain_update.class},
			translated="update")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.ParaulaProhibidaPoliticaContrasenya update(
		es.caib.seycon.ng.comu.ParaulaProhibidaPoliticaContrasenya paraulaProhibidaContrasenya)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.passwordDomain_delete.class},
			translated="delete")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void delete(
		es.caib.seycon.ng.comu.ParaulaProhibidaPoliticaContrasenya paraulaProhibidaContrasenya)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Operation ( grantees={roles.passwordDomain_create.class},
			translated="create")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.DominiContrasenya create(
		es.caib.seycon.ng.comu.DominiContrasenya dominiContrasenya)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.passwordDomain_update.class},
			translated="update")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.DominiContrasenya update(
		es.caib.seycon.ng.comu.DominiContrasenya dominiContrasenya)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.passwordDomain_delete.class},
			translated="delete")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void delete(
		es.caib.seycon.ng.comu.DominiContrasenya dominiContrasenya)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Operation ( grantees={roles.usersDomain_query.class},
			translated="findUserDomainByName")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.DominiUsuari findDominiUsuariByCodi(
		java.lang.String codiDominiUsuari)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.usersDomain_query.class},
			translated="findForbiddenWordsPasswordPolicy")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.ParaulaProhibidaPoliticaContrasenya> findParaulesProhibidesPoliticaContrasenya(
		es.caib.seycon.ng.comu.PoliticaContrasenya politicaContrasenya)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.usersDomain_query.class},
			translated="findPasswordDomainByName")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.DominiContrasenya findDominiContrasenyaByCodi(
		java.lang.String codi)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.usersDomain_query.class},
			translated="findPolicyByTypeAndPasswordDomain")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.PoliticaContrasenya findPoliticaByTipusAndDominiContrasenyas(
		java.lang.String tipus, 
		java.lang.String domini)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.usersDomain_query.class},
			translated="findAllPasswordDomain")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.DominiContrasenya> findAllDominiContrasenya()
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.usersDomain_query.class},
			translated="findNameGenerators")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<java.lang.String> findNameGenerators()
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
}
