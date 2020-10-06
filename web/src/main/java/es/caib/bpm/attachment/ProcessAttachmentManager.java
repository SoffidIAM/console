package es.caib.bpm.attachment;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import es.caib.bpm.exception.BPMException;
import es.caib.bpm.servei.ejb.BpmEngine;
import es.caib.bpm.vo.ProcessInstance;
import es.caib.seycon.ng.EJBLocator;
import es.caib.seycon.ng.exception.InternalErrorException;

public class ProcessAttachmentManager extends com.soffid.iam.web.bpm.attachment.ProcessAttachmentManager {
    public ProcessAttachmentManager(ProcessInstance process) {
        super( com.soffid.iam.bpm.api.ProcessInstance.toProcessInstance(process));
    }

    public ProcessAttachmentManager(long processId) throws CreateException, NamingException, BPMException, InternalErrorException {
        super(null);
        BpmEngine engine = EJBLocator.getBpmEngine();
        
        super.process=com.soffid.iam.bpm.api.ProcessInstance.toProcessInstance (engine.getProcess(processId) );
            
    }

}
