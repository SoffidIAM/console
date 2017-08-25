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

import com.soffid.iam.api.AttributeVisibilityEnum;

import es.caib.seycon.ng.EJBLocator;
import es.caib.seycon.ng.comu.TipusDada;
import es.caib.seycon.ng.comu.TypeEnumeration;
import es.caib.seycon.ng.comu.Usuari;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.servei.ejb.SelfService;
import es.caib.seycon.ng.web.Messages;
import es.caib.zkib.binder.BindContext;
import es.caib.zkib.binder.SingletonBinder;
import es.caib.zkib.datasource.CommitException;
import es.caib.zkib.datasource.XPathUtils;
import es.caib.zkib.events.XPathEvent;
import es.caib.zkib.events.XPathSubscriber;
import es.caib.zkib.events.XPathValueEvent;
import es.caib.zkib.zkiblaf.Frame;

public class InputField extends Div implements XPathSubscriber{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String compos;
	boolean userType;
	
	private SingletonBinder binder = new SingletonBinder(this);
	private SingletonBinder binder3 = new SingletonBinder(this);
	private SingletonBinder binder4 = new SingletonBinder(this);
	private boolean twoPhaseEdit;

	public boolean isTwoPhaseEdit() {
		return twoPhaseEdit;
	}

	public void setTwoPhaseEdit(boolean twoPhaseEdit) {
		this.twoPhaseEdit = twoPhaseEdit;
	}

	public InputField(){
		super();
		compos = new String();
	}
	
	public void onCreate () throws NamingException, CreateException, InternalErrorException, IOException
	{
		binder.setDataPath("@valorDada");
		binder3.setDataPath("@blobDataValue");
		binder4.setDataPath("@valorDadaDate");
		createField();
	}
	
