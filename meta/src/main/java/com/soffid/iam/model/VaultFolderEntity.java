package com.soffid.iam.model;

import java.util.List;

import com.soffid.iam.api.VaultFolder;
import com.soffid.iam.service.ACLService;
import com.soffid.mda.annotation.Column;
import com.soffid.mda.annotation.DaoFinder;
import com.soffid.mda.annotation.Depends;
import com.soffid.mda.annotation.Entity;
import com.soffid.mda.annotation.ForeignKey;
import com.soffid.mda.annotation.Identifier;
import com.soffid.mda.annotation.Nullable;

import es.caib.seycon.ng.model.GrupEntity;
import es.caib.seycon.ng.model.RolEntity;
import es.caib.seycon.ng.model.UsuariEntity;
import es.caib.seycon.ng.servei.UsuariService;

@Entity (table="SC_VAUFOL")
@Depends({VaultFolder.class,
	GrupEntity.class,
	RolEntity.class,
	UsuariEntity.class,
	ACLService.class,
	UsuariService.class})
public class VaultFolderEntity {
	@Identifier
	@Column(name="VAF_ID")
	Long id;
	
	@Column(name="VAF_NAME", length=256)
	String name;
	
	@Column(name="VAF_DESCRI", length=1024)
	String description;
	
	@Nullable
	@Column(name="VAF_PARENT", reverseAttribute="children")
	VaultFolderEntity parent;
	
	@Nullable
	@Column(name="VAF_PERSON", defaultValue="false")
	Boolean personal;

	@Nullable
	@Column(name="VAF_PPO_ID", reverseAttribute = "vaultFolders")
	PamPolicyEntity pamPolicy;
	
	@Column(name="VAF_TEN_ID")
	TenantEntity tenant;
	
	@DaoFinder("select distinct v from com.soffid.iam.model.VaultFolderEntity as v "
			+ "where v.parent is null and tenant.id=:tenantId")
	public List<VaultFolderEntity> findRoots () { return null; }

	@DaoFinder("select distinct v from com.soffid.iam.model.VaultFolderEntity as v "
			+ "where v.parent is null and v.personal = false and tenant.id=:tenantId")
	public List<VaultFolderEntity> findPublicRoots () { return null; }


	public List<VaultFolderEntity> findByParent (VaultFolderEntity parent) { return null; }

	@DaoFinder("select distinct v from com.soffid.iam.model.VaultFolderEntity as v "
			+ "where v.name like :name and tenant.id=:tenantId")
	public List<VaultFolderEntity> findByName (String name) { return null; }

	@DaoFinder("select distinct v from com.soffid.iam.model.VaultFolderEntity as v "
			+ "join v.acl as acl "
			+ "join acl.user as user "
			+ "where v.parent is null and v.personal = true and user.userName=:user and user.tenant.id=:tenantId")
	public List<VaultFolderEntity> findPersonalFolders (String user) { return null; }
}
