package com.soffid.iam.web.users.additionalData;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.logging.LogFactory;
import org.zkoss.image.AImage;
import org.zkoss.util.media.AMedia;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.HtmlBasedComponent;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.InputEvent;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Fileupload;
import org.zkoss.zul.Image;
import org.zkoss.zul.Include;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Timer;
import org.zkoss.zul.Window;
import org.zkoss.zul.impl.InputElement;
import org.zkoss.zul.mesg.MZul;

import com.soffid.iam.ServiceLocator;
import com.soffid.iam.api.Application;
import com.soffid.iam.api.AsyncList;
import com.soffid.iam.api.CustomObject;
import com.soffid.iam.api.DataType;
import com.soffid.iam.api.Group;
import com.soffid.iam.api.Host;
import com.soffid.iam.api.MailDomain;
import com.soffid.iam.api.Role;
import com.soffid.iam.api.Task;
import com.soffid.iam.api.User;
import com.soffid.iam.api.UserType;
import com.soffid.iam.bpm.api.ProcessInstance;
import com.soffid.iam.service.ejb.UserService;
import com.soffid.iam.service.impl.bshjail.SecureInterpreter;
import com.soffid.iam.utils.Security;
import com.soffid.iam.web.component.Identity;

import bsh.EvalError;
import bsh.TargetError;
import es.caib.seycon.ng.EJBLocator;
import es.caib.seycon.ng.comu.Aplicacio;
import es.caib.seycon.ng.comu.Grup;
import es.caib.seycon.ng.comu.Rol;
import es.caib.seycon.ng.comu.TypeEnumeration;
import es.caib.seycon.ng.comu.Usuari;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.servei.ejb.UsuariService;
import es.caib.seycon.ng.web.Messages;
import es.caib.zkib.binder.BindContext;
import es.caib.zkib.binder.SingletonBinder;
import es.caib.zkib.datasource.CommitException;
import es.caib.zkib.datasource.XPathUtils;
import es.caib.zkib.jxpath.JXPathException;
import es.caib.zkib.zkiblaf.Frame;

public class InputField2 extends Div 
{
	org.apache.commons.logging.Log log = LogFactory.getLog(getClass());
	
	private final class TimerEventListener implements EventListener {
		public void onEvent(Event event) throws Exception {
			try {
				updateSearchStatus();
			} catch (Throwable e) {
				throw new UiException(""+e);
			}
		}
	}

	private static final long serialVersionUID = 1L;
	private String compos;
	DataType dataType;
	private String bind;
	private Object ownerObject;
	SingletonBinder binder = new SingletonBinder(this);
	
	public DataType getDataType() {
		return dataType;
	}

	public void setDataType(DataType dataType) {
		this.dataType = dataType;
	}

	public String getBind() {
		return bind;
	}

	public void setBind(String bind) {
		this.bind = bind;
		binder.setDataPath(bind);
	}

	private boolean twoPhaseEdit;

	public boolean isTwoPhaseEdit() {
		return twoPhaseEdit;
	}

	public void setTwoPhaseEdit(boolean twoPhaseEdit) {
		this.twoPhaseEdit = twoPhaseEdit;
	}

	public InputField2(){
		super();
		compos = new String();
	}
	
	public void onCreate () throws NamingException, CreateException, InternalErrorException, IOException
	{
		createField();
	}
	
	boolean disableRecursive = false;
	private boolean updateUser;
	private boolean updateGroup;
	private boolean updateApplication;
	private boolean updateCustomObject;
	private boolean readonly;
	private String ownerContext;
	
	public void onSelectUser (Event event) {
		Page p = getDesktop().getPage("usuarisLlista");
		Boolean multiValued = dataType.isMultiValued();
		Events.postEvent("onInicia", p.getFellow("esquemaLlista"), event.getTarget());
		Events.postEvent("onConfigure", p.getFellow("esquemaLlista"), new Object [] {  filter, multiValued });
	}

	public void onSelectGroup(Event event) {
		Page p = getDesktop().getPage("grupsLlista");
		p.setAttribute("tipus", "");
		p.setAttribute("llistaObsolets", false);
		Events.postEvent("onInicia", p.getFellow("esquemaLlista"), event.getTarget());
		Events.postEvent("onConfigure", p.getFellow("esquemaLlista"), new Object [] {  filter, dataType.isMultiValued() });
	}

	public void onSelectApplication(Event event) {
		Page p = getDesktop().getPage("aplicacionsLlista");
		Events.postEvent("onInicia", p.getFellow("esquemaLlista"), event.getTarget());
	}

	public void onSelectCustomObject(Event event) {
		Page p = getDesktop().getPage("customObjectsLlista");
		p.setAttribute("type", dataType.getDataObjectType());
		Boolean multiValued = dataType.isMultiValued();
		Events.postEvent("onInicia", p.getFellow("esquemaLlista"), new Object[] {event.getTarget(), multiValued});
	}

	public void onSelectHost(Event event) {
		Page p = getDesktop().getPageIfAny("maquinesLlista");
		if ( p == null)
		{
			Component hostsWindow = getPage().getFellowIfAny("hostsWindow");
			if (hostsWindow == null)
			{
				hostsWindow = new Window();
				hostsWindow.setId("hostsWindow");
				hostsWindow.setPage(getPage());
				Executions.getCurrent().createComponents("/maquinesllista.zul", hostsWindow, new HashMap());
			}
			Events.postEvent("onInicia", hostsWindow.getFellow("esquemaLlista"), event.getTarget());
		}
		else
			Events.postEvent("onInicia", p.getFellow("esquemaLlista"), event.getTarget());
	}

	public void onSelectMailDomain(Event event) {
		Page p = getDesktop().getPageIfAny("dominisCorreuLlista");
		if ( p == null)
		{
			Component mailDomainWindow = getPage().getFellowIfAny("mailDomainWindow");
			if (mailDomainWindow == null)
			{
				mailDomainWindow = new Window();
				mailDomainWindow.setId("mailDomainWindow");
				mailDomainWindow.setPage(getPage());
				Executions.getCurrent().createComponents("/dominisCorreullista.zul", mailDomainWindow, new HashMap());
			}
			Events.postEvent("onInicia", mailDomainWindow.getFellow("esquemaLlista"), event.getTarget());
		}
		else
			Events.postEvent("onInicia", p.getFellow("esquemaLlista"), event.getTarget());
	}

	/** 
	 * Event received on two phase edit 
	 * @throws CommitException */
	public void changeData() throws CommitException
	{
		XPathUtils.getComponentContext(this).getDataSource().commit();
		Div div = (Div) getChildren().get(0);
		((HtmlBasedComponent)div.getChildren().get(0)).setVisible(true);
		((HtmlBasedComponent)div.getChildren().get(1)).setVisible(true);
		((HtmlBasedComponent)div.getChildren().get(2)).setVisible(false);
		((HtmlBasedComponent)div.getChildren().get(3)).setVisible(false);
	}

