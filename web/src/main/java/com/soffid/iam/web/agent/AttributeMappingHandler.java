package com.soffid.iam.web.agent;

import java.io.IOException;
import java.util.Base64;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.ComponentNotFoundException;
import org.zkoss.zk.ui.HtmlBasedComponent;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Row;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.ObjectMappingProperty;
import com.soffid.iam.api.SoffidObjectType;
import com.soffid.iam.api.System;
import com.soffid.iam.service.ejb.DispatcherService;
import com.soffid.iam.sync.engine.intf.GetObjectResults;
import com.soffid.iam.web.component.TreeCollapse;
import com.soffid.iam.web.popup.Editor;

import es.caib.seycon.ng.comu.AttributeDirection;
import es.caib.seycon.ng.comu.SoffidObjectTrigger;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.binder.BindContext;
import es.caib.zkib.component.DataGrid;
import es.caib.zkib.component.DataModel;
import es.caib.zkib.component.DataTree2;
import es.caib.zkib.datamodel.DataNode;
import es.caib.zkib.datasource.DataSource;
import es.caib.zkib.datasource.XPathUtils;
import es.caib.zkib.jxpath.JXPathContext;
import es.caib.zkib.zkiblaf.ImageClic;
import es.caib.zkib.zkiblaf.Missatgebox;

public class AttributeMappingHandler extends DataGrid {
	public void onNewObjectMapping (Event event) {
		Row r = (Row) event.getData();
		
		DataSource model = (DataModel) Path.getComponent("/model");
		BindContext ctx = XPathUtils.getComponentContext( getParent() );
		DataNode dn = (DataNode) XPathUtils.getValue(ctx, ".");
		
		System system = (System) dn.getInstance();
		String value= system.getClassName();

		Div triggersBlock = (Div) r.getFirstChild().getFellowIfAny("triggersBlock"); 
		Div methodsBlock = (Div) r.getFirstChild().getFellowIfAny("methodsBlock");
		
		if (methodsBlock != null) {
			methodsBlock.setVisible(false);
			Map pageScope = (Map) getPage().getVariable("pageScope");
			if (pageScope != null) {
				String[][] methodDescriptor = (String[][]) pageScope.get("methodDescriptor");
				if (methodDescriptor != null) {
					methodsBlock.setVisible( true);
				}
			}
		}

		if (triggersBlock != null) {
			try {
				Boolean triggers = (Boolean) XPathUtils.getValue(model, "/plugin[className='"+value+"']/@enableObjectTriggers");
				triggersBlock.setVisible( Boolean.TRUE.equals(triggers)); 
			}
			catch (Exception e) { 
				triggersBlock.setVisible( false ); 
			}
		}
		
		String filterObjectType = (String) getVariable("objectType", false);
		if (filterObjectType != null) {
			SoffidObjectType type = (SoffidObjectType) XPathUtils.getValue(r, "@soffidObject");
			if ( type == null)
				XPathUtils.setValue(r, "@soffidObject", SoffidObjectType.fromString(filterObjectType));
			else if ( ! filterObjectType.equals(type.toString()))
				r.setVisible(false);
		}
	}

	public void addObject(Event event) throws Exception {
		BindContext ctx = XPathUtils.getComponentContext(this);
		String path = XPathUtils.createPath(ctx.getDataSource(), "/objectMapping");
		String filterObjectType = (String) getVariable("objectType", false);
		if (filterObjectType != null)
			XPathUtils.setValue(ctx.getDataSource(), path+"/soffidObject",
				SoffidObjectType.fromString(filterObjectType));
	}
	
	public void removeObject(Event event) {
		es.caib.zkib.binder.BindContext bindCtx = XPathUtils.getComponentContext (event.getTarget());
		XPathUtils.removePath (bindCtx.getDataSource(), bindCtx.getXPath());
	}
	
	public void createTrigger(Event event) throws Exception {
		es.caib.zkib.binder.BindContext bindCtx = XPathUtils.getComponentContext(event.getTarget());							                                   
		XPathUtils.createPath(bindCtx.getDataSource(), bindCtx.getXPath());
	}
	
	public void foldUnfold (Event event) {
		TreeCollapse treeCollapse = (TreeCollapse) event.getTarget();
		treeCollapse.setOpen( ! treeCollapse.isOpen() );
		Component labelDiv = event.getTarget().getNextSibling();
		Component contentDiv = labelDiv.getNextSibling();
		if ( ! treeCollapse.isOpen() )
		{
			contentDiv.setVisible(false);
			labelDiv.setVisible(true);
		} else {
			contentDiv.setVisible(true);
			labelDiv.setVisible(false);
		}
	}
	
