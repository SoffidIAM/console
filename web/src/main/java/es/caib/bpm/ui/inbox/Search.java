package es.caib.bpm.ui.inbox;


import java.io.IOException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.DocumentException;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import es.caib.bpm.exception.BPMException;
import es.caib.bpm.servei.ejb.BpmEngine;
import es.caib.bpm.toolkit.BPMApplication;
import es.caib.bpm.vo.ProcessDefinition;
import es.caib.bpm.vo.ProcessInstance;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.zkiblaf.Application;
import es.caib.zkib.zkiblaf.Frame;
import es.caib.zkib.zkiblaf.Missatgebox;

public class Search extends Frame 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static Log log = LogFactory.getLog(Search.class);
	
	private Textbox txtProcessID;

	private Textbox txtTarea;
	
	private Datebox txtStartDate, txtEndDate;

	private Checkbox chkFinalizada;

	public Search() {}
	
	public void onCreate () throws Exception
	{
		txtProcessID = (Textbox) getFellow ("txtProcessID"); //$NON-NLS-1$
		txtTarea = (Textbox) getFellow ("txt"); //$NON-NLS-1$
		txtStartDate = (Datebox) getFellow ("startDate"); //$NON-NLS-1$
		txtEndDate = (Datebox) getFellow ("endDate"); //$NON-NLS-1$
		chkFinalizada = (Checkbox) getFellow("txtFinalizada"); //$NON-NLS-1$
	}
	
	private BpmEngine getEngine(Session sesion) throws CreateException, NamingException {
		return BPMApplication.getEngine();
	}
	
	public void buscar() throws RemoteException, InterruptedException,
		CreateException, NamingException, WrongValueException, BPMException,
		InternalErrorException
	{
		Session sesion= this.getDesktop().getSession();
		BpmEngine engine = getEngine(sesion);
		List resultado= null;
		Listitem item= null;
		Listbox resultadoBusqueda= null;
		
		try 
		{
			SimpleDateFormat formatoLucene = new SimpleDateFormat("yyyyMMdd"); //$NON-NLS-1$
			String dataIni=null, dataFi=null;
			if (txtStartDate.getValue()!=null) {
				try {
					dataIni = formatoLucene.format(txtStartDate.getValue());
					if (dataIni.length()!=8) throw new Exception();
				} catch (Throwable th) { 
	        		throw new BPMException(Messages.getString("Search.StartDateError"),-1); //$NON-NLS-1$
	        	}
			}
			if (txtEndDate.getValue()!=null) {
				try {
					dataFi = formatoLucene.format(txtEndDate.getValue());
					if (dataFi.length()!=8) throw new Exception();
				} catch (Throwable th) {
        			throw new BPMException(Messages.getString("Search.EndDateError"),-1); //$NON-NLS-1$
        		}
			}
			
			resultado = engine.searchProcessInstances(txtTarea.getValue(),
				txtProcessID.getValue(), dataIni, dataFi,
				chkFinalizada.isChecked());

			log.debug(String.format(Messages.getString("Search.NumInstancesFoundInfo"), resultado.size()));   //$NON-NLS-1$

			resultadoBusqueda= (Listbox)this.getFellow("resultadoBusqueda"); //$NON-NLS-1$
			
			//Limpiamos el resultado y la imagen
			resultadoBusqueda.getItems().clear();
			if(resultado.size() == 0)
			{
				Missatgebox.avis(Labels.getLabel("observacion.resultadoVacio")); //$NON-NLS-1$
			}
			
			SimpleDateFormat formatConHora= new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); //$NON-NLS-1$
			
			for(Iterator it= resultado.iterator(); it.hasNext();)
			{
				ProcessInstance instance = (ProcessInstance)it.next();
				if (chkFinalizada.isChecked() || instance.getEnd() == null)
				{
					ProcessDefinition definition = engine.getProcessDefinition(instance);
					item= new Listitem();
					item.setValue(instance);
					item.appendChild(new Listcell(Long.toString(instance.getId())));
					item.appendChild(new Listcell(definition.getName()));
					item.appendChild(new Listcell(instance.getCurrentTask()));
					item.appendChild(new Listcell(instance.getStart() != null? formatConHora.format(instance.getStart()): ""));
					item.appendChild(new Listcell((instance.getEnd()!= null) ?
							formatConHora.format(instance.getEnd()) : "")); //$NON-NLS-1$
					
					resultadoBusqueda.appendChild(item);
				}
			}
		} 
		catch (BPMException e) 
		{
			String msgError = Labels.getLabel("error.code." + e.getErrorCode()); //$NON-NLS-1$
			if (msgError==null)
				msgError = e.getMessage();
			Missatgebox.error(msgError);
		}
	}
	
	public void seleccionarProceso(Listbox listbox) throws DocumentException, IOException, CreateException, NamingException, BPMException
	{
        Listitem item = listbox.getSelectedItem();
        ProcessInstance process = (ProcessInstance) item.getValue();
        listbox.setSelectedItem(null);
        
        Application.call(BPMApplication.getProcessURL(process));
	}
}
