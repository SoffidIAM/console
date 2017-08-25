package es.caib.bpm.ui.admin;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;

import com.soffid.iam.doc.exception.NASException;

import es.caib.bpm.toolkit.BPMApplication;
import es.caib.seycon.ng.EJBLocator;
import es.caib.seycon.ng.comu.Configuracio;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.servei.ejb.ConfiguracioService;
import es.caib.zkib.zkiblaf.Frame;
import es.caib.zkib.zkiblaf.Missatgebox;

public class ConfigureIndex extends Frame
{
	
	private static final long serialVersionUID = 1L;
	private Textbox txt;
	private Listbox selected;
	private Textbox server;
	private Textbox path;
	private Textbox tempPath;
	private Textbox user;
	private Textbox password;

	public void onCreate () throws Exception
	{
		getDocumentManagerSettings();
		getIndexSettings();
	}

	public void configure () throws IOException, CreateException,
		NamingException, InternalErrorException
	{
		configureDocumentManager();
		configureIndex();
	}

	public void reindex () throws InterruptedException, CreateException,
		NamingException, IOException, InternalErrorException
	{
		BPMApplication.getEngine().reindex();
		Missatgebox.info(Labels.getLabel("configure.endIndexing")); //$NON-NLS-1$
	}

	/**
	 * Method that implements the functionality to obtain the index settings.
	 * 
	 * @throws InternalErrorException
	 * @throws CreateException
	 * @throws NamingException
	 */
	private void getIndexSettings () throws InternalErrorException,
		CreateException, NamingException
	{
		txt = (Textbox) getFellow("txt"); //$NON-NLS-1$
		Map m = BPMApplication.getEngine().getConfiguration();
		String value = (String) m.get("lucene.dir"); //$NON-NLS-1$
		txt.setValue(value);
	}

	/**
	 * Method that implements the functionality to obtain the document manager
	 * settings.
	 * 
	 */
	public void getDocumentManagerSettings () throws CreateException,
		NamingException, InternalErrorException
	{
		selected = (Listbox) getFellow("lbStrategy"); //$NON-NLS-1$
		server = (Textbox) getFellow("txtboxServer"); //$NON-NLS-1$
		path = (Textbox) getFellow("txtboxPath"); //$NON-NLS-1$
		tempPath = (Textbox) getFellow("txtboxtempPath"); //$NON-NLS-1$
		user = (Textbox) getFellow("txtboxUsername"); //$NON-NLS-1$
		password = (Textbox) getFellow("txtboxPassword"); //$NON-NLS-1$

		// Get configuration parameters
		String docStrategy = getDocumentMngParameter("soffid.ui.docStrategy"); //$NON-NLS-1$
		String docServer = getDocumentMngParameter("soffid.ui.docServer"); //$NON-NLS-1$
		String docPath = getDocumentMngParameter("soffid.ui.docPath"); //$NON-NLS-1$
		String docTempPath = getDocumentMngParameter("soffid.ui.docTempPath"); //$NON-NLS-1$)
		String username = getDocumentMngParameter("soffid.ui.docUsername"); //$NON-NLS-1$
		String userPassword = getDocumentMngParameter("soffid.ui.docUserPassword"); //$NON-NLS-1$

		// Set in window
		List<Listitem> listitems = selected.getItems();
		for (Listitem item : listitems)
		{
			if (item.getValue().equals(docStrategy))
			{
				selected.setSelectedItem(item);
				Events.postEvent(new org.zkoss.zk.ui
					.event.Event("onSelect", selected)); //$NON-NLS-1$
				break;
			}
		}
		
		server.setValue(docServer);
		path.setValue(docPath);
		tempPath.setValue(docTempPath);
		user.setValue(username);
		password.setValue(userPassword);
	}

	/**
	 * Method that implements the functionality to set index settings.
	 * @throws InternalErrorException
	 * @throws CreateException
	 * @throws NamingException
	 * @throws IOException
	 */
	public void configureIndex () throws InternalErrorException,
		CreateException, NamingException, IOException
	{
		Map m = BPMApplication.getEngine().getConfiguration();
		m.put("lucene.dir", txt.getValue()); //$NON-NLS-1$
		BPMApplication.getEngine().changeConfiguration(m);
	}

