/**
 * 
 */
package com.soffid.iam.service;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.servei.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.Principal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.apache.commons.collections.map.LRUMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.soffid.iam.bpm.service.impl.UserContextCache;
import com.soffid.iam.utils.ConfigurationCache;
import com.soffid.iam.utils.Security;

/**
 * @author bubu
 *
 */
public class SessionCacheServiceImpl extends com.soffid.iam.service.SessionCacheServiceBase
	implements ApplicationContextAware
{
	private Log log = LogFactory.getLog(getClass());
	/**
	 * 
	 */
	@SuppressWarnings ("unchecked")
	public SessionCacheServiceImpl ()
	{
	}
	
	@SuppressWarnings ("rawtypes")
	static Map<String,Map<String,Object>> map = null;
	static ThreadLocal<String> currentSession = new ThreadLocal<String>();
	@SuppressWarnings ("rawtypes")
	static ThreadLocal<Map<String,Object>> currentMap = new ThreadLocal<Map<String,Object>>();


	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.SessionCacheServiceBase#handleGetObject(java.lang.String)
	 */
	@Override
	protected Object handleGetObject (String tag) throws Exception
	{
		Map<String, Object> sessionMap = currentMap.get();
		if (sessionMap == null)
			return null;
		return sessionMap.get(Security.getCurrentTenantName()+"/"+tag);
	}

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.SessionCacheServiceBase#handlePutObject(java.lang.String, java.lang.Object)
	 */
	@Override
	protected void handlePutObject (String tag, Object value) throws Exception
	{
		Map<String, Object> sessionMap = currentMap.get();
		if (sessionMap != null)
			sessionMap.put(Security.getCurrentTenantName()+"/"+tag, value);
	}

	@Override
	protected String handleClearSession() throws Exception {
		currentMap.set(null);
		currentSession.set(null);
		return null;
	}

	static Random random = new Random();
	@Override
	protected String handleCreateSession() throws Exception {
		String id;
		do
		{
			id = Security.getCurrentTenantName()+"_"+Security.getCurrentAccount()+"_"+random.nextLong();
		} while (map.containsKey(id));
		Map<String,Object> sessionMap = new HashMap<String, Object>();
		currentSession.set(id);
		currentMap.set(sessionMap);
		map.put(id, sessionMap);
		return id;
	}

	@Override
	protected String handleGetCurrentSessionId() throws Exception {
		return currentSession.get();
	}

	@Override
	protected String handleSetSession(String sessionId) throws Exception {
		if ( ! map.containsKey(sessionId))
		{
			Map<String,Object> sessionMap = new HashMap<String, Object>();
			currentSession.set(sessionId);
			currentMap.set(sessionMap);
			map.put(sessionId, sessionMap);
		}
		else
		{
			currentMap.set( map.get(sessionId));
			currentSession.set(sessionId);
		}
		return sessionId;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
    	int size = 500;
    	try {
	    	String cacheSize = ConfigurationCache.getMasterProperty("soffid.cache.identity.size");
	    	if (cacheSize != null )
	    		size = Integer.parseInt(cacheSize);
    	} catch (Throwable t) {
    		
    	}
		map = Collections.synchronizedMap(new LRUMap(size));			
	}

}
