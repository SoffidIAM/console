package com.soffid.iam.web.agent;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.ComponentNotFoundException;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Div;
import org.zkoss.zul.Window;

import com.soffid.iam.utils.AutoritzacionsUsuari;

import es.caib.seycon.ng.comu.TypeEnumeration;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.component.DataTable;
import es.caib.zkib.datamodel.DataNodeCollection;
import es.caib.zkib.datasource.XPathUtils;
import es.caib.zkib.zkiblaf.Missatgebox;

public class MetadataHandler extends Div implements AfterCompose{
	private DataTable metadataGrid;
	private Window metadataWindow;

	@Override
	protected void addMoved(Component oldparent, Page oldpg, Page newpg) {
		super.addMoved(oldparent, oldpg, newpg);
		boolean canModifyMetadata = AutoritzacionsUsuari.hasUpdateMetadata();
		if (newpg != null)
			newpg.setVariable("canModifyMetadata", canModifyMetadata);
	}

	public void addNew (Event event) {
		metadataGrid.addNew();
		metadataWindow.doHighlighted();
	}

	public void showDetails (Event event) {
		metadataWindow.doHighlighted();
	}

	@Override
	public void afterCompose() {
		metadataGrid = (DataTable) getFellow("metadataGrid");
		metadataWindow = (Window) getFellow("metadataWindow");
	}


	public void apply () {
		metadataGrid.commit();
		metadataWindow.setVisible(false);
	}

	public void undo () throws Exception {
		
		DataNodeCollection coll = (DataNodeCollection) metadataGrid.getDataSource().getJXPathContext().getValue(metadataGrid.getXPath());
		coll.refresh();
		metadataWindow.setVisible(false);
	}
	
	public void editScript(Event event) throws ComponentNotFoundException, InternalErrorException, NamingException, CreateException {
	    Events.sendEvent(new Event ("onEdit", 
	    		getDesktop().getPage("editor").getFellow("top"),
	    		new Object[] {
					    event.getTarget().getPreviousSibling(),
					    new com.soffid.iam.web.agent.ScriptEnviroment().getUserAttributeValidationVars(null)
				}
	    ));
	}
	
	public void onChangeDataType(Event event) {
    	TypeEnumeration type = (TypeEnumeration) XPathUtils.getValue(event.getTarget().getParent(),"type");
    	event.getTarget().getFellow ("visibility6").setVisible( type == es.caib.seycon.ng.comu.TypeEnumeration.GROUP_TYPE ||
    			type == es.caib.seycon.ng.comu.TypeEnumeration.USER_TYPE);
	}
	
	public void delete (Event event) {
		Missatgebox.confirmaOK_CANCEL(org.zkoss.util.resource.Labels.getLabel("agents.DeleteAgent"),
				org.zkoss.util.resource.Labels.getLabel("process.warning"),
					(evt) -> {
						if ("onOK".equals(evt.getName())) {
							metadataGrid.delete();
							metadataWindow.setVisible(false);
						}
					});
	}
}
