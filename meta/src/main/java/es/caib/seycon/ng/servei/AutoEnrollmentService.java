//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.servei;
import com.soffid.mda.annotation.*;

import org.springframework.transaction.annotation.Transactional;

@Service (translatedName="AutoEnrollmentService",
	translatedPackage="com.soffid.iam.service")
@Depends ({es.caib.seycon.ng.servei.InternalPasswordService.class,
	es.caib.seycon.ng.model.DominiContrasenyaEntity.class,
	es.caib.seycon.ng.model.TasqueEntity.class,
	es.caib.seycon.ng.model.UsuariEntity.class,
	es.caib.seycon.ng.model.DadaUsuariEntity.class})
public abstract class AutoEnrollmentService {

	@Operation (translated="registration")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.lang.Boolean alta(
		java.lang.String nom, 
		java.lang.String llinatge1, 
		java.lang.String llinatge2, 
		java.lang.String correuElectronic, 
		@Nullable java.lang.String urlServidor)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation (translated="assignPassword")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.lang.Boolean assignarPassword(
		java.lang.String correuElectronic, 
		java.lang.String PIN, 
		java.lang.String newPassword)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation (translated="resetPassword")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.lang.Boolean resetejarPassword(
		java.lang.String correuElectronic, 
		java.lang.String oldPassword, 
		java.lang.String newPassword)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation (translated="modifyData")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.lang.Boolean modificarDades(
		java.lang.String nom, 
		java.lang.String llinatge1, 
		java.lang.String llinatge2, 
		java.lang.String correuElectronic, 
		java.lang.String codiUsuariAnonim)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation (translated="sendNewPIN")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void enviarNouPIN(
		java.lang.String correuElectronic, 
		java.lang.String urlServidor)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Operation (translated="seeData")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.UsuariAnonim consultarDades(
		java.lang.String codiUsuariAnonim)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
}
