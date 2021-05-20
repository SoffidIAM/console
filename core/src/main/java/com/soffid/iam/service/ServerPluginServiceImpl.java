// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package com.soffid.iam.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.resource.spi.SecurityException;

import com.soffid.iam.api.AgentDescriptor;
import com.soffid.iam.api.AgentDescriptorWorkflow;
import com.soffid.iam.api.AttributeMapping;
import com.soffid.iam.api.Audit;
import com.soffid.iam.api.Configuration;
import com.soffid.iam.api.ObjectMappingProperty;
import com.soffid.iam.api.ServerPlugin;
import com.soffid.iam.api.ServerPluginModule;
import com.soffid.iam.common.security.SoffidPrincipal;
import com.soffid.iam.model.AgentDescriptorEntity;
import com.soffid.iam.model.AgentDescriptorEntityDao;
import com.soffid.iam.model.AuditEntity;
import com.soffid.iam.model.DefaultAttributeMappingEntity;
import com.soffid.iam.model.DefaultAttributeMappingEntityDao;
import com.soffid.iam.model.DefaultObjectMappingEntity;
import com.soffid.iam.model.DefaultObjectMappingEntityDao;
import com.soffid.iam.model.DefaultObjectMappingPropertyEntity;
import com.soffid.iam.model.DefaultObjectMappingPropertyEntityDao;
import com.soffid.iam.model.ServerPluginEntity;
import com.soffid.iam.model.ServerPluginEntityDao;
import com.soffid.iam.model.ServerPluginModuleEntity;
import com.soffid.iam.model.ServerPluginModuleEntityDao;
import com.soffid.iam.service.impl.InternalAgentDescriptor;
import com.soffid.iam.service.impl.InternalObjectMapping;
import com.soffid.iam.service.impl.InternalServerPluginModule;
import com.soffid.iam.service.impl.ServerPluginParser;
import com.soffid.iam.utils.Security;

import es.caib.seycon.ng.comu.ServerPluginModuleType;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.servei.DuplicatedClassException;

/**
 * @see es.caib.seycon.ng.servei.ServerPluginServer
 */
public class ServerPluginServiceImpl extends com.soffid.iam.service.ServerPluginServiceBase {
    final String versionTag = "serverBaseVersion"; //$NON-NLS-1$
    final String consoleVersionTag = "consolePluginVersion"; //$NON-NLS-1$

    private String translateVersion (String version)
    {
    	if (version.endsWith("-SNAPSHOT")) //$NON-NLS-1$
    	{
    		version = version.substring(0, version.length()-8);
    		version = version + new SimpleDateFormat("yyyy.MM.dd.HH.mm").format(new Date()); //$NON-NLS-1$
    	}
    	return version;
    }
    
    /**
     * @see es.caib.seycon.ng.servei.ServerPluginServer#deployPlugin(java.io.InputStream)
     */
    protected void handleDeployPlugin(byte b[]) throws java.lang.Exception {
    	ServerPluginParser spp = new ServerPluginParser();
    	spp.parse(b);
    	if (spp.getPlugin() == null)
    		throw new InternalErrorException (Messages.getString("ServerPluginNameImpl.NotAPluginFile")); //$NON-NLS-1$
    	deployPlugin(spp);
    }

