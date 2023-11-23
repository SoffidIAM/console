package com.soffid.iam.service;

import com.soffid.mda.annotation.Nullable;
import com.soffid.mda.annotation.Service;

@Service(internal = true)
public class SignalService {
	public void signalAccount (String signal, String account, String system, @Nullable String ...attributes) {}

	public void signalUser (String signal, String user, @Nullable String ...attributes) {}

	public void signal (String signal, @Nullable String ...attributes) {}
}
