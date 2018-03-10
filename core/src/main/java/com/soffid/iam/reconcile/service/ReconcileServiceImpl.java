/**
 * 
 */
/**
 * 
 */
/**
 * 
 */
package com.soffid.iam.reconcile.service;

import com.soffid.iam.api.Account;
import com.soffid.iam.api.AccountStatus;
import com.soffid.iam.api.Application;
import com.soffid.iam.api.Domain;
import com.soffid.iam.api.DomainValue;
import com.soffid.iam.api.Role;
import com.soffid.iam.api.RoleAccount;
import com.soffid.iam.api.User;
import com.soffid.iam.api.UserAccount;
import com.soffid.iam.model.TaskEntity;
import com.soffid.iam.model.TaskEntityDao;
import com.soffid.iam.model.UserEntityDao;
import com.soffid.iam.reconcile.common.AccountProposedAction;
import com.soffid.iam.reconcile.common.ProposedAction;
import com.soffid.iam.reconcile.common.ReconcileAccount;
import com.soffid.iam.reconcile.common.ReconcileAssignment;
import com.soffid.iam.reconcile.common.ReconcileRole;
import com.soffid.iam.reconcile.model.ReconcileAccountAttributesEntity;
import com.soffid.iam.reconcile.model.ReconcileAccountEntity;
import com.soffid.iam.reconcile.model.ReconcileAccountEntityDao;
import com.soffid.iam.reconcile.model.ReconcileAssignmentEntity;
import com.soffid.iam.reconcile.model.ReconcileAssignmentEntityDao;
import com.soffid.iam.reconcile.model.ReconcileRoleEntity;
import com.soffid.iam.reconcile.model.ReconcileRoleEntityDao;
import com.soffid.iam.service.AccountService;
import com.soffid.iam.service.ApplicationService;
import com.soffid.iam.service.DispatcherService;
import com.soffid.iam.service.UserService;
import com.soffid.iam.sync.engine.TaskHandler;
import com.soffid.iam.util.NameParser;

