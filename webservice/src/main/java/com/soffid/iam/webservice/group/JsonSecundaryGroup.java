package com.soffid.iam.webservice.group;

import com.soffid.iam.api.GroupUser;

@SuppressWarnings("serial")
public class JsonSecundaryGroup extends GroupUser {

	private Long id = null;
	private String group = null;
	private String groupDescription = null;

	public JsonSecundaryGroup(GroupUser secundaryGroup) {
		this.id = secundaryGroup.getId();
		this.group = secundaryGroup.getGroup();
		this.groupDescription = secundaryGroup.getGroupDescription();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getGroupDescription() {
		return groupDescription;
	}

	public void setGroupDescription(String groupDescription) {
		this.groupDescription = groupDescription;
	}
}
