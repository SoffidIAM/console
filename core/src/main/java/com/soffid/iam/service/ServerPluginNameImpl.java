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

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.soffid.iam.api.AgentDescriptor;
import com.soffid.iam.api.AttributeMapping;
import com.soffid.iam.api.Configuration;
import com.soffid.iam.api.ObjectMappingProperty;
import com.soffid.iam.api.ServerPlugin;
import com.soffid.iam.api.ServerPluginModule;
import com.soffid.iam.model.AgentDescriptorEntity;
import com.soffid.iam.model.AgentDescriptorEntityDao;
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

import es.caib.seycon.ng.comu.ServerPluginModuleType;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.servei.DuplicatedClassException;

/**
 * @see es.caib.seycon.ng.servei.ServerPluginServer
 */
public class ServerPluginNameImpl extends com.soffid.iam.service.ServerPluginNameBase {
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
    	// Test duplicated classes
    	testDuplicatedClasses (spp.getPlugin());
    	// Test duplicated syncserver
    	testDuplicatedSyncServer (spp.getPlugin());
    	//
    	ServerPluginEntity plugin = getServerPluginEntityDao().findByName(spp.getPlugin().getName());
    	if (plugin == null)
    	{
    		plugin = getServerPluginEntityDao().newServerPluginEntity();
    		plugin.setName(spp.getPlugin().getName());
    		plugin.setEnabled(true);
    		plugin.setVersion(translateVersion(spp.getPlugin().getVersion()));
    		getServerPluginEntityDao().create(plugin);
    	} 
    	else
    	{
    		plugin.setVersion(translateVersion(spp.getPlugin().getVersion()));
    		getServerPluginEntityDao().update(plugin);
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
	    			agentEntity.setPlugin(plugin);
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

	private void testDuplicatedClasses(ServerPlugin plugin) throws DuplicatedClassException
	{
		for (ServerPluginModule module: plugin.getModules())
		{
			if (module.getAgents() != null)
			{
				for ( AgentDescriptor agent: module.getAgents())
				{
					AgentDescriptorEntity ad2 = getAgentDescriptorEntityDao().findByClass(
	                    agent.getClassName());
					if (ad2 != null && ad2.getModule() == null)
					{
						// Migración de agente anterior
						getAgentDescriptorEntityDao().remove(ad2);
					}
					else if (ad2 != null &&
							ad2.getModule() != null && ad2.getModule().getPlugin() != null &&
							ad2.getModule().getPlugin().isEnabled() &&
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
        	ServerPlugin p = toServerPlugin(plugin);
        	res.add(p);
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
        if (status) {
	        ServerPlugin reloaded = toServerPlugin(entity);
	        testDuplicatedSyncServer(plugin);
	        testDuplicatedClasses(plugin);
        }
        entity.setEnabled(status);
        getServerPluginEntityDao().update(entity);
        updateConfig();
    }

    /**
     * @see es.caib.seycon.ng.servei.ServerPluginServer#getPluginAgentDescriptors(es.caib.seycon.ng.comu.ServerPlugin)
     */
    @SuppressWarnings(value = {"rawtypes"})
    protected java.util.Collection<AgentDescriptor> handleGetPluginAgentDescriptors(com.soffid.iam.api.ServerPlugin plugin) throws java.lang.Exception {
        Collection<ServerPluginEntity> agentsBasics = getServerPluginEntityDao().findAgentsBasicDataByServerPluginID(plugin.getId());
        Collection<AgentDescriptor> res = new LinkedList<AgentDescriptor>();
        if (agentsBasics != null)
            for (Iterator it = agentsBasics.iterator(); it.hasNext();) {
                Object[] obj = (Object[]) it.next();
                Boolean enable = (Boolean) obj[3];
                Boolean authoritative = (Boolean) obj[4];
                Boolean enableAttributeMapping = (Boolean) obj[5];
                AgentDescriptor ag = new AgentDescriptor();
                ag.setId((Long) obj[0]);
                ag.setDescription((String) obj[1]);
                ag.setClassName((String) obj[2]);
                ag.setEnableAccessControl(enable != null ? enable.booleanValue() : false);
                ag.setAuthoritativeSource(authoritative != null ? authoritative.booleanValue() : false);
                res.add(ag);
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
        AgentDescriptorEntity ade = getAgentDescriptorEntityDao().findByClass(className);
        return getAgentDescriptorEntityDao().toAgentDescriptor(ade);

    }

    /**
     * @see es.caib.seycon.ng.servei.ServerPluginServer#getAgentDescriptors()
     */
    protected java.util.Collection<AgentDescriptor> handleGetAgentDescriptors()
            throws java.lang.Exception {
        LinkedList<AgentDescriptorEntity> v = new LinkedList<AgentDescriptorEntity>();
        for (Iterator<ServerPluginEntity> itPlugin = getServerPluginEntityDao().loadAll()
                .iterator(); itPlugin.hasNext();) {
            ServerPluginEntity sp = (ServerPluginEntity) itPlugin.next();
            if (sp.isEnabled()) {
                v.addAll(sp.getAgents());
            }
        }
        return getAgentDescriptorEntityDao().toAgentDescriptorList(v);
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
        v2.addAll(getAgentDescriptorEntityDao().toAgentDescriptorList(v));
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
					doed.remove(dom);
        		}
        		aded.remove(agent);
        	}
        	spmed.remove(module);
        }
        sped.remove(plugin.getId());
        updateConfig();
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
}
