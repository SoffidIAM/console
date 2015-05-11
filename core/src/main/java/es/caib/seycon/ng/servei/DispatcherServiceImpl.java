// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.caib.seycon.ng.servei;

import com.soffid.iam.api.ScheduledTask;
import com.soffid.iam.model.AccessControlEntity;
import com.soffid.iam.model.AccountEntity;
import com.soffid.iam.model.AgentDescriptorEntity;
import com.soffid.iam.model.AttributeMappingEntity;
import com.soffid.iam.model.DefaultAttributeMappingEntity;
import com.soffid.iam.model.DefaultObjectMappingEntity;
import com.soffid.iam.model.DefaultObjectMappingPropertyEntity;
import com.soffid.iam.model.GroupEntity;
import com.soffid.iam.model.GroupEntityDao;
import com.soffid.iam.model.ObjectMappingEntity;
import com.soffid.iam.model.ObjectMappingPropertyEntity;
import com.soffid.iam.model.ReplicaDatabaseEntity;
import com.soffid.iam.model.ReplicaDatabaseEntityDao;
import com.soffid.iam.model.RoleEntity;
import com.soffid.iam.model.ServerEntity;
import com.soffid.iam.model.ServerEntityDao;
import com.soffid.iam.model.SystemEntity;
import com.soffid.iam.model.SystemGroupEntity;
import com.soffid.iam.model.TaskEntity;
import com.soffid.iam.model.UserEntity;
import com.soffid.iam.model.UserGroupEntity;
import com.soffid.iam.model.UserTypeEntity;
import com.soffid.iam.model.UserTypeEntityDao;
import com.soffid.iam.model.UserTypeSystemEntity;
import com.soffid.iam.service.SystemScheduledTasks;

import es.caib.seycon.ng.comu.AttributeMapping;
import es.caib.seycon.ng.comu.Configuracio;
import es.caib.seycon.ng.comu.ContenidorRol;
import es.caib.seycon.ng.comu.ControlAcces;
import es.caib.seycon.ng.comu.Dispatcher;
import es.caib.seycon.ng.comu.GrupDispatcher;
import es.caib.seycon.ng.comu.ObjectMapping;
import es.caib.seycon.ng.comu.ObjectMappingProperty;
import es.caib.seycon.ng.comu.ReplicaDatabase;
import es.caib.seycon.ng.comu.RolGrant;
import es.caib.seycon.ng.comu.Server;
import es.caib.seycon.ng.comu.ServerType;
import es.caib.seycon.ng.comu.Tasca;
import es.caib.seycon.ng.comu.TipusUsuariDispatcher;
import es.caib.seycon.ng.comu.Usuari;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.exception.SeyconAccessLocalException;
import es.caib.seycon.ng.exception.SeyconException;
import com.soffid.iam.model.Parameter;
import es.caib.seycon.ng.sync.engine.TaskHandler;
import es.caib.seycon.ng.sync.intf.DatabaseReplicaMgr;
import es.caib.seycon.ng.utils.AutoritzacionsUsuari;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import org.apache.axis.components.logger.LogFactory;
import org.hibernate.Hibernate;
import org.mortbay.log.Log;

/**
 * @see es.caib.seycon.ng.servei.DispatcherService
 */
public class DispatcherServiceImpl extends es.caib.seycon.ng.servei.DispatcherServiceBase {
	org.apache.commons.logging.Log log = LogFactory.getLog(getClass().getName());
    /**
     * @see es.caib.seycon.ng.servei.DispatcherService#create(es.caib.seycon.ng.comu.Dispatcher)
     */
    protected es.caib.seycon.ng.comu.Dispatcher handleCreate(
            es.caib.seycon.ng.comu.Dispatcher dispatcher) throws java.lang.Exception {
    	
        // Check dispatcher type
    	if (dispatcher.getNomCla().isEmpty())
    	{
    		throw new IllegalArgumentException(Messages.getString("DispatcherServiceImpl.AgentTypeRequired")); //$NON-NLS-1$
    	}
        
        // Check user domain
        if (dispatcher.getDominiUsuaris().isEmpty())
        {
        	throw new IllegalArgumentException(Messages.getString("DispatcherServiceImpl.UserDomainRequired")); //$NON-NLS-1$
        }
        
        // Check password domain
        if (dispatcher.getDominiContrasenyes().isEmpty())
        {
        	throw new IllegalArgumentException(Messages.getString("DispatcherServiceImpl.PasswordDomainRequired")); //$NON-NLS-1$
        }
        
        // Check user type
        if (dispatcher.getRelacioLaboral() == null)
        {
        	dispatcher.setRelacioLaboral(""); //$NON-NLS-1$
        }
        
		SystemEntity dispatchersSameCode = getSystemEntityDao().findByName(dispatcher.getCodi());
		if(dispatchersSameCode != null)
			throw new SeyconException(String.format(Messages.getString("DipatcherServiceImpl.CodeDispatcherExists"),  //$NON-NLS-1$
							dispatcher.getCodi())); 
        
        SystemEntity dispatcherEntity = getSystemEntityDao().dispatcherToEntity(dispatcher);
        dispatcherEntity.setMainSystem(getSystemEntityDao().loadAll().isEmpty());
        dispatcherEntity.setTimeStamp(new Date());
        getSystemEntityDao().create(dispatcherEntity);
        dispatcher.setId(dispatcherEntity.getId());
        
        updateAutomaticTasks (dispatcher, false);

        handleSetDefaultMappingsByDispatcher(dispatcher.getId());
        
        updateTipusAndGrups(dispatcher, dispatcherEntity);
        
        dispatcher = getSystemEntityDao().toDispatcher(dispatcherEntity);
        
        updateServers();
        
        return dispatcher;
    }

