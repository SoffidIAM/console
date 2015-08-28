package com.soffid.iam.bpm.business;

import com.soffid.iam.ServiceLocator;
import com.soffid.iam.api.BpmUserProcess;
import com.soffid.iam.service.UserService;

import es.caib.bpm.process.UserProcessData;
import es.caib.seycon.ng.exception.InternalErrorException;

import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbpm.context.exe.VariableInstance;
import org.jbpm.graph.exe.ProcessInstance;

public class UserSEUProcessData extends UserProcessData {

	private Log log = LogFactory.getLog(this.getClass());

	public UserSEUProcessData() {
		super();
	}

	@Override
	public void updateProcessInstance(ProcessInstance pi) throws Exception {
		try { // cridat onPreUpdate PER A TOT ProcessInstance
				// Marquem com a finalitzat el procés

			UserService usuariService = ServiceLocator.instance().getUserService();

			if (pi != null) {
				// Cerquem si existeix aquest procés a la bbdd
				Collection<BpmUserProcess> procesosExistents = usuariService.findBpmUserProcessByProcessId(pi.getId());
				if (procesosExistents != null && procesosExistents.size() > 0) {
					// Si el procés està finalitzat: actualitzem els existents
					if (pi.hasEnded()) {
						// Ja existeix: els marquem tots (en principi només existirà 1)
						for (BpmUserProcess procesUsuari : procesosExistents) {
                            if (!procesUsuari.getTerminated()) {
                                procesUsuari.setTerminated(pi.hasEnded());
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
				UserService usuariService = ServiceLocator.instance().getUserService();

				// Cerquem si ja existeix aquest procés a la bbdd (UPDATE)
				Collection<BpmUserProcess> procesosExistents = usuariService.findBpmUserProcessByProcessId(pi.getId());
				if (procesosExistents != null && procesosExistents.size() > 0) {
					for (BpmUserProcess procesUsuari : procesosExistents) {
                        if ("nif".equals(variableInstance.getName())) {
                            procesUsuari.setUserNationalId((String) variableInstance.getValue());
                            usuariService.update(procesUsuari);
                        } else if ("codiUsuari".equals(variableInstance.getName())) {
                            procesUsuari.setUserCode((String) variableInstance.getValue());
                            usuariService.update(procesUsuari);
                        }
                    }
				} else {
					// Hem de crear una nova instància
					// Creem un de nou: encara no existeix
					BpmUserProcess nouProces = new BpmUserProcess();
					nouProces.setProcessId(pi.getId());
					if ("nif".equals(variableInstance.getName())) { //$NON-NLS-1$
						nouProces.setUserNationalId((String) variableInstance.getValue());
					} else if ("codiUsuari".equals(variableInstance.getName())) { //$NON-NLS-1$
						nouProces.setUserCode((String) variableInstance.getValue());
					}
					// Ho marquem com a finalitzat (si cal..)
					nouProces.setTerminated(pi.hasEnded());
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
			UserService usuariService = ServiceLocator.instance().getUserService();

			if (pi != null) {
				// Cerquem les dades d'aquest procés
				Collection<BpmUserProcess> procesosExistents = usuariService.findBpmUserProcessByProcessId(pi.getId());
				if (procesosExistents != null) {
					// Si el procés està finalitzat: actualitzem els existents
					for (BpmUserProcess procesUsuari : procesosExistents) {
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
