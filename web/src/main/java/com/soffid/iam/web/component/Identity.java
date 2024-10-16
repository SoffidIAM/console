package com.soffid.iam.web.component;

import java.util.Comparator;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Div;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;

import com.soffid.iam.api.Group;
import com.soffid.iam.api.Host;
import com.soffid.iam.api.Network;
import com.soffid.iam.api.Role;
import com.soffid.iam.api.RoleAccount;
import com.soffid.iam.api.User;

import com.soffid.iam.api.Account;
import com.soffid.iam.api.Application;
import com.soffid.iam.api.CustomObject;
import com.soffid.iam.api.DomainValue;

import es.caib.zkib.events.SerializableEventListener;
import es.caib.zkib.zkiblaf.ImageClic;

public class Identity implements Comparable<Identity>{
	public enum Type {
		USER, ACCOUNT, ROLE, GROUP, GRANT, CUSTOM_OBJECT, APPLICATION, NETWORK, HOST
	} ;
	
	Type type;
	
	String label;
	String selectorLabel;
	
	Object object;

	
	public Identity (User u)
	{
		type = Type.USER;
		label = u.getUserName()+" - "+u.getFullName();
		object = u;
	}
	
	public Identity (Account u)
	{
		type = Type.ACCOUNT;
		label = u.getName()+" @ " + u.getSystem()+" - "+u.getDescription();
		object = u;
	}

	public Identity (Group g)
	{
		type = Type.GROUP;
		label = g.getName()+" - "+g.getDescription();
		object = g;
	}

	public Identity (Role role, DomainValue domainValue)
	{
		type = Type.GRANT;
		if (domainValue == null)
		{
			label = role.getName()+" @ "+role.getSystem()+" - "+role.getDescription();
		} else if (domainValue.getValue() == null ) {
			label = role.getName()+" @ "+role.getSystem()+" - "+role.getDescription()
				+ " / " + Labels.getLabel("attributeQuery.all");
		} else {
			label = role.getName()+" @ "+role.getSystem()+" - "+role.getDescription()
				+ " / " + domainValue.getValue()+" - "+domainValue.getDescription();
			selectorLabel = role.getName()+" @ "+role.getSystem()
				+ " / " + domainValue.getValue()+" - "+domainValue.getDescription();
		}
		RoleAccount ra = generateRoleAccount(role);
		ra.setDomainValue(domainValue);
		object = ra;
	}

	public Identity (Role role, Group g)
	{
		type = Type.GRANT;
		DomainValue domainValue = null;
		if (g == null)
		{
			label = role.getName()+" @ "+role.getSystem()+" - "+role.getDescription();
		} else {
			domainValue = new DomainValue();
			domainValue.setDescription(g.getDescription());
			domainValue.setValue(g.getName());
			label = role.getName()+" @ "+role.getSystem()+" - "+role.getDescription()
				+ " / " + domainValue.getValue()+" - "+domainValue.getDescription();
		}
		RoleAccount ra = generateRoleAccount(role);
		ra.setDomainValue(domainValue);
		object = ra;
	}

	private RoleAccount generateRoleAccount(Role role) {
		RoleAccount ra = new RoleAccount();
		ra.setRoleName(role.getName());
		ra.setRoleDescription(role.getDescription());
		ra.setRoleCategory(role.getCategory());
		ra.setSystem(role.getSystem());
		ra.setRoleCategory(role.getCategory());
		ra.setInformationSystemName(role.getInformationSystemName());
		return ra;
	}

	public Identity (Role role, Application app)
	{
		type = Type.GRANT;
		DomainValue domainValue = null;
		if (app == null)
		{
			label = role.getName()+" @ "+role.getSystem()+" - "+role.getDescription();
		} else {
			domainValue = new DomainValue();
			domainValue.setDescription(app.getDescription());
			domainValue.setValue(app.getName());
			label = role.getName()+" @ "+role.getSystem()+" - "+role.getDescription()
				+ " / " + domainValue.getValue()+" - "+domainValue.getDescription();
		}
		RoleAccount ra = generateRoleAccount(role);
		ra.setDomainValue(domainValue);
		object = ra;
	}

	public Identity (Role r)
	{
		type = Type.ROLE;
		label = r.getName()+" @ "+r.getSystem()+" - "+r.getDescription();
		object = r;
	}

	public Identity(CustomObject o) {
		type = Type.CUSTOM_OBJECT;
		label = o.getName()+" - "+o.getDescription();
		object = o;
	}

	public Identity(Application o) {
		type = Type.APPLICATION;
		label = o.getName()+" - "+o.getDescription();
		object = o;
	}

	public Identity(Network o) {
		type = Type.NETWORK;
		label = o.getCode()+" - "+o.getDescription();
		object = o;
	}

	public Identity(Host o) {
		type = Type.HOST;
		label = o.getName()+" - "+o.getDescription();
		object = o;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}
	
