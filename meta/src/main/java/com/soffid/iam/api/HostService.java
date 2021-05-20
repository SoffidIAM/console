package com.soffid.iam.api;

import com.soffid.iam.model.HostPortEntity;
import com.soffid.iam.model.HostServiceEntity;
import com.soffid.mda.annotation.Depends;
import com.soffid.mda.annotation.Identifier;
import com.soffid.mda.annotation.Nullable;
import com.soffid.mda.annotation.ValueObject;

@ValueObject
@Depends({HostPortEntity.class})
public class HostService {
	@Nullable @Identifier Long id;
	
	Long hostId;
	
	String hostName;
	
	String service;
	
	Long accountId;
	
	String accountName;
	
	String accountSystem;
}
