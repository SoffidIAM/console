package es.caib.seycon.ng.bpm.ui.user;

import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbpm.context.exe.VariableInstance;
import org.jbpm.graph.exe.ProcessInstance;

import es.caib.bpm.process.UserProcessData;
import es.caib.seycon.ng.ServiceLocator;
import es.caib.seycon.ng.comu.UsuariWFProcess;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.servei.UsuariService;


public class UserSEUProcessData extends UserProcessData {

	private Log log = LogFactory.getLog(this.getClass());

	public UserSEUProcessData() {
		super();
	}

	@Override
	public void updateProcessInstance(ProcessInstance pi) throws Exception {
		try { // cridat onPreUpdate PER A TOT ProcessInstance
				// Marquem com a finalitzat el procés

			UsuariService usuariService = ServiceLocator.instance().getUsuariService();

			if (pi != null) {
				// Cerquem si existeix aquest procés a la bbdd
				Collection<UsuariWFProcess> procesosExistents = usuariService.findProcessosWFUsuariByIdProces(pi.getId());
				if (procesosExistents != null && procesosExistents.size() > 0) {
					// Si el procés està finalitzat: actualitzem els existents
					if (pi.hasEnded()) {
						// Ja existeix: els marquem tots (en principi només existirà 1)
						for (UsuariWFProcess procesUsuari : procesosExistents) {
							if (!procesUsuari.getFinalitzat()) {
								// Ho marquem com a finalitzat
								procesUsuari.setFinalitzat(pi.hasEnded());
								// I ho actualitzem
								usuariService.update(procesUsuari);
							}
						}
					}
				}

				// No l'inserim... ha d'arribar una varibleinstance adeqyada

				/*				
				if (procesosExistents == null || (procesosExistents != null && procesosExistents.size() == 0)) {
					// Obtenim els camps a guardar (codiUsuari i/o nif)
					Object objCodiUsuariProces = pi.getContextInstance().getVariable("codiUsuari");
					Object objNifUsuariProces = pi.getContextInstance().getVariable("nif");

					// Creem un de nou: encara no existeix
					UsuariWFProcess nouProces = new UsuariWFProcess();
					nouProces.setIdProces(pi.getId());
					if (objCodiUsuariProces != null && objCodiUsuariProces instanceof String)
						nouProces.setCodiUsuari((String) objCodiUsuariProces);
					if (objNifUsuariProces != null && objNifUsuariProces instanceof String)
						nouProces.setNifUsuari((String) objNifUsuariProces);
					nouProces.setFinalitzat(pi.hasEnded());
					// I ho creem
					usuariService.create(nouProces);
				}
				*/

			}

		} catch (InternalErrorException e) {
			log.error(Messages.getString("UserSEUProcessData.ErrorMessage"), e); //$NON-NLS-1$
			throw new Exception(e);
		}

	}

	@Override
	public void addVariableInstance(VariableInstance variableInstance) throws Exception {
		// Cerquem el processInstance
		// Cridat en onPreInsert i onPreUpdate
		try {
			if (variableInstance != null && variableInstance.getProcessInstance() != null) {
				ProcessInstance pi = variableInstance.getProcessInstance();
				UsuariService usuariService = ServiceLocator.instance().getUsuariService();

				// Cerquem si ja existeix aquest procés a la bbdd (UPDATE)
				Collection<UsuariWFProcess> procesosExistents = usuariService.findProcessosWFUsuariByIdProces(pi.getId());
				if (procesosExistents != null && procesosExistents.size() > 0) {
					for (UsuariWFProcess procesUsuari : procesosExistents) {
						if ("nif".equals(variableInstance.getName())) { //$NON-NLS-1$
							procesUsuari.setNifUsuari((String) variableInstance.getValue());
							// I ho actualitzem
							usuariService.update(procesUsuari);
						} else if ("codiUsuari".equals(variableInstance.getName())) { //$NON-NLS-1$
							procesUsuari.setCodiUsuari((String) variableInstance.getValue());
							// I ho actualitzem
							usuariService.update(procesUsuari);
						}
					}
				} else {
					// Hem de crear una nova instància
					// Creem un de nou: encara no existeix
					UsuariWFProcess nouProces = new UsuariWFProcess();
					nouProces.setIdProces(pi.getId());
					if ("nif".equals(variableInstance.getName())) { //$NON-NLS-1$
						nouProces.setNifUsuari((String) variableInstance.getValue());
					} else if ("codiUsuari".equals(variableInstance.getName())) { //$NON-NLS-1$
						nouProces.setCodiUsuari((String) variableInstance.getValue());
					}
					// Ho marquem com a finalitzat (si cal..)
					nouProces.setFinalitzat(pi.hasEnded());
					// I ho creem
					usuariService.create(nouProces);

				}
			}
		} catch (InternalErrorException e) {
			log.error(Messages.getString("UserSEUProcessData.ErrorMessage"), e); //$NON-NLS-1$
			throw new Exception(e);
		}
	}

	@Override
	public void deleteProcessInstance(ProcessInstance pi) throws Exception {
		// Esborrem tota l'informació d'aquest procés
		try { // cridat onPreUpdate PER A TOT ProcessInstance

			UsuariService usuariService = ServiceLocator.instance().getUsuariService();

			if (pi != null) {
				// Cerquem les dades d'aquest procés
				Collection<UsuariWFProcess> procesosExistents = usuariService.findProcessosWFUsuariByIdProces(pi.getId());
				if (procesosExistents != null) {
					// Si el procés està finalitzat: actualitzem els existents
					for (UsuariWFProcess procesUsuari : procesosExistents) {
						// l'esborrem
						usuariService.delete(procesUsuari);
					}
				}
			}

		} catch (InternalErrorException e) {
			log.error(Messages.getString("UserSEUProcessData.ErrorMessage"), e); //$NON-NLS-1$
			throw new Exception(e);
		}

	}

}
