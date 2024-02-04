package com.soffid.iam.model;

import java.util.Date;

import com.soffid.mda.annotation.Column;
import com.soffid.mda.annotation.DaoFinder;
import com.soffid.mda.annotation.DaoOperation;
import com.soffid.mda.annotation.Entity;
import com.soffid.mda.annotation.Identifier;
import com.soffid.mda.annotation.Index;
import com.soffid.mda.annotation.Nullable;

import es.caib.seycon.ng.model.UsuariEntity;

@Entity(table = "SC_PAMATO")
public class PasswordManagerTokenEntity {
	@Nullable @Identifier @Column(name="PMT_ID")
	Long id;
	
	@Column(name="PMT_USU_ID", reverseAttribute = "passwordManagerToken")
	UsuariEntity user;
	
	@Column(name="PMT_CREATE")
	Date created;
	
	@Column(name="PMT_EXPIRE")
	Date expires;
	
	@Column(name="PMT_RENEW")
	Date renew;

	@Column(name="PMT_TOKEN", length = 256)
	String token;

	@Nullable @Column(name="PMT_OLDTOK", length = 256)
	String oldToken;

	@DaoFinder
	PasswordManagerTokenEntity findByToken(String token) {return null;}
	
	@DaoFinder
	PasswordManagerTokenEntity findByOldToken(String oldToken) {return null;}

	@DaoOperation
	void deleteExpired() {}
}

@Index(columns = {"PMT_TOKEN"}, entity = PasswordManagerTokenEntity.class, name = "SC_PAMATO_UK")
class PasswordManagerTokenEntityByToken{}
