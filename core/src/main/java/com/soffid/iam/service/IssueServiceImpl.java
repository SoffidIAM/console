package com.soffid.iam.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.resource.spi.SecurityException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.soffid.iam.api.AsyncList;
import com.soffid.iam.api.DataType;
import com.soffid.iam.api.EventUserAction;
import com.soffid.iam.api.Issue;
import com.soffid.iam.api.IssueActionDefinition;
import com.soffid.iam.api.IssueHost;
import com.soffid.iam.api.IssuePolicyAction;
import com.soffid.iam.api.IssuePolicyStatus;
import com.soffid.iam.api.IssueStatus;
import com.soffid.iam.api.IssueUser;
import com.soffid.iam.api.PagedResult;
import com.soffid.iam.api.User;
import com.soffid.iam.bpm.service.scim.ScimHelper;
import com.soffid.iam.common.security.SoffidPrincipal;
import com.soffid.iam.config.Config;
import com.soffid.iam.model.ConfigEntity;
import com.soffid.iam.model.IssueEntity;
import com.soffid.iam.model.IssueEntityDao;
import com.soffid.iam.model.IssueHostEntity;
import com.soffid.iam.model.IssuePolicyActionEntity;
import com.soffid.iam.model.IssuePolicyEntity;
import com.soffid.iam.model.IssueUserEntity;
import com.soffid.iam.model.UserEntity;
import com.soffid.iam.model.UserEntityDao;
import com.soffid.iam.model.criteria.CriteriaSearchConfiguration;
import com.soffid.iam.service.impl.IssueDataParser;
import com.soffid.iam.service.impl.issues.AutomaticActionHandler;
import com.soffid.iam.service.impl.issues.ManualActionHandler;
import com.soffid.iam.utils.ConfigurationCache;
import com.soffid.iam.utils.Security;
import com.soffid.scimquery.EvalException;
import com.soffid.scimquery.parser.ParseException;
import com.soffid.scimquery.parser.TokenMgrError;

import es.caib.seycon.ng.comu.TypeEnumeration;
import es.caib.seycon.ng.exception.InternalErrorException;

public class IssueServiceImpl extends IssueServiceBase {
	Log log = LogFactory.getLog(getClass());
	
	@Override
	protected AsyncList<Issue> handleFindMyIssuesByJsonQueryAsync(String query) throws Exception {
		AsyncList<Issue> l = new AsyncList<>();
		getAsyncRunnerService().run(() -> {
			try {
				findMyIssues(query, null, null, l);
			} catch (Exception e) {
				l.cancel(e);
			}
		}, l);
		return l;
	}

	private PagedResult<Issue> findMyIssues(String query, Integer start, Integer pageSize, List<Issue> l) throws UnsupportedEncodingException, ClassNotFoundException, JSONException, InternalErrorException, EvalException, ParseException, TokenMgrError {
		final SoffidPrincipal soffidPrincipal = Security.getSoffidPrincipal();
		String[] roles = soffidPrincipal.getSoffidRoles();
		int rolesStep = 100;
		
		final IssueEntityDao dao = getIssueEntityDao();
		PagedResult<Issue> pr = new PagedResult<>();
		pr.setStartIndex(start);
		pr.setItemsPerPage(pageSize);
		int total = 0;
		for (int roleIndex = 0; roleIndex <= roles.length; roleIndex += rolesStep) {
			List<String> newRoles = new LinkedList<>();
			for (int i = roleIndex; i < roleIndex + rolesStep && i < roles.length; i++) 
				newRoles.add(roles[i]);
			if (newRoles.size() < rolesStep && soffidPrincipal.getUserName() != null)
				newRoles.add(soffidPrincipal.getUserName());
			
			ScimHelper h = new ScimHelper(Issue.class);
			CriteriaSearchConfiguration config = new CriteriaSearchConfiguration();
			config.setFirstResult(start);
			config.setMaximumResultSize(pageSize);
			h.setConfig(config);
			h.setTenantFilter("tenant.id"); //$NON-NLS-1$
			h.setGenerator((entity) -> {
				IssueEntity ue = (IssueEntity) entity;
				return dao.toIssue(ue);
			});
			
			h.setExtraWhere("o.actor in (:list)"); //$NON-NLS-1$
			HashMap<String,Object> parameters = new HashMap<>();
			parameters.put("list", newRoles); //$NON-NLS-1$
			h.setExtraParameters(parameters);
			h.search(null, query, (Collection) l); 
			total += h.count();
			pr.setTotalResults(total);
			if ( pageSize != null && l.size() >= pageSize.intValue())
				return pr;
		}

		return pr;
	}

