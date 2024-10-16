//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.servei;
import org.springframework.transaction.annotation.Transactional;

import com.soffid.iam.api.AsyncList;
import com.soffid.iam.api.PagedResult;
import com.soffid.iam.service.AsyncRunnerService;
import com.soffid.mda.annotation.Depends;
import com.soffid.mda.annotation.Nullable;
import com.soffid.mda.annotation.Operation;
import com.soffid.mda.annotation.Service;

import es.caib.seycon.ng.comu.Impressora;
import es.caib.seycon.ng.comu.UsuariImpressora;
import es.caib.seycon.ng.model.GrupEntity;
import es.caib.seycon.ng.model.TipusDadaEntity;

@Service (translatedName="PrinterService",
	translatedPackage="com.soffid.iam.service")
@Depends ({es.caib.seycon.ng.model.GrupImpressoraEntity.class,
	es.caib.seycon.ng.model.UsuariImpressoraEntity.class,
	es.caib.seycon.ng.model.UsuariEntity.class,
	es.caib.seycon.ng.servei.XarxaService.class,
	es.caib.seycon.ng.model.ImpressoraEntity.class,
	GrupEntity.class,
	AutoritzacioService.class,
	AsyncRunnerService.class,
	TipusDadaEntity.class})
public abstract class ImpressoraService {

	@Operation ( grantees={roles.Tothom.class},
			translated="getPrinters")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.Impressora> getImpressores()
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.printer_create.class},
			translated="create")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.Impressora create(
		es.caib.seycon.ng.comu.Impressora impressora)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.printer_delete.class},
			translated="delete")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void delete(
		es.caib.seycon.ng.comu.Impressora impressora)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Operation ( grantees={roles.printer_update.class},
			translated="update")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.Impressora update(
		es.caib.seycon.ng.comu.Impressora impressora)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.Tothom.class},
			translated="findPrintersByPrinterName")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.Impressora> findImpressoresByCodiImpressora(
		@Nullable java.lang.String codiImpressora)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.printer_query.class},
			translated="findPrintersByFilter")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.Impressora> findImpressoresByCriteri(
		@Nullable java.lang.String codi, 
		@Nullable java.lang.String model, 
		@Nullable java.lang.String local, 
		@Nullable java.lang.String maquina)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.Tothom.class},
			translated="findPrinterByPrinterName")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.Impressora findImpressoraByCodiImpressora(
		java.lang.String codiImpressora)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.user_printer_create.class,
			roles.user_printer_acl_create.class},
			translated="create")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.UsuariImpressora create(
		es.caib.seycon.ng.comu.UsuariImpressora usuariImpressora)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.user_printer_delete.class,
			roles.user_printer_acl_delete.class},
			translated="delete")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void delete(
		es.caib.seycon.ng.comu.UsuariImpressora usuariImpressora)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Operation ( grantees={roles.group_printer_create.class},
			translated="create")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.GrupImpressora create(
		es.caib.seycon.ng.comu.GrupImpressora grupImpressora)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.group_printer_delete.class},
			translated="delete")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void delete(
		es.caib.seycon.ng.comu.GrupImpressora grupImpressora)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Operation ( grantees={roles.Tothom.class},
			translated="findPrinterGroupByGroupNameAndPrinterName")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.GrupImpressora findGrupImpressoraByCodiGrupAndCodiImpressora(
		java.lang.String codiGrup, 
		java.lang.String codiImpressora)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.Tothom.class},
			translated="findPrinterUserByUserNameAndPrinterName")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.UsuariImpressora findUsuariImpressoraByCodiUsuariAndCodiImpressora(
		java.lang.String codiUsuari, 
		java.lang.String codiImpressora)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.printer_query.class},
			translated="getUserPrintersByPrinterName")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.UsuariImpressora> getUsuariImpressoresByCodiImpressora(
		java.lang.String codiImpressora)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.Tothom.class},
			translated="getPrintersGroupByPrinterName")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection getGrupImpressoresByCodiImpressora(
		java.lang.String codiImpressora)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.user_printer_create.class,
			roles.user_custom_update.class,
			roles.user_printer_acl_create.class},
			translated="update")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.UsuariImpressora update(
		es.caib.seycon.ng.comu.UsuariImpressora usuariImpressora)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.Tothom.class,roles.group_query.class},
			translated="findPrintersGroupByGroupName")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.GrupImpressora> findGrupImpressoresByCodiGrup(
		java.lang.String codiGrup)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.group_printer_create.class},
			translated="update")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.GrupImpressora update(
		es.caib.seycon.ng.comu.GrupImpressora grupImpressora)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	

	@Operation(grantees = { roles.printer_query.class })
	public java.util.List<Impressora> findPrinterByTextAndJsonQuery(
			@Nullable String text,
			@Nullable String jsonQuery,
			@Nullable Integer start, @Nullable Integer pageSize) {
		return null;
	}

	@Operation(grantees = { roles.printer_query.class })
	public AsyncList<Impressora> findPrinterByTextAndJsonQueryAsync(
			@Nullable String text,
			@Nullable String jsonQuery) {
		return null;
	}

	@Operation(grantees = { roles.printer_query.class })
	public PagedResult<UsuariImpressora> findPrinterUserByTextAndJsonQuery(
			@Nullable String text,
			@Nullable String jsonQuery,
			@Nullable Integer start, @Nullable Integer pageSize) {
		return null;
	}

	@Operation(grantees = { roles.printer_query.class })
	public AsyncList<UsuariImpressora> findPrinterUserByTextAndJsonQueryAsync(
			@Nullable String text,
			@Nullable String jsonQuery) {
		return null;
	}
}