	public void onActualitzaUser(Event event) throws UnsupportedEncodingException, IOException, CommitException {
		if (dataType.isMultiValued())
		{
			InputElement textbox = (InputElement) event.getTarget().getPreviousSibling();
			List<String> data = (List<String>) event.getData();
			for (String s: data)
			{
				textbox.setRawValue(s);
				onChildChange( new Event (event.getName(), textbox ) );
				List l = (List) binder.getValue();
				int currentSize = l.size();
				textbox = (InputElement) getFellow( getIdForPosition(currentSize));
			}			
		}
		else
		{
			String[] data = (String[]) event.getData();
			String userName = data[0];
			((InputElement) event.getTarget().getPreviousSibling()).setRawValue(userName);
			onChildChange( new Event (event.getName(), event.getTarget().getPreviousSibling() ) );
		}
	}

	org.zkoss.zhtml.Div searchBox = null;
	AsyncList<?> currentSearch = null;
	private Div searchContent;
	int searchPosition = 0;
	List<Identity> searchResults = null;
	private String searchCriteria;
	private InputElement currentSearchTextbox;
	private SearchFilter filter;
	public void onChanging(InputEvent event) throws Throwable {
		currentSearchTextbox = (InputElement) event.getTarget();
		searchCriteria = (String) event.getValue();
		cancelSearch();
		if (searchBox != null)
			searchBox.detach();
		searchResults = new LinkedList<Identity>();
		searchPosition = 0;
		Security.nestedLogin(Security.ALL_PERMISSIONS);
		try
		{
			if (dataType.getType() == TypeEnumeration.CUSTOM_OBJECT_TYPE)
			{
				currentSearch = ServiceLocator.instance().getCustomObjectService().findCustomObjectByTextAsync(dataType.getDataObjectType(), searchCriteria);
			}
			if (dataType.getType() == TypeEnumeration.USER_TYPE)
			{
				currentSearch = ServiceLocator.instance().getUserService().findUserByTextAsync(searchCriteria);
			}
			if (dataType.getType() == TypeEnumeration.GROUP_TYPE)
			{
				currentSearch = ServiceLocator.instance().getGroupService().findGroupByTextAsync(searchCriteria);
			}
			if (dataType.getType() == TypeEnumeration.APPLICATION_TYPE)
			{
				currentSearch = ServiceLocator.instance().getApplicationService().findApplicationByTextAsync(searchCriteria);
			}
		} finally {
			Security.nestedLogoff();
		}
		searchBox = new org.zkoss.zhtml.Div();
		searchBox.setDynamicProperty("tabindex", "-1");
		searchBox.setSclass("attributeSearchPopup");
		currentSearchTextbox.getParent().insertBefore(searchBox, currentSearchTextbox);
		Timer t = new org.zkoss.zul.Timer();
		t.setDelay(1000);
		t.setRepeats(true);
		t.addEventListener("onTimer", new TimerEventListener());
		searchBox.appendChild(t);
		searchContent = new Div();
		searchBox.appendChild (searchContent);
		Image searchProgress = new Image();
		searchProgress.setSrc("~./img/soffid-progress.gif");
		searchProgress .setStyle("height: 2em");
		searchBox.appendChild(searchProgress);
		
		updateSearchStatus ();
		
	}

	private void updateSearchStatus() throws Throwable {
		if (currentSearch == null)
			return;
		Iterator it = currentSearch.iterator();
		if ( (currentSearch.isDone() &&  searchPosition == currentSearch.size()) || currentSearch.isCancelled())
		{
			((Component)searchBox.getChildren().get(2)).setVisible(false); // Hide ensaimada
			((Timer)searchBox.getChildren().get(0)).stop(); // Stop timer
			Throwable th = currentSearch.getExceptionToThrow();
			currentSearch.clearExceptionToThrow();
			if (th != null)
			{
				cancelSearch();
				throw th; 
			}
		} else if (currentSearch.size() > searchPosition) {
			int i = 0;
			boolean any = false;
			while (it.hasNext())
			{
			    Object o = it.next();
				if (i++ >= searchPosition)
				{
					any = true;
					Div d = new Div();
					if (filter == null || filter.isAllowedValue(o))
					{
						Identity identity = null;
						if (o instanceof CustomObject)
							identity = new Identity( (CustomObject ) o);
						if (o instanceof Group)
							identity = new Identity( (Group ) o);
						if (o instanceof User)
							identity = new Identity( (User ) o);
						if (o instanceof Application)
							identity = new Identity( (Application ) o);
						if (identity != null)
						{
							searchResults.add(identity);
						}
					}
					searchPosition ++;
				}
			}
			if (any)
			{			
				Collections.sort(searchResults, com.soffid.iam.web.component.Identity.getComparator());
				searchContent.getChildren().clear();
				for (com.soffid.iam.web.component.Identity id: searchResults)
				{
					Object o = id.getObject();
					String value = o instanceof CustomObject ? ((CustomObject) o).getName() :
						o instanceof User ? ((User) o).getUserName() :
						o instanceof Group ? ((Group) o).getName() :
						o instanceof Application ? ((Application) o).getName() :
						o instanceof Role ? ((Role) o).getName() :
						null;
					if (value != null)
					{
						value = org.apache.commons.lang3.StringEscapeUtils.escapeJava(value);
						Div d = id.generateSelector(searchCriteria);
						d.setAction("onMouseDown: document.getElementById('"+currentSearchTextbox.getUuid()+"').value='" + value + "'");
						searchContent.appendChild(d);
					}
				}
			}
		} 
	}

	protected void selectCandidate(Event e) throws UnsupportedEncodingException, IOException, CommitException {
		Div d = (Div) e.getTarget();
		Identity identity = (Identity) d.getAttribute("identity");
		Object o = identity.getObject();
		String value = null;
		if ( o instanceof CustomObject)
			value = ((CustomObject) o).getName();
		if (value != null)
		{
			cancelSearch();
			currentSearchTextbox.setRawValue( value );
			applyChange(currentSearchTextbox, value);
			Events.postEvent("onChange", this, null);
		}
	}

	public void onBlur (Event event)
	{
		cancelSearch();
	}
	
	public void cancelSearch() {
		if (currentSearch != null)
		{
			currentSearch.cancel();
			currentSearch = null;
		}
		if (searchBox != null)
		{
			searchBox.setVisible(false);
		}
	}

