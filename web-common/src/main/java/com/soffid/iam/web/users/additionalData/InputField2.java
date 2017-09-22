package com.soffid.iam.web.users.additionalData;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.apache.commons.lang.StringEscapeUtils;
import org.zkoss.image.AImage;
import org.zkoss.util.media.AMedia;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.HtmlBasedComponent;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Div;
import org.zkoss.zul.Fileupload;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.soffid.iam.api.CustomObject;
import com.soffid.iam.api.DataType;

import es.caib.seycon.ng.EJBLocator;
import es.caib.seycon.ng.comu.Aplicacio;
import es.caib.seycon.ng.comu.Grup;
import es.caib.seycon.ng.comu.TypeEnumeration;
import es.caib.seycon.ng.comu.Usuari;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.servei.ejb.UsuariService;
import es.caib.seycon.ng.web.Messages;
import es.caib.zkib.binder.BindContext;
import es.caib.zkib.datasource.CommitException;
import es.caib.zkib.datasource.XPathUtils;
import es.caib.zkib.zkiblaf.Frame;

public class InputField2 extends Div 
{
	private static final long serialVersionUID = 1L;
	private String compos;
	DataType dataType;
	private String bind;
	
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
		p.setAttribute("type", "Device");
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

	public void onActualitzaUser(Event event) {
		String[] data = (String[]) event.getData();
		String userName = data[0];
		setValue(userName);
		updateUser();
	}

	public void onActualitzaGroup(Event event) {
		String[] data = (String[]) event.getData();
		String group = data[0];
		setValue(group);
		updateGroup();
	}

	public void onActualitzaApplication(Event event) {
		String data = (String) event.getData();
		setValue(data);
		updateApplication();
	}

	public void onActualitzaCustomObject(Event event) {
		String data = (String) event.getData();
		setValue(data);
		updateCustomObject();
	}

	private void setValue(Object userName) {
		BindContext ctx = XPathUtils.getComponentContext(this);
		XPathUtils.setValue(ctx, bind, userName);
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
		String type = "Device";
		String customObject = (String) getValue();
		Executions.getCurrent().sendRedirect("/index.zul?target=/customObjects.zul&type="+type+"&customobject=" + customObject, "_new");
	}

	private Object getValue() {
		return XPathUtils.getValue( XPathUtils.getComponentContext(this), bind );
	}
	
	public boolean updateUser()
	{
		String user = (String) getValue();
		Component c = (Component) ((Component) getChildren().get(0));
		
		Label l;
		
		if (c.getChildren().get(2) instanceof Label)
			l = (Label) c.getChildren().get(2);
		else
			l = (Label) c.getChildren().get(4);

		try {
			if (user == null)
				l.setValue("");
			else
			{
				UsuariService ejb = EJBLocator.getUsuariService();
				Usuari u = ejb.findUsuariByCodiUsuari(user);
				if (u == null)
				{
					l.setValue("?");
					return false;
				}
				l.setValue(u.getFullName());
			}
		} catch (Exception e) {
		}
		return true;
	}

	public boolean updateGroup() {

		String group = (String) getValue();
		Component c = (Component) ((Component) getChildren().get(0));
		Label l;

		if (c.getChildren().get(2) instanceof Label)
			l = (Label) c.getChildren().get(2);
		else
			l = (Label) c.getChildren().get(4);

		try {
			if (group == null)
				l.setValue("");
			else {
				Grup g = EJBLocator.getGrupService().findGrupByCodiGrup(group);
				if (g == null) {
					l.setValue("?");
					return false;
				}
				l.setValue(g.getDescripcio());
			}
		} catch (Exception e) {}
		return true;
	}

	public boolean updateApplication() {

		String application = (String) getValue();
		Component c = (Component) ((Component) getChildren().get(0));
		Label l;

		if (c.getChildren().get(2) instanceof Label)
			l = (Label) c.getChildren().get(2);
		else
			l = (Label) c.getChildren().get(4);

		try {
			if (application == null)
				l.setValue("");
			else {
				Aplicacio a = EJBLocator.getAplicacioService().findAplicacioByCodiAplicacio(application);
				if (a == null) {
					l.setValue("?");
					return false;
				}
				l.setValue(a.getNom());
			}
		} catch (Exception e) {}
		return true;
	}

	public boolean updateCustomObject() {

		String customObject = (String) getValue();
		Component c = (Component) ((Component) getChildren().get(0));
		Label l;

		if (c.getChildren().get(2) instanceof Label)
			l = (Label) c.getChildren().get(2);
		else
			l = (Label) c.getChildren().get(4);

		try {
			if (customObject == null)
				l.setValue("");
			else {
				CustomObject co = EJBLocator.getCustomObjectService().findCustomObjectByTypeAndName("Device", customObject);
				if (co == null) {
					l.setValue("?");
					return false;
				}
				l.setValue(co.getDescription());
			}
		} catch (Exception e) {}
		return true;
	}

