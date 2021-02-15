package com.soffid.iam.web.plugin;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.UploadEvent;

import com.soffid.iam.exception.DuplicatedClassException;
import com.soffid.iam.utils.Security;
import com.soffid.iam.web.component.FrameHandler;
import com.soffid.iam.web.popup.FileUpload2;

import es.caib.seycon.ng.EJBLocator;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.web.Messages;
import es.caib.zkib.zkiblaf.Missatgebox;

public class PluginHandler extends FrameHandler {
	private boolean isMaster;
	private boolean canUpdatePlugins;
	private boolean canQueryPlugins;
	private boolean canRestartConsole;

	public PluginHandler() throws InternalErrorException {
		com.soffid.iam.api.Tenant masterTenant = com.soffid.iam.ServiceLocator.instance().getTenantService().getMasterTenant();

		isMaster = com.soffid.iam.utils.Security.getCurrentTenantName().equals ( masterTenant.getName() );
		canUpdatePlugins = isMaster && Security.isUserInRole("plugins:update");
		canQueryPlugins = isMaster && Security.isUserInRole("plugins:query");;
		canRestartConsole = isMaster && Security.isUserInRole("console:restart");
		try
		{
			es.caib.zkib.zkiblaf.Application.setTitle(org.zkoss.util.resource
				.Labels.getLabel("seu.plugins"));
		}
		catch (Exception ex){}
	}

	@Override
	public void setPage(Page p) {
		super.setPage(p);
		getNamespace().setVariable("canUpdatePlugins", canUpdatePlugins, true);
		getNamespace().setVariable("canQueryPlugins", canQueryPlugins, true);
		getNamespace().setVariable("canRestartConsole", canRestartConsole, true);
	}

	
	
	public void removeValue(Event ev) {
		es.caib.zkib.binder.BindContext bindCtx = es.caib.zkib.datasource.XPathUtils.getComponentContext(ev.getTarget());
		es.caib.zkib.datasource.XPathUtils.removePath(bindCtx.getDataSource(), bindCtx.getXPath());
	}
	


	public void onChangeForm(Event ev) {
		
	}
	
	public void addNew() throws InternalErrorException, DuplicatedClassException, NamingException, CreateException, InterruptedException, IOException {
        FileUpload2.get((event) -> {
        	Media dataSubida = ((UploadEvent)event).getMedia();
        	if (dataSubida == null) return; //Per si l'usuari pitja en Cancelar
        	if (!dataSubida.isBinary()) {
        		throw new UiException(Messages.getString("PluginsUI.NotBinaryFileError")); //$NON-NLS-1$
        	}
        	byte data[];
        	if (dataSubida.inMemory()) {
        		data = dataSubida.getByteData();
        	} else {
        		ByteArrayOutputStream os = new ByteArrayOutputStream();
        		InputStream is = dataSubida.getStreamData();
        		byte b[] = new byte[2048];
        		int read = is.read(b);
        		while (read > 0) {
        			os.write(b, 0, read);
        			read = is.read(b);
        		}
        		is.close();
        		os.close();
        		data = os.toByteArray();
        	}
        	EJBLocator.getServerPluginService().deployPlugin(data);
        	getModel().refresh();
        });
	}
	
	public void showDetails() {
		getCard().setSclass ( "card is-flipped" );
	}

    /** Method that implements the functionality to force restart server.
     * 
     */
    public void restartConsole()
    {
    	Missatgebox.confirmaYES_NO(
			Messages.getString("PluginsUI.ResetServerQuestion"), //$NON-NLS-1$
    		new EventListener() {
				public void onEvent(Event event) throws Exception
    			{
    				if (event.getName() == "onYes") //$NON-NLS-1$
    				{
    					createFileToResetConsole();
    					showRefreshPopup();
    				}
    			}
    		}
    	);
	}
    
    /** Method that implements the functionality to create a file in server
     * to force the restart server process.
     * @throws IOException
     */
	private void createFileToResetConsole() throws IOException
	{
		String fileName = "reset console.txt"; //$NON-NLS-1$
		String fileContent = "File to check reset console"; //$NON-NLS-1$
		File file = new File(fileName);
		FileWriter wr = null;
		String home = System.getProperty ("catalina.home"); //$NON-NLS-1$
		File addonFolder = new File(new File(home, "soffid"), "addons"); //$NON-NLS-1$ //$NON-NLS-2$

		try
		{
			addonFolder.mkdirs();
			file = new File(addonFolder, fileName);
			wr = new FileWriter(file, true);
			wr.write(fileContent);
		}
		finally
		{
			if (wr != null) wr.close();
		}
    }
	
	private void showRefreshPopup() throws InterruptedException
	{
		org.zkoss.zul.Window p = (org.zkoss.zul.Window) getFellow("popupRefreshPage"); //$NON-NLS-1$
		p.doHighlighted();
		response("reload", new org.zkoss.zk.au.out.AuScript(this,  "play(); setTimeout(()=>{document.location.reload()}, 10000);"));
	}
}
