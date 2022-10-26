package com.soffid.iam.web.wheel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import javax.swing.text.Element;
import javax.swing.text.html.HTMLDocument;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.au.AuRequest;
import org.zkoss.zk.au.AuScript;
import org.zkoss.zk.au.Command;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Button;
import org.zkoss.zul.Html;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Window;

import com.lowagie.text.Document;
import com.soffid.iam.ServiceLocator;
import com.soffid.iam.api.Server;
import com.soffid.iam.web.component.FrameHandler;

import es.caib.zkib.component.DataModel;
import es.caib.zkib.component.Form;

public class WheelHandler extends FrameHandler {
	int welcomePosition = 0;
	
	private Window welcome;

	private Label welcomeMessage;

	private Quarter[] quarters;
	
	public WheelHandler () throws Exception {
		System.out.println("");
	}

	@Override
	public void afterCompose() {
		super.afterCompose();
		
		welcome = (Window) getFellow("welcome");
		welcomeMessage = (Label) welcome.getFellow("welcomelabel");
		
		 try {
			 InputStream in = getClass().getResourceAsStream("/com/soffid/iam/web/wheel.svg");
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
	 					new Sector("am01"),
	 					new Sector("am02"),
	 					new Sector("am03"),
	 					new Sector("am04")),
	 			new Quarter("iga", 
	 					new Sector("iga01"),
	 					new Sector("iga02"),
	 					new Sector("iga03"),
	 					new Sector("iga04")),
	 			new Quarter("irc", 
	 					new Sector("irc01"),
	 					new Sector("irc02"),
	 					new Sector("irc03"),
	 					new Sector("irc04")),
	 			new Quarter("pam", 
	 					new Sector("pam01"),
	 					new Sector("pam02"),
	 					new Sector("pam03"),
	 					new Sector("pam04")),
	 	};
	 	
	 	for (Quarter q: quarters) {
	 		q.installHanlder(this);
	 		q.updateStatus(this);
	 	}
	}
	
	public void showWelcome(Event ev) {
		
		welcome.doHighlighted();
	}
	
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