	private void updateAutomaticTasks (Dispatcher dispatcher, boolean remove) throws InternalErrorException
	{
		if (dispatcher.getUrl() == null || dispatcher.getUrl().isEmpty())
		{
			updateAutomaticTasks(SystemScheduledTasks.RECONCILE_DISPATCHER, 
					"Reconcile all accounts from %s", //$NON-NLS-1$
					dispatcher, true);
		
			updateAutomaticTasks(SystemScheduledTasks.AUTHORITATIVE_DATA_IMPORT, 
						"Import authoritative data from %s", //$NON-NLS-1$
						dispatcher, true);
		}
		else
		{
			if (dispatcher.isReadOnly() || dispatcher.isAuthoritative())
				updateAutomaticTasks(SystemScheduledTasks.RECONCILE_DISPATCHER, 
								"Reconcile all accounts from %s", //$NON-NLS-1$
								dispatcher, remove);
			else
				updateAutomaticTasks(SystemScheduledTasks.RECONCILE_DISPATCHER, 
						"Reconcile unmanaged accounts from %s", //$NON-NLS-1$
						dispatcher, remove);
			
			updateAutomaticTasks(SystemScheduledTasks.AUTHORITATIVE_DATA_IMPORT, 
							"Import authoritative data from %s", //$NON-NLS-1$
							dispatcher, remove || ! dispatcher.isAuthoritative());
		}
	}
	
	private void updateAutomaticTasks (String handler, String description,
					Dispatcher dispatcher, boolean remove) throws InternalErrorException
	{
		ScheduledTask task = getScheduledTaskService().
			findScheduledTaskByHandlerAndParams(
							handler, 
							dispatcher.getId().toString());
		if (task == null && ! remove)
		{
			task = new ScheduledTask();
			task.setHandlerName(handler);
			task.setParams(dispatcher.getId().toString());
			 task.setActive(false);
			 task.setDayOfWeekPattern("*"); //$NON-NLS-1$
			 task.setDayPattern("*"); //$NON-NLS-1$
			 task.setHoursPattern("0"); //$NON-NLS-1$
			 task.setMinutesPattern("0"); //$NON-NLS-1$
			 task.setMonthsPattern("*"); //$NON-NLS-1$
			 task.setName(String.format(description, dispatcher.getCodi()));
			 getScheduledTaskService().create(task);
		}
		else if (task != null && remove)
		{
			getScheduledTaskService().remove(task);
		}
		else if (task != null && ! remove)
		{
			task.setName(String.format(description, dispatcher.getCodi()));
			getScheduledTaskService().update(task);
		}
	}

	/**
     * @see es.caib.seycon.ng.servei.DispatcherService#update(es.caib.seycon.ng.comu.Dispatcher)
     */
    protected es.caib.seycon.ng.comu.Dispatcher handleUpdate(
            es.caib.seycon.ng.comu.Dispatcher dispatcher) throws java.lang.Exception {
        // Obtenim el anterior per comparar els grups i els tipus d'usuari
        SystemEntity entityOld = getSystemEntityDao().findByName(dispatcher.getCodi());

        // fem còpia dels antics per comparar
        Collection<UserTypeSystemEntity> tipusUsuariOld = new java.util.HashSet<com.soffid.iam.model.UserTypeSystemEntity>(entityOld.getUserType());
        HashSet<SystemGroupEntity> grupsOld = new HashSet<SystemGroupEntity>(entityOld.getSystemGroup());

        // Obtenim el nou entity
        SystemEntity entity = getSystemEntityDao().dispatcherToEntity(dispatcher);
        entity.setTimeStamp(new Date());

        updateAutomaticTasks (dispatcher, false);

        updateTipusAndGrups(dispatcher, entity);

        updateServers();

        return getSystemEntityDao().toDispatcher(entity);
    }

    private void updateTipusAndGrups(Dispatcher dispatcher, SystemEntity entity) throws InternalErrorException, Exception {
        updateTipus(dispatcher, entity);
        updateGrups(dispatcher, entity);
    }

    private void updateTipus(Dispatcher dispatcher, SystemEntity entity) throws InternalErrorException, Exception {
        UserTypeEntityDao tipusDao = getUserTypeEntityDao();
        AccountService accService = getAccountService();
        String tipus[] = dispatcher.getRelacioLaboral().split(","); //$NON-NLS-1$
        Collection<UserTypeEntity> tipusUsuariToGenerateAccounts = new LinkedList<UserTypeEntity>();
        for (int i = 0; i < tipus.length; i++) {
            String t = tipus[i].trim();
            boolean found = false;
            for (UserTypeSystemEntity td : entity.getUserType()) {
                if (td.getUserType().getName().equals(t)) {
                    found = true;
                    break;
                }
            }
            if (!found && t.length() > 0) {
                UserTypeEntity tu = tipusDao.findByName(t);
                if (tu == null) throw new InternalErrorException(String.format(Messages.getString("DispatcherServiceImpl.0"), t));
                UserTypeSystemEntity td = getUserTypeSystemEntityDao().newUserTypeSystemEntity();
                td.setSystem(entity);
                td.setUserType(tu);
                getUserTypeSystemEntityDao().create(td);
                entity.getUserType().add(td);
                tipusUsuariToGenerateAccounts.add(tu);
            }
        }
        for (Iterator<UserTypeSystemEntity> it = entity.getUserType().iterator(); it.hasNext(); ) {
            UserTypeSystemEntity td = it.next();
            boolean found = false;
            for (int i = 0; i < tipus.length; i++) {
                if (tipus[i].trim().equals(td.getUserType().getName())) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                tipusUsuariToGenerateAccounts.add(td.getUserType());
                getUserTypeSystemEntityDao().remove(td);
                it.remove();
                UsuariService usuService = getUsuariService();
                Collection<Usuari> usuaris = usuService.findUsuariByCriteri("", "", "", "", "", "", "", "", "", "", td.getUserType().getName(), "", "", "", "", "", "", "", false);
                accService = getAccountService();
                long l = usuaris.size();
                int i = 0;
                for (Usuari usuari : usuaris) {
                    i++;
                    if (i % 100 == 1) log.info("Updating user " + i + " of " + l);
                    generateUpdateUser(usuari.getCodi(), entity.getName());
                }
                log.info("Updated " + l + " users");
            }
        }
        for (UserTypeEntity tu : tipusUsuariToGenerateAccounts) {
            UsuariService usuService = getUsuariService();
            Collection<Usuari> usuaris = usuService.findUsuariByCriteri("", "", "", "", "", "", "", "", "", "", tu.getName(), "", "", "", "", "", "", "", false);
            accService = getAccountService();
            long l = usuaris.size();
            int i = 0;
            for (Usuari usuari : usuaris) {
                i++;
                if (i % 100 == 1) log.info("Updating user " + i + " of " + l);
                generateUpdateUser(usuari.getCodi(), entity.getName());
            }
            log.info("Updated " + l + " users");
        }
        if(tipusUsuariToGenerateAccounts != null && !tipusUsuariToGenerateAccounts.isEmpty())
        	if(dispatcher.getUrl() != null)
        		handlePropagateUsuarisDispatcher(dispatcher.getCodi());
    }