	/**
	 * /** Method that implements the functionality to set document manager settings.
	 * 
	 */
	public void configureDocumentManager ()  throws CreateException,
		NamingException, InternalErrorException
	{
		String docStrategy =
			selected.getSelectedItem().getValue().toString();	// Document manager strategy
		
		// Check document manager path
		if (path.getValue().isEmpty())
		{
			throw new InternalErrorException
				(Messages.getString("ConfigureIndex.VoidPathMessage")); //$NON-NLS-1$
		}
		
		// Check document manager temporary path
		if (tempPath.getValue().isEmpty())
		{
			throw new InternalErrorException(
				Messages.getString("ConfigureIndex.VoidTempPathMsg")); //$NON-NLS-1$
		}
		
		// Check document manager server
		if (docStrategy.equals("es.caib.bpm.nas.comm.FTPStrategy") && //$NON-NLS-1$
			server.getValue().isEmpty())
		{
			throw new InternalErrorException(
				Messages.getString("ConfigureIndex.VoidServerMsg")); //$NON-NLS-1$
		}
		
		// Check user/password restrictions
		if ((docStrategy.equals("es.caib.bpm.nas.comm.CIFSStrategy") || //$NON-NLS-1$
			docStrategy.equals("es.caib.bpm.nas.comm.FTPStrategy"))) //$NON-NLS-1$
		{
			if (user.getValue().isEmpty() || password.getValue().isEmpty())
			{
				throw new InternalErrorException(
					Messages.getString("ConfigureIndex.VoidUserDataMessage")); //$NON-NLS-1$
			}
		}
		
		saveDocumentManagerParameters(docStrategy, server.getValue(),
			path.getValue(), tempPath.getValue(), user.getValue(),
			password.getValue());
		
		Missatgebox.info("The configuration was succesfully saved",
			"Configuration updated");
	}

	/**
	 * Method that implements the functionality to save the document manager settings.
	 * @param docStrategy
	 * @param docPath
	 * @param username
	 * @param userPassword
	 * @param server
	 * @param string 
	 * @throws InternalErrorException 
	 * @throws CreateException 
	 * @throws NamingException 
	 * @throws NASException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	private void saveDocumentManagerParameters(String docStrategy,
		String server, String docPath, String docTempPath, String username,
		String userPassword) throws NamingException, CreateException,
		InternalErrorException
	{
		// Document manager strategy
		saveParameter("soffid.ui.docStrategy", docStrategy); //$NON-NLS-1$
		
		// Document manager path
		saveParameter("soffid.ui.docPath", docPath); //$NON-NLS-1$
		
		// Document manager temporary path
		saveParameter("soffid.ui.docTempPath", docTempPath); //$NON-NLS-1$
		
		if (docStrategy.equals("es.caib.bpm.nas.comm.FTPStrategy") || //$NON-NLS-1$
				docStrategy.equals("es.caib.bpm.nas.comm.CIFSStrategy") ||
				docStrategy.equals("es.caib.bpm.nas.comm.HTTPStrategy")) //$NON-NLS-1$
		{
			saveParameter("soffid.ui.docServer", server); //$NON-NLS-1$
			
			saveParameter("soffid.ui.docUsername", username); //$NON-NLS-1$
			saveParameter("soffid.ui.docUserPassword", userPassword); //$NON-NLS-1$
		}
		
		else
		{
			clearNotUserProperty("soffid.ui.docServer"); //$NON-NLS-1$
			clearNotUserProperty("soffid.ui.docUsername"); //$NON-NLS-1$
			clearNotUserProperty("soffid.ui.docUserPassword"); //$NON-NLS-1$
		}
	}

	/**
	 * Method that implements the functionality to remove properties
	 * not used for the current strategy.
	 * @param string
	 * @throws NamingException 
	 * @throws CreateException 
	 * @throws InternalErrorException 
	 */
	private void clearNotUserProperty(String key) throws NamingException,
		CreateException, InternalErrorException
	{
		ConfiguracioService ejb = EJBLocator.getConfiguracioService();
		
		Collection<Configuracio> result =
			ejb.findConfiguracioByFiltre(key, null, null, null);
		
		if (!result.isEmpty())
		{
			ejb.delete(result.iterator().next());
			System.clearProperty(key);
		}
	}

	/** Method that implements the functionality to store
	 * only one document manager setting.
	 * @param key
	 * @param docStrategy
	 * @throws NamingException 
	 * @throws CreateException 
	 * @throws InternalErrorException 
	 */
	private void saveParameter(String key, String value)
		throws NamingException, CreateException, InternalErrorException
	{
		ConfiguracioService ejb = EJBLocator.getConfiguracioService();
		
		Collection<Configuracio> result =
			ejb.findConfiguracioByFiltre(key, null, null, null);
		
		System.setProperty(key, value);
		
		if (result.isEmpty())
		{
			Configuracio configuracio = new Configuracio();
			configuracio.setCodi(key);
			configuracio.setValor(value);
			ejb.create(configuracio);
		}
		
		else
		{
			result.iterator().next().setValor(value);
			ejb.update(result.iterator().next());
		}
	}
	
	/**
	 * Method that implements the functionality to obtain
	 * the document manager settings.
	 * @param key
	 * @return
	 * @throws NamingException 
	 * @throws CreateException 
	 * @throws InternalErrorException 
	 */
	private String getDocumentMngParameter(String key) throws NamingException,
		CreateException, InternalErrorException
	{
		ConfiguracioService ejb = EJBLocator.getConfiguracioService();
		
		Collection<Configuracio> result =
			ejb.findConfiguracioByFiltre(key, null, null, null);
		
		return (result.isEmpty() ? "" : result.iterator().next().getValor()); //$NON-NLS-1$
	}
}
