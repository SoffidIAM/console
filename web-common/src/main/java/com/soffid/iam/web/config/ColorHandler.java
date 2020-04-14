package com.soffid.iam.web.config;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

import org.zkoss.image.AImage;
import org.zkoss.util.media.Media;
import org.zkoss.zk.au.out.AuScript;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zul.Image;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Textbox;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.Configuration;
import com.soffid.iam.service.ejb.ConfigurationService;
import com.soffid.iam.utils.ConfigurationCache;
import com.soffid.iam.web.component.FrameHandler;

import es.caib.seycon.ng.exception.InternalErrorException;

public class ColorHandler extends FrameHandler {
	public ColorHandler () throws Exception {
	}

	@Override
	public void setPage(Page p) {
		super.setPage(p);
		try {
			setColors();
		} catch (Exception e) {
			throw new UiException(e);
		}
	}
	
	ConfigurationService configurationService = EJBLocator.getConfigurationService();
	byte [] image = null;
	byte [] image2 = null;
	
	public void setColors () throws InternalErrorException, NamingException, CreateException, IOException
	{
		String green = ConfigurationCache.getProperty("soffid.ui.color1");
		if (green == null) green = "#9ec73c";

		String blue = ConfigurationCache.getProperty("soffid.ui.color2");
		if (blue == null) blue = "#637792";

		
		String sky = ConfigurationCache.getProperty("soffid.ui.color3");
		if (sky == null) sky = "#22B9D8";


		String greenText = ConfigurationCache.getProperty("soffid.ui.text1");
		if (greenText == null) greenText = "#000000";

		String blueText = ConfigurationCache.getProperty("soffid.ui.text2");
		if (blueText == null) blueText = "#ffffff";

		String skyText = ConfigurationCache.getProperty("soffid.ui.text3");
		if (skyText == null) skyText = "#ffffff";
		
		getNamespace().setVariable("color1", green, true);
		getNamespace().setVariable("color1text", greenText, true);
		getNamespace().setVariable("color2", blue, true);
		getNamespace().setVariable("color2text", blueText, true);
		getNamespace().setVariable("color3", sky, true);
		getNamespace().setVariable("color3text", skyText, true);
		
		image = configurationService.getBlob("logo");
		
		AImage aImage = new AImage("logo.png", image == null ?  getBlankImage(): image);
		
		getNamespace().setVariable("logoImage", aImage, true);

		image2 = configurationService.getBlob("logo2");
		if (image2 == null)
			image2 = getBlankImage();
		aImage = new AImage("logo2.png", image2 == null ?  getBlankImage(): image2);
		
		getNamespace().setVariable("logoImage2", aImage, true);
	}
	
	public void deleteImage () {
		image = new byte[0];
		image2 = new byte[0];
	}
	
	public void onUpload ( Event ev ) throws IOException {
		UploadEvent event = (UploadEvent) ev;
		Media media = event.getMedia();
		if (media != null && media.isBinary())
		{
			if (media.inMemory())
				image = media.getByteData();
			else
			{
				ByteArrayOutputStream buffer = new ByteArrayOutputStream();
				InputStream in = media.getStreamData();
				for (int read = in.read(); read >= 0; read = in.read()) buffer.write(read);
				image = buffer.toByteArray();
			}
			Image img = (Image) event.getTarget().getFellow("logoImage");
			img.setContent( new AImage( "logo.png", image ));
					
		} else {
			image = new byte[0];
		}
	}
	
	public void onUpload2 ( Event ev ) throws IOException {
		UploadEvent event = (UploadEvent) ev;
		Media media = event.getMedia();
		if (media != null && media.isBinary())
		{
			if (media.inMemory())
				image2 = media.getByteData();
			else
			{
				ByteArrayOutputStream buffer = new ByteArrayOutputStream();
				InputStream in = media.getStreamData();
				for (int read = in.read(); read >= 0; read = in.read()) buffer.write(read);
				image2 = buffer.toByteArray();
			}
			Image img = (Image) event.getTarget().getFellow("logoImage2");
			img.setContent( new AImage( "logo.png", image2 ));
					
		} else {
			image2 = new byte[0];
		}
	}
	

	public void reset (Event event) throws IOException
	{
		reset (event.getTarget(), "color1tb", "color1Selector", "#9ec73c");
		reset (event.getTarget(), "color1Texttb", "color1TextSelector", "#000000");
		reset (event.getTarget(), "color2tb", "color2Selector", "#637792");
		reset (event.getTarget(), "color2Texttb", "color2TextSelector", "#ffffff");
		reset (event.getTarget(), "color3tb", "color3Selector", "#22B9D8");
		reset (event.getTarget(), "color3Texttb", "color3TextSelector", "#ffffff");
		image = null;
		Image img = (Image) event.getTarget().getFellow("logoImage");
		img.setContent( (AImage) null );
		image2 = null;
		img = (Image) event.getTarget().getFellow("logoImage2");
		img.setContent( (AImage) null );
	}
	
	private void reset(Component target, String component, String colorSelector, String value) {
		((Textbox)target.getFellow(component)).setValue(value);
		Executions.getCurrent().addAuResponse( "reset"+colorSelector, 
				new AuScript(target, "onChangeTBColor('"+colorSelector+"','"+value+"')"));
		
	}

	public void commit (Event event) throws InternalErrorException, NamingException, CreateException {
		save ("soffid.ui.color1", "color1tb");
		save ("soffid.ui.text1", "color1Texttb");
		save ("soffid.ui.color2", "color2tb");
		save ("soffid.ui.text2", "color2Texttb");
		save ("soffid.ui.color3", "color3tb");
		save ("soffid.ui.text3", "color3Texttb");
		if (image == null || image.length == 0)
			configurationService.deleteBlob("logo");
		else
			configurationService.updateBlob("logo", image);
		if (image2 == null || image2.length == 0)
			configurationService.deleteBlob("logo2");
		else
			configurationService.updateBlob("logo2", image2);
		
		Executions.getCurrent().addAuResponse("refresh", new org.zkoss.zk.au.out.AuScript(event.getTarget(), "location.reload()"));
	}

	private void save(String parameter, String textBox) throws InternalErrorException, NamingException, CreateException {
		String v = ((Textbox)getFellow(textBox)).getValue();
		Configuration cfg = configurationService.findParameterByNameAndNetworkName(parameter, null);
		if (cfg == null)
		{
			cfg = new Configuration();
			cfg.setCode(parameter);
			cfg.setValue(v);
			cfg.setDescription("UI setting");
			configurationService.create(cfg);
		}
		else
		{
			cfg.setValue(v);
			configurationService.update(cfg);
		}
	}
	
	byte[] getBlankImage () throws IOException {
		InputStream in = getClass().getResourceAsStream("/web/img/blanc.gif");
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		for (int i = in.read(); i >= 0; i = in.read())
		{
			out.write(i);
		}
		in.close();
		out.close();
		return out.toByteArray();
	}

	@Override
	public void afterCompose() {
		super.afterCompose();
	}

}
