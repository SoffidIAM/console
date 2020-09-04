package com.soffid.iam.web.menu;

import java.util.Collection;
import java.util.Vector;

import com.soffid.iam.api.AccessTree;
import com.soffid.iam.api.AccessTreeExecution;

import es.caib.zkib.datamodel.DataContext;
import es.caib.zkib.datamodel.xml.handler.FinderHandler;


public class ExecutionFinder implements FinderHandler {

	@Override
	public boolean isSuitable(DataContext node) {
		return true;
	}

	@Override
	public Collection find(DataContext ctx) throws Exception {
		AccessTree at = (AccessTree) ctx.getCurrent().getInstance();
		
		Vector<WebAccessTreeExecution> v = new Vector<>(3);
		v.setSize(3);
		WebAccessTreeExecution wate = new WebAccessTreeExecution();
		AccessTreeExecution ate = wate.getExec();
		ate.setScope("L");
		ate.setAccessTreeId(at.getId());
		v.set(0,  wate);
		wate = new WebAccessTreeExecution();
		ate = wate.getExec();
		ate.setScope("W");
		ate.setAccessTreeId(at.getId());
		v.set(1,  wate);
		wate = new WebAccessTreeExecution();
		ate = wate.getExec();
		ate.setScope("I");
		ate.setAccessTreeId(at.getId());
		v.set(2,  wate);
		wate = new WebAccessTreeExecution();
		ate = wate.getExec();
		if (at.getExecutions() != null) {
			for (AccessTreeExecution a: at.getExecutions()) {
				if ("L".equals(a.getScope())) {
					v.get(0).setExec(a);
					v.get(0).setEnabled(true);
				}
				if ("W".equals(a.getScope())) {
					v.get(1).setExec(a);
					v.get(1).setEnabled(true);
				}
				if ("I".equals(a.getScope())) {
					v.get(2).setExec(a);
					v.get(2).setEnabled(true);
				}
			}
		}
		return v;

	}

}
