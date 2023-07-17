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
import com.soffid.iam.web.component.FrameHandler;
import com.soffid.iam.web.popup.MergeActionHandler;
import com.soffid.iam.web.user.UserMergeAction;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.binder.BindContext;
import es.caib.zkib.component.DataModel;
import es.caib.zkib.datamodel.DataModelNode;
import es.caib.zkib.datamodel.DataNode;
import es.caib.zkib.datamodel.DataNodeCollection;
import es.caib.zkib.datasource.XPathUtils;
import es.caib.zkib.events.XPathRerunEvent;
import es.caib.zkib.zkiblaf.Missatgebox;

public class MergeUsers implements ManualActionHandler {

	@Override
	public void init(Window w, List<Issue> issues) throws InternalErrorException, NamingException, CreateException {
		if (issues.size() > 1)
		{
			Missatgebox.avis(Labels.getLabel("issues.cannotMergeMultiple"));
			return;
		}
		w.setVisible(false);
		StringBuffer sb = new StringBuffer();
		int number = 0;
		Issue issue = issues.iterator().next();
		for (IssueUser iu: issue.getUsers()) {
			if (iu.getUserId() != null) {
				if (sb.length() > 0) sb.append(" or ");
				sb.append("id eq "+iu.getUserId());
				number ++;
			}
		}
		if (number < 2) {
			Missatgebox.avis(Labels.getLabel("merge.warning"));
			return;
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
			List<Integer> positions = new LinkedList<>();
			for (int i = 0; i < coll.size(); i++) {
				DataNode dn = (DataNode) coll.getDataModel(i);
				User u  = (User) dn.getInstance();
				users.add(u);
				names.add(u.getUserName());
				positions.add(i+1);
			}
			
			
			int[] pos = new int[positions.size()];
			for (int i = 0; i < positions.size(); i++)
				pos[i] = positions.get(i).intValue();

			UserMergeAction action = new UserMergeAction();
			action.setOnApply((ev) -> {
				BindContext ctx = XPathUtils.getComponentContext(w.getPage().getFellow("listbox"));
				ctx.getDataSource().sendEvent(
						new XPathRerunEvent(ctx.getDataSource(), ctx.getXPath()));
				FrameHandler f = (FrameHandler) w.getPage().getFellow("frame");
				f.hideDetails();
			});
			action.start(w, model, "/user", pos, false, issue);
		} catch (InternalErrorException e) {
			throw e;
		} catch (Exception e) {
			throw new InternalErrorException("Error processing issue", e);
		}
	}

	@Override
	public void process(Window w, List<Issue> issues, Map<String, Object> parameters)
			throws InternalErrorException, NamingException, CreateException {
	}

}
