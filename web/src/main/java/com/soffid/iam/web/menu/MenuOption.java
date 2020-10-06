package com.soffid.iam.web.menu;

import java.util.List;

public class MenuOption {
	String literal;
	String label;
	String url;
	String img;
	String[] permissions;
	List<MenuOption> options;
	DynamicMenuHandler handler;
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String[] getPermissions() {
		return permissions;
	}
	public void setPermissions(String[] permissions) {
		this.permissions = permissions;
	}
	public List<MenuOption> getOptions() {
		return options;
	}
	public void setOptions(List<MenuOption> options) {
		this.options = options;
	}
	
	public DynamicMenuHandler getHandler() {
		return handler;
	}
	
	public void setHandler(DynamicMenuHandler handler) {
		this.handler = handler;
	}
	
	public String getLiteral() {
		return literal;
	}
	
	public void setLiteral(String literal) {
		this.literal = literal;
	}
}
