//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.mda.annotation.*;

@Entity (table="SC_CONFIG", translatedName="ConfigEntity", translatedPackage="com.soffid.iam.model" )
@Depends ({es.caib.seycon.ng.comu.Configuracio.class,
	es.caib.seycon.ng.model.XarxaEntity.class,
	es.caib.seycon.ng.model.AuditoriaEntity.class})
public abstract class ConfiguracioEntity {

	@Column (name="CON_ID")
	@Identifier
	public java.lang.Long id;

	@Column (name="CON_CODI", length=50, translated="code")
	public java.lang.String codi;

	@Column (name="CON_VALOR", length=250, translated="value")
	public java.lang.String valor;

	@Column (name="CON_IDXAR", translated="network")
	@Nullable
	public es.caib.seycon.ng.model.XarxaEntity xarxa;

	@Column (name="CON_DESC", length=255, translated="description")
	@Nullable
	public java.lang.String descripcio;

	@Operation(translated="findByCodeAndNetworkCode")
	@DaoFinder("select configuracio  \nfrom \nes.caib.seycon.ng.model.ConfiguracioEntity configuracio \nleft join configuracio.xarxa as xarxa \nwhere \nconfiguracio.codi = :codi and \n((:codiXarxa is null and xarxa is null) or (xarxa.codi = :codiXarxa))")
	public es.caib.seycon.ng.model.ConfiguracioEntity findByCodiAndCodiXarxa(
		java.lang.String codi, 
		java.lang.String codiXarxa) {
	 return null;
	}
	@DaoFinder
	public java.util.List<es.caib.seycon.ng.model.ConfiguracioEntity> find(
		@Nullable java.util.Collection<es.caib.seycon.ng.model.Parameter> parameters) {
	 return null;
	}
}