	@Override
	protected Issue handleCreate(Issue issue) throws Exception {
		if (issue.getType().equals("duplicated-user")) //$NON-NLS-1$
			return createIssue(issue, true);
		else
			throw new SecurityException(Messages.getString("IssueServiceImpl.0")); //$NON-NLS-1$
	}

	@Override
	protected Issue handleCreateInternalIssue(Issue issue) throws Exception {
		return createIssue(issue, false);
	}

	protected Issue createIssue(Issue issue, boolean manual) throws FileNotFoundException, IOException, InternalErrorException {
		Collection<IssuePolicyEntity> policies = getIssuePolicyEntityDao().findByType(issue.getType());
		
		
		IssuePolicyStatus max = IssuePolicyStatus.IGNORE;
		IssuePolicyEntity currentPolicy = null;
		for (IssuePolicyEntity policy: policies) {
			if (policy.getStatus() != null &&
					policy.getStatus().getValue().compareTo(max.getValue()) > 0) {
				max = policy.getStatus();
				currentPolicy = policy;
			}
			if (currentPolicy == null)
				currentPolicy = policy;
		}
		
		if (max != IssuePolicyStatus.IGNORE || manual) {
			if (issue.getHash() != null) {
				for (IssueEntity i: getIssueEntityDao().findBySearchHash(issue.getType(), issue.getHash())) {
					i.setTimes(Integer.valueOf(i.getTimes() == null? 2: i.getTimes().intValue()+1));
					getIssueEntityDao().update(i);
					return getIssueEntityDao().toIssue(i);
				}
			}
			
			IssueEntity entity = getIssueEntityDao().newIssueEntity();
			getIssueEntityDao().issueToEntity(issue, entity, true);
			if (max == IssuePolicyStatus.RECORD) {
				entity.setStatus(IssueStatus.ACKNOWLEDGED);
				entity.setAcknowledged(new Date());
			}
			else {
				entity.setStatus(IssueStatus.NEW);
			}
			entity.setNumber(getNewIssueNumber());
			entity.setCreated(new Date());
			entity.setTimes(1);
			if (currentPolicy != null)
				entity.setActor(currentPolicy.getActor());
			addHistory(entity, "Created"); //$NON-NLS-1$
			getIssueEntityDao().create(entity);
			if (issue.getUsers() != null) 
				for (IssueUser user: issue.getUsers()) {
					IssueUserEntity issueUserEntity = getIssueUserEntityDao().issueUserToEntity(user);
					UserEntity userEntity = user.getUserId() == null ?
							getUserEntityDao().findByUserName(user.getUserName() ) :
								getUserEntityDao().load(user.getUserId());
					// Check user has an active issue
					if ("duplicated-user".equals(issue.getType())) { //$NON-NLS-1$
						if (getIssueEntityDao().findByIssueAndUser(issue.getType(), user.getUserName()).size() > 0)
						{
							throw new InternalErrorException(
									String.format(Messages.getString("IssueServiceImpl.7"), //$NON-NLS-1$
											user.getUserName()));
						}
					}
					issueUserEntity.setIssue(entity);
					issueUserEntity.setUser(userEntity);
					issueUserEntity.setExternalId(user.getExternalId());
					if (userEntity != null)
						issueUserEntity.setUserName(userEntity.getUserName());
					issueUserEntity.setAction(EventUserAction.UNKNOWN);
					getIssueUserEntityDao().create(issueUserEntity);
					entity.getUsers().add(issueUserEntity);
				}
			if (issue.getHosts() != null) 
				for (IssueHost host: issue.getHosts()) {
					IssueHostEntity hostEntity = getIssueHostEntityDao().issueHostToEntity(host);
					hostEntity.setIssue(entity);
					getIssueHostEntityDao().create(hostEntity);
					entity.getHosts().add(hostEntity);
				}
			
			processAutomaticIssues(entity, policies);
			return getIssueEntityDao().toIssue(entity);
		}
		else
			return issue;
	}