	public void deployPlugin(ServerPluginParser spp)
			throws DuplicatedClassException, InternalErrorException, SecurityException {
		// Test duplicated classes
    	testDuplicatedClasses (spp.getPlugin());
    	// Test duplicated syncserver
    	testDuplicatedSyncServer (spp.getPlugin());
    	//
    	for ( ServerPluginModule module : spp.getPlugin().getModules())
    	{
    		if (! Security.getMasterTenantName().equals(Security.getCurrentTenantName()) &&
    				! module.getType().equals(ServerPluginModuleType.MODULE_AGENT))
    		{
    			throw new SecurityException("Only connector plugins are allowed");
    		}
    	}
    	ServerPluginEntity plugin = getServerPluginEntityDao().findByName(spp.getPlugin().getName());
    	if (plugin == null)
    	{
    		plugin = getServerPluginEntityDao().newServerPluginEntity();
    		plugin.setName(spp.getPlugin().getName());
    		plugin.setEnabled(true);
    		plugin.setVersion(translateVersion(spp.getPlugin().getVersion()));
    		SoffidPrincipal p = Security.getSoffidPrincipal();
    		if (p != null && p.getUserName() != null)
    			plugin.setAuthor(p.getUserName());
    		plugin.setDeployed(new Date());
    		getServerPluginEntityDao().create(plugin);
    		audit(plugin, "C");
    	} 
    	else if (plugin.getTenant().getId().equals(Security.getCurrentTenantId()))
    	{
    		plugin.setVersion(translateVersion(spp.getPlugin().getVersion()));
    		SoffidPrincipal p = Security.getSoffidPrincipal();
    		if (p != null && p.getUserName() != null)
    			plugin.setAuthor(p.getUserName());
    		else
    			plugin.setAuthor(null);
    		plugin.setDeployed(new Date());
    		getServerPluginEntityDao().update(plugin);
    		audit(plugin, "U");
    		for (ServerPluginModuleEntity module: plugin.getModules())
    		{
    			for ( AgentDescriptorEntity agent: module.getAgents())
    			{
            		for (DefaultObjectMappingEntity dom: agent.getDefaultObjectMappings())
            		{
            			for (DefaultObjectMappingPropertyEntity domp: dom.getProperties())
            			{
            				getDefaultObjectMappingPropertyEntityDao().remove(domp);
            			}
                		for (DefaultAttributeMappingEntity dam: dom.getDefaultAttributeMappings())
                		{
                			getDefaultAttributeMappingEntityDao().remove(dam);
                		}
            			getDefaultObjectMappingEntityDao().remove(dom);
            		}
    				getAgentDescriptorEntityDao().remove(agent);
    			}
    			getServerPluginModuleEntityDao().remove(module);
    		}
    	} else {
    		return;
    	}
    	
    	for (ServerPluginModule module: spp.getPlugin().getModules())
    	{
    		ServerPluginModuleEntity moduleEntity = getServerPluginModuleEntityDao().
    				newServerPluginModuleEntity();
    		InternalServerPluginModule i = (InternalServerPluginModule) module;
    		moduleEntity.setInitClass(i.getInitClass());
    		moduleEntity.setName(i.getName());
    		moduleEntity.setType(i.getType());
    		moduleEntity.setContents(i.getData());
    		moduleEntity.setPlugin(plugin);
    		getServerPluginModuleEntityDao().create(moduleEntity);
    		if (module.getAgents() != null)
    		{
	    		for (AgentDescriptor agent: module.getAgents())
	    		{
	    			AgentDescriptorEntity agentEntity = getAgentDescriptorEntityDao().
	    					newAgentDescriptorEntity();
	    			agentEntity.setClassName(agent.getClassName());
	    			agentEntity.setDescription(agent.getDescription());
	    			agentEntity.setEnableAccessControl(agent.isEnableAccessControl());
	    			agentEntity.setEnableAttributeMapping(agent.isEnableAttributeMapping());
	    			agentEntity.setUserInterface(agent.getUserInterface());
	    			agentEntity.setModule(moduleEntity);
	    			agentEntity.setAuthoritativeSource(agent.isAuthoritativeSource());
	    			agentEntity.setEnableObjectTriggers(agent.isEnableObjectTriggers());
	    			agentEntity.setPlugin(plugin);
	    			agentEntity.setService(agent.isService());
	    			getAgentDescriptorEntityDao().create(agentEntity);
	    			if (agent instanceof InternalAgentDescriptor && ((InternalAgentDescriptor) agent).getObjects() != null)
	    			{
	    				for (InternalObjectMapping om: ((InternalAgentDescriptor) agent).getObjects())
	    				{
	    					DefaultObjectMappingEntity dom = getDefaultObjectMappingEntityDao().newDefaultObjectMappingEntity();
	    					dom.setAgentDescriptor(agentEntity);
	    					dom.setCondition(om.getCondition());
	    					dom.setSoffidObject(om.getSoffidObject());
	    					dom.setSystemObject(om.getSystemObject());
	    					getDefaultObjectMappingEntityDao().create(dom);
	    					agentEntity.getDefaultObjectMappings().add(dom);
	    					for (ObjectMappingProperty prop: om.getProperties())
	    					{
	    						DefaultObjectMappingPropertyEntity dompe = getDefaultObjectMappingPropertyEntityDao().newDefaultObjectMappingPropertyEntity();
	    						dompe.setObject(dom);
	    						dompe.setProperty(prop.getProperty());
	    						dompe.setValue(prop.getValue());
	    						getDefaultObjectMappingPropertyEntityDao().create(dompe);
	    						dom.getProperties().add(dompe);
	    					}
		    				for (AttributeMapping am: om.getAttributes())
		    				{
		    					DefaultAttributeMappingEntity dam = getDefaultAttributeMappingEntityDao().newDefaultAttributeMappingEntity();
		    					dam.setDefaultObjectMapping(dom);
		    					dam.setDirection(am.getDirection());
		    					dam.setSoffidAttribute(am.getSoffidAttribute());
		    					dam.setSystemAttribute(am.getSystemAttribute());
		    					getDefaultAttributeMappingEntityDao().create(dam);
		    					dom.getDefaultAttributeMappings().add(dam);
		    				}
	    				}
	    			}
	    		}
    		}
    	}
        updateConfig();
	}