	public synchronized void createField() throws NamingException, CreateException, InternalErrorException, IOException{
		
		if (getPage() == null)
			return;	
		
		if (disableRecursive)
			return;
		
		disableRecursive = true;
		
		try
		{
			if(dataType != null){
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
					
				if(stringType != null && !stringType.trim().isEmpty()){
					if(TypeEnumeration.USER_TYPE.equals(type))
					{
						updateUser = true;
							result = "<div style='display:inline' visible='true'>"
									+ "<textbox sclass=\"textbox\" onOK='' maxlength=\"" + size +"\" "
											+ "bind=\""+getBind()+"\" "
													+ "onChange=\"self.parent.parent.updateUser()\" readonly=\""
									+readonlyExpr+"\"/>" +
									"<imageclic src='/img/user.png' visible=\""+(!readonly)+"\" "
											+ "onClick='self.parent.parent.onSelectUser(event)' "
											+ "onActualitza='self.parent.parent.onActualitzaUser(event)' style='margin-left:2px; margin-right:2px; vertical-align:-4px' />"
									+ "<label style='text-decoration: underline; cursor:pointer' onClick='self.parent.parent.openUser()'/>"
									+ required+"</div>";
					}
					else if(TypeEnumeration.GROUP_TYPE.equals(type))
					{
						updateGroup = true;
						StringBuffer sb = new StringBuffer();
						sb.append("<div style='display:inline' visible='true'>");
						sb.append("<textbox sclass='textbox' maxlength='"+size+"' bind=\""+getBind()+"\" onChange='self.parent.parent.updateGroup()' onOK='' readonly='"+readonlyExpr+"'/>");
						sb.append("<imageclic src='/zkau/web/img/grup.gif' visible='true' onClick='self.parent.parent.onSelectGroup(event)' onActualitza='self.parent.parent.onActualitzaGroup(event)' style='margin-left:2px; margin-right:2px; vertical-align:-4px; width:16px' />");
						sb.append("<label style='text-decoration:underline; cursor:pointer' onClick='self.parent.parent.openGroup()'/>");
						sb.append(required+"</div>");
						result = sb.toString();
					}
					else if(TypeEnumeration.APPLICATION_TYPE.equals(type))
					{
						updateApplication = true;
						StringBuffer sb = new StringBuffer();
						sb.append("<div style='display:inline' visible='true'>");
						sb.append("<textbox sclass='textbox' maxlength='"+size+"' bind=\""+getBind()+"\" onChange='self.parent.parent.updateApplication()' onOK='' readonly='"+readonlyExpr+"'/>");
						sb.append("<imageclic src='/zkau/web/img/servidorHome.gif' visible='true' onClick='self.parent.parent.onSelectApplication(event)' onActualitza='self.parent.parent.onActualitzaApplication(event)' style='margin-left:2px; margin-right:2px; vertical-align:-4px; width:16px' />");
						sb.append("<label style='text-decoration:underline; cursor:pointer' onClick='self.parent.parent.openApplication()'/>");
						sb.append(required+"</div>");
						result = sb.toString();
					}
					else if(TypeEnumeration.CUSTOM_OBJECT_TYPE.equals(type))
					{
						updateCustomObject = true;
						StringBuffer sb = new StringBuffer();
						sb.append("<div style='display:inline' visible='true'>");
						sb.append("<textbox sclass='textbox' maxlength='"+size+"' bind=\""+getBind()+"\" onChange='self.parent.parent.updateCustomObject()' onOK='' readonly='"+readonlyExpr+"'/>");
						sb.append("<imageclic src='/zkau/web/img/servidorPerfils.gif' visible='true' onClick='self.parent.parent.onSelectCustomObject(event)' onActualitza='self.parent.parent.onActualitzaCustomObject(event)' style='margin-left:2px; margin-right:2px; vertical-align:-4px; width:16px' />");
						sb.append("<label style='text-decoration:underline; cursor:pointer' onClick='self.parent.parent.openCustomObject()'/>");
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
						result = "<zk><datebox bind=\""+getBind()+"\" format=\"${c:l('usuaris.zul.dateFormat2')}\" " + "disabled=\""+readonlyExpr+"\" onOK='' visible='true' />"+required+"</zk>"; 
					}
					else if(TypeEnumeration.EMAIL_TYPE.equals(type))
					{
						result = "<textbox sclass=\"textbox\" onOK=''  maxlength=\"" + size +"\" bind=\""+getBind()+"\" onChange=\"\" width='100%' visible='true' "
									+ "readonly=\""+readonlyExpr+"\" constraint=\"/(^$|.+@.+\\.[a-z]+)/: ${c:l('InputField.NoCorrectEmail')}\"/>";
						if (required.length() > 0)
						{
							result = "<zk>"+result+required+"</zk>";
						}
					}	
					else if(TypeEnumeration.SSO_FORM_TYPE.equals(type))
					{
						String []split = getFormValues ();
						result = "<zk><textbox sclass=\"textbox\" maxlength=\"" + size/2 +"\" onChange=\"self.parent.updateSsoForm()\" width='40%'  "
									+ "readonly=\""+readonlyExpr+"\" onOK='' value='"+StringEscapeUtils.escapeXml(split[0])+"'/>" 
									+ "<label value=' = '/>"
									+ "<textbox sclass=\"textbox\" maxlength=\"" + size/2 +"\" onChange=\"self.parent.updateSsoForm()\" width='40%'  "
									+ "readonly=\""+readonlyExpr+"\" onOK='' value='"+StringEscapeUtils.escapeXml(split[1])+"'/>"
									+ "</zk>";
						if (required.length() > 0)
						{
							result = "<zk>"+result+required+"</zk>"; 
								
						}
					}	
					else if (dataType.getValues() == null || dataType.getValues().isEmpty())//String
					{
							result = "<zk><textbox sclass=\"textbox\" maxlength=\"" + size +"\" bind=\""+getBind()+"\" onChange=\"\" width='98%' "
									+ "readonly=\""+readonlyExpr+"\"/>"+required+"</zk>";
					} else { // Listbox
						result = "<listbox mold=\"select\" bind=\""+getBind()+"\" onChange=\"\" "
								+ "disabled=\""+readonlyExpr+"\" visible='true'>";
						result = result + "<listitem value=\"\"/>";
						for (String v: dataType.getValues())
						{
							String s = v.replaceAll("\"", "&quot;");
							result = result + "<listitem value=\""+s+"\" label=\""+s+"\"/>";
						}
						result = result + "</listbox>";
						if (required.length() > 0)
						{
							result = "<zk>"+result+required+"</zk>"; 
								
						}
					}
				}
				if (result.equals(""))
				{
					if (twoPhaseEdit && ! readonly)
						result= "<div style='display:inline-block;'><label bind='"+getBind()+"'/>"
								+ "<imageclic src='/img/pencil.png' "
									+ "onClick='self.visible = self.previousSibling.visible = false; "
										+ "self.nextSibling.visible = self.nextSibling.nextSibling.visible=true'/> "
								+ "<textbox sclass=\"textbox\" bind=\""+getBind()+"\" width='90%' "
										+ "readonly=\""+readonlyExpr+"\" visible='false' onOK='parent.parent.changeData()'/>"
								+ "<imageclic src='/img/accepta16.png' visible='false' onClick='parent.parent.changeData()'/>"+required+"</div>";
					else
						result= "<zk><textbox sclass=\"textbox\" bind=\""+getBind()+"\" width='100%' onOK='' readonly=\""+readonlyExpr+"\"/>"+required+"</zk>";
				}
				if(compos.isEmpty() || !compos.equals(result))
				{
					while (!getChildren().isEmpty())
					{
						((Component)getChildren().get(0)).setParent(null);
					}
					compos=result;
					Executions.createComponentsDirectly(result, "zul", this, map);
					if (updateUser) updateUser();
					if (updateGroup) updateGroup();
					if (updateApplication) updateApplication();
					if (updateCustomObject) updateCustomObject();
				}
				//Aquí s'ha de fer que mostri cada camp amb el size i el type corresponen
				//A dins el zul dels usuaris falta que mostri valorDada o el blob segons estigui ple un o l'altre
			}
		} catch (Throwable e) {
			e.printStackTrace();
			// Ignore
		} finally {
			disableRecursive = false;
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
        setValue(data);
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
        setValue(data);
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
	}

	public Object clone() {
		InputField2 clone = (InputField2) super.clone();
		clone.bind = this.bind;
		clone.dataType = this.dataType;
		clone.compos = this.compos;
		clone.twoPhaseEdit = this.twoPhaseEdit;
		clone.updateUser = this.updateUser;
		return clone;
	}
		
	
	public void updateSsoForm () throws UnsupportedEncodingException
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
		setValue(URLEncoder.encode(values[0], "UTF-8")
				+ "="
				+URLEncoder.encode(values[1], "UTF-8"));
	}
}