	private Long getNewIssueNumber() {
		Long next = 1L;
		ConfigEntity config = getConfigEntityDao().findByCodeAndNetworkCode("soffid.issue.next", null);
		if (config == null) {
			config = getConfigEntityDao().newConfigEntity();
			config.setName("soffid.issue.next");
			config.setValue(Long.toString(next + 1));
			getConfigEntityDao().create(config);
		} else {
			try {
				next = Long.parseLong(config.getValue());
			} catch (Exception e) {
			}
			config.setValue(Long.toString(next + 1));
			getConfigEntityDao().update(config);
		}
		return next;
	}

	private void processAutomaticIssues(IssueEntity issue, Collection<IssuePolicyEntity> policies) throws IOException {
		JSONArray actions = IssueDataParser.instance().getActions();
		for (IssuePolicyEntity policy: policies) {
			if ( policy.getActor() != null && ! policy.getActor().trim().isEmpty())
				issue.setActor(policy.getActor());
			for (IssuePolicyActionEntity actionEntity: policy.getActions()) {
				if (actionEntity.getStatus() == issue.getStatus() ||
						actionEntity.getAction() == null && issue.getStatus() == IssueStatus.NEW) {
					try {
						addHistory(issue, Messages.getString("IssueServiceImpl.4")+actionEntity.getAction()); //$NON-NLS-1$
						processRule(issue, actionEntity, actions);
					} catch (Exception e) {
						log.warn (Messages.getString("IssueServiceImpl.3")+policy.getDescription(), e); //$NON-NLS-1$
					}
				}
			}
		}
	}

	private void processRule(IssueEntity issue, IssuePolicyActionEntity actionEntity, JSONArray actions) throws Exception {
		for (int i = 0; i < actions.length(); i++) {
			final JSONObject jsonObject = actions.getJSONObject(i);
			String name = jsonObject.optString("name", null); //$NON-NLS-1$
			String handler = jsonObject.optString("handler", null);  //$NON-NLS-1$
			if (name != null && handler != null && name.equals(actionEntity.getAction())) {
				AutomaticActionHandler h = (AutomaticActionHandler) Class.forName(handler).getConstructor().newInstance();
				h.process(getIssueEntityDao().toIssue(issue), issue, actionEntity);
			}
		}
	}

	@Override
	protected Issue handleUpdate(Issue issue) throws Exception {
		IssueEntity entity = getIssueEntityDao().load(issue.getId());
		IssueStatus oldStatus = entity.getStatus();
		if (issue.getStatus() == IssueStatus.ACKNOWLEDGED &&
				entity.getStatus() == IssueStatus.NEW) {
			entity.setStatus(IssueStatus.ACKNOWLEDGED);
			entity.setAcknowledged(new Date());
			addHistory(entity, Messages.getString("IssueServiceImpl.2")); //$NON-NLS-1$
			getIssueEntityDao().update(entity);
		}
		
		LinkedList l = new LinkedList<>(entity.getUsers());
		for (IssueUserEntity iuEntity: entity.getUsers()) {
			for (IssueUser iu: issue.getUsers()) {
				if (iu.getUserName().equals(iuEntity.getUserName()) ) {
					if (iu.getAction() != iuEntity.getAction()) {
						iuEntity.setAction(iu.getAction());
						if (iu.getAction() == EventUserAction.DUPLICATED)
							iuEntity.setUser(null);
						getIssueUserEntityDao().update(iuEntity);
					}
					break;
				}
			}
		}
		
		if (issue.getStatus() == IssueStatus.SOLVED &&
				entity.getStatus() != IssueStatus.SOLVED) {
			entity.setStatus(IssueStatus.SOLVED);
			entity.setSolved(new Date());
			entity.setHash(null);
			addHistory(entity, Messages.getString("IssueServiceImpl.1")); //$NON-NLS-1$
			if (issue.getType().equals("duplicated-user")) { //$NON-NLS-1$
				boolean anyMerge = false;
				for (IssueUserEntity ue: entity.getUsers()) {
					if (ue.getAction() == null || ue.getAction() == EventUserAction.UNKNOWN ) {
						ue.setAction(EventUserAction.DIFFERENT_USER);
						getIssueUserEntityDao().update(ue);
					} else if (ue.getAction() != EventUserAction.DIFFERENT_USER) {
						anyMerge = true;
					}
				}
				if (!anyMerge) {
					entity.setStatus(IssueStatus.SOLVED_NOTADUPLICATE);
				}
			}
			getIssueEntityDao().update(entity);
		}
		
		
		if (entity.getStatus() != oldStatus) {
			Collection<IssuePolicyEntity> policies = getIssuePolicyEntityDao().findByType(issue.getType());
			processAutomaticIssues(entity, policies);
		}
		return getIssueEntityDao().toIssue(entity);
	}

