package es.caib.bpm.nas.hibernate;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import es.caib.bpm.nas.comm.FTPStrategy;

/**
 * Singleton para obtener apertura y cierre de sesiones hibernate.
 * 
 * @author Pablo Hern�n Gim�nez.
 */
public class HibernateUtil
{
	public static final SessionFactory sessionFactory;
	
	static
	{
		
		
		try
		{
			//	 Create the SessionFactory from hibernate.cfg.xml
			Configuration configuration = new Configuration();
			Configuration defaultConfiguration = configuration.configure("document-manager.hibernate.cfg.xml");
			sessionFactory = defaultConfiguration.buildSessionFactory();
			
			Logger.getLogger(HibernateUtil.class).info("Se creo la conexion");
		}
		catch (Throwable ex)
		{
			//	 Make sure you log the exception, as it might be swallowed
			System.err.println("Initial SessionFactory creation failed." + ex);
			
			Logger.getLogger(HibernateUtil.class).error("No se creo la conexion: "+ex.getMessage(),ex);
			throw new ExceptionInInitializerError(ex);
			
		}
	}
	
	public static final ThreadLocal session = new ThreadLocal();
	
	public static Session currentSession() throws HibernateException
	{
		Session s = (Session) session.get();
		//	 Open a new Session, if this thread has none yet
		
		if (s == null)
		{
			s = sessionFactory.openSession();
			//	 Store it in the ThreadLocal variable
			session.set(s);
		}
		
		return s;
	}
	
	public static void closeSession() throws HibernateException
	{
		Session s = (Session) session.get();
		if (s != null)
		{
			s.close();
		}
		
		session.set(null);
	}
}
