//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import java.util.Date;

import com.soffid.mda.annotation.*;

@Entity (table="SC_USULCO" , translatedName="UserEmailEntity", translatedPackage="com.soffid.iam.model")
@Depends ({es.caib.seycon.ng.model.AuditoriaEntity.class,
	es.caib.seycon.ng.model.LlistaCorreuEntity.class,
	es.caib.seycon.ng.model.UsuariEntity.class,
	es.caib.seycon.ng.comu.LlistaCorreuUsuari.class,
	es.caib.seycon.ng.model.TasqueEntity.class})
public abstract class LlistaCorreuUsuariEntity {

	@Column (name="ULC_IDLCO", translated="mailList")
	public es.caib.seycon.ng.model.LlistaCorreuEntity llistaDeCorreu;

	@Column (name="ULC_IDUSU", translated="user")
	public es.caib.seycon.ng.model.UsuariEntity usuari;

	@Column (name="ULC_ID")
	@Identifier
	public java.lang.Long id;

	@Column (name="ULC_START")
	@Nullable
	public Date start;

	@Column (name="ULC_END")
	@Nullable
	public Date end;

	@Column (name="ULC_DISABLED")
	@Nullable
	public Boolean disabled;

	@Operation(translated="findByListAndUser")
	@DaoFinder("select liu "
			+ "from com.soffid.iam.model.UserEmailEntity liu "
			+ "left join liu.mailList.domain as dominiCorreu "
			+ "where liu.mailList.name = :mailList and "
			+ "  liu.mailList.tenant.id = :tenantId and "
			+ " ((:domain is null and dominiCorreu is null) or "
			+ " ( :domain is not null and dominiCorreu is not null and dominiCorreu.name = :domain)) and"
			+ " liu.user.userName = :user and liu.disabled = false")
	public es.caib.seycon.ng.model.LlistaCorreuUsuariEntity findByNomLlistaCorreuAndCodiDominiAndCodiUsuari(
		java.lang.String mailList, 
		java.lang.String domain, 
		java.lang.String user) {
	 return null;
	}
	@Operation(translated="findByUser")
	@DaoFinder("select liu "
			+ "from  com.soffid.iam.model.UserEmailEntity liu "
			+ "where liu.user.userName = :user and liu.user.tenant.id = :tenantId and liu.disabled = false "
			+ "order by liu.mailList.name, liu.mailList.domain.name")
	public java.util.List<es.caib.seycon.ng.model.LlistaCorreuUsuariEntity> findByCodiUsuari(
		java.lang.String user) {
	 return null;
	}
	@Operation(translated="findByMailList")
	@DaoFinder("select liu "
			+ "from  com.soffid.iam.model.UserEmailEntity liu "
			+ "left join liu.mailList.domain as dominiCorreu "
			+ "where liu.mailList.name = :mailList and "
			+ "liu.mailList.tenant.id = :tenantId and "
			+ "liu.disabled = false and "
			+ "((:domain is null and dominiCorreu is null) or "
			+ " ( :domain is not null and dominiCorreu is not null and dominiCorreu.name = :domain))")
	public java.util.List<es.caib.seycon.ng.model.LlistaCorreuUsuariEntity> findByNomLlistaCorreuAndCodiDomini(
		java.lang.String mailList, 
		java.lang.String domain) {
	 return null;
	}
	
}
