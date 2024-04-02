package com.soffid.iam.web.main;

import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jbpm.graph.exe.Execution;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.au.AuRequest;
import org.zkoss.zk.au.Command;
import org.zkoss.zk.au.out.AuScript;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.DesktopCleanup;
import org.zkoss.zul.Label;
import org.zkoss.zul.Timer;
import org.zkoss.zul.Window;

import com.soffid.iam.common.security.SoffidPrincipal;
import com.soffid.iam.ui.Executions;
import com.soffid.iam.utils.ConfigurationCache;
import com.soffid.iam.utils.Security;
import com.soffid.iam.web.component.Frame;
import com.soffid.iam.web.component.Menu3;

import es.caib.bpm.filters.WorkflowInterceptor;
import es.caib.zkib.zkiblaf.Application;
import es.caib.zkib.zkiblaf.Frameable;
import es.caib.zkib.zkiblaf.Missatgebox;
import es.caib.zkib.zkiblaf.MissatgeboxDlg;


public class MainWindow extends Window {
	private HashSet<WeakReference<MainWindow>> mainWindows;

	public void menu (Event evt) throws Exception {
		Menu3 menu3 = (Menu3) getFellow("menu3");
		menu3.open();
	}
	
	public void help (Event event) {
		Frameable frameInfo = (Frameable) Application.getActiveFrame();
		String url = "https://bookstack.soffid.com";
		if (frameInfo != null && frameInfo instanceof Frame) {
			Frame frame = (Frame) frameInfo;
			if (frame.getHelp() != null && !frame.getHelp().isEmpty() )
				url = frame.getHelp();
			
		}
		org.zkoss.zk.ui.Executions.getCurrent().sendRedirect(url, "_blank");
	}

	@Override
	public void setParent(Component parent) {
		super.setParent(parent);
	}
	
	public void profile(Event event) {
		es.caib.zkib.zkiblaf.Application.call("self/profile.zul");
	}

	public void logout(Event event) {
		Missatgebox.confirmaOK_CANCEL(
				Labels.getLabel("zkiblaf.tancarSessioConfirm"), //$NON-NLS-1$
				Labels.getLabel("zkiblaf.tancarSessioTitle"), //$NON-NLS-1$
				new EventListener() {

					public void onEvent(Event event) throws Exception {
						if ("onOK".equals(event.getName())) { //$NON-NLS-1$
							HttpServletRequest req = (HttpServletRequest) getDesktop().getExecution().getNativeRequest();
							HttpSession session = req.getSession();
							Object nestedPrincipal = session.getAttribute(WorkflowInterceptor.SOFFID_NESTED_PRINCIPAL);
							if (nestedPrincipal != null )
							{
								session.removeAttribute(WorkflowInterceptor.SOFFID_NESTED_PRINCIPAL);
								session.removeAttribute(WorkflowInterceptor.SOFFID_NESTED_TENANT);
								session.removeAttribute(WorkflowInterceptor.SOFFID_NESTED_PERMISSIONS);
								getDesktop().getExecution().sendRedirect("/index.zul");
							}
							else
							{
								getDesktop().getExecution().sendRedirect("/anonymous/logout.zul");
								session.invalidate();
							}
						}
					}
					
				});						

	}
	
	public void goHome(Event ev) {
		SoffidPrincipal p = Security.getSoffidPrincipal();
		if (Arrays.binarySearch(p.getSoffidRoles(),"SOFFID_ADMIN") >= 0) {
			Application.jumpTo("/config/wheel.zul");
		} else {
			Application.jumpTo("/main/menu.zul");
		}
	}

	@Override
	public void onPageAttached(Page newpage, Page oldpage) {
		super.onPageAttached(newpage, oldpage);
		SoffidPrincipal p = Security.getSoffidPrincipal();
		String usuari = p.getName();
		String nom = p.getFullName();
		if (usuari.startsWith("master\\"))
			usuari = usuari.substring(7);
		getPage().getNamespace().setVariable("usuari", usuari, true);
		getPage().getNamespace().setVariable("nom", nom, true);
		String t = ConfigurationCache.getProperty("soffid.auth.timeout");
		if (t == null || t.trim().isEmpty()) t = "false";
		getPage().getNamespace().setVariable("sessionTimeout", t, true);
		// Register session desktops
		mainWindows = (HashSet<WeakReference<MainWindow>>) getDesktop().getSession().getAttribute("mainWindows");
		if (mainWindows == null) {
			mainWindows = new HashSet<>();
			getDesktop().getSession().setAttribute("mainWindows", mainWindows);
		}
		if (!mainWindows.contains(weakReference)) {
			weakReference = new WeakReference<MainWindow>(this);
			mainWindows.add( weakReference);
			getDesktop().addListener(new DesktopCleanup() {
				@Override
				public void cleanup(Desktop desktop) throws Exception {
					mainWindows.remove(weakReference);
				}
			});
		}
	}

	@Override
	public Command getCommand(String cmdId) {
		if (cmdId.equals("onTimeout"))
			return onTimeoutCommand ;
		else
			return super.getCommand(cmdId);
	}
	private static Command onTimeoutCommand = new Command("onTimeout", 0) {
		@Override
		protected void process(AuRequest request) {
			((MainWindow)request.getComponent()).onTimeout();
		}
	};
	private MissatgeboxDlg currentTimeoutDlg;
	private WeakReference<MainWindow> weakReference = new WeakReference<MainWindow>(this);
	
	protected void onTimeout() {
		Window w = (Window) getFellow("sessionTimeoutWindow");
		if (!w.isVisible()) {
			w.doHighlighted();
			final Label counter = (Label)(w.getFellow("counter"));
			counter.setValue("");
			response(null, new AuScript(this, ""
					+ "var v = Date.now() + 60000;"
					+ "let label=document.getElementById('"+counter.getUuid()+"');"
					+ "var logoutInterval = setInterval( () => {"
					+ "   let t = Math.floor((v - Date.now()) / 1000);"
					+ "   if (t <= 30 && t >= 0)"
					+ "      label.innerText = t; "
					+ "}, 1000);"
					+ "label.logoutInterval = logoutInterval;"));
			Timer t = (Timer) w.getFellow("timer");
			t.start();
			t.setRepeats(true);
		}
	}

	public void cancelTimeout (Event ev) {
		Window w = (Window) getFellow("sessionTimeoutWindow");
		Timer t = (Timer) w.getFellow("timer");
		final Label counter = (Label)(w.getFellow("counter"));
		response(null, new AuScript(this, ""
				+ "let label=document.getElementById('"+counter.getUuid()+"');"
				+ "clearInterval(label.logoutInterval);"));
		t.stop();
		w.setVisible(false);
	}

	public void confirmTimeout (Event ev) {
		for (Iterator<WeakReference<MainWindow>> iterator = mainWindows.iterator();
				iterator.hasNext();) {
			WeakReference<MainWindow> r = iterator.next();
			if (r.get() == null)
				iterator.remove();
		}
		if (mainWindows.size() <= 1) {
			getDesktop().getSession().invalidate();
		}
		getDesktop().getExecution().sendRedirect("/anonymous/logout.zul");
	}

	@Override
	public void onPageDetached(Page page) {
		super.onPageDetached(page);
		mainWindows.remove(weakReference);
	}
}