	public Div generateSelector (String search)
	{
		Div d = new Div();
		d.setSclass("identity-selector");
		d.setAttribute("identity", this);
		Image img = new Image (type == Type.USER ? "/img/user.svg" :
			type == Type.GROUP ? "/img/group.svg" :
			type == Type.ROLE ? "/img/role.svg":
			type == Type.GRANT ? "/img/role.svg":
			type == Type.APPLICATION ? "/img/application.svg":
			type == Type.NETWORK ? "/img/network.svg":
			type == Type.HOST ? "/img/host.svg":
				"/img/account.svg");
		img.setSclass("small-icon");
		d.appendChild(img);
		String l = selectorLabel == null ? label: selectorLabel;
		
		if (search == null || search.trim().isEmpty() || l.length() == 0)
		{
			Label label = new Label(l);
			d.appendChild(label);
		}
		else
		{
			boolean[] highlight = new boolean [l.length()];
			for (int i = 0; i < highlight.length; i++)
				highlight[i] = false;
			
			for (String s: search.trim().split(" +"))
			{
				int i = l.toUpperCase().indexOf(s.toUpperCase());
				while ( i >= 0)
				{
					for (int j = 0; j < s.length() && j + i < label.length(); j++)
						highlight[i + j] = true;
					i = l.toUpperCase().indexOf(s.toUpperCase(), i+1);
				}
			}
			
			int start = 0;
			int position = 1;
			for (position = 1; position <= highlight.length; position ++)
			{
				if (position == highlight.length || highlight[position] != highlight[start])
				{
					Label label = new Label(l.substring(start, position));
					if (highlight[start]) label.setStyle("font-weight: bold");
					d.appendChild(label);
					start = position;
				}
			}
		}
		return d;		
	}

	public Div generateTag (boolean canRemove)
	{
		Div d = new Div();
		d.setSclass(type == Type.USER ? "identity-tag-user" :
			type == Type.GROUP ? "identity-tag-group" :
			type == Type.ROLE ? "identity-tag-role":
			type == Type.GRANT ? "identity-tag-role":
				"identity-tag-account");
		d.setAttribute("identity", this);
		Image img = new Image (type == Type.USER ? "/img/user.svg" :
			type == Type.GROUP ? "/img/group.svg" :
			type == Type.GRANT ? "/img/key.svg":
			type == Type.ROLE ? "/img/role.svg":
			type == Type.HOST ? "/img/host.svg":
			type == Type.NETWORK ? "/img/network.svg":
				"/img/account.svg");
		img.setSclass("small-icon");
		d.appendChild(img);
		Label l = new Label(label);
		d.appendChild(l);
		ImageClic ic = new ImageClic("~./img/remove.png");
		ic.setTitle(Labels.getLabel("contenidoTarea.btnEliminar"));
		d.appendChild(ic);
		
		ic.addEventListener("onClick", onRemoveListener);
		return d;		
	}

	static EventListener onRemoveListener = new SerializableEventListener() {
		@Override
		public void onEvent(Event event) throws Exception {
			Component ic = event.getTarget();
			Events.postEvent(new Event("onRemove", ic.getParent()));
			ic.getParent().detach();
		}
	};

	private static Comparator<Identity> comparator = new Comparator<Identity>() {
		@Override
		public int compare(Identity o1, Identity o2) {
			return o1.compareTo(o2);
		}
	};
	
	public boolean equals (Object o)
	{
		if ( ! (o instanceof Identity))
			return false;
		Identity id = (Identity)o;
		if (id.type != type)
			return false;
		switch (type)
		{
		case USER:
			return ((User)getObject()).getId().equals(((User) id.getObject()).getId());
		case ROLE:
			return ((Role)getObject()).getId().equals(((Role) id.getObject()).getId());
		case GROUP:
			return ((Group)getObject()).getId().equals(((Group) id.getObject()).getId());
		case ACCOUNT:
			return ((Account)getObject()).getId().equals(((Account) id.getObject()).getId());
		}
		return false;
	}

	@Override
	public int compareTo(Identity o) {
		return label.compareTo(o.label);
	}
	
	public static Comparator<Identity> getComparator ()
	{
		return comparator ;
	}

	public String getSelectorLabel() {
		return selectorLabel;
	}

	public void setSelectorLabel(String selectorLabel) {
		this.selectorLabel = selectorLabel;
	}

	public static Identity fromObject(Object o) {
		if (o instanceof User)
			return new Identity((User) o);
		if (o instanceof Account)
			return new Identity((Account) o);
		if (o instanceof Application)
			return new Identity((Application) o);
		if (o instanceof CustomObject)
			return new Identity((CustomObject) o);
		if (o instanceof Group)
			return new Identity((Group) o);
		if (o instanceof Host)
			return new Identity((Host) o);
		if (o instanceof Network)
			return new Identity((Network) o);
		if (o instanceof Role)
			return new Identity((Role) o);
		throw new IllegalArgumentException("Invalid object "+o.toString());
	}

	public static Identity fromObject(Role role, Object o) {
		if (o instanceof Application)
			return new Identity(role, (Application) o);
		if (o instanceof DomainValue)
			return new Identity(role, (DomainValue) o);
		if (o instanceof Group)
			return new Identity(role, (Group) o);
		throw new IllegalArgumentException("Invalid object "+o.toString());
	}
}
