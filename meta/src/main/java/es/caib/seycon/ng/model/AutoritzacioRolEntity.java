//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.mda.annotation.*;

@Entity (table="SC_AUTROL", translatedName="AuthorizationEntity", translatedPackage="com.soffid.iam.model" )
@Depends ({es.caib.seycon.ng.comu.AutoritzacioRol.class,
	es.caib.seycon.ng.model.RolAccountEntity.class,
	es.caib.seycon.ng.model.RolEntity.class,
	es.caib.seycon.ng.model.DominiAplicacioEntity.class,
	es.caib.seycon.ng.model.ValorDominiAplicacioEntity.class,
	es.caib.seycon.ng.model.AuditoriaEntity.class})
public abstract class AutoritzacioRolEntity {

	@Column (name="AUR_ID")
	@Identifier
	public java.lang.Long id;

	@Column (name="AUR_AUTCOD", length=100, translated="authorization")
	public java.lang.String autoritzacio;

	@Column (name="AUR_ROL", translated="role")
	public es.caib.seycon.ng.model.RolEntity rol;

	@Operation(translated="findByAuthorization")
	@DaoFinder("from com.soffid.iam.model.AuthorizationEntity as autoritzacioRolEntity \n"
			+ "where autoritzacioRolEntity.authorization = :authorization \norder by autoritzacioRolEntity.role.name")
	public java.util.List<es.caib.seycon.ng.model.AutoritzacioRolEntity> findByAutoritzacio(
		java.lang.String authorization) {
	 return null;
	}
	@Operation(translated="findByRoleID")
	@DaoFinder("from com.soffid.iam.model.AuthorizationEntity as autoritzacioRolEntity \n"
			+ "where autoritzacioRolEntity.role.id = :roleId\n"
			+ "order by autoritzacioRolEntity.authorization")
	public java.util.List<es.caib.seycon.ng.model.AutoritzacioRolEntity> findByIdRol(
		java.lang.Long roleId) {
	 return null;
	}
}
