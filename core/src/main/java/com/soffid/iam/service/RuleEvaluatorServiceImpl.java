/**
 * 
 */
package com.soffid.iam.service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import bsh.EvalError;
import bsh.Interpreter;

import com.soffid.iam.api.Group;
import com.soffid.iam.api.RoleAccount;
import com.soffid.iam.api.User;
import com.soffid.iam.model.RuleAssignedRoleEntity;
import com.soffid.iam.model.RuleEntity;

import es.caib.seycon.ng.comu.AccountType;
import es.caib.seycon.ng.comu.Grup;
import es.caib.seycon.ng.comu.RolAccount;
import es.caib.seycon.ng.comu.Tasca;
import es.caib.seycon.ng.comu.TipusDomini;
import es.caib.seycon.ng.comu.UserAccount;
import es.caib.seycon.ng.comu.Usuari;
import es.caib.seycon.ng.comu.ValorDomini;
import es.caib.seycon.ng.exception.AccountAlreadyExistsException;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.exception.NeedsAccountNameException;
import es.caib.seycon.ng.model.AccountEntity;
import es.caib.seycon.ng.model.DadaUsuariEntity;
import es.caib.seycon.ng.model.DispatcherEntity;
import es.caib.seycon.ng.model.GrupEntity;
import es.caib.seycon.ng.model.RolAccountEntity;
import es.caib.seycon.ng.model.RolAccountEntityDao;
import es.caib.seycon.ng.model.RolEntity;
import es.caib.seycon.ng.model.TasqueEntity;
import es.caib.seycon.ng.model.UserAccountEntity;
import es.caib.seycon.ng.model.UsuariEntity;
import es.caib.seycon.ng.model.UsuariGrupEntity;
import es.caib.seycon.ng.model.ValorDominiAplicacioEntity;
import es.caib.seycon.ng.sync.engine.TaskHandler;
import es.caib.seycon.ng.utils.Security;

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

	protected void doApply (RuleEntity rule, UsuariEntity user, InterpreterEnvironment env) throws Exception
	{
		Security.nestedLogin(Security.getCurrentAccount(), new String [] {
			Security.AUTO_USER_QUERY+Security.AUTO_ALL,
			Security.AUTO_APPLICATION_QUERY+Security.AUTO_ALL,
			Security.AUTO_USER_ROLE_CREATE+Security.AUTO_ALL,
			Security.AUTO_USER_ROLE_DELETE+Security.AUTO_ALL,
			Security.AUTO_USER_ROLE_QUERY+Security.AUTO_ALL
		});
		try {
			RolAccountEntityDao raDao = getRolAccountEntityDao();
			Object result = null;
			if ("S".equals(user.getActiu())) //$NON-NLS-1$
				result = evaluate (rule.getBshExpression(), env);
			if (result != null && ! (result instanceof Boolean))
			{
				throw new InternalErrorException (String.format(Messages.getString("RuleEvaluatorServiceImpl.NotBooleanReturn"), result.toString())); //$NON-NLS-1$
			}
			List<RolAccountEntity> roles = raDao.findAllByCodiUsuari(user.getCodi());
			if (result != null && ((Boolean) result).booleanValue())
			{
				for (RuleAssignedRoleEntity rar: rule.getRoles())
				{
					ValorDominiAplicacioEntity valor = null;
					String stringValue = null;
					if (!rar.getRole().getTipusDomini().equals (TipusDomini.SENSE_DOMINI))
					{
						if (rar.getDomainValue() != null && rar.getDomainValue().length() > 0)
						{
							stringValue = rar.getDomainValue();
						}
						else if (rar.getBshDomainValueExpression() != null &&
										rar.getBshDomainValueExpression().length() > 0)
						{
							Object obj = evaluate(rar.getBshDomainValueExpression(), env);
							if (obj != null)
								stringValue = obj.toString();
						}
					}
					
					// Now grant role (if needed)
					// If a role account is reused, it is removed from roles list
					assignRole (rule, roles, user, rar.getRole(), stringValue);
				}
			}
			// Now remove unneded roles
			for (RolAccountEntity role: roles)
			{
				if (role.getRule() == rule)
				{
                    RolAccount r = getRolAccountEntityDao().toRolAccount(role);
                    getAplicacioService().update(r);
					raDao.remove(role);
                    // insert into sc_tasque
                    // (tas_id,tas_role,tas_bd,tas_status,tas_data,tas_transa)
                    // values
                    // (sc_tas_seq.nextval,codi_role,codi_bd,'P',sysdate,'UpdateRole');
                    Tasca updateRole = new Tasca();
                    updateRole.setTransa("UpdateRole");// Actualització del rol //$NON-NLS-1$
                    updateRole.setDataTasca(Calendar.getInstance());
                    updateRole.setStatus("P");// Posem com a pendent //$NON-NLS-1$
                    updateRole.setRole(r.getNomRol());
                    updateRole.setBd(r.getBaseDeDades());
                    TasqueEntity tasca = getTasqueEntityDao().tascaToEntity(updateRole);
                    getTasqueEntityDao().create(tasca);

                    Tasca updateUser = new Tasca();
                    updateUser.setTransa(TaskHandler.UPDATE_USER);// Actualització del rol //$NON-NLS-1$
                    updateUser.setDataTasca(Calendar.getInstance());
                    updateUser.setStatus("P");// Posem com a pendent //$NON-NLS-1$
                    updateUser.setUsuari(user.getCodi());
                    TasqueEntity tasca2 = getTasqueEntityDao().tascaToEntity(updateUser);
                    getTasqueEntityDao().create(tasca2);

				}
			}
		} catch (Throwable e) {
			throw new InternalErrorException (String.format(Messages.getString("RuleEvaluatorServiceImpl.EvaluationRuleError"), rule.getDescription(), user.getCodi())
					+"\n"+e.getMessage());
		} finally {
			Security.nestedLogoff();
		}
	}

	/* (non-Javadoc)
	 * @see com.soffid.iam.service.RuleEvaluatorServiceBase#handleApply(com.soffid.iam.model.RuleEntity, es.caib.seycon.ng.model.UsuariEntity)
	 */
	@Override
	protected void handleApply (RuleEntity rule, UsuariEntity user) throws Exception
	{
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
	private void assignRole (RuleEntity rule, List<RolAccountEntity> roles, UsuariEntity user, RolEntity role,
					String stringValue) throws InternalErrorException, NeedsAccountNameException, AccountAlreadyExistsException
	{
		// First. Test if role is already assigned
		for (Iterator<RolAccountEntity> it = roles.iterator(); it.hasNext();)
		{
			RolAccountEntity ra = it.next();
			boolean match = false;
			if (ra.getRol().getId().equals(role.getId()))
			{
				if (ra.getValorDominiAplicacio() != null)
				{
					if (ra.getValorDominiAplicacio().getValor().equals(stringValue))
						match = true;
				} else if (ra.getGrup() != null) {
					if (ra.getGrup().getCodi().equals(stringValue))
						match = true;
				} else if (ra.getAplicacioAdministrada() != null) {
					if (ra.getAplicacioAdministrada().getCodi().equals(stringValue))
						match = true;
				} else {
					if (stringValue == null)
						match = true;
				}
			}
			if (match)
			{
				it.remove();
				return;
			}
		}
		
		// Second. Assign now
		List<AccountEntity> accounts = getAccountsForRole(user, role);
		RolAccount ra = generateRolAccount (rule, role, stringValue);
		if (accounts.isEmpty())
		{
			UserAccount account = getAccountService (). createAccount ( 
							getUsuariEntityDao().toUsuari(user), 
							getDispatcherEntityDao().toDispatcher(role.getBaseDeDades()), 
							null);
			ra.setAccountId(account.getId());
			ra.setAccountName(account.getName());
			Security.nestedLogin(Security.getCurrentAccount(), new String[] {
				Security.AUTO_USER_ROLE_CREATE+Security.AUTO_ALL
			});
			try {
				getAplicacioService().create(ra);
			} finally {
				Security.nestedLogoff();
			}
		} else {
			for (AccountEntity account: accounts)
			{
				ra.setAccountId(account.getId());
				ra.setAccountName(account.getName());
				getAplicacioService().create(ra);
			}
		}
		
	}

	/**
	 * @param rule 		
	 * @param role
	 * @param stringValue
	 * @return
	 */
	private RolAccount generateRolAccount (RuleEntity rule, RolEntity role, String stringValue)
	{
		RolAccount ra = new RolAccount();
        ra.setAccountDispatcher(role.getBaseDeDades().getCodi());
        ra.setBaseDeDades(role.getBaseDeDades().getCodi());
        ra.setCodiAplicacio(role.getAplicacio().getCodi());
        ra.setNomRol(role.getNom());
        ra.setRuleId(rule.getId());
        ra.setRuleDescription(rule.getDescription());
        if (stringValue != null)
        {
        	ValorDomini vd = new ValorDomini();
        	vd.setValor(stringValue);
    		vd.setDescripcio("??"); //$NON-NLS-1$
        	if (role.getDominiAplicacio() != null)
        	{
        		vd.setNomDomini(role.getDominiAplicacio().getNom());
        		vd.setCodiExternDomini(role.getDominiAplicacio().getNom());
        	} 
        	else if (role.getTipusDomini() != null)
        	{
        		vd.setNomDomini(role.getTipusDomini());
        		vd.setCodiExternDomini(role.getTipusDomini());
        	}
    		ra.setValorDomini(vd);
        }
        return ra;
	}

	/**
	 * @param user
	 * @param role
	 * @return
	 */
	private List<AccountEntity> getAccountsForRole (UsuariEntity user, RolEntity role)
	{
		LinkedList<AccountEntity> accounts = new LinkedList<AccountEntity>();
		for (UserAccountEntity ua: user.getAccounts())
		{
			AccountEntity account = ua.getAccount();
			if (account.getType().equals (AccountType.USER) && 
				account.getDispatcher().getId().equals (role.getBaseDeDades().getId()))
			{
				accounts.add(ua.getAccount());
			}
		}
		return accounts;
	}

	/* (non-Javadoc)
	 * @see com.soffid.iam.service.RuleEvaluatorServiceBase#handleApplyRules(es.caib.seycon.ng.model.UsuariEntity)
	 */
	@Override
	protected void handleApplyRules (UsuariEntity user) throws Exception
	{
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
		for (UsuariEntity u: getUsuariEntityDao().loadAll())
		{
			apply (rule, u);
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
	private void addGroups (HashMap<String, Group> groups, GrupEntity grup)
	{
		if (!groups.containsKey(grup.getCodi()))
		{
			Grup grupVO = getGrupEntityDao().toGrup (grup);
			groups.put (grup.getCodi(), Group.toGroup( grupVO));
			if (grup.getPare() != null)
				addGroups (groups, grup.getPare());	
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
		
		public InterpreterEnvironment (UsuariEntity user)
		{
			Usuari usuariVO = getUsuariEntityDao().toUsuari(user);
			userVO = User.toUser(usuariVO);
			
			attributes = new HashMap<String, String>();
			for (DadaUsuariEntity dada: user.getDadaUsuari())
			{
				attributes.put(dada.getTipusDada().getCodi(), dada.getValorDada());
			}
			
			groups = new HashMap<String, Group>();
			addGroups (groups, user.getGrupPrimari());
			for (UsuariGrupEntity grup: user.getGrupsSecundaris())
				addGroups (groups, grup.getGrup());
			
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


