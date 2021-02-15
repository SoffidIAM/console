package com.soffid.iam.web.launcher;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zkoss.image.AImage;
import org.zkoss.image.Image;
import org.zkoss.zk.ui.Executions;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.AccessTree;
import com.soffid.iam.api.Account;
import com.soffid.iam.api.VaultFolder;
import com.soffid.iam.bpm.api.ProcessDefinition;
import com.soffid.iam.web.menu.DynamicMenuHandler;
import com.soffid.iam.web.menu.MenuOption;

import es.caib.bpm.exception.BPMException;
import es.caib.seycon.ng.exception.InternalErrorException;


public class LauncherMenuHandler implements DynamicMenuHandler {
	Log log = LogFactory.getLog(getClass());
	
	@Override
	public List<MenuOption> getOptions(MenuOption option) {
		if (option.getOptions() != null && !option.getOptions().isEmpty())
			return option.getOptions();
//		log.info("Fetching menus for "+option.getMenuType()+": "+option.getMenuId());
		List<MenuOption> list = new LinkedList<MenuOption>();
		try {
			if (option.getMenuType() == null) {
				processRoot(list);
			}
			else if ("appmenu".equals(option.getMenuType()))
			{
				processAppMenu(option, list);
			}
			else if ("vaultfolder".equals(option.getMenuType()))
			{
				processVaultFolder(option, list);
			}
		} catch ( Exception e) {
			log.warn("Error fetching process to start", e);
		}
		option.setSmall(list.size() > 6);
		return list;
	}

	private void processAppMenu(MenuOption option, List<MenuOption> list) throws InternalErrorException, NamingException, CreateException {
		AccessTree entryPoint = option.getAccessTree();
		for (AccessTree child: EJBLocator.getSelfService().findChildren(entryPoint )) {
			MenuOption o = new MenuOption();
			if (child.getIcon1Id() == null)
				o.setImg( child.isMenu() ? "/img/menu/container.svg": "/img/menu/launcher.svg" );
			else 
				generateIcon(o, child);
			o.setLiteral(child.getName());
			if (child.isMenu()) {
				o.setHandler(this);
				o.setMenuType("appmenu");
			} 
			else {
				o.setExecHandler( new MenuEntryLauncher());
			}
			o.setMenuId(child.getId().toString());
			o.setAccessTree(child);
			o.setLabel("appmenu_"+child.getId());
			list.add(o);
		}
	}

	public void generateIcon(MenuOption option, AccessTree child) {
		try {
			byte[] img = child.getIcon1Image();
			if (img.length > 10) {
				String sb = img[0] == '<' && img[1] == '?' ? "image/svg+xml" :
					img[0] == 0x77 && img[1] == 0xd8 && img[2] == 0xff ?  "image/jpeg":
					img[0] == 0x89 && img[1] == 0x50 && img[2] == 0x4e ?  "image/png":
					img[0] == 'G' && img[1] == 'I' && img[2] == 'F' ?  "image/gif":
							"image/x-icon";
				option.setImg( "data:"+sb+";base64," + Base64.getEncoder().encodeToString(img) );
				option.setImage(new AImage("icon", child.getIcon1Image()));
			}
		} catch (IOException e) {
		}
	}

	private void processVaultFolder(MenuOption option, List<MenuOption> list) throws InternalErrorException, NamingException, CreateException {
		Long id = Long.parseLong(option.getMenuId());
		VaultFolder folder = EJBLocator.getVaultService().findFolder(id.longValue());
		for (VaultFolder child: EJBLocator.getVaultService().getChildren( folder )) {
			MenuOption o = new MenuOption();
			o.setImg("/img/menu/container.svg");
			o.setLiteral(child.getName());
			o.setHandler(this);
			o.setMenuType("vaultfolder");
			o.setMenuId(child.getId().toString());
			o.setLabel("vaultfolder_"+child.getId());
			list.add(o);
		}
		
		for (Account account: EJBLocator.getVaultService().list(folder)) {
			MenuOption o = new MenuOption();
			o.setImg("/img/account.svg");
			o.setLiteral(( account.getLoginName() == null ?account.getName(): account.getLoginName()) + " - "+account.getDescription());
			o.setExecHandler(new AccountLauncher());
			o.setMenuId(account.getId().toString());
			o.setAccount(account);
			o.setLabel("account_"+account.getId());
			list.add(o);
		}
	}

	public void processRoot(List<MenuOption> list) throws InternalErrorException, NamingException, CreateException {
		AccessTree root = EJBLocator.getSelfService().findRoot();
		MenuOption o = new MenuOption();
		if (root.getIcon1Id() == null)
			o.setImg( "/img/menu/container.svg" );
		else 
			generateIcon(o, root);
		o.setLiteral(root.getName());
		o.setLabel("appmenu");
		o.setHandler(this);
		o.setMenuType("appmenu");
		o.setMenuId(root.getId().toString());
		o.setAccessTree(root);
		list.add(o);
		
		for ( VaultFolder folder: EJBLocator.getVaultService().getPublicRootFolders()) {
			o = new MenuOption();
			o.setImg("/img/menu/container.svg");
			o.setLiteral(folder.getName());
			o.setHandler(this);
			o.setMenuType("vaultfolder");
			o.setLabel("vaultfolder_"+folder.getId());
			o.setMenuId(folder.getId().toString());
			list.add(o);
		}
	}

	@Override
	public String getTip(MenuOption option) {
		return null;
	}

	@Override
	public boolean isVisible(MenuOption option) {
		return true;
	}

	@Override
	public boolean isLeaf() {
		return false;
	}
}
