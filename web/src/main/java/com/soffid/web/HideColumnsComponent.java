package com.soffid.web;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.CreateException;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Menu;
import org.zkoss.zul.Menuitem;
import org.zkoss.zul.Menupopup;
import org.zkoss.zul.Toolbarbutton;

import es.caib.seycon.ng.comu.Usuari;
import es.caib.seycon.ng.comu.UsuariSEU;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.servei.ejb.UsuariService;
import es.caib.seycon.ng.servei.ejb.UsuariServiceHome;
import es.caib.zkib.binder.list.DataListItemRenderer;

public class HideColumnsComponent extends Toolbarbutton
{
	String listboxName;
	Listbox listbox;
	String preferenceName;
	
	public String getPreferenceName() {
		return preferenceName;
	}

	public void setPreferenceName(String preferenceName) {
		this.preferenceName = preferenceName;
	}

	boolean created = false;
	private Menupopup popupMenu;
	
	public HideColumnsComponent ()
	{
		super ();
		setLabel(Labels.getLabel("auditoria.zul.Columnesvisibles"));
		setImage ("~./img/checked.gif"); 
	}

	public String getListbox() {
		return listboxName;
	}

	public void setListbox(String listbox) throws InternalErrorException, NamingException, CreateException {
		this.listboxName = listbox;
		this.listbox = null;
		if (created)
			generateMenu();
	}

	public void setListbox (Listbox listbox) throws InternalErrorException, NamingException, CreateException
	{
		this.listboxName = null;
		this.listbox = listbox;
		if (created)
			generateMenu();
	}
	
	public void onCreate() throws InternalErrorException, NamingException, CreateException
	{
		created = true;
		generateMenu ();
		addEventListener("onClick", new EventListener() {
			
			public void onEvent(Event event) throws Exception {
				popupMenu.open( HideColumnsComponent.this );
			}
		});
	}

	protected int getNumberOfColumns ()
	{
		int number = 0;
		Component head = listbox.getListhead();
		for ( Component c: (List<Component>) head.getChildren())
		{
			if (c instanceof Listheader)
				number ++;
		}
		return number;
	}
	
	protected void generateMenu() throws InternalErrorException, NamingException, CreateException {
		if (listbox == null)
			listbox = (Listbox) getFellow(listboxName);
		List<Component> children = new LinkedList(getChildren());
		for (Component c: children)
			c.setParent(null);
		popupMenu = new Menupopup();
		popupMenu.setParent(this.getParent().getParent());
		Component head = listbox.getListhead();
		
		boolean[] desired = getCurrentPreferences();
		int columnNumber = 0;
		for ( Component c: (List<Component>) head.getChildren())
		{
			if (c instanceof Listheader)
			{
				final Listheader lh = (Listheader) c;
				if (desired != null && desired.length > columnNumber)
					lh.setVisible(desired[columnNumber]);
				columnNumber ++;
				if (lh.getLabel() != null && lh.getLabel().length() > 0)
				{
					final Menuitem mi = new Menuitem(lh.getLabel());
					mi.setChecked(lh.isVisible());
					mi.addEventListener("onClick", new EventListener() {
						
						public void onEvent(Event event) throws Exception {
							showOrHide (mi, lh);
						}
					});
					mi.setParent(popupMenu);
				}
			}
		}
	}

	protected void showOrHide(Menuitem mi, Listheader lh) throws InternalErrorException, NamingException, CreateException {
		lh.setVisible( ! lh.isVisible());
		mi.setChecked(lh.isVisible());
		savePreferences();
		if (lh.isVisible())
		{
			Listbox lb = (Listbox) lh.getParent().getParent();
			if (lb.getItemRenderer() != null &&
					lb.getItemRenderer() instanceof DataListItemRenderer)
			{
				for (Listitem i: (List<Listitem>)lb.getItems())
				{
					try {
						lb.getItemRenderer().render(i, null);
					} catch (Exception e) {
					}
				}
			}
		}
	}

	private UsuariService getUsuariService () throws NamingException, CreateException
	{
		return (UsuariService) new InitialContext().lookup(UsuariServiceHome.JNDI_NAME);
	}
	
	private boolean[] getCurrentPreferences () throws InternalErrorException, NamingException, CreateException
	{
		Usuari u = getUsuariService().getCurrentUsuari();
		if (u == null)
			return null;
		UsuariSEU us = u.getUsuariSEU();
		if (us == null)
			return null;
		String p = (String) us.getPreferenciesSEU().get("hcc-"+preferenceName);
		if (p == null)
			return null;
		Long stored = Long.decode(p);
		// Now create and populate array
		int columns = getNumberOfColumns();
		boolean result[] = new boolean [columns];
		long l = stored;
		for (int bits = 0; bits < columns; bits++)
		{
			result[bits] = ( l & 1) == 1;
			l = l >> 1;
		}
		return result;
	}

	private void savePreferences () throws InternalErrorException, NamingException, CreateException
	{
		long l = 0;
		long mask = 1;
		Component head = listbox.getListhead();
		for ( Component c: (List<Component>) head.getChildren())
		{
			if (c instanceof Listheader)
			{
				if (c.isVisible())
					l = l | mask;
				mask = mask << 1;
			}
		}
		Usuari u = getUsuariService().getCurrentUsuari();
		if (u == null)
			return;
		UsuariSEU us = u.getUsuariSEU();
		if (us == null)
			return;
		us.getPreferenciesSEU().put("hcc-"+preferenceName, Long.toString(l));
		getUsuariService().update(us);
	}
	
}
