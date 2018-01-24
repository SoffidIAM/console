package es.caib.bpm.ui.task;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import javax.ejb.CreateException;
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
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import es.caib.bpm.servei.ejb.BpmEngine;
import es.caib.bpm.toolkit.EJBContainer;
import es.caib.bpm.vo.BPMUser;
import es.caib.seycon.ng.comu.Usuari;

public class SeleccionUsuarioUI extends Window 
{
	private static final long serialVersionUID = 1L;
	private String delegateUsuCodi=null;
	public Comparator [] cmpAsc=new StringArrayComparator[4];
	public Comparator [] cmpDsc=new StringArrayComparator[4];
	public ListModelList listModel=new ListModelList();
	public ListitemRenderer listRenderer=new SeleccionUsuarioUIItemRenderer();

	/**
	 * @return the listModel
	 */
	public ListModelList getListModel ()
	{
		return listModel;
	}

	/**
	 * @return the cmpAsc
	 */
	public Comparator[] getCmpAsc ()
	{
		return cmpAsc;
	}

	/**
	 * @return the cmpDsc
	 */
	public Comparator[] getCmpDsc ()
	{
		return cmpDsc;
	}

	/**
	 * @return the listRenderer
	 */
	public ListitemRenderer getListRenderer ()
	{
		return listRenderer;
	}

	public SeleccionUsuarioUI() throws NamingException, CreateException {
	}
	
	public String getUsuarioSeleccionado()
	{
		return delegateUsuCodi;
	}
	
	private void removeSearchRows() {
		Component comp=getFellow("busquedafirmanterows"); //$NON-NLS-1$
		
		Listbox rows=(Listbox) comp;
		List l=rows.getItems();
		int size=l.size();
		Listitem r[]=(Listitem [])l.toArray(new Listitem[size]);
		//saltamos la primera fila
		
		for(int i=0;i<size;i++)
		{
			rows.removeItemAt(i);
		}
	}
	
	public void setDelegat(String usuCodi){
		delegateUsuCodi=usuCodi;
	}
	
	private static Log log = LogFactory.getLog(SeleccionUsuarioUI.class);
	
	public class SeleccionUsuarioUIItemRenderer implements ListitemRenderer
	{
		Button delegar = null;
		
		public void render(Listitem item, java.lang.Object data)
		{
			BPMUser user = (BPMUser) data;

			Label lb = new Label(user.getUserName());
			Listcell cell = new Listcell();
			cell.appendChild(lb);
			item.appendChild(cell);
			
			lb = new Label(user.getGivenName());
			cell = new Listcell();
			cell.appendChild(lb);
			item.appendChild(cell);

			lb = new Label(user.getSurName());
			cell = new Listcell();
			cell.appendChild(lb);
			item.appendChild(cell);

			Listcell cell2 = new Listcell();
			delegar = new Button(Messages.getString("SeleccionUsuarioUI.DelegateTask")); //$NON-NLS-1$
			delegar.setAttribute("usucodi", user.getUserName()); //$NON-NLS-1$
			delegar.addEventListener("onClick", //$NON-NLS-1$
				new SeleccionUsuarioUIDelegarBtnEventListener(user.getUserName()));
			cell2.appendChild(delegar);
			item.appendChild(cell2);
		} 
	}
	
	protected class SeleccionUsuarioUIDelegarBtnEventListener implements EventListener, Serializable{
		private static final long serialVersionUID = 1L;
		private String codiUsuari;

		public SeleccionUsuarioUIDelegarBtnEventListener(String codiUsuari) {
			this.codiUsuari=codiUsuari;
		}

		public void onEvent(Event arg0)
		{
			setDelegat(codiUsuari);
			detach();
		}
	}

	public void findUsers(String nom, String llinatges, String group)
					throws Exception
	{
		BpmEngine engine = EJBContainer.getEJBContainer(Sessions
			.getCurrent()).getEngine();

		Collection<BPMUser> users = engine.findUsers(null, nom, llinatges,
			group);
		
		listModel.clear();
		for(BPMUser user: users)
		{
			listModel.add(user);
		}
	}
	
	public void findUsers(String code, String nom, String llinatges,
		String group) throws Exception
	{
		BpmEngine engine = EJBContainer.getEJBContainer(Sessions
			.getCurrent()).getEngine();

		Collection<BPMUser> users = engine.findUsers(code, nom, llinatges, group);
		
		listModel.clear();
		for(BPMUser user: users)
		{
			listModel.add(user);
		}
	}
	
	protected class SeleccionUsuarioListModel extends AbstractListModel{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		List model= new ArrayList();
		public SeleccionUsuarioListModel(Collection model) {
			this.model.addAll(model);
		}

		public Object getElementAt(int index) {
			if(model==null) return null;
			if(model.size()<=index) return new IndexOutOfBoundsException();
			Usuari u=((Usuari)model.get(index));
			
			return new String [] {u.getCodi(), u.getNom()+" "+u.getPrimerLlinatge()+" "+u.getSegonLlinatge()}; //$NON-NLS-1$ //$NON-NLS-2$
		}

		public int getSize() {
			return model.size();
		}
	}

	public void searchDelegateUsers() throws WrongValueException, Exception
	{
		Textbox code_t = (Textbox)getFellow("usu_code"); //$NON-NLS-1$
		Textbox nom_t = (Textbox)getFellow("usu_nom"); //$NON-NLS-1$
		Textbox lli_t = (Textbox)getFellow("usu_lli"); //$NON-NLS-1$
		String userCode;	// User code
		String userName;	// User name
		String surname;		// User surname
		
		// Check void search parameters
		if (!code_t.getValue().isEmpty() || !nom_t.getValue().isEmpty() ||
			!lli_t.getValue().isEmpty())
		{
			userCode = ((!code_t.getValue().isEmpty()) ?
				Autowildcards.replaceAsteriskChar(code_t.getValue()) : null);
			
			userName = ((!nom_t.getValue().isEmpty()) ?
				Autowildcards.replaceAsteriskChar(nom_t.getValue()) : null);
			
			surname = ((!lli_t.getValue().isEmpty()) ?
				Autowildcards.replaceAsteriskChar(lli_t.getValue()) : null);
			
			findUsers(userCode, userName, surname, null);
		}
	}
	
	public class StringArrayComparator implements Comparator{
		boolean asc=true;
		int col=0;
	
		public StringArrayComparator(boolean ascending,int column)
		{
			this.asc=ascending;
			this.col=column;
		}
		
		public int compare(Object arg0, Object arg1) {
			int cmp=((String)((Object[]) arg0)[col]).toLowerCase().compareTo(((String)((Object [])arg1)[col]).toLowerCase());
			return (asc)?cmp:-cmp;
		}
	}
}
