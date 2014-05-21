package es.caib.seycon.ng.legacy;

import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Clase para gestión de locks
 */
public class LockoutTracker {
        
        private static Hashtable<Object,LockoutTracker> locks = new Hashtable<Object, LockoutTracker>();              
        private static Log log = LogFactory.getLog(SesionEJB.class);
        private boolean locked;
        private int  failures;
        private long lastFailure;
        private long enableTime;
    	private long firstFailure;
    	private Calendar passwordTimeStamp;
        
        protected LockoutTracker ()
        {
                locked = false;
                failures = 0;
                lastFailure = 0;
        }
        
        
        private boolean isPasswordChanged (Calendar lastPasswordSet)
        {
        	if (passwordTimeStamp == null)
        		return lastPasswordSet != null;
        	else
        		return !passwordTimeStamp.equals(lastPasswordSet);
        }
        
        public static boolean isLocked (Object user, Calendar lastPasswordSet)
        {
        		LockoutTracker userLocks = locks.get(user);
                if ( userLocks != null)
                {
                	if (userLocks.locked && userLocks.enableTime > System.currentTimeMillis() &&
                			!userLocks.isPasswordChanged(lastPasswordSet))
                	{
               			return true;
                	}
                }
                return false;
        }
        
        public static void registerFailure (Object user, long maxFails, long lockoutPeriod,
                        Calendar lastPasswordSet)
        {
                LockoutTracker lock = locks.get(user);
                if ( lock == null)
                {
                        lock = new LockoutTracker();
                        lock.failures = 0;
                        lock.firstFailure = System.currentTimeMillis();
                        lock.lastFailure = System.currentTimeMillis();
                        lock.passwordTimeStamp = lastPasswordSet;
                        locks.put(user, lock);
                }
                if (lock.isPasswordChanged(lastPasswordSet))
                {
                	lock.passwordTimeStamp = lastPasswordSet;
                	lock.failures = 0;
                }
                lock.failures = lock.failures + 1;
                lock.lastFailure = System.currentTimeMillis();
                if (log.isDebugEnabled())
                        log.debug(String.format(Messages.getString("LockoutTracker.FailNumberForUser"), new Integer(lock.failures), user)); //$NON-NLS-1$
                if ( lock.failures > maxFails) 
                {
                        lock.locked = true;
                        lock.enableTime = lock.lastFailure + lockoutPeriod;
                        log.warn(String.format(Messages.getString("LockoutTracker.UserLocked"), user, new Date(lock.enableTime).toString())); //$NON-NLS-1$
                }
        }

        public static void registerSuccess (Object user)
        {
        		locks.remove(user);
        }
}

