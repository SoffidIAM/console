package com.soffid.iam.model;

import java.util.Date;

import com.soffid.iam.api.ServerRegistrationToken;

public class ServerRegistrationTokenEntityDaoImpl extends ServerRegistrationTokenEntityDaoBase {

	@Override
	public ServerRegistrationTokenEntity serverRegistrationTokenToEntity(ServerRegistrationToken instance) {
		ServerRegistrationTokenEntity entity = newServerRegistrationTokenEntity();
		super.serverRegistrationTokenToEntity(instance, entity, true);
		return entity;
	}

	@Override
	protected void handleRemoveExpiredTokens() throws Exception {
		getSession().createQuery("delete from com.soffid.iam.model.ServerRegistrationTokenEntityImpl where expiration < :expiration")
			.setDate("expiration", new Date())
			.executeUpdate();
	}

}
