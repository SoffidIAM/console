package com.soffid.iam.service;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.HeaderParam;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.AccessTree;
import com.soffid.iam.api.Account;
import com.soffid.iam.api.Host;
import com.soffid.iam.api.HostPort;
import com.soffid.iam.api.HostService;
import com.soffid.iam.api.Network;
import com.soffid.iam.api.Password;
import com.soffid.iam.api.PasswordDomain;
import com.soffid.iam.api.ScheduledTask;
import com.soffid.iam.api.ScheduledTaskHandler;
import com.soffid.iam.api.Server;
import com.soffid.iam.api.System;
import com.soffid.iam.api.UserDomain;
import com.soffid.iam.model.AccountEntity;
import com.soffid.iam.model.HostEntity;
import com.soffid.iam.model.HostEntryPointEntity;
import com.soffid.iam.model.HostPortEntity;
import com.soffid.iam.model.HostServiceEntity;
import com.soffid.iam.model.HostSystemEntity;
import com.soffid.iam.model.NetworkDiscoveryAccountEntity;
import com.soffid.iam.model.NetworkEntity;
import com.soffid.iam.model.ServerEntity;
import com.soffid.iam.model.SystemEntity;
import com.sun.org.apache.xalan.internal.xsltc.DOM;

import es.caib.seycon.ng.exception.InternalErrorException;

public class NetworkDiscoveryServiceImpl extends NetworkDiscoveryServiceBase {
	Log log = LogFactory.getLog(getClass());
	
	@Override
	protected void handleEnableNetworkDiscoveryScheduledTask(Network network) throws Exception {
		NetworkEntity ne = getNetworkEntityDao().load(network.getId());
		if (! Boolean.TRUE.equals(ne.getDiscovery())) {
			// Check the handler exists
			if (!handlerExists())
				registerHandler();
			
			final String name = "Discover network "+network.getName()+" - "+network.getDescription();
			if (!taskExists(ne.getId().toString())) {
				ScheduledTask sc = new ScheduledTask();
				sc.setActive(false);
				sc.setDayOfWeekPattern("6");
				sc.setDayPattern("*");
				sc.setHandlerName(SystemScheduledTasks.NETWORK_DISCOVERY);
				sc.setHoursPattern("0");
				sc.setMinutesPattern("0");
				sc.setMonthsPattern("*");
				sc.setName(name);
				sc.setParams(ne.getId().toString());
				sc.setServerName("*");
				getScheduledTaskService().create(sc);
			}
			ne.setDiscovery(true);
			getNetworkEntityDao().update(ne);			
		}
	}


	private boolean taskExists(String name) throws InternalErrorException {
		ScheduledTask task = getScheduledTaskService().findScheduledTaskByHandlerAndParams(SystemScheduledTasks.NETWORK_DISCOVERY, name);
		return task != null;
	}


	private void registerHandler() throws InternalErrorException {
		ScheduledTaskHandler h = new ScheduledTaskHandler();
		h.setName(SystemScheduledTasks.NETWORK_DISCOVERY);
		h.setClassName("com.soffid.iam.sync.engine.cron.NetworkDiscovery"); //$NON-NLS-1$
		getScheduledTaskService().create(h);
	}


	private boolean handlerExists() throws InternalErrorException {
		for (ScheduledTaskHandler h: getScheduledTaskService().listHandlers()) {
			if (h.getName().endsWith(SystemScheduledTasks.NETWORK_DISCOVERY))
				return true;
		}
		return false;
	}


	@Override
	protected ScheduledTask handleFindNetworkDiscoveryScheduledTask(Network network) throws Exception {
		NetworkEntity ne = getNetworkEntityDao().load(network.getId());
		if (! Boolean.TRUE.equals(ne.getDiscovery()))
			return null;
		else {
			return getScheduledTaskService().findScheduledTaskByHandlerAndParams(SystemScheduledTasks.NETWORK_DISCOVERY, ne.getId().toString());
		}
	}

