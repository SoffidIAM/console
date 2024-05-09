package com.soffid.iam.service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;

import com.soffid.iam.model.PasswordManagerTokenEntity;
import com.soffid.iam.model.SessionEntity;
import com.soffid.iam.utils.ConfigurationCache;
import com.soffid.iam.utils.Security;

import es.caib.seycon.ng.comu.TipusSessio;

public class PasswordManagerServiceImpl extends PasswordManagerServiceBase {

	@Override
	protected String handleFindUserByToken(String token) throws Exception {
		getPasswordManagerTokenEntityDao().deleteExpired();
		PasswordManagerTokenEntity t = getPasswordManagerTokenEntityDao().findByToken(token);
		if (t == null)
			t = getPasswordManagerTokenEntityDao().findByOldToken(token);
		if (t == null) {
			for (SessionEntity s: getSessionEntityDao().findByKey(token)) {
				if (s.getUser().getActive().equals("S") && s.getType() == TipusSessio.ESSO)
					return s.getUser().getUserName();
			}
		}
		if (t == null)
			return null;
		else if (t.getUser().getActive().equals("S"))
			return t.getUser().getUserName();
		else
			return null;
	}

	@Override
	protected String handleGenerateToken(String user) throws Exception {
		byte b[] = new byte[36];
		new SecureRandom().nextBytes(b);
		String token = Base64.getEncoder().encodeToString(b);
		PasswordManagerTokenEntity e = getPasswordManagerTokenEntityDao().newPasswordManagerTokenEntity();
		e.setUser(getUserEntityDao().findByUserName(user));
		if (e.getUser() == null)
			return null;
		e.setCreated(new Date());
		e.setExpires(new Date(System.currentTimeMillis() + getTokenDuration()));
		e.setRenew(new Date(System.currentTimeMillis()+getTokenRenew()));
		e.setToken(token);
		getPasswordManagerTokenEntityDao().create(e);
		return token;
	}

	protected long getTokenDuration() {
		long duration = 24 * 60 * 60 * 1000L; // 24 hours
		try {
			String d = ConfigurationCache.getProperty("soffid.passwordManager.token.timeout");
			if (d != null) {
				duration = Long.parseLong(d) * 60 * 1000L;
			}
		} catch (Exception ex) {
			
		}
		return duration;
	}

	protected long getTokenRenew() {
		long duration = 15 * 60 * 1000L; // 15 minutes
		try {
			String d = ConfigurationCache.getProperty("soffid.passwordManager.token.renew");
			if (d != null) {
				duration = Long.parseLong(d) * 60 * 1000L;
			}
		} catch (Exception ex) {
			
		}
		return duration;
	}

	@Override
	protected String handleRenewToken(String token) throws Exception {
		String user = Security.getCurrentUser();
		PasswordManagerTokenEntity t = getPasswordManagerTokenEntityDao().findByToken(token);
		if (t == null)
			t = getPasswordManagerTokenEntityDao().findByOldToken(token);
		if (t == null) {
			if (user == null)
				return null;
			else
				return handleGenerateToken(user);
		}
		if (System.currentTimeMillis() > t.getRenew().getTime() ||
				token.equals(t.getOldToken())) {
			byte b[] = new byte[36];
			new SecureRandom().nextBytes(b);
			t.setOldToken(token);
			token = Base64.getEncoder().encodeToString(b);
			t.setToken(token);
			t.setRenew(new Date(System.currentTimeMillis()+getTokenRenew()));
			if (user != null) {
				t.setExpires(new Date(System.currentTimeMillis()+getTokenDuration()));
			}
			getPasswordManagerTokenEntityDao().update(t);
		} else if (t.getOldToken() != null) {
			t.setOldToken(null);
			getPasswordManagerTokenEntityDao().update(t);
		}
		return token;
	}

}
