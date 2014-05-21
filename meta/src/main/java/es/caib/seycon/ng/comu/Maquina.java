//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import com.soffid.mda.annotation.*;

@ValueObject ( translatedName="Host",
	 translatedPackage="com.soffid.iam.api")
public abstract class Maquina {

	@Nullable
	public java.lang.Long id;

	@Attribute(translated = "name" )
	public java.lang.String nom;

	@Attribute(translated = "os" )
	public java.lang.String sistemaOperatiu;

	@Nullable
	@Attribute(translated = "ip" )
	public java.lang.String adreca;

	@Nullable
	@Attribute(translated = "description" )
	public java.lang.String descripcio;

	@Nullable
	public java.lang.String dhcp;

	@Nullable
	@Attribute(translated = "mail" )
	public java.lang.Boolean correu;

	@Nullable
	@Attribute(translated = "office" )
	public java.lang.Boolean ofimatica;

	@Nullable
	@Attribute(translated = "networkCode" )
	public java.lang.String codiXarxa;

	@Nullable
	public java.lang.String mac;

	@Nullable
	@Attribute(translated = "hostAlias" )
	public java.lang.String aliasMaquina;

	@Nullable
	@Attribute(translated = "printersServer" )
	public java.lang.Boolean servidorImpressores;

	@Nullable
	public java.lang.String serialNumber;

	@Nullable
	public java.lang.Boolean dynamicIp;

	@Nullable
	public java.util.Calendar lastSeen;

}
