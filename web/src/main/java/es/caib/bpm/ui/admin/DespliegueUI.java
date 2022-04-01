package es.caib.bpm.ui.admin;


import java.io.IOException;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.security.auth.login.LoginException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zkoss.util.media.Media;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zul.Textbox;

import com.soffid.iam.bpm.api.ProcessDefinition;
import com.soffid.iam.bpm.service.ejb.BpmEngine;
import com.soffid.iam.web.bpm.BPMApplication;
import com.soffid.iam.web.component.FrameHandler;
import com.soffid.iam.web.popup.FileUpload2;

import es.caib.bpm.exception.BPMException;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.component.DataTable;
import es.caib.zkib.component.Switch;
import es.caib.zkib.datasource.XPathUtils;

public class DespliegueUI extends FrameHandler 
{
	
	public DespliegueUI() throws InternalErrorException {
		super();
	}

	private static final long serialVersionUID = 1L;
	private DataTable listbox;
	private static Log log = LogFactory.getLog(DespliegueUI.class);

	public void onCreate () throws Exception
	{
		listbox = (DataTable) getFellow("listbox"); //$NON-NLS-1$
	}
	
	public void onDisable(Event event) throws InternalErrorException, BPMException, CreateException, NamingException {
		ProcessDefinition def = (ProcessDefinition) XPathUtils.eval(getListbox(), "instance");
		if (def.isEnabled())
		{
			BPMApplication.getEngine().disableProcessDefinition(def);
			def.setEnabled(false);
			listbox.updateClientRow(listbox.getSelectedIndex());
		}
		else
		{
			BPMApplication.getEngine().enableProcessDefinition(def);
			def.setEnabled(true);
			listbox.updateClientRow(listbox.getSelectedIndex());
		}
	}
	
	/**
	 * Sube un archivo a la aplicación de workflow.
	 * @throws LoginException 
	 * @throws NamingException 
	 * @throws CreateException 
	 * @throws InternalErrorException 
	 *
	 */
	public void upload() throws LoginException, CreateException,
		NamingException, InternalErrorException 
	{
		
		
		FileUpload2.get((event) -> {
			doUpload((UploadEvent) event);
		});
	}

	public void doUpload(UploadEvent event) throws CreateException, NamingException, InternalErrorException {
		this.getDesktop().getSession();
		BpmEngine engine= BPMApplication.getEngine();
		byte[] buffer= new byte[4096];
		InputStream streamLectura= null;
		int leidos= 0;
		Textbox resultadoDespliegue= (Textbox)this.getFellow("txtResultadoDespliegue"); //$NON-NLS-1$
		try 
		{
			Media dataSubida = event.getMedia();
			if(dataSubida!= null)
			{
				resultadoDespliegue.setText(Labels.getLabel("deploy.msgDesplegandoProceso")); //$NON-NLS-1$
				
				streamLectura= dataSubida.getStreamData();
				
				log.debug(Messages.getString("DespliegueUI.OpenTransfer")); //$NON-NLS-1$
				engine.openDeployParDefinitionTransfer();
				
				log.debug(Messages.getString("DespliegueUI.SendPackages")); //$NON-NLS-1$
				while((leidos= streamLectura.read(buffer))!= -1)
				{
					engine.nextDeployParDefinitionPackage(buffer, leidos);
				}
				
				log.debug(Messages.getString("DespliegueUI.CloseTransfer")); //$NON-NLS-1$
				engine.endDeployParDefinitionTransfer();
				String messages [] = engine.getDeployMessages ();
				
				getModel().refresh();

				log.debug(Messages.getString("DespliegueUI.ProcessDeployedOK")); //$NON-NLS-1$
				
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy kk:mm:ss"); //$NON-NLS-1$
				String dataDesplegament = sdf.format(new Date());
				
				StringBuffer b = new StringBuffer ();
				b.append(dataDesplegament+": "+Labels.getLabel("deploy.msgProcesoDesplegadoCorrectamente")); //$NON-NLS-1$ //$NON-NLS-2$
				for (int i = 0; i < messages.length; i++)
				{
					b.append ('\n');
					b.append (messages[i]);
				}
				resultadoDespliegue.setText(b.toString());
			}
			onCheck(event);
		}
		catch (BPMException e) 
		{
			String messages [] = engine.getDeployMessages ();
			StringBuffer b = new StringBuffer ();
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy kk:mm:ss"); //$NON-NLS-1$
			String dataDesplegament = sdf.format(new Date());
			
			b.append(dataDesplegament+": "+Labels.getLabel("deploy.msgErrorDesplegandoProceso")); //$NON-NLS-1$ //$NON-NLS-2$
			for (int i = 0; i < messages.length; i++)
			{
				b.append ('\n');
				b.append (messages[i]);
			}
			resultadoDespliegue.setText(b.toString());
			log.error(e);
		}
		catch (Exception e) 
		{
			resultadoDespliegue.setText(Labels.getLabel("deploy.msgErrorDesplegandoProceso") + e.toString()); //$NON-NLS-1$
			log.error(e);
		} 
		finally 
		{
			if(streamLectura!= null) 
			{
				try 
				{
					streamLectura.close();
				} 
				catch (IOException e) 
				{
					log.error(e);
				}
			}
		}
	}
	
	public void onCheck(Event ev) throws LoginException, RemoteException, CreateException, NamingException, InternalErrorException {
		Switch todos = (Switch) getFellow("todos");
		getModel().getJXPathContext().getVariables().declareVariable("all", ! todos.isChecked());
		getModel().refresh();
	}
	
}
