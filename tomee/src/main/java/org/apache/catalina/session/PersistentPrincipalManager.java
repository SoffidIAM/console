package org.apache.catalina.session;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.catalina.Context;
import org.apache.catalina.Loader;
import org.apache.catalina.Session;
import org.apache.catalina.session.StandardManager;
import org.apache.catalina.session.StandardSession;
import org.apache.catalina.util.CustomObjectInputStream;
import org.apache.commons.logging.LogFactory;
import org.apache.juli.logging.Log;
import org.apache.tomcat.util.ExceptionUtils;

public class PersistentPrincipalManager extends StandardManager {
	org.apache.commons.logging.Log log = LogFactory.getLog(getClass());
    /**
     * Load any currently active sessions that were previously unloaded
     * to the appropriate persistence mechanism, if any.  If persistence is not
     * supported, this method returns without doing anything.
     *
     * @exception ClassNotFoundException if a serialized class cannot be
     *  found during the reload
     * @exception IOException if an input/output error occurs
     */
    protected void doLoad() throws ClassNotFoundException, IOException {
        if (log.isDebugEnabled()) {
            log.debug("Start: Loading persisted sessions");
        }

        // Initialize our internal data structures
        sessions.clear();

        // Open an input stream to the specified pathname, if any
        File file = file();
        if (file == null) {
            return;
        }
        if (log.isDebugEnabled()) {
            log.debug(sm.getString("standardManager.loading", pathname));
        }
        Loader loader = null;
        ClassLoader classLoader = null;
    	FileInputStream fis = null;
        BufferedInputStream bis = null;
        try
        {
        	fis = new FileInputStream(file.getAbsolutePath());
            bis = new BufferedInputStream(fis);
            Context c = getContext();
            loader = c.getLoader();
            Log logger = c.getLogger();
            if (loader != null) {
                classLoader = loader.getClassLoader();
            }
            if (classLoader == null) {
                classLoader = getClass().getClassLoader();
            }

            // Load the previously unloaded active sessions
            synchronized (sessions) {
            	ObjectInputStream ois = null;
                try {
                	ois = new CustomObjectInputStream(bis, classLoader, logger,
                        getSessionAttributeValueClassNamePattern(),
                        getWarnOnSessionAttributeFilterFailure());
                    Integer count = (Integer) ois.readObject();
                    int n = count.intValue();
                    if (log.isDebugEnabled())
                        log.debug("Loading " + n + " persisted sessions");
                    for (int i = 0; i < n; i++) {
                        StandardSession session = getNewSession();
                        session.readObjectData(ois);
                        Principal principal = (Principal) ois.readObject();
                        if (principal != null)
                        	session.setPrincipal(principal);
                        session.setManager(this);
                        sessions.put(session.getIdInternal(), session);
                        session.activate();
                        if (!session.isValidInternal()) {
                            // If session is already invalid,
                            // expire session to prevent memory leak.
                            session.setValid(true);
                            session.expire();
                        }
                        sessionCounter++;
                    }
                } finally {
                	if (ois != null)
                		ois.close();
                    // Delete the persistent storage file
                    if (file.exists()) {
                        file.delete();
                    }
                }
            }
        } catch (FileNotFoundException e) {
            if (log.isDebugEnabled()) {
                log.debug("No persisted data file found");
            }
            return;
        } finally {
        	if (bis != null)
        		bis.close();
        	if (fis != null)
        		fis.close();
        }

        if (log.isDebugEnabled()) {
            log.debug("Finish: Loading persisted sessions");
        }
    }

    /**
     * Save any currently active sessions in the appropriate persistence
     * mechanism, if any.  If persistence is not supported, this method
     * returns without doing anything.
     *
     * @exception IOException if an input/output error occurs
     */
    protected void doUnload() throws IOException {

        if (log.isDebugEnabled())
            log.debug(sm.getString("standardManager.unloading.debug"));

        if (sessions.isEmpty()) {
            log.debug(sm.getString("standardManager.unloading.nosessions"));
            return; // nothing to do
        }

        // Open an output stream to the specified pathname, if any
        File file = file();
        if (file == null) {
            return;
        }
        if (log.isDebugEnabled()) {
            log.debug(sm.getString("standardManager.unloading", pathname));
        }

        // Keep a note of sessions that are expired
        ArrayList<StandardSession> list = new ArrayList<StandardSession>();

        FileOutputStream fos =null;
        BufferedOutputStream bos = null;
        ObjectOutputStream oos = null;
        try {
        	fos = new FileOutputStream(file.getAbsolutePath());
            bos = new BufferedOutputStream(fos);
            oos = new ObjectOutputStream(bos) ;

            synchronized (sessions) {
                if (log.isDebugEnabled()) {
                    log.debug("Unloading " + sessions.size() + " sessions");
                }
                // Write the number of active sessions, followed by the details
                oos.writeObject(Integer.valueOf(sessions.size()));
                Iterator<Session> elements = sessions.values().iterator();
                while (elements.hasNext()) {
                    StandardSession session =
                        (StandardSession) elements.next();
                    list.add(session);
                    session.passivate();
                	Object att = session.getAttribute("javax.zkoss.zk.ui.Session");
                	if (att != null)
                	{
                        try {
                        	ObjectOutputStream os = new ObjectOutputStream(new DummyOutputStream());
                        	os.writeObject(att);
                        	os.close();
                        } 
                        catch (NotSerializableException e)
                        {
                        	getContext().getLogger().warn("Errro serializing ZK session", e);
                        	session.removeAttribute("javax.zkoss.zk.ui.Session");
                        }
                	}
                   	session.writeObjectData(oos);
                    oos.writeObject(session.getPrincipal());
                }
            }
        } finally {
        	if (oos != null) oos.close();
        	if (bos != null) bos.close();
        	if (fos != null) fos.close();
        }

        // Expire all the sessions we just wrote
        if (log.isDebugEnabled()) {
            log.debug("Expiring " + list.size() + " persisted sessions");
        }
        Iterator<StandardSession> expires = list.iterator();
        while (expires.hasNext()) {
            StandardSession session = expires.next();
            try {
                session.expire(false);
            } catch (Throwable t) {
                ExceptionUtils.handleThrowable(t);
            } finally {
                session.recycle();
            }
        }

        if (log.isDebugEnabled()) {
            log.debug("Unloading complete");
        }
    }



}
