package com.soffid.iam.service;

import java.io.PrintWriter;

import com.soffid.iam.api.NetworkIntelligence;
import com.soffid.mda.annotation.Operation;
import com.soffid.mda.annotation.Service;

import roles.Tothom;

@Service
public class NetworkIntelligenceService {

	@Operation(grantees={Tothom.class})
	public NetworkIntelligence validateToken(String token) {return null;}

	@Operation(grantees={Tothom.class})
	public Boolean isPasswordBreached(String password) {return null;}

	@Operation(grantees={Tothom.class})
	public Boolean isAccountBreached(String account, String system) {return null;}

	@Operation(grantees={Tothom.class})
	public String isEmailBreached(String shortName, String mailDomain) {return null;}

	@Operation(grantees={Tothom.class})
	public void verifyDomains(PrintWriter out) {return;}
}
