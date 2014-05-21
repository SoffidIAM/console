//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.mda.annotation.*;

@Entity (table="SC_SSO" )
@Depends ({es.caib.seycon.ng.model.ServeiEntity.class,
	es.caib.seycon.ng.model.GrupEntity.class,
	es.caib.seycon.ng.model.MaquinaEntity.class,
	es.caib.seycon.ng.model.UsuariEntity.class})
public abstract class SsoEntity {

	@Column (name="SSO_ID")
	@Identifier
	public java.lang.Long id;

	@Column (name="SSO_ORDRE")
	public java.lang.Long ordre;

	@Column (name="SSO_NOMAPL", length=250)
	@Nullable
	public java.lang.String nomAplicacio;

	@Column (name="SSO_SOCKET")
	public java.lang.Long socket;

	@Column (name="SSO_TIPUS", length=1)
	@Nullable
	public java.lang.String tipus;

	@Column (name="SSO_IDMAQ")
	public es.caib.seycon.ng.model.MaquinaEntity maquina;

}
