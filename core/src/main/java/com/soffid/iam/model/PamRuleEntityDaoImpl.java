package com.soffid.iam.model;

import com.soffid.iam.api.PamRule;

public class PamRuleEntityDaoImpl extends PamRuleEntityDaoBase {

	@Override
	public void toPamRule(PamRuleEntity source, PamRule target) {
		super.toPamRule(source, target);
		if (source.getContent() != null && ! source.getContent().trim().isEmpty())
			target.setContent(source.getContent());
		else if (source.getBlob() != null && ! source.getBlob().trim().isEmpty())
			target.setContent(source.getBlob());
		else
			target.setContent("");
	}

	@Override
	public void pamRuleToEntity(PamRule source, PamRuleEntity target, boolean copyIfNull) {
		super.pamRuleToEntity(source, target, copyIfNull);
		if (source.getContent() == null || source.getContent().trim().isEmpty()) {
			target.setContent(null);
			target.setBlob(null);
		} else if (source.getContent().length() < 500) {
			target.setContent(source.getContent());
			target.setBlob(null);
		} else {
			target.setContent(null);
			target.setBlob(source.getContent());
		}
	}

}
