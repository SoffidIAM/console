package es.caib.loginModule.auth;

import java.security.Principal;
import java.security.acl.Group;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.LoginException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.security.SimpleGroup;
import org.jboss.security.SimplePrincipal;

import es.caib.seycon.ng.ServiceLocator;
import es.caib.seycon.ng.servei.AutoritzacioService;

public class SEUAuthorizationModule implements
		javax.security.auth.spi.LoginModule {

	protected Subject subject;
	protected CallbackHandler callbackHandler;
	protected Map sharedState;
	protected Map options;
	private static Log log = LogFactory.getLog(SeyconEJBLoginModule.class);

	String autoritzacioServiceJNDI = null;
	
	private String nameUnauthenticatedIdentity;

	public void initialize(Subject subject, CallbackHandler callbackHandler,
			Map sharedState, Map options) {
		this.subject = subject;
		this.callbackHandler = callbackHandler;
		this.sharedState = sharedState;
		this.options = options;

		//log.trace("initialize");

		// Obtenim el JNDI del servei per obtindre les autoritzacions
		autoritzacioServiceJNDI = (String) options
				.get("autoritzacioServiceJNDI"); //$NON-NLS-1$

		// Usuari a q tindrà com a autorització "nobody"
		nameUnauthenticatedIdentity = (String) options
				.get("unauthenticatedIdentity"); //$NON-NLS-1$
		

	}

	public boolean login() throws LoginException {
		//log.trace("login");
		return true;
	}

	public boolean commit() throws LoginException {
		try {
			//log.trace("commit");
			
			Group autoritzacionsUsuari = new SimpleGroup("Roles"); //$NON-NLS-1$
			
			//boolean mustChangePassword = false;
			
			// Obtenim 3 principals: Principal 'normal'
			// i Principals de tipus Group que hem
			// de reemplaçar per les autoritzacions de l'usuari
			Set principals = subject.getPrincipals();
			Principal principal = null;
			String codiUsuari = null;

			if (principals != null) {
				for (Iterator it = principals.iterator(); it.hasNext();) {
					Principal p = (Principal) it.next();
					//log.info("##principal "+p.toString());
					if (p instanceof Group) {
						//Nomes elminem els rols
						Group g = (Group) p;
						if ("Roles".equals(p.getName())) { //$NON-NLS-1$
							it.remove(); // Li llevem SEMPRE
							if (g.isMember(new SimplePrincipal("SEYCON_CHANGE_PASSWORD"))) { //$NON-NLS-1$
								if (principal!=null &&  principal.getName()!=null) {
									log.info (String.format(Messages.getString("SEUAuthorizationModule.mustChangePassword"), principal.getName()));   //$NON-NLS-1$
								}
								//return true; //SORTIM i no fem res (!!)
								//mustChangePassword = true; //No li afegim rols "extra" tothom ni bpm_internal
								//it.remove();
								// Afegim el nou
								autoritzacionsUsuari.addMember(new SimplePrincipal("SEYCON_CHANGE_PASSWORD")); //$NON-NLS-1$
							}
							/*else {
								it.remove();
							}*/
						}
					} else if (p instanceof Principal) {
						principal = p;
					}
				}
			}

			// Obtenim les autoritzacions
			String autoritzacions[] = null;
			boolean isNobody = false;
			
			// Si es l'usuari "nobody" (paràmetre nameUnauthenticatedIdentity)
			// li posem com a autorització "nobody"
			
			codiUsuari = principal!=null &&  principal.getName()!=null? principal.getName(): null; 
			
			// Obtenim les autoritzacions
			//if (codiUsuari!=null && !codiUsuari.equals(nameUnauthenticatedIdentity)) {
			AutoritzacioService svc = ServiceLocator.instance().getAutoritzacioService();
	
			autoritzacions = svc.getUserAuthorizationsString(codiUsuari);
			/*} else {
				isNobody = true;
			}*/

			

			if (autoritzacions != null) {
				for (int i = 0; i < autoritzacions.length; i++) {
					String roleName = autoritzacions[i];
					autoritzacionsUsuari.addMember(new SimplePrincipal(roleName));
				}
			}
			
			// A l'usuari nobody li posem el rol BPM_INTERNAL i a la
			// resta d'usuaris, el rol tothom
		//if (!mustChangePassword) {
				if (isNobody) {
					//autoritzacionsUsuari.addMember(new SimplePrincipal("BPM_INTERNAL"));
				} else {
					autoritzacionsUsuari.addMember(new SimplePrincipal("tothom")); //$NON-NLS-1$
				}
		//}
			// Afegim les autoritzacions
			principals.add(autoritzacionsUsuari);
				
				
			/*log.info("##Autoritzacions FINALS de l'usuari  '"+codiUsuari);
			for (Iterator it = principals.iterator(); it.hasNext();) {
				Principal p = (Principal) it.next();
				log.info("##principal "+p.toString());
			}*/
		} catch (Exception e) {
			log.error(Messages.getString("SEUAuthorizationModule.Error"), e); //$NON-NLS-1$
			throw new LoginException(Messages.getString("SEUAuthorizationModule.Error")); //$NON-NLS-1$
		}

		return true;
	}

	public boolean abort() throws LoginException {
		//log.trace("abort");
		return true;
	}

	public boolean logout() throws LoginException {
		//log.trace("logout");
		return true;
	}

}
