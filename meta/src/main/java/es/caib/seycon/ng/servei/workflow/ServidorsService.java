//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.servei.workflow;
import com.soffid.mda.annotation.*;

import org.springframework.transaction.annotation.Transactional;

@Service (translatedName="ServerService",
	translatedPackage="com.soffid.iam.service.workflow")
@Depends ({es.caib.seycon.ng.servei.XarxaService.class})
public abstract class ServidorsService {

	@Operation (translated="getMailServers")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.Maquina> getServidorsCorreu()
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.Maquina> getServidorsPerfil()
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation (translated="getHomeServers")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.Maquina> getServidorsHome()
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation (translated="getMailboxNameByMailServer")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.lang.String getNomBustiaDeCorreuByServidorCorreu(
		java.lang.String nomUsuari, 
		java.lang.String llinatgeUsuari, 
		java.lang.String nomServidorCorreu)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void sendMail(
		java.lang.String codiUsuari, 
		java.lang.String header, 
		java.lang.String content)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Operation ( grantees={roles.Tothom.class})
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void sendMailGeneric(
		java.lang.String adrecaCorreuCompleta, 
		java.lang.String header, 
		java.lang.String content)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
}
