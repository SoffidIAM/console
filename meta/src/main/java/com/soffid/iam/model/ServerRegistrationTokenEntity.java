package com.soffid.iam.model;

import java.util.Date;

import com.soffid.iam.api.ServerRegistrationToken;
import com.soffid.mda.annotation.Column;
import com.soffid.mda.annotation.DaoFinder;
import com.soffid.mda.annotation.DaoOperation;
import com.soffid.mda.annotation.Depends;
import com.soffid.mda.annotation.Entity;
import com.soffid.mda.annotation.Identifier;
import com.soffid.mda.annotation.Nullable;

@Entity(table = "SC_SRRETO")
@Depends({ServerRegistrationToken.class})
public class ServerRegistrationTokenEntity {
	@Nullable @Identifier
	@Column(name="SRT_ID")
	Long id;
	
	@Column(name = "SRT_STEP")
	int step;
	
	@Column(name = "SRT_TOKEN", length = 100)
	String token;
	
	@Column(name = "SRT_EXPIR")
	Date expiration;
	
	@Column(name = "SRT_TEN_ID")
	TenantEntity tenant;
	
	@DaoOperation
	void removeExpiredTokens() {};
	
	@DaoFinder
	ServerRegistrationTokenEntity findByToken(String token) {return null;}
	
}