    private void generateUpdateUser ( String usuari, String dispatcher ) throws InternalErrorException
    {
    	TaskEntity tasque = getTaskEntityDao().newTaskEntity();
    	tasque.setTransaction(TaskHandler.UPDATE_USER);// Actualització de l'usuari a //$NON-NLS-1$
                                           // l'agent
    	tasque.setDate(new Timestamp(System.currentTimeMillis()));
    	tasque.setUser(usuari);
    	tasque.setSystemName(dispatcher); // Només es genera la tasca al
                                         // dispatcher actual
    	tasque.setStatus("P");// Posem com a pendent //$NON-NLS-1$
        getTaskEntityDao().createNoFlush(tasque);
    }
    private void updateGrups(Dispatcher dispatcher, SystemEntity entity) throws InternalErrorException, Exception {
        GroupEntityDao grupDao = getGroupEntityDao();
        AccountService accService = getAccountService();
        String grups[] = dispatcher.getGrups() == null ? new String[0]: dispatcher.getGrups().split(","); //$NON-NLS-1$
        	
        Collection<GroupEntity> groupsToGenerateAccounts = new HashSet<GroupEntity>();
        boolean emptyGrups = grups.length == 0 ||
        				grups.length == 1 && grups[0].length() == 0;
        if((emptyGrups && !entity.getSystemGroup().isEmpty()) || (!emptyGrups && entity.getSystemGroup().isEmpty())){
        	List<GroupEntity> tots = grupDao.loadAll();
        	for (GroupEntity g : tots) {
                groupsToGenerateAccounts.add(g);
            }
        }
        
        for (int i = 0; i < grups.length; i++) {
            String t = grups[i].trim();
            boolean found = false;
            for (SystemGroupEntity gd : entity.getSystemGroup()) {
                if (gd.getGroup().getName().equals(t)) {
                    found = true;
                    break;
                }
            }
            if (!found && t.length() > 0) {
                GroupEntity gr = grupDao.findByName(t);
                if (gr == null) throw new InternalErrorException(String.format(Messages.getString("DispatcherServiceImpl.1"), t));
                SystemGroupEntity gd = getSystemGroupEntityDao().newSystemGroupEntity();
                gd.setSystem(entity);
                gd.setGroup(gr);
                getSystemGroupEntityDao().create(gd);
                entity.getSystemGroup().add(gd);
                groupsToGenerateAccounts.add(gr);
            }
        }

        for (Iterator<SystemGroupEntity> it = entity.getSystemGroup().iterator(); it.hasNext(); ) {
            SystemGroupEntity gd = it.next();
            boolean found = false;
            for (int i = 0; i < grups.length; i++) {
                if (grups[i].trim().equals(gd.getGroup().getName())) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                groupsToGenerateAccounts.add(gd.getGroup());
                getSystemGroupEntityDao().remove(gd);
                it.remove();
                List<UserEntity> usuaris = getUserEntityDao().findUsersGroupAndSubgroupsByGroupCode(gd.getGroup().getName());
                accService = getAccountService();
                long l = usuaris.size();
                int i = 0;
                for (UserEntity usuari : usuaris) {
                    i++;
                    if (i % 100 == 1) log.info("Updating user " + i + " of " + l);
                    generateUpdateUser(usuari.getUserName(), entity.getName());
                }
                log.info("Updated " + l + " users");
            }
        }
        for (GroupEntity gr : groupsToGenerateAccounts) {
            List<UserEntity> usuaris = getUserEntityDao().findUsersGroupAndSubgroupsByGroupCode(gr.getName());
            long l = usuaris.size();
            int i = 0;
            for (UserEntity usuari : usuaris) {
                i++;
                if (i % 100 == 1) log.info("Updating user " + i + " of " + l);
                generateUpdateUser(usuari.getUserName(), entity.getName());
            }
            log.info("Updated " + l + " users");
        }
        if(groupsToGenerateAccounts != null && !groupsToGenerateAccounts.isEmpty())
        	 if(dispatcher.getUrl() != null)
             	handlePropagateUsuarisDispatcher(dispatcher.getCodi());
    }

	/**
	 * @see es.caib.seycon.ng.servei.DispatcherService#delete(es.caib.seycon.ng.comu.Dispatcher)
	 */
	protected void handleDelete (es.caib.seycon.ng.comu.Dispatcher dispatcher)
					throws java.lang.Exception
	{
		SystemEntity dispatcherEntity = getSystemEntityDao().findByName(dispatcher.getCodi());
		// Esborrem les relacions existents amb d'altres taules
		getSystemGroupEntityDao().remove(dispatcherEntity.getSystemGroup());
		getUserTypeSystemEntityDao().remove(dispatcherEntity.getUserType());
		getAccessControlEntityDao().remove(dispatcherEntity.getAccessControls());

		for (ObjectMappingEntity om : dispatcherEntity.getObjectMappings())
		{
			for (AttributeMappingEntity am : om.getAttributeMappings())
			{
				getAttributeMappingEntityDao().remove(am);
			}
			for (ObjectMappingPropertyEntity omp : om.getProperties())
			{
				getObjectMappingPropertyEntityDao().remove(omp);
			}
			getObjectMappingEntityDao().remove(om);
		}
		getSystemEntityDao().remove(dispatcherEntity);
        updateAutomaticTasks (dispatcher, true);
        updateServers();
	}

    protected Dispatcher handleFindDispatcherByCodi(String codi) throws Exception {
        SystemEntity dispatcherEntity = getSystemEntityDao().findByName(codi);
        if (dispatcherEntity != null) {
            Dispatcher dispatcher = getSystemEntityDao().toDispatcher(dispatcherEntity);
            return dispatcher;
        }
        return null;
    }

