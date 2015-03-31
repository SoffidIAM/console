//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.mda.annotation.*;

@Entity (table="SC_USUACC" , translatedName="UserAccountEntity", translatedPackage="com.soffid.iam.model")
@Depends ({es.caib.seycon.ng.comu.UserAccount.class,
	es.caib.seycon.ng.model.TasqueEntity.class,
	es.caib.seycon.ng.model.UsuariEntity.class,
	es.caib.seycon.ng.model.AccountEntity.class})
public abstract class UserAccountEntity {

	@Column (name="UAC_USU_ID")
	public es.caib.seycon.ng.model.UsuariEntity user;

	@Column (name="UAC_ACC_ID")
	public es.caib.seycon.ng.model.AccountEntity account;

	@Column (name="UAC_ID")
	@Identifier
	public java.lang.Long id;

	@Column (name="UAC_UNTIL")
	@Nullable
	public java.util.Date untilDate;

	@Operation(translated="findByAccountSystemAndName")
	@DaoFinder("select uae\nfrom  es.caib.seycon.ng.model.UserAccountEntity as uae\nwhere uae.user.codi=:user and uae.account.name=:account and uae.account.dispatcher.codi=:dispatcher")
	public es.caib.seycon.ng.model.UserAccountEntity findByAccountDispatcherAndName(
		java.lang.String account, 
		java.lang.String dispatcher, 
		java.lang.String user) {
	 return null;
	}
	@Operation(translated="findByUserAndDispatcher")
	@DaoFinder("select uae\nfrom  es.caib.seycon.ng.model.UserAccountEntity uae\nwhere uae.user.codi=:user and uae.account.dispatcher.codi=:dispatcher and uae.account.type = 'U'")
	public java.util.List<es.caib.seycon.ng.model.UserAccountEntity> findByUserAndDispatcher(
		java.lang.String user, 
		java.lang.String dispatcher) {
	 return null;
	}
	@DaoOperation
	public void propagateChanges(
		es.caib.seycon.ng.model.UserAccountEntity account)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
}
