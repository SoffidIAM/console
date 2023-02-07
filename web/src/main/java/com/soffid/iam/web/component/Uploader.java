package com.soffid.iam.web.component;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.zkoss.util.media.AMedia;
import org.zkoss.util.media.Media;
import org.zkoss.zk.au.AuRequest;
import org.zkoss.zk.au.Command;
import org.zkoss.zk.au.ComponentCommand;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.HtmlBasedComponent;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.UploadEvent;

import es.caib.zkib.component.Databox;


public class Uploader extends HtmlBasedComponent {
	boolean multiple;

	
	public boolean isMultiple() {
		return multiple;
	}

	
	public void setMultiple(boolean multiple) {
		this.multiple = multiple;
	}
	
	public String getMultipleTag() {
		return multiple ? "multiple": "";
	}

	private static Command _onCancelCommand = new ComponentCommand("onClose", 0) {
		@Override
		protected void process(AuRequest request) {
			Uploader uploader = (Uploader) request.getComponent();
			Events.postEvent("onClose", uploader, null);
		}
	};
	
	private static Command _onUploadCommand = new ComponentCommand("onUpload", 0) {
		@Override
		protected void process(AuRequest request) {
			Uploader uploader = (Uploader) request.getComponent();
			List<Media> medias = uploader.getUploaded();
			Events.postEvent( new UploadEvent("onUpload", uploader, medias.toArray(new Media[medias.size()])));
		}
	};
	
	private static Command _onChooseFileCommand = new ComponentCommand("onChooseFile", 0) {
		@Override
		protected void process(AuRequest request) {
			Uploader uploader = (Uploader) request.getComponent();
			List<Media> medias = uploader.getUploaded();
			Events.postEvent( new UploadEvent("onUpload", uploader, medias.toArray(new Media[medias.size()])));
		}
	};
	
	public Command getCommand(String cmdId) {
		if (_onCancelCommand.equals(cmdId))
			return _onCancelCommand;

		if (_onUploadCommand.getId().equals(cmdId))
			return _onUploadCommand;

		if (_onChooseFileCommand.getId().equals(cmdId))
			return _onChooseFileCommand;

		return super.getCommand(cmdId);
	}

	private List<Media> getUploaded() {
		HttpServletRequest req = (HttpServletRequest) Executions.getCurrent().getNativeRequest();
		HttpSession session = req.getSession();
		List<Media> medias = (List<Media>) session.getAttribute("$$soffid-uploaded$$-"+getUuid());
		session.removeAttribute("$$soffid-uploaded$$-"+getUuid());
		return medias;
	}

	public static void register(HttpSession session, String uuid, AMedia media) {
		List<Media> medias = (List<Media>) session.getAttribute("$$soffid-uploaded$$-"+uuid);
		if (medias == null) {
			medias = new LinkedList<>();
			session.setAttribute("$$soffid-uploaded$$-"+uuid, medias);
		}
		medias.add(media);
	}

}
