package com.soffid.iam.interp;

import org.openjdk.nashorn.api.scripting.AbstractJSObject;

public class ServiceFunctionMethod extends AbstractJSObject {
	private Object service;

	public ServiceFunctionMethod(Object service) {
		this.service = service;
	}

	@Override
	public Object call(Object thiz, Object... args) {
		return service;
	}

	@Override
	public boolean isFunction() {
		return true;
	}

}