    protected Collection<Dispatcher> handleFindDispatchersByFiltre(String codi,
		String nomCla, String url, String basRol, String segur, Boolean actiu)
		throws Exception
	{
    	int limitResults = Integer.parseInt(System.getProperty("soffid.ui.maxrows")); //$NON-NLS-1$
    	
    	if (codi == null || codi.trim().compareTo("") == 0) { //$NON-NLS-1$
            codi = "%"; //$NON-NLS-1$
        }
    	
        if (nomCla == null || nomCla.trim().compareTo("") == 0) { //$NON-NLS-1$
            nomCla = "%"; //$NON-NLS-1$
        }
        
        if (url != null && (url.trim().compareTo("") == 0 || url.trim().compareTo("%") == 0)) { //$NON-NLS-1$ //$NON-NLS-2$
            url = null;
        }
        
        if (basRol != null
                && (basRol.trim().compareTo("") == 0 || basRol.trim().compareTo("%") == 0)) { //$NON-NLS-1$ //$NON-NLS-2$
            basRol = null;
        }
        
        if (segur != null && (segur.trim().compareTo("") == 0 || segur.trim().compareTo("%") == 0)) { //$NON-NLS-1$ //$NON-NLS-2$
            segur = null;
        }
        
        String esActiu = null;
        if (actiu != null) {
            esActiu = "S"; //$NON-NLS-1$
        }
        
        Collection dispatchers = getSystemEntityDao().findByFilter(codi, nomCla, url, basRol, segur, esActiu);
        if (dispatchers != null)
        {
        	// Check maximum number of results
        	if (dispatchers.size() > limitResults)
        	{
        		return getSystemEntityDao().toDispatcherList(dispatchers).subList(0, limitResults);
        	}
        	
            return getSystemEntityDao().toDispatcherList(dispatchers);
        }
        
        return new Vector();
    }

    protected ControlAcces handleCreate(ControlAcces controlAcces) throws Exception {
        if (AutoritzacionsUsuari.canCreateAccessControlAgent()) {
            AccessControlEntity entity = getAccessControlEntityDao().controlAccesToEntity(controlAcces);
            getAccessControlEntityDao().create(entity);
            controlAcces.setId(entity.getId());
            controlAcces = getAccessControlEntityDao().toControlAcces(entity);
            updateServers();
            return controlAcces;
        }
        throw new SeyconAccessLocalException("DispatcherService", "create (ControlAcces)", //$NON-NLS-1$ //$NON-NLS-2$
                "agent:accessControl:create/*", //$NON-NLS-1$
                Messages.getString("DispatcherServiceImpl.2")); //$NON-NLS-1$

    }

    protected ControlAcces handleUpdate(ControlAcces controlAcces) throws Exception {
        if (AutoritzacionsUsuari.canUpdateAccessControlAgent()) {
            AccessControlEntity entity = getAccessControlEntityDao().controlAccesToEntity(controlAcces);
            getAccessControlEntityDao().update(entity);
            controlAcces.setId(entity.getId());
            controlAcces = getAccessControlEntityDao().toControlAcces(entity);

            // Ací hem de crear la tasca de UpdateAccessControl
            Tasca updateAccessControl = new Tasca();
            updateAccessControl.setTransa("UpdateAccessControl");// Actualització //$NON-NLS-1$
                                                                 // de l'usuari
                                                                 // a l'agent
            updateAccessControl.setDataTasca(Calendar.getInstance());
            // nomAgent = getAgent().getCodi()
            updateAccessControl.setCoddis(controlAcces.getNomAgent()); // Només
                                                                       // es
                                                                       // genera
                                                                       // la
                                                                       // tasca
                                                                       // al
                                                                       // dispatcher
                                                                       // actual
            updateAccessControl.setStatus("P");// Posem com a pendent //$NON-NLS-1$
            TaskEntity tasca = getTaskEntityDao().tascaToEntity(updateAccessControl);
            getTaskEntityDao().create(tasca);

            updateServers();

            return controlAcces;
        }
        throw new SeyconAccessLocalException("DispatcherService", "update (ControlAcces)", //$NON-NLS-1$ //$NON-NLS-2$
                "agent:accessControl:update/*", //$NON-NLS-1$
                Messages.getString("DispatcherServiceImpl.3")); //$NON-NLS-1$
    }

    protected void handleDelete(ControlAcces controlAcces) throws Exception {
        AccessControlEntity entity = getAccessControlEntityDao().controlAccesToEntity(controlAcces);
        if (entity != null)
            getAccessControlEntityDao().remove(entity);

        updateServers();
    }

    protected Collection<ControlAcces> handleFindControlAccesByCodiAgent(String codiAgent)
            throws Exception {
        if (!"%".equals(codiAgent)) { //$NON-NLS-1$
            Collection control = getAccessControlEntityDao().findByAgentCode(codiAgent);
            return getAccessControlEntityDao().toControlAccesList(control);
        }
        return new Vector();
    }

    protected void handlePropagateUsuarisDispatcher(String codiAgent) throws Exception {
        // Verifiquem que l'agent siga actiu
        if (codiAgent == null || "".equals(codiAgent.trim())) //$NON-NLS-1$
            throw new SeyconException(Messages.getString("DispatcherServiceImpl.4")); //$NON-NLS-1$
        Dispatcher agent = findDispatcherByCodi(codiAgent);
        if (agent == null || agent.getUrl() == null)
            throw new SeyconException(
                    Messages.getString("DispatcherServiceImpl.5")); //$NON-NLS-1$

        // Obtenim tots els codis d'usuari:
        Collection<AccountEntity> col = getSystemEntityDao().findByName(codiAgent).getAccounts();
        // Creem les tasques per a cadascun dels usuaris
        for (Iterator it = col.iterator(); it.hasNext(); ) {
            AccountEntity ae = (AccountEntity) it.next();
            String codiUsuari = ae.getName();
            Tasca updateUser = new Tasca();
            updateUser.setTransa(TaskHandler.UPDATE_ACCOUNT);
            updateUser.setDataTasca(Calendar.getInstance());
            updateUser.setUsuari(codiUsuari);
            updateUser.setCoddis(codiAgent);
            updateUser.setStatus("P");
            TaskEntity tasca = getTaskEntityDao().tascaToEntity(updateUser);
            getTaskEntityDao().createNoFlush(tasca);
        }

    }

