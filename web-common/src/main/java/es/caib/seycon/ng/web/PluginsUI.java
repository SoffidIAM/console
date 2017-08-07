package es.caib.seycon.ng.web;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Fileupload;
import org.zkoss.zul.Label;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Tree;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.Window;

import es.caib.seycon.ng.EJBLocator;
import es.caib.seycon.ng.comu.ServerPlugin;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.component.DataModel;
import es.caib.zkib.component.DataTree;
import es.caib.zkib.datamodel.DataNode;
import es.caib.zkib.zkiblaf.Missatgebox;
import es.caib.zkib.zkiblaf.MissatgeboxDlg;

public class PluginsUI extends Window implements AfterCompose {


    DataModel getModel() {
        return (DataModel) getPage().getFellow("model"); //$NON-NLS-1$
    }

    Window getNavegador() {
        return (Window) getFellow("lista"); //$NON-NLS-1$
    }

    Toolbarbutton getDisableButton() {
        return (Toolbarbutton) getNavegador().getFellow("disableButton"); //$NON-NLS-1$
    }

    Toolbarbutton getDeleteButton() {
        return (Toolbarbutton) getNavegador().getFellow("deleteButton"); //$NON-NLS-1$
    }

    Toolbarbutton getEnableButton() {
        return (Toolbarbutton) getNavegador().getFellow("enableButton"); //$NON-NLS-1$
    }

    Toolbarbutton getUploadButton() {
        return (Toolbarbutton) getNavegador().getFellow("uploadButton"); //$NON-NLS-1$
    }

    DataTree getTree() {
        return (DataTree) getNavegador().getFellow("listbox"); //$NON-NLS-1$
    }

    public void upload() throws Exception {
        Media dataSubida = Fileupload.get();
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
        refresh();
    }

    public void disable() throws Exception {
        ServerPlugin sp = getSelectedPlugin();
        if (sp != null) {
            EJBLocator.getServerPluginService().enablePlugin(sp, false);
            refresh();
        }
    }

    public void delete() throws Exception {
        ServerPlugin sp = getSelectedPlugin();
        if (sp != null) {
            MissatgeboxDlg mb = Missatgebox.confirmaYES_NO(Messages.getString("PluginsUI.DeletePluginConfirmMessage"), new EventListener() { //$NON-NLS-1$
                
                public void onEvent(Event event) throws Exception {
                    if (event.getName() == "onYes") { //$NON-NLS-1$
                        EJBLocator.getServerPluginService().deletePlugin(getSelectedPlugin());
                        refresh();
                    }
                }
            });
        }
    }

    public void refresh() throws Exception {
        getModel().refresh();
        disableButtons();
        getTree().setDataPath("/model:/"); //$NON-NLS-1$
        refreshBaseVersion();
    }

    public void enable() throws Exception {
        ServerPlugin sp = getSelectedPlugin();
        if (sp != null) {
            EJBLocator.getServerPluginService().enablePlugin(sp, true);
            refresh();
        }
    }

    private ServerPlugin getSelectedPlugin() {
        Tree lb = getTree();
        if (lb.getSelectedItem() == null)
            return null;
        else {
            DataNode node = (DataNode) lb.getSelectedItem().getValue();
            if (node.getInstance() instanceof ServerPlugin)
                return (ServerPlugin) node.getInstance();
            else
                return null;
        }

    }

    public void select() throws CreateException, NamingException {
        ServerPlugin sp = getSelectedPlugin();
        if (sp == null) {
            disableButtons();
        } else {
            getEnableButton().setVisible( ! sp.isEnabled());
            getDisableButton().setVisible( sp.isEnabled());
            getDeleteButton().setVisible( ! sp.isEnabled());
        }
    }

    public void disableButtons() {
        getEnableButton().setVisible(true);
        getDisableButton().setVisible(true);
        getDeleteButton().setVisible(true);
    }

    public void onRenderServerPlugin(Treeitem item) {
        //System.out.println("En render server plugin");
        DataNode data = (DataNode) item.getValue();
        if (data.getInstance() instanceof ServerPlugin) {
            ServerPlugin sp = (ServerPlugin) data.getInstance();
            String color = sp.isEnabled() ? "#000000" : "#808080"; //$NON-NLS-1$ //$NON-NLS-2$
            for (Iterator it = item.getTreerow().getChildren().iterator(); it
                    .hasNext();) {
                Component c = (Component) it.next();
                if (c instanceof Treecell) {
                    for (Iterator it2 = c.getChildren().iterator(); it2
                            .hasNext();) {
                        Component c2 = (Component) it2.next();
                        if (c2 instanceof Label) {
                            Label l = (Label) c2;
                            l.setStyle("color: " + color + ";"); //$NON-NLS-1$ //$NON-NLS-2$
                        }
                    }
                }
            }
        }
    }
    
    public void afterCompose() {
    	try {
    		refreshBaseVersion();
    	} catch (InternalErrorException e) {
    		e.printStackTrace();
		}
    }

    private void refreshBaseVersion() throws InternalErrorException {
        try {
            Label l = (Label) getNavegador().getFellow("serverVersion"); //$NON-NLS-1$
            String version = EJBLocator.getServerPluginService().getServerVersion();
            if (version != null)
                l.setValue(version);
        } catch (NamingException e) {
            throw new UiException(e);
        } catch (CreateException e) {
            throw new UiException(e);
        }
    }
    
    /** Method that implements the functionality to force restart server.
     * 
     */
    public void restartConsole()
    {
    	Missatgebox.confirmaYES_NO(
			Messages.getString("PluginsUI.ResetServerQuestion"), //$NON-NLS-1$
    		new EventListener()
    		{
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
		FileWriter wr = new FileWriter(file, true);
		String home = System.getProperty ("jboss.server.home.dir"); //$NON-NLS-1$
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
			wr.close();
		}
    }
	
	private void showRefreshPopup() throws InterruptedException
	{
		org.zkoss.zul.Window p = (org.zkoss.zul.Window) getNavegador()
						.getFellow("popupRefreshPage"); //$NON-NLS-1$
//		p.doModal();
		p.doHighlighted();

	}
}