	@Override
	protected List<AccessTree> handleFindHostEntryPoints(Host host) throws Exception {
		HostEntity he = getHostEntityDao().load(host.getId());
		LinkedList<AccessTree> r = new LinkedList<AccessTree>();
		for (HostEntryPointEntity hep: he.getEntryPoints()) {
			AccessTree ep = getEntryPointService().findApplicationAccessById(hep.getEntryPoint().getId());
			if (ep != null)
				r.add(ep);
		}
		return r;
	}

	@Override
	protected List<HostPort> handleFindHostPorts(Host host) throws Exception {
		HostEntity he = getHostEntityDao().load(host.getId());
		return getHostPortEntityDao().toHostPortList(he.getPorts());
	}

	@Override
	protected List<HostService> handleFindHostServices(Host host) throws Exception {
		HostEntity he = getHostEntityDao().load(host.getId());
		return getHostServiceEntityDao().toHostServiceList(he.getServices());
	}

	@Override
	protected List<System> handleFindHostSystems(Host host) throws Exception {
		HostEntity he = getHostEntityDao().load(host.getId());
		LinkedList<System> r = new LinkedList<System>();
		for (HostSystemEntity hep: he.getSystems()) {
			System s = getDispatcherService().findDispatcherByName(hep.getSystem().getName());
			if (s != null)
				r.add(s);
		}
		return r;
	}

	@Override
	protected void handleRegisterHostEntryPoint(Host host, AccessTree entryPoint) throws Exception {
		if (entryPoint.getId() == null) {
			entryPoint = getEntryPointService().create(entryPoint);
		}
		HostEntryPointEntity hep = getHostEntryPointEntityDao().newHostEntryPointEntity();
		hep.setEntryPoint(getEntryPointEntityDao().load(entryPoint.getId()));
		hep.setHost(getHostEntityDao().load(host.getId()));
		getHostEntryPointEntityDao().create(hep);
	}

	@Override
	protected void handleRegisterHostPorts(Host host, List<HostPort> ports) throws Exception {
		HostEntity entity = getHostEntityDao().load(host.getId());
		getHostPortEntityDao().remove(entity.getPorts());
		entity.getPorts().clear();
		for (HostPort port: ports) {
			port.setHostId(host.getId());
			HostPortEntity pe = getHostPortEntityDao().hostPortToEntity(port);
			getHostPortEntityDao().create(pe);
			entity.getPorts().add(pe);
		}
	}

	@Override
	protected void handleRegisterHostSystem(Host host, System system) throws Exception {
		if (system.getId() == null) {
			system = getDispatcherService().create(system);
		}
		HostSystemEntity hep = getHostSystemEntityDao().newHostSystemEntity();
		hep.setSystem(getSystemEntityDao().load(system.getId()));
		hep.setHost(getHostEntityDao().load(host.getId()));
		for (HostSystemEntity link: hep.getSystem().getHosts()) {
			if (link.getHost() == hep.getHost())
				return;
		}
		getHostSystemEntityDao().create(hep);
	}

	@Override
	protected void handleRegisterHostServices(Host host, System s, List<HostService> services, Map<String,String> domainToSystemMap) throws Exception {
		HostEntity entity = getHostEntityDao().load(host.getId());
		
		services = new LinkedList<HostService>(services);
		// Remove services without account
		for(Iterator<HostService> it = services.iterator(); it.hasNext();) {
			HostService service = it.next();
			updateAccountId(service, s, domainToSystemMap);
			if (service.getAccountId() == null) {
				it.remove();
			}
		}

		for (Iterator<HostServiceEntity> it = entity.getServices().iterator(); it.hasNext();) {
			HostServiceEntity hse = it.next();
			if (! hse.isManual()) {
				HostService hs = find(hse, services);
				if (hs == null) {
					getHostServiceEntityDao().remove(hse);
					it.remove();
				} else {
					services.remove(hs);
				}
			}
		}
		
		for (HostService service: services) {
			HostServiceEntity pe = getHostServiceEntityDao().newHostServiceEntity();
			service.setHostId(host.getId());
			getHostServiceEntityDao().hostServiceToEntity(service, pe, true);
			pe.setManual(false);
			getHostServiceEntityDao().create(pe);
			entity.getServices().add(pe);
		}
	}

