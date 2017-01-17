package com.soffid.iam.model;

import com.soffid.mda.annotation.Column;
import com.soffid.mda.annotation.DaoFinder;
import com.soffid.mda.annotation.Depends;
import com.soffid.mda.annotation.Entity;
import com.soffid.mda.annotation.Identifier;

import es.caib.seycon.ng.model.AccountEntity;

@Entity (table="SC_ACCPUE" )
@Depends ({es.caib.seycon.ng.comu.AutoritzacioPuntEntrada.class,
	es.caib.seycon.ng.model.RolEntity.class,
	es.caib.seycon.ng.model.AuditoriaEntity.class,
	es.caib.seycon.ng.model.PuntEntradaEntity.class})
public class EntryPointAccountEntity {

	@Column (name="CPE_IDPUE", composition=true)
	public es.caib.seycon.ng.model.PuntEntradaEntity entryPoint;

	@Column (name="RPC_NIVAUT", length=1)
	public java.lang.String authorizationlevel;

	@Column (name="RPC_ID")
	@Identifier
	public java.lang.Long id;

	@Column (name="RPC_IDROL", composition=true)
	public AccountEntity account;

	public java.util.List<EntryPointAccountEntity> findAll() {
	 return null;
	}
	@DaoFinder("select auth.entryPoint "
			+ "from com.soffid.iam.model.EntryPointAccountEntity auth "
			+ "where auth.account.id=:accountId")
	public java.util.List<es.caib.seycon.ng.model.AutoritzacioPUERolEntity> findByAccountId(
		java.lang.Long accountId) {
	 return null;
	}

}
