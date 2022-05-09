/**
 * 
 */
/**
 * 
 */
package com.soffid.iam.service;

import com.soffid.iam.api.Account;
import com.soffid.iam.api.User;
import java.security.cert.X509Certificate;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author bubu
 *
 */
public class CertificateValidationServiceImpl extends CertificateValidationServiceBase implements ApplicationContextAware
{

	private ApplicationContext applicationContext;
	private List<CertificateValidationService> modules = null;

	/* (non-Javadoc)
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 */
	public void setApplicationContext (ApplicationContext applicationContext)
					throws BeansException
	{
		this.applicationContext = applicationContext;
	}


	public List<CertificateValidationService> getModules()
	{
		if (modules == null)
		{
			LinkedList<CertificateValidationService> m = new LinkedList<CertificateValidationService>();
			for (String name: applicationContext.getBeanNamesForType(CertificateValidationService.class))
			{
				if (!name.equals("certificateValidationService-v2"))
					m.add ((CertificateValidationService) applicationContext.getBean(name));
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
		for (CertificateValidationService module: getModules())
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
		for (CertificateValidationService module: getModules())
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
    protected User handleGetCertificateUser(List<X509Certificate> certs) throws Exception {
		for (CertificateValidationService module: getModules())
		{
			if (module.validateCertificate(certs))
				return module.getCertificateUser(certs);
		}
		for (CertificateValidationService module : getModules()) {
            User user = module.getCertificateUser(certs);
            if (user != null) return user;
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
		for (CertificateValidationService module: getModules())
		{
			if (module.validateCertificate(certs))
				return module.getCertificateAccount(certs);
		}
		for (CertificateValidationService module: getModules())
		{
			Account account = module.getCertificateAccount(certs);
			if (account != null)
				return account;
		}
		return null;
	}

}
