package com.soffid.iam.web.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.ComponentNotFoundException;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Row;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.google.common.collect.Lists;
import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.AttributeVisibilityEnum;
import com.soffid.iam.api.Configuration;
import com.soffid.iam.api.CustomObjectType;
import com.soffid.iam.api.DataType;
import com.soffid.iam.api.MetadataScope;
import com.soffid.iam.service.ejb.ApplicationService;
import com.soffid.iam.service.ejb.AuthorizationService;
import com.soffid.iam.service.ejb.ConfigurationService;
import com.soffid.iam.utils.Security;
import com.soffid.iam.web.component.FrameHandler;
import com.soffid.iam.web.popup.CsvParser;
import com.soffid.iam.web.popup.Editor;
import com.soffid.iam.web.popup.ImportCsvHandler;
import com.soffid.iam.web.util.RemoveOnCloseStream;

import au.com.bytecode.opencsv.CSVWriter;
import es.caib.seycon.ng.comu.TypeEnumeration;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.component.DataTable;
import es.caib.zkib.component.ReorderEvent;
import es.caib.zkib.datamodel.DataNode;
import es.caib.zkib.datamodel.DataNodeCollection;
import es.caib.zkib.datasource.CommitException;
import es.caib.zkib.datasource.XPathUtils;
import es.caib.zkib.jxpath.JXPathContext;
import es.caib.zkib.jxpath.JXPathException;
import es.caib.zkib.zkiblaf.Missatgebox;

public class MetadataHandler extends FrameHandler implements AfterCompose {
	private boolean isMaster;
	private boolean canCreateMetadata;
	private boolean canUpdateMetadata;
	private boolean canDeleteMetadata;
	private boolean canQueryMetadata;
	private ConfigurationService configSvc;
	private Window objectAttributeWindow;

	public MetadataHandler() throws InternalErrorException {
		com.soffid.iam.api.Tenant masterTenant = com.soffid.iam.ServiceLocator.instance().getTenantService().getMasterTenant();

		isMaster = com.soffid.iam.utils.Security.getCurrentTenantName().equals ( masterTenant.getName() );
		canCreateMetadata = Security.isUserInRole("metadata:create");
		canUpdateMetadata = Security.isUserInRole("metadata:update");
		canDeleteMetadata = Security.isUserInRole("metadata:delete");
		canQueryMetadata = Security.isUserInRole("metadata:query");;
		
	}

	@Override
	public void setPage(Page p) {
		super.setPage(p);
		getNamespace().setVariable("isMaster", isMaster, true);
		getNamespace().setVariable("canCreateMetadata", canCreateMetadata, true);
		getNamespace().setVariable("canUpdateMetadata", canUpdateMetadata, true);
		getNamespace().setVariable("canDeleteMetadata", canDeleteMetadata, true);
		getNamespace().setVariable("canQueryMetadata", canQueryMetadata, true);
		getNamespace().setVariable("canModifyMetadata", canUpdateMetadata || canCreateMetadata, true);
	}
		
	public void afterCompose () {
		super.afterCompose();
		objectAttributeWindow = (Window) getFellow("objectAttributeWindow");
	}

	public void onChangeForm(Event event) {
		Boolean builtin = Boolean.TRUE;
		try {
			builtin = (Boolean) XPathUtils.getValue(getForm(), "@builtin" );
		} catch (Exception e) {}
		((Textbox)getFellow("objectTypeName")).setReadonly(builtin);
		((Textbox)getFellow("objectTypeDescription")).setReadonly(builtin);
	}
	
