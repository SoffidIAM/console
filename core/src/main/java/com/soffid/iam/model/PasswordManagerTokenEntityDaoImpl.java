package com.soffid.iam.model;

import java.util.Date;

public class PasswordManagerTokenEntityDaoImpl extends PasswordManagerTokenEntityDaoBase {
	long lastDeleteExpired = 0L;
	@Override
	protected void handleDeleteExpired() throws Exception {
		if (System.currentTimeMillis() - lastDeleteExpired < 60000L)
			return;
		lastDeleteExpired = System.currentTimeMillis();
		getSession().createQuery("delete from com.soffid.iam.model.PasswordManagerTokenEntity "
				+ "where expires < :now")
			.setDate("now",	new Date())
			.executeUpdate();
	}

}