	@Override
	protected PagedResult<Issue> handleFindMyIssuesByJsonQuery(String query, Integer first, Integer pageSize)
			throws Exception {
		LinkedList<Issue> l = new LinkedList<>();
		return findMyIssues(query, first, pageSize, l);
	}

	@Override
	protected List<IssueActionDefinition> handleListManualActions() throws Exception {
		List<IssueActionDefinition> list = new LinkedList<>();
		JSONArray issues = IssueDataParser.instance().getIssues();
		JSONArray actions = IssueDataParser.instance().getManualActions();
		for (int i = 0; i < actions.length(); i++) {
			JSONObject o = actions.getJSONObject(i);
			IssueActionDefinition def  = new IssueActionDefinition();
			def.setName(o.optString("name", null)); //$NON-NLS-1$
			def.setHandler(o.getString("handler")); //$NON-NLS-1$
			def.setLabel(o.optString("nlsLabel", "com.soffid.iam.api.IssueActionDefinition." +  def.getName())); //$NON-NLS-1$ //$NON-NLS-2$
			LinkedList<DataType> att = new LinkedList<DataType>();
			def.setParameters(att);
			JSONArray parameters = o.optJSONArray("parameters"); //$NON-NLS-1$
			if (parameters != null) {
				for (int j = 0; j < parameters.length(); j++) {
					JSONObject parameter = parameters.getJSONObject(j);
					DataType dt = new DataType();
					dt.setName(parameter.optString("name")); //$NON-NLS-1$
					dt.setNlsLabel(parameter.optString("nlsLabel")); //$NON-NLS-1$
					dt.setType(searchDataType (parameter.optString("dataType"))); //$NON-NLS-1$
					att.add(dt);
				}
			}
			
			def.setIssueTypes(new LinkedList<>());
			for (int j = 0; j < issues.length(); j++) {
				JSONArray manualActions = issues.getJSONObject(j).optJSONArray("manual-actions"); //$NON-NLS-1$
				for (int k = 0; k < manualActions.length(); k++) 
					if (manualActions.getString(k).equals(def.getName()))
					{
						def.getIssueTypes().add(issues.getJSONObject(j).getString("name")); //$NON-NLS-1$
						break;
					}
			}
			
			list.add(def);
		}
		return list;
	}

	private TypeEnumeration searchDataType(String dataTypeName) {
		List names = TypeEnumeration.names();
		for ( int i = 0; i < names.size(); i++)
		{
			String name = (String) names.get(i);
			TypeEnumeration type = TypeEnumeration.fromString( (String) TypeEnumeration.literals().get(i) );
			if (type.toString().equals(dataTypeName) ||
					name.equalsIgnoreCase(dataTypeName) ||
					name.toLowerCase().equals(dataTypeName.toLowerCase()+"_type") ) { //$NON-NLS-1$
				return type;
			} 
		}
		return TypeEnumeration.STRING_TYPE;
	}

