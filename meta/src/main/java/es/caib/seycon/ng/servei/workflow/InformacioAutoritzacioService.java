//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.servei.workflow;
import com.soffid.mda.annotation.*;

import es.caib.seycon.ng.servei.AutoritzacioService;

import org.springframework.transaction.annotation.Transactional;

@Service (translatedName="AuthorizationInformationService",
	translatedPackage="com.soffid.iam.service.workflow")
@Depends ({es.caib.seycon.ng.model.RolAccountEntity.class,
	es.caib.seycon.ng.servei.UsuariService.class,
	AutoritzacioService.class,
	es.caib.seycon.ng.servei.AplicacioService.class})
public abstract class InformacioAutoritzacioService {

	@Operation (translated="getApplications")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.Aplicacio> getAplicacions()
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation (translated="findRolesByApplicationCode")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.Rol> findRolsByCodiAplicacio(
		java.lang.String codiAplicacio)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation (translated="findRolesByUserCode")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.Rol> findRolsByCodiUsuari(
		java.lang.String codiUsuari)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation (translated="findApplicationManagersByApplicationCode")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.Usuari> findAdministradorsAplicacioByCodiAplicacio(
		java.lang.String codiAplicacio)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation (translated="findUserByUserData")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.Usuari> findUsuariByDadesUsuari(
		@Nullable java.lang.String dni, 
		@Nullable String nom, 
		@Nullable String primerLlinatge, 
		@Nullable String segonLlinatge)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation (translated="isApplicationManager")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.lang.Boolean isAdministradorAplicacio(
		java.lang.String codiUsuari, 
		java.lang.String codiApliacio)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation (translated="getApplicationRolesByUserCodeAndApplicationCode")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.Rol> getRolsAplicacioByCodiUsuariAndCodiAplicacio(
		java.lang.String codiUsuari, 
		java.lang.String codiAplicacio)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation (translated="getRolesByUserCode")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.Rol> getRolsByCodiUsuari(
		java.lang.String codiUsuari)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation (translated="getApplicationsByUserCode")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.Aplicacio> getAplicacionsByCodiUsuari(
		java.lang.String codiUsuari)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation (translated="interventionNeeded")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public boolean necessitaIntervencioSistemes(
		@Nullable java.lang.String codiAplicacio, 
		@Nullable java.lang.String[] codisRols)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return false;
	}
	@Operation (translated="getSystemsRoles")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.Rol getRolSistemes(
		java.lang.String codiAplicacio)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation (translated="findManagedApplicationsByUserCode")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.Aplicacio> findAplicacionsAdministradesByCodiUsuari(
		java.lang.String codiUsuari)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation (translated="findApplicationByCriteria")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.Aplicacio> findAplicacioByCriteri(
		@Nullable java.lang.String codi, 
		@Nullable java.lang.String nom, 
		@Nullable java.lang.String directoriFonts, 
		@Nullable java.lang.String responsable, 
		@Nullable java.lang.String directoriExecutable, 
		@Nullable java.lang.String bd)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation (translated="findApplicationByApplicationCode")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.Aplicacio findAplicacioByCodiAplicacio(
		java.lang.String codiAplicacio)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation (translated="findRolesByApplicationCodeUnrestricted")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.Rol> findRolsByCodiAplicacioSenseRestriccions(
		java.lang.String codiAplicacio)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
}
