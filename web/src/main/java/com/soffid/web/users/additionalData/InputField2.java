package com.soffid.web.users.additionalData;

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
import javax.naming.InitialContext;
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

import com.soffid.iam.api.AttributeVisibilityEnum;
import com.soffid.iam.api.DataType;

import es.caib.seycon.ng.EJBLocator;
import es.caib.seycon.ng.comu.TipusDada;
import es.caib.seycon.ng.comu.TypeEnumeration;
import es.caib.seycon.ng.comu.Usuari;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.servei.ejb.SelfService;
import es.caib.seycon.ng.servei.ejb.SelfServiceHome;
import es.caib.seycon.ng.servei.ejb.UsuariService;
import es.caib.seycon.ng.servei.ejb.UsuariServiceHome;
import es.caib.seycon.ng.web.Messages;
import es.caib.zkib.binder.BindContext;
import es.caib.zkib.binder.SingletonBinder;
import es.caib.zkib.datasource.CommitException;
import es.caib.zkib.datasource.XPathUtils;
import es.caib.zkib.events.XPathEvent;
import es.caib.zkib.events.XPathSubscriber;
import es.caib.zkib.events.XPathValueEvent;
import es.caib.zkib.jxpath.JXPathNotFoundException;
import es.caib.zkib.zkiblaf.Frame;

public class InputField2 extends Div 
{

