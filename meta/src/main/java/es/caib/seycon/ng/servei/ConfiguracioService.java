//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.servei;
import org.springframework.transaction.annotation.Transactional;

import com.soffid.mda.annotation.Depends;
import com.soffid.mda.annotation.Nullable;
import com.soffid.mda.annotation.Operation;
import com.soffid.mda.annotation.Service;

@Service (translatedName="ConfigurationService",
	translatedPackage="com.soffid.iam.service")
@Depends ({es.caib.seycon.ng.model.ConfiguracioEntity.class,
	SeyconServerService.class,
	es.caib.seycon.ng.model.BlobConfigurationEntity.class})
public abstract class ConfiguracioService {

	@Operation ( grantees={roles.parameter_query.class},
			translated="findParameterByNameAndNetworkName")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.Configuracio findParametreByCodiAndCodiXarxa(
		@Nullable java.lang.String codiParametre, 
		@Nullable java.lang.String codiXarxa)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}

	@Operation ( grantees={roles.Tothom.class})
	@Transactional(readOnly=true)
	public String findTenantParameter(
		String tenant, String parameter)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}


	@Operation 
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.Configuracio findMasterParameterByNameAndNetwork(
		@Nullable java.lang.String paramter, 
		@Nullable java.lang.String networkName)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}

	
	@Operation ( grantees={roles.parameter_query.class},
			translated="getParameters")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.Configuracio> getParametres()
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.parameter_delete.class},
			translated="delete")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void delete(
		es.caib.seycon.ng.comu.Configuracio configuracio)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Operation ( grantees={roles.parameter_create.class},
			translated="create")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.Configuracio create(
		es.caib.seycon.ng.comu.Configuracio configuracio)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.parameter_query.class},
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
	@Operation ( grantees={roles.parameter_update.class},
			translated="update")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.Configuracio update(
		es.caib.seycon.ng.comu.Configuracio configuracio)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation(grantees={roles.parameter_update.class},translated="updateBlob")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void updateBlob(
		java.lang.String name, 
		byte[] data)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Operation(grantees={roles.parameter_update.class},translated="getBlob")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public byte[] getBlob(
		java.lang.String name)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation(grantees={roles.parameter_update.class},translated="deleteBlob")
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