    private void testDuplicatedSyncServer(ServerPlugin plugin) throws DuplicatedClassException
	{
    	boolean found = false;
    	for ( ServerPluginModule module : plugin.getModules())
    	{
    		if (module.getType().equals(ServerPluginModuleType.MODULE_SYNCSERVER))
    		{
    			found = true;
    			break;
    		}
    	}
    	if (! found)
    		return; // No syncserver module included
    	
    	List<ServerPluginModuleEntity> modules = getServerPluginModuleEntityDao().
    			findByType(ServerPluginModuleType.MODULE_SYNCSERVER);
    	for (ServerPluginModuleEntity module: modules) 
    	{
    		if (!module.getPlugin().getName().equals(plugin.getName()) &&
    				module.getPlugin().isEnabled())
    		{
                throw new DuplicatedClassException(String.format("A synchronization engine is already enabled on plugin %s", //$NON-NLS-1$
                        module.getPlugin().getName()));
    		}
    	}
	}

	private void testDuplicatedClasses(ServerPlugin plugin) throws DuplicatedClassException, InternalErrorException
	{
		for (ServerPluginModule module: plugin.getModules())
		{
			if (module.getAgents() != null)
			{
				for ( AgentDescriptor agent: module.getAgents())
				{
					AgentDescriptorEntity ad2 = getAgentDescriptorEntityDao().findByClass(
	                    Security.getCurrentTenantName(), agent.getClassName());
					if (ad2 != null && ad2.getModule() == null
							&& ad2.getPlugin().getTenant().getId().equals(Security.getCurrentTenantId()))
					{
						// Migración de agente anterior
						getAgentDescriptorEntityDao().remove(ad2);
					}
					else if (ad2 != null &&
							ad2.getModule() != null && ad2.getModule().getPlugin() != null &&
							ad2.getModule().getPlugin().isEnabled() &&
							ad2.getModule().getPlugin().getTenant().getId().equals(Security.getCurrentTenantId()) &&
							!ad2.getModule().getPlugin().getName().equals(plugin.getName()))
					{
		                throw new DuplicatedClassException(String.format("Duplicated class %s", //$NON-NLS-1$
		                        agent.getClassName()));
					}
				}
			}
		}
	}

	/**
     * @see es.caib.seycon.ng.servei.ServerPluginServer#listServerPlugins()
     */
    @SuppressWarnings("rawtypes")
    protected java.util.Collection<ServerPlugin> handleListServerPlugins()
            throws java.lang.Exception {
        Collection res = new LinkedList<ServerPlugin>();
        for (ServerPluginEntity plugin: getServerPluginEntityDao()
                .loadAll())
        {
        	if (plugin.getTenant().getId().equals(Security.getCurrentTenantId()))
        	{
	        	ServerPlugin p = toServerPlugin(plugin);
	        	res.add(p);
        	}
        }
        return res;
    }

	private ServerPlugin toServerPlugin(ServerPluginEntity plugin)
	{
		ServerPlugin p = getServerPluginEntityDao().toServerPlugin(plugin);
		List<ServerPluginModule> modules = new LinkedList<ServerPluginModule>();
		for (ServerPluginModuleEntity module: plugin.getModules())
		{
			ServerPluginModule m = getServerPluginModuleEntityDao().
					toServerPluginModule(module);
			modules.add(m);
			List<AgentDescriptor> agents = new LinkedList<AgentDescriptor>();
			for (AgentDescriptorEntity agent: module.getAgents())
			{
				agents.add(getAgentDescriptorEntityDao().toAgentDescriptor(agent));
			}
			m.setAgents(agents);
		}
		p.setModules(modules);
		return p;
	}

