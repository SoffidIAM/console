/**
 * 
 */
/**
 * 
 */
/**
 * 
 */
package com.soffid.iam.service;

import bsh.EvalError;
import bsh.Interpreter;

import com.soffid.iam.api.DomainValue;
import com.soffid.iam.api.Group;
import com.soffid.iam.api.RoleAccount;
import com.soffid.iam.api.Task;
import com.soffid.iam.api.User;
import com.soffid.iam.api.UserAccount;
import com.soffid.iam.model.AccountEntity;
import com.soffid.iam.model.DomainValueEntity;
import com.soffid.iam.model.GroupEntity;
import com.soffid.iam.model.RoleAccountEntity;
import com.soffid.iam.model.RoleAccountEntityDao;
import com.soffid.iam.model.RoleEntity;
import com.soffid.iam.model.RuleAssignedRoleEntity;
import com.soffid.iam.model.RuleEntity;
import com.soffid.iam.model.TaskEntity;
import com.soffid.iam.model.UserAccountEntity;
import com.soffid.iam.model.UserDataEntity;
import com.soffid.iam.model.UserEntity;
import com.soffid.iam.model.UserGroupEntity;
import com.soffid.iam.sync.engine.TaskHandler;
import com.soffid.iam.utils.Security;

import es.caib.seycon.ng.comu.AccountType;
import es.caib.seycon.ng.comu.TipusDomini;
import es.caib.seycon.ng.exception.AccountAlreadyExistsException;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.exception.NeedsAccountNameException;

import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author bubu
 *
 */
public class RuleEvaluatorServiceImpl extends RuleEvaluatorServiceBase implements ApplicationContextAware
{

	private ApplicationContext ctx;

	/**
	 * 
	 */
	public RuleEvaluatorServiceImpl ()
	{
	}

	protected void doApply(RuleEntity rule, UserEntity user, InterpreterEnvironment env) throws Exception {
		Security.nestedLogin(Security.getCurrentAccount(), new String [] {
			Security.AUTO_AUTHORIZATION_ALL
		});
		try {
			RoleAccountEntityDao raDao = getRoleAccountEntityDao();
			Object result = null;
			if ("S".equals(user.getActive())) //$NON-NLS-1$
				result = evaluate (rule.getBshExpression(), env);
			if (result != null && ! (result instanceof Boolean))
			{
				throw new InternalErrorException (String.format(Messages.getString("RuleEvaluatorServiceImpl.NotBooleanReturn"), result.toString())); //$NON-NLS-1$
			}
			List<RoleAccountEntity> roles = raDao.findAllByUserName(user.getUserName());
			if (result != null && ((Boolean) result).booleanValue())
			{
				for (RuleAssignedRoleEntity rar : rule.getRoles()) {
                    DomainValueEntity valor = null;
                    String stringValue = null;
                    if (!rar.getRole().getDomainType().equals(TipusDomini.SENSE_DOMINI)) {
                        if (rar.getDomainValue() != null && rar.getDomainValue().length() > 0) {
                            stringValue = rar.getDomainValue();
                        } else if (rar.getBshDomainValueExpression() != null && rar.getBshDomainValueExpression().length() > 0) {
                            Object obj = evaluate(rar.getBshDomainValueExpression(), env);
                            if (obj != null) stringValue = obj.toString();
                        }
                    }
                    assignRole(rule, roles, user, rar.getRole(), stringValue);
                }
			}
			// Now remove unneded roles
			for (RoleAccountEntity role : roles) {
                if (role.getRule() == rule) {
                    RoleAccount r = getRoleAccountEntityDao().toRoleAccount(role);
                    getApplicationService().update(r);
                    raDao.remove(role);
                    Task updateRole = new Task();
                    updateRole.setTransaction("UpdateRole");
                    updateRole.setTaskDate(Calendar.getInstance());
                    updateRole.setStatus("P");
                    updateRole.setRole(r.getRoleName());
                    updateRole.setDatabase(r.getSystem());
                    TaskEntity tasca = getTaskEntityDao().taskToEntity(updateRole);
                    getTaskEntityDao().create(tasca);
                    Task updateUser = new Task();
                    updateUser.setTransaction(TaskHandler.UPDATE_USER);
                    updateUser.setTaskDate(Calendar.getInstance());
                    updateUser.setStatus("P");
                    updateUser.setUser(user.getUserName());
                    TaskEntity tasca2 = getTaskEntityDao().taskToEntity(updateUser);
                    getTaskEntityDao().create(tasca2);
                }
            }
		} catch (Exception e) {
			throw new InternalErrorException(String.format(Messages.getString("RuleEvaluatorServiceImpl.EvaluationRuleError"), rule.getDescription(), user.getUserName()),
					e);
		} finally {
			Security.nestedLogoff();
		}
	}