import es.caib.bpm.toolkit.exception.UserWorkflowException;
import es.caib.seycon.ng.comu.AccountType;
import es.caib.seycon.ng.comu.TipusDomini;
import es.caib.seycon.ng.exception.AccountAlreadyExistsException;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.exception.NeedsAccountNameException;
import es.caib.seycon.ng.sync.servei.TaskQueue;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

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
		
		if (userInfo.getAttributes() != null )
			for (String att: userInfo.getAttributes().keySet())
			{
				Object value = userInfo.getAttributes().get(att);
				if (value != null)
				{
					if (value instanceof String)
					{
						ReconcileAccountAttributesEntity at = getReconcileAccountAttributesEntityDao().newReconcileAccountAttributesEntity();
						at.setAccount(entity);
						at.setAttribute(att);
						at.setValue((String) value);
						entity.getAttributes().add(at);
						getReconcileAccountAttributesEntityDao().create(at);
					}
				}
			}

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
		TaskEntity entity = getTaskEntityDao().newTaskEntity();

		entity.setSystemName(dispatcher);
		entity.setHost(processID.toString());
		entity.setDate(new Timestamp(new Date().getTime()));
		entity.setTransaction(TaskHandler.RECONCILE_USERS);

		getTaskEntityDao().create(entity);
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
		TaskEntityDao entityDAO = getTaskEntityDao();
		List<TaskEntity> tasksList;
		boolean isPendingTask = false;

		TaskQueue tq = null;
		try
		{
			tq = (TaskQueue) applicationContext.getBean(TaskQueue.SERVICE_NAME);
		}
		catch (NoSuchBeanDefinitionException e)
		{
		}
		
		tasksList = entityDAO.findByHost(Long.toString(processId)) ;

		// Analyze tasks
		for (TaskEntity tasqueEntity : tasksList) {
            if (!tasqueEntity.getId().equals(taskId) && !tasqueEntity.getStatus().equals("C")) {
                if (tq == null) isPendingTask = true; else {
                    TaskHandler th = tq.findTaskHandlerById(tasqueEntity.getId());
                    if (th == null || !th.isComplete()) {
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
							AccountProposedAction.BIND_TO_EXISTING_USER) && account.getNewAccount().booleanValue())
			{
				bindExistingUser(account);
			}

			// Create new user
			if (account.getProposedAction()
							.equals(AccountProposedAction.CREATE_NEW_USER)
							 && account.getNewAccount().booleanValue())
			{
				createNewUserAccount(account);
			}

			// Independent account
			if (account.getProposedAction().equals(AccountProposedAction.SHARED)
					 && account.getNewAccount().booleanValue())
			{
				createSharedAccount(account);
			}

			if (account.getProposedAction().equals(AccountProposedAction.UPDATE_ACCOUNT)
					 && ! account.getNewAccount().booleanValue()
					 && ! account.getDeletedAccount().booleanValue())
			{
				updateAccount(account);
			}

			if (account.getProposedAction().equals(AccountProposedAction.DELETE_ACCOUNT)
					 && ! account.getDeletedAccount().booleanValue())
			{
				deleteAccount(account);
			}
		}
	}

	private void deleteAccount(ReconcileAccount account) throws InternalErrorException {
		Account previous = getAccountService().findAccount(account.getAccountName(), account.getDispatcher());
		if (previous != null)
		{
			for ( RoleAccount grant : getApplicationService().findRoleAccountByAccount(previous.getId()))
			{
				getApplicationService().delete(grant);
			}
			getAccountService().removeAccount(previous);
		}
	}

	private void updateAccount(ReconcileAccount account) throws InternalErrorException {
		Account previous = getAccountService().findAccount(account.getAccountName(), account.getDispatcher());
		if (previous == null)
			throw new InternalErrorException("Cannot update non existing account: "+account.getAccountName());
		previous.setDescription(account.getDescription());
		previous.setStatus(account.isActive()? AccountStatus.ACTIVE: AccountStatus.DISABLED);
		previous.setDisabled(! account.isActive());
		previous.getAttributes().putAll(account.getAttributes());
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
		newAccount.setSystem(account.getDispatcher());
		newAccount.setType(account.getAccountType());
		newAccount.setPasswordPolicy(account.getUserType());
		newAccount.setGrantedGroups(Collections.EMPTY_LIST);
		newAccount.setGrantedRoles(Collections.EMPTY_LIST);
		newAccount.setGrantedUsers(Collections.EMPTY_LIST);
		newAccount.setAttributes(account.getAttributes());
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
		User user = null; // User data
		com.soffid.iam.api.System disp = null; // Dispatcher data
		UserEntityDao entity = getUserEntityDao(); // Get user data from DB handler

		user = getUserEntityDao().toUser(entity.findByUserName(account.getUserCode()));
		disp = dispService.findDispatcherByName(account.getDispatcher());

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
			UserAccount acc = accountServ.createAccount(user, disp, account.getAccountName());
			acc.setAttributes(account.getAttributes());
			accountServ.updateAccount(acc);
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
		UserService userServ = getUserService(); // Service to add user info
		User user = null; // User data
		com.soffid.iam.api.System disp = null; // Dispatcher data

		user = createUserByReconcileAccount(account);
		userServ.create(user);

		disp = dispService.findDispatcherByName(account.getDispatcher());

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
	private User createUserByReconcileAccount(ReconcileAccount recAccount) {
		NameParser nameParser = new NameParser(); // Class to process user full names
		User user = new User(); // User data to add
		String[] userFullName; // User full name
		int numWords = 0; // Number of words in full name

		numWords = countNumWordsInString(recAccount.getDescription());
		userFullName = nameParser.parse(recAccount.getDescription(), numWords);

		user.setUserName(recAccount.getAccountName());
		user.setFirstName(userFullName[0]);

		// Check user surname 1
		if (userFullName.length > 1)
		{
			user.setLastName(userFullName[1]);
		}

		else
		{
			user.setLastName(recAccount.getAccountName());
		}

		// Check user surname 2
		if (userFullName.length > 2)
		{
			user.setMiddleName(userFullName[2]);
		}

		user.setProfileServer("null"); //$NON-NLS-1$
		user.setMailServer("null"); //$NON-NLS-1$
		user.setHomeServer("null"); //$NON-NLS-1$
		user.setPrimaryGroup(recAccount.getPrimaryGroup());
		user.setUserType(recAccount.getUserType());
		user.setActive(recAccount.isActive());

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
		Role role = null; // Role to add
		ApplicationService appService = getApplicationService(); // Add role handler

		for (ReconcileRole recRole : handleFindAllReconRole(processId)) {
            if (recRole.getProposedAction().equals(ProposedAction.LOAD)) {
                validateReconcileRole(recRole);
                role = new Role();
                role.setName(recRole.getRoleName());
                role.setDescription(recRole.getDescription());
                if (recRole.getAppName() == null || recRole.getAppName().isEmpty()) 
                	throw new UserWorkflowException(String.format(
                			Messages.getString("ReconcileServiceImpl.AppCodeNeeded"), recRole.getRoleName())); 
                else 
                	role.setInformationSystemName(recRole.getAppName());
                role.setSystem(recRole.getDispatcher());
                role.setDomain(new Domain(TipusDomini.SENSE_DOMINI));
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
		Collection<Role> rolesList = null; // Roles found
		Role role = null;// Role to assign
		AccountService accServ = getAccountService(); // Account info handler
		ApplicationService appService = getApplicationService(); // Role info handler
		RoleAccount accountRole = null; // Account-role to create

		for (ReconcileAssignment assignmentAccount : handleFindAllReconAssignment(processId)) {
            if (assignmentAccount.getProposedAction().equals(ProposedAction.LOAD)) {
                account = accServ.findAccount(assignmentAccount.getAccountName(), assignmentAccount.getDispatcher());
                if (account != null) {
                    rolesList = appService.findRolesByFilter(assignmentAccount.getRoleName(), null, null, assignmentAccount.getDispatcher(), null, null);
                    if (rolesList.size() > 1) {
                        throw new UserWorkflowException(String.format(Messages.getString("ReconcileServiceImpl.InvalidRoleListSize"), assignmentAccount.getRoleName()));
                    }
                    if (rolesList.iterator().hasNext()) {
                        role = rolesList.iterator().next();
                    } else {
                        throw new UserWorkflowException(String.format(Messages.getString("ReconcileServiceImpl.NotReconciledRole"), assignmentAccount.getRoleName(), assignmentAccount.getAccountName()));
                    }
                    boolean found = false;
                    for (RoleAccount ra : appService.findRoleAccountByAccount(account.getId())) {
                        if (ra.getRoleName().equals(assignmentAccount.getRoleName()) && ra.getSystem().equals(assignmentAccount.getDispatcher())) {
                            if (assignmentAccount.getDomainValue() == null || ra.getDomainValue() == null || assignmentAccount.getDomainValue().equals(ra.getDomainValue().getValue())) {
                                found = true;
                                break;
                            }
                        }
                    }
                    if (!found) {
                        accountRole = new RoleAccount();
                        accountRole.setSystem(assignmentAccount.getDispatcher());
                        accountRole.setRoleDescription(role.getDescription());
                        accountRole.setAccountName(account.getName());
                        accountRole.setRoleName(role.getName());
                        accountRole.setInformationSystemName(role.getInformationSystemName());
                        if (assignmentAccount.getDomainValue() != null) {
                            accountRole.setDomainValue(new DomainValue());
                            accountRole.getDomainValue().setValue(assignmentAccount.getDomainValue());
                        }
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

		ApplicationService appService = getApplicationService(); // Add role handler
		Application application = appService.findApplicationByApplicationName(roleInfo.getAppName());

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
