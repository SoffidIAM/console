package com.soffid.iam.sync.service;

import java.util.List;
import java.util.Map;

import com.soffid.iam.api.NewPamSession;
import com.soffid.iam.api.OtpChallengeProxy;
import com.soffid.iam.service.OTPValidationService;
import com.soffid.iam.service.PamSecurityHandlerService;
import com.soffid.iam.service.PamSessionService;
import com.soffid.mda.annotation.Depends;
import com.soffid.mda.annotation.Description;
import com.soffid.mda.annotation.Nullable;
import com.soffid.mda.annotation.Service;

import es.caib.bpm.servei.BpmEngine;
import es.caib.bpm.vo.ProcessInstance;
import es.caib.seycon.ng.comu.Account;
import es.caib.seycon.ng.comu.Challenge;
import es.caib.seycon.ng.comu.Password;
import es.caib.seycon.ng.comu.PasswordValidation;
import es.caib.seycon.ng.comu.TipusSessio;
import es.caib.seycon.ng.comu.Usuari;
import es.caib.seycon.ng.model.AccountEntity;
import es.caib.seycon.ng.model.UsuariEntity;
import es.caib.seycon.ng.servei.AccountService;
import es.caib.seycon.ng.servei.DadesAddicionalsService;
import es.caib.seycon.ng.servei.DispatcherService;
import es.caib.seycon.ng.servei.PasswordService;
import es.caib.seycon.ng.servei.UsuariService;

@Service(serverOnly = true , serverRole = "agent", serverPath = "/seycon/PamsshSessionService")
@Depends({OTPValidationService.class, AccountEntity.class, AccountService.class, BpmEngine.class, PamSessionService.class, DispatcherService.class,
	UsuariService.class, PasswordService.class,
	DadesAddicionalsService.class,
	UsuariEntity.class, PamSecurityHandlerService.class})
public class PamProxySessionService {
	public PasswordValidation validatePassword(String user, Password password ) { return null;}
	
	@Description("Returns the user name")
	public boolean validateSshKey(String user, String sshKey) {return false;}
	
	public OtpChallengeProxy generateOtp(String user) {return null;}
	
	public boolean validatePin(OtpChallengeProxy challenge, String value) { return false;}
	
	public List<Account> findAccounts(String userName, String url, String accountName) {return null;}
	
	public ProcessInstance startWorkflow(String workflow, String userName, Account account, @Nullable int hours, @Nullable String comments) {return null;}

	public NewPamSession openSession (String userName, Account account,
			@Nullable String sourceIp,
			@Nullable TipusSessio type, @Nullable String info, @Nullable Map<String,Map<String,String>> obligations)
		{return null;}
	
	public void sendEmailNotification(Map<String, String> obligationDetails) {}
}
