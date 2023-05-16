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
import com.soffid.iam.api.Issue;
import com.soffid.iam.api.IssueActionDefinition;
import com.soffid.iam.api.IssueHost;
import com.soffid.iam.api.IssuePolicyAction;
import com.soffid.iam.api.IssueStatus;
import com.soffid.iam.api.IssueUser;
import com.soffid.iam.api.PagedResult;
import com.soffid.iam.api.User;
import com.soffid.iam.bpm.service.scim.ScimHelper;
import com.soffid.iam.common.security.SoffidPrincipal;
import com.soffid.iam.config.Config;
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
import com.soffid.iam.utils.Security;
import com.soffid.scimquery.EvalException;
import com.soffid.scimquery.parser.ParseException;
import com.soffid.scimquery.parser.TokenMgrError;

import es.caib.seycon.ng.comu.TypeEnumeration;
import es.caib.seycon.ng.exception.InternalErrorException;

public class IssueServiceImpl extends IssueServiceBase {
	Log log = LogFactory.getLog(getClass());
	
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

	private PagedResult<Issue> findIssues(String query, Integer start, Integer pageSize, List<Issue> l) throws UnsupportedEncodingException, ClassNotFoundException, JSONException, InternalErrorException, EvalException, ParseException, TokenMgrError {
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
			for (int i = roleIndex; i < roleIndex + rolesStep; i++) 
				newRoles.add(roles[i]);
			if (newRoles.size() < rolesStep && soffidPrincipal.getUserName() != null)
				newRoles.add(soffidPrincipal.getUserName());
			
			ScimHelper h = new ScimHelper(User.class);
			CriteriaSearchConfiguration config = new CriteriaSearchConfiguration();
			config.setFirstResult(start);
			config.setMaximumResultSize(pageSize);
			h.setConfig(config);
			h.setTenantFilter("tenant.id");
			h.setGenerator((entity) -> {
				IssueEntity ue = (IssueEntity) entity;
				return dao.toIssue(ue);
			});
			
			h.setExtraWhere("o.actor in :list");
			HashMap<String,Object> parameters = new HashMap<>();
			parameters.put("list", newRoles);
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
	protected Issue handleCreate(Issue Issue) throws Exception {
		if (Issue.getType().equals("duplicated-user"))
			return handleCreateInternalIssue(Issue);
		else
			throw new SecurityException("Not authorized to create manual Issues");
	}

	@Override
	protected Issue handleCreateInternalIssue(Issue issue) throws Exception {
		IssueEntity entity = getIssueEntityDao().newIssueEntity();
		getIssueEntityDao().issueToEntity(issue, entity, true);
		entity.setStatus(IssueStatus.NEW);
		entity.setCreated(new Date());
		addHistory(entity, "Created");
		getIssueEntityDao().create(entity);
		if (issue.getUsers() != null) 
			for (IssueUser user: issue.getUsers()) {
				IssueUserEntity userEntity = getIssueUserEntityDao().issueUserToEntity(user);
				userEntity.setIssue(entity);
				getIssueUserEntityDao().create(userEntity);
				entity.getUsers().add(userEntity);
			}
		if (issue.getHosts() != null) 
			for (IssueHost host: issue.getHosts()) {
				IssueHostEntity hostEntity = getIssueHostEntityDao().issueHostToEntity(host);
				hostEntity.setIssue(entity);
				getIssueHostEntityDao().create(hostEntity);
				entity.getHosts().add(hostEntity);
			}
		
		processAutomaticIssues(entity);
		return getIssueEntityDao().toIssue(entity);
	}

	private void processAutomaticIssues(IssueEntity Issue) throws IOException {
		JSONArray actions = IssueDataParser.instance().getActions();
		for (IssuePolicyEntity policy: getIssuePolicyEntityDao().findByType(Issue.getType())) {
			if ( policy.getActor() != null && ! policy.getActor().trim().isEmpty())
				Issue.setActor(policy.getActor());
			for (IssuePolicyActionEntity actionEntity: policy.getActions()) {
				try {
					addHistory(Issue, "Executed automatic task "+actionEntity.getAction());
					getAsyncRunnerService().runNewTransaction(() -> {
						processRule(Issue, actionEntity, actions);
						return null;
					});
				} catch (Exception e) {
					log.warn ("Error processing rule "+policy.getDescription(), e);
				}
			}
		}
	}

	private void processRule(IssueEntity Issue, IssuePolicyActionEntity actionEntity, JSONArray actions) throws Exception {
		for (int i = 0; i < actions.length(); i++) {
			final JSONObject jsonObject = actions.getJSONObject(i);
			String name = jsonObject.optString("name", null);
			String handler = jsonObject.optString("handler", null); 
			if (name != null && handler != null && name.equals(actionEntity.getAction())) {
				AutomaticActionHandler h = (AutomaticActionHandler) Class.forName(handler).getConstructor().newInstance();
				h.process (Issue, actionEntity);
			}
		}
	}

	@Override
	protected Issue handleUpdate(Issue Issue) throws Exception {
		IssueEntity entity = getIssueEntityDao().load(Issue.getId());
		if (Issue.getStatus() == IssueStatus.ACKNOWLEDGED &&
				entity.getStatus() == IssueStatus.NEW) {
			entity.setStatus(IssueStatus.ACKNOWLEDGED);
			entity.setAcknowledged(new Date());
			addHistory(entity, "Acknowledged");
			getIssueEntityDao().update(entity);
		}
		if (Issue.getStatus() == IssueStatus.SOLVED &&
				entity.getStatus() != IssueStatus.SOLVED) {
			entity.setStatus(IssueStatus.SOLVED);
			entity.setSolved(new Date());
			addHistory(entity, "Salved");
			getIssueEntityDao().update(entity);
		}
		return getIssueEntityDao().toIssue(entity);
	}

	@Override
	protected PagedResult<Issue> handleFindIssuesByJsonQuery(String query, Integer first, Integer pageSize)
			throws Exception {
		LinkedList<Issue> l = new LinkedList<>();
		return findIssues(query, first, pageSize, l);
	}

	@Override
	protected List<IssueActionDefinition> handleListManualActions() throws Exception {
		List<IssueActionDefinition> list = new LinkedList<>();
		JSONArray actions = IssueDataParser.instance().getManualActions();
		for (int i = 0; i < actions.length(); i++) {
			JSONObject o = actions.getJSONObject(i);
			IssueActionDefinition def  = new IssueActionDefinition();
			def.setName(o.optString("name", null));
			def.setLabel(o.optString("nlsLabel", "com.soffid.iam.api.IssueActionDefinition." +  def.getName()));
			LinkedList<DataType> att = new LinkedList<DataType>();
			def.setParameters(att);
			JSONArray parameters = o.getJSONArray("parameters");
			if (parameters != null) {
				for (int j = 0; j < parameters.length(); j++) {
					JSONObject parameter = parameters.getJSONObject(j);
					DataType dt = new DataType();
					dt.setName(parameter.optString("name"));
					dt.setNlsLabel(parameter.optString("nlsLabel"));
					dt.setType(searchDataType (parameter.optString("dataType")));
					att.add(dt);
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
					name.toLowerCase().equals(dataTypeName.toLowerCase()+"_type") ) {
				return type;
			} 
		}
		return TypeEnumeration.STRING_TYPE;
	}

	@Override
	protected Issue handleApplyManualAction(Issue Issue, String action, Map<String, Object> parameters)
			throws Exception {
		IssueEntity entity = getIssueEntityDao().load(Issue.getId());
		JSONArray actions = IssueDataParser.instance().getManualActions();
		for (int i = 0; i < actions.length(); i++) {
			JSONObject o = actions.getJSONObject(i);
			if (o.optString("name", "").equals(action)) {
				String handler = o.getString("handler");
				ManualActionHandler h = (ManualActionHandler) Class.forName(handler).getConstructor().newInstance();
				h.process (entity, action, parameters);
				addHistory (entity, "Executed manual action "+action);
			}
		}
		return getIssueEntityDao().toIssue(entity);
	}

	private void addHistory(IssueEntity Issue, String msg) throws FileNotFoundException, IOException {
		String user = Security.getCurrentUser();
		if (user == null) user = Security.getCurrentAccount();
		if (user == null) user = "-";
		String line = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss").format(new Date())+" "+user+" "+msg+"\n";
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

}
