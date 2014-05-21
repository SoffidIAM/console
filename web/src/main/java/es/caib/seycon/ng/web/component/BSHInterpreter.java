package es.caib.seycon.ng.web.component;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zkoss.zk.scripting.Namespace;
import org.zkoss.zk.ui.UiException;

import bsh.EvalError;
import bsh.TargetError;


public class BSHInterpreter extends org.zkoss.zk.scripting.bsh.BSHInterpreter {
	Log log = LogFactory.getLog(getClass());
	
	@Override
	protected void exec(String script) {
		try {
			super.exec(script);
		} catch (RuntimeException ex) {
			log.warn(String.format(Messages.getString("BSHInterpreter.ExecutingStringError"), ex.getMessage())); //$NON-NLS-1$
			log.info(String.format(Messages.getString("BSHInterpreter.BadScript"), script)); //$NON-NLS-1$
			if (ex.getCause() instanceof TargetError)
			{
				TargetError e = (TargetError) ex.getCause();
				throw new RuntimeException(e.getTarget());
			}
			throw ex;
		}
	}

}
