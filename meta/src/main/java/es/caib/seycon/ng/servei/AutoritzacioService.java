//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.servei;
import java.util.Map;

import com.soffid.mda.annotation.*;

import es.caib.seycon.ng.model.RolAssociacioRolEntity;
import es.caib.seycon.ng.model.RolEntity;
import es.caib.seycon.ng.model.UsuariEntity;
import es.caib.seycon.ng.model.UsuariImpressoraEntity;

import org.springframework.transaction.annotation.Transactional;

@Service (translatedName="AuthorizationService",
	translatedPackage="com.soffid.iam.service")
@Depends ({es.caib.seycon.ng.model.AutoritzacioRolEntity.class,
	es.caib.seycon.ng.servei.UsuariService.class,
	es.caib.seycon.ng.model.RolAccountEntity.class,
	RolEntity.class,
	es.caib.seycon.ng.model.GrupEntity.class,
	es.caib.seycon.ng.model.AuditoriaEntity.class,
	es.caib.seycon.ng.servei.AplicacioService.class,
	es.caib.seycon.ng.servei.AccountService.class,
	es.caib.seycon.ng.servei.PasswordService.class,
	es.caib.seycon.ng.servei.SessionCacheService.class,
	XarxaService.class,
	
	UsuariEntity.class,
	UsuariImpressoraEntity.class})
public abstract class AutoritzacioService {

	@Operation ( grantees={roles.Tothom.class},
			translated="getUserAuthorization")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.AutoritzacioRol> getUserAuthorization(
		java.lang.String codiAutoritzacio)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.Tothom.class},
			translated="getUserAuthorization")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.AutoritzacioRol> getUserAuthorization(
		java.lang.String codiAutoritzacio, 
		java.lang.String codiUsuari)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.authorization_rol_create.class},
			translated="create")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.AutoritzacioRol create(
		es.caib.seycon.ng.comu.AutoritzacioRol autoritzacio)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.authorization_rol_delete.class},
			translated="delete")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void delete(
		es.caib.seycon.ng.comu.AutoritzacioRol autoritzacio)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Operation ( grantees={roles.Tothom.class,roles.authorization_query.class},
			translated="getAuthorizationRoles")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.AutoritzacioRol> getRolsAutoritzacio(
		java.lang.String autoritzacio)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	
	@Operation(translated="getUserAuthorizationString")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.lang.String[] getUserAuthorizationString(
		java.lang.String codiAutoritzacio)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}

	@Description("Gets the autohrization given some login process properties")
	@Operation
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.lang.String[] getUserAuthorizationsString(
		String user, Map<String,String> loginProperties)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}

	@Operation(translated="getUserAuthorizationString")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.lang.String[] getUserAuthorizationString(
		java.lang.String codiAutoritzacio, 
		java.lang.String codiUsuari)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation(translated="getUserAuthorizations")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.AutoritzacioRol> getUserAuthorizations()
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation(translated="getUserAuthorizations")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection getUserAuthorizations(
		java.lang.String codiUsuari)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation(translated="getUserAuthorizationsString")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.lang.String[] getUserAuthorizationsString()
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation(translated="getUserAuthorizationsString")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.lang.String[] getUserAuthorizationsString(
		java.lang.String codiUsuari)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.Tothom.class},
			translated="getDescriptionUserAuthorizations")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.AutoritzacioRol> getDescriptionUserAuthorizations()
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.Tothom.class},
			translated="getDescriptionUserAuthorizations")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.AutoritzacioRol> getDescriptionUserAuthorizations(
		java.lang.String codiUsuari)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation (translated="getAuthorizationInfo")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<java.lang.Object> getInformacioAutoritzacio(
		java.lang.String autoritzacio)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.authorization_query.class},
			translated="findAuthorizations")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection findAuthorizations(
		@Nullable java.lang.String ambit, 
		@Nullable java.lang.String descripcio, 
		@Nullable java.lang.String codi)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.authorization_query.class},
			translated="getScopeList")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.List getScopeList()
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	
	
	@Description("Returns true if the user has the selected permission on the selected objects")
	@Operation
	public boolean hasPermission (String action, Object object)
	{
		return true;
	}
}
