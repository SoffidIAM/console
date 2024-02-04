package com.soffid.iam.service;

import com.soffid.iam.model.PasswordManagerTokenEntity;
import com.soffid.mda.annotation.Depends;
import com.soffid.mda.annotation.Nullable;
import com.soffid.mda.annotation.Operation;
import com.soffid.mda.annotation.Service;

import es.caib.seycon.ng.model.UsuariEntity;

@Depends({PasswordManagerTokenEntity.class,
	UsuariEntity.class})
@Service(internal = true)
public class PasswordManagerService {
	@Operation ( grantees={roles.Tothom.class})
	String findUserByToken(String token) { return null; }
	
	@Operation ( grantees={roles.Tothom.class})
	String renewToken(String token) {return null;}

	@Operation ( grantees={roles.Tothom.class})
	String generateToken(String user) {return null;}
}
