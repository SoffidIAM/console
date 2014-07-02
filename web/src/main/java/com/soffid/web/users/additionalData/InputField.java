package com.soffid.web.users.additionalData;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.CreateException;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.zkoss.image.AImage;
import org.zkoss.util.media.AMedia;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.ComponentNotFoundException;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Div;
import org.zkoss.zul.Fileupload;
import org.zkoss.zul.Label;
import org.zkoss.zul.Window;

import es.caib.seycon.ng.comu.TipusDada;
import es.caib.seycon.ng.comu.TypeEnumeration;
import es.caib.seycon.ng.comu.Usuari;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.servei.ejb.DadesAddicionalsService;
import es.caib.seycon.ng.servei.ejb.DadesAddicionalsServiceHome;
import es.caib.seycon.ng.servei.ejb.UsuariService;
import es.caib.seycon.ng.servei.ejb.UsuariServiceHome;
import es.caib.seycon.ng.web.Messages;
import es.caib.zkib.binder.SingletonBinder;
import es.caib.zkib.events.XPathEvent;
import es.caib.zkib.events.XPathSubscriber;
import es.caib.zkib.zkiblaf.Frame;

public class InputField extends Div implements XPathSubscriber{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String compos;
	boolean userType;
	
	private DadesAddicionalsService dadesAddicionalsServ;
	private SingletonBinder binder = new SingletonBinder(this);
	private SingletonBinder binder2 = new SingletonBinder(this);
	private SingletonBinder binder3 = new SingletonBinder(this);
	private SingletonBinder binder4 = new SingletonBinder(this);

	public InputField(){
		super();
		compos = new String();
	}
	
	public void onCreate () throws NamingException, CreateException, InternalErrorException, IOException
	{
		binder.setDataPath("@valorDada");
		binder2.setDataPath("@codiDada");
		binder3.setDataPath("@blobDataValue");
		binder4.setDataPath("@valorDadaDate");
		createField();
	}
	
