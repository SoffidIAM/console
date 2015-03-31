//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.mda.annotation.*;

@Entity (table="SC_SSO", translatedName="SsoEntity", translatedPackage="com.soffid.iam.model" )
@Depends ({es.caib.seycon.ng.model.ServeiEntity.class,
	es.caib.seycon.ng.model.GrupEntity.class,
	es.caib.seycon.ng.model.MaquinaEntity.class,
	es.caib.seycon.ng.model.UsuariEntity.class})
public abstract class SsoEntity {

	@Column (name="SSO_ID")
	@Identifier
	public java.lang.Long id;

	@Column (name="SSO_ORDRE", translated="order")
	public java.lang.Long ordre;

	@Column (name="SSO_NOMAPL", length=250, translated="applicationName")
	@Nullable
	public java.lang.String nomAplicacio;

	@Column (name="SSO_SOCKET")
	public java.lang.Long socket;

	@Column (name="SSO_TIPUS", length=1, translated="type")
	@Nullable
	public java.lang.String tipus;

	@Column (name="SSO_IDMAQ", translated="host")
	public es.caib.seycon.ng.model.MaquinaEntity maquina;

}
