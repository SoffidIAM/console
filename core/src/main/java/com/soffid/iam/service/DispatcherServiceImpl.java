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
import java.io.UnsupportedEncodingException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.JSONException;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.orm.hibernate3.SessionFactoryUtils;

import com.soffid.iam.api.AccessControl;
import com.soffid.iam.api.AsyncList;
import com.soffid.iam.api.AttributeMapping;
import com.soffid.iam.api.Audit;
import com.soffid.iam.api.Configuration;
import com.soffid.iam.api.CustomObject;
import com.soffid.iam.api.ObjectMapping;
import com.soffid.iam.api.ObjectMappingProperty;
import com.soffid.iam.api.ObjectMappingTrigger;
import com.soffid.iam.api.PagedResult;
import com.soffid.iam.api.ReconcileTrigger;
import com.soffid.iam.api.RoleGrant;
import com.soffid.iam.api.ScheduledTask;
import com.soffid.iam.api.Server;
import com.soffid.iam.api.System;
import com.soffid.iam.api.SoffidObjectType;
import com.soffid.iam.api.SystemGroup;
import com.soffid.iam.api.Task;
import com.soffid.iam.api.User;
import com.soffid.iam.api.UserTypeDispatcher;
import com.soffid.iam.bpm.service.scim.ScimHelper;
import com.soffid.iam.interp.Evaluator;
import com.soffid.iam.model.AccessControlEntity;
import com.soffid.iam.model.AccountEntity;
import com.soffid.iam.model.AccountMetadataEntity;
import com.soffid.iam.model.AgentDescriptorEntity;
import com.soffid.iam.model.AttributeMappingEntity;
import com.soffid.iam.model.DefaultAttributeMappingEntity;
import com.soffid.iam.model.DefaultObjectMappingEntity;
import com.soffid.iam.model.DefaultObjectMappingPropertyEntity;
import com.soffid.iam.model.GroupEntity;
import com.soffid.iam.model.GroupEntityDao;
import com.soffid.iam.model.ObjectMappingEntity;
import com.soffid.iam.model.ObjectMappingPropertyEntity;
import com.soffid.iam.model.ObjectMappingTriggerEntity;
import com.soffid.iam.model.ReconcileTriggerEntity;
import com.soffid.iam.model.ReconcileTriggerEntityDao;
import com.soffid.iam.model.RoleEntity;
import com.soffid.iam.model.ServerCertificateEntity;
import com.soffid.iam.model.ServerEntity;
import com.soffid.iam.model.ServerEntityDao;
import com.soffid.iam.model.SystemEntity;
import com.soffid.iam.model.SystemEntityDao;
import com.soffid.iam.model.SystemGroupEntity;
import com.soffid.iam.model.TaskEntity;
import com.soffid.iam.model.TenantServerEntity;
import com.soffid.iam.model.UserDomainEntity;
import com.soffid.iam.model.UserEntity;
import com.soffid.iam.model.UserGroupEntity;
import com.soffid.iam.model.UserTypeEntity;
import com.soffid.iam.model.UserTypeEntityDao;
import com.soffid.iam.model.UserTypeSystemEntity;
import com.soffid.iam.model.criteria.CriteriaSearchConfiguration;
import com.soffid.iam.service.RuleEvaluatorServiceImpl.InterpreterEnvironment;
import com.soffid.iam.service.impl.AccountDiffReport;
import com.soffid.iam.service.impl.RuleDryRunMethod;
import com.soffid.iam.sync.engine.intf.DebugTaskResults;
import com.soffid.iam.sync.engine.TaskHandler;
import com.soffid.iam.sync.engine.intf.GetObjectResults;
import com.soffid.iam.sync.intf.ExtensibleObject;
import com.soffid.iam.sync.service.SyncStatusService;
import com.soffid.iam.utils.AutoritzacionsUsuari;
import com.soffid.iam.utils.ConfigurationCache;
import com.soffid.iam.utils.Security;
import com.soffid.scimquery.EvalException;
import com.soffid.scimquery.parser.ParseException;
import com.soffid.scimquery.parser.TokenMgrError;

import es.caib.seycon.ng.comu.Dispatcher;
import es.caib.seycon.ng.comu.ServerType;
import es.caib.seycon.ng.exception.AccountAlreadyExistsException;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.exception.NeedsAccountNameException;
import es.caib.seycon.ng.exception.SeyconAccessLocalException;
import es.caib.seycon.ng.exception.SeyconException;

/**
 * @see es.caib.seycon.ng.servei.DispatcherServiceh
 */
