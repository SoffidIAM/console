package com.soffid.iam.ui;

import java.util.Collection;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listhead;

public class SeyconTaskComposer extends GenericForwardComposer {

	private Listbox listboxTasques;

	private SeyconTasksData seyconTasksData;

	private ListModelList listmodel;
	
	SeyconTaskRenderer dataRenderer;

	public SeyconTaskComposer() {
		seyconTasksData = new SeyconTasksData();
		dataRenderer = new SeyconTaskRenderer();
		listmodel = new ListModelList(seyconTasksData.getAll());
	}

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		
		listboxTasques.setModel(listmodel);
		listboxTasques.setItemRenderer(dataRenderer);
	}

	public void setModel(SeyconTasksData dades) {
		if (listboxTasques != null)
			listboxTasques.setModel(listmodel = new ListModelList(dades
					.getAll()));
	}

	public void setTasques(Collection agentsHead, Collection tasques,
					String ampleFinestra, String URLagent) {
		// Afegim la capçalera al renderer
		dataRenderer.setAgentsListhead(agentsHead);
		
		// Eliminem i reemplacem la capçalera del listbox (om du bara vill..)
		listboxTasques.getChildren().removeAll(listboxTasques.getHeads());
		Listhead cabecera = dataRenderer.getListheadAgents();
		listboxTasques.getChildren().add(cabecera);
		// Posem l'amplària mínima del listbox
		if (ampleFinestra !=null) {
			int ampleMinim = dataRenderer.getAmpleMinimListbox();
			String s_ampleListbox = listboxTasques.getWidth();
			if (ampleMinim !=0) { 
				try {
					//int ampleMinim = Integer.parseInt(s_ampleMinim.replaceAll("[a-z,A-Z]",""));
					// falla si és percentual
					int ampleListbox = s_ampleListbox == null || s_ampleListbox.endsWith("%") //$NON-NLS-1$
							? -1 
							: Integer.parseInt(s_ampleListbox.replaceAll("[a-z,A-Z]", "")); //$NON-NLS-1$ //$NON-NLS-2$
					if (ampleListbox < ampleMinim) {
						listboxTasques.setWidth(ampleMinim+"px"); //$NON-NLS-1$
						
					}
				} catch (Throwable th) {
					listboxTasques.setWidth("100%"); //$NON-NLS-1$
				}
				
			} else listboxTasques.setWidth("100%"); //$NON-NLS-1$
		}

		// Establim les tasques
		seyconTasksData.setUrlAgent(URLagent);
		seyconTasksData.setTasques(tasques);
		listmodel.clear();
		listmodel.addAll(seyconTasksData.getAll());
		// Afegim l'eventListener al header d'agents [és nou (!!)]
		if (cabecera != null && eventListenerHeader != null)
			getDataRenderer().addHeaderEventListener(tipusEventListenerHeader, eventListenerHeader);

	}
	
	public void setTasques(Collection agentsHead, Collection tasques, String urlAgent) {
		setTasques(agentsHead, tasques, null, urlAgent);
	}

	public SeyconTaskRenderer getDataRenderer() {
		return dataRenderer;
	}
	
	// Guardem el eventListener de la capçalera i el tipus d'event
	EventListener eventListenerHeader = null;
	String tipusEventListenerHeader = null;

	public EventListener getEventListenerHeader() {
		return eventListenerHeader;
	}

	public void setEventListenerHeader(String tipusEvent, EventListener eventListenerHeader) {
		this.tipusEventListenerHeader = tipusEvent; 
		this.eventListenerHeader = eventListenerHeader;
	}
	
	// Per defecte al fer click
	public void setEventListenerHeader(EventListener eventListenerHeader) {
		this.tipusEventListenerHeader = Events.ON_CLICK; 
		this.eventListenerHeader = eventListenerHeader;
	}

}
