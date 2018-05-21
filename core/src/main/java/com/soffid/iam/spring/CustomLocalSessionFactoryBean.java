package com.soffid.iam.spring;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.mapping.PersistentClass;
import org.hibernate.mapping.Property;
import org.hibernate.mapping.Table;
import org.mortbay.log.Log;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;

import com.soffid.iam.model.AccountEntity;
import com.soffid.iam.model.AccountEntityImpl;
import com.soffid.iam.model.GroupEntityImpl;
import com.soffid.iam.model.RoleAccountEntityImpl;
import com.soffid.iam.model.RoleEntityImpl;
import com.soffid.iam.model.RoleGroupEntity;
import com.soffid.iam.model.RoleGroupEntityImpl;
import com.soffid.iam.model.UserAccountEntityImpl;
import com.soffid.iam.model.UserEntity;
import com.soffid.iam.model.UserEntityImpl;
import com.soffid.iam.model.UserGroupEntityImpl;

public class CustomLocalSessionFactoryBean extends LocalSessionFactoryBean implements ApplicationContextAware
{
	ApplicationContext ctx;

	@Override
	protected SessionFactory newSessionFactory (Configuration config)
					throws HibernateException
	{
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
		String enableCache = System.getProperty("soffid.cache.enable");
		if ( enableCache != null && ! "false".equals(enableCache))
		{
			config.setProperty("hibernate.cache.provider_class", "com.soffid.iam.spring.JCSCacheProvider"); //$NON-NLS-1$ //$NON-NLS-2$
			config.setProperty("hibernate.cache.use_second_level_cache", "true");
			config.setProperty("hibernate.cache.region_prefix", "hibernate");
		}

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

		if ( "true".equals(enableCache))
		{
			config.setCacheConcurrencyStrategy(UserEntityImpl.class.getName(), "read-write");
			config.setCollectionCacheConcurrencyStrategy(UserEntityImpl.class.getName()+".accounts", "read-write");
			config.setCollectionCacheConcurrencyStrategy(UserEntityImpl.class.getName()+".secondaryGroups", "read-write");

			config.setCacheConcurrencyStrategy(UserGroupEntityImpl.class.getName(), "read-write");

			config.setCacheConcurrencyStrategy(GroupEntityImpl.class.getName(), "read-write");
			config.setCollectionCacheConcurrencyStrategy(GroupEntityImpl.class.getName()+".children", "read-write");
			config.setCollectionCacheConcurrencyStrategy(GroupEntityImpl.class.getName()+".primaryGroupUsers", "read-write");
			config.setCollectionCacheConcurrencyStrategy(GroupEntityImpl.class.getName()+".secondaryGroupUsers", "read-write");
			config.setCollectionCacheConcurrencyStrategy(GroupEntityImpl.class.getName()+".grantedRoles", "read-write");

			config.setCacheConcurrencyStrategy(RoleGroupEntityImpl.class.getName(), "read-write");

			config.setCacheConcurrencyStrategy(UserAccountEntityImpl.class.getName(), "read-write");

			config.setCacheConcurrencyStrategy(AccountEntityImpl.class.getName(), "read-write");
			config.setCollectionCacheConcurrencyStrategy(AccountEntityImpl.class.getName()+".roles", "read-write");
			config.setCollectionCacheConcurrencyStrategy(AccountEntityImpl.class.getName()+".users", "read-write");

			config.setCacheConcurrencyStrategy(RoleAccountEntityImpl.class.getName(), "read-write");

			config.setCacheConcurrencyStrategy(RoleEntityImpl.class.getName(), "read-write");
			config.setCollectionCacheConcurrencyStrategy(RoleEntityImpl.class.getName()+".containerGroups", "read-write");
			config.setCollectionCacheConcurrencyStrategy(RoleEntityImpl.class.getName()+".containerRoles", "read-write");
			config.setCollectionCacheConcurrencyStrategy(RoleEntityImpl.class.getName()+".containedRoles", "read-write");
			
			config.setCacheConcurrencyStrategy(RoleAccountEntityImpl.class.getName(), "read-write");

		}
		else if ( "full".equals(enableCache))
		{
			for ( java.util.Iterator it = config.getClassMappings(); it.hasNext();)
			{
				
				PersistentClass pc = (PersistentClass) it.next();
				config.setCacheConcurrencyStrategy( pc.getClassName(), "read-write");
				for ( Iterator it2 = pc.getPropertyIterator(); it2.hasNext();)
				{
					Property p = (Property) it2.next();
					if (! p.getValue().isSimpleValue())
						config.setCollectionCacheConcurrencyStrategy(pc.getClassName()+"."+p.getName(), "read-write");
				}
			}		
		}
	}

}
