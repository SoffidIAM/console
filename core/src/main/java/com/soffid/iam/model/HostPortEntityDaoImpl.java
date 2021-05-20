package com.soffid.iam.model;

import com.soffid.iam.api.HostPort;

public class HostPortEntityDaoImpl extends HostPortEntityDaoBase {

	@Override
	public void toHostPort(HostPortEntity source, HostPort target) {
		super.toHostPort(source, target);
		target.setHostId(source.getHost().getId());
		target.setHostName(source.getHost().getName());
	}

	@Override
	public void hostPortToEntity(HostPort source, HostPortEntity target, boolean copyIfNull) {
		super.hostPortToEntity(source, target, copyIfNull);
		target.setHost(getHostEntityDao().load(source.getHostId()));
	}

}