	private void addHistory(IssueEntity Issue, String msg) throws FileNotFoundException, IOException {
		String user = Security.getCurrentUser();
		if (user == null) user = Security.getCurrentAccount();
		if (user == null || user.equals("null")) user = "-"; //$NON-NLS-1$
		String line = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss").format(new Date())+" "+user+" "+msg+"\n"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		String m = Issue.getPerformedActions();
		if (m == null)
			Issue.setPerformedActions(line);
		else
			Issue.setPerformedActions(Issue.getPerformedActions()+line);
	}

	@Override
	protected void handleDelete(Issue Issue) throws Exception {
		IssueEntity entity = getIssueEntityDao().load(Issue.getId());
		if (entity != null)
			getIssueEntityDao().remove(entity);
	}

	@Override
	protected AsyncList<Issue> handleFindIssuesByJsonQueryAsync(String query) throws Exception {
		AsyncList<Issue> l = new AsyncList<>();
		getAsyncRunnerService().run(() -> {
			try {
				findIssues(query, null, null, l);
			} catch (Exception e) {
				l.cancel(e);
			}
		}, l);
		return l;
	}

	@Override
	protected PagedResult<Issue> handleFindIssuesByJsonQuery(String query, Integer first, Integer pageSize)
			throws Exception {
		LinkedList<Issue> l = new LinkedList<>();
		return findIssues(query, first, pageSize, l);
	}


	private PagedResult<Issue> findIssues(String query, Integer start, Integer pageSize, List<Issue> l) throws UnsupportedEncodingException, ClassNotFoundException, JSONException, InternalErrorException, EvalException, ParseException, TokenMgrError {
		final IssueEntityDao dao = getIssueEntityDao();
		PagedResult<Issue> pr = new PagedResult<>();
		pr.setStartIndex(start);
		pr.setItemsPerPage(pageSize);

		ScimHelper h = new ScimHelper(Issue.class);
		CriteriaSearchConfiguration config = new CriteriaSearchConfiguration();
		config.setFirstResult(start);
		config.setMaximumResultSize(pageSize);
		h.setConfig(config);
		h.setTenantFilter("tenant.id"); //$NON-NLS-1$
		h.setGenerator((entity) -> {
			IssueEntity ue = (IssueEntity) entity;
			return dao.toIssue(ue);
		});
			
		h.search(null, query, (Collection) l); 
		pr.setResources(l);
		pr.setTotalResults(h.count());

		return pr;
	}

	@Override
	protected Issue handleNotify(Issue issue, String address, String subject, String body) throws Exception {
		IssueEntity entity = getIssueEntityDao().load(issue.getId());
		addHistory(entity, Messages.getString("IssueServiceImpl.5")+address); //$NON-NLS-1$
		getIssueEntityDao().update(entity);
		getMailService().sendHtmlMail(address, Messages.getString("IssueServiceImpl.33")+issue.getId()+": "+subject, //$NON-NLS-1$ //$NON-NLS-2$
				body);
		return getIssueEntityDao().toIssue(entity);
		
	}

	@Override
	protected Issue handleRegisterAction(Issue issue, String action) throws Exception {
		IssueEntity entity = getIssueEntityDao().load(issue.getId());
		addHistory(entity, action);
		getIssueEntityDao().update(entity);
		return getIssueEntityDao().toIssue(entity);
	}

	@Override
	protected List<Issue> handleFindIssuesByUser(String user) throws Exception {
		Collection<IssueEntity> l = getIssueEntityDao().findByUserName(user);
		return getIssueEntityDao().toIssueList(l);
	}

	@Override
	protected int handleCountMyIssues() throws Exception {
		int count = 0;
		final SoffidPrincipal soffidPrincipal = Security.getSoffidPrincipal();
		String[] roles = soffidPrincipal.getSoffidRoles();
		
		final IssueEntityDao dao = getIssueEntityDao();
		for (String role: roles) {
			count += dao.countPending(role);
		}

		return count;
	}
}
