//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
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

	@Operation(translated="findByUserAndGroup")
	@DaoFinder("select usuariGrup "
			+ "from com.soffid.iam.model.UserGroupEntity usuariGrup "
			+ "where usuariGrup.group.name = :groupName and "
			+ "usuariGrup.user.userName = :userName \n"
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
			+ "usuariGrup.user.userName = :userName \n"
			+ "order by usuariGrup.user.userName, usuariGrup.group.name")
	public java.util.List<es.caib.seycon.ng.model.UsuariGrupEntity> findByCodiUsuari(
		java.lang.String userName) {
	 return null;
	}
	@Operation(translated="findByGroupName")
	@DaoFinder("select usuariGrup "
			+ "from com.soffid.iam.model.UserGroupEntity usuariGrup "
			+ "where usuariGrup.group.name = :groupName "
			+ "order by usuariGrup.user.userName, usuariGrup.group.name")
	public java.util.List<es.caib.seycon.ng.model.UsuariGrupEntity> findByCodiGrup(
		java.lang.String groupName) {
	 return null;
	}
}
