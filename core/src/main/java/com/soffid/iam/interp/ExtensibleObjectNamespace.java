/**
 * 
 */
package com.soffid.iam.interp;

import java.util.HashMap;
import java.util.Map;

import com.soffid.iam.config.Config;
import com.soffid.iam.remote.RemoteServiceLocator;

import bsh.BshClassManager;
import bsh.ExternalNameSpace3;
import bsh.NameSpace;
import bsh.Primitive;
import bsh.UtilEvalError;
import bsh.Variable;

public class ExtensibleObjectNamespace extends ExternalNameSpace3 {
	Map<String, Object> externalMap = new HashMap<String, Object>();
	private Map<String, Object> vars;
	
	/**
	 * @param parent
	 * @param classManager
	 * @param name
	 * @param dades
	 * @param ead
	 * @param accountName
	 */
	@SuppressWarnings("unchecked")
	public ExtensibleObjectNamespace (NameSpace parent, BshClassManager classManager, String name,
					Map<String,Object> vars)
	{
		super(parent, name, new HashMap<String, Object>());
		externalMap = super.getMap();
		this.vars = vars;
	}

	boolean inSetVariable = false;
	
	@Override
	protected Variable getVariableImpl(String name, boolean recurse)
			throws UtilEvalError {
		try
		{
			if (inSetVariable || externalMap.get(name) != null)
				return super.getVariableImpl(name, recurse);

			Object value = vars.get(name);
			if ("serviceLocator".equals (name))
			{
				Config config = Config.getConfig();
				if (config.isAgent())
					externalMap.put(name,  new RemoteServiceLocator());
				else
					externalMap.put(name,  com.soffid.iam.ServiceLocator.instance());
			}
			else if ("THIS".equalsIgnoreCase(name) )
				externalMap.put(name,  vars);
			else if (vars.containsKey(name)){
				if (value == null || value.getClass().getSimpleName().equals("NullSqlObjet"))
					externalMap.put(name,  Primitive.NULL);
				else
					externalMap.put(name,  value);				
			}

			return super.getVariableImpl(name, recurse);
		}
		catch (Exception e)
		{
			throw new UtilEvalError(e.toString());
		}
	}

}
