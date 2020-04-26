package es.caib.bpm.ui.task;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.AbstractListModel;
import org.zkoss.zul.Button;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Textbox;

import es.caib.seycon.ng.comu.Usuari;


public abstract class SeleccionUsuarioUITemplate extends es.caib.bpm.ui.task.SeleccionUsuarioUI 
{
	private static final long serialVersionUID = 1L;
	protected String delegateUsuCodi=null;
	public Comparator [] cmpAsc=new StringArrayComparator[4];
	public Comparator [] cmpDsc=new StringArrayComparator[4];
	protected String buttonLabel=""; //$NON-NLS-1$
	public ListModelList listModel=new ListModelList();
	public ListitemRenderer listRenderer=new SeleccionUsuarioUIItemRenderer();
	protected Map params=null;
	
	public SeleccionUsuarioUITemplate() throws CreateException, NamingException{
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
	
	protected void removeSearchRows() {
//		Component comp=getFellow("busquedafirmanterows");
//		
//		Listbox rows=(Listbox) comp;
//		List l=rows.getItems();		
//		int size=l.size();
//		Listitem r[]=(Listitem [])l.toArray(new Listitem[size]);
//		//saltamos la primera fila
//		
//		for(int i=0;i<size;i++){
//			
//			rows.removeItemAt(i);
//		}
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

	public  abstract void cancelSearch() ;

	public abstract void findUsers(String nom, String llinatges,String nif) throws Exception ;
	
	public void searchDelegateUsers() throws WrongValueException, Exception{
		Textbox nom_t=(Textbox)getFellow("usu_nom"); //$NON-NLS-1$
		Textbox lli_t=(Textbox)getFellow("usu_lli"); //$NON-NLS-1$
		findUsers( nom_t.getValue(), lli_t.getValue(),""); //$NON-NLS-1$
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
			Usuari u=((Usuari)model.get(index));
			return new String [] {u.getCodi(),u.getNom()};
		}

		public int getSize() {
			return model.size();
		}
	}
	
	/**
	 * @return the parameters
	 */
	public Map getParams() {
		return params;
	}

	/**
	 * @param parameters the parameters to set
	 */
	public void setParams(Map params) {
		this.params = params;
	}

	/**
	 * Ha de retornar el String amb la cadena que es farà servir de label del botó per a finalitzar la selecció d'usuari 	
	 * @param label
	 * @return
	 */
	public abstract String getSelectButtonLabel(Object[] cols);

	/**
	 * Ha de retornar un EventListener diferent cada vegada, que processarà la sel.lecció de l'usuari.
	 * Principalment ha d'invocar setDelegat(codiUsuari); per a que posteriorment es pugui recuperar el codi de l'usuari sel.leccionat
	 * @param cols
	 * @return
	 */
	public abstract EventListener setSelectButtonEventListener(Object[] cols);

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
