package com.soffid.iam.ui;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.SyncAgentTaskLog;
import com.soffid.iam.ui.SeyconTask.Estat;

import es.caib.zkib.events.SerializableEventListener;
import es.caib.zkib.zkiblaf.ImageClic;
import es.caib.zkib.zkiblaf.Missatgebox;

public class SeyconTaskRenderer implements ListitemRenderer {
	
	private Collection<String> agentsHeader = null;
	
	private final int AMPLE_MINIM_CEL_LA = 70; //en pixels
	
	private Listhead listheadAgents = null;
	
	public SeyconTaskRenderer() {
		this.agentsHeader = null;
		// construim capçalera buida
		listheadAgents = buildListHeadAgents();
	}

	public SeyconTaskRenderer(Collection<String> agentsHeader) {
		this.agentsHeader = agentsHeader;
		
		// Construim la capçalera per als agents
		listheadAgents = buildListHeadAgents();
	}
	
	public void render(Listitem item, Object data) throws Exception {
		item.setValue(data);
		doRender(item, data);
	}

	protected void doRender(final Listitem item, Object data) throws Exception {
		final SeyconTask t = (SeyconTask) data;
		Listcell lc = new Listcell(t.getDescripcioTasca());
		lc.setParent(item);
		ImageClic icCancel = new ImageClic("/img/stop.png");
		icCancel.setTitle("Cancel task");
		icCancel.setParent(lc);
		icCancel.setStyle("width: 16px; float:right");
		icCancel.setVariable("Title", t.getDescripcioTasca(), true);
		icCancel.setVariable("id", t.getId(), true);
		icCancel.addEventListener("onClick", new SerializableEventListener() {
			public void onEvent(Event event) throws Exception {
				Missatgebox.confirmaOK_CANCEL(String.format("Do you really want to cancel task %s?", t.getDescripcioTasca()),
						"Cancel task",
						new EventListener() {
							
							public void onEvent(Event event) throws Exception {
								if (event.getData().equals( Missatgebox.OK) )
								{
									EJBLocator.getSyncServerService()
										.cancelTask(t.getId());
									item.setParent(null);
								}
							}
						});
			}
		});
		String [] estatExecucio = t.getEstatExecucioAgents();
		if (estatExecucio == null) {
			// Posem l'estat com a desconegut 
			for (int i=0; i < getNumAgents(); i++) {
				Listcell c = new Listcell();
				c.setLabel(Estat.UNKNOWN.toString());
				c.setParent(item);
			}
		} else {
			// Iterem per la capçalera.. per no entrar en error
			for (int i = 0; i < getNumAgents(); i++) {
                if (i >= estatExecucio.length) {
                    Listcell c = new Listcell();
                    c.setSclass("seycontask " + Estat.UNKNOWN);
                    c.setLabel(Estat.UNKNOWN);
                    c.setParent(item);
                } else {
                    Listcell c = new Listcell();
                    c.setLabel(estatExecucio[i]);
                    c.setSclass("seycontask " + estatExecucio[i]);
                    c.setParent(item);
                    if (estatExecucio[i].equals(Estat.ERROR) && t.getUrlAgent() != null) {
                        c.setLabel(estatExecucio[i] + "*");
                        String taskAgent = this.agentsHeader.toArray()[i].toString();
                        Iterator<SyncAgentTaskLog> taskIterator = EJBLocator.getSyncServerService().getAgentTasks(t.getUrlAgent(), taskAgent).iterator();
                        while (taskIterator.hasNext()) {
                            com.soffid.iam.api.SyncAgentTaskLog log = taskIterator.next();
                            if (log.getTaskId().equals(t.getId())) {
                                if (log.getStackTrace() != null && log.getStackTrace().length() > 0) c.setTooltiptext(formatStackTrace(log.getStackTrace())); else c.setTooltiptext("");
                                c.setStyle("cursor: help;");
                            }
                        }
                    }
                }
            }
		}
	}
	
	
	/**
	 * @param stackTrace
	 * @return
	 */
	private String formatStackTrace (String stackTrace)
	{
		String msg = null;
		int pos = stackTrace.indexOf("\n\t"); //$NON-NLS-1$
		
		if (pos != -1)
		{
			msg = stackTrace.substring(0, pos);
		}
		
		return msg;
	}

