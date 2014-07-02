/**
 * 
 */
package com.soffid.iam.reconcile.service;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.soffid.iam.reconcile.common.AccountProposedAction;
import com.soffid.iam.reconcile.common.ProposedAction;
import com.soffid.iam.reconcile.common.ReconcileAccount;
import com.soffid.iam.reconcile.common.ReconcileAssignment;
import com.soffid.iam.reconcile.common.ReconcileRole;
import com.soffid.iam.reconcile.model.ReconcileAccountEntity;
import com.soffid.iam.reconcile.model.ReconcileAccountEntityDao;
import com.soffid.iam.reconcile.model.ReconcileAssignmentEntity;
import com.soffid.iam.reconcile.model.ReconcileAssignmentEntityDao;
import com.soffid.iam.reconcile.model.ReconcileRoleEntity;
import com.soffid.iam.reconcile.model.ReconcileRoleEntityDao;

import es.caib.bpm.toolkit.exception.UserWorkflowException;
import es.caib.bpm.toolkit.exception.WorkflowException;
import es.caib.seycon.ng.comu.Account;
import es.caib.seycon.ng.comu.AccountType;
import es.caib.seycon.ng.comu.Aplicacio;
import es.caib.seycon.ng.comu.Dispatcher;
import es.caib.seycon.ng.comu.Domini;
import es.caib.seycon.ng.comu.Rol;
import es.caib.seycon.ng.comu.RolAccount;
import es.caib.seycon.ng.comu.Usuari;
import es.caib.seycon.ng.comu.sso.NameParser;
import es.caib.seycon.ng.exception.AccountAlreadyExistsException;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.exception.NeedsAccountNameException;
import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.ng.model.AplicacioEntity;
import es.caib.seycon.ng.model.Parameter;
import es.caib.seycon.ng.model.TasqueEntity;
import es.caib.seycon.ng.model.TasqueEntityDao;
import es.caib.seycon.ng.model.UsuariEntityDao;
import es.caib.seycon.ng.servei.AccountService;
import es.caib.seycon.ng.servei.AplicacioService;
import es.caib.seycon.ng.servei.DispatcherService;
import es.caib.seycon.ng.servei.UsuariService;
import es.caib.seycon.ng.sync.engine.TaskHandler;
import es.caib.seycon.ng.sync.servei.TaskQueue;
import es.caib.seycon.ng.utils.TipusDomini;

/**
 * @author (C) Soffid 2013
 * 
 */
public class ReconcileServiceImpl extends ReconcileServiceBase implements ApplicationContextAware
{

	private ApplicationContext applicationContext;

	/*
	 * (non-Javadoc)
	 * @see com.soffid.iam.reconcyle.ReconcileServiceBase#handleAddUser(com.soffid.iam
	 * .reconcyle.common.ReconcileAccount)
	 */
	@Override
	protected ReconcileAccount handleAddUser (ReconcileAccount userInfo)
					throws Exception
	{
		ReconcileAccountEntity entity = getReconcileAccountEntityDao()
						.newReconcileAccountEntity();
		getReconcileAccountEntityDao().reconcileAccountToEntity(userInfo, entity, true);
		getReconcileAccountEntityDao().create(entity);

		return getReconcileAccountEntityDao().toReconcileAccount(entity);
	}

