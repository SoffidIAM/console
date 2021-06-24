package com.soffid.iam.service;

import java.util.List;
import java.util.Map;

import com.soffid.iam.api.HostPort;
import com.soffid.iam.api.HostService;
import com.soffid.iam.api.ScheduledTask;
import com.soffid.iam.model.HostEntryPointEntity;
import com.soffid.iam.model.HostPortEntity;
import com.soffid.iam.model.HostServiceEntity;
import com.soffid.iam.model.HostSystemEntity;
import com.soffid.iam.model.NetworkDiscoveryAccountEntity;
import com.soffid.mda.annotation.Depends;
import com.soffid.mda.annotation.Description;
import com.soffid.mda.annotation.Operation;
import com.soffid.mda.annotation.Service;

import es.caib.seycon.ng.comu.Account;
import es.caib.seycon.ng.comu.Dispatcher;
import es.caib.seycon.ng.comu.Maquina;
import es.caib.seycon.ng.comu.Password;
import es.caib.seycon.ng.comu.PuntEntrada;
import es.caib.seycon.ng.comu.Xarxa;
import es.caib.seycon.ng.model.AccountEntity;
import es.caib.seycon.ng.model.DispatcherEntity;
import es.caib.seycon.ng.model.MaquinaEntity;
import es.caib.seycon.ng.model.PuntEntradaEntity;
import es.caib.seycon.ng.model.XarxaEntity;
import es.caib.seycon.ng.servei.DispatcherService;
import es.caib.seycon.ng.servei.DominiUsuariService;
import es.caib.seycon.ng.servei.PuntEntradaService;
import es.caib.seycon.ng.servei.XarxaService;
import es.caib.seycon.ng.sync.servei.SyncStatusService;
import roles.agent_create;
import roles.networkDiscovery_query;
import roles.networkDiscovery_schedule;

@Service 
@Depends({XarxaService.class,
	HostPortEntity.class,
	HostSystemEntity.class,
	HostEntryPointEntity.class,
	HostServiceEntity.class,
	PuntEntradaService.class,
	DispatcherService.class,
	MaquinaEntity.class,
	SyncStatusService.class,
	ScheduledTaskService.class,
	XarxaEntity.class,
	PuntEntradaEntity.class,
	DispatcherEntity.class,
	AccountEntity.class,
	NetworkDiscoveryAccountEntity.class,
	DominiUsuariService.class
})
public class NetworkDiscoveryService {
	@Operation (grantees= {networkDiscovery_query.class})
	@Description("Gets the open ports for a host")
	List<HostPort> findHostPorts(Maquina host) { return null; }

	@Description("Internal method to register the host open ports")
	void registerHostPorts(Maquina host, List<HostPort> ports) { }

	@Operation (grantees= {networkDiscovery_query.class})
	@Description("Gets the account protected services for a host")
	List<HostService> findHostServices(Maquina host) { return null; }

	@Description("Internal method to register the account protected services")
	void registerHostServices(Maquina host, Dispatcher dispatcher, List<HostService> services, Map<String,String> domainToSystemMap) { }
	
	@Operation (grantees= {networkDiscovery_schedule.class})
	@Description("Method to register a manual host service")
	HostService createHostService(HostService service) { return null;}
	
	@Operation (grantees= {networkDiscovery_schedule.class})
	@Description("Method to register a manual host service")
	HostService updateHostService(HostService service) { return null;}

	@Operation (grantees= {networkDiscovery_schedule.class})
	@Description("Method to register a manual host service")
	void deleteHostService(HostService service) { }

	@Operation (grantees= {networkDiscovery_query.class})
	@Description("Gets the account protected services for a host")
	List<Dispatcher> findHostSystems(Maquina host) { return null; }

	@Operation (grantees= {networkDiscovery_query.class})
	@Description("Gets the account protected services for a host")
	List<Maquina> findSystemHosts(Dispatcher system) { return null; }

	@Description("Method to register a accounts repository")
	@Operation (grantees= {agent_create.class})
	void registerHostSystem(Maquina host, Dispatcher system) { }
	
	@Operation (grantees= {networkDiscovery_query.class})
	@Description("Gets the entry points for a host")
	List<PuntEntrada> findHostEntryPoints(Maquina host) { return null; }

	@Description("Method to register an entry point")
	@Operation (grantees= {agent_create.class})
	void registerHostEntryPoint(Maquina host, PuntEntrada entryPoint) { }

	@Description("Enable network discovery")
	@Operation (grantees= {networkDiscovery_schedule.class})
	void enableNetworkDiscoveryScheduledTask(Xarxa network) {}

	@Description("Internal method to scan a network")
	@Operation (grantees= {networkDiscovery_schedule.class})
	void startDiscovery(Xarxa network) { }

	@Description("Internal method to get the discovery schedule")
	@Operation (grantees= {networkDiscovery_schedule.class})
	ScheduledTask findNetworkDiscoveryScheduledTask(Xarxa network) { return null; }

	@Description("Register account for network discovery")
	@Operation (grantees= {networkDiscovery_schedule.class})
	void createNetworkAccount(Xarxa network, Account account) {  }

	@Description("Removes account for network discovery")
	@Operation (grantees= {networkDiscovery_schedule.class})
	void removeNetworkAccount(Xarxa network, Account account) {  }

	@Description("Retrieves account for network discovery")
	@Operation (grantees= {networkDiscovery_schedule.class})
	List<Account> findNetworkAccount(Xarxa network) { return null; }

	@Description("Generates a candidate system definiton")
	@Operation (grantees= {networkDiscovery_schedule.class})
	Dispatcher createSystemCandidate(Maquina host, String type, String userName, Password password) { return null; }
}
