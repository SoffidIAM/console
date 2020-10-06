package es.caib.bpm.attachment;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.RemoveException;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;

import com.soffid.iam.doc.exception.DocumentBeanException;

import es.caib.bpm.exception.BPMException;
import es.caib.bpm.servei.ejb.BpmEngine;
import es.caib.bpm.toolkit.EJBContainer;
import es.caib.bpm.vo.TaskInstance;
import es.caib.seycon.ng.exception.InternalErrorException;

public class TaskAttachmentManager extends com.soffid.iam.web.bpm.attachment.TaskAttachmentManager {

    public TaskAttachmentManager(TaskInstance task) {
        super(com.soffid.iam.bpm.api.TaskInstance.toTaskInstance(task));
    }
}
