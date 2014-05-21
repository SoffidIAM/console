//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import com.soffid.mda.annotation.*;

@ValueObject ( translatedName="AccessControl",
	 translatedPackage="com.soffid.iam.api")
public abstract class ControlAcces {

	@Nullable
	public java.lang.Long id;

	@Nullable
	@Attribute(translated = "agentId" )
	public java.lang.Long idAgent;

	@Nullable
	@Attribute(translated = "agentName" )
	public java.lang.String nomAgent;

	@Nullable
	@Attribute(translated = "roleDescription" )
	public java.lang.String descripcioRol;

	@Nullable
	@Attribute(translated = "roleId" )
	public java.lang.Long idRol;

	@Nullable
	@Attribute(translated = "hostName" )
	public java.lang.String nomMaquina;

	@Nullable
	@Attribute(translated = "hostId" )
	public java.lang.Long idMaquina;

	@Nullable
	public java.lang.String program;

	@Nullable
	@Attribute(translated = "genericUser" )
	public java.lang.String usuariGeneric;

	@Nullable
	@Attribute(translated = "genericHost" )
	public java.lang.String maquinaGeneric;

	@Nullable
	@Attribute(translated = "remoteIp" )
	public java.lang.String ipsPropagades;

}