	/**
	 * Obtenim el número de columnes del listbox actual
	 * @return
	 */
	public int getNumAgents() {
		return agentsHeader != null ? agentsHeader.size() : 0;
	}
	
	/**
	 * Construix la capçalera del listhead amb el nom dels agents
	 * @return
	 */
	private Listhead buildListHeadAgents() {
		Listhead lh= new Listhead();
		lh.setSclass("headcentrat agentcss"); // la centrem //$NON-NLS-1$
		
		/*Integer ampleFinestra = null;
		
		// Obtenim amplària de la finestra des del atribut de sessió
		try {
			// Això és dependent del SEU (!!)
			org.zkoss.zk.ui.Session sessio = org.zkoss.zk.ui.Sessions.getCurrent();
			
			if (sessio != null) {ampleFinestra = (Integer) sessio.getAttribute("ample");}			
		} catch (Throwable th) {
			ampleFinestra = null;			
		}*/
		
		int ampleMinim = -1; //desactivat per defecte
		
		if (agentsHeader != null) {

			// Es calcula l'ample de cada cel·la
			int ample = (int) (100 / (getNumAgents()+3));
			
			// Mirem si arribem a l'ample mínim establert a la constant
			// AMPLE_MINIM_CEL_LA
			/*if (ampleFinestra!=null) {
				ampleMinim = (int) ( (ampleFinestra - 80) / (getNumAgents()+2));
				if (ampleMinim < AMPLE_MINIM_CEL_LA) ampleMinim = ampleMinimCelda;
			} */

			
			String ampleDescripcioTasca = ampleMinim !=-1 ? (ampleMinim*3)+"px" : ample*3+"%"; //$NON-NLS-1$ //$NON-NLS-2$
			Listheader tasca = new Listheader(Messages.getString("SeyconTaskRenderer.TaskHeader"), null, ampleDescripcioTasca);   //$NON-NLS-1$
			tasca.setParent(lh);
			tasca.setSort("auto"); //Permitim ordenar //$NON-NLS-1$
			//new Listheader("Tasca",null).setParent(lh);
			
			String ampleEstatAgent = ampleMinim != -1 ? ampleMinim+"px" : ample+"%"; //$NON-NLS-1$ //$NON-NLS-2$
			
			for (Iterator<String> it = agentsHeader.iterator(); it.hasNext();) {
				Listheader agent = new Listheader(it.next(), null, ampleEstatAgent);
				agent.setTooltiptext(agent.getLabel()); //Posem tooltip
				agent.setParent(lh);
			}
		}
		
		return lh;
	}

	public Listhead getListheadAgents() {
		return listheadAgents;
	}
	
	public void setAgentsListhead(Collection agentsHeader) {
		this.agentsHeader = agentsHeader;
		
		// Construim la capçalera
		listheadAgents = buildListHeadAgents();
	}

	// En aquest valer tenim l'ample minim en pixels
	// que ha de tindre el listbox per representar les cel·les
	// amb una mida que supere o iguale al valor de
	// AMPLE_MINIM_CEL_LA
	public int getAmpleMinimListbox() {
		// Li donem 2 cel·les per a la descripció
		return agentsHeader != null ? (getNumAgents()+3)* AMPLE_MINIM_CEL_LA : 0;
	}
	
	public void addHeaderEventListener(String evento, EventListener eventListener) {
		if (listheadAgents!=null) {
			List fills = listheadAgents.getChildren();
			if (fills!=null) for (Iterator it = fills.iterator(); it.hasNext(); ) {
				Object f = it.next();
				if (f instanceof  Listheader) {
					((Listheader) f).addEventListener(evento, eventListener);
				}
			}
		}
	}
}
