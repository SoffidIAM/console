//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.mda.annotation.*;

@Entity (table="SC_ACCPWD" )
@Depends ({es.caib.seycon.ng.comu.EstatContrasenya.class,
	es.caib.seycon.ng.model.AccountEntity.class})
public abstract class AccountPasswordEntity {

	@Column (name="APW_ID")
	@Identifier
	public java.lang.Long id;

	@Column (name="APW_PASSWD", length=255)
	public java.lang.String password;

	@Column (name="APW_ORDER")
	public java.lang.Long order;

	@Column (name="APW_DATE")
	public java.util.Date date;

	@Column (name="APW_EXPDAT")
	public java.util.Date expirationDate;

	@Column (name="APW_ACTIVE", length=255)
	public java.lang.String active;

	@Column (name="APW_ACC_ID")
	public es.caib.seycon.ng.model.AccountEntity account;

	@DaoFinder("select pwd\nfrom es.caib.seycon.ng.model.AccountPasswordEntity as pwd\nwhere pwd.account.id = :accountId\nand pwd.order=0")
	public es.caib.seycon.ng.model.AccountPasswordEntity findLastByAccount(
		long accountId) {
	 return null;
	}
}
