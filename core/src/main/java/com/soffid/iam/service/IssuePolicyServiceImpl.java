package com.soffid.iam.service;

import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.soffid.iam.api.AsyncList;
import com.soffid.iam.api.DataType;
import com.soffid.iam.api.Issue;
import com.soffid.iam.api.IssueActionDefinition;
import com.soffid.iam.api.IssuePolicy;
import com.soffid.iam.api.IssuePolicyAction;
import com.soffid.iam.api.PagedResult;
import com.soffid.iam.api.User;
import com.soffid.iam.bpm.service.scim.ScimHelper;
import com.soffid.iam.common.security.SoffidPrincipal;
import com.soffid.iam.model.IssueEntity;
import com.soffid.iam.model.IssueEntityDao;
import com.soffid.iam.model.IssuePolicyActionEntity;
import com.soffid.iam.model.IssuePolicyEntity;
import com.soffid.iam.model.IssuePolicyEntityDao;
import com.soffid.iam.model.criteria.CriteriaSearchConfiguration;
import com.soffid.iam.service.impl.IssueDataParser;
import com.soffid.iam.utils.Security;
import com.soffid.scimquery.EvalException;
import com.soffid.scimquery.parser.ParseException;
import com.soffid.scimquery.parser.TokenMgrError;

import es.caib.seycon.ng.exception.InternalErrorException;

public class IssuePolicyServiceImpl extends IssuePolicyServiceBase {

	@Override
	protected AsyncList<IssuePolicy> handleFindIssuePoliciesByJsonQueryAsync(String query) throws Exception {
		AsyncList<IssuePolicy> l = new AsyncList<>();
		getAsyncRunnerService().run(() -> {
			try {
				findIssuePolicies(query, null, null, l);
			} catch (Exception e) {
				l.cancel(e);
			}
		}, l);
		return l;
	}