	public void createProperty (Event event) throws Exception {
		es.caib.zkib.binder.BindContext bindCtx = XPathUtils.getComponentContext(event.getTarget());							                                   
		XPathUtils.createPath(bindCtx.getDataSource(), bindCtx.getXPath());
	}
	
	public void focusTextarea(Event event) {
		Textbox self = (Textbox) event.getTarget();
		String v = self.getValue();
		if (v.length() > 50 || v.indexOf ('\n') >= 0)
		{
			self.setMultiline(true);
			self.setHeight("5em");
			self.setStyle("resize: vertical");
		}
	}
	
	public void removeProperty (Event event) {
		es.caib.zkib.binder.BindContext bindCtx = XPathUtils.getComponentContext (event.getTarget());
		XPathUtils.removePath (bindCtx.getDataSource(), bindCtx.getXPath());
	}
	
	public void editAttribute (final Event event) throws ComponentNotFoundException, InternalErrorException, NamingException, CreateException, IOException {
		Editor.edit((Textbox) event.getTarget().getPreviousSibling(),
					    new com.soffid.iam.web.agent.ScriptEnviroment().getSystemVars(event.getTarget()));
	}
	
	public void editSoffidAttribute (final Event event) throws ComponentNotFoundException, InternalErrorException, NamingException, CreateException, IOException {
		Editor.edit((Textbox) event.getTarget().getPreviousSibling(),
					    new com.soffid.iam.web.agent.ScriptEnviroment().getSoffidVars(event.getTarget()));
	}

	public void displayTestRow (Event event)
	{
		Component c = event.getTarget();
		SoffidObjectType type = (SoffidObjectType) XPathUtils.getValue(c, "@soffidObject");
		c.getFellow("testRowLabel1").setVisible(true);
		c.getFellow("testRowTextbox1").setVisible(true);						
		((Textbox)c.getFellow("testRowTextbox1")).setValue("");						
		((Textbox)c.getFellow("testRowTextbox2")).setValue("");						
		c.getFellow("testRowButton1").setVisible(false);
		Component b2 = c.getFellowIfAny("testRowButton2");
		Component b3 = c.getFellowIfAny("testRowButton3");
		Component b4 = c.getFellowIfAny("testRowButton4");
		Component b5 = c.getFellowIfAny("testRowButton5");
		if (b2 != null) b2.setVisible(true);						
		if (b3 != null) b3.setVisible(true);						
		if (b4 != null) b4.setVisible(true);						
		if (b5 != null) b5.setVisible(true);						
		if (type.equals(SoffidObjectType.OBJECT_ACCOUNT))
		{
			((Label)c.getFellow("testRowLabel1")).setValue("Account: ");
			c.getFellow("testRowLabel2").setVisible(false);						
			c.getFellow("testRowTextbox2").setVisible(false);						
		}
		else if (type.equals(SoffidObjectType.OBJECT_ALL_GRANTED_GROUP) ||
				type.equals(SoffidObjectType.OBJECT_GRANTED_GROUP))
		{
			((Label)c.getFellow("testRowLabel1")).setValue("Account: ");
			((Label)c.getFellow("testRowLabel2")).setValue("Group: ");
			c.getFellow("testRowLabel2").setVisible(true);						
			c.getFellow("testRowTextbox2").setVisible(true);						
		}
		else if (type.equals(SoffidObjectType.OBJECT_ALL_GRANTED_ROLES) ||
				type.equals(SoffidObjectType.OBJECT_GRANTED_ROLE))
		{
			((Label)c.getFellow("testRowLabel1")).setValue("Account: ");
			((Label)c.getFellow("testRowLabel2")).setValue("Role: ");
			c.getFellow("testRowLabel2").setVisible(true);						
			c.getFellow("testRowTextbox2").setVisible(true);						
		}
		else if (type.equals(SoffidObjectType.OBJECT_GRANT))
		{
			((Label)c.getFellow("testRowLabel1")).setValue("Account: ");
			((Label)c.getFellow("testRowLabel2")).setValue("Role / Group: ");
			c.getFellow("testRowLabel2").setVisible(true);						
			c.getFellow("testRowTextbox2").setVisible(true);						
		}
		else if (type.equals(SoffidObjectType.OBJECT_GROUP))
		{
			((Label)c.getFellow("testRowLabel1")).setValue("Group: ");
			c.getFellow("testRowLabel2").setVisible(false);						
			c.getFellow("testRowTextbox2").setVisible(false);						
		}
		else if (type.equals(SoffidObjectType.OBJECT_MAIL_LIST))
		{
			((Label)c.getFellow("testRowLabel1")).setValue("Name: ");
			((Label)c.getFellow("testRowLabel2")).setValue("Domain: ");
			c.getFellow("testRowLabel2").setVisible(true);						
			c.getFellow("testRowTextbox2").setVisible(true);						
		}
		else if (type.equals(SoffidObjectType.OBJECT_ROLE))
		{
			((Label)c.getFellow("testRowLabel1")).setValue("Role: ");
			c.getFellow("testRowLabel2").setVisible(false);						
			c.getFellow("testRowTextbox2").setVisible(false);						
		}
		else if (type.equals(SoffidObjectType.OBJECT_USER))
		{
			((Label)c.getFellow("testRowLabel1")).setValue("User: ");
			c.getFellow("testRowLabel2").setVisible(false);
			c.getFellow("testRowTextbox2").setVisible(false);						
		}
		else if (type.equals(SoffidObjectType.OBJECT_CUSTOM))
		{
			String t = (String) XPathUtils.getValue(c, "@soffidCustomObject");
			((Label)c.getFellow("testRowLabel1")).setValue("Object type: ");
			((Textbox)c.getFellow("testRowTextbox1")).setValue (t); 
			c.getFellow("testRowLabel2").setVisible(true);						
			c.getFellow("testRowTextbox2").setVisible(true);						
			((Label)c.getFellow("testRowLabel2")).setValue("Object name: ");						
			((Textbox)c.getFellow("testRowTextbox2")).focus(); 
		}
		else
		{
			c.getFellow("testRowLabel1").setVisible(false);
			c.getFellow("testRowTextbox1").setVisible(false);						
			b2.setVisible(false);						
			b3.setVisible(false);						
			b4.setVisible(true);						
			b5.setVisible(true);						
		}
	}
	
