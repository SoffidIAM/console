package com.soffid.iam.web.menu;

import java.util.List;

import org.zkoss.image.Image;

import com.soffid.iam.api.AccessTree;
import com.soffid.iam.api.Account;
import com.soffid.iam.web.launcher.LauncherMenuHandler;

public class MenuOption implements Cloneable {
	String literal;
	String label;
	String url;
	String img;
	String[] permissions;
	String menuType;
	String menuId;
	AccessTree accessTree;
	Image image;
	boolean small = false;
	
	List<MenuOption> options;
	DynamicMenuHandler handler;
	private DynamicLauncher execHandler;
	private Account account;
	
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
	
	public String getMenuType() {
		return menuType;
	}
	
	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}
	
	public String getMenuId() {
		return menuId;
	}
	
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public void setExecHandler(DynamicLauncher launcherMenuHandler) {
		this.execHandler = launcherMenuHandler;
		
	}
	
	public DynamicLauncher getExecHandler() {
		return execHandler;
	}
	
	public AccessTree getAccessTree() {
		return accessTree;
	}
	
	public void setAccessTree(AccessTree accessTree) {
		this.accessTree = accessTree;
	}
	
	public boolean isSmall() {
		return small;
	}
	
	public void setSmall(boolean small) {
		this.small = small;
	}
	public void setAccount(Account account) {
		this.account = account;
		
	}
	
	public Account getAccount() {
		return account;
	}
	@Override
	public Object clone()  {
		MenuOption mo;
		mo = new MenuOption();
		mo.accessTree = accessTree;
		mo.account = account;
		mo.execHandler = execHandler;
		mo.handler = handler;
		mo.img = img;
		mo.image = image;
		mo.label = label;
		mo.literal = literal;
		mo.menuId = menuId;
		mo.menuType = menuType;
		mo.options = options;
		mo.permissions = permissions;
		mo.small = small;
		mo.url = url;
		return mo;
	}
	
	public Image getImage() {
		return image;
	}
	
	public void setImage(Image image) {
		this.image = image;
	}
}