	public void importAttributeCsv () throws IOException, CommitException {
		getModel().commit();
		
		String[][] data = { 
				{"order", Labels.getLabel("dadesAddicionals.zul.Ordre")},
				{"name", Labels.getLabel("dadesAddicionals.zul.Codi-2")},
				{"label", Labels.getLabel("dadesAddicionals.zul.Label")},
				{"type", Labels.getLabel("dadesAddicionals.zul.Type")},
				{"dataObjectType", Labels.getLabel("dadesAddicionals.zul.CustomType")},
				{"required", Labels.getLabel("dadesAddicionals.zul.Required")},
				{"unique", Labels.getLabel("dadesAddicionals.zul.Unique")},
				{"multiValued", Labels.getLabel("dadesAddicionals.zul.Multivalued")},
				{"multiValuedRows", Labels.getLabel("dadesAddicionals.zul.MultivaluedRows")},
				{"size", Labels.getLabel("dadesAddicionals.zul.Size")},
				{"values", Labels.getLabel("dadesAddicionals.zul.Values")},
				{"adminVisibility", Labels.getLabel("dadesAddicionals.zul.AdminVisibility")},
				{"operatorVisibility", Labels.getLabel("dadesAddicionals.zul.OperatorVisibility")},
				{"userVisibility", Labels.getLabel("dadesAddicionals.zul.UserVisibility")},
				{"visibilityExpression", Labels.getLabel("dadesAddicionals.zul.VisibilityExpr")},
				{"validationExpression", Labels.getLabel("dadesAddicionals.zul.ValidExpr")},
				{"filterExpression", Labels.getLabel("dadesAddicionals.zul.FilterExpr")},
				{"onLoadTrigger", Labels.getLabel("dadesAddicionals.zul.onLoadTrigger")},
				{"onChangeTrigger", Labels.getLabel("dadesAddicionals.zul.onChangeTrigger")}
		};
		
		String title = Labels.getLabel("tenant.zul.import");
		ImportCsvHandler.startWizard(title, data, this, 
				parser -> importAttributeCsv(parser),
				false);
	}

	private void importAttributeCsv(CsvParser parser) throws UiException {
		Map<String,String> m = null;
		int updates = 0;
		int inserts = 0;
		int unchanged = 0;
		int removed = 0;
		try {
			DataNodeCollection coll = (DataNodeCollection) XPathUtils.getValue(getForm(), "/metadata");
			Set<String> names = new HashSet<String>();
			for ( Iterator<Map<String, String>> iterator = parser.iterator(); iterator.hasNext(); )
			{
				m = iterator.next();
				String name = m.get("name");
				if (name != null && ! name.trim().isEmpty())
				{
					DataType dt = findDataType (coll, name);
					for (String s: m.keySet())
					{
						String value = m.get(s);
						if (value != null && value.trim().isEmpty())
							value = null;
						if (s.equals("values") && value != null) {
							String[] array = value.split("[ ,]+");
							PropertyUtils.setProperty(dt, s, Lists.newArrayList(array));
						} else if (s.equals("type") && value != null) {
							dt.setType(TypeEnumeration.fromString(value));
						} else if (s.equals("adminVisibility") && value != null) {
							dt.setAdminVisibility(AttributeVisibilityEnum.fromString(value));
						} else if (s.equals("operatorVisibility") && value != null) {
							dt.setOperatorVisibility(AttributeVisibilityEnum.fromString(value));
						} else if (s.equals("userVisibility") && value != null) {
							dt.setUserVisibility(AttributeVisibilityEnum.fromString(value));
						} else if (value != null && !value.trim().isEmpty()) {
							BeanUtils.setProperty(dt, s, value);
						}
					}
					names.add(name);
				}
			}
	    	for (DataNode dataNode: (Collection<DataNode>)coll) {
	    		if ( !dataNode.isDeleted()) {
	    			DataType dt = (DataType) dataNode.getInstance();
	    			if (! names.contains(dt.getName()))
	    				dataNode.delete();
	    		}
	    	}
	    	getModel().commit();
	    	coll.refresh();
	    	Missatgebox.avis(Labels.getLabel("parametres.zul.import", new Object[] { updates, inserts, removed, unchanged }));
		} catch (UiException e) {
			throw e;
		} catch (Exception e) {
			if (m == null)
				throw new UiException(e);
			else
				throw new UiException("Error loading parameter "+m.get("name"), e);
		}

	}
	
