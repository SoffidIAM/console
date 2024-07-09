package com.soffid.iam.api;

import java.util.Date;

import com.soffid.mda.annotation.Identifier;
import com.soffid.mda.annotation.ValueObject;

@ValueObject
public class NetworkIntelligence {

	@Identifier public Long id;
	public String token;
	public String level;
	public Date start;
	public Date end;
	public Boolean enabled;
}
