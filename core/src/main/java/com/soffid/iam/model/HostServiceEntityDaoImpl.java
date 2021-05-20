package com.soffid.iam.model;

import com.soffid.iam.api.HostService;

public class HostServiceEntityDaoImpl extends HostServiceEntityDaoBase {

	@Override
	public void toHostService(HostServiceEntity source, HostService target) {
		super.toHostService(source, target);
		target.setHostId(source.getHost().getId());
		target.setHostName(source.getHost().getName());
		target.setAccountId(source.getAccount().getId());
		target.setAccountName(source.getAccount().getName());
		target.setAccountSystem(source.getAccount().getSystem().getName());
	}

	@Override
	public void hostServiceToEntity(HostService source, HostServiceEntity target, boolean copyIfNull) {
		super.hostServiceToEntity(source, target, copyIfNull);
		target.setHost(getHostEntityDao().load(source.getHostId()));
		target.setAccount( source.getAccountId() == null ? 
			getAccountEntityDao().findByNameAndSystem(source.getAccountName(), source.getAccountSystem()) :
			getAccountEntityDao().load(source.getAccountId()));
	}

}
