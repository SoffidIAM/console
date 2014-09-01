package com.soffid.web;

import java.lang.reflect.Method;

import javax.servlet.jsp.el.FunctionMapper;

import org.jbpm.jpdl.el.ELException;

import es.caib.seycon.ng.utils.Security;

public class SecurityFunctionMapper implements FunctionMapper {

	public Method resolveFunction(String prefix, String functionName) {
		if (prefix == null || prefix.length() == 0)
		{
			if (functionName.equals ("isUserInRole"))
			{
				try {
					return Security.class.getMethod("isUserInRole",  String.class);
				} catch (NoSuchMethodException e) {
					throw new ELException(e);
				}
			}
				
		}
		return null;
	}

}