	public void onUpdate(XPathEvent arg0) 
	{
		try {
			if (! ( arg0 instanceof XPathValueEvent))
			{
				binder.setDataPath("@valorDada");
				binder3.setDataPath("@blobDataValue");
				binder4.setDataPath("@valorDadaDate");
				compos = new String();
				createField();
			}
			else if (updateUser)
				updateUser();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	boolean disableRecursive = false;
	private boolean updateUser;
	
	
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
		binder.getDataSource().commit();
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
		binder.setValue(userName);
	}
	
	public void openUser ()
	{
		Executions.getCurrent().sendRedirect("/index.zul?target=/usuaris.zul?user="+binder.getValue(), "_new");
	}
	
	public boolean updateUser()
	{
		String user = (String) binder.getValue();
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
				Usuari u = EJBLocator.getUsuariService().findUsuariByCodiUsuari(user);
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
			SelfService ejb = EJBLocator.getSelfService();
			BindContext bindCtx = XPathUtils.getComponentContext(this);
			String dataType = (String) XPathUtils.getValue(bindCtx, "@codiDada");
			String systemName = (String) XPathUtils.getValue(bindCtx, "@systemName");
			if(dataType != null){
				String result = "";
				Map <String,Object> map=new HashMap<String, Object>();
				updateUser = false;
				AttributeVisibilityEnum visibility = (AttributeVisibilityEnum) XPathUtils.getValue(bindCtx, "@visibility");
	
				boolean readonly = AttributeVisibilityEnum.READONLY.equals(visibility);
				boolean dualEdit = twoPhaseEdit && ! readonly;
				String readonlyExpr = readonly ? 
						"true" : "${!canUpdateUserMetadata}";
	
				TipusDada typeData;
				typeData = ejb.getDataTypeDescription(systemName, dataType);
					
				
				
				TypeEnumeration type = typeData.getType();
				String stringType = new String();
				if(type!=null)
					stringType = type.toString();
				int size = 0;
				if(typeData.getSize() != null)
					size = typeData.getSize();
				String required = "";
				if (typeData.isRequired())
					required = "*";
					
				if(stringType != null && !stringType.trim().isEmpty()){
					if(TypeEnumeration.USER_TYPE.equals(type))
					{
						updateUser = true;
						if (dualEdit)
						{
							result= "<div style='display:inline;'>"
									+ "<label bind=\"@valorDada\" />"
									+ "<imageclic src='/img/pencil.png' "
										+ "onClick='self.visible = self.previousSibling.visible = false; "
											+ "self.nextSibling.visible = self.nextSibling.nextSibling.visible=true'/> "
									+ "<textbox sclass=\"textbox\" maxlength=\"" + size +"\" bind=\"@valorDada\" onChange=\"\" onOK=\"\" readonly=\""
										+readonlyExpr+"\" visible='false'/>" 
									+ "<imageclic src='/img/accepta16.png' visible='false' onClick='self.parent.parent.changeData()'/>"
									+ "<label/>"+required+"</div>";
						} else {
							result = "<div style='display:inline' visible='"+(!dualEdit)+"'>"
									+ "<textbox sclass=\"textbox\" maxlength=\"" + size +"\" bind=\"@valorDada\" onChange=\"\"  onOK=\"\" readonly=\""
									+readonlyExpr+"\"/>" +
									"<imageclic src='/img/user.png' visible=\""+(!readonly)+"\" onClick='self.parent.parent.onSelectUser(event)' onActualitza='self.parent.parent.onActualitza(event)'/>"
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
									"</button><button label=\"Download\" disabled=\"${!canUpdateUserMetadata}\" visible=\"" + visible + "\" onClick=\"self.parent.parent.downloadBinary(self.parent);\">" +
									"</button>"+required+"</h:span>";
					}
					else if(TypeEnumeration.PHOTO_TYPE.equals(type))
					{
						if(binder3.getValue() != null){
							map.put("image", byteArrayToImage(getBlobDataValue()));
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
						result = "<zk><datebox bind=\"@valorDadaDate\" format=\"${c:l('usuaris.zul.dateFormat2')}\" " + "disabled=\""+readonlyExpr+"\" visible='"+(!dualEdit)+"' />"+required+"</zk>"; 
						if (dualEdit)
						{
							result= "<div style='display:inline-block;'><datebox bind=\"@valorDadaDate\" disabled='true' format=\"${c:l('usuaris.zul.dateFormat2')}\" onChange=\"\" />"
									+ "<imageclic src='/img/pencil.png' "
										+ "onClick='self.visible = self.previousSibling.visible = false; "
											+ "self.nextSibling.visible = self.nextSibling.nextSibling.visible=true'/> "
									+ result
									+ "<imageclic src='/img/accepta16.png' visible='false' onClick='self.parent.parent.changeData()'/></div>";
						}
					}
					else if(TypeEnumeration.EMAIL_TYPE.equals(type))
					{
						result = "<textbox sclass=\"textbox\" maxlength=\"" + size +"\" bind=\"@valorDada\" onChange=\"\" width='80%' visible='"+(!dualEdit)+"' "
									+ "readonly=\""+readonlyExpr+"\" constraint=\"/(^$|.+@.+\\.[a-z]+)/: ${c:l('InputField.NoCorrectEmail')}\"/>";
						if (dualEdit)
						{
							result= "<div style='display:inline-block;'><label bind='@valorDada'/>"
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
									+ "readonly=\""+readonlyExpr+"\" value='"+StringEscapeUtils.escapeXml(split[0])+"'/>" 
									+ "<label value=' = '/>"
									+ "<textbox sclass=\"textbox\" maxlength=\"" + size/2 +"\" onChange=\"self.parent.updateSsoForm()\" width='40%'  "
									+ "readonly=\""+readonlyExpr+"\" value='"+StringEscapeUtils.escapeXml(split[1])+"'/>"
									+ "</zk>";
						if (required.length() > 0)
						{
							result = "<zk>"+result+required+"</zk>"; 
								
						}
					}	
					else if (typeData.getValues() == null || typeData.getValues().isEmpty())//String
					{
						if (dualEdit)
							result= result + "<div style='display:inline-block;'><label bind='@valorDada'/>"
									+ "<imageclic src='/img/pencil.png' "
										+ "onClick='self.visible = self.previousSibling.visible = false; "
											+ "self.nextSibling.visible = self.nextSibling.nextSibling.visible=true'/> "
									+ "<textbox sclass=\"textbox\" bind=\"@valorDada\" maxlength=\"" + size +"\" width='80%' "
											+ "readonly=\""+readonlyExpr+"\" visible='false' onOK='self.parent.parent.changeData()'/>"
									+ "<imageclic src='/img/accepta16.png' visible='false' onClick='self.parent.parent.changeData()'/>"+required+"</div>";
						else
							result = "<zk><textbox sclass=\"textbox\" maxlength=\"" + size +"\" bind=\"@valorDada\" onChange=\"\" width='80%' "
									+ "readonly=\""+readonlyExpr+"\"/>"+required+"</zk>";
					} else { // Listbox
						result = "<listbox mold=\"select\" bind=\"@valorDada\" onChange=\"\" "
								+ "disabled=\""+readonlyExpr+"\" visible='"+(!dualEdit)+"'>";
						result = result + "<listitem value=\"\"/>";
						for (String v: typeData.getValues())
						{
							String s = v.replaceAll("\"", "&quot;");
							result = result + "<listitem value=\""+s+"\" label=\""+s+"\"/>";
						}
						result = result + "</listbox>";
						if (dualEdit)
						{
							result= "<div style='display:inline-block;'><label bind='@valorDada'/>"
									+ "<imageclic src='/img/pencil.png' "
										+ "onClick='self.visible = self.previousSibling.visible = false; "
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
						result= "<div style='display:inline-block;'><label bind='@valorDada'/>"
								+ "<imageclic src='/img/pencil.png' "
									+ "onClick='self.visible = self.previousSibling.visible = false; "
										+ "self.nextSibling.visible = self.nextSibling.nextSibling.visible=true'/> "
								+ "<textbox sclass=\"textbox\" bind=\"@valorDada\" width='70%' "
										+ "readonly=\""+readonlyExpr+"\" visible='false' onOK='parent.parent.changeData()'/>"
								+ "<imageclic src='/img/accepta16.png' visible='false' onClick='parent.parent.changeData()'/>"+required+"</div>";
					else
						result= "<zk><textbox sclass=\"textbox\" bind=\"@valorDada\" width='80%' readonly=\""+readonlyExpr+"\"/>"+required+"</zk>";
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

	private String[] getFormValues() throws UnsupportedEncodingException {
		String result[] = new String[] {"", ""};
		String v = (String) binder.getValue();
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
		if(getBlobDataValue() != null )
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
        setBlobDataValue(data);
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
        	binder.getDataSource().commit();
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
        setBlobDataValue(data);
        if (twoPhaseEdit)
        	binder.getDataSource().commit();
    }
	
	public void downloadBinary(Component span) throws Exception {
		byte b[] = getBlobDataValue();
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

	public void setParent(Component parent) {
		super.setParent(parent);
		binder.setParent(parent);
		binder3.setParent(parent);
		binder4.setParent(parent);
/*
		if (parent != null && getPage() != null)
		{
			try {
				createField();
			} catch (NamingException e) {
			} catch (CreateException e) {
			} catch (InternalErrorException e) {
			} catch (IOException e) {
			}
		}
*/
	}

	public Object clone() {
		InputField clone = (InputField) super.clone();
		clone.binder = new SingletonBinder(clone);
		clone.binder.setDataPath(binder.getDataPath());
		clone.binder3 = new SingletonBinder(clone);
		clone.binder3.setDataPath(binder3.getDataPath());
		clone.binder4 = new SingletonBinder(clone);
		clone.binder4.setDataPath(binder4.getDataPath());
		return clone;
	}
		
	public void setBlobDataValue(byte[] d)
	{
		binder3.setValue(d);
	}
	 
	public byte[] getBlobDataValue()
	{
		return (byte[]) binder3.getValue();
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
		binder.setValue(URLEncoder.encode(values[0], "UTF-8")
				+ "="
				+URLEncoder.encode(values[1], "UTF-8"));
	}
}