	/* (non-Javadoc)
	 * @see com.soffid.iam.service.RuleEvaluatorServiceBase#handleApply(com.soffid.iam.model.RuleEntity, es.caib.seycon.ng.model.UsuariEntity)
	 */
	@Override
    protected void handleApply(RuleEntity rule, UserEntity user) throws Exception {
		doApply(rule, user, new InterpreterEnvironment(user));
	}
		
	/**
	 * @param rule 
	 * @param roles
	 * @param role
	 * @param stringValue
	 * @throws InternalErrorException 
	 * @throws AccountAlreadyExistsException 
	 * @throws NeedsAccountNameException 
	 */
	private void assignRole(RuleEntity rule, List<RoleAccountEntity> roles, UserEntity user, RoleEntity role, String stringValue) throws InternalErrorException, NeedsAccountNameException, AccountAlreadyExistsException {
		// First. Test if role is already assigned
		for (Iterator<RoleAccountEntity> it = roles.iterator(); it.hasNext(); ) {
            RoleAccountEntity ra = it.next();
            boolean match = false;
            if (ra.getRole().getId().equals(role.getId())) {
                if (ra.getDomainValue() != null) {
                    if (ra.getDomainValue().getValue().equals(stringValue)) match = true;
                } else if (ra.getGroup() != null) {
                    if (ra.getGroup().getName().equals(stringValue)) match = true;
                } else if (ra.getInformationSystem() != null) {
                    if (ra.getInformationSystem().getName().equals(stringValue)) match = true;
                } else {
                    if (stringValue == null) match = true;
                }
            }
            if (match) {
                it.remove();
                return;
            }
        }
		
		// Second. Assign now
		List<AccountEntity> accounts = getAccountsForRole(user, role);
		RoleAccount ra = generateRolAccount(rule, role, stringValue);
		if (accounts.isEmpty())
		{
			UserAccount account = getAccountService().createAccount(getUserEntityDao().toUser(user), getSystemEntityDao().toSystem(role.getSystem()), null);
			ra.setAccountId(account.getId());
			ra.setAccountName(account.getName());
			Security.nestedLogin(Security.getCurrentAccount (), new String[] {
				Security.AUTO_USER_ROLE_CREATE+Security.AUTO_ALL
			});
			try {
				getApplicationService().create(ra);
			} finally {
				Security.nestedLogoff();
			}
		} else {
			for (AccountEntity account : accounts) {
                ra.setAccountId(account.getId());
                ra.setAccountName(account.getName());
                getApplicationService().create(ra);
            }
		}
		
	}

	/**
	 * @param rule 		
	 * @param role
	 * @param stringValue
	 * @return
	 */
	private RoleAccount generateRolAccount(RuleEntity rule, RoleEntity role, String stringValue) {
		RoleAccount ra = new RoleAccount();
        ra.setAccountSystem(role.getSystem().getName());
        ra.setSystem(role.getSystem().getName());
        ra.setInformationSystemName(role.getInformationSystem().getName());
        ra.setRoleName(role.getName());
        ra.setRuleId(rule.getId());
        ra.setRuleDescription(rule.getDescription());
        if (stringValue != null)
        {
        	DomainValue vd = new DomainValue();
        	vd.setValue(stringValue);
    		vd.setDescription("??"); //$NON-NLS-1$
        	if (role.getApplicationDomain() != null)
        	{
        		vd.setDomainName(role.getApplicationDomain().getName());
        		vd.setExternalCodeDomain(role.getApplicationDomain().getName());
        	} 
        	else if (role.getDomainType() != null)
        	{
        		vd.setDomainName(role.getDomainType());
        		vd.setExternalCodeDomain(role.getDomainType());
        	}
    		ra.setDomainValue(vd);
        }
        return ra;
	}

