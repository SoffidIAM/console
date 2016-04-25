package com.soffid.iam.model;

import java.util.List;

import org.hibernate.QueryException;
import org.hibernate.dialect.function.SQLFunction;
import org.hibernate.engine.Mapping;
import org.hibernate.engine.SessionFactoryImplementor;
import org.hibernate.type.LongType;
import org.hibernate.type.Type;

import com.soffid.iam.utils.Security;

public class CurrentTenantFunction implements SQLFunction {

	@Override
	public Type getReturnType(Type columnType, Mapping mapping)
			throws QueryException {
		return new LongType ();
	}

	@Override
	public boolean hasArguments() {
		return false;
	}

	@Override
	public boolean hasParenthesesIfNoArguments() {
		return false;
	}

	@Override
	public String render(List args, SessionFactoryImplementor factory)
			throws QueryException {
		return Long.toString(Security.getCurrentTenantId());
	}

}
