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

import java.io.File;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.orm.hibernate3.SessionFactoryUtils;

import com.soffid.iam.ServiceLocator;
import com.soffid.iam.api.AsyncProcessTracker;
import com.soffid.iam.api.DelegationStatus;
import com.soffid.iam.api.DomainValue;
import com.soffid.iam.api.Group;
import com.soffid.iam.api.Role;
import com.soffid.iam.api.RoleAccount;
import com.soffid.iam.api.User;
import com.soffid.iam.api.UserAccount;
import com.soffid.iam.common.security.SoffidPrincipal;
import com.soffid.iam.interp.Evaluator;
import com.soffid.iam.model.AccountEntity;
import com.soffid.iam.model.DomainValueEntity;
import com.soffid.iam.model.GroupEntity;
import com.soffid.iam.model.Parameter;
import com.soffid.iam.model.RoleAccountEntity;
import com.soffid.iam.model.RoleAccountEntityDao;
import com.soffid.iam.model.RoleEntity;
import com.soffid.iam.model.RuleAssignedRoleEntity;
import com.soffid.iam.model.RuleEntity;
import com.soffid.iam.model.UserAccountEntity;
import com.soffid.iam.model.UserEntity;
import com.soffid.iam.model.UserGroupEntity;
import com.soffid.iam.model.criteria.CriteriaSearchConfiguration;
import com.soffid.iam.service.impl.RuleDryRunMethod;
import com.soffid.iam.service.impl.RuleEvaluatorGrantRevokeMethod;
import com.soffid.iam.utils.ConfigurationCache;
import com.soffid.iam.utils.Security;

import es.caib.seycon.ng.comu.AccountType;
import es.caib.seycon.ng.comu.TipusDomini;
import es.caib.seycon.ng.exception.AccountAlreadyExistsException;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.exception.NeedsAccountNameException;
import es.caib.seycon.ng.exception.SoffidStackTrace;

/**
 * @author bubu
 *
 */
public class RuleEvaluatorServiceImpl extends RuleEvaluatorServiceBase implements ApplicationContextAware
{
	Log log = LogFactory.getLog(getClass());
	private ApplicationContext ctx;
	private SessionFactory sessionFactory;

	/**
	 * 
	 */
	public RuleEvaluatorServiceImpl ()
	{
	}

