package com.soffid.iam.web.custom;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.UiException;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.Configuration;
import com.soffid.iam.api.CustomObject;
import com.soffid.iam.api.DataType;
import com.soffid.iam.service.ejb.CustomObjectService;
import com.soffid.iam.web.WebDataType;
import com.soffid.iam.web.component.FrameHandler;
import com.soffid.iam.web.popup.CsvParser;
import com.soffid.iam.web.popup.ImportCsvHandler;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.datasource.CommitException;
import es.caib.zkib.datasource.XPathUtils;
import es.caib.zkib.zkiblaf.Missatgebox;


public class CustomObjectHandler extends FrameHandler {
	String type;
	
	public CustomObjectHandler() throws InternalErrorException {
		super();
	}

	
	public String getType() {
		return type;
	}

	
	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public void afterCompose() {
		getModel().getJXPathContext().getVariables().declareVariable("objectType", type);
		setUrl("/custom/custom.zul?type="+type);
	}


	@Override
	public void addNew() throws Exception {
		super.addNew();
		XPathUtils.setValue(getListbox(), "type", type);
	}
	
	public void importCsv() throws IOException, CommitException, InternalErrorException, NamingException, CreateException {
		new CustomObjectImporter(type).importCsv(this);
	}

}
