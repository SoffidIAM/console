package com.soffid.iam.web;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Menuitem;
import org.zkoss.zul.Menupopup;
import org.zkoss.zul.Toolbarbutton;

import es.caib.seycon.ng.EJBLocator;
import es.caib.seycon.ng.comu.Usuari;
import es.caib.seycon.ng.comu.UsuariSEU;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.binder.list.DataListItemRenderer;
import es.caib.zkib.events.SerializableEventListener;

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

	public void refresh () throws InternalErrorException, NamingException, CreateException
	{
		generateMenu();
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
		addEventListener("onClick", new SerializableEventListener() {
			
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
					mi.addEventListener("onClick", new SerializableEventListener() {
						
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
				int row = 0;
				for (Listitem i: (List<Listitem>)lb.getItems())
				{
					if (! i.getChildren().isEmpty())
					{
						try {
							lb.getItemRenderer().render(i, null);
						} catch (Exception e) {
						}
					}
				}
				lb.invalidate();
			}
		}
	}
	
	private boolean[] getCurrentPreferences () throws InternalErrorException, NamingException, CreateException
	{
		Usuari u = EJBLocator.getUsuariService().getCurrentUsuari();
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
		Usuari u = EJBLocator.getUsuariService().getCurrentUsuari();
		if (u == null)
			return;
		UsuariSEU us = u.getUsuariSEU();
		if (us == null)
			return;
		us.getPreferenciesSEU().put("hcc-"+preferenceName, Long.toString(l));
		EJBLocator.getUsuariService().update(us);
	}
	
}
