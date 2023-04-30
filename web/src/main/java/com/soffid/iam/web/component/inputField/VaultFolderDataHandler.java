package com.soffid.iam.web.component.inputField;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.EventListener;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.Group;
import com.soffid.iam.api.VaultFolder;
import com.soffid.iam.service.ejb.VaultService;
import com.soffid.iam.web.component.InputField3;
import com.soffid.iam.web.popup.FinderHandler;
import com.soffid.iam.api.Account;
import com.soffid.iam.api.AsyncList;
import com.soffid.iam.api.CrudHandler;
import com.soffid.iam.api.DataType;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.component.Databox;

public class VaultFolderDataHandler extends InputFieldDataHandler<VaultFolder> {

	private CrudHandler<VaultFolder> handler;
	VaultService vaultService = EJBLocator.getVaultService();

	public VaultFolderDataHandler(DataType dataType) throws InternalErrorException, NamingException, CreateException {
		super (dataType);
		handler = EJBLocator.getCrudRegistryService().getHandler(VaultFolder.class);
	}

	@Override
	public String getDescription(String name, String filter) throws InternalErrorException, NamingException, CreateException {
		VaultFolder vf = vaultService.findFolder(Long.parseLong(name));
		if (vf == null)
			return null;
		else {
			String path = vf.getName();
			while (vf != null && vf.getParentId() != null) {
				vf = vaultService.findFolder(vf.getParentId());
				if (vf != null) {
					path = vf.getName()+"/"+path;
				}
			}
			return path;
		}
	}

	@Override
	public AsyncList<VaultFolder> search(String text, String filter) throws Exception {
		String [] texts = text.split("[ /]+");
		List<AsyncList<VaultFolder>> src = new LinkedList<AsyncList<VaultFolder>>();
		for (String t: texts) {
			src.add(handler.readAsync(t, filter));
		}
		return new VaultFolderAsyncList(src, texts);
	}

	@Override
	public void openFinder(String filter, boolean multiple, Component databox, EventListener listener) throws Exception {
		FinderHandler.startWizard("Select folder", VaultFolder.class.getName(),
				databox, multiple, 
				filter,
				listener);
		
	}

	@Override
	public String followLink(String value) {
		return null;
	}

	@Override
	public String[] toNameDescription(VaultFolder vf0) {
		try {
			VaultFolder vf = vf0;
			String path = vf.getName();
			while (vf != null && vf.getParentId() != null) {
				vf = vaultService.findFolder(vf.getParentId());
				if (vf != null) {
					path = vf.getName()+"/"+path;
				}
			}
			return new String[] {vf0.getId().toString(), path};
		} catch (InternalErrorException e) {
			return new String[] {vf0.getId().toString(), vf0.getName()};
		}
	}

	@Override
	public VaultFolder getObject(String name, String filter) throws InternalErrorException, NamingException, CreateException {
		VaultFolder vf = vaultService.findFolder(Long.parseLong(name));
		return vf;
	}

	@Override
	protected String getClassName() {
		return VaultFolder.class.getName();
	}
}
