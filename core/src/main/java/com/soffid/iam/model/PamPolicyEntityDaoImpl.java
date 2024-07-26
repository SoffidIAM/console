package com.soffid.iam.model;

import java.util.HashSet;
import java.util.LinkedList;

import com.soffid.iam.api.PamPolicy;

public class PamPolicyEntityDaoImpl extends PamPolicyEntityDaoBase {
	@Override
	public void toPamPolicy(PamPolicyEntity source, PamPolicy target) {
		super.toPamPolicy(source, target);
		HashSet<String> l = new HashSet<>();
		for (PamPolicyJITPermissionEntity jit: source.getJustInTimePermissions()) {
			l.add(jit.getName());
		}
		target.setJustInTimePermissions(new LinkedList<>(l));
	}
}