	public void onChildChange(Event event) throws UnsupportedEncodingException, IOException, CommitException {
//		cancelSearch();
		Component tb = event.getTarget();
		
		Object value = null;
		if (tb instanceof InputElement)
			value = ((InputElement) tb).getRawValue();
		else if (tb instanceof Listbox)
		{
			Listbox lb = (Listbox) tb;
			if (lb.getSelectedItem() != null)
				value = lb.getSelectedItem().getValue();
		}
		Events.postEvent("onChange", this, null);
		applyChange(tb, value);
	}

	private void applyChange(Component tb, Object value) throws IOException, UnsupportedEncodingException, CommitException {
		Integer order = (Integer) tb.getAttribute("position");
		
		if (order == null)
			binder.setValue(value);
		else {
			List l = (List) binder.getValue();
			if (l == null) l = new LinkedList();
			if (order.intValue() == l.size() )
			{
				l.add(value);
				createFieldElement(new Integer (l.size()), null);
			}
			else
				l.set(order.intValue(), value);
			binder.setValue(new LinkedList());
			binder.setValue(l);
		}
				
		attributeValidate( order );

		Component c = this;
		do
		{
			if (c instanceof AttributesDiv)
			{
				((AttributesDiv) c).adjustVisibility();
				break;
			}
			else if (c instanceof UserAttributesDiv)
			{
				((UserAttributesDiv) c).adjustVisibility();
				break;
			}
			else
				c = c.getParent();
		} while (c != null);
		
	}

	public void changeHtml(Event ev) throws Exception {
		String text = (String) ev.getData();
        byte data[] = text.getBytes("UTF-8");
        applyChange(ev.getTarget(), data);
        if (twoPhaseEdit)
        	binder.getDataSource().commit();
    }

	public void onActualitzaGroup(Event event) throws UnsupportedEncodingException, IOException, CommitException {
		if (dataType.isMultiValued())
		{
			InputElement textbox = (InputElement) event.getTarget().getPreviousSibling();
			List<String> data = (List<String>) event.getData();
			for (String s: data)
			{
				textbox.setRawValue(s);
				onChildChange( new Event (event.getName(), textbox ) );
				List l = (List) binder.getValue();
				int currentSize = l.size();
				textbox = (InputElement) getFellow( getIdForPosition(currentSize));
			}			
		}
		else
		{
			String[] data = (String[]) event.getData();
			String groupName = data[0];
			((InputElement) event.getTarget().getPreviousSibling()).setRawValue(groupName);
			onChildChange( new Event (event.getName(), event.getTarget().getPreviousSibling() ) );
		}
	}

	public void onActualitzaApplication(Event event) throws UnsupportedEncodingException, IOException, CommitException {
		String data = (String) event.getData();
		((InputElement) event.getTarget().getPreviousSibling()).setRawValue(data);
		onChildChange( new Event (event.getName(), event.getTarget().getPreviousSibling() ) );
	}


	public void onActualitzaHost(Event event) throws UnsupportedEncodingException, IOException, CommitException {
		Object[] data = (Object[]) event.getData();
		((InputElement) event.getTarget().getPreviousSibling()).setRawValue(data[0]);
		onChildChange( new Event (event.getName(), event.getTarget().getPreviousSibling() ) );
	}

	public void onActualitzaMailDomain(Event event) throws UnsupportedEncodingException, IOException, CommitException {
		String data = (String) event.getData();
		((InputElement) event.getTarget().getPreviousSibling()).setRawValue(data);
		onChildChange( new Event (event.getName(), event.getTarget().getPreviousSibling() ) );
	}

	public void onActualitzaCustomObject(Event event) throws UnsupportedEncodingException, IOException, CommitException {
		InputElement textbox = (InputElement) event.getTarget().getPreviousSibling();
		if ( dataType.isMultiValued() )
		{
			List<String> data = (List<String>) event.getData();
			for (String s: data)
			{
				textbox.setRawValue(s);
				onChildChange( new Event (event.getName(), textbox ) );
				List l = (List) binder.getValue();
				int currentSize = l.size();
				textbox = (InputElement) getFellow( getIdForPosition(currentSize));
			}
		}
		else
		{
			String data = (String) event.getData();
			textbox.setRawValue(data);
			onChildChange( new Event (event.getName(), event.getTarget().getPreviousSibling() ) );
		}
	}

	private void commit() throws CommitException {
		XPathUtils.getComponentContext(this).getDataSource().commit();
	}

	public void openUser() {
		String user = (String) getValue();
		Executions.getCurrent().sendRedirect("/index.zul?target=/usuaris.zul&user="+user, "_new");
	}

	public void openGroup() {
		String grup = (String) getValue();
		Executions.getCurrent().sendRedirect("/index.zul?target=/grups.zul&group=" + grup, "_new");
	}

	public void openApplication() {
		String application = (String) getValue();
		Executions.getCurrent().sendRedirect("/index.zul?target=/aplicacions.zul&application=" + application, "_new");
	}

	public void openCustomObject() {
		String type = dataType.getDataObjectType();
		String customObject = (String) getValue();
		Executions.getCurrent().sendRedirect("/index.zul?target=/customObjects.zul&type="+type+"&customobject=" + customObject, "_new");
	}

	private Object getValue() {
		return XPathUtils.getValue( XPathUtils.getComponentContext(this), bind );
	}
	
	public boolean updateUser(String id)
	{
		InputElement inputElement = (InputElement) getFellow(id);
		String user = inputElement.getText();
		
		Label l = (Label) getFellowIfAny(id+"b");
		
		User u = null;
		if (user == null || user.isEmpty())
		{
			if (l != null) l.setValue("");
		}
		else
		{
			try {
				UserService ejb = com.soffid.iam.EJBLocator.getUserService();
				u = ejb.findUserByUserName(user);
			} catch (Exception e) {
			}
			if (u == null || ( filter != null && ! filter.isAllowedValue(u)))
			{
				if (l != null) l.setValue("?");
				throw new WrongValueException(inputElement, MZul.VALUE_NOT_MATCHED);
			}
			else
			{
				if (l != null) l.setValue(u.getFullName());
			}
		}
		
		return true;
	}

	public boolean updateGroup(String id) {

		InputElement inputElement = (InputElement) getFellow(id);
		String group = inputElement.getText();

		Label l = (Label) getFellowIfAny(id+"b");

		
		if (group == null || group.isEmpty())
		{
			if (l != null) l.setValue("");
		}
		else {
			Group g = null;
			try {
				g = com.soffid.iam.EJBLocator.getGroupService().findGroupByGroupName(group);
			} catch (Exception e) {}
			if (g == null || ( filter != null && ! filter.isAllowedValue(g))) {
				if (l != null) l.setValue("?");
				throw new WrongValueException(inputElement, MZul.VALUE_NOT_MATCHED);
			}
			if (l != null )
				l.setValue(g.getDescription());
		}
		return true;
	}

