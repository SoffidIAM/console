package es.caib.bpm.entity;

import java.lang.reflect.Constructor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.event.PreDeleteEvent;
import org.hibernate.event.PreDeleteEventListener;
import org.hibernate.event.PreInsertEvent;
import org.hibernate.event.PreInsertEventListener;
import org.hibernate.event.PreUpdateEvent;
import org.hibernate.event.PreUpdateEventListener;
import org.jbpm.context.exe.VariableInstance;
import org.jbpm.graph.exe.ProcessInstance;

import es.caib.bpm.business.UserSEUProcessData;
import es.caib.bpm.process.UserProcessData;

/**
 * Fem un seguiment dels processos d'usuari (es guarda en una taula) mitjançant
 * una implementació de la classe abstracta UserProcessData
 * 
 * - Alta ProcessInstance (PI): [PRE-INSERT] No fem res (no podem mirar les
 * variables)
 * 
 * - Alta - mod VariableInstance (VI): {codiUsuari, nif} [PRE-INSERT,
 * PRE-UPDATE] Cerquem el seu PI i si no existeix es crea filera Es guarda
 * aquesta VI (actualitzant existent if exists)
 * 
 * - Update ProcessInstance (PI): [PRE-UPDATE] Si és finalittat, es marca com a
 * tal
 * 
 * - Baixa de ProcesSInstance: [PRE-DELETE] S'esborren les entrades
 * 
 * Alejandro Usero Ruiz, 15-10-2012
 * 
 * @author u88683
 * 
 */
public class UserProcessEventListener implements PreInsertEventListener, PreUpdateEventListener, PreDeleteEventListener {

	private static boolean notified = false;
	private static final long serialVersionUID = 1L;

	// Instància per guardar informació dels processos d'usuari
	protected UserProcessData userProcessDataClass = null;

	private transient Log log = LogFactory.getLog(getClass());
	
	public UserProcessEventListener() {
		try {
			log.info(Messages.getString("UserProcessEventListener.IniciantUserProcessEventListenerMessage")); //$NON-NLS-1$

			userProcessDataClass = new UserSEUProcessData();
		} catch (Throwable th) {
			if (!notified) {
				notified = true;
				userProcessDataClass = null;
			}
		}

	}

	public boolean onPreInsert(PreInsertEvent event) {
		// Si no tenim classe executora: no fem res
		if (userProcessDataClass == null)
			return false;

		try {
			if (event.getEntity() instanceof ProcessInstance) {
				// S'ignora
				log.info(Messages.getString("UserProcessEventListener.ProcessInstanceMessage")); //$NON-NLS-1$
			} else if (event.getEntity() instanceof VariableInstance) {
				VariableInstance vi = (VariableInstance) event.getEntity();

				// Mirem si hem de guardar les dades de la variable
				// Només iniciem el procés si la variable es "codiUsuari" o "nif"
				if ("codiUsuari".equals(vi.getName()) || "nif".equals(vi.getName())) { //$NON-NLS-1$ //$NON-NLS-2$
					userProcessDataClass.addVariableInstance(vi);
				}
			}

		} catch (Exception e) {
			log.error(String.format(Messages.getString("UserProcessEventListener.onPreinsertError"),  //$NON-NLS-1$
					e.getMessage()), e); 
		}

		return false;
	}

	public boolean onPreUpdate(PreUpdateEvent event) {
		// Si no tenim classe executora: no fem res
		if (userProcessDataClass == null)
			return false;

		log.info(String.format(Messages.getString("UserProcessEventListener.onPreUpdateReceived"), event.getEntity())); //$NON-NLS-1$

		try {
			if (event.getEntity() instanceof ProcessInstance) {
				// Mirem si el procés ha estàt finalizat (o cancel·lat)
				// i si es així marquem com a finalitzat el procés
				ProcessInstance pi = (ProcessInstance) event.getEntity();
				userProcessDataClass.updateProcessInstance(pi);
			} else if (event.getEntity() instanceof VariableInstance) {
				VariableInstance vi = (VariableInstance) event.getEntity();
				// Mirem si hem de guardar les dades de la variable
				// Només iniciem el procés si la variable es "codiUsuari" o "nif"
				if ("codiUsuari".equals(vi.getName()) || "nif".equals(vi.getName())) { //$NON-NLS-1$ //$NON-NLS-2$
					userProcessDataClass.addVariableInstance(vi);
				}
			}

		} catch (Exception e) {
			log.error(String.format(Messages.getString("UserProcessEventListener.onPreUpdateError"), //$NON-NLS-1$
					e.getMessage()), e); 
		}

		return false;
	}

	public boolean onPreDelete(PreDeleteEvent event) {
		// Si no tenim classe executora: no fem res
		if (userProcessDataClass == null)
			return false;

		try {
			if (event.getEntity() instanceof ProcessInstance) {
				// Mirem si el procés ha estat esborrat.. per donar-lo de baixa
				ProcessInstance pi = (ProcessInstance) event.getEntity();
				userProcessDataClass.deleteProcessInstance(pi);
			}
		} catch (Exception e) {
			log.error(String.format(Messages.getString("UserProcessEventListener.onPreDeleteError"), //$NON-NLS-1$
					e.getMessage()), e); 
		}
		return false;
	}
}