public class DispatcherServiceImpl extends
		com.soffid.iam.service.DispatcherServiceBase 
		implements ApplicationContextAware {
	org.apache.commons.logging.Log log = LogFactory
			.getLog(getClass().getName());

	/**
	 * @see es.caib.seycon.ng.servei.DispatcherService#create(es.caib.seycon.ng.comu.Dispatcher)
	 */
	protected com.soffid.iam.api.System handleCreate(
			com.soffid.iam.api.System dispatcher) throws java.lang.Exception {

		String t = getTaskEntityDao().startVirtualSourceTransaction();
		try
		{
			if (dispatcher.getName().contains("@"))
				throw new IllegalArgumentException("A target system name should not contain the at (@) sign");
	    	soffidDispatcher = null;
			// Check dispatcher type
			if (dispatcher.getClassName().isEmpty()) {
				throw new IllegalArgumentException(
						Messages.getString("DispatcherServiceImpl.AgentTypeRequired")); //$NON-NLS-1$
			}
	
			// Check user domain
			if (dispatcher.getUsersDomain() == null || dispatcher.getUsersDomain().isEmpty()) {
				throw new IllegalArgumentException(
						Messages.getString("DispatcherServiceImpl.UserDomainRequired")); //$NON-NLS-1$
			}
	
			// Check password domain
			if (dispatcher.getPasswordsDomain() == null || dispatcher.getPasswordsDomain().isEmpty()) {
				throw new IllegalArgumentException(
						Messages.getString("DispatcherServiceImpl.PasswordDomainRequired")); //$NON-NLS-1$
			}
	
			// Check user type
			if (dispatcher.getUserTypes() == null) {
				dispatcher.setUserTypes(""); //$NON-NLS-1$
			}
			
			checkSystemServer (dispatcher);
	
			SystemEntity dispatchersSameCode = getSystemEntityDao().findByName(
					dispatcher.getName());
			if (dispatchersSameCode != null)
				throw new SeyconException(String.format(Messages
						.getString("DipatcherServiceImpl.CodeDispatcherExists"),
						dispatcher.getName()));
	
			SystemEntity dispatcherEntity = getSystemEntityDao().systemToEntity(
					dispatcher);
			dispatcherEntity
					.setMainSystem(getSystemEntityDao().loadAll().isEmpty());
			dispatcherEntity.setTimeStamp(new Date());
			getSystemEntityDao().create(dispatcherEntity);
			dispatcher.setId(dispatcherEntity.getId());
	
			updateAutomaticTasks(dispatcher, false);
	
//			handleSetDefaultMappingsByDispatcher(dispatcher.getId());
	
			updateTipusAndGrups(dispatcher, dispatcherEntity);
	
			dispatcher = getSystemEntityDao().toSystem(dispatcherEntity);
	
			updateServers();
	
			return dispatcher;
		} finally {
			getTaskEntityDao().finishVirtualSourceTransaction(t);
		}
	}

	private void checkSystemServer(com.soffid.iam.api.System dispatcher) throws Exception {
		if (dispatcher.getUrl() == null ||
				dispatcher.getUrl().isEmpty())
			return;
		if (dispatcher.getUrl().equals("local"))
			return;
		
		for (Server s: handleFindAllServers())
		{
			if (s.getUrl().equals(dispatcher.getUrl()))
				return;
		}
		
		throw new InternalErrorException(String.format("Server %s does not exist", dispatcher.getUrl()));
	}

	private void updateAutomaticTasks(com.soffid.iam.api.System dispatcher,
			boolean remove) throws InternalErrorException {
		if (dispatcher.getUrl() == null || dispatcher.getUrl().isEmpty()) {
			updateAutomaticTasks(SystemScheduledTasks.RECONCILE_DISPATCHER,
					"Reconcile all accounts from %s", //$NON-NLS-1$
					dispatcher, true);

			updateAutomaticTasks(SystemScheduledTasks.DISPATCHER_IMPACT,
					"Analize impact for changes on %s", //$NON-NLS-1$
					dispatcher, true);

			updateAutomaticTasks(
					SystemScheduledTasks.AUTHORITATIVE_DATA_IMPORT,
					"Import authoritative data from %s", //$NON-NLS-1$
					dispatcher, true);
		} else {
			if (dispatcher.isReadOnly() || dispatcher.isAuthoritative())
				updateAutomaticTasks(SystemScheduledTasks.RECONCILE_DISPATCHER,
						"Reconcile all accounts from %s", //$NON-NLS-1$
						dispatcher, remove);
			else
				updateAutomaticTasks(SystemScheduledTasks.RECONCILE_DISPATCHER,
						"Reconcile unmanaged accounts from %s", //$NON-NLS-1$
						dispatcher, remove);

			updateAutomaticTasks(SystemScheduledTasks.DISPATCHER_IMPACT,
					"Analize impact for changes on %s", //$NON-NLS-1$
					dispatcher, remove);

			updateAutomaticTasks(
					SystemScheduledTasks.AUTHORITATIVE_DATA_IMPORT,
					"Import authoritative data from %s", //$NON-NLS-1$
					dispatcher, remove || !dispatcher.isAuthoritative());
		}
	}

	private void updateAutomaticTasks(String handler, String description,
			com.soffid.iam.api.System dispatcher, boolean remove)
			throws InternalErrorException {
		ScheduledTask task = getScheduledTaskService()
				.findScheduledTaskByHandlerAndParams(handler,
						dispatcher.getId().toString());
		if (task == null && !remove) {
			task = new ScheduledTask();
			task.setHandlerName(handler);
			task.setParams(dispatcher.getId().toString());
			task.setActive(false);
			task.setDayOfWeekPattern("*"); //$NON-NLS-1$
			task.setDayPattern("*"); //$NON-NLS-1$
			task.setHoursPattern("0"); //$NON-NLS-1$
			task.setMinutesPattern("0"); //$NON-NLS-1$
			task.setMonthsPattern("*"); //$NON-NLS-1$
			task.setName(String.format(description, dispatcher.getName()));
			getScheduledTaskService().create(task);
		} else if (task != null && remove) {
			getScheduledTaskService().remove(task);
		} else if (task != null && !remove) {
			task.setName(String.format(description, dispatcher.getName()));
			getScheduledTaskService().update(task);
		}
	}

	/**
	 * @see es.caib.seycon.ng.servei.DispatcherService#update(es.caib.seycon.ng.comu.Dispatcher)
	 */
	protected com.soffid.iam.api.System handleUpdate(
			com.soffid.iam.api.System dispatcher) throws java.lang.Exception {
		
		String t = getTaskEntityDao().startVirtualSourceTransaction();
		try
		{
			if (dispatcher.getName().contains("@"))
				throw new IllegalArgumentException("A target system name should not contain the at (@) sign");
	    	soffidDispatcher = null;
			// Obtenim el anterior per comparar els grups i els tipus d'usuari
			SystemEntity entityOld = dispatcher.getId() == null ? 
					getSystemEntityDao().findByName(dispatcher.getName()) :
					getSystemEntityDao().load(dispatcher.getId());
	
			// fem còpia dels antics per comparar
			Collection<UserTypeSystemEntity> tipusUsuariOld = new java.util.HashSet<com.soffid.iam.model.UserTypeSystemEntity>(
					entityOld.getUserType());
			HashSet<SystemGroupEntity> grupsOld = new HashSet<SystemGroupEntity>(
					entityOld.getSystemGroup());
	
			// Obtenim el nou entity
			getSystemEntityDao().systemToEntity(dispatcher, entityOld, true);
			entityOld.setTimeStamp(new Date());
			
			updateAutomaticTasks(dispatcher, false);
	
			updateTipusAndGrups(dispatcher, entityOld);
	
			updateServers();
	
			getSystemEntityDao().update(entityOld);
			
			return getSystemEntityDao().toSystem(entityOld);
		} finally {
			getTaskEntityDao().finishVirtualSourceTransaction(t);
		}

	}
	private void updateTipusAndGrups(com.soffid.iam.api.System dispatcher,
			SystemEntity entity) throws InternalErrorException, Exception {
		updateTipus(dispatcher, entity);
		updateGrups(dispatcher, entity);
	}

	private void updateTipus(com.soffid.iam.api.System dispatcher,
			SystemEntity entity) throws InternalErrorException, Exception {
		UserTypeEntityDao tipusDao = getUserTypeEntityDao();
		com.soffid.iam.service.AccountService accService = getAccountService();
		String[] tipus = dispatcher.getUserTypes().split("[, ]+"); //$NON-NLS-1$
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
				if (tu == null)
					throw new InternalErrorException(String.format(
							Messages.getString("DispatcherServiceImpl.0"), t));
				UserTypeSystemEntity td = getUserTypeSystemEntityDao()
						.newUserTypeSystemEntity();
				td.setSystem(entity);
				td.setUserType(tu);
				getUserTypeSystemEntityDao().create(td);
				entity.getUserType().add(td);
			}
		}
		for (Iterator<UserTypeSystemEntity> it = entity.getUserType()
				.iterator(); it.hasNext();) {
			UserTypeSystemEntity td = it.next();
			boolean found = false;
			for (int i = 0; i < tipus.length; i++) {
				if (tipus[i].trim().equals(td.getUserType().getName())) {
					found = true;
					break;
				}
			}
			if (!found) {
				getUserTypeSystemEntityDao().remove(td);
				it.remove();
			}
		}
	}

	private void updateGrups(com.soffid.iam.api.System dispatcher,
			SystemEntity entity) throws InternalErrorException, Exception {
		GroupEntityDao grupDao = getGroupEntityDao();
		com.soffid.iam.service.AccountService accService = getAccountService();
		List<String> grups = dispatcher.getGroupsList() == null ? 
				new LinkedList<>():
				dispatcher.getGroupsList();

		Collection<GroupEntity> groupsToGenerateAccounts = new HashSet<GroupEntity>();
		if ( grups.isEmpty() && !entity.getSystemGroup().isEmpty()
				|| (!grups.isEmpty() && entity.getSystemGroup().isEmpty())) {
			List<GroupEntity> tots = grupDao.loadAll();
			for (GroupEntity g : tots) {
				groupsToGenerateAccounts.add(g);
			}
		}

		for (int i = 0; i < grups.size(); i++) {
			String t = grups.get(i).trim();
			boolean found = false;
			for (SystemGroupEntity gd : entity.getSystemGroup()) {
				if (gd.getGroup().getName().equals(t)) {
					found = true;
					break;
				}
			}
			if (!found && t.length() > 0) {
				GroupEntity gr = grupDao.findByName(t);
				if (gr == null)
					throw new InternalErrorException(String.format(
							Messages.getString("DispatcherServiceImpl.1"), t));
				SystemGroupEntity gd = getSystemGroupEntityDao()
						.newSystemGroupEntity();
				gd.setSystem(entity);
				gd.setGroup(gr);
				getSystemGroupEntityDao().create(gd);
				entity.getSystemGroup().add(gd);
				groupsToGenerateAccounts.add(gr);
			}
		}

		for (Iterator<SystemGroupEntity> it = entity.getSystemGroup()
				.iterator(); it.hasNext();) {
			SystemGroupEntity gd = it.next();
			boolean found = false;
			for (int i = 0; i < grups.size(); i++) {
				if (grups.get(i).trim().equals(gd.getGroup().getName())) {
					found = true;
					break;
				}
			}
			if (!found) {
				groupsToGenerateAccounts.add(gd.getGroup());
				getSystemGroupEntityDao().remove(gd);
				it.remove();
			}
		}
	}

	/**
	 * @see es.caib.seycon.ng.servei.DispatcherService#delete(es.caib.seycon.ng.comu.Dispatcher)
	 */
	protected void handleDelete(com.soffid.iam.api.System dispatcher)
			throws java.lang.Exception {
    	soffidDispatcher = null;
		SystemEntity dispatcherEntity = getSystemEntityDao().findByName(
				dispatcher.getName());
		// Esborrem les relacions existents amb d'altres taules
		getSystemGroupEntityDao().remove(dispatcherEntity.getSystemGroup());
		getUserTypeSystemEntityDao().remove(dispatcherEntity.getUserType());
		getAccessControlEntityDao()
				.remove(dispatcherEntity.getAccessControls());

		getHostSystemEntityDao().remove(dispatcherEntity.getHosts());
		for (ObjectMappingEntity om : dispatcherEntity.getObjectMappings()) {
			for (AttributeMappingEntity am : om.getAttributeMappings()) {
				getAttributeMappingEntityDao().remove(am);
			}
			for (ObjectMappingPropertyEntity omp : om.getProperties()) {
				getObjectMappingPropertyEntityDao().remove(omp);
			}
			getObjectMappingEntityDao().remove(om);
		}
		getSystemEntityDao().remove(dispatcherEntity);
		updateAutomaticTasks(dispatcher, true);
		updateServers();
	}

	protected com.soffid.iam.api.System handleFindDispatcherByName(String codi)
			throws Exception {
		SystemEntity dispatcherEntity = getSystemEntityDao().findByName(codi);
		if (dispatcherEntity != null) {
			com.soffid.iam.api.System dispatcher = getSystemEntityDao()
					.toSystem(dispatcherEntity);
			return dispatcher;
		}
		return null;
	}

	protected Collection<com.soffid.iam.api.System> handleFindDispatchersByFilter(
			String codi, String nomCla, String url, String basRol,
			String segur, Boolean actiu) throws Exception {
		int limitResults = Integer.parseInt(ConfigurationCache
				.getProperty("soffid.ui.maxrows")); //$NON-NLS-1$

		if (codi == null || codi.trim().compareTo("") == 0) { //$NON-NLS-1$
			codi = "%"; //$NON-NLS-1$
		}

		if (nomCla == null || nomCla.trim().compareTo("") == 0) { //$NON-NLS-1$
			nomCla = "%"; //$NON-NLS-1$
		}

		if (url != null
				&& (url.trim().compareTo("") == 0 || url.trim().compareTo("%") == 0)) { //$NON-NLS-1$ //$NON-NLS-2$
			url = null;
		}

		if (basRol != null
				&& (basRol.trim().compareTo("") == 0 || basRol.trim().compareTo("%") == 0)) { //$NON-NLS-1$ //$NON-NLS-2$
			basRol = null;
		}

		if (segur != null
				&& (segur.trim().compareTo("") == 0 || segur.trim().compareTo("%") == 0)) { //$NON-NLS-1$ //$NON-NLS-2$
			segur = null;
		}

		String esActiu = null;
		if (actiu != null) {
			esActiu = "S"; //$NON-NLS-1$
		}

		Collection dispatchers = getSystemEntityDao().findByFilter(codi,
				nomCla, url, basRol, segur, esActiu);
		if (dispatchers != null) {
			// Check maximum number of results
			if (dispatchers.size() > limitResults) {
				return getSystemEntityDao().toSystemList(dispatchers).subList(
						0, limitResults);
			}

			List<com.soffid.iam.api.System> l = getSystemEntityDao().toSystemList(dispatchers);
			Collections.sort(l, new Comparator<com.soffid.iam.api.System>() {
				@Override
				public int compare(com.soffid.iam.api.System o1, com.soffid.iam.api.System o2) {
					return o1.getName().compareToIgnoreCase(o2.getName());
				}
			});
			return l;
		}

		return new Vector();
	}

	protected AccessControl handleCreate(AccessControl controlAcces)
			throws Exception {
		if (AutoritzacionsUsuari.canCreateAccessControlAgent()) {
			AccessControlEntity entity = getAccessControlEntityDao()
					.accessControlToEntity(controlAcces);
			getAccessControlEntityDao().create(entity);
			controlAcces.setId(entity.getId());
			controlAcces = getAccessControlEntityDao().toAccessControl(entity);
			updateServers();
			return controlAcces;
		}
		throw new SeyconAccessLocalException(
				"DispatcherService", "create (ControlAcces)", //$NON-NLS-1$ //$NON-NLS-2$
				"agent:accessControl:create/*", //$NON-NLS-1$
				Messages.getString("DispatcherServiceImpl.2")); //$NON-NLS-1$

	}

	protected AccessControl handleUpdate(AccessControl controlAcces)
			throws Exception {
		if (AutoritzacionsUsuari.canUpdateAccessControlAgent()) {
			AccessControlEntity entity = getAccessControlEntityDao()
					.accessControlToEntity(controlAcces);
			getAccessControlEntityDao().update(entity);
			controlAcces.setId(entity.getId());
			controlAcces = getAccessControlEntityDao().toAccessControl(entity);

			// Ací hem de crear la tasca de UpdateAccessControl
			Task updateAccessControl = new Task();
			updateAccessControl.setTransaction("UpdateAccessControl");// Actualització //$NON-NLS-1$
			// de l'usuari
			// a l'agent
			updateAccessControl.setTaskDate(Calendar.getInstance());
			// nomAgent = getAgent().getCodi()
			updateAccessControl.setSystemName(controlAcces.getAgentName()); // Només
			// es
			// genera
			// la
			// tasca
			// al
			// dispatcher
			// actual
			updateAccessControl.setStatus("P");// Posem com a pendent //$NON-NLS-1$
			TaskEntity tasca = getTaskEntityDao().taskToEntity(
					updateAccessControl);
			getTaskEntityDao().create(tasca);

			updateServers();

			return controlAcces;
		}
		throw new SeyconAccessLocalException(
				"DispatcherService", "update (ControlAcces)", //$NON-NLS-1$ //$NON-NLS-2$
				"agent:accessControl:update/*", //$NON-NLS-1$
				Messages.getString("DispatcherServiceImpl.3")); //$NON-NLS-1$
	}

	protected void handleDelete(AccessControl controlAcces) throws Exception {
		AccessControlEntity entity = getAccessControlEntityDao()
				.accessControlToEntity(controlAcces);
		if (entity != null)
			getAccessControlEntityDao().remove(entity);

		updateServers();
	}

	protected Collection<AccessControl> handleFindAccessControlByDispatcherName(
			String codiAgent) throws Exception {
		if (!"%".equals(codiAgent)) { //$NON-NLS-1$
			Collection control = getAccessControlEntityDao().findByAgentCode(
					codiAgent);
			return getAccessControlEntityDao().toAccessControlList(control);
		}
		return new Vector();
	}

	protected void handlePorpagateUsersDispatcher(String codiAgent)
			throws Exception {
		String status = ConfigurationCache.getProperty("soffid.task.mode");
		if ("readonly".equals( status ) )
			throw new InternalErrorException ("Task configuration setting is in read only mode");
		// Verifiquem que l'agent siga actiu
		if (codiAgent == null || "".equals(codiAgent.trim())) //$NON-NLS-1$
			throw new SeyconException(
					Messages.getString("DispatcherServiceImpl.4")); //$NON-NLS-1$
		com.soffid.iam.api.System agent = findDispatcherByName(codiAgent);
		if (agent == null || agent.getUrl() == null)
			throw new SeyconException(
					Messages.getString("DispatcherServiceImpl.5")); //$NON-NLS-1$

		
		handleApplyConfiguration(agent);
		// Obtenim tots els codis d'usuari:
		Collection<AccountEntity> col = getSystemEntityDao().findByName(
				codiAgent).getAccounts();
		// Creem les tasques per a cadascun dels usuaris
		for (Iterator it = col.iterator(); it.hasNext();) {
			AccountEntity ae = (AccountEntity) it.next();
			String codiUsuari = ae.getName();
			Task updateUser = new Task();
			updateUser.setTransaction(TaskHandler.UPDATE_ACCOUNT);
			updateUser.setTaskDate(Calendar.getInstance());
			updateUser.setUser(codiUsuari);
			updateUser.setSystemName(codiAgent);
			updateUser.setStatus("P");
			TaskEntity tasca = getTaskEntityDao().taskToEntity(updateUser);
			getTaskEntityDao().createForce(tasca);
		}

	}

	protected void handlePropagateDispatcherRoles(String codiAgent)
			throws Exception {
		String status = ConfigurationCache.getProperty("soffid.task.mode");
		if ("readonly".equals( status ) )
			throw new InternalErrorException ("Task configuration setting is in read only mode");
		// Verifiquem que l'agent siga actiu
		if (codiAgent == null || "".equals(codiAgent.trim())) //$NON-NLS-1$
			throw new SeyconException(
					Messages.getString("DispatcherServiceImpl.4")); //$NON-NLS-1$

		com.soffid.iam.api.System agent = findDispatcherByName(codiAgent);
		handleApplyConfiguration(agent);

		SystemEntity system = getSystemEntityDao().findByName(codiAgent);
		if (system == null || system.getUrl() == null)
			throw new SeyconException(
					Messages.getString("DispatcherServiceImpl.5")); //$NON-NLS-1$

		
		// Creem les tasques per a cadascun dels usuaris
		for (RoleEntity role : system.getRole()) {
			Task updateRole = new Task();
			updateRole.setTransaction("UpdateRole");
			updateRole.setRole(role.getName());
			updateRole.setTaskDate(Calendar.getInstance());
			updateRole.setDatabase(codiAgent);
			updateRole.setSystemName(codiAgent);
			updateRole.setStatus("P");
			TaskEntity tasca = getTaskEntityDao().taskToEntity(updateRole);
			getTaskEntityDao().createForce(tasca);
		}

	}

	@Override
	protected UserTypeDispatcher handleCreate(UserTypeDispatcher tipusUsuari)
			throws Exception {
		return null;
	}

	@Override
	protected void handleDelete(UserTypeDispatcher tipusUsuari)
			throws Exception {
	}

	@Override
	protected SystemGroup handleCreate(SystemGroup grupDispatcher)
			throws Exception {
		return null;
	}

	@Override
	protected void handleDelete(SystemGroup grupDispatcher) throws Exception {

	}

	@Override
	protected UserTypeDispatcher handleUpdate(UserTypeDispatcher tipusUsuari)
			throws Exception {
		return null;
	}

	@Override
	protected SystemGroup handleUpdate(SystemGroup grupDispatcher)
			throws Exception {
		return null;
	}

	@Override
	protected Collection<AccessControl> handleGetAccessControl(
			com.soffid.iam.api.System agent) throws Exception {
		Collection<AccessControlEntity> cace = getAccessControlEntityDao()
				.findByAgentCode(agent.getName());
		return getAccessControlEntityDao().toAccessControlList(cace);
	}

	@Override
	protected Collection<SystemGroup> handleGetDispatcherGroups(
			com.soffid.iam.api.System agent) throws Exception {
		Collection<SystemGroupEntity> grupsE = getSystemGroupEntityDao()
				.findBySystem(agent.getName());
		return getSystemGroupEntityDao().toSystemGroupList(grupsE);
	}

	@Override
	protected Collection<UserTypeDispatcher> handleGetDispatcherUserTypes(
			com.soffid.iam.api.System agent) throws Exception {
		Collection<UserTypeSystemEntity> tipusUsuariE = getUserTypeSystemEntityDao()
				.findBySystem(agent.getName());
		return getUserTypeSystemEntityDao().toUserTypeDispatcherList(
				tipusUsuariE);

	}

	@Override
	protected boolean handleIsUserAllowed(com.soffid.iam.api.System dispatcher,
			String user) throws Exception {
		return handleIsUserAllowed(dispatcher, user, null);
	}

	private boolean userBelongsToGroup(UserEntity userEntity, GroupEntity grup) {
		if (isChildGroup(userEntity.getPrimaryGroup(), grup))
			return true;
		for (Iterator<UserGroupEntity> it = userEntity.getSecondaryGroups()
				.iterator(); it.hasNext();) {
			UserGroupEntity ug = it.next();
			if (! Boolean.TRUE.equals(ug.getDisabled()) &&
					isChildGroup(ug.getGroup(), grup))
				return true;
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
	protected Collection<com.soffid.iam.api.System> handleFindAllActiveDispatchers()
			throws Exception {
		return getSystemEntityDao().toSystemList(
				getSystemEntityDao().findActives());
	}

	@Override
	protected boolean handleIsGroupAllowed(
			com.soffid.iam.api.System dispatcher, String group)
			throws Exception {
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
					if (ge.getName().equals(group))
						return true;
					ge = ge.getParent();
				} while (ge != null);
			}
			return false;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * es.caib.seycon.ng.servei.DispatcherServiceBase#handleFindAllServers()
	 */
	@Override
	protected Collection<Server> handleFindAllServers() throws Exception {
		if (Security.isMasterTenant() && Security.isUserInRole(Security.AUTO_TENANT_QUERY))
		{
			ServerEntityDao dao = getServerEntityDao();
			List<Server> servers = new LinkedList<Server>(); 
			for (ServerEntity server: dao.loadAll())
			{
				Server vo = getServerEntityDao().toServer(server);
				vo.setAuth(null);
				vo.setPk(null);
				vo.setPublicKey(null);
				servers.add(vo);
			}
			return servers;
		}
		else
			return handleFindTenantServers();
	}

	@Override
	protected Collection<Server> handleFindTenantServers() throws Exception {
		ServerEntityDao dao = getServerEntityDao();
		Collection<ServerEntity> db;
		List<Server> servers = new LinkedList<Server>(); 
		for (ServerEntity server: dao.findByTenant(Security.getCurrentTenantName()))
		{
			Server vo = getServerEntityDao().toServer(server);
			vo.setAuth(null);
			vo.setPk(null);
			vo.setPublicKey(null);
			if (! Security.getMasterTenantName().equals(Security.getCurrentTenantName()) &&
					server.getTenants().size() > 1) {
				vo.setJavaOptions(null);
			}
			servers.add(vo);
		}
		
		return servers;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * es.caib.seycon.ng.servei.DispatcherServiceBase#handleUpdate(es.caib.seycon
	 * .ng.comu.Server)
	 */
	@Override
	protected Server handleUpdate(Server server) throws Exception {
		ServerEntityDao dao = getServerEntityDao();
		ServerEntity entity = dao.load(server.getId());
		
		if ( (entity.getType() == ServerType.MASTERSERVER || entity.getType() == ServerType.GATEWAY) && 
				!Security.isUserInRole(Security.AUTO_SERVER_MANAGE_SERVER) )
			throw new InternalErrorException("Not authorized to manage servers");
		
		if ((entity.getType() == ServerType.PROXYSERVER || entity.getType() == ServerType.REMOTESERVER) && 
				!Security.isUserInRole(Security.AUTO_SERVER_MANAGE_PROXY) )
			throw new InternalErrorException("Not authorized to manage servers");

		if (canAccess (entity))
		{
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
		} else
			return null;
	}

	private boolean canAccess(ServerEntity entity) throws InternalErrorException {
		if (Security.isUserInRole(Security.AUTO_TENANT_QUERY))
			return true;
		if (entity.getType() == ServerType.MASTERSERVER)
			return false;
		
		for (TenantServerEntity st: entity.getTenants())
		{
			if (st.getServerTenant().getName().equals(Security.getCurrentTenantName()))
				return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * es.caib.seycon.ng.servei.DispatcherServiceBase#handleDelete(es.caib.seycon
	 * .ng.comu.Server)
	 */
	@Override
	protected void handleDelete(Server server) throws Exception {
		ServerEntity entity = getServerEntityDao().load(server.getId());
		
		if (entity.getType() == ServerType.MASTERSERVER && 
				!Security.isUserInRole(Security.AUTO_SERVER_MANAGE_SERVER) )
			throw new InternalErrorException("Not authorized to manage servers");
		
		if (entity.getType() == ServerType.PROXYSERVER && 
				!Security.isUserInRole(Security.AUTO_SERVER_MANAGE_PROXY) )
			throw new InternalErrorException("Not authorized to manage servers");

		if (canAccess (entity))
		{
			getServerCertificateEntityDao().remove(entity.getCertificates());
			getServerEntityDao().remove(entity);
			updateSeyconServerList();
		}
	}

	protected void updateSeyconServerList() throws InternalErrorException {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * es.caib.seycon.ng.servei.DispatcherServiceBase#handleCreate(es.caib.seycon
	 * .ng.comu.Server)
	 */
	@Override
	protected Server handleCreate(Server server) throws Exception {
		if (server.getType() == ServerType.MASTERSERVER && 
				!Security.isUserInRole(Security.AUTO_SERVER_MANAGE_SERVER) )
			throw new InternalErrorException("Not authorized to manage servers");
		
		if (server.getType() == ServerType.PROXYSERVER && 
				!Security.isUserInRole(Security.AUTO_SERVER_MANAGE_PROXY) )
			throw new InternalErrorException("Not authorized to manage servers");

		ServerEntityDao dao = getServerEntityDao();
		ServerEntity serverEntity = dao.serverToEntity(server);
		dao.create(serverEntity);
		updateSeyconServerList();
		return dao.toServer(serverEntity);
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * es.caib.seycon.ng.servei.DispatcherServiceBase#handleCreate(es.caib.seycon
	 * .ng.comu.AttributeMapping)
	 */
	@Override
	protected AttributeMapping handleCreate(AttributeMapping mapping)
			throws Exception {
		AttributeMappingEntity ame = getAttributeMappingEntityDao()
				.attributeMappingToEntity(mapping);
		getAttributeMappingEntityDao().create(ame);
		if (ame.getObject() != null && ame.getObject().getSystem() != null) {
			ame.getObject().getSystem().setTimeStamp(new Date());
			getSystemEntityDao().update(ame.getObject().getSystem());
		}
		updateServers();
		return getAttributeMappingEntityDao().toAttributeMapping(ame);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * es.caib.seycon.ng.servei.DispatcherServiceBase#handleUpdate(es.caib.seycon
	 * .ng.comu.AttributeMapping)
	 */
	@Override
	protected AttributeMapping handleUpdate(AttributeMapping mapping)
			throws Exception {
		AttributeMappingEntity ame = getAttributeMappingEntityDao()
				.attributeMappingToEntity(mapping);
		getAttributeMappingEntityDao().update(ame);
		if (ame.getObject() != null && ame.getObject().getSystem() != null) {
			ame.getObject().getSystem().setTimeStamp(new Date());
			getSystemEntityDao().update(ame.getObject().getSystem());
		}
		updateServers();
		return getAttributeMappingEntityDao().toAttributeMapping(ame);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * es.caib.seycon.ng.servei.DispatcherServiceBase#handleDelete(es.caib.seycon
	 * .ng.comu.AttributeMapping)
	 */
	@Override
	protected void handleDelete(AttributeMapping mapping) throws Exception {
		AttributeMappingEntity ame = getAttributeMappingEntityDao()
				.attributeMappingToEntity(mapping);
		if (ame.getObject() != null && ame.getObject().getSystem() != null) {
			ame.getObject().getSystem().setTimeStamp(new Date());
			getSystemEntityDao().update(ame.getObject().getSystem());
		}
		updateServers();
		getAttributeMappingEntityDao().remove(ame);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see es.caib.seycon.ng.servei.DispatcherServiceBase#
	 * handleFindAttributeMappingsByDispatcher(java.lang.Long)
	 */
	@Override
	protected Collection<AttributeMapping> handleFindAttributeMappingsByObject(
			Long objectId) throws Exception {
		ObjectMappingEntity obj = getObjectMappingEntityDao().load(objectId);
		List<AttributeMapping> list = getAttributeMappingEntityDao()
				.toAttributeMappingList(obj.getAttributeMappings());
		Collections.sort(list, new Comparator<AttributeMapping>() {

			public int compare(AttributeMapping o1, AttributeMapping o2) {
				return o1.getId().compareTo(o2.getId());
			}

		});
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see es.caib.seycon.ng.servei.DispatcherServiceBase#
	 * handleSetDefaultMappingsByDispatcher(java.lang.Long)
	 */
	@Override
	protected void handleSetDefaultMappingsByDispatcher(Long dispatcherId)
			throws Exception {
		Evaluator ev = Evaluator.instance();
	
		SystemEntity de = getSystemEntityDao().load(dispatcherId);

		for (ObjectMappingEntity ome : de.getObjectMappings()) {
			getObjectMappingPropertyEntityDao().remove(ome.getProperties());
			getAttributeMappingEntityDao().remove(ome.getAttributeMappings());
			getObjectMappingEntityDao().remove(ome);
		}

		de.getObjectMappings().clear();

		AgentDescriptorEntity ad = getAgentDescriptorEntityDao().findByClass(
				Security.getCurrentTenantName(),
				de.getClassName());
		if (ad == null)
			ad = getAgentDescriptorEntityDao().findByClass(
					Security.getMasterTenantName(),
					de.getClassName());
		if (ad != null) {
			for (DefaultObjectMappingEntity dom : ad.getDefaultObjectMappings()) {
				ObjectMappingEntity ome = getObjectMappingEntityDao()
						.newObjectMappingEntity();
				ome.setCondition(dom.getCondition());
				ome.setSystem(de);
				ome.setSoffidObject(dom.getSoffidObject());
				ome.setSystemObject(dom.getSystemObject());
				getObjectMappingEntityDao().create(ome);
				for (DefaultObjectMappingPropertyEntity domp : dom
						.getProperties()) {
					ObjectMappingPropertyEntity ompe = getObjectMappingPropertyEntityDao()
							.newObjectMappingPropertyEntity();
					ompe.setObject(ome);
					ompe.setProperty(domp.getProperty());
					ompe.setValue(domp.getValue());
					getObjectMappingPropertyEntityDao().create(ompe);
					ome.getProperties().add(ompe);
				}
				for (DefaultAttributeMappingEntity dam : dom
						.getDefaultAttributeMappings()) {
					AttributeMappingEntity am = getAttributeMappingEntityDao()
							.newAttributeMappingEntity();
					am.setObject(ome);
					am.setDirection(dam.getDirection());
					am.setSoffidAttribute(ev.translateFromBsh( dam.getSoffidAttribute()) );
					am.setSystemAttribute(ev.translateFromBsh( dam.getSystemAttribute()) );
					getAttributeMappingEntityDao().create(am);
					ome.getAttributeMappings().add(am);
				}
			}
		}
		updateServers();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * es.caib.seycon.ng.servei.DispatcherServiceBase#handleCreate(es.caib.seycon
	 * .ng.comu.ObjectMapping)
	 */
	@Override
	protected ObjectMapping handleCreate(ObjectMapping om) throws Exception {
		ObjectMappingEntity ome = getObjectMappingEntityDao()
				.objectMappingToEntity(om);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * es.caib.seycon.ng.servei.DispatcherServiceBase#handleUpdate(es.caib.seycon
	 * .ng.comu.ObjectMapping)
	 */
	@Override
	protected ObjectMapping handleUpdate(ObjectMapping om) throws Exception {
		ObjectMappingEntity ome = getObjectMappingEntityDao()
				.objectMappingToEntity(om);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * es.caib.seycon.ng.servei.DispatcherServiceBase#handleDelete(es.caib.seycon
	 * .ng.comu.ObjectMapping)
	 */
	@Override
	protected void handleDelete(ObjectMapping om) throws Exception {
		ObjectMappingEntity ome = getObjectMappingEntityDao()
				.objectMappingToEntity(om);
		for (ObjectMappingPropertyEntity ompe : ome.getProperties()) {
			getObjectMappingPropertyEntityDao().remove(ompe);
		}

		for (AttributeMappingEntity ame : ome.getAttributeMappings()) {
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * es.caib.seycon.ng.servei.DispatcherServiceBase#handleCreate(es.caib.seycon
	 * .ng.comu.ObjectMappingProperty)
	 */
	@Override
	protected ObjectMappingProperty handleCreate(ObjectMappingProperty omp)
			throws Exception {
		ObjectMappingPropertyEntity ome = getObjectMappingPropertyEntityDao()
				.objectMappingPropertyToEntity(omp);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * es.caib.seycon.ng.servei.DispatcherServiceBase#handleUpdate(es.caib.seycon
	 * .ng.comu.ObjectMappingProperty)
	 */
	@Override
	protected ObjectMappingProperty handleUpdate(ObjectMappingProperty omp)
			throws Exception {
		ObjectMappingPropertyEntity ome = getObjectMappingPropertyEntityDao()
				.objectMappingPropertyToEntity(omp);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * es.caib.seycon.ng.servei.DispatcherServiceBase#handleDelete(es.caib.seycon
	 * .ng.comu.ObjectMappingProperty)
	 */
	@Override
	protected void handleDelete(ObjectMappingProperty omp) throws Exception {
		ObjectMappingPropertyEntity ome = getObjectMappingPropertyEntityDao()
				.objectMappingPropertyToEntity(omp);
		ome.getObject().getSystem().setTimeStamp(new Date());
		getSystemEntityDao().update(ome.getObject().getSystem());
		if (Hibernate.isPropertyInitialized(ome.getObject(), "properties")) //$NON-NLS-1$
		{
			ome.getObject().getProperties().remove(ome);
		}
		getObjectMappingPropertyEntityDao().remove(ome);
		updateServers();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see es.caib.seycon.ng.servei.DispatcherServiceBase#
	 * handleFindObjectMappingsByDispatcher(java.lang.Long)
	 */
	@Override
	protected Collection<ObjectMapping> handleFindObjectMappingsByDispatcher(
			Long dispatcherId) throws Exception {
		SystemEntity de = getSystemEntityDao().load(dispatcherId);
		if (de == null)
			return Collections.emptyList();
		else
			return getObjectMappingEntityDao().toObjectMappingList(
					de.getObjectMappings());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see es.caib.seycon.ng.servei.DispatcherServiceBase#
	 * handleFindObjectMappingPropertesByObject(java.lang.Long)
	 */
	@Override
	protected Collection<ObjectMappingProperty> handleFindObjectMappingPropertiesByObject(
			Long objectId) throws Exception {
		ObjectMappingEntity ome = getObjectMappingEntityDao().load(objectId);
		if (ome == null)
			return Collections.emptyList();
		else
		{
			List<ObjectMappingProperty> list = getObjectMappingPropertyEntityDao().toObjectMappingPropertyList(ome.getProperties());
			Collections.sort(
						list,
						new Comparator<ObjectMappingProperty>() {

							public int compare(ObjectMappingProperty o1,
									ObjectMappingProperty o2) {
								return o1.getProperty().compareTo(o2.getProperty());
							}
						});
			return list;
		}
	}

	private void updateServers() throws InternalErrorException {
		getSyncServerService().updateDispatcherConfiguration();
	}

	@Override
	protected void handlePropagateDispatcherGroups(String codiAgent)
			throws Exception {
		String status = ConfigurationCache.getProperty("soffid.task.mode");
		if ("readonly".equals( status ) )
			throw new InternalErrorException ("Task configuration setting is in read only mode");
		// Verifiquem que l'agent siga actiu
		if (codiAgent == null || "".equals(codiAgent.trim())) //$NON-NLS-1$
			throw new SeyconException(
					Messages.getString("DispatcherServiceImpl.4")); //$NON-NLS-1$
		com.soffid.iam.api.System agent = findDispatcherByName(codiAgent);
		if (agent == null || agent.getUrl() == null)
			throw new SeyconException(
					Messages.getString("DispatcherServiceImpl.5")); //$NON-NLS-1$

		// Obtenim tots els rols de l'agent:
		Collection col = getGroupEntityDao().loadAll();
		// col = new Vector(col);
		// Creem les tasques per a cadascun dels usuaris
		for (Iterator it = col.iterator(); it.hasNext();) {
			GroupEntity g = (GroupEntity) it.next();
			Task updateRole = new Task();
			updateRole.setTransaction("UpdateGroup");
			updateRole.setGroup(g.getName());
			updateRole.setTaskDate(Calendar.getInstance());
			updateRole.setDatabase(codiAgent);
			updateRole.setStatus("P");
			TaskEntity tasca = getTaskEntityDao().taskToEntity(updateRole);
			getTaskEntityDao().createForce(tasca);
		}

	}

	com.soffid.iam.api.System soffidDispatcher = null;
	private ApplicationContext ctx;
	@Override
	protected com.soffid.iam.api.System handleFindSoffidDispatcher()
			throws Exception {
		if (soffidDispatcher != null)
			return soffidDispatcher;
		SystemEntity sd = getSystemEntityDao().findSoffidSystem();
		if (sd == null)
			throw new InternalErrorException(
					"Unable to locate Soffid system descriptor");
		soffidDispatcher = getSystemEntityDao().toSystem(sd);
		return soffidDispatcher;
	}

	@Override
	protected String[] handleGetServerTenants(Server server) throws Exception {
		ServerEntity entity = getServerEntityDao().findByName(server.getName());
		if (canAccess(entity))
		{
			List<String> tenants = new LinkedList<String>();
			for (TenantServerEntity st: entity.getTenants())
			{
				if (Security.isUserInRole(Security.AUTO_TENANT_QUERY) ||
						st.getServerTenant().getName().equals(Security.getCurrentTenantName()))
					tenants.add(st.getServerTenant().getName());
			}
			return tenants.toArray(new String[tenants.size()]);
		}
		else
			return null;
	}

	@Override
	protected void handleDelete(ReconcileTrigger rp) throws Exception {
    	soffidDispatcher = null;

    	getReconcileTriggerEntityDao().remove(rp.getId());
	}

	@Override
	protected ReconcileTrigger handleCreate(ReconcileTrigger rp)
			throws Exception {
    	soffidDispatcher = null;
		ReconcileTriggerEntityDao dao = getReconcileTriggerEntityDao();
		ReconcileTriggerEntity entity = dao.reconcileTriggerToEntity(rp);
		dao.create(entity);
		
		return dao.toReconcileTrigger(entity);
	}

	@Override
	protected ReconcileTrigger handleUpdate(ReconcileTrigger rp)
			throws Exception {
    	soffidDispatcher = null;

    	ReconcileTriggerEntityDao dao = getReconcileTriggerEntityDao();
		ReconcileTriggerEntity entity = dao.reconcileTriggerToEntity(rp);
		dao.create(entity);
		
		return dao.toReconcileTrigger(entity);
	}

	@Override
	protected Collection<ReconcileTrigger> handleFindReconcileTriggersByDispatcher(
			Long dispatcherId) throws Exception {
    	soffidDispatcher = null;

    	SystemEntity entity = getSystemEntityDao().load(dispatcherId);
		return  getReconcileTriggerEntityDao().toReconcileTriggerList(entity.getReconcileTriggers());
	}

	@Override
	protected Map<String, Object> handleTestObjectMapping(Map<String,String> sentences, String dispatcher, 
					SoffidObjectType type, String object1, String object2) throws InternalErrorException {
		SyncStatusService svc = ( SyncStatusService ) getSyncServerService().getServerService(SyncStatusService.REMOTE_PATH);
		
		if (svc == null)
			throw new InternalErrorException ("No sync server available");
		return svc.testObjectMapping(sentences, dispatcher, type, object1, object2);
	}

	@Override
	protected DebugTaskResults handleTestPropagateObject(String dispatcher,
			SoffidObjectType type, String object1, String object2)
			throws Exception {
		SystemEntity s = getSystemEntityDao().findByName(dispatcher);
		if (s == null)
			throw new InternalErrorException (String.format("Unknown system %s", dispatcher));
		if (s.isReadOnly())
			throw new InternalErrorException (String.format("System %s is in read only mode", dispatcher));
			
		String status = ConfigurationCache.getProperty("soffid.task.mode");
		if ("readonly".equals( status ) )
			throw new InternalErrorException ("Task configuration setting is in read only mode");

		SyncStatusService svc = ( SyncStatusService ) getSyncServerService().getServerService(SyncStatusService.REMOTE_PATH);
		
		if (svc == null)
			throw new InternalErrorException ("No sync server available");
		return svc.testPropagateObject(dispatcher, type, object1, object2);
	}


	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.DispatcherServiceBase#handleCreate(es.caib.seycon.ng.comu.AttributeMapping)
	 */
	@Override
	protected ObjectMappingTrigger handleCreate (ObjectMappingTrigger trigger) throws Exception
	{
    	soffidDispatcher = null;

    	ObjectMappingTriggerEntity ame = getObjectMappingTriggerEntityDao().objectMappingTriggerToEntity(trigger);
		getObjectMappingTriggerEntityDao().create(ame);
		if (ame.getObject() != null && ame.getObject().getSystem() != null)
		{
			ame.getObject().getSystem().setTimeStamp(new Date());
			getSystemEntityDao().update(ame.getObject().getSystem());
		}
        updateServers();
		return getObjectMappingTriggerEntityDao().toObjectMappingTrigger(ame);
	}

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.DispatcherServiceBase#handleUpdate(es.caib.seycon.ng.comu.AttributeMapping)
	 */
	@Override
	protected ObjectMappingTrigger handleUpdate (ObjectMappingTrigger mapping) throws Exception
	{
    	soffidDispatcher = null;

    	ObjectMappingTriggerEntity ame = getObjectMappingTriggerEntityDao().objectMappingTriggerToEntity(mapping);
		getObjectMappingTriggerEntityDao().update(ame);
		if (ame.getObject() != null && ame.getObject().getSystem() != null)
		{
			ame.getObject().getSystem().setTimeStamp(new Date());
			getSystemEntityDao().update(ame.getObject().getSystem());
		}
        updateServers();
		return getObjectMappingTriggerEntityDao().toObjectMappingTrigger(ame);
	}

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.DispatcherServiceBase#handleDelete(es.caib.seycon.ng.comu.AttributeMapping)
	 */
	@Override
	protected void handleDelete (ObjectMappingTrigger mapping) throws Exception
	{
    	soffidDispatcher = null;

    	ObjectMappingTriggerEntity ame = getObjectMappingTriggerEntityDao().objectMappingTriggerToEntity(mapping);
		if (ame.getObject() != null && ame.getObject().getSystem() != null)
		{
			ame.getObject().getSystem().setTimeStamp(new Date());
			getSystemEntityDao().update(ame.getObject().getSystem());
		}
        updateServers();
		getObjectMappingTriggerEntityDao().remove(ame);
	}

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.DispatcherServiceBase#handleFindAttributeMappingsByDispatcher(java.lang.Long)
	 */
	@Override
	protected Collection<ObjectMappingTrigger> handleFindObjectMappingTriggersByObject (
					Long objectId) throws Exception
	{
		ObjectMappingEntity obj = getObjectMappingEntityDao().load(objectId);
		List<ObjectMappingTrigger> list = getObjectMappingTriggerEntityDao().toObjectMappingTriggerList(obj.getTriggers());
		Collections.sort(list, new Comparator<ObjectMappingTrigger>(){

			public int compare (ObjectMappingTrigger o1, ObjectMappingTrigger o2)
			{
				return o1.getId().compareTo(o2.getId());
			}
			
		});
		return list;
	}

	@Override
	protected GetObjectResults handleGetNativeObject(String dispatcher, SoffidObjectType type, String object1,
			String object2) throws Exception {
		SyncStatusService svc = ( SyncStatusService ) getSyncServerService().getServerService(SyncStatusService.REMOTE_PATH);
		
		if (svc == null)
			throw new InternalErrorException ("No sync server available");
		GetObjectResults o = svc.getNativeObject(dispatcher, type, object1, object2);
		Map<String,Object> r = new HashMap<String, Object>();

		if (o == null)
			throw new InternalErrorException ("Cannot connect to target system");
		fill ("", "", r, o.getObject());
		
		o.setObject(r);
		return o;
	}

	@Override
	protected GetObjectResults handleGetSoffidObject(String dispatcher, SoffidObjectType type, String object1,
			String object2) throws Exception {
		SyncStatusService svc = ( SyncStatusService ) getSyncServerService().getServerService(SyncStatusService.REMOTE_PATH);
		
		if (svc == null)
			throw new InternalErrorException ("No sync server available");
		GetObjectResults o = svc.getSoffidObject(dispatcher, type, object1, object2);
		Map<String,Object> r = new HashMap<String, Object>();
		fill ("", "", r, o.getObject());
		
		o.setObject(r);
		
		return o;
	}

	private void fill(String prefix, String suffix, Map<String, Object> r, Map<String, Object> o) {
		if (o != null)
		{
			for (String s: o.keySet())
			{
				Object v = o.get(s);
				if (v instanceof Map)
				{
					fill(prefix+s+suffix+"{\"", "\"}", r, (Map<String, Object>) v);
				}
				else
				{
					r.put(prefix+s+suffix, v);
				}
			}
		}
	}

	@Override
	protected void handleCheckConnectivity(String dispatcher) throws Exception {
		SyncStatusService svc = ( SyncStatusService ) getSyncServerService().getServerService(SyncStatusService.REMOTE_PATH);
		
		if (svc == null)
			throw new InternalErrorException ("No sync server available");
		svc.checkConnectivity(dispatcher);
	}

	@Override
	protected String handleGenerateChangesReport(com.soffid.iam.api.System dispatcher) throws Exception {
		SessionFactory sessionFactory;
		sessionFactory = (SessionFactory) ctx.getBean("sessionFactory");
		Session session = SessionFactoryUtils.getSession(sessionFactory, false) ;
		
		HashSet<String> groups = new HashSet<String>();
		if (dispatcher.getGroups() != null && ! dispatcher.getGroups().isEmpty())
		{
			for (String s: dispatcher.getGroups().split("[, ]+"))
			{
				groups.add(s);
			}
		}

		HashSet<String> types = new HashSet<String>();
		if (dispatcher.getUserTypes() != null && ! dispatcher.getUserTypes().isEmpty())
		{
			for (String s: dispatcher.getUserTypes().split("[, ]+"))
			{
				types.add(s);
			}
		}

		List<Long> allUsers = new LinkedList<Long>();
		for (UserEntity u: getUserEntityDao().loadAll())
		{
			allUsers.add(u.getId());
		}
		
		UserDomainEntity ud = getUserDomainEntityDao().findByName(dispatcher.getUsersDomain());
		if (ud == null)
			throw new InternalErrorException("Invalid user domain "+dispatcher.getUsersDomain());
		
		AccountDiffReport report = new AccountDiffReport();
		report.setSystem(dispatcher);
		report.setApply(false);
		report.generateHeader ();
		report.setAccountEntityDao(getAccountEntityDao());
		report.setUserEntityDao(getUserEntityDao());
		report.setAccountService(getAccountService());
		for (Long l: allUsers)
		{
			UserEntity u = getUserEntityDao().load(l);

			analyze (u, dispatcher, groups, types, ud,
					report);
		}
		report.close();
		return report.getFile().getAbsolutePath();
	}

	private void analyze(UserEntity u, com.soffid.iam.api.System dispatcher, HashSet<String> groups,
			HashSet<String> userTypes,
			UserDomainEntity ud, AccountDiffReport report) throws InternalErrorException, NeedsAccountNameException, AccountAlreadyExistsException {
		boolean match = true;
		match = matchGroup(u, groups);
		match = match && matchUserType(u, userTypes);

		
		List<AccountEntity> accounts = getAccountEntityDao().findByUserAndSystem(u.getUserName(), dispatcher.getName());
		if (accounts.isEmpty() && u.getActive().equals("S"))
		{
			match = match && ! Boolean.TRUE.equals(dispatcher.getManualAccountCreation());
			if (match && Boolean.TRUE.equals(dispatcher.getRolebased()))
			{
				match = false;
				for (RoleGrant grant: getApplicationService().findEffectiveRoleGrantByUser(u.getId()))
				{
					if (grant.getSystem().equals(dispatcher.getName()))
					{
						match = true;
						break;
					}
				}
				
			}
			if (match)
			{
				String accountName = getAccountService().predictAccountName(u.getId(), dispatcher.getName(), ud.getId());
				if (accountName != null)
				{
					report.createAccount (u, accountName);
				}
			}
		} else {
			for (AccountEntity acc: accounts)
			{
				boolean willBeEnabled =  u.getActive().equals("S") && 
						match && 
						(! Boolean.TRUE.equals( dispatcher.getRolebased() ) || 
								! getApplicationService().findEffectiveRoleGrantByAccount(acc.getId()).isEmpty());
				
				if (! acc.isDisabled() && ! willBeEnabled)
				{
					report.disableAccount (u, acc);
				}
				if (acc.isDisabled() && willBeEnabled)
				{
					report.createAccount (u, acc.getName());
				}
			}
		}
	}

	private boolean matchUserType(UserEntity u, HashSet<String> userTypes) {
		return (userTypes.contains( u.getUserType().getName()));
	}

	private boolean matchGroup(UserEntity u, HashSet<String> groups) {
		if (groups.isEmpty())
			return true;
		else
		{
			if (matchGroup (u.getPrimaryGroup(), groups))
				return true;
			else
			{
				for (UserGroupEntity ug: u.getSecondaryGroups())
				{
					if (! Boolean.TRUE.equals(ug.getDisabled()) && 
							matchGroup(ug.getGroup(), groups))
					{
						return true;
					}
				}
			}
		}
		return false;
	}

	private boolean matchGroup(GroupEntity primaryGroup, HashSet<String> groups) {
		GroupEntity g = primaryGroup;
		do {
			if (groups.contains(g.getName()))
				return true;
			else
				g  = g.getParent();
		} while (g != null);
		return false;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.ctx = applicationContext;
	}

	@Override
	protected void handleApplyConfiguration(com.soffid.iam.api.System dispatcher) throws Exception {
		handleUpdate(dispatcher);
		
		SessionFactory sessionFactory;
		sessionFactory = (SessionFactory) ctx.getBean("sessionFactory");
		Session session = SessionFactoryUtils.getSession(sessionFactory, false) ;

		String t = getTaskEntityDao().startVirtualSourceTransaction();
		try
		{
			HashSet<String> groups = new HashSet<String>();
			if (dispatcher.getGroups() != null && ! dispatcher.getGroups().isEmpty())
			{
				for (String s: dispatcher.getGroups().split("[, ]+"))
				{
					groups.add(s);
				}
			}
	
			HashSet<String> types = new HashSet<String>();
			if (dispatcher.getUserTypes() != null && ! dispatcher.getUserTypes().isEmpty())
			{
				for (String s: dispatcher.getUserTypes().split("[, ]+"))
				{
					types.add(s);
				}
			}
	
			List<Long> allUsers = new LinkedList<Long>();
			for (UserEntity u: getUserEntityDao().loadAll())
			{
				allUsers.add(u.getId());
			}
			
			UserDomainEntity ud = getUserDomainEntityDao().findByName(dispatcher.getUsersDomain());
			if (ud == null)
				throw new InternalErrorException("Invalid user domain "+dispatcher.getUsersDomain());
			
			AccountDiffReport report = new AccountDiffReport();
			report.setSystem(dispatcher);
			report.setApply(true);
			report.setAccountEntityDao(getAccountEntityDao());
			report.setUserEntityDao(getUserEntityDao());
			report.setAccountService(getAccountService());
			report.generateHeader ();
			for (Long l: allUsers)
			{
				session.flush();
				session.clear();
				UserEntity u = getUserEntityDao().load(l);
	
				analyze (u, dispatcher, groups, types, ud,
						report);
			}
			report.close();
			report.getFile().delete();
		} finally {
			getTaskEntityDao().finishVirtualSourceTransaction(t);
		}
	}

	@Override
	protected void handleRenameAccounts(com.soffid.iam.api.System dispatcher) throws Exception {
		
	}

	@Override
	protected boolean handleIsUserAllowed(com.soffid.iam.api.System dispatcher, String user,
			Collection<RoleGrant> grants) throws Exception {
		SystemEntity de = getSystemEntityDao().load(dispatcher.getId());
		if (de == null)
			return false;
		UserEntity ue = getUserEntityDao().findByUserName(user);
		if (ue.getActive().equals("N"))
			return false;

		if (de.getManualAccountCreation() != null
				&& de.getManualAccountCreation())
			return true;

		// Test user types
		boolean found = false;
		for (Iterator<UserTypeSystemEntity> it = de.getUserType().iterator(); !found
				&& it.hasNext();) {
			UserTypeSystemEntity tu = it.next();
			if (tu.getUserType().getId().equals(ue.getUserType().getId()))
				found = true;
		}
		if (!found)
			return false;

		// Test dispatcher groups
		if (!de.getSystemGroup().isEmpty()) {
			found = false;
			for (Iterator<SystemGroupEntity> it = de.getSystemGroup()
					.iterator(); it.hasNext() && !found;) {
				SystemGroupEntity gde = it.next();
				if (userBelongsToGroup(ue, gde.getGroup()))
					found = true;
			}
			if (!found)
				return false;
		}

		// Test role-based condition
		if (de.getRoleBased().equals("S")) { //$NON-NLS-1$
			if (grants == null)
				grants = getApplicationService()
					.findEffectiveRoleGrantByUser(ue.getId());
			for (RoleGrant grant : grants) {
				if (grant.getSystem().equals(de.getName()))
					return true;
			}
			return false;
		}
		return true;
	}

	@Override
	protected String handleCreateRemoteServer(String name, String tenant) throws Exception {
		ServerEntity gateway = null;
		int guests = Integer.MAX_VALUE;
		for ( ServerEntity candidate: getServerEntityDao().findGatewayByTenant(tenant))
		{
			Long q = getServerEntityDao().countServersByName("%."+candidate.getName());
			if ( q.intValue() < guests)
			{
				guests = q.intValue();
				gateway = candidate;
			}
		}
		if (gateway == null)
			return null;
		
		
		Security.nestedLogin("master", "admin", Security.ALL_PERMISSIONS);
		try
		{
			ServerEntity r = getServerEntityDao().newServerEntity();
			Configuration p = getConfigurationService().findMasterParameterByNameAndNetwork("server.nextRemoteServer", null);
			if (p == null)
			{
				p = new Configuration();
				p.setCode("server.nextRemoteServer");
				p.setValue("" + java.lang.System.currentTimeMillis());
				p = getConfigurationService().create(p);
			}
			String assignedName = "s"+p.getValue()+"."+gateway.getName();
			p.setValue( Long.toString( 1L + Long.parseLong(p.getValue())));
			getConfigurationService().update(p);
			return assignedName;
		} finally {
			Security.nestedLogoff();
		}
	}

	@Override
	protected GetObjectResults handleReconcile(String dispatcher, String accountName) throws Exception {
		SyncStatusService svc = ( SyncStatusService ) getSyncServerService().getServerService(SyncStatusService.REMOTE_PATH);
		
		if (svc == null)
			throw new InternalErrorException ("No sync server available");
		GetObjectResults o = svc.reconcile(dispatcher, accountName);
		Map<String,Object> r = new HashMap<String, Object>();
		fill ("", "", r, o.getObject());
		
		o.setObject(r);
		
		return o;
	}

	@Override
	protected AsyncList<com.soffid.iam.api.System> handleFindSystemByTextAndJsonQueryAsync(String text,
			String jsonQuery) throws Exception {
		final AsyncList<com.soffid.iam.api.System> result = new AsyncList<com.soffid.iam.api.System>();
		getAsyncRunnerService().run(new Runnable() {
			@Override
			public void run() {
				try {
					handleFindSystemByTextAndJsonQueryAsync(text, jsonQuery, null, null, result);
				} catch (Throwable e) {
					throw new RuntimeException(e);
				}				
			}
		}, result);

		return result;
	}

	@Override
	protected PagedResult<com.soffid.iam.api.System> handleFindSystemByTextAndJsonQuery(String text, String jsonQuery,
			Integer start, Integer pageSize) throws Exception {
		final LinkedList<com.soffid.iam.api.System> result = new LinkedList<com.soffid.iam.api.System>();
		return doFindSystemByTextAndJsonQuery(text, jsonQuery, start, pageSize, result);
	}
	
	private void handleFindSystemByTextAndJsonQueryAsync(String text, String jsonQuery,
			Integer start, Integer pageSize,
			Collection<com.soffid.iam.api.System> result) throws Exception {
		final SystemEntityDao dao = getSystemEntityDao();
		ScimHelper h = new ScimHelper(com.soffid.iam.api.System.class);
		h.setPrimaryAttributes(new String[] { "name", "description"});
		CriteriaSearchConfiguration config = new CriteriaSearchConfiguration();
		config.setFirstResult(start);
		config.setMaximumResultSize(pageSize);
		h.setConfig(config);
		h.setTenantFilter("tenant.id");
		h.setGenerator((entity) -> {
			return dao.toSystem((SystemEntity) entity);
		}); 
		h.search(text, jsonQuery, (Collection) result); 
	}

	@Override
	protected AsyncList<System> handleFindSystemByTextAndFilterAsync(String text, String query) throws Exception {
		final AsyncList<System> result = new AsyncList<System>();
		getAsyncRunnerService().run(new Runnable() {
			@Override
			public void run() {
				try {
					doFindSystemByTextAndJsonQuery(text, query, null, null, result);
				} catch (Throwable e) {
					throw new RuntimeException(e);
				}				
			}
		}, result);

		return result;
	}

	@Override
	protected PagedResult<System> handleFindSystemByTextAndFilter(String text, String query, Integer first,
			Integer pageSize) throws Exception {
		final LinkedList<System> result = new LinkedList<System>();
		return doFindSystemByTextAndJsonQuery(text, query, first, pageSize, result);
	}
	
	private PagedResult<System> doFindSystemByTextAndJsonQuery(String text, String jsonQuery,
			Integer start, Integer pageSize,
			List<System> result) throws UnsupportedEncodingException, ClassNotFoundException, InternalErrorException, EvalException, JSONException, ParseException, TokenMgrError {
		final SystemEntityDao dao = getSystemEntityDao();
		ScimHelper h = new ScimHelper(System.class);
		h.setPrimaryAttributes(new String[] { "name", "description"});
		CriteriaSearchConfiguration config = new CriteriaSearchConfiguration();
		config.setFirstResult(start);
		config.setMaximumResultSize(pageSize);
		h.setConfig(config);
		h.setTenantFilter("tenant.id");
		h.setGenerator((entity) -> {
			return dao.toSystem((SystemEntity) entity);
		}); 
		h.search(text, jsonQuery, (Collection) result); 


		PagedResult<System> pr = new PagedResult<>();
		pr.setStartIndex(start);
		pr.setItemsPerPage(pageSize);
		pr.setTotalResults(h.count());
		pr.setResources(result);
		return pr;
	}


	public void handleFinishVirtualSourceTransaction(String transactionId) throws InternalErrorException, InternalErrorException {
		getTaskEntityDao().finishVirtualSourceTransaction(transactionId);
	}

	@Override
	protected String handleStartVirtualSourceTransaction(boolean readonly, String server) throws Exception {
		return getTaskEntityDao().startVirtualSourceTransaction(readonly, server);
	}

	@Override
	protected void handleAddCertificate(Server server, X509Certificate cert) throws Exception {
		ServerEntity serverEntity = getServerEntityDao().load(server.getId());
		
		if (serverEntity != null) {
			ServerCertificateEntity certEntity = getServerCertificateEntityDao().newServerCertificateEntity();
			certEntity.setServer(serverEntity);
			certEntity.setSince(cert.getNotBefore());
			certEntity.setUntil(cert.getNotAfter());
			certEntity.setCert(cert.getEncoded());
			getServerCertificateEntityDao().create(certEntity);
		}
	}

	@Override
	protected List<X509Certificate> handleFindValidCertificates() throws Exception {
		List<X509Certificate> certs = new LinkedList<>();
		CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
		Date now = new Date();
		for (ServerCertificateEntity entity: getServerCertificateEntityDao().loadAll()) {
			if (now.after(entity.getSince()) && now.before(entity.getUntil())) {
				X509Certificate cert = (X509Certificate)certFactory.generateCertificate(new ByteArrayInputStream(entity.getCert()));
				certs.add(cert);
			}
		}
		return certs;
	}

	@Override
	protected Collection<Map<String, Object>> handleInvoke(String dispatcher, String verb, String object,
			Map<String, Object> attributes) throws Exception {
		Audit audit = new Audit();
		audit.setObject("SC_DISPAT");
		audit.setAction("I");
		audit.setCalendar(Calendar.getInstance());
		audit.setCustomObjectType(verb);
		audit.setCustomObjectName(object);
		audit.setDatabase(dispatcher);
		getAuditService().create(audit);
		SyncStatusService svc = ( SyncStatusService ) getSyncServerService().getServerService(SyncStatusService.REMOTE_PATH);
		
		if (svc == null)
			throw new InternalErrorException ("No sync server available");
		return svc.invoke(dispatcher, verb, object, attributes);
	}

}
