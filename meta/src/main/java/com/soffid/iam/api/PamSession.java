package com.soffid.iam.api;

import java.util.Date;
import java.util.List;

import com.soffid.mda.annotation.Nullable;
import com.soffid.mda.annotation.ValueObject;

@ValueObject
public class PamSession {
	@Nullable
	String id;
	
	@Nullable
	String user;
	
	@Nullable
	String accountName;
	
	@Nullable
	String jumpServerGroup;
	
	@Nullable
	String serverUrl;
	
	@Nullable
	String path;
	
	@Nullable
	List<Long> chapters;
	
	@Nullable
	Date serverStart;
	
	@Nullable
	Date serverEnd;
	
	@Nullable
	List<Long> bookmarks;

}
