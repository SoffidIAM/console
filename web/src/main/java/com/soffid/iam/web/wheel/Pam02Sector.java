package com.soffid.iam.web.wheel;

import java.util.List;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.Network;
import com.soffid.iam.api.PagedResult;
import com.soffid.iam.api.VaultFolder;

public class Pam02Sector extends Sector {

	public Pam02Sector(String tag) {
		super(tag);
	}

	@Override
	public boolean isDone() {
		try {
			List<VaultFolder> list = EJBLocator.getVaultService().getPublicRootFolders();
			for (VaultFolder s: list) {
				if (! s.isPersonal())
					return true;
			}
			return false;
		}
		catch (Exception e) {
			return false;
		}
	}
}
