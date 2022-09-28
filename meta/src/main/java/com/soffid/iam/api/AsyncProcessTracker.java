package com.soffid.iam.api;

import java.util.Date;

import com.soffid.mda.annotation.Nullable;
import com.soffid.mda.annotation.ValueObject;

@ValueObject
public class AsyncProcessTracker {
	Long id;
	
	float progress;
	
	@Nullable String report;
	
	boolean finished;
	
	boolean cancelled;
	
	@Nullable String errorMessage;
	
	@Nullable Date start;
	
	@Nullable Date end;
	
	@Nullable String current;
}
