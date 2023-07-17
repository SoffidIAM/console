package com.soffid.iam.web.issue;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Window;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.DataType;
import com.soffid.iam.api.Issue;
import com.soffid.iam.api.IssueActionDefinition;
import com.soffid.iam.api.IssuePolicy;
import com.soffid.iam.api.IssuePolicyAction;
import com.soffid.iam.api.IssueStatus;
import com.soffid.iam.utils.Security;
import com.soffid.iam.web.WebDataType;
import com.soffid.iam.web.component.CustomField3;
import com.soffid.iam.web.component.FrameHandler;
import com.soffid.iam.web.component.InputField3;
import com.soffid.iam.web.component.SearchBox;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.component.DataTable;
import es.caib.zkib.datasource.CommitException;
import es.caib.zkib.datasource.DataSource;
import es.caib.zkib.datasource.XPathUtils;
import es.caib.zkib.jxpath.JXPathNotFoundException;


public class MyIssueHandler extends IssueHandler {

	public MyIssueHandler() throws InternalErrorException, NamingException, CreateException {
		super();
	}


	@Override
	public void afterCompose() {
		super.afterCompose();
	}
	
}