	public void updateApplication(String id) {

		InputElement inputElement = (InputElement) getFellow(id);
		String application = inputElement.getText();

		Label l = (Label) getFellowIfAny(id+"b");

		if (application == null || application.isEmpty()) {
			if (l != null)
				l.setValue("");
		} else {
			Aplicacio a = null;
			try {
				a = EJBLocator.getAplicacioService().findAplicacioByCodiAplicacio(application);
			} catch (Exception e) {}
			if (a == null || ( filter != null && ! filter.isAllowedValue(a))) {
				if (l != null) l.setValue("?");
				throw new WrongValueException(inputElement, MZul.VALUE_NOT_MATCHED);
			}
			if (l != null) l.setValue(a.getNom());
		}
	}

	public void updateHost(String id) {

		InputElement inputElement = (InputElement) getFellow(id);
		String host = inputElement.getText();

		Label l = (Label) getFellowIfAny(id+"b");

		if (host == null || host.isEmpty()) {
			if (l != null)
				l.setValue("");
		} else {
			Host a = null;
			try {
				a = com.soffid.iam.EJBLocator.getNetworkService().findHostByName(host);
			} catch (Exception e) {}
			if (a == null || ( filter != null && ! filter.isAllowedValue(a))) {
				if (l != null) l.setValue("?");
				throw new WrongValueException(inputElement, MZul.VALUE_NOT_MATCHED);
			}
			if (l != null) l.setValue(a.getDescription());
		}
	}

	public void updateMailDomain(String id) {

		InputElement inputElement = (InputElement) getFellow(id);
		String mailDomain = inputElement.getText();

		Label l = (Label) getFellowIfAny(id+"b");

		if (mailDomain == null || mailDomain.isEmpty()) {
			if (l != null)
				l.setValue("");
		} else {
			MailDomain a = null;
			try {
				a = com.soffid.iam.EJBLocator.getMailListsService().findMailDomainByName(mailDomain);
			} catch (Exception e) {}
			if (a == null || ( filter != null && ! filter.isAllowedValue(a))) {
				if (l != null) l.setValue("?");
				throw new WrongValueException(inputElement, MZul.VALUE_NOT_MATCHED);
			}
			if (l != null) l.setValue(a.getDescription());
		}
	}

	public void updateCustomObject(String id) {
		InputElement inputElement = (InputElement) getFellow(id);
		String customObject = inputElement.getText();

		Label l = (Label) getFellowIfAny(id+"b");

		if (customObject == null || customObject.isEmpty())
		{
			if (l != null) l.setValue("");
		}
		else {
			CustomObject co = null;
			try {
				co = EJBLocator.getCustomObjectService().findCustomObjectByTypeAndName(dataType.getDataObjectType(), customObject);
			} catch (Exception e) {}
			if (co == null || ( filter != null && ! filter.isAllowedValue(co))) {
				if (l != null) l.setValue("?");
				throw new WrongValueException(inputElement, MZul.VALUE_NOT_MATCHED);
			}
			else
				if (l != null) l.setValue(co.getDescription());
		}
	}

	public synchronized void createField() throws NamingException, CreateException, InternalErrorException, IOException{
		
		if (getPage() == null)
			return;	
		
		if (disableRecursive)
			return;
		
		disableRecursive = true;
		
		try
		{
			while (!getChildren().isEmpty())
			{
				((Component)getChildren().get(0)).setParent(null);
			}
			if(dataType != null)
			{
				Object value = binder.getValue();
				calculateVisibility();
				if (dataType.isMultiValued())
				{
					if (value == null)
					{
						value = new LinkedList();
//						binder.setValue(value);
					}
					if (value instanceof List)
					{
						List l = (List) value;
						int i;
						for ( i = 0; i < l.size(); i++)
						{
							createFieldElement(new Integer(i), l.get(i));
						}
						if (!readonly)
							createFieldElement(new Integer(i), null);
					}
				}
				else
					createFieldElement(null, value);
			}
		} catch (Throwable e) {
			log.warn(e);
		} finally {
			disableRecursive = false;
		}
		
	}