	public void testRowTextbox1OK(Event event) throws InternalErrorException, NamingException, CreateException
	{
		Textbox tb2 = (Textbox) event.getTarget().getFellow("testRowTextbox2");
		if (tb2.isVisible())
			tb2.focus();
		else
			doTest (event);
	}
	
	@SuppressWarnings("unchecked")
	public void doTest (Event event) throws InternalErrorException, NamingException, CreateException
	{
		Grid g = (Grid) event.getTarget().getFellow ("attributesGrid");
		Map<String,String> map = new HashMap<String,String>();
		for ( Row r: (Collection<Row>) g.getRows().getChildren())
		{
			Listbox lb = (Listbox) r.getChildren().get(2);
			AttributeDirection dir = (AttributeDirection) lb.getSelectedItem().getValue();
			if (dir == AttributeDirection.INPUTOUTPUT ||
					dir == AttributeDirection.OUTPUT)
			{
				Textbox sys = (Textbox) r.getChildren().get(0);
				Textbox soff = (Textbox) r.getChildren().get(3);
				map.put (sys.getValue(), soff.getValue());
			}
		}
		DispatcherService svc = EJBLocator.getDispatcherService();
		String o1 = ((Textbox)g.getFellow("testRowTextbox1")).getValue();
		if (o1 == null || o1.trim().length() == 0)
			o1 = "-";
		String o2 = ((Textbox)g.getFellow("testRowTextbox2")).getValue();
		if (o2 == null || o2.trim().length() == 0)
			o2 = "-";
		Map<String, Object> map2 = svc.testObjectMapping(map,
				(String) XPathUtils.getValue(g, "../../@name"),
				(SoffidObjectType) XPathUtils.getValue(g, "../@soffidObject"),
				o1, o2);
		g.getFellow("testColumn").setVisible(true);
		for ( Row r:  (Collection<Row>) g.getRows().getChildren())
		{
			Textbox sys = (Textbox) r.getChildren().get(0);
			Listbox direction = (Listbox) r.getChildren().get(2);
			Div rdiv = (Div) r.getChildren().get(5);
			Label result = (Label) rdiv.getFirstChild();
			es.caib.zkib.zkiblaf.ImageClic image = (ImageClic) rdiv.getLastChild();
			Object obj = map2.get(sys.getValue());
			if (direction.getSelectedItem() == null ||
					direction.getSelectedIndex() == 0 )
			{
				result.setValue("");
				image.setVisible(false);
			}
			else if (obj == null)
			{
				result.setValue("");
				image.setVisible(false);
			}
			else if (obj instanceof Exception)
			{
				result.setValue("");
				image.setVisible(true);
				if (obj instanceof es.caib.seycon.ng.exception.InternalErrorException)
					image.setTitle(((Exception)obj).getMessage());
				else
					image.setTitle(obj.toString());
			}
			else
			{
				
				result.setValue(stringify(obj, ""));
				image.setVisible(false);
			}
		}
	}

	
	public String stringify (Object obj, String indent)
	{
		if (obj == null) return "";
		
		if (obj instanceof java.util.Calendar)
		{
			return new java.text.SimpleDateFormat(org.zkoss.util.resource.Labels.getLabel("selfService.Format"))
					.format(((Calendar) obj).getTime());
		}
		if (obj instanceof java.util.Date)
		{
			return new java.text.SimpleDateFormat(org.zkoss.util.resource.Labels.getLabel("selfService.Format"))
					.format(obj);
		}
		if (obj instanceof java.util.Collection)
		{
			String r = "";
			for ( Object obj2 : (Collection) obj) {
				if (r.isEmpty()) r = "[";
				else r = r + ",\n"+indent;
				r = r + stringify(obj2, indent+"  ");
			}
			return r + "]";
		}
		if (obj instanceof java.util.Map)
		{
			String r = "";
			for ( Object k : ((Map) obj).keySet()) {
				if (r.isEmpty()) r = "{";
				else r = r + ", ";
				r = r + stringify(k, indent+" ") + ": "+stringify (((Map)obj).get(k), indent+"  ");
			}
			return r + "}";
		}
		if (obj instanceof byte[]) {
			return Base64.getEncoder().encodeToString((byte[]) obj);
		}
		if (obj.getClass().isArray())
		{
			String r = "";
			for ( Object obj2 : (Object[])obj) {
				if (r.isEmpty()) r = "[";
				else r = r + ",\n"+indent;
				r = r + stringify(obj2, indent+"  ");
			}
			return r + "]";
		}
		String s = obj.toString();
		if (s.length() > 150) s = s.substring(0, 145)+" ...";
		return s;
	}

