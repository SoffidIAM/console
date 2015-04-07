package com.soffid.iam.model;

import es.caib.seycon.ng.model.*;

import com.soffid.iam.model.RoleAccountEntity;
import org.zkoss.zk.au.in.GetUploadInfoCommand;

public class RoleAccountEntityImpl extends RoleAccountEntity
{

	@Override
	public String toString()
	{
		return String.format(Messages.getString("RoleAccountEntityImpl.0"), getAccount().getName(), getRole().toString());
	}

}
