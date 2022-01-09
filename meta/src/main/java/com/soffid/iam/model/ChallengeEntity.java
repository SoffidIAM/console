package com.soffid.iam.model;

import java.util.Date;
import java.util.Collection;

import com.soffid.mda.annotation.Column;
import com.soffid.mda.annotation.DaoFinder;
import com.soffid.mda.annotation.Depends;
import com.soffid.mda.annotation.Entity;
import com.soffid.mda.annotation.Identifier;
import com.soffid.mda.annotation.Nullable;

import es.caib.seycon.ng.comu.Challenge;
import es.caib.seycon.ng.model.AccountEntity;
import es.caib.seycon.ng.model.MaquinaEntity;
import es.caib.seycon.ng.model.UsuariEntity;

@Entity(table = "SC_CHALLE")
@Depends( { Challenge.class} )
public class ChallengeEntity {
	@Column(name = "CHA_ID")
	@Nullable @Identifier Long id;
	
	@Column(name="CHA_TYPE")
	int type;
	
	@Column(name="CHA_USU_ID") @Nullable 
	UsuariEntity user;
	
	@Nullable @Column(name="CHA_ACC_ID")
    AccountEntity account;

	@Nullable @Column(name="CHA_USEKEY", length = 100)
    String userKey;
    
	@Nullable @Column(name="CHA_MAQ_ID")
    MaquinaEntity host;

	@Nullable @Column(name="CHA_CL_MAQ_ID")
    MaquinaEntity clientHost;
	
	@Nullable @Column(name="CHA_CENTINEL")
	Integer centinelPort;
	
	@Nullable @Column(name="CHA_OTPHAN")
    String otpHandler;
	
	@Nullable @Column(name="CHA_CARNUM")
    String cardNumber;
	
	@Nullable @Column(name="CHA_CELL")
    String cell;
	
	@Nullable @Column(name="CHA_VALUE")
    String value;
	
	@Nullable @Column(name="CHA_DATE")
	Date timeStamp;

	@Nullable @Column(name="CHA_CLIVER")
    Integer clientVersion;

	@Nullable @Column(name="CHA_KERBER")
    String kerberosDomain;

	@Nullable @Column(name="CHA_CHAID")
    String challengeId;

	@Nullable @Column(name="CHA_DOMAIN", length=100)
    private String domain;
	
	@Column(name="CHA_CLOSES")
    boolean closeOldSessions;
	
	@Column(name="CHA_SILENT")
    boolean silent;
	
	@Column(name="CHA_TEN_ID")
	TenantEntity tenant;

	@DaoFinder
	ChallengeEntity findByChallengeId(String challengeId) {return null;}

	@DaoFinder("select ch from com.soffid.iam.model.ChallengeEntity as ch "
			+ "where ch.tenant.id=:tenantId and ch.timeStamp < :timeStamp")
	Collection<ChallengeEntity> findExpiredChallenges(java.util.Date timeStamp) {return null;}
}
