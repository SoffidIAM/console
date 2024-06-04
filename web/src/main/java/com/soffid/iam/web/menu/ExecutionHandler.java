package com.soffid.iam.web.menu;

import java.util.Collection;
import java.util.Vector;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.AccessTree;
import com.soffid.iam.api.AccessTreeExecution;

import es.caib.zkib.datamodel.DataContext;
import es.caib.zkib.datamodel.xml.handler.FinderHandler;
import es.caib.zkib.datamodel.xml.handler.PersistenceHandler;


public class ExecutionHandler implements PersistenceHandler {

	@Override
	public boolean isSuitable(DataContext node) {
		return true;
	}

	@Override
	public void doInsert(DataContext node) throws Exception {
	}

	@Override
	public void doDelete(DataContext node) throws Exception {
	}

	@Override
	public void doUpdate(DataContext node) throws Exception {
		AccessTree at = (AccessTree) node.getParent().getInstance();
		WebAccessTreeExecution wat = (WebAccessTreeExecution) node.getCurrent().getInstance();
		if (wat.enabled) {
			if (wat.getExec().getId() == null) {
				at.getExecutions().remove(wat.getExec());
				wat.setExec(EJBLocator.getEntryPointService().createExecution(at, wat.getExec()));
				at.getExecutions().add(wat.getExec());
			} else {
				EJBLocator.getEntryPointService().updateExecution(at, wat.getExec());
			}
		} else {
			if (wat.getExec().getId() == null) {
				// Already removed
			} else {
				at.getExecutions().remove(wat.getExec());
				EJBLocator.getEntryPointService().deleteExecution(at, wat.getExec());
			}
		}
	}

}
