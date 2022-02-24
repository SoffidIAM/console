package com.soffid.iam.interp;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.openjdk.nashorn.api.scripting.AbstractJSObject;
import org.openjdk.nashorn.internal.runtime.ECMAException;
import org.springframework.context.ApplicationContext;

import com.soffid.iam.remote.RemoteServiceLocator;
import com.soffid.iam.utils.Security;

public class SecureServiceLocator extends AbstractJSObject {
	RemoteServiceLocator remoteServiceLocator;
	com.soffid.iam.ServiceLocator localServiceLocator;

	SecureServiceLocator() {
		if (Security.isSyncProxy())
			remoteServiceLocator = new RemoteServiceLocator();
		else
			localServiceLocator = com.soffid.iam.ServiceLocator.instance();
	}
	
	@Override
	public Object getMember(String name) {
		try {
			if (name.startsWith("get")) {
				Object service = findService (name.substring(3));
				if (service == null)
					return null;
				return new ServiceFunctionMethod( service );
			}
			else
				return findService(name);
		} catch (Exception e) {
			throw new ECMAException("Service unavailable", e);
		}
	}

	private Object findService(String name) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		if (name.equalsIgnoreCase("context"))
			return false;
		if (name.toLowerCase().endsWith("dao"))
			return null;
		Object service = null;
		if (localServiceLocator != null) {
			String methodName = "get"+name.substring(0, 1).toUpperCase()+name.substring(1);
			try {
				Method m = remoteServiceLocator.getClass().getMethod(methodName);
				service = m.invoke(remoteServiceLocator);
			} catch (NoSuchMethodException e) {
				
			}
			if (service == null) {
				name = name.substring(0, 1).toLowerCase()+name.substring(1);
				ApplicationContext ctx = localServiceLocator.getContext();
				for (String n: ctx.getBeanDefinitionNames()) {
					if (n.equalsIgnoreCase(name) || 
							n.equalsIgnoreCase(name+"-v2") ||
							n.toLowerCase().endsWith("-"+name))
					{
						service = ctx.getBean(n);
						break;
					}
				}
			}
		}
		
		if (remoteServiceLocator != null) {
			String methodName = "get"+name.substring(0, 1).toUpperCase()+name.substring(1);
			try {
				Method m = remoteServiceLocator.getClass().getMethod(methodName);
				return m.invoke(remoteServiceLocator);
			} catch (NoSuchMethodException e) {
				
			}
		}
		
		// Check the service implements the desired interface
		if (service != null) {
			boolean valid = false;
			for (Class<?> intf: service.getClass().getInterfaces() ) {
				if (intf.getName().startsWith("com.soffid.iam") && 
						intf.getSimpleName().equalsIgnoreCase(name)) {
					valid = true;
					break;
				}
			}
			if (!valid)
				return false;
		}
		
		return service;
	}

	@Override
	public boolean hasMember(String name) {
		try {
			if (name.startsWith("get")) {
				Object service = findService (name.substring(3));
				return service != null;
			}
			else
				return findService(name) != null;
		} catch (Exception e) {
			throw new ECMAException("Service unavailable", e);
		}
	}

}
