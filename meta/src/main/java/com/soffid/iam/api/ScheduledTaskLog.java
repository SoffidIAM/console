package com.soffid.iam.api;

import com.soffid.mda.annotation.Attribute;
import com.soffid.mda.annotation.Identifier;
import com.soffid.mda.annotation.Nullable;
import com.soffid.mda.annotation.ValueObject;

@ValueObject
public class ScheduledTaskLog {
	@Nullable
	@Identifier
	public java.lang.Long id;

	@Nullable
	public java.util.Date time;

	@Nullable
	public java.lang.String logReferenceID;

	@Attribute ( defaultValue="false")
	public boolean error;


}