	public void onUpdate(XPathEvent arg0) 
	{
		try {
			binder.setDataPath("@valorDada");
			binder2.setDataPath("@codiDada");
			binder3.setDataPath("@blobDataValue");
			binder4.setDataPath("@valorDadaDate");
			compos = new String();
			createField();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	boolean disableRecursive = false;
	
	
	public void onSelectUser (Event event)
	{
		Page p = getDesktop().getPage("usuarisLlista");
		Events.postEvent("onInicia", p
				.getFellow("esquemaLlista"), event.getTarget());

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
		Label l = (Label) ((Component) getChildren().get(0)).getChildren().get(2);
		try {
			if (user == null)
				l.setValue("");
			else
			{
				UsuariService ejb = ((UsuariServiceHome) new InitialContext(). lookup (UsuariServiceHome.JNDI_NAME))
						.create();
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
	
	private synchronized void createField() throws NamingException, CreateException, InternalErrorException, IOException{
		
		if (getPage() == null)
			return;	
		
		if (disableRecursive)
			return;
		
		disableRecursive = true;
		
		DadesAddicionalsServiceHome homeDades = (DadesAddicionalsServiceHome) new InitialContext().lookup (DadesAddicionalsServiceHome.JNDI_NAME);
		dadesAddicionalsServ = homeDades.create();
		
		if(binder2.getValue() != null){
			List<TipusDada> tipusDadaList = (List) dadesAddicionalsServ.findTipusDadesByCodi(binder2.getValue().toString());
			String result = "";
			Map <String,Object> map=new HashMap<String, Object>();
			boolean updateUser = false;


			for(int i=0; i<tipusDadaList.size(); i++){
				TipusDada typeData = tipusDadaList.get(i);
				TypeEnumeration type = typeData.getType();
				String stringType = new String();
				if(type!=null)
					stringType = type.toString();
				int size = 0;
				if(typeData.getSize() != null)
					size = typeData.getSize();
				if(stringType != null && !stringType.trim().isEmpty()){
					if(TypeEnumeration.USER_TYPE.equals(type))
					{
						updateUser = true;
						result = "<div style='display:inline-block'>"
								+ "<textbox maxlength=\"" + size +"\" bind=\"@valorDada\" onChange=\"\" readonly=\"${!canUpdateUserMetadata}\"/>"+
								"<imageclic src='/img/user.png' onClick='self.parent.parent.onSelectUser(event)' onActualitza='self.parent.parent.onActualitza(event)'/>"
								+ "<label style='text-decoration: underline; cursor:pointer' onClick='self.parent.parent.openUser()'/>"
								+ "</div>";
					}
					else if(TypeEnumeration.BINARY_TYPE.equals(type))
					{
						boolean visible = fileAlreadySaved();
						result = "<h:span xmlns:h=\"http://www.w3.org/1999/xhtml\"><button label=\"Upload\" " +
									"onClick=\"self.parent.parent.uploadBinary();\" disabled=\"${!canUpdateUserMetadata}\">" +
									"</button><button label=\"Download\" disabled=\"${!canUpdateUserMetadata}\" visible=\"" + visible + "\" onClick=\"self.parent.parent.downloadBinary(self.parent);\">" +
									"</button></h:span>";
					}
					else if(TypeEnumeration.PHOTO_TYPE.equals(type))
					{
						if(binder3.getValue() != null){
							map.put("image", byteArrayToImage(getBlobDataValue()));
							result = "<h:span xmlns:h=\"http://www.w3.org/1999/xhtml\"><button label=\"Upload Photo\" " +
								    " onClick=\"self.parent.parent.upload(self.parent);\" disabled=\"${!canUpdateUserMetadata}\">" +
									"</button><div height=\"100px\" width=\"100px\" style=\"overflow:auto\"><image content=\"${arg.image}" + 
									 "\"/></div></h:span>";
						}else{
							result = "<h:span xmlns:h=\"http://www.w3.org/1999/xhtml\"><button label=\"Upload Photo\" " +
								    " onClick=\"self.parent.parent.upload(self.parent);\" disabled=\"${!canUpdateUserMetadata}\">" +
									"</button><div height=\"100px\" width=\"100px\" style=\"overflow:auto\"></div></h:span>";
						}
					}
					else if(TypeEnumeration.DATE_TYPE.equals(type))
					{
						if((binder4.getValue() != null) || (binder.getValue() != null)) {
							Calendar stringDate = null;
							if(binder4.getValue() != null)
								stringDate =  (Calendar) binder4.getValue();
							else if(stringDate == null)
								stringDate = (Calendar) binder.getValue();
							result = "<h:span xmlns:h=\"http://www.w3.org/1999/xhtml\"><datebox bind=\"@valorDadaDate\" " +
									"format=\"dd/MM/yyyy\" disabled=\"${!canUpdateUserMetadata}\"/></h:span>"; 
						}else{
							result = "<datebox bind=\"@valorDadaDate\" onChange=\"\" disabled=\"${!canUpdateUserMetadata}\"/>";
						}
					}
					else if(TypeEnumeration.EMAIL_TYPE.equals(type))
					{
						if(binder.getValue() != null && !binder.getValue().toString().trim().isEmpty()){
							boolean isEmail = testEmail(binder.getValue().toString());
							String email = binder.getValue().toString();
							if(!isEmail){
								result = "<textbox maxlength=\"" + size +"\" value=\"\" tooltip=\"${c:l('InputField.NoCorrectEmail')}\" " +
										"onChange=\"\" readonly=\"${!canUpdateUserMetadata}\"/>";
							}else{
								result = "<textbox maxlength=\"" + size +"\" bind=\"@valorDada\" onChange=\"\" readonly=\"${!canUpdateUserMetadata}\"/>";
							}
						}else
							result = "<textbox bind=\"@valorDada\" onChange=\"\" readonly=\"${!canUpdateUserMetadata}\"/>";
					}	
					else if (typeData.getValues() == null || typeData.getValues().isEmpty())//String
					{
						if(binder.getValue() != null)
							result = "<textbox maxlength=\"" + size +"\" bind=\"@valorDada\" onChange=\"\" readonly=\"${!canUpdateUserMetadata}\"/>";
						else
							result = "<textbox maxlength=\"" + size +"\" bind=\"@valorDada\" onChange=\"\" readonly=\"${!canUpdateUserMetadata}\"/>";
					} else { // Listbox
						result = "<listbox mold=\"select\" bind=\"@valorDada\" onChange=\"\" disabled=\"${!canUpdateUserMetadata}\">";
						result = result + "<listitem value=\"\"/>";
						for (String v: typeData.getValues())
						{
							String s = v.replaceAll("\"", "&quot;");
							result = result + "<listitem value=\""+s+"\" label=\""+s+"\"/>";
						}
						result = result + "</listbox>";
					}
				}
				if (result.equals(""))
				{
					result= result + "<textbox bind=\"@valorDada\" readonly=\"${!canUpdateUserMetadata}\"/>";
				}
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
		disableRecursive = false;
		
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
        org.zkoss.zul.Image img = new org.zkoss.zul.Image();
        img.setContent(byteArrayToImage(data));
        img.setParent((Component) span.getChildren().get(1));        
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
        System.out.println("Uploaded file. ");
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
		binder.setPage(page);
		binder2.setPage(page);
		binder3.setPage(page);
		binder4.setPage(page);
	}

	public void setParent(Component parent) {
		super.setParent(parent);
		binder.setParent(parent);
		binder2.setParent(parent);
		binder3.setParent(parent);
		binder4.setParent(parent);
	}

	public Object clone() {
		InputField clone = (InputField) super.clone();
		clone.binder = new SingletonBinder(clone);
		clone.binder.setDataPath(binder.getDataPath());
		clone.binder2 = new SingletonBinder(clone);
		clone.binder2.setDataPath(binder2.getDataPath());
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
}