    protected void handlePropagateRolsDispatcher(String codiAgent) throws Exception {
        // Verifiquem que l'agent siga actiu
        if (codiAgent == null || "".equals(codiAgent.trim())) //$NON-NLS-1$
            throw new SeyconException(Messages.getString("DispatcherServiceImpl.4")); //$NON-NLS-1$
        
        SystemEntity system = getSystemEntityDao().findByName(codiAgent);
        if (system == null || system.getUrl() == null)
            throw new SeyconException(
                    Messages.getString("DispatcherServiceImpl.5")); //$NON-NLS-1$

        // Creem les tasques per a cadascun dels usuaris
        for (RoleEntity role: system.getRole()) {
            Tasca updateRole = new Tasca();
            updateRole.setTransa("UpdateRole");
            updateRole.setRole(role.getName());
            updateRole.setDataTasca(Calendar.getInstance());
            updateRole.setBd(codiAgent);
            updateRole.setCoddis(codiAgent);
            updateRole.setStatus("P");
            TaskEntity tasca = getTaskEntityDao().tascaToEntity(updateRole);
            getTaskEntityDao().createNoFlush(tasca);
        }

    }

    @Override
    protected TipusUsuariDispatcher handleCreate(TipusUsuariDispatcher tipusUsuari)
            throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected void handleDelete(TipusUsuariDispatcher tipusUsuari) throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    protected GrupDispatcher handleCreate(GrupDispatcher grupDispatcher) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected void handleDelete(GrupDispatcher grupDispatcher) throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    protected TipusUsuariDispatcher handleUpdate(TipusUsuariDispatcher tipusUsuari)
            throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected GrupDispatcher handleUpdate(GrupDispatcher grupDispatcher) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected Collection<ControlAcces> handleGetControlAcces(Dispatcher agent) throws Exception {
        Collection<AccessControlEntity> cace = getAccessControlEntityDao().findByAgentCode(agent.getCodi());
        return getAccessControlEntityDao().toControlAccesList(cace);
    }

    @Override
    protected Collection<GrupDispatcher> handleGetGrupsDispatcher(Dispatcher agent)
            throws Exception {
        Collection<SystemGroupEntity> grupsE = getSystemGroupEntityDao().findBySystem(agent.getCodi());
        return getSystemGroupEntityDao().toGrupDispatcherList(grupsE);
    }

    @Override
    protected Collection<TipusUsuariDispatcher> handleGetTipusUsuariDispatcher(Dispatcher agent)
            throws Exception {
        Collection<UserTypeSystemEntity> tipusUsuariE = getUserTypeSystemEntityDao().findBySystem(agent.getCodi());
        return getUserTypeSystemEntityDao().toTipusUsuariDispatcherList(tipusUsuariE);

    }

	@Override
	protected boolean handleIsUserAllowed(Dispatcher dispatcher, String user)
			throws Exception
	{
		SystemEntity de = getSystemEntityDao().load(dispatcher.getId());
		if (de == null)
			return false;
		UserEntity ue = getUserEntityDao().findByUserName(user);
		if (ue.getActive().equals("N"))
			return false;
		
        // Test user types
        boolean found = false;
        for (Iterator<UserTypeSystemEntity> it = de.getUserType().iterator(); !found && it.hasNext(); ) {
            UserTypeSystemEntity tu = it.next();
            if (tu.getUserType().getId().equals(ue.getUserType().getId())) found = true;
        }
        if (!found)
        	return false;

        // Test dispatcher groups
        if (!de.getSystemGroup().isEmpty()) {
            found = false;
            for (Iterator<SystemGroupEntity> it = de.getSystemGroup().iterator(); it.hasNext() && !found; ) {
                SystemGroupEntity gde = it.next();
                if (userBelongsToGroup(ue, gde.getGroup())) found = true;
            }
            if (!found)
            	return false;
        }

        // Test role-based condition
        if (de.getRoleBased().equals("S")) { //$NON-NLS-1$
        	Collection<RolGrant> grants = getAplicacioService().findEffectiveRolGrantByUser(ue.getId());
        	for (RolGrant grant : grants) {
                if (grant.getDispatcher().equals(de.getName())) return true;
            }
        	return false;
        }
        return true;

	}

    private boolean userBelongsToGroup(UserEntity userEntity, GroupEntity grup) {
        if (isChildGroup(userEntity.getPrimaryGroup(), grup))
            return true;
        for (Iterator<UserGroupEntity> it = userEntity.getSecondaryGroups().iterator(); it.hasNext(); ) {
            if (isChildGroup(it.next().getGroup(), grup)) return true;
        }
        return false;
    }

    private boolean isChildGroup(GroupEntity child, GroupEntity grup) {
        while (child != null) {
            if (child.getId().equals(grup.getId()))
                return true;
            else
                child = child.getParent();
        }
        return false;
    }

    @Override
	protected Collection<Dispatcher> handleFindAllActiveDispatchers()
			throws Exception
	{
		return getSystemEntityDao().toDispatcherList(getSystemEntityDao().findActives());
	}

	@Override
	protected boolean handleIsGroupAllowed(Dispatcher dispatcher, String group)
			throws Exception
	{
		SystemEntity de = getSystemEntityDao().load(dispatcher.getId());
		if (de == null)
			return false;

        // Test dispatcher groups
        if (de.getSystemGroup().isEmpty()) {
        	return true;
        } else {
            for (SystemGroupEntity gde : de.getSystemGroup()) {
                GroupEntity ge = gde.getGroup();
                do {
                    if (ge.getName().equals(group)) return true;
                    ge = ge.getParent();
                }                 while (ge != null);
            }
           	return false;
        }

	}

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.DispatcherServiceBase#handleFindAllDatabases()
	 */
	@Override
	protected Collection<ReplicaDatabase> handleFindAllDatabases () throws Exception
	{
		List<ReplicaDatabaseEntity> db = getReplicaDatabaseEntityDao().loadAll();
		return getReplicaDatabaseEntityDao().toReplicaDatabaseList(db);
	}

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.DispatcherServiceBase#handleUpdate(es.caib.seycon.ng.ReplicaDatabase)
	 */
	@Override
	protected ReplicaDatabase handleUpdate (ReplicaDatabase database) throws Exception
	{
		ReplicaDatabaseEntityDao dao = getReplicaDatabaseEntityDao();
		ReplicaDatabaseEntity entity = dao.replicaDatabaseToEntity(database);
		dao.update(entity);
		updateReplicaAgent(entity);
		return dao.toReplicaDatabase(entity);
	}

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.DispatcherServiceBase#handleCreate(es.caib.seycon.ng.ReplicaDatabase)
	 */
	@Override
	protected ReplicaDatabase handleCreate (ReplicaDatabase database) throws Exception
	{
		ReplicaDatabaseEntityDao dao = getReplicaDatabaseEntityDao();
		ReplicaDatabaseEntity entity = dao.replicaDatabaseToEntity(database);
		if (entity.getIdSeed() == null)
		{
    		long last = 0;
    		for (ReplicaDatabaseEntity rdbe : dao.loadAll())
    		{
    			if (rdbe.getIdSeed().longValue() >= last)
    				last = rdbe.getIdSeed().longValue();
    		}
    		entity.setIdSeed(new Long(last+1));
		}
		dao.create(entity);
		updateReplicaAgent(entity);
		updateReplicaParameter();
		return dao.toReplicaDatabase(entity);
	}

