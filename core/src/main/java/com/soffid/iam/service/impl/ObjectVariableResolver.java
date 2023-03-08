package com.soffid.iam.service.impl;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.PropertyUtils;
import org.jbpm.jpdl.el.ELException;
import org.jbpm.jpdl.el.VariableResolver;

public class ObjectVariableResolver implements VariableResolver {
	private Object object;

	public ObjectVariableResolver(Object o) {
		this.object = o;
	}

	@Override
	public Object resolveVariable(String pName) throws ELException {
		try {
			return PropertyUtils.getProperty(object, pName);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			throw new ELException("Error evaluating "+pName, e);
		} 
	}
}