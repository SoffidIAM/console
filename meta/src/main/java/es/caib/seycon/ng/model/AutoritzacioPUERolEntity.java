//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;

import com.soffid.mda.annotation.*;

@Entity(table = "SC_ROLPUE", translatedName = "EntryPointRoleEntity", translatedPackage = "com.soffid.iam.model")
@Depends({ es.caib.seycon.ng.comu.AutoritzacioPuntEntrada.class,
		es.caib.seycon.ng.model.RolEntity.class,
		es.caib.seycon.ng.model.AuditoriaEntity.class,
		es.caib.seycon.ng.model.PuntEntradaEntity.class })
public abstract class AutoritzacioPUERolEntity {

	@Column(name = "RPE_IDPUE", translated = "entryPoint")
	public es.caib.seycon.ng.model.PuntEntradaEntity puntEntrada;

	@Column(name = "RPE_NIVAUT", length = 1, translated = "authorizationLevel")
	public java.lang.String nivellAutoritzacio;

	@Column(name = "RPE_ID")
	@Identifier
	public java.lang.Long id;

	@Column(name = "RPE_IDROL")
	public RolEntity role;

	@Operation(translated = "findByRoleId")
	@DaoFinder("select autor from com.soffid.iam.model.EntryPointRoleEntity autor where autor.role.id=:idRol")
	public java.util.List<AutoritzacioPUERolEntity> findByIdRol(
			java.lang.Long idRol) {
		return null;
	}
}

@Index (name="PUE_ROL_2",	unique=false,
entity=es.caib.seycon.ng.model.AutoritzacioPUERolEntity.class,
columns={"RPE_IDROL"})
 abstract class AutoritzacioPUERolIndex {
}

