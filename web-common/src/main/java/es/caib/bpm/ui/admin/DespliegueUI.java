package es.caib.bpm.ui.admin;


import java.awt.Desktop;
import java.awt.geom.Path2D;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.security.auth.login.LoginException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zkoss.util.media.Media;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Button;
import org.zkoss.zul.Fileupload;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;
import es.caib.bpm.exception.BPMException;
import es.caib.bpm.servei.ejb.BpmEngine;
import es.caib.bpm.toolkit.BPMApplication;
import es.caib.bpm.vo.ProcessDefinition;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.component.DataListbox;
import es.caib.zkib.events.SerializableEventListener;
import es.caib.zkib.zkiblaf.Frame;

public class DespliegueUI extends Frame 
{
	private static final long serialVersionUID = 1L;
	private Listbox listbox;
	private Button disablebutton;
	private Button enablebutton;
	private static Log log = LogFactory.getLog(DespliegueUI.class);

	public void onCreate () throws Exception
	{
		listbox = (Listbox) getFellow("listbox"); //$NON-NLS-1$
		//disablebutton = (Button) getFellow("disableButton"); //$NON-NLS-1$
		//enablebutton = (Button) getFellow("enableButton"); //$NON-NLS-1$
		cargarProcesosHabilitados();
	}
	
	/**
	 * @throws LoginException 
	 * @throws RemoteException 
	 * @throws NamingException 
	 * @throws CreateException 
	 * @throws InternalErrorException 
	 * 
	 */
	public void cargarListadoProcesos() throws LoginException,
		RemoteException, CreateException, NamingException,
		InternalErrorException 
	{
		BpmEngine engine= BPMApplication.getEngine();
		List list= null;
		ProcessDefinition definition= null;
		Listitem item= null;

		list= engine.findProcessDefinitions(null, false);
		
		if(listbox.getItems()!= null) {
			listbox.getItems().clear();
		}
		
		for(Iterator it= list.iterator(); it.hasNext();)
		{
			definition = (ProcessDefinition)it.next();
			
			item= new Listitem();
			String color = definition.isEnabled() ? "#000000" : "#808080"; //$NON-NLS-1$ //$NON-NLS-2$
			Listcell nom = new Listcell(definition.getName());
			nom.setStyle("color: " + color + ";"); //$NON-NLS-1$ //$NON-NLS-2$
			Listcell tag = new Listcell(definition.getTag());
			tag.setStyle("color: " + color + ";"); //$NON-NLS-1$ //$NON-NLS-2$
			item.getChildren().add(nom);
			item.getChildren().add(tag);
			if (definition.isEnabled())
			{
				Button boto = new Button();
				boto.setLabel(Labels.getLabel("deploy.btnDisable")); //$NON-NLS-1$
				boto.setAttribute("definition", definition); //$NON-NLS-1$
				boto.addEventListener(Events.ON_CLICK, new SerializableEventListener()
				{
					public void onEvent(org.zkoss.zk.ui.event.Event event)
						throws BPMException, CreateException, NamingException,
						LoginException, RemoteException, InternalErrorException 
					{
						ProcessDefinition definition = (ProcessDefinition) event.getTarget().getAttribute("definition"); //$NON-NLS-1$
						definition = BPMApplication.getEngine().disableProcessDefinition(definition);
						cargarListadoProcesos();
					}
				});
				Listcell cell = new Listcell();
				boto.setParent(cell);
				cell.setParent(item);
			}
			
			else
			{
				Button boto = new Button();
				boto.setLabel(Labels.getLabel("deploy.btnEnable")); //$NON-NLS-1$
				boto.setAttribute("definition", definition); //$NON-NLS-1$
				boto.addEventListener(Events.ON_CLICK, new SerializableEventListener()
				{
					public void onEvent(org.zkoss.zk.ui.event.Event event)
						throws BPMException, CreateException, NamingException,
						LoginException, RemoteException, InternalErrorException
					{
						ProcessDefinition definition = (ProcessDefinition) event.getTarget().getAttribute("definition"); //$NON-NLS-1$
						definition = BPMApplication.getEngine().enableProcessDefinition(definition);
						cargarListadoProcesos();
					}
				});
				Listcell cell = new Listcell();
				boto.setParent(cell);
				cell.setParent(item);
			}
			item.setValue(definition);
			listbox.getItems().add(item);
		}
	}
	
