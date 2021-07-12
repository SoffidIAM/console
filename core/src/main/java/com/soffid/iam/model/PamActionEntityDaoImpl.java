package com.soffid.iam.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.soffid.iam.api.PamAction;

public class PamActionEntityDaoImpl extends PamActionEntityDaoBase {

	@Override
	public PamActionEntity pamActionToEntity(PamAction instance) {
		return null;
	}

	@Override
	protected PamAction handleCreate(PamAction action) throws Exception {
		handleRemove(action);
		if (action.getActions() != null) {
			for (PamActionType a: action.getActions()) {
				create (action, a);
			}
		}
		return action;
	}

	private void create(PamAction action, PamActionType a) {
		PamActionEntity entity = newPamActionEntity();
		entity.setAuthor(action.getAuthor());
		entity.setDate(action.getDate());
		entity.setPolicy(getPamPolicyEntityDao().findByName(action.getPolicyName()));
		if (entity.getPolicy() == null)
			throw new RuntimeException ("Wrong policy "+action.getPolicyName());
		entity.setRule(getPamRuleEntityDao().findByName(action.getRuleName()));
		if (entity.getRule() == null)
			throw new RuntimeException ("Wrong rule "+action.getRuleName());
		entity.setType(a);
		create(entity);
	}

	@Override
	protected PamAction handleUpdate(PamAction action) throws Exception {
		return handleCreate(action);
	}

	@Override
	protected void handleRemove(PamAction action) throws Exception {
		remove(findByPolicyAndRule(action.getPolicyName(), action.getRuleName()));
	}

	@Override
	public void toPamAction(PamActionEntity source, PamAction target) {
		super.toPamAction(source, target);
		target.setPolicyName(source.getPolicy().getName());
		target.setRuleName(source.getRule().getName());
		target.setAuthor(source.getAuthor());
		target.setDate(source.getDate());
		target.setActions(new LinkedList<>());
		target.getActions().add(source.getType());
	}

	@Override
	protected List<PamAction> handleGetActionsByPolicy(String policyName) throws Exception {
		HashMap<String,PamAction> actions = new HashMap<>();
		for (PamRuleEntity rule: getPamRuleEntityDao().loadAll()) {
			PamAction a = new PamAction();
			a.setPolicyName(policyName);
			a.setRuleName(rule.getName());
			a.setAuthor(rule.getAuthor());
			a.setDate(rule.getDate());
			a.setActions(new LinkedList<>());
			actions.put(a.getRuleName(),a);
		}
		PamPolicyEntity policy = getPamPolicyEntityDao().findByName(policyName);
		if (policy != null) {
			for (PamActionEntity entity: findByPolicy(policyName) ) {
				PamAction a = actions.get(entity.getRule().getName());
				if (a != null && entity.getType() != PamActionType.NONE)
					a.getActions().add(entity.getType());
			}
		}
		return new LinkedList<>(actions.values());
	}

}