	/*
	 * (non-Javadoc)
	 * @see com.soffid.iam.reconcyle.ReconcileServiceBase#handleAddRole(com.soffid.iam
	 * .reconcyle.common.ReconcileRole)
	 */
	@Override
	protected ReconcileRole handleAddRole (ReconcileRole roleInfo) throws Exception
	{
		ReconcileRoleEntity entity = getReconcileRoleEntityDao()
						.newReconcileRoleEntity();

		getReconcileRoleEntityDao().reconcileRoleToEntity(roleInfo, entity, true);
		getReconcileRoleEntityDao().create(entity);

		return getReconcileRoleEntityDao().toReconcileRole(entity);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.soffid.iam.reconcyle.ReconcileServiceBase#handleAddAssignment(com.soffid
	 * .iam.reconcyle.common.ReconcileAssignment)
	 */
	@Override
	protected ReconcileAssignment handleAddAssignment (ReconcileAssignment assignmentInfo)
					throws Exception
	{
		ReconcileAssignmentEntity entity = getReconcileAssignmentEntityDao()
						.newReconcileAssignmentEntity();

		getReconcileAssignmentEntityDao().reconcileAssignmentToEntity(assignmentInfo,
						entity, true);
		getReconcileAssignmentEntityDao().create(entity);

		return getReconcileAssignmentEntityDao().toReconcileAssignment(entity);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.soffid.iam.reconcile.service.ReconcileServiceBase#handleCreateReconcileTask
	 * (java.lang.Long, java.lang.String)
	 */
	@Override
	protected void handleCreateReconcileTask (Long processID, String dispatcher)
					throws Exception
	{
		TasqueEntity entity = getTasqueEntityDao().newTasqueEntity();

		entity.setCoddis(dispatcher);
		entity.setMaquin(processID.toString());
		entity.setData(new Timestamp(new Date().getTime()));
		entity.setTransa(TaskHandler.RECONCILE_USERS);

		getTasqueEntityDao().create(entity);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.soffid.iam.reconcile.service.ReconcileServiceBase#handleIsPendingTasks(java
	 * .lang.Long, java.lang.Long)
	 */
	@Override
	protected boolean handleIsPendingTasks (Long processId, Long taskId)
					throws Exception
	{
		TasqueEntityDao entityDAO = getTasqueEntityDao();
		List<TasqueEntity> tasksList;
		boolean isPendingTask = false;

		TaskQueue tq = null;
		try
		{
			tq = (TaskQueue) applicationContext.getBean(TaskQueue.SERVICE_NAME);
		}
		catch (NoSuchBeanDefinitionException e)
		{
		}
		
		tasksList = entityDAO
						.query("select tas from es.caib.seycon.ng.model.TasqueEntity as tas where tas.maquin=:process", //$NON-NLS-1$
										new Parameter[] { new Parameter("process", Long //$NON-NLS-1$
														.toString(processId)) });

		// Analyze tasks
		for (TasqueEntity tasqueEntity : tasksList)
		{
			// Check pending task
			if (!tasqueEntity.getId().equals(taskId)
							&& !tasqueEntity.getStatus().equals("C")) //$NON-NLS-1$
			{
				if (tq == null)
					isPendingTask = true;
				else {
					TaskHandler th = tq.findTaskHandlerById(tasqueEntity.getId());
					if (th == null || ! th.isComplete())
					{
						isPendingTask = true;
					}
				}
			}
		}

		return isPendingTask;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.soffid.iam.reconcile.service.ReconcileServiceBase#handleUpdateUser(com.soffid
	 * .iam.reconcile.common.ReconcileAccount)
	 */
	@Override
	protected void handleUpdateUser (ReconcileAccount userInfo) throws Exception
	{
		ReconcileAccountEntity entity = getReconcileAccountEntityDao()
						.reconcileAccountToEntity(userInfo);

		getReconcileAccountEntityDao().update(entity);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.soffid.iam.reconcile.service.ReconcileServiceBase#handleUpdateRole(com.soffid
	 * .iam.reconcile.common.ReconcileRole)
	 */
	@Override
	protected void handleUpdateRole (ReconcileRole roleInfo) throws Exception
	{
		ReconcileRoleEntity entity = getReconcileRoleEntityDao().reconcileRoleToEntity(
						roleInfo);

		getReconcileRoleEntityDao().update(entity);
	}

	@Override
	protected void handleUpdateAssignment (ReconcileAssignment assignInfo)
					throws Exception
	{
		ReconcileAssignmentEntity entity = getReconcileAssignmentEntityDao()
						.reconcileAssignmentToEntity(assignInfo);

		getReconcileAssignmentEntityDao().update(entity);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.soffid.iam.reconcile.service.ReconcileServiceBase#handleFindReconRoleById(
	 * java.lang.Long)
	 */
	@Override
	protected ReconcileRole handleFindReconRoleById (Long roleID) throws Exception
	{
		ReconcileRoleEntityDao entityDAO = getReconcileRoleEntityDao();

		return getReconcileRoleEntityDao().toReconcileRole(entityDAO.load(roleID));
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.soffid.iam.reconcile.service.ReconcileServiceBase#handleFindReconAccountById
	 * (java.lang.Long)
	 */
	@Override
	protected ReconcileAccount handleFindReconAccountById (Long accountID)
					throws Exception
	{
		ReconcileAccountEntityDao entityDAO = getReconcileAccountEntityDao();

		return getReconcileAccountEntityDao().toReconcileAccount(
						entityDAO.load(accountID));
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.soffid.iam.reconcile.service.ReconcileServiceBase#handleFindReconAssignmentById
	 * (java.lang.Long)
	 */
	@Override
	protected ReconcileAssignment handleFindReconAssignmentById (Long assignID)
					throws Exception
	{
		ReconcileAssignmentEntityDao entityDAO = getReconcileAssignmentEntityDao();

		return getReconcileAssignmentEntityDao().toReconcileAssignment(
						entityDAO.load(assignID));
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.soffid.iam.reconcile.service.ReconcileServiceBase#handleFindAllReconAccounts
	 * (java.lang.Long)
	 */
	@Override
	protected List<ReconcileAccount> handleFindAllReconAccounts (Long processId)
					throws Exception
	{
		ReconcileAccountEntityDao entity = getReconcileAccountEntityDao();

		return getReconcileAccountEntityDao().toReconcileAccountList(
						entity.findByProcessId(processId));
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.soffid.iam.reconcile.service.ReconcileServiceBase#handleFindAllReconAssignment
	 * (java.lang.Long)
	 */
	@Override
	protected List<ReconcileAssignment> handleFindAllReconAssignment (Long processId)
					throws Exception
	{
		ReconcileAssignmentEntityDao entity = getReconcileAssignmentEntityDao();

		return getReconcileAssignmentEntityDao().toReconcileAssignmentList(
						entity.findByProcessId(processId));
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.soffid.iam.reconcile.service.ReconcileServiceBase#handleFindAllReconRole
	 * (java .lang.Long)
	 */
	@Override
	protected List<ReconcileRole> handleFindAllReconRole (Long processId)
					throws Exception
	{
		ReconcileRoleEntityDao entity = getReconcileRoleEntityDao();

		return getReconcileRoleEntityDao().toReconcileRoleList(
						entity.findByProcessId(processId));
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.soffid.iam.reconcile.service.ReconcileServiceBase#handleReconcileUsers(java
	 * .lang.Long)
	 */
	@Override
	protected void handleReconcileUsers (Long processId) throws Exception
	{
		// Process reconcile account tasks
		for (ReconcileAccount account : handleFindAllReconAccounts(processId))
		{
			validateReconcileAccount(account);

			// Binding existing user
			if (account.getProposedAction().equals(
							AccountProposedAction.BIND_TO_EXISTING_USER))
			{
				bindExistingUser(account);
			}

			// Create new user
			if (account.getProposedAction()
							.equals(AccountProposedAction.CREATE_NEW_USER))
			{
				createNewUserAccount(account);
			}

			// Independent account
			if (account.getProposedAction().equals(AccountProposedAction.SHARED))
			{
				createSharedAccount(account);
			}
		}
	}

	/**
	 * Created shared account
	 * 
	 * <p>
	 * Implements the functionality to create a new shared account.
	 * 
	 * @param account
	 *            Account data to create.
	 * @throws InternalErrorException
	 * @throws AccountAlreadyExistsException
	 * @throws NeedsAccountNameException
	 * @throws UserWorkflowException
	 */
	private void createSharedAccount (ReconcileAccount account)
					throws InternalErrorException, AccountAlreadyExistsException,
					NeedsAccountNameException, UserWorkflowException
	{
		AccountService accountServ = getAccountService(); // Account handler
		Account newAccount = new Account(); // Account data to create

		newAccount.setName(account.getAccountName());
		newAccount.setDescription(account.getDescription());
		newAccount.setDispatcher(account.getDispatcher());
		newAccount.setType(account.getAccountType());
		newAccount.setPasswordPolicy(account.getUserType());
		newAccount.setGrantedGroups(Collections.EMPTY_LIST);
		newAccount.setGrantedRoles(Collections.EMPTY_LIST);
		newAccount.setGrantedUsers(Collections.EMPTY_LIST);

		// Check single shared account
		if (account.getAccountType().equals(AccountType.USER))
		{
			createNewUserAccount(account);
		}

		else
		{
			accountServ.createAccount(newAccount);
		}
	}

	/**
	 * Bind to existing user
	 * 
	 * <p>
	 * Implements the functionality to bind a existing user data to an account.
	 * 
	 * @param account
	 *            Reconcile account with all data to reconcile.
	 * @throws InternalErrorException
	 * @throws UserWorkflowException
	 * @throws NeedsAccountNameException
	 * @throws AccountAlreadyExistsException
	 */
	private void bindExistingUser (ReconcileAccount account)
					throws InternalErrorException, UserWorkflowException,
					NeedsAccountNameException, AccountAlreadyExistsException
	{
		AccountService accountServ = getAccountService(); // Account handler
		DispatcherService dispService = getDispatcherService(); // Dispatcher handler
		Usuari user = null; // User data
		Dispatcher disp = null; // Dispatcher data
		UsuariEntityDao entity = getUsuariEntityDao(); // Get user data from DB handler

		user = getUsuariEntityDao().toUsuari(entity.findByCodi(account.getUserCode()));
		disp = dispService.findDispatcherByCodi(account.getDispatcher());

		// Check valid obtained user
		if (user == null)
		{
			throw new UserWorkflowException(String.format(
							Messages.getString("ReconcileServiceImpl.UserNotFound"), //$NON-NLS-1$
							account.getUserCode()));
		}

		// Check valid obtained dispatcher
		if (disp == null)
		{
			throw new UserWorkflowException(String.format(Messages
							.getString("ReconcileServiceImpl.DispatcherNotFound"), //$NON-NLS-1$
							account.getDispatcher()));
		}

		try
		{
			accountServ.createAccount(user, disp, account.getAccountName());
		}

		catch (AccountAlreadyExistsException ex)
		{
			throw new AccountAlreadyExistsException(String.format(
							Messages.getString("ReconcileServiceImpl.ExistingAccountError"), //$NON-NLS-1$
							account.getAccountName()));
		}
	}

	/**
	 * Load user data to account.
	 * 
	 * <p>
	 * Implements the functionality to create user data for an account.
	 * 
	 * @param account
	 *            Reconcile account with all data to reconcile.
	 * @throws InternalErrorException
	 * @throws UserWorkflowException
	 * @throws NeedsAccountNameException
	 * @throws AccountAlreadyExistsException
	 */
	private void createNewUserAccount (ReconcileAccount account)
					throws InternalErrorException, UserWorkflowException,
					NeedsAccountNameException, AccountAlreadyExistsException
	{
		AccountService accountServ = getAccountService(); // Account handler
		DispatcherService dispService = getDispatcherService(); // Dispatcher handler
		UsuariService userServ = getUsuariService(); // Service to add user info
		Usuari user = null; // User data
		Dispatcher disp = null; // Dispatcher data

		user = createUserByReconcileAccount(account);
		userServ.create(user);

		disp = dispService.findDispatcherByCodi(account.getDispatcher());

		// Check valid obtained dispatcher
		if (disp == null)
		{
			throw new UserWorkflowException(String.format(Messages
							.getString("ReconcileServiceImpl.DispatcherNotFound"), //$NON-NLS-1$
							account.getDispatcher()));
		}

		// Check existing account
		if (accountServ.findAccount(account.getAccountName(), account.getDispatcher()) == null)
		{
			accountServ.createAccount(user, disp, account.getAccountName());
		}
	}

	/**
	 * Create user by reconcile account data.
	 * 
	 * <p>
	 * Implements the functionality to create a "Usuari" instance by data in reconcile
	 * account.
	 * 
	 * @param recAccount
	 *            Reconcile account to obtain user data.
	 * @return User data obtained.
	 */
	private Usuari createUserByReconcileAccount (ReconcileAccount recAccount)
	{
		NameParser nameParser = new NameParser(); // Class to process user full names
		Usuari user = new Usuari(); // User data to add
		String[] userFullName; // User full name
		int numWords = 0; // Number of words in full name

		numWords = countNumWordsInString(recAccount.getDescription());
		userFullName = nameParser.parse(recAccount.getDescription(), numWords);

		user.setCodi(recAccount.getAccountName());
		user.setNom(userFullName[0]);

		// Check user surname 1
		if (userFullName.length > 1)
		{
			user.setPrimerLlinatge(userFullName[1]);
		}

		else
		{
			user.setPrimerLlinatge(recAccount.getAccountName());
		}

		// Check user surname 2
		if (userFullName.length > 2)
		{
			user.setSegonLlinatge(userFullName[2]);
		}

		user.setServidorPerfil("null"); //$NON-NLS-1$
		user.setServidorCorreu("null"); //$NON-NLS-1$
		user.setServidorHome("null"); //$NON-NLS-1$
		user.setCodiGrupPrimari(recAccount.getPrimaryGroup());
		user.setTipusUsuari(recAccount.getUserType());
		user.setActiu(recAccount.isActive());

		return user;
	}

	/**
	 * Count number of words in string.
	 * 
	 * @param fullName
	 *            String to process.
	 * @return Number of words.
	 */
	private int countNumWordsInString (String fullName)
	{
		int numWords = 1; // Number of words in string
		String separator = " "; // Separator char or words //$NON-NLS-1$

		while (fullName.indexOf(separator) > -1)
		{
			fullName = fullName.substring(
							fullName.indexOf(separator) + separator.length(),
							fullName.length());

			numWords++;
		}

		return numWords;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.soffid.iam.reconcile.service.ReconcileServiceBase#handleReconcileRoles(java
	 * .lang.Long)
	 */
	@Override
	protected void handleReconcileRoles (Long processId) throws Exception
	{
		Rol role = null; // Role to add
		AplicacioService appService = getAplicacioService(); // Add role handler

		for (ReconcileRole recRole : handleFindAllReconRole(processId))
		{
			// Check reconcile role action
			if (recRole.getProposedAction().equals(ProposedAction.LOAD))
			{
				validateReconcileRole(recRole);

				role = new Rol();

				role.setNom(recRole.getRoleName());
				role.setDescripcio(recRole.getDescription());
				if (recRole.getAppName() == null || recRole.getAppName().isEmpty())
					throw new UserWorkflowException(String.format(Messages
									.getString("ReconcileServiceImpl.AppCodeNeeded"), //$NON-NLS-1$
									recRole.getRoleName()));
				else
					role.setCodiAplicacio(recRole.getAppName());

				role.setBaseDeDades(recRole.getDispatcher());
				role.setDomini(new Domini(TipusDomini.SENSE_DOMINI));

				appService.create(role);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.soffid.iam.reconcile.service.ReconcileServiceBase#handleReconcileAssignment
	 * (java.lang.Long)
	 */
	@Override
	protected void handleReconcileAssignment (Long processId) throws Exception
	{
		Account account = null; // Account data to reconcile assignment
		Collection<Rol> rolesList = null; // Roles found
		Rol role = null;// Role to assign
		AccountService accServ = getAccountService(); // Account info handler
		AplicacioService appService = getAplicacioService(); // Role info handler
		RolAccount accountRole = null; // Account-role to create

		for (ReconcileAssignment assignmentAccount : handleFindAllReconAssignment(processId))
		{
			if (assignmentAccount.getProposedAction().equals(ProposedAction.LOAD))
			{
				account = accServ.findAccount(assignmentAccount.getAccountName(),
								assignmentAccount.getDispatcher());

				// Check account stored
				if (account != null)
				{
					rolesList = appService.findRolsByFiltre(
									assignmentAccount.getRoleName(), null, null,
									assignmentAccount.getDispatcher(), null, null);

					// Check collection correct size
					if (rolesList.size() > 1)
					{
						throw new UserWorkflowException(
										String.format(Messages
														.getString("ReconcileServiceImpl.InvalidRoleListSize"), //$NON-NLS-1$
														assignmentAccount.getRoleName()));
					}

					// Check existing role for account
					if (rolesList.iterator().hasNext())
					{
						role = rolesList.iterator().next();
					}

					else
					{
						throw new UserWorkflowException(
										String.format(Messages
														.getString("ReconcileServiceImpl.NotReconciledRole"), //$NON-NLS-1$
														assignmentAccount.getRoleName(),
														assignmentAccount
																		.getAccountName()));
					}

					// Check previous assignment reconciled
					boolean found = false;
					for (RolAccount ra: appService.findRolAccountByAccount(account.getId()))
					{
						if (ra.getNomRol().equals(assignmentAccount.getRoleName()) &&
							ra.getBaseDeDades().equals(assignmentAccount.getDispatcher()))
						{
							found = true ;
							break;
						}
					}
					if (! found) 
					{
						accountRole = new RolAccount();
						accountRole.setBaseDeDades(assignmentAccount.getDispatcher());
						accountRole.setDescripcioRol(role.getDescripcio());
						accountRole.setAccountName(account.getName());
						accountRole.setNomRol(role.getNom());
						accountRole.setCodiAplicacio(role.getCodiAplicacio());

						appService.create(accountRole);
					}
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.soffid.iam.reconcile.service.ReconcileServiceBase#handleReconcileData(java
	 * .lang.Long)
	 */
	@Override
	protected void handleReconcileData (Long processId) throws Exception
	{
		handleReconcileUsers(processId);
		handleReconcileRoles(processId);
		handleReconcileAssignment(processId);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.soffid.iam.reconcile.service.ReconcileServiceBase#handleValidateReconcileAccount
	 * (com.soffid.iam.reconcile.common.ReconcileAccount)
	 */
	@Override
	protected void handleValidateReconcileAccount (ReconcileAccount accountinfo)
					throws Exception
	{
		// Check binding user action
		if (accountinfo.getProposedAction().equals(
						AccountProposedAction.BIND_TO_EXISTING_USER))
		{
			// Check selected user code
			if (accountinfo.getUserCode().isEmpty())
			{
				throw new UserWorkflowException(String.format(
								Messages.getString("ReconcileServiceImpl.UserRequired"), //$NON-NLS-1$
								accountinfo.getAccountName()));
			}
		}

		// Check create new user action
		if (accountinfo.getProposedAction()
						.equals(AccountProposedAction.CREATE_NEW_USER))
		{
			// Check account group
			if (accountinfo.getPrimaryGroup() == null)
			{
				throw new UserWorkflowException(
								String.format(Messages
												.getString("ReconcileServiceImpl.GroupRequired"), //$NON-NLS-1$
												accountinfo.getAccountName()));
			}

			// Check user type
			if (accountinfo.getUserType() == null)
			{
				throw new UserWorkflowException(
								String.format(Messages
												.getString("ReconcileServiceImpl.UserTypeForUserRequired"), //$NON-NLS-1$
												accountinfo.getAccountName()));
			}
		}

		// Check share account action
		if (accountinfo.getProposedAction().equals(AccountProposedAction.SHARED))
		{
			// Check user type
			if (accountinfo.getUserType() == null)
			{
				throw new UserWorkflowException(
								String.format(Messages
												.getString("ReconcileServiceImpl.UserTypeForAccountRequired"), //$NON-NLS-1$
												accountinfo.getAccountName()));
			}

			// Check account type
			if (accountinfo.getAccountType() == null)
			{
				throw new UserWorkflowException(String.format(Messages
								.getString("ReconcileServiceImpl.AccountTypeRequired"), //$NON-NLS-1$
								accountinfo.getAccountName()));
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.soffid.iam.reconcile.service.ReconcileServiceBase#handleValidateReconcileRole
	 * (com.soffid.iam.reconcile.common.ReconcileRole)
	 */
	@Override
	protected void handleValidateReconcileRole (ReconcileRole roleInfo) throws Exception
	{
		// Check application selected
		if (roleInfo.getAppName() == null)
		{
			throw new UserWorkflowException(String.format(Messages
							.getString("ReconcileServiceImpl.ApplicationRequired"), //$NON-NLS-1$
							roleInfo.getRoleName()));
		}

		AplicacioService appService = getAplicacioService(); // Add role handler
		Aplicacio application = appService.findAplicacioByCodiAplicacio(roleInfo
						.getAppName());

		// Check existing application
		if (application == null)
		{
			throw new UserWorkflowException(
				String.format(Messages.getString("ReconcileServiceImpl.NoExistingAppError"), //$NON-NLS-1$
					roleInfo.getAppName(), roleInfo.getRoleName()));
		}
	}

	/* (non-Javadoc)
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 */
	public void setApplicationContext (ApplicationContext applicationContext)
					throws BeansException
	{
		this.applicationContext = applicationContext ;
	}
}
