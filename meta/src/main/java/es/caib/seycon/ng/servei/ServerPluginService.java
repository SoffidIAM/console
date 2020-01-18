//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.servei;
import org.springframework.transaction.annotation.Transactional;

import com.soffid.mda.annotation.Depends;
import com.soffid.mda.annotation.Description;
import com.soffid.mda.annotation.Operation;
import com.soffid.mda.annotation.Service;

import es.caib.seycon.ng.model.AuditoriaEntity;

@Service (translatedName="ServerPluginService",
	translatedPackage="com.soffid.iam.service")
@Depends ({es.caib.seycon.ng.model.ServerPluginModuleEntity.class,
	es.caib.seycon.ng.model.DefaultAttributeMappingEntity.class,
	es.caib.seycon.ng.model.DefaultObjectMappingEntity.class,
	es.caib.seycon.ng.model.DefaultObjectMappingPropertyEntity.class,
	es.caib.seycon.ng.model.ServerPluginEntity.class,
	es.caib.seycon.ng.model.AgentDescriptorEntity.class,
	AuditoriaEntity.class,
	es.caib.seycon.ng.servei.ConfiguracioService.class})
public abstract class ServerPluginService {

	@Operation ( grantees={roles.plugins_update.class},
			translated="deployPlugin")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void deployPlugin(
		byte[] i)
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.seycon.ng.servei.DuplicatedClassException {
	}

	@Description("Only deploys if it's a newer version")
	@Operation
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void updatePlugin(
		byte[] i)
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.seycon.ng.servei.DuplicatedClassException {
	}

	@Operation ( grantees={roles.plugins_update.class},
			translated="enablePlugin")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void enablePlugin(
		es.caib.seycon.ng.comu.ServerPlugin plugin, 
		boolean status)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Operation ( grantees={roles.plugins_query.class},
			translated="getAgentDescriptor")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.AgentDescriptor getAgentDescriptor(
		java.lang.String className)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.plugins_query.class},
			translated="getAgentDescriptors")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.AgentDescriptor> getAgentDescriptors()
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.plugins_update.class},
			translated="getPluginAgentDescriptors")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.AgentDescriptor> getPluginAgentDescriptors(
		es.caib.seycon.ng.comu.ServerPlugin plugin)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.plugins_update.class},
			translated="listServerPlugins")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.ServerPlugin> listServerPlugins()
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.plugins_update.class},
			translated="getServerVersion")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.lang.String getServerVersion()
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.plugins_update.class},
			translated="deletePlugin")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void deletePlugin(
		es.caib.seycon.ng.comu.ServerPlugin plugin)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Operation ( grantees={roles.plugins_update.class},
			translated="getAllAgentDescriptorsInfo")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.AgentDescriptor> getAllAgentDescriptorsInfo()
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
}