	private void createFieldElement(Integer position, Object value) throws IOException, UnsupportedEncodingException {
		String result = "";
		Map <String,Object> map=new HashMap<String, Object>();
		updateUser = false;
		updateGroup = false;
		updateApplication = false;
		updateCustomObject = false;
		String readonlyExpr = readonly ? "true" : "false";
		TypeEnumeration type = dataType.getType();
		String stringType = new String();
		if(type!=null)
			stringType = type.toString();
		int size = 0;
		if(dataType.getSize() != null)
			size = dataType.getSize();
		String required = "";
		if (dataType.isRequired())
			required = "*";
			
		String id = getIdForPosition(position);
		String id2 = id + "b";
		String id3 = id + "c";
		if(stringType != null && !stringType.trim().isEmpty()){
			if(TypeEnumeration.USER_TYPE.equals(type))
			{
				updateUser = true;
					result = "<div style='display:block' visible='true'>"
							+ "<textbox sclass=\"textbox\" onOK='' maxlength=\"" + size +"\" "
									+ "id=\""+id+"\" "
									+ "onChange='self.parent.parent.onChildChange(event)' "  
									+ "onBlur='self.parent.parent.onBlur(event)' "
									+ "onChanging='self.parent.parent.onChanging(event)' "
									+ "readonly=\"" +readonlyExpr+ "\"/>" +
							"<imageclic src='/img/user.png' visible=\""+(!readonly)+"\" "
									+ "onClick='self.parent.parent.onSelectUser(event)' "
									+ "onActualitza='self.parent.parent.onActualitzaUser(event)' style='margin-left:2px; margin-right:2px; vertical-align:-4px' />"
							+ "<label style='text-decoration: underline; cursor:pointer' onClick='self.parent.parent.openUser()' id=\""+id2+"\" />"
							+ required+"</div>";
			}
			else if(TypeEnumeration.GROUP_TYPE.equals(type))
			{
				updateGroup = true;
				StringBuffer sb = new StringBuffer();
				sb.append("<div style='display:block' visible='true'>");
				sb.append("<textbox sclass='textbox' maxlength='"+size+"' onOK='' "
						+ "onChange='self.parent.parent.onChildChange(event)' "  
						+ "onBlur='self.parent.parent.onBlur(event)' "
						+ "onChanging='self.parent.parent.onChanging(event)' "
						+ "id=\""+id+"\" "
						+ "readonly='"+readonlyExpr+"'/>");
				sb.append("<imageclic src='/zkau/web/img/grup.gif' onClick='self.parent.parent.onSelectGroup(event)' "
						+ "onActualitza='self.parent.parent.onActualitzaGroup(event)' "
						+ "style='margin-left:2px; margin-right:2px; vertical-align:-4px; width:16px' "
						+ " visible=\""+(!readonly)+"\" />");
				sb.append("<label style='text-decoration:underline; cursor:pointer' onClick='self.parent.parent.openGroup()' id=\""+id2+"\"/>");
				sb.append(required+"</div>");
				result = sb.toString();
			}
			else if(TypeEnumeration.APPLICATION_TYPE.equals(type))
			{
				updateApplication = true;
				StringBuffer sb = new StringBuffer();
				sb.append("<div style='display: block' visible='true'>");
				sb.append("<textbox sclass='textbox' maxlength='"+size+"' onChange='self.parent.parent.onChildChange(event)' onOK='' "
						+ "id=\""+id+"\" "
						+ "readonly='"+readonlyExpr+"'/>");
				sb.append("<imageclic src='/zkau/web/img/servidorHome.gif' "
						+ "onClick='self.parent.parent.onSelectApplication(event)' "
						+ "onActualitza='self.parent.parent.onActualitzaApplication(event)' "
						+ "style='margin-left:2px; margin-right:2px; vertical-align:-4px; width:16px' "
						+ " visible=\""+(!readonly)+"\"/>");
				sb.append("<label style='text-decoration:underline; cursor:pointer' onClick='self.parent.parent.openApplication()' id=\""+id2+"\"/>");
				sb.append(required+"</div>");
				result = sb.toString();
			}
			else if(TypeEnumeration.HOST_TYPE.equals(type))
			{
				StringBuffer sb = new StringBuffer();
				sb.append("<div style='display: block' visible='true'>");
				sb.append("<textbox sclass='textbox' maxlength='"+size+"' onChange='self.parent.parent.onChildChange(event)' onOK='' "
						+ "id=\""+id+"\" "
						+ "readonly='"+readonlyExpr+"'/>");
				sb.append("<imageclic src='/zkau/web/img/host.png' "
						+ "onClick='self.parent.parent.onSelectHost(event)' "
						+ "onActualitza='self.parent.parent.onActualitzaHost(event)' "
						+ "style='margin-left:2px; margin-right:2px; vertical-align:-4px; width:16px' "
						+ " visible=\""+(!readonly)+"\"/>");
				sb.append("<label id=\""+id2+"\"/>");
				sb.append(required+"</div>");
				result = sb.toString();
			}
			else if(TypeEnumeration.MAIL_DOMAIN_TYPE.equals(type))
			{
				StringBuffer sb = new StringBuffer();
				sb.append("<div style='display: block' visible='true'>");
				sb.append("<textbox sclass='textbox' maxlength='"+size+"' onChange='self.parent.parent.onChildChange(event)' onOK='' "
						+ "id=\""+id+"\" "
						+ "readonly='"+readonlyExpr+"'/>");
				sb.append("<imageclic src='~./img/mail.png' "
						+ "onClick='self.parent.parent.onSelectMailDomain(event)' "
						+ "onActualitza='self.parent.parent.onActualitzaMailDomain(event)' "
						+ "style='margin-left:2px; margin-right:2px; vertical-align:-4px; width:16px' "
						+ " visible=\""+(!readonly)+"\"/>");
				sb.append("<label id=\""+id2+"\" visible='false'/>");
				sb.append(required+"</div>");
				result = sb.toString();
			}
			else if(TypeEnumeration.CUSTOM_OBJECT_TYPE.equals(type))
			{
				updateCustomObject = true;
				StringBuffer sb = new StringBuffer();
				sb.append("<div style='display:block' visible='true' >");
				sb.append("<textbox sclass='textbox' maxlength='"+size+"' onChange='self.parent.parent.onChildChange(event)' onOK='' "
						+ "onBlur='self.parent.parent.onBlur(event)' "
						+ "onChanging='self.parent.parent.onChanging(event)'  "
						+ "id=\""+id+"\" "
						+ "readonly='"+readonlyExpr+"'/>");
				sb.append("<imageclic src='/zkau/web/img/servidorPerfils.gif' "
						+ " visible=\""+(!readonly)+"\" "
						+ "onClick='self.parent.parent.onSelectCustomObject(event)' "
						+ "onActualitza='self.parent.parent.onActualitzaCustomObject(event)' "
						+ "style='margin-left:2px; margin-right:2px; vertical-align:-4px; width:16px' />");
				sb.append("<label style='text-decoration:underline; cursor:pointer' onClick='self.parent.parent.openCustomObject()' id=\""+id2+"\"/>");
				sb.append(required+"</div>");
				result = sb.toString();
			}
			else if(TypeEnumeration.BINARY_TYPE.equals(type))
			{
				boolean visible = fileAlreadySaved();
				result = "<h:span xmlns:h=\"http://www.w3.org/1999/xhtml\"><button label=\"Upload\" " +
							"onClick=\"self.parent.parent.uploadBinary();\" "
							+ "disabled=\""+readonlyExpr+"\">" +
							"</button><button label=\"Download\" disabled=\"${!canUpdateUserMetadata}\" visible=\"" + visible + "\" "
									+ "onClick=\"self.parent.parent.downloadBinary(self.parent);\">" +
							"</button>"+required+"</h:span>";
			}
			else if(TypeEnumeration.PHOTO_TYPE.equals(type))
			{
				if(getValue() != null){
					map.put("image", byteArrayToImage((byte[])getValue()));
					result = "<h:span xmlns:h=\"http://www.w3.org/1999/xhtml\"><button label=\"Upload Photo\" "
						    + " onClick=\"self.parent.parent.upload(self.parent);\" "
						    + "disabled=\""+readonlyExpr+"\"/>"
							+ "<image content=\"${arg.image}\" style=\"max-width: 100px; max-height: 100px;\"/>"+required+"</h:span>";
				}else{
					result = "<h:span xmlns:h=\"http://www.w3.org/1999/xhtml\"><button label=\"Upload Photo\" " +
						    " onClick=\"self.parent.parent.upload(self.parent);\" "
						    + "disabled=\""+readonlyExpr+"\">" +
							"</button>"+required+"</h:span>";
				}
			}
			else if(TypeEnumeration.DATE_TYPE.equals(type))
			{
				result = "<div><datebox format=\"${c:l('usuaris.zul.dateFormat2')}\" " + "disabled=\""+readonlyExpr+"\" onOK='' visible='true' "
						+ (readonly ? "buttonVisible=\"false\" ": "")
						+ "id=\""+id+"\" "
						+ "onChange='self.parent.parent.onChildChange(event)'/>"+required+"</div>"; 
			}
			else if(TypeEnumeration.EMAIL_TYPE.equals(type))
			{
				result = "<textbox sclass=\"textbox\" onOK=''  maxlength=\"" + size +"\"  width='100%' visible='true' "
						+ "id=\""+id+"\" "
							+ "readonly=\""+readonlyExpr+"\" constraint=\"/(^$|.+@.+\\.[a-z]+)/: ${c:l('InputField.NoCorrectEmail')}\" "
									+ "onChange='self.parent.parent.onChildChange(event)'/>";
				result = "<div>"+result+required+"</div>";
			}	
			else if(TypeEnumeration.SSO_FORM_TYPE.equals(type))
			{
				String []split = getFormValues ();
				result = "<textbox sclass=\"textbox\" maxlength=\"" + size/2 +"\" onChange=\"self.parent.parent.updateSsoForm(event)\" width='40%'  "
							+ "id=\""+id+"\" "
							+ "readonly=\""+readonlyExpr+"\" onOK='' value='"+StringEscapeUtils.escapeXml(split[0])+"'/>" 
							+ "<label value=' = '/>"
							+ "<textbox sclass=\"textbox\" maxlength=\"" + size/2 +"\" onChange=\"self.parent.parent.updateSsoForm(event)\" width='40%'  "
							+ "id=\""+id2+"\" "
							+ "readonly=\""+readonlyExpr+"\" onOK='' value='"+StringEscapeUtils.escapeXml(split[1])+"'/>";
				result = "<div>"+result+required+"</div>"; 
			}	
			else if ( TypeEnumeration.HTML.equals(type))
			{
				String v = value == null ? "": value instanceof byte[] ? new String((byte[])value, "UTF-8") : value.toString(); 
				result = "<div>"
						+ "<html style='display: inline-block; border: solid 1px black' id='"+id+"'>"
						+ "<attribute name=\"onChange\"><![CDATA[\n" 
						+ "self.parent.parent.changeHtml (event);"
						+ "]]></attribute>" 
					  	+ "<![CDATA["
						+ (v)
						+ "]]></html>" ;
				if (!readonly)
				{
						result = result + 
							"<imageclic style='valign:top' src=\"/img/pencil.png\" width=\"1em\" >\n" + 
								"<attribute name=\"onClick\"><![CDATA[\n" + 
									"Events.sendEvent(new Event (\"onEdit\", \n" + 
										"desktop.getPage(\"htmlEditor\").getFellow(\"top\"),\n" + 
										"new Object[] {\n" + 
											"event.getTarget().getPreviousSibling()"+ 
										"}" + 
									"));" + 
								"]]></attribute>" + 
							"</imageclic>";
				}
				result = result +  "</div>";
			}
			else if ( TypeEnumeration.BOOLEAN_TYPE.equals(type))
			{
					result = "<div><checkbox  id=\""+id+"\" "
							+ "disabled=\""+readonlyExpr+"\" onCheck='self.parent.parent.onChildChange(event)'/>"+required+"</div>";
			}
			else if (dataType.getValues() != null && ! dataType.getValues().isEmpty())//String
			{
				result = "<listbox mold=\"select\" onChange=\"\" "
						+ "id=\""+id+"\" "
						+ "disabled=\""+readonlyExpr+"\" visible='true' onSelect='self.parent.parent.onChildChange(event)'>";
				result = result + "<listitem value=\"\"/>";
				for (String v: dataType.getValues())
				{
					String s = escapeString(v);
					int separator = s.indexOf(':');
					if (separator > 0)
						result = result + "<listitem value=\""+s.substring(0, separator).trim()+"\" label=\""+s.substring(separator+1).trim()+"\"/>";
					else
						result = result + "<listitem value=\""+s+"\" label=\""+s+"\"/>";
				}
				result = result + "</listbox>";
				result = "<div>"+result+required+"</div>"; 
			}
			else if (TypeEnumeration.USER_TYPE_TYPE.equals(type))
			{
				result = "<listbox mold=\"select\" onChange=\"\" "
						+ "id=\""+id+"\" "
						+ "disabled=\""+readonlyExpr+"\" visible='true' onSelect='self.parent.parent.onChildChange(event)'>";
				result = result + "<listitem value=\"\"/>";
				try {
					for (UserType v: com.soffid.iam.EJBLocator.getUserDomainService().findAllUserType())
					{
						result = result + "<listitem value=\""+escapeString(v.getCode())+ "\" label=\""+escapeString(v.getDescription())+"\"/>";
					}
				} catch (Exception e) {
					log.info("Error getting user types", e);
				}
				result = result + "</listbox>";
				result = "<div>"+result+required+"</div>"; 
			} else { // Listbox
				result = "<div><textbox sclass=\"textbox\" maxlength=\"" + size +"\" width='98%' "
						+ "id=\""+id+"\" "
						+ "readonly=\""+readonlyExpr+"\" onChange='self.parent.parent.onChildChange(event)' onOK=''/>"+required+"</div>";
			}
		}
		if (result.equals(""))
		{
			if (twoPhaseEdit && ! readonly)
				result= "<div><label id='"+id3+"'/>"
						+ "<imageclic src='/img/pencil.png' "
							+ "onClick='self.visible = self.previousSibling.visible = false; "
								+ "self.nextSibling.visible = self.nextSibling.nextSibling.visible=true'/> "
						+ "<textbox sclass=\"textbox\" width='90%' "
								+ "id=\""+id+"\" "
								+ "readonly=\""+readonlyExpr+"\" visible='false' onOK='parent.parent.changeData()' "
										+ "onChange='parent.parent.onChildChange(event)'/>"
						+ "<imageclic src='/img/accepta16.png' visible='false' onClick='parent.parent.changeData()' "
						+ "onChange='self.parent.parent.onChildChange(event)'/>"+required+"</div>";
			else
				result= "<div><textbox sclass=\"textbox\" id=\""+id+"\" width='100%' onOK='' readonly=\""+readonlyExpr+"\"/>"+required+"</div>";
		}
		if(compos.isEmpty() || !compos.equals(result))
		{
			compos=result;
			Executions.createComponentsDirectly(result, "zul", this, map);
			Component c = getFellowIfAny(id);
			if (c != null)
			{
				c.setAttribute("position", position);
				if (value != null && ! TypeEnumeration.SSO_FORM_TYPE.equals(type))
				{
					if (c instanceof Datebox) {
						if (value instanceof Date)
							((Datebox) c).setValue ((Date) value);
						else if (value instanceof Calendar)
							((Datebox) c).setValue ( ((Calendar) value ).getTime() );
						else
							((Datebox) c).setRawValue(value);
					}
					else if (c instanceof Listbox) {
						Listbox lb = (Listbox) c;
						for (Listitem item: (Collection<Listitem>)lb.getItems()){
							if (value.equals(item.getValue()))
								lb.setSelectedItem(item);
						}
					}
					else if (c instanceof InputElement) ((InputElement) c).setRawValue(value);
				}
			}
			Component c2 = getFellowIfAny(id2);
			if (c2 != null)
				c2.setAttribute("position", position);
			Component c3 = getFellowIfAny(id3);
			if (c3 != null && c3 instanceof Label && value != null)
				((Label) c3).setValue(value.toString());
			if (updateUser) updateUser(id);
			if (updateGroup) updateGroup(id);
			if (updateApplication) updateApplication(id);
			if (updateCustomObject) updateCustomObject(id);
		}
		//Aquí s'ha de fer que mostri cada camp amb el size i el type corresponen
		//A dins el zul dels usuaris falta que mostri valorDada o el blob segons estigui ple un o l'altre
	}

