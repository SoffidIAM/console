//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.servei;
import com.soffid.mda.annotation.*;

import org.springframework.transaction.annotation.Transactional;

@Service (translatedName="OrganizationalUnitTypeService",
	translatedPackage="com.soffid.iam.service")
@Depends ({es.caib.seycon.ng.model.TipusUnitatOrganitzativaEntity.class,
	es.caib.seycon.ng.comu.TipusUnitatOrganitzativa.class})
public abstract class TipusUnitatOrganitzativaService {

	@Operation ( grantees={Roles.organizationalUnit_create.class},
			translated="create")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.TipusUnitatOrganitzativa create(
		es.caib.seycon.ng.comu.TipusUnitatOrganitzativa tipus)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={Roles.organizationalUnit_delete.class},
			translated="delete")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void delete(
		es.caib.seycon.ng.comu.TipusUnitatOrganitzativa tipus)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Operation ( grantees={Roles.organizationalUnit_update.class},
			translated="update")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.TipusUnitatOrganitzativa update(
		es.caib.seycon.ng.comu.TipusUnitatOrganitzativa tipus)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation (translated="findOUTypeByName")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.TipusUnitatOrganitzativa findTipusUnitatOrganitzativaByCodi(
		java.lang.String CodiTipusUnitatOrganitzativa)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={Roles.organizationalUnit_query.class},
	translated="findOUTypeByFilter")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection findTipusUnitatOrganitzativaByFiltre(
		@Nullable java.lang.String codi, 
		@Nullable java.lang.String descripcio)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
}
