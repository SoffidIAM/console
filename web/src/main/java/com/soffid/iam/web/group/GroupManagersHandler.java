package com.soffid.iam.web.group;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Div;

import com.soffid.iam.api.DomainValue;
import com.soffid.iam.api.Group;
import com.soffid.iam.api.Role;
import com.soffid.iam.api.RoleAccount;
import com.soffid.iam.api.User;
import com.soffid.iam.web.component.Identity;
import com.soffid.iam.web.component.Identity.Type;
import com.soffid.iam.web.popup.IdentityHandler;

import es.caib.zkib.component.DataTree2;
import es.caib.zkib.datasource.CommitException;
import es.caib.zkib.datasource.XPathUtils;


public class GroupManagersHandler extends Div implements AfterCompose {
	String listboxPath;
	
	public GroupManagersHandler() throws NamingException, CreateException {
	}
	
	public void afterCompose() {
	}
	
	
	public void addManager(Event event) throws IOException {
		IdentityHandler.selectIdentity(Labels.getLabel("user-groups.selectUser"), 
				new Type[] { Type.USER}, event.getTarget(), "onCompleteAddManager");
	}
	
	public void completeManager(Event event) throws Exception {
		DataTree2 groups = (DataTree2) Path.getComponent(listboxPath);
		Group group = (Group) XPathUtils.eval(groups, "/instance");
				
		List<Identity> identities = (List<Identity>) event.getData();
		DataTree2 tree = (DataTree2) getFellow("listbox");
		for (Identity identity: identities) {
			User user = (User) identity.getObject();
			Role role = (Role) XPathUtils.eval(tree, "instance");
			RoleAccount ra = new RoleAccount();
			ra.setUserCode(user.getUserName());
			ra.setUserFullName(user.getFullName());
			ra.setRoleName(role.getName());
			ra.setSystem(role.getSystem());
			ra.setAccountSystem(role.getSystem());
			ra.setStartDate(new Date());
			ra.setDomainValue(new DomainValue());
			ra.getDomainValue().setValue( group.getName() );
			XPathUtils.createPath(tree, "/manager", ra);
		}
		groups.getDataSource().commit();
	}

	
	public String getListboxPath() {
		return listboxPath;
	}

	
	public void setListboxPath(String listboxPath) {
		this.listboxPath = listboxPath;
	}
}