	/**
	 * @param user
	 * @param role
	 * @return
	 */
	private List<AccountEntity> getAccountsForRole(UserEntity user, RoleEntity role) {
		LinkedList<AccountEntity> accounts = new LinkedList<AccountEntity>();
		for (UserAccountEntity ua : user.getAccounts()) {
            AccountEntity account = ua.getAccount();
            if (account.getType().equals(AccountType.USER) && account.getSystem().getId().equals(role.getSystem().getId())) {
                accounts.add(ua.getAccount());
            }
        }
		return accounts;
	}

	/* (non-Javadoc)
	 * @see com.soffid.iam.service.RuleEvaluatorServiceBase#handleApplyRules(es.caib.seycon.ng.model.UsuariEntity)
	 */
	@Override
    protected void handleApplyRules(UserEntity user) throws Exception {
		List<RuleEntity> rules = getRuleEntityDao().loadAll();
		if (! rules.isEmpty())
		{
    		InterpreterEnvironment env = new InterpreterEnvironment(user);
    		for (RuleEntity rule: rules)
    		{
    			doApply (rule, user, env);
    		}
		}
		
		for (String name : ctx.getBeanNamesForType(SoffidEventListener.class))
		{
			SoffidEventListener bean = (SoffidEventListener) ctx.getBean(name);
			if (bean != null)
				bean.onUserChange(user);
		}
	}

	/* (non-Javadoc)
	 * @see com.soffid.iam.service.RuleEvaluatorServiceBase#handleApply(com.soffid.iam.model.RuleEntity)
	 */
	@Override
	protected void handleApply (RuleEntity rule) throws Exception
	{
		for (UserEntity u : getUserEntityDao().loadAll()) {
            apply(rule, u);
        }
	}

	private Object evaluate (String expression, InterpreterEnvironment env) throws EvalError
	{
		Interpreter interpreter = env.getInterpeter();
		return interpreter.eval(expression);
	}

	/**
	 * @param groups
	 * @param grup
	 */
	private void addGroups(HashMap<String, Group> groups, GroupEntity grup) {
		if (!groups.containsKey(grup.getName()))
		{
			Group grupVO = getGroupEntityDao().toGroup(grup);
			groups.put(grup.getName(), (grupVO));
			if (grup.getParent() != null)
				addGroups(groups, grup.getParent());	
		}
	}

	/* (non-Javadoc)
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 */
	public void setApplicationContext (ApplicationContext applicationContext)
					throws BeansException
	{
		ctx = applicationContext;
		
	}
	
	class InterpreterEnvironment {
		
		private User userVO;
		private HashMap<String, String> attributes;
		private HashMap<String, Group> groups;
		
		public InterpreterEnvironment(UserEntity user) {
			User usuariVO = getUserEntityDao().toUser(user);
			userVO = (usuariVO);
			
			attributes = new HashMap<String, String>();
			for (UserDataEntity dada : user.getUserData()) {
                attributes.put(dada.getDataType().getName(), dada.getValue());
            }
			
			groups = new HashMap<String, Group>();
			addGroups(groups, user.getPrimaryGroup());
			for (UserGroupEntity grup : user.getSecondaryGroups()) addGroups(groups, grup.getGroup());
			
		}
		
		public Interpreter getInterpeter() throws EvalError
		{
			Interpreter interpreter = new Interpreter();
			interpreter.set("user", userVO); //$NON-NLS-1$
			interpreter.set("attributes", attributes); //$NON-NLS-1$
			interpreter.set("groups", groups); //$NON-NLS-1$
			interpreter.set("groupsList", groups.keySet()); //$NON-NLS-1$
			interpreter.set("applicationContext", ctx); //$NON-NLS-1$
			
			return interpreter;
		}
		
	}
}