	public void doLoadTest (Event event) throws InternalErrorException, NamingException, CreateException
	{
		if (getDataModel().isCommitPending()) {
			Missatgebox.avis (org.zkoss.util.resource.Labels
				.getLabel("agents.Avis"),
				org.zkoss.util.resource.Labels.getLabel("agents.Canvis"));
			return;
		}
		Grid g = (Grid) event.getTarget().getFellow ("attributesGrid");
		DispatcherService svc = EJBLocator.getDispatcherService();
		String o1 = ((Textbox)g.getFellow("testRowTextbox1")).getValue();
		if (o1 == null || o1.trim().length() == 0)
			o1 = "-";
		String o2 = ((Textbox)g.getFellow("testRowTextbox2")).getValue();
		if (o2 == null || o2.trim().length() == 0)
			o2 = "-";
		
		SoffidObjectType objectType = (SoffidObjectType) XPathUtils.getValue(g, "../@soffidObject"); 
		String systemName = (String) XPathUtils.getValue(g, "../../@name");
		GetObjectResults result = svc.getSoffidObject(
				systemName,
				objectType,
				o1, o2);
		Map<String, Object> map = result.getObject();
		if (map == null)
		{
			es.caib.zkib.zkiblaf.Missatgebox.avis(org.zkoss.util.resource.Labels.getLabel("agents.zul.notFound"));
			return;
		}
		Component c = Path.getComponent("//objectAttributes/window");
		for (String key: map.keySet())
		{
			map.put(key, stringify(map.get(key), ""));
		}
		if (objectType == SoffidObjectType.OBJECT_ACCOUNT)
		{
			Events.postEvent("onStart", c, new Object[]{
					o1,
					result,
					new String[] {systemName, o1}
			});
		}
		else
		{
			Events.postEvent("onStart", c, new Object[]{
					o1,
					result
			});
		}
	}

	private DataModel getDataModel() {
		return (DataModel) XPathUtils.getPath(this, "/model");
	}

