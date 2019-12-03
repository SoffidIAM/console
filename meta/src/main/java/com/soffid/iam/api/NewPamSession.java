package com.soffid.iam.api;

import java.net.URL;
import java.util.Date;
import java.util.List;

import com.soffid.mda.annotation.Nullable;
import com.soffid.mda.annotation.ValueObject;

@ValueObject
public class NewPamSession {
	String sessionId;
	
	String jumpServerGroup;
	
	URL url;
	
}
