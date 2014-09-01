//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.servei;
import com.soffid.mda.annotation.*;

import org.springframework.transaction.annotation.Transactional;

@Service ( serverPath="/seycon/DadesAddicionalsService",
	 serverRole="agent",
	 translatedName="AdditionalDataService",
	 translatedPackage="com.soffid.iam.service")
@Depends ({es.caib.seycon.ng.model.TipusDadaEntity.class,
	es.caib.seycon.ng.model.DadaUsuariEntity.class,
	es.caib.seycon.ng.model.UsuariEntity.class,
	com.soffid.iam.service.RuleEvaluatorService.class})
public abstract class DadesAddicionalsService {

	@Operation (translated="getDataTypes")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.TipusDada> getTipusDades()
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.metadata_create.class},
			translated="create")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.TipusDada create(
		es.caib.seycon.ng.comu.TipusDada tipusDada)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.metadata_delete.class},
			translated="delete")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void delete(
		es.caib.seycon.ng.comu.TipusDada tipusDada)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Operation ( grantees={roles.metadata_update.class},
			translated="update")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.TipusDada update(
		es.caib.seycon.ng.comu.TipusDada tipusDada)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.metadata_query.class},
			translated="findDataTypesByName")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.TipusDada> findTipusDadesByCodi(
		java.lang.String codi)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation (grantees={roles.metadata_query.class}, translated="findDataTypeByName")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.TipusDada findTipusDadaByCodi(
		java.lang.String codi)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.user_metadata_update.class,
			roles.user_custom_update.class},
			translated="create")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.DadaUsuari create(
		es.caib.seycon.ng.comu.DadaUsuari dadaUsuari)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.user_metadata_update.class,
			roles.user_custom_update.class},
			translated="delete")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void delete(
		es.caib.seycon.ng.comu.DadaUsuari dadaUsuari)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Operation ( grantees={roles.user_metadata_update.class,
			roles.user_custom_update.class},
			translated="update")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.DadaUsuari update(
		es.caib.seycon.ng.comu.DadaUsuari dadaUsuari)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}

}
