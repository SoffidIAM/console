//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import java.util.Date;

import com.soffid.mda.annotation.*;

import es.caib.seycon.ng.comu.AccountAccessLevelEnum;

@Entity (table="SC_ACCACC", translatedName="AccountAccessEntity", translatedPackage="com.soffid.iam.model")
@Description ("Contains the access control list for an account")
@Depends ({es.caib.seycon.ng.model.AuditoriaEntity.class,
	es.caib.seycon.ng.model.RolEntity.class,
	es.caib.seycon.ng.model.GrupEntity.class,
	es.caib.seycon.ng.model.UsuariEntity.class,
	es.caib.seycon.ng.model.AccountEntity.class})
public abstract class AccountAccessEntity {

	@Description ("Grantee role")
	@Column (name="AAC_ROL_ID", composition=true)
	@Nullable
	public es.caib.seycon.ng.model.RolEntity role;

	@Description ("Grantee group")
	@Column (name="AAC_GRU_ID", composition=true)
	@Nullable
	public es.caib.seycon.ng.model.GrupEntity group;

	@Description ("Grantee user")
	@Column (name="AAC_USU_ID", composition=true)
	@Nullable
	public es.caib.seycon.ng.model.UsuariEntity user;

	@Description ("Granted accounte")
	@Column (name="AAC_ACC_ID", composition=true)
	public es.caib.seycon.ng.model.AccountEntity account;

	@Description ("Access level")
	@Column (name="AAC_LEVEL", defaultValue=" es.caib.seycon.ng.comu.AccountAccessLevelEnum.ACCESS_OWNER")
	public AccountAccessLevelEnum level;
	
	@Column (name="AAC_ID")
	@Nullable
	@Identifier
	@Description("Identifier")
	public java.lang.Long id;

	@Column (name="AAC_START")
	@Nullable
	public Date start;

	@Column (name="AAC_END")
	@Nullable
	public Date end;

	@Column (name="AAC_DISABLED")
	@Nullable
	public Boolean disabled;
}