	/**
	 * @throws InternalErrorException 
	 * 
	 */
	private void updateReplicaParameter () throws InternalErrorException
	{
		String value ;
		if (getReplicaDatabaseEntityDao().loadAll().isEmpty())
			value = "false"; //$NON-NLS-1$
		else
			value = "true"; //$NON-NLS-1$
		ConfiguracioService configSvc = getConfiguracioService();
		Configuracio config = configSvc.findParametreByCodiAndCodiXarxa("soffid.replica.enabled", null); //$NON-NLS-1$
		if (config == null)
		{
			config = new Configuracio();
			config.setCodi("soffid.replica.enabled"); //$NON-NLS-1$
			config.setValor(value);
			config.setDescripcio("Enables Soffid replica mechanism"); //$NON-NLS-1$
			configSvc.create(config);
		} else {
			config.setValor(value);
			configSvc.update(config);
		}
	}

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.DispatcherServiceBase#handleFindAllServers()
	 */
	@Override
	protected Collection<Server> handleFindAllServers () throws Exception
	{
		ServerEntityDao dao = getServerEntityDao();
		List<ServerEntity> db = dao.loadAll();
		List<Server> servers = dao.toServerList(db);
		for (Server server: servers)
		{
			server.setAuth(null);
			server.setPk(null);
			server.setPublicKey(null);
		}
		return servers;
	}

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.DispatcherServiceBase#handleUpdate(es.caib.seycon.ng.comu.Server)
	 */
	@Override
	protected Server handleUpdate (Server server) throws Exception
	{
		ServerEntityDao dao = getServerEntityDao();
		ServerEntity entity = dao.load(server.getId());
		server.setAuth(entity.getAuth());
		server.setPk(entity.getPk());
		dao.serverToEntity(server, entity, true);
		dao.update(entity);
		server = dao.toServer(entity);
		server.setAuth(null);
		server.setPk(null);
		server.setPublicKey(null);
		updateSeyconServerList();
		return server;
	}

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.DispatcherServiceBase#handleDelete(es.caib.seycon.ng.comu.Server)
	 */
	@Override
	protected void handleDelete (Server server) throws Exception
	{
		getServerEntityDao().remove(server.getId());
		updateSeyconServerList();
	}

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.DispatcherServiceBase#handleDelete(es.caib.seycon.ng.ReplicaDatabase)
	 */
	@Override
	protected void handleDelete (ReplicaDatabase database) throws Exception
	{
		ReplicaDatabaseEntity dbEntity = getReplicaDatabaseEntityDao().load(database.getId());
		SystemEntity dispatcher = dbEntity.getSystem();
		getReplicaDatabaseEntityDao().remove(dbEntity);
		getSystemEntityDao().remove(dispatcher);
	}
	
	protected void updateSeyconServerList () throws InternalErrorException
	{
		StringBuffer serverList = null;
		List<ServerEntity> servers = new LinkedList(getServerEntityDao().loadAll());
		Collections.sort(servers, new Comparator<ServerEntity>()
		{

			public int compare (ServerEntity o1, ServerEntity o2)
			{
				return o1.getId().compareTo(o2.getId());
			}
		});
		for (ServerEntity server: servers)
		{
			if (server.getType() == ServerType.MASTERSERVER)
			{
    			if (serverList == null)
    				serverList = new StringBuffer();
    			else
    				serverList.append(","); //$NON-NLS-1$
    			serverList.append (server.getUrl());
			}
		}
		String serversString = serverList == null ? null: serverList.toString();
		ConfiguracioService configSvc = getConfiguracioService();
		Configuracio config = configSvc.findParametreByCodiAndCodiXarxa("seycon.server.list", null); //$NON-NLS-1$
		if (serversString == null)
		{
			if (config != null)
				configSvc.delete(config);
		}
		else if (config == null)
		{
			config = new Configuracio();
			config.setCodi("seycon.server.list"); //$NON-NLS-1$
			config.setValor(serversString);
			config.setDescripcio("Synchronization servers list"); //$NON-NLS-1$
			configSvc.create(config);
		} else {
			config.setValor(serversString);
			configSvc.update(config);
		}
	}

