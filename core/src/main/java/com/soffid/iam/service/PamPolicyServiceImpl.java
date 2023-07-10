package com.soffid.iam.service;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.WebClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.soffid.iam.api.AccountStatus;
import com.soffid.iam.api.AsyncList;
import com.soffid.iam.api.Audit;
import com.soffid.iam.api.Issue;
import com.soffid.iam.api.IssueUser;
import com.soffid.iam.api.PagedResult;
import com.soffid.iam.api.PamAction;
import com.soffid.iam.api.PamPolicy;
import com.soffid.iam.api.PamRule;
import com.soffid.iam.api.Password;
import com.soffid.iam.api.System;
import com.soffid.iam.bpm.service.scim.ScimHelper;
import com.soffid.iam.model.AccountAccessEntity;
import com.soffid.iam.model.AccountEntity;
import com.soffid.iam.model.AuditEntity;
import com.soffid.iam.model.JumpServerGroupEntity;
import com.soffid.iam.model.PamActionEntity;
import com.soffid.iam.model.PamActionType;
import com.soffid.iam.model.PamPolicyEntity;
import com.soffid.iam.model.PamPolicyEntityDao;
import com.soffid.iam.model.PamRuleEntity;
import com.soffid.iam.model.PamRuleEntityDao;
import com.soffid.iam.model.SessionEntity;
import com.soffid.iam.model.criteria.CriteriaSearchConfiguration;
import com.soffid.iam.utils.Security;
import com.soffid.scimquery.EvalException;
import com.soffid.scimquery.parser.ParseException;
import com.soffid.scimquery.parser.TokenMgrError;

import es.caib.seycon.ng.comu.AccountAccessLevelEnum;
import es.caib.seycon.ng.comu.TipusSessio;
import es.caib.seycon.ng.exception.InternalErrorException;

public class PamPolicyServiceImpl extends PamPolicyServiceBase {

	@Override
	protected AsyncList<PamPolicy> handleFindPolicyByJsonQueryAsync(String text, String query) throws Exception {
		final AsyncList<PamPolicy> result = new AsyncList<PamPolicy>();
		getAsyncRunnerService().run(new Runnable() {
			@Override
			public void run() {
				try {
					doFindPolicyByTextAndJsonQuery(text, query, null, null, result);
				} catch (Throwable e) {
					throw new RuntimeException(e);
				}				
			}
		}, result);

		return result;
	}

	private PagedResult<PamPolicy> doFindPolicyByTextAndJsonQuery(String text, String jsonQuery,
			Integer start, Integer pageSize,
			List<PamPolicy> result) throws UnsupportedEncodingException, ClassNotFoundException, InternalErrorException, EvalException, JSONException, ParseException, TokenMgrError {
		final PamPolicyEntityDao dao = getPamPolicyEntityDao();
		ScimHelper h = new ScimHelper(PamPolicy.class);
		h.setPrimaryAttributes(new String[] { "name", "description" });
		CriteriaSearchConfiguration config = new CriteriaSearchConfiguration();
		config.setFirstResult(start);
		config.setMaximumResultSize(pageSize);
		h.setConfig(config);
		h.setTenantFilter("tenant.id");
		h.setGenerator((entity) -> {
			return dao.toPamPolicy((PamPolicyEntity) entity);
		}); 
		h.search(text, jsonQuery, (Collection) result); 
		PagedResult<PamPolicy> pr = new PagedResult<>();
		pr.setStartIndex(start);
		pr.setItemsPerPage(pageSize);
		pr.setTotalResults(h.count());
		pr.setResources(result);
		return pr;
	}

	@Override
	protected AsyncList<PamRule> handleFindRuleByJsonQueryAsync(String text, String query) throws Exception {
		final AsyncList<PamRule> result = new AsyncList<PamRule>();
		getAsyncRunnerService().run(new Runnable() {
			@Override
			public void run() {
				try {
					doFindRuleByTextAndJsonQuery(text, query, null, null, result);
				} catch (Throwable e) {
					throw new RuntimeException(e);
				}				
			}
		}, result);

		return result;
	}

