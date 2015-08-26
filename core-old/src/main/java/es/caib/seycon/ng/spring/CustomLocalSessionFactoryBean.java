package es.caib.seycon.ng.spring;

import java.io.IOException;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.mortbay.log.Log;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;

public class CustomLocalSessionFactoryBean extends LocalSessionFactoryBean implements ApplicationContextAware
{
	ApplicationContext ctx;

	@Override
	protected SessionFactory newSessionFactory (Configuration config)
					throws HibernateException
	{
		// TODO Auto-generated method stub
		return new CustomSessionFactory( super.newSessionFactory(config) );
	}

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException
	{
		ctx = applicationContext;
	}

	@Override
	protected void postProcessConfiguration(Configuration config)
			throws HibernateException
	{
		Map beans = ctx.getBeansOfType(AddonHibernateBean.class);
		
		config.setProperty("hibernate.FlushMode", "commit"); //$NON-NLS-1$ //$NON-NLS-2$
		
		for (Object name: beans.keySet())
		{
			AddonHibernateBean ahb = (AddonHibernateBean) ctx.getBean(name.toString());
			try
			{
				ahb.reconfigure(config);
			}
			catch (IOException e)
			{
				Log.warn(String.format(Messages.getString("CustomLocalSessionFactoryBean.ErrorConfigurinHibernate"), //$NON-NLS-1$
								name.toString()), e);
			}
		}
	}

}