	private String escapeString(String v) {
		return v.replaceAll("\"", "&quot;");
	}

	private String getIdForPosition(Integer position) {
		String id = "s_"+hashCode();
		if ( position != null)
			id = id + "_p_" + position;
		return id;
	}

	private void calculateVisibility() throws EvalError, MalformedURLException {
		if (dataType.getVisibilityExpression() != null && 
				!dataType.getVisibilityExpression().trim().isEmpty())
		{
			BindContext ctx = XPathUtils.getComponentContext(this);
			String path = ctx.getXPath() + bind;
			int i = path.lastIndexOf("/attributes");
			if (i > 0)
			{
				path = path.substring(0, i);
				SecureInterpreter interp = createInterpreter();
				if ( Boolean.FALSE.equals(interp.eval(dataType.getVisibilityExpression())))
					this.setVisible(false);
				else
					this.setVisible(true);
			}
		}
		else
		{
			this.setVisible(true);
		}
	}

	public boolean isReadonly() {
		return readonly;
	}

	public void setReadonly(boolean readonly) {
		this.readonly = readonly;
	}

	private String[] getFormValues() throws UnsupportedEncodingException {
		String result[] = new String[] {"", ""};
		String v = (String) getValue();
		if (v != null)
		{
			String split [] = v.split("=");
			if (split.length > 0)
				result[0] = URLDecoder.decode(split[0], "UTF-8");
			if (split.length > 1)
				result[1] = URLDecoder.decode(split[1], "UTF-8");
		}
		return result;
	}

