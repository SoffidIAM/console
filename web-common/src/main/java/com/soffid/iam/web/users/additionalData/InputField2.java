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
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.xml.xpath.XPathException;

import org.apache.commons.lang.StringEscapeUtils;
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
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Fileupload;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;
import org.zkoss.zul.impl.InputElement;
import org.zkoss.zul.mesg.MZul;

import com.soffid.iam.api.Application;
import com.soffid.iam.api.CustomObject;
import com.soffid.iam.api.DataType;
import com.soffid.iam.api.Group;
import com.soffid.iam.api.Role;
import com.soffid.iam.api.User;
import com.soffid.iam.service.impl.bshjail.SecureInterpreter;

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
import es.caib.zkib.component.DataTextbox;
import es.caib.zkib.datasource.CommitException;
import es.caib.zkib.datasource.XPathUtils;
import es.caib.zkib.jxpath.JXPathException;
import es.caib.zkib.zkiblaf.Frame;

public class InputField2 extends Div 
{
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
		Events.postEvent("onInicia", p.getFellow("esquemaLlista"), event.getTarget());
	}

	public void onSelectGroup(Event event) {
		Page p = getDesktop().getPage("grupsLlista");
		p.setAttribute("tipus", "");
		p.setAttribute("llistaObsolets", false);
		Events.postEvent("onInicia", p.getFellow("esquemaLlista"), event.getTarget());
	}

	public void onSelectApplication(Event event) {
		Page p = getDesktop().getPage("aplicacionsLlista");
		Events.postEvent("onInicia", p.getFellow("esquemaLlista"), event.getTarget());
	}

	public void onSelectCustomObject(Event event) {
		Page p = getDesktop().getPage("customObjectsLlista");
		p.setAttribute("type", dataType.getDataObjectType());
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

	public void onActualitzaUser(Event event) throws UnsupportedEncodingException, IOException {
		String[] data = (String[]) event.getData();
		String userName = data[0];
		((InputElement) event.getTarget().getPreviousSibling()).setRawValue(userName);
		onChange( new Event (event.getName(), event.getTarget().getPreviousSibling() ) );
	}

	public void onChange(Event event) throws UnsupportedEncodingException, IOException {
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

	public void onActualitzaGroup(Event event) throws UnsupportedEncodingException, IOException {
		String[] data = (String[]) event.getData();
		String group = data[0];
		((InputElement) event.getTarget().getPreviousSibling()).setRawValue(group);
		onChange( new Event (event.getName(), event.getTarget().getPreviousSibling() ) );
	}

	public void onActualitzaApplication(Event event) throws UnsupportedEncodingException, IOException {
		String data = (String) event.getData();
		((InputElement) event.getTarget().getPreviousSibling()).setRawValue(data);
		onChange( new Event (event.getName(), event.getTarget().getPreviousSibling() ) );
	}

	public void onActualitzaCustomObject(Event event) throws UnsupportedEncodingException, IOException {
		String data = (String) event.getData();
		((InputElement) event.getTarget().getPreviousSibling()).setRawValue(data);
		onChange( new Event (event.getName(), event.getTarget().getPreviousSibling() ) );
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
		String user = ( (InputElement) getFellow(id)).getText();
		Component c = (Component) ((Component) getChildren().get(0));
		
		Label l = (Label) getFellowIfAny(id+"b");
		
		Usuari u = null;
		if (user == null || user.isEmpty())
		{
			if (l != null) l.setValue("");
		}
		else
		{
			try {
				UsuariService ejb = EJBLocator.getUsuariService();
				u = ejb.findUsuariByCodiUsuari(user);
			} catch (Exception e) {
			}
			if (u == null)
			{
				if (l != null) l.setValue("?");
				throw new WrongValueException(this, MZul.VALUE_NOT_MATCHED);
			}
			else
			{
				if (l != null) l.setValue(u.getFullName());
			}
		}
		
		return true;
	}

	public boolean updateGroup(String id) {

		String group = ( (InputElement) getFellow(id)).getText();
		Component c = (Component) ((Component) getChildren().get(0));

		Label l = (Label) getFellowIfAny(id+"b");

		
		if (group == null || group.isEmpty())
		{
			if (l != null) l.setValue("");
		}
		else {
			Grup g = null;
			try {
				g = EJBLocator.getGrupService().findGrupByCodiGrup(group);
			} catch (Exception e) {}
			if (g == null) {
				if (l != null) l.setValue("?");
				throw new WrongValueException(this, MZul.VALUE_NOT_MATCHED);
			}
			if (l != null )
				l.setValue(g.getDescripcio());
		}
		return true;
	}

	public void updateApplication(String id) {

		String application = ( (InputElement) getFellow(id)).getText();
		Component c = (Component) ((Component) getChildren().get(0));

		Label l = (Label) getFellowIfAny(id+"b");

		if (application == null || application.isEmpty()) {
			if (l != null)
				l.setValue("");
		} else {
			Aplicacio a = null;
			try {
				a = EJBLocator.getAplicacioService().findAplicacioByCodiAplicacio(application);
			} catch (Exception e) {}
			if (a == null) {
				if (l != null) l.setValue("?");
				throw new WrongValueException(this, MZul.VALUE_NOT_MATCHED);
			}
			if (l != null) l.setValue(a.getNom());
		}
	}

	public void updateCustomObject(String id) {
		String customObject = ( (InputElement) getFellow(id)).getText();
		Component c = (Component) ((Component) getChildren().get(0));

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
			if (co == null) {
				if (l != null) l.setValue("?");
				throw new WrongValueException(this, MZul.VALUE_NOT_MATCHED);
			}
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
						binder.setValue(value);
					}
					if (value instanceof List)
					{
						List l = (List) value;
						int i;
						for ( i = 0; i < l.size(); i++)
						{
							createFieldElement(new Integer(i), l.get(i));
						}
						createFieldElement(new Integer(i), null);
					}
				}
				else
					createFieldElement(null, value);
			}
		} catch (Throwable e) {
			e.printStackTrace();
			// Ignore
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
											+ "onChange=\"self.parent.parent.onChange(event)\" readonly=\""
							+readonlyExpr+ "\"/>" +
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
				sb.append("<textbox sclass='textbox' maxlength='"+size+"' onChange='self.parent.parent.onChange(event)' onOK='' "
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
				sb.append("<textbox sclass='textbox' maxlength='"+size+"' onChange='self.parent.parent.onChange(event)' onOK='' "
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
			else if(TypeEnumeration.CUSTOM_OBJECT_TYPE.equals(type))
			{
				updateCustomObject = true;
				StringBuffer sb = new StringBuffer();
				sb.append("<div style='display:block' visible='true'>");
				sb.append("<textbox sclass='textbox' maxlength='"+size+"' onChange='self.parent.parent.onChange(event)' onOK='' "
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
						+ "id=\""+id+"\" "
						+ "onChange='self.parent.parent.onChange(event)'/>"+required+"</div>"; 
			}
			else if(TypeEnumeration.EMAIL_TYPE.equals(type))
			{
				result = "<textbox sclass=\"textbox\" onOK=''  maxlength=\"" + size +"\"  width='100%' visible='true' "
						+ "id=\""+id+"\" "
							+ "readonly=\""+readonlyExpr+"\" constraint=\"/(^$|.+@.+\\.[a-z]+)/: ${c:l('InputField.NoCorrectEmail')}\" "
									+ "onChange='self.parent.onChange(event)'/>";
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
			else if (dataType.getValues() == null || dataType.getValues().isEmpty())//String
			{
					result = "<div><textbox sclass=\"textbox\" maxlength=\"" + size +"\" width='98%' "
							+ "id=\""+id+"\" "
							+ "readonly=\""+readonlyExpr+"\" onChange='self.parent.parent.onChange(event)' onOK=''/>"+required+"</div>";
			} else { // Listbox
				result = "<listbox mold=\"select\" onChange=\"\" "
						+ "id=\""+id+"\" "
						+ "disabled=\""+readonlyExpr+"\" visible='true' onSelect='self.parent.parent.onChange(event)'>";
				result = result + "<listitem value=\"\"/>";
				for (String v: dataType.getValues())
				{
					String s = v.replaceAll("\"", "&quot;");
					result = result + "<listitem value=\""+s+"\" label=\""+s+"\"/>";
				}
				result = result + "</listbox>";
				result = "<div>"+result+required+"</div>"; 
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
										+ "onChange='parent.parent.onChange(event)'/>"
						+ "<imageclic src='/img/accepta16.png' visible='false' onClick='parent.parent.changeData()' "
						+ "onChange='self.parent.parent.onChange(event)'/>"+required+"</div>";
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

		if (dataType.isRequired() && ( value == null ||  "".equals(value)))
			throw new WrongValueException(this, MZul.EMPTY_NOT_ALLOWED);
			
		if (dataType.getType() == TypeEnumeration.APPLICATION_TYPE)
			updateApplication( getIdForPosition(position) );
		if (dataType.getType() == TypeEnumeration.USER_TYPE)
			updateUser( getIdForPosition(position) );
		if (dataType.getType() == TypeEnumeration.GROUP_TYPE)
			updateGroup( getIdForPosition(position) );
		if (dataType.getType() == TypeEnumeration.CUSTOM_OBJECT_TYPE)
			updateCustomObject( getIdForPosition(position) );
		
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
					throw new WrongValueException(this, "Invalid value");
			}
			else
				throw new UiException(String.format("Validation expression for attribute %s has not returned a boolean value", dataType.getCode()));
		} catch ( TargetError e) {
			if (e.getTarget() instanceof UiException)
				throw new WrongValueException(this, e.getMessage());
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
			throw new WrongValueException(e.getMessage(), e);
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
				if (value instanceof List)
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

}