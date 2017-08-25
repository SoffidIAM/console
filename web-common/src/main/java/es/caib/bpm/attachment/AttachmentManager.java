package es.caib.bpm.attachment;

import java.util.HashSet;
import java.util.Set;

import org.jbpm.context.exe.ContextInstance;

import es.caib.bpm.vo.Deserializer;

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
