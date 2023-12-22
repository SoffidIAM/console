package com.soffid.iam.web.wheel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Locale;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.util.Locales;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.au.AuRequest;
import org.zkoss.zk.au.Command;
import org.zkoss.zk.au.ComponentCommand;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Button;
import org.zkoss.zul.Html;
import org.zkoss.zul.Label;
import org.zkoss.zul.Window;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.ServiceLocator;
import com.soffid.iam.api.Server;
import com.soffid.iam.web.component.FrameHandler;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.component.DataTable;
import es.caib.zkib.component.ReorderEvent;
import es.caib.zkib.component.Switch;

public class WheelHandler extends FrameHandler {
	int welcomePosition = 0;
	
	private Window welcome;

	private Label welcomeMessage;

	private Quarter[] quarters;
	
	public WheelHandler () throws Exception {
	}

	@Override
	public void afterCompose() {
		super.afterCompose();
		
		welcome = (Window) getFellow("welcome");
		welcomeMessage = (Label) welcome.getFellow("welcomelabel");
		
		 try {
			 Locale locale = Locales.getCurrent();
			 InputStream in = null;
			 if (locale != null)
				 in = getClass().getResourceAsStream("/com/soffid/iam/web/wheel-"+
						 locale.getLanguage().toLowerCase()
						 +".svg");

			 if (in == null)
				 in = getClass().getResourceAsStream("/com/soffid/iam/web/wheel.svg");
			 
		     ByteArrayOutputStream out = new ByteArrayOutputStream();
		     byte b[] = new byte[4096]; //Por qué este valor? Preguntar a Biel
		     for (int read = in.read(b); read >= 0; read = in.read(b)) {
		            out.write(b, 0, read);
		     }
		     Html html = (Html) getFellow("wheel");
		     String text = out.toString("UTF-8");
		     text = text.replaceFirst("<\\?xml.*\n", "")
		    		 .replaceFirst("<svg", "<svg style='width: 100%; height: 100%; max-height: calc( 100vh - 120px) '");
		     html.setContent(text);
		 }catch(IOException ex) { //De momento solo saco la traza, no hago nada con la excepcion
				 throw new UiException(ex);
		 }
	 
	 	quarters = new Quarter[] {
	 			new Quarter("am", 
	 					new Iga02Sector("am01"),
	 					new Am02Sector("am02"),
	 					new Am03Sector("am03"),
	 					new Am04Sector("am04")),
	 			new Quarter("iga", 
	 					new InstallSynserverSector("iga01"),
	 					new Iga02Sector("iga02"),
	 					new Iga03Sector("iga03"),
	 					new Iga04Sector("iga04")),
	 			new Quarter("irc", 
	 					new Irc01Sector("irc01"),
	 					new Irc02Sector("irc02"),
	 					new Irc03Sector("irc03"),
	 					new Irc04Sector("irc04")),
	 			new Quarter("pam", 
	 					new Pam01Sector("pam01"),
	 					new Pam02Sector("pam02"),
	 					new Pam03Sector("pam03"),
	 					new Pam04Sector("pam04")),
	 	};
	 	
	 	for (Quarter q: quarters) {
	 		q.installHandler(this);
	 		q.updateStatus(this);
	 	}
	 	
	 	
	}
	
	public void updateStatus(Event ev) {
	 	for (Quarter q: quarters) {
	 		q.updateStatus(this);
	 	}
	}
	
	public void showWelcome(Event ev) {
		
		try {
			String hideTips = EJBLocator.getPreferencesService().findMyPreference("wheel-tips");
			if ("false".equals(hideTips)) 
				welcome.setVisible(false);
			else {
				response("showOrNot", new org.zkoss.zk.au.out.AuScript(this, 
						  "if  ('hide' != localStorage.getItem('welcome')) {\n"
						+ "  zkau.send ({uuid: '"+getUuid()+"', cmd: 'onShowWelcome', data : []}, 5);\n"
						+ "}"));
			}
		} catch (Exception e) {
		}
	}

	private static Command _onShowWelcome  = new ComponentCommand ("onShowWelcome", 0) {
		protected void process(AuRequest request) {
			final WheelHandler w = (WheelHandler) request.getComponent();
			w.welcome.doHighlighted();
		}
	};