	private void updateAccountId(HostService service, System s, Map<String, String> domainToSystemMap) {
		String accountName = service.getAccountName();
		if (accountName == null || accountName.trim().isEmpty()) return;
		accountName = accountName.toLowerCase();
		
		if (accountName.startsWith("nt authority\\")) return;
		if (accountName.equals("localsystem")) return;
		AccountEntity acc = null;
		if (accountName.startsWith(".\\")) {
			String accountName2 = accountName.substring(2);
			service.setAccountName(accountName);
			acc = getAccountEntityDao().findByNameAndSystem(accountName2, s.getName());
		}
		else if (accountName.contains("\\"))
		{
			int i = accountName.indexOf("\\");
			String domain = accountName.substring(0,i);
			String accountName2 = accountName.substring(i+1);
			String system = domainToSystemMap.get(domain.toUpperCase());
			if (system == null) {
				log.info("Cannot found system for "+domain.toUpperCase()+" // "+domainToSystemMap);
			} else {
				acc = getAccountEntityDao().findByNameAndSystem(accountName2, system);
			}
		}
		else
		{
			acc = getAccountEntityDao().findByNameAndSystem(accountName, s.getName());
		}
		if (acc == null && accountName.contains("@")) {
			int i = accountName.indexOf("@");
			String domain = accountName.substring(i+1);
			String accountName2 = accountName.substring(0,i);
			String system = domainToSystemMap.get(domain.toLowerCase());
			if (system == null) {
				log.info("Cannot found system for "+domain.toLowerCase()+" // "+domainToSystemMap);
			} else {
				acc = getAccountEntityDao().findByNameAndSystem(accountName, system);
			}
		}
		if (acc != null) {
			service.setAccountId(acc.getId());
			service.setAccountSystem(s.getName());
		} else {
			log.info("Account "+accountName+"  not found");
		}
	}


	private HostService find(HostServiceEntity hse, List<HostService> services) {
		for ( HostService service: services ) {
			if (service.getService().equals(hse.getService()) && 
					service.getAccountId().equals(hse.getAccount().getId())) {
				return service;
			}
		}
		return null;
	}

	@Override
	protected void handleStartDiscovery(Network network) throws Exception {
		getScheduledTaskService().startNow(handleFindNetworkDiscoveryScheduledTask(network));
	}


	@Override
	protected List<Account> handleFindNetworkAccount(Network network) throws Exception {
		List<Account> r = new LinkedList<>();
		NetworkEntity ne = getNetworkEntityDao().load(network.getId());

		for (NetworkDiscoveryAccountEntity nea: ne.getAccounts()) {
			r.add(getAccountEntityDao().toAccount(nea.getAccount()));
		}
		
		return r ;
	}


	@Override
	protected void handleCreateNetworkAccount(Network network, Account account) throws Exception {
		NetworkEntity ne = getNetworkEntityDao().load(network.getId());
		AccountEntity acc = getAccountEntityDao().load(account.getId());
		NetworkDiscoveryAccountEntity entity = getNetworkDiscoveryAccountEntityDao().newNetworkDiscoveryAccountEntity();
		
		entity.setAccount(acc);
		entity.setNetwork(ne);
		getNetworkDiscoveryAccountEntityDao().create(entity);
	}


	@Override
	protected void handleRemoveNetworkAccount(Network network, Account account) throws Exception {
		NetworkEntity ne = getNetworkEntityDao().load(network.getId());
		for (Iterator<NetworkDiscoveryAccountEntity> it = ne.getAccounts().iterator(); it.hasNext(); ) {
			NetworkDiscoveryAccountEntity nda = it.next();
			if (nda.getAccount().getId().equals(account.getId())) {
				getNetworkDiscoveryAccountEntityDao().remove(nda);
				it.remove();
			}
		}
	}


