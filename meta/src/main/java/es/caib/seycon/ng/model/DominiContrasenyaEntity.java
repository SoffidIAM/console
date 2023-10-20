//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import java.util.List;

import com.soffid.iam.model.TenantEntity;
import com.soffid.mda.annotation.*;

@Entity (table="SC_DOMCON", translatedName="PasswordDomainEntity", translatedPackage="com.soffid.iam.model" )
@Depends ({es.caib.seycon.ng.model.PoliticaContrasenyaEntity.class,
	es.caib.seycon.ng.comu.DominiContrasenya.class,
	es.caib.seycon.ng.model.DominiUsuariEntity.class,
	es.caib.seycon.ng.model.AuditoriaEntity.class,
	es.caib.seycon.ng.model.DispatcherEntity.class})
public abstract class DominiContrasenyaEntity {

	@Column (name="DCN_ID")
	@Identifier
	public java.lang.Long id;

	@Column (name="DCN_CODI", length=50, translated="name")
	public java.lang.String codi;

	@Column (name="DCN_DESC", length=100, translated="description")
	@Nullable
	public java.lang.String descripcio;
	
	@Column (name="DCN_TEN_ID")
	TenantEntity tenant;

	@ForeignKey (foreignColumn="PCD_DCN_ID", translated="passwordPolicies")
	public java.util.Collection<es.caib.seycon.ng.model.PoliticaContrasenyaEntity> politicaContrasenyes;

	@ForeignKey (foreignColumn="DIS_DCN_ID", translated="systems")
	public java.util.Collection<es.caib.seycon.ng.model.DispatcherEntity> dispatchers;

	@Operation(translated="findByName")
	@DaoFinder
	public es.caib.seycon.ng.model.DominiContrasenyaEntity findByCodi(
		java.lang.String name) {
	 return null;
	}
	@DaoFinder("select pd "
			+ "from com.soffid.iam.model.UserEntity as usuari "
			+ "inner join usuari.userType as tipus "
			+ "inner join tipus.policies as politica  with politica.type='M'  "
			+ "inner join politica.passwordDomain pd "
			+ "where usuari.id= :userId and pd.tenant.id = :tenantId")
	public es.caib.seycon.ng.model.DominiContrasenyaEntity findDefaultDomain(
		long userId) {
	 return null;
	}
	
	@Operation(translated="findBySystem")
	@DaoFinder("select de.passwordDomain "
			+ "from com.soffid.iam.model.SystemEntity as de "
			+ "where de.name=:systemName and de.tenant.id = :tenantId")
	public es.caib.seycon.ng.model.DominiContrasenyaEntity findByDispatcher(
		java.lang.String systemName) {
	 return null;
	}

	@DaoFinder("select distinct pd "
			+ "from com.soffid.iam.model.UserEntity as user "
			+ "join user.accounts as ua "
			+ "join ua.account.system.passwordDomain as pd "
			+ "where pd.tenant.id =:tenantId and user.id=:id")
	public List<es.caib.seycon.ng.model.DominiContrasenyaEntity> findByUser(
		Long id) {
	 return null;
	}
}

@Index(name="SC_DOMCON_TEN_NDX", entity=DominiContrasenyaEntity.class, columns={"DCN_TEN_ID", "DCN_CODI"}, unique=true)
class DominiContrasenyEntityIndex {
	
}