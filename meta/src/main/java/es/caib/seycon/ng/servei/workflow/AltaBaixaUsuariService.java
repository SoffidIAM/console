//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.servei.workflow;
import com.soffid.mda.annotation.*;

import org.springframework.transaction.annotation.Transactional;

@Service (translatedName="CreateDisableUserService",
		translatedPackage="com.soffid.iam.service.workflow")
@Depends ({es.caib.seycon.ng.model.DominiCorreuEntity.class,
	es.caib.seycon.ng.servei.XarxaService.class,
	es.caib.seycon.ng.servei.UsuariService.class,
	es.caib.seycon.ng.servei.GrupService.class,
	es.caib.seycon.ng.servei.AutoritzacioService.class,
	es.caib.seycon.ng.model.UsuariEntity.class,
	es.caib.seycon.ng.model.GrupEntity.class})
public abstract class AltaBaixaUsuariService {

	@Operation (translated="disableUser")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.Usuari baixaUsuari(
		java.lang.String codiUsuari)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation (translated="createUser")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.Usuari altaUsuari(
		byte[] peticio, 
		es.caib.signatura.api.Signature signatura)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation (translated="setServersToUser")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.Usuari setServidorsToUsuari(
		java.lang.String codiUsuari, 
		@Nullable java.lang.String servidorPerfilId, 
		@Nullable java.lang.String servidorCorreuId, 
		@Nullable java.lang.String servidorHomeId)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation (translated="getOUDependent")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.Grup> getUnitatsOrganitzativesDepenents(
		java.lang.String codiUnitatOrganitzativa)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation (translated="findUserByUserData")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.Usuari> findUsuariByDadesUsuari(
		@Nullable java.lang.String dni, 
		@Nullable java.lang.String nom, 
		@Nullable java.lang.String primerLlinatge, 
		@Nullable java.lang.String segonLlinatge)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={Roles.Tothom.class},
			translated="setInitialPasswordToUser")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.lang.String setPasswordInicialToUsuari(
		java.lang.String codiUsuari, 
		java.lang.String codiDominiContrasenyes)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation (translated="getContractTypesUserCreate")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<java.lang.String> getTipusContractesAltaUsuari()
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation (translated="FindUserByUserData")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.Usuari> findUsuariByDadesUsuari(
		@Nullable java.lang.String codiUsuari, 
		@Nullable java.lang.String dni, 
		@Nullable java.lang.String nom, 
		@Nullable java.lang.String primerLlinatge, 
		@Nullable java.lang.String segonLlinatge)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation (translated="findGroupsByUserCode")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.Grup> findGrupsByCodiUsuari(
		java.lang.String codiUsuari)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation (translated="existShortName")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.lang.Boolean existeixNomCurt(
		java.lang.String nomCurt)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation (translated="getSuperGroup")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.Grup getSuperGrup(
		java.lang.String codiSubGrup)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation (translated="findUserByShortName")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.Usuari findUsuariByNomCurt(
		java.lang.String nomCurt)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={Roles.Tothom.class})
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.Grup> getManagedGroups()
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation (translated="getAdministratorRoleByGroup")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.Rol getRolAdministradorByGrup(
		java.lang.String codiGrup)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation (translated="getUsersByNIF")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.Usuari> getUsuarisByNIFSenseRestriccions(
		java.lang.String nif)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation (translated="findGroupsByFilter")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.Grup> findGrupsByFiltreSenseRestriccions(
		java.lang.String codi, 
		java.lang.String pare, 
		java.lang.String unitatOfimatica, 
		java.lang.String descripcio, 
		java.lang.String tipus, 
		java.lang.String obsolet)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.Grup> getManagedGroups(
		java.lang.String codiUsuari)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
}