	@Override
	protected System handleCreateSystemCandidate(Host host, String agentType, String userName, Password password, String instance)
			throws Exception {
		
		HostEntity hostEntity = getHostEntityDao().load(host.getId());
		String url = "local";
		NetworkEntity network = hostEntity.getNetwork();
		
		ServerEntity server = network.getDiscoveryServer();
		if (server != null)
			url = server.getUrl();
		System s = new System();
		s.setUrl(url);
		s.setAccessControl(false);
		s.setAuthoritative(false);
		s.setDescription( host.getDescription()  );
		s.setFullReconciliation(false);
		s.setGenerateTasksOnLoad(false);
		s.setManualAccountCreation(true);
		s.setName(agentType+" "+host.getName());
		if (instance != null && !instance.trim().isEmpty())
			s.setName(s.getName()+"/"+instance);
		PasswordDomain pd = EJBLocator.getUserDomainService().findPasswordDomainByName("DEFAULT");
		if (pd == null) {
			pd = EJBLocator.getUserDomainService().findAllPasswordDomain().iterator().next();
		}
		s.setPasswordsDomain(pd.getName());
		s.setPasswordsDomainId(pd.getId());
		UserDomain ud = EJBLocator.getUserDomainService().findUserDomainByName("DEFAULT");
		if (ud == null) {
			ud = EJBLocator.getUserDomainService().findAllUserDomain().iterator().next();
		}
		s.setUsersDomain(ud.getName());
		s.setReadOnly(true);
		s.setRolebased(false);
		s.setSharedDispatcher(true);
		s.setTrusted(false);
		if ("AD".equalsIgnoreCase(agentType)) {
			s.setClassName("com.soffid.iam.sync.agent2.CustomizableActiveDirectoryAgent");
			s.setSharedDispatcher(false);
			s.setParam0(host.getName());
			s.setParam1(host.getName());
			
			for (HostPortEntity hp: hostEntity.getPorts()) {
				if (hp.getPort().equals("tcp/389")) {
					int t = hp.getDescription().indexOf("Domain: ");
					if (t > 0) {
						String dn = hp.getDescription().substring(t+8);
						if (dn.contains(","))
							dn = dn.substring(0, dn.indexOf(","));
						if (dn.endsWith("0."))
							dn = dn.substring(0, dn.length() - 2);
						StringBuffer sb = new StringBuffer();
						for (String part: dn.split("\\.")) {
							if (sb.length() > 0) sb.append(",");
							sb.append("dc=");
							sb.append(part);
						}
						s.setParam0(sb.toString());
					}
				}
			}
			s.setParam2(userName);
			s.setParam3(password.toString());
			s.setParam4("false");
			s.setParam5("false");
			s.setParam6("");
			s.setParam7("false");
			s.setParam8("true");
			s.setParam9("false");
		} 
		else if ("Windows".equalsIgnoreCase(agentType)) {
			s.setClassName("com.soffid.iam.sync.agent.SimpleWindowsAgent");
			s.setParam0(userName);
			s.setParam2(password.toString());
			s.setParam3(host.getIp());
			s.setParam4("true"); // only passwords
			s.setParam7("false"); // debug
		} 
		else if ("LDAP".equals(agentType)) {
			s.setClassName("com.soffid.iam.sync.agent2.CustomizableLDAPAgent");
			s.setSharedDispatcher(false);
			s.setParam0(userName);
			s.setParam1(password.toString());
			s.setParam2(host.getName());
			s.setParam8("false"); //debug
			s.setParam9("false"); //ssl
			for (HostPortEntity hp: hostEntity.getPorts()) {
				if (hp.getPort().equals("tcp/636")) {
					s.setParam9("true");
				}
			}

			s.setParam7(instance);
		} 
		else if ("Linux".equalsIgnoreCase(agentType)) {
			s.setClassName("com.soffid.iam.sync.agent.SimpleSSHAgent");
			s.setParam0(userName);
			s.setParam2(password.toString());
			s.setParam3(host.getIp());
			s.setParam4("true"); // only passwords
			s.setParam6("UTF-8");
			s.setParam7("false"); // debug
		} 
		else if ("MariaDB".equals(agentType)) {
			s.setClassName("com.soffid.iam.agent.mariadb.MariadbAgent");
			s.setParam0(userName);
			s.setParam1(password.toString());
			s.setParam2("jdbc:mariadb://"+host.getName()+"/"+instance);
			s.setParam4("false"); // debug
		} 
		else if ("PostgreSQL".equals(agentType)) {
			s.setClassName("com.soffid.iam.agent.postgresql.PostgresqlAgent");
			s.setParam0(userName);
			s.setParam1(password.toString());
			s.setParam2("jdbc:postgresql://"+host.getName()+"/"+instance);
			s.setParam4("false"); // debug
		} 
		else if ("SQLServer".equals(agentType)) {
			s.setClassName("com.soffid.iam.agent.sqlserver.SqlServerAgent");
			s.setParam0(userName);
			s.setParam1(password.toString());
			s.setParam2("jdbc:sqlserver://"+host.getName()+";databaseName="+instance);
			s.setParam3("false"); // multi-instance
			s.setParam4("false"); // debug
		} 
		else if ("Oracle".equals(agentType)) {
			String n = instance.replace("@", "");
			int i = n.indexOf("thin:");
			if (i > 0) n = n.substring(i+5);
			s.setName("Oracle "+n);
			s.setClassName("com.soffid.iam.agent.oracle.OracleAgent");
			s.setParam0(userName);
			s.setParam1(password.toString());
			s.setParam2(instance);
			s.setParam3("true"); // multi-instance
			s.setParam4("false"); // debug
		}
		else {
			throw new InternalErrorException("Unknown system type "+agentType);
		}
		
		s = getDispatcherService().create(s);
		
		handleRegisterHostSystem(host, s);
		
		return s;
	}


