package es.caib.bpm.ui.task;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import javax.ejb.CreateException;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.AbstractListModel;
import org.zkoss.zul.Button;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import es.caib.bpm.servei.ejb.BpmEngine;
import es.caib.bpm.toolkit.EJBContainer;
import es.caib.bpm.vo.BPMUser;
import es.caib.seycon.ng.comu.Usuari;
import es.caib.seycon.ng.servei.ejb.UsuariService;
import es.caib.seycon.ng.servei.ejb.UsuariServiceHome;
import es.caib.zkib.zkiblaf.Missatgebox;


public class SeleccionUsuarioUI2  extends SeleccionUsuarioUITemplate 
{
	
	private static final long serialVersionUID = 1L;
	protected String delegateUsuCodi=null;
	public Comparator [] cmpAsc=new StringArrayComparator[4];
	public Comparator [] cmpDsc=new StringArrayComparator[4];
	protected String buttonLabel=""; //$NON-NLS-1$
	public ListModelList listModel=new ListModelList();	
	public ListitemRenderer listRenderer=new SeleccionUsuarioUIItemRenderer();
	private UsuariService usuariService;
	
	public SeleccionUsuarioUI2() throws CreateException, NamingException {
		UsuariServiceHome svcHome = (UsuariServiceHome) new InitialContext().lookup(UsuariServiceHome.JNDI_NAME);
		usuariService = svcHome.create();

		cmpAsc[3]=new StringArrayComparator(true,3);
		cmpAsc[2]=new StringArrayComparator(true,2);
		cmpAsc[1]=new StringArrayComparator(true,1);
		cmpAsc[0]=new StringArrayComparator(true,0);

		cmpDsc[3]=new StringArrayComparator(false,3);
		cmpDsc[2]=new StringArrayComparator(false,2);
		cmpDsc[1]=new StringArrayComparator(false,1);
		cmpDsc[0]=new StringArrayComparator(false,0);
			

	}

	
	public String getUsuarioSeleccionado()
	{
		return delegateUsuCodi;
	}
	

	public void setDelegat(String usuCodi){
		delegateUsuCodi=usuCodi;
	}
	
	private static Log log = LogFactory.getLog(SeleccionUsuarioUI2.class);
	
	public class SeleccionUsuarioUIItemRenderer implements ListitemRenderer{
		Button delegar=null;
		
		public void render(Listitem item, java.lang.Object data){
			Object [] cols =(Object [])data;
			int i=0;
			for(i=0;i<cols.length;i++){
				Label lb=new Label((String)cols[i]);
				Listcell cell=new Listcell();
				cell.appendChild(lb);
				item.appendChild(cell);
			}
			
			Listcell cell2=new Listcell();
			delegar=new Button(getSelectButtonLabel(cols));
			delegar.setAttribute("usucodi",(String)cols[0]); //$NON-NLS-1$
			delegar.addEventListener("onClick",setSelectButtonEventListener(cols)); //$NON-NLS-1$
			cell2.appendChild(delegar);
			item.appendChild(cell2);
		} 
	}

	public void findUsers(String nom, String llinatges,String group) throws Exception{
		BpmEngine engine = EJBContainer.getEJBContainer(Sessions.getCurrent()).getEngine();

		Collection<BPMUser> users = engine.findUsers(null, nom, llinatges, group);
		
		listModel.clear();
		for(BPMUser user: users) {
			listModel.add(user);
		}
	}
	

	public void searchDelegateUsers() throws WrongValueException, Exception{
		Textbox nom_t=(Textbox)getFellow("usu_nom"); //$NON-NLS-1$
		Textbox lli_t=(Textbox)getFellow("usu_lli"); //$NON-NLS-1$
		String nom = nom_t.getValue();
		String lli = lli_t.getValue();
		if ((nom==null || "".equals(nom.trim())) && (lli==null || "".equals(lli.trim()))) { //$NON-NLS-1$ //$NON-NLS-2$
			Missatgebox.avis(Messages.getString("SeleccionUsuarioUI2.SearchParamsInfo")); //$NON-NLS-1$
			return;
		}
				
		findUsers( nom,lli,""); //$NON-NLS-1$
	}
		
	
	
	

	public class StringArrayComparator implements Comparator{
		boolean asc=true;
		int col=0;
	
		public StringArrayComparator(boolean ascending,int column) {
			this.asc=ascending;
			this.col=column;
	
		}
		
		public int compare(Object arg0, Object arg1) {
			int cmp=((String)((Object[]) arg0)[col]).toLowerCase().compareTo(((String)((Object [])arg1)[col]).toLowerCase());
			return (asc)?cmp:-cmp;
		}
		
		
	}
	protected class SeleccionUsuarioListModel extends AbstractListModel{
		List model= new ArrayList();
		public SeleccionUsuarioListModel(Collection model) {
			this.model.addAll(model);
		}

		public Object getElementAt(int index) {
			if(model==null) return null;
			if(model.size()<=index) return new IndexOutOfBoundsException();
			BPMUser user = (BPMUser) model.get(index);
			return user;
		}

		public int getSize() {
			return model.size();
		}
		

		
	}
	/**
	 * Ha de retornar el String amb la cadena que es farà servir de label del botó per a finalitzar la selecció d'usuari 	
	 * @param label
	 * @return
	 */
	public String getSelectButtonLabel(Object[] cols){
		return "Delega"; //$NON-NLS-1$
	
	}

	/**
	 * Ha de retornar un EventListener diferent cada vegada, que processarà la sel.lecció de l'usuari.
	 * Principalment ha d'invocar setDelegat(codiUsuari); per a que posteriorment es pugui recuperar el codi de l'usuari sel.leccionat
	 * @param cols
	 * @return
	 */
	public EventListener setSelectButtonEventListener(Object[] cols) {
		return new SeleccionUsuarioUIDelegarBtnEventListener((String)cols[0]);
	}
	
	public  class SeleccionUsuarioUIDelegarBtnEventListener implements EventListener{
		private String codiUsuari;

		public SeleccionUsuarioUIDelegarBtnEventListener(String codiUsuari) {
			this.codiUsuari=codiUsuari;
		}

		public void onEvent(Event arg0) {
           		setDelegat(codiUsuari);
           		detach();
       	}
	}

	
	public void cancelSearch() {
		setDelegat(null);
		
	}	

	/**
	 * @return the listModel
	 */
	public ListModelList getListModel() {
		return listModel;
	}


	/**
	 * @param listModel the listModel to set
	 */
	public void setListModel(ListModelList listModel) {
		this.listModel = listModel;
	}


	/**
	 * @return the listRenderer
	 */
	public ListitemRenderer getListRenderer() {
		return listRenderer;
	}


	/**
	 * @param listRenderer the listRenderer to set
	 */
	public void setListRenderer(ListitemRenderer listRenderer) {
		this.listRenderer = listRenderer;
	}


	/**
	 * @return the cmpAsc
	 */
	public Comparator[] getCmpAsc() {
		return cmpAsc;
	}


	/**
	 * @param cmpAsc the cmpAsc to set
	 */
	public void setCmpAsc(Comparator[] cmpAsc) {
		this.cmpAsc = cmpAsc;
	}


	/**
	 * @return the cmpDsc
	 */
	public Comparator[] getCmpDsc() {
		return cmpDsc;
	}


	/**
	 * @param cmpDsc the cmpDsc to set
	 */
	public void setCmpDsc(Comparator[] cmpDsc) {
		this.cmpDsc = cmpDsc;
	}

	
	
}
