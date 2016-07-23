package es.caib.bpm.attachment;

import java.io.IOException;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.ejb.CreateException;
import javax.ejb.EJBContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import javax.servlet.http.HttpSession;

import org.jbpm.context.exe.ContextInstance;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zul.Messagebox;

import es.caib.bpm.exception.BPMException;
import es.caib.bpm.toolkit.EJBContainer;
import es.caib.bpm.vo.Deserializer;
import es.caib.bpm.vo.ProcessInstance;
import es.caib.bpm.vo.TaskInstance;

public class AttachmentManager extends AbstractAttachmentManager {
    private ContextInstance ctx;

    public AttachmentManager(ContextInstance ctx) {
        super();
        this.ctx = ctx;
    }


    protected Set getVariables() {
    	if(ctx.getVariables()!=null)
    		return ctx.getVariables().keySet();
    	else
    		return new HashSet();
    }


    protected void putVariable(String key, Object value) {
        ctx.setVariable(key, value);
    }


    protected void removeVariable(String key) {
        ctx.deleteVariable(key);
    }


    protected Object getVariable(String key) {
        return Deserializer.deserialize(ctx.getVariable(key));
    }


}
