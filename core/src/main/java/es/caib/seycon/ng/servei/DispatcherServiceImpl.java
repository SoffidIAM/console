// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.caib.seycon.ng.servei;

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

import com.soffid.iam.api.ScheduledTask;
import com.soffid.iam.service.SystemScheduledTasks;

import es.caib.seycon.ng.comu.ReplicaDatabase;
import es.caib.seycon.ng.model.ReplicaDatabaseEntity;
import es.caib.seycon.ng.model.ReplicaDatabaseEntityDao;
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
import es.caib.seycon.ng.model.AccountEntity;
import es.caib.seycon.ng.model.AgentDescriptorEntity;
import es.caib.seycon.ng.model.AttributeMappingEntity;
import es.caib.seycon.ng.model.ControlAccessEntity;
import es.caib.seycon.ng.model.DefaultAttributeMappingEntity;
import es.caib.seycon.ng.model.DefaultObjectMappingEntity;
import es.caib.seycon.ng.model.DefaultObjectMappingPropertyEntity;
import es.caib.seycon.ng.model.DispatcherEntity;
import es.caib.seycon.ng.model.GrupDispatcherEntity;
import es.caib.seycon.ng.model.GrupEntity;
import es.caib.seycon.ng.model.GrupEntityDao;
import es.caib.seycon.ng.model.ObjectMappingEntity;
import es.caib.seycon.ng.model.ObjectMappingPropertyEntity;
import es.caib.seycon.ng.model.Parameter;
import es.caib.seycon.ng.model.RolEntity;
import es.caib.seycon.ng.model.ServeiEntity;
import es.caib.seycon.ng.model.ServerEntity;
import es.caib.seycon.ng.model.ServerEntityDao;
import es.caib.seycon.ng.model.TasqueEntity;
import es.caib.seycon.ng.model.TipusUsuariDispatcherEntity;
import es.caib.seycon.ng.model.TipusUsuariEntity;
import es.caib.seycon.ng.model.TipusUsuariEntityDao;
import es.caib.seycon.ng.model.UsuariEntity;
import es.caib.seycon.ng.model.UsuariGrupEntity;
import es.caib.seycon.ng.sync.engine.TaskHandler;
import es.caib.seycon.ng.sync.intf.DatabaseReplicaMgr;
import es.caib.seycon.ng.utils.AutoritzacionsUsuari;

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
        
		DispatcherEntity dispatchersSameCode = getDispatcherEntityDao().findByCodi(dispatcher.getCodi());
		if(dispatchersSameCode != null)
			throw new SeyconException(String.format(Messages.getString("DipatcherServiceImpl.CodeDispatcherExists"),  //$NON-NLS-1$
							dispatcher.getCodi())); 
        
        DispatcherEntity dispatcherEntity = getDispatcherEntityDao().dispatcherToEntity(dispatcher);
        dispatcherEntity.setMainDispatcher ( getDispatcherEntityDao().loadAll().isEmpty());
        dispatcherEntity.setTimeStamp(new Date());
        getDispatcherEntityDao().create(dispatcherEntity);
        dispatcher.setId(dispatcherEntity.getId());
        
        updateAutomaticTasks (dispatcher, false);

        handleSetDefaultMappingsByDispatcher(dispatcher.getId());
        
        updateTipusAndGrups(dispatcher, dispatcherEntity);
        
        dispatcher = getDispatcherEntityDao().toDispatcher(dispatcherEntity);
        
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
        DispatcherEntity entityOld = getDispatcherEntityDao().findByCodi(dispatcher.getCodi());

        // fem còpia dels antics per comparar
        Collection<TipusUsuariDispatcherEntity> tipusUsuariOld = new java.util.HashSet<es.caib.seycon.ng.model.TipusUsuariDispatcherEntity>(
                entityOld.getTipusUsuari());
        HashSet<GrupDispatcherEntity> grupsOld = new HashSet<GrupDispatcherEntity>(
                entityOld.getGrupDispatcher());

        // Obtenim el nou entity
        DispatcherEntity entity = getDispatcherEntityDao().dispatcherToEntity(dispatcher);
        entity.setTimeStamp(new Date());

        updateAutomaticTasks (dispatcher, false);

        updateTipusAndGrups(dispatcher, entity);

        updateServers();

        return getDispatcherEntityDao().toDispatcher(entity);
    }

    private void updateTipusAndGrups(Dispatcher dispatcher, DispatcherEntity entity) throws InternalErrorException, Exception {
        updateTipus(dispatcher, entity);
        updateGrups(dispatcher, entity);
    }

    private void updateTipus(Dispatcher dispatcher, DispatcherEntity entity) throws InternalErrorException, Exception {
        TipusUsuariEntityDao tipusDao = getTipusUsuariEntityDao ();
        AccountService accService = getAccountService();
        String tipus[] = dispatcher.getRelacioLaboral().split(","); //$NON-NLS-1$
        Collection<TipusUsuariEntity> tipusUsuariToGenerateAccounts = new LinkedList<TipusUsuariEntity>();
        for (int i = 0; i < tipus.length; i++) {
            String t = tipus[i].trim();
            boolean found = false;
            for (TipusUsuariDispatcherEntity td: entity.getTipusUsuari()) {
                if (td.getTipusUsuari().getCodi().equals(t))
                {
                    found = true;
                    break;
                }
            }
            if (! found && t.length() > 0) {
                TipusUsuariEntity tu = tipusDao.findByCodi(t);
                if (tu == null)
                    throw new InternalErrorException (String.format(Messages.getString("DispatcherServiceImpl.0"),t)); //$NON-NLS-1$
                TipusUsuariDispatcherEntity td = getTipusUsuariDispatcherEntityDao().newTipusUsuariDispatcherEntity();
                td.setDispatcher(entity);
                td.setTipusUsuari(tu);
                getTipusUsuariDispatcherEntityDao().create(td);
                entity.getTipusUsuari().add(td);
                tipusUsuariToGenerateAccounts.add(tu);
            }
        }
        for (Iterator<TipusUsuariDispatcherEntity> it=entity.getTipusUsuari().iterator(); it.hasNext();) {
        	TipusUsuariDispatcherEntity td = it.next();
            boolean found = false;
            for (int i = 0; i < tipus.length; i++) {
                if (tipus[i].trim().equals (td.getTipusUsuari().getCodi())) {
                    found = true;
                    break;
                }
            }
            if (! found)
            {
            	tipusUsuariToGenerateAccounts.add(td.getTipusUsuari());
                getTipusUsuariDispatcherEntityDao().remove(td);
                it.remove();
                //Aquí cridar handleGenerateUserAccounts
                UsuariService usuService = getUsuariService();
                Collection<Usuari> usuaris= usuService.findUsuariByCriteri("","","","","","","","","","",  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$
                				td.getTipusUsuari().getCodi(),"","","","","","","",false); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$
                accService = getAccountService();
                long l = usuaris.size();
                int i = 0;
                for(Usuari usuari: usuaris)
                {
                	i ++;
                	if (i % 100 == 1)
                		log.info ("Updating user "+i+" of "+l); //$NON-NLS-1$ //$NON-NLS-2$
                	generateUpdateUser (usuari.getCodi(), entity.getCodi());
                }
                log.info("Updated "+l+" users"); //$NON-NLS-1$ //$NON-NLS-2$
                //Aquí acaba 
            }
        }
        for (TipusUsuariEntity tu: tipusUsuariToGenerateAccounts)
        {
            //Comença
        	UsuariService usuService = getUsuariService();
            Collection<Usuari> usuaris= usuService.findUsuariByCriteri("","","","","","","","","","",  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$
            				tu.getCodi(),"","","","","","","",false); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$
            accService = getAccountService();
            long l = usuaris.size();
            int i = 0;
            for(Usuari usuari: usuaris)
            {
            	i ++;
            	if (i % 100 == 1)
            		log.info ("Updating user "+i+" of "+l); //$NON-NLS-1$ //$NON-NLS-2$
            	generateUpdateUser (usuari.getCodi(), entity.getCodi());
            }
            log.info("Updated "+l+" users"); //$NON-NLS-1$ //$NON-NLS-2$
            //Aquí acaba 
        }
        if(tipusUsuariToGenerateAccounts != null && !tipusUsuariToGenerateAccounts.isEmpty())
        	if(dispatcher.getUrl() != null)
        		handlePropagateUsuarisDispatcher(dispatcher.getCodi());
    }

    private void generateUpdateUser ( String usuari, String dispatcher ) throws InternalErrorException
    {
    	TasqueEntity tasque = getTasqueEntityDao().newTasqueEntity();
    	tasque.setTransa(TaskHandler.UPDATE_USER);// Actualització de l'usuari a //$NON-NLS-1$
                                           // l'agent
    	tasque.setData(new Timestamp(System.currentTimeMillis()));
    	tasque.setUsuari(usuari);
    	tasque.setCoddis(dispatcher); // Només es genera la tasca al
                                         // dispatcher actual
    	tasque.setStatus("P");// Posem com a pendent //$NON-NLS-1$
        getTasqueEntityDao().createNoFlush(tasque);
    }
    private void updateGrups(Dispatcher dispatcher, DispatcherEntity entity) throws InternalErrorException, Exception {
        GrupEntityDao grupDao = getGrupEntityDao ();
        AccountService accService = getAccountService();
        String grups[] = dispatcher.getGrups() == null ? new String[0]: dispatcher.getGrups().split(","); //$NON-NLS-1$
        	
        Collection<GrupEntity> groupsToGenerateAccounts = new HashSet<GrupEntity>();
        boolean emptyGrups = grups.length == 0 ||
        				grups.length == 1 && grups[0].length() == 0;
        if((emptyGrups && !entity.getGrupDispatcher().isEmpty()) || (! emptyGrups && entity.getGrupDispatcher().isEmpty())){
        	List<GrupEntity> tots = grupDao.loadAll();
        	for(GrupEntity g: tots)
        	{
        		groupsToGenerateAccounts.add(g);
        	}
        }
        
        for (int i = 0; i < grups.length; i++) {
            String t = grups[i].trim();
            boolean found = false;
            for (GrupDispatcherEntity gd: entity.getGrupDispatcher()) {
                if (gd.getGrup().getCodi().equals(t))
                {
                    found = true;
                    break;
                }
            }
            if (! found && t.length() > 0) {
                GrupEntity gr = grupDao.findByCodi(t);
                if (gr == null)
                    throw new InternalErrorException (String.format(Messages.getString("DispatcherServiceImpl.1"),t)); //$NON-NLS-1$
                GrupDispatcherEntity gd = getGrupDispatcherEntityDao().newGrupDispatcherEntity(); 
                gd.setDispatcher(entity);
                gd.setGrup(gr);
                getGrupDispatcherEntityDao().create(gd);
                entity.getGrupDispatcher().add(gd);
                groupsToGenerateAccounts.add(gr);
            }	
        }

        for (Iterator<GrupDispatcherEntity> it=entity.getGrupDispatcher().iterator(); it.hasNext();) {
        	GrupDispatcherEntity gd = it.next();
            boolean found = false;
            for (int i = 0; i < grups.length; i++) {
                if (grups[i].trim().equals (gd.getGrup().getCodi())) {
                    found = true;
                    break;
                }
            }
            if (! found)
            {
            	groupsToGenerateAccounts.add(gd.getGrup());
                getGrupDispatcherEntityDao().remove(gd);
                it.remove();
                //Comença
            	List<UsuariEntity> usuaris = getUsuariEntityDao().findUsuarisGrupISubgrupsByCodiGrup(gd.getGrup().getCodi());

            	accService = getAccountService();

                long l = usuaris.size();
                int i = 0;
                for(UsuariEntity usuari: usuaris)
                {
                	i ++;
                	if (i % 100 == 1)
                		log.info ("Updating user "+i+" of "+l); //$NON-NLS-1$ //$NON-NLS-2$
                	generateUpdateUser (usuari.getCodi(), entity.getCodi());
                }
                log.info("Updated "+l+" users"); //$NON-NLS-1$ //$NON-NLS-2$
                //Aquí acaba
            }
        }
        for (GrupEntity gr: groupsToGenerateAccounts)
        {
            //Comença
            List<UsuariEntity> usuaris = getUsuariEntityDao().findUsuarisGrupISubgrupsByCodiGrup(gr.getCodi());
            long l = usuaris.size();
            int i = 0;
            for(UsuariEntity usuari: usuaris)
            {
            	i ++;
            	if (i % 100 == 1)
            		log.info ("Updating user "+i+" of "+l); //$NON-NLS-1$ //$NON-NLS-2$
            	generateUpdateUser (usuari.getCodi(), entity.getCodi());
            }
            log.info("Updated "+l+" users"); //$NON-NLS-1$ //$NON-NLS-2$
            //Aquí cridar handleGenerateUserAccounts
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
		DispatcherEntity dispatcherEntity = getDispatcherEntityDao().findByCodi(
						dispatcher.getCodi());
		// Esborrem les relacions existents amb d'altres taules
		getGrupDispatcherEntityDao().remove(dispatcherEntity.getGrupDispatcher());
		getTipusUsuariDispatcherEntityDao().remove(dispatcherEntity.getTipusUsuari());
		getControlAccessEntityDao().remove(dispatcherEntity.getControlAccess());

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
		getDispatcherEntityDao().remove(dispatcherEntity);
        updateAutomaticTasks (dispatcher, true);
        updateServers();
	}

    protected Dispatcher handleFindDispatcherByCodi(String codi) throws Exception {
        DispatcherEntity dispatcherEntity = getDispatcherEntityDao().findByCodi(codi);
        if (dispatcherEntity != null) {
            Dispatcher dispatcher = getDispatcherEntityDao().toDispatcher(dispatcherEntity);
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
        
        Collection dispatchers = getDispatcherEntityDao()
                .query(
                        "from es.caib.seycon.ng.model.DispatcherEntity " //$NON-NLS-1$
                                + "where (:codi is null or upper(codi) like upper(:codi)) and (:nomCla is null or upper(nomCla) like upper(:nomCla)) " //$NON-NLS-1$
                                + "and (:url is null or upper(url) like upper(:url)) and (:basRol is null or upper(basRol) = upper(:basRol)) and " //$NON-NLS-1$
                                + "(:segur is null or segur = :segur) and (:actiu is null or url is not null) order by codi", //$NON-NLS-1$
                    new Parameter[] {
                        new Parameter ("codi", codi), //$NON-NLS-1$
                        new Parameter ("nomCla", nomCla), //$NON-NLS-1$
                        new Parameter ("url", url), //$NON-NLS-1$
                        new Parameter ("basRol", basRol), //$NON-NLS-1$
                        new Parameter ("segur", segur), //$NON-NLS-1$
                        new Parameter ("actiu", esActiu) //$NON-NLS-1$
        });
        if (dispatchers != null)
        {
        	// Check maximum number of results
        	if (dispatchers.size() > limitResults)
        	{
        		return getDispatcherEntityDao().toDispatcherList(dispatchers)
					.subList(0, limitResults);
        	}
        	
            return getDispatcherEntityDao().toDispatcherList(dispatchers);
        }
        
        return new Vector();
    }

    protected ControlAcces handleCreate(ControlAcces controlAcces) throws Exception {
        if (AutoritzacionsUsuari.canCreateAccessControlAgent()) {
            ControlAccessEntity entity = getControlAccessEntityDao().controlAccesToEntity(
                    controlAcces);
            getControlAccessEntityDao().create(entity);
            controlAcces.setId(entity.getId());
            controlAcces = getControlAccessEntityDao().toControlAcces(entity);
            updateServers();
            return controlAcces;
        }
        throw new SeyconAccessLocalException("DispatcherService", "create (ControlAcces)", //$NON-NLS-1$ //$NON-NLS-2$
                "agent:accessControl:create/*", //$NON-NLS-1$
                Messages.getString("DispatcherServiceImpl.2")); //$NON-NLS-1$

    }

    protected ControlAcces handleUpdate(ControlAcces controlAcces) throws Exception {
        if (AutoritzacionsUsuari.canUpdateAccessControlAgent()) {
            ControlAccessEntity entity = getControlAccessEntityDao().controlAccesToEntity(
                    controlAcces);
            getControlAccessEntityDao().update(entity);
            controlAcces.setId(entity.getId());
            controlAcces = getControlAccessEntityDao().toControlAcces(entity);

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
            TasqueEntity tasca = getTasqueEntityDao().tascaToEntity(updateAccessControl);
            getTasqueEntityDao().create(tasca);

            updateServers();

            return controlAcces;
        }
        throw new SeyconAccessLocalException("DispatcherService", "update (ControlAcces)", //$NON-NLS-1$ //$NON-NLS-2$
                "agent:accessControl:update/*", //$NON-NLS-1$
                Messages.getString("DispatcherServiceImpl.3")); //$NON-NLS-1$
    }

    protected void handleDelete(ControlAcces controlAcces) throws Exception {
        ControlAccessEntity entity = getControlAccessEntityDao().controlAccesToEntity(controlAcces);
        if (entity != null)
            getControlAccessEntityDao().remove(entity);

        updateServers();
    }

    protected Collection<ControlAcces> handleFindControlAccesByCodiAgent(String codiAgent)
            throws Exception {
        if (!"%".equals(codiAgent)) { //$NON-NLS-1$
            Collection control = getControlAccessEntityDao().findByCodiAgent(codiAgent);
            return getControlAccessEntityDao().toControlAccesList(control);
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
        Collection<AccountEntity> col = getDispatcherEntityDao().findByCodi(codiAgent).getAccounts();
        /*Collection col = getUsuariEntityDao().query(
                "select usuari.codi from es.caib.seycon.ng.model.UsuariEntity usuari", //$NON-NLS-1$
                new Parameter[] {});*/
        // col = new Vector(col);
        // Creem les tasques per a cadascun dels usuaris
        for (Iterator it = col.iterator(); it.hasNext();) {
        	AccountEntity ae = (AccountEntity) it.next();
        	String codiUsuari = ae.getName();
            //String codiUsuari = (String) it.next();
            Tasca updateUser = new Tasca();
            updateUser.setTransa(TaskHandler.UPDATE_ACCOUNT);// Actualització de l'usuari a //$NON-NLS-1$
                                               // l'agent
            updateUser.setDataTasca(Calendar.getInstance());
            updateUser.setUsuari(codiUsuari);
            updateUser.setCoddis(codiAgent); // Només es genera la tasca al
                                             // dispatcher actual
            updateUser.setStatus("P");// Posem com a pendent //$NON-NLS-1$
            TasqueEntity tasca = getTasqueEntityDao().tascaToEntity(updateUser);
            getTasqueEntityDao().createNoFlush(tasca);
        }

    }

    protected void handlePropagateRolsDispatcher(String codiAgent) throws Exception {
        // Verifiquem que l'agent siga actiu
        if (codiAgent == null || "".equals(codiAgent.trim())) //$NON-NLS-1$
            throw new SeyconException(Messages.getString("DispatcherServiceImpl.4")); //$NON-NLS-1$
        Dispatcher agent = findDispatcherByCodi(codiAgent);
        if (agent == null || agent.getUrl() == null)
            throw new SeyconException(
                    Messages.getString("DispatcherServiceImpl.5")); //$NON-NLS-1$

        // Obtenim tots els rols de l'agent:
        Collection col = getRolEntityDao()
                .query("select distinct rol.nom from es.caib.seycon.ng.model.RolEntity rol " //$NON-NLS-1$
                        + " left join rol.baseDeDades baseDeDades where baseDeDades.codi=:codiAgent", //$NON-NLS-1$
                        new Parameter[] { new Parameter("codiAgent", codiAgent) }); //$NON-NLS-1$
        // col = new Vector(col);
        // Creem les tasques per a cadascun dels usuaris
        for (Iterator it = col.iterator(); it.hasNext();) {
            String nomRole = (String) it.next();
            Tasca updateRole = new Tasca();
            updateRole.setTransa("UpdateRole");// Actualització de l'usuari a //$NON-NLS-1$
                                               // l'agent
            updateRole.setRole(nomRole);
            updateRole.setDataTasca(Calendar.getInstance());
            updateRole.setBd(codiAgent);
            updateRole.setCoddis(codiAgent); // Només es genera la tasca al
                                             // dispatcher actual
            updateRole.setStatus("P");// Posem com a pendent //$NON-NLS-1$
            TasqueEntity tasca = getTasqueEntityDao().tascaToEntity(updateRole);
            getTasqueEntityDao().createNoFlush(tasca);
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
        Collection<ControlAccessEntity> cace = getControlAccessEntityDao().findByCodiAgent(
                agent.getCodi());
        return getControlAccessEntityDao().toControlAccesList(cace);
    }

    @Override
    protected Collection<GrupDispatcher> handleGetGrupsDispatcher(Dispatcher agent)
            throws Exception {
        Collection<GrupDispatcherEntity> grupsE = getGrupDispatcherEntityDao().findByCodiAgent(
                agent.getCodi());
        return getGrupDispatcherEntityDao().toGrupDispatcherList(grupsE);
    }

    @Override
    protected Collection<TipusUsuariDispatcher> handleGetTipusUsuariDispatcher(Dispatcher agent)
            throws Exception {
        Collection<TipusUsuariDispatcherEntity> tipusUsuariE = getTipusUsuariDispatcherEntityDao()
                .findByCodiAgent(agent.getCodi());
        return getTipusUsuariDispatcherEntityDao().toTipusUsuariDispatcherList(tipusUsuariE);

    }

	@Override
	protected boolean handleIsUserAllowed(Dispatcher dispatcher, String user)
			throws Exception
	{
		DispatcherEntity de = getDispatcherEntityDao().load(dispatcher.getId());
		if (de == null)
			return false;
		UsuariEntity ue = getUsuariEntityDao().findByCodi(user);
		if (ue.getActiu().equals("N"))
			return false;
		
		if (de.getManualAccountCreation() != null && de.getManualAccountCreation())
			return true;
		
        // Test user types
        boolean found = false;
        for (Iterator<TipusUsuariDispatcherEntity> it = de.getTipusUsuari()
                .iterator(); !found && it.hasNext();) {
            TipusUsuariDispatcherEntity tu = it.next();
            if (tu.getTipusUsuari().getId().equals(ue.getTipusUsuari().getId()))
                found = true;
        }
        if (!found)
        	return false;

        // Test dispatcher groups
        if (!de.getGrupDispatcher().isEmpty()) {
            found = false;
            for (Iterator<GrupDispatcherEntity> it = de.getGrupDispatcher()
                    .iterator(); it.hasNext() && !found;) {
                GrupDispatcherEntity gde = it.next();
                // Verify if user belongs to such a group
                if (userBelongsToGroup(ue, gde.getGrup()))
                    found = true;
            }
            if (!found)
            	return false;
        }

        // Test role-based condition
        if (de.getBasRol().equals("S")) { //$NON-NLS-1$
        	Collection<RolGrant> grants = getAplicacioService().findEffectiveRolGrantByUser(ue.getId());
        	for (RolGrant grant: grants)
        	{
        		if (grant.getDispatcher().equals(de.getCodi()))
        			return true;
        	}
        	return false;
        }
        return true;

	}

    private boolean userBelongsToGroup(UsuariEntity userEntity, GrupEntity grup) {
        if (isChildGroup(userEntity.getGrupPrimari(), grup))
            return true;
        for (Iterator<UsuariGrupEntity> it = userEntity.getGrupsSecundaris().iterator(); it
                .hasNext();) {
            if (isChildGroup(it.next().getGrup(), grup))
                return true;
        }
        return false;
    }

    private boolean isChildGroup(GrupEntity child, GrupEntity grup) {
        while (child != null) {
            if (child.getId().equals(grup.getId()))
                return true;
            else
                child = child.getPare();
        }
        return false;
    }

    @Override
	protected Collection<Dispatcher> handleFindAllActiveDispatchers()
			throws Exception
	{
		return getDispatcherEntityDao().toDispatcherList(
				getDispatcherEntityDao().findActius());
	}

	@Override
	protected boolean handleIsGroupAllowed(Dispatcher dispatcher, String group)
			throws Exception
	{
		DispatcherEntity de = getDispatcherEntityDao().load(dispatcher.getId());
		if (de == null)
			return false;

        // Test dispatcher groups
        if (de.getGrupDispatcher().isEmpty()) {
        	return true;
        } else {
            for (GrupDispatcherEntity gde: de.getGrupDispatcher())
            {
            	GrupEntity ge = gde.getGrup();
            	do
            	{
                	if (ge.getCodi().equals(group))
                		return true;
                	ge = ge.getPare();
            	} while (ge != null);
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
		DispatcherEntity dispatcher = dbEntity.getDispatcher();
		getReplicaDatabaseEntityDao().remove(dbEntity);
		getDispatcherEntityDao().remove(dispatcher);
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
		if (db.getDispatcher() == null)
		{
			DispatcherEntity mainDispatcher = getDispatcherEntityDao().findSoffidDispatcher();
			DispatcherEntity dispatcher = getDispatcherEntityDao().newDispatcherEntity();
			dispatcher.setNomCla("com.soffid.iam.addons.replica.agent.ReplicaAgent"); //$NON-NLS-1$
			dispatcher.setBasRol("N"); //$NON-NLS-1$
			dispatcher.setCodi(db.getName());
			dispatcher.setReadOnly(false);
			dispatcher.setSegur("S"); //$NON-NLS-1$
			dispatcher.setUrl("local"); //$NON-NLS-1$
			dispatcher.setDominiUsuari(mainDispatcher.getDominiUsuari());
			dispatcher.setDomini(mainDispatcher.getDomini());
			dispatcher.setControlAcces("N"); //$NON-NLS-1$
			getDispatcherEntityDao().create(dispatcher);
			dispatcher.getReplicaDatabases().add(db);
			db.setDispatcher( dispatcher );
			getReplicaDatabaseEntityDao().update(db);
		}
		else
		{
			db.getDispatcher().setCodi(db.getName());
			getDispatcherEntityDao().update(db.getDispatcher());
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
		if (ame.getObject() != null && ame.getObject().getDispatcher() != null)
		{
			ame.getObject().getDispatcher().setTimeStamp(new Date());
			getDispatcherEntityDao().update(ame.getObject().getDispatcher());
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
		if (ame.getObject() != null && ame.getObject().getDispatcher() != null)
		{
			ame.getObject().getDispatcher().setTimeStamp(new Date());
			getDispatcherEntityDao().update(ame.getObject().getDispatcher());
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
		if (ame.getObject() != null && ame.getObject().getDispatcher() != null)
		{
			ame.getObject().getDispatcher().setTimeStamp(new Date());
			getDispatcherEntityDao().update(ame.getObject().getDispatcher());
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
		DispatcherEntity de = getDispatcherEntityDao().load(dispatcherId);
		
		for (ObjectMappingEntity ome: de.getObjectMappings())
		{
			getObjectMappingPropertyEntityDao().remove(ome.getProperties());
			getAttributeMappingEntityDao().remove(ome.getAttributeMappings());
			getObjectMappingEntityDao().remove(ome);
		}
		
		de.getObjectMappings().clear();
		
		AgentDescriptorEntity ad = getAgentDescriptorEntityDao().findByClass(de.getNomCla());
		if (ad != null)
		{
			for (DefaultObjectMappingEntity dom: ad.getDefaultObjectMappings())
			{
				ObjectMappingEntity ome = getObjectMappingEntityDao().newObjectMappingEntity();
				ome.setCondition(dom.getCondition());
				ome.setDispatcher(de);
				ome.setSoffidObject(dom.getSoffidObject());
				ome.setSystemObject(dom.getSystemObject());
				getObjectMappingEntityDao().create(ome);
				for (DefaultObjectMappingPropertyEntity domp: dom.getProperties())
				{
					ObjectMappingPropertyEntity ompe = getObjectMappingPropertyEntityDao().newObjectMappingPropertyEntity();
					ompe.setObject(ome);
					ompe.setProperty(domp.getProperty());
					ompe.setValue(domp.getValue());
					getObjectMappingPropertyEntityDao().create(ompe);
					ome.getProperties().add(ompe);
				}
				for (DefaultAttributeMappingEntity dam: dom.getDefaultAttributeMappings())
				{
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
        ome.getDispatcher().setTimeStamp(new Date());
        if (Hibernate.isPropertyInitialized(ome.getDispatcher(), "objectMappings")) //$NON-NLS-1$
        {
        	ome.getDispatcher().getObjectMappings().add(ome);
        }
        getDispatcherEntityDao().update(ome.getDispatcher());
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
        ome.getDispatcher().setTimeStamp(new Date());
        if (Hibernate.isPropertyInitialized(ome.getDispatcher(), "objectMappings")) //$NON-NLS-1$
        {
        	ome.getDispatcher().getObjectMappings().add(ome);
        }
        getDispatcherEntityDao().update(ome.getDispatcher());
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


		DispatcherEntity de = ome.getDispatcher();
		getObjectMappingEntityDao().remove(ome);
		
		de.setTimeStamp(new Date());
        if (Hibernate.isPropertyInitialized(ome.getDispatcher(), "objectMappings")) //$NON-NLS-1$
        {
        	de.getObjectMappings().remove(ome);
        }
        

        getDispatcherEntityDao().update(de);
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
        ome.getObject().getDispatcher().setTimeStamp(new Date());
        if (Hibernate.isPropertyInitialized(ome.getObject(), "properties")) //$NON-NLS-1$
        {
        	ome.getObject().getProperties().add(ome);
        }
        getDispatcherEntityDao().update(ome.getObject().getDispatcher());
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
        ome.getObject().getDispatcher().setTimeStamp(new Date());
        if (Hibernate.isPropertyInitialized(ome.getObject(), "properties")) //$NON-NLS-1$
        {
        	ome.getObject().getProperties().add(ome);
        }
        getDispatcherEntityDao().update(ome.getObject().getDispatcher());
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
        ome.getObject().getDispatcher().setTimeStamp(new Date());
        getDispatcherEntityDao().update(ome.getObject().getDispatcher());
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
		DispatcherEntity de = getDispatcherEntityDao().load(dispatcherId);
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
        Collection col = getGrupEntityDao().loadAll(); 
        // col = new Vector(col);
        // Creem les tasques per a cadascun dels usuaris
        for (Iterator it = col.iterator(); it.hasNext();) {
            GrupEntity g = (GrupEntity) it.next();
            Tasca updateRole = new Tasca();
            updateRole.setTransa("UpdateGroup");// Actualització de l'usuari a //$NON-NLS-1$
                                               // l'agent
            updateRole.setGrup(g.getCodi());
            updateRole.setDataTasca(Calendar.getInstance());
            updateRole.setCoddis(codiAgent); // Només es genera la tasca al
                                             // dispatcher actual
            updateRole.setStatus("P");// Posem com a pendent //$NON-NLS-1$
            TasqueEntity tasca = getTasqueEntityDao().tascaToEntity(updateRole);
            getTasqueEntityDao().createNoFlush(tasca);
        }

	}

	@Override
	protected Dispatcher handleFindSoffidDispatcher() throws Exception {
		DispatcherEntity sd = getDispatcherEntityDao().findSoffidDispatcher();
		if (sd == null)
			throw new InternalErrorException("Unable to locate Soffid system descriptor");
		return getDispatcherEntityDao().toDispatcher(sd);
	}
}
