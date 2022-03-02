package com.soffid.iam.web.agent;

import java.io.IOException;
import java.util.Comparator;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.ComponentNotFoundException;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Div;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.ServiceLocator;
import com.soffid.iam.api.DataType;
import com.soffid.iam.api.MetadataScope;
import com.soffid.iam.utils.AutoritzacionsUsuari;
import com.soffid.iam.web.component.FrameHandler;
import com.soffid.iam.web.popup.Editor;

import es.caib.seycon.ng.comu.TypeEnumeration;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.component.DataTable;
import es.caib.zkib.component.ReorderEvent;
import es.caib.zkib.datamodel.DataNode;
import es.caib.zkib.datamodel.DataNodeCollection;
import es.caib.zkib.datasource.CommitException;
import es.caib.zkib.datasource.XPathUtils;
import es.caib.zkib.zkiblaf.Missatgebox;

public class MetadataHandler extends FrameHandler implements AfterCompose{
	public MetadataHandler() throws InternalErrorException {
		super();
	}

	private DataTable metadataGrid;
	private Window metadataWindow;
	private boolean canModifyMetadata;

	@Override
	protected void addMoved(Component oldparent, Page oldpg, Page newpg) {
		super.addMoved(oldparent, oldpg, newpg);
		canModifyMetadata = AutoritzacionsUsuari.hasUpdateMetadata();
		if (newpg != null)
			newpg.setVariable("canModifyMetadata", canModifyMetadata);
	}

	public void addNew (Event event) {
		Component lb = getFellow("metadataGrid");
		if (lb instanceof DataTable)
		{
			DataNodeCollection coll = (DataNodeCollection) XPathUtils.getValue(this, "/metadata");
			long next = 1;
			for ( int i = 0; i < coll.getSize(); i++) {
				DataNode dn = (DataNode) coll.get(i);
				if (!dn.isDeleted())
				{
					DataType dt = (DataType) dn.getInstance();
					if (dt.getOrder() != null && dt.getOrder().longValue() >= next)
						next = dt.getOrder().longValue()+1;
				}
			}
			metadataGrid.addNew();

			XPathUtils.setValue(lb, "@order", next);
			
			metadataWindow.doHighlighted();
		}
	}

	public void showDetails (Event event) {
		metadataWindow.doHighlighted();
		onChangeMultipleValues();
		onChangeDataType();
	}

	@Override
	public void afterCompose() {
		metadataGrid = (DataTable) getFellow("metadataGrid");
		metadataWindow = (Window) getFellow("metadataWindow");
	}


	public void apply (Event event) {
		metadataGrid.commit();
		metadataGrid.setSelectedIndex(-1);
		metadataWindow.setVisible(false);
	}

	public void undo () throws Exception {
		
		DataNodeCollection coll = (DataNodeCollection) metadataGrid.getDataSource().getJXPathContext().getValue(metadataGrid.getXPath());
		coll.refresh();
		metadataWindow.setVisible(false);
	}
	
	public void editScript(Event event) throws ComponentNotFoundException, InternalErrorException, NamingException, CreateException, IOException {
		Editor.edit((Textbox) event.getTarget().getPreviousSibling(),
				new com.soffid.iam.web.agent.ScriptEnviroment().getUserAttributeValidationVars(null));
	}
	
	public void onChangeDataType() {
		TypeEnumeration type = (TypeEnumeration) XPathUtils.getValue(getFellow("metadataGrid"),"type");
    	getFellow("metadataWindow").getFellow ("visibility6").setVisible( type == es.caib.seycon.ng.comu.TypeEnumeration.GROUP_TYPE ||
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
	
	public void addValue(Event event) throws Exception {
		if (canModifyMetadata) {
			es.caib.zkib.binder.BindContext ctx = XPathUtils.getComponentContext(event.getTarget());
			XPathUtils.createPath(ctx.getDataSource(), ctx.getXPath(), new String());
		}
	}
	
	public void removeValue(Event event) {
		if (canModifyMetadata) {
			es.caib.zkib.binder.BindContext ctx = XPathUtils.getComponentContext(event.getTarget());
			XPathUtils.removePath(ctx.getDataSource(), ctx.getXPath());
		}
	}
	
	public void onChangeMultipleValues() {
		Component lb = getFellow("metadataGrid");
    	Boolean multivalue = (Boolean) XPathUtils.getValue(lb,"multiValued");
    	getFellow("metadataWindow").getFellow("multiRowRow").setVisible(Boolean.TRUE.equals( multivalue));
	}

	
	public void reorder (ReorderEvent event) {
		DataNodeCollection collection = (DataNodeCollection) XPathUtils.getValue(this, "/metadata");
		
		DataNode src = (DataNode) event.getSrcObject();
		DataType srcDatatype = (DataType) src.getInstance();
		long order = 1;
		for (int i = 0; i < collection.size(); i++) {
			DataNode target = (DataNode) collection.get(i);
			if ( ! target.isDeleted() && target != src) {
				DataType datatype = (DataType) target.getInstance();
				if (target == event.getInsertBeforeObject()) {
					srcDatatype.setOrder(order++);
					src.update();
				}
				datatype.setOrder(order++);
				target.update();
			}
		}
		if (event.getInsertBeforeObject() == null) {
			srcDatatype.setOrder(order++);
			src.update();
		}
		collection.sort(new OrderComparator());
		
	}

	@Override
	public void onPageAttached(Page newpage, Page oldpage) {
		super.onPageAttached(newpage, oldpage);
		try {
			newpage.setVariable("js_template", new com.soffid.iam.web.agent.ScriptEnviroment().getUserAttributeValidationVars(null));
		} catch (InternalErrorException | NamingException | CreateException | IOException e) {
			throw new UiException("Error generating javascript template", e);
		}
	}
	
}

class OrderComparator implements Comparator<DataType>
{

	@Override
	public int compare(DataType o1, DataType o2) {
		return o1.getOrder().compareTo(o2.getOrder());
	}
	
}
