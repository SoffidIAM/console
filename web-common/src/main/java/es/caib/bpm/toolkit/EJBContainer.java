package es.caib.bpm.toolkit;

import javax.ejb.CreateException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.LogFactory;
import org.zkoss.zk.ui.Session;

import es.caib.bpm.servei.ejb.BpmConfigService;
import es.caib.bpm.servei.ejb.BpmConfigServiceHome;
import es.caib.bpm.servei.ejb.BpmEngine;
import es.caib.bpm.servei.ejb.BpmEngineHome;


public class EJBContainer {
	private static final String EJB_CONTAINER = "ejb-container"; //$NON-NLS-1$
	transient BpmEngine engine = null;
	
	public static EJBContainer getEJBContainer (HttpSession session)
	{
		EJBContainer container = (EJBContainer) session.getAttribute(EJB_CONTAINER);
		if (container == null)
		{
			container = new EJBContainer ();
			session.setAttribute(EJB_CONTAINER, container);
		}
		return container;
	}
	
	public static EJBContainer getEJBContainer (Session session)
	{
		EJBContainer container = (EJBContainer) session.getAttribute(EJB_CONTAINER);
		if (container == null)
		{
			container = new EJBContainer ();
			session.setAttribute(EJB_CONTAINER, container);
		}
		return container;
	}

	public static void dropEJBContainer (HttpSession session)
	{
		session.removeAttribute(EJB_CONTAINER);
	}

	public static void dropEJBContainer (Session session)
	{
		session.removeAttribute(EJB_CONTAINER);
	}

	public BpmEngine getEngine () throws CreateException, NamingException
	{
		
		if (engine != null)
		{
			try {
				engine.ping();
			} catch (Throwable t) {
				if(!t.getMessage().contains("tried to enter Stateful bean with different tx context"))				  //$NON-NLS-1$
					engine = null;
				else
					LogFactory.getLog(this.getClass()).error(t.getMessage(), t);
			}
		}
		if (engine == null)
		{
			engine = (BpmEngine) new InitialContext().lookup (BpmEngineHome.JNDI_NAME);
		}
		return engine;

	}

	public static BpmConfigService getBPMConfigBean () throws CreateException, NamingException
	{
		try{
			return  (BpmConfigService) new InitialContext().lookup (BpmConfigServiceHome.JNDI_NAME);
		}catch(NamingException e){
			LogFactory.getLog(EJBContainer.class).error(e.getMessage(), e);
			throw e;
		}
	}
	
}
