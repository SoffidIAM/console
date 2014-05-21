//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.mda.annotation.*;

@Entity (table="SC_ACCACC" )
@Depends ({es.caib.seycon.ng.model.AuditoriaEntity.class,
	es.caib.seycon.ng.model.RolEntity.class,
	es.caib.seycon.ng.model.GrupEntity.class,
	es.caib.seycon.ng.model.UsuariEntity.class,
	es.caib.seycon.ng.model.AccountEntity.class})
public abstract class AccountAccessEntity {

	@Column (name="AAC_ROL_ID")
	@Nullable
	public es.caib.seycon.ng.model.RolEntity rol;

	@Column (name="AAC_GRU_ID")
	@Nullable
	public es.caib.seycon.ng.model.GrupEntity group;

	@Column (name="AAC_USU_ID")
	@Nullable
	public es.caib.seycon.ng.model.UsuariEntity user;

	@Column (name="AAC_ACC_ID")
	public es.caib.seycon.ng.model.AccountEntity account;

	@Column (name="AAC_ID")
	@Nullable
	@Identifier
	public java.lang.Long id;

}
