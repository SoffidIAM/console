package com.soffid.iam.interp;

import java.util.Map;

import com.soffid.iam.service.impl.bshjail.SecureInterpreter;

import bsh.EvalError;
import bsh.Interpreter;
import bsh.NameSpace;
import bsh.ParseException;
import bsh.Primitive;
import bsh.TargetError;
import es.caib.seycon.ng.exception.InternalErrorException;

public class BeanshellEvaluator extends Evaluator {
	static ThreadLocal<SecureInterpreter> interpreters = new ThreadLocal<SecureInterpreter>();

	@Override
	public Object evaluate(String script, Map<String, Object> vars, String label) throws Exception {
		if (!vars.containsKey("out"))
			vars.put("out", System.out);
		SecureInterpreter interpreter = interpreters.get();
		if ( interpreter == null)
		{
			interpreter = new SecureInterpreter();
			interpreters.set(interpreter);
		}
		SecureInterpreter interpret = new SecureInterpreter();
		NameSpace ns = interpret.getNameSpace();

		ExtensibleObjectNamespace newNs = new ExtensibleObjectNamespace(ns, interpret.getClassManager(),
						"interpeter", vars);
		
		try {
			Object result = interpret.eval(script, newNs);
			if (result != null && result instanceof Primitive)
			{
				result = ((Primitive)result).getValue();
			}
			return result;
		} catch (ParseException e) {
			throw new InternalErrorException("Error evaluating "+label+": "+e.getMessage()+"\nat: "+e.getScriptStackTrace());
		} catch (TargetError e) {
			throw new InternalErrorException ("Error evaluating "+label+": "+
					e.getTarget().getMessage(),
					e.getTarget());
		} catch (EvalError e) {
			String msg;
			try {
				msg = e.getMessage() + "[ "+ e.getErrorText()+"] ";
			} catch (Exception e2) {
				msg = e.getMessage();
			}
			throw new InternalErrorException ("Error evaluating "+label+": "+msg);
		}
	}

	@Override
	public boolean isSecure() {
		return false;
	}

	@Override
	public String translateFromBsh(String bshScript) {
		return bshScript;
	}

}