    private DataType findDataType(DataNodeCollection coll, String name) {
    	for (DataNode dataNode: (Collection<DataNode>)coll) {
    		if ( !dataNode.isDeleted()) {
    			DataType dt = (DataType) dataNode.getInstance();
    			if (dt.getName().equals(name))
    				return dt;
    		}
    	}
    	DataType dt = new DataType();
    	coll.add(dt);
    	return dt;
	}

	public void displayRestrictions ()
    {
    	com.soffid.iam.api.MetadataScope scope = (MetadataScope) XPathUtils.getValue(
    			objectAttributeWindow.getFellow("form"), 
    			"scope");
    	if (scope == null)
    	{
	    	scope = (MetadataScope) XPathUtils.getValue(getForm(), "scope");
    	}
    			
    	boolean display = scope == com.soffid.iam.api.MetadataScope.USER;
    	objectAttributeWindow.getFellow ("visibility1").setVisible(display);
    	objectAttributeWindow.getFellow ("visibility2").setVisible(display);
    	objectAttributeWindow.getFellow ("visibility3").setVisible(display);
    	objectAttributeWindow.getFellow ("visibility4").setVisible(true);
    	objectAttributeWindow.getFellow ("visibility5").setVisible(true);
    	es.caib.seycon.ng.comu.TypeEnumeration type = (TypeEnumeration) XPathUtils.getValue(
    			objectAttributeWindow.getFellow("form"), 
    			"type");
    	objectAttributeWindow.getFellow ("visibility6").setVisible( type == es.caib.seycon.ng.comu.TypeEnumeration.GROUP_TYPE ||
    			type == es.caib.seycon.ng.comu.TypeEnumeration.USER_TYPE ||
    			type == es.caib.seycon.ng.comu.TypeEnumeration.APPLICATION_TYPE ||
    			type == es.caib.seycon.ng.comu.TypeEnumeration.CUSTOM_OBJECT_TYPE);
    }
    
	public void displayValues ()
	{
			DataTable ds = (DataTable) getFellow("attributes-listbox"); 
			JXPathContext ctx = ds.getJXPathContext(); 
			DataType registre = (DataType) ((DataNode) ctx.getValue("/")).getInstance();
			Row valuesRow = (Row) objectAttributeWindow.getFellow("valuesRow");
			if (registre.getType() == null ||
					registre.getType().equals (es.caib.seycon.ng.comu.TypeEnumeration.STRING_TYPE))
				valuesRow.setVisible(true);
			else
				valuesRow.setVisible(false);
	}
	
