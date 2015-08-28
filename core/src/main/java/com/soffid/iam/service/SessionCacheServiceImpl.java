/**
 * 
 */
package com.soffid.iam.service;

import es.caib.seycon.ng.servei.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.Principal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.collections.map.LRUMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.webdav.lib.properties.GetLastModifiedProperty;
import org.zkoss.zk.ui.Sessions;

import com.soffid.iam.bpm.service.impl.UserContextCache;
import com.soffid.iam.utils.Security;

/**
 * @author bubu
 *
 */
public class SessionCacheServiceImpl extends com.soffid.iam.service.SessionCacheServiceBase
{
	private Class<?> zkSessionClass = null;
	private Method zkGetCurrentMethod = null;
	private Method zkGetNativeSession = null;
	private Log log = LogFactory.getLog(getClass());
	private Class<?> zkSessionsClass;
	/**
	 * 
	 */
	@SuppressWarnings ("unchecked")
	public SessionCacheServiceImpl ()
	{
	}
	
	private void loadSessionClass ()
	{
		if (zkSessionClass == null)
		{
    		try
    		{
    			zkSessionClass = Class.forName("org.zkoss.zk.ui.Session"); //$NON-NLS-1$
    			zkSessionsClass = Class.forName("org.zkoss.zk.ui.Sessions"); //$NON-NLS-1$
    			zkGetNativeSession = zkSessionClass.getMethod("getNativeSession", new Class[0]); //$NON-NLS-1$
    			zkGetCurrentMethod = zkSessionsClass.getMethod("getCurrent", new Class[0]); //$NON-NLS-1$
    		}
    		catch (Exception e)
    		{
    			log.info (Messages.getString("SessionCacheServiceImpl.SessionCacheServiceDisabled") + e.toString()); //$NON-NLS-1$
    		}
    		if (map == null)
    		{
    			map = Collections.synchronizedMap(new LRUMap(500));			
    		}
		}
	}
	
	@SuppressWarnings ("rawtypes")
	static Map map = null;
	public static UserContextCache get (String user) {
		UserContextCache data = (UserContextCache) map.get(user);
		return data;
	}
	
	@SuppressWarnings ("unchecked")
	public static void  put (String user, UserContextCache ctx ) {
		map.put(user, ctx);
	}

	@SuppressWarnings ("unchecked")
	private Map<String,Object> getSessionContext() throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		loadSessionClass();
		if (zkGetCurrentMethod == null || zkGetNativeSession == null)
			return null;
		
		String sessionId;
		Principal p = Security.getPrincipal();
		String user;
		if (p == null)
			user = "nobody"; //$NON-NLS-1$
		else
			user = p.getName();

		
		Object session = zkGetCurrentMethod.invoke(null, new Object[0]);
		if (session == null) {
			return null;
		}

		HttpSession s = (HttpSession) zkGetNativeSession.invoke(session, new Object[0]);
		sessionId = "s" + s.getId()+"."+user; //$NON-NLS-1$ //$NON-NLS-2$
		
		Map<String,Object> v = (Map<String, Object>) map.get(sessionId);
		if (v == null)
		{
			v = new HashMap<String, Object>();
			map.put(sessionId, v);
		}
		
		return v;

	}


	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.SessionCacheServiceBase#handleGetObject(java.lang.String)
	 */
	@Override
	protected Object handleGetObject (String tag) throws Exception
	{
		Map<String, Object> sessionMap = getSessionContext();
		if (sessionMap == null)
			return null;
		return sessionMap.get(tag);
	}

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.SessionCacheServiceBase#handlePutObject(java.lang.String, java.lang.Object)
	 */
	@Override
	protected void handlePutObject (String tag, Object value) throws Exception
	{
		Map<String, Object> sessionMap = getSessionContext();
		if (sessionMap != null)
			sessionMap.put(tag, value);
	}

}