	public void cargarProcesosHabilitados() throws LoginException,
		RemoteException, CreateException, NamingException,
		InternalErrorException 
	{
		BpmEngine engine= BPMApplication.getEngine();
		List list= null;
		ProcessDefinition definition= null;
		Listitem item= null;

		list= engine.findProcessDefinitions(null, false);
		
		if(listbox.getItems()!= null)
		{
			listbox.getItems().clear();
		}
		
		for (Iterator it= list.iterator(); it.hasNext();)
		{
			definition = (ProcessDefinition)it.next();
			
			if(!definition.isEnabled() && it.hasNext())
				definition = (ProcessDefinition)it.next();
			
			if(definition.isEnabled())
			{
				item= new Listitem();
				String color = definition.isEnabled() ? "#000000" : "#808080"; //$NON-NLS-1$ //$NON-NLS-2$
				Listcell nom = new Listcell(definition.getName());
				nom.setStyle("color: " + color + ";"); //$NON-NLS-1$ //$NON-NLS-2$
				Listcell tag = new Listcell(definition.getTag());
				tag.setStyle("color: " + color + ";"); //$NON-NLS-1$ //$NON-NLS-2$
				item.getChildren().add(nom);
				item.getChildren().add(tag);
			
				Button boto = new Button();
				boto.setLabel(Labels.getLabel("deploy.btnDisable")); //$NON-NLS-1$
				boto.setAttribute("definition", definition); //$NON-NLS-1$
				boto.addEventListener(Events.ON_CLICK, new SerializableEventListener()
				{
					public void onEvent(org.zkoss.zk.ui.event.Event event)
						throws BPMException, CreateException, NamingException,
						LoginException, RemoteException, InternalErrorException 
					{
						ProcessDefinition definition = (ProcessDefinition) event.getTarget().getAttribute("definition"); //$NON-NLS-1$
						definition = BPMApplication.getEngine().disableProcessDefinition(definition);
						cargarProcesosHabilitados();
					}
				});
				Listcell cell = new Listcell();
				boto.setParent(cell);
				cell.setParent(item);
				
				item.setValue(definition);
				listbox.getItems().add(item);
			}
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
		this.getDesktop().getSession();
		BpmEngine engine= BPMApplication.getEngine();
		Media dataSubida= null;
		InputStream streamLectura= null;
		int leidos= 0;
		byte[] buffer= new byte[4096];
		Textbox resultadoDespliegue= null;
		
		try 
		{
			resultadoDespliegue= (Textbox)this.getFellow("txtResultadoDespliegue"); //$NON-NLS-1$
			
			dataSubida = Fileupload.get();
			
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
				this.cargarListadoProcesos();
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
	
	public void enable () throws BPMException, CreateException,
		NamingException, LoginException, RemoteException, InternalErrorException
	{
		if (listbox.getSelectedItem() != null)
		{
			ProcessDefinition def = (ProcessDefinition) listbox.getSelectedItem().getValue();
			def = BPMApplication.getEngine().enableProcessDefinition(def);
			listbox.getSelectedItem().setValue(def);
			cargarListadoProcesos();
		}
	}

	public void disable () throws BPMException, CreateException,
		NamingException, LoginException, RemoteException, InternalErrorException
	{
		if (listbox.getSelectedItem() != null)
		{
			ProcessDefinition def = (ProcessDefinition) listbox.getSelectedItem().getValue();
			def = BPMApplication.getEngine().disableProcessDefinition(def);
			listbox.getSelectedItem().setValue(def);
			cargarListadoProcesos();
		}
	}
	
	public boolean hayDeshabilitados () throws LoginException,
		RemoteException, CreateException, NamingException,
		InternalErrorException 
	{
		BpmEngine engine= BPMApplication.getEngine();
		List list= null;
		ProcessDefinition definition= null;
		Listitem item= null;

		list= engine.findProcessDefinitions(null, false);
		
		if(listbox.getItems()!= null) {
			listbox.getItems().clear();
		}
		for (Iterator it= list.iterator(); it.hasNext();)
		{
			definition = (ProcessDefinition)it.next();
			if(!definition.isEnabled())
				return true;
		}
		return false;
	}
}
