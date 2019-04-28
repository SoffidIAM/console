//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import java.util.Date;

import com.soffid.mda.annotation.*;

@Entity (table="SC_USUGRU", translatedName="UserGroupEntity", translatedPackage="com.soffid.iam.model" )
@Depends ({es.caib.seycon.ng.model.RolsGrupEntity.class,
	LlistaCorreuEntity.class,
	es.caib.seycon.ng.comu.UsuariGrup.class,
	es.caib.seycon.ng.model.GrupEntity.class,
	es.caib.seycon.ng.model.UsuariEntity.class,
	es.caib.seycon.ng.model.AuditoriaEntity.class,
	es.caib.seycon.ng.model.TasqueEntity.class})
public abstract class UsuariGrupEntity {

	@Column (name="UGR_IDUSU", translated="user")
	@Nullable
	public es.caib.seycon.ng.model.UsuariEntity usuari;

	@Column (name="UGR_IDGRU", translated="group")
	@Nullable
	public es.caib.seycon.ng.model.GrupEntity grup;

	@Column (name="UGR_ID")
	@Identifier
	public java.lang.Long id;

	@Column (name="UGR_START")
	@Nullable
	public Date start;

	@Column (name="UGR_END")
	@Nullable
	public Date end;

	@Column (name="UGR_DISABLED")
	@Nullable
	public Boolean disabled;

	@Column (name="UGR_PRIGRP")
	@Description("This column indicates that this membership is an historic snapshot of a primary group membership")
	@Nullable
	public Boolean primaryGroup;

	@Operation(translated="findByUserAndGroup")
	@DaoFinder("select usuariGrup "
			+ "from com.soffid.iam.model.UserGroupEntity usuariGrup "
			+ "where usuariGrup.group.name = :groupName and "
			+ "usuariGrup.user.userName = :userName and "
			+ "usuariGrup.user.tenant.id = :tenantId and "
			+ "usuariGrup.disabled = false "
			+ "order by usuariGrup.user.userName, usuariGrup.group.name")
	public es.caib.seycon.ng.model.UsuariGrupEntity findByCodiUsuariAndCodiGrup(
		java.lang.String userName, 
		java.lang.String groupName) {
	 return null;
	}
	@Operation(translated="findByUserName")
	@DaoFinder("select usuariGrup "
			+ "from com.soffid.iam.model.UserGroupEntity usuariGrup "
			+ "where "
			+ "usuariGrup.user.userName = :userName and "
			+ "usuariGrup.user.tenant.id = :tenantId and "
			+ "usuariGrup.disabled = false "
			+ "order by usuariGrup.user.userName, usuariGrup.group.name")
	public java.util.List<es.caib.seycon.ng.model.UsuariGrupEntity> findByCodiUsuari(
		java.lang.String userName) {
	 return null;
	}
	@Operation(translated="findByGroupName")
	@DaoFinder("select usuariGrup "
			+ "from com.soffid.iam.model.UserGroupEntity usuariGrup "
			+ "where usuariGrup.group.name = :groupName and "
			+ "usuariGrup.disabled = false and "
			+ "usuariGrup.group.tenant.id = :tenantId "
			+ "order by usuariGrup.user.userName, usuariGrup.group.name")
	public java.util.List<es.caib.seycon.ng.model.UsuariGrupEntity> findByCodiGrup(
		java.lang.String groupName) {
	 return null;
	}

	@Description("Returns true if the permission on this object is granted")
	public boolean isAllowed(String permission) { return false; }

	public void customCache() {
	}
}
