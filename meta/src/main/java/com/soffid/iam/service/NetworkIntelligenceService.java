package com.soffid.iam.service;

import com.soffid.iam.api.NetworkIntelligence;
import com.soffid.mda.annotation.Operation;
import com.soffid.mda.annotation.Service;

import roles.Tothom;

@Service
public class NetworkIntelligenceService {

	@Operation(grantees={Tothom.class})
	public NetworkIntelligence validateTokenFromSsokm(String token) {return null;}
}