	/**
	 * 
	 */
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
	private boolean readonly;
	
	
	public void onSelectUser (Event event)
	{
		Page p = getDesktop().getPage("usuarisLlista");
		Events.postEvent("onInicia", p
				.getFellow("esquemaLlista"), event.getTarget());

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
	
	public void onActualitza(Event event)
	{
		String [] data = (String[]) event.getData();
		String userName = data[0];
		setValue(userName);
		updateUser();
	}

	private void setValue(Object userName) {
		BindContext ctx = XPathUtils.getComponentContext(this);
		XPathUtils.setValue(ctx, bind, userName);
	}
	
	private void commit() throws CommitException {
		XPathUtils.getComponentContext(this).getDataSource().commit();
	}

	public void openUser ()
	{
		String user = (String) getValue();
		Executions.getCurrent().sendRedirect("/index.zul?target=/usuaris.zul?user="+user, "_new");
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
	
	public synchronized void createField() throws NamingException, CreateException, InternalErrorException, IOException{
		
		if (getPage() == null)
			return;	
		
		if (disableRecursive)
			return;
		
		disableRecursive = true;
		
		try
		{
			
			BindContext bindCtx = XPathUtils.getComponentContext(this);
			if(dataType != null){
				String result = "";
				Map <String,Object> map=new HashMap<String, Object>();
				updateUser = false;
				boolean dualEdit = twoPhaseEdit && ! readonly;
				String readonlyExpr = readonly ? 
						"true" : "false";
	
				
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
						if (dualEdit)
						{
							result= "<div style='display:inline;'>"
									+ "<label bind=\""+getBind()+"\" />"
									+ "<imageclic src='/img/pencil.png' "
										+ "onClick='self.visible = self.previousSibling.visible = false; "
											+ "self.nextSibling.visible = self.nextSibling.nextSibling.visible=true'/> "
									+ "<textbox sclass=\"textbox\" onOK='' maxlength=\"" + size +"\" bind=\""+getBind()+"\" "
											+ "onChange=\"self.parent.parent.updateUser()\" readonly=\""
										+readonlyExpr+"\" visible='false'/>" 
									+ "<imageclic src='/img/accepta16.png' visible='false' onClick='self.parent.parent.changeData()'/>"
									+ "<label/>"+required+"</div>";
						} else {
							result = "<div style='display:inline' visible='"+(!dualEdit)+"'>"
									+ "<textbox sclass=\"textbox\" onOK='' maxlength=\"" + size +"\" "
											+ "bind=\""+getBind()+"\" "
													+ "onChange=\"self.parent.parent.updateUser()\" readonly=\""
									+readonlyExpr+"\"/>" +
									"<imageclic src='/img/user.png' visible=\""+(!readonly)+"\" "
											+ "onClick='self.parent.parent.onSelectUser(event)' "
											+ "onActualitza='self.parent.parent.onActualitza(event)'/>"
									+ "<label style='text-decoration: underline; cursor:pointer' onClick='self.parent.parent.openUser()'/>"
									+ required+"</div>";
						}
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
						result = "<zk><datebox bind=\""+getBind()+"\" format=\"${c:l('usuaris.zul.dateFormat2')}\" " + "disabled=\""+readonlyExpr+"\" onOK='' visible='"+(!dualEdit)+"' />"+required+"</zk>"; 
						if (dualEdit)
						{
							result= "<div style='display:inline-block;'><datebox bind=\""+getBind()+"\" disabled='true' format=\"${c:l('usuaris.zul.dateFormat2')}\""
									+ " onChange=\"\" onOK='' />"
									+ "<imageclic src='/img/pencil.png' "
										+ "onClick='self.visible = self.previousSibling.visible = false; "
											+ "self.nextSibling.visible = self.nextSibling.nextSibling.visible=true'/> "
									+ result
									+ "<imageclic src='/img/accepta16.png' visible='false' onClick='self.parent.parent.changeData()'/></div>";
						}
					}
					else if(TypeEnumeration.EMAIL_TYPE.equals(type))
					{
						result = "<textbox sclass=\"textbox\" onOK=''  maxlength=\"" + size +"\" bind=\""+getBind()+"\" onChange=\"\" width='100%' visible='"+(!dualEdit)+"' "
									+ "readonly=\""+readonlyExpr+"\" constraint=\"/(^$|.+@.+\\.[a-z]+)/: ${c:l('InputField.NoCorrectEmail')}\"/>";
						if (dualEdit)
						{
							result= "<div style='display:inline-block;'><label bind='"+getBind()+"'/>"
									+ "<imageclic src='/img/pencil.png' "
										+ "onClick='self.visible = self.previousSibling.visible = false; "
											+ "self.nextSibling.visible = self.nextSibling.nextSibling.visible=true'/> "
									+ result
									+ "<imageclic src='/img/accepta16.png' visible='false' onClick='self.parent.parent.changeData()'/>"+required+"</div>";
						}
						else if (required.length() > 0)
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
					else if ( TypeEnumeration.HTML.equals(type))
					{
						Object v = getValue();
						result = "<div>"
								+ "<html style='display: inline-block; border: solid 1px black'>"
								+ "<attribute name=\"onChange\"><![CDATA[\n" +
									"self.parent.parent.changeHtml (event.data);"+
								  "]]></attribute>" 
								+ "<![CDATA["
								+ (v == null ? "": 
									v instanceof String ? v:
									new String((byte[]) v, "UTF-8"))
								+ "]]></html>" ;
						if (!readonly)
						{
								result = result + 
									"<imageclic style='valign:top' src=\"/img/pencil.png\" width=\"1em\" >\n" + 
										"<attribute name=\"onClick\"><![CDATA[\n" + 
											"Events.sendEvent(new Event (\"onEdit\", \n" + 
												"desktop.getPage(\"htmlEditor\").getFellow(\"top\"),\n" + 
												"new Object[] {\n" + 
													"event.getTarget().getPreviousSibling(),"+ 
												"}" + 
											"));" + 
										"]]></attribute>" + 
									"</imageclic>";
						}
						result = result +  "</div>";
					}
					else if (dataType.getValues() == null || dataType.getValues().isEmpty())//String
					{
						Object v = XPathUtils.getValue(this, getBind());
						if (dualEdit)
							result= result + "<div style='display:inline-block;'><label bind='"+getBind()+"'/>"
									+ "<imageclic src='/img/pencil.png' "
										+ "onClick='self.visible = self.previousSibling.visible = false; "
											+ "self.nextSibling.visible = self.nextSibling.nextSibling.visible=true'/> "
									+ "<textbox sclass=\"textbox\" bind=\""+getBind()+"\" maxlength=\"" + size +"\" width='70%' "
											+ "readonly=\""+readonlyExpr+"\" visible='false' onOK='self.parent.parent.changeData()'/>"
									+ "<imageclic src='/img/accepta16.png' visible='false' onClick='self.parent.parent.changeData()'/>"+required+"</div>";
						else
							result = "<zk><textbox sclass=\"textbox\" maxlength=\"" + size +"\" bind=\""+getBind()+"\" onChange=\"\" width='100%' "
									+ "readonly=\""+readonlyExpr+"\"/>"+required+"</zk>";
					} else { // Listbox
						result = "<listbox mold=\"select\" bind=\""+getBind()+"\" onChange=\"\" "
								+ "disabled=\""+readonlyExpr+"\" visible='"+(!dualEdit)+"'>";
						result = result + "<listitem value=\"\"/>";
						for (String v: dataType.getValues())
						{
							String s = v.replaceAll("\"", "&quot;");
							result = result + "<listitem value=\""+s+"\" label=\""+s+"\"/>";
						}
						result = result + "</listbox>";
						if (dualEdit)
						{
							result= "<div style='display:inline-block;'><label bind='"+getBind()+"'/>"
									+ "<imageclic src='/img/pencil.png' "
										+ "onClick='self.visible = self.previousSibling.visible = false; onOK=''  "
											+ "self.nextSibling.visible = self.nextSibling.nextSibling.visible = self.nextSibling.nextSibling.visible=true'/> "
									+ result
									+ "<imageclic src='/img/accepta16.png' visible='false' onClick='self.parent.parent.changeData()'/>"+required+"</div>";
						}
						else if (required.length() > 0)
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
					if (updateUser) updateUser ();
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

	private boolean testEmail(String value) {
		boolean isEmail = false;
		if(value.contains("@"))
			isEmail = true;
		return isEmail;
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
        for (Iterator it = span.getChildren().iterator(); it.hasNext();)
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
	

	public void changeHtml(String text) throws Exception {
        byte data[] = text.getBytes("UTF-8");
        setValue(data);
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