    /**
     * @see es.caib.seycon.ng.servei.ServerPluginServer#enablePlugin(es.caib.seycon.ng.comu.ServerPlugin,
     *      boolean)
     */
    protected void handleEnablePlugin(com.soffid.iam.api.ServerPlugin plugin, boolean status) throws java.lang.Exception {
        ServerPluginEntity entity = getServerPluginEntityDao().load(plugin.getId());
        if (entity.getTenant().getId().equals(Security.getCurrentTenantId()))
        {
	        if (status) {
		        ServerPlugin reloaded = toServerPlugin(entity);
		        testDuplicatedSyncServer(plugin);
		        testDuplicatedClasses(plugin);
	        }
	        entity.setEnabled(status);
	        getServerPluginEntityDao().update(entity);
    		audit(entity, status?"E":"D");
	        updateConfig();
        }
    }

    /**
     * @see es.caib.seycon.ng.servei.ServerPluginServer#getPluginAgentDescriptors(es.caib.seycon.ng.comu.ServerPlugin)
     */
    @SuppressWarnings(value = {"rawtypes"})
    protected java.util.Collection<AgentDescriptor> handleGetPluginAgentDescriptors(com.soffid.iam.api.ServerPlugin plugin) throws java.lang.Exception {
        Collection<Object[]> agentsBasics = getServerPluginEntityDao().findAgentsBasicDataByServerPluginID(plugin.getId());
        
        Collection<AgentDescriptor> res = new LinkedList<AgentDescriptor>();
        if (agentsBasics != null)
            for (Iterator<Object[]> it = agentsBasics.iterator(); it.hasNext();) {
                Object[] obj = it.next();
                Boolean enable = (Boolean) obj[3];
                Boolean authoritative = (Boolean) obj[4];
                Boolean enableAttributeMapping = (Boolean) obj[5];
                Boolean enableObjectTriggers = (Boolean) obj[6];
                String tenant = (String) obj[7];
                
                if (tenant.equals(Security.getMasterTenantName()) ||
                		tenant.equals(Security.getCurrentTenantName()))
                {
	                AgentDescriptor ag = new AgentDescriptor();
	                ag.setId((Long) obj[0]);
	                ag.setDescription((String) obj[1]);
	                ag.setClassName((String) obj[2]);
	                ag.setEnableAttributeMapping(enableAttributeMapping != null ? enableAttributeMapping.booleanValue(): false);
	                ag.setEnableAccessControl(enable != null ? enable.booleanValue() : false);
	                ag.setAuthoritativeSource(authoritative != null ? authoritative.booleanValue() : false);
	                ag.setEnableObjectTriggers(enableObjectTriggers != null ? enableObjectTriggers.booleanValue(): false);
	                res.add(ag);
                }
            }

        return res;

        /*
         * ServerPluginEntity entity =
         * getServerPluginEntityDao().load(plugin.getId()); Vector v = new
         * Vector(); v.addAll(entity.getAgents());
         * getAgentDescriptorEntityDao().toAgentDescriptorCollection(v); return
         * v;
         */
    }

    /**
     * @see es.caib.seycon.ng.servei.ServerPluginServer#getAgentDescriptor(java.lang.String)
     */
    protected com.soffid.iam.api.AgentDescriptor handleGetAgentDescriptor(java.lang.String className) throws java.lang.Exception {
        AgentDescriptorEntity ade = getAgentDescriptorEntityDao().findByClass(Security.getCurrentTenantName(), className);
        if (ade == null)
        	ade = getAgentDescriptorEntityDao().findByClass(Security.getMasterTenantName(), className);
        return getAgentDescriptorEntityDao().toAgentDescriptor(ade);
    }

    /**
     * @see es.caib.seycon.ng.servei.ServerPluginServer#getAgentDescriptors()
     */
    protected java.util.Collection<AgentDescriptor> handleGetAgentDescriptors()
            throws java.lang.Exception {
        LinkedList<AgentDescriptorEntity> v = new LinkedList<AgentDescriptorEntity>();
        for (Iterator<ServerPluginEntity> itPlugin = getServerPluginEntityDao().findAll()
                .iterator(); itPlugin.hasNext();) {
            ServerPluginEntity sp = (ServerPluginEntity) itPlugin.next();
        	String tenant = sp.getTenant().getName();
            if (sp.isEnabled() && (
            		tenant.equals( Security.getCurrentTenantName()) ||
        			tenant.equals(Security.getMasterTenantName()))) {
                v.addAll(sp.getAgents());
            }
        }
        List<AgentDescriptor> l = getAgentDescriptorEntityDao().toAgentDescriptorList(v);
        Collections.sort(l, new Comparator<AgentDescriptor>() {

			@Override
			public int compare(AgentDescriptor o1, AgentDescriptor o2) {
				return o1.getDescription().compareTo(o2.getDescription());
			}
		});
        return l;
    }