	@Override
	protected List<Host> handleFindSystemHosts(System system) throws Exception {
		SystemEntity se = getSystemEntityDao().load(system.getId());
		List<Host> r = new LinkedList<>();
		for (HostSystemEntity hs: se.getHosts()) {
			r.add(getHostEntityDao().toHost(hs.getHost()));
		}
		return r;
	}


	@Override
	protected HostService handleCreateHostService(HostService service) throws Exception {
		HostServiceEntity entity = getHostServiceEntityDao().hostServiceToEntity(service);
		entity.setManual(true);
		getHostServiceEntityDao().create(entity);
		return getHostServiceEntityDao().toHostService(entity);
	}


	@Override
	protected HostService handleUpdateHostService(HostService service) throws Exception {
		HostServiceEntity entity = getHostServiceEntityDao().load(service.getId());
		if (entity == null)
			return null;
		entity.setCommand(service.getCommand());
		if (entity.isManual()) {
			entity.setService(service.getService());
			entity.setHost( getHostEntityDao().load(service.getHostId()) );
			entity.setAccount( getAccountEntityDao().findByNameAndSystem(service.getAccountName(), service.getAccountSystem()) );
		}
		getHostServiceEntityDao().create(entity);
		return getHostServiceEntityDao().toHostService(entity);
	}


	@Override
	protected void handleDeleteHostService(HostService service) throws Exception {
		HostServiceEntity entity = getHostServiceEntityDao().load(service.getId());
		if (entity != null && entity.isManual()) {
			getHostServiceEntityDao().remove(entity);
		}
	}


	@Override
	protected void handleDisconnectSystemFromHost(Host host, System system) throws Exception {
		HostEntity he = getHostEntityDao().load(host.getId());
		for (HostSystemEntity link: new LinkedList<>( he.getSystems())) {
			if (link.getSystem().getId().equals(system.getId())) {
				getHostSystemEntityDao().remove(link);
			}
		}
	}

}
