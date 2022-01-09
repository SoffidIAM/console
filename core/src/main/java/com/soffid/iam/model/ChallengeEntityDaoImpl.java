package com.soffid.iam.model;

import java.sql.Date;
import java.sql.Timestamp;

import com.soffid.iam.api.Challenge;

public class ChallengeEntityDaoImpl extends ChallengeEntityDaoBase {

	@Override
	public void toChallenge(ChallengeEntity source, Challenge target) {
		super.toChallenge(source, target);
		if (source.getAccount() != null)
			target.setAccount(getAccountEntityDao().toAccount(source.getAccount()));
		if (source.getUser() != null)
			target.setUser(getUserEntityDao().toUser(source.getUser()));
		if (source.getHost() != null)
			target.setHost(getHostEntityDao().toHost(source.getHost()));
		if (source.getClientHost() != null)
			target.setClientHost(getHostEntityDao().toHost(source.getClientHost()));
		target.setCentinelPort(source.getCentinelPort() == null ? -1: source.getCentinelPort().intValue());
		target.setTimeStamp(new Timestamp(source.getTimeStamp().getTime()));
		target.setClientVersion(source.getClientVersion() == null ? -1: source.getClientVersion().intValue());
	}

	@Override
	public void challengeToEntity(Challenge source, ChallengeEntity target, boolean copyIfNull) {
		super.challengeToEntity(source, target, copyIfNull);
		if (source.getUser() != null && source.getUser().getId() != null)
			target.setUser(getUserEntityDao().load(source.getUser().getId()));
		if (source.getAccount() != null && source.getAccount().getId() != null)
			target.setAccount(getAccountEntityDao().load(source.getAccount().getId()));
		if (source.getHost() != null && source.getHost().getId() != null)
			target.setHost(getHostEntityDao().load(source.getHost().getId()));
		if (source.getClientHost() != null && source.getClientHost().getId() != null)
			target.setClientHost(getHostEntityDao().load(source.getClientHost().getId()));
		if (source.getTimeStamp() == null)
			target.setTimeStamp(new Date(System.currentTimeMillis()));
		else
			target.setTimeStamp( new Date(source.getTimeStamp().getTime()));
		target.setCentinelPort(new Integer(source.getCentinelPort()));
		target.setClientVersion(source.getClientVersion());
	}

	@Override
	public ChallengeEntity challengeToEntity(Challenge instance) {
		ChallengeEntity che = findByChallengeId(instance.getChallengeId());
		if (che == null)
			che = newChallengeEntity();
		challengeToEntity(instance, che, true);
		return che;
	}

}
