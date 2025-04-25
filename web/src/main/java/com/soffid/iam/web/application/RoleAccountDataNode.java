package com.soffid.iam.web.application;

import java.lang.reflect.InvocationTargetException;

import com.soffid.iam.api.RoleAccount;

import es.caib.zkib.datamodel.DataContext;
import es.caib.zkib.datamodel.DataNode;


public class RoleAccountDataNode extends DataNode {
	public RoleAccountDataNode(DataContext ctx) {
		super(ctx);
		ctx.setData(new RoleAccount());
	}

	@Override
	public String getChildProperty() {
		return null;
	}

	@Override
	public Object getCurrentId() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		return null;
	}

	@Override
	public Object getParentId() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		return null;
	}

	@Override
	public Object loadParentObject() throws Exception {
		return null;
	}

	@Override
	protected void doDelete() throws Exception {
	}

	@Override
	protected void doInsert() throws Exception {
	}

	@Override
	protected void doUpdate() throws Exception {
	}

}
