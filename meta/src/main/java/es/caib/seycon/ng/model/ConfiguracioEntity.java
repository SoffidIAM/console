//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import java.util.List;

import com.soffid.iam.model.TenantEntity;
import com.soffid.mda.annotation.*;

@Entity (table="SC_CONFIG", translatedName="ConfigEntity", translatedPackage="com.soffid.iam.model" )
@Depends ({es.caib.seycon.ng.comu.Configuracio.class,
	es.caib.seycon.ng.model.XarxaEntity.class,
	es.caib.seycon.ng.model.AuditoriaEntity.class})
public abstract class ConfiguracioEntity {

	@Column (name="CON_ID")
	@Identifier
	public java.lang.Long id;

	@Column (name="CON_CODI", length=50, translated="name")
	public java.lang.String codi;

	@Column (name="CON_VALOR", length=500, translated="value")
	public java.lang.String valor;

	@Column (name="CON_IDXAR", translated="network")
	@Nullable
	public es.caib.seycon.ng.model.XarxaEntity xarxa;

	@Column (name="CON_DESC", length=255, translated="description")
	@Nullable
	public java.lang.String descripcio;
	
	@Column (name="CON_TEN_ID")
	public TenantEntity tenant;

	@Operation(translated="findByCodeAndNetworkCode")
	@DaoFinder("select configuracio  \n"
			+ "from com.soffid.iam.model.ConfigEntity as configuracio \n"
			+ "left join configuracio.network as network \n"
			+ "where \n"
			+ "  configuracio.name = :name and \n"
			+ "  configuracio.tenant.id = :tenantId and\n"
			+ " ((:networkName is null and network is null) or (network.name = :networkName))")
	public es.caib.seycon.ng.model.ConfiguracioEntity findByCodiAndCodiXarxa(
		java.lang.String name, 
		java.lang.String networkName) {
	 return null;
	}


	@Operation
	@DaoFinder("select configuracio  \n"
			+ "from com.soffid.iam.model.ConfigEntity as configuracio \n"
			+ "left join configuracio.network as network \n"
			+ "where \n"
			+ "  configuracio.name = :name and \n"
			+ "  configuracio.tenant.name = :tenant and \n"
			+ " ((:networkName is null and network is null) or (network.name = :networkName))")
	public es.caib.seycon.ng.model.ConfiguracioEntity findByTenantNameAndNetwork(
		String tenant,
		java.lang.String name, 
		@Nullable java.lang.String networkName) {
	 return null;
	}

	@DaoFinder(	"select config " + //$NON-NLS-1$
				"from com.soffid.iam.model.ConfigEntity config " + //$NON-NLS-1$
				"left join config.network network "+ //$NON-NLS-1$
				"where " + //$NON-NLS-1$
				"(:name is null or config.name like :name) and " + //$NON-NLS-1$
				"(:network is null or network.name like :network) and " + //$NON-NLS-1$
				"(:value is null or config.value like :value) and " + //$NON-NLS-1$
				"(:description is null or config.description like :description) and \n"+
				"config.tenant.id = :tenantId")
	public List<ConfiguracioEntity> findByFilter(
			java.lang.String name, 
			java.lang.String network,
			String value, String description) {
		 return null;
	}

	@DaoOperation
	public void createMasterConfig(ConfiguracioEntity entity) {}
}
