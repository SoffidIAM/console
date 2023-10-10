package com.soffid.iam.service;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.Date;
import java.util.List;

import com.soffid.iam.api.JumpServerGroup;
import com.soffid.iam.api.NewPamSession;
import com.soffid.iam.api.PamSession;
import com.soffid.iam.model.JumpServerEntity;
import com.soffid.iam.model.JumpServerGroupEntity;
import com.soffid.mda.annotation.Depends;
import com.soffid.mda.annotation.Description;
import com.soffid.mda.annotation.Nullable;
import com.soffid.mda.annotation.Operation;
import com.soffid.mda.annotation.Role;
import com.soffid.mda.annotation.Service;

import es.caib.seycon.ng.comu.Account;
import es.caib.seycon.ng.comu.Sessio;
import es.caib.seycon.ng.comu.TipusSessio;
import es.caib.seycon.ng.model.AccountEntity;
import es.caib.seycon.ng.model.AuditoriaEntity;
import es.caib.seycon.ng.model.MaquinaEntity;
import es.caib.seycon.ng.model.RegistreAccesEntity;
import es.caib.seycon.ng.model.ServeiEntity;
import es.caib.seycon.ng.model.SessioEntity;
import es.caib.seycon.ng.model.UsuariEntity;
import es.caib.seycon.ng.servei.AccountService;
import es.caib.seycon.ng.servei.DispatcherService;
import es.caib.seycon.ng.servei.XarxaService;
import roles.Tothom;

@Service
@Depends({JumpServerGroupEntity.class, JumpServerEntity.class, AccountEntity.class, AccountService.class,
	AuditoriaEntity.class,
	ServeiEntity.class,
	RegistreAccesEntity.class, SessioEntity.class, UsuariEntity.class,
	MaquinaEntity.class,
	DispatcherService.class,
	XarxaService.class,
	PamSecurityHandlerService.class})
public class PamSessionService {
	@Operation(grantees = {Tothom.class})
	List<JumpServerGroup> findJumpServerGroups() {return null;}
	
	@Operation(grantees= {JumpServer_create.class})
	JumpServerGroup create (JumpServerGroup jumpServerGroup) {return null;}
	
	@Operation(grantees = {JumpServer_update.class})
	JumpServerGroup update (JumpServerGroup jumpServerGroup) {return null;}
	
	@Operation(grantees = {JumpServer_delete.class})
	void remove (JumpServerGroup jumpServerGroup) {}
	
	@Operation(grantees = {Tothom.class})
	@Description("Creates a jump server session and returns the session URL")
	NewPamSession createJumpServerSession (Account account) {return null;}

	@Operation(grantees = {Tothom.class})
	@Description("Creates a jump server session and returns the session URL")
	NewPamSession createJumpServerSession (Account account, String entryPointDescriptor) {return null;}

	@Operation(grantees = {Tothom.class})
	@Description("Creates a jump server session and returns the session URL")
	NewPamSession createJumpServerSession (Account account, String entryPointDescriptor, String pamPolicy) {return null;}

	@Description("Creates a jump server session and returns the session URL. Internal method for SSH and RDP proxies")
	NewPamSession createCustomJumpServerSession (Account account, @Nullable String sourceIp, @Nullable TipusSessio type, @Nullable String info ) {return null;}

	@Description("Checks a server session is up")
	boolean checkJumpServerSession (Sessio sessio) {return false;}
	
	@Operation(grantees = {pamSession_query.class})
	@Description("Retrieves a pam session descriptor")
	PamSession findSession (String serverGroup, String sessionId) {return null;}

	@Operation(grantees = {pamSession_query.class})
	@Description("Retrieves a pam session keystrokes")
	void generateKeystrokes (PamSession session, OutputStream stream) { }

	@Operation(grantees = {pamSession_query.class})
	@Description("Retrieves a pam session video")
	void generateVideo (PamSession session, long chapter, OutputStream stream, long start, long end) { }
	
	@Operation(grantees = {pamSession_query.class})
	@Description("Retrieves a pam session video")
	long getVideoSize (PamSession session, long chapter) { return 0; }
	
	Integer getActiveSessions(String server)  {return null;}
	
	Long getConsoleFreeSpace(String jumpServerGroup)  {return null;}
	
	Long getConsoleUsedSpace(String jumpServerGroup)  {return null;}
	
	@Operation(grantees = {pamSession_query.class})
	@Description("Searches in pam sessions log")
	List<PamSession> search(
			@Nullable String jumpServerGroup,
			@Nullable String url, @Nullable String text,
			@Nullable String user, @Nullable Date since, @Nullable Date until) {return null;}

	@Operation(grantees = {pamSession_query.class})
	@Description("Searches in pam sessions log")
	List<PamSession> search(
			@Nullable String jumpServerGroup,
			@Nullable String url, @Nullable String text, @Nullable String screenshots,
			@Nullable String user, @Nullable Date since, @Nullable Date until) {return null;}
}

@Role (name="seu:pamSessionConfigure" ) class Soffid_PamSessionConfigure { }

@Role (name="seu:pamSessionAudit" ) class Soffid_pamSessionAudit { }

@Role (name="jumpServer:create" ) class JumpServer_create { }

@Role (name="jumpServer:update" ) class JumpServer_update { }

@Role (name="jumpServer:delete" ) class JumpServer_delete { }

@Role (name="jumpServer:query" ) class JumpServer_query { }

@Role (name="pamSession:query" ) class pamSession_query { }

@Role (name="pamSession:create" ) class pamSession_create { }