	private PagedResult<PamRule> doFindRuleByTextAndJsonQuery(String text, String jsonQuery,
			Integer start, Integer pageSize,
			List<PamRule> result) throws UnsupportedEncodingException, ClassNotFoundException, InternalErrorException, EvalException, JSONException, ParseException, TokenMgrError {
		final PamRuleEntityDao dao = getPamRuleEntityDao();
		ScimHelper h = new ScimHelper(PamRule.class);
		h.setPrimaryAttributes(new String[] { "name", "description" });
		CriteriaSearchConfiguration config = new CriteriaSearchConfiguration();
		config.setFirstResult(start);
		config.setMaximumResultSize(pageSize);
		h.setConfig(config);
		h.setTenantFilter("tenant.id");
		h.setGenerator((entity) -> {
			return dao.toPamRule((PamRuleEntity) entity);
		}); 
		h.search(text, jsonQuery, (Collection) result); 
		PagedResult<PamRule> pr = new PagedResult<>();
		pr.setStartIndex(start);
		pr.setItemsPerPage(pageSize);
		pr.setTotalResults(h.count());
		pr.setResources(result);
		return pr;
	}


	@Override
	protected PagedResult<PamPolicy> handleFindPolicyByJsonQuery(String text, String query, Integer first, Integer pageSize)
			throws Exception {
		final LinkedList<PamPolicy> result = new LinkedList<PamPolicy>();
		return doFindPolicyByTextAndJsonQuery(text, query, first, pageSize, result);
	}

	@Override
	protected PagedResult<PamRule> handleFindRuleByJsonQuery(String text, String query, Integer first, Integer pageSize)
			throws Exception {
		final LinkedList<PamRule> result = new LinkedList<PamRule>();
		return doFindRuleByTextAndJsonQuery(text, query, first, pageSize, result);
	}

	@Override
	protected PamAction handleUpdateAction(PamAction action) throws Exception {
        Audit auditoria = new Audit();
        auditoria.setAction("U"); //$NON-NLS-1$
        auditoria.setRule(action.getRuleName());
        auditoria.setDomain(action.getPolicyName());
        auditoria.setAuthor(Security.getCurrentAccount());
        auditoria.setObject("SC_PAMACT"); //$NON-NLS-1$
        AuditEntity auditoriaEntity = getAuditEntityDao().auditToEntity(auditoria);
        getAuditEntityDao().create(auditoriaEntity);

        action.setAuthor(Security.getCurrentAccount());
        action.setDate(new Date());
        action = getPamActionEntityDao().update(action);
        
        PamPolicyEntity policy = getPamPolicyEntityDao().findByName(action.getPolicyName());
        
        sendPolicy(policy.getName(), policy);
        
        return action;
	}

	@Override
	protected PamPolicy handleCreatePolicy(PamPolicy policy) throws Exception {
        Audit auditoria = new Audit();
        auditoria.setAction("C"); // Administrador //$NON-NLS-1$
        auditoria.setDomain(policy.getName());
        auditoria.setAuthor(Security.getCurrentAccount());
        auditoria.setObject("SC_PAMPOL"); //$NON-NLS-1$
        AuditEntity auditoriaEntity = getAuditEntityDao().auditToEntity(auditoria);
        getAuditEntityDao().create(auditoriaEntity);

		PamPolicyEntity entity = getPamPolicyEntityDao().newPamPolicyEntity();
        policy.setAuthor(Security.getCurrentAccount());
        policy.setDate(new Date());
		getPamPolicyEntityDao().pamPolicyToEntity(policy, entity, false);
		getPamPolicyEntityDao().create(entity);
		
        sendPolicy(entity.getName(), entity);

		return getPamPolicyEntityDao().toPamPolicy(entity);
	}

	@Override
	protected PamPolicy handleUpdatePolicy(PamPolicy policy) throws Exception {
        Audit auditoria = new Audit();
        auditoria.setAction("U"); // Administrador //$NON-NLS-1$
        auditoria.setDomain(policy.getName());
        auditoria.setAuthor(Security.getCurrentAccount());
        auditoria.setObject("SC_PAMPOL"); //$NON-NLS-1$
        AuditEntity auditoriaEntity = getAuditEntityDao().auditToEntity(auditoria);
        getAuditEntityDao().create(auditoriaEntity);

        PamPolicyEntity entity = getPamPolicyEntityDao().load(policy.getId());
        String oldName = entity.getName();
        policy.setAuthor(Security.getCurrentAccount());
        policy.setDate(new Date());
		getPamPolicyEntityDao().pamPolicyToEntity(policy, entity, false);
		getPamPolicyEntityDao().update(entity);
        sendPolicy(oldName, entity);
		return getPamPolicyEntityDao().toPamPolicy(entity);
	}