	private boolean fileAlreadySaved(){
		if(getValue() != null )
			return true;
		else
			return false;
	}
	
	public void upload(Component span) throws Exception {
        Media uploadData = Fileupload.get();
        if (uploadData == null) return; //Per si l'usuari pitja en Cancelar
        if (!uploadData.isBinary()) {
            throw new UiException(Messages.getString("PluginsUI.NotBinaryFileError")); //$NON-NLS-1$
        }
        byte data[];
        if (uploadData.inMemory()) {
            data = uploadData.getByteData();
        } else {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            InputStream is = uploadData.getStreamData();
            byte b[] = new byte[2024];
            int read = is.read(b);
            while (read > 0) {
                os.write(b, 0, read);
                read = is.read(b);
            }
            is.close();
            os.close();
            data = os.toByteArray();
        }
        
        binder.setValue(data);
        
        for (Iterator<?> it = span.getChildren().iterator(); it.hasNext();)
        {
        	Component c = (Component) it.next();
        	if (c instanceof Image)
        		it.remove();
        }
        org.zkoss.zul.Image img = new org.zkoss.zul.Image();
        img.setContent(byteArrayToImage(data));
        img.setParent(span);       
        img.setStyle("max-width: 100px; max-height: 100px; ");
        if (twoPhaseEdit)
        {
        	commit();
        }
    }
	

	public static AImage byteArrayToImage(byte[] bytes) throws IOException{
		
		return new AImage("photo", bytes);
	}
	
	
	public void uploadBinary() throws Exception {
        Media uploadData = Fileupload.get();
        if (uploadData == null) return; //Per si l'usuari pitja en Cancelar
        byte data[];
        if (!uploadData.isBinary()) {
	        if (uploadData.inMemory()) {
	            data = uploadData.getStringData().getBytes("UTF-8");
	        } else {
	            ByteArrayOutputStream os = new ByteArrayOutputStream();
	            Reader is = uploadData.getReaderData();
	            char b[] = new char[2048];
	            int read = is.read(b);
	            while (read > 0) {
	                os.write(new String (b,  0, read).getBytes("UTF-8"));
	                read = is.read(b);
	            }
	            is.close();
	            os.close();
	            data = os.toByteArray();
	        }
        } else {
	        if (uploadData.inMemory()) {
	            data = uploadData.getByteData();
	        } else {
	            ByteArrayOutputStream os = new ByteArrayOutputStream();
	            InputStream is = uploadData.getStreamData();
	            byte b[] = new byte[2048];
	            int read = is.read(b);
	            while (read > 0) {
	                os.write(b, 0, read);
	                read = is.read(b);
	            }
	            is.close();
	            os.close();
	            data = os.toByteArray();
	        }
        }
        binder.setValue(data);
        if (twoPhaseEdit)
        	commit();
    }
	
	public void downloadBinary(Component span) throws Exception {
		byte b[] = (byte[]) getValue();
		if(b != null)
		{
			ByteArrayInputStream is=new ByteArrayInputStream(b);
			AMedia amedia = new AMedia("Temporary", null, "binary/octet-stream", is);
			org.zkoss.zul.Iframe iframe = new org.zkoss.zul.Iframe();
			iframe.setContent(amedia);
			span = span.getParent();
			while(!(span instanceof Frame)){
				span = span.getParent();
			}
			Window w = (Window) span.getChildren().get(7);
			iframe.setParent((Component) w);
		}
		else
		{
			throw new UiException(Messages.getString("InputField.NotDocument"));
		}
	}
	
	public void setPage(Page page) {
		super.setPage(page);
		binder.setPage(page);
	}

	public Object clone() {
		InputField2 clone = (InputField2) super.clone();
		clone.bind = this.bind;
		clone.dataType = this.dataType;
		clone.compos = this.compos;
		clone.twoPhaseEdit = this.twoPhaseEdit;
		clone.updateUser = this.updateUser;
		clone.binder = new SingletonBinder (clone);
		clone.binder.setDataPath(binder.getDataPath());
		return clone;
	}
		
	
	public void updateSsoForm (Event event) throws IOException
	{
		String values[] = new String[] { "", ""};
		int i = 0;
		for (Object obj: getChildren())
		{
			if (i < 2 && obj instanceof Textbox)
			{	
				values[i++] = ((Textbox)obj).getText();
			}
		}
		binder.setValue(URLEncoder.encode(values[0], "UTF-8")
				+ "="
				+URLEncoder.encode(values[1], "UTF-8"));
		attributeValidate( null );

		Component c = this;
		do
		{
			if (c instanceof AttributesDiv)
			{
				((AttributesDiv) c).adjustVisibility();
				break;
			}
			else if (c instanceof UserAttributesDiv)
			{
				((UserAttributesDiv) c).adjustVisibility();
				break;
			}
			else
				c = c.getParent();
		} while (c != null);
	}