	private PagedResult<IssuePolicy> findIssuePolicies(String query, Integer start, Integer pageSize, List<IssuePolicy> l) throws UnsupportedEncodingException, ClassNotFoundException, JSONException, InternalErrorException, EvalException, ParseException, TokenMgrError {
		final SoffidPrincipal soffidPrincipal = Security.getSoffidPrincipal();
		String[] roles = soffidPrincipal.getSoffidRoles();
		int rolesStep = 100;
		
		final IssuePolicyEntityDao dao = getIssuePolicyEntityDao();
		PagedResult<IssuePolicy> pr = new PagedResult<>();
		pr.setStartIndex(start);
		pr.setItemsPerPage(pageSize);
		int total = 0;
		for (int roleIndex = 0; roleIndex <= roles.length; roleIndex += rolesStep) {
			List<String> newRoles = new LinkedList<>();
			for (int i = roleIndex; i < roleIndex + rolesStep && i < roles.length; i++) 
				newRoles.add(roles[i]);
			if (newRoles.size() < rolesStep && soffidPrincipal.getUserName() != null)
				newRoles.add(soffidPrincipal.getUserName());
			
			ScimHelper h = new ScimHelper(IssuePolicy.class);
			CriteriaSearchConfiguration config = new CriteriaSearchConfiguration();
			config.setFirstResult(start);
			config.setMaximumResultSize(pageSize);
			h.setConfig(config);
			h.setTenantFilter("tenant.id");
			h.setGenerator((entity) -> {
				IssuePolicyEntity ue = (IssuePolicyEntity) entity;
				return dao.toIssuePolicy(ue);
			});
			
			h.setExtraWhere("o.actor in (:list)");
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
	protected IssuePolicy handleUpdate(IssuePolicy Issue) throws Exception {
		IssuePolicyEntity entity = getIssuePolicyEntityDao().load(Issue.getId());
		getIssuePolicyEntityDao().issuePolicyToEntity(Issue, entity, true);
		getIssuePolicyEntityDao().update(entity);
		
		// Updates
		LinkedList<IssuePolicyActionEntity> l = new LinkedList<>(entity.getActions());
		for (Iterator<IssuePolicyActionEntity> it = l.iterator(); it.hasNext(); ) {
			IssuePolicyActionEntity action = it.next();
			for (Iterator<IssuePolicyAction> it2 = Issue.getActions().iterator(); it2.hasNext(); ) {
				IssuePolicyAction action2 = it2.next();
				if (action.getId().equals(action2.getId())) {
					getIssuePolicyActionEntityDao().issuePolicyActionToEntity(action2, action, true);
					getIssuePolicyActionEntityDao().update(action);
					it.remove();
					it2.remove();
					break;
				}
			}
		}
		
		// Delete
		for (IssuePolicyActionEntity actionEntity: l) {
			getIssuePolicyActionEntityDao().remove(actionEntity);
			entity.getActions().remove(actionEntity);
		}
		
		// Insert
		for (IssuePolicyAction action: Issue.getActions()) {
			IssuePolicyActionEntity actionEntity = getIssuePolicyActionEntityDao().newIssuePolicyActionEntity();
			getIssuePolicyActionEntityDao().issuePolicyActionToEntity(action, actionEntity, true);
			actionEntity.setIssuePolicy(entity);
			entity.getActions().add(actionEntity);
			getIssuePolicyActionEntityDao().create(actionEntity);
		}
		
		return getIssuePolicyEntityDao().toIssuePolicy(entity);
	}

	@Override
	protected PagedResult<IssuePolicy> handleFindIssuePoliciesByJsonQuery(String query, Integer first, Integer pageSize)
			throws Exception {
		LinkedList<IssuePolicy> l = new LinkedList<>();
		return findIssuePolicies(query, first, pageSize, l);
	}

	@Override
	protected List<IssueActionDefinition> handleListAutomaticActions() throws Exception {
		List<IssueActionDefinition> list = new LinkedList<>();
		JSONArray actions = IssueDataParser.instance().getActions();
		for (int i = 0; i < actions.length(); i++) {
			JSONObject o = actions.getJSONObject(i);
			IssueActionDefinition def  = new IssueActionDefinition();
			def.setName(o.optString("name", null));
			def.setLabel(o.optString("nlsLabel", "com.soffid.iam.api.IssueActionDefinition." +  def.getName()));
			LinkedList<DataType> att = new LinkedList<DataType>();
			def.setParameters(att);
			JSONArray parameters = o.optJSONArray("parameters");
			if (parameters != null) {
				for (int j = 0; j < parameters.length(); j++) {
					String name = parameters.getString(j);
					DataType dt = getMetadata(name);
					if (dt != null)
						att.add(dt);
				}
			}
			list.add(def);
		}
		return list;
	}

	private DataType getMetadata(String name) throws InternalErrorException {
		for (DataType dt: getAdditionalDataService().findDataTypesByObjectTypeAndName2(IssuePolicyAction.class.getName(), name)) {
			return dt;
		}
		return null;
	}

	@Override
	protected void handleCreatePolicies() throws Exception {
		List<IssuePolicyEntity> l = getIssuePolicyEntityDao().loadAll();
		JSONArray Issues = IssueDataParser.instance().getIssues();
		for (int i = 0; i < Issues.length(); i++) {
			IssuePolicyEntity found = null;
			final JSONObject jsonObject = Issues.getJSONObject(i);
			final String name = jsonObject.optString("name", "");
			for (java.util.Iterator<IssuePolicyEntity> it = l.iterator(); it.hasNext();) {
				IssuePolicyEntity policy = it.next();
				if (policy.getType().equals(name)) {
					it.remove();
					found = policy;
					break;
				}
			}
			if (found == null) {
				IssuePolicyEntity entity = getIssuePolicyEntityDao().newIssuePolicyEntity();
				entity.setDescription("");
				entity.setType(name);
				entity.setActor("SOFFID_ADMIN@soffid");
				getIssuePolicyEntityDao().create(entity);
			}
		}
	}
	
}