	void onSelectAttribute()
	{
		try {
			es.caib.zkib.binder.BindContext ctxO = getForm(); 
			es.caib.zkib.binder.BindContext ctxA = XPathUtils.getComponentContext(objectAttributeWindow.getFellow("form"));
			
			displayValues();
			displayRestrictions ();

			com.soffid.iam.api.CustomObjectType type = (CustomObjectType) ((DataNode)XPathUtils.getValue(ctxO, "/")).getInstance();
			DataType registre = (DataType) ((DataNode)XPathUtils.getValue(ctxA, "/")).getInstance();

			if (registre.getScope() == null)
			{
				registre.setScope(type.getScope());
				registre.setCustomObjectType(type.getName());
			}
			
			objectAttributeWindow.setSclass("displayedFromRight");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void closeAttribute()
	{
		objectAttributeWindow.setSclass("hideRight");
	}

	void tryToRemoveAttribute(Component c)
	{
		es.caib.zkib.binder.BindContext bindCtx = XPathUtils.getComponentContext(c);
		String attName = (String) XPathUtils.getValue(bindCtx, "@codi");
		String message = String.format(org.zkoss.util.resource.Labels.getLabel("dadesAddicionals.zul.RemoveAtt"),
				new Object[]{attName});
		Missatgebox.confirmaOK_CANCEL(message,
				(evt) -> {
					if ("onOK".equals(evt.getName()))
					{
						XPathUtils.removePath(bindCtx.getDataSource(), bindCtx.getXPath());
					}
				}
		);
	}

	void tryToRemoveObject(Component c)
	{
		es.caib.zkib.binder.BindContext bindCtx = XPathUtils.getComponentContext(c);
		String attName = (String) XPathUtils.getValue(bindCtx, "@name");
		String message = String.format(org.zkoss.util.resource.Labels.getLabel("dadesAddicionals.zul.RemoveAtt"),
				new Object[]{attName});
		Missatgebox.confirmaOK_CANCEL(message,
				evt -> 
				{
					if ("onOK".equals(evt.getName()))
					{
						XPathUtils.removePath(bindCtx.getDataSource(), bindCtx.getXPath());
						getModel().commit();
					}
				}
		);
	}

	public void addNewAttribute(Event event) {
		Component lb = getFellow("metadataGrid");
		if (lb instanceof DataTable)
		{
			DataNodeCollection coll = (DataNodeCollection) XPathUtils.getValue(getForm(), "/metadata");
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
			((DataTable) lb).addNew();

			XPathUtils.setValue(lb, "@order", next);
			
			objectAttributeWindow.doHighlighted();
		}
	}
	
	public void onChangeMultipleValues() {
		Component lb = getFellow("metadataGrid");
    	Boolean multivalue = (Boolean) XPathUtils.getValue(lb,"multiValued");
    	objectAttributeWindow.getFellow("multiRowRow").setVisible(Boolean.TRUE.equals( multivalue));
	}

	public void onChangeDataType() {
		Component lb = getFellow("metadataGrid");
    	TypeEnumeration type = (TypeEnumeration) XPathUtils.getValue(lb,"type");
    	objectAttributeWindow.getFellow("visibility6").setVisible( type == es.caib.seycon.ng.comu.TypeEnumeration.GROUP_TYPE ||
    			type == es.caib.seycon.ng.comu.TypeEnumeration.USER_TYPE);
	}
	
	public void addValue(Event event) throws Exception {
		es.caib.zkib.binder.BindContext ctx = XPathUtils.getComponentContext(event.getTarget());
		XPathUtils.createPath(ctx.getDataSource(), ctx.getXPath(), new String());
	}

	public void applyAttribute(Event event) throws CommitException {
		getModel().commit();
		objectAttributeWindow.setVisible(false);
	}

	public void undoAttribute(Event event) throws Exception {
		DataNodeCollection coll = (DataNodeCollection) XPathUtils.getValue(getForm(), "/metadata");
		coll.refresh();
		objectAttributeWindow.setVisible(false);
	}

	public void showAttributeDetails() {
		objectAttributeWindow.doHighlighted();
	}
	
	public void onChangeAttributeForm(Event event) {
		try {
			onChangeDataType();
			onChangeMultipleValues();
		} catch (JXPathException e) {}
	}
	
	public void reorder (ReorderEvent event) {
		DataNodeCollection collection = (DataNodeCollection) XPathUtils.getValue(getForm(), "/metadata");
		
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

	public void downloadAttributeCsv(Event event) throws IOException {
		File f = File.createTempFile("export", ".csv");
		CSVWriter w = new CSVWriter( new OutputStreamWriter(new FileOutputStream(f), "UTF-8"));
		w.writeNext(new String[] { 
			Labels.getLabel("dadesAddicionals.zul.Ordre"),
			Labels.getLabel("dadesAddicionals.zul.Codi-2"),
			Labels.getLabel("dadesAddicionals.zul.Label"),
			Labels.getLabel("dadesAddicionals.zul.Type"),
			Labels.getLabel("dadesAddicionals.zul.CustomType"),
			Labels.getLabel("dadesAddicionals.zul.Required"),
			Labels.getLabel("dadesAddicionals.zul.Unique"),
			Labels.getLabel("dadesAddicionals.zul.Multivalued"),
			Labels.getLabel("dadesAddicionals.zul.MultivaluedRows"),
			Labels.getLabel("dadesAddicionals.zul.Size"),
			Labels.getLabel("dadesAddicionals.zul.Values"),
			Labels.getLabel("dadesAddicionals.zul.AdminVisibility"),
			Labels.getLabel("dadesAddicionals.zul.OperatorVisibility"),
			Labels.getLabel("dadesAddicionals.zul.UserVisibility"),
			Labels.getLabel("dadesAddicionals.zul.VisibilityExpr"),
			Labels.getLabel("dadesAddicionals.zul.ValidExpr"),
			Labels.getLabel("dadesAddicionals.zul.FilterExpr"),
			Labels.getLabel("dadesAddicionals.zul.onLoadTrigger"),
			Labels.getLabel("dadesAddicionals.zul.onChangeTrigger")
		});
		
		DataNodeCollection collection = (DataNodeCollection) XPathUtils.getValue(getForm(), "/metadata");
		for (int i = 0; i < collection.size(); i++) {
			DataNode target = (DataNode) collection.get(i);
			if ( ! target.isDeleted()) {
				DataType td = (DataType) target.getInstance();
				w.writeNext(new String[] {
						stringify(td.getOrder()),
						stringify(td.getName()),
						stringify(td.getLabel()),
						stringify(td.getType()),
						stringify(td.getDataObjectType()),
						td.isRequired()?"true":"false",
						stringify(td.getUnique()),
						stringify(td.getMultiValuedRows()),
						stringify(td.getMultiValuedRows()),
						stringify(td.getSize()),
						stringify(td.getValues()),
						stringify(td.getAdminVisibility()),
						stringify(td.getOperatorVisibility()),
						stringify(td.getUserVisibility()),
						stringify(td.getVisibilityExpression()),
						stringify(td.getValidationExpression()),
						stringify(td.getFilterExpression()),
						stringify(td.getOnLoadTrigger()),
						stringify(td.getOnChangeTrigger()),
						stringify(td.getOnFocusTrigger())
				});
			
			}
		}
		w.close();
		
		Filedownload.save(new RemoveOnCloseStream(f), "text/csv", XPathUtils.getValue(getForm(), "name")+"-metadata.csv");
	}

	private String stringify(Object o) {
		if (o == null) return null;
		else if (o instanceof Collection) {
			String r = null;
			for ( String oo: (Collection<String>) o ) {
				if (r == null) r = oo;
				else r = r +", "+oo;
			}
			return r;
		}
		else return o.toString();
	}

	public void deleteAttribute (Event event) {
		final DataTable metadataGrid = (DataTable) getFellow("metadataGrid");
		final Window metadataWindow = (Window) getFellow("objectAttributeWindow");
		
		Missatgebox.confirmaOK_CANCEL(org.zkoss.util.resource.Labels.getLabel("agents.DeleteAgent"),
				org.zkoss.util.resource.Labels.getLabel("process.warning"),
					(evt) -> {
						if ("onOK".equals(evt.getName())) {
							metadataGrid.delete();
							metadataWindow.setVisible(false);
						}
					});
	}
	
	public void editScript(Event event) throws ComponentNotFoundException, InternalErrorException, NamingException, CreateException {
		Editor.edit((Textbox) event.getTarget().getPreviousSibling(),
				new com.soffid.iam.web.agent.ScriptEnviroment().getUserAttributeValidationVars(null));
	}
	

}

class OrderComparator implements Comparator<DataType>
{

	@Override
	public int compare(DataType o1, DataType o2) {
		return o1.getOrder().compareTo(o2.getOrder());
	}
	
}