	@Override
	protected PamRule handleCreateRule(PamRule rule) throws Exception {
        Audit auditoria = new Audit();
        auditoria.setAction("C"); //$NON-NLS-1$
        auditoria.setRule(rule.getName());
        auditoria.setAuthor(Security.getCurrentAccount());
        auditoria.setObject("SC_PAMRUL"); //$NON-NLS-1$
        AuditEntity auditoriaEntity = getAuditEntityDao().auditToEntity(auditoria);
        getAuditEntityDao().create(auditoriaEntity);

        PamRuleEntity entity = getPamRuleEntityDao().newPamRuleEntity();
        rule.setAuthor(Security.getCurrentAccount());
        rule.setDate(new Date());
		getPamRuleEntityDao().pamRuleToEntity(rule, entity, false);
		getPamRuleEntityDao().create(entity);
		
		return getPamRuleEntityDao().toPamRule(entity);
	}

	@Override
	protected PamRule handleUpdateRule(PamRule rule) throws Exception {
        Audit auditoria = new Audit();
        auditoria.setAction("U"); //$NON-NLS-1$
        auditoria.setRule(rule.getName());
        auditoria.setAuthor(Security.getCurrentAccount());
        auditoria.setObject("SC_PAMRUL"); //$NON-NLS-1$
        AuditEntity auditoriaEntity = getAuditEntityDao().auditToEntity(auditoria);
        getAuditEntityDao().create(auditoriaEntity);

        PamRuleEntity entity = getPamRuleEntityDao().load(rule.getId());
        rule.setAuthor(Security.getCurrentAccount());
        rule.setDate(new Date());
		getPamRuleEntityDao().pamRuleToEntity(rule, entity, false);
		getPamRuleEntityDao().create(entity);
		
		for (PamActionEntity action: entity.getActions())
	        sendPolicy(action.getPolicy().getName(), action.getPolicy());

		return getPamRuleEntityDao().toPamRule(entity);
	}

	@Override
	protected void handleDeletePolicy(PamPolicy policy) throws Exception {
        Audit auditoria = new Audit();
        auditoria.setAction("D"); //$NON-NLS-1$
        auditoria.setDomain(policy.getName());
        auditoria.setAuthor(Security.getCurrentAccount());
        auditoria.setObject("SC_PAMPOL"); //$NON-NLS-1$
        AuditEntity auditoriaEntity = getAuditEntityDao().auditToEntity(auditoria);
        getAuditEntityDao().create(auditoriaEntity);

        PamPolicyEntity entity = getPamPolicyEntityDao().load(policy.getId());
		getPamPolicyEntityDao().remove(entity);
	}

	@Override
	protected void handleDeleteRule(PamRule rule) throws Exception {
        Audit auditoria = new Audit();
        auditoria.setAction("D"); //$NON-NLS-1$
        auditoria.setRule(rule.getName());
        auditoria.setAuthor(Security.getCurrentAccount());
        auditoria.setObject("SC_PAMRUL"); //$NON-NLS-1$
        AuditEntity auditoriaEntity = getAuditEntityDao().auditToEntity(auditoria);
        getAuditEntityDao().create(auditoriaEntity);

        PamRuleEntity entity = getPamRuleEntityDao().load(rule.getId());

        for (PamActionEntity action: entity.getActions()) {
        	PamPolicyEntity policy = action.getPolicy();
        	policy.getActions().remove(action);
        	getPamActionEntityDao().remove(action);
        	sendPolicy(policy.getName(), policy);
        }

		getPamRuleEntityDao().remove(entity);
	}

	@Override
	protected List<PamAction> handleFindPolicyActions(PamPolicy policy) throws Exception {
		return getPamActionEntityDao().getActionsByPolicy(policy.getName());
	}

	public void sendPolicy(String oldName, PamPolicyEntity policy) throws MalformedURLException, UnsupportedEncodingException, InternalErrorException {
    	for ( JumpServerGroupEntity js: getJumpServerGroupEntityDao().loadAll()) {
    		URL url = new URL(js.getStoreUrl());
    		sendPolicy(js, url, oldName, policy);
    	}
    	
	}

	private void sendPolicy(JumpServerGroupEntity selected, URL url, String oldName, PamPolicyEntity policy) throws InternalErrorException, MalformedURLException, UnsupportedEncodingException {
		if (existsPolicy (selected, oldName)) {
			updatePolicy (selected, oldName, policy);
		} else {
			createPolicy (selected, policy);
		}
	}

