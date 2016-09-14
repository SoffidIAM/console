//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package com.soffid.iam.model;
import com.soffid.mda.annotation.*;

import es.caib.seycon.ng.comu.AccountAccessLevelEnum;

@Entity (table="SC_VAFCACC" )
@Description ("Contains the access control list for a vault folder")
@Depends ({es.caib.seycon.ng.model.AuditoriaEntity.class,
	es.caib.seycon.ng.model.RolEntity.class,
	es.caib.seycon.ng.model.GrupEntity.class,
	es.caib.seycon.ng.model.UsuariEntity.class,
	es.caib.seycon.ng.model.AccountEntity.class})
public abstract class VaultFolderAccessEntity {

	@Description ("Grantee role")
	@Column (name="VFA_ROL_ID")
	@Nullable
	public es.caib.seycon.ng.model.RolEntity rol;

	@Description ("Grantee group")
	@Column (name="VFA_GRU_ID")
	@Nullable
	public es.caib.seycon.ng.model.GrupEntity group;

	@Description ("Grantee user")
	@Column (name="VFA_USU_ID")
	@Nullable
	public es.caib.seycon.ng.model.UsuariEntity user;

	@Description ("Granted folder")
	@Column (name="VFA_VAF_ID", reverseAttribute="acl", cascadeDelete=true)
	public VaultFolderEntity vault;

	@Description ("Access level")
	@Column (name="VFA_LEVEL", defaultValue=" es.caib.seycon.ng.comu.AccountAccessLevelEnum.ACCESS_OWNER")
	public AccountAccessLevelEnum level;
	
	@Column (name="VFA_ID")
	@Nullable
	@Identifier
	@Description("Identifier")
	public java.lang.Long id;

}
