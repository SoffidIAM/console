package com.soffid.iam.web.issue;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zul.Window;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.DataType;
import com.soffid.iam.api.Issue;
import com.soffid.iam.api.IssueUser;
import com.soffid.iam.api.User;
import com.soffid.iam.web.popup.MergeActionHandler;
import com.soffid.iam.web.user.UserMergeAction;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.component.DataModel;
import es.caib.zkib.datamodel.DataModelNode;
import es.caib.zkib.datamodel.DataNode;
import es.caib.zkib.datamodel.DataNodeCollection;
import es.caib.zkib.zkiblaf.Missatgebox;

public class MergeUsers implements ManualActionHandler {

	@Override
	public void init(Window w, Issue issue) throws InternalErrorException, NamingException, CreateException {
		w.setVisible(false);
		StringBuffer sb = new StringBuffer();
		for (IssueUser iu: issue.getUsers()) {
			if (iu.getUserId() != null) {
				if (sb.length() > 0) sb.append(" or ");
				sb.append("id eq "+iu.getUserId());
			}
		}
		DataModel model = (DataModel) w.getPage().getFellow("model");
		model.getJXPathContext().getVariables().declareVariable("userquery", sb.toString());
		
		try {
			DataNodeCollection coll = (DataNodeCollection) model.getJXPathContext().getValue("/user");
			coll.refresh();
			
			if (coll.size() < 2) {
				Missatgebox.avis(Labels.getLabel("merge.warning"));
				return;
			}
			
			List<DataType> dataTypes = new LinkedList<>(
					EJBLocator.getAdditionalDataService()
					.findDataTypesByObjectTypeAndName2(User.class.getName(), null));
			List<Object> users = new LinkedList<>();
			List<String> names = new LinkedList<>();
			for (int i = 0; i < coll.size(); i++) {
				DataNode dn = (DataNode) coll.getDataModel(i);
				User u  = (User) dn.getInstance();
				users.add(u);
				names.add(u.getUserName());
			}
			
			MergeActionHandler.startWizard (w, users, names, dataTypes , new UserMergeAction() );
		} catch (InternalErrorException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void process(Window w, Issue issue, Map<String, Object> parameters)
			throws InternalErrorException, NamingException, CreateException {
	}

}
