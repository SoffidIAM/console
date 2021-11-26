package com.soffid.iam.api;

import org.ietf.jgss.GSSContext;

import com.soffid.mda.annotation.Nullable;
import com.soffid.mda.annotation.ValueObject;

import es.caib.seycon.ng.comu.Account;
import es.caib.seycon.ng.comu.Maquina;
import es.caib.seycon.ng.comu.Password;
import es.caib.seycon.ng.comu.Usuari;

@ValueObject
public class OtpChallengeProxy {
	@Nullable
    Usuari user;
	@Nullable
    Account account;
	@Nullable
    String otpHandler;
	@Nullable
    String cardNumber;
	@Nullable
    String cell;
	@Nullable
    String value;
}
