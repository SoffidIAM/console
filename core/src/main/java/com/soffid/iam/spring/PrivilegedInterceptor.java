package com.soffid.iam.spring;

import java.lang.reflect.InvocationTargetException;
import java.security.AccessController;
import java.security.PrivilegedAction;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class PrivilegedInterceptor implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation mi) throws Throwable {
		final Response r = new Response();
		r.method = mi;
		
		AccessController.doPrivileged(new PrivilegedAction<Object>() {
			public Object run() {
				try {
					r.result = r.method.getMethod().invoke(r.method.getThis(), r.method.getArguments());
				} catch (Throwable e) {
					r.error = e;
				}
				return null;
			}
		});
		if (r.error != null)
			throw r.error;
		else
			return r.result;
	}

	class Response implements PrivilegedAction<Object> {
		MethodInvocation method;
		Object result;
		Throwable error;
		@Override
		public Object run() {
			try {
				result = method.getMethod().invoke(method.getThis(), method.getArguments());
			} catch (Throwable e) {
				error = e;
			}
			return null;
		}
	}
}
