//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.servei;
import org.springframework.transaction.annotation.Transactional;

import com.soffid.mda.annotation.Depends;
import com.soffid.mda.annotation.Nullable;
import com.soffid.mda.annotation.Operation;
import com.soffid.mda.annotation.Service;

@Service (translatedName="ServiceService",
	translatedPackage="com.soffid.iam.service")
@Depends ({es.caib.seycon.ng.model.ServeiEntity.class})
public abstract class ServeiService {

	@Operation ( grantees={roles.service_create.class},
			translated="create")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.Servei create(
		es.caib.seycon.ng.comu.Servei servei)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.service_delete.class},
			translated="delete")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void delete(
		es.caib.seycon.ng.comu.Servei servei)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Operation ( grantees={roles.service_update.class},
			translated="update")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.Servei update(
		es.caib.seycon.ng.comu.Servei servei)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.service_query.class},
			translated="findServicesByCriteria")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.Servei> findServeisByCriteri(
		@Nullable java.lang.String codi, 
		@Nullable java.lang.String descripcio)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation (translated="findServiceByName")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.Servei findServeiByCodi(
		java.lang.String codi)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation (translated="getServices")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.Servei> getServeis()
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
}
