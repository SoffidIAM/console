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
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Image;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Textbox;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.Configuration;
import com.soffid.iam.service.ejb.ConfigurationService;
import com.soffid.iam.utils.ConfigurationCache;
import com.soffid.iam.web.component.FrameHandler;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.binder.BindContext;
import es.caib.zkib.component.DataModel;
import es.caib.zkib.component.Form;
import es.caib.zkib.component.Select;
import es.caib.zkib.datamodel.DataModelCollection;
import es.caib.zkib.datasource.CommitException;
import es.caib.zkib.datasource.XPathUtils;

public class AuthenticationHandler extends FrameHandler {
	private Form samlform;
	private DataModel model;
	private Listbox idpselect;

	public AuthenticationHandler () throws Exception {
	}

	@Override
	public void afterCompose() {
		super.afterCompose();
		samlform = (Form) getFellow("samlform");
		model = (DataModel) getFellow("model");
		idpselect = (Listbox) getFellow("idpselect");
	}

	public void refreshIDP () throws Exception {
		String md = (String) XPathUtils.getValue((BindContext) samlform, "metadata");
		model.getVariables().declareVariable("metadata", md);
		((DataModelCollection) model.getValue("/idps")).refresh();
		idpselect.setSelectedIndex(0);

	}
	
	public void downloadMetadata() throws InternalErrorException, NamingException, CreateException
	{
		HttpServletRequest req = (HttpServletRequest) Executions.getCurrent().getNativeRequest();
		String hostName = req.getHeader("Host");
		String md = com.soffid.iam.EJBLocator.getSamlService().generateMetadata(hostName);
		Filedownload.save (md, "text/plain", "soffid-metadata.xml");
	}

	public void commit() throws CommitException {
		model.commit();
	}
}
