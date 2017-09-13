//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import java.util.Collection;
import java.util.List;

import com.soffid.mda.annotation.*;

import es.caib.seycon.ng.comu.Domini;

@Entity (table="SC_VALOR_DOMINI", translatedName="DomainValueEntity", translatedPackage="com.soffid.iam.model"  )
@Depends ({es.caib.seycon.ng.comu.ValorDomini.class,
	es.caib.seycon.ng.model.DominiAplicacioEntity.class,
	es.caib.seycon.ng.model.AuditoriaEntity.class,
	es.caib.seycon.ng.model.RolAccountEntity.class})
public abstract class ValorDominiAplicacioEntity {

	@Column (name="VDO_VALOR", length=30, translated="value")
	public java.lang.String valor;

	@Column (name="VDO_DOM", translated="domain")
	public es.caib.seycon.ng.model.DominiAplicacioEntity domini;

	@Column (name="VDO_ID")
	@Identifier
	public java.lang.Long id;

	@Column (name="VDO_DESC", length=50, translated="description")
	public java.lang.String descripcio;

	@Operation(translated="findByRoleAndValue")
	@DaoFinder("select valorDominiAplicacio "
			+ "from com.soffid.iam.model.DomainValueEntity valorDominiAplicacio \n"
			+ "join valorDominiAplicacio.domain domini\n"
			+ "join valorDominiAplicacio.domain.roles rol\n"
			+ "where \n"
				+ "rol.id = :roleId and \n"
				+ "valorDominiAplicacio.value = :value")
	public es.caib.seycon.ng.model.ValorDominiAplicacioEntity findValorDominiByNomDominiAndNomRolDominiAndValorDomini(
		java.lang.Long roleId, 
		java.lang.String value) {
	 return null;
	}
	
	@DaoFinder("select value "
			+ "from com.soffid.iam.model.DomainValueEntity as value \n"
			+ "left join value.domain as domain\n"
			+ "left join domain.informationSystem as app\n"
			+ "where \n"
				+ "app.name = :app and "
				+ "app.tenant.id = :tenantId and "
				+ "domain.name = :domain and "
				+ "value.value = :value")
	public es.caib.seycon.ng.model.ValorDominiAplicacioEntity findByApplicationDomainValue(
		java.lang.String app, 
		java.lang.String domain, 
		java.lang.String value) {
	 return null;
	}
	
	@DaoFinder("select value "
			+ "from com.soffid.iam.model.DomainValueEntity as value \n"
			+ "left join value.domain as domain\n"
			+ "left join domain.informationSystem as app\n"
			+ "where \n"
				+ "app.name = :informationSystem and "
				+ "app.tenant.id = :tenantId and\n"
				+ "domain.name = :domain")
	public List<ValorDominiAplicacioEntity> findByInformationSystem(
		java.lang.String informationSystem, 
		java.lang.String domain) {
	 return null;
	}

	@DaoFinder("-")
	public Collection<ValorDominiAplicacioEntity>findByText (Domini domain, String text) { return null; }
}

@Index (name="UNIQUE_VALOR_DOMINI",	unique=true,
entity=es.caib.seycon.ng.model.ValorDominiAplicacioEntity.class,
columns={"VDO_VALOR", "VDO_DOM"})
abstract class ValorDominiUniqueIndex {
}

