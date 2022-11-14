package com.soffid.iam.interp;

import java.util.Map;

public class AutodetectEvaluator extends Evaluator {
	BeanshellEvaluator bsh = new BeanshellEvaluator();
	JavascriptEvaluator js = new JavascriptEvaluator();
	
	@Override
	public Object evaluate(String script, Map<String, Object> vars, String label) throws Exception {
		return isJavascript(script) ? 
				js.evaluate(script, vars, label) : 
				bsh.evaluate(script, vars, label);
	}

	public boolean isJavascript(String script) {
		return script.startsWith("//js") || script.startsWith("//javascript") ||
				script.startsWith("/*js*/");
	}

	@Override
	public boolean isSecure() {
		return false;
	}

	@Override
	public String translateFromBsh(String bshScript) {
		return isJavascript(bshScript) ? 
				js.translateFromBsh(bshScript): 
				bsh.translateFromBsh(bshScript);
	}

}