    /**
     * @see es.caib.seycon.ng.servei.ServerPluginServer#getAllAgentDescriptorsInfo()
     */
    protected java.util.Collection<AgentDescriptor> handleGetAllAgentDescriptorsInfo()
            throws java.lang.Exception {
        // Carreguem tots els descriptor d'agents (no importa si estan actius o
        // no)
        List<AgentDescriptorEntity> v = getAgentDescriptorEntityDao().findAllOnlyBasicData();

        List<AgentDescriptor> v2 = new LinkedList<AgentDescriptor>();
        // Afegim un descriptor d'agents buit (per la cerca per descripcio)
        AgentDescriptor ad = new AgentDescriptor ();
        ad.setAuthoritativeSource(false);
        ad.setClassName(""); //$NON-NLS-1$ //$NON-NLS-2$
        ad.setDescription("%"); //$NON-NLS-1$ //$NON-NLS-2$
        ad.setEnableAccessControl(false);
        ad.setId(new Long(0));
        v2.add(ad);
        for (AgentDescriptorEntity entity: v)
        {
        	String tenant = entity.getPlugin().getTenant().getName();
        	if (tenant.equals( Security.getCurrentTenantName()) ||
        			tenant.equals(Security.getMasterTenantName()))
        		v2.add(getAgentDescriptorEntityDao().toAgentDescriptor(entity));
        }
        return v2;
    }

    @Override
    protected String handleGetServerVersion() throws Exception {
    	List<ServerPluginModuleEntity> modules = getServerPluginModuleEntityDao().
    			findByType(ServerPluginModuleType.MODULE_SYNCSERVER);
    	for (ServerPluginModuleEntity module: modules) 
    	{
    		if (module.getPlugin().isEnabled())
    		{
    			return module.getPlugin().getVersion();
    		}
    	}
    	return ""; //$NON-NLS-1$
    }

    @Override
    protected void handleDeletePlugin(ServerPlugin plugin) throws Exception {
        ServerPluginEntityDao sped = getServerPluginEntityDao();
        ServerPluginModuleEntityDao spmed = getServerPluginModuleEntityDao();
        AgentDescriptorEntityDao aded = getAgentDescriptorEntityDao();
        ServerPluginEntity spe = sped.serverPluginToEntity(plugin);
        DefaultAttributeMappingEntityDao damed = getDefaultAttributeMappingEntityDao();
        DefaultObjectMappingPropertyEntityDao doped = getDefaultObjectMappingPropertyEntityDao();
        DefaultObjectMappingEntityDao doed = getDefaultObjectMappingEntityDao();

        ServerPluginEntity p = sped.load(plugin.getId());
        if (p.getTenant().getId().equals(Security.getCurrentTenantId()))
        {
    		audit(p, "R");
	        for (ServerPluginModuleEntity module: spe.getModules())
	        {
	        	for (AgentDescriptorEntity agent: module.getAgents())
	        	{
	        		for (DefaultObjectMappingEntity dom: agent.getDefaultObjectMappings())
	        		{
	        			for (DefaultAttributeMappingEntity dam: dom.getDefaultAttributeMappings())
	        				damed.remove(dam);
						for (DefaultObjectMappingPropertyEntity dop: dom.getProperties())
	        				doped.remove(dop);
						dom.getDefaultAttributeMappings().clear();
						dom.getProperties().clear();
						doed.remove(dom);
	        		}
	        		agent.getDefaultObjectMappings().clear();
	        		aded.remove(agent);
	        	}
	        	module.getAgents().clear();
	        	spmed.remove(module);
	        }
	        plugin.getModules().clear();
	        sped.remove(plugin.getId());
	        updateConfig();
        }
    }

    
    private void updateConfig () throws InternalErrorException
    {
    	Configuration config = getConfigurationService().findParameterByNameAndNetworkName("plugin.timestamp", null); //$NON-NLS-1$
    	if (config == null)
    	{
    		config = new Configuration();
    		config.setCode("plugin.timestamp"); //$NON-NLS-1$
    		config.setNetworkCode(null);
    		config.setDescription("Autogenerated value. Do not modify"); //$NON-NLS-1$
    		config.setValue(Long.toString(System.currentTimeMillis()));
    		getConfigurationService().create(config);
    	} 
    	else
    	{
    		config.setValue(Long.toString(System.currentTimeMillis()));
    		getConfigurationService().update(config);
    	}
    }

