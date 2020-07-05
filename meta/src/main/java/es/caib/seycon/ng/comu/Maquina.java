//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import com.soffid.mda.annotation.*;

import es.caib.seycon.ng.model.MaquinaEntity;
import es.caib.seycon.ng.model.UsuariEntity;

@JsonObject (hibernateClass=MaquinaEntity.class)
@ValueObject ( translatedName="Host",
	 translatedPackage="com.soffid.iam.api")
public class Maquina {

	@Nullable
	@Attribute(hidden = true)
	public java.lang.Long id;

	@Attribute(translated = "name", searchCriteria = true )
	public java.lang.String nom;

	@Nullable
	@Attribute(translated = "description", searchCriteria = true )
	public java.lang.String descripcio;
	
	@Nullable
	@Attribute(translated = "networkCode" )
	public java.lang.String codiXarxa;
	
	@Nullable
	@Attribute(translated = "ip", searchCriteria = true )
	public java.lang.String adreca;
	
	@Attribute(translated = "os" )
	public java.lang.String sistemaOperatiu;

	@Nullable
	public java.lang.String dhcp;

	@Nullable
	@Attribute(translated = "mail" )
	public java.lang.Boolean correu;

	@Nullable
	@Attribute(translated = "office" )
	public java.lang.Boolean ofimatica;

	@Nullable
	public java.lang.String mac;

	@Nullable
	@Attribute(translated = "hostAlias" )
	public java.lang.String aliasMaquina;

	@Nullable
	@Attribute(translated = "printersServer" )
	public java.lang.Boolean servidorImpressores;

	@Nullable
	@Attribute(readonly = true)
	public java.lang.String serialNumber;

	@Nullable
	public java.lang.Boolean dynamicIp;

	@Nullable
	@Attribute(readonly = true)
	public java.util.Calendar lastSeen;

}
