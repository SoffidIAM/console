/**
 * 
 */
package com.soffid.iam.service;

import java.security.cert.X509Certificate;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import es.caib.seycon.ng.comu.Account;
import es.caib.seycon.ng.comu.Usuari;

/**
 * @author bubu
 *
 */
public class CertificateValidationServiceImpl extends CertificateValidationServiceBase implements ApplicationContextAware
{

	private ApplicationContext applicationContext;
	private List<CertificateValidationModule> modules = null;

	/* (non-Javadoc)
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 */
	public void setApplicationContext (ApplicationContext applicationContext)
					throws BeansException
	{
		this.applicationContext = applicationContext;
	}


	public List<CertificateValidationModule> getModules()
	{
		if (modules == null)
		{
			LinkedList<CertificateValidationModule> m = new LinkedList<CertificateValidationModule>();
			for (String name: applicationContext.getBeanNamesForType(CertificateValidationModule.class))
			{
				m.add ((CertificateValidationModule) applicationContext.getBean(name));
			}
			modules = m;
		}
		return modules ;
	}
	/**
	 * 
	 */
	public CertificateValidationServiceImpl ()
	{
	}

	/* (non-Javadoc)
	 * @see com.soffid.iam.service.CertificateValidationServiceBase#handleGetRootCertificateList()
	 */
	@Override
	protected Collection<X509Certificate> handleGetRootCertificateList ()
					throws Exception
	{
		LinkedList<X509Certificate> roots = new LinkedList<X509Certificate>();
		for (CertificateValidationModule module: getModules())
		{
			roots.addAll(module.getRootCertificateList());
		}
		return roots;
	}

	/* (non-Javadoc)
	 * @see com.soffid.iam.service.CertificateValidationServiceBase#handleValidateCertificate(java.util.List)
	 */
	@Override
	protected boolean handleValidateCertificate (List<X509Certificate> certs)
					throws Exception
	{
		for (CertificateValidationModule module: getModules())
		{
			if (module.validateCertificate(certs))
				return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.soffid.iam.service.CertificateValidationServiceBase#handleGetCertificateUser(java.util.List)
	 */
	@Override
	protected Usuari handleGetCertificateUser (List<X509Certificate> certs)
					throws Exception
	{
		for (CertificateValidationModule module: getModules())
		{
			if (module.validateCertificate(certs))
				return module.getCertificateUser(certs);
		}
		for (CertificateValidationModule module: getModules())
		{
			Usuari user = module.getCertificateUser(certs);
			if (user != null)
				return user;
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.soffid.iam.service.CertificateValidationServiceBase#handleGetCertificateAccount(java.util.List)
	 */
	@Override
	protected Account handleGetCertificateAccount (List<X509Certificate> certs)
					throws Exception
	{
		for (CertificateValidationModule module: getModules())
		{
			if (module.validateCertificate(certs))
				return module.getCertificateAccount(certs);
		}
		for (CertificateValidationModule module: getModules())
		{
			Account account = module.getCertificateAccount(certs);
			if (account != null)
				return account;
		}
		return null;
	}

}
