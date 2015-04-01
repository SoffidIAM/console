//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.mda.annotation.*;

@Entity (table="SC_USUSEU", translatedName="UserPreferencesEntity", translatedPackage="com.soffid.iam.model"  )
@Depends ({es.caib.seycon.ng.model.UsuariEntity.class,
	es.caib.seycon.ng.comu.UsuariSEU.class})
public abstract class UsuariSEUEntity {

	@Column (name="USE_ID")
	@Identifier
	public java.lang.Long id;

	@Column (name="USE_DATSEU", translated="lastLoginData")
	public java.util.Date dataDarrerLogin;

	@Column (name="USE_VERSEU", length=50, translated="version")
	public java.lang.String versio;

	@Column (name="USE_FAVS", length=1024, translated="preferences")
	@Nullable
	public java.lang.String preferencies;

	@Column (name="USE_USUID", translated="user")
	public es.caib.seycon.ng.model.UsuariEntity usuari;

	@Operation(translated="findByUserCode")
	@DaoFinder("select ususeu from es.caib.seycon.ng.model.UsuariSEUEntity ususeu  left join ususeu.usuari usu where (:codiUsuari is null or usu.codi = :codiUsuari)")
	public es.caib.seycon.ng.model.UsuariSEUEntity findByCodiUsuari(
		java.lang.String codiUsuari) {
	 return null;
	}
}
