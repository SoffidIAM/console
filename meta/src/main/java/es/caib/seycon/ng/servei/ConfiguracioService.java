//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.servei;
import com.soffid.mda.annotation.*;

import org.springframework.transaction.annotation.Transactional;

@Service (translatedName="ConfigurationService",
	translatedPackage="com.soffid.iam.service")
@Depends ({es.caib.seycon.ng.model.ConfiguracioEntity.class,
	es.caib.seycon.ng.model.BlobConfigurationEntity.class})
public abstract class ConfiguracioService {

	@Operation ( grantees={Roles.parameter_query.class},
			translated="findParameterByNameAndNetworkName")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.Configuracio findParametreByCodiAndCodiXarxa(
		@Nullable java.lang.String codiParametre, 
		@Nullable java.lang.String codiXarxa)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={Roles.parameter_query.class},
			translated="getParameters")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.Configuracio> getParametres()
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={Roles.parameter_delete.class},
			translated="delete")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void delete(
		es.caib.seycon.ng.comu.Configuracio configuracio)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Operation ( grantees={Roles.parameter_create.class},
			translated="create")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.Configuracio create(
		es.caib.seycon.ng.comu.Configuracio configuracio)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={Roles.parameter_query.class},
			translated="findConfigurationByFilter")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.Configuracio> findConfiguracioByFiltre(
		@Nullable java.lang.String codi, 
		@Nullable java.lang.String valor, 
		@Nullable java.lang.String descripcio, 
		@Nullable java.lang.String codiXarxa)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={Roles.parameter_update.class},
			translated="update")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.Configuracio update(
		es.caib.seycon.ng.comu.Configuracio configuracio)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation(translated="updateBlob")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void updateBlob(
		java.lang.String name, 
		byte[] data)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Operation(translated="getBlob")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public byte[] getBlob(
		java.lang.String name)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation(translated="deleteBlob")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void deleteBlob(
		java.lang.String name)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Operation(translated="updateBlob")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void updateBlob(
		java.lang.String name, 
		byte[] data, 
		java.lang.String version)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Operation(translated="getBlobVersion")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.lang.String getBlobVersion(
		java.lang.String name)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
}