	private boolean existsPolicy(JumpServerGroupEntity selected, String oldName) throws MalformedURLException, InternalErrorException, UnsupportedEncodingException {
		URL url = new URL(selected.getStoreUrl());
		URL url2 = new URL(url.getProtocol(), url.getHost(), url.getPort(), "/store/ipsPolicy?filter=" + URLEncoder.encode( "policyName eq '"+quote(oldName)+"'", "UTF-8"));
		try {
			Response response;
			response = 
					WebClient
					.create(url2.toString(), selected.getStoreUserName(), 
							Password.decode(selected.getPassword()).getPassword(), null)
					.accept(MediaType.APPLICATION_JSON)
					.get();
			if (response.getStatus() != HttpServletResponse.SC_OK)
				throw new InternalErrorException("Error querying policies "+url2+": HTTP/"+response.getStatus());
			
			JSONObject result  = new JSONObject( new JSONTokener( response.readEntity( String.class   ) ) );
			if (! result.getBoolean("success"))
				throw new InternalErrorException("Error querying policies "+url2+": "+result.optString("reason"));
			return result.getJSONArray("result").length() > 0;
		} catch (Exception e) {
			throw new InternalErrorException("Error querying policies "+url2, e);
		}
	}

	private void updatePolicy(JumpServerGroupEntity selected, String oldName, PamPolicyEntity policy) throws MalformedURLException, UnsupportedEncodingException, InternalErrorException {
		URL url = new URL(selected.getStoreUrl());
		URL url2 = new URL(url.getProtocol(), url.getHost(), url.getPort(), "/store/ipsPolicy/"+URLEncoder.encode( oldName,  "UTF-8"));
		try {
			JSONObject o = buildJsonPolicy(policy);
			Response response;
			response = 
					WebClient
					.create(url2.toString(), selected.getStoreUserName(), 
							Password.decode(selected.getPassword()).getPassword(), null)
					.header("Content-Type", MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
					.put( o.toString());
			if (response.getStatus() != HttpServletResponse.SC_OK)
				throw new InternalErrorException("Error updating policy "+url2+": HTTP/"+response.getStatus());
			
			JSONObject result  = new JSONObject( new JSONTokener( response.readEntity( String.class   ) ) );
			if (! result.getBoolean("success"))
				throw new InternalErrorException("Error updating policy "+url2+": "+result.optString("reason"));
		} catch (Exception e) {
			throw new InternalErrorException("Error updating policy "+url2, e);
		}
	}

	private void createPolicy(JumpServerGroupEntity selected, PamPolicyEntity policy) throws MalformedURLException, UnsupportedEncodingException, InternalErrorException {
		URL url = new URL(selected.getStoreUrl());
		URL url2 = new URL(url.getProtocol(), url.getHost(), url.getPort(), "/store/ipsPolicy");
		try {
			JSONObject o = buildJsonPolicy(policy);
			Response response;
			response = 
					WebClient
					.create(url2.toString(), selected.getStoreUserName(), 
							Password.decode(selected.getPassword()).getPassword(), null)
					.header("Content-Type", MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
					.post( o.toString());
			if (response.getStatus() != HttpServletResponse.SC_OK)
				throw new InternalErrorException("Error updating policy "+url2+": HTTP/"+response.getStatus());
			
			JSONObject result  = new JSONObject( new JSONTokener( response.readEntity( String.class   ) ) );
			if (! result.getBoolean("success"))
				throw new InternalErrorException("Error updating policy "+url2+": "+result.optString("reason"));
		} catch (Exception e) {
			throw new InternalErrorException("Error updating policy "+url2, e);
		}
	}


	private JSONObject buildJsonPolicy(PamPolicyEntity policy) {
		JSONObject o = new JSONObject();
		o.put("policyName", policy.getName());
		o.put("author", policy.getAuthor());
		o.put("date", policy.getDate().getTime());
		JSONArray a = new JSONArray();
		o.put("actions", a);
		
		HashMap<String,JSONObject> jsonActions = new HashMap<>();
		for (PamActionEntity entity: policy.getActions() ) {
			JSONObject jsonAction = jsonActions.get(entity.getRule().getName());
			if (jsonAction == null) {
				jsonAction = new JSONObject();
				jsonAction.put("content", entity.getRule().getContent());
				jsonAction.put("date", entity.getRule().getDate().getTime());
				jsonAction.put("author", entity.getRule().getAuthor());
				jsonAction.put("description", entity.getRule().getDescription());
				jsonAction.put("shortName", entity.getRule().getName());
				jsonAction.put("type", entity.getRule().getType().toString());
				jsonAction.put("actions", new JSONArray());
				a.put(jsonAction);
				jsonActions.put(entity.getRule().getName(), jsonAction);
			}
			jsonAction.getJSONArray("actions").put(entity.getType().toString());
		}
		return o;
	}

	private String quote(String name) {
		return name.replace("\\","\\\\").replace("\"", "\\\"").replace("'", "\\'");
	}

	@Override
	protected void handleApplyRule(String sessionKey, String policyName, String ruleName) throws Exception {
		System d = getDispatcherService().findSoffidDispatcher();
		for (SessionEntity session: getSessionEntityDao().findByKey(sessionKey)) {
			AccountEntity account = session.getAccount();
			if (session.getType() == TipusSessio.PAM && account != null) {

				Audit auditoria = new Audit();
				auditoria.setAction("T"); //$NON-NLS-1$
				auditoria.setRule(ruleName);
				auditoria.setDomain(policyName);
				auditoria.setAccount(account.getName());
				auditoria.setDatabase(account.getSystem().getName());
				for (AccountEntity accounts: getAccountEntityDao().findByUserAndSystem(session.getUser().getUserName(), d.getName())) {
					auditoria.setAuthor(accounts.getName());
				}
				auditoria.setObject("SC_PAMACT"); //$NON-NLS-1$
				AuditEntity auditoriaEntity = getAuditEntityDao().auditToEntity(auditoria);
				getAuditEntityDao().create(auditoriaEntity);
				
				List<PamActionEntity> actions = getPamActionEntityDao().findByPolicyAndRule(policyName, ruleName);
				for (PamActionEntity action: actions) {
					if (action.getType() == PamActionType.ISSUE) {
						Issue i = new Issue();
						i.setAccount(account.getName()+"@"+account.getSystem());
						i.setRule(getPamRuleEntityDao().toPamRule(action.getRule()));
						if (session.getUser() != null) {
							IssueUser iu = new IssueUser();
							iu.setUserId(session.getUser().getId());
							iu.setUserName(session.getUser().getUserName());
							i.setUsers(Arrays.asList(iu));
						}
						getIssueService().createInternalIssue(i);
					}
					if (action.getType() == PamActionType.LOCK_ACCOUNT) {
						account.setStatus(AccountStatus.LOCKED);
						getAccountEntityDao().update(account);
					}
					if (action.getType() == PamActionType.NOTIFY) {
						notify(session, account, actions, action.getRule());
					}
				}
			}
		}
				
	}

	protected void notify(SessionEntity session, AccountEntity account, List<PamActionEntity> actions, PamRuleEntity pamRuleEntity) throws InternalErrorException {
		List<String> owners = new LinkedList<>();
		for (AccountAccessEntity ace: account.getAcl()) {
			if (! ace.getDisabled() && ace.getLevel() == AccountAccessLevelEnum.ACCESS_OWNER) {
				if (ace.getUser() != null) owners.add(ace.getUser().getUserName());
				if (ace.getRole() != null) owners.add(ace.getRole().getName()+"@"+ace.getRole().getSystem().getName());
				if (ace.getGroup() != null) owners.add(ace.getGroup().getName());
			}
		}
		if (!owners.isEmpty()) {
			String msg = "PAM Policy violation for account ";
			if (account.getLoginName() != null)
				msg += account.getLoginName();
			else
				msg += account.getName();
			msg += " in ";
			if (account.getServerName() != null)
				msg += account.getServerName();
			else
				msg += account.getSystem().getName();

			StringBuffer body = new StringBuffer();
			body.append("This is an automatic Soffid notification. Please, do not replay to this message\n\n");
			body.append("The user "+session.getUser().getFullName()+" is connected to "+session.getHostName()+"\n");
			body.append("The user is using the account: ");
			if (account.getLoginName() != null)
				body.append(account.getLoginName());
			else
				body.append(account.getName());
			msg += " in ";
			if (account.getServerName() != null)
				body.append(account.getServerName());
			else
				body.append(account.getSystem().getName());

			body.append("\n");
			
			body.append("\nDuring the session, the following alarm has raised: "+pamRuleEntity.getName()+". "+pamRuleEntity.getDescription());
			body.append("\n\n");
			if (actions.size() > 1) {
				body.append("The following actions has been taken:\n");
				for (PamActionEntity action: actions) {
					if (action.getType() == PamActionType.CLOSE_SESSION) 
						body.append (" - The session has been closed-n");
					else if (action.getType() == PamActionType.ISSUE) 
						body.append (" - A security incident has been registered\n");
					else if (action.getType() == PamActionType.LOCK_ACCOUNT) 
						body.append (" - The account has been locked\n");
				}
			}
			body.append("\n\nYou can check the full session recording in Soffid console\n");
			getMailService().sendTextMailToActors(owners.toArray(new String[owners.size()]), msg, body.toString());
		}
	}
}