	protected void updateReplicaAgent (ReplicaDatabaseEntity db) throws InternalErrorException
	{
		if (db.getSystem() == null)
		{
			SystemEntity mainDispatcher = getSystemEntityDao().findSoffidSystem();
			SystemEntity dispatcher = getSystemEntityDao().newSystemEntity();
			dispatcher.setClassName("com.soffid.iam.addons.replica.agent.ReplicaAgent"); //$NON-NLS-1$
			dispatcher.setRoleBased("N"); //$NON-NLS-1$
			dispatcher.setName(db.getName());
			dispatcher.setReadOnly(false);
			dispatcher.setTrusted("S"); //$NON-NLS-1$
			dispatcher.setUrl("local"); //$NON-NLS-1$
			dispatcher.setUserDomain(mainDispatcher.getUserDomain());
			dispatcher.setPasswordDomain(mainDispatcher.getPasswordDomain());
			dispatcher.setEnableAccessControl("N"); //$NON-NLS-1$
			getSystemEntityDao().create(dispatcher);
			dispatcher.getReplicaDatabases().add(db);
			db.setSystem(dispatcher);
			getReplicaDatabaseEntityDao().update(db);
		}
		else
		{
			db.getSystem().setName(db.getName());
			getSystemEntityDao().update(db.getSystem());
		}
	}

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.DispatcherServiceBase#handleCreate(es.caib.seycon.ng.comu.Server)
	 */
	@Override
	protected Server handleCreate (Server server) throws Exception
	{
		ServerEntityDao dao = getServerEntityDao();
		ServerEntity serverEntity = dao.serverToEntity(server);
		dao.create(serverEntity);
		updateSeyconServerList();
		return dao.toServer(serverEntity);
	}

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.DispatcherServiceBase#handleFindReplicaDatabase(java.lang.Long)
	 */
	@Override
	protected ReplicaDatabase handleFindReplicaDatabase (Long id) throws Exception
	{
		ReplicaDatabaseEntity entity = getReplicaDatabaseEntityDao().load(id);
		if (entity == null)
			return  null;
		else
			return getReplicaDatabaseEntityDao().toReplicaDatabase(entity);
	}

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.DispatcherServiceBase#handleCreate(es.caib.seycon.ng.comu.AttributeMapping)
	 */
	@Override
	protected AttributeMapping handleCreate (AttributeMapping mapping) throws Exception
	{
		AttributeMappingEntity ame = getAttributeMappingEntityDao().attributeMappingToEntity(mapping);
		getAttributeMappingEntityDao().create(ame);
		if (ame.getObject() != null && ame.getObject().getSystem() != null)
		{
			ame.getObject().getSystem().setTimeStamp(new Date());
			getSystemEntityDao().update(ame.getObject().getSystem());
		}
        updateServers();
		return getAttributeMappingEntityDao().toAttributeMapping(ame);
	}

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.DispatcherServiceBase#handleUpdate(es.caib.seycon.ng.comu.AttributeMapping)
	 */
	@Override
	protected AttributeMapping handleUpdate (AttributeMapping mapping) throws Exception
	{
		AttributeMappingEntity ame = getAttributeMappingEntityDao().attributeMappingToEntity(mapping);
		getAttributeMappingEntityDao().update(ame);
		if (ame.getObject() != null && ame.getObject().getSystem() != null)
		{
			ame.getObject().getSystem().setTimeStamp(new Date());
			getSystemEntityDao().update(ame.getObject().getSystem());
		}
        updateServers();
		return getAttributeMappingEntityDao().toAttributeMapping(ame);
	}

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.DispatcherServiceBase#handleDelete(es.caib.seycon.ng.comu.AttributeMapping)
	 */
	@Override
	protected void handleDelete (AttributeMapping mapping) throws Exception
	{
		AttributeMappingEntity ame = getAttributeMappingEntityDao().attributeMappingToEntity(mapping);
		if (ame.getObject() != null && ame.getObject().getSystem() != null)
		{
			ame.getObject().getSystem().setTimeStamp(new Date());
			getSystemEntityDao().update(ame.getObject().getSystem());
		}
        updateServers();
		getAttributeMappingEntityDao().remove(ame);
	}

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.DispatcherServiceBase#handleFindAttributeMappingsByDispatcher(java.lang.Long)
	 */
	@Override
	protected Collection<AttributeMapping> handleFindAttributeMappingsByObject (
					Long objectId) throws Exception
	{
		ObjectMappingEntity obj = getObjectMappingEntityDao().load(objectId);
		List<AttributeMapping> list = getAttributeMappingEntityDao().toAttributeMappingList(obj.getAttributeMappings());
		Collections.sort(list, new Comparator<AttributeMapping>(){

			public int compare (AttributeMapping o1, AttributeMapping o2)
			{
				return o1.getId().compareTo(o2.getId());
			}
			
		});
		return list;
	}

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.DispatcherServiceBase#handleSetDefaultMappingsByDispatcher(java.lang.Long)
	 */
	@Override
	protected void handleSetDefaultMappingsByDispatcher (Long dispatcherId)
					throws Exception
	{
		SystemEntity de = getSystemEntityDao().load(dispatcherId);
		
		for (ObjectMappingEntity ome: de.getObjectMappings())
		{
			getObjectMappingPropertyEntityDao().remove(ome.getProperties());
			getAttributeMappingEntityDao().remove(ome.getAttributeMappings());
			getObjectMappingEntityDao().remove(ome);
		}
		
		de.getObjectMappings().clear();
		
		AgentDescriptorEntity ad = getAgentDescriptorEntityDao().findByClass(de.getClassName());
		if (ad != null)
		{
			for (DefaultObjectMappingEntity dom : ad.getDefaultObjectMappings()) {
                ObjectMappingEntity ome = getObjectMappingEntityDao().newObjectMappingEntity();
                ome.setCondition(dom.getCondition());
                ome.setSystem(de);
                ome.setSoffidObject(dom.getSoffidObject());
                ome.setSystemObject(dom.getSystemObject());
                getObjectMappingEntityDao().create(ome);
                for (DefaultObjectMappingPropertyEntity domp : dom.getProperties()) {
                    ObjectMappingPropertyEntity ompe = getObjectMappingPropertyEntityDao().newObjectMappingPropertyEntity();
                    ompe.setObject(ome);
                    ompe.setProperty(domp.getProperty());
                    ompe.setValue(domp.getValue());
                    getObjectMappingPropertyEntityDao().create(ompe);
                    ome.getProperties().add(ompe);
                }
                for (DefaultAttributeMappingEntity dam : dom.getDefaultAttributeMappings()) {
                    AttributeMappingEntity am = getAttributeMappingEntityDao().newAttributeMappingEntity();
                    am.setObject(ome);
                    am.setDirection(dam.getDirection());
                    am.setSoffidAttribute(dam.getSoffidAttribute());
                    am.setSystemAttribute(dam.getSystemAttribute());
                    getAttributeMappingEntityDao().create(am);
                    ome.getAttributeMappings().add(am);
                }
            }
		}
        updateServers();
	}

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.DispatcherServiceBase#handleCreate(es.caib.seycon.ng.comu.ObjectMapping)
	 */
	@Override
	protected ObjectMapping handleCreate (ObjectMapping om) throws Exception
	{
		ObjectMappingEntity ome = getObjectMappingEntityDao().objectMappingToEntity(om);
		getObjectMappingEntityDao().create(ome);
        ome.getSystem().setTimeStamp(new Date());
        if (Hibernate.isPropertyInitialized(ome.getSystem(), "objectMappings")) //$NON-NLS-1$
        {
        	ome.getSystem().getObjectMappings().add(ome);
        }
        getSystemEntityDao().update(ome.getSystem());
        updateServers();
		return getObjectMappingEntityDao().toObjectMapping(ome);
	}

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.DispatcherServiceBase#handleUpdate(es.caib.seycon.ng.comu.ObjectMapping)
	 */
	@Override
	protected ObjectMapping handleUpdate (ObjectMapping om) throws Exception
	{
		ObjectMappingEntity ome = getObjectMappingEntityDao().objectMappingToEntity(om);
		getObjectMappingEntityDao().update(ome);
        ome.getSystem().setTimeStamp(new Date());
        if (Hibernate.isPropertyInitialized(ome.getSystem(), "objectMappings")) //$NON-NLS-1$
        {
        	ome.getSystem().getObjectMappings().add(ome);
        }
        getSystemEntityDao().update(ome.getSystem());
        updateServers();
		return getObjectMappingEntityDao().toObjectMapping(ome);
	}

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.DispatcherServiceBase#handleDelete(es.caib.seycon.ng.comu.ObjectMapping)
	 */
	@Override
	protected void handleDelete (ObjectMapping om) throws Exception
	{
		ObjectMappingEntity ome = getObjectMappingEntityDao().objectMappingToEntity(om);
		for (ObjectMappingPropertyEntity ompe: ome.getProperties())
		{
			getObjectMappingPropertyEntityDao().remove(ompe);
		}

		for (AttributeMappingEntity ame: ome.getAttributeMappings())
		{
			getAttributeMappingEntityDao().remove(ame);
		}


		SystemEntity de = ome.getSystem();
		getObjectMappingEntityDao().remove(ome);
		
		de.setTimeStamp(new Date());
        if (Hibernate.isPropertyInitialized(ome.getSystem(), "objectMappings")) //$NON-NLS-1$
        {
        	de.getObjectMappings().remove(ome);
        }
        

        getSystemEntityDao().update(de);
        updateServers();
	}

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.DispatcherServiceBase#handleCreate(es.caib.seycon.ng.comu.ObjectMappingProperty)
	 */
	@Override
	protected ObjectMappingProperty handleCreate (ObjectMappingProperty omp)
					throws Exception
	{
		ObjectMappingPropertyEntity ome = getObjectMappingPropertyEntityDao().objectMappingPropertyToEntity(omp);
		getObjectMappingPropertyEntityDao().create(ome);
        ome.getObject().getSystem().setTimeStamp(new Date());
        if (Hibernate.isPropertyInitialized(ome.getObject(), "properties")) //$NON-NLS-1$
        {
        	ome.getObject().getProperties().add(ome);
        }
        getSystemEntityDao().update(ome.getObject().getSystem());
        updateServers();
		return getObjectMappingPropertyEntityDao().toObjectMappingProperty(ome);
	}

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.DispatcherServiceBase#handleUpdate(es.caib.seycon.ng.comu.ObjectMappingProperty)
	 */
	@Override
	protected ObjectMappingProperty handleUpdate (ObjectMappingProperty omp)
					throws Exception
	{
		ObjectMappingPropertyEntity ome = getObjectMappingPropertyEntityDao().objectMappingPropertyToEntity(omp);
		getObjectMappingPropertyEntityDao().update(ome);
        ome.getObject().getSystem().setTimeStamp(new Date());
        if (Hibernate.isPropertyInitialized(ome.getObject(), "properties")) //$NON-NLS-1$
        {
        	ome.getObject().getProperties().add(ome);
        }
        getSystemEntityDao().update(ome.getObject().getSystem());
        updateServers();
		return getObjectMappingPropertyEntityDao().toObjectMappingProperty(ome);
	}

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.DispatcherServiceBase#handleDelete(es.caib.seycon.ng.comu.ObjectMappingProperty)
	 */
	@Override
	protected void handleDelete (ObjectMappingProperty omp) throws Exception
	{
		ObjectMappingPropertyEntity ome = getObjectMappingPropertyEntityDao().objectMappingPropertyToEntity(omp);
        ome.getObject().getSystem().setTimeStamp(new Date());
        getSystemEntityDao().update(ome.getObject().getSystem());
        if (Hibernate.isPropertyInitialized(ome.getObject(), "properties")) //$NON-NLS-1$
        {
        	ome.getObject().getProperties().remove(ome);
        }
		getObjectMappingPropertyEntityDao().remove(ome);
        updateServers();
	}

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.DispatcherServiceBase#handleFindObjectMappingsByDispatcher(java.lang.Long)
	 */
	@Override
	protected Collection<ObjectMapping> handleFindObjectMappingsByDispatcher (
					Long dispatcherId) throws Exception
	{
		SystemEntity de = getSystemEntityDao().load(dispatcherId);
		if (de == null)
			return Collections.emptyList();
		else
			return getObjectMappingEntityDao().toObjectMappingList(de.getObjectMappings());
	}

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.DispatcherServiceBase#handleFindObjectMappingPropertesByObject(java.lang.Long)
	 */
	@Override
	protected Collection<ObjectMappingProperty> handleFindObjectMappingPropertiesByObject (
					Long objectId) throws Exception
	{
		ObjectMappingEntity ome = getObjectMappingEntityDao().load(objectId);
		if (ome == null)
			return Collections.emptyList();
		else
			return getObjectMappingPropertyEntityDao().toObjectMappingPropertyList(ome.getProperties());
	}
	