	public void doQueryTest (Event event) throws InternalErrorException, NamingException, CreateException
	{
		Grid g = (Grid) event.getTarget().getFellow ("attributesGrid");
		DispatcherService svc = EJBLocator.getDispatcherService();
		String o1 = ((Textbox)g.getFellow("testRowTextbox1")).getValue();
		if (o1 == null || o1.trim().length() == 0)
			o1 = "-";
		String o2 = ((Textbox)g.getFellow("testRowTextbox2")).getValue();
		if (o2 == null || o2.trim().length() == 0)
			o2 = "-";
		com.soffid.iam.sync.engine.intf.GetObjectResults result = svc.getNativeObject(
				(String) XPathUtils.getValue(g, "../../@name"),
				(SoffidObjectType) XPathUtils.getValue(g, "../@soffidObject"),
				o1, o2);
		Map<String, Object> map = result.getObject();
		if (map == null)
		{
			es.caib.zkib.zkiblaf.Missatgebox.avis(org.zkoss.util.resource.Labels.getLabel("agents.zul.notFound"));
			return;
		}
		Component c = Path.getComponent("//objectAttributes/window");
		for (String key: map.keySet())
		{
			map.put(key, stringify(map.get(key), ""));
		}
		Events.postEvent("onStart", c, new Object[]{
				o1,
				result
		});
	}

	public void doFullTest (Event event) throws InternalErrorException, NamingException, CreateException
	{
		Component g = event.getTarget();
		if (getDataModel().isCommitPending()) {
			Missatgebox.avis (org.zkoss.util.resource.Labels
				.getLabel("agents.Avis"),
				org.zkoss.util.resource.Labels.getLabel("agents.Canvis"));
			return;
		}
		DispatcherService svc = EJBLocator.getDispatcherService();
		String o1 = ((Textbox)g.getFellow("testRowTextbox1")).getValue();
		if (o1 == null || o1.trim().length() == 0)
			o1 = "-";
		String o2 = ((Textbox)g.getFellow("testRowTextbox2")).getValue();
		if (o2 == null || o2.trim().length() == 0)
			o2 = "-";
		com.soffid.iam.sync.engine.intf.DebugTaskResults r = svc.testPropagateObject(
				(String) XPathUtils.getValue(g, "../@name"),
				(SoffidObjectType) XPathUtils.getValue(g, "@soffidObject"),
				o1, o2);
		if (r == null) {
			Missatgebox.avis("Cannot connect to target system");
		} else {
			Window testWindow = (Window) getFellow("testWindow");
			testWindow.setAttribute("logText", r.getLog());
			((Label)testWindow.getFellow("status")).setValue(r.getStatus());
			DataTree2 tree = (DataTree2)testWindow.getFellow("log");
			new LogParser().parseLog(r.getLog(), tree);
			testWindow.doHighlighted();
		}
	}
	
	public void editTrigger(Event event ) throws ComponentNotFoundException, InternalErrorException, NamingException, CreateException, IOException {
		Editor.edit((Textbox) event.getTarget().getPreviousSibling(),
					    new com.soffid.iam.web.agent.ScriptEnviroment().getTriggerVars(event.getTarget()));

	}

	public void onNewAttributeMapping(Event event) {
		Row r = (Row) event.getData();
		String direction = (String) getVariable("direction", false);
		if (direction != null) {
			AttributeDirection expected = direction.toUpperCase().startsWith("OUT") ?
					AttributeDirection.OUTPUT :
					AttributeDirection.INPUT;
			try {
				AttributeDirection type = (AttributeDirection) XPathUtils.eval(r, "@direction");
				if ( type == null)
					XPathUtils.setValue(r, "@direction", expected);
				else if ( type != expected)
					r.setVisible(false);
			} catch (Exception e) {
				// Ignore
			}
		}

	}

	public void onNewProperty(Event event) {
		Row r = (Row) event.getData();
		Set<String> filter = (Set<String>) getVariable("propertiesFilter", false);
		if (filter != null) {
			String name = (String) XPathUtils.eval(r, "property");
			if (name != null && !name.isEmpty() && ! filter.contains(name))
				r.setVisible(false);
		}

	}

	public void onNewTrigger(Event event) {
		try {
			Row r = (Row) event.getData();
			String triggerType = (String) getVariable("triggerType", false);
			if (triggerType != null) {
				SoffidObjectTrigger tr = SoffidObjectTrigger.fromString(triggerType); 
				SoffidObjectTrigger type = (SoffidObjectTrigger) XPathUtils.eval(r, "@trigger");
				if ( type == null)
					XPathUtils.setValue(r, "@trigger", tr);
				else if ( type != tr)
					r.setVisible(false);
			}
		} catch (Exception e)
		{
		}
	}
}
