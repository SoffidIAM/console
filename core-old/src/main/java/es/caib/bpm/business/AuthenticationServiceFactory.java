package es.caib.bpm.business;

import java.lang.reflect.Constructor;
import java.security.cert.X509Certificate;

import org.jbpm.security.AuthenticationService;
import org.jbpm.svc.Service;
import org.jbpm.svc.ServiceFactory;

import es.caib.bpm.toolkit.EJBContainer;
import es.caib.bpm.vo.ConfigParameterVO;



public class AuthenticationServiceFactory implements ServiceFactory {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7437201257360080496L;

	public void close() {

	}

	/**
	 * Configura el servicio de autenticación, a partir de una propiedad de sistema.
	 * Por defecto seycon.
	 */
	public Service openService() {
		try{
			
	        ConfigParameterVO param = null;
	        String authenticationServiceClassName="es.caib.bpm.security.SeyconAuthenticationService"; //$NON-NLS-1$
	        try {
				param = EJBContainer.getBPMConfigBean().findFirstByAppKey("BPM","es.caib.bpm.authenticationServiceDelegate"); //$NON-NLS-1$ //$NON-NLS-2$
			} catch (Exception e) {}
	        if(param!=null) authenticationServiceClassName=param.getValue();	        

			Class authenticationServiceClass=this.getClass().getClassLoader().loadClass(authenticationServiceClassName);
			Constructor constructor=authenticationServiceClass.getConstructor(
					new Class[] {
							X509Certificate[].class,
							boolean.class 
					}
				);
			AuthenticationService authenticationService=(AuthenticationService)constructor.newInstance((Object[])null);
			return authenticationService;
		}catch(Exception e){
			return null;
		}
	}

}