	public Object getOwnerObject() {
		return ownerObject;
	}

	public void setOwnerObject(Object ownerObject) {
		this.ownerObject = ownerObject;
	}
	
	public boolean attributeValidate(Integer position)
	{
		Clients.closeErrorBox(this);
		
		BindContext ctx = XPathUtils.getComponentContext(this);
		Object value = XPathUtils.getValue(ctx, bind);
		if (position != null && value instanceof List)
			value = ((List)value).get(position.intValue());

		Component input = getFellow(getIdForPosition(position));
		if (input instanceof InputElement)
		{
			InputElement inputElement = (InputElement) input;
			inputElement.clearErrorMessage();
		}
		
		if (dataType.isRequired() && ( value == null ||  "".equals(value)))
			throw new WrongValueException(input, MZul.VALUE_NOT_MATCHED);
			
		if (dataType.getType() == TypeEnumeration.APPLICATION_TYPE)
			updateApplication( getIdForPosition(position) );
		if (dataType.getType() == TypeEnumeration.USER_TYPE)
			updateUser( getIdForPosition(position) );
		if (dataType.getType() == TypeEnumeration.GROUP_TYPE)
			updateGroup( getIdForPosition(position) );
		if (dataType.getType() == TypeEnumeration.CUSTOM_OBJECT_TYPE)
			updateCustomObject( getIdForPosition(position) );
		if (dataType.getType() == TypeEnumeration.HOST_TYPE)
			updateHost( getIdForPosition(position) );
		if (dataType.getType() == TypeEnumeration.MAIL_DOMAIN_TYPE)
			updateMailDomain( getIdForPosition(position) );
		
		if (dataType.getValidationExpression() == null ||
				dataType.getValidationExpression().isEmpty())
			return true;		
		try {
			SecureInterpreter i = createInterpreter();
			Object o = i.eval(dataType.getValidationExpression());
			if (o == null)
				throw new UiException(String.format("Validation expression for attribute %s has returned a null value", dataType.getCode()));
			if (o != null && o instanceof Boolean)
			{
				if  (!((Boolean) o).booleanValue())
					throw new WrongValueException(input, MZul.VALUE_NOT_MATCHED);
			}
			else
				throw new UiException(String.format("Validation expression for attribute %s has not returned a boolean value", dataType.getCode()));
		} catch ( TargetError e) {
			if (e.getTarget() instanceof UiException)
				throw new UiException(e);
			else
				throw new RuntimeException(e.getTarget());
		} catch ( EvalError e) {
			throw new UiException(e.toString());
		} catch (MalformedURLException e) {
			throw new UiException (e.toString());
		}
		return true;
	}

	public boolean attributeVisible()
	{
		if (dataType.getVisibilityExpression() == null ||
				dataType.getVisibilityExpression().isEmpty())
			return true;
		
		try {
			SecureInterpreter i = createInterpreter();
			Object o = i.eval(dataType.getVisibilityExpression());
			if (o == null)
				throw new UiException(String.format("Visibility expression for attribute %s has returned a null value", dataType.getCode()));
			if (o != null && o instanceof Boolean)
				return ((Boolean) o).booleanValue();
			else
				throw new UiException(String.format("Visibility expression for attribute %s has not returned a boolean value", dataType.getCode()));
		} catch ( TargetError e) {
			throw new UiException(e.getTarget());
		} catch ( EvalError e) {
			throw new UiException(e.toString(), e);
		} catch (MalformedURLException e) {
			throw new UiException (e.toString());
		} catch (JXPathException e) {
			return false;
		}
	}

	private SecureInterpreter createInterpreter() throws EvalError {
		BindContext ctx = XPathUtils.getComponentContext(this);
		Object value = null;
		value = XPathUtils.getValue(ctx, bind);
		Component grandpa = getParent().getParent();
		Map attributes = grandpa instanceof UserAttributesDiv ? 
			((UserAttributesDiv) grandpa).getAttributesMap():
			(Map) XPathUtils.getValue(ctx, "/.");
		SecureInterpreter i = new SecureInterpreter();

		i.set("value", value);
		i.set("attributes", attributes);
		i.set("serviceLocator", new com.soffid.iam.EJBLocator());
		if (ownerObject != null)
		{
			i.set("object", ownerObject);
			if (ownerObject instanceof User)
				i.set("user", ownerObject);
			if (ownerObject instanceof Usuari)
			{
				i.set("user", User.toUser((Usuari) ownerObject));
				i.set("object", User.toUser((Usuari) ownerObject));
			}
			if (ownerObject instanceof Group)
				i.set("group", ownerObject);
			if (ownerObject instanceof Grup)
			{
				i.set("group", Group.toGroup((Grup) ownerObject) );
				i.set("object", Group.toGroup((Grup) ownerObject) );
			}
			if (ownerObject instanceof Role)
				i.set("role", ownerObject);
			if (ownerObject instanceof Rol)
			{
				i.set("role", Role.toRole((Rol) ownerObject));
				i.set("object", Role.toRole((Rol) ownerObject));
			}
			if (ownerObject instanceof Application)
				i.set("application", ownerObject);
			if (ownerObject instanceof Aplicacio)
			{
				i.set("application", Application.toApplication((Aplicacio) ownerObject));
				i.set("object", Application.toApplication((Aplicacio) ownerObject));
			}
			if (ownerObject instanceof Task)
			{
				i.set("task",  ownerObject);
			}
			if (ownerObject instanceof ProcessInstance)
			{
				i.set("process", ownerObject);
			}
		}
		i.set("context", ownerContext);
		return i;
	}

	public void setOwnerContext(String ownerContext) {
		this.ownerContext = ownerContext;
	}


	public void setParent(Component parent) {
		super.setParent(parent);
		binder.setParent(parent);
	}

	public boolean attributeValidateAll() {
		if(dataType != null)
		{
			Object value = binder.getValue();
			if (dataType.isMultiValued())
			{
				if (value != null && value instanceof List)
				{
					List l = (List) value;
					int i;
					for ( i = 0; i < l.size(); i++)
					{
						attributeValidate(new Integer(i));
					}
				}
			}
			else
				attributeValidate(null);
		}
		return true;
	}

	public void setSearchFilter(SearchFilter filter) {
		this.filter = filter;
	}

}