//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.iam.model.TenantEntity;
import com.soffid.mda.annotation.*;

@Entity (table="SC_BLOCON" , translatedName="BlobConfigurationEntity", translatedPackage="com.soffid.iam.model")
public abstract class BlobConfigurationEntity {

	@Column (name="BCO_ID")
	@Identifier
	public java.lang.Long id;

	@Column (name="BCO_NAME", length=255)
	public java.lang.String name;

	@Column (name="BCO_VALUE", length=10240000)
	public byte[] value;

	@Column (name="BCO_VERSIO", length=50, translated="version")
	@Nullable
	public java.lang.String versio;

	@Column (name="BCO_TEN_ID")
	public TenantEntity tenant;


	@DaoFinder
	public java.util.List<es.caib.seycon.ng.model.BlobConfigurationEntity> findByName(
		java.lang.String name) {
	 return null;
	}

	@DaoFinder ("select b from "
			+ "com.soffid.iam.model.BlobConfigurationEntity as b "
			+ "where b.name=:name and b.tenant.id=:tenantId")
	public java.util.List<es.caib.seycon.ng.model.BlobConfigurationEntity> findByNameAndTenant(
		java.lang.String name, Long tenantId) {
	 return null;
	}

	@DaoFinder ("select b from "
			+ "com.soffid.iam.model.BlobConfigurationEntity as b "
			+ "where b.name=:name and b.tenant.name=:tenantName")
	public java.util.List<es.caib.seycon.ng.model.BlobConfigurationEntity> findByNameAndTenantName(
		java.lang.String name, String tenantName) {
	 return null;
	}
}