	public void nextWelcome (Event ev) {
		Window w;
		switch (welcomePosition++) {
		case 0:
			w = (Window) getFellow("cloakLayer1");
			w.doOverlapped();
			welcomeMessage.setValue(Labels.getLabel("tutorial.userOptionsMessageBox"));
			break;
		case 1:
			w = (Window) getFellow("cloakLayer1");
			w.setVisible(false);
			w = (Window) getFellow("cloakLayer2");
			w.doOverlapped();
			welcomeMessage.setValue(Labels.getLabel("tutorial.helpOptionsMessageBox"));
			break;
		case 2:
			w = (Window) getFellow("cloakLayer2");
			w.setVisible(false);
			w = (Window) getFellow("cloakLayer3");
			w.doOverlapped();
			welcomeMessage.setValue(Labels.getLabel("tutorial.searchOptionsMessageBox"));
			break;
		case 3:
			w = (Window) getFellow("cloakLayer3");
			w.setVisible(false);
			w = (Window) getFellow("cloakLayer4");
			w.doOverlapped();
			welcomeMessage.setValue(Labels.getLabel("tutorial.mainNavigationMessageBox"));
			break;
		case 4:
			w = (Window) getFellow("cloakLayer4");
			w.setVisible(false);
			w = (Window) getFellow("cloakLayer5");
			w.doOverlapped();
			welcomeMessage.setValue(Labels.getLabel("tutorial.breadscrumsOptionsMessageBox"));
			break;
		case 5:
			w = (Window) getFellow("cloakLayer5");
			w.setVisible(false);
			welcomeMessage.setValue(Labels.getLabel("tutorial.end"));
			Button b = (Button) welcome.getFellow("next");
			b.setLabel(Labels.getLabel("user_createaccount.Finish"));
			welcome.getFellow("showagain").setVisible(true);
			break;
		default:
			welcome.setVisible(false);
		}
	}


	//1.-userOptions ==> Aparece segun se crea la pagina o hacemos alguna accion
	public void showUserOptionsMessageBox(Event ev) {
		getFellow("cloakLayer1").setVisible(true);
		getFellow("userOptionsArrow").setVisible(true);
		getFellow("userOptionsArrow").setVisible(true);
	}
	//2.-helpOptions 
	public void showHelpOptionsMessageBox(Event ev) {
		getFellow("cloakLayer1").detach();//No me vale con ponerla invisible, tengo k eliminarla....
		getFellow("cloakLayer2").setVisible(true);
		getFellow("helpOptionsArrow").setVisible(true);
		getFellow("helpOptionsMessageBox").setVisible(true);
		
	}
	
	public void onChangeShowagainswitch(Event ev) throws InternalErrorException, NamingException, CreateException {
		Switch i = (Switch) ev.getTarget();
		response("setShow", new org.zkoss.zk.au.out.AuScript(this, 
				  "localStorage.setItem('welcome', '"+(i.isChecked()?"hide":"show")+"')"));
		EJBLocator.getPreferencesService().updateMyPreference("wheel-tips",
				i.isChecked() ? "false": "true");
	}
	
	//3.-searchOptions 
	public void showSearchOptionsMessageBox(Event ev) {
		getFellow("cloakLayer2").detach();//No me vale con ponerla invisible, tengo k eliminarla....
		getFellow("cloakLayer3").setVisible(true);
		getFellow("searchOptionsArrow").setVisible(true);
		getFellow("searchOptionsMessageBox").setVisible(true);

	}
	
	//4.-MainNavigation
	public void showMainNavigationMessageBox(Event ev) {
		getFellow("cloakLayer3").detach();//No me vale con ponerla invisible, tengo k eliminarla....
		getFellow("cloakLayer4").setVisible(true);
		getFellow("mainNavigationArrow").setVisible(true);
		getFellow("mainNavigationMessageBox").setVisible(true);
	}
	
	//5.-BreadscrumsOptions
	public void showBreadscrumsOptionsMessageBox(Event ev) {
		getFellow("cloakLayer4").detach();//No me vale con ponerla invisible, tengo k eliminarla....
		getFellow("cloakLayer5").setVisible(true);
		getFellow("breadscrumsOptionsArrow").setVisible(true);
		getFellow("breadscrumsOptionsMessageBox").setVisible(true);
	}
	
	public void hideBreadscrumsOptionsMessageBox(Event ev) {
		getFellow("cloakLayer5").detach();//No me vale con ponerla invisible, tengo k eliminarla....
		getFellow("wheelContainer").setVisible(true);
	}

	
	
	private boolean existSyncServer() {
		try {
			Collection<Server> syncServerList = ServiceLocator.instance().getSyncServerService().getSyncServerInstances();
			return (syncServerList!=null && !syncServerList.isEmpty());
		}catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	public Command getCommand(String cmdId) {
		if ("onSector".equals(cmdId))
			return _onSectorCommand;
		if ("onShowWelcome".equals(cmdId))
			return _onShowWelcome;
		return super.getCommand(cmdId);
				
	}

	public static Command _onSectorCommand = new Command("onSector", 0) {
		@Override
		protected void process(AuRequest arg0) {
			WheelHandler target = (WheelHandler) arg0.getComponent();
			target.onSector(arg0.getData()[0]);
		}
	};
	
	public void onSector(String tag) {
		for (Quarter q: quarters) {
			q.onSector(tag);
		}
	}
}