	private void updateServers () throws InternalErrorException
	{
		getSeyconServerService().updateDispatcherConfiguration();
	}

	@Override
	protected void handlePropagateDispatcherGroups(String codiAgent)
			throws Exception {
        // Verifiquem que l'agent siga actiu
        if (codiAgent == null || "".equals(codiAgent.trim())) //$NON-NLS-1$
            throw new SeyconException(Messages.getString("DispatcherServiceImpl.4")); //$NON-NLS-1$
        Dispatcher agent = findDispatcherByCodi(codiAgent);
        if (agent == null || agent.getUrl() == null)
            throw new SeyconException(
                    Messages.getString("DispatcherServiceImpl.5")); //$NON-NLS-1$

        // Obtenim tots els rols de l'agent:
        Collection col = getGroupEntityDao().loadAll(); 
        // col = new Vector(col);
        // Creem les tasques per a cadascun dels usuaris
        for (Iterator it = col.iterator(); it.hasNext(); ) {
            GroupEntity g = (GroupEntity) it.next();
            Tasca updateRole = new Tasca();
            updateRole.setTransa("UpdateGroup");
            updateRole.setGrup(g.getName());
            updateRole.setDataTasca(Calendar.getInstance());
            updateRole.setCoddis(codiAgent);
            updateRole.setStatus("P");
            TaskEntity tasca = getTaskEntityDao().tascaToEntity(updateRole);
            getTaskEntityDao().createNoFlush(tasca);
        }

	}
}