	public void audit(ServerPluginEntity entity, String action) {
		Audit auditoria = new Audit();
		auditoria.setAction(action); //$NON-NLS-1$
		auditoria.setAuthor(Security.getCurrentAccount());
		auditoria.setConfigurationParameter( entity.getName());
		auditoria.setObject("SC_SERPLU"); //$NON-NLS-1$
		AuditEntity auditoriaEntity = getAuditEntityDao().auditToEntity(
				auditoria);
		getAuditEntityDao().create(auditoriaEntity);
	}

	@Override
	protected void handleUpdatePlugin(byte[] b) throws Exception {
    	ServerPluginParser spp = new ServerPluginParser();
    	spp.parse(b);
    	if (spp.getPlugin() == null)
    		throw new InternalErrorException (Messages.getString("ServerPluginNameImpl.NotAPluginFile")); //$NON-NLS-1$
    	ServerPluginEntity plugin = getServerPluginEntityDao().findByName(spp.getPlugin().getName());
    	if (plugin == null)
    	{
    		deployPlugin(spp);
    	}
    	else 
    	{
    		int i = compareVersions (plugin.getVersion(), spp.getPlugin().getVersion());
    		if ( i < 0) 
    			deployPlugin(spp);
    	}
	}

	private int compareVersions(String version1, String version2) {
		String[] versionA = version1.split("[.-]*");
		String[] versionB = version2.split("[.-]*");
		for ( int i = 0; i <  versionB.length && i < versionA.length; i++)
		{
			if (versionB[i].equals(versionA[i]))
			{
				// Skip to next
			}
			else
			{
				Integer i1 = null;
				Integer i2 = null;
				try {
					i1 = Integer.parseInt(versionA[i]);
				} catch ( NumberFormatException e ) { }
				try {
					i2 = Integer.parseInt(versionB[i]);
				} catch ( NumberFormatException e ) { }
				if (i1 != null && i2 != null)
				{
					int c = i1.intValue() - i2.intValue();
					if ( c != 0 ) return c;
				}
				else if ( i1 == null)
					return -1;
				else if ( i2 == null)
					return +1;
				else
				{
					int c = versionA[i].compareToIgnoreCase(versionB[i]);
					if (c != 0)
						return c;
				}
			}
		}
		return versionA.length - versionB.length;
	}

	@Override
	protected void handleUpdatePlugin(ServerPlugin plugin) throws Exception {
		ServerPluginEntity spe = getServerPluginEntityDao().load(plugin.getId());
		if (spe.isEnabled() != plugin.isEnabled())
		{
			spe.setEnabled(plugin.isEnabled());
			getServerPluginEntityDao().update(spe);
		}
	}

	@Override
	protected Collection<AgentDescriptorWorkflow> handleFindAgentDescriptorWorkflows(AgentDescriptor agent) throws Exception {
		Collection<AgentDescriptorWorkflow> result = new LinkedList<>();
		AgentDescriptorEntity agentEntity = getAgentDescriptorEntityDao().load(agent.getId());
		if (agentEntity == null)
			return null;
		ServerPluginModuleEntity module = agentEntity.getModule();
		byte data[] = module.getContents();
		ByteArrayInputStream in = new ByteArrayInputStream(data);
		ZipInputStream zin = new ZipInputStream(in);
		ZipEntry entry;
		while ( ( entry = zin.getNextEntry() ) != null ) {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			int read;
			for ( read = zin.read(); read >= 0; read = zin.read()) {
				out.write(read);
			}
			String entryName = entry.getName();
			if (entryName.startsWith(agentEntity.getDescription()+"/") ||
					entryName.startsWith(agentEntity.getDescription()+"\\")) {
				if (entryName.endsWith(".svg")) {
					String action = entryName.substring(agentEntity.getDescription().length() + 1);
					action = action.substring(0, action.length()-4);
					result.add ( new AgentDescriptorWorkflow(action, out.toByteArray()) );
				}
			}
		}
		return result;
	}
}