	protected void doApply(RuleEntity rule, UserEntity user, InterpreterEnvironment env,
			RuleEvaluatorGrantRevokeMethod method) throws Exception {
		Security.nestedLogin(Security.getCurrentAccount(), Security.ALL_PERMISSIONS);
		try {
			RoleAccountEntityDao raDao = getRoleAccountEntityDao();
			Object result = null;
			if ("S".equals(user.getActive())) //$NON-NLS-1$
				result = evaluate (rule.getBshExpression(), rule.getName(), env);
			if (result != null && ! (result instanceof Boolean))
			{
				throw new InternalErrorException (String.format(Messages.getString("RuleEvaluatorServiceImpl.NotBooleanReturn"), result.toString())); //$NON-NLS-1$
			}
			List<RoleAccountEntity> roles = new LinkedList<RoleAccountEntity>( raDao.findAllByUserName(user.getUserName()));
			if (! "true".equals(ConfigurationCache.getProperty("soffid.delegation.disable")))
			{
				// Remmove roles delegated by another user
				for ( Iterator<RoleAccountEntity> it = roles.iterator(); it.hasNext ();)
				{
					RoleAccountEntity ra = it.next();
					if (DelegationStatus.DELEGATION_ACTIVE.equals(ra.getDelegationStatus()))
						it.remove();
				}
				// Add delegated roles
				
				roles.addAll( raDao.findDelegatedRolAccounts(user.getUserName()));
			}
			HashSet<Long> rolesToRemove = new HashSet<Long>();
			for (Iterator<RoleAccountEntity> iterator = roles.iterator(); iterator.hasNext();) {
				RoleAccountEntity role = iterator.next();
				if (role.isEnabled() && ( 
						role.getEndDate() == null ||
						role.getEndDate().after(new Date())))
				{
					if (role.getRule() != null && role.getRule().getId().equals(rule.getId())) {
                		rolesToRemove.add(role.getId());
                	}
                }
				else
				{
					iterator.remove();
				}
			}
			// Add role if needed
			if (result != null && ((Boolean) result).booleanValue())
			{
				for (RuleAssignedRoleEntity rar : rule.getRoles()) {
                    DomainValueEntity valor = null;
                    String stringValue = null;
                    if (rar.getRole().getDomainType() != null && !rar.getRole().getDomainType().equals(TipusDomini.SENSE_DOMINI)) {
                        if (rar.getDomainValue() != null && rar.getDomainValue().length() > 0) {
                            stringValue = rar.getDomainValue();
                        } else if (rar.getBshDomainValueExpression() != null && rar.getBshDomainValueExpression().length() > 0) {
                            Object obj = evaluate(rar.getBshDomainValueExpression(), rule.getName(), env);
                            if (obj != null) stringValue = obj.toString();
                        }
                    }
                    assignRole(rule, roles, user, rar.getRole(), stringValue, method);
                }
				
				if ( rule.getBshRoles() != null)
				{
					Object o = evaluate(rule.getBshRoles(), rule.getName(), env);
					if (o != null) {
						if (! (o instanceof Collection))
							throw new InternalErrorException(
									String.format("Rule %s has a roles expression, but it has returned an object of class %s when it should be a Collection",
											rule.getName(), o.getClass().toString()));
						for (Object grant: (Collection)o) {
							RoleEntity r;
							String domainValue = null;
							if (grant != null) {
								if (grant instanceof RoleAccount) {
									if (((RoleAccount) grant).getId() != null)
										r = getRoleEntityDao().load(((RoleAccount) grant).getId());
									else
										r = getRoleEntityDao().findByNameAndSystem(((RoleAccount) grant).getRoleName(), ((RoleAccount) grant).getSystem());
									if (((RoleAccount) grant).getDomainValue() != null)
										domainValue = ((RoleAccount) grant).getDomainValue().getValue();
								}
								else if (grant instanceof Role) {
									if (((Role) grant).getId() != null)
										r = getRoleEntityDao().load(((RoleAccount) grant).getId());
									else
										r = getRoleEntityDao().findByNameAndSystem(((Role) grant).getName(), ((Role) grant).getSystem());
								}
								else if (grant instanceof String) {
									String grantString = (String) grant;
									int at = grantString.lastIndexOf('@');
									int slash = at >= 0 ? grantString.indexOf('/', at) :
										grantString.lastIndexOf('/');
									if (slash > 0) {
										domainValue = grantString.substring(slash + 1);
										grantString = grantString.substring(0, slash);
									}
									r = getRoleEntityDao().findByShortName(grantString);
								} else {
									throw new InternalErrorException(
											String.format("Rule %s has a roles expression, but it has returned a collection with an object of class %s when it should be one out of Role, RoleAccount or String",
													rule.getName(), grant.getClass().toString()));
								}
								if (r == null)
									throw new InternalErrorException(
											String.format("Rule %s has a roles expression, but it has returned an unknown role",
													rule.getName()));
			                    assignRole(rule, roles, user, r, domainValue, method);
							}
						}
					}
				}
			}
			// Now remove unneeded roles
			for (RoleAccountEntity role : roles) {
				if (rolesToRemove.contains(role.getId())) {
               		method.revoke(user, role);
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
		doApply(rule, user, new InterpreterEnvironment(user), new ActualUpdateMethod());
	}
		
	/**
	 * @param rule 
	 * @param roles
	 * @param role
	 * @param stringValue
	 * @param method 
	 * @throws InternalErrorException 
	 * @throws AccountAlreadyExistsException 
	 * @throws NeedsAccountNameException 
	 */
	private void assignRole(RuleEntity rule, List<RoleAccountEntity> roles, UserEntity user, 
			RoleEntity role, String stringValue, RuleEvaluatorGrantRevokeMethod method) throws InternalErrorException, NeedsAccountNameException, AccountAlreadyExistsException {
		LinkedList<AccountEntity> accounts = new LinkedList<AccountEntity>();
		LinkedList<AccountEntity> disabledAccounts = new LinkedList<AccountEntity>();
		for (UserAccountEntity uac: user.getAccounts()) {
			if (uac.getAccount().getSystem() == role.getSystem()) {
				if (uac.getAccount().isDisabled())
					disabledAccounts.add(uac.getAccount());
				else
					accounts.add(uac.getAccount());
			}
		}
		
		if (accounts.isEmpty()) {
			if (disabledAccounts.isEmpty())
				assignRoleAccount(rule, roles, user, role, stringValue, method, null);
			else for (AccountEntity account: disabledAccounts) {
				assignRoleAccount(rule, roles, user, role, stringValue, method, account);
			}
		}
		else for (AccountEntity account: accounts) {
			assignRoleAccount(rule, roles, user, role, stringValue, method, account);
		}
		
	}

	private void assignRoleAccount(RuleEntity rule, List<RoleAccountEntity> roles, UserEntity user, RoleEntity role,
			String stringValue, RuleEvaluatorGrantRevokeMethod method, AccountEntity account)
			throws InternalErrorException, NeedsAccountNameException, AccountAlreadyExistsException {
		// First. Test if role is already assigned
		
		if (account != null) {
			for (Iterator<RoleAccountEntity> it = roles.iterator(); it.hasNext(); ) {
				RoleAccountEntity ra = it.next();
				boolean match = false;
				if (ra.getRole().getId().equals(role.getId()) && account == ra.getAccount()) {
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
			
			
		}
		// Second. Assign now
		method.grant(rule, user, role, stringValue, account);
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
    			doApply (rule, user, env, new ActualUpdateMethod());
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
		if (sessionFactory == null)
			sessionFactory = (SessionFactory) ctx.getBean("sessionFactory");
		Session session = SessionFactoryUtils.getSession(sessionFactory, false) ;

		List<Long> allUsers = new LinkedList<Long>();
		for (UserEntity u: getUserEntityDao().query("select us from com.soffid.iam.model.UserEntity as us where us.active='S' and us.tenant.id=:tenantId", 
				new Parameter[] { new Parameter("tenantId", Security.getCurrentTenantId())} ))
		{
			allUsers.add(u.getId());
		}
		int i = 100;
		for (Long l: allUsers)
		{
			if (i++ >= 100)
			{
				session.flush();
				session.clear();
				session.load(rule, rule.getId());
				i = 0;
			}
			log.info("User "+l);
			UserEntity u = getUserEntityDao().load(l);
			apply (rule, u);
		}
	}

	private Object evaluate (String expression, String ruleName, InterpreterEnvironment env) throws Exception
	{
		return Evaluator.instance().evaluate(expression, env.getVars(), ruleName);
	}

	private String cut(String expression) {
		if (expression.length() > 80)
			return expression.substring(0, 80);
		else
			return expression;
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
		private Map<String, Object> attributes;
		private HashMap<String, Group> groups;
		
		public InterpreterEnvironment(UserEntity user) {
			User usuariVO = getUserEntityDao().toUser(user);
			userVO = (usuariVO);
			
			try {
				attributes = getUserService().findUserAttributes(user.getUserName());
			} catch (InternalErrorException e) {
				attributes = null;
			}
			
			groups = new HashMap<String, Group>();
			addGroups(groups, user.getPrimaryGroup());
			for (UserGroupEntity grup : user.getSecondaryGroups()) 
				if (! Boolean.TRUE.equals(grup.getDisabled()))
					addGroups(groups, grup.getGroup());
			
		}
		
		public Map<String,Object> getVars() {
			Map<String,Object> vars = new HashMap<>();
			vars.put("user", userVO); //$NON-NLS-1$
			vars.put("attributes", attributes); //$NON-NLS-1$
			vars.put("groups", groups); //$NON-NLS-1$
			vars.put("groupsList", groups.keySet()); //$NON-NLS-1$
			vars.put("serviceLocator", ServiceLocator.instance()); //$NON-NLS-1$
			
			return vars;
		}
		
	}
	
	class ActualUpdateMethod implements RuleEvaluatorGrantRevokeMethod {
		@Override
		public void revoke(UserEntity user, RoleAccountEntity role) throws InternalErrorException {
            RoleAccount r = getRoleAccountEntityDao().toRoleAccount(role);
            getApplicationService().deleteByRuleEvaluation(r);
		}
		
		@Override
		public void grant(RuleEntity rule, UserEntity user, RoleEntity role, String domainValue, AccountEntity account)
				throws InternalErrorException, NeedsAccountNameException, AccountAlreadyExistsException {
			RoleAccount ra = generateRolAccount(rule, role, domainValue);
			if (account == null) {
				UserAccount ua = getAccountService().createAccount(getUserEntityDao().toUser(user), getSystemEntityDao().toSystem(role.getSystem()), null);
				ra.setAccountId(ua.getId());
				ra.setAccountName(ua.getName());
			} else {
				ra.setAccountId(account.getId());
				ra.setAccountName(account.getName());
			}
			Security.nestedLogin(Security.getCurrentAccount (), Security.ALL_PERMISSIONS);
			try {
				getApplicationService().create(ra);
			} finally {
				Security.nestedLogoff();
			}
		}
	}

	@Override
	protected File handleDryRun(RuleEntity rule) throws Exception {
		if (sessionFactory == null)
			sessionFactory = (SessionFactory) ctx.getBean("sessionFactory");
		Session session = SessionFactoryUtils.getSession(sessionFactory, false) ;

		List<Long> allUsers = new LinkedList<Long>();
		for (UserEntity u: getUserEntityDao().query("select us from com.soffid.iam.model.UserEntity as us where us.active='S' and us.tenant.id=:tenantId", 
				new Parameter[] { new Parameter("tenantId", Security.getCurrentTenantId())} ))
		{
			allUsers.add(u.getId());
		}
		int i = 100;
		RuleDryRunMethod report = new RuleDryRunMethod();
		for (Long l: allUsers)
		{
			if (i++ >= 100)
			{
//				session.flush();
//				session.clear();
				i = 0;
			}
			log.info("User "+l);
			UserEntity u = getUserEntityDao().load(l);
			
			doApply(rule, u, new InterpreterEnvironment(u), report);
		}
		report.close();
		return report.getFile();
	};

	Map<Long,AsyncProcessTracker> proc = new HashMap<>();
	
	@Override
	protected AsyncProcessTracker handleApplyAsync (RuleEntity rule) throws Exception
	{
		AsyncProcessTracker old = proc.get(rule.getId());
		if (old != null)
			old.setCancelled(true);
		final AsyncProcessTracker p = new AsyncProcessTracker();
		p.setId(rule.getId());
		proc.put(p.getId(), p);
		p.setStart(new Date());
		
		if (sessionFactory == null)
			sessionFactory = (SessionFactory) ctx.getBean("sessionFactory");

		final SoffidPrincipal principal = Security.getSoffidPrincipal();
		
		new Thread ( () -> {
			
			Session session = SessionFactoryUtils.getSession(sessionFactory, true) ;
			final Number count = (Number) session
					.createQuery( "select count(*) from com.soffid.iam.model.UserEntity as us "
								+ "where us.active='S' and us.tenant.id=:tenantId ")
					.setParameter("tenantId", Security.getCurrentTenantId())
					.list()
					.iterator()
					.next();
			
			int pos = 0; 
			int step = 100;
			Object end = Boolean.FALSE;
			Security.nestedLogin(principal);
			try {
				p.setProgress(0);
				final CriteriaSearchConfiguration criteria = new CriteriaSearchConfiguration();
				criteria.setFirstResult(0);
				criteria.setFetchSize(step);
				criteria.setMaximumResultSize(step);
				while (! Boolean.TRUE.equals(end) && ! p.isCancelled()) {
					end = getAsyncRunnerService().runNewTransaction(
						() -> {
							RuleEntity entity = getRuleEntityDao().load(rule.getId());
							List<UserEntity> list = getUserEntityDao().query("select us from com.soffid.iam.model.UserEntity as us where us.active='S' and us.tenant.id=:tenantId order by us.id", 
									new Parameter[] { new Parameter("tenantId", Security.getCurrentTenantId())},
									criteria );
							if (list.isEmpty())
								return Boolean.TRUE;
							int i = 0;
							for (UserEntity user: list) {
								if (p.isCancelled())
									return Boolean.TRUE;
								p.setCurrent(user.getUserName());
								apply (entity, user);
								i++;
								p.setProgress( (float) ( criteria.getFirstResult() + i ) / count.floatValue());
							}
							session.flush();
							session.clear();
							criteria.setFirstResult(criteria.getFirstResult().intValue() + list.size());
							return Boolean.FALSE;
						}
					);
				}
				p.setCurrent(null);
				p.setStart(new Date());
				p.setErrorMessage(null);
				p.setFinished(true);
			} catch (Exception e) {
				log.warn("Error evaluating rules", e);
				p.setErrorMessage(SoffidStackTrace.generateShortDescription(e));
				p.setStart(new Date());
				p.setFinished(true);
			} finally {
				Security.nestedLogoff();
				session.close();
			}
		} ).start();
		
		return p;
		
	}

	@Override
	protected AsyncProcessTracker handleQueryProcessStatus(AsyncProcessTracker process) throws Exception {
		return proc.get(process.getId());
